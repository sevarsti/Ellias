package com.saille.ogzq;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;

import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-11-5
 * Time: 13:53:59
 * To change this template use File | Settings | File Templates.
 */
public class LockIDThread extends Thread{
    DefaultHttpClient client = new DefaultHttpClient();
    public void run() {
        DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0, false);
        client.setHttpRequestRetryHandler(handler);
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
        client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向
        while(true) {
            try {
                PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/lock.ini"));
                String[] ids = conf.getStringArray("id");
                if(ids != null) {
                    for(String id : ids) {
                        if(id == null || id.length() > 0) {
                            String server = id.substring(id.lastIndexOf("@") + 1);
                            id = id.substring(0, id.indexOf("@"));
                            for(int i = 0; i < 10; i++) {
                                if(server.startsWith("e7wan")) {
                                    loginE7wan(id);
                                } else if(server.startsWith("fenghuang")) {
                                    loginFenghuang(id);
                                }
                            }
                            System.out.println("锁定" + id + "完毕");
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(1000 * 60 * 2);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void loginE7wan(String id) throws Exception {
        HttpGet gm = new HttpGet("http://pass.e7wan.com/login.php?act=login&gameName=ogzq&name=" + id + "&pwd=fwjielc&code=&forward=http%3A%2F%2Fogzq.e7wan.com%2Findex.html");
        client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
        client.getParams().setParameter("http.protocol.single-cookie-header", true);
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
        client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

        CloseableHttpResponse response = client.execute(gm);
        response.getEntity().getContent().close();
        response.close();
        gm.releaseConnection();
    }

    private void loginFenghuang(String id) throws Exception {

        HttpPost pm = new HttpPost("http://play.ifeng.com/?_a=Dologin");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", id));
        params.add(new BasicNameValuePair("password", "asdf"));
        params.add(new BasicNameValuePair("inpass", "on"));
        params.add(new BasicNameValuePair("game", "ogzq"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        CloseableHttpResponse res = client.execute(pm);
        InputStream is = res.getEntity().getContent();
//        String ret = CommonUtils.getString(res.getEntity().getContent(), "utf-8");
        is.close();
        res.close();
        pm.releaseConnection();
    }
}
