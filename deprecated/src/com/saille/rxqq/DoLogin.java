package com.saille.rxqq;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

class DoLogin extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private String id;
    private String pwd;

    public DoLogin(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

    public void run() {
        RxqqUtils.login(this.id, this.pwd);
        System.out.println(this.id + "登录完成");
        this.LOGGER.info("id共有" + RxqqInstance.id.size() + "个");
        RxqqUtils.myInfo(this.id);
        String string = RxqqUtils.myInfo(this.id);
        try {
            JSONObject json = new JSONObject(string);
            RxqqInstance.info.put(this.id, json);
            Loop thread = new Loop(this.id);
            thread.start();
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.info(string);
            this.LOGGER.error("id: " + this.id + "," + sw.toString());
        }
    }
}