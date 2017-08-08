package com;

import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class GlobalConstant {
    public static final String BOOKPATH = "D:\\work\\Ellias\\book\\";
    public static Properties properties;
    public static int i = 0;
    public static boolean EDU = true;
    public static String TARGETIDPATH;
    public static final SimpleDateFormat DF_YYYY_MM_DD_HH_MM_SS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private GlobalConstant() {
        try {
            ClassLoader classloader = Thread.currentThread().getContextClassLoader();
            URL url = classloader.getResource("../config.ini");
            InputStream inputstream = url.openStream();
            properties = new Properties();
            properties.load(inputstream);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean getEDU() {
        return EDU;
    }

    public void setEDU(boolean edu) {
        EDU = edu;
    }

    public String getTARGETIDPATH() {
        return TARGETIDPATH;
    }

    public void setTARGETIDPATH(String TARGETIDPATH) {
        TARGETIDPATH = TARGETIDPATH;
    }
}