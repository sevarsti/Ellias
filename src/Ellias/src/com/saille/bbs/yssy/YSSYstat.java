package com.saille.bbs.yssy;

import com.GlobalConstant;
import com.saille.html.HTMLUtil;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-12-31
 * Time: 20:37:55
 * To change this template use File | Settings | File Templates.
 */
public class YSSYstat {
    public static void main(String args[]) {
        try {
            YSSYUtil util = new YSSYUtil();
            File f = new File(GlobalConstant.DISKPATH + "tmp.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String tmp;
            System.out.println("网龄 上站次数 发表文章 在线时间");
            while((tmp = br.readLine()) != null) {
                String cookie = util.login(tmp, (tmp.equalsIgnoreCase("Ellias") || tmp.equalsIgnoreCase("YTHTforever")) ? "zloves" : "pmgkglor");
                if(StringUtils.isEmpty(cookie)) {
                    System.out.println(tmp);
                    continue;
                }
                HTMLUtil htmlUtil = HTMLUtil.getInstance();
                String ret = htmlUtil.getWeb("http://bbs.sjtu.cn/bbsstat", null, cookie);
//            System.out.println(ret);
                ret = ret.substring(ret.indexOf("<tr><td>") + "<tr><td>".length());
                ret = ret.substring(ret.indexOf("<tr><td>") + "<tr><td>".length());
                ret = ret.substring(0, ret.indexOf("</table>"));
                String[] parts = ret.split("<tr><td>");
                String[][] parts2 = new String[4][];
                for(int i = 0; i < 4; i++) {
                    String[] atts = parts[i].split("<td>");
                    parts2[i] = new String[3];
                    parts2[i][0] = atts[1].substring(0, atts[1].length() - 1);
                    parts2[i][1] = atts[2];
                    parts2[i][2] = atts[3].substring(4);
                }
                System.out.print(tmp + "\t");
                for(int i = 0; i < parts2.length; i++) {
                    for(int j = 0; j < parts2[i].length; j++) {
                        System.out.print(parts2[i][j] + "\t");
                    }
                }
                System.out.println("");
            }
//            String timeRate =
//            <html>
//            <meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=gb2312">
//            <link rel=stylesheet type=text/css href='/file/bbs/bbs.css'>
//            <script type="text/javascript" src="/check.js"></script>
//            <script type="JavaScript">myself={  userid : "PQ",  nick   : "Power Quest",  ip     : "180.154.212.54",  level  : 71303199    ,  gender : "M",  unreadMail : 0 };</script><link rel=stylesheet type=text/css href='/file/bbs/blue.css'>
//            <center><font class="title">饮水思源 - 个人排名统计　　</font>[使用者: PQ]<hr>
//            <table width=320><tr><td>项目<td>数值<td>全站排名<td>相对比例
//            <tr><td>本站网龄<td>2873天<td>13033<td>TOP 23.45%<tr><td>上站次数<td>14359次<td>55<td>TOP  0.10%<tr><td>发表文章<td>8次<td>30834<td>TOP 55.49%<tr><td>在线时间<td>375676分<td>4076<td>TOP  7.34%</table><br>总用户数: 55569</html>

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
