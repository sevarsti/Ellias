package com.saille.ogzq;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

import com.saille.util.UtilFunctions;
import com.saille.util.CommonUtils;
import com.saille.util.SortUtils;
import com.saille.ogzq.dailyLoop.SellJiqingThread;
import com.saille.ogzq.dailyLoop.SearchPlayerThread;
import com.saille.ogzq.dailyLoop.TrainingmatchThread;

public class OperationUtils {
    private static final Logger LOGGER = Logger.getLogger(OperationUtils.class);

    public static void defaults(String email, int type) throws Exception {
//        LOGGER.info(email + ": 查询基本资料");
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + "/Default.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", String.valueOf(type)));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(email, pm);

        if(type == 7) {
            String[] subs = rett.split("[|]");

            if(!IDUtils.IDInfos.containsKey(email)) {
                IDUtils.IDInfos.put(email, new Hashtable());
            }
            Map infos = (Map) IDUtils.IDInfos.get(email);
            if(!"".equals(subs[10])) {
                String club = subs[10].substring(0, subs[10].indexOf("]"));
                club = club.substring(club.indexOf("[") + 1);
                infos.put("club", club);
            }
            infos.put("nick", subs[0]);
            infos.put("gold", subs[2]);
            infos.put("silver", subs[3]);
            infos.put("level", subs[4]);
            infos.put("exp", subs[5]);
            infos.put("nextlevel", subs[6]);
            infos.put("shili", subs[7]);
            String club = subs[10];
            if(club.indexOf("[") > 0) {
                club = club.substring(club.indexOf("[") + 1);
                club = club.substring(0, club.indexOf("]"));
                infos.put("club", club);
            }
            infos.put("shengwang", subs[9]);
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loadPower", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            rett = IDUtils.execute(email, pm);
            String search = rett.substring(0, rett.indexOf("|")) + "/";
            rett = rett.substring(rett.indexOf("|") + 1);
            search = search + rett.substring(0, rett.indexOf("|"));
//            try {
//                LoopUtils.getInstance().addEvent(email, LoopUtils.DOSEARCHPLAYER, new Date().getTime() + Integer.parseInt(rett.split("[|]")[3]) * 1000 + 2000, 600 * 1000);
//            } catch(Exception ex) {}
            infos.put("search", search);
        }

        Calendar c = Calendar.getInstance();
        int weekday = c.get(Calendar.DAY_OF_WEEK);
//        if(weekday >= Calendar.MONDAY && weekday <= Calendar.FRIDAY) {
            int hour = c.get(Calendar.HOUR_OF_DAY);
        if(email.startsWith("sevarsti")) {
            if(hour >= 12 && hour < 14) {
                OperationUtils.TrainingBase2(email);
            } else {
                if(Integer.parseInt(IDUtils.IDInfos.get(email).get("shili")) < 40000) {
                    changeTactic(email, 1);
                }
            }
        } else if((hour >= 20 && hour < 22) || (hour >= 12 && hour < 14)) {
                OperationUtils.TrainingBase2(email);
            }
        int time = Integer.parseInt(new SimpleDateFormat("HHmm").format(c.getTime()));
//        if(Integer.parseInt(IDUtils.IDInfos.get(email).get("level")) > 60 && time > 600) {
//        if(Integer.parseInt(IDUtils.IDInfos.get(email).get("level")) > 60) {
            doOgzd(email);
//        }

        if(email.startsWith("sevarsti") || email.indexOf("leonis") >= 0) {
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && (time >= 958 && time <= 1010)) {
                OperationUtils.doSellDaily(email);
            } else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && time <= 30) {
                OperationUtils.doSellDaily(email);
            }
        }
        if(email.startsWith("blue") || email.startsWith("orange") || email.startsWith("bixi") || email.startsWith("robot")) {
            if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY && (time >= 958 && time <= 1010)) {
                if(email.startsWith("robot")) {
                    OperationUtils.doSellDaily(email);
                }
                doSellLv5(email);
            } else if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY && time <= 30) {
                if(email.startsWith("robot")) {
                    OperationUtils.doSellDaily(email);
                }
                doSellLv5(email);
            }
        }
//        dailySignin(email);
        leagueSignin(email);
    }

    public static void finishTask(String email, String id) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("taskIndex", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
    }

    public static void taskInfo(String email) throws Exception {
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
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
        IDUtils.IDTaskInfos.put(email, idtask);
    }

    public static void queryTask(String email) throws Exception {
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "3"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(email, pm);

        String xunhuisai = rett.substring(0, rett.indexOf("|"));
        Map tasks = new Hashtable();
        tasks.put("xunhuisai", xunhuisai);
        rett = rett.substring(rett.indexOf("^") + 1);
        String xunliansai = rett.substring(0, rett.indexOf("^"));
        tasks.put("xunliansai", xunliansai);
        rett = rett.substring(rett.indexOf("^") + 1);
        String arena = rett.substring(0, rett.indexOf("^"));
        tasks.put("arena", arena);
        rett = rett.substring(rett.indexOf("^") + 1);
        String league = rett.substring(0, rett.indexOf("^"));
        tasks.put("league", league);
        if(league.equals("重新报名")) {
            leagueSignup(email);
        }
        IDUtils.IDTasks.put(email, tasks);
    }

    public static String doTrainingMatch(String email) throws Exception {
        return TrainingmatchThread.doTrainingMatch(email);
    }

    public static String doOgTrainingMatch(String email) throws Exception {
        return TrainingmatchThread.doOgTrainingMatch(email);
    }

    public static List<Map<String, String>> middleman(String email) throws Exception {
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getPlayerHole", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);

        String[] sub1 = ret.split("&");
        String maxSize = sub1[0];
        String currentSize = sub1[1];
        String[] players = sub1[2].split("[*]");
        List retPlayers = new ArrayList();
        if(!sub1[2].equals("-1")) {
            for(String player : players) {
                String[] sub2 = player.split("[|]");
                Map retPlayer = new Hashtable();
                retPlayer.put("id", sub2[0]);
                retPlayer.put("name", sub2[1]);
                retPlayer.put("level", sub2[2]);
                retPlayer.put("value", sub2[3]);
                retPlayer.put("place", sub2[4]);
                retPlayer.put("lefttime", String.valueOf(Integer.parseInt(sub2[6]) / 60 / 60));
                retPlayers.add(retPlayer);
            }
        }
        return retPlayers;
    }

    /* 搜索球员
    * -1:未知错误
    * >0: 搜索的时间*/
    public static int findPlayer(String email) throws Exception {
        return SearchPlayerThread.findPlayer(email);
    }

    public static void afterFindPlayer(String email) throws Exception {
        SearchPlayerThread.afterFindPlayer(email);
    }

    public static void dropPlayer(String email, String id) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("gp", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
    }

    public static String useCoachItem(String email, String coachIndex, String itemCode) throws Exception {
        //1=milu, 2=hiddink, 3=lippi, 4=guadiora, 5=ferguson, 6=mourinho
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "14"));
        params.add(new BasicNameValuePair("TrainManId", coachIndex));
        params.add(new BasicNameValuePair("ItemCode", itemCode));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        return str;
    }

    public static String combineCoach(String email, String itemId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        params.add(new BasicNameValuePair("prop1", itemId));
        params.add(new BasicNameValuePair("propCount", "3"));
        params.add(new BasicNameValuePair("isInsure", "0"));
        params.add(new BasicNameValuePair("iscoin", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    public static String coachLevelUp(String email, String coachIndex) throws Exception {
        //idx = idx - 1, begin with 0   1=milu, 2=hiddink, 3=lippi, 4=guadiora, 5=ferguson, 6=mourinho
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Upgrade1", coachIndex));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        defaults(email, 7);
        return str;
    }

    public static String hireCoach(String email, String coachIndex) throws Exception {
        //idx = idx - 1, begin with 0   1=milu, 2=hiddink, 3=lippi, 4=guadiora, 5=ferguson, 6=mourinho
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("PinYongCoach1", coachIndex));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        return str;
    }

    public static List<Map<String, String>> listCoachBags(String email, String itemtype) throws Exception {
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("itemtype", itemtype));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        if(str.equals("-1")) {
            return ret;
        }
        
        str = str.split("@")[0];
        String[] bags = str.split("\\|");
        for(String b : bags) {
            Map<String, String> m = new HashMap<String, String>();
            String[] parts = b.split("\\*");
            m.put("itemId", parts[0]);
            m.put("name", parts[3]);
            m.put("count", parts[7]);
            m.put("canuse", parts[8]);
            m.put("itemCode", parts[11]);
            ret.add(m);
//      0itemId	3name	7count	8can use	9	11itemCode	12can combine	14	15	16	17
//      2051968*	神奇球靴1级*	67*	0*	0*	2101*	1*	*	*	*	*
//      2066740*	神奇球靴2级*	9*	0*	0*	2102*	1*	*	*	*	*
//      2189026*	神奇球靴3级*	34*	0*	0*	2103*	1*	*	*	*	*
//      2457490*	神奇球靴4级*	13*	0*	0*	2104*	1*	*	*	*	*
//      2891068*	神奇球靴6级*	15*	0*	0*	2106*	1*	*	*	*	*
//      2056486*	希式秒表1级*	33*	0*	0*	2201*	1*	*	*	*	*
//      2454364*	希式秒表2级*	17*	0*	0*	2202*	1*	*	*	*	*
        }
        ret = SortUtils.sortCoachBag(ret, 0, ret.size());
        return ret;
    }

    public static List<Map<String, String>> listBags(String email, String itemtype) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("itemtype", itemtype));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        String str = IDUtils.execute(email, pm);
        List ret = new ArrayList();
        if("1".equals(itemtype)) {
            String[] items = str.split("[|]");
            for(String item : items) {
                String[] atts = item.split("[*]");
                Map m = new Hashtable();
                m.put("email", email);
                m.put("id", atts[0]);
                m.put("place", atts[1]);
                m.put("name", atts[3]);
                m.put("number", atts[7]);
                m.put("itemid", atts[11]);
                ret.add(m);
            }
        } else if("0".equals(itemtype)) {
            String[] items = str.split("[|]");
            for(String item : items) {
                String[] atts = item.split("[*]");
                Map m = new Hashtable();
                m.put("email", email);
                m.put("id", atts[0]);
                m.put("place", atts[1]);
                m.put("name", atts[3]);
                m.put("number", atts[7]);
                m.put("itemid", atts[11]);
                ret.add(m);
            }
        }
        return ret;
    }

    public static void doSellLv5(String email) throws Exception {
        LOGGER.info(email + ": 卖lv5道具");
        List<Map<String, String>> bags = listBags(email, "1");
        for(Map<String, String> b : bags) {
            String itemId = b.get("itemid");
            if(itemId.equals("1305")) { //游艇
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "5"));
                params.add(new BasicNameValuePair("aii", b.get("id")));
                params.add(new BasicNameValuePair("propcount", "" + (Math.min(5, Integer.parseInt(b.get("number"))))));
                params.add(new BasicNameValuePair("propprice", "30"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                LOGGER.info(IDUtils.execute(email, pm));
                Thread.sleep(1000);
            } else if(itemId.equals("1405")) { //飞机
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "5"));
                params.add(new BasicNameValuePair("aii", b.get("id")));
                params.add(new BasicNameValuePair("propcount", "" + (Math.min(5, Integer.parseInt(b.get("number"))))));
                params.add(new BasicNameValuePair("propprice", "45"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                LOGGER.info(IDUtils.execute(email, pm));
                Thread.sleep(1000);
            } else if(itemId.equals("1205")) { //汽车
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "5"));
                params.add(new BasicNameValuePair("aii", b.get("id")));
                params.add(new BasicNameValuePair("propcount", "" + (Math.min(5, Integer.parseInt(b.get("number"))))));
                params.add(new BasicNameValuePair("propprice", "15"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                LOGGER.info(IDUtils.execute(email, pm));
                Thread.sleep(1000);
            }
        }
    }

    //robot系列卖道具包场
    public static void doSellDaily(String email) throws Exception {
        LOGGER.info(email + ": 卖包场道具");
        String item = "";
        String price = "";
        if(email.indexOf("0001") >= 0) { //游艇
            item = "1301";price = "2";
        } else if(email.indexOf("0002") >= 0) {
            item = "1302";price = "2";
        } else if(email.indexOf("0003") >= 0) {
            item = "1303";price = "5";
        } else if(email.indexOf("0004") >= 0) {
            item = "1304";price = "14";
        } else if(email.indexOf("0005") >= 0) { //飞机
            item = "1401";price = "2";
        } else if(email.indexOf("0006") >= 0) {
            item = "1402";price = "2";
        } else if(email.indexOf("leonis") >= 0) {
//        } else if(email.indexOf("0007") >= 0) {
            item = "1403";price = "6";
        } else if(email.indexOf("0008") >= 0) {
            item = "1404";price = "18";
        } else if(email.indexOf("0009") >= 0) { //车
            item = "1201";price = "2";
        } else if(email.indexOf("sevarsti") >= 0) {
//        } else if(email.indexOf("0010") >= 0) {
            item = "1202";price = "2";
//        } else if(email.indexOf("0011") >= 0) {
//            item = "1203";price = "3";
        } else if(email.indexOf("0011") >= 0) {
            item = "1204";price = "6";
        } else {
            return;
        }
        List<Map<String, String>> bags = listBags(email, "1");
        for(Map<String, String> b : bags) {
            String itemId = b.get("itemid");
            if(itemId.equals(item)) {
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "5"));
                params.add(new BasicNameValuePair("aii", b.get("id")));
                if(item.equals("1203")) {
                    params.add(new BasicNameValuePair("propcount", "30"));
                } else if(item.equals("1403")) {
                    params.add(new BasicNameValuePair("propcount", "30"));
                } else {
                    params.add(new BasicNameValuePair("propcount", "30"));
                }
                params.add(new BasicNameValuePair("propprice", price));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                LOGGER.info(email + "卖包场道具：" + IDUtils.execute(email, pm));
                Thread.sleep(1000);
            }
        }
    }

    public static String cheDan(String email, String id) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Prop/Trade.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Chedan1", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    public static List<Map<String, String>> viewSell(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRADE);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ItemTrade_Order1", "2*0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ss = IDUtils.execute(email, pm);
        String[] objs = ss.split("[|]");
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
        for(String s : objs) {
            if(s.indexOf("*") == -1) {
                continue;
            }
            String[] atts = s.split("[*]");
            Map<String, String> single = new Hashtable<String, String>();
            single.put("itemcode", atts[0]);
            single.put("maxcount", atts[4] + "/" + atts[3]);
            single.put("sellcount", atts[11]);
            single.put("price", atts[5]);
            single.put("lefttime", atts[12]);
            String sellId = atts[13].substring(atts[13].indexOf("'") + 1);
            sellId = sellId.substring(0, sellId.indexOf("'"));
            single.put("sellId", sellId);
            ret.add(single);
        }
        return ret;
//        1305*31676*1*1*1*30*游艇5縿大牌球员追逐超级巨星的游艇，已不再是新闻?球员大厅，大牌球员签约道具?1305.png*0*0*23小时59分钟*objTrade.ShowCheDanBox('248464','1305')|
//        1205*31676*1*2*2*15*汽车5縿看这辆兰博基尼，你猜是哪位大牌球员的座驾?球员大厅，大牌球员签约道具?1205.png*1*0*23小时59分钟*objTrade.ShowCheDanBox('248463','1205')|
    }

    public static int doArena(String email) throws Exception {
        return com.saille.ogzq.dailyLoop.ArenaThread.doArena(email);
    }

    public static List<Map<String, String>> viewTeam(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        List ret = new ArrayList();

        String point = str.substring(0, str.indexOf("^"));
        str = str.substring(str.indexOf("^") + 1);
        Map pp = new Hashtable();
        pp.put("point", point);
        ret.add(pp);
        String[] players = str.split("[|]");
        for(String p : players) {
            Map att = new Hashtable();
            String[] sub = p.split("[*]");
            att.put("email", email);
            att.put("nick", IDUtils.getNick(email));
            att.put("id", sub[0]);
            att.put("name", sub[1]);
            att.put("level", sub[2]);
            att.put("pinzhi", sub[3]);
            att.put("exp", sub[4].substring(sub[4].indexOf("~") + 1));
            att.put("place", sub[5]);
            att.put("ability", sub[6]);
            att.put("tibu", sub[7].equals("3") ? "" : "替补");
            ret.add(att);
        }
        return ret;
    }

    public static Map<String, String> viewPlayer(String email, String id) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadPlayer1", id));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        String[] atts = str.split("\\*");
        Map ret = new Hashtable();
        ret.put("pos", atts[1]);
        ret.put("nationality", atts[2]);
        ret.put("height", atts[4]);
        ret.put("name", atts[5]);
        ret.put("level", atts[6]);
        ret.put("ability", atts[7]);
        ret.put("shemen", atts[9]);
        ret.put("tupo", atts[10]);
        ret.put("duanqiu", atts[11]);
        ret.put("chanqiu", atts[12]);
        ret.put("chuanqiu", atts[13]);
        ret.put("sudu", atts[14]);
        ret.put("pujiu", atts[15]);
        ret.put("chuji", atts[16]);
        ret.put("weight", atts[18]);
        ret.put("id", atts[20]);
