package com.saille.ogzq;

import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;
import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URLEncoder;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import servlet.GlobalContext;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-22
 * Time: 10:34:57
 * To change this template use File | Settings | File Templates.
 */
public class CheckAllTopThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(CheckAllTopThread.class);
    public List<String[]> servers = new ArrayList<String[]>();
    private DefaultHttpClient client = null;

    public static Map<String, String> lastCheckTime = new HashMap<String, String>();
    public static Map<String, List<String[]>> detailAbility = new HashMap<String, List<String[]>>();
    public static List<String[]> ranks = new ArrayList<String[]>();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    private static CheckAllTopThread instance;
    public synchronized static CheckAllTopThread getInstance() {
        if(instance == null) {
            instance = new CheckAllTopThread();
        }
        return instance;
    }

    private int idx9377 = 1;
    private int getNext9377() {
        idx9377++;
        if(idx9377>15) {
            idx9377 = 1;
        }
        return idx9377;
    }
    private CheckAllTopThread() {
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "2"});
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "13"});
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "25"});
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "34"});
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "40"});
        servers.add(new String[]{"252506500@qq.com", "wfypyejtg", "官方", "46"});
        servers.add(new String[]{"sevarsti@sina.com", "pmgkpmgk", "官方", "52"});
        servers.add(new String[]{"robot0003@sina.com", "lspmgk", "官方", "63"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "1"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "91"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "227"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "237"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "276"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "301"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "321"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "330"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "335"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "338"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "339"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "340"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "341"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "342"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "343"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "344"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "345"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "351"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "356"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "361"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "362"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "363"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "364"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "365"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "366"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "367"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "369"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "370"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "371"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "372"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "373"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "374"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "375"});
