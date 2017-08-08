package com.saille.ogzq;

import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-7-5
 * Time: 1:03:15
 * To change this template use File | Settings | File Templates.
 */
public class Worldcup32Thread extends Thread{
    private String email;

    public Worldcup32Thread(String email) {
        this.email = email;
        this.setName("worldcup32-" + email);
    }

    public void run() {
        while(true) {
            try {
                HttpPost pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Load1", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(email, pm);
                String[] parts = ret.split("#");
                if(parts[0].indexOf("inmatch|") >= 0) { //比赛中
                    Thread.sleep(1000 * 10);
                    continue;
                } else if(!"".equals(parts[0].split("@")[3])) { //需要领奖
                    String[] cards = parts[0].split("@")[3].split("[|]");
                    boolean hasOpened = false;
                    for(String card : cards) {
                        if(card.split("[*]")[0].equals("0")) {
                            hasOpened = true;
                            break;
                        }
                    }
                    if(!hasOpened) {
                        pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("FlopCard1", "" + ((new Random().nextInt() % 5) + 1)));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        ret = IDUtils.execute(email, pm);
                    }

                    pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("AcceptCardAward1", ""));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    ret = IDUtils.execute(email, pm);
                    continue;
                } else { //打比赛
                    String[] teams = parts[0].split("@")[2].split("[\\^]");
                    for(String t : teams) {
                        int resttime = Integer.parseInt(t.split("[*]")[3]);
                        if(resttime > 0) {
                            pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("Challenge1", t.split("[*]")[0]));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            ret = IDUtils.execute(email, pm);
                            if(Integer.parseInt(ret) > 0) {

                            }
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
