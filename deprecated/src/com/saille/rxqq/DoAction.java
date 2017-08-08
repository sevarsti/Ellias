package com.saille.rxqq;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.log4j.Logger;

class DoAction extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private String id;
    private String url;

    public DoAction(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public void run() {
        try {
            RxqqUtils.execute(this.id, this.url);
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + this.id + "," + sw.toString());
        }
    }
}