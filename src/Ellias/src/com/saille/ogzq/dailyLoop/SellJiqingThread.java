package com.saille.ogzq.dailyLoop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.util.*;
import java.text.SimpleDateFormat;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;
import com.saille.ogzq.OperationUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-24
 * Time: 15:48:24
 * To change this template use File | Settings | File Templates.
 */
public class SellJiqingThread extends ParentThread {
    private final static Logger LOGGER = Logger.getLogger(SellJiqingThread.class);
    private static SellJiqingThread instance;

//    public List<String> finishIds = new ArrayList<String>();

    private SellJiqingThread() {
        threadName = "卖激情";
        setThreadname(threadName);
    }

    public synchronized static SellJiqingThread getInstance() {
        if(instance == null) {
            instance = new SellJiqingThread();
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
                lastCheckTime = sdf3.format(new Date());
                
                if(lastDate == null || today.compareTo(lastDate) > 0) { //到新的一天
                    lastDate = today;
                }
                if(now.compareTo(ConfigUtils.BEGINTIME) <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + ConfigUtils.BEGINTIME + "，等待300秒");
                    Thread.sleep(1000 * 60 * 5);
                    continue;
                }

                if(now.compareTo("2200") > 0) {
                    LOGGER.info("当前时间：" + now + "，大于22点");
                    Thread.sleep(1000 * 60 * 60);
                    continue;
                }
                if(now.compareTo("2000") < 0) {
                    LOGGER.info("当前时间：" + now + "，小于20点");
                    Thread.sleep(1000 * 60 * 5);
                    continue;
                }
                List<String> ids = IDUtils.GETIDS();
                LOGGER.info("卖激情循环" + ids.size() + "个号");
                for(String id : ids) {
                    OperationUtils.autoSellJiqing(id);
                }
                LOGGER.info("卖激情循环结束，等待10分钟");
                Thread.sleep(1000 * 60 * 10 + new Random().nextInt(60) * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String autoSellJiqing(String email) throws Exception {
        Calendar c = Calendar.getInstance();
//        if(c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
//            return "";
//        }
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            if(c.get(Calendar.HOUR_OF_DAY) < 20) {
                return "";
            }
        }
        if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            if(c.get(Calendar.HOUR_OF_DAY) > 22) {
                return "";
            }
        }
        if(!"1".equals(ConfigUtils.getConf(email, "是否卖激情"))) {
            return "";
        }
        LOGGER.info(email + "/" + IDUtils.getNick(email) + "自动卖激情值。。。");
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Prop/Trade2.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        params.add(new BasicNameValuePair("TradeNum", "0"));
        params.add(new BasicNameValuePair("PageNumber", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm); //&0|0|0|13552&1/0/0
        if(ret.equals("-2")) {
            return "";
        }
        ret = ret.substring(ret.indexOf("&") + 1);
        ret = ret.substring(0, ret.indexOf("&"));
        String[] v = ret.split("[|]"); //4种激情剩余
        int[] v2 = new int[4];
        int maxIdx = -1, max = 0;
        for(int i = 0; i < 4; i++) {
            v2[i] = Integer.parseInt(v[i]);
            if(v2[i] > max) {
                maxIdx = i;
                max = v2[i];
            }
        }
        if(max < 46) { //最少一次需要挂10金
            return "";
        }
        if(max > 100) { //一次挂100激情,就是20金
            max = 100;
        }
        pm = new HttpPost(OgzqURL.URL + "/Prop/Trade2.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm); //获取剩余可以卖的数量
//        180/300*1
        int left = Integer.parseInt(ret.substring(0, ret.indexOf("/")));
        if(left == 0) {
            return "";
        }
        while(left >= 10 && max > 0) { //一次挂超过10金才可以
            pm = new HttpPost(OgzqURL.URL + "/Prop/Trade2.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            for(int i = 0; i < 4; i++) { //0-4分别对应蓝橙红紫
                if(maxIdx == i) {
                    params.add(new BasicNameValuePair("Count" + (i + 1), "" + (Math.min(max, left * 5))));
                    params.add(new BasicNameValuePair("Price" + (i + 1), "" + (int)((Math.min(max, (left * 5)) + 4) / 5)));
                    max = max - Math.min(max, left * 5);
                } else {
                    params.add(new BasicNameValuePair("Count" + (i + 1), "0"));
                    params.add(new BasicNameValuePair("Price" + (i + 1), "0"));
                }
            }
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(email, pm);
            LOGGER.info(email + "卖激情：" + IDUtils.execute(email, pm));

            pm = new HttpPost(OgzqURL.URL + "/Prop/Trade2.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(email, pm); //获取剩余可以卖的数量
            left = Integer.parseInt(ret.substring(0, ret.indexOf("/")));
        }
        return null;
    }
}
