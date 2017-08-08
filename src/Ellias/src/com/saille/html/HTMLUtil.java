package com.saille.html;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HTMLUtil {
    private static HTMLUtil instance;
    boolean needProxy = false;
    String proxyIP = "10.22.223.48";
    int proxyPort = 8080;

    public static HTMLUtil getInstance() {
        if(instance == null) {
            instance = new HTMLUtil();
        }
        return instance;
    }

    public String logInYSSY(String id, String pwd) {
        try {
            HttpClient client = new HttpClient();
            client.getState().setCookiePolicy(0);
            PostMethod method = new PostMethod("http://bbs.sjtu.cn/bbslogin");
            method.addParameters(new NameValuePair[]{new NameValuePair("id", id), new NameValuePair("pw", pwd)});
            method.getParams().setContentCharset("gb2312");
            if(this.needProxy) {
                client.getHostConfiguration().setProxy(this.proxyIP, this.proxyPort);
            }
            client.executeMethod(method);
            Cookie[] cookies = client.getState().getCookies();
            StringBuffer sb = new StringBuffer("");
            for(Cookie coo : cookies) {
                if(coo.getName().startsWith("utmp")) {
                    if(!sb.toString().equals("")) {
                        sb.append("; ");
                    }
                    sb.append(coo.getName()).append("=").append(coo.getValue());
                }
            }
            return sb.toString();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

//    public Object[] postWeb(String url, List<String[]> params, List<String[]> inCookies, List<String[]> headers) {
    public Object[] postWeb(String url, List<String[]> params, String cookie, List<String[]> headers) {
        Object[] ret = new Object[3];
        try {
            HttpClient client = new HttpClient();
            client.getState().setCookiePolicy(0);
            PostMethod method = new PostMethod(url);
            if((params != null) && (params.size() > 0)) {
                NameValuePair[] data = new NameValuePair[params.size()];
                for(int i = 0; i < params.size(); i++) {
                    data[i] = new NameValuePair((params.get(i))[0], (params.get(i))[1]);
                }
                method.addParameters(data);
            }
            if(headers != null) {
                for(String[] h : headers) {
                    method.addRequestHeader(h[0], h[1]);
                }
            }
            method.getParams().setContentCharset("gb2312");

            if(cookie != null) {
                method.addRequestHeader("Cookie", cookie);
            }
//            if(inCookies != null) {
//                Cookie[] cc = new Cookie[inCookies.size()];
//                String cook = "";
//                for(int i = 0; i < inCookies.size(); i++) {
//                    cc[i] = new Cookie();
//                    cc[i].setName((inCookies.get(i))[0]);
//                    cc[i].setValue((inCookies.get(i))[1]);
//                    cc[i].setDomain((inCookies.get(i))[2]);
//                    cc[i].setPath((inCookies.get(i))[3]);
//                    cook = cook + (inCookies.get(i))[0] + ":" + (inCookies.get(i))[1] + "; ";
//                }
//                method.addRequestHeader("Cookie", cook);
//                client.getState().addCookies(cc);
//            }

            if(this.needProxy) {
                client.getHostConfiguration().setProxy(this.proxyIP, this.proxyPort);
            }
            client.executeMethod(method);
            List cookies = new ArrayList();
            for(Cookie c : client.getState().getCookies()) {
                cookies.add(c);
            }
            ret[2] = new String(method.getResponseBody(), method.getResponseCharSet());
            List retHeaders = new ArrayList();
            for(Header header : method.getResponseHeaders()) {
                if(header.getName().equals("Set-Cookie")) {
                    Cookie c = new Cookie();
                    c.setName(header.getElements()[0].getName());
                    c.setValue(header.getElements()[0].getValue());
                    for(int i = 0; i < header.getElements().length; i++) {
                        for(NameValuePair ps : header.getElements()[i].getParameters()) {
                            if(ps.getName().equals("domain")) {
                                c.setDomain(ps.getValue());
                            } else if(ps.getName().equals("path")) {
                                c.setPath(ps.getValue());
                            }
                        }
                    }
                    cookies.add(c);
                } else {
                    retHeaders.add(header);
                }
            }
            Header[] retH = new Header[retHeaders.size()];
            for(int i = 0; i < retHeaders.size(); i++) {
                retH[i] = ((Header) retHeaders.get(i));
            }
            ret[1] = retH;
            ret[0] = cookies;

            method.releaseConnection();
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return ret;
    }

    public String getWeb(String url, List<String[]> params, String cookie) {
        try {
            HttpClient client = new HttpClient();

            client.getState().setCookiePolicy(0);
            GetMethod method = new GetMethod(url);
            method.getParams().setParameter("http.protocol.cookie-policy", "compatibility");

            method.getParams().setContentCharset("utf-8");
            method.addRequestHeader("x-forward-for", "10.45.0.39");
            method.addRequestHeader("FORWARDED_FOR", "100.45.0.39");
            method.addRequestHeader("x-forward-for", "1.1.1.1");
            method.addRequestHeader("x-forwarded-for", "1.1.1.1");
            method.addRequestHeader("HTTP_X_FORWARDED_FOR", "3.3.3.3");
            method.addRequestHeader("HTTP_X_FORWARD_FOR", "3.3.3.3");
            method.addRequestHeader("REMOTE_ADDR", "2.2.2.2");
            if(cookie != null) {
                method.addRequestHeader("Cookie", cookie);
            }
            if(this.needProxy) {
                client.getHostConfiguration().setProxy(this.proxyIP, this.proxyPort);
            }
            client.executeMethod(method);
            return new String(method.getResponseBody(), method.getResponseCharSet());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void PrintStream(InputStream is, String charset) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        while((s = new String(br.readLine().getBytes(), charset)) != null) {
            System.out.println(s);
        }
    }
}