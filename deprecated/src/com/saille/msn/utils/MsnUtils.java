package com.saille.msn.utils;

import com.saille.msn.MsnMain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MsnUtils {
    public static Map<String, MsnMain> messengers = new HashMap<String, MsnMain>();

    public List<List<String[]>> updateStatus(String email) {
        MsnMain main = messengers.get(email);
        List<List<String[]>> ret = new ArrayList<List<String[]>>();
        if(main != null) {
            List<String[]> messages = loadMessage(main);
            ret.add(messages);

            List<String[]> statusChanged = loadStatusChanged(main);
            ret.add(statusChanged);

            List<String[]> updateContactList = loadContactList(main);
            ret.add(updateContactList);
        }

        return ret;
    }

    public void sendMessage(String currentEmail, String target, String message) {
        MsnMain main = messengers.get(currentEmail);
        main.sendMessage(target, message);
    }

    private List<String[]> loadMessage(MsnMain main) {
        List<String[]> list = main.getUnreadMessage();
        List<String[]> ret = new ArrayList<String[]>();
        for(String[] s : list) {
            ret.add(new String[]{s[0], s[1], s[2], s[3], s[4]});
        }
        main.updateMessage(null, null, null, null, 2);
        return ret;
    }

    private List<String[]> loadStatusChanged(MsnMain main) {
        List<String[]> list = main.getUnreadContactStatusChanged();
        List<String[]> ret = new ArrayList<String[]>();
        for(String[] s : list) {
            ret.add(new String[]{s[0], s[1], s[2], s[3], s[4]});
        }
        main.updateContactStatusChanged(null, null, null, null, 2);
        return ret;
    }

    private List<String[]> loadContactList(MsnMain main) {
        List<String[]> list = main.getUnreadContact();
        List<String[]> ret = new ArrayList<String[]>();
        for(String[] s : list) {
            ret.add(new String[]{s[0], s[1], s[2], s[3], s[4]});
        }
        main.updateContactList(null, null, null, null, 2);
        return ret;
    }
}