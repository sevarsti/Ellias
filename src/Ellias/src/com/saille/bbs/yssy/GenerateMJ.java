package com.saille.bbs.yssy;

import com.GlobalConstant;
import com.saille.html.HTMLUtil;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2012-11-30
 * Time: 0:13:12
 * To change this template use File | Settings | File Templates.
 */
public class GenerateMJ {
    public static void main(String[] args) {
        try {
            File f = new File(GlobalConstant.DISKPATH + "vbs\\id2.txt");
            InputStream is = new FileInputStream(f);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));

            File of = new File(GlobalConstant.DISKPATH + "vbs\\id21.txt");
            FileOutputStream os = new FileOutputStream(of);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            String id;
            YSSYUtil util = new YSSYUtil();
            while((id=r.readLine()) != null) {
                String cookie = util.login(id, "pmgkglor");
                String d = viewInfo(id, cookie);
                d = convertDate(d);
                writer.write(id + "\t" + d + "\r\n");
                writer.flush();
            }
            writer.close();
            os.close();
            r.close();
            is.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String convertDate(String in) {
        if(in.equals("--")) {
            return in;
        }
        //Tue Nov 14 03:31:05 2006
        //012345678901234567890123
        String ret = in.substring(20,24) + "年";
        String week = in.substring(0, 3);
        String month = in.substring(4, 7);
        if(month.equals("Jan")) {
            ret += "01月";
        } else if(month.equals("Feb")) {
            ret += "02月";
        } else if(month.equals("Mar")) {
            ret += "03月";
        } else if(month.equals("Apr")) {
            ret += "04月";
        } else if(month.equals("May")) {
            ret += "05月";
        } else if(month.equals("Jun")) {
            ret += "06月";
        } else if(month.equals("Jul")) {
            ret += "07月";
        } else if(month.equals("Aug")) {
            ret += "08月";
        } else if(month.equals("Sep")) {
            ret += "09月";
        } else if(month.equals("Oct")) {
            ret += "10月";
        } else if(month.equals("Nov")) {
            ret += "11月";
        } else if(month.equals("Dec")) {
            ret += "12月";
        }
        ret += in.substring(8,10) + "日" + in.substring(11, 19) + " ";
        if(week.equals("Sun")) {
            ret += "星期天";
        } else if(week.equals("Mon")) {
            ret += "星期一";
        } else if(week.equals("Tue")) {
            ret += "星期二";
        } else if(week.equals("Wed")) {
            ret += "星期三";
        } else if(week.equals("Thu")) {
            ret += "星期四";
        } else if(week.equals("Fri")) {
            ret += "星期五";
        } else if(week.equals("Sat")) {
            ret += "星期六";
        }
        return ret;
    }

    public static String viewInfo(String id, String cookie) {
        HTMLUtil util = HTMLUtil.getInstance();
        String ret = util.getWeb("http://bbs.sjtu.edu.cn/bbsinfo", null, cookie);
        if(ret.indexOf("帐号建立： <td>") == -1) {
            ret = "--";
        } else {
            ret = ret.substring(ret.indexOf("帐号建立： <td>") + "帐号建立： <td>".length());
            ret = ret.substring(0, ret.indexOf("\n"));
        }
        return ret;
    }
}
