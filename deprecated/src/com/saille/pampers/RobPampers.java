package com.saille.pampers;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.htmlparser.Parser;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.tags.FormTag;
import org.htmlparser.tags.InputTag;
import org.htmlparser.util.NodeList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.io.*;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-6
 * Time: 18:02:06
 */
public class RobPampers extends Thread{
    public static Date newItemTime;
    private boolean loop = true;
    private Date lastOperateTime = null;
    private boolean isRunning = false;
    private String exitReason = "";
    private int count = 0;
    private int relogintimes = 0;
    private String currentStatus = "";
    private String score;
    private Date scoreTime;

    private String user;
    private String pwd;

    private String realname;
    private String email;
    private String mobile;
    private String province;
    private String city;
    private String address;
    private String zipcode;
    private String itemId;

    public RobPampers(String user, String pwd, String name, String email, String mobile, String province,
                      String city, String address, String zipcode, String itemId) {
        this.user = user;
        this.pwd = pwd;
        this.realname = name;
        this.email = email;
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.itemId = itemId;
    }


    public boolean getIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean running) {
        isRunning = running;
        if(isRunning) {
            this.exitReason = "";
        }
    }

    public Date getLastOperateTime() {
        return lastOperateTime;
    }

    public void setLastOperateTime(Date lastOperateTime) {
        this.lastOperateTime = lastOperateTime;
    }

    public String getExitReason() {
        return exitReason;
    }

    public void setExitReason(String exitReason) {
        this.exitReason = exitReason;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String name) {
        this.realname = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getRelogintimes() {
        return relogintimes;
    }

    public void setRelogintimes(int relogintimes) {
        this.relogintimes = relogintimes;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Date getScoreTime() {
        return scoreTime;
    }

    public void setScoreTime(Date scoreTime) {
        this.scoreTime = scoreTime;
    }

    public void run() {
        try {
            this.exitReason = "";
            HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
            client.getParams().setCookiePolicy("compatibility");
            client.getParams().setParameter("http.protocol.single-cookie-header", true);
            client.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
            client.getHttpConnectionManager().getParams().setSoTimeout(60000);

            GetMethod gm = null;
            PostMethod pm = null;
            //loop login
//            doLogin(client);

            boolean login = false;
//            this.isRunning = true;
            String verifyCode = "";
            boolean paramsGot = false;
            loop:
            while(loop) {
                try {
//                    System.out.println(count++);
                    if(!this.isRunning) {
                        this.exitReason = "手动停止";
                        Thread.sleep(10000);
                        continue;
//                        break;
                    }
                    LOG(count++);
                    lastOperateTime = new Date();
                    if(!login) {
                        this.currentStatus = "登录中";
                        doLogin(client);
                        login = true;
                    }

                    boolean doWait = (newItemTime == null || ((new Date().getTime() - newItemTime.getTime()) >= 1000 * 900)) &&
                            (verifyCode != null && verifyCode.length() == 4);
                    if(doWait) {
                        this.currentStatus = "等待上新";
                        String url = "https://epoint.pampers.com.cn/pages/rewards.aspx?r=" + Math.random();
                        gm = new GetMethod(url);
                        client.executeMethod(gm);
                        String score = this.getScore(new String(gm.getResponseBody(), gm.getResponseCharSet()));
                        if(score == null) {
                            login = false;
                        } else {
                            this.score = score;
                            this.scoreTime = new Date();
                        }
                        Thread.sleep(10000);
                        continue;
                    }

                    //load commit page
                    while(!paramsGot) {
                        if(!this.isRunning) {
                            this.exitReason = "手动停止";
                            Thread.sleep(10000);
                            continue loop;
//                            break;
                        }
                        this.currentStatus = "获取参数";
                        String url = "http://epoint.pampers.com.cn/pages/redeem.aspx?p1=" + this.itemId;
                        gm = new GetMethod(url);
                        client.executeMethod(gm);
                        String ret = new String(gm.getResponseBody(), gm.getResponseCharSet());
                        if(gm.getPath().equalsIgnoreCase("/pages/member_error.aspx")) {
                            String s = ret.substring(ret.indexOf("<span id=\"Lerror\">") + "<span id=\"Lerror\">".length());
                            s = s.substring(0, s.indexOf("</span>"));
                            LOG("失败：" + s);
                            this.currentStatus = "";
                            this.exitReason = s;
                            break loop;
                        }
                        String score = this.getScore(new String(gm.getResponseBody(), gm.getResponseCharSet()));
                        if(score == null) {
                            login = false;
                            continue loop;
                        } else {
                            this.score = score;
                            this.scoreTime = new Date();
                        }
                        pm = new PostMethod(url);
                        pm.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                        paramsGot = getParams(ret, gm.getResponseCharSet(), pm);
                    }

                    //process varify code
//                    String verifyCode = null;
                    while(verifyCode == null || verifyCode.length() != 4) {
                        try {
                            this.currentStatus = "获取验证码";
                            LOG("get code");
                            gm = new GetMethod("http://epoint.pampers.com.cn/include/getCheckCode.aspx?ticks=" + new Date().getTime());
                            client.executeMethod(gm);
//                            long size = gm.getResponseContentLength();
                            byte[] bb = gm.getResponseBody();
//                            LOG("code size: " + bb.length);
                            ByteArrayInputStream bis = new ByteArrayInputStream(bb);
                            BufferedImage image = ImageIO.read(bis);
                            verifyCode = getVerifyCode(image);
                            LOG("verify code: " + verifyCode);
                            image.flush();
                        } catch(IOException ex) {
                            LOG("io exception");
                            StringWriter sw = new StringWriter();
                            PrintWriter pw = new PrintWriter(sw);
                            ex.printStackTrace(pw);
                            LOG(sw.toString());
                        }
                    }

                    if(!this.isRunning) {
                        this.exitReason = "手动停止";
                        Thread.sleep(10000);
                        continue loop;
//                        break;
                    }
                    //commit
                    pm.removeParameter("ImgbtnRedeem");
                    pm.removeParameter("CB1");
                    pm.addParameter(new NameValuePair("CB1", "on"));
                    pm.removeParameter("uxTextBoxCode");
                    pm.addParameter(new NameValuePair("uxTextBoxCode", verifyCode));
                    this.currentStatus = "提交请求";
                    client.executeMethod(pm);
                    if(pm.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                        String url;
                        if(pm.getResponseHeader("location").getValue().startsWith("/")) {
                            url = "http://epoint.pampers.com.cn" + pm.getResponseHeader("location").getValue();
                        } else {
                            url = pm.getResponseHeader("location").getValue();
                        }
                        gm = new GetMethod(url);
                        client.executeMethod(gm);
                        String content = new String(gm.getResponseBody(), gm.getResponseCharSet());
                        String score = this.getScore(new String(gm.getResponseBody(), gm.getResponseCharSet()));
                        if(score == null) {
                            login = false;
                            continue loop;
                        } else {
                            this.score = score;
                            this.scoreTime = new Date();
                        }
                        if(gm.getURI().getName().indexOf("member_error.aspx") >= 0) {
                            String s = content.substring(content.indexOf("<span id=\"Lerror\">") + "<span id=\"Lerror\">".length());
                            s = s.substring(0, s.indexOf("</span>"));
                            LOG("失败原因：" + s);
                        } else {
                            LOG("理论上成功了");
                            LOG(content);
                        }
                    } else if(pm.getStatusCode() == HttpStatus.SC_OK) { //又回到本页，应该是验证码有误
                        verifyCode = "";
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.isRunning = false;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private String getScore(String content) {
        if(content == null) {
            return null;
        }
        if(content.indexOf("您目前可用积分") < 0) {
            return null;
        }
        content = content.substring(content.indexOf("您目前可用积分") + "您目前可用积分".length());
        content = content.substring(0, content.indexOf("</span>"));
        content = content.substring(content.indexOf(">") + 1);
        return content;
    }

    private void doLogin(HttpClient client) throws Exception{
        String url = "http://epoint.pampers.com.cn/system/Login2.aspx";
        boolean login = false;
        GetMethod gm = new GetMethod("https://epoint.pampers.com.cn/");
        int status = client.executeMethod(gm);
        List<String[]> params = getFormParams(gm.getResponseBodyAsString(), gm.getResponseCharSet(), url);
        while(!login) {
            url = "http://epoint.pampers.com.cn/system/Login2.aspx";
            PostMethod pm = new PostMethod(url);
            for(String[] p : params) {
                if(p[0].equals("email")) {
                    p[1] = this.user;
                } else if(p[0].equals("password")) {
                    p[1] = this.pwd;
                }
                if(p != null && p[0] != null && p[1] != null) {
                    pm.addParameter(new NameValuePair(p[0], p[1]));
                }
            }
            status = client.executeMethod(pm);
            if(pm.getStatusCode() == HttpStatus.SC_MOVED_TEMPORARILY) {
                url = "http://epoint.pampers.com.cn" + pm.getResponseHeader("location").getValue();
                gm = new GetMethod(url);
                client.executeMethod(gm);
                String content = gm.getResponseBodyAsString();
//                System.out.println(content);
//                System.out.println(url);
                if(content.indexOf("您目前可用积分") >= 0) {
                    login = true;
                    relogintimes++;
                }
            }
        }
    }

    private List<String[]> getFormParams(String content, String charset, String formName) throws Exception{
        Parser parser = Parser.createParser(content, charset);
        NodeList list = parser.extractAllNodesThatMatch(new TagNameFilter("FORM"));
        FormTag node = null;
        for(int i = 0; i < list.size(); i++) {
            if(list.elementAt(i) != null && list.elementAt(i) instanceof FormTag) {
                FormTag form = (FormTag) list.elementAt(i);
                if(form.getFormLocation().equals(formName)) {
                    node = form;
                    break;
                }
            }
        }
        if(node == null) {
            return new ArrayList<String[]>();
        }
        String s = node.getStringText();
        parser = Parser.createParser(s, charset);
        list = parser.extractAllNodesThatMatch(new TagNameFilter("INPUT"));
        List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < list.size(); i++) {
            if(list.elementAt(i) != null && list.elementAt(i) instanceof InputTag) {
                InputTag tag = (InputTag) list.elementAt(i);
                ret.add(new String[]{tag.getAttribute("name"), tag.getAttribute("value")});
            }
        }
        return ret;
    }

    private boolean getParams(String content, String charset, PostMethod pm) throws Exception{
        List<String[]> params = getFormParams(content, charset, "redeem.aspx?p1=" + this.itemId);
        if(params != null && params.size() > 0) {
            for(String[] p : params) {
                if(p == null || p[0] == null) {
                    continue;
                }
                if(p[0].equals("r1")) { //name
//                    new String(conf.getString("arabel@126.com.name").getBytes("utf-8"), "utf-8")
                    p[1] = this.realname;
                } else if(p[0].equals("r2")) { //email
                    p[1] = this.email;
                } else if(p[0].equals("r3")) { //mobile
                    p[1] = this.mobile;
                } else if(p[0].equals("r4")) { //province
                    p[1] = this.province;
                } else if(p[0].equals("r5")) { //city
                    p[1] = this.city;
                } else if(p[0].equals("r6")) { //address
                    p[1] = this.address;
                } else if(p[0].equals("r7")) { //zipcode
                    p[1] = this.zipcode;
                }
                if(p[1] == null) {
                    p[1] = "";
                }
                pm.addParameter(new NameValuePair(p[0], p[1]));
            }
            if(pm.getParameter("r4") == null) {
                pm.addParameter(new NameValuePair("r4", "26"));
            }
            if(pm.getParameter("r5") == null) {
                pm.addParameter(new NameValuePair("r5", "396"));
            }
            pm.addParameter(new NameValuePair("__EVENTTARGET", ""));
            pm.addParameter(new NameValuePair("__EVENTARGUMENT", ""));
            pm.addParameter(new NameValuePair("__LASTFOCUS", ""));
            pm.addParameter(new NameValuePair("ImgbtnRedeem.x", String.valueOf(Math.abs(new Random().nextInt() % 60))));
            pm.addParameter(new NameValuePair("ImgbtnRedeem.y", String.valueOf(Math.abs(new Random().nextInt() % 60))));
            return true;
        } else {
            return false;
        }
    }

    public static String getVerifyCode(BufferedImage image) throws Exception{
        String[][] codes = new String[26][61];
        for(int i = 0; i < 26; i++) {
            for(int j = 0; j < 61; j++) {
                int rgb = image.getRGB(j, i);
                if(rgb < 0) {
                    rgb = 256 * 256 * 256 + rgb;
                }
                int r, g, b;
                r = rgb / 65536;
                g = (rgb % 65536) / 256;
                b = rgb % 256;
                if(r > 150 && g > 150 && b < 50) {
                    codes[i][j] = "*";
                } else {
                    codes[i][j] = " ";
                }
            }
        }
        return compareVerifyCode(codes);
    }

    private static String compareVerifyCode(String[][] codes) throws Exception{
        int start = 5;
        String ret = "";
        int count = 0;
        for(int i = 0; i < 4; i++) {
            if(count > 60) {
                break;
            }
            if(check0(codes, start) > 0.9) {
                ret += "0";
            } else if(check2(codes, start) > 0.9) {
                ret += "2";
            } else if(check4(codes, start) > 0.9) {
                ret += "4";
            } else if(check6(codes, start) > 0.9) {
                ret += "6";
            } else if(check8(codes, start) > 0.9) {
                ret += "8";
            } else if(checkF(codes, start) > 0.9) {
                ret += "F";
            } else if(checkJ(codes, start) > 0.9) {
                ret += "J";
            } else if(checkN(codes, start) > 0.9) {
                ret += "N";
            } else if(checkX(codes, start) > 0.9) {
                ret += "X";
            } else if(checkT(codes, start) > 0.9) {
                ret += "T";
            } else if(checkV(codes, start) > 0.9) {
                ret += "V";
            } else if(checkD(codes, start) > 0.9) {
                ret += "D";
            } else if(checkP(codes, start) > 0.9) {
                ret += "P";
            } else if(checkH(codes, start) > 0.9) {
                ret += "H";
            } else if(checkL(codes, start) > 0.9) {
                ret += "L";
            } else if(checkR(codes, start) > 0.9) {
                ret += "R";
            } else if(checkZ(codes, start) > 0.9) {
                ret += "Z";
            } else if(checkB(codes, start) > 0.9) {
                ret += "B";
            } else {
                i = i - 1;
                count++;
            }
            start += 1;
        }
        return ret;
    }

    private static double checkBase(String[][] codes, int start, String str) {
        StringBuffer inBuf = new StringBuffer();
        for(int row = 6; row <= 19; row++) {
            for(int col = 0; col < str.length() / 14; col++) {
                if(start + col >= codes[0].length) {
                    inBuf.append(" ");
                } else {
                    inBuf.append(codes[row][start + col]);
                }
            }
        }
        String inStr = inBuf.toString();
        double correct = 0d;
        for(int i = 0; i < str.length(); i++) {
            if(str.charAt(i) == inStr.charAt(i)) {
                correct += 1d;
            }
        }
        return correct / str.length();
    }

    private static double check0(String[][] codes, int start) {
        String str = "    ****     ******   ***  **   **   *** ***   *** **    *** **    ******    ******    ** ***    **  **   ***  **  ***   ******     ****    ";
        return checkBase(codes, start, str);
    }

    private static double check2(String[][] codes, int start) {
        String str = "    ****    *******   ***  *** ***   ***       ***      ****      **       ***      ***      **       ****     **        ******** ********  ";
        return checkBase(codes, start, str);
    }

    private static double check4(String[][] codes, int start) {
        String str = "       **      ***     ****    *****    *****   **  *   **  **  ***  ** ***   ** ******** *********     **       **       **  ";
        return checkBase(codes, start, str);
    }

    private static double check6(String[][] codes, int start) {
        String str = "   ****    ******   **  **  **   ******      *******  ******** ***  *** ***   ** **    ** **    ** ***  ***  ******    ***    ";
        return checkBase(codes, start, str);
    }

    private static double check8(String[][] codes, int start) {
        String str = "    ****    *******   ***  ***  **   ***  **   **    *****    *****    ***  ***  **    ** **     ** ***   * *  **   **   ******     ****    ";
        return checkBase(codes, start, str);
    }

    private static double checkF(String[][] codes, int start) {
        String str = "   *********  **********  **********  ***         ***         *********   *********  *********   ***         **          **          **          **         ***         ";
        return checkBase(codes, start, str);
    }

    private static double checkJ(String[][] codes, int start) {
        String str = "        **        **       ***       ***       ***       **        **       ***       *** **    **  ***  ***  *******   *******    *****    ";
        return checkBase(codes, start, str);
    }

    private static double checkN(String[][] codes, int start) {
        String str = "   ***      *  ****     **  ****    ***  ****    ***  *****   **   ** **   **   ** ***  **  ***  **  **  ***  ** ***  **   *****   **    ****   **    ****  ***     ***  ***     ***  ";
        return checkBase(codes, start, str);
    }

    private static double checkX(String[][] codes, int start) {
        String str = "  ***      ****   ***    ****     **   ****      ***  ***       *******         *****          ****           ****          ******         ******        *** ***       ***   ***     ***    ***    ***      ***   ";
        return checkBase(codes, start, str);
    }

    private static double checkT(String[][] codes, int start) {
        String str = "******************************   ***       ***       **        **       ***       ***       **        **       ***       ***       * *      ";
        return checkBase(codes, start, str);
    }

    private static double checkV(String[][] codes, int start) {
        String str = "***       ** **      *** **      *** **      **  ***    ***  ***    **   ***   ***    **   **     **  ***     ** ***      ******      *****       *** *        ***      ";
        return checkBase(codes, start, str);
    }

    private static double checkD(String[][] codes, int start) {
        String str = "   *******     *********    **********   ***    ***   ***     ***  **       ** ***      *** ***      *** ***      *** **      ***  **     ****  **********  **********   ********     ";
        return checkBase(codes, start, str);
    }

    private static double checkP(String[][] codes, int start) {
        String str = "   ********   **********  **********  ***     **  ***     **  **     ***  *********  **********  *********   **          **          **         ***         ***         ";
        return checkBase(codes, start, str);
    }

    private static double checkH(String[][] codes, int start) {
        String str = "   **      ***  ***      **   ***     ***   ***     ***   ***     ***   **********    **********   ***********   ***     ***   ***     ***   **       *   ***      **   ***     ***   ***     ***   ";
        return checkBase(codes, start, str);
    }

    private static double checkL(String[][] codes, int start) {
        String str = "   **        **       ***       ***       **        **       ***       ***       **        **        **        *****************************";
        return checkBase(codes, start, str);
    }

    private static double checkR(String[][] codes, int start) {
        String str = "   *********    *********   ***********  ***      **  ***      **  **      ***  **********  **********   *********    **    ***    **     ***   **     ***  ***      *** ***      *** ";
        return checkBase(codes, start, str);
    }

    private static double checkZ(String[][] codes, int start) {
        String str = "  **********  *********   *********        ***         ***        ***        ***        ***        ***        ***        ***        **********  **********  **********  ";
        return checkBase(codes, start, str);
    }

    private static double checkB(String[][] codes, int start) {
        String str = "   ********    *********  **********  ***     **  ***     **  ********** **********  *********** ***     *** ***     *** **      *** ********************** *********   ";
        return checkBase(codes, start, str);
    }

    private static void LOG(int msg) {
        LOG(String.valueOf(msg));
    }

    public static void LOG(String msg) {
        if(msg == null || msg.length() == 0) {
            return;
        }
        System.out.println("loop: " + msg);
//        Logger LOGGER = Logger.getLogger(this.getClass());
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            File f = new File("D:\\pampersLog.log");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f, true);
            fos.write(sdf.format(new Date()).getBytes());
            fos.write(": ".getBytes());
            fos.write(msg.getBytes());
            fos.write("\r\n".getBytes());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
