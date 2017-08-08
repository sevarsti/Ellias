package com.saille.fetion;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.Calendar;
import java.util.ArrayList;

import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2012-11-24
 * Time: 2:30:15
 * To change this template use File | Settings | File Templates.
 */
public class Test {
    public static void main(String[] args) {
        try {
            if(true) {
                HttpPost pm = new HttpPost("http://f2.ogzq.xdgame.cn/Gullit_Top_Club.aspx");
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                HttpResponse response = new DefaultHttpClient().execute(pm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                System.out.println(ret);
                System.exit(0);
            }
//            HttpClient client = new HttpClient();
//            client.getParams().setCookiePolicy("compatibility");
//            client.getParams().setParameter("http.protocol.single-cookie-header", true);
//
//            String url = "https://webim.feixin.10086.cn/WebIM/Login.aspx";
//            PostMethod pm = new PostMethod(url);
//            pm.addParameter(new NameValuePair("UserName", "13818207760"));
//            pm.addParameter(new NameValuePair("Pwd", "zloves512618"));
//            pm.addParameter(new NameValuePair("AccountType", "1"));
//            pm.addParameter(new NameValuePair("OnlineStatus", "400"));
//
//            System.out.println(client.executeMethod(pm));
//            String rc = new String(pm.getResponseBody(), pm.getResponseCharSet());
//            JSONObject json = new JSONObject(rc);
//            switch(json.getInt("rc")) {
//                case 999: //need verifycode
//                {
//                    url = "https://webim.feixin.10086.cn/WebIM/GetPicCode.aspx?Type=ccpsession&" + Math.random();
//                    GetMethod gm = new GetMethod(url);
//                    client.executeMethod(gm);
//                    File f = new File("D:\\code.png");
//                    FileOutputStream fos = new FileOutputStream(f);
//                    byte[] b = gm.getResponseBody();
//                    fos.write(b);
//                    fos.close();
//                    System.out.println("input code:");
//                    InputStreamReader isr = new InputStreamReader(System.in);
//                    BufferedReader br = new BufferedReader(isr);
//                    String ret = br.readLine();
//                    url = "https://webim.feixin.10086.cn/WebIM/Login.aspx";
//                    pm = new PostMethod(url);
//                    pm.addParameter(new NameValuePair("UserName", "13818207760"));
//                    pm.addParameter(new NameValuePair("Pwd", "zloves512618"));
//                    pm.addParameter(new NameValuePair("AccountType", "1"));
//                    pm.addParameter(new NameValuePair("OnlineStatus", "400"));
//                    pm.addParameter(new NameValuePair("Ccp", ret));
//                    System.out.println(client.executeMethod(pm));
//                    rc = new String(pm.getResponseBody(), pm.getResponseCharSet());
//                }
//            }
//            String[] ids = new String[2]; //1: userid, 0: sessionid
//            Cookie[] cookies = client.getState().getCookies();
//            for(Cookie c : cookies) {
//                if(c.getName().equals("webim_usersid")) {
//                    ids[1] = c.getValue();
//                } else if(c.getName().equals("webim_sessionid")) {
//                    ids[0] = c.getValue();
//                }
//            }
//            Cookie newCookie = new Cookie();
//            newCookie.setDomain(".feixin.10086.cn");
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.MINUTE, 2);
//            newCookie.setExpiryDate(c.getTime());
//            newCookie.setPath("/");
//            newCookie.setName("webim_remindmsgs");
//            newCookie.setValue("");
//            client.getState().addCookie(newCookie);
//            pm = new PostMethod("https://webim.feixin.10086.cn/WebIM/GetPersonalInfo.aspx?Version=1");
//            pm.addParameter(new NameValuePair("ssid", ids[0]));
//            System.out.println(client.executeMethod(pm));
//            System.out.println(new String(pm.getResponseBody(), pm.getResponseCharSet()));
//
//            pm = new PostMethod("https://webim.feixin.10086.cn/WebIM/GetContactList.aspx?Version=2");
//            pm.addParameter(new NameValuePair("ssid", ids[0]));
//            System.out.println(client.executeMethod(pm));
////            System.out.println(new String(pm.getResponseBody(), pm.getResponseCharSet()));
//
//            //loop query: getConnect
//            int i = 3;
//            while(true) {
//                System.out.println("loop...");
//                pm = new PostMethod("https://webim.feixin.10086.cn/WebIM/GetConnect.aspx?Version=" + (i++));
//                pm.addParameter(new NameValuePair("ssid", ids[0]));
//                System.out.println(client.executeMethod(pm));
//                String s = new String(pm.getResponseBody(), pm.getResponseCharSet());
//                System.out.println(s);
//                System.out.println(s.indexOf("\"DataType\":3") >= 0);
//                Thread.sleep(1000);
//                newCookie = new Cookie();
//                newCookie.setDomain(".feixin.10086.cn");
//                c = Calendar.getInstance();
//                c.add(Calendar.MINUTE, 2);
//                newCookie.setExpiryDate(c.getTime());
//                newCookie.setPath("/");
//                newCookie.setName("webim_remindmsgs");
//                newCookie.setValue("");
//                client.getState().addCookie(newCookie);            }
//            //send sms
////            pm = new PostMethod("https://webim.feixin.10086.cn/WebIM/SendSMS.aspx?Version=9");
////            pm.addParameter(new NameValuePair("UserName", ids[1]));
////            pm.addParameter(new NameValuePair("Message", "test"));
////            pm.addParameter(new NameValuePair("Receivers", "229677012"));
////            pm.addParameter(new NameValuePair("ssid", ids[0]));
////            System.out.println(client.executeMethod(pm));
////            System.out.println(new String(pm.getResponseBody(), pm.getResponseCharSet()));
//
//            //uid: 586155326
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}
