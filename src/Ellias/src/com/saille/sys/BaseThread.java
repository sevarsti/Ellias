package com.saille.sys;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
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
    private final static Logger LOGGER = Logger.getLogger(BaseThread.class);
    protected abstract int execute();
    private Date lastExecuteTime = null;
    private Date nextExecuteTime = null;
    private int interval;
    private int start;
    protected boolean running = false;
    public static Map<String, BaseThread> threads = new HashMap<String, BaseThread>();

    protected BaseThread() {
    }

    public static synchronized void createInstance(String classname, int start, int interval) {
        if(!threads.containsKey(classname) || threads.get(classname) == null) {
            try {
                Class c = Class.forName(classname);
                Constructor con = c.getDeclaredConstructor();
                con.setAccessible(true);
                Object obj = con.newInstance();
                con.setAccessible(false);
                if(!(obj instanceof BaseThread)) {
                    LOGGER.error(classname + "不是BaseThread的子类！");
                    return;
                }
                BaseThread instance = (BaseThread) obj;
                instance.setDaemon(true);
                instance.start = start;
                instance.interval = interval;
                threads.put(instance.getClass().getName(), instance);
                instance.start();
                LOGGER.info("★★★" + (start == 1 ? "启动" : "暂停") + "线程 ：" + classname);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static BaseThread findThread(String threadname) {
        BaseThread thread = threads.get(threadname);
        return thread;
    }

    public void run() {
        while(true) {
            int waittime = 0;
            try {
                this.running = true;
                if(start == 1) {
                    waittime = execute();
                }
                this.running = false;
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

    public void interrupt() {
        if(this.running) {
            return;
        }
        super.interrupt();
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
