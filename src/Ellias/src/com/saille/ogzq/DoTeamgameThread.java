package com.saille.ogzq;

import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OperationUtils;

import java.util.Calendar;

import org.apache.log4j.Logger;

public class DoTeamgameThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(DoTeamgameThread.class);
    private String email;
    private long i;

    public DoTeamgameThread(String email, long id) {
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
                Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                if((!ConfigUtils.getConf(this.email, "是否踢球会大作战").equals("1")) || (hour >= 20) || (hour <= 12)) {
                    continue;
                }
                OperationUtils.teamGame(this.email, 1);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(200000L);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}