//        servers.add(new String[]{"ssevarst", "ssesse", "9377", "376"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "1"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "5"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "9"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "25"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "27"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "65"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "93"});
        servers.add(new String[]{"ssevarst", "ssesse1", "05玩", "103"});
        servers.add(new String[]{"2634442629@qq.com", "ssesse", "开心", "1", "683"});
        servers.add(new String[]{"2634442629@qq.com", "ssesse", "开心", "7", "847"});
        servers.add(new String[]{"2634442629@qq.com", "ssesse", "开心", "11", "955"});
        servers.add(new String[]{"2634442629@qq.com", "ssesse", "开心", "17", "1250"});
        servers.add(new String[]{"ssevarst", "ssesse", "紫霞", "1"});
        servers.add(new String[]{"ssevarst", "ssesse", "搜狗", "1"});
        servers.add(new String[]{"ssevarst", "ssesse", "百度", "1"});
        servers.add(new String[]{"ssevarst", "ssesse", "百度", "15"});
        servers.add(new String[]{"ssevarst", "ssesse", "百度", "39"});
        servers.add(new String[]{"ssevarst", "ssesse", "百度", "51"});
        servers.add(new String[]{"ssevarst", "ssesse", "百度", "100"});
        servers.add(new String[]{"ssevarst", "ssesse1", "平安", "1", "4'"});
        servers.add(new String[]{"ssevarst", "ssesse1", "平安", "2", "680"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "1"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "10"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "50"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "62"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "74"});
        servers.add(new String[]{"ssevarst@wanwan.sina.com", "ssesse", "新浪", "86"});
        servers.add(new String[]{"ssevarst", "ssesse", "要玩", "1", "1720"});

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ((DataSource) GlobalContext.getSpringContext().getBean("mysql_ds")).getConnection();
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, -30);
            stmt = conn.prepareStatement("select * from ogzq_alltop where pubdate >= '" + sdf.format(c.getTime()) + "' order by pubdate");
            ResultSet rs = stmt.executeQuery();
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while(rs.next()) {
                String[] list = new String[]{rs.getString("name"), ""+rs.getInt("ability"), ""+rs.getInt("localplace"),
                    rs.getString("platform") + " " + rs.getInt("server") + "服", rs.getString("platform"), ""+rs.getInt("server"), rs.getString("pubdate"), sdf2.format(rs.getTimestamp("updatetime"))};
                addRank(list, conn, false);
                if(detailAbility.containsKey(rs.getString("platform")+rs.getInt("server")+rs.getString("name"))) {

                } else {
                    detailAbility.put(rs.getString("platform")+rs.getInt("server")+rs.getString("name"), new ArrayList<String[]>());
                }
                detailAbility.get(rs.getString("platform")+rs.getInt("server")+rs.getString("name")).add(list);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally{
            if(stmt != null) {
                try {
                    stmt.close();
                } catch(Exception ex) {}
            }
            if(conn != null) {
                try {
                    conn.close();
                } catch(Exception ex) {}
            }
        }
        System.out.println("初始化结束");
    }
    
    public static void main(String[] args) {
        new CheckAllTopThread().run();
    }

    public void updateServer(String platform, String server) {
        for(String[] s : this.servers) {
            if(s[2].equals(platform) && s[3].equals(server)) {
                if(s[2].equals("9377")) {
                    getNext9377();
                }
                this.client = null;
                List<String[]> l = check(s);
                if(l != null) {
                    String today = this.sdf.format(new Date());
                    lastCheckTime.put(s[2]+s[3], today);
                    for(String[] ll : l) {
                        if(detailAbility.containsKey(s[2]+s[3]+ll[0])) {
                            if(detailAbility.get(s[2]+s[3]+ll[0]).size() == 0) {
                                detailAbility.get(s[2]+s[3]+ll[0]).add(ll);
                            } else {
                                if(detailAbility.get(s[2]+s[3]+ll[0]).get(detailAbility.get(s[2]+s[3]+ll[0]).size() - 1)[6].equals(today)) {
                                    detailAbility.get(s[2]+s[3]+ll[0]).set(detailAbility.get(s[2]+s[3]+ll[0]).size() - 1, ll);
                                }
                            }
                        } else {
                            detailAbility.put(s[2]+s[3]+ll[0], new ArrayList<String[]>());
                            detailAbility.get(s[2]+s[3]+ll[0]).add(ll);
                        }
                        Connection conn = null;
                        try {
                            conn = ((DataSource) GlobalContext.getSpringContext().getBean("mysql_ds")).getConnection();
                            addRank(ll, conn, true);
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        } finally{
                            if(conn != null) {
                                try {
                                    conn.close();
                                } catch(Exception ex) {}
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        while(true) {
            LOGGER.info("循环检查全平台实力");
            Connection conn = null;
            try {
                conn = ((DataSource) GlobalContext.getSpringContext().getBean("mysql_ds")).getConnection();
                List<String[]> list = new ArrayList<String[]>();
                for(int i = 0; i < servers.size(); i++) {
                    String[] s = servers.get(i);
                    if(i > 0 && !s[2].equals(servers.get(i - 1)[2])) {
                        client = null;
                        if(s[2].equals("9377")) {
                            getNext9377();
                        }
                    }
                    LOGGER.info(i + "/" + servers.size() + ":" + s[2] + "_" + s[3]);
                    List<String[]> l = check(s);
                    if(l != null) {
                        String today = sdf.format(new Date());
                        lastCheckTime.put(s[2]+s[3], today);
                        list.addAll(l);
                        for(String[] ll : l) {
                            if(detailAbility.containsKey(s[2]+s[3]+ll[0])) {
                                if(detailAbility.get(s[2]+s[3]+ll[0]).size() == 0) {
                                    detailAbility.get(s[2]+s[3]+ll[0]).add(ll);
                                } else {
                                    if(detailAbility.get(s[2]+s[3]+ll[0]).get(detailAbility.get(s[2]+s[3]+ll[0]).size() - 1)[6].equals(today)) {
                                        detailAbility.get(s[2]+s[3]+ll[0]).set(detailAbility.get(s[2]+s[3]+ll[0]).size() - 1, ll);
                                    }
                                }
                            } else {
                                detailAbility.put(s[2]+s[3]+ll[0], new ArrayList<String[]>());
                                detailAbility.get(s[2]+s[3]+ll[0]).add(ll);
                            }
                            addRank(ll, conn, true);
                        }
                    }
                }
                this.client = null;
                System.out.println("size=" + list.size());
                for(String[] ss : list) {
                    for(String s : ss) {
                        System.out.print(s + "\t");
                    }
                    System.out.println();
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            } finally{
                try {
                    if(conn != null) {
                        conn.close();
                    }
                } catch(Exception ex) {}
            }
            try {
                Thread.sleep(1000 * 60 * 60 * 2);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private List<String[]> check(String[] s) {
        if(s[2].equals("官方")) {
            return checkXD(s);
        } else if(s[2].equals("9377")) {
            return check9377(s);
        } else if(s[2].equals("紫霞")) {
            return checkZixia(s);
        } else if(s[2].equals("05玩")) {
            return check05wan(s);
        } else if(s[2].equals("百度")) {
            return checkBaidu(s);
        } else if(s[2].equals("开心")) {
            return checkKaixin(s);
        } else if(s[2].equals("平安")) {
            return checkPingan(s);
        } else if(s[2].equals("搜狗")) {
            return checkSougou(s);
        } else if(s[2].equals("新浪")) {
            return checkSina(s);
        } else if(s[2].equals("要玩")) {
            return checkYaowan(s);
        } else if(s[2].equals("CNTV")) {//todo
            return checkCntv(s);
        } else if(s[2].equals("JJ竞技")) {//todo
            return checkJj(s);
        }
        return null;
    }

    private List<String[]> checkJj(String[] serverparams) {
        return null;
    }

    private List<String[]> checkCntv(String[] serverparams) {
        return null;
    }

    private List<String[]> checkSina(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".kof.sports.sina.com";

            String userkey = UtilFunctions.getJsByFile(new String[]{"D:\\js\\sina_ssologin.js"}, "encode", new Object[]{email});
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
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
            pwd = UtilFunctions.getJsByFile(new String[]{"D:\\js\\sina_ssoconfig.js"}, "make", new Object[]{servertime ,nonce ,pwd, pubkey});
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

            String url = (server.equals("1") ? "" : (new DecimalFormat("00").format(Integer.parseInt(server)-1)));
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

            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "GBK");
            resp.close();
            gm.releaseConnection();
            msg = msg.substring(msg.indexOf("location.replace(\"") + "location.replace(\"".length());
            msg = msg.substring(0, msg.indexOf("\""));

            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            gm.releaseConnection();

            msg = msg.substring(msg.indexOf("location.replace('") + "location.replace('".length());
            msg = msg.substring(0, msg.indexOf("'"));
            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            gm.releaseConnection();

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkSougou(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".ogzq.wan.sogou.com";

            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            HttpPost pm = new HttpPost("https://account.sogou.com/web/login");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", email));
            params.add(new BasicNameValuePair("password", pwd));
            params.add(new BasicNameValuePair("captcha", ""));
            params.add(new BasicNameValuePair("autoLogin", "0"));
            params.add(new BasicNameValuePair("client_id", "1100"));
            params.add(new BasicNameValuePair("domain", ""));
            params.add(new BasicNameValuePair("xd", "http://wan.sogou.com/static/jump.html"));
//                params.add(new BasicNameValuePair("token", ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            CloseableHttpResponse resp = client.execute(pm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpGet gm = new HttpGet("http://wan.sogou.com/play.do?gid=20&sid=" + server);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            gm = new HttpGet("http://wan.sogou.com/playgame.do?gid=20&sid=" + server);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            msg = msg.substring(msg.indexOf("<form"));
            msg = msg.substring(0, msg.indexOf("</form"));
            params = new ArrayList<NameValuePair>();
            while(msg.indexOf("<input") > 0) {
                msg = msg.substring(msg.indexOf("<input") + "<input".length());
                String pp;
                if(msg.indexOf("<input") > 0) {
                    pp = msg.substring(0, msg.indexOf("<input"));
                } else {
                    pp = msg;
                }
                pp = pp.substring(pp.indexOf("name=\"") + "name=\"".length());
                String key = pp.substring(0, pp.indexOf("\""));
                pp = pp.substring(pp.indexOf("value=\"") + "value=\"".length());
                String value = pp.substring(0, pp.indexOf("\""));
                params.add(new BasicNameValuePair(key, value));
            }
            pm = new HttpPost("http://s" + server + ".ogzq.wan.sogou.com/SouGou/SouGouUnion/SouGouLogin.aspx");
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<String[]> checkPingan(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = new DecimalFormat("000").format(Integer.parseInt(serverparams[3]));
            String gameurl = "http://ogzq" + server + ".game.24caipiao.com";

            DefaultHttpClient client;
            if(this.client == null) {
                client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                String key = "CC48B04975F355A4CF863A26D26D9CCC6CF98EF3E5241EB7B5C5DCE7D0384AB4595B9B9088F9F1D3BE7AC30F2D20EF0504CCEB6A6DA45E220B60E8FF3DC453E06C47DE35BDFDFA1A6689C3178BB6F96DF6EB433764E8036581594AC67B528B35BCECCBE35BA36B38A77E859F7F6D3AEA80D36C16EDE4E98B97821BE00DF4393F";
                pwd = UtilFunctions.getJsByFile(new String[]{"D:\\js\\rsa_all.js"}, "cpRSAEncrypt", new Object[]{pwd, key});

                HttpPost pm = new HttpPost("https://passport.wanlitong.com/pass-info/oauth2/loginPassport.shtml");
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("loginName", email));
                params.add(new BasicNameValuePair("pwd", pwd));
                params.add(new BasicNameValuePair("client_id", "IN_000002"));
                params.add(new BasicNameValuePair("redirect_uri", "http://game.wanlitong.com/?act=login&st=loginCallback&go_url=aHR0cDovL2dhbWUud2FubGl0b25nLmNvbS8="));
                params.add(new BasicNameValuePair("response_type", "code"));
                params.add(new BasicNameValuePair("platform", "WEB"));
                params.add(new BasicNameValuePair("media_source", "game_pc"));
                params.add(new BasicNameValuePair("otherLogin", "QQ|ALIPAY|WEIBO|WEIXIN"));
                params.add(new BasicNameValuePair("state", "_"+new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "000000"));
                params.add(new BasicNameValuePair("tabs", "paw|wlt|toa"));
                params.add(new BasicNameValuePair("accountType", "paw"));
                params.add(new BasicNameValuePair("loginPage", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                CloseableHttpResponse resp = client.execute(pm);

                if(resp.getStatusLine().getStatusCode() == 302) {
                    resp.close();
                    pm.releaseConnection();
                    HttpGet gm  = new HttpGet(resp.getHeaders("Location")[0].getValue());
                    resp = client.execute(gm);
                    String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                    resp.close();
                    gm.releaseConnection();
                }

                pm = new HttpPost("http://game.wanlitong.com/?act=index&st=get_user_grade");
                resp = client.execute(pm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                resp.close();
                pm.releaseConnection();
            } else {
                client = this.client;
            }

            HttpGet gm = new HttpGet("http://game.wanlitong.com/?act=mygame&st=play&type=1&id=10&serverId=" + serverparams[4]);
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            gm.releaseConnection();
            msg = msg.substring(msg.indexOf("<iframe"));
            msg = msg.substring(msg.indexOf("src='") + "src='".length());
            msg = msg.substring(0, msg.indexOf("'"));
            msg = msg.replaceAll(" ", "%20");
            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            gm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkKaixin(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".ogzq.kaixin001.com.cn";

            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            HttpPost pm = new HttpPost("https://security.kaixin001.com/login/login_post.php");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loginemail", email));
            params.add(new BasicNameValuePair("password", pwd));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            CloseableHttpResponse resp = client.execute(pm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpGet gm = new HttpGet("http://game.kaixin001.com/play.php?aid=5228&sid=" + serverparams[4] + "&mtype=1&rf=&pos="); //1322=服务器
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            gm = new HttpGet("http://game.kaixin001.com/wg_into_server.php?aid=5228&sid=" + serverparams[4] + "&mtype=1&rf=&t=");
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            msg = msg.substring(msg.indexOf("<form"));
            msg = msg.substring(0, msg.indexOf("</form"));
            msg = msg.substring(msg.indexOf("name=\"verify\"") + "name=\"verify\"".length());
            msg = msg.substring(msg.indexOf("value=\"") + "value=\"".length());
            msg = msg.substring(0, msg.indexOf("\""));
            pm = new HttpPost(gameurl + "/KaiXinUnion/KaiXinUnionLogin.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("verify", msg));
            params.add(new BasicNameValuePair("serverid", server));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private List<String[]> checkBaidu(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".baidu.xdgame.cn";

            DefaultHttpClient client = null;
            if(this.client == null) {
                client = com.saille.baidu.LoginUtils.login(email, pwd);
                HttpGet gm = new HttpGet("http://www.05wan.com/interface/login?KeyID=&refer=http://o.05wan.com/server.html&usbkey_rand=" + new Random().nextInt() +"&return_EncData=&username=" + email + "&password=" + pwd);
                CloseableHttpResponse resp = client.execute(gm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            } else {
                client = this.client;
            }
            HttpGet gm = new HttpGet("http://youxi.baidu.com/login_game_by_post.xhtml?id=359&client=&inviteMsg=&serverId=" + server + "&toolbarHide=&showlogintype=#null");
            CloseableHttpResponse resp = client.execute(gm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            gm.releaseConnection();
            ret = ret.substring(ret.indexOf("document.getElementById(\"game_frame\").src=\"") + "document.getElementById(\"game_frame\").src=\"".length());
            ret = ret.substring(0, ret.indexOf(";") - 1);
            ret = ret.replace("\"+\"", "");

            gm = new HttpGet("http://youxi.baidu.com"+ret);
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            gm.releaseConnection();

            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            while(ret.indexOf("oForm.appendChild(oInput)") > 0) {
                String value = ret.substring(ret.indexOf("oInput.value = \"") + "oInput.value = \"".length());
                value = value.substring(0, value.indexOf("\""));
                String key = ret.substring(ret.indexOf("oInput.name = \"") + "oInput.name = \"".length());
                key = key.substring(0, key.indexOf("\""));
                if(StringUtils.isNotEmpty(value)) {
                    params.add(new BasicNameValuePair(key, value));
                }
                ret = ret.substring(ret.indexOf("oForm.appendChild(oInput)") + "oForm.appendChild(oInput)".length());
            }

            HttpPost pm = new HttpPost(gameurl + "/BaiDuUnion/BaiDuLogin.aspx");
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.close();
            pm.releaseConnection();

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            return null;
        }
    }

    private List<String[]> check05wan(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".o.05wan.com";
            DefaultHttpClient client = null;
            if(this.client == null) {
                client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                HttpGet gm = new HttpGet("http://www.05wan.com/interface/login?KeyID=&refer=http://o.05wan.com/server.html&usbkey_rand=" + new Random().nextInt() +"&return_EncData=&username=" + email + "&password=" + pwd);
                CloseableHttpResponse resp = client.execute(gm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            } else {
                client = this.client;
            }
            HttpGet gm = new HttpGet("http://www.05wan.com/server/ogame?server=s" + server);
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            msg = msg.substring(msg.indexOf("<table id=\"main\"") + "<table id=\"main\"".length());
            msg = msg.substring(msg.indexOf("<iframe") + "<iframe".length());
            msg = msg.substring(msg.indexOf("<iframe"));
            msg = msg.substring(msg.indexOf("src=\"") + "src=\"".length());
            msg = msg.substring(0, msg.indexOf("\""));

            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpPost pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkZixia(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".ogzq.game.zixia.com";
            DefaultHttpClient client = null;
            if(this.client == null) {
                client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                HttpGet gm = new HttpGet("http://game.zixia.com/game_json.php?action=login&pwuser=" + email + "&pwpwd=" + pwd + "&lgt=0&gdcode=");
                CloseableHttpResponse resp = client.execute(gm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            } else {
                client = this.client;
            }
            HttpGet gm = new HttpGet("http://game.zixia.com/ogzq/" + server + "/");
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpPost pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkYaowan(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".ogzq.yaowan.com";
            DefaultHttpClient client = null;
            if(this.client == null) {
                client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                HttpPost pm = new HttpPost("http://www.yaowan.com/?c=2016&m=user&action=loginform_ajax");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", email));
                params.add(new BasicNameValuePair("password", pwd));
                params.add(new BasicNameValuePair("savelog", "undefined"));
                params.add(new BasicNameValuePair("tourl", "http://www.yaowan.com/"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                CloseableHttpResponse resp = client.execute(pm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

                pm = new HttpPost("http://www.yaowan.com/?c=2016&m=Template&action=getUserData");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("loged", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = client.execute(pm);
                msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            } else {
                client = this.client;
            }
            HttpGet gm = new HttpGet("http://www.yaowan.com/Default.php?m=game&game_id=98&district_id=" + serverparams[4]);
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            msg = msg.substring(msg.indexOf("<iframe src='") + "<iframe src='".length());
            msg = msg.substring(0, msg.indexOf("'"));
            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            msg = msg.substring(msg.indexOf("<iframe"));
            msg = msg.substring(msg.indexOf("src=\"") + "src=\"".length());
            msg = msg.substring(0, msg.indexOf("\""));

            gm = new HttpGet("http://member.yaowan.com/?m=user&action=me");
            resp = client.execute(gm);
            String msg1 = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpPost pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> check9377(String[] serverparams) {
        try {
            String email = serverparams[0] + idx9377;
            String pwd = serverparams[1];
            String server = serverparams[3];
            String gameurl = "http://s" + server + ".ogzq.9377.com";
            DefaultHttpClient client = null;
            if(this.client == null) {
                client = new DefaultHttpClient();
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                HttpPost pm = new HttpPost("http://wvw.9377.com/login.php");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("do", "login"));
                params.add(new BasicNameValuePair("gourl", "http://www.9377.com/game_box.php/game/ogzq"));
                params.add(new BasicNameValuePair("username", email));
                params.add(new BasicNameValuePair("password", pwd));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                CloseableHttpResponse resp = client.execute(pm);
                String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            } else {
                client = this.client;
            }
            HttpGet gm = new HttpGet("http://www.9377.com/game_login.php?game=ogzq&server=" + server);
            CloseableHttpResponse resp = client.execute(gm);
            String msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            if(msg.indexOf("您登录游戏次数过多") > 0) {
                return null;
            }

            msg = msg.substring(msg.indexOf("<iframe") + "<iframe".length());
            msg = msg.substring(msg.indexOf("src=\"") + "src=\"".length());
            msg = msg.substring(0, msg.indexOf("\""));
            gm = new HttpGet(msg);
            resp = client.execute(gm);
            msg = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            HttpPost pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            String ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(Integer.parseInt(attrs[2]) > curShili) {
                    curId = attrs[11];
                    curShili = Integer.parseInt(attrs[2]);
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            resp = client.execute(pm);
            resp.getEntity().getContent().close();
            resp.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            resp = client.execute(gm);
            ret = CommonUtils.getString(resp.getEntity().getContent(), "utf-8");
            resp.getEntity().getContent().close();
            resp.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkXD(String[] serverparams) {
        try {
            String email = serverparams[0];
            String pwd = serverparams[1];
            String gameurl = "http://f" + serverparams[3] + ".ogzq.xdgame.cn";
            String nick = null;
            DefaultHttpClient client = new DefaultHttpClient();
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0,false);
            client.setHttpRequestRetryHandler(handler);
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
//                HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
            client.getParams().setParameter("http.protocol.single-cookie-header", true);
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

            HttpPost pm = new HttpPost("http://ogzq.xdgame.cn:80/iframePage/Loginiframe.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Login_1", email + "*" + pwd));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(pm);
            InputStream is = response.getEntity().getContent();
            String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            is.close();
            response.close();
            pm.releaseConnection();
            ret = ret.substring(ret.indexOf("*") + 1);
            if(ret.indexOf("邮箱账号或密码错误") >= 0) {
                return null;
            }
            String u = ret.substring(0, ret.indexOf("*"));
//                Thread.sleep(2000);

            HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/Transition.aspx?S=" + serverparams[3]);
            response = (CloseableHttpResponse)client.execute(gm);
            ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();
            if(ret.indexOf("请您先登陆") >= 0) {
                System.out.println("fuck，又要重登录了");
                return null;
            }
            String t = ret.substring(ret.indexOf("&T=") + 3);
            t = t.substring(0, t.indexOf("&"));
            String m = ret.substring(ret.indexOf("&M=") + 3);
            m = m.substring(0, m.indexOf("&"));

            gm = new HttpGet(gameurl + "/ChooseRole.aspx?S=" + serverparams[3] + "&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0");
            response = (CloseableHttpResponse)client.execute(gm);
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            response.getEntity().getContent().close();
            response.close();
            pm.releaseConnection();
            String[] ids = ret.split("@");
            String curId = null;
            int curShili = 0;
            for(String id : ids) {
                String[] attrs = id.split("[*]");
                if(nick != null && !"".equals(nick)) {
                    if(attrs[1].equals(nick)) {
                        curId = attrs[11];
                        break;
                    }
                } else {
                    if(Integer.parseInt(attrs[2]) > curShili) {
                        curId = attrs[11];
                        curShili = Integer.parseInt(attrs[2]);
                    }
                }
            }

            pm = new HttpPost(gameurl + "/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            response.getEntity().getContent().close();
            response.close();
            pm.releaseConnection();

            gm = new HttpGet(gameurl + "/Default.aspx");
            response = (CloseableHttpResponse)client.execute(gm);
            ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();

            return checkRank(client, gameurl, serverparams);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private List<String[]> checkRank(DefaultHttpClient client, String url, String[] serverparams) throws Exception {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            HttpPost pm = new HttpPost(url + "/ChampionCup.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            CloseableHttpResponse resp = client.execute(pm);
            String s = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
            resp.close();
            pm.releaseConnection();
            String[] teams = s.split("\\*");
            List<String[]> rett = new ArrayList<String[]>();
            for(int i = 0; i < teams.length; i++) {
                String id = teams[i].split("\\|")[2];
                pm = new HttpPost(url + "/TeamAndPlayer/Team.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("LoadTeam1", id));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                resp = client.execute(pm);
                s = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");
                resp.close();
                pm.releaseConnection();
                String[] parts = s.split("\\#");
                rett.add(new String[]{parts[0], parts[24], parts[3], serverparams[2] + " " + serverparams[3] + "服", serverparams[2], serverparams[3], sdf.format(new Date()), sdf2.format(new Date())});
            }
            this.client = client;
            return rett;
        } catch(Exception ex) {
            HttpPost pm = new HttpPost(url + "/Ranking.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("typeIndex", "3"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            CloseableHttpResponse resp = client.execute(pm);
            String s = CommonUtils.getString(resp.getEntity().getContent(), "UTF-8");

            s = s.substring(0, s.indexOf("∫"));
            String[] ss = s.split("\\⊥");
            List<String[]> rett = new ArrayList<String[]>();
            for(int i = 0; i < ss.length; i++) {
                String sss = ss[i];
                String[] p = sss.split("\\∠");
                if(p[0].indexOf("</a>") > 0) {
                    rett.add(new String[]{p[0].substring(p[0].indexOf("</a>") + "</a>".length()), p[1], i+1+"", serverparams[2] + " " + serverparams[3] + "服", serverparams[2], serverparams[3], sdf.format(new Date()), sdf2.format(new Date())});
                } else {
                    rett.add(new String[]{p[0], p[1], i+1+"", serverparams[2] + " " + serverparams[3] + "服", serverparams[2], serverparams[3], sdf.format(new Date()), sdf2.format(new Date())});
                }
            }
            this.client = client;
            return rett;
        }
    }

    private List<String[]> sort(List<String[]> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Integer.parseInt(list.get(i)[1]) > Integer.parseInt(list.get(pos)[1])) {
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

    private void addRank(String[] list, Connection conn, boolean update) {
        try {
            if(update) {
                String pubdate = sdf.format(new Date());
                PreparedStatement stmt = conn.prepareStatement("select count(1) from ogzq_alltop where pubdate = ? and platform = ? and server = ? and name = ?");
                stmt.setString(1, pubdate);
                stmt.setString(2, list[4]);
                stmt.setInt(3, Integer.parseInt(list[5]));
                stmt.setString(4, list[0]);
                ResultSet rs = stmt.executeQuery();
                int count = 0;
                if(rs.next()) {
                    count = rs.getInt(1);
                }
                rs.close();
                stmt.close();
                if(count > 0) {
                    String sql = "update ogzq_alltop set ability = ?, localplace = ?, updatetime = now() where pubdate = ? and platform = ? and server = ? and name = ?";
                    stmt = conn.prepareStatement(sql);
                    stmt.setInt(1, Integer.parseInt(list[1]));
                    stmt.setInt(2, Integer.parseInt(list[2]));
                    stmt.setString(3, pubdate);
                    stmt.setString(4, list[4]);
                    stmt.setInt(5, Integer.parseInt(list[5]));
                    stmt.setString(6, list[0]);
                    stmt.executeUpdate();
                } else {
                    String sql = "insert into ogzq_alltop(platform, server, name, ability, localplace, pubdate) values(?,?,?,?,?,?)";
                    stmt = conn.prepareStatement(sql);
                    stmt.setString(1, list[4]);
                    stmt.setInt(2, Integer.parseInt(list[5]));
                    stmt.setString(3, list[0]);
                    stmt.setInt(4, Integer.parseInt(list[1]));
                    stmt.setInt(5, Integer.parseInt(list[2]));
                    stmt.setString(6, pubdate);
                    stmt.executeUpdate();
                }
                stmt.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if(ranks.size() >= 100 && Integer.parseInt(ranks.get(99)[1]) > Integer.parseInt(list[1])) {
            return;
        }
        boolean found = false;
        int curPlace = -1;
        if(ranks.size() == 0) {
            ranks.add(list);
            return;
        }
        for(int i = 0; i < ranks.size(); i++) {
            if(ranks.get(i)[4].equals(list[4]) &&
                    ranks.get(i)[5].equals(list[5]) &&
                    ranks.get(i)[0].equals(list[0])) {
                found = true;
                ranks.set(i,list);
                break;
            } else if(Integer.parseInt(list[1]) > Integer.parseInt(ranks.get(i)[1])) {
                if(curPlace < 0) {
                    curPlace = i;
                }
            }
        }
        if(!found) {
            if(curPlace == -1) {
                ranks.add(list);
            } else {
                ranks.add(curPlace, list);
            }
        }
        if(ranks.size() > 100) {
            ranks = ranks.subList(0, 100);
        }
        ranks = sort(ranks, 0, ranks.size());
    }
}
