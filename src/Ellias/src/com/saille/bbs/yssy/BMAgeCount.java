package com.saille.bbs.yssy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.io.InputStream;

import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-3-3
 * Time: 13:43:28
 * To change this template use File | Settings | File Templates.
 */
public class BMAgeCount {
    public static void main(String[] args) {
        try {
            HttpClient client = new HttpClient();
            GetMethod getMethod = new GetMethod("http://bbs.sjtu.edu.cn/bbsall");
            client.executeMethod(getMethod);
            String str = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
            str = str.substring(str.indexOf("<td>板主") + "<td>板主".length());
            String[] boards = str.split("<tr>");
            String boardUrl = "http://bbs.sjtu.edu.cn/bbsdoc,board,%s.html";
            String userUrl = "http://bbs.sjtu.edu.cn/bbsqry?userid=%s";
            long now = new Date().getTime();
            List<String[]> xxx = new ArrayList<String[]>();
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
                bm = bm.substring(0, bm.indexOf("<td"));
                String[] bms = bm.split("</a>");
                String queryUrl = "http://bbs.sjtu.edu.cn/bbsbfind?board=announce&title=%s&title2=%s&dt=9999&type=1";
                for(String bmid : bms) {
                    if(StringUtils.isEmpty(bmid)) {
                        continue;
                    }
                    bmid = bmid.substring(bmid.indexOf("\">") + "\">".length());
                    url = String.format(queryUrl, bmid, boardname);
                    getMethod = new GetMethod(url);
                    client.executeMethod(getMethod);
                    content = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
                    content = content.substring(content.indexOf("<td>编号<td>标记<td>作者<td>日期<td>标题") + "<td>编号<td>标记<td>作者<td>日期<td>标题".length());
                    content = content.substring(0, content.indexOf("</table>"));
                    String[] titles = content.split("<tr><td>");
                    boolean found = false;
                    for(int i = titles.length - 1; i >= 0; i--) {
                        String title = titles[i];
                        if(title.indexOf("任命 " + bmid + " 为 " + boardname + " 讨论区") >= 0) {
                            String renmingURL = title.substring(title.indexOf("a href=bbscon") + "a href=".length());
                            renmingURL = renmingURL.substring(0, renmingURL.indexOf(">"));
                            getMethod = new GetMethod("http://bbs.sjtu.edu.cn/" + renmingURL);
                            client.executeMethod(getMethod);
                            content = getString(getMethod.getResponseBodyAsStream(), getMethod.getResponseCharSet());
                            content = content.substring(content.indexOf("发信站: 饮水思源") + "发信站: 饮水思源".length());
                            content = content.substring(content.indexOf("<font class='c37'>(") + "<font class='c37'>(".length());
                            content = content.substring(0, content.indexOf(")"));
                            xxx.add(new String[]{boardname, bmid, content});
                            found = true;
                            break;
                        }
                    }
                    if(!found) {
                        xxx.add(new String[]{boardname, bmid, ""});
                    }
                }
            }
            xxx = sort(xxx, 0, xxx.size());
            for(String[] xx : xxx) {
                for(String x : xx) {
                    System.out.print(x + "\t");
                }
                System.out.println();
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

    public static List<String[]> sort(List<String[]> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(list.get(i)[2].compareTo(list.get(pos)[2]) < 0) {
                needSwap = true;
            }

            if(needSwap) {
                String[] tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sort(list, start, pos);
        sort(list, pos + 1, end);
        return list;
    }
}
