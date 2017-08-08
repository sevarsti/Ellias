package com.saille.sys;

import java.util.Date;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-14
 * Time: 11:25:16
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseThread extends Thread{
    protected abstract int execute();
    private Date lastExecuteTime = null;
    private Date nextExecuteTime = null;
    private int interval;
    public static Map<String, BaseThread> threads = new HashMap<String, BaseThread>();

    protected BaseThread(int interval) {
        this.interval = interval;
        threads.put(this.getClass().getName(), this);
    }

    public void run() {
        while(true) {
            int waittime = 0;
            try {
                waittime = execute();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            if(waittime == 0) {
                lastExecuteTime = new Date();
                nextExecuteTime = new Date(lastExecuteTime.getTime() + 1000 * 60 * interval);
                try {
                    Thread.sleep(1000 * 60 * interval);
                } catch(Exception ex) {
//                    ex.printStackTrace();
                }
            } else {
                nextExecuteTime = new Date(new Date().getTime() + 1000 * waittime);
                try {
                    Thread.sleep(1000 * waittime);
                } catch(Exception ex) {
//                    ex.printStackTrace();
                }
            }
        }
    }

    public Date getLastExecuteTime() {
        return lastExecuteTime;
    }

    public void setLastExecuteTime(Date lastExecuteTime) {
        this.lastExecuteTime = lastExecuteTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public Date getNextExecuteTime() {
        return nextExecuteTime;
    }

    public void setNextExecuteTime(Date nextExecuteTime) {
        this.nextExecuteTime = nextExecuteTime;
    }
}
