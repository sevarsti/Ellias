package com.saille.ogzq.loop;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OperationUtils;

import java.util.Map;

import org.apache.log4j.Logger;

public class DoChallengeThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(DoChallengeThread.class);
    private String email;
    private long i;

    public DoChallengeThread(String email, long id) {
        this.email = email;
        this.i = id;
    }

    public void run() {
        LOGGER.info(this.email + " " + getClass().getName() + " started");
        while(true) {
            try {
                if(this.i != ((Long) IDUtils.IDThreads.get(this.email)).longValue()) {
                    break;
                }
                String doChallenge = ConfigUtils.getConf(this.email, "ÊÇ·ñÌßÑ²»ØÈü");
                if(doChallenge.equals("1")) {
                    OperationUtils.doChallenge(this.email);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(300000L);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}