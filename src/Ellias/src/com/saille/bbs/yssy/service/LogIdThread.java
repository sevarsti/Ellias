package com.saille.bbs.yssy.service;

import com.saille.bbs.yssy.YSSYUtil;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

class LogIdThread extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private Map<String, String[]> cookies = new HashMap();
    private String id;
    private String pwd;
    private boolean run = true;

    public LogIdThread(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public void run() {
        YSSYUtil util = new YSSYUtil();
        String[] c = new String[3];
        for(int i = 0; i < 3; i++) {
            this.LOGGER.info("id: " + this.id + "\t" + i);
            String cookie = util.login(this.id, this.pwd);
            this.LOGGER.info("cookie: " + cookie);
            try {
                Thread.sleep(6000L);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            c[i] = cookie;
        }
        this.cookies.put(this.id, c);

        while(this.run) {
            try {
                Thread.sleep(600000L);
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                this.LOGGER.error(sw.toString());
            }
            for(String cc : (String[]) this.cookies.get(this.id)) {
                if((cc == null) || (!StringUtils.isNotEmpty(cc))) {
                    continue;
                }
                try {
                    util.viewSmd(this.id, cc);
                    Thread.sleep(1000L);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                this.LOGGER.info("cookie: " + c);
            }
        }
    }
}