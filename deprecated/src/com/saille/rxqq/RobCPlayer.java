package com.saille.rxqq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HeaderElement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class RobCPlayer {
    private int id;
    private String value;
    private static final int n = 0;
    private static final int o = 8;
    private static Map<String, Integer> prices = new HashMap();
    private static String ROB_URL = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?BuyPrice=%s&Idx=%s&action=BidUp&CurrencyType=2";

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private static int q(int t, int b, int c, int d) {
        if(t < 20) {
            return b & c | (b ^ 0xFFFFFFFF) & d;
        }
        if(t < 40) {
            return b ^ c ^ d;
        }
        if(t < 60) {
            return b & c | b & d | c & d;
        }
        return b ^ c ^ d;
    }

    private static int r(int t) {
        return t < 60 ? -1894007588 : t < 40 ? 1859775393 : t < 20 ? 1518500249 : -899497514;
    }

    private static int u(int x, int y) {
        int a = (x & 0xFFFF) + (y & 0xFFFF);
        int b = (x >> 16) + (y >> 16) + (a >> 16);
        return b << 16 | a & 0xFFFF;
    }

    private static int v(int a, int b) {
        return a << b | a >>> 32 - b;
    }

    private static int[] z(String aa) {
        int[] a = new int[aa.length()];
        for(int i = 0; i < aa.length(); i++) {
            a[i] = aa.charAt(i);
        }
        int[] b = new int[a.length * 8];
        int c = 255;
        for(int i = 0; i < a.length * 8; i += 8) {
            b[(i >> 5)] |= (a[(i / 8)] & c) << 24 - i % 32;
        }
        int size = 0;
        for(int i = 0; i < b.length; i++) {
            if(b[i] == 0) {
                size = i;
                break;
            }
        }
        int[] ret = new int[size];
        for(int i = 0; i < size; i++) {
            ret[i] = b[i];
        }
        return ret;
    }

    private static String A(int[] a) {
        String b = "0123456789abcdef";
        String c = "";
        for(int i = 0; i < a.length * 4; i++) {
            c = c + b.charAt(a[(i >> 2)] >> (3 - i % 4) * 8 + 4 & 0xF);
            c = c + b.charAt(a[(i >> 2)] >> (3 - i % 4) * 8 & 0xF);
        }
        return c;
    }

    private static int[] p(int[] x, int f) {
        if(x.length < (f >> 5) + 1) {
            int[] xx = new int[(f >> 5) + 1];
            for(int i = 0; i < x.length; i++) {
                xx[i] = x[i];
            }
            for(int i = x.length; i < (f >> 5) + 1; i++) {
                xx[i] = 0;
            }
            x = xx;
        }
        x[(f >> 5)] |= 128 << 24 - f % 32;
        if(x.length < (f + 64 >> 9 << 4) + 16) {
            int[] n = new int[(f + 64 >> 9 << 4) + 16];
            for(int i = 0; i < x.length; i++) {
                n[i] = x[i];
            }
            for(int i = x.length; i < (f + 64 >> 9 << 4) + 16; i++) {
                n[i] = 0;
            }
            x = n;
        }
        x[((f + 64 >> 9 << 4) + 15)] = f;
        int[] w = new int[80];
        int a = 1732584193;
        int b = -271733879;
        int c = -1732584194;
        int d = 271733878;
        int e = -1009589776;
        for(int i = 0; i < x.length; i += 16) {
            int g = a;
            int h = b;
            int k = c;
            int l = d;
            int m = e;
            for(int j = 0; j < 80; j++) {
                if(j < 16) {
                    w[j] = x[(i + j)];
                } else {
                    w[j] = v(w[(j - 3)] ^ w[(j - 8)] ^ w[(j - 14)] ^ w[(j - 16)], 1);
                }
                int t = u(u(v(a, 5), q(j, b, c, d)), u(u(e, w[j]), r(j)));
                e = d;
                d = c;
                c = v(b, 30);
                b = a;
                a = t;
            }
            a = u(a, g);
            b = u(b, h);
            c = u(c, k);
            d = u(d, l);
            e = u(e, m);
        }
        return new int[]{a, b, c, d, e};
    }

    private static String hex_sha1(String s) {
        return A(p(z(s), s.length() * 8));
    }

    private static void genCookieFromResponse(Cookie[] cookies, Map<String, Cookie> origCookies) {
        for(Cookie c : cookies) {
            origCookies.put(c.getName(), c);
        }
    }

    private static String genCookie(Map<String, Cookie> cookies) {
        if((cookies == null) || (cookies.size() == 0)) {
            return "";
        }
        String ret = "";
        for(Cookie c : cookies.values()) {
            ret = ret + "; " + c.getName() + "=" + c.getValue();
        }
        return ret.substring(2);
    }

    private static String getTicket(String origStr) {
        if(origStr == null) {
            return "";
        }
        if(origStr.indexOf("\"ticket\"") != -1) {
            String tmp = origStr.substring(origStr.indexOf("ticket\":\"") + "ticket\":\"".length());
            String t = tmp.substring(0, tmp.indexOf("\""));
            System.out.println("ticket: " + t);
            return t;
        }
        return "";
    }

    private static void genCookieFromResponse(Header[] headers, Map<String, Cookie> cookies) {
        Cookie prvCookie;
        for(Header header : headers) {
            if(header.getName().equals("Set-Cookie")) {
                if(header.getElements()[0].getValue().equals("deleted")) {
                    cookies.remove(header.getElements()[0].getName());
                } else {
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
                    prvCookie = (Cookie) cookies.put(c.getName(), c);
                }
            }
        }
    }

    public static String[] getParams(String s) {
        if((s == null) || (s.length() == 0)) {
            return null;
        }
        if(s.indexOf("sinaSSOController.preloginCallBack") == -1) {
            return null;
        }
        s = s.substring("sinaSSOController.preloginCallBack".length());
        String[] ret = new String[3];
        String tmp = s.substring(s.indexOf("\"retcode\":") + "\"retcode\":".length());
        tmp = tmp.substring(0, tmp.indexOf(","));
        ret[0] = tmp;
        tmp = s.substring(s.indexOf("\"servertime\":") + "\"servertime\":".length());
        tmp = tmp.substring(0, tmp.indexOf(","));
        ret[1] = tmp;
        tmp = s.substring(s.indexOf("\"nonce\":\"") + "\"nonce\":\"".length());
        tmp = tmp.substring(0, tmp.indexOf("\""));
        ret[2] = tmp;
        return ret;
    }

    private static void loadPlayers() {
        try {
            File f = new File("D:\\work\\Ellias\\exploded\\rxqq\\robcplayer.txt");
            FileInputStream fis = new FileInputStream(f);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String tmp = null;
            while((tmp = br.readLine()) != null) {
                String[] prices = tmp.split("\t");
                String name = prices[0];
                for(int i = 1; i <= 10; i++) {
                    if(StringUtils.isNotEmpty(prices[i])) {
                        RobCPlayer.prices.put(name + "_" + i, Integer.valueOf(Integer.parseInt(prices[i])));
                        System.out.println("增加价格：" + name + "/lv " + i + ": " + prices[i]);
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String id = "wolaijiangyou@sina.com";
        String pwd = "lspmgk";
        try {
            HttpClient client = new HttpClient();
            client.getParams().setCookiePolicy("compatibility");
            client.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));
            Map cookies = new HashMap();
            List headers = new ArrayList();

            String url = "http://login.sina.com.cn/sso/prelogin.php?entry=wanwan&callback=sinaSSOController.preloginCallBack&user=" + id + "&client=ssologin.js(v1.3.15)&_=" + System.currentTimeMillis();
            GetMethod getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Referer", "http://wanwan.sina.com.cn/wanloginreg/wanarea/h1.php");
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            String[] l1Params = getParams(new String(getMethod.getResponseBody(), getMethod.getResponseCharSet()));

            url = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.15)";
            PostMethod postMethod = new PostMethod(url);
            postMethod.addParameter(new NameValuePair("entry", "wanwan"));
            postMethod.addParameter(new NameValuePair("gateway", "1"));
            postMethod.addParameter(new NameValuePair("from", ""));
            postMethod.addParameter(new NameValuePair("savestate", "0.0069"));
            postMethod.addParameter(new NameValuePair("useticket", "0"));
            postMethod.addParameter(new NameValuePair("username", id));
            postMethod.addParameter(new NameValuePair("service", "wanwan"));
            postMethod.addParameter(new NameValuePair("servertime", l1Params[1]));
            postMethod.addParameter(new NameValuePair("nonce", l1Params[2]));
            postMethod.addParameter(new NameValuePair("pwencode", "wsse"));
            postMethod.addParameter(new NameValuePair("password", hex_sha1(hex_sha1(hex_sha1(pwd)) + l1Params[1] + l1Params[2])));
            postMethod.addParameter(new NameValuePair("encoding", "GB2312"));
            postMethod.addParameter(new NameValuePair("callback", "parent.sinaSSOController.loginCallBack"));
            postMethod.addParameter(new NameValuePair("returntype", "IFRAME"));
            postMethod.addParameter(new NameValuePair("setdomain", "1"));
            client.executeMethod(postMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            genCookieFromResponse(postMethod.getResponseHeaders(), cookies);

            url = "http://login.sina.com.cn/sso/login.php?entry=wanwan&service=webgame03602&returntype=TEXT&callback=func";
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Cookie", genCookie(cookies));
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            String ret = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);

            url = "http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/dologin.php";
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Cookie", genCookie(cookies));
            getMethod.addRequestHeader("Referer", "http://wanwan.sina.com.cn/wanloginreg/wanarea/h1.php");
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);

            url = "http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/dogame.php?service=webgame03602";
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Cookie", genCookie(cookies));
            getMethod.addRequestHeader("Referer", "http://2010.sina.com.cn/qiuqiu/");
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);

            String ticket = getTicket(ret);
            url = "http://s3.qiuqiu.2010.sina.com.cn/login.aspx?ticket=" + ticket;
            getMethod = new GetMethod(url);
            getMethod.addRequestHeader("Referer", "http://2010.sina.com.cn/qiuqiu/");
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);

            url = "http://s3.qiuqiu.2010.sina.com.cn/Login.do?action=1ogdlaiy";
            getMethod = new GetMethod(url);
            client.executeMethod(getMethod);
            genCookieFromResponse(client.getState().getCookies(), cookies);
            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);

            loadPlayers();

            int pageCount = 1;
            int maxPage = 1;

            String s1 = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=Select&ItemType=4&Order=2&ItemLevel=2&Index=2&PageIndex=%s";
            String s2 = "http://s3.qiuqiu.2010.sina.com.cn/Auction.do?action=Select&ItemType=4&Order=2&ItemLevel=1&Index=2&PageIndex=%s";
            boolean legend = false;
            int errorCount = 0;
            int checkCount = 0;
            while(errorCount < 100) {
                if(pageCount == 1) {
                    System.out.println(new Date() + "循环..." + checkCount++ + "\tpage: " + pageCount + "/" + maxPage);
                }
                try {
                    String s = legend ? s2 : s1;
                    url = String.format(s, new Object[]{Integer.valueOf(pageCount)});
                    getMethod = new GetMethod(url);
                    client.executeMethod(getMethod);
                    ret = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
                    JSONObject json = new JSONObject(ret);
                    if(json.has("PageCount")) {
                        maxPage = json.getInt("PageCount");
                    } else {
                        maxPage = 1;
                    }
                    if((pageCount >= maxPage) || (pageCount <= 0)) {
                        pageCount = 1;
                        legend = !legend;
                    } else {
                        pageCount++;
                    }
                    if(json.has("Info")) {
                        JSONArray players = json.getJSONArray("Info");
                        for(int i = 0; i < players.length(); i++) {
                            JSONObject player = players.getJSONObject(i);
                            checkRob(player, client);
                        }
                    }
                    errorCount = 0;
                } catch(Exception ex) {
                    ex.printStackTrace();
                    errorCount++;
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static boolean checkRob(JSONObject player, HttpClient client) {
        try {
            if(!player.has("itemStrengthLevel")) {
                System.out.println("找不到itemStrengthLevel");
                return false;
            }
            if(!player.has("itemName")) {
                System.out.println("找不到itemName");
                return false;
            }
            if(!player.has("sellEndPrice")) {
                System.out.println("找不到sellEndPrice");
                return false;
            }
            if(!player.has("currencyType")) {
                System.out.println("找不到currencyType");
            }
            if(!player.has("itemLevel")) {
                System.out.println("找不到itemLevel");
            }
            String name = player.getString("itemName");
            int level = player.getInt("itemStrengthLevel");
            int price = player.getInt("sellEndPrice");
            int itemLevel = player.getInt("itemLevel");
            Integer setPrice = null;
            if(itemLevel == 1) {
                setPrice = (Integer) prices.get("#" + name + "_" + level);
            } else if(itemLevel == 2) {
                setPrice = (Integer) prices.get(name + "_" + level);
            }
            if(setPrice == null) {
                return false;
            }
            if(player.getInt("currencyType") != 2) {
                System.out.println("出售方式不是点券");
            }
            if((price > 0) && (setPrice.intValue() >= price)) {
                System.out.println(name + ", lv " + level + "一口价：" + price + "，低于设定价格：" + setPrice + "，需要截卡");
                String url = String.format(ROB_URL, new Object[]{Integer.valueOf(price), Integer.valueOf(player.getInt("idx"))});
                GetMethod getMethod = new GetMethod(url);
                client.executeMethod(getMethod);
                String msg = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
                doLog(new Date() + "抢卡: " + name + "，等级：" + level + "，价格：" + price + "，卖家：" + player.getString("sellerName") + "，挂卡时间：" + player.getString("rowTime"));
                doLog("消息：" + msg);
                return true;
            }
            return false;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private static void doLog(String log) {
        try {
            File f = new File("D:\\robcard.txt");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            fos.write(log.getBytes());
            fos.write("\r\n".getBytes());
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}