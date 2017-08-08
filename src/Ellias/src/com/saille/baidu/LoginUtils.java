package com.saille.baidu;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-24
 * Time: 9:10:34
 * To change this template use File | Settings | File Templates.
 */
public class LoginUtils {
    public static DefaultHttpClient login(String id, String pwd) {
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            HttpGet gm = new HttpGet("http://www.baidu.com/");
            CloseableHttpResponse resp = client.execute(gm);
            resp.close();
            gm.releaseConnection();


            HttpPost pm = new HttpPost("http://iyouxi.baidu.com/sso/login/login.json");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loginName", id));
            params.add(new BasicNameValuePair("password", pwd));
            params.add(new BasicNameValuePair("platformId", "1"));
            params.add(new BasicNameValuePair("isPhone", "false"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
             resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            pm.releaseConnection();

            long now = new Date().getTime();
            gm = new HttpGet("http://passport.bdimg.com/passApi/js/wrapper.js?cdnversion="+now+"&_="+now);
            resp = client.execute(gm);
            resp.close();
            gm.releaseConnection();

            gm = new HttpGet("http://passport.bdimg.com/passApi/js/login_tangram_98041df6.js");
            resp = client.execute(gm);
            resp.close();
            gm.releaseConnection();

            String gid = getGid();

            gm = new HttpGet("https://passport.baidu.com/v2/api/?" +
                    "getapi" +"&tpl=yx" +"&apiver=v3" +"&tt=" +new Date().getTime() +
                    "&class=login" +
                    "&gid=" +gid+
                    "&logintype=basicLogin" +"&callback=bd__cbs__f8ufvy");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            gm.releaseConnection();

            ret = ret.substring(ret.indexOf("\"token\"") + "\"token\"".length());
            ret = ret.substring(ret.indexOf("\"") + 1);
            String token = ret.substring(0, ret.indexOf("\""));

            pm = new HttpPost("https://passport.baidu.com/v2/api/?login");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("staticpage", "http://youxi.baidu.com/v3Jump.html"));
            params.add(new BasicNameValuePair("charset", "UTF-8"));
            params.add(new BasicNameValuePair("token", token));
            params.add(new BasicNameValuePair("tpl", "yx"));
            params.add(new BasicNameValuePair("subpro", ""));
            params.add(new BasicNameValuePair("apiver", "v3"));
            params.add(new BasicNameValuePair("tt", "" + new Date().getTime()));
            params.add(new BasicNameValuePair("codestring", ""));
            params.add(new BasicNameValuePair("safeflg", "0"));
            params.add(new BasicNameValuePair("u", "http://youxi.baidu.com/user_center.xhtml"));
            params.add(new BasicNameValuePair("isPhone", "false"));
            params.add(new BasicNameValuePair("detect", "1"));
            params.add(new BasicNameValuePair("gid", gid));
            params.add(new BasicNameValuePair("quick_user", "1"));
            params.add(new BasicNameValuePair("logintype", "basicLogin"));
            params.add(new BasicNameValuePair("logLogintype", "pc_loginBasic"));
            params.add(new BasicNameValuePair("idc", ""));
            params.add(new BasicNameValuePair("username", id));
            params.add(new BasicNameValuePair("password", pwd));
            params.add(new BasicNameValuePair("mem_pass", "on"));
            params.add(new BasicNameValuePair("ppui_logintime", "32423"));
            params.add(new BasicNameValuePair("countrycode", ""));
            params.add(new BasicNameValuePair("callback", "parent.bd_pcbs__4grOvg"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");


            gm = new HttpGet("http://youxi.baidu.com/seven_cool_user_session.xhtml?c=updateUserSession");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            gm.releaseConnection();

            gm = new HttpGet("http://youxi.baidu.com/ajax_user.xhtml?c=v2&isShowHistoryGame=1");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            gm.releaseConnection();
            return client;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static String getGid() {
        String tmp = "xxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx";
        byte[] bytes = tmp.getBytes();
        for(int i = 0; i < tmp.length(); i++) {
            if(tmp.charAt(i) == 'x') {
                int j = ((int)(new Random().nextDouble() * 16))|0;
                if(j < 10) {
                    bytes[i] = (byte) (j+48);
                } else {
                    bytes[i] = (byte) (j+55);
                }
            } else if(bytes[i] == 'y') {
                int j = ((int)(new Random().nextDouble() * 16)) & 3 | 8;
                if(j < 10) {
                    bytes[i] = (byte) (j+48);
                } else {
                    bytes[i] = (byte) (j+55);
                }
            }
        }
        return new String(bytes);
    }
}
