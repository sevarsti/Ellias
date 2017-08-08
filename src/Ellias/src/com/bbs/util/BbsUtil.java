package com.bbs.util;

import com.GlobalConstant;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

public class BbsUtil {
    private static BbsUtil instance;
    private Connection connection;
    private Statement stmt;

    private BbsUtil() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + String.valueOf(GlobalConstant.properties.get("database")) + "?characterEncoding=" + String.valueOf(GlobalConstant.properties.get("dbEncoding")), String.valueOf(GlobalConstant.properties.get("dbuser")), String.valueOf(GlobalConstant.properties.get("dbpwd")));

            this.stmt = this.connection.createStatement(1005, 1008);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static BbsUtil getInstance() {
        try {
            if((instance == null) || (instance.connection.isClosed())) {
                instance = new BbsUtil();
            }
        } catch(Exception ex) {
            instance = new BbsUtil();
        }
        return instance;
    }

    public String searchPost(String boardname, String context, int day) {
        try {
            StringBuffer sb = new StringBuffer();

            URL url = new URL(String.valueOf(GlobalConstant.properties.get("bbssite")) + "/bbsbfind?board=" + boardname + "&title=" + context + "&dt=" + day);

            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String tmp;
            while((tmp = in.readLine()) != null) {
                sb.append(tmp);
            }
            return sb.toString();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String readPost(String address) {
        try {
            StringBuffer sb = new StringBuffer();

            URL url = new URL(String.valueOf(GlobalConstant.properties.get("bbssite")) + address);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String tmp;
            while((tmp = in.readLine()) != null) {
                sb.append(tmp);
            }
            return sb.toString();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}