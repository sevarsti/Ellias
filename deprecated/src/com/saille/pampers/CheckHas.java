package com.saille.pampers;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;

public class CheckHas {
    public static void main(String[] args) {
        try {
            String url = "http://epoint.pampers.com.cn/system/Login2.aspx";
            String user = "sucan611@gmail.com";
            String pwd = "woshimaizi";
            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy("compatibility");
            client.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));

            GetMethod gm = new GetMethod(url + "?email=" + user + "&password=" + pwd + "&fromweb=ECRM&url=https://epoint.pampers.com.cn/system/action.aspx?u=http%3a%2f%2fepoint.pampers.com.cn%2findex.aspx%3fr%3d634833765236939032");
            client.executeMethod(gm);
            url = "https://epoint.pampers.com.cn/pages/rewards.aspx";
            gm = new GetMethod(url);
            client.executeMethod(gm);

            String content = gm.getResponseBodyAsString();
            content = content.substring(content.indexOf("<table width=\"220\" class=\"index2_my\"  border=\"0\" cellpadding=\"0\" cellspacing=\"0\">"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}