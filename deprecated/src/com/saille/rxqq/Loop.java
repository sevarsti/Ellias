package com.saille.rxqq;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;

class Loop extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private String id;

    public Loop(String id) {
        this.id = id;
    }

    public void run() {
        while(true) {
            try {
                if(!RxqqInstance.id.containsKey(this.id)) {
                    break;
                }
                Thread.sleep(60000L);
                String s = RxqqUtils.myInfo(this.id);
                RxqqUtils.rank(this.id);
                RxqqUtils.getMatchPlayer(this.id);

                continue;
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                this.LOGGER.error("id: " + this.id + "," + sw.toString());
            }
        }
    }
}