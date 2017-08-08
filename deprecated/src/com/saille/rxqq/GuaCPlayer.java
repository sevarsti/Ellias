package com.saille.rxqq;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;

class GuaCPlayer extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private String id;
    private int big;

    public GuaCPlayer(String id, int big) {
        this.id = id;
        this.big = big;
    }

    private void guaCPlayer() {
        try {
            int beibaoSize;
            String ret;
            JSONArray tempItems;
            boolean hasGetBack;
            RxqqInstance.yizhiCount.clear();
            String url;
            while(true) {
                String beibao = RxqqUtils.execute(this.id, "http://s3.qiuqiu.2010.sina.com.cn/Package.do?action=getpackage");
                JSONObject json = new JSONObject(beibao);
                if(!json.has("Items")) {
                    this.LOGGER.warn(this.id + ", key: Items没有对应的内容！");
                    Iterator it = json.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + json.getString(key));
                    }
                }

                JSONArray items = json.getJSONArray("Items");
                int jingcaicount = 0;
                for(int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    if((item.getInt("Type") == 4) && ((item.getInt("CardLevel") == 2) || (item.getInt("CardLevel") == 1)) && (item.getInt("IsBinding") == 0)) {
                        int cardLevel = item.getInt("CardLevel");
                        int strengthen = item.getInt("Strengthen");
                        if((this.big == 1) && (strengthen < 3) && (cardLevel == 2)) {
                            if(jingcaicount < 2) {
                                jingcaicount++;
                                this.LOGGER.info(this.id + "大号的C卡" + item.getString("Name") + "等级为1/2等待竞猜，跳过");
                            } else {
                                this.LOGGER.info("已经有卡进行竞猜，需要拍卖");
                            }
                        } else {
                            if((cardLevel == 2) && (strengthen < 5)) {
                                strengthen = 5;
                            }
                            Integer[] price;
                            if(cardLevel == 1) {
                                price = RxqqInstance.cprice.get("!" + item.getString("Name").trim() + "_" + strengthen);
                            } else {
                                price = RxqqInstance.cprice.get(item.getString("Name").trim() + "_" + strengthen);
                            }
                            if(price == null) {
                                this.LOGGER.info("没有找到球员价格：" + item.getString("Name") + "，跳过");
                            } else {
                                String itemId = item.getString("ItemId");
                                url = String.format("http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&StartPrice=%s&EndPrice=%s&TimeType=2&ItemId=%s", new Object[]{price[0], price[1], itemId});
                                RxqqUtils.execute(this.id, url);
                                this.LOGGER.info(this.id + "球员: " + item.getString("Name") + "已经挂出，价格：" + price[0] + "-" + price[1]);
                            }
                        }
                    } else {
                        if(this.big == 1) {
                            continue;
                        }
                        if(this.big == 2) {
                            if((item.getInt("Type") == 4) && (item.getInt("IsBinding") == 0)) {
                                int level = item.getInt("CardLevel");
                                if((level >= 4) && (level <= 6)) {
                                    boolean found = false;
                                    for(String[] s : RxqqInstance.yizhiqiuyuan) {
                                        if(Integer.parseInt(s[1]) == item.getInt("Code")) {
                                            found = true;
                                            Integer count = RxqqInstance.yizhiCount.get(item.getInt("Code"));
                                            if(count == null) {
                                                RxqqInstance.yizhiCount.put(item.getInt("Code"), 1);
                                                url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=" + item.getString("ItemId");
                                                RxqqUtils.execute(this.id, url);
                                                this.LOGGER.info("球员：" + item.getString("Name") + "(" + level + ")已经挂出");
                                                break;
                                            }
                                            if(count.intValue() >= 2) {
                                                this.LOGGER.info("球员：" + item.getString("Name") + "(" + level + ")拍卖超过2张，分解");
                                                RxqqUtils.execute(this.id, "http://s3.qiuqiu.2010.sina.com.cn/Pandora.do?action=postDecompound&itemId=" + item.getString("ItemId"));
                                                break;
                                            }
                                            RxqqInstance.yizhiCount.put(item.getInt("Code"), count.intValue() + 1);
                                            url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=" + item.getString("ItemId");
                                            RxqqUtils.execute(this.id, url);
                                            this.LOGGER.info("球员：" + item.getString("Name") + "(" + level + ")已经挂出");

                                            break;
                                        }

                                    }

                                    if((!found) && (item.getString("Name").trim().equals("马克莱莱")) && (item.getInt("CardLevel") == 4)) {
                                        url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=" + item.getString("ItemId");
                                        RxqqUtils.execute(this.id, url);
                                        this.LOGGER.info("马克莱莱已经挂出");
                                        continue;
                                    }
                                }
                            } else {
                                if(item.optInt("IsBinding", 0) != 1) {
                                    continue;
                                }
                                this.LOGGER.info("绑定卡，进行分解：" + item.getString("Name"));
                                RxqqUtils.execute(this.id, "http://s3.qiuqiu.2010.sina.com.cn/Pandora.do?action=postDecompound&itemId=" + item.getString("ItemId"));
                            }
                        }

                    }

                }

                beibao = RxqqUtils.execute(this.id, "http://s3.qiuqiu.2010.sina.com.cn/Package.do?action=getpackage");
                json = new JSONObject(beibao);
                items = json.getJSONArray("Items");
                beibaoSize = json.getInt("ItemLimit");
                if(items.length() >= beibaoSize) {
                    this.LOGGER.info(this.id + "物品数量超过背包数量(" + beibaoSize + ")，停止挂卡");
                    return;
                }
                url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
                ret = RxqqUtils.execute(this.id, url);
                json = new JSONObject(ret);
                tempItems = json.getJSONArray("Info");
                if(tempItems.length() == 0) {
                    this.LOGGER.info(this.id + "临时背包数量为0，挂卡完成");
                }
                hasGetBack = false;
                for(int i = 0; i < tempItems.length(); i++) {
                    JSONObject player = tempItems.getJSONObject(i);
                    if((player.has("LeftTime")) && (player.getInt("ItemType") == 4) && (player.getInt("IsBinding") == 0)) {
                        if(this.big == 2) {
                            url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + player.getInt("Id");
                            RxqqUtils.execute(this.id, url);
                            this.LOGGER.info(this.id + "球员取出成功: ");
                            hasGetBack = true;
                            break;
                        }

                        url = "http://s3.qiuqiu.2010.sina.com.cn/Package.do?ver=1.6&action=Description&ItemCode=" + player.getInt("ItemCode");
                        JSONObject desc = new JSONObject(RxqqUtils.execute(this.id, url));
                        int level = desc.getInt("CardLevel");
                        if((level == 2) || (level == 1)) {
                            url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + player.getInt("Id");
                            RxqqUtils.execute(this.id, url);
                            this.LOGGER.info(this.id + "球员取出成功: " + desc.get("Name"));
                            hasGetBack = true;
                            break;
                        }
                    } else {
                        if(this.big == 1) {
                            continue;
                        }
                        if((this.big != 2) || ((player.getInt("ItemType") != 1) && (player.getInt("ItemType") != 2))) {
                            continue;
                        }
                        url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + player.getInt("Id");
                        RxqqUtils.execute(this.id, url);
                        hasGetBack = true;
                        this.LOGGER.info("取出装备");
                        break;
                    }

                }

                if(!hasGetBack) {
                    this.LOGGER.info(this.id + "球员挂卡结束");
                    break;
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + this.id + "," + sw.toString());
        }
    }

    public void run() {
        guaCPlayer();
    }
}