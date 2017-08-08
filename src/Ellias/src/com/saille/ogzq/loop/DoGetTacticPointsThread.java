package com.saille.ogzq.loop;

import org.apache.log4j.Logger;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.ConfigUtils;
import com.saille.ogzq.OperationUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-11
 * Time: 23:33:11
 * To change this template use File | Settings | File Templates.
 */
public class DoGetTacticPointsThread extends Thread{
    private static final Logger LOGGER = Logger.getLogger(DoGetTacticPointsThread.class);
    private String email;
    private long i;

    public DoGetTacticPointsThread(String email, long id) {
        this.email = email;
        this.i = id;
    }

    public void run() {
        LOGGER.info(this.email + " " + getClass().getName() + " started");
        int waitSec = 86400;
        while(true) {
            try {
                if(this.i != ((Long) IDUtils.IDThreads.get(this.email)).longValue()) {
                    break;
                }
                waitSec = OperationUtils.getTacticPoint(this.email);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(waitSec * 1000);
            } catch(InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}
