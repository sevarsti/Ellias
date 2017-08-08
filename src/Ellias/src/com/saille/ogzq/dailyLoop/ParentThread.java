package com.saille.ogzq.dailyLoop;

import java.util.Map;
import java.util.HashMap;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-27
 * Time: 9:06:20
 * To change this template use File | Settings | File Templates.
 */
public class ParentThread extends Thread {
    public static Map<String, ParentThread> threads = new HashMap<String, ParentThread>();
    protected SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Map<String, Long> targetTime = new HashMap<String, Long>();

    public String lastDate = null;
    protected String threadName;
    protected String lastCheckTime;
    protected String lastEndTime;
    protected long waitTime;

    protected void setThreadname(String name) {
        ParentThread.threads.put(name, this);
    }
    protected long getWaitTime(long sysTime) {
        long minvalue = 1000 * 60 * 60 * 2;
        if(targetTime.size() == 0) {
            minvalue = 1000 * 60;
        } else {
            for(String key : targetTime.keySet()) {
                long target = targetTime.get(key);
                if((target - sysTime) < minvalue) {
                    minvalue = target - sysTime;
                }
            }
        }
        minvalue = minvalue + new Random().nextInt(5) * 1000;
        waitTime = minvalue;
        lastEndTime = sdf3.format(new Date());
        return minvalue;
    }

    public Map<String, Long> getTargetTime() {
        return targetTime;
    }

    public void setTargetTime(Map<String, Long> targetTime) {
        this.targetTime = targetTime;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public String getLastCheckTime() {
        return lastCheckTime;
    }

    public String getLastEndTime() {
        return lastEndTime;
    }

    public long getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(long waitTime) {
        this.waitTime = waitTime;
    }
}
