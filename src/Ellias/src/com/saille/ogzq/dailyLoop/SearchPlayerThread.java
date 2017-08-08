package com.saille.ogzq.dailyLoop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.*;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OperationUtils;
import com.saille.ogzq.OgzqURL;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-25
 * Time: 15:51:11
 * To change this template use File | Settings | File Templates.
 */
public class SearchPlayerThread extends ParentThread {
    private final static Logger LOGGER = Logger.getLogger(SearchPlayerThread.class);
    private static SearchPlayerThread instance;

    private SearchPlayerThread() {
        threadName = "球员搜索";
        setThreadname(threadName);
    }

    public synchronized static SearchPlayerThread getInstance() {
        if(instance == null) {
            instance = new SearchPlayerThread();
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
                    lastDate = today;
                }
                if(now.compareTo(ConfigUtils.BEGINTIME) <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + ConfigUtils.BEGINTIME + "，等待300秒");
                    Thread.sleep(1000 * 60 * 5);
                    continue;
                }

                List<String> ids = IDUtils.GETIDS();
                LOGGER.info("搜索球员循环" + ids.size() + "个号");
                for(String id : ids) {
                    if(targetTime.containsKey(id) && targetTime.get(id).longValue() > sysTime) {
                        continue;
                    }

                    if(ConfigUtils.getConf(id, "是否搜索球员").equals("1")) {
                        OperationUtils.afterFindPlayer(id);
                        int time = OperationUtils.findPlayer(id);

                        if(time > 0) {
                            LOGGER.info(id + "/" + IDUtils.getNick(id) + "搜索球员，耗时" + time + "秒");
                            targetTime.put(id, sysTime + time * 1000);
                        } else {
                            if(time == -1) {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "球员大厅位置不够，不能找球员");
                            } else if(time == -2) {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "飞机油量已经不足");
                            } else if(time == -10) {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "搜索顺序：法甲<德甲<意甲<英超<西甲");
                            } else if(time == -11) {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "您的球星号召力达到7级才可使用");
                            } else if(time == -12) {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "巅峰搜索60级开放");
                            } else {
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + ": " + time);
                            }
                            targetTime.put(id, sysTime + 1000 * 60 * 10);
                        }
                    }
                }
//                long minvalue = 1000 * 60 * 60 * 2;
//                if(targetTime.size() == 0) {
//                    minvalue = 1000 * 60;
//                } else {
//                    for(String key : targetTime.keySet()) {
//                        long target = targetTime.get(key);
//                        if((target - sysTime) < minvalue) {
//                            minvalue = target - sysTime;
//                        }
//                    }
//                }
//                minvalue = minvalue + new Random().nextInt(5) * 1000;
                long minvalue = getWaitTime(sysTime);
                LOGGER.info("搜索球员循环结束，等待" + minvalue / 1000 + "秒");
                Thread.sleep(minvalue);
//                Thread.sleep(1000 * 60 * 10 + new Random().nextInt(60) * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /* 搜索球员
    * -1:未知错误
    * >0: 搜索的时间*/
    public static int findPlayer(String email) throws Exception {
/*
法
德
意
英
西
意英
意西
英西
意英西
德意
德英
德西
德意英
德意西
德英西
德意法
*/
//        LOGGER.info(email + ": 寻找球员");

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("GetMiddleInfo", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String level = ret.substring(0, ret.indexOf("|"));
        String conflevel = ConfigUtils.getConf(email, "球员搜索范围");
        String reslevel = conflevel;
        if(conflevel.equals("-1")) {
            reslevel = level;
        }

        pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("beforeFindPlayer", "1"));
        params.add(new BasicNameValuePair("LeagueIndex", reslevel));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);

        if(Integer.parseInt(reslevel) <= 5) {
            pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("findPlayer", "1"));
            params.add(new BasicNameValuePair("LeagueIndex", reslevel));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "搜索球员：" + reslevel + "，结果：" + s);
            if(s.indexOf("|") > 0) {
                return Integer.parseInt(s.split("\\|")[3]);
            } else {
                return -1;
            }
        } else {
            pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("SelPlayer1", "" + (Integer.parseInt(reslevel) - 5)));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            LOGGER.info(email + "/" + IDUtils.getNick(email) + "搜索巅峰球员：" + (Integer.parseInt(reslevel) - 5) + "，结果：" + s);
            if(s.indexOf("|") > 0) {
                return Integer.parseInt(s.split("\\|")[3]);
            } else {
                if("-10".equals(s)) {
                    pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("loadPower", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(email, pm);
                    return Integer.parseInt(s.split("\\|")[3]);
                } else {
                    return Integer.parseInt(s);
                }
            }
        }
    }

    public static void afterFindPlayer(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getAndShowFreshMan", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        if(!s.equals("-1")) {
            LOGGER.info(email + "球员搜索结果：" + s);
            String[] parts = s.split("[|]");
            //5036067|弗里茨|3    |74     |2  |Germany/06/20606.png
            //id     |name  |level|ability|pos|
            if(!IDUtils.checkPlayerInitial(parts[1], parts[4], parts[2])) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("LoadPlayer1", parts[0]));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(email, pm);
                String[] atts = ret.split("[*]");
                String[] abis = new String[8];
                for(int i = 0; i < 8; i++) {
                    abis[i] = atts[9 + i];
                }
                IDUtils.savePlayerInitial(parts[1], parts[4], parts[2], abis);
            }
            if(!OperationUtils.needRemainPlayer(email,parts[1], parts[2], parts[4], parts[3], true) && parts[1].indexOf("少年") < 0) {
                OperationUtils.dropPlayer(email, parts[0]);
            }
        }
        //检查大厅
        List<Map<String, String>> list = OperationUtils.middleman(email);
        for(Map<String, String> player : list) {
            if(!IDUtils.checkPlayerInitial(player.get("name"), player.get("place"), player.get("level"))) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("LoadPlayer1", player.get("id")));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(email, pm);
                String[] atts = ret.split("[*]");
                String[] abis = new String[8];
                for(int i = 0; i < 8; i++) {
                    abis[i] = atts[9 + i];
                }
                IDUtils.savePlayerInitial(player.get("name"), player.get("place"), player.get("level"), abis);
            }
            if(!OperationUtils.needRemainPlayer(email, player.get("name"), player.get("level"), player.get("place"), player.get("value"), false) && player.get("name").indexOf("少年") < 0) {
                LOGGER.info(email + "/" + IDUtils.getNick(email) + "需要放弃球员：" + player.get("name"));
                OperationUtils.dropPlayer(email, player.get("id"));
            }
        }
    }
}
