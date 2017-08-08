package com.saille.ogzq;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;

public class ConfigUtils {
    public final static String BEGINTIME = "0025"; //每天开始自动程序的时间

    private static PropertiesConfiguration conf;
    public static PropertiesConfiguration teamLevels;

    public static List<String[]> listAll() {
        Iterator it = conf.getKeys();
        List<String[]> ret = new ArrayList<String[]>();
        List<String> keys = new ArrayList<String>();
        while(it.hasNext()) {
            String key = (String) it.next();
            keys.add(key);
        }
        Collections.sort(keys);
        for(String key : keys) {
            ret.add(new String[]{key, conf.getString(key)});
        }
        return ret;
    }

    public static String getConfExact(String email, String type) {
        return conf.getString(type + "." + email);
    }

    public static String getConf(String email, String type) {
        String ret = conf.getString(type + "." + email);
        if(StringUtils.isEmpty(ret)) {
            ret = conf.getString(type);
        }
        return ret;
    }

    public static String saveConf(String key, String value) {
        conf.setProperty(key, value);
        return "";
    }

    public static String deleteConf(String key) {
        conf.setProperty(key, null);
        return "";
    }

    static {
        try {
            conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/config.ini"));
            System.out.println(conf.getEncoding());
            conf.setEncoding("GB2312");
            conf.setAutoSave(true);
            teamLevels = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/trainingteam.ini"));
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}