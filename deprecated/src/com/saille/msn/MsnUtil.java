package com.saille.msn;

import net.sf.jml.MsnMessenger;
import net.sf.jml.impl.MsnMessengerFactory;

public class MsnUtil {
    public MsnMessenger login(String username, String pwd) {
        MsnMessenger messenger = MsnMessengerFactory.createMsnMessenger(username, pwd);
        return messenger;
    }
}