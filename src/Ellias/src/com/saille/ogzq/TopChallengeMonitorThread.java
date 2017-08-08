package com.saille.ogzq;

import org.springframework.beans.factory.InitializingBean;
import org.apache.log4j.Logger;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;

import java.util.List;
import java.util.ArrayList;

import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-31
 * Time: 22:57:41
 * To change this template use File | Settings | File Templates.
 */
public class TopChallengeMonitorThread extends Thread implements InitializingBean {
    private final static Logger LOGGER = Logger.getLogger(TopChallengeMonitorThread.class);
    private static TopChallengeMonitorThread instance = null;

    private HttpClient client = null;
//    public String email = "meijianbai@hotmail.com", pwd = "meiweilin";
    public String email = "robot0001@sina.com", pwd = "lspmgk";

    public static int restTime = 99999;
    public List<String[]> places = new ArrayList<String[]>();
    public static int restChangci = 0;
    public static boolean end = false;
    private String matchId = "";

    public static void main(String[] args) {
        TopChallengeMonitorThread.getInstance();
    }

    private TopChallengeMonitorThread() {
    }

    public synchronized static TopChallengeMonitorThread getInstance() {
        if(instance == null) {
            instance = new TopChallengeMonitorThread();
            instance.start();
        }
        return instance;
    }

    public void addChangci(int i) {
        if(restChangci < 0) {
            restChangci = 0;
        }
        restChangci += i;
    }

    public void closeChallenge() {
        end = true;
    }

    public void run() {
        while(!end) {                                       
            try {
                if(this.client == null) {
                    this.client = LoginUtils.Login(email, pwd);
                }
//                if(restChangci > 0) {
                    doMatch();
//                } else {
//                    Thread.sleep(1000);
//                    continue;
//                }
                if(places == null || places.size() == 0) {
                    Thread.sleep(1000);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void doMatch() throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(client, email, pm);
        if(ret.equals("")) {
            return;
        }
        if(ret.indexOf("inmatch|") >= 0) {
            String matchId = ret.split("[|]")[1];
            if(matchId.equals(this.matchId)) {
                return;
            }
            if(restChangci > 0) {
                Thread.sleep(500);
                this.matchId = matchId;
                pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ImaEndMatchID", matchId));
                params.add(new BasicNameValuePair("ImaMatchCategory", "31"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(client, email, pm);
                LOGGER.info(ret);
            }
            return;
        }
        //大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@国米战队|10^1500
        if(ret.indexOf("╋") >= 0) {
            ret = ret.substring(0, ret.indexOf("╋"));
        }
        this.restTime = Integer.parseInt(ret.split("\\^")[1]);
        //大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@国米战队|10
        ret = ret.substring(0, ret.indexOf("^"));
        String[] teams = ret.split("@");
        this.places.clear();
        for(String t : teams) {
            String[] ss = t.split("[|]");
            if(ss.length < 2) {
                continue;
            }
            this.places.add(new String[]{ss[0], ss[1]});
        }

        if(restTime <= 0) {
            end = true;
            return;
        }

        pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(client, email, pm);

        if(ret.split("[|]")[0].equals("1")) { //可挑战，直接进入比赛
            restChangci = restChangci - 1;
            return;
        } else {
            if(restChangci > 0) {
                pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "4")); //4 表示直接结束冷却
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(client, email, pm);
                System.out.println(ret);
                restChangci = restChangci - 1;
            }
        }
    }
    public void afterPropertiesSet() throws Exception {
        TopChallengeMonitorThread.getInstance();
    }
}
