package com.saille.qq;

import com.GlobalConstant;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang.StringUtils;

import java.net.URLConnection;
import java.net.URL;
import java.io.InputStream;
import java.io.FileOutputStream;

/**
 * Description : 组合调整批量导入的Action
 * User: yang yebo
 * Date: 2009-4-30
 * Time: 14:38:53
 * Version : 1.0.0
 */
public class SendQQMsg {
    private static String sid;
    private static String qq = "2777976363";
    private static String pwd = "feifei";

    public static void main(String[] args) {
        sendMsg("tttest");
    }
    
    public static void sendMsg(String msg) {
        try {
            if(StringUtils.isNotEmpty(sid)) {
                HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
                client.getParams().setParameter("http.protocol.single-cookie-header", true);
                client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
                client.getHttpConnectionManager().getParams().setSoTimeout(5000);
                client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                PostMethod pm = new PostMethod("http://q16.3g.qq.com/g/s?sid=" + sid);
                pm.addParameter("msg", msg);
                pm.addParameter("u", "173606100");
                pm.addParameter("saveURL", "0");
                pm.addParameter("do", "send");
                pm.addParameter("on", "1");
                pm.addParameter("aid", "发送");
                client.executeMethod(pm);
                System.out.println(pm.getResponseBodyAsString());

                if(pm.getResponseBodyAsString().indexOf("nLogin") >= 0) {
                    login(qq, pwd);

                    client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
                    client.getParams().setParameter("http.protocol.single-cookie-header", true);
                    client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
                    client.getHttpConnectionManager().getParams().setSoTimeout(5000);
                    client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                    pm = new PostMethod("http://q16.3g.qq.com/g/s?sid=" + sid);
                    pm.addParameter("msg", msg);
                    pm.addParameter("u", "173606100");
                    pm.addParameter("saveURL", "0");
                    pm.addParameter("do", "send");
                    pm.addParameter("on", "1");
                    pm.addParameter("aid", "发送");
                    client.executeMethod(pm);
                    System.out.println(pm.getResponseBodyAsString());
                }
            } else {
                login(qq, pwd);

                HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
                client.getParams().setParameter("http.protocol.single-cookie-header", true);
                client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
                client.getHttpConnectionManager().getParams().setSoTimeout(5000);
                client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
                PostMethod pm = new PostMethod("http://q16.3g.qq.com/g/s?sid=" + sid);
                pm.addParameter("msg", msg);
                pm.addParameter("u", "173606100");
                pm.addParameter("saveURL", "0");
                pm.addParameter("do", "send");
                pm.addParameter("on", "1");
                pm.addParameter("aid", "发送");
                client.executeMethod(pm);
                System.out.println(pm.getResponseBodyAsString());
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void login(String qq, String pwd) {
        try {
            HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
            client.getParams().setParameter("http.protocol.single-cookie-header", true);
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            client.getHttpConnectionManager().getParams().setSoTimeout(5000);
            client.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");

            GetMethod gm = new GetMethod("http://pt.3g.qq.com");
            client.executeMethod(gm);
            String src = gm.getResponseBodyAsString();
            sid = src.substring(src.indexOf("sid\" value=\"") + "sid\" value=\"".length());
            sid = sid.substring(0, sid.indexOf("\""));

            String vdata = src.substring(src.indexOf("vdata=") + "vdata=".length());
            vdata = vdata.substring(0, vdata.indexOf("\""));

            PostMethod pm = new PostMethod("http://pt.3g.qq.com/handleLogin?vdata=" + vdata);
            pm.addParameter("bid", "0");
            pm.addParameter("go_url", "");
            pm.addParameter("hiddenPwd", "false");
            pm.addParameter("qq", qq);
            pm.addParameter("pwd", pwd);
            pm.addParameter("loginType", "1");
            pm.addParameter("sid", sid);

            client.executeMethod(pm);
            System.out.println(pm.getResponseBodyAsString());
            sid = pm.getResponseBodyAsString();
            if(sid.indexOf("请输入验证码进行验证") >= 0) { //需要验证码:(
                String imagepath = sid.substring(sid.indexOf("请输入验证码进行验证"));
                imagepath = imagepath.substring(imagepath.indexOf("<img src=\"") + "<img src=\"".length());
                imagepath = imagepath.substring(0, imagepath.indexOf("\"") + "\"".length());
                URL url = new URL(imagepath);
                URLConnection conn = url.openConnection();
                InputStream is = conn.getInputStream();
                FileOutputStream fos = new FileOutputStream(GlobalConstant.DISKPATH + "verifycode.gif");
                int read;
                while((read = is.read()) >= 0) {
                    fos.write(read);
                }
                is.close();
                fos.close();
                
/*
                系统检测到您的操作异常，为保证您的号码安全，请输入验证码进行验证，防止他人盗取密码。<br/>
                <img src="http://nvcsz.gtimg.com/2777976363/738363146470467358.gif?r=49302" alt="验证码"/><br/>
                <select name="imgType" multiple="false" value="gif">
                <option value="gif">gif</option>
                <option value="png">png</option>
                <option value="jpg">jpg</option>
                </select>
                <anchor>看不清，换一张
                <go href="http://pt.3g.qq.com/s?aid=safeLoginImageVerify&amp;sid=AQaY2IZV1bRrDEUrrS_DnnEO&amp;vdata=8D3BE00E97FCF068660A93488A18515E" method="post">

                <postfield  name="bid_code" value="qqchatLogin"/>

                <postfield name="qq" value="2777976363"/>
                <postfield name="u_token" value="2777976363"/>

                <postfield name="hexpwd" value="666569666569"/>
                <postfield name="sidtype" value="1" />
                <postfield name="hexp" value="true"/>
                <postfield name="auto" value="0"/>
                <postfield name="loginTitle" value="手机腾讯网"/>
                <postfield name="q_from" value=""/>
                <postfield name="modifySKey" value="0"/>
                <postfield name="q_status" value="10"/>
                <postfield name="r" value="49302"/>
                <postfield name="loginType" value="1"/>
                <postfield name="imgType" value="$imgType"/>
                <postfield name="extend" value="http://nvcsz.gtimg.com/2777976363/738363146470467358"/>
                <postfield name="r_sid" value="W2pL65Oap0dHL7YTTSNC29MZRPWOnuEFBenRPWHaEuplU77ogmaJeI4gs-TxNI-_kPC_z1-vB4ghq-AgkWdqBEFPZc0I7S2r-BzOofuGkIVuU.BE"/>
                <postfield name="bid" value="0"/>
                <postfield name="login_url" value="http://pt.3g.qq.com/s?aid=nLogin"/>

                <postfield name="rip" value="61.173.115.134"/>

                </go>
                </anchor><br/>
*/
            }
            sid = sid.substring(sid.indexOf("sid=") + "sid=".length());
            sid = sid.substring(0, sid.indexOf("&"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
