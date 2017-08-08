package com.saille.newBBS.telnet;

import com.saille.newBBS.UserInfo;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

class DaemonThread extends Thread {
    private int port;
    private ServerSocket server;
    private Map<String, NewBBSMain> threads = new HashMap();
    private final Logger LOGGER = Logger.getLogger(getClass());

    public DaemonThread(int port) {
        try {
            this.port = port;
            ServerSocket server = new ServerSocket(port);
            this.server = server;
            this.LOGGER.info("telnet start, port: " + this.port);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        while(true) {
            try {
                Socket socket = this.server.accept();

                NewBBSMain main = new NewBBSMain(socket);
                UserInfo user = new UserInfo(main.getName());
                main.setUserInfo(user);
                this.threads.put(main.getName(), main);
                main.start();

                continue;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}