package com.saille.bbs.yssy.service;

import com.saille.bbs.yssy.YSSYUtil;
import com.saille.html.HTMLUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

public class YSSYDetectService {
    private static File f;
    private static OutputStream os;
    private static BufferedWriter writer;

    public static void main(String[] args) {
        init();
        List ids = new ArrayList();
        ids = read();
        System.out.println("size: " + ids.size());
//        String cookie = new YSSYUtil().login("Donadoni", "pmgkglor");
        HttpClient client = new HttpClient();
        GetMethod gm;
        for(int i = 0; i < ids.size(); i++) {
            boolean error = true;
            while(error) {
                String id = (String) ids.get(i);
                System.out.println(i + ": " + id);
                try {
                    StringBuffer sb = new StringBuffer();
//                    String is = HTMLUtil.getInstance().getWeb("http://202.120.58.161/bbsqry?userid=" + id, null, cookie);
                    gm = new GetMethod("http://bbs.sjtu.cn/bbsqry?userid=" + id);
                    client.executeMethod(gm);
                    String is = gm.getResponseBodyAsString();
                    if(is.indexOf("匆匆过客不能查询用户，请先登录") != -1) {
                        error = true;
                        continue;
                    }

                    String tmp = null;

                    tmp = sb.toString();
                    tmp = getInfo(id, is);
                    log(tmp);
                    error = false;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        end();
    }

    private static void init() {
        try {
            f = new File("D:\\vbs\\target1new.txt");
            os = new FileOutputStream(f);
            writer = new BufferedWriter(new OutputStreamWriter(os));
            writer.write("id\tlogin time\tlife\tip\tpost\tlogintimes\tage\r\n");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private static void end() {
        try {
            writer.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<String> read() {
        List list = new ArrayList();
        String tmp = null;
        try {
            File f = new File("D:\\vbs\\target_new.txt");
            InputStream is = new FileInputStream(f);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            r.readLine();
            while((tmp = r.readLine()) != null) {
                list.add(tmp.split("\t")[0]);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private static String getInfo(String id, String in) {
        StringBuffer sb = new StringBuffer();
        sb.append(id).append("\t");

        int place = in.indexOf("上 次 在: [</font><font class='c32'>");
        String strValue = in.substring(place + 33, place + 33 + 24);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("生命力:[</font><font class='c32'>");
        strValue = in.substring(place + 30, place + 30 + 3);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }

        sb.append(strValue).append("\t");

        place = in.indexOf("从 [</font><font class='c32'>");
        strValue = in.substring(place + 28, place + 28 + 15);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("文章:[</font><font class='c32'>");
        strValue = in.substring(place + 29, place + 29 + 5);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("共上站 </font><font class='c32'>");
        strValue = in.substring(place + 29, place + 29 + 6);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("网龄[</font><font class='c32'>");
        strValue = in.substring(place + "网龄[</font><font class='c32'>".length(), place + "网龄[</font><font class='c32'>".length() + 5);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        return sb.toString();
    }

    private static void log(String str) {
        try {
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}