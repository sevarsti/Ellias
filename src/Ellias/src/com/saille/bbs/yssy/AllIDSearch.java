package com.saille.bbs.yssy;

import com.GlobalConstant;
import com.saille.html.HTMLUtil;
import com.saille.util.FileUtils;

import java.io.File;
import java.io.PrintStream;

public class AllIDSearch {
    public static void main(String[] args) {
        try {
            YSSYUtil util = new YSSYUtil();
            String cookie = util.login("pq", "pmgkglory");
            if(cookie != null) {
                HTMLUtil util2 = HTMLUtil.getInstance();
                for(int i = 0; i < 55900; i += 20) {
                    System.out.println(i);
                    String ret = util2.getWeb("http://bbs.sjtu.cn/bbsalluser?start=" + i, null, cookie);
                    int idx = ret.indexOf("<tr><td>");
                    if(idx == -1) {
                        System.out.println(ret);
                    }
                    ret = ret.substring(idx);
                    ret = ret.substring(0, ret.indexOf("</table><hr>"));
                    ret.replaceAll("\n", "\r\n");

                    File f = new File(GlobalConstant.DISKPATH + "alluser\\" + "all" + ".txt");
                    FileUtils.WriteFile(f, ret, true);
                }

            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}