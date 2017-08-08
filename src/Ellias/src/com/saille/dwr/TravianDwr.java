package com.saille.dwr;

import com.saille.html.travian.TravianMain;
import com.saille.html.travian.TravianUserMain;

import java.io.PrintStream;

public class TravianDwr {
    public void useDwr() {
        System.out.println("get here");
    }

    public void refreshUser(String userid) {
        TravianMain main = TravianMain.getInstance();
        TravianUserMain user = main.getUser(userid);
        if(user == null) {
            return;
        }
        user.refreshTowns();
    }

    public void addUser(String userid, String pwd) {
        TravianMain main = TravianMain.getInstance();
        main.addUser(userid, pwd);
    }

    public void deleteUser(String userid) {
        TravianMain.getInstance().deleteUser(userid);
    }

    public String refreshMerchant(String userid, int townId) {
        TravianUserMain user = TravianMain.getInstance().getUser(userid);
        if(user == null) {
            return null;
        }
        return user.viewMerchant(townId);
    }
}