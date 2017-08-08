package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-26
 * Time: 18:02:32
 * To change this template use File | Settings | File Templates.
 */
public class UseHuidou extends Thread {
    private HttpClient client;
    private static Logger LOGGER = Logger.getLogger(UseHuidou.class);
    private String id;
    private String nick;
    public boolean cont = true;

    public static void main(String[] args) {
        try {
            HttpPost pm = null;
            String email = "super88man66@126.com";
            String pwd = "072022021";
            HttpClient client = LoginUtils.Login(email, pwd);
            int huidoucount = 0, tiaoguocount = 0;
            pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            String str = IDUtils.execute(client, email, pm);
            System.out.println(str);
            String[] items = str.split("[|]");
            String huidoucode = null, tiaoguocode = null;
            for(String item : items) {
                String[] atts = item.split("[*]");
                if(atts[3].equals("回豆卡")) {
                    huidoucode = atts[0];
                    huidoucount = Integer.parseInt(atts[7]);
                } else if(atts[3].equals("跳过次数卡")) {
                    tiaoguocode = atts[0];
                    tiaoguocount = Integer.parseInt(atts[7]);
                }
            }
            System.out.println("回豆卡：" + huidoucount + "，跳过卡：" + tiaoguocount);
            while(true) {
                pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("TrainMatchload1", "7*0*0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(client, email, pm);

                if(s.indexOf("inmatch|") >= 0) { //在打比赛
//            todo
                    String[] pp = s.split("[|]");
                    String matchId = pp[1];

                    pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("MatchID", matchId));
                    params.add(new BasicNameValuePair("MatchCategory", "102"));
                    params.add(new BasicNameValuePair("KFC", "0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);

                    s = s.substring(s.lastIndexOf("|")+1);
                    int skipTimes = 50 - Integer.parseInt(s.substring(0, s.indexOf("~")));
                    LOGGER.info("tiaoguocishu: " + skipTimes);
                    if(skipTimes > 0) {

                    } else {
                        if(tiaoguocount <= 0) {
                            System.out.println("跳过次数卡用完，退出程序");
                            break;
                        }
                        pm = new HttpPost(OgzqURL.URL + "/Bag.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("type", "17"));
                        params.add(new BasicNameValuePair("aii", tiaoguocode));
                        params.add(new BasicNameValuePair("eqItemCode", "8104"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(client, email, pm);

                        pm = new HttpPost(OgzqURL.URL + "/Bag.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("type", "7"));
                        params.add(new BasicNameValuePair("aii", tiaoguocode));
                        params.add(new BasicNameValuePair("isAll", "0"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(client, email, pm);
                        tiaoguocount--;
                        System.out.println("使用跳过次数卡：" + s + "，剩余次数：" + tiaoguocount);
                        Thread.sleep(2000);
                    }
                    pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("ImaEndMatchID", matchId));
                    params.add(new BasicNameValuePair("ImaMatchCategory", "102"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);
                    Thread.sleep(2000);

                    pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("TrainMatchload1", "7*0*0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);
                }
                pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING); //领取奖励
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getAward", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            IDUtils.execute(client, email, pm);
                LOGGER.info(email + " 奖品:" + IDUtils.execute(client, email, pm));

                if(s.indexOf("inmatch") >= 0) {
                    continue;
                }
                String[] parts = s.split("&");
                String trainingMatch2 = parts[0];

                int blankMatch = 0;
                int restMatch = 0;
                if(!trainingMatch2.equals("-2")) {
                    if(parts[5].equals("0")) {
                        if(huidoucount <= 0) {
                            System.out.println("回豆卡用完，退出程序");
                            break;
                        }
                        pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("ShowTmMsgOK1", "1*" + huidoucode));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        String ss = IDUtils.execute(client, email, pm);
                        huidoucount--;
                        LOGGER.info("使用回豆卡：" + ss + "，剩余次数：" + huidoucount);
                        Thread.sleep(2000l);
                        parts[5] = parts[4];
                    }
                    blankMatch = Integer.parseInt(parts[4]) - Integer.parseInt(parts[5]); //离满豆还差多少场
                    restMatch = Integer.parseInt(parts[5]);
                } else {
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("GetPowerCount", "1"));
                    params.add(new BasicNameValuePair("pt", "7"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String powerCount = IDUtils.execute(client, email, pm);
                    int maxMatch = 0;
                    try {
                        maxMatch = Integer.parseInt(powerCount);
                    } catch(Exception ex) {
                    }
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAM_PLAY);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("getB", "7"));
                    params.add(new BasicNameValuePair("title", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String currentCount = IDUtils.execute(client, email, pm);
                    int currentMatch = 0;
                    try {
                        currentMatch = Integer.parseInt(currentCount.split("[|]")[0]);
                        restMatch = currentMatch;
                    } catch(Exception ex) {
                    }
                    blankMatch = maxMatch - currentMatch;
                }

                if(restMatch <= 0) { //如果剩余场次不够，那就不打
                    System.out.println("??");
                    return;
                }

                { //踢70-80训练场
                    String nextTeam = null;
                    int pageCount = 6;
                    String pageInfo = null;
                    while(nextTeam == null) {
                        pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("TrainMatchload1", "7*" + pageCount + "*1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(client, email, pm);
                        if(s.indexOf("inmatch") >= 0) {
                            break;
                        }
//                System.out.println(s);
                        pageInfo = s.split("&")[3];
                        String[] teams = s.split("&")[8].split("[*]");
                        for(String t : teams) {
                            if(t.split("[|]")[6].equals("-1")) {
                                nextTeam = t.split("[|]")[0];
                                break;
                            }
                        }

                        pageCount--;
                        if(pageCount < 5) {
                            pageCount = 50;
                        }
                    }

                    pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("InitChallengeMatch1", nextTeam + "*" + pageInfo));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(client, email, pm);
                    LOGGER.info(email + "踢70-80训练赛：" + s);
                    Thread.sleep(2000);
//                return;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
//        Test instance = new Test("yuliang0526@163.com", "yuliang83012", "不及阁", 0, 24, 0, 100);
//        instance.start();
    }
}
