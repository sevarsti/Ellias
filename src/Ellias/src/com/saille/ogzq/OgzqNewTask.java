package com.saille.ogzq;

import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import java.text.DecimalFormat;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.InputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-8-19
 * Time: 20:52:07
 * To change this template use File | Settings | File Templates.
 */
public class OgzqNewTask {
    private static boolean dotry = false;
    private static String pwd = "lspmgk";
    public static void main(String[] args) {
        try {
            OgzqURL.URL = "http://f13.ogzq.xdgame.cn";
            DecimalFormat df = new DecimalFormat("00000");
//            dotask("jiong0499");
//            File f = new File("D:\\list.txt");
//            FileReader fr = new FileReader(f);
//            BufferedReader br = new BufferedReader(fr);
//            String tmp;
//            for(int i = 0; i < 48; i++) {
//                br.readLine();
//            }
            for(int i = 1285; i <=2000; i++) {//
//                tmp = br.readLine();
//                System.out.println(i);
//                String nick =
                String email = "yun" + df.format(i);
                String nick = "云" + df.format(i) + generateRandom(5);
                HttpClient client = register(email, nick);
                System.out.println("注册完毕");
                dotask(email);
            }
//            if(dotry) {
//            } else {
//                DecimalFormat df = new DecimalFormat("00000");
//                for(int i = 1; i < 500; i++) {
//                    System.out.println("i = " + i);
//                    dotask("jiong" + df.format(i + 1));
//                }
//            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static HttpClient register(String email, String nick) throws Exception {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
        PoolingClientConnectionManager c = new PoolingClientConnectionManager(schemeRegistry);
        HttpParams param = new BasicHttpParams();
        HttpClient client = new DefaultHttpClient(c, param);

        HttpPost pm = new HttpPost("http://www.xdgame.cn/Register?id=2");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("username", email));
        params.add(new BasicNameValuePair("password", pwd));
        params.add(new BasicNameValuePair("passwordT", pwd));
        params.add(new BasicNameValuePair("realname", "张三"));
        params.add(new BasicNameValuePair("userID", "440106198202020555"));
        params.add(new BasicNameValuePair("checkbox", "checkbox"));
        params.add(new BasicNameValuePair("fromUid", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = (CloseableHttpResponse)client.execute(pm);
//        System.out.println(CommonUtils.getString(response.getEntity().getContent(), "gbk"));

        while(true) {
            pm = new HttpPost("http://ogzq.xdgame.cn/iframePage/Loginiframe.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Login_1", email + "*" + pwd));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse) client.execute(pm);
            String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            String u = ret.substring(0, ret.indexOf("*"));

            HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/Transition.aspx?S=13");
            response = (CloseableHttpResponse)client.execute(gm);
            ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//            String t = ret.substring(ret.indexOf("&T=") + 3);
//            t = t.substring(0, t.indexOf("&"));
//            String m = ret.substring(ret.indexOf("&M=") + 3);
//            m = m.substring(0, m.indexOf("&"));

            pm = new HttpPost("http://f13.ogzq.xdgame.cn/CreateClubInfo.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("STeam1", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
//        System.out.println(CommonUtils.getString(response.getEntity().getContent(), "utf-8"));

            pm = new HttpPost("http://f13.ogzq.xdgame.cn/CreateClubInfo2.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("CreateLoad1", ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            String s = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            s = s + (char)(new Random().nextInt(26) + 65);
            System.out.println(s);

            pm = new HttpPost("http://f13.ogzq.xdgame.cn/CreateClubInfo2.aspx");
            params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("CreateClub1", s + "*2"));
            params.add(new BasicNameValuePair("CreateClub1", nick + "*2"));
            System.out.println("昵称：" + nick);
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            response = (CloseableHttpResponse)client.execute(pm);
            String ok = (CommonUtils.getString(response.getEntity().getContent(), "utf-8"));

            if("0".equals(ok)) {
                System.out.println("昵称不通过");
            } else {
                break;
            }
        }
        return client;
    }

    public static void dotask(String email) throws Exception {
        HttpClient client = Login(email, pwd);
        IDUtils.IDS.put(email, client);
        String task = "";
        while(!task.equals("asdf")) {
            HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn/Default.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "3"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(client, email, pm);
            task = ret.split("[*]")[0];
            System.out.println(email + "任务：" + task);
            if(task.equals("1001")) { //巡回赛 //1001*1*1001*0
                do1001(client, email);
            } else if(task.equals("5998")) { //领取任务
                List<Map<String, String>> list = taskInfo(email);
//                List<Map<String, String>> list = IDUtils.IDTaskInfos.get(email);
                for(Map<String, String> m : list) {
                    if(m.containsKey("finished") && StringUtils.isNotEmpty(m.get("finished")) && "主线".equals(m.get("type"))) {
                        finishTask(email, m.get("id"));
                    }
                }
            } else if(task.equals("1002")) { //搜索球员
                do1002(client, email);
            } else if(task.equals("1003")) { //变阵
                do1003(client, email);
            } else if(task.equals("1006")) { //训练赛
                do1006(client, email);
            } else if(task.equals("1005")) { //开启球员训练
                do1005(client, email);
//            } else if(task.equals("1004")) { //开启球员训练
            } else { //开启球员训练
                break;
//                do1004(client, email);
            }
            Thread.sleep(1000);
        }
        System.out.println(email + "新手任务完成");
        IDUtils.IDS.clear();
        IDUtils.IDObjIds.clear();
        IDUtils.IDInfos.clear();
        IDUtils.IDTaskInfos.clear();
    }

    public static void do1001(HttpClient client, String email) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn/Default.aspx");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "6"));
        params.add(new BasicNameValuePair("intTaskIndex", "1001"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(client, email, pm);
        OperationUtils.doChallenge(email); //12345@
//        if(ret.indexOf("@") >= 0) {
//            String matchId = ret.substring(0, ret.indexOf("@"));
//
//            Thread.sleep(1000);
//            pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
//            params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("ImaEndMatchID", matchId));
//            params.add(new BasicNameValuePair("ImaMatchCategory", "1"));
//            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            IDUtils.execute(client, email, pm);
//        }
//        System.out.println(ret);
    }

    public static void do1002(HttpClient client, String email) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MIDDLE_MAN);
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loadPower", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MIDDLE_MAN);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("beforeFindPlayer", "1"));
        params.add(new BasicNameValuePair("LeagueIndex", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MIDDLE_MAN);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("findPlayer", "1"));
        params.add(new BasicNameValuePair("LeagueIndex", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(client, email, pm);
        System.out.println("开始搜索：" + s);

        Thread.sleep(4000); //等待返回
        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MIDDLE_MAN);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getAndShowFreshMan", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm); //1857876|格里希丁|1|69|2|France/03/10313.png|
        System.out.println("搜索结果：" + ret);
        String id = ret.split("[|]")[0];
//
        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MIDDLE_MAN); //签约
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("doDeal", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        System.out.println("签约结果：" + ret);
        if("1".equals(ret)) {
            pm = new HttpPost("http://f13.ogzq.xdgame.cn/Default.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "6"));
            params.add(new BasicNameValuePair("intTaskIndex", "1002"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(client, email, pm);
        }
    }

    public static void do1003(HttpClient client, String email) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.TACTICAL);
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(client, email, pm);
        //55∠1∠France/01/10101.png∠皮奥尼耶∠1∠1864547∠a∠1186∟51∠2∠France/02/10213.png∠米尼奥∠1∠1864548∠b∠1187∟52∠2∠France/05/10515.png∠当泽∠1∠1864549∠c∠1188∟52∠2∠France/05/10503.png∠卡特里纳∠1∠1864550∠d∠1189∟52∠2∠France/05/10511.png∠马万加∠1∠1864551∠e∠1190∟57∠3∠France/08/10808.png∠福法纳∠1∠1864552∠f∠1191∟74∠3∠Italy/09/30907.png∠斯坦科维奇∠4∠1864553∠g∠1192∟57∠3∠France/03/10308.png∠科索科∠1∠1864554∠h∠1193∟56∠3∠France/01/10114.png∠拉孔布∠1∠1864555∠i∠1194∟47∠4∠France/03/10315.png∠郑祖国∠1∠1864556∠j∠1195∟49∠4∠France/06/10615.png∠古尔勒∠1∠1864557∠k∠1196∟69∠2∠France/10/11004.png∠谢朱∠1∠1864559∠z∠11004⊥2⊥602<a style='color:#973e18'></a>⊥3⊥-1∠-1∠-1⊥3⊥0*0|0|0*-1*0% / 0%⊥0⊥0⊥0^0^0
        ret = ret.substring(0, ret.indexOf("⊥"));
        String[] players = ret.split("∟");
        StringBuffer tacticParams = new StringBuffer();
        for(int i = 0; i < 12; i++) {
            tacticParams.append("*").append(players[i].split("∠")[5]);
        }

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/Tactical.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("playerIDList", tacticParams.substring(1)));
        params.add(new BasicNameValuePair("tacticalIndex", "2"));
        params.add(new BasicNameValuePair("pLM5", UtilFunctions.md5(tacticParams.substring(1) + IDUtils.IDObjIds.get(email)[0] + IDUtils.IDObjIds.get(email)[1])));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(client, email, pm);
    }

    public static void do1006(HttpClient client, String email) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/MatchList/TrainMatch/TrainMatch2.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("TrainMatchload1", "7*0*0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(client, email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        params.add(new BasicNameValuePair("lli", "-1"));
        params.add(new BasicNameValuePair("intRoomIndex", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GetPowerCount", "1"));
        params.add(new BasicNameValuePair("pt", "7"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String powerCount = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.TEAM_PLAY);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getB", "7"));
        params.add(new BasicNameValuePair("title", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String currentCount = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("leagueIndex", "1"));
        params.add(new BasicNameValuePair("pageIndex", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        ret = ret.substring(0, ret.indexOf("@"));
        String[] teams = ret.split("&");
        String matchId = teams[0].split("[|]")[0];

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("CheckInsertMatch", matchId));
        params.add(new BasicNameValuePair("li", "1"));
        params.add(new BasicNameValuePair("npci", "0"));
        params.add(new BasicNameValuePair("pi", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", matchId));
        params.add(new BasicNameValuePair("li", "1"));
        params.add(new BasicNameValuePair("npci", "0"));
        params.add(new BasicNameValuePair("pi", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(client, email, pm);
        System.out.println("踢普通训练赛：" + ret);

        Thread.sleep(1000);
        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/MatchEngine.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ImaEndMatchID", matchId));
        params.add(new BasicNameValuePair("ImaMatchCategory", "7"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(client, email, pm);

        Thread.sleep(500);
        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.MATCH_TRAINING); //领取奖励
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getAward", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn/Default.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "6"));
        params.add(new BasicNameValuePair("intTaskIndex", "1006"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(client, email, pm);
    }

    public static void do1005(HttpClient client, String email) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/Training.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(client, email, pm);
        //1500^1864755*皮奥尼耶*1*1*52~33/100*1*55*3*0*0*0*0*0*0*********-1**********************|1864756*米尼奥*1*1*52~33/100*2*51*3*0*0*0*0*0*0*********-1**********************|1864757*当泽*1*1*52~33/100*2*52*3*0*0*0*0*0*0*********-1**********************|1864759*卡特里纳*1*1*52~33/100*2*52*3*0*0*0*0*0*0*********-1**********************|1864760*马万加*1*1*52~33/100*2*52*3*0*0*0*0*0*0*********-1**********************|1864761*福法纳*1*1*52~33/100*3*57*3*0*0*0*0*0*0*********-1**********************|1864762*斯坦科维奇*1*4*21~33/250*3*74*3*0*0*0*0*0*0*********-1**********************|1864763*科索科*1*1*52~33/100*3*57*3*0*0*0*0*0*0*********-1**********************|1864764*拉孔布*1*1*52~33/100*3*56*3*0*0*0*0*0*0*********-1**********************|1864768*卢乔·冈萨雷斯*1*1*0~0/100*3*66*4*0*0*0*0*0*0*********-1**********************|1864765*郑祖国*1*1*52~33/100*4*47*3*0*0*0*0*0*0*********-1**********************|1864766*古尔勒*1*1*52~33/100*4*49*3*0*0*0*0*0*0*********-1**********************
        s = s.substring(s.indexOf("^") + 1);
        String playerId = s.substring(0, s.indexOf("*"));

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/TeamAndPlayer/Player.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadPlayer1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(client, email, pm);
        //France/01/10101.png*门将*法国**184*皮奥尼耶*1*55**39*39*47*50*50*49*52*54*HXBECTYSSTZ*76**1864755*24*24*29*31*31*31*32*34*0*0*普通*s1.png*3*40C42C*39/46*39/46*47/71*50/80*50/80*49/78*52/87*54/92*52*33/100*46*46*71*80*80*78*87*92*1500*540203*540203*0*divZDshengji_grey*29*29*44*50*50*49*54*58*0*0*1700n*1600n*1800n*1500n**0****0****0****0************1*0*0*0*1186*-1*-1*0*0*********0*0*-1****4200l*0******************5**0*0@*0@*0@*0@*0@*0*0
        String[] parts = s.split("[*]");
        String p = playerId;
        for(int i = 8; i < 16; i++) {
            p += "*" + parts[i];
        }

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + "/Player.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpPlayer1", p));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(client, email, pm);

        pm = new HttpPost("http://f13.ogzq.xdgame.cn/Default.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "6"));
        params.add(new BasicNameValuePair("intTaskIndex", "1005"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(client, email, pm);
    }

    public static String generateRandom(int count) {
        String ret = "";
        Random r = new Random();
        for(int i = 0; i < count; i++) {
            int c = r.nextInt(26) + 65;
            ret += (char) c;
        }
        return ret;
    }

    public static List taskInfo(String email) throws Exception {
        HttpPost pm = null;

        pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.TASK);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(email, pm);
        String[] tasks = rett.split("[|]");
        List idtask = new ArrayList();
        for(int i = 0; i <= 2; i++) {
            if(tasks[i].equals("")) {
                continue;
            }
            String[] subs = tasks[i].split("∠");
            for(String sub : subs) {
                Map t = new Hashtable();
                if(i == 0) {
                    t.put("type", "主线");
                } else if(i == 1) {
                    t.put("type", "支线");
                } else if(i == 2) {
                    t.put("type", "日常");
                }

                String[] blocks = sub.split("[*]");
                t.put("id", blocks[0]);
                t.put("desc", blocks[2]);
                t.put("status", blocks[9]);
                t.put("finished", blocks[6].equals("1") ? "是" : "");
                idtask.add(t);
            }
        }
        return idtask;
//        IDUtils.IDTaskInfos.put(email, idtask);
    }

    public static void finishTask(String email, String id) throws Exception {
        HttpPost pm = new HttpPost("http://f13.ogzq.xdgame.cn" + OgzqURL.TASK);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("taskIndex", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
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
                DefaultHttpClient client = new DefaultHttpClient();
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

                HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/Transition.aspx?S=13");
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

                gm = new HttpGet("http://f13.ogzq.xdgame.cn:80/ChooseRole.aspx?S=13&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0");
                response = (CloseableHttpResponse)client.execute(gm);
                response.getEntity().getContent().close();
                response.close();
                gm.releaseConnection();

                pm = new HttpPost("http://f13.ogzq.xdgame.cn:80/ChooseRole.aspx");
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

                pm = new HttpPost("http://f13.ogzq.xdgame.cn/ChooseRole.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                params.add(new BasicNameValuePair("ai", curId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                response = (CloseableHttpResponse)client.execute(pm);
                response.getEntity().getContent().close();
                response.close();
                pm.releaseConnection();

//                String url = "http://f13.ogzq.xdgame.cn/Loading.aspx?S=8&U=" + u + "&T=" + t + "&M=" + m + "&UM=" + email + "&UT=0";
                gm = new HttpGet("http://f13.ogzq.xdgame.cn/Default.aspx");
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
//            return LoginHupu(id, pwd, s);
        } else if(server.startsWith("e7wan")) {
//            return LoginE7Wan(id, pwd);
        } else if(server.startsWith("fenghuang")) {
//            return LoginFenghuang(id, pwd);
        } else if(server.equals("qq")) {
//            return LoginQq(id, pwd);
        } else {
            return LoginXDGame(id, pwd);
        }
        return null;
    }
}
