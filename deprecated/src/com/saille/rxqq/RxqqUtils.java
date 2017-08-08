package com.saille.rxqq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

public class RxqqUtils {
    private static final Logger LOGGER = Logger.getLogger(RxqqUtils.class);

    public static boolean autoCookie = true;

    private static void genCookieFromResponse(Cookie[] cookies, Map<String, Cookie> origCookies) {
        for(Cookie c : cookies) {
            origCookies.put(c.getName(), c);
        }
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

    public static Object[] login(String id, String pwd) {
        try {
            HttpClient client;
            while(true) {
                try {
                    String su = LoginUtils.base64Encode(id);
                    client = new HttpClient();
                    client.getParams().setParameter("http.useragent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.6) Gecko/20100625 Firefox/3.6.6");

                    if(autoCookie) {
                        client.getParams().setCookiePolicy("compatibility");
                        client.getParams().setParameter("http.protocol.single-cookie-header", Boolean.valueOf(true));
                    } else {
                        client.getParams().setCookiePolicy("ignoreCookies");
                    }
                    Map cookies = new HashMap();
                    Cookie loginname = new Cookie();
                    loginname.setDomain("sina.com.cn");
                    loginname.setName("wanloginname");
                    loginname.setValue(id);
                    loginname.setPath("/");
                    cookies.put("wanloginname", loginname);
                    List headers = new ArrayList();

                    String url = "http://login.sina.com.cn/cgi/pin.php?s=0&r=123";
                    GetMethod getMethod = new GetMethod(url);
                    getMethod.addRequestHeader("Referer", "http://2010.sina.com.cn/qiuqiu/");
                    client.executeMethod(getMethod);
                    genCookieFromResponse(getMethod.getResponseHeaders(), cookies);
                    String path = "/rxqq/verifycode/" + id + "_" + new Date().getTime() + ".png";
                    RxqqInstance.verifyFile.put(id, path);
                    File f = new File(RxqqDwr.class.getResource("../../../../..").getFile() + path);

                    if(!f.exists()) {
                        f.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(f);
                    fos.write(getMethod.getResponseBody());
                    fos.close();

                    url = "http://login.sina.com.cn/sso/prelogin.php?entry=wanwan&callback=sinaSSOController.preloginCallBack&client=ssologin.js(v1.3.17)&_=" + System.currentTimeMillis() + "&su=" + su.replaceAll("@", "%3D");

                    getMethod = new GetMethod(url);
                    getMethod.addRequestHeader("Referer", "http://2010.sina.com.cn/qiuqiu/");
                    if(!autoCookie) {
                        getMethod.addRequestHeader("Cookie", genCookie(cookies));
                    }
                    client.executeMethod(getMethod);
                    genCookieFromResponse(client.getState().getCookies(), cookies);
                    String[] l1Params = getParams(new String(getMethod.getResponseBody(), getMethod.getResponseCharSet()));

                    boolean ok = false;
                    boolean first = true;
                    if(!ok) {
                        url = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.3.17)";
                        PostMethod postMethod = new PostMethod(url);
                        postMethod.addRequestHeader("Referer", "http://2010.sina.com.cn/qiuqiu/");
                        postMethod.addRequestHeader("Keep-Alive", "115");
                        postMethod.addRequestHeader("Connection", "keep-alive");
                        postMethod.addParameter(new NameValuePair("entry", "wanwan"));
                        postMethod.addParameter(new NameValuePair("gateway", "1"));
                        postMethod.addParameter(new NameValuePair("from", ""));
                        postMethod.addParameter(new NameValuePair("savestate", "0.0069"));
                        postMethod.addParameter(new NameValuePair("useticket", "0"));
                        if((!first) && (RxqqInstance.verifyFile.containsKey(id))) {
                            if(RxqqInstance.verifyCode.containsKey(id)) {
                                continue;
                            }
                            try {
                                Thread.sleep(1000L);
                            } catch(Exception ex) {
                                continue;
                            }
                            postMethod.addParameter(new NameValuePair("door", (String) RxqqInstance.verifyCode.get(id)));
                            RxqqInstance.verifyCode.remove(id);
                            if(!autoCookie) {
                                postMethod.addRequestHeader("Cookie", genCookie(cookies));
                            }
                        }
                        postMethod.addParameter(new NameValuePair("su", su));
                        postMethod.addParameter(new NameValuePair("service", "wanwan"));
                        postMethod.addParameter(new NameValuePair("servertime", l1Params[1]));
                        postMethod.addParameter(new NameValuePair("nonce", l1Params[2]));
                        postMethod.addParameter(new NameValuePair("pwencode", "wsse"));
                        postMethod.addParameter(new NameValuePair("sp", LoginUtils.hex_sha1(LoginUtils.hex_sha1(LoginUtils.hex_sha1(pwd)) + l1Params[1] + l1Params[2])));
                        l1Params[1] = String.valueOf(new Date().getTime() / 1000L);
                        l1Params[2] = LoginUtils.generateNonce(6);
                        postMethod.addParameter(new NameValuePair("encoding", "UTF-8"));
                        postMethod.addParameter(new NameValuePair("callback", "parent.sinaSSOController.loginCallBack"));
                        postMethod.addParameter(new NameValuePair("returntype", "IFRAME"));
                        postMethod.addParameter(new NameValuePair("setdomain", "1"));

                        if(!autoCookie) {
                            client.getState().clearCookies();
                        }
                        client.executeMethod(postMethod);
                        genCookieFromResponse(client.getState().getCookies(), cookies);
                        genCookieFromResponse(postMethod.getResponseHeaders(), cookies);

                        String ret = new String(postMethod.getResponseBody(), postMethod.getResponseCharSet());
                        ret = ret.substring(ret.indexOf("parent.sinaSSOController.loginCallBack(") + "parent.sinaSSOController.loginCallBack(".length());
                        ret = ret.substring(0, ret.indexOf("}") + 1);
                        JSONObject json = new JSONObject(ret);
                        int retcode = json.getInt("retcode");
                        System.out.println(id + "/" + retcode + ":" + json.optString("reason", "Ellias: 无错误信息"));
                        if((retcode != 4049) && (retcode != 2070) && (retcode != 2093)) {
                            RxqqInstance.verifyCode.remove(id);
                            RxqqInstance.verifyFile.remove(id);
                            continue;
                        } else {
                            url = "http://login.sina.com.cn/cgi/pin.php?s=0&r=" + new Date().getTime();
                            getMethod = new GetMethod(url);
                            if(!autoCookie) {
                                getMethod.addRequestHeader("Cookie", genCookie(cookies));
                            }
                            client.executeMethod(getMethod);
                            genCookieFromResponse(getMethod.getResponseHeaders(), cookies);
                            path = "/rxqq/verifycode/" + id + "_" + new Date().getTime() + ".png";
                            RxqqInstance.verifyFile.put(id, path);
                            f = new File(RxqqDwr.class.getResource("../../../../..").getFile() + path);
                            if(!f.exists()) {
                                f.createNewFile();
                            }
                            fos = new FileOutputStream(f);
                            fos.write(getMethod.getResponseBody());
                            fos.close();
                        }
                        ok = true;
                        first = false;
                        continue;
                    }
                    ok = false;
                    if(!ok) {
                        url = "http://login.sina.com.cn/sso/login.php?entry=wanwan&service=webgame03602&returntype=TEXT&callback=func";
                        getMethod = new GetMethod(url);
                        if(!autoCookie) {
                            getMethod.addRequestHeader("Cookie", genCookie(cookies));
                        }
                        client.executeMethod(getMethod);
                        genCookieFromResponse(client.getState().getCookies(), cookies);
                        String ret = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
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
                        ret = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
                        try {
                            JSONObject j = new JSONObject(ret);
                            if(!j.has("Code")) {
                                LOGGER.info(id + "登录时找不到Code");
                                Iterator ii = j.keys();
                                if(ii.hasNext()) {
                                    String k = (String) ii.next();
                                    LOGGER.info(k + ": " + j.get(k));
                                    continue;
                                }
                            }
                            if(j.optInt("Code", -1) == 0) {
                                ok = true;
                            } else {
                                continue;
                            }
                        } catch(Exception ex) {
                            continue;
                        }
                        genCookieFromResponse(client.getState().getCookies(), cookies);
                        genCookieFromResponse(getMethod.getResponseHeaders(), cookies);
                    }
                    if(ok) {
                        break;
                    }
                } catch(Exception ex) {
                    LOGGER.error(id + "登录失败，重新尝试中：" + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            RxqqInstance.id.put(id, new Object[]{client, Integer.valueOf(0)});
            myInfo(id);
            rank(id);
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            LOGGER.error("id: " + id + "," + sw.toString());
        }
        return null;
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

    public static String myInfo(String id) {
        while(true) {
            try {
                String info = execute(id, "http://s3.qiuqiu.2010.sina.com.cn/Manager.do?action=myinfo");
                if(info != null) {
                    RxqqInstance.info.put(id, new JSONObject(info));
                    return info;
                }
                return "";
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                LOGGER.error("id: " + id + "," + sw.toString());
            }
        }
    }

    public static String rank(String id) {
        while(true) {
            try {
                String info = execute(id, "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?action=myinfo");
                String info2 = execute(id, "http://s3.qiuqiu.2010.sina.com.cn/CrossArena.do?domain=1&action=ranklist");
                if(info != null) {
                    RxqqInstance.crossRank.put(id, new JSONObject[]{new JSONObject(info), new JSONObject(info2)});
                    break;
                }
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                LOGGER.error("id: " + id + "," + sw.toString());
            }
        }
        while(true) {
            try {
                String info = execute(id, "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=managerinfo");
                String info2 = execute(id, "http://s3.qiuqiu.2010.sina.com.cn/Arena.do?action=rank");
                if(info != null) {
                    RxqqInstance.arenaRank.put(id, new JSONObject[]{new JSONObject(info), new JSONObject(info2)});
                    break;
                }
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                LOGGER.error("id: " + id + "," + sw.toString());
            }
        }
        return null;
    }

    public static String getMatchPlayer(String id) {
        try {
            String matchId = null;
            do {
                matchId = RxqqInstance.updateMatch(id, null, 2);
                if(StringUtils.isEmpty(matchId)) {
                    return null;
                }

                String url = "http://s3.qiuqiu.2010.sina.com.cn/Index.aspx?isPlayBack=1&MatchType=1&MatchId=" + matchId;
                execute(id, url);
                url = "http://s3.qiuqiu.2010.sina.com.cn/interface/Get-MatchStat.aspx?MatchType=1&MatchId=" + matchId;
                execute(id, url);
                url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?action=GetLottery&MatchId=" + matchId;
                execute(id, url);
                url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?action=SaveLottery&MatchId=" + matchId;
                String ret = execute(id, url);
                JSONObject getPlayer = new JSONObject(ret);
                if(!getPlayer.has("ResultCards")) {
                    RxqqInstance.updateMatch(id, matchId, 1);
                    LOGGER.warn(getPlayer.optString(id + ":" + "Message", "null---"));
                    LOGGER.warn(id + ", key: ResultCards没有对应的内容！");
                    Iterator it = getPlayer.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        LOGGER.info(key + ": " + getPlayer.getString(key));
                    }
                }

                JSONArray players = getPlayer.getJSONArray("ResultCards");
                String out = id + "得到球员：";
                for(int i = 0; i < players.length(); i++) {
                    out = out + players.getJSONObject(i).getString("Name") + ",";
                }
                LOGGER.info(out);
            } while(matchId != null);
            return null;
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            LOGGER.error("id: " + id + "," + sw.toString());
        }
        return null;
    }

    public static String execute(String id, String url) {
        Object[] objs = (Object[]) RxqqInstance.id.get(id);
        if(objs == null) {
            return null;
        }
        HttpClient client = (HttpClient) objs[0];
        int lock = ((Integer) objs[1]).intValue();
        boolean retry = false;
        while(lock != 0) {
            try {
                objs = (Object[]) RxqqInstance.id.get(id);
                lock = ((Integer) objs[1]).intValue();
                Thread.sleep(100L);
            } catch(Exception ex) {
            }
        }
        objs[1] = Integer.valueOf(1);
        String ret = null;
        while(true) {
            try {
                if(!RxqqInstance.id.containsKey(id)) {
                    break;
                }
                if(((Object[]) RxqqInstance.id.get(id))[0] != client) {
                    break;
                }
                GetMethod getMethod = new GetMethod(url);
                client.executeMethod(getMethod);
                ret = new String(getMethod.getResponseBody(), getMethod.getResponseCharSet());
                if(retry) {
                    LOGGER.info(id + "重新连接成功: " + url);
                }

            } catch(Exception ex) {
                LOGGER.error("id: " + id + ", url: " + url + ", error is: \n" + ex.getMessage());
                retry = true;
            }
        }
        objs[1] = Integer.valueOf(0);
        return ret;
    }
}