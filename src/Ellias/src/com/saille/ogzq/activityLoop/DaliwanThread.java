package com.saille.ogzq.activityLoop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.util.List;
import java.util.ArrayList;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;
import com.saille.ogzq.OperationUtils;
import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-12-7
 * Time: 14:12:00
 * To change this template use File | Settings | File Templates.
 */
public class DaliwanThread extends Thread {
    private final static Logger LOGGER = Logger.getLogger(DaliwanThread.class);
    private static DaliwanThread instance = null;

    private DaliwanThread() {

    }

    public static DaliwanThread getInstance() {
        if(instance == null) {
            instance = new DaliwanThread();
        }
        return instance;
    }

    public void run() {
        List<String> ids = IDUtils.GETIDS();
        for(int i = ids.size() - 1; i >= 0; i--) {
            if(ids.get(i).indexOf("txjcf") >= 0) {
                ids.remove(i);
            }
        }
        while(ids.size() > 0) {
            for(int i = ids.size() - 1; i >= 0; i--) {
                String id = ids.get(i);
                try {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(id, pm);
                    if(s.indexOf("inmatch") >= 0) {
                        continue;
                    }
                    if(s.indexOf("╋") >= 0) {
                        s = s.substring(0, s.indexOf("╋"));
                    }
                    String[] parts = s.split("\\@");
                    if(parts[0].equals("1")) { //挑战时间
                        int matchtimes = Integer.parseInt(parts[6]) / 1000;
                        int daliwancount = Integer.parseInt(parts[2]);
                        int silver = Integer.parseInt(IDUtils.IDInfos.get(id).get("silver"));
                        if(silver > 1000000 && matchtimes < 10) {
                            pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "2"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(id, pm);
                            LOGGER.info(id + "/" + IDUtils.getNick(id) + "踢大力丸比赛：" + s + "，已打比赛=" + matchtimes);
                        } else {
                            if(matchtimes >= 10) {
                                ids.remove(i);
                            }
                        }
                    } else if(parts[0].equals("2")) { //加成时间
                        if(parts[1].equals("0")) {
                            String[] players = parts[3].split("\\*");
                            if(players.length == 1 && players[0].equals("0")) {
                                ids.remove(i);
                                continue;
                            }
                            String[] upplayer = null;
                            int upshili = 99999;
                            for(String player : players) {
                                String[] subs = player.split("\\|"); //690094|10056|欧冠C罗*695956|220301|▲诺伊尔*712132|110000072|巴雷西
                                boolean canup = false;
                                if(subs[2].equals("巴雷西") ||
                                        subs[2].equals("▲诺伊尔") ||
                                        subs[2].equals("欧冠C罗") ||
                                        subs[2].equals("金球梅西")) {
                                    canup = true;
                                } else if(id.indexOf("blue") == 0) {
                                    if(subs[2].equals("卡巴耶") ||
                                            subs[2].equals("卡巴耶") ||
                                            subs[2].equals("卡列洪") ||
                                            subs[2].equals("朗德洛") ||
                                            subs[2].equals("马克斯维尔") ||
                                            subs[2].equals("姆西塔良") ||
                                            subs[2].equals("戈比") ||
                                            subs[2].equals("格拉内罗") ||
                                            subs[2].equals("格雷罗") ||
                                            subs[2].equals("巴斯托斯")) {
                                        canup = true;
                                    }
                                } else if(id.indexOf("orange") == 0) {
                                    if(subs[2].equals("▲布拉什契科夫斯基") ||
                                            subs[2].equals("▲迭戈·洛佩斯") ||
                                            subs[2].equals("▲京多安") ||
                                            subs[2].equals("▲施梅尔策") ||
                                            subs[2].equals("▲瓦拉内") ||
                                            subs[2].equals("金靴巴神") ||
                                            subs[2].equals("金靴小法")) {
                                        canup = true;
                                    }
                                } else if(id.indexOf("bixi") == 0) {
                                    if(subs[2].equals("▲巴尔德斯") ||
                                            subs[2].equals("★莱万多夫斯基") ||
                                            subs[2].equals("候选·C罗") ||
                                            subs[2].equals("候选·梅西") ||
                                            subs[2].equals("候选·小白") ||
                                            subs[2].equals("金靴C罗")) {
                                        canup = true;
                                    }
                                }
                                if(canup) {
                                    pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
                                    params = new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("type", "3"));
                                    params.add(new BasicNameValuePair("playerid", subs[0]));
                                    params.add(new BasicNameValuePair("playerDataId", subs[1]));
                                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    s = IDUtils.execute(id, pm);
                                    int newshili = Integer.parseInt(s.split("\\|")[2]);
                                    if(newshili < upshili) {
                                        upshili = newshili;
                                        upplayer = subs;
                                    }
                                }
                            }
                            if(upplayer != null) {
                                pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("type", "4"));
                                params.add(new BasicNameValuePair("playerid", upplayer[0]));
                                params.add(new BasicNameValuePair("playerDataId", upplayer[1]));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                s = IDUtils.execute(id, pm);
                                if(s.equals("1")) {
                                    s = "成功";
                                    OperationUtils.trainFull(id, upplayer[0], true);
                                } else if(s.equals("-1")) {
                                    s = "大力丸不够";
                                } else if(s.equals("-2")) {
                                    s = "只能对一名球员使用大力丸";
                                } else {
                                    s = "操作失败";
                                }

                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "使用大力丸，球员=" + upplayer[2] + "，结果：" + s);
                            }
                            ids.remove(i);
                        } else {
                            ids.remove(i);
                            continue;
                        }
                    } else {
                        LOGGER.error("未知时间段类型！");
                        ids.clear();
                        break;
                    }
//	时间段类型(1:挑战时间，2：属性加成时间)@
// 是否已加成（0，没有；1加了）@
// 已获得大力丸总数(无论是否消耗)@
// 可选球员列表@
// 加成前球员信息@加成后球员信息@累计获得训练点@累计获得银币@累计获得大力神积分
//	可选球员列表：playerid|PlayerDataID|CnName * playerid|PlayerDataID|CnName * playerid|PlayerDataID|CnName    没有可选球员时，返回0
//	球员信息格式：Playerid|PlayerDataID|PlayerInfo_Power|role|Photo|CnName|PlayerQuality    没有完成一次加成时，返回0
//	正在比赛的情况返回：inmatch|matchId|matchCategry
//result = "1@0@17@2375679|20711|亨特拉尔*3643717|21001|诺伊尔*2873783|21006|李贝里*3114835|21007|施魏因斯泰格*2850083|21009|罗本*4620747|40509|贝尔*1163933|40516|伦侬*4396585|41010|鲁尼*3834245|50911|伊瓜因*8606594|110000040|君王·内斯塔@0@0";
//1@
//0@
//1@
//690094|10056|欧冠C罗*695956|220301|▲诺伊尔*712132|110000072|巴雷西@
//0@
//0@
//1000@
//1000@
//10
//╋12月7日|12月8日
                } catch(Exception ex) {
                    UtilFunctions.LogError(LOGGER, ex);
                }
            }
            try {
                Thread.sleep(1000 * 60 * 5);
            } catch(Exception ex) {}
        }
        LOGGER.info("大力丸进程结束");
        DaliwanThread.instance = null;
    }
}
