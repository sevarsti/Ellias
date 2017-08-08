package com.saille.ogzq.dailyLoop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OperationUtils;
import com.saille.ogzq.OgzqURL;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-2
 * Time: 13:38:58
 * To change this template use File | Settings | File Templates.
 */
public class TrainingmatchThread extends ParentThread{
    private final static Logger LOGGER = Logger.getLogger(TrainingmatchThread.class);
    private static TrainingmatchThread instance;

    private TrainingmatchThread() {
        threadName = "训练赛";
        setThreadname(threadName);
    }

    public synchronized static TrainingmatchThread getInstance() {
        if(instance == null) {
            instance = new TrainingmatchThread();
        }
        return instance;
    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
        while(true) {
            try {
                String today = sdf.format(new Date());
                String now = sdf2.format(new Date());
                long sysTime = new Date().getTime();
                lastCheckTime = sdf3.format(new Date());
                
                if(now.compareTo(ConfigUtils.BEGINTIME) <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + ConfigUtils.BEGINTIME + "，等待60秒");
                    Thread.sleep(1000 * 60);
                    continue;
                }

                List<String> ids = IDUtils.GETIDS();
                LOGGER.info("训练赛循环" + ids.size() + "个号");

                for(String id : ids) {
                    if(targetTime.containsKey(id) && targetTime.get(id).longValue() > sysTime) {
                        continue;
                    }
                    String result = OperationUtils.doTrainingMatch(id);
                    if("-1".equals(result)) { //训练豆不够
                        targetTime.put(id, sysTime + 1000 * 60 * 10);
                    } else {
                        targetTime.put(id, sysTime + 1000 * 10);
                    }
                }
                long minvalue = getWaitTime(sysTime);
                LOGGER.info("训练赛循环结束，等待" + minvalue / 1000 + "秒");
                Thread.sleep(minvalue);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String doTrainingMatch(String email) throws Exception {
//        LOGGER.info(email + ": 训练赛");
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("TrainMatchload1", "7*0*0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);

        if(s.indexOf("inmatch|") >= 0) { //在打比赛
            return null;
        }

        String conflevel = ConfigUtils.getConf(email, "训练赛范围"); //配置的训练赛范围

        String[] parts = s.split("&");
//        if(parts[0].equals("-1")) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING); //领取奖励
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getAward", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String award = IDUtils.execute(email, pm);
        if(!"-10".equals(award)) {
            LOGGER.info(email + "训练赛奖品:" + award);
        }
//        }

        String trainingMatch2 = parts[0];

        int blankMatch = 0;
        int restMatch = 0;
        if(!trainingMatch2.equals("-2")) {
//            if(parts[5].equals("0") && "sevarsti@sina.com".equals(email)) {
//                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("ShowTmMsgOK1", "1*3083735"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                String ss = IDUtils.execute(email, pm);
//                LOGGER.info("sevarsti@sina.com使用回豆卡：" + ss);
//                Thread.sleep(2000l);
//                parts[5] = parts[4];
//            }
            blankMatch = Integer.parseInt(parts[4]) - Integer.parseInt(parts[5]); //离满豆还差多少场
            restMatch = Integer.parseInt(parts[5]);
            ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", restMatch + "/" + parts[4]);
        } else {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("GetPowerCount", "1"));
            params.add(new BasicNameValuePair("pt", "7"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String powerCount = IDUtils.execute(email, pm);
            int maxMatch = 0;
            try{
                maxMatch = Integer.parseInt(powerCount);
            } catch(Exception ex) {}
            pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAM_PLAY);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getB", "7"));
            params.add(new BasicNameValuePair("title", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String currentCount = IDUtils.execute(email, pm);
            int currentMatch = 0;
            try{
                currentMatch = Integer.parseInt(currentCount.split("[|]")[0]);
                restMatch = currentMatch;
            } catch(Exception ex) {}
            blankMatch = maxMatch - currentMatch;
            ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", restMatch + "/" + maxMatch);
        }

        if(restMatch == 0) {
//                    todo
            boolean useHuidou = false;
            if("sevarsti@sina.com".equals(email) || "1412913604@qq.com".equals(email) ||
                    "meijianbai@hotmail.com".equals(email) || "rentao@vip.sina.com".equals(email) ||
                    "liaoqing262@163.com".equals(email)) {
                useHuidou = false;
            }

            if(useHuidou) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "0"));
                params.add(new BasicNameValuePair("itemtype", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String str = IDUtils.execute(email, pm);
                String[] items = str.split("[|]");
                String huidouId = "";
                int count = 0;
                for(String ii : items) {
                    String[] i = ii.split("[*]");
                    if(i[3].equals("回豆卡")) {
                        huidouId = i[0];
                        count = Integer.parseInt(i[7]);
                        break;
                    }
                }
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("ShowTmMsgOK1", "1*" + huidouId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ss = IDUtils.execute(email, pm);
                LOGGER.info(email + "使用回豆卡：" + ss);
                count = count - 1;
                LOGGER.info(email + "剩余：" + count + "张");
                return null;
            }
        }

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GetPowerCount", "1"));
        params.add(new BasicNameValuePair("pt", "9"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String powerCount = IDUtils.execute(email, pm);
        int maxOgMatch = 0;
        try{
            maxOgMatch = Integer.parseInt(powerCount);
        } catch(Exception ex) {}
        pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAM_PLAY);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getB", "9"));
        params.add(new BasicNameValuePair("title", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String currentCount = IDUtils.execute(email, pm);
        int currentOgMatch = 0;
        try{
            currentOgMatch = Integer.parseInt(currentCount.split("[|]")[0]);
        } catch(Exception ex) {}
        int blankOgMatch = maxOgMatch - currentOgMatch;

        if(blankMatch > blankOgMatch && (currentOgMatch > 0) && Integer.parseInt(IDUtils.IDInfos.get(email).get("level")) > 60) { //如果欧冠比赛空余少，那就打欧冠，否则打普通
            return doOgTrainingMatch(email);
        }

        if(restMatch <= 0) { //如果剩余场次不够，那就不打
            return "-1";
        }

        if(trainingMatch2.equals("-2") || !conflevel.equals("7")) { //打不了70-80，或者配置打低级别联赛
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            params.add(new BasicNameValuePair("lli", "-1"));
            params.add(new BasicNameValuePair("intRoomIndex", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            String rett = IDUtils.execute(email, pm);
            if(rett.indexOf("inmatch") != -1) {
                return null;
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("GetPowerCount", "1"));
            params.add(new BasicNameValuePair("pt", "7"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            powerCount = IDUtils.execute(email, pm);
            if(IDUtils.IDInfos.containsKey(email)) {
                String xunliansai = (String) ((Map) IDUtils.IDInfos.get(email)).get("xunliansai");
                if(xunliansai == null) {
                    ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", "?/" + powerCount);
                } else {
                    ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", xunliansai.substring(0, xunliansai.indexOf("/") + 1) + powerCount);
                }
            } else {
                IDUtils.IDInfos.put(email, new Hashtable());
                ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", "?/" + powerCount);
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAM_PLAY);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getB", "7"));
            params.add(new BasicNameValuePair("title", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            currentCount = IDUtils.execute(email, pm);

            String[] subs = currentCount.split("[|]");
            if(IDUtils.IDInfos.containsKey(email)) {
                String xunliansai = (String) ((Map) IDUtils.IDInfos.get(email)).get("xunliansai");
                if(xunliansai == null) {
                    ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", subs[0] + "/?");
                } else {
                    ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", subs[0] + "/" + xunliansai.substring(xunliansai.indexOf("/") + 1));
                }
            } else {
                IDUtils.IDInfos.put(email, new Hashtable());
                ((Map) IDUtils.IDInfos.get(email)).put("xunliansai", subs[0] + "/?");
            }

            String level = rett.substring(rett.indexOf("@") + 1);
            level = level.substring(level.indexOf("@") + 1);
            level = level.substring(level.indexOf("@") + 1);
            level = level.substring(0, level.indexOf("@"));
            String matchId = "";
            int index = 30;
            int npci = 0;
            String resLevel = level;
            if(conflevel.equals("-1")) {
                resLevel = level;
            } else {
                if(Integer.parseInt(conflevel) <= Integer.parseInt(resLevel)) {
                    resLevel = conflevel;
                }
            }
            String targetTeam = ConfigUtils.getConf(email, "训练赛对手");
            if(StringUtils.isNotEmpty(targetTeam)) {
                String targetLevel = ConfigUtils.teamLevels.getString(targetTeam);
                resLevel = targetLevel;
            }
            while(true) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("leagueIndex", resLevel));
                params.add(new BasicNameValuePair("pageIndex", String.valueOf(index)));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                String ret = IDUtils.execute(email, pm);
                ret = ret.substring(0, ret.indexOf("@"));
                String[] teams = ret.split("&");
                for(int i = 0; i < teams.length; i++) {
                    String team = teams[i];
                    if((team.split("[|]").length != 7) || ((StringUtils.isNotEmpty(targetTeam)) && (!targetTeam.equals(team.split("[|]")[1])))) {
                        continue;
                    }
                    matchId = team.split("[|]")[0];
                    npci = i;
                    break;
                }

                if(StringUtils.isNotEmpty(matchId)) {
                    break;
                }
                index--;
                if(index <= 10) {
                    index = 30;
                }
            }
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("CheckInsertMatch", matchId));
            params.add(new BasicNameValuePair("li", resLevel));
            params.add(new BasicNameValuePair("npci", String.valueOf(npci)));
            params.add(new BasicNameValuePair("pi", String.valueOf(index)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(email, pm);

            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("insertMatch", matchId));
            params.add(new BasicNameValuePair("li", resLevel));
            params.add(new BasicNameValuePair("npci", String.valueOf(npci)));
            params.add(new BasicNameValuePair("pi", String.valueOf(index)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm);
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "踢普通训练赛：" + ret);
            return ret.equals("-1") ? null : ret;
        } else { //踢70-80训练场
            String nextTeam = null;
            int pageCount = new Random().nextInt(59);
            String pageInfo = null;
            while(nextTeam == null) {
                pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("TrainMatchload1", "7*" + pageCount + "*1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                if(s.indexOf("inmatch") >= 0) {
                    return null;
                }
//                System.out.println(s);
                pageInfo = s.split("&")[3];
                String[] teams = s.split("&")[8].split("[*]");
                for(String t : teams) {
                    if(t.split("[|]")[6].equals("-1")) {
                        nextTeam = t.split("[|]")[0];
                        break;
                    }
                }

                pageCount--;
                if(pageCount < 0) {
                    pageCount = 59;
                    break;
                }
            }

            if(nextTeam != null) {
                pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("InitChallengeMatch1", nextTeam + "*" + pageInfo));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "踢70-80训练赛：" + s);
                return s.equals("-1") ? null : s;
            } else {
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "没有可以踢的训练赛");
                return null;
            }
        }
    }

    public static String doOgTrainingMatch(String email) throws Exception {
//        LOGGER.info(email + ": 欧冠训练赛");
        if((!IDUtils.IDInfos.containsKey(email)) || (!(IDUtils.IDInfos.get(email)).containsKey("level"))) {
            return null;
        }
        String level = (String) ((Map) IDUtils.IDInfos.get(email)).get("level");
        if(Integer.parseInt(level) < 61) {
            return "-1";
        }
        HttpPost pm = null;

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        params.add(new BasicNameValuePair("lli", "-1"));
        params.add(new BasicNameValuePair("intRoomIndex", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String rett = IDUtils.execute(email, pm);
        if(rett.indexOf("inmatch") != -1) {
            return null;
        }

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getAward", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
//        LOGGER.info(email + " 欧冠训练赛奖品:" + IDUtils.execute(email, pm));

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GetPowerCount", "1"));
        params.add(new BasicNameValuePair("pt", "9"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String powerCount = IDUtils.execute(email, pm);

        pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAM_PLAY);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getB", "9"));
        params.add(new BasicNameValuePair("title", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String currentCount = IDUtils.execute(email, pm);
        String matchId = "";
        int index = 55;
        int npci = -1;
        String[] targets = {"波尔图", "利物浦", "国米", "阿贾克斯", "拜仁", "尤文图斯", "巴萨", "米兰", "皇马", "曼联"};
        List<Map<String, String>> items = new ArrayList<Map<String, String>>();
        try {
            items = OperationUtils.listBags(email, "0");
        } catch(Exception ex) {}
        boolean[] found = new boolean[10];
        boolean allfound = true;
        for(int i = 0; i < 10; i++) {
            found[i] = false;
        }
        for(Map m : items) {
            for(int i = 0; i < 10; i++) {
                if(((String) m.get("name")).indexOf(targets[i]) != -1) {
                    found[i] = true;
                    break;
                }
            }
        }
        for(int i = 0; i < 10; i++) {
            if(!found[i]) {
                allfound = false;
                break;
            }
        }
        while(true) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("leagueIndex", "6"));
            params.add(new BasicNameValuePair("pageIndex", String.valueOf(index)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(email, pm);
            ret = ret.substring(0, ret.indexOf("@"));
            String[] teams = ret.split("&");
            for(int i = 0; i < teams.length; i++) {
                String team = teams[i];
                if(team.split("[|]").length == 7) {
                    String teamname = team.split("[|]")[1];
                    if(allfound) {
                        matchId = team.split("[|]")[0];
                        npci = i;
                        break;
                    }
                    boolean has = false;
                    for(int j = 0; j < 10; j++) {
                        if((!found[j]) && (teamname.indexOf(targets[j]) != -1)) {
                            matchId = team.split("[|]")[0];
                            npci = i;
                            has = true;
                            break;
                        }
                    }
                    if(has) {
                        break;
                    }
                }
            }
            if(StringUtils.isNotEmpty(matchId)) {
                break;
            }
            index--;
            if(index < 1) {
                break;
//                index = 50;
            }
        }
        if(npci == -1) {
            LOGGER.info("找不到可以打的61-80训练赛");
            return null;
        }
        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("CheckInsertMatch", matchId));
        params.add(new BasicNameValuePair("li", "6"));
        params.add(new BasicNameValuePair("npci", String.valueOf(npci)));
        params.add(new BasicNameValuePair("pi", String.valueOf(index)));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MATCH_TRAINING);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", matchId));
        params.add(new BasicNameValuePair("li", "6"));
        params.add(new BasicNameValuePair("npci", String.valueOf(npci)));
        params.add(new BasicNameValuePair("pi", String.valueOf(index)));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        LOGGER.info(email + "踢61-80训练赛：" + ret);
        return ret.equals("-1") ? null : ret;
    }

}
