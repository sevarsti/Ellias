package com.saille.bbs.yssy;

import java.io.InputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

public class BMLoginCheck {
    public static void main(String[] args) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
            HttpClient client = new HttpClient();
            GetMethod getMethod = new GetMethod("http://bbs.sjtu.edu.cn/bbsall");
            client.executeMethod(getMethod);
            String str = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
            str = str.substring(str.indexOf("<td>板主") + "<td>板主".length());
            String[] boards = str.split("<tr>");
            String boardUrl = "http://bbs.sjtu.edu.cn/bbsdoc,board,%s.html";
            String userUrl = "http://bbs.sjtu.edu.cn/bbsqry?userid=%s";
            long now = new Date().getTime();
            long day10 = now - 86400000l * 10L;
            Date limit = new Date(day10);
            List<String> xxx = new ArrayList<String>();
            for(int j = 0; j < boards.length; j++) {
                String board = boards[j];
                System.out.println(j + "/" + boards.length);
                if((board.indexOf("诚征板主中") != -1) || (board.indexOf("<td>") == -1)) {
                    continue;
                }
                board = board.substring(board.indexOf("bbsdoc,board,") + "bbsdoc,board,".length());
                String boardname = board.substring(0, board.indexOf(".html"));
                String url = String.format(boardUrl, new Object[]{boardname});
                getMethod = new GetMethod(url);
                client.executeMethod(getMethod);
                String content = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
                String bm = content.substring(content.indexOf("板主:") + "板主:".length());
                content = content.substring(content.indexOf("板主寄语") + "板主寄语".length());
                content = content.substring(content.indexOf("<td align='right'>") + "<td align='right'>".length());
                String district = content.substring(0, 1);
                bm = bm.substring(0, bm.indexOf("<td"));
                String[] bms = bm.split("</a>");
                for(String bmid : bms) {
                    if(StringUtils.isEmpty(bmid)) {
                        continue;
                    }
                    bmid = bmid.substring(bmid.indexOf("\">") + "\">".length());
                    if((bmid.equalsIgnoreCase("bbsdev")) || (bmid.equalsIgnoreCase("Arbitrator")) || (bmid.equalsIgnoreCase("FundForBBS")) || (bmid.equalsIgnoreCase("YSSYWiki")) || (bmid.equalsIgnoreCase("SYSOP"))) {
                        continue;
                    }
                    url = String.format(userUrl, new Object[]{bmid});
                    getMethod = new GetMethod(url);
                    client.executeMethod(getMethod);
                    content = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
                    if(content.indexOf("不存在") != -1) {
                        xxx.add(district + "  " + String.format("%-16s", new Object[]{boardname}) + "\t" + String.format("%-14s", new Object[]{bmid}) + "不存在");
                    } else {
                        content = content.substring(content.indexOf("上 次 在: [</font><font class='c32'>") + "上 次 在: [</font><font class='c32'>".length());
                        content = content.substring(0, content.indexOf("</font>") - 4);
                        Date logintime = sdf.parse(content);
                        if(logintime.before(limit)) {
                            xxx.add(district + "  " + String.format("%-16s", new Object[]{boardname}) + "\t" + String.format("%-14s", new Object[]{bmid}) + content + " " + (now - logintime.getTime()) / 86400000L);
                        }
                    }
                }
            }
            Collections.sort(xxx);
            System.out.println("区 板面             板主          上次登录时间           未上站天数");
            for(String s : xxx) {
                System.out.println(s);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getString(InputStream is, String charset) throws Exception {
        List list = new ArrayList();
        int i;
        while((i = is.read()) != -1) {
            list.add(Byte.valueOf((byte) i));
        }
        byte[] bb = new byte[list.size()];
        for(int ii = 0; ii < list.size(); ii++) {
            bb[ii] = ((Byte) list.get(ii)).byteValue();
        }
        String ret = new String(bb, charset);
        return ret;
    }
}