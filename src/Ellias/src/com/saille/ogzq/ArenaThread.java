package com.saille.ogzq;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import java.util.*;
import java.io.StringWriter;
import java.io.PrintWriter;

import com.saille.util.DateUtils;
import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-30
 * Time: 19:03:50
 * To change this template use File | Settings | File Templates.
 */
public class ArenaThread extends Thread implements InitializingBean {
    private final static Logger LOGGER = Logger.getLogger(ArenaThread.class);
    public static Map<String, String[]> JJCAgainst = new Hashtable<String, String[]>(); //每个号竞技场的对手
    public static Map<String, String> NICK = new Hashtable<String, String>();
    public int d = 0;
    private static Map<String, HttpClient> CLIENTS = new Hashtable<String, HttpClient>();
    private static Map<String, String> PWD = new Hashtable<String, String>();
    private static ArenaThread instance = null;

    public static void main(String[] args) {
        try {
//            doAdd("你好", "asdf");

            PropertiesConfiguration conf = new PropertiesConfiguration("D:\\apache-tomcat\\webapps\\ROOT\\ogzq\\arenaId.ini");
            conf.setEncoding("GB2312");
            String[] ss = conf.getStringArray("id");
            String sss = conf.getString(ss[ss.length - 1]);

//            HttpClient c = login("中博浩@hupu7", "5263648wuzhh");
//            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("load", "1"));
//            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//
//            HttpResponse response = c.execute(pm);
//            String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//            System.out.println(ret);
        } catch(Exception ex) {
            ex.printStackTrace();
        }

//        ArenaThread.getInstance();
    }
    private ArenaThread() {
    }

    public static synchronized ArenaThread getInstance() {
        if(instance == null) {
            instance = new ArenaThread();
            instance.setName("Thread-ArenaThread");
//            instance.start();
        }
        return instance;
    }

    public void run() {
        while(true) {
            try {
                Date date = new Date();
                int d = DateUtils.convert2Int(date);
                if(d != this.d) {
                    NICK.clear();
                    JJCAgainst.clear();
                    this.d = d;
                }
//                PropertiesConfiguration conf = new PropertiesConfiguration("D:\\work\\ellias\\web\\ROOT\\ogzq\\arenaId.ini");
                PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/arenaId.ini"));
                String[] ids = conf.getStringArray("id");
                for(String id : ids) {
                    try {
                        if(!NICK.containsKey(id)) {
                            String pwd = conf.getString(id);
                            PWD.put(id, pwd);
                            HttpClient client = login(id, pwd);
                            if(client == null) {
                                continue;
                            }
                            CLIENTS.put(id, client);

                            HttpPost pm = new HttpPost(OgzqURL.URL + "/Default.aspx");
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("type", "7"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            String rett = execute(id, pm);
                            String[] subs = rett.split("[|]");
                            NICK.put(id, subs[0]);
                        }
                        if(!JJCAgainst.containsKey(id)) {
                            //view arena
                            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("load", "1"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                            String ret = execute(id, pm);
                            if(ret.indexOf("inmatch") != -1) {
                                continue;
                            }
                            try {
                                int retValue = Integer.parseInt(ret);
                                if(retValue < 0) {
                                    continue;
                                }
                            } catch(NumberFormatException ex) {}
                            ret = ret.substring(0, ret.indexOf("&"));
                            String[] teams = ret.split("[*]");
                            JJCAgainst.put(id, teams);
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
                Thread.sleep(5000);
            } catch(Exception ex) {
                ex.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch(Exception exx) {}
            }
        }
    }

    public static String execute(String email, HttpPost pm) {
        int count = 0;
        while(count < 3) {
            try {
                HttpClient client = CLIENTS.get(email);
                if(client == null) {
                    return "";
                }
                String ret = "";
                synchronized(client) {
                    pm.getParams().setParameter("Connection", "keep-alive");
                    HttpResponse response = client.execute(pm);
                    ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//                    pm.abort();
                    pm.releaseConnection();
                    if(ret.indexOf("ASP.NET") != -1 || ret.indexOf("未将对象引用设置到对象的实例") != -1 ||
                            ret.indexOf("action=\"Arena.aspx\"") != -1) {
                        client = login(email, PWD.get(email));

                        CLIENTS.put(email, client);
                        pm = new HttpPost(OgzqURL.URL + "/Default.aspx");
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("type", "7"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        String rett = execute(email, pm);
                        String[] subs = rett.split("[|]");
                        NICK.put(email, subs[0]);
                    }
                }
                return ret;
            } catch(Exception ex) {
                System.out.println(email + "/" + pm.getURI().getPath() + " occurs error: " + ex.getMessage());
                count++;
                continue;
            }
        }
        return null;
    }

    public static List<String> GETIDS() {
        Set<String> ss = NICK.keySet();
        List<String> ret = new ArrayList<String>();
        for(String s : ss) {
            ret.add(s);
        }
        Collections.sort(ret);
        return ret;
    }

    public void afterPropertiesSet() throws Exception {
        ArenaThread.getInstance();
    }

    public void doAdd(String id, String pwd) {
        try {
            if(StringUtils.isEmpty(id)) {
                return;
            }
//            PropertiesConfiguration conf = new PropertiesConfiguration("D:\\work\\ellias\\web\\ROOT\\ogzq\\arenaId.ini");
            PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/arenaId.ini"));
            conf.setEncoding("GB2312");
            String[] ids = conf.getStringArray("id");
            boolean found = false;
            for(String s : ids) {
                if(s.equals(id)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                String[] s = new String[ids.length + 1];
                for(int i = 0; i < ids.length; i++) {
                    s[i] = ids[i];
                }
                s[ids.length] = id;
                conf.setProperty("id", s);
                conf.setProperty(id, pwd);
                conf.save();
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
//            LOGGER.error(sw.toString());
        }
    }

    public static HttpClient login(String id, String pwd) {
//        String server = id.substring(id.indexOf("@") + 1);
        LOGGER.info("登录竞技场：" + id);
        return LoginUtils.Login(id, pwd);
    }
}
