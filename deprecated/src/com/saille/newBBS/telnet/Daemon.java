package com.saille.newBBS.telnet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

public class Daemon implements InitializingBean {
    private int port;
    private static final Logger LOGGER = Logger.getLogger(Daemon.class);
    private DaemonThread thread;

    public int getPort() {
        return this.port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void afterPropertiesSet() {
    }

    public void destroy() {
        this.thread.interrupt();
    }
}