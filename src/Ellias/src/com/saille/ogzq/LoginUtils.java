package com.saille.ogzq;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.net.URLEncoder;
import java.io.InputStream;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.*;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;

public class LoginUtils {
    static HttpParams param = new BasicHttpParams();
//                ConnManagerParams.setTimeout(param, 5000);
//                HttpConnectionParams.setConnectionTimeout(param, 5000);
//                HttpConnectionParams.setSoTimeout(param, 5000);

    private static  SchemeRegistry schemeRegistry;
    private static PoolingClientConnectionManager cm[];
    private static int nextCM = 0;
    static {
        schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        cm = new PoolingClientConnectionManager[10];
        for(PoolingClientConnectionManager c : cm) {
            c = new PoolingClientConnectionManager(schemeRegistry);
            c.setMaxTotal(50);
        }
    }
//                HttpHost host1 = new HttpHost("ogzq.xdgame.cn", 80);
//                HttpHost host2 = new HttpHost("f7.ogzq.xdgame.cn", 80);

//                cm.setMaxPerRoute(new HttpRoute(host1), 100);
//                cm.setMaxPerRoute(new HttpRoute(host2), 100);
//                cm.setDefaultMaxPerRoute(20);
    public static void main(String[] args) {
        try {
            String[] emails = {"jin6@sohu.com"};
//
            String pwd = "jin123";
            for(String email : emails) {
                HttpClient client = Login(email, pwd);
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.LEAGUE);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("signup", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(client, email, pm);
                System.out.println(ret);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static HttpClient Login(String id, String pwd) {
//        try {Thread.sleep(2000);}catch(Exception ex){}
        String server = id.substring(id.lastIndexOf("@") + 1);
        if(server.indexOf("*") >= 0) {
            server = server.substring(0, server.indexOf("*"));
        }
        IDUtils.IDPWDS.put(id, pwd);
        if(server.startsWith("hupu")) {
            int s;
            if(server.indexOf("*") >= 0) {
                s = Integer.parseInt(server.substring(4, server.indexOf("*")));
            } else {
                s = Integer.parseInt(server.substring(4));
            }
            return LoginHupu(id, pwd, s);
        } else if(server.startsWith("e7wan")) {
            return LoginE7Wan(id, pwd);
        } else if(server.startsWith("fenghuang")) {
            return LoginFenghuang(id, pwd);
        } else if(server.equals("qq")) {
            return LoginQq(id, pwd);
        } else {
            return LoginXDGame(id, pwd);
        }
    }

    public static HttpClient LoginXDGame(String originanlemail, String pwd) {
        while(true) {
            try {
                String nick = null;
                String email = originanlemail;
                if(originanlemail.indexOf("*") >= 0) {
                    nick = email.substring(email.indexOf("*") + 1);
                    email = email.substring(0, email.indexOf("*"));
                }
                PropertiesConfiguration conf = null;
                try {
                    conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/ids.ini"));
                } catch(Exception ex) {}
                if(conf != null) {
                    String p = conf.getString(email);
                    if(StringUtils.isNotEmpty(p)) {
                        pwd = p;
                    }
                }

//                HttpClient client = new DefaultHttpClient();
                DefaultHttpClient client = new DefaultHttpClient(cm[next()], param);
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

                HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/Transition.aspx?S=2");
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
//                pm = new HttpPost("http://ogzq.xdgame.cn:80/iframePage/Loginiframe.aspx");
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("GetNewUrl_1", u));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                response = client.execute(pm);
//                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//                pm.releaseConnection();
                if(ret.indexOf("请您先登陆") >= 0) {
                    System.out.println("fuck，又要重登录了");
                    continue;
                }
                String t = ret.substring(ret.indexOf("&T=") + 3);
                t = t.substring(0, t.indexOf("&"));
                String m = ret.substring(ret.indexOf("&M=") + 3);
                m = m.substring(0, m.indexOf("&"));

                gm = new HttpGet("http://f7.ogzq.xdgame.cn:80/ChooseRole.aspx?S=2&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0");
                response = (CloseableHttpResponse)client.execute(gm);
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                pm = new HttpPost("http://f7.ogzq.xdgame.cn:80/ChooseRole.aspx");
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

                pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                params.add(new BasicNameValuePair("ai", curId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse)client.execute(pm);
                response.getEntity().getContent().close();
                response.close();
                pm.releaseConnection();

//                String url = "http://f7.ogzq.xdgame.cn/Loading.aspx?S=8&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0";
                gm = new HttpGet("http://f7.ogzq.xdgame.cn/Default.aspx");
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
                ret = ret.substring(ret.indexOf("HiddenFieldMyClubID") + "HiddenFieldMyClubID".length());
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String clubId = ret.substring(0, ret.indexOf("\""));
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String accountId = ret.substring(0, ret.indexOf("\""));
                IDUtils.IDObjIds.put(originanlemail, new String[]{clubId, accountId});

                return client;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static HttpClient LoginHupu(String originanlemail, String pwd, int server) {
        try {
            String nick = null;
            String email = originanlemail;
            if(email.indexOf("*") >= 0) {
                nick = email.substring(email.indexOf("*") + 1);
            }
            email = email.substring(0, email.lastIndexOf("@"));

//            HttpClient client = new DefaultHttpClient();
            DefaultHttpClient client = new DefaultHttpClient(cm[next()], param);
            DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0,false);
            client.setHttpRequestRetryHandler(handler);
            client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
            client.getParams().setParameter("http.protocol.single-cookie-header", true);
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
            client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
            client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

            HttpPost pm = new HttpPost("http://passport.hupu.com/login?from_webgame_ogzq");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("username", email));
//            params.add(new BasicNameValuePair("username", URLEncoder.encode(email, "utf-8")));
            params.add(new BasicNameValuePair("password", pwd));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            CloseableHttpResponse response = (CloseableHttpResponse)client.execute(pm);
//            System.out.println(CommonUtils.getString(response.getEntity().getContent(), "utf-8"));
            response.getEntity().getContent().close();
            response.close();
            pm.releaseConnection();

            HttpGet gm = new HttpGet("http://ogzq.hupu.com/?s=" + server);
            response = (CloseableHttpResponse)client.execute(gm);
            String s = (CommonUtils.getString(response.getEntity().getContent(), "utf-8"));
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();

            s = s.substring(s.indexOf("<iframe") + "<iframe".length());
            s = s.substring(0, s.indexOf("</ifra"));
            s = s.substring(s.indexOf("src=\"") + "src=\"".length());
            s = s.substring(0, s.indexOf("\""));

            for(int i = 1; i < 7; i++) {
                s = s.replaceAll("s" + i + ".ogzq.hupu.com", "f7.ogzq.xdgame.cn");
            }
            gm = new HttpGet(s);
            response = (CloseableHttpResponse)client.execute(gm);
//            System.out.println(CommonUtils.getString(response.getEntity().getContent(), "utf-8"));
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();

//            gm = new HttpGet("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
//            response = client.execute(gm);
//            System.out.println(CommonUtils.getString(response.getEntity().getContent(), "utf-8"));
//            gm.releaseConnection();

            pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
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

            pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("ai", curId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            response.getEntity().getContent().close();
            response.close();
            pm.releaseConnection();

            gm = new HttpGet("http://f7.ogzq.xdgame.cn/Default.aspx");
            response = (CloseableHttpResponse)client.execute(gm);
            ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            response.getEntity().getContent().close();
            response.close();
            gm.releaseConnection();
            ret = ret.substring(ret.indexOf("HiddenFieldMyClubID") + "HiddenFieldMyClubID".length());
            ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
            String clubId = ret.substring(0, ret.indexOf("\""));
            ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
            String accountId = ret.substring(0, ret.indexOf("\""));

            IDUtils.IDObjIds.put(originanlemail, new String[]{clubId, accountId});
            return client;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static HttpClient LoginE7Wan(String originanlemail, String pwd) {
        while(true) {
            try {
                String nick = null;
                String email = originanlemail;
                if(email.indexOf("*") >= 0) {
                    nick = email.substring(email.indexOf("*") + 1);
                }
                email = email.substring(0, email.lastIndexOf("@"));
                PropertiesConfiguration conf = null;
                try {
                    conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/ids.ini"));
                } catch(Exception ex) {}
                if(conf != null) {
                    String p = conf.getString(originanlemail);
                    if(StringUtils.isNotEmpty(p)) {
                        pwd = p;
                    }
                }

                HttpGet gm = new HttpGet("http://pass.e7wan.com/login.php?act=login&gameName=ogzq&name=" + email + "&pwd=" + pwd + "&code=&forward=http%3A%2F%2Fogzq.e7wan.com%2Findex.html");

//                HttpClient client = new DefaultHttpClient();
                DefaultHttpClient client = new DefaultHttpClient(cm[next()], param);
                DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0,false);
                client.setHttpRequestRetryHandler(handler);
                client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
                client.getParams().setParameter("http.protocol.single-cookie-header", true);
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

                CloseableHttpResponse response = (CloseableHttpResponse)client.execute(gm);
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
//                System.out.println(CommonUtils.getString(response.getEntity().getContent(), "gb2312"));

                gm = new HttpGet("http://pass.e7wan.com/games/userstate.php?fun=userstate");
                response = (CloseableHttpResponse)client.execute(gm);
                CommonUtils.getString(response.getEntity().getContent(), "gb2312");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                gm = new HttpGet("http://pass.e7wan.com/game.php?GameName=ogzq&ServerName=og1");
                response = (CloseableHttpResponse)client.execute(gm);
                String s = (CommonUtils.getString(response.getEntity().getContent(), "gb2312"));
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                List<String[]> pp = new ArrayList<String[]>();
                pp.add(new String[]{"title", URLEncoder.encode("双线一区")});
                s = s.substring(s.indexOf("\"url\"") + "url".length());
                s = s.substring(s.indexOf("value=\"") + "value=\"".length());
                s = s.substring(0, s.indexOf("\""));
                pp.add(new String[]{"url", URLEncoder.encode(s)});

                HttpPost pm = new HttpPost("http://ogzq.e7wan.com/servers/");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                for(String[] p : pp) {
                    params.add(new BasicNameValuePair(p[0], p[1]));
                }
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                pm.addHeader("Referer", "http://pass.e7wan.com/game.php?GameName=ogzq&ServerName=og1");
                response = (CloseableHttpResponse)client.execute(pm);
                CommonUtils.getString(response.getEntity().getContent(), "gb2312");
                response.getEntity().getContent().close();
                response.close();
                pm.releaseConnection();

                gm = new HttpGet("http://i.e7wan.com/space.php?do=calls&GameName=ogzq");
                response = (CloseableHttpResponse)client.execute(gm);
                CommonUtils.getString(response.getEntity().getContent(), "gb2312");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                gm = new HttpGet("http://i.e7wan.com/space.php?do=head");
                response = (CloseableHttpResponse)client.execute(gm);
                CommonUtils.getString(response.getEntity().getContent(), "gb2312");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();


                gm = new HttpGet(pp.get(1)[1].replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%3F","?").replaceAll("%3D", "=").replaceAll("%26", "&"));
                response = (CloseableHttpResponse)client.execute(gm);
                s = (CommonUtils.getString(response.getEntity().getContent(), "gb2312"));
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                s = s.substring(s.indexOf("href='") + "href='".length());
                s = s.substring(0, s.indexOf("'"));
                gm = new HttpGet(s);
                response = (CloseableHttpResponse)client.execute(gm);
                CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse)client.execute(pm);
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
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

                pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                params.add(new BasicNameValuePair("ai", curId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse)client.execute(pm);
                response.getEntity().getContent().close();
                response.close();
                pm.releaseConnection();

                gm = new HttpGet("http://f7.ogzq.xdgame.cn/Default.aspx");
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
                ret = ret.substring(ret.indexOf("HiddenFieldMyClubID") + "HiddenFieldMyClubID".length());
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String clubId = ret.substring(0, ret.indexOf("\""));
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String accountId = ret.substring(0, ret.indexOf("\""));

                IDUtils.IDObjIds.put(originanlemail, new String[]{clubId, accountId});
                return client;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static HttpClient LoginFenghuang(String originanlemail, String pwd) {
        while(true) {
            try {
                String nick = null;
                String email = originanlemail;
                if(email.indexOf("*") >= 0) {
                    nick = email.substring(email.indexOf("*") + 1);
                }
                email = email.substring(0, email.lastIndexOf("@"));
                PropertiesConfiguration conf = null;
                try {
                    conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/ids.ini"));
                } catch(Exception ex) {}
                if(conf != null) {
                    String p = conf.getString(originanlemail);
                    if(StringUtils.isNotEmpty(p)) {
                        pwd = p;
                    }
                }

//                HttpClient client = new DefaultHttpClient();
                DefaultHttpClient client = new DefaultHttpClient(cm[next()], param);
                DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0,false);
                client.setHttpRequestRetryHandler(handler);
                client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
                client.getParams().setParameter("http.protocol.single-cookie-header", true);
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

                HttpPost pm = new HttpPost("http://play.ifeng.com/?_a=Dologin");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("username", email));
                params.add(new BasicNameValuePair("password", pwd));
                params.add(new BasicNameValuePair("inpass", "on"));
                params.add(new BasicNameValuePair("game", "ogzq"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                CloseableHttpResponse response = client.execute(pm);
                InputStream is = response.getEntity().getContent();
                String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                is.close();
                response.close();
                pm.releaseConnection();

                HttpGet gm = new HttpGet("http://play.ifeng.com/?_c=ogzq&_a=enterGame&area=7");
                response = client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
//                System.out.println(ret);
                ret = ret.substring(ret.indexOf("$(\"#game_iframe_id\").attr(\"src\",\"") + "$(\"#game_iframe_id\").attr(\"src\",\"".length());
                ret = ret.substring(0, ret.indexOf("\""));
                ret = ret.replaceAll("f12.ogzq.xdgame.cn", "f7.ogzq.xdgame.cn");

                gm = new HttpGet(ret);
                response = (CloseableHttpResponse)client.execute(gm);
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                gm = new HttpGet("http://f7.ogzq.xdgame.cn:80/ChooseRole.aspx");
                response = (CloseableHttpResponse)client.execute(gm);
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                pm = new HttpPost("http://f7.ogzq.xdgame.cn:80/ChooseRole.aspx");
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

                pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                params.add(new BasicNameValuePair("ai", curId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse)client.execute(pm);
                response.getEntity().getContent().close();
                response.close();
                pm.releaseConnection();

                gm = new HttpGet("http://f7.ogzq.xdgame.cn/Default.aspx");
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
                ret = ret.substring(ret.indexOf("HiddenFieldMyClubID") + "HiddenFieldMyClubID".length());
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String clubId = ret.substring(0, ret.indexOf("\""));
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String accountId = ret.substring(0, ret.indexOf("\""));

                IDUtils.IDObjIds.put(originanlemail, new String[]{clubId, accountId});
                return client;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static HttpClient LoginQq(String originanlemail, String pwd) {
        while(true) {
            try {
                String nick = null;
                String email = originanlemail;
                if(originanlemail.indexOf("*") >= 0) {
                    nick = email.substring(email.indexOf("*") + 1);
                    email = email.substring(0, email.indexOf("*"));
                }
                email = email.substring(0, email.lastIndexOf("@"));
                PropertiesConfiguration conf = null;
                try {
                    conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/ids.ini"));
                } catch(Exception ex) {}
                if(conf != null) {
                    String p = conf.getString(originanlemail);
                    if(StringUtils.isNotEmpty(p)) {
                        pwd = p;
                    }
                }

//                HttpClient client = new DefaultHttpClient();
                DefaultHttpClient client = new DefaultHttpClient(cm[next()], param);
                DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0,false);
                client.setHttpRequestRetryHandler(handler);
                client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
//                HttpClient client = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
                client.getParams().setParameter("http.protocol.single-cookie-header", true);
                client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
                client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
                client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, false); //禁用重定向

                HttpPost pm = new HttpPost("http://qq.ogzq.xdgame.cn:80/iframePage/Loginiframe.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Login_1", email + "*" + pwd));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                CloseableHttpResponse response = client.execute(pm);
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

                HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/Transition.aspx?S=7&TP=QQ");
                response = (CloseableHttpResponse) client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                if(ret.indexOf("请您先登陆") >= 0) {
                    System.out.println("fuck，又要重登录了");
                    continue;
                }
                ret = ret.substring(ret.indexOf("<a href=\"") + "<a href=\"".length());
                ret = ret.substring(0, ret.indexOf("\""));
                ret = ret.replaceAll("&amp;", "&");

                gm = new HttpGet(ret);
                response = (CloseableHttpResponse) client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                String redirect = ret;
                if(ret.indexOf("<a href=\"") > 0) {
                    ret = ret.substring(ret.indexOf("<a href=\"") + "<a href=\"".length());
                    ret = ret.substring(0, ret.indexOf("\""));
                    ret = ret.replaceAll("&amp;", "&");
                    redirect = ret;
                    gm = new HttpGet(ret);
                    response = (CloseableHttpResponse) client.execute(gm);
                    ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                    response.getEntity().getContent().close();
                    response.close();
                    gm.releaseConnection();
                }

                pm = new HttpPost("http://f7.ogzq.xdgame.cn:80/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse) client.execute(pm);
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

                gm = new HttpGet(redirect + "&ai=" + curId);
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

//                String url = "http://f7.ogzq.xdgame.cn/Loading.aspx?S=8&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0";
                gm = new HttpGet("http://f7.ogzq.xdgame.cn/Default.aspx");
                response = (CloseableHttpResponse)client.execute(gm);
                ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();
                ret = ret.substring(ret.indexOf("HiddenFieldMyClubID") + "HiddenFieldMyClubID".length());
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String clubId = ret.substring(0, ret.indexOf("\""));
                ret = ret.substring(ret.indexOf("value=\"") + "value=\"".length());
                String accountId = ret.substring(0, ret.indexOf("\""));
                IDUtils.IDObjIds.put(originanlemail, new String[]{clubId, accountId});

                return client;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private static int next() {
        nextCM++;
        if(nextCM >= cm.length) {
            nextCM = 0;
        }
        return nextCM;
    }
}