//        ret.put("shemeno", atts[21]); 宽度
//        ret.put("tupoo", atts[22]);
//        ret.put("duanqiuo", atts[23]);
//        ret.put("chanqiuo", atts[24]);
//        ret.put("chuanqiuo", atts[25]);
//        ret.put("suduo", atts[26]);
//        ret.put("pujiuo", atts[27]);
//        ret.put("chujio", atts[28]);
        ret.put("pinzhi", atts[31]);
        ret.put("jingyan", atts[44]);
        ret.put("shemenm", atts[45]);
        ret.put("tupom", atts[46]);
        ret.put("duanqium", atts[47]);
        ret.put("chanqium", atts[48]);
        ret.put("chuanqium", atts[49]);
        ret.put("sudum", atts[50]);
        ret.put("pujium", atts[51]);
        ret.put("chujim", atts[52]);
        ret.put("xunliandian", atts[53]);
        ret.put("jiqing", atts[115]);
        ret.put("promotion", atts[103]);
        ret.put("qiuyi", atts[84]);
        if(Integer.parseInt(atts[85]) > 0) {
            ret.put("qiuyi1", atts[112]); //普通
            ret.put("qiuyi2", atts[113]); //额外
            ret.put("qiuyi3", atts[142].split("@")[0]); //宝石
        }
        ret.put("huxi", atts[76]);
        if(Integer.parseInt(atts[77]) > 0) {
            ret.put("huxi1", atts[108]); //普通
            ret.put("huxi2", atts[109]); //额外
            ret.put("huxi3", atts[144].split("@")[0]); //宝石
        }
        ret.put("qiuxie", atts[72]);
        if(Integer.parseInt(atts[73]) > 0) {
            ret.put("qiuxie1", atts[106]); //普通附加
            ret.put("qiuxie2", atts[107]); //额外附加
            ret.put("qiuxie3", atts[143].split("@")[0]); //宝石
        }
        ret.put("shoutao", atts[80]);
        if(Integer.parseInt(atts[81]) > 0) {
            ret.put("shoutao1", atts[110]); //普通附加
            ret.put("shoutao2", atts[111]); //额外附加
            ret.put("shoutao3", atts[145].split("@")[0]); //宝石
        }
        if(!"-1".equals(atts[116])) {
            ret.put("xiubiao", atts[117]);
            if(!"".equals(atts[118]) && Integer.parseInt(atts[118]) > 0) {
                ret.put("xiubiao1", atts[122]); //普通附加
                ret.put("xiubiao2", atts[123]); //额外附加
                ret.put("xiubiao3", atts[146].split("@")[0]); //宝石
            }
        } else {
            ret.put("xiubiao", "-1");
        }
        ret.put("tipcount", atts[67]); //不知道什么用，在保存球员训练信息时会传入
        ret.put("chengzhang", atts[148]); //球员成长次数
        ret.put("chengzhangjifen", atts[147]); //剩余球员成长积分
        ret.put("playerCode", atts[101]); //球员本身的代码
        ret.put("guojiaduizhilu", atts[103]);
        ret.put("shijiebeijifen", atts[105]);
        return ret;
    }

    public static void signupTeamGame(String email) throws Exception {
        LOGGER.info(email + ": 报名球会大作战");
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("checkSignup", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
    }

    public static void teamGame(String email, int type) throws Exception {
        if(!"1".equals(ConfigUtils.getConf(email, "是否踢球会大作战"))) {
            return;
        }
//        LOGGER.info(email + ": 球会大作战");
        Calendar c = Calendar.getInstance();
        String now = c.get(Calendar.HOUR_OF_DAY) + "";
        if(now.length() < 2) {
            now = "0" + now;
        }
        int minute = c.get(Calendar.MINUTE);
        now += minute > 9 ? minute : ("0" + minute);
        HttpPost pm;
        String start = "1200";
        String end = "2000";
        String confStart = ConfigUtils.getConf(email, "球会战开始");
        String confEnd = ConfigUtils.getConf(email, "球会战结束");
        if(confStart != null && confStart.compareTo(start) >= 0) {
            start = confStart;
        }
        if(confEnd != null && confEnd.compareTo(end) <= 0) {
            end = confEnd;
        }
        if(now.compareTo(start) < 0 || now.compareTo(end) > 0) {
            return;
        }
        String[] myplace = MonitorTeamgameThread.getInstance().myPlaces.get(email);
        if(myplace == null) { //没进场
            return;
        } else if(myplace[6].split("-")[1].equals("1")) { //在牌子位
            pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            params.add(new BasicNameValuePair("rank", myplace[6].split("-")[0]));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String sss = IDUtils.execute(email, pm);

            if(sss.indexOf("inmatch") != -1) {
                return;
            }
            String restDacanTime = sss.split("@")[7];
            if(Integer.parseInt(restDacanTime) > 0) {
                int placeInt = Integer.parseInt(myplace[6].split("-")[0]) * 10 + Integer.parseInt(myplace[6].split("-")[1]);
                for(int i = 1; i < 11; i++) {
                    int targetPlaceInt = placeInt + i;
                    String targetPlace = (targetPlaceInt / 10) + "-" + (targetPlaceInt % 10);
                    if(MonitorTeamgameThread.getInstance().places.get(targetPlace) != null) {
                        if(MonitorTeamgameThread.getInstance().places.get(targetPlace)[5].equals("11") ||
                                MonitorTeamgameThread.getInstance().places.get(targetPlace)[5].equals("12")) { //有礼包
                            if(MonitorTeamgameThread.getInstance().places.get(targetPlace)[7].equals("0")) { //对方没比赛
                                String targetPlayerId = MonitorTeamgameThread.getInstance().places.get(targetPlace)[0];
                                String ability = OgzqUtils.viewTeamAbility(targetPlayerId);
                                if(StringUtils.isNotEmpty(ability) && ability.indexOf("-") > 0) {
                                    ability = ability.substring(ability.indexOf("-") + 1);
                                }
                                String myAbility = IDUtils.IDInfos.get(email).get("shili");
                                if(Integer.parseInt(myAbility) - Integer.parseInt(ability) >= 10000) {
                                    //打比赛
                                    pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                                    params = new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("insertMatch", MonitorTeamgameThread.getInstance().places.get(targetPlace)[0]));
                                    params.add(new BasicNameValuePair("MatchPrice", "15"));
                                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    String s = IDUtils.execute(email, pm);
                                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "打残球会大作战：" + s);
                                    try {
                                        int id = Integer.parseInt(s);
                                        if(id > 0) {
                                            break;
                                        }
                                    } catch(Exception ex) {
                                        ex.printStackTrace();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } else { //不在牌子位，向前打礼包
            //检查是否在比赛
            pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            params.add(new BasicNameValuePair("rank", myplace[6].split("-")[0]));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String sss = IDUtils.execute(email, pm);

            if(sss.indexOf("inmatch") != -1) {
                return;
            }

            int placeInt = Integer.parseInt(myplace[6].split("-")[0]) * 10 + Integer.parseInt(myplace[6].split("-")[1]);
            for(int i = 1; i < 11; i++) {
                int targetPlaceInt = placeInt - i;
                String targetPlace = (targetPlaceInt / 10) + "-" + (targetPlaceInt % 10);
                if(MonitorTeamgameThread.getInstance().places.get(targetPlace) != null) {
                    if(MonitorTeamgameThread.getInstance().places.get(targetPlace)[5].equals("11") ||
                            MonitorTeamgameThread.getInstance().places.get(targetPlace)[5].equals("12")) { //有礼包
                        if(MonitorTeamgameThread.getInstance().places.get(targetPlace)[7].equals("0")) { //对方没比赛
                            String targetPlayerId = MonitorTeamgameThread.getInstance().places.get(targetPlace)[0];
                            String ability = OgzqUtils.viewTeamAbility(targetPlayerId);
                            if(StringUtils.isNotEmpty(ability) && ability.indexOf("-") > 0) {
                                ability = ability.substring(ability.indexOf("-") + 1);
                            }
                            String myAbility = IDUtils.IDInfos.get(email).get("shili");
                            if(Integer.parseInt(myAbility) - Integer.parseInt(ability) >= 10000) {
                                //打比赛
                                pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("insertMatch", MonitorTeamgameThread.getInstance().places.get(targetPlace)[0]));
                                params.add(new BasicNameValuePair("MatchPrice", "0"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String s = IDUtils.execute(email, pm);
                                LOGGER.info(email + "/" + IDUtils.getNick(email) + "打球会大作战：" + s);
                                try {
                                    int id = Integer.parseInt(s);
                                    if(id > 0) {
                                        break;
                                    }
                                } catch(Exception ex) {
                                    ex.printStackTrace();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static String doTeamGame(String email, String opponent, String matchPrice) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", opponent));
        params.add(new BasicNameValuePair("MatchPrice", matchPrice));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    public static String doOtherTeamGame(String email, String pwd, String opponent, String matchPrice) throws Exception {
        HttpClient client = LoginUtils.Login(email, pwd);
        if(client != null) {
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("insertMatch", opponent));
            params.add(new BasicNameValuePair("MatchPrice", matchPrice));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            return IDUtils.execute(client, email, pm);
        } else {
            return "登录失败";
        }
    }

    private static int getTeamGameCount(String pos, String in) {
//        todo
        return 0;
    }

    public static String[][] viewTeamInfo(String email, String teamId) throws Exception{
//缥缈过客#0#66#230#在线#2##npc30.png#
//3323014*1*诺伊尔*607*objplayer.PlayerMust(3323014)*3*objplayer.PlayerMust(3323014)*6*DF2920*+73*Item/1702.png*Item/1602.png*Item/1803.png*Item/1502.png*战靴2级*50*射门*突破*护腿2级*50*铲球*断球*手套3级*80*扑救*出击*战袍2级*50*速度*传球|
//2523160*2*T·席尔瓦*600*objplayer.PlayerMust(2523160)*3*objplayer.PlayerMust(2523160)*6*DF2920*+79*Item/1702.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴2级*50*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3859858*2*拉莫斯*599*objplayer.PlayerMust(3859858)*3*objplayer.PlayerMust(3859858)*6*DF2920*+88*Item/1703.png*Item/1603.png*Item/1803.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套3级*80*扑救*出击*战袍3级*80*速度*传球|
//4629886*2*★巴雷西*673*objplayer.PlayerMust(4629886)*3*objplayer.PlayerMust(4629886)*6*B924C5*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//1862646*3*  哈斯勒  *597*objplayer.PlayerMust(1862646)*3*objplayer.PlayerMust(1862646)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3297294*3*大卫·席尔瓦*608*objplayer.PlayerMust(3297294)*3*objplayer.PlayerMust(3297294)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3798960*3*施魏因斯泰格*627*objplayer.PlayerMust(3798960)*3*objplayer.PlayerMust(3798960)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3827372*3*缥缈过客*864*objplayer.PlayerMust(3827372)*3*objplayer.PlayerMust(3827372)*7*E08F00*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//4530780*3*西多夫*644*objplayer.PlayerMust(4530780)*3*objplayer.PlayerMust(4530780)*6*B924C5*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3942446*4*伊布*683*objplayer.PlayerMust(3942446)*3*objplayer.PlayerMust(3942446)*6*B924C5*+79*Item/1703.png*Item/1602.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿2级*50*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//4380093*4*★舍甫琴科*653*objplayer.PlayerMust(4380093)*3*objplayer.PlayerMust(4380093)*6*DF2920*+79*Item/1703.png*Item/1602.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿2级*50*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3519904*2*贝尔*606*objplayer.PlayerMust(3519904)*4*objplayer.PlayerMust(3519904)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//#1#-27#0##-113#-60##-47#-60##0#-60##-85#-60#10975#352.png#57342#武汉联盟#[武]#6##none

//缥缈过客#0#67#231#在线#2##npc30.png#3323014*1*诺伊尔*607*objplayer.PlayerMust(3323014)*3*objplayer.PlayerMust(3323014)*6*DF2920*+73*Item/1702.png*Item/1602.png*Item/1803.png*Item/1502.png*战靴2级*50*射门*突破*护腿2级*50*铲球*断球*手套3级*80*扑救*出击*战袍2级*50*速度*传球|
//2523160*2*T·席尔瓦*600*objplayer.PlayerMust(2523160)*3*objplayer.PlayerMust(2523160)*6*DF2920*+79*Item/1702.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴2级*50*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3519904*2*贝尔*606*objplayer.PlayerMust(3519904)*3*objplayer.PlayerMust(3519904)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3859858*2*拉莫斯*599*objplayer.PlayerMust(3859858)*3*objplayer.PlayerMust(3859858)*6*DF2920*+88*Item/1703.png*Item/1603.png*Item/1803.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套3级*80*扑救*出击*战袍3级*80*速度*传球|
//4629886*2*★巴雷西*673*objplayer.PlayerMust(4629886)*3*objplayer.PlayerMust(4629886)*6*B924C5*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//1862646*3*  哈斯勒  *597*objplayer.PlayerMust(1862646)*3*objplayer.PlayerMust(1862646)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3297294*3*大卫·席尔瓦*608*objplayer.PlayerMust(3297294)*3*objplayer.PlayerMust(3297294)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3798960*3*施魏因斯泰格*627*objplayer.PlayerMust(3798960)*3*objplayer.PlayerMust(3798960)*6*DF2920*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3827372*3*缥缈过客*865*objplayer.PlayerMust(3827372)*3*objplayer.PlayerMust(3827372)*7*E08F00*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//4530780*3*西多夫*645*objplayer.PlayerMust(4530780)*3*objplayer.PlayerMust(4530780)*6*B924C5*+85*Item/1703.png*Item/1603.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿3级*80*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//3942446*4*伊布*684*objplayer.PlayerMust(3942446)*3*objplayer.PlayerMust(3942446)*6*B924C5*+79*Item/1703.png*Item/1602.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿2级*50*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//4380093*4*★舍甫琴科*653*objplayer.PlayerMust(4380093)*4*objplayer.PlayerMust(4380093)*6*DF2920*+79*Item/1703.png*Item/1602.png*Item/1802.png*Item/1503.png*战靴3级*80*射门*突破*护腿2级*50*铲球*断球*手套2级*50*扑救*出击*战袍3级*80*速度*传球|
//#1#-27#0##-113#-60##-47#-60##0#-60##-85#-60#10979#352.png#58537#武汉联盟#[武]#6#4@6551|王者happy|npc40.png|6453|57|银宝箱X3*71398|超级米兰22|npc40.png|5334|50|银币1100*55563|LF金靴C罗|npc44.png|6858|50|战术积分150@0#none
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_TEAM);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadTeam1", teamId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String team =  IDUtils.execute(email, pm);
        System.out.println(team);
        team = team.substring(team.indexOf("png") + 4);
        String shili = team.substring(team.indexOf("#") + 1); //1#-27#0##-113#-60##-47#-60##0#-60##-85#-60#10975#352.png#57342#武汉联盟#[武]#6##none
        shili = shili.substring(0, shili.indexOf(".png")); //1#-27#0##-113#-60##-47#-60##0#-60##-85#-60#10975#352
        shili = shili.substring(0, shili.lastIndexOf("#")); //1#-27#0##-113#-60##-47#-60##0#-60##-85#-60#10975
        shili = shili.substring(shili.lastIndexOf("#") + 1); //10975
        team = team.substring(0, team.indexOf("#"));
        String[] players = team.split("[|]");
        String[][] ret = new String[12][];
        ret[0] = new String[]{shili};
        for(int i = 0; i < 11; i++) {
            String[] atts = players[i].split("[*]");
            String[] p = new String[2];
            p[0] = atts[2];
            p[1] = atts[1];
            p[2] = atts[3];
            ret[i+1] = p;
        }
        return ret;
    }

    public static void trainPlayer(String email, String str) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpPlayer1", str));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        CacheManager.loadPlayer(email, str.substring(0, str.indexOf("*")), true);
    }

    /**
     * 签约后直接解雇到战术中心
     */
    public static String signAndFirePlayer(String email, String playerId) throws Exception {
        if(isJiqinging(email)) {
            return "激情训练中";
        }
        List<String[]> needItems = beforeSignPlayer(email, playerId);
        if(needItems == null) {
            return "替补席满了";
        }
        List<Map<String, String>> currentItems = OperationUtils.listBags(email, "1");
        for(String[] i : needItems) {
            String code = i[0];
            String name = i[1];
            boolean found = false;
            for(Map<String, String> m : currentItems) {
                if(!m.get("itemid").equals(code)) {
                    continue;
                }
                found = true;
                break;
            }
            if(!found) {
                return "签约材料不够：" + code + "-" + name;
            }
        }

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("doDeal", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        CacheManager.loadPlayer(email, playerId, true);

        fireToTactic(email, playerId);
        return "done";
    }

    /**
     * 得到某个球员完美镶嵌的被镶嵌球员列表
     * @param email 号
     * @param playerName 球员姓名
     * @param level 球员品质
     * @return Object[2]: 0=null:可以镶嵌,Str:错误原因；1=镶嵌球员列表
     * @throws Exception
     */
    public static Object[] getXiangqianIds(String email, String playerName, int level) throws Exception {
        boolean needXiangqian = level >= 2;
        String[] xiangqianId = new String[level - 1];
        if(needXiangqian) {
            List<String[]> xiangqians = IDUtils.getXiangqianPlayer(email, 0);
            String[] targetName = new String[xiangqianId.length], targetShili = new String[xiangqianId.length];
            if(playerName.equals("芒当达")) {
                targetName[0] = "鲁菲耶";
                targetShili[0] = "160";
            } else if(playerName.equals("戈尼亚隆斯")) {
                targetName[0] = "谢朱";
                targetShili[0] = "180";
            } else if(playerName.equals("瓦尔布埃纳")) {
                targetName[0] = "福法纳";
                targetShili[0] = "173";
            } else if(playerName.equals("乌塔卡")) {
                targetName[0] = "博卡利";
                targetShili[0] = "169";
            } else if(playerName.equals("朗德洛")) {
                targetName[0] = "鲁菲耶";
                targetShili[0] = "160";
                targetName[1] = "芒当达";
                targetShili[1] = "264";
            } else if(playerName.equals("戈比")) {
                targetName[0] = "谢朱";
                targetShili[0] = "180";
                targetName[1] = "戈尼亚隆斯";
                targetShili[1] = "270";
            } else if(playerName.equals("卡列洪中")) {
                targetName[0] = "福法纳";
                targetShili[0] = "173";
                targetName[1] = "瓦尔布埃纳";
                targetShili[1] = "276";
            } else if(playerName.equals("卡列洪前")) {
                targetName[0] = "博卡利";
                targetShili[0] = "169";
                targetName[1] = "乌塔卡";
                targetShili[1] = "273";
            } else if(playerName.equals("什科琴尼")) {
                targetName[0] = "鲁菲耶";
                targetShili[0] = "160";
                targetName[1] = "芒当达";
                targetShili[1] = "264";
                targetName[2] = "朗德洛";
                targetShili[2] = "443";
            } else if(playerName.equals("阿巴特")) {
                targetName[0] = "福法纳";
                targetShili[0] = "173";
                targetName[1] = "瓦尔布埃纳";
                targetShili[1] = "276";
                targetName[2] = "卡列洪中";
                targetShili[2] = "440";
            } else if(playerName.equals("梅策尔德")) {
                targetName[0] = "谢朱";
                targetShili[0] = "180";
                targetName[1] = "戈尼亚隆斯";
                targetShili[1] = "270";
                targetName[2] = "戈比";
                targetShili[2] = "448";
            } else if(playerName.equals("贝尔巴托夫")) {
                targetName[0] = "博卡利";
                targetShili[0] = "169";
                targetName[1] = "乌塔卡";
                targetShili[1] = "273";
                targetName[2] = "卡列洪前";
                targetShili[2] = "445";
            } else if(playerName.equals("斯特克伦博格")) {
                targetName[0] = "鲁菲耶";
                targetShili[0] = "160";
                targetName[1] = "芒当达";
                targetShili[1] = "264";
                targetName[2] = "朗德洛";
                targetShili[2] = "443";
                targetName[3] = "什科琴尼";
                targetShili[3] = "823";
            } else if(playerName.equals("拉莫斯后")) {
                targetName[0] = "谢朱";
                targetShili[0] = "180";
                targetName[1] = "戈尼亚隆斯";
                targetShili[1] = "270";
                targetName[2] = "戈比";
                targetShili[2] = "448";
                targetName[3] = "梅策尔德";
                targetShili[3] = "832";
            } else if(playerName.equals("拉莫斯中")) {
                targetName[0] = "福法纳";
                targetShili[0] = "173";
                targetName[1] = "瓦尔布埃纳";
                targetShili[1] = "276";
                targetName[2] = "卡列洪中";
                targetShili[2] = "440";
                targetName[3] = "阿巴特";
                targetShili[3] = "807";
            } else if(playerName.equals("纳尼")) {
                targetName[0] = "博卡利";
                targetShili[0] = "169";
                targetName[1] = "乌塔卡";
                targetShili[1] = "273";
                targetName[2] = "卡列洪前";
                targetShili[2] = "445";
                targetName[3] = "贝尔巴托夫";
                targetShili[3] = "831";
            } else {
                return new Object[]{"找不到完美镶嵌球员清单: " + playerName, null};
            }

            for(int i = 0; i < xiangqianId.length; i++) {
                for(String[] xq : xiangqians) {
                    if(xq[1].equals(targetName[i]) && xq[3].equals(targetShili[i]) && xq[5].equals("1")) {
                        xiangqianId[i] = xq[0];
                        break;
                    }
                }
                if(xiangqianId[i] == null) {
                    return new Object[]{email + "的球员" + playerName + "没有可镶嵌的等级" + (i + 1) + "球员", null};
                }
            }
        }
        return new Object[]{null, xiangqianId};
    }

    /**
     * 签约后训满然后解雇到战术中心，默认是普通球员，吃500点
     */
    public static String signAndTrainAndFirePlayer(String email, String playerName, String playerId, int level) throws Exception {
        if(isJiqinging(email)) {
            return "激情训练中";
        }
        //如果不是优秀，先检查镶嵌
        boolean needXiangqian = level >= 2;
        Object[] objs = getXiangqianIds(email, playerName, level);
        if(objs[0] != null) {
            return objs[0].toString();
        }
        String[] xiangqianId = (String[]) objs[1];

        //签
        List<String[]> needItems = beforeSignPlayer(email, playerId);
        if(needItems == null) {
            return email + "替补席满了";
        }
        List<Map<String, String>> currentItems = OperationUtils.listBags(email, "1");
        for(String[] i : needItems) {
            String code = i[0];
            String name = i[1];
            boolean found = false;
            for(Map<String, String> m : currentItems) {
                if(!m.get("itemid").equals(code)) {
                    continue;
                }
                found = true;
                break;
            }
            if(!found) {
                return email + "签约材料不够：" + code + "-" + name;
            }
        }

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("doDeal", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        //升级
        String s = fullTrain(email, playerId, level);
        if(!s.equals("done")) {
            return email + s;
        }

        //镶嵌
        if(needXiangqian) {
            for(int i = 0; i < xiangqianId.length; i++) {
                String ret = xiangqian(email, playerId, xiangqianId[i]);
                if(!"1".equals(ret)) {
                    return email + "镶嵌失败";
                }
            }
        }

        //解
        fireToTactic(email, playerId);
        return "done";
    }

    public static void leagueSignup(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.LEAGUE);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("signup", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
    }

    public static List<String[]> beforeSignPlayer(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("showDeal", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        if(str.equals("-10")) { //替补席满了
            return null;
        }
        str = str.substring(str.indexOf("&") + 1);
        str = str.substring(str.indexOf("&") + 1);
        String[] items = str.split("[*]");
        List ret = new ArrayList();
        for(String item : items) {
            String[] subs = item.split("[|]");
            ret.add(new String[]{subs[0], subs[3]});
        }
        return ret;
    }

    public static void signPlayer(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("doDeal", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        CacheManager.loadPlayer(email, playerId, true);
    }

    public static void doChallenge(String email) throws Exception {
//        LOGGER.info(email + ": 巡回赛");

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);

        ret = ret.substring(0, ret.indexOf("@"));
        String[] leagues = ret.split("[|]");
        int leagueIndex = -1;
        int leagueSubIndex = -1;
        for(int i = 0; i < 5; i++) {
            if(Integer.parseInt(leagues[i]) < 10) {
                leagueIndex = i;
                leagueSubIndex = Integer.parseInt(leagues[i]);
                break;
            }
        }

        if(!IDUtils.IDInfos.containsKey(email)) {
            IDUtils.IDInfos.put(email, new Hashtable());
        }
        ((Map) IDUtils.IDInfos.get(email)).put("xunhuisai", leagueIndex + 1 + "-" + (leagueSubIndex + 1));

        if(leagueIndex != -1) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("initCards", "1"));
            params.add(new BasicNameValuePair("LeagueIndex", String.valueOf(leagueIndex + 1)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
            if(!ret.equals("-1")) {
                LOGGER.info(ret);
                if(ret.split("[*]")[0].equals("-1|-1^-1|-1^-1|-1^-1|-1")) {
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("OpenCard", "1"));
                    params.add(new BasicNameValuePair("cardindex", "2"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String ss = IDUtils.execute(email, pm);
                    LOGGER.info(ss);
                }

                pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getAward", "1"));
                params.add(new BasicNameValuePair("lod", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ss = IDUtils.execute(email, pm);
                LOGGER.info(ss);
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loadClubList", "1"));
            params.add(new BasicNameValuePair("LeagueIndex", String.valueOf(leagueIndex + 1)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);

            String inmatch = ret.substring(0, ret.indexOf("|"));
            if(inmatch.equals("1")) {
                return;
            }
            String[] clubs = ret.split("\\^");
            int clubIndex = -1;
            for(int j = 0; j < clubs.length; j++) {
                String[] subs = clubs[j].split("[|]");

                if(Integer.parseInt(subs[19]) == 0) {
                    clubIndex = j;
                    break;
                }
            }
            if(clubIndex != -1) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("insertMatch", String.valueOf(leagueIndex * 10 + clubIndex + 1)));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(email, pm);
            }
        }
    }

    public static void doTraining(String email) throws Exception {
//        LOGGER.info(email + ": 训练");

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("leagueIndex", "-1"));
        params.add(new BasicNameValuePair("pgIndex", "-1"));
        params.add(new BasicNameValuePair("isGetUserInfo", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);

        String placeId = "";
        int pgIndex = 0;
        int profit = 0;
        String leagueIndex = "";

        while(!str.equals("-5")) {
            String summary = str.substring(0, str.indexOf("^"));
            leagueIndex = summary.split("[|]")[5];
            str = str.substring(str.indexOf("^") + 1);
            String[] teams = str.split("[&]");
            for(String t : teams) {
                String[] atts = t.split("[*]");
                int p = Integer.parseInt(atts[1]);
                String status = atts[2];
                if(!status.equals("0")) {
                    continue;
                }
                if(p > profit) {
                    placeId = atts[0];
                    profit = p;
                }
            }
            if(!placeId.equals("")) {
                break;
            }
            pgIndex++;
            if(pgIndex >= 10) {
                pgIndex = 0;
            }
            pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("leagueIndex", leagueIndex));
            params.add(new BasicNameValuePair("pgIndex", String.valueOf(pgIndex)));
            params.add(new BasicNameValuePair("isGetUserInfo", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            str = IDUtils.execute(email, pm);
        }

        if(!placeId.equals("")) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            params.add(new BasicNameValuePair("baseIndex", placeId));
            params.add(new BasicNameValuePair("leagueIndex", leagueIndex));
            params.add(new BasicNameValuePair("pgIndex", String.valueOf(pgIndex)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
        }
    }

    public static void upgradePlayer(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Upgrade1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        trainFull(email, playerId, false);
    }

    public static void combine(String email, String itemId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        params.add(new BasicNameValuePair("prop1", itemId));
        params.add(new BasicNameValuePair("propCount", "5"));
        params.add(new BasicNameValuePair("isInsure", "0"));
        params.add(new BasicNameValuePair("iscoin", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
    }

    public static boolean autoTrainOnePoint(String email, Map<String, String> player) throws Exception {
//        Map detail = viewPlayer(email, (String) player.get("id"));
        Map<String, String> detail = CacheManager.loadPlayer(email, player.get("id"), false);
        String pos = (String) player.get("place");
        int[] pujiu = getAbility(detail, "pujiu");
        int[] chuji = getAbility(detail, "chuji");
        int[] duanqiu = getAbility(detail, "duanqiu");
        int[] chanqiu = getAbility(detail, "chanqiu");
        int[] chuanqiu = getAbility(detail, "chuanqiu");
        int[] sudu = getAbility(detail, "sudu");
        int[] shemen = getAbility(detail, "shemen");
        int[] tupo = getAbility(detail, "tupo");
        if(pos.equals("1")) {
            if(pujiu[0] < pujiu[1]) {
                pujiu[0] += 1;
            } else if(chuji[0] < chuji[1]) {
                chuji[0] += 1;
            } else if(chanqiu[0] < chanqiu[1]) {
                chanqiu[0] += 1;
            } else if(duanqiu[0] < duanqiu[1]) {
                duanqiu[0] += 1;
            } else if(chuanqiu[0] < chuanqiu[1]) {
                chuanqiu[0] += 1;
            } else if(sudu[0] < sudu[1]) {
                sudu[0] += 1;
            } else if(tupo[0] < tupo[1]) {
                tupo[0] += 1;
            } else if(shemen[0] < shemen[1]) {
                shemen[0] += 1;
            } else {
                return false;
            }
        } else if(pos.equals("2")) {
            if(chanqiu[0] < chanqiu[1]) {
                chanqiu[0] += 1;
            } else if(duanqiu[0] < duanqiu[1]) {
                duanqiu[0] += 1;
            } else if(sudu[0] < sudu[1]) {
                sudu[0] += 1;
            } else if(chuanqiu[0] < chuanqiu[1]) {
                chuanqiu[0] += 1;
            } else if(tupo[0] < tupo[1]) {
                tupo[0] += 1;
            } else if(shemen[0] < shemen[1]) {
                shemen[0] += 1;
            } else if(chuji[0] < chuji[1]) {
                chuji[0] += 1;
            } else if(pujiu[0] < pujiu[1]) {
                pujiu[0] += 1;
            } else {
                return false;
            }
        } else if(pos.equals("3")) {
            if(chuanqiu[0] < chuanqiu[1]) {
                chuanqiu[0] += 1;
            } else if(sudu[0] < sudu[1]) {
                sudu[0] += 1;
            } else if(chanqiu[0] < chanqiu[1]) {
                chanqiu[0] += 1;
            } else if(duanqiu[0] < duanqiu[1]) {
                duanqiu[0] += 1;
            } else if(tupo[0] < tupo[1]) {
                tupo[0] += 1;
            } else if(shemen[0] < shemen[1]) {
                shemen[0] += 1;
            } else if(pujiu[0] < pujiu[1]) {
                pujiu[0] += 1;
            } else if(chuji[0] < chuji[1]) {
                chuji[0] += 1;
            } else {
                return false;
            }
        } else if(pos.equals("4")) {
            if(shemen[0] < shemen[1]) {
                shemen[0] += 1;
            } else if(tupo[0] < tupo[1]) {
                tupo[0] += 1;
            } else if(sudu[0] < sudu[1]) {
                sudu[0] += 1;
            } else if(chuanqiu[0] < chuanqiu[1]) {
                chuanqiu[0] += 1;
            } else if(chanqiu[0] < chanqiu[1]) {
                chanqiu[0] += 1;
            } else if(duanqiu[0] < duanqiu[1]) {
                duanqiu[0] += 1;
            } else if(pujiu[0] < pujiu[1]) {
                pujiu[0] += 1;
            } else if(chuji[0] < chuji[1]) {
                chuji[0] += 1;
            } else {
                return false;
            }
        } else {
            return false;
        }
        String str = (String) player.get("id") + "*" + shemen[0] + "*" + tupo[0] + "*" + duanqiu[0] + "*" + chanqiu[0] + "*" + chuanqiu[0] + "*" + sudu[0] + "*" + pujiu[0] + "*" + chuji[0] + "*3";

        trainPlayer(email, str);
        return true;
    }

    public static String useItem(String email, String itemId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "7"));
        params.add(new BasicNameValuePair("aii", itemId));
        params.add(new BasicNameValuePair("isAll", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if("8101".equals(s) || "8103".equals(s) || "8104".equals(s)) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "17"));
            params.add(new BasicNameValuePair("aii", itemId));
            params.add(new BasicNameValuePair("eqItemCode", s));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            return s;
        } else {
            return s;
        }
    }

    public static int getTacticPoint(String email) throws Exception {
        if(Integer.parseInt(IDUtils.IDInfos.get(email).get("level")) < 40) {
            return 86400;
        }
//2994110|阿什利·扬|3 |England/10/41006.png|534|4|260|3|    0|0|1*
//3004542|德米凯利斯|2 |Spain/05/50503.png  |492|4|260|4|86179|0|1*
//3187394|巴内加    |3 |Spain/06/50608.png  |529|4|260|3|    0|0|1*
//0      |          |-1|                    | -1|0|  0|1|    0|0|1*
//0      |          |-1|                    | -1|0|  0|1|    0|0|1*
//0      |          |-1|                    | -1|0|  0|1|    0|0|1*
//0      |          |-1|                    | -1|0|  0|1|    0|0|1^
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTIC);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        s = s.substring(0, s.indexOf("^"));
        String[] players = s.split("[*]");
        boolean found = true;
        int ret = 86400;
//        while(found) {
//            found = false;
            for(int i = 0; i < players.length; i++) {
                String player = players[i];
                String[] atts = player.split("[|]");
                if(Integer.parseInt(atts[6]) > 0) {
                    if(Integer.parseInt(atts[9]) == 0) {
                        pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTIC);
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("Gather", "1"));
                        params.add(new BasicNameValuePair("gatheraward", atts[6]));
                        params.add(new BasicNameValuePair("checkbox", "0"));
                        params.add(new BasicNameValuePair("Gatherindex", String.valueOf(i)));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(email, pm);
                        found = true;
                    } else {
                        ret = Math.min(ret, Integer.parseInt(atts[9]));
                    }
                }
            }
//        }
        return ret;
    }

    private static boolean needDropPlayer(String email, String position, String level, String ability) {
        String config = ConfigUtils.getConf(email, "球员保留" + position);
        if(StringUtils.isEmpty(config)) {
            config = ConfigUtils.getConf(email, "球员保留");
        }
        if(StringUtils.isEmpty(config)) {
            return false;
        }
        int keepLevel = 0;
        int keepAbility = 0;
        if(config.indexOf("-") != -1) {
            String[] parts = config.split("-");
            keepLevel = Integer.parseInt(parts[0]);
            keepAbility = Integer.parseInt(parts[1]);
        } else {
            keepLevel = Integer.parseInt(config);
        }
        int playerLevel = Integer.parseInt(level);
        int playerAbility = Integer.parseInt(ability);
        if(playerLevel < keepLevel) {
            return true;
        }
        if(keepAbility == 0) {
            return false;
        }

        return playerAbility < keepAbility;
    }

    /* 中止激情训练 */
    public static String stopTrainingBase2(String email, String level) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "3"));
        params.add(new BasicNameValuePair("leagueIndex", level));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    /* 激情训练 */
    public static void TrainingBase2(String email) throws Exception {
        if(!"1".equals(ConfigUtils.getConf(email, "是否激情训练"))) {
            return;
        }
        String leagueLevel = getTrainingBaseLevel(email);
        if(leagueLevel == null) {
            return;
        }
        if("sevarsti@sina.com".equals(email)) {
            leagueLevel = "0";
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            IDUtils.execute(email, pm);
            String s = IDUtils.execute(email, pm);

            if(s.startsWith("-")) {
                if(Integer.parseInt(IDUtils.IDInfos.get(email).get("shili")) < 40000) {
                    changeTactic(email, 1);
                }
                return;
            }
            String restTime = s.substring(0, s.indexOf("@"));
            restTime = restTime.split("[|]")[5];
            if(Integer.parseInt(restTime) > 0 && s.substring(0, s.indexOf("@")).split("[|]")[6].equals("0")) {
                changeToBlueTeam(email, true);

                pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "1"));
                params.add(new BasicNameValuePair("baseIndex", "10"));
                params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
                params.add(new BasicNameValuePair("typestate", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(email, pm);
                pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "1"));
                params.add(new BasicNameValuePair("baseIndex", "10"));
                params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
                params.add(new BasicNameValuePair("typestate", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(email, pm);
                return;
            } else {
                if(Integer.parseInt(IDUtils.IDInfos.get(email).get("shili")) < 30000) {
                    changeTactic(email, 1);
                }
            }
        }

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        String s = IDUtils.execute(email, pm);

        if(s.startsWith("-")) {
            return;
        }
        String restTime = s.substring(0, s.indexOf("@"));
        restTime = restTime.split("[|]")[5];
        if(Integer.parseInt(restTime) > 0 && s.substring(0, s.indexOf("@")).split("[|]")[6].equals("0")) {
            s = s.substring(s.indexOf("@") + 1);
            String[] ss = s.split("[*]");
            for(String sss : ss) {
                String[] atts = sss.split("[|]");
                if(atts[9].equals("0") && StringUtils.isEmpty(atts[4])) {
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    params.add(new BasicNameValuePair("baseIndex", atts[1]));
                    params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
                    params.add(new BasicNameValuePair("typestate", "0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    IDUtils.execute(email, pm);
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAININGBASE2);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    params.add(new BasicNameValuePair("baseIndex", atts[1]));
                    params.add(new BasicNameValuePair("leagueIndex", leagueLevel));
                    params.add(new BasicNameValuePair("typestate", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    IDUtils.execute(email, pm);
                    break;
                }
            }
        }

//                type=1&baseIndex=10&leagueIndex=0&typestate=0


//        type=0&leagueIndex=2
//        training info:
//        3|0|0|0|0|1200|0|0|-1|0|30|-1|0@
//        0|0|0|300||||||1*
//        0|1|0|300||||||1*
//        0|2|0|300||||||0*
//        0|3|0|360||||||0*
//        0|4|0|300||||||0*
//        0|5|0|300||||||1*
//        0|6|0|300||||||1*
//        0|7|0|300||||||1*
//        0|8|0|300||||||1*
//        0|9|2|360|不亦乐乎|by|缥缈过客|黑|1|0*
//        0|10|0|450||||||0*
//        0|11|1|360|KM西蒙尼|婷|||2|0*
//        0|12|0|300||||||1*
//        0|13|0|300||||||1*
//        0|14|0|300||||||1*
//        0|15|0|300||||||1*
//        0|16|1|300|黑龙会小罗|黑|||6|0*
//        0|17|1|360|GK本泽马|GG|||4|0*
//        0|18|0|300||||||0*
//        0|19|0|300||||||1*
//        0|20|0|300||||||1

//        start train:
//        type=1&baseIndex=10&leagueIndex=2&typestate=1
    }

    //将sevarsti@sina.com转换为精英阵容
    public static void changeToBlueTeam(String email, boolean train) throws Exception {
        if(!train) {
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(email, pm);
            ret = ret.substring(ret.indexOf("^") + 1);
            String[] players = ret.split("[|]");

            List<List<String[]>> abilitys = new ArrayList<List<String[]>>();
            for(int i = 0; i < 4; i++) {
                abilitys.add(new ArrayList<String[]>());
            }

            for(String p : players) {
                String[] atts = p.split("[*]");
                int pos = Integer.parseInt(atts[5]);
                int abi = Integer.parseInt(atts[6]);
                String playerId = atts[0];
                boolean add = false;
                for(int i = 0; i < abilitys.get(pos - 1).size(); i++) {
                    if(abi > Integer.parseInt(abilitys.get(pos - 1).get(i)[1])) {
                        abilitys.get(pos - 1).add(i, new String[]{atts[0], atts[6]});
                        add = true;
                        break;
                    }
                }
                if(!add) {
                    abilitys.get(pos - 1).add(new String[]{atts[0], atts[6]});
                }
            }
            StringBuffer tacticParams = new StringBuffer();
            tacticParams.append("*").append(abilitys.get(0).get(0)[0]);
            abilitys.get(0).remove(0);
            for(int i = 0; i < 3; i++) {
                tacticParams.append("*").append(abilitys.get(1).get(0)[0]);
                abilitys.get(1).remove(0);
            }
            for(int i = 0; i < 5; i++) {
                tacticParams.append("*").append(abilitys.get(2).get(0)[0]);
                abilitys.get(2).remove(0);
            }
            for(int i = 0; i < 2; i++) {
                tacticParams.append("*").append(abilitys.get(3).get(0)[0]);
                abilitys.get(3).remove(0);
            }
            for(int i = 0; i < abilitys.get(0).size(); i++) {
                tacticParams.append("*").append(abilitys.get(0).get(i)[0]);
            }
            for(int i = 0; i < abilitys.get(1).size(); i++) {
                tacticParams.append("*").append(abilitys.get(1).get(i)[0]);
            }
            for(int i = 0; i < abilitys.get(2).size(); i++) {
                tacticParams.append("*").append(abilitys.get(2).get(i)[0]);
            }
            for(int i = 0; i < abilitys.get(3).size(); i++) {
                tacticParams.append("*").append(abilitys.get(3).get(i)[0]);
            }

            pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("playerIDList", tacticParams.substring(1)));
            params.add(new BasicNameValuePair("tacticalIndex", "1"));
            params.add(new BasicNameValuePair("pLM5", UtilFunctions.md5(tacticParams.substring(1) + IDUtils.IDObjIds.get(email)[0] + IDUtils.IDObjIds.get(email)[1])));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute("sevarsti@sina.com", pm);
            return;
        }
        List<Map<String, String>> list = OperationUtils.viewTeam("sevarsti@sina.com");
        list.remove(0);
        List<String> newOrder = new ArrayList<String>();
        while(list.size() > 0) {
            boolean add = false;
            for(Map<String, String> m : list) {
                if(m.get("place").equals("门将") && m.get("pinzhi").equals("3")) {
                    newOrder.add(m.get("id"));
                    list.remove(m);
                    add = true;
                    break;
                }
            }
            if(!add) {
                for(Map<String, String> m : list) {
                    if(m.get("pinzhi").equals("3")) {
                        newOrder.add(m.get("id"));
                        list.remove(m);
                        add = true;
                        break;
                    }
                }
            }
            if(!add) {
                for(int i = list.size() - 1; i >= 0; i--) {
                    newOrder.add(list.get(i).get("id"));
                    list.remove(i);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(String s : newOrder) {
            sb.append("*").append(s);
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("playerIDList", sb.substring(1)));
        params.add(new BasicNameValuePair("tacticalIndex", "1"));
        params.add(new BasicNameValuePair("pLM5", UtilFunctions.md5(sb.substring(1) + IDUtils.IDObjIds.get("sevarsti@sina.com")[0] + IDUtils.IDObjIds.get("sevarsti@sina.com")[1])));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute("sevarsti@sina.com", pm);
    }

    /* 获取进行激情训练的等级 */
    public static String getTrainingBaseLevel(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        if(str.indexOf("⊥") == -1) {
            return null;
        }
        str = str.substring(0, str.indexOf("⊥"));
        String[] players = str.split("∟");
        String gkColor = "";
        int blue = 0, orange = 0, red = 0;
        for(String p : players) {
            String[] att = p.split("∠");
            if(att[6].equals("a")) { //a means gk
                gkColor = att[4]; //level, 6=purple, 5=red, 4=orange, 3=blue
            } else if(att[6].equals("z")) {
                continue;
            }
            if(att[4].equals("5")) {
                red++;
            } else if(att[4].equals("4")) {
                orange++;
            } else if(att[4].equals("3")) {
                blue++;
            }
        }
        //0：蓝色， 1：橙色， 2：红色， 3：紫色
        if(gkColor.equals("5")) {
            return red >= 6 ? "2" : "3";
        } else if(gkColor.equals("4")) {
            return orange >= 6 ? "1" : "3";
        } else if(gkColor.equals("3")) {
            return blue >= 6 ? "0" : "3";
        }
        return "3";
    }

    /* 踢世界杯 */
    public static String doWorldCup(String email) throws Exception {
        if(Integer.parseInt(IDUtils.IDInfos.get(email).get("level")) < 51) {
            return null;
        }
//        LOGGER.info(email + ": 世界杯");
        Calendar c = Calendar.getInstance();
        if(c.get(Calendar.DAY_OF_WEEK) < Calendar.MONDAY || c.get(Calendar.DAY_OF_WEEK) > Calendar.THURSDAY) {
            return null;
        }
        if(c.get(Calendar.HOUR_OF_DAY) < 8 || c.get(Calendar.HOUR_OF_DAY) > 21) {
            return null;
        }
        String conf = ConfigUtils.getConf(email, "世界杯难度");
        if(StringUtils.isEmpty(conf)) {
            return null;
        }
        try {
            int lv = Integer.parseInt(conf);
            if(lv < 1 || lv > 3) {
                return null;
            }
        } catch(Exception ex) {
            return null;
        }
        if(IDUtils.WorldCupFinished.containsKey(email) &&
                IDUtils.WorldCupFinished.get(email)) {
            return null;
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.WORLDCUP);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("worldcup_signup_load1", ""));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm); //0:未进行，1：进行中，2：淘汰赛

        if(!"2".equals(ret)) {
            if("0".equals(ret)) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.WORLDCUP);
                params = new ArrayList<NameValuePair>();
                if("1".equals(conf)) {
                    params.add(new BasicNameValuePair("OpenWorldCup1", "简单"));
                } else if("2".equals(conf)) {
                    params.add(new BasicNameValuePair("OpenWorldCup1", "普通"));
                } else if("3".equals(conf)) {
                    params.add(new BasicNameValuePair("OpenWorldCup1", "困难"));
                } else {
                    return null;
                }
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(email, pm);
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.WORLDCUP);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("worldcup_groupload1", "-1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);

            if(ret.indexOf("inmatch") == -1) {
                String str = ret.substring(ret.indexOf("#") + 1);
                str = str.substring(0, str.indexOf("#"));
                if(Integer.parseInt(str) < 3) { //小组赛未结束
                    pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("WorldCup_NextgroupMatch1", "0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    ret = IDUtils.execute(email, pm); //id: 比赛id，-1：正在比赛
                } else { //小组赛结束，判断名次
                    str = ret.substring(ret.lastIndexOf("&"));
                    str = str.substring(str.indexOf("#") + 1);
                    str = str.substring(str.indexOf("#") + 1);
                    str = str.substring(str.indexOf("#") + 1);
                    str = str.substring(0, str.indexOf("#"));
                    if(Integer.parseInt(str) <= 2) { //前2名，开始淘汰赛
                        pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("worldcup_knockout_load1", ""));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        ret = IDUtils.execute(email, pm);

                        pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("worldcup_knockout_game1", "0"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(email, pm);
                    } else { //比赛结束
                        pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("worldcup_leave1", ""));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        ret = IDUtils.execute(email, pm);
                        IDUtils.WorldCupFinished.put(email, true);
                    }
                }
            }
        } else {
            pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("worldcup_knockout_load1", ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
            if(StringUtils.isNotEmpty(ret)) {
                if(ret.indexOf("inmatch") == -1) {
                    //判断&出现次数，出现4次说明冠军出现，不用再比了
                    int count = 0;
                    String tmp = ret;
                    while(tmp.indexOf("&") > -1) {
                        tmp = tmp.substring(tmp.indexOf("&") + 1);
                        count++;
                    }
                    if(count >= 4) {
                        IDUtils.WorldCupFinished.put(email, true);
                    } else {
                        String me = ret.substring(ret.indexOf("#") + 1);
                        String against = me.substring(me.indexOf("#") + 1);
                        me = me.substring(0, me.indexOf("#"));
                        against = against.substring(0, against.indexOf("#"));
                        if(Integer.parseInt(me) > Integer.parseInt(against)) { //胜利，再打一场
                            pm = new HttpPost(OgzqURL.URL + "/WorldCup.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("worldcup_knockout_game1", "0"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            ret = IDUtils.execute(email, pm);
                        } else {
                            IDUtils.WorldCupFinished.put(email, true);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static String doFuben(String email) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        int now = Integer.parseInt(sdf.format(new Date()));
        if(now < 1020) { //打巅峰之路
            dianfengzhilu(email);
            return null;
        }
        if(now > 2349) {
            return null;
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        /*
10|10|10|10|10|5@99|4|Super/headh.png|缥缈过客  |7|-1@1*1*1*1@0
10|10|10|10|10|2@99|4|Super/headh.png|挺着大肚子|7|-1@1*1*1*1@-1
10|10|10|10|1|0@99|4|Super/headh.png|蓝精灵001  |7|-1@0*0*0*0@-1
                                                      4个副本 卡福
                                                      1克打   0可打
         */
        ret = ret.substring(ret.indexOf("@") + 1); //99|4|Super/headh.png|缥缈过客  |7|-1@1*1*1*1@0
        ret = ret.substring(ret.indexOf("@") + 1); //1*1*1*1@0
        boolean[] canFight = new boolean[5];
        ret = ret.substring(0, ret.lastIndexOf("@"));
        if(Integer.parseInt(ret.substring(ret.indexOf("@") + 1)) == 0) {
            canFight[4] = true;
        } else {
            canFight[4] = false;
        }
        ret = ret.substring(0, ret.indexOf("@"));
        String[] subs = ret.split("[*]");
        for(int i = 0; i < 4; i++) {
            if("1".equals(subs[i])) {
                canFight[i] = true;
            } else {
                canFight[i] = false;
            }
        }
        if(!IDUtils.FubenStatus.containsKey(email)) {
            IDUtils.FubenStatus.put(email, new int[]{-2,-2,-2,-2,-2});
            for(int j = 0; j < 5; j++) {
                if(!canFight[j]) {
                    IDUtils.FubenStatus.get(email)[j] = -1;
                }
            }
            for(int i = 11; i <= 14; i++) {
                pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("loadClubList", "1"));
                params.add(new BasicNameValuePair("LeagueIndex", ""+i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(email, pm);

                ret = ret.substring(ret.lastIndexOf("^") + 1); //*-1*1101*10138|缥缈过客 |npc30.png|20078|2*-1*2*5|0*2|2565986| 德尚 |4|83|3|Super/110109.png|10138|缥缈过客╋-1╋-1╋-1|-1
                String[] subs1 = ret.split("[*]");
                String restMatch = subs1[6];
                restMatch = restMatch.substring(restMatch.indexOf("|") + 1);
                IDUtils.FubenStatus.get(email)[i - 11] = Integer.parseInt(restMatch);
            }
        } else {
        }
        for(int i = 14; i >= 11; i--) { //分别对应4个副本
            if(!canFight[i-11]) {
                IDUtils.FubenStatus.get(email)[i - 11] = -1;
                continue;
            }
            pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loadClubList", "1"));
            params.add(new BasicNameValuePair("LeagueIndex", ""+i));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
/*
            -1*11*
            无比赛
            1|66534354|74464*11*
            比赛中
            0|1101|南特|德尚序幕|0@0&6001&球员经验卡50点&6001.png@0&7001&训练卡100点&7001.png@0&1501&球衣1级&1501.png@0&5001&银币&5001.png|0|0|1|1506|3|74462|1943|索潘|金丝雀|联赛冠军8次||npc1101.png|-1|-1|0|0|1|0^
            0|1102|波尔多|战胜南特开启|0@0&6001&球员经验卡50点&6001.png@0&7001&训练卡100点&7001.png@0&1501&球衣1级&1501.png@0&5001&银币&5001.png|0|0|1|1812|1|74463|1881|德尔马斯|80年代霸主|联赛6次||npc4.png|518|穆里尼熬|0|0|1|0^
            0|1103|马赛|战胜波尔多开启|0@0&6001&球员经验卡50点&6001.png@0&7001&训练卡100点&7001.png@0&1501&球衣1级&1501.png@0&5001&银币&5001.png|0|0|1|2117|5|74464|1899|维罗德洛姆|落魄贵族|联赛9次|欧冠1次|npc9.png|41651|VG萨内蒂|0|0|1|0^
            0|1104|尤文图斯|战胜马赛开启|0@0&6002&球员经验卡100点&6002.png@0&7001&训练卡100点&7001.png@0&1501&球衣1级&1501.png@0&5001&银币&5001.png|0|0|1|2429|5|74465|1897|尤文图斯|斑马军团|联赛27次|欧冠2次|npc28.png|9852|直击丶AC|0|0|1|0^
            0|1105|法国国家队|战胜尤文图斯开启|0@0&6001&球员经验卡50点&6001.png@0&7001&训练卡100点&7001.png@0&1502&球衣2级&1502.png@0&5001&银币&5001.png|0|0|1|2752|3|74466|1904|法兰西球场|高卢雄鸡|欧洲杯2次|世界杯1次|npc1105.png|-1|-1|0|0|1|0^
            *-1           *1101*10138|缥缈过客 |npc30.png|20078|2*-1            *2*5|0*2|2565986| 德尚 |4|83|3|Super/110109.png|10138|缥缈过客╋-1╋-1╋-1|-1
             未领奖励场次  下场对手                               未领取的奖励       剩余场次
*/
            ret = ret.substring(ret.lastIndexOf("^") + 1); //*-1*1101*10138|缥缈过客 |npc30.png|20078|2*-1*2*5|0*2|2565986| 德尚 |4|83|3|Super/110109.png|10138|缥缈过客╋-1╋-1╋-1|-1
            String[] subs1 = ret.split("[*]");
            String restMatch = subs1[6];
            restMatch = restMatch.substring(restMatch.indexOf("|") + 1);
            IDUtils.FubenStatus.get(email)[i - 11] = Integer.parseInt(restMatch);

            String conf = ConfigUtils.getConf(email, "是否踢副本" + (i - 10));
            if(!"1".equals(conf)) {
                continue;
            }
//            if(!subs1[4].startsWith("-1")) {
                String match = ret.substring(1);
                match = match.substring(0, match.indexOf("*"));
                String award = getFubenAward(email, match);
//                if(StringUtils.isNotEmpty(award)) {
//                    LOGGER.warn(email + ": 无法领取副本奖励");
//                    return null;
//                }
//            如果指定副本最后一场有活动，将这里注释即可
//            if(i == 14 && Integer.parseInt(restMatch) <= 1) {
//                continue;
//            }
                pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("loadClubList", "1"));
                params.add(new BasicNameValuePair("LeagueIndex", ""+i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(email, pm);
                ret = ret.substring(ret.lastIndexOf("^") + 1); //*-1*1101*10138|缥缈过客 |npc30.png|20078|2*-1*2*5|0*2|2565986| 德尚 |4|83|3|Super/110109.png|10138|缥缈过客╋-1╋-1╋-1|-1
                subs1 = ret.split("[*]");
//            }
            if(IDUtils.FubenStatus.get(email)[i - 11] == 0) {
                continue;
            }
            String next = subs1[2];
            pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("insertMatch", next));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm); //返回比赛id
            if(!"-1".equals(ret)) {
                LOGGER.info(email + "进行副本：" + i + "/" + ret);
                break;
            }
        }

        if(true) { //处理Cafu副本
//        if(IDUtils.FubenStatus.get(email)[4] > 0 || IDUtils.FubenStatus.get(email)[4] == -2) { //处理Cafu副本
            pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList2.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load1", "15"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            subs = s.split("#");
            String type = subs[0];
            if("0".equals(type)) { //可以比赛
                String conf = ConfigUtils.getConf(email, "是否踢副本5");
                if(!"1".equals(conf)) {
                    return null;
                }
                String[] power = subs[2].split("[|]"); //总共场次|剩余场次
                IDUtils.FubenStatus.get(email)[4] = Integer.parseInt(power[1]);
                pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Challenge1", "15"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
            } else if("1".equals(type) || "2".equals(type)) { //领卡片，2=一开始，1=卡片已经翻了
                if("2".equals(type)) {
                    pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList2.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("AcceptAward1", "15"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(email, pm);

                    pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList2.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("FlopCard1", "15*0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(email, pm);
                }
                pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/ChallengeList2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("AcceptCardAward1", "15"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
            } else if("3".equals(type)) { //比赛中
                return null;
            }
        }
        return null;
    }

    private static String getFubenAward(String email, String match) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ra", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        int leagueIndex = 0;
        for(int i = 11; i <= 14; i++) {
            pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("loadClubList", "1"));
            params.add(new BasicNameValuePair("LeagueIndex", ""+i));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(email, pm);
            ret = ret.substring(ret.lastIndexOf("^") + 1); //*-1*1101*10138|缥缈过客 |npc30.png|20078|2*-1*2*5|0*2|2565986| 德尚 |4|83|3|Super/110109.png|10138|缥缈过客╋-1╋-1╋-1|-1
            String[] subs1 = ret.split("[*]");
            if(!"-1".equals(subs1[1])) {
                leagueIndex = i;
                break;
            }
        }

        if(leagueIndex == 0) {
            return "无法领奖";
        }
        pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("initCards", "1"));
        params.add(new BasicNameValuePair("LeagueIndex", "" + leagueIndex));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        if(!ret.equals("-1")) {
//            LOGGER.info(email + "副本奖励：" + ret);

            if(ret.split("[*]")[0].equals("-1|-1^-1|-1^-1|-1") ||
                    ret.split("[*]")[0].equals("-1|-1^-1|-1^-1|-1^-1|-1")) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("OpenCard", "1"));
                params.add(new BasicNameValuePair("cardindex", "2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ss = IDUtils.execute(email, pm);
//                LOGGER.info(email + ": " + ss);
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.CHALLENGE_2);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getAward", "1"));
            params.add(new BasicNameValuePair("lod", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ss = IDUtils.execute(email, pm);
//            LOGGER.info(ss);
        }
        return null;
    }

    public static String shoubian(String email, String teamId) throws Exception{
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", teamId));
        params.add(new BasicNameValuePair("MatchPrice", "-1200"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    public static String changeCoach(String email, String coach) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpdateMyTrainMan1", coach)); //coach=里皮/瓜迪奥拉/。。。。。。
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        return s;
    }

    public static String openjieshuo(String email, String index) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "kaiQi"));
        params.add(new BasicNameValuePair("index", index));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        LOGGER.info(email + "开启解说" + index + ":" + s);
        return s;
    }

    public static String jieshuoLevelup(String email, String index) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "sheng"));
        params.add(new BasicNameValuePair("index", index));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if("1".equals(s)) {
            s = "升级成功";
            defaults(email, 7);
        } else if("-1".equals(s)) {
            s = "银币不足";
        } else if("-2".equals(s)) {
            s = "解说经验不满足条件";
        } else if("-3".equals(s)) {
            s = "所需潜力卡数量不足";
        } else if("-4".equals(s)) {
            s = "升级到6级解说需要三个解说等级都为5级9星且经验值达到70000";
        } else {
            s = "操作失败，请重新操作";
        }
        LOGGER.info(email + "升级" + index + ":" + s);
        return s;
    }

    public static String autoSellJiqing(String email) throws Exception {
        return SellJiqingThread.autoSellJiqing(email);
    }

    public static String fireToTactic(String email, String playerId) throws Exception {
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "解雇球员到战术中心：" + playerId);
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Training.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        params.add(new BasicNameValuePair("playerid", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        String[] parts = s.split("[|]");
        if(!"-1".equals(parts[2])) {
            int time = Integer.parseInt(parts[2]);
            int sec = time % 60;
            int min = ((time - sec) / 60) % 60;
            int hour = (time - min * 60 - sec) / 3600;
            return "解雇剩余时间：" + hour + ":" + min + ":" + sec;
        }

//        Map<String, String> playerDetail = OperationUtils.viewPlayer(email, playerId);
        Map<String, String> playerDetail = CacheManager.loadPlayer(email, playerId, false);
        String level = playerDetail.get("pinzhi");

        pm = new HttpPost(OgzqURL.URL + "/Tactics.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("fire", "1"));
        params.add(new BasicNameValuePair("fireid", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(email, pm);

        List<String> ids = new ArrayList<String>();
        ids.add(email);
        try {
            int l = 0;
            if("普通".equals(level)) {
                l = 1;
            } else if("优秀".equals(level)) {
                l = 2;
            } else if("精英".equals(level)) {
                l = 3;
            } else if("杰出".equals(level)) {
                l = 4;
            } else if("大牌".equals(level)) {
                l = 5;
            } else if("超级巨星".equals(level)) {
                l = 6;
            }
            new TeamXiangqianCacheThread(ids, l, true).start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }

    public static String fireToHalfBack(String email, String playerId) throws Exception {
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "解雇球员：" + playerId);
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Training.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        params.add(new BasicNameValuePair("playerid", playerId));
        params.add(new BasicNameValuePair("isSelected", "0"));
        params.add(new BasicNameValuePair("breakdouble", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        CacheManager.deletePlayer(email, playerId);
        return "done";
    }

    public static String useTrainCard(String email, String playerId, String itemId, int count) throws Exception {
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "使用道具：" + itemId + "，对球员：" + playerId + "，次数：" + count);
        for(int i = 0; i < count; i++) {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Bag.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "8"));
            params.add(new BasicNameValuePair("playerid", playerId));
            params.add(new BasicNameValuePair("aii", itemId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
        }
        CacheManager.loadPlayer(email, playerId, true);
        return "done";
    }

    public static String decomposition(String email, String itemId, int count) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/ItemDecomposition.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        pm = new HttpPost(OgzqURL.URL + "/ItemDecomposition.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("mold", "1"));
        params.add(new BasicNameValuePair("itemCode", itemId));
        params.add(new BasicNameValuePair("count", "" + count));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        return IDUtils.execute(email, pm);
    }

    public static String getDecompositionGift(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/ItemDecomposition.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm).split("╋")[0];

        int count = Integer.parseInt(s.substring(0, s.indexOf("*")));
        for(int i = 0; i < count / 2000; i++) {
            pm = new HttpPost(OgzqURL.URL + "/ItemDecomposition.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "3"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
        }
        return "done";
    }

    public static String doOgzd(String email) throws Exception {
//        if(Integer.parseInt(IDUtils.IDInfos.get(email).get("gold")) >= 20 && email.indexOf("robot0009@sina.com") < 0) {
//            return null;
//        }
//        if(true) {
//            return null;
//        }
        String conf = ConfigUtils.getConf(email, "是否踢欧冠之巅");
        if(!"1".equals(conf)) {
            return null;
        }

        /* 检查联赛进度 */
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OgzdGameLoad1", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);//9@2@法甲|1|1*德甲|2|0*意甲|3|0*英超|4|0*西甲|5|0
        int restTime = Integer.parseInt(s.split("@")[0]) + 1;
        String league = s.substring(s.lastIndexOf("@") + 1);
        String[] leagues = league.split("[*]");
        int curLeague = -1;
//        for(int i = 0; i < leagues.length; i++) {
//            if(leagues[i].split("[|]")[2].equals("1")) {
//                curLeague = i;
//            }
//        }

        /* 检查是否有未领取奖励 */
        int matchId = 0;
        for(int i = 0; i < 5; i++) {
            pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ShowOgzdGame1", (i + 1) + ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            matchId = Integer.parseInt(s.substring(0, s.indexOf("@")));
            if(matchId != 0) {
                curLeague = i;
                break;
            }
        }

        if(matchId > 0) { //>0表示比赛ID
            return null;
        } else {
            boolean needGetReward = false;
//            if(matchId == 0) {
//                //先判断，如果下一场比赛是某联赛的第一场，需要领上一个联赛的奖励
//                String team = s.split("@")[5];
//                String[] teams = team.split("&");
//                int next = 0;
//                for(int i = 0; i < teams.length; i++) {
//                    String[] subs = teams[i].split("[|]");
//                    if(subs[7].equals("1")) {
//                        next = i;
//                    }
//                }
//                if(next == 0) {
//                    curLeague = curLeague - 1;
//                    needGetReward = true;
//                }
//            }
            if(matchId == -3 || needGetReward) { //需要领奖
                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx"); //领取其他奖品，比如汤圆等
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("OtherGet", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(email, pm);

                int count = -1;
                LOGGER.info(email + "欧冠之巅领取奖励结果：" + s.split("@")[1].split("#")[2]);
                String[] ss = s.split("@")[1].split("#")[2].split("[*]");
                for(int i = 0; i < 5; i++) {
                    if(ss[i].split("&")[0].equals("0")) {
                        count = 1;
                        break;
                    }
                }
                int fanplayerlevel = -1;
                if(count == -1) {
                    pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("FlopCard1", (curLeague + 1) + "*4"));
//                    params.add(new BasicNameValuePair("FlopCard1", (curLeague + 1) + "*" + new Random().nextInt(5)));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(email, pm);
                    LOGGER.info(email + "欧冠之巅需要翻牌：" + s);
                    if(s.indexOf("#") >= 0) {
                        if(s.split("#")[2].split("&")[6].equals("1")) {
                            fanplayerlevel = Integer.parseInt(s.split("#")[2].split("&")[5]);
                        }
                    }
                }
                if(fanplayerlevel > 0) {
                    List<String> ids = new ArrayList<String>();
                    ids.add(email);
                    new TeamXiangqianCacheThread(ids, fanplayerlevel, true).start();
                }

                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("AcceptCardAward1", (curLeague + 1) + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                LOGGER.info(email + "/" + s);
            }
        }

        if(restTime <= 0) {
            return null;
        }

        //打比赛
        //检查欧冠之巅打了之后是不是完成了主线任务
//        pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
//        params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("type", "0"));
//        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//        String rett = IDUtils.execute(email, pm);
//        String[] dailytasks = rett.split("\\|")[0].split("∠");
//        for(String ss : dailytasks) {
//            if(ss.indexOf("欧冠之巅") >= 0 && ss.split("\\*")[6].equals("1")) {
//                finishTask(email, ss.split("\\*")[0]);
//            }
//        }

        String nextTeam = getOgzdOpponent(email);
        if(StringUtils.isNotEmpty(nextTeam)) {
            pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Challenge1", nextTeam));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            if(s.equals("-6")) {
                s = "正在比赛中，不能挑战";
            } else if(s.equals("-4")) {
                s = "挑战次数不足";
            }
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "踢欧冠之巅：" + s);
        } else {
            LOGGER.warn(email + "/" + IDUtils.getNick(email) + "找不到可以踢的欧冠之巅！");
        }
        return null;
    }

    public static String removeEquipment(String email, String player, String type) throws Exception {
        //type: 0=衣服，1=护膝，2=球鞋，3=手套，4=袖标
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "11"));
        params.add(new BasicNameValuePair("eqIndex", type));
        params.add(new BasicNameValuePair("playerid", player));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        CacheManager.loadPlayer(email, player, true);
        return "done";
    }

    public static String useJieshuoItem(String email, String itemcode, String index) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "useCard"));
        params.add(new BasicNameValuePair("itemcode", itemcode));
        params.add(new BasicNameValuePair("index", index));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        String desc;
        if(Integer.parseInt(s) > 0) {
            desc = "使用成功";
        } else if("-1".equals(s)) {
            desc = "数量不足";
        } else if("-2".equals(s)) {
            desc = "该解说未开启";
        } else {
            desc = "操作失败，请重新操作！";
        }
        LOGGER.info(email + "使用解说道具" + itemcode + ": " + desc);
        return desc;
    }

    public static String wearEquipment(String email, String playerId, String itemId, String itemCode) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        if(itemCode.equals("1515") || itemCode.equals("1516") || itemCode.equals("1517") || itemCode.equals("1518") || itemCode.equals("1615") || itemCode.equals("1616") || itemCode.equals("1617") || itemCode.equals("1618") || itemCode.equals("1715") || itemCode.equals("1716") || itemCode.equals("1717") || itemCode.equals("1718") || itemCode.equals("1815") || itemCode.equals("1816") || itemCode.equals("1817") || itemCode.equals("1818") || itemCode.equals("4215") || itemCode.equals("4216") || itemCode.equals("4217") || itemCode.equals("4218")) {
            params.add(new BasicNameValuePair("WearEquipment1", itemId + "*" + playerId));
        } else {
            params.add(new BasicNameValuePair("type", "10"));
            params.add(new BasicNameValuePair("eqItemCode", itemCode));
            params.add(new BasicNameValuePair("playerid", playerId));
        }
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        CacheManager.loadPlayer(email, playerId, true);
        return "done";
    }

    public static String changePlayerPos(String email, String p1, String p2) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        String[] parts = str.split("⊥");
        String tacticalIndex = parts[1];

        String[] playerIndexs = parts[0].split("∟");
        List<String> playerInd = new ArrayList<String>();
        for(String p : playerIndexs) {
            String[] pp = p.split("∠");
            playerInd.add(pp[5]);
        }
        int found = 0;
        for(int i = 0; i < playerInd.size(); i++) {
            if(playerInd.get(i).equals(p1)) {
                playerInd.set(i, p2);
                found++;
            } else if(playerInd.get(i).equals(p2)) {
                playerInd.set(i, p1);
                found++;
            }
        }
//        if(found != 2) {
//            return "fail: " + found;
//        }

        StringBuffer tacticParams = new StringBuffer();
        for(String p : playerInd) {
            tacticParams.append("*").append(p);
        }

        pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("playerIDList", tacticParams.substring(1)));
        params.add(new BasicNameValuePair("tacticalIndex", tacticalIndex));
        params.add(new BasicNameValuePair("pLM5", UtilFunctions.md5(tacticParams.substring(1) + IDUtils.IDObjIds.get(email)[0] + IDUtils.IDObjIds.get(email)[1])));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        return "done";
    }

    public static String playerChallenge(String email, String player) throws Exception {
        String url = OgzqURL.URL + "/" + player + "_Challenge.aspx";
        HttpPost pm = new HttpPost(url);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        String ret = IDUtils.execute(email, pm);
        //0#1&0&100&10040|80|3|Super/10040.png|少年布雷默|4
        //0#2&0&100&10040|80|3|Super/10040.png|少年布雷默|4
        //1#0|0*0|0*0|0*0|0&0
        if(ret.indexOf("╋") > 0) {
            ret = ret.substring(0, ret.indexOf("╋"));
        }
        if(ret.equals("")) {
            return "";
        }
        String type = ret.substring(0, 1);
        String otherInfo = ret.substring(2);
        if(type.equals("0")) { //可以进行下一场比赛
            //	type==0: 加载副本界面
            //		其他信息:
            //		当前挑战到的球队Index & 是否可领取球员 & 少年布雷默的PlayerDataID|PlayerInfo_Power|role|Photo|CnName|PlayerQuality
            String matchIndex = otherInfo.substring(0, 1);
            if(matchIndex.equals("5")) {
                LOGGER.info(email + "/" + IDUtils.getNick(email) + " " + player + "副本通关");
                return "1";
            }
            pm = new HttpPost(url);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Challenge", "1"));
            params.add(new BasicNameValuePair("index", matchIndex));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "打" + player + "副本：" + matchIndex);
        } else if(type.equals("1")) {
            //	type==1: 尚未翻牌、奖励未领取   加载翻牌界面
            //		其他信息:
            //		ItemCode|ItemName*ItemCode|ItemName*ItemCode|ItemName*ItemCode|ItemName & 翻牌花费
            //		0|0*ItemCode|ItemName*0|0*0|0 & 翻牌花费
            //		0|0*0|0*0|0*0|0 & 翻牌花费
            int cost = Integer.parseInt(otherInfo.substring(otherInfo.lastIndexOf("&") + 1));
            if(cost == 0) {
                pm = new HttpPost(url);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("TurnCard", "1"));
                params.add(new BasicNameValuePair("index", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(email, pm);
            }

            pm = new HttpPost(url);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getgift", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "领取" + player + "副本奖励：" + ret);
            return "0";
        } else if(type.equals("3")) {
            //	type==3: 比赛未打完   加载比赛
            //		其他信息: matchId | 标示比赛的那个数字
            //result = "0#1&0&100&10040|80|3|Super/10040.png|少年布雷默|4";
            //result = "1#1106|合同6级*0|0*0|0*0|0&60";
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "正在打" + player + "副本");
            return "0";
        }
        return "2";
    }

    public static String changeTactic(String email, int tactic) throws Exception {
        if(StringUtils.isEmpty(email)) {
            return "没有指定需要变阵的帐号";
        }
        if(!IDUtils.IDInfos.containsKey(email)) {
            return "指定帐号不存在";
        }
        //获取战术
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "9"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String[] v = ret.split("[*]");

        //获取球员
        pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        ret = ret.substring(ret.indexOf("^") + 1);
        String[] players = ret.split("[|]");

        List<List<String[]>> abilitys = new ArrayList<List<String[]>>(); //第一维：位置；第二维：球员；第三维：属性
        for(int i = 0; i < 4; i++) {
            abilitys.add(new ArrayList<String[]>());
        }

        for(String p : players) {
            String[] atts = p.split("[*]");
            int pos = Integer.parseInt(atts[5]);
            int abi = Integer.parseInt(atts[6]);
            String playerId = atts[0];
            boolean add = false;
            for(int i = 0; i < abilitys.get(pos - 1).size(); i++) {
                if(abi > Integer.parseInt(abilitys.get(pos - 1).get(i)[1])) {
                    abilitys.get(pos - 1).add(i, new String[]{atts[0], atts[6]});
                    add = true;
                    break;
                }
            }
            if(!add) {
                abilitys.get(pos - 1).add(new String[]{atts[0], atts[6]});
            }
        }
        StringBuffer tacticParams = new StringBuffer();
//        tacticParams.append("*").append(abilitys.get(0).get(0)[0]); //门将
//        abilitys.get(0).remove(0);
        switch(tactic) {
            case 0://初攻
                return "暂不支持初级战术";
            case 1://高攻
                if(Integer.parseInt(v[3]) < 0) {
                    return "该帐号未开通高攻";
                }
                tacticParams.append(change2ExpertAttack(abilitys, "0".equals(v[14])));
                break;
            case 2://初组
                return "暂不支持初级战术";
            case 3://高组
                if(Integer.parseInt(v[4]) < 0) {
                    return "该帐号未开通高组";
                }
                tacticParams.append(change2ExpertMid(abilitys, "0".equals(v[15])));
                break;
            case 4://初防
                return "暂不支持初级战术";
            case 5://高防
                if(Integer.parseInt(v[5]) < 0) {
                    return "该帐号未开通高防";
                }
                tacticParams.append(change2ExpertDefend(abilitys, "0".equals(v[16])));
                break;
            default:
                return "指定的阵型不存在：" + tactic;
        }
        //增加剩余球员
//        int added = -100;
//        for(int i = 0; i < abilitys.get(0).size(); i++) {
//            if(added < 7) {
//                tacticParams.append("*").append(abilitys.get(0).get(i)[0]);
//                added++;
//            }
//        }
//        for(int i = 0; i < abilitys.get(1).size(); i++) {
//            if(added < 7) {
//                tacticParams.append("*").append(abilitys.get(1).get(i)[0]);
//                added++;
//            }
//        }
//        for(int i = 0; i < abilitys.get(2).size(); i++) {
//            if(added < 7) {
//                tacticParams.append("*").append(abilitys.get(2).get(i)[0]);
//                added++;
//            }
//        }
//        for(int i = 0; i < abilitys.get(3).size(); i++) {
//            if(added < 7) {
//                tacticParams.append("*").append(abilitys.get(3).get(i)[0]);
//                added++;
//            }
//        }
        pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        String currentTactical = str.split("⊥")[1];
        str = str.split("⊥")[0];
        String[] currentOnPlayers = str.split("∟");
        int add = 0;
        for(String allPlayers : currentOnPlayers) {
            if(tacticParams.indexOf(allPlayers.split("∠")[5]) < 0 && add < 7) {
                tacticParams.append("*").append(allPlayers.split("∠")[5]);
                add++;
            }
        }

        for(int i = 0; i < 2; i++) {
            pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("playerIDList", tacticParams.toString()));
            params.add(new BasicNameValuePair("tacticalIndex", "" + tactic));
            params.add(new BasicNameValuePair("pLM5", UtilFunctions.md5(tacticParams.toString() + IDUtils.IDObjIds.get(email)[0] + IDUtils.IDObjIds.get(email)[1])));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);

            pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            params.add(new BasicNameValuePair("tacticalIndex", "" + tactic));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
        }
        // coach
        pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadCoach1", ""));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        ret = ret.substring(ret.indexOf("@") + 1);
        ret = ret.substring(0, ret.indexOf("@"));
        String[] coaches = ret.split("[\\^]");
        String newCoach = "";
        int newCoachAbility = 0;
        for(String c : coaches) { //1|0|0|4|瓜迪奥拉|7|118357|168000|0|108|72|42|20|12|0|0|0|0|0|0|0^
            String[] atts = c.split("[|]");
            if(!"1".equals(atts[0])) {
                continue;
            }
            int ability = Integer.parseInt(atts[tactic / 2 + 9]);
            if(ability > newCoachAbility) {
                newCoach = atts[4];
                newCoachAbility = ability;
            }
        }
        pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpdateMyTrainMan1", newCoach));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        defaults(email, 7);
        return "变阵完成";
    }

    //装备技能加成
    public static String equipBonus(String email, String itemId) throws Exception {
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "装备技能加成");
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Bonus1", itemId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        return ret;
    }

    //装备技能刷新
    public static String equipRefresh(String email, String itemId, String level) throws Exception {
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "刷新装备");
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Refresh1", itemId + "*" + level + "*000*1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String[] parts = ret.split("[*]");

        pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("SavePropList1", itemId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        if(parts.length > 13) {
            ret = parts[13]; ////全属性+100,突破+25,突破+30,突破+85,扑救+60
            String s = parts[13].substring(parts[13].indexOf(",") + 1);
            parts = s.split(",");
            int gk = 0, d = 0, m = 0, f = 0;
            for(String ss : parts) {
                if(ss.startsWith("突破") || ss.startsWith("射门")) {
//                    gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 5;
//                    d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 10;
//                    m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 10;
                    f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
                } else if(ss.startsWith("传球") || ss.startsWith("速度")) {
//                    gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 15;
//                    d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 15;
                    m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
//                    f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 15;
                } else if(ss.startsWith("断球") || ss.startsWith("铲球")) {
//                    gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 5;
                    d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
//                    m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 10;
//                    f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 10;
                } else if(ss.startsWith("扑救") || ss.startsWith("出击")) {
                    gk += Integer.parseInt(ss.substring(ss.indexOf("+") + 1));
//                    d += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 5;
//                    m += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 5;
//                    f += Integer.parseInt(ss.substring(ss.indexOf("+") + 1)) * 5;
                }
            }
            return ret + "\r\n" + "门将：" + (gk) + "，后卫：" + (d) + "，中场：" + (m) + "，前锋：" + (f);
        } else {
            return ret;
        }
    }

    public static int doWorldcup32(String email) throws Exception {
        return com.saille.ogzq.dailyLoop.Worldcup32Thread.doWorldcup32(email);
    }

    public static String qiuyuanshengjie(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/QiuYuanShengJie.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("playerid", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String[] flags = ret.split("@")[1].split("[*]");
        for(int i = 0; i < 6; i++) {
            String[] ff = flags[i].split("[|]");
            if(Integer.parseInt(ff[4]) < 2) {
                pm = new HttpPost(OgzqURL.URL + "/QiuYuanShengJie.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "3"));
                params.add(new BasicNameValuePair("playerid", playerId));
                params.add(new BasicNameValuePair("index", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(email, pm);
            }
        }
        pm = new HttpPost(OgzqURL.URL + "/QiuYuanShengJie.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        params.add(new BasicNameValuePair("playerid", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);

        trainFull(email, playerId, false);
        return ret;
    }

    public static long gjxls(String email) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        if(Integer.parseInt(sdf.format(new Date())) < 900) {
            return 1000 * 60 * 10;
        }
        String conf = ConfigUtils.getConf(email, "是否踢联盟训练赛");
        if(!"1".equals(conf)) {
            return 1000 * 60 * 20;
        }
        
        int page = 1;
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GameLoad1", "-1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
//        if(s.split("@").length > 9) {
//            LOGGER.info(email + "/" + IDUtils.getNick(email) + "打联盟训练赛结果：" + s.split("@")[9] + "。");
//        } else {
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "打联盟训练赛结果：" + s + "。");
//        }
        int restMatch = 0;
        for(page = 4; page >= 0; page--) {
            pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("GameLoad1", "" + page));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            if(s.indexOf("inmatch|") >= 0) {
                pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("MatchID", s.split("\\|")[1]));
                params.add(new BasicNameValuePair("MatchCategory", "104"));
                params.add(new BasicNameValuePair("KFC", "3"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                String matchpath = s.split("\\~")[0];
                HttpGet gm = new HttpGet("http://f2.ogzq.xdgame.cn/txt/" + matchpath + ".txt");
                CloseableHttpResponse resp = (CloseableHttpResponse)IDUtils.IDS.get(email).execute(gm);
                resp.close();
                gm.releaseConnection();
                return 1000 * 60;
            }
            String[] parts = s.split("@");
            restMatch = Integer.parseInt(parts[1]);
            if(restMatch <= 0) { //剩余场次
                for(int i = 0; i < 5; i++) {
                    pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("JiangliLq1", "" + i));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(email, pm);
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "联盟训练赛星级奖励：" + s + "。");
                    if("-2".equals(s)) {
                        break;
                    }
                }
                return 1000 * 60 * 20;
            }
//0@
//10@
//0@
//30@
//5@
//2745@
//1@    当前页
//5@    一共页
//-1&卓依服饰&hd2.png&9377-110服&罗马联盟&npc27.png&0|0|0&31299|54582|77830&0&80|100|120&1#-2&普拉蒂尼&UC.png&人人-15服&阿森纳联盟&npc36.png&0|0|0&31427|54800|78148&0&96|120|144&0#-3&ZY凤歌&npc60.png&05玩-41服&曼城联盟&npc39.png&0|0|0&37071|64670|92234&0&112|140|168&0#-4&S皮克&npc60.png&9377-91服&皇家马德里联盟&npc49.png&0|0|0&37363|65140|92880&0&128|160|192&0#-5&木加木&UC.png&新浪-1服&拜仁慕尼黑联盟&npc20.png&0|0|0&37665|65670|93634&0&144|180|216&0#-6&光辉岁月&npc60.png&游侠-27服&热刺联盟&npc35.png&0|0|0&37784|65877|93927&0&160|200|240&0#-7&雅鹿&npc60.png&官方-44服&马德里竞技联盟&npc47.png&0|0|0&38444|67013|95536&0&176|220|264&0@
//@
//缥缈过客#npc58.png
            String[] clubs = parts[8].split("#");
            for(int i = clubs.length - 1; i >= 0; i--) {
                String[] attrs = clubs[i].split("&");
                if(attrs[10].equals("0")) { //0表示不能打，1表示可以打
                    continue;
                }
                //todo:变阵
//                String[] shili = attrs[7].split("[|]");
//                int index = Math.abs(Integer.parseInt(attrs[0])) - 1;
//                String zhenxing = IDUtils.GJXLS.get(index)[3];
                
                pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("BeginGame1", attrs[0] + "*" + "0")); //id*难度
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                LOGGER.info(email + "打冠军训练赛（" + attrs[0] + "）：" + s + "，剩余场次：" + restMatch);
                return 1000 * 60 * 1;
            }
        }
        return 1000 * 10;
    }
    
    //变阵为高攻
    //第一维：位置；第二维：球员；第三维：属性
    private static String change2ExpertAttack(List<List<String[]>> abilitys, boolean jianrong) {
        String[] players = new String[11]; //0=gk, 1-3=d, 4-7=m, 8=m/f, 9-10=f
        //gk
        players[0] = abilitys.get(0).get(0)[0];
        abilitys.get(0).remove(0);
        //def
        int size = Math.min(3, abilitys.get(1).size());
        for(int i = 0; i < size; i++) {
            players[i + 1] = abilitys.get(1).get(0)[0];
            abilitys.get(1).remove(0);
        }
        //mid
        size = Math.min(4, abilitys.get(2).size());
        for(int i = 0; i < size; i++) {
            players[i + 4] = abilitys.get(2).get(0)[0];
            abilitys.get(2).remove(0);
        }
        //att
        size = Math.min(2, abilitys.get(3).size());
        for(int i = 0; i < size; i++) {
            players[i + 9] = abilitys.get(3).get(0)[0];
            abilitys.get(3).remove(0);
        }
        //前腰，兼容中场前锋
        int midAbility = abilitys.get(2).size() > 0 ? Integer.parseInt(abilitys.get(2).get(0)[1]) : 0;
        int attAbility = (jianrong && abilitys.get(3).size() > 0) ? Integer.parseInt(abilitys.get(3).get(0)[1]) : 0;
        if(midAbility > attAbility) {
            players[8] = abilitys.get(2).get(0)[0];
            abilitys.get(2).remove(0);
        } else {
            players[8] = abilitys.get(3).get(0)[0];
            abilitys.get(3).remove(0);
        }
        //检查是不是有位置没有set
        for(int i = 0; i < 11; i++) {
            if(players[i] == null) {
                for(int j = 1; j <= 3; j++) {
                    if(abilitys.get(j).size() > 0) {
                        players[i] = abilitys.get(j).get(0)[0];
                        abilitys.remove(j);
                        break;
                    }
                }
            }
        }
        StringBuffer ret = new StringBuffer();
        for(String p : players) {
            if(ret.length() > 0) {
                ret.append("*");
            }
            ret.append(p);
        }
        return ret.toString();
    }

    //变阵为高组
    //第一维：位置；第二维：球员；第三维：属性
    private static String change2ExpertMid(List<List<String[]>> abilitys, boolean jianrong) {
        String[] players = new String[11]; //0=gk, 1-4=d, 5-7=m, 8-9=m/f, 10=f
        //gk
        players[0] = abilitys.get(0).get(0)[0];
        abilitys.get(0).remove(0);
        //def
        int size = Math.min(4, abilitys.get(1).size());
        for(int i = 0; i < size; i++) {
            players[i + 1] = abilitys.get(1).get(0)[0];
            abilitys.get(1).remove(0);
        }
        //mid
        size = Math.min(3, abilitys.get(2).size());
        for(int i = 0; i < size; i++) {
            players[i + 5] = abilitys.get(2).get(0)[0];
            abilitys.get(2).remove(0);
        }
        //att
        size = Math.min(1, abilitys.get(3).size());
        for(int i = 0; i < size; i++) {
            players[i + 10] = abilitys.get(3).get(0)[0];
            abilitys.get(3).remove(0);
        }
        //前腰，兼容中场前锋
        for(int i = 0; i < 2; i++) {
            int midAbility = abilitys.get(2).size() > 0 ? Integer.parseInt(abilitys.get(2).get(0)[1]) : 0;
            int attAbility = (jianrong && abilitys.get(3).size() > 0) ? Integer.parseInt(abilitys.get(3).get(0)[1]) : 0;
            if(midAbility > attAbility) {
                players[8 + i] = abilitys.get(2).get(0)[0];
                abilitys.get(2).remove(0);
            } else {
                players[8 + i] = abilitys.get(3).get(0)[0];
                abilitys.get(3).remove(0);
            }
        }
        //检查是不是有位置没有set
        for(int i = 0; i < 11; i++) {
            if(players[i] == null) {
                for(int j = 1; j <= 3; j++) {
                    if(abilitys.get(j).size() > 0) {
                        players[i] = abilitys.get(j).get(0)[0];
                        abilitys.remove(j);
                        break;
                    }
                }
            }
        }
        StringBuffer ret = new StringBuffer();
        for(String p : players) {
            if(ret.length() > 0) {
                ret.append("*");
            }
            ret.append(p);
        }
        return ret.toString();
    }

    //变阵为高防
    //第一维：位置；第二维：球员；第三维：属性
    private static String change2ExpertDefend(List<List<String[]>> abilitys, boolean jianrong) {
        String[] players = new String[11]; //0=gk, 2-4=d, 1/5=d/m, 6-8=m, 9-10=f
        //gk
        players[0] = abilitys.get(0).get(0)[0];
        abilitys.get(0).remove(0);
        //def
        int size = Math.min(3, abilitys.get(1).size());
        for(int i = 0; i < size; i++) {
            players[i + 2] = abilitys.get(1).get(0)[0];
            abilitys.get(1).remove(0);
        }
        //mid
        size = Math.min(3, abilitys.get(2).size());
        for(int i = 0; i < size; i++) {
            players[i + 6] = abilitys.get(2).get(0)[0];
            abilitys.get(2).remove(0);
        }
        //att
        size = Math.min(2, abilitys.get(3).size());
        for(int i = 0; i < size; i++) {
            players[i + 9] = abilitys.get(3).get(0)[0];
            abilitys.get(3).remove(0);
        }
        //边后，兼容后卫中场
        for(int i = 0; i < 2; i++) {
            int defAbility = abilitys.get(1).size() > 0 ? Integer.parseInt(abilitys.get(1).get(0)[1]) : 0;
            int midAbility = (jianrong && abilitys.get(2).size() > 0) ? Integer.parseInt(abilitys.get(2).get(0)[1]) : 0;
            if(defAbility > midAbility) {
                players[i * 4 + 1] = abilitys.get(1).get(0)[0];
                abilitys.get(1).remove(0);
            } else {
                players[i * 4 + 1] = abilitys.get(2).get(0)[0];
                abilitys.get(2).remove(0);
            }
        }
        //检查是不是有位置没有set
        for(int i = 0; i < 11; i++) {
            if(players[i] == null) {
                for(int j = 1; j <= 3; j++) {
                    if(abilitys.get(j).size() > 0) {
                        players[i] = abilitys.get(j).get(0)[0];
                        abilitys.remove(j);
                        break;
                    }
                }
            }
        }
        StringBuffer ret = new StringBuffer();
        for(String p : players) {
            if(ret.length() > 0) {
                ret.append("*");
            }
            ret.append(p);
        }
        return ret.toString();
    }

    private static boolean checkHasUnLightedXiangqianPlayer(String in) throws Exception {
//8|圣埃蒂安|3001|10000|npc2.png|1|3|1|7|鲁菲耶*1*a*1*66*1*PlayersHead2/300501.png^古拉姆*1*b*2*65*1*France/02/10202.png^保朗*1*c*2*66*0*France/02/10203.png^马尔沙尔*1*d*2*66*1*France/02/10204.png^内里*1*e*2*67*1*France/02/10205.png^克莱芒*1*f*3*65*1*France/02/10206.png^古拉沃吉*1*g*3*67*0*France/02/10207.png^巴卡里·萨科*1*h*3*67*1*France/02/10208.png^尼科利塔*2*i*3*70*0*France/02/10214.png^格拉德尔*1*j*4*65*1*France/02/10210.png^庞格勒*2*k*4*69*0*France/02/10211.png
        String player = in.split("[|]")[9];
        String team = in.split("[|]")[1];
        String[] players = player.split("\\^");
        boolean needToFight = false;
        if(team.equals("圣埃蒂安")) { //鲁菲耶
            for(String p : players) {
                if(p.contains("鲁菲耶")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("蒙彼利埃")) { //乌塔卡，博卡利
            for(String p : players) {
                if(p.contains("博卡利") || p.contains("乌塔卡")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("里昂")) { //戈尼亚隆斯，福法纳
            for(String p : players) {
                if(p.contains("戈尼亚隆斯") || p.contains("福法纳")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("马赛")) { //芒当达，瓦尔布埃纳
            for(String p : players) {
                if(p.contains("瓦尔布埃纳") || p.contains("芒当达") || p.contains("吉尼亚克")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("里尔")) { //谢朱，朗德罗
            for(String p : players) {
                if(p.contains("谢朱") || p.contains("朗德洛")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("摩纳哥")) { //卡瓦略
            for(String p : players) {
                if(p.contains("卡瓦略")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("巴黎圣日耳曼")) { //马克斯维尔
            for(String p : players) {
                if(p.contains("马克斯维尔") || p.contains("帝亚戈·莫塔")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("帕尔马")) { //戈比
            for(String p : players) {
                if(p.contains("戈比")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("热那亚")) { //戈比
            for(String p : players) {
                if(p.contains("佩林")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("那不勒斯")) { //卡列洪
            for(String p : players) {
                if(p.contains("卡列洪")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("沙尔克04")) { //梅策尔德
            for(String p : players) {
                if(p.contains("梅策尔德")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("AC米兰")) { //阿巴特
            for(String p : players) {
                if(p.contains("阿巴特")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("阿森纳")) { //什科琴尼
            for(String p : players) {
                if(p.contains("什科琴尼")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("富勒姆")) { //贝尔巴托夫
            for(String p : players) {
                if(p.contains("贝尔巴托夫") || p.contains("斯特克伦博格")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("曼联")) { //贝尔巴托夫
            for(String p : players) {
                if(p.contains("纳尼")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else if(team.equals("皇家马德里")) { //贝尔巴托夫
            for(String p : players) {
                if(p.contains("拉莫斯")) {
                    needToFight |= p.split("[*]")[5].equals("0");
                }
            }
        } else {
            return false;
        }
        return needToFight;
    }

    private static String getOgzdOpponent(String email) throws Exception {
        String ret = null;
        String conf = ConfigUtils.getConf(email, "欧冠之巅对手");
//        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
//        List<NameValuePair> params = new ArrayList<NameValuePair>();
//        params.add(new BasicNameValuePair("type", "0"));
//        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//        String rett = IDUtils.execute(email, pm);
//        String[] dailytasks = rett.split("\\|")[0].split("∠");
//        for(String s : dailytasks) {
//            if(s.indexOf("欧冠之巅") >= 0) {
//                String taskid = s.split("\\*")[0];
//                int matchidx = Integer.parseInt(taskid) - 5100; //第一个对手是5100，以此类推
//                conf = (matchidx / 10 + 1) + "-" + (matchidx % 10 + 1);
//                break;
//            }
//        }

        if(conf != null) {
            String[] parts = conf.split("-");
            try {
                int league = Integer.parseInt(parts[0]);
                int index = Integer.parseInt(parts[1]);

                HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ShowOgzdGame1", league + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(email, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6

                String team = s.split("@")[5];
                String[] teams = team.split("&");

                ret = league + "*" + teams[index - 1].split("[|]")[0];
                return ret;
            } catch(Exception ex) {
                LOGGER.warn("解析" + email + "/" + IDUtils.getNick(email) + "的欧冠对手出错");
            }
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("OgzdGameLoad1", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);//9@2@法甲|1|1*德甲|2|0*意甲|3|0*英超|4|0*西甲|5|0
        String league = s.substring(s.lastIndexOf("@") + 1);
        String[] leagues = league.split("[*]");
        int curLeague = 0;
//        for(int i = 0; i < leagues.length; i++) {
//            if(leagues[i].split("[|]")[2].equals("1")) {
//                curLeague = i;
//                break;
//            }
//        }
        String backupTeamId = null;

        //先判断是不是已经打通,如果没有,则1、实力够则打通；2、实力不够，打到实力够的地方
        pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ShowOgzdGame1", "5"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(email, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6
        boolean needFightRM = false;
        if(Integer.parseInt(s.substring(0, s.indexOf("@"))) < 0) { //还不能打西甲
            needFightRM = true;
        } else {
            String team = s.split("@")[5];
            String[] teams = team.split("&");
//            if(!teams[9].split("\\|")[7].equals("1")) { //还不能打皇马
            if(teams[9].split("\\|")[8].equals("0")) { //皇马1张牌都没翻出来
                needFightRM = true;
            }
        }
        if(needFightRM) {
            String nextOpponent = null, opponentShili = null;
            boolean foundEnd = false;
            for(int i = 1; i <= 5; i++) {
                if(foundEnd) {
                    break;
                }
                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ShowOgzdGame1", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6
                if(Integer.parseInt(s.substring(0, s.indexOf("@"))) < 0) {
                    continue;
                }
                String team = s.split("@")[5];
                String[] teams = team.split("&");
                for(int j = 0; j < teams.length; j++) { //从前往后找最后一个打得过的
                    if(teams[j].split("\\|")[7].equals("1")) {
                        nextOpponent = i + "*" + teams[j].split("\\|")[0];
                        opponentShili = teams[j].split("\\|")[3];
                    } else {
                        foundEnd = true;
                        break;
                    }
                }
            }
            if(nextOpponent != null && Integer.parseInt(IDUtils.IDInfos.get(email).get("shili")) >= Integer.parseInt(opponentShili)) {
                return nextOpponent;
            }
        }
        /* 判断是否通关结束 */

        while(ret == null && curLeague < 5) {
            pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ShowOgzdGame1", (curLeague + 1) + ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6
//            if(Integer.parseInt(s.substring(0, s.indexOf("@"))) == -3) {
//                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("FlopCard1", (curLeague + 1) + "*1"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                IDUtils.execute(email, pm);
//
//                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("AcceptCardAward1", (curLeague + 1) + ""));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                IDUtils.execute(email, pm);
//
//                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("ShowOgzdGame1", (curLeague + 1) + ""));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                s = IDUtils.execute(email, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6
//            }
            String team = s.split("@")[5];
            String[] teams = team.split("&");
//8|圣埃蒂安|3001|10000|npc2.png|1|3|1|7|鲁菲耶*1*a*1*66*1*PlayersHead2/300501.png^古拉姆*1*b*2*65*1*France/02/10202.png^保朗*1*c*2*66*0*France/02/10203.png^马尔沙尔*1*d*2*66*1*France/02/10204.png^内里*1*e*2*67*1*France/02/10205.png^克莱芒*1*f*3*65*1*France/02/10206.png^古拉沃吉*1*g*3*67*0*France/02/10207.png^巴卡里·萨科*1*h*3*67*1*France/02/10208.png^尼科利塔*2*i*3*70*0*France/02/10214.png^格拉德尔*1*j*4*65*1*France/02/10210.png^庞格勒*2*k*4*69*0*France/02/10211.png
//78439|蒙彼利埃|3002|10500|npc1.png|2|3|1|11|茹尔德朗*1*a*1*65*1*France/01/10112.png^贝迪莫*1*b*2*66*1*France/01/10102.png^伊尔顿*1*c*2*62*1*France/01/10104.png^蒂耶内*1*d*2*65*1*PlayersHead2/301010.png^博卡利*1*e*2*67*1*PlayersHead2/301003.png^乌塔卡*2*f*3*72*1*France/01/10110.png^贝朗达*1*g*3*66*1*France/01/10107.png^马尔沃*1*h*3*62*1*France/01/10108.png^埃斯特拉达*2*i*3*71*1*France/01/10106.png^埃雷拉*1*j*4*62*1*PlayersHead2/301008.png^卡马拉*1*k*4*62*1*PlayersHead2/301009.png
//78440|索肖|3003|11000|npc6.png|3|3|1|3|P·克罗斯*1*a*1*63*1*PlayersHead2/300904.png^米卡里*1*b*2*60*1*PlayersHead2/300901.png^佩贝尔纳*2*c*2*70*0*PlayersHead2/300905.png^康德*1*d*2*67*0*PlayersHead2/300906.png^巴纳纳*2*e*2*71*0*PlayersHead2/300907.png^布德布兹*1*f*3*65*0*PlayersHead2/300902.png^杜拜*2*g*3*67*0*PlayersHead2/300908.png^普若尔*1*h*3*66*0*PlayersHead2/300909.png^特拉奥雷*2*i*3*67*0*France/300910.png^普里瓦*2*j*4*66*0*France/06/10610.png^巴卡布*1*k*4*66*1*PlayersHead2/300911.png
//78441|雷恩|3004|11521|npc5.png|4|3|1|10|科斯蒂尔*1*a*1*64*1*PlayersHead2/300808.png^卡特里纳*1*b*2*63*1*France/05/10503.png^比耶克*1*c*2*69*1*France/05/10502.png^马万加*1*d*2*63*1*France/05/10511.png^当泽*1*e*2*63*1*France/05/10515.png^皮特罗帕*1*f*3*64*1*PlayersHead2/300803.png^费雷*1*g*3*61*1*PlayersHead2/300804.png^姆维拉*4*h*3*81*1*PlayersHead2/300805.png^亚利桑德里尼*1*i*3*66*1*France/05/10516.png^埃尔丁*1*j*4*66*0*PlayersHead2/300811.png^蒙塔尼奥*1*k*4*64*1*France/05/10508.png
//78442|波尔多|3005|12021|npc4.png|5|3|1|10|D·卡拉索*3*a*1*73*1*France/04/10401.png^亨里克*1*b*2*62*1*PlayersHead2/300710.png^卢多维奇·萨内*1*c*2*64*1*PlayersHead2/300702.png^普拉尼*1*d*2*65*0*PlayersHead2/300703.png^特雷穆利纳斯*2*e*2*69*1*PlayersHead2/300704.png^茹谢*1*f*3*62*1*PlayersHead2/300706.png^普拉希尔*2*g*3*70*1*PlayersHead2/300705.png^哈勒法拉*1*h*3*62*1*France/04/10409.png^贝莱*1*i*3*60*1*PlayersHead2/300707.png^萨拉*1*j*4*64*1*PlayersHead2/300711.png^贝利昂*2*k*4*71*1*France/04/10411.png
//78443|里尔|3006|12517|npc10.png|6|3|1|7|朗德罗*3*a*1*79*1*France/10/11001.png^贝里亚*1*b*2*62*1*France/10/11005.png^马尔科·巴萨*2*c*2*70*1*France/10/11003.png^谢朱*1*d*2*69*1*France/10/11004.png^罗泽纳尔*2*e*2*72*1*France/10/11013.png^佩德雷蒂*3*f*3*78*0*France/10/11009.png^马武巴*3*g*3*72*0*France/10/11007.png^巴尔蒙*1*h*3*61*0*France/10/11008.png^古埃耶*1*i*3*63*1*PlayersHead2/300610.png^德梅洛*1*j*4*60*1*France/10/11010.png^卡卢*4*k*4*81*0*France/10/40817.png
//78444|里昂|3007|13037|npc8.png|7|3|1|11|韦库特*1*a*1*60*1*France/08/10812.png^雷韦埃*2*b*2*67*1*France/08/10805.png^C·克里斯*2*c*2*68*1*France/08/10804.png^比瓦舍奇*2*d*2*66*1*PlayersHead2/300408.png^达博*2*e*2*67*1*PlayersHead2/300409.png^马尔布兰克*3*f*3*77*1*France/08/10818.png^古尔库夫*4*g*3*84*1*France/08/10807.png^戈尼亚隆斯*2*h*3*72*1*France/08/10819.png^福法纳*1*i*3*68*1*France/08/10808.png^戈米斯*2*j*4*68*1*France/08/10811.png^利桑德罗*3*k*4*74*1*France/08/10810.png
//78445|马赛|3008|13510|npc9.png|8|3|1|10|芒当达*2*a*1*72*1*France/09/10901.png^S·迪亚瓦拉*1*b*2*61*1*France/09/10904.png^法尼*1*c*2*63*1*PlayersHead2/300303.png^恩库卢*2*d*2*72*1*France/09/10913.png^莫雷尔*1*e*2*61*1*France/09/10902.png^罗马奥*3*f*3*77*0*France/300306.png^约尔丹·阿尤*1*g*3*65*1*France/09/10906.png^谢鲁*1*h*3*64*1*France/09/10909.png^瓦尔布埃纳*2*i*3*73*1*France/09/10917.png^吉尼亚克*4*j*4*84*1*France/09/10911.png^雷米*1*k*4*59*1*France/09/10910.png
//78446|摩纳哥|3009|14006|npc3009.png|9|3|1|3|苏巴什奇*3*a*1*77*1*France/300201.png^法比尼奥*3*b*2*77*0*France/300205.png^卡瓦略*4*c*2*84*0*Spain/09/50903.png^阿比达尔*5*d*2*85*0*Spain/10/51011.png^库尔扎瓦*3*e*2*76*0*France/300204.png^奥巴迪*3*f*3*77*0*France/300207.png^奥坎波斯*4*g*3*82*1*France/300208.png^图拉朗*4*h*3*76*0*Spain/05/50508.png^J.罗德里格斯*3*i*3*76*0*France/300209.png^R·法尔考*5*j*4*92*1*Spain/07/50710.png^里维埃尔*4*k*4*83*0*France/300211.png
//78447|巴黎圣日耳曼|3010|14508|npc7.png|10|2|1|3|西里古*5*a*1*87*0*PlayersHead2/300101.png^马奎尼奥斯*4*b*2*78*0*Super/10720.png^T·席尔瓦*5*c*2*90*0*France/07/31003.png^加列特*3*d*2*74*0*PlayersHead2/300111.png^马克斯维尔*3*e*2*79*1*France/07/51002.png^马图伊迪*1*f*3*63*1*PlayersHead2/300102.png^蒂亚戈·莫塔*4*g*3*84*1*France/07/30916.png^小卢卡斯*4*h*3*80*0*Super/10719.png^帕斯托雷*5*i*3*90*0*France/07/10709.png^卡瓦尼*6*j*4*93*0*Italy/06/30611.png^伊布*6*k*4*98*0*France/07/31010.png
            for(int i = 0; i < teams.length; i++) {
                String[] subs = teams[i].split("[|]");
                if(i == 0 && curLeague == 0) {
                    backupTeamId = (curLeague + 1) + "*" + subs[0];
//                    ret = (curLeague + 1) + "*" + subs[0];
                }
                if(subs[8].equals("11")) {
                    continue;
                }
                if(Integer.parseInt(subs[3]) > Integer.parseInt(IDUtils.IDInfos.get(email).get("shili"))) {
                    ret = backupTeamId;
                    break;
                }
                if(subs[7].equals("1")) {
                    if(Integer.parseInt(subs[8]) < 7) { //只要开出来的球员数少于7个，就直接打这个球队
                        ret = (curLeague + 1) + "*" + subs[0];
                        break;
                    } else if(checkHasUnLightedXiangqianPlayer(teams[i])) {
                        ret = (curLeague + 1) + "*" + subs[0];
                        break;
                    } else {
                        backupTeamId = (curLeague + 1) + "*" + subs[0];
                    }
//                    ret = (curLeague + 1) + "*" + subs[0];
                } else {
                    ret = backupTeamId;
                    break;
                }
            }
            curLeague++;
        }
        if(ret == null) {
            ret = backupTeamId;
        }
        return ret;
    }

    private static int[] getAbility(Map<String, String> s, String key) {
        return new int[]{Integer.parseInt(s.get(key)), Integer.parseInt(s.get(key + "m"))};
    }

    public static boolean needRemainPlayer(String email, String name, String level, String pos, String ability, boolean showlog) {
//        LOGGER.info(email + "/" + IDUtils.getNick(email) + "检查球员保留：" + name+"/品质" + level+"/位置"+pos+"/能力"+ability);
        boolean ret = false;
        String conf = ConfigUtils.getConf(email, "球员保留");
        if(Integer.parseInt(level) >= Integer.parseInt(conf)) {
            LOGGER.debug(email + "/" + IDUtils.getNick(email) + "品质="+level+"大于配置品质"+conf+"，保留");
            ret = true;
        } else {
            if(Integer.parseInt(level) < 3) {
                LOGGER.debug(email + "/" + IDUtils.getNick(email) + "品质<3，放弃");
                ret = false;
            } else {
                int[] curValues = IDUtils.checkPlayer(name, pos, level);
                if(curValues[0] == 0 && curValues[1] == 0) {
                    LOGGER.debug(email + "/" + IDUtils.getNick(email) + "没有球员信息，保留");
                    ret = true;
                } else {
                    if("1".equals(ConfigUtils.getConf(email, "搜集资料"))) {
                        if(curValues[0] != Integer.parseInt(ability)) {
                            LOGGER.debug(email + "/" + IDUtils.getNick(email) + "搜集资料，球员能力="+ability+"和资料信息"+curValues[0]+"不相等，保留");
                            ret = true;
                        } else if(curValues[1] == 0) {
                            LOGGER.debug(email + "/" + IDUtils.getNick(email) + "搜集资料，球员能力="+ability+"没有满级资料，保留");
                            ret = true;
                        }
                    }
                }
//                ret = false;
            }
        }
//        if(!ret && !"1".equals(ConfigUtils.getConf(email, "搜集资料"))) {
        if(!ret) {
            if(!email.startsWith("txjcf")) {
                ret = IDUtils.isXiangQianPlayer(name, pos, level);
                LOGGER.debug(email + "/" + IDUtils.getNick(email) + "，球员是镶嵌球员？" + ret);
            }
        }
        if(!ret && "1".equals(ConfigUtils.getConf(email, "搜集资料"))) {
            ret = !IDUtils.hasXiangQianInfo(name, pos, level);
            LOGGER.debug(email + "/" + IDUtils.getNick(email) + "，没有镶嵌数据？true=没数据" + ret);
        }
        if(ret && showlog) {
            LOGGER.info(email + "最终结果，" + name+"/品质" + level+"/位置"+pos+"/能力"+ability + "是否保留？" + ret);
        }
        return ret;
    }

    public static String qiuyuanchengzhang(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Player.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("QyChengzhang2", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        trainFull(email, playerId, false);
        return str;
    }

    public static String xiangqian(String email, String playerId, String xiangqianId) throws Exception {
//        Map<String, String> playerDetail = OperationUtils.viewPlayer(email, xiangqianId);
        Map<String, String> playerDetail = CacheManager.loadPlayer(email, xiangqianId, false);
        String level = playerDetail.get("pinzhi");

        HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ClMosaic1", playerId + "*" + xiangqianId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        defaults(email, 7);
        CacheManager.loadPlayer(email, playerId, true);

        try {
            int l = 0;
            if("普通".equals(level)) {
                l = 1;
            } else if("优秀".equals(level)) {
                l = 2;
            } else if("精英".equals(level)) {
                l = 3;
            } else if("杰出".equals(level)) {
                l = 4;
            } else if("大牌".equals(level)) {
                l = 5;
            } else if("超级巨星".equals(level)) {
                l = 6;
            }
            IDUtils.deleteXiangqianPlayer(email, l, xiangqianId);
//            new TeamXiangqianCacheThread(ids, l).start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return str;
    }

    public static String fullTrain(String email, String playerId, int level) throws Exception {
//        Map<String, String> detail = viewPlayer(email, playerId);
        Map<String, String> detail = CacheManager.loadPlayer(email, playerId, false);
        int currentExp = Integer.parseInt(detail.get("jingyan").split("/")[0]);

        List<Map<String, String>> items = OperationUtils.listBags(email, "0");
        List<int[]> cards = new ArrayList<int[]>(); //手上拥有的球员经验卡，按从小到大的顺序
        int totalTrain = 0;
        int rest = 500; //普通球员升满需要500经验
        if(level == 2) { //优秀
            rest = 1800;
        } else if(level == 3) { //精英
            rest = 6000;
        } else if(level == 4) { //杰出
            rest = 37500;
        } else if(level == 5) { //大牌
            rest = 75000;
        } else if(level == 6) { //巨星
            rest = 122500;
        }
        rest = rest - currentExp;

        for(Map<String, String> item : items) {
            if(item.get("name").startsWith("球员经验卡")) {
                int code = Integer.parseInt(item.get("id"));
                int number = Integer.parseInt(item.get("number"));
                String key = item.get("name");
                key = key.substring(key.indexOf("球员经验卡") + "球员经验卡".length());
                key = key.substring(0, key.indexOf("点"));
                int value = Integer.parseInt(key);
                if((value - rest) > 50) {
                    continue;
                }
                int[] toAdd = new int[]{value, number, code};
                boolean add = false;
                for(int i = 0; i < cards.size(); i++) {
                    if(cards.get(i)[0] > value) {
                        cards.add(i, toAdd);
                        add = true;
                        break;
                    }
                }
                if(!add) {
                    cards.add(toAdd);
                }
                totalTrain += number * value;
            }
        }
        if(totalTrain < rest) {
            return "球员经验点不够升级";
        }

        items = OperationUtils.listBags(email, "1");//检查合同够不够
        boolean[] contracts = new boolean[11];
        for(Map<String, String> item : items) {
            int itemCode = Integer.parseInt(item.get("itemid"));
            if(itemCode >= 1101 && itemCode <= 1110) {
                contracts[itemCode - 1100] = true;
            }
        }

        int levelUpTime = 2;
        if(level == 2) { //优秀
            levelUpTime = 3;
        } else if(level == 3) { //精英
            levelUpTime = 4;
        } else if(level == 4) { //杰出
            levelUpTime = 6;
        } else if(level == 5) { //大牌
            levelUpTime = 7;
        } else if(level == 6) { //巨星
            levelUpTime = 8;
        }
        int currLevel = Integer.parseInt(detail.get("level"));
        for(int i = currLevel + 1; i <= levelUpTime + 1; i++) {
            if(!contracts[i]) {
                return "球员合同不够：" + i;
            }
        }

        for(int i = cards.size() - 1; i >= 0; i--) {
            if(rest <= 0) {
                break;
            }
            if((cards.get(i)[0] - rest) > 50) {
                continue;
            }
            for(int j = 0; j < cards.get(i)[1]; j++) {
                HttpPost pm = new HttpPost(OgzqURL.URL + "/Bag.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "8"));
                params.add(new BasicNameValuePair("playerid", playerId));
                params.add(new BasicNameValuePair("aii", cards.get(i)[2] + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(email, pm);
                Thread.sleep(500);
                rest = rest - cards.get(i)[0];
                if(cards.get(i)[0] > rest) {
                    break;
                }
            }
        }

        while(currLevel < levelUpTime + 1) {
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Upgrade1", playerId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
            Thread.sleep(500);
            detail = CacheManager.loadPlayer(email, playerId, false);
            currLevel = Integer.parseInt(detail.get("level"));
        }
//        for(int i = 0; i < levelUpTime; i++) { //普通球员满级=3，需要升级2次
//        }

        //训练
//        Map<String, String> playerDetails = OperationUtils.viewPlayer(email, playerId);
        Map<String, String> playerDetails = CacheManager.loadPlayer(email, playerId, true);
//        UpPlayer1=793231*62*164*395*429*159*307*50*50*3
        String str = playerId + "*" + playerDetails.get("shemenm") + "*" + playerDetails.get("tupom") + "*" +
                playerDetails.get("duanqium") + "*" + playerDetails.get("chanqium") + "*" +
                playerDetails.get("chuanqium") + "*" + playerDetails.get("sudum") + "*" +
                playerDetails.get("pujium") + "*" + playerDetails.get("chujim") + "*" +
                playerDetails.get("tipcount");
        trainPlayer(email, str);
        return "done";
    }

    public static String openXiubiao(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Player.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ArmBand_Open1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        defaults(email, 7);
        CacheManager.loadPlayer(email, playerId, true);
        return str;
    }

    //读取战术中心中某个品质的球员可否镶嵌信息
    public static List<String[]> loadXiangqianTactic(String email, int level) throws Exception {
        String str = null;
        List<String[]> ret = new ArrayList<String[]>();
        while(str == null) {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ZszxLoad1", "" + level));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            str = IDUtils.execute(email, pm);
        }
        int size = Integer.parseInt(str.split("@")[2]);
        String dating = str.split("@")[0];
        String[] scoreplayers = dating.split("[*]");
        for(String p : scoreplayers) {
            if(p.split("#")[0].equals("-2")) {
                continue;
            }
            String[] attrs = p.split("[|]");
            if(attrs[3].equals(level + "")) {
                if(attrs[7].split("#")[0].equals("1")) {
                    ret.add(new String[]{attrs[0], attrs[2], attrs[3], attrs[4], attrs[5], "1"});
                } else {
                    ret.add(new String[]{attrs[0], attrs[2], attrs[3], attrs[4], attrs[5], "0"});
                }
            }
        }

        int pagecount = (size - 1) / 16;
        for(int j = 0; j <= (size - 1) / 16; j++) {
            System.out.println("品质：" + level + "，第" + j + "页，共" + (pagecount + 1) + "页，id=" + email);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("PageIndex1", j + "*" + level));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            str = IDUtils.execute(email, pm);
            if(str.equals("")) {
                continue;
            }
//            if(j == 0) {
//                String dating = str.split("@")[0];
//                String[] players = dating.split("[*]");
//                for(String p : players) {
//                    String[] attrs = p.split("[|]");
//                    if(attrs[7].split("#")[0].equals("1")) {
//                        ret.add(new String[]{attrs[0], attrs[2], attrs[3], attrs[4], attrs[5]});
//                    }
//                }
//            }
            String[] players;
            if(str.indexOf("@") >= 0) {
                players = str.split("@")[1].split("[*]");
            } else {
                players = str.split("[*]");
            }
            for(String p : players) {
                String[] attrs = p.split("[|]");
                if(attrs[3].equals(level + "")) {
                    if(attrs[7].split("#")[0].equals("1")) {
                        ret.add(new String[]{attrs[0], attrs[2], attrs[3], attrs[4], attrs[5], "1"});
                    } else {
                        ret.add(new String[]{attrs[0], attrs[2], attrs[3], attrs[4], attrs[5], "0"});
                    }
                }
            }
        }
        ret = SortUtils.sortXiangqianPlayer(ret, 0, ret.size());
        return ret;
    }

    public static String backFromTacti(String email, String playerId)  throws Exception {
        try {
//            Map<String, String> detail = viewPlayer(email, playerId);
            Map<String, String> detail = CacheManager.loadPlayer(email, playerId, false);
            int pinzhi = 0;
            if(detail.get("pinzhi").equals("普通")) {
                pinzhi = 1;
            } else if(detail.get("pinzhi").equals("优秀")) {
                pinzhi = 2;
            } else if(detail.get("pinzhi").equals("精英")) {
                pinzhi = 3;
            } else if(detail.get("pinzhi").equals("杰出")) {
                pinzhi = 4;
            } else if(detail.get("pinzhi").equals("大牌")) {
                pinzhi = 5;
            } else if(detail.get("pinzhi").equals("超级巨星")) {
                pinzhi = 6;
            }
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Tactics.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getbacksure", "2"));
            params.add(new BasicNameValuePair("KCindex", playerId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(email, pm);

            pm = new HttpPost(OgzqURL.URL + "/Tactics.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getbackLB", "1"));
            params.add(new BasicNameValuePair("playerid", playerId));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
            if("-1".equals(ret)) {
                return "您的银币不足";
            } else if("-10".equals(ret)) {
                return "替补位已满";
            } else if("-8".equals(ret)) {
                return "该球员已挂靴，不能返场";
            } else if("1".equals(ret)) {
                IDUtils.deleteXiangqianPlayer(email, pinzhi, playerId);
                return "返场成功";
            } else {
                return "未知错误";
            }
        } catch(Exception ex) {
            return "出现异常：" + ex.getClass().getName() + "|" + ex.getMessage();
        }
    }

    public static String upQiuyuanyishi(String email, String playerId, String jingong, String zuzhi, String fangshou) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("ZhanshuyishiSave1", playerId + "*" + jingong + "*" + zuzhi + "*" + fangshou));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        defaults(email, 7);
        return s;
    }

    //自动训练球员，full ? 包括次要属性 : 不包括次要属性
    public static String trainFull(String email, String playerId, boolean full) throws Exception {
        Map<String, String> playerDetails = CacheManager.loadPlayer(email, playerId, true);
//        Map<String, String> playerDetails = OperationUtils.viewPlayer(email, playerId);
//        UpPlayer1=793231*62*164*395*429*159*307*50*50*3
        full = true;
        if(full) {
            String str = playerId + "*" + playerDetails.get("shemenm") + "*" + playerDetails.get("tupom") + "*" +
                    playerDetails.get("duanqium") + "*" + playerDetails.get("chanqium") + "*" +
                    playerDetails.get("chuanqium") + "*" + playerDetails.get("sudum") + "*" +
                    playerDetails.get("pujium") + "*" + playerDetails.get("chujim") + "*" +
                    playerDetails.get("tipcount");
            trainPlayer(email, str);
            return "done";
        }
        if(playerDetails.get("pos").equals("门将")) {
            String str = playerId + "*" + playerDetails.get("shemen") + "*" + playerDetails.get("tupo") + "*" +
                    playerDetails.get("duanqium") + "*" + playerDetails.get("chanqium") + "*" +
                    playerDetails.get("chuanqium") + "*" + playerDetails.get("sudum") + "*" +
                    playerDetails.get("pujium") + "*" + playerDetails.get("chujim") + "*" +
                    playerDetails.get("tipcount");
            trainPlayer(email, str);
            return "done";
        } else {
            String str = playerId + "*" + playerDetails.get("shemenm") + "*" + playerDetails.get("tupom") + "*" +
                    playerDetails.get("duanqium") + "*" + playerDetails.get("chanqium") + "*" +
                    playerDetails.get("chuanqium") + "*" + playerDetails.get("sudum") + "*" +
                    playerDetails.get("pujiu") + "*" + playerDetails.get("chuji") + "*" +
                    playerDetails.get("tipcount");
            trainPlayer(email, str);
            return "done";
        }
//        return "done";
    }

    public static List<String[]> sjkUpCost(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/SJK.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("SJKUp1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
//0@
//7*
//2533|3|Super/10041.png|里杰卡尔德|6|643060|7*
//9|500|50000*
//504|2|Activity1/220205.png|▲施梅尔策|4|869686#
//555|2|Spain/10/51011.png|阿比答尔|5|567610#
//808|2|Spain/10/51014.png|丹尼·阿尔维斯|5|587926#
//636|3|Spain/09/50909.png|迪玛利亚|5|576754#
//850|3|Super/10038.png|候选·小白|5|583270#
//504|3|Spain/10/51005.png|金靴小法|4|693262#
//855|3|Germany/10/21006.png|李贝里|6|572674#
//803|2|Spain/09/50902.png|马塞洛|5|565606#
//782|3|England/10/41009.png|纳尼|5|544114#
//821|1|Germany/10/21001.png|诺伊尔|6|541606#
//650|2|Spain/10/51012.png|皮克|5|592624#
//828|3|Spain/10/51015.png|伊涅斯塔|6|564034
        s = s.split("@")[1];
        String[] parts = s.split("\\*");
        List<String[]> ret = new ArrayList<String[]>();
        ret.add(new String[]{email, playerId, parts[1].split("\\|")[3], parts[2].split("\\|")[1], parts[2].split("\\|")[2]}); //email, 三剑客id，三剑客姓名，消耗球员实力，消耗银币
        parts = parts[3].split("#");
        for(int i = 0; i < parts.length; i++) {
            String[] attrs = parts[i].split("\\|");
            ret.add(new String[]{attrs[5], attrs[3], attrs[1], attrs[4], attrs[0]}); //球员id，球员名字，位置，品质，实力
        }
        return ret;
    }

    public static List<String[]> steelpowerUpCost(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/SteelPower.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LevelupPID", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
//0@
//7*
//2533|3|Super/10041.png|里杰卡尔德|6|643060|7*
//9|500|50000*
//504|2|Activity1/220205.png|▲施梅尔策|4|869686#
//555|2|Spain/10/51011.png|阿比答尔|5|567610#
//808|2|Spain/10/51014.png|丹尼·阿尔维斯|5|587926#
//636|3|Spain/09/50909.png|迪玛利亚|5|576754#
//850|3|Super/10038.png|候选·小白|5|583270#
//504|3|Spain/10/51005.png|金靴小法|4|693262#
//855|3|Germany/10/21006.png|李贝里|6|572674#
//803|2|Spain/09/50902.png|马塞洛|5|565606#
//782|3|England/10/41009.png|纳尼|5|544114#
//821|1|Germany/10/21001.png|诺伊尔|6|541606#
//650|2|Spain/10/51012.png|皮克|5|592624#
//828|3|Spain/10/51015.png|伊涅斯塔|6|564034
        s = s.split("@")[1];
        String[] parts = s.split("\\*");
        List<String[]> ret = new ArrayList<String[]>();
        ret.add(new String[]{email, playerId, parts[1].split("\\|")[3], parts[2].split("\\|")[1], parts[2].split("\\|")[2]}); //email, 三剑客id，三剑客姓名，消耗球员实力，消耗银币
        parts = parts[3].split("#");
        for(int i = 0; i < parts.length; i++) {
            String[] attrs = parts[i].split("\\|");
            ret.add(new String[]{attrs[5], attrs[3], attrs[1], attrs[4], attrs[0]}); //球员id，球员名字，位置，品质，实力
        }
        return ret;
    }

    public static String sjkUp(String email, String playerId, String costPlayerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/SJK.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpgradePlayer1", costPlayerId + "*" + playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        try {
            int i = Integer.parseInt(s);
            if(!s.equals(IDUtils.IDInfos.get(email).get("shili"))) { //实力变化，说明全攻全守等级变了
                pm = new HttpPost(OgzqURL.URL + "/SJK.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("SJKLoad1", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(email, pm);
                String[] parts = ret.split("\\*");
                IDUtils.IDInfos.get(email).put("sjk", parts[0]);
            }
            IDUtils.IDInfos.get(email).put("shili", s);

//            Map<String, String> playerDetail = OperationUtils.viewPlayer(email, costPlayerId);
            Map<String, String> playerDetail = CacheManager.loadPlayer(email, costPlayerId, false);
            String level = playerDetail.get("pinzhi");
            int l = 0;
            if("普通".equals(level)) {
                l = 1;
            } else if("优秀".equals(level)) {
                l = 2;
            } else if("精英".equals(level)) {
                l = 3;
            } else if("杰出".equals(level)) {
                l = 4;
            } else if("大牌".equals(level)) {
                l = 5;
            } else if("超级巨星".equals(level)) {
                l = 6;
            }
            IDUtils.deleteXiangqianPlayer(email, l, costPlayerId);
        } catch(Exception ex) {}
        return s;
    }

    public static String steelpowerUp(String email, String playerId, String costPlayerId) throws Exception {
        Map<String, String> playerDetail = CacheManager.loadPlayer(email, costPlayerId, false);
        String level = playerDetail.get("pinzhi");

        HttpPost pm = new HttpPost(OgzqURL.URL + "/SteelPower.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LevelUpid1", playerId));
        params.add(new BasicNameValuePair("tacticsplayerid", costPlayerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        try {
            int i = Integer.parseInt(s);
            IDUtils.IDInfos.get(email).put("shili", s);

            int l = 0;
            if("普通".equals(level)) {
                l = 1;
            } else if("优秀".equals(level)) {
                l = 2;
            } else if("精英".equals(level)) {
                l = 3;
            } else if("杰出".equals(level)) {
                l = 4;
            } else if("大牌".equals(level)) {
                l = 5;
            } else if("超级巨星".equals(level)) {
                l = 6;
            }
            IDUtils.deleteXiangqianPlayer(email, l, costPlayerId);
        } catch(Exception ex) {}
        return s;
    }

    public static String xiangqianGem(String email, String playerId, String gemId, String itemType) throws Exception {
        /*
        1、卸装备
        2、嵌宝石
        3、装备
         */
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("itemtype", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String[] items = ret.split("[|]");
        List<String> oldItemIds = new ArrayList<String>();
        for(String s : items) {
            oldItemIds.add(s.split("\\*")[0]);
        }
        removeEquipment(email, playerId, itemType);

        pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("itemtype", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        items = ret.split("[|]");
        String itemId = null;
        String itemCode = null;
        for(String s : items) {
            if(oldItemIds.indexOf(s.split("\\*")[0]) < 0) {
                itemId = s.split("\\*")[0];
                itemCode = s.split("\\*")[11];
                break;
            }
        }
        if(itemId == null) {
            return "找不到卸下的装备";
        }

        pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Xiangqian1", itemId + "*" + gemId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        System.out.println("宝石镶嵌结果：" + ret);
//        todo
        
        wearEquipment(email, playerId, itemId, itemCode);
        return "宝石镶嵌成功";
    }

    public static String upClubBuff(String email, String buffIndex, String teamId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "21"));
        params.add(new BasicNameValuePair("TechnologyIndex", buffIndex));
        params.add(new BasicNameValuePair("teamid", teamId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        return s;
    }

    public static String jiqingxunlian(String email, String player) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Player.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("JqShengji1", player));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        trainFull(email, player, false);
        return s;
    }

    //三剑三马成长
    public static String challengeAddPower(String email, String player) throws Exception {
        String name = "";
        if(player.equals("Rijkaard")) {
            name = "里杰卡尔德";
        } else if(player.equals("Basten")) {
            name = "范·巴斯滕";
        } else if(player.equals("Gullit")) {
            name = "古力特";
        } else if(player.equals("Bremer")) {
            name = "布雷默";
        } else if(player.equals("Mateus")) {
            name = "马特乌斯";
        } else if(player.equals("Klinsmann")) {
            name = "克林斯曼";
        } else {
            return "未知球员";
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/" + player + "_AddPower.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if(s.equals(("1"))) {
        } else if(s.equals("-1")) {
            return "通关【布雷默生涯闯关】才可以增加实力哦！";
        } else if(s.equals("-2")) {
            return "初始能力已达最大，不能再增加了！";
        } else if(s.equals("-3")) {
            return "没有布雷默！";
        } else if(s.equals("-4")) {
            return "少年布雷默数量不足！";
        } else if(s.equals("-5")) {
            return "所需材料数量不足！";
        } else if(s.equals("-6")) {
            return "请卸下少年布雷默身上的装备！";
        } else {
            return "操作异常，请重新操作！";
        }
        List<Map<String, String>> players = viewTeam(email);
        for(int i = 1; i < players.size(); i++) {
            Map<String, String> m = players.get(i);
            if(m.get("name").equals(name) && m.get("pinzhi").equals("6")) {
                String id = m.get("id");
                return trainFull(email, id, false);
            }
        }
        return "已签约，解雇状态未知";
    }

    //三剑三马成长少年签约
    public static String signChallengePlayer(String email, String player) throws Exception {
        String name = "";
        if(player.equals("Rijkaard")) {
            name = "里杰卡尔德";
        } else if(player.equals("Basten")) {
            name = "范·巴斯滕";
        } else if(player.equals("Gullit")) {
            name = "古力特";
        } else if(player.equals("Bremer")) {
            name = "布雷默";
        } else if(player.equals("Mateus")) {
            name = "马特乌斯";
        } else if(player.equals("Klinsmann")) {
            name = "克林斯曼";
        } else {
            return "未知球员";
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/" + player + "_Challenge.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getPlayer", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if(s.equals("1")) {

        } else {
            if(s.equals("-1")) {
                return "不满足签约条件！";
            } else if(s.equals("-2")) {
                return "已签约该球员！";
            } else if(s.equals("-3")) {
                return "替补席已满,请清理后再签约！";
            } else {
                return "操作异常，请重新操作！";
            }
        }
        List<Map<String, String>> players = viewTeam(email);
        for(int i = 1; i < players.size(); i++) {
            Map<String, String> m = players.get(i);
            if(m.get("name").equals("少年" + name) && m.get("ability").equals("80") &&
                    m.get("pinzhi").equals("4") && m.get("tibu").equals("替补")) {
                String id = m.get("id");
                return fireToTactic(email, id);
            }
        }
        return "已签约，解雇状态未知";
    }

    /**
     * 打贝利比赛
     * @param email
     * @return -1:异常，0:收工，1:比赛中，2:正常
     */
    public static String doPele(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "Load_ZBS"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if(s.indexOf("inmatch") >= 0) {
            return "1";
        }
//自己信息  @  玩家1*玩家2*玩家3*玩家4*玩家5   @  还能挑战次数   @  挑战记录  @排名奖励(徽章数目)
//自己信息：clubid_Kfc|球队名|实力|排名|连胜
//玩家信息：clubid_Kfc(正负)|球队头像|球队名|球队等级
//挑战记录：记录1|记录2|记录3
//result = "1169|范特西足球|94500|无|0@14696|UC.png|幸福|86*-17478|npc60.png|铁血hm|86*4473|UC.png|梦幻舰队梦幻舰队梦幻舰队|84*6857|UC.png|穆元帅|84*14920|UC.png|阿瑞尔梅洛|84@12@你挑战欧阳夏丹s1,你胜利了，排名升至100|你挑战欧阳夏丹s2,你胜利了，排名升至100|你挑战欧阳夏丹s3,你胜利了，排名升至100@1000";
        String[] parts = s.split("@");
        String restTime = parts[2];
        if(Integer.parseInt(restTime) <= 0) {
            /* 翻牌 */
            while(true) {
                pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "tiaoZhanCiShu"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String times = IDUtils.execute(email, pm);
                if(Integer.parseInt(times) >= 6) {
                    LOGGER.info(IDUtils.getNick(email) + "可以贝利翻牌");
                    pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("request", "LoadFanPai"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String result = IDUtils.execute(email, pm);
                    //ItemCode | ItemName * ItemCode | ItemName * ItemCode | ItemName * ItemCode | ItemName... @ 翻牌花费 * 偷看次数 * 提示次数
                    //result = "1106|合同6级*0|0*0|0*0|0*0|0*0|0*0|0*0|0*0|0@60*10*10";
                    String cost = result.split("@")[1].split("\\*")[0];
                    if(cost.equals("0")) {
                        String[] fanpaijilu = result.split("@")[0].split("\\*");
                        int idx = 0;
                        for(int i = 0; i < fanpaijilu.length; i++) {
                            if(fanpaijilu[i].equals("0|0|0") || fanpaijilu[i].equals("0|0")) {
                                idx = i;
                                break;
                            }
                        }
                        pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("request", "TurnCard"));
                        params.add(new BasicNameValuePair("index", (idx + 1) + ""));
                        params.add(new BasicNameValuePair("type", "0"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        result = IDUtils.execute(email, pm);
                        LOGGER.info(IDUtils.getNick(email) + "贝利翻牌结果：" + result);
                        continue;
                    } else { //领取
                        pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("request", "fanPaiLingQu"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        result = IDUtils.execute(email, pm);
                        LOGGER.info(IDUtils.getNick(email) + "贝利翻牌领取结果：" + result);
                    }
                } else {
                    LOGGER.info(IDUtils.getNick(email) + "贝利翻牌次数没了");
                    break;
                }
            }

            return "0";
        }
        String[] teams = parts[1].split("\\*");
        for(String t : teams) {
            if(Integer.parseInt(t.split("\\|")[0]) < 0) {
                pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "tiaoZhan"));
                params.add(new BasicNameValuePair("clubid_kfc", Math.abs(Integer.parseInt(t.split("\\|")[0])) + ""));
                params.add(new BasicNameValuePair("type", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String match = IDUtils.execute(email, pm);
                if("-1".equals(match)) {
                    match = "当前时间不可挑战";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "0";
                } else if ("-2".equals(match)) {
                    match = "挑战次数不足";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "0";
                } else if ("-3".equals(match)) {
                    match = "挑战玩家排名已发生变化";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "-1";
                } else if ("-4".equals(match)) {
                    match = "对手处于免战状态";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "-1";
                } else if ("-5".equals(match)) {
                    match = "挑战异常";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "-1";
                } else if ("-6".equals(match)) {
                    match = "挑战次数不足";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "0";
                } else if ("-7".equals(match)) {
                    match = "金币不足";
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "0";
                } else if (Integer.parseInt(match) > 0) {// 有比赛
                    match = "比赛ID: " + match;
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，打贝利比赛：" + match);
                    return "2";
                } else {
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "剩余场次=" + restTime + "，结果未知：" + match);
                    return "-1";
                }
            }
        }
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "打贝利比赛，对手未知：" + parts[1]);
        return "2";
    }

    public static void leagueSignin(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/LeagueSign.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);

        pm = new HttpPost(OgzqURL.URL + "/LeagueSign.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GuessNum", "" + new Random().nextInt(10)));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(email, pm);
    }

    public static void dailySignin(String email) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(new Date());
        if(today.equals(IDUtils.SigninDate.containsKey(email))) {
            return;
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "Load"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);

        pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "qianDao"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
//            sb.append(ids.indexOf(id)).append("/").append(ids.size()).append(": ").append(s).append("<br/>");

        if(str.indexOf("╋") >= 0) {
            str = str.substring(0, str.indexOf("╋"));
        }
        if(str.equals("")) {
//                sb.append("no result<br/>");
//                continue;
            return;
        }
        String needReset = str.split("@")[1];
        str = str.split("@")[2];
        //result = "1.26|1|0*1.27|1|0*1.28|1|1*1.29|0|0*1.30|0|0*1.31|0|0*2.1|0|0@1@2天奖励|1*4天奖励|0*7天奖励|0";
        for(int i = 0; i < 3; i++) { //2天奖励|1*4天奖励|0*7天奖励|0";
            if(str.split("\\*")[i].split("\\|")[1].equals("1")) {
                pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "lingQu"));
                params.add(new BasicNameValuePair("index", "" + (i + 1)));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                LOGGER.info(IDUtils.getNick(email) + "领取日常签到奖励：" + s);
//                    sb.append("领奖").append(i).append(":").append(s).append("<br/>");
                if(i == 2) { //

                }
            }
        }
        if("0".equals(needReset)) {
            pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "reset"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            LOGGER.info(IDUtils.getNick(email) + "日常签到重置:" + s);
//                sb.append("重置：").append(s).append("<br/>");
        }
        IDUtils.SigninDate.put(email, today);
        return;
    }

    public static String autoUpJieshuo(String email) throws Exception {
        //1、获取最nb的教练
        //2、获取可以升级的道具
        //3、使用
        //4、升级
        HttpPost pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("request", "Load"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);

        String[] infos = s.split("@");
        int[] exps = new int[3];
        int coachidx = 0;
        for(int i = 0; i < 3; i++) {
            String[] info = infos[i].split("\\&");
            if("1".equals(info[0])) {
                exps[i] = Integer.parseInt(info[2].split("\\*")[1]) + Integer.parseInt(info[1].split("\\*")[1]) * 10000000;
            } else {
                exps[i] = 0;
            }
        }
        if(exps[0] > exps[1]) {
            if(exps[0] > exps[2]) {
                coachidx = 1;
            } else {
                coachidx = 3;
            }
        } else {
            if(exps[1] > exps[2]) {
                coachidx = 2;
            } else {
                coachidx = 3;
            }
        }

        List<Map<String, String>> items = listCoachBags(email, "5");
        for(Map<String, String> item : items) {
            String code = item.get("itemCode");
            if(code.startsWith("55")) {
                int count = Integer.parseInt(item.get("count"));
                for(int i = 0; i < count; i++) {
                    useJieshuoItem(email, code, coachidx + "");
                }
            }
        }

        while(true) {
            pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "sheng"));
            params.add(new BasicNameValuePair("index", coachidx + ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            if(!"1".equals(s)) {
                break;
            }
        }
        defaults(email, 7);
        return "done";
    }

    public static String combineJieshuo(String email, String itemId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Bag.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        params.add(new BasicNameValuePair("prop1", itemId));
        params.add(new BasicNameValuePair("propCount", "5"));
        params.add(new BasicNameValuePair("isInsure", "0"));
        params.add(new BasicNameValuePair("iscoin", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        return s;
    }

    public static String upgradePlayerShijiebei(String email, String playerId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpdateWorldCup_Break_DO1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        System.out.println("球员世界杯积分升级结果：" + s);
        trainFull(email, playerId, true);
        if(s.equals("-1")) {
            return "银币不足";
        } else if(s.equals("-2")) {
            return "积分不足";
        } else {
            return s;
        }
    }

    public static int dianfengzhilu(String email) throws Exception {
        String conf = ConfigUtils.getConf(email, "是否踢巅峰之路");
        if(!"1".equals(conf)) {
            return 0;
        }

        HttpPost pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("loadClubList", "1"));
        params.add(new BasicNameValuePair("LeagueIndex", "103"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        String[] results = s.split("╋");
        String[] parts = results[0].split("\\*");
        String[] allopponents = parts[2].split("\\^");
        String firstopponent = allopponents[0].split("\\|")[1];
        boolean canreset = results[6].split("\\*")[1].equals("1");
        if(!parts[0].equals("-1")) { //比赛中
            return 1000 * 60 * 5;
        }
        String nextopponent = parts[4];
        if(results[0].split("@").length > 4 && results[0].split("@")[4].equals("1")) { //需要翻牌
            String fanpai = results[0].split("@")[3]; //翻牌结果
            if(fanpai.length() == 0) { //没有翻过牌
                pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("PickCard", "103"));
                params.add(new BasicNameValuePair("CardIndex", (new Random().nextInt(5) + 1) + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "巅峰之路翻牌结果：" + s);
            }
            //领取奖励
            pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("CardAward", "103"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            nextopponent = Integer.parseInt(nextopponent) + 1 + "";
        }
        //比较实力，判断是否要踢下一场
        String my = results[6].split("\\*")[0];
        for(int i = 0; i < allopponents.length; i++) {
            String[] attrs = allopponents[i].split("\\|");
            if(attrs[1].equals(nextopponent)) {
                if(Integer.parseInt(attrs[8]) > Integer.parseInt(my)) {
                    LOGGER.info(email + "/" + IDUtils.getNick(email) + "对手实力大于自己(" + my + "/" + attrs[8] + ")，巅峰之路结束，共打了" + (Integer.parseInt(nextopponent) - Integer.parseInt(firstopponent)) + "个对手");
                    if(canreset) {
                        pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("DFRoad_ResetProcess", "1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(email, pm);
                        LOGGER.info(email + "/" + IDUtils.getNick(email) + "重置结果：" + s);
                    }
                    return -1;
                }
                break;
            }
        }
        //比赛
        pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", nextopponent));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(email, pm);
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "巅峰之路比赛：" + s);
        return 1000 * 60 * 10;
    }

    /* 得到战术中心中某球员的满训数量 */
    public static int[] getFullXiangqianCount(Map<String, String> m, List<String[]> xiangqians) {
        int[] counts = new int[4];
        if(m.get("name").equals("谢朱")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 180) {
                    counts[1]++;
                }
            }
        } else if(m.get("name").equals("福法纳")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) ==  173) {
                    counts[2]++;
                }
            }
        } else if(m.get("name").equals("博卡利")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) ==  169) {
                    counts[3]++;
                }
            }
        } else if(m.get("name").equals("鲁菲耶")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) ==  160) {
                    counts[0]++;
                }
            }
        } else if(m.get("name").equals("乌塔卡")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 273) {
                    counts[3]++;
                }
            }
        } else if(m.get("name").equals("戈尼亚隆斯")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 270) {
                    counts[1]++;
                }
            }
        } else if(m.get("name").equals("瓦尔布埃纳")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 276) {
                    counts[2]++;
                }
            }
        } else if(m.get("name").equals("芒当达")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 264) {
                    counts[0]++;
                }
            }
        } else if(m.get("name").equals("朗德洛")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 443) {
                    counts[0]++;
                }
            }
        } else if(m.get("name").equals("戈比")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 448) {
                    counts[1]++;
                }
            }
        } else if(m.get("name").equals("卡列洪")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name") + "中")) {
                    counts[2]++;
                } else if(xq[1].equals(m.get("name") + "前")) {
                    counts[3]++;
                }
            }
        } else if(m.get("name").equals("什科琴尼")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 823) {
                    counts[0]++;
                }
            }
        } else if(m.get("name").equals("阿巴特")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 807) {
                    counts[2]++;
                }
            }
        } else if(m.get("name").equals("贝尔巴托夫")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 831) {
                    counts[3]++;
                }
            }
        } else if(m.get("name").equals("梅策尔德")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 832) {
                    counts[1]++;
                }
            }
        } else if(m.get("name").equals("斯特克伦博格")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name")) && Integer.parseInt(xq[3]) == 1325) {
                    counts[0]++;
                }
            }
        } else if(m.get("name").equals("拉莫斯")) {
            for(String[] xq : xiangqians) {
                if(xq[1].equals(m.get("name") + "后")) {
                    counts[1]++;
                } else if(xq[1].equals(m.get("name") + "中")) {
                    counts[2]++;
                }
            }
        }
        return counts;
    }

    public static boolean isJiqinging(String email) throws Exception {
        String now = new SimpleDateFormat("HHmm").format(new Date());
        boolean needcheck = false;
        if(now.compareTo("1159") >= 0 || now.compareTo("1401") <= 0) {
            needcheck = true;
        }
        if(now.compareTo("1959") >= 0 || now.compareTo("2201") <= 0) {
            needcheck = true;
        }
        if(!needcheck) {
            return false;
        }
        String nick = IDUtils.IDInfos.get(email).get("nick");
        for(int i = 0; i <= 3; i++) {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/TrainingBase2.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("leagueIndex", "" + i));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            if("-1".equals(s)) {
                return false;
            }
            s = s.split("@")[1];
            String[] clubs = s.split("[*]");
            for(String c : clubs) {
                String[] parts = c.split("\\|");
                if(parts[9].equals("1")) { //不能训练的场地
                    continue;
                }
                if(nick.equals(parts[4])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String upgradeTitle(String email, String playerId, String isLucky) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/MatchList/ChallengeMatch/Challenge1.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("DFRoad_PlayerTitleUpgrade", "1"));
        params.add(new BasicNameValuePair("playerid", playerId));
        params.add(new BasicNameValuePair("cointype", "1"));
        params.add(new BasicNameValuePair("islucky", isLucky));
        params.add(new BasicNameValuePair("issafe", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        return s;
        //$.post("MatchList/ChallengeMatch/Challenge1.aspx", { "DFRoad_PlayerTitleUpgrade": 1, playerid: who, cointype: jinbi, islucky: baohu, issafe: baoxian }
        //1是保险 0不保险
        //1是银币 2是金币
        //1是使用合计
    }
}
