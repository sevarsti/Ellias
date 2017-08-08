package com.saille.sina;

import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.cookie.Cookie;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jettison.json.JSONObject;

import java.util.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.text.DecimalFormat;

import com.saille.util.UtilFunctions;
import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-24
 * Time: 16:09:44
 * To change this template use File | Settings | File Templates.
 */
public class LoginUtils {
    private static final int n = 0;
    private static final int o = 8;

    public static void main(String[] args) {
        login("ssevarst@wanwan.sina.com", "ssesse");
    }
    public static DefaultHttpClient login(String id, String pwd) {
        try {
            String userkey = UtilFunctions.getJsByFile("D:\\js\\sina_ssologin.js", "encode", new Object[]{id});

            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet gm = new HttpGet("http://login.sina.com.cn/sso/prelogin.php?entry=wanwan&callback=sinaSSOController.preloginCallBack&su=" + URLEncoder.encode(userkey) +
                "&rsakt=mod");
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            gm.releaseConnection();
            msg = msg.substring(msg.indexOf("(") + 1);
            msg = msg.substring(0, msg.lastIndexOf(")"));
            JSONObject json = new JSONObject(msg);
            String nonce = json.getString("nonce");
            String rsakv = json.getString("rsakv");
            String pubkey = json.getString("pubkey");
            String servertime = json.getString("servertime");
//            String servertime = "1466758656";
//            String pubkey = "EB2A38568661887FA180BDDB5CABD5F21C7BFD59C090CB2D245A87AC253062882729293E5506350508E7F9AA3BB77F4333231490F915F6D63C55FE2F08A49B353F444AD3993CACC02DB784ABBB8E42A9B1BBFFFB38BE18D78E87A0E41B9B8F73A928EE0CCEE1F6739884B9777E4FE9E88A1BBE495927AC4A799B3181D6442443";
//            String nonce = "29UK7L";
//            pwd = UtilFunctions.getJsByFile("D:\\js\\rsa_all.js", "cpRSAEncrypt", new Object[]{servertime + "\t"+nonce + "\n"+pwd, pubkey});
            pwd = UtilFunctions.getJsByFile("D:\\js\\sina_ssoconfig.js", "make", new Object[]{servertime ,nonce ,pwd, pubkey});
            System.out.println(pwd);
            HttpPost pm = new HttpPost("http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.18)&_=" + new Date().getTime());
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("entry", "wanwan"));
            params.add(new BasicNameValuePair("gateway", "1"));
            params.add(new BasicNameValuePair("from", ""));
            params.add(new BasicNameValuePair("savestate", "0.0069"));
            params.add(new BasicNameValuePair("useticket", "0"));
            params.add(new BasicNameValuePair("pagerefer", "https://www.baidu.com/link/url/7V6cZOm38Y3ZYw_dYZ6gWHfQj_Dv1yFr9hHjxjkCNwE7uaFrsV_EjDMmt_HWNXDL&wd=&eqid=dfc126610001ff8300000004576ce69f"));
            params.add(new BasicNameValuePair("su", userkey));
            params.add(new BasicNameValuePair("service", "wanwan"));
            params.add(new BasicNameValuePair("servertime", servertime));
            params.add(new BasicNameValuePair("nonce", nonce));
            params.add(new BasicNameValuePair("pwencode", "rsa2"));
            params.add(new BasicNameValuePair("rsakv", rsakv));
            params.add(new BasicNameValuePair("sp", pwd));
            params.add(new BasicNameValuePair("sr", "1280*1024"));
            params.add(new BasicNameValuePair("encoding", "UTF-8"));
            params.add(new BasicNameValuePair("cdult", "3"));
            params.add(new BasicNameValuePair("domain", "sina.com.cn"));
            params.add(new BasicNameValuePair("prelt", "178"));
            params.add(new BasicNameValuePair("returntype", "TEXT"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            pm.releaseConnection();
            String ticket = msg.substring(msg.indexOf("ticket=") + "ticket=".length());
            ticket = ticket.substring(0, ticket.indexOf("&"));
            ticket = URLDecoder.decode(ticket);

            for(int i = 1; i < 146; i++) {
                System.out.println("i="+i);
                String url = (i == 1 ? "" : (new DecimalFormat("00").format(i-1)));
                gm = new HttpGet("http://game.sports.sina.com.cn/game_login/index.php?" +
                        "srv=webgame086" + url +
                        "&gourl=http%3A%2F%2Fs145.kof.sports.sina.com%2FSina%2Fsinaunion%2FsinaUnion.aspx%3Fticket%3D" + URLEncoder.encode(ticket) +
                        "&ticket=" + ticket);
                resp = client.execute(gm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "GBK");
                resp.close();
                gm.releaseConnection();

                gm = new HttpGet("http://game.sports.sina.com.cn/game_login/game.php?srv=webgame086"+url);
                resp = client.execute(gm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "GBK");
                resp.close();
                gm.releaseConnection();

                msg = msg.substring(msg.indexOf("window.location.href='") + "window.location.href='".length());
                msg = msg.substring(0, msg.indexOf("'"));

//                System.out.println("url="+msg);
                gm = new HttpGet(msg);
                resp = client.execute(gm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "GBK");
                resp.close();
                gm.releaseConnection();
                msg = msg.substring(msg.indexOf("location.replace(\"") + "location.replace(\"".length());
                msg = msg.substring(0, msg.indexOf("\""));

//                System.out.println("url="+msg);
                gm = new HttpGet(msg);
                resp = client.execute(gm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                resp.close();
                gm.releaseConnection();
//            System.out.println(msg);

                msg = msg.substring(msg.indexOf("location.replace('") + "location.replace('".length());
                msg = msg.substring(0, msg.indexOf("'"));
//                System.out.println("url="+msg);
                gm = new HttpGet(msg);
                resp = client.execute(gm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                resp.close();
                gm.releaseConnection();

                pm = new HttpPost("http://s"+i+".kof.sports.sina.com/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = client.execute(pm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

                if(msg.equals("-2")) {
                    System.out.println(i + "需要注册新号");
                } else {
                    continue;
                }

                pm = new HttpPost("http://s" + i + ".kof.sports.sina.com/CreateClubInfo.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("STeam1", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = client.execute(pm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

                pm = new HttpPost("http://s" + i + ".kof.sports.sina.com/CreateClubInfo2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("CreateLoad1", ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = client.execute(pm);
                String s = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
                pm = new HttpPost("http://s" + i + ".kof.sports.sina.com/CreateClubInfo2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("CreateClub1", "sina缥缈" + i + "*2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = (CloseableHttpResponse)client.execute(pm);
                String ok = (CommonUtils.getString(resp.getEntity().getContent(), "utf-8"));

                if("0".equals(ok)) {
                    System.out.println(i + "昵称不通过");
                    System.exit(0);
                } else {
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
