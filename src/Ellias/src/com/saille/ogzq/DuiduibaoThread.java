package com.saille.ogzq;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-8-20
 * Time: 14:58:29
 * To change this template use File | Settings | File Templates.
 */
public class DuiduibaoThread extends Thread{
    private static DuiduibaoThread instance = null;
    private boolean cont = true;

    private DuiduibaoThread() {
    }

    public static DuiduibaoThread getInstance() {
        if(instance == null) {
            instance = new DuiduibaoThread();
        }
        return instance;
    }

    public void run() {
        PropertiesConfiguration conf = null;
        try {
            conf = new PropertiesConfiguration("D:\\work\\Ellias\\web\\ROOT\\ogzq\\ids.ini");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        Logger LOGGER = Logger.getLogger(this.getClass());
        while(true) {
            List<String> ids = IDUtils.GETIDS();
            LOGGER.info("循环连连看，一共有" + ids.size() + " 个号");
            for(String id : ids) {
                String pwd = "lspmgk";
                String p = conf.getString(id);
                if(StringUtils.isNotEmpty(p)) {
                    pwd = p;
                }
//                if(pwd.equals("www123") || pwd.equals("880612")) {
//                    continue;
//                }
//                if(id.indexOf("sevarsti") >= 0) {
//                    LOGGER.info("跳过大号");
//                    continue;
//                }
                try {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                    List<NameValuePair> pp = new ArrayList<NameValuePair>();
                    pp.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                    String ret = IDUtils.execute(id, pm);
                    String[] params = ret.split("\\^");

                    long now = System.currentTimeMillis();
                    if(Integer.parseInt(params[5]) <= 0) { //已经结束
                        int cd = Integer.parseInt(params[6]);
                        if(cd <= 0) {
                            pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                            pp = new ArrayList<NameValuePair>();
                            pp.add(new BasicNameValuePair("type", "3"));
                            pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                            System.out.println("reset: " + IDUtils.execute(id, pm));
                            cont = true;
                            pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                            pp = new ArrayList<NameValuePair>();
                            pp.add(new BasicNameValuePair("type", "1"));
                            pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                            ret = IDUtils.execute(id, pm);
                            now = System.currentTimeMillis();
                        } else {
                            continue;
                        }

                        ret = ret.substring(0, ret.indexOf("^"));
                        System.out.println(ret);
                        String[] lines = ret.split("[*]");
                        List<String> items = new ArrayList<String>();
                        for(int i = 0; i < 8; i++) {
                            String l = lines[i];
                            String[] ii = l.split("[|]");
                            for(String s : ii) {
                                items.add(s);
                            }
                        }
                        System.out.println("size: " + items.size() + "<br/>");
                        for(int i = 0; i < items.size(); i++) {
                            System.out.println(i +":" + items.get(i)+ "<br/>");
                        }
                        int count = 0;
                        for(int i = 0; i < 64; i++) {
                            if(items.get(i).equals("0")) {
                                continue;
                            }
                            for(int j = i + 1; j < 64; j++) {
                                if(items.get(j).equals("0")) {
                                    continue;
                                }
                                if(items.get(i).equals(items.get(j))) {
                                    int[] place1 = convert(i);
                                    int[] place2 = convert(j);
                                    pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                                    pp = new ArrayList<NameValuePair>();
                                    pp.add(new BasicNameValuePair("type", "2"));
                                    pp.add(new BasicNameValuePair("i1", "" +place1[0]));
                                    pp.add(new BasicNameValuePair("j1", "" + place1[1]));
                                    pp.add(new BasicNameValuePair("i2", ""+place2[0]));
                                    pp.add(new BasicNameValuePair("j2", ""+place2[1]));
                                    pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                                    if(count == 31) {
                                        long cur = System.currentTimeMillis();
                                        if(pwd.equals("yuliang83012") || pwd.equals("meiweilin") || pwd.equals("14789632")) {
                                            Thread.sleep(16950 - (cur - now));
                                        } else {
                                            Thread.sleep(16050 - (cur - now));
                                        }
                                    }
                                    System.out.println(id + ": i=" + i + ", " +IDUtils.execute(id, pm));
//                                    if(id.indexOf("sevarsti") >= 0) {
//                                        Thread.sleep(400);
//                                    } else {
//                                        Thread.sleep(600);
//                                    }
                                    System.out.println(count + "---" + place1[0] + "/"+place1[1] + "--" + place2[0] + "/" + place2[1] + "--------" + items.get(i) + "<br/>");
                                    count++;
                                    items.set(i, "0");
                                    items.set(j, "0");
                                }
                            }
                        }
                        System.out.println(id + " cost: " + (System.currentTimeMillis() - now));
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            try {
                Thread.sleep(1000 * 60);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private int[] convert(int idx) {
        int i = idx / 8;
        int j = idx % 8;
        return new int[]{i, j};
    }
}
