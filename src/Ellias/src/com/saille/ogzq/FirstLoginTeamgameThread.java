package com.saille.ogzq;

import com.saille.util.UtilFunctions;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-6-5
 * Time: 9:39:53
 * To change this template use File | Settings | File Templates.
 * 每天9点之前进场抢位子
 */
public class FirstLoginTeamgameThread extends Thread{
    private final Logger LOGGER = Logger.getLogger(FirstLoginTeamgameThread.class);
    private String[] emails;

    public FirstLoginTeamgameThread(String[] emails) {
        this.emails = emails;
    }
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        Date d = new Date();
        while(sdf.format(d).compareTo("0902") < 0) {
            for(String email : emails) {
                try {
                    OperationUtils.signupTeamGame(email);
                } catch(Exception ex) {
                    UtilFunctions.LogError(LOGGER, ex);
                }
            }
            d = new Date();
        }
    }
}
