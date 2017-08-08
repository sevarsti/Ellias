package com.saille.ogzq;

import org.apache.log4j.Logger;

public class AutoReloginLoopThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AutoReloginLoopThread.class);

    public AutoReloginLoopThread() {
        this.setName("Thread-AutoReloginLoopThread");
    }

    public void run() {
//        while(true) {
            LOGGER.info("begin loop...");
            try {
                new LockIDThread().start();
                new OgzqDwr().init();
//                Thread.sleep(3600000L);
            } catch(Exception ex) {
            }
//        }
    }
}