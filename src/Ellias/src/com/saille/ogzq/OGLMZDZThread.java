package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 14-5-6
 * Time: 下午1:25
 * To change this template use File | Settings | File Templates.
 */
public class OGLMZDZThread extends Thread {
    private String email;
    private int min;
    private int max;
    private final static SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
    private final static Logger LOGGER = Logger.getLogger(OGLMZDZThread.class);

    public OGLMZDZThread(String email, int min, int max) {
        this.setName("OGLMZDZThread-" + email);
        this.email = email;
        this.min = min;
        this.max = max;
    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        Date date = new Date();
        int defCD = 0;
        while(Integer.parseInt(sdf.format(date)) <= 2000) {
            try{
                Thread.sleep(5000);
                date = new Date();
            } catch(Exception ex) {}
        }
        while(Integer.parseInt(sdf.format(date)) <= 2200) {
            try{
                HttpPost pm = new HttpPost(OgzqURL.URL + "/OGLM.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Limzdz1", "-1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(email, pm);
                if(s.indexOf("inmatch|") >= 0) {
                    //在比赛中
                } else {
//圣日耳曼@
//33375@
//npc7.png@
//40|曼联|npc40.png|0|0*19|多特蒙德|npc19.png|0|0*20|拜仁慕尼黑|npc20.png|0|0*27|罗马|npc27.png|0|0*28|尤文图斯|npc28.png|0|0*29|国际米兰|npc29.png|0|0*30|AC米兰|npc30.png|0|0*35|热刺|npc35.png|0|0*36|阿森纳|npc36.png|0|0*37|利物浦|npc37.png|0|0*38|切尔西|npc38.png|0|0*39|曼城|npc39.png|0|0*47|马德里竞技|npc47.png|0|0*49|皇家马德里|npc49.png|0|0*50|巴塞罗那|npc50.png|0|0@
//3&狗仔队|CNTV3服|FCD|新浪76服|1*3&情谊沧海枭雄|937753服|呼呼呼呼呼|JJ竞技14服|0*2&0015|新浪132服|30018|npc37.png|利物浦|6|8|35|0|1237*3&就是钱多|05玩65服|bingbao|05玩65服|1*3&北鬼江|平安1服|2584981014|JJ竞技1服|1*3&AAAA|G36165服|VO内马尔|开心10服|1*3&诺坎普18号|ya24769服|乔巴|05玩9服|0*3&水瓶座|93775服|辉煌之巅|05玩27服|1*2&NACL|新浪1服|88552|npc39.png|曼城|7|8|41|1|348@
//0@0@
//本联盟的<b style="color:red;">DW雄起中国</b>击败占领者[<b style="color:red;">利物浦</b>]的<b style="color:red;">0015</b>，挽回了2联盟积分，获得20联盟贡献@
//缥缈过客|npc58.png|42721|圣日耳曼@45448@npc58.png
                    String[] list = s.split("@");
                    defCD = Integer.parseInt(list[5]); //6=attCD
                    if(defCD > 0) {
                        Thread.sleep(defCD * 900);
                    }
                    if(!"-1".equals(list[4])) {
                        String[] kengList = list[4].split("[*]");
                        int currentJifen = 0, currentIdx = -1;
                        for(int i = 0; i < kengList.length; i++) {
                            String[] keng = kengList[i].split("&"); //7: 积分
                            if("2".equals(keng[0])) { //已占场地
                                int shili = Integer.parseInt(keng[1].split("[|]")[2]);
//                                int myShili = Integer.parseInt(IDUtils.IDInfos.get(email).get("shili"));
                                if(shili > min && shili <= max) {
                                    if(list[0].equals(list[8].split("[|]")[3])) {
                                        LOGGER.info(email + "/" + IDUtils.getNick(email) + "可以踢联盟争夺战，积分：" + keng[1].split("[|]")[7] + "，位置：" + i);
                                        if(Integer.parseInt(keng[1].split("[|]")[7]) > currentJifen) {
                                            currentJifen = Integer.parseInt(keng[1].split("[|]")[7]);
                                            currentIdx = i;
                                        }
                                    }
                                }
                            }
                        }
                        if(currentIdx >= 0) {
                            pm = new HttpPost(OgzqURL.URL + "/OGLM.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("GuardRoom1", "" + currentIdx));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(email, pm);
                            if("-999".equals(s)) {
                                s = "您手指不累么？休息十分钟再玩吧！";
                            } else if("-888".equals(s)) {
                                s = "您今日已参与其他时间段内的赛事，不可继续参与";
                            } else if("-2".equals(s)) {
                                s = "已经有别人正在防守此球场";
                            } else if("-3".equals(s)) {
                                s = "不能防守除本联盟以外的球场";
                            } else if("-4".equals(s)) {
                                s = "您正在攻占其他联盟，不可参与防守";
                            } else if("-5".equals(s)) {
                                s = "剩余CD时间：" + defCD;
                            } else if("-6".equals(s)) {
                                s = "亲，不带这样玩的！";
                            } else if("-7".equals(s)) {
                                s = "联盟争夺战已截止";
                            }
                            LOGGER.info(email + "/" + IDUtils.getNick(email) + "踢联盟争夺战，积分：" + currentJifen + ": " + s);
                            System.out.println(s);
                        }
                    }
                }
            } catch(Exception ex) {
            }
            try{
                Thread.sleep(1000);
            } catch(Exception ex) {}
            date = new Date();
        }
    }
}
