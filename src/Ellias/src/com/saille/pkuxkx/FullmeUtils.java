package com.saille.pkuxkx;

import com.saille.util.SendSMSUtils;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Ellias on 2018-07-18.
 */
public class FullmeUtils {
    private static Map<String, String> FULLME = new HashMap<String, String>(); //Map<filename, content>
    private static Map<String, Long> FULLMETIME = new HashMap<String, Long>();

    public static void add(String url) {
        clearExpired();
        FULLME.put(url, "");
        FULLMETIME.put(url, System.currentTimeMillis());
        SendSMSUtils.sendSMS("fullme��֤�룺" + url);
    }

    public static String getValue(String url) {
        clearExpired();
        String value = FULLME.get(url);
        if(StringUtils.isBlank(value)) {
            return "";
        }
        return value;
    }

    public static List<String> getUnfinished() {
        clearExpired();
        List<String> keys = new ArrayList<String>(FULLME.keySet());
        for(int i = keys.size() - 1; i >= 0; i--) {
            String value = FULLME.get(keys.get(i));
            if(value != null && value.length() > 0) {
                keys.remove(i);
            }
        }
        Collections.sort(keys);
        return keys;
    }

    public boolean doFullme(String url, String value) {
        clearExpired();
        if(FULLME.containsKey(url)) {
            FULLME.put(url, value);
            return true;
        }
        return false;
    }

    private static void clearExpired() {
        long now = System.currentTimeMillis();
        List<String> keys = new ArrayList<String>(FULLME.keySet());
        for(String k : keys) {
            long begin = FULLMETIME.get(k).longValue();
            if(now - begin >= 1000 * 60 * 5) {
                FULLME.remove(k);
                FULLMETIME.remove(k);
            }
        }
    }
}
