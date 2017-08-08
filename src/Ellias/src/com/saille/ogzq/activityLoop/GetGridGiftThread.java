package com.saille.ogzq.activityLoop;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-11-19
 * Time: 15:17:48
 * To change this template use File | Settings | File Templates.
 */
public class GetGridGiftThread extends Thread{
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private static GetGridGiftThread instance = null;

    private GetGridGiftThread() {}
    public synchronized static GetGridGiftThread getInstance() {
        if(instance == null) {
            instance = new GetGridGiftThread();
        }
        return instance;
    }

    public void run() {
        boolean goon = true;
        while(goon) {
            try {
                List<String> gets = new ArrayList<String>();
                List<String> ids = IDUtils.GETIDS();
                for(String id : ids) {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("load", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String ret = IDUtils.execute(id, pm);
                    String[] ss;
                    if(ret.indexOf("╋") >= 0) {
                        ss = ret.substring(0, ret.indexOf("╋")).split("[|]");
                    } else {
                        ss = ret.split("[|]");
                    }
                    if("-1".equals(ss[0])) {
                        GetGridGiftThread.instance = null;
                        goon = false;
                        break;
                    }
                    if(ss[0].equals("0") && Integer.parseInt(ss[3]) > 0) {
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("clickToGetReward", "1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(id, pm);
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("getReward", "1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info(id + "/15分钟::::::" + IDUtils.execute(id, pm));
                    }
                    if(ss[1].equals("0") && Integer.parseInt(ss[4]) > 0) {
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("clickToGetReward", "2"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(id, pm);
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("getReward", "2"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info(id + "/ 6小时::::::" + IDUtils.execute(id, pm));
                    }
                    if(ss[2].equals("0") && Integer.parseInt(ss[5]) > 0) {
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("clickToGetReward", "3"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(id, pm);
                        pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("getReward", "3"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info(id + "/12小时::::::" + IDUtils.execute(id, pm));
                    }
                }
                Thread.sleep(60000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
