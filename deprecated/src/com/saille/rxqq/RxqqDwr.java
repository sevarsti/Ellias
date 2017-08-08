package com.saille.rxqq;

import com.txsec.lc.is.security.EncryptUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class RxqqDwr {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private static final String KEY = "F289A70483E9C779C175F198";

    public String init(int type) {
        try {
            String[] ids = new String[0];
            if(type == 1) {
                PropertiesConfiguration conf = new PropertiesConfiguration(RxqqDwr.class.getResource("../../../../../rxqq/ids.ini"));

                ids = conf.getStringArray("id");
            } else if(type == 2) {
                ids = new String[7];
                for(int i = 0; i < 6; i++) {
                    ids[i] = ("yssy0" + i + "@wanwan.sina.com");
                }
                ids[6] = "sevarsti@sina.com";
            }
            for(String id : ids) {
                String pwd = EncryptUtil.decode("zZJDqrnKhZY=", "F289A70483E9C779C175F198");
                if(id.equals("sevarsti@sina.com")) {
                    pwd = "pmgkglory";
                }
                DoLogin action = new DoLogin(id, pwd);
                action.start();
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("初始化帐号错误：" + sw.toString());
        }
        return null;
    }

    public String[] login(String id, String pwd) {
        this.LOGGER.info("登录：" + id);
        if(StringUtils.isEmpty(pwd)) {
            pwd = EncryptUtil.decode("zZJDqrnKhZY=", "F289A70483E9C779C175F198");
        }
        RxqqInstance.pwd.put(id, pwd);
        RxqqUtils.login(id, pwd);
        String string = RxqqUtils.myInfo(id);
        try {
            JSONObject json = new JSONObject(string);
            RxqqInstance.info.put(id, json);
            Loop thread = new Loop(id);
            thread.start();
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        this.LOGGER.info(id + "登录完毕");
        return new String[]{id, string};
    }

    public String relogin(String id) {
        String pwd = RxqqInstance.pwd.get(id);
        RxqqUtils.login(id, pwd);
        return "重新登录完成";
    }

    public String logout(String id) {
        this.LOGGER.info(id + "注销");
        RxqqInstance.id.remove(id);
        return id + "注销成功";
    }

    public String dftt(String id) {
        this.LOGGER.info(id + "单服天梯");
        if(id.equals("")) {
            List goIds = generateIds(id);
            this.LOGGER.info("一共有" + goIds.size() + "个号");
            int count = 0;
            int jiou = RxqqInstance.getDfttJiou();
            for(int j = 0; j < goIds.size(); j++) {
                if(j % 2 == jiou) {
                    count++;
                    String i = (String) goIds.get(j);
                    DoAction doAction = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=attendladder");
                    doAction.start();
                }
            }
            this.LOGGER.info("天梯准备完毕，" + count + "个号参加了tt");
        } else {
            try {
                int type = Integer.parseInt(id);
                if(type == 1) {
                    List<String> ids = generateIds(null);
                    int count = 0;
                    int jo = RxqqInstance.getDfttJiou();
                    for(String i : ids) {
                        int rank = RxqqInstance.arenaRank.get(i)[0].optInt("Score", 0);
                        if(rank <= RxqqInstance.DF_SFFS) {
                            count++;
                            if(count % 2 == jo) {
                                DoAction doAction = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=attendladder");
                                doAction.start();
                            }
                        } else {
                            this.LOGGER.info(i + "单服积分为" + rank + "，超过设定分数：" + RxqqInstance.DF_SFFS + "，不参加单服tt");
                        }
                    }
                    this.LOGGER.info("一共有" + count + "个号参加了单服tt");
                }
            } catch(Exception ex) {
                DoAction doAction = new DoAction(id, "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=attendladder");
                doAction.start();
            }
        }
        return null;
    }

    public String kftt(String id) {
        this.LOGGER.info(id + "跨服天梯");
        List ids = generateIds(id);
        this.LOGGER.info("一共有" + ids.size() + "个号");
        int jiou = RxqqInstance.getDfttJiou();
        int count = 0;
        for(int j = 0; j < ids.size(); j++) {
            if(j % 2 == jiou) {
                count++;
                String i = (String) ids.get(j);
                DoAction doAction = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?action=join");
                doAction.start();
            }
        }
        this.LOGGER.info("跨服天梯准备完毕，" + count + "个号参加了tt");
        return null;
    }

    public String jjc(String id) {
        this.LOGGER.info(id + "竞技场");
        List<String> ids = generateIds(id);
        this.LOGGER.info("一共有" + ids.size() + "个号");
        for(String i : ids) {
            DoAction doAction = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/AthleticsCoin.do?action=Register");
            doAction.start();
        }
        this.LOGGER.info("竞技场准备完毕");
        return null;
    }

    public String jingcaiLiansai(String id) {
        this.LOGGER.info(id + "竞猜联赛");
        List<String> ids = generateIds(id);
        try {
            for(String i : ids) {
                this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ": " + i);
                JSONObject json = RxqqInstance.info.get(i);
                if(!json.has("Area")) {
                    this.LOGGER.warn(id + ", key: Area没有对应的内容！");
                    Iterator it = json.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + json.getString(key));
                    }
                    continue;
                }
                Integer area = (Integer) json.get("Area");
                if(area.intValue() == 1) {
                    RxqqUtils.execute(i, "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=3561ee2b%2D21a2%2D45f9%2Da305%2D2d7a5dbd98ff&Money=800&action=PostGambleLeague");
                } else if(area.intValue() == 2) {
                    RxqqUtils.execute(i, "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=6f62d071%2Da7b4%2D413d%2Db865%2D00046f71781b&Money=800&action=PostGambleLeague");
                } else if(area.intValue() == 3) {
                    RxqqUtils.execute(i, "http://s3.qiuqiu.2010.sina.com.cn/League.do?mid=1759ef4e%2D4693%2D4d78%2Dbb93%2D3bfa8a4ce037&Money=800&action=PostGambleLeague");
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return "done";
    }

    public String changgui(String id) {
        this.LOGGER.info(id + "常规赛");
        JSONObject json = (JSONObject) RxqqInstance.info.get(id);
        try {
            Integer area = (Integer) json.get("Area");
            String url = null;
            if(area.intValue() == 1) {
                url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
            } else if(area.intValue() == 2) {
                url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=b790d339%2Dbe78%2D4cd2%2Da7c2%2D4da1fbba32d5&action=creatematchdaily";
            } else if(area.intValue() == 3) {
                url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
            }
            String ret = RxqqUtils.execute(id, url);
            JSONObject j = new JSONObject(ret);
            String matchId = j.getString("MatchId");
            RxqqInstance.updateMatch(id, matchId, 1);
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return null;
    }

    public String changguiAll(String inId) {
        this.LOGGER.info(inId + "开始全部常规");
        String ret = null;
        List<String> ids = generateIds(inId);
        for(String i : ids) {
            this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
            DoChangGui changgui = new DoChangGui(i);
            changgui.start();
        }
        return ret;
    }

    public List<String[]> beibao(String id) throws Exception {
        this.LOGGER.info(id + "查看背包");
        List<String> ids = generateIds(id);
        List ret = new ArrayList();
        for(String i : ids) {
            String url = "http://s3.qiuqiu.2010.sina.com.cn/Package.do?action=getpackage";
            String ss = RxqqUtils.execute(i, url);
            ret.add(new String[]{i, ss});
        }
        return ret;
    }

    public String guaCPlayerAll(int big) {
        List<String> ids = generateIds("");
        for(String s : ids) {
            this.LOGGER.info(ids.indexOf(s) + "/" + ids.size() + ": " + s);
            guaCPlayer(s, big);
            this.LOGGER.info(s + "挂C卡结束");
        }
        return "done";
    }

    public String guaCPlayer(String id, int big) {
        this.LOGGER.info(id + "挂C卡");
        GuaCPlayer g = new GuaCPlayer(id, big);
        g.start();
        return "done";
    }

    public String beibaozhengli(String inId) {
        this.LOGGER.info(inId + "整理背包");
        List<String> ids = generateIds(inId);
        try {
            for(String id : ids) {
                this.LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
                String url = "http://s3.qiuqiu.2010.sina.com.cn/Package.do?action=getpackage";
                String ret = RxqqUtils.execute(id, url);
                JSONObject json = new JSONObject(ret);
                if(!json.has("Items")) {
                    this.LOGGER.warn(id + ", key: Items没有对应的内容！");
                    Iterator it = json.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + json.getString(key));
                    }
                    continue;
                }
                JSONArray items = json.getJSONArray("Items");
                for(int i = 0; i < items.length(); i++) {
                    JSONObject item = items.getJSONObject(i);
                    if(item.getInt("Type") != 4) {
                        if(((item.getInt("Type") == 1) || (item.getInt("Type") == 2)) && ((!item.has("EquipPrefix")) || (StringUtils.isEmpty(item.getString("EquipPrefix"))))) {
                            url = String.format("http://s3.qiuqiu.2010.sina.com.cn/Token.do?action=ExchangeToken&Type=4&Num=1&ItemId=%s&random=%s", new Object[]{item.getString("ItemId"), Double.valueOf(Math.random())});
                            RxqqUtils.execute(id, url);
                            this.LOGGER.info(id + "兑换装备：" + item.getString("Name"));
                        } else {
                            if((item.getInt("Type") != 6) || (item.getInt("Code") != 8500590)) {
                                continue;
                            }
                            url = "http://s3.qiuqiu.2010.sina.com.cn/interface/Post-ItemOperate.aspx?Op=2&ItemId=" + item.getString("ItemId");
                            RxqqUtils.execute(id, url);
                            this.LOGGER.info(id + "丢弃物品：" + item.getString("Name"));
                        }
                    } else {
                        int level = item.getInt("CardLevel");
                        if(level == 2) {
                            int strengthen = item.getInt("Strengthen");
                            if(strengthen < 5) {
                                strengthen = 5;
                            }
                            Integer[] price = (Integer[]) RxqqInstance.cprice.get(item.getString("Name").trim() + "_" + strengthen);
                            if(price == null) {
                                this.LOGGER.info("没有找到球员价格：" + item.getString("Name") + "，跳过");
                            } else {
                                String itemId = item.getString("ItemId");
                                url = String.format("http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&StartPrice=%s&EndPrice=%s&TimeType=2&ItemId=%s", new Object[]{price[0], price[1], itemId});
                                RxqqUtils.execute(id, url);
                                this.LOGGER.info("球员: " + item.getString("Name") + "已经挂出，价格：" + price[0] + "-" + price[1]);
                            }
                        } else {
                            if((level < 4) || (level > 6)) {
                                continue;
                            }
                            boolean found = false;
                            RxqqInstance.initYizhi();
                            for(String[] s : RxqqInstance.yizhiqiuyuan) {
                                if(Integer.parseInt(s[1]) == item.getInt("Code")) {
                                    found = true;
                                    url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=" + item.getString("ItemId");
                                    RxqqUtils.execute(id, url);
                                    break;
                                }
                            }
                            if(!found) {
                                if((item.getString("Name").trim().equals("马克莱莱")) && (item.getInt("CardLevel") == 4)) {
                                    url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=StartAuction&CurrencyType=2&EndPrice=50&StartPrice=50&TimeType=2&ItemId=" + item.getString("ItemId");
                                    RxqqUtils.execute(id, url);
                                } else {
                                    this.LOGGER.info("球员不是意志，分解：" + item.getString("Name") + "/" + RxqqInstance.CARDLEVEL[level]);
                                    fenjie(id, item.getString("ItemId"));
                                }
                            }
                        }
                    }
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + inId + "," + sw.toString());
        }
        return "整理完毕，非意志卡非橙卡已分解";
    }

    public String fenjie(String id, String itemId) {
        this.LOGGER.info(id + "分解");
        try {
            String url = "http://s3.qiuqiu.2010.sina.com.cn/Pandora.do?action=postDecompound&itemId=" + itemId;
            String ret = RxqqUtils.execute(id, url);
            return ret;
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return null;
    }

    public String mfs(String id) {
        this.LOGGER.info(id + "魔法社");
        try {
            String url = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=buy&payType=2";
            String ret = RxqqUtils.execute(id, url);
            JSONObject json = new JSONObject(ret);
            String cardId = json.getString("ResultCard");
            String name = json.getJSONArray("Players").getJSONObject(0).getString("Name");
            url = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=hit&pStr=" + cardId;
            RxqqUtils.execute(id, url);
            url = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=state";
            RxqqUtils.execute(id, url);
            url = "http://s3.qiuqiu.2010.sina.com.cn/CardPack.do?action=packs";
            RxqqUtils.execute(id, url);
            return name;
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return null;
    }

    public String mfsAll(String id) {
        this.LOGGER.info(id + "魔法社全部抽卡");
        String ret = "获得物品：";
        try {
            String myInfo = RxqqUtils.myInfo(id);
            JSONObject json = new JSONObject(myInfo);
            int money = json.getInt("Money");
            while(money > 800) {
                beibaozhengli(id);
                String beibao = ((String[]) beibao(id).get(0))[1];
                JSONObject beibaoJson = new JSONObject(beibao);
                JSONArray items = beibaoJson.getJSONArray("Items");
                int beibaoSize = beibaoJson.getInt("ItemLimit");
                if(items.length() >= beibaoSize) {
                    this.LOGGER.info("物品数量超过背包数量(" + beibaoSize + ")，停止抽卡");
                    break;
                }
                String getItem = mfs(id);
                ret = ret + getItem + ",";
                money -= 800;
                this.LOGGER.info(id + "获得：" + getItem + "，剩余现金：" + money);
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return ret;
    }

    public List<String[]> dopaimai(String id) {
        this.LOGGER.info(id + "查看拍卖市场");
        List<String> ids = generateIds(id);
        List ret = new ArrayList();
        for(String i : ids) {
            try {
                String s = RxqqUtils.execute(i, "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetBySellerId");
                ret.add(new String[]{i, s});
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                this.LOGGER.error("id: " + i + "," + sw.toString());
            }
        }
        this.LOGGER.info(id + "查看拍卖市场结束");
        return ret;
    }

    public String juanxian(String inId, int juanxianId) {
        this.LOGGER.info(inId + "捐献物品：" + juanxianId);
        String url = "";
        String gxUrl = "";
        int wupinId = 0;
        if(juanxianId == 102) {
            wupinId = 8500102;
            gxUrl = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM102&actNo=1";
        } else if(juanxianId == 301) {
            wupinId = 8500113;
            gxUrl = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM301&actNo=1";
        } else if(juanxianId == 101) {
            wupinId = 8500101;
            gxUrl = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM101&actNo=1";
        } else if(juanxianId == 401) {
            wupinId = 8500502;
            gxUrl = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM401&actNo=1";
        } else if(juanxianId == 402) {
            wupinId = 8500501;
            gxUrl = "http://s3.qiuqiu.2010.sina.com.cn/Guild.do?actqty=1&action=shopAct&bdcode=GM402&actNo=1";
        }
        try {
            List<String> ids = generateIds(inId);
            for(String id : ids) {
                this.LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ":" + id);
                String beibaos = ((String[]) beibao(id).get(0))[1];
                JSONObject obj = new JSONObject(beibaos);
                if(!obj.has("Items")) {
                    this.LOGGER.warn(id + ", key: Items没有对应的内容！");
                    Iterator it = obj.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + obj.getString(key));
                    }
                    continue;
                }
                JSONArray items = obj.getJSONArray("Items");
                int itemCount = items.length();
                for(int i = 0; i < items.length(); i++) {
                    if(items.getJSONObject(i).getInt("Code") == wupinId) {
                        this.LOGGER.info(id + "捐献：" + wupinId);
                        DoAction doAction = new DoAction(id, gxUrl);
                        doAction.run();
                    }
                }
                url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
                String ret = RxqqUtils.execute(id, url);
                JSONObject json = new JSONObject(ret);
                JSONArray tempItems = json.getJSONArray("Info");
                if(tempItems.length() == 0) {
                    continue;
                }
                for(int i = 0; i < tempItems.length(); i++) {
                    if(tempItems.getJSONObject(i).getInt("ItemCode") == wupinId) {
                        this.LOGGER.info(id + "取出：" + wupinId);
                        url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + tempItems.getJSONObject(i).getInt("Id");
                        RxqqUtils.execute(id, url);
                    }
                }
                beibaos = ((String[]) beibao(id).get(0))[1];
                items = new JSONObject(beibaos).getJSONArray("Items");
                itemCount = items.length();
                for(int i = 0; i < items.length(); i++) {
                    if(items.getJSONObject(i).getInt("Code") == wupinId) {
                        DoAction doAction = new DoAction(id, url);
                        doAction.run();
                    }
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + inId + "," + sw.toString());
        }
        return "done";
    }

    public String beisai(String id) {
        this.LOGGER.info(id + "报名工会杯赛");
        List<String> ids = generateIds(id);
        for(String i : ids) {
            this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
            DoAction action = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/interface/Post-JoinDailyCup.aspx");
            action.run();
        }
        this.LOGGER.info("报名每日杯赛结束");
        return "报名每日杯赛结束";
    }

    public String gonghuiBeisai(String id) {
        this.LOGGER.info(id + "报名工会杯赛");
        List<String> ids = generateIds(id);
        for(String i : ids) {
            this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
            DoAction action = new DoAction(i, "http://s3.qiuqiu.2010.sina.com.cn/GuildCup.do?action=join");
            action.run();
        }
        this.LOGGER.info("报名工会杯赛结束");
        return "报名工会杯赛结束";
    }

    public List<String[]> linshibeibao(String id) {
        this.LOGGER.info(id + "查看临时背包");
        List<String> ids = generateIds(id);

        List ret = new ArrayList();
        try {
            for(String i : ids) {
                try {
                    this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
                    String url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
                    String values = RxqqUtils.execute(i, url);
                    JSONObject json = new JSONObject(values);
                    JSONArray tempItems = json.getJSONArray("Info");
                    for(int j = 0; j < tempItems.length(); j++) {
                        JSONObject item = tempItems.getJSONObject(j);
                        int code = item.getInt("ItemCode");
                        String name = RxqqInstance.getItemDesc(code);
                        int left = item.optInt("LeftTime", 0);
                        ret.add(new String[]{i, String.valueOf(code), name, String.valueOf(left)});
                    }
                } catch(Exception ex) {
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return ret;
    }

    public String usewupin(String id, int wupinId) {
        this.LOGGER.info(id + "使用物品：" + wupinId + "/" + RxqqInstance.getItemDesc(wupinId));
        List<String> ids = generateIds(id);
        try {
            for(String i : ids) {
                this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
                String beibao = ((String[]) beibao(i).get(0))[1];
                JSONObject json = new JSONObject(beibao);
                if(!json.has("Items")) {
                    this.LOGGER.warn(id + ", key: Items没有对应的内容！");
                    Iterator it = json.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + json.getString(key));
                    }
                    continue;
                }
                JSONArray items = json.getJSONArray("Items");
                int limit = json.getInt("ItemLimit");
                int current = items.length();
                for(int j = 0; j < items.length(); j++) {
                    JSONObject item = items.getJSONObject(j);
                    if(item.getInt("Code") == wupinId) {
                        String url = "http://s3.qiuqiu.2010.sina.com.cn/MallItem.do?action=use&itemId=" + item.getString("ItemId");
                        this.LOGGER.info(i + "使用物品: " + item.getString("Name"));
                        String ret = RxqqUtils.execute(i, url);
                        this.LOGGER.info(ret);
                        if(wupinId == 8500211) {
                            Thread.sleep(10000L);
                        }
                        current--;
                    }
                }
                if(current < limit) {
                    String url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
                    String ret = RxqqUtils.execute(i, url);
                    json = new JSONObject(ret);
                    JSONArray tempItems = json.getJSONArray("Info");
                    for(int j = 0; j < tempItems.length(); j++) {
                        JSONObject item = tempItems.getJSONObject(j);
                        if(item.getInt("ItemCode") == wupinId) {
                            url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + item.getInt("Id");
                            RxqqUtils.execute(i, url);
                            this.LOGGER.info("物品取出成功：" + wupinId);
                        }
                    }
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return "done";
    }

    public String diuqiwupin(String id, int wupinId) {
        this.LOGGER.info(id + "丢弃物品：" + wupinId + "/" + RxqqInstance.getItemDesc(wupinId));
        List<String> ids = generateIds(id);
        try {
            for(String i : ids) {
                this.LOGGER.info(ids.indexOf(i) + "/" + ids.size() + ":" + i);
                String beibao = ((String[]) beibao(i).get(0))[1];
                JSONObject json = new JSONObject(beibao);
                JSONArray items = json.getJSONArray("Items");
                int limit = json.getInt("ItemLimit");
                int current = items.length();
                for(int j = 0; j < items.length(); j++) {
                    JSONObject item = items.getJSONObject(j);
                    if(item.getInt("Code") == wupinId) {
                        String url = "http://s3.qiuqiu.2010.sina.com.cn/interface/Post-ItemOperate.aspx?Op=2&ItemId=" + item.getString("ItemId");
                        RxqqUtils.execute(i, url);
                        this.LOGGER.info(i + "丢弃物品: " + item.getString("Name"));
                        current--;
                    }
                }
                if(current < limit) {
                    String url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=CheckTempPackage";
                    String ret = RxqqUtils.execute(i, url);
                    json = new JSONObject(ret);
                    JSONArray tempItems = json.getJSONArray("Info");
                    for(int j = 0; j < tempItems.length(); j++) {
                        JSONObject item = tempItems.getJSONObject(j);
                        if(item.getInt("ItemCode") == wupinId) {
                            url = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=GetItem&ItemId=" + item.getInt("Id");
                            RxqqUtils.execute(i, url);
                            this.LOGGER.info("物品取出成功");
                        }
                    }
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + id + "," + sw.toString());
        }
        return "done";
    }

    public List<String> generateIds(String id) {
        List<String> ids = new ArrayList();
        if(StringUtils.isEmpty(id)) {
            Set<String> allIds = RxqqInstance.id.keySet();
            for(String s : allIds) {
                ids.add(s);
            }
        } else {
            ids.add(id);
        }
        Collections.sort(ids);
        return ids;
    }

    public String initYizhi() {
        RxqqInstance.yizhiqiuyuan.clear();
        RxqqInstance.initYizhi();
        return "done";
    }

    public String initCPlayer() {
        RxqqInstance.cprice.clear();
        RxqqInstance.initCPrice();
        return "done";
    }

    public String updateDFFS(int fs) {
        RxqqInstance.DF_SFFS = fs;
        return "单服tt分数设定为：" + fs;
    }

    public String kfnk(int fs) {
        String ret = "参加拿卡的id为：\n";
        try {
            this.LOGGER.info("分数：" + fs);
            List<String> ids = generateIds(null);
            List<String[]> todoIds = new ArrayList<String[]>();
            for(String id : ids) {
                int i = 0;
                int distance = Math.abs(RxqqInstance.crossRank.get(id)[0].optInt("RealScore", 0) - fs);
                for(i = 0; i < Math.min(todoIds.size(), 6); i++) {
                    if(distance < Math.abs(Integer.parseInt(todoIds.get(i)[1]) - fs)) {
                        todoIds.add(i, new String[]{id, String.valueOf(RxqqInstance.crossRank.get(id)[0].optInt("RealScore", 0))});
                        break;
                    }
                }
                todoIds.add(new String[]{id, String.valueOf(RxqqInstance.crossRank.get(id)[0].optInt("RealScore", 0))});
            }
            todoIds = todoIds.subList(0, 6);
            for(String[] s : todoIds) {
                ret = ret + s[0] + ": " + s[1] + "\n";
            }
            for(String[] id : todoIds) {
                DoAction doAction = new DoAction(id[0], "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?action=join");
                doAction.start();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public String veryfyCode(String id, String code) {
        RxqqInstance.verifyCode.put(id, code);
        return null;
    }
}