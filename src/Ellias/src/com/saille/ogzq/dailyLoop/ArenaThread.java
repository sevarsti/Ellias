package com.saille.ogzq.dailyLoop;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.OperationUtils;
import com.saille.ogzq.OgzqURL;

import java.util.*;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.commons.lang.StringUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-24
 * Time: 13:13:29
 * To change this template use File | Settings | File Templates.
 * 每天打竞技场
 */
public class ArenaThread extends ParentThread {
    private final static Logger LOGGER = Logger.getLogger(ArenaThread.class);
    private static ArenaThread instance;

    public List<String> finishIds = new ArrayList<String>();
    public static Map<String, Integer> failCount = new HashMap<String, Integer>();
    public static Map<String, String> nextTarget = new HashMap<String, String>();

    private ArenaThread() {
        threadName = "竞技场";
        setThreadname(threadName);
    }

    public synchronized static ArenaThread getInstance() {
        if(instance == null) {
            instance = new ArenaThread();
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

                if(lastDate == null || today.compareTo(lastDate) > 0) { //到新的一天
                    LOGGER.info("当前日期：" + today + "，重置竞技场");
                    finishIds.clear();
                    failCount.clear();
                    nextTarget.clear();
                    lastDate = today;
                }
                if(now.compareTo(ConfigUtils.BEGINTIME) <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + ConfigUtils.BEGINTIME + "，等待60秒");
                    Thread.sleep(1000 * 60);
                    continue;
                }

                List<String> ids = IDUtils.GETIDS();
                LOGGER.info("竞技场循环" + ids.size() + "个号");
                for(String id : ids) {
                    String doArena = ConfigUtils.getConf(id, "是否踢竞技场");
                    if(!doArena.equals("1")) {
                        continue;
                    }
                    if(finishIds.contains(id)) {
                        continue;
                    }
                    if(targetTime.containsKey(id) && targetTime.get(id).longValue() > sysTime) {
                        continue;
                    }
                    if(failCount.containsKey(id) && failCount.get(id).intValue() > 5) {
                        LOGGER.info(id + "/" + IDUtils.getNick(id) + "挑战同一个对手超过5次，不再挑战");
                        targetTime.put(id, sysTime + 1000 * 60 * 60);
                        failCount.remove(id);
                        nextTarget.remove(id);
                        continue;
                    }
                    int result = OperationUtils.doArena(id);
                    if(result == 0) {
//                        finishIds.add(id);
                        targetTime.put(id, sysTime + 1000 * 60 * 20);
                    } else if(result > 0) {
                        targetTime.put(id, sysTime + result);
                    } else {
                        targetTime.put(id, sysTime + 1000 * 60 * 5);
                    }
                }
                long minvalue = getWaitTime(sysTime);
                LOGGER.info("竞技场循环结束，等待" + minvalue / 1000 + "秒");
                Thread.sleep(minvalue);
//                Thread.sleep(1000 * 60 * 10 + new Random().nextInt(60) * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
                try {
                    Thread.sleep(5000);
                } catch(Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }

    /*打竞技场
    * return
    * -1:比赛中
    * -2:未知错误
    * 0:全都打完了
    * >0:开始比赛
    * */
    public static int doArena(String email) throws Exception {
//        LOGGER.info(email + ": 竞技场");

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("load", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

        String ret = IDUtils.execute(email, pm);
        if(ret.indexOf("inmatch") != -1) {
            return -1; //比赛中
        }
        try {
            int retValue = Integer.parseInt(ret);
            if(retValue < 0) {
                return -2; //未知错误
            }
        } catch(NumberFormatException ex) {
        }

        //initcards
        pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("initCards", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if(!"-1".equals(s) && s.length() > 0) {
            String[] ss = s.split("[*]");
            String[] step = ss[1].split("[|]");
            LOGGER.info(email + "竞技场结果：" + s);
            if(s.startsWith("-1|-1^-1|-1^-1|-1^-1|-1^-1|-1")) { //一张都没翻
                pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("OpenCard", "1"));
                params.add(new BasicNameValuePair("cardindex", "4"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);

                if(s.indexOf("5004") >= 0 || s.indexOf("5005") >= 0) { //5004: xiangzi 5005: key
                    //检查数量
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "0"));
                    params.add(new BasicNameValuePair("itemtype", "3"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String str = IDUtils.execute(email, pm);
                    str = str.split("@")[0];
                    String[] bags = str.split("\\|");
                    int ptbox = 0, ptkey = 0;
                    for(String b : bags) {
                        String[] parts = b.split("\\*");
                        if(parts[11].equals("5007")) { //白金箱子
                            ptbox = Integer.parseInt(parts[7]);
                        } else if(parts[11].equals("5006")) { //白金钥匙
                            ptkey = Integer.parseInt(parts[7]);
                        }
                    }
                    String code;
                    boolean needupgrade = false;
                    if(s.indexOf("5005") >= 0) { //金箱子
                        code = "5005";
                        if(ptbox <= (ptkey + 10)) {
                            needupgrade = true;
                        }
                    } else { //金钥匙
                        code = "5004";
                        if(ptkey <= (ptbox + 10)) {
                            needupgrade = true;
                        }
                    }

                    if(needupgrade) {
                        pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("UpItemcode1", "4*" + code));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(email, pm);
                    }
                }
            } else {

            }
//            if(step[3].equals("0")) { //翻了0张
//
//            } else if(step[3].equals("1")) { //翻了1张，大概！
//
//            }
            pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("getAward", "1"));
            params.add(new BasicNameValuePair("lod", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
        }

        if(!ret.split("\\^")[2].equals("-1")) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("serF", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
        }
        ret = ret.substring(0, ret.indexOf("&"));
        String[] teams = ret.split("[*]");
        int minAbility = 99999999;
        String minId = "";
        int win = 0;
        int count = 0;
        String[] against = new String[teams.length];
        for(int i = 0; i < teams.length; i++) {
            against[i] = teams[i];
            String[] subs = teams[i].split("[|]");
            count++;
            if(subs[9].equals("1")) {
                win++;
            } else {
                int curAbility = Integer.parseInt(subs[5]);
                if(curAbility < minAbility) {
                    minAbility = curAbility;
                    minId = subs[0];
                }
            }
        }
        IDUtils.JJCAgainst.put(email, against);
        ((Map) IDUtils.IDInfos.get(email)).put("arena", win + "/" + count);

        if(StringUtils.isNotEmpty(minId)) {
            if(nextTarget.containsKey(email) && nextTarget.get(email).equals(minId)) {
                if(failCount.containsKey(email)) {
                    failCount.put(email, failCount.get(email).intValue() + 1);
                } else {
                    failCount.put(email, 1);
                }
            } else {
                nextTarget.put(email, minId);
                failCount.put(email, 0);
            }
            pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("insertMatch", minId));
            String daoju = ConfigUtils.getConf(email, "竞技场高级道具");
            if("1".equals(daoju)) {
                params.add(new BasicNameValuePair("prop", "2"));
            } else {
                params.add(new BasicNameValuePair("prop", "1"));
            }
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(email, pm);
            if(s.indexOf("&") > 0) {
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "打竞技场：" + s.split("&")[1]);
            } else {
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "打竞技场：" + s);
            }
            return 1000 * 60 * 5;
        } else {
            return 0;//全都过关了
        }
    }

}
