package com.saille.bbs.yssy.service;

import com.GlobalConstant;
import com.saille.bbs.yssy.YSSYUtil;
import com.txsec.lc.is.security.EncryptUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

public class YssyGIDService extends TimerTask {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private static String KEY = "F289A70483E9C779C175F198";
    private static String PWD = "FK7KDOU42B8ybsV1Sw7YPQ==";
    private String[] ids;
    private Thread daemonThread = null;

    private boolean run = true;

    private List<LogIdThread> threads = new ArrayList();

    public YssyGIDService() {
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration(GlobalConstant.DISKPATH + "vbs\\id.txt");
            this.ids = conf.getStringArray("id");
            this.daemonThread = new Thread(this);
            this.daemonThread.start();
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
    }

    public void run() {
        String pwd = EncryptUtil.decode(PWD, KEY);
        this.LOGGER.info("into run, total " + this.ids.length + " ids");
        for(int i = 0; i < this.ids.length; i++) {
            this.LOGGER.info("id: " + (i + 1) + "/" + this.ids.length + this.ids[i]);
        }
        YSSYUtil util = new YSSYUtil();
        Map cookies = new HashMap();
        for(String id : this.ids) {
            LogIdThread thread = new LogIdThread(id, pwd);
            thread.start();
            this.threads.add(thread);
        }
        this.LOGGER.info("login end");
    }

    public void destroy() {
        if(this.daemonThread != null) {
            this.daemonThread.interrupt();
            this.LOGGER.info("ÇåËã Background Thread Stopped!");
        }
        for(LogIdThread t : this.threads) {
            t.stop();
        }
    }

    public boolean isRun() {
        return this.run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
}