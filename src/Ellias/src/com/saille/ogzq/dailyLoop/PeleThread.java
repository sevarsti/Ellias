package com.saille.ogzq.dailyLoop;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OperationUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-26
 * Time: 17:50:05
 * To change this template use File | Settings | File Templates.
 */
public class PeleThread extends ParentThread {
    private final static Logger LOGGER = Logger.getLogger(PeleThread.class);
    private static PeleThread instance;

    private PeleThread() {
        threadName = "球王贝利";
        setThreadname(threadName);
    }

    public synchronized static PeleThread getInstance() {
        if(instance == null) {
            instance = new PeleThread();
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
                    targetTime.clear();
                    lastDate = today;
                }
                if(now.compareTo(ConfigUtils.BEGINTIME) <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + ConfigUtils.BEGINTIME + "，等待300秒");
                    Thread.sleep(1000 * 60 * 5);
                    continue;
                }

                List<String> ids = IDUtils.GETIDS();
                LOGGER.info("球王贝利循环" + ids.size() + "个号");
                for(String id : ids) {
                    if(targetTime.containsKey(id) && targetTime.get(id).longValue() > sysTime) {
                        continue;
                    }
                    String peleType = OperationUtils.doPele(id);
                    if("-1".equals(peleType)) { //未知状态，延迟1分钟
                        targetTime.put(id, sysTime + 1000 * 60);
                    } else if("0".equals(peleType)) { //全都打完了，延迟1小时
                        targetTime.put(id, sysTime + 1000 * 60 * 60);
                    } else if("1".equals(peleType)) { //比赛中
                        targetTime.put(id, sysTime + 1000 * 60);
                    } else if("2".equals(peleType)) { //正常状态，进入比赛
                        targetTime.put(id, sysTime + 1000 * 60 * 5);
                    }
                }

                long minvalue = getWaitTime(sysTime);
                LOGGER.info("球王贝利循环结束，等待" + minvalue / 1000 + "秒");
                Thread.sleep(minvalue);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
