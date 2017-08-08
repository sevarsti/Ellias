package com.saille.ogzq;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-9-15
 * Time: 8:53:37
 * To change this template use File | Settings | File Templates.
 * 指定号打三剑三马巅峰
 */
public class CustomTopChallengeThread extends Thread {
    public List<String[]> IDS = new ArrayList<String[]>();
    public Map<String, HttpClient> CLIENTS = new HashMap<String, HttpClient>();
    public Map<String, String> OLDTEAMS = new HashMap<String, String>();
    public Map<String, String[]> OLDLEADERS = new HashMap<String, String[]>();

    public List<String[]> BIGTEAMS = new ArrayList<String[]>();
    public Map<String, HttpClient> BIGCLIENTS = new HashMap<String, HttpClient>();

    public Map<String, Long> waittime = new HashMap<String, Long>();

    public CustomTopChallengeThread() {
    }

    public void run() {
        try {
            boolean begin = false;
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            while(!begin) {
                Date now = new Date();
                if(Integer.parseInt(sdf.format(now)) < 1001 ||
                        Integer.parseInt(sdf.format(now)) >= 1800) {
                    Thread.sleep(1000 * 60);
                }
            }
            PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/custtopchallenge.ini"));
            conf.setEncoding("GB2312");
            conf.setAutoSave(false);
            String[] ids = conf.getStringArray("id");
            for(String id : ids) {
                String pwd = conf.getString(id);
                System.out.println("登录球会巅峰：" + id + "=" + pwd);
                if(pwd != null) {
                    HttpClient client = LoginUtils.Login(id, pwd);
                    if(client != null) {
                        IDS.add(new String[]{id, pwd});
                        CLIENTS.put(id, client);
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        boolean goon = true;
        while(goon) {
            try {
                long now = System.currentTimeMillis();
                for(int i = 0; i < IDS.size(); i++) {
                    String[] id = IDS.get(i);
                    System.out.println(i + "/" + IDS.size() + ":" + id[0]);
                    long target = waittime.get(id[0]);
                    if(target > now) {
                        System.out.println("目标时间" + target + "，当前时间" + now + "，还有 " + ((target - now) / 1000) + "秒");
                        continue;
                    }
                    /* 打球会 */
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/Gullit_Top_Club.aspx");
                    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(CLIENTS.get(id[0]), id[0], pm);
                    if(s.startsWith("1@")) { //正在比赛
                        /* 快速结束 */
                        pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("ImaEndMatchID", s.split("@")[1].split("\\|")[0]));
                        params.add(new BasicNameValuePair("ImaMatchCategory", s.split("@")[1].split("\\|")[1]));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(CLIENTS.get(id[0]), id[0], pm);
                        System.out.println("巅峰立即结束结果：" + s);
                        try {
                            Thread.sleep(3000);
                        } catch(Exception ex) {}

                        waittime.put(id[0], now + 60 * 15 * 1000 + 1000);
//                        continue;
                    } else if("-2".equals(s)) { //没有球会，表示登录状态丢失
                        HttpClient client = LoginUtils.Login(id[0], id[1]);
                        CLIENTS.put(id[0], client);
                        continue;
                    } else {
                        int rest = Integer.parseInt(s);
                        String matchId = null, matchCategory = null;
                        if(rest > 0) { //需要等待
                            System.out.println("还需要等待" + rest + "秒");
                            waittime.put(id[0], now + rest * 1000 + 1000);
//                            continue;
                        } else if(rest == -1) { //活动结束
                            goon = false;
                            break;
                        } else if(rest == 0) {
                            pm = new HttpPost(OgzqURL.URL + "/" + TopChallengeThread.PLAYER + "_Top_Club.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "2"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(CLIENTS.get(id[0]), id[0], pm);
                            System.out.println(id[0] + "打巅峰战：" + s);
                            if(s.equals("-3")) {
                                HttpClient client = LoginUtils.Login(id[0], id[1]);
                                CLIENTS.put(id[0], client);
                            } else if(s.indexOf("@") >= 0) {
                                waittime.put(id[0], now + 60 * 20 * 1000 + 1000);
                                matchId = s.split("@")[1].split("\\|")[0];
                                matchCategory = s.split("@")[1].split("\\|")[1];
                            }
                            try {
                                Thread.sleep(3000);
                            } catch(Exception ex) {}

                            /* 快速结束 */
                            pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("ImaEndMatchID", matchId));
                            params.add(new BasicNameValuePair("ImaMatchCategory", matchCategory));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(CLIENTS.get(id[0]), id[0], pm);
                            System.out.println("巅峰立即结束结果");
                            try {
                                Thread.sleep(3000);
                            } catch(Exception ex) {}
                        }
                    }
                }
                if(!goon) {
                    break;
                }
                for(int i = 0; i < IDS.size(); i++) {
                    loop(CLIENTS.get(IDS.get(i)[0]), IDS.get(i)[0]);
                }
                for(int i = 0; i < BIGTEAMS.size(); i++) {
                    loop(BIGCLIENTS.get(BIGTEAMS.get(i)[0]), BIGTEAMS.get(i)[0]);
                }
                Thread.sleep(20 * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("统统结束！！！！！！！！！！！！！!!!!!!!!!!!!!!!!!!!!!");
    }

    public void loop(HttpClient client, String email) throws Exception {
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + "/Default.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "7"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(client, email, pm);

    }
}
