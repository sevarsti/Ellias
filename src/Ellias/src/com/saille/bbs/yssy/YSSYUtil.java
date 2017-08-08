package com.saille.bbs.yssy;

import com.saille.html.HTMLUtil;
import com.saille.util.CommonUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class YSSYUtil {
    private final Logger LOGGER = Logger.getLogger(getClass());
    public static final String BBSSJTUCN = "bbs.sjtu.cn";
    public static final String BBSSJTUEDUCN = "bbs.sjtu.edu.cn";

    public String login(String id, String pwd) {
        HTMLUtil util = HTMLUtil.getInstance();
        String cookie = util.logInYSSY(id, pwd);
        return cookie;
    }

    public String viewSmd(String id, String cookie) {
        HTMLUtil util = HTMLUtil.getInstance();
        String ret = util.getWeb("http://bbs.sjtu.edu.cn/bbsplan", null, cookie);
        this.LOGGER.info(ret);
        int place = ret.indexOf("<textarea name=text rows=20 cols=80 wrap=physicle>");
        if(place == -1) {
            return null;
        }
        ret = ret.substring(place + "<textarea name=text rows=20 cols=80 wrap=physicle>".length());
        place = ret.indexOf("</textarea>");
        if(place == -1) {
            return null;
        }
        ret = ret.substring(0, place);
        return ret;
    }

    public boolean changeSmd(String id, String cookie, String smd) {
        try {
            HTMLUtil util = HTMLUtil.getInstance();
            List<Object[]> params = new ArrayList<Object[]>();
            params.add(new String[]{"type", "update"});
            params.add(new String[]{"text", smd});
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public Id detectId(String id) {
        try {
            URL url = new URL("http://bbs.sjtu.edu.cn/bbsqry?userid=" + id);
            StringBuffer sb = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String tmp = null;
            while((tmp = in.readLine()) != null) {
                sb.append(tmp);
            }
            tmp = sb.toString();
            Id newId = getInfo(tmp);
            return newId;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Id getInfo(String in) {
        Id id = new Id();

        int place = in.indexOf("上次在 [<font class='c32'>");
        String strValue = in.substring(place + 23, place + 23 + 26);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        id.setLastLoginTime(strValue);

        place = in.indexOf("生命力：[<font class='c32'>");
        strValue = in.substring(place + 23, place + 23 + 3);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }

        id.setLife(Integer.parseInt(strValue));

        place = in.indexOf("从 [<font class='c32'>");
        strValue = in.substring(place + 21, place + 21 + 15);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        id.setIp(strValue);

        place = in.indexOf("发表文章 <font class='c32'>");
        strValue = in.substring(place + 23, place + 23 + 5);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        id.setPost(Integer.parseInt(strValue));

        place = in.indexOf("共上站 <font class='c32'>");
        strValue = in.substring(place + 22, place + 22 + 6);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        id.setLoginTimes(Integer.parseInt(strValue));

        return id;
    }

    public static String getDistrict(String board) {
        if(StringUtils.isEmpty(board)) {
            return " ";
        }
        HttpClient client = new HttpClient();
        String url = "http://bbs.sjtu.cn/bbsdoc?board=" + board;
        GetMethod gm = new GetMethod(url);
        try {
            client.executeMethod(gm);
            String content = CommonUtils.getString(gm.getResponseBodyAsStream(), gm.getResponseCharSet());
            content = content.substring(content.indexOf("<td align='right'>") + "<td align='right'>".length());
            return content.substring(0, 1);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return " ";
    }
}