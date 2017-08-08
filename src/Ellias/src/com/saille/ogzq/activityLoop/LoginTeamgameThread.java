package com.saille.ogzq.activityLoop;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import com.saille.ogzq.LoginUtils;
import com.saille.ogzq.OgzqURL;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.ConfigUtils;

import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-9-20
 * Time: 8:51:39
 * To change this template use File | Settings | File Templates.
 */
public class LoginTeamgameThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(LoginTeamgameThread.class);

    public static String checkEmail = null;
    public static Vector<String> paiziEmails = new Vector<String>();
    public static Vector<String> litEmails = new Vector<String>();
    public static Map<String, String> pwds = new HashMap<String, String>();

    public LoginTeamgameThread() {
        try {
            File paiziFile = new File(ConfigUtils.class.getResource("../../../../../ogzq/paizi.txt").getPath());
            File litFile = new File(ConfigUtils.class.getResource("../../../../../ogzq/lit.txt").getPath());
            FileReader paiziFR = new FileReader(paiziFile);
            FileReader litFR = new FileReader(litFile);
            BufferedReader paiziBR = new BufferedReader(paiziFR);
            BufferedReader litBR = new BufferedReader(litFR);
            String s;
            int count = 1;
            long start = System.currentTimeMillis();
            litEmails = new Vector<String>();
            while((s = litBR.readLine()) != null) {
                litEmails.add(s.split("\t")[0]);
                pwds.put(s.split("\t")[0], s.split("\t")[1]);
            }
            paiziEmails = new Vector<String>();
            while((s = paiziBR.readLine()) != null) {
                paiziEmails.add(s.split("\t")[0]);
                pwds.put(s.split("\t")[0], s.split("\t")[1]);
            }
            LOGGER.info("全部读取完毕，共有" + paiziEmails.size() + "个大号，" + litEmails.size() + "个小号");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            Date d = new Date();
            String tmp = sdf.format(d);
            while(tmp.compareTo("0901") < 0) {
                System.out.println("等待球会战进场");
                try {
                    Thread.sleep(1000l*60);
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
                d = new Date();
                tmp = sdf.format(d);
            }
            checkEmail = IDUtils.GETIDS().get(0);
            int[] result = checkPage(0, null);
            int maxpage = result[3];//[0]本页1牌还是2牌，[1]本页球会数，[2]第几页，[3]一共几页
            while(litEmails.size() > 0) {
                result = checkPage(maxpage, null);
                maxpage = result[3];
                if((result[2] == result[3] && result[1] == 10) ||
                        (result[1] < 3 && result[3] == 0)) { //需要进牌子
                    if(paiziEmails.size() > 0) {
                        String email = paiziEmails.get(0);
                        System.out.println("本页有" + result[1] + "个球会，第" + result[2] + "/" + result[3] + "页，进牌子: " + email);
                        paiziEmails.remove(0);

                        String pwd = pwds.get(email);
//                        email = email.split("\t")[0];
                        HttpClient client = LoginUtils.Login(email, pwd);
//                        TeamGameThread.CLIENTS.put(email, client);
                        enterTeamgame(email, paiziEmails, client);
                        Thread.sleep(1000);
                    } else {
                        String email = litEmails.get(0);
                        litEmails.remove(0);

                        String pwd = pwds.get(email);
//                        email = email.split("\t")[0];
                        HttpClient client = LoginUtils.Login(email, pwd);
//                        TeamGameThread.CLIENTS.put(email, client);
                        enterTeamgame(email, litEmails, client);
                        Thread.sleep(1000);
                    }
                } else {
                    String email = litEmails.get(0);
                    System.out.println("本页有" + result[1] + "个球会，第" + result[2] + "/" + result[3] + "页，进lit: " + email);
                    litEmails.remove(0);

                    String pwd = pwds.get(email);
//                    email = email.split("\t")[0];
                    HttpClient client = LoginUtils.Login(email, pwd);
//                    TeamGameThread.CLIENTS.put(email, client);
                    enterTeamgame(email, litEmails, client);
                    Thread.sleep(1000);
//                    enterTeamgame(email, TeamGameThread.litEmails);
                }
            }
            LOGGER.info("全部进号完毕");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean enterTeamgame(String email, List<String> list, HttpClient client) {
        try {
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("checkSignup", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(client, email, pm);
            if(s.equals("-3")) {
                LOGGER.warn(email + "没有球会！！！！！！");
                return false;
            } else if(s.equals("-1")) {
//                Thread.sleep(100);
                return true;
//            } else if(Integer.parseInt(s) > 0) {
            } else if(s.indexOf("form name=\"form1\"") >= 0) {
                return true;
            } else {
                return false;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            list.add(email);
            return false;
        }
    }

    public static int[] checkPage(int page, StringBuffer sb) throws Exception {
        int[] ret = new int[4]; //[0]本页1牌还是2牌，[1]本页球会数，[2]第几页，[3]一共几页
        while(true) {
            try {
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("pageindex", page + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(checkEmail, pm);
                String[] ss = s.split("@");
                if(s.equals("@1")) {
                    return new int[]{3,0,1,1};
                }
                ret[0] = Integer.parseInt(ss[0].split("[*]")[0].split("[|]")[5]);
                ret[1] = ss[0].split("[*]").length;
                ret[2] = Integer.parseInt(ss[1].split("[|]")[0]);
                ret[3] = Integer.parseInt(ss[1].split("[|]")[1]);
                if(sb != null) {
                    sb.append(ss[0].split("[*]")[0].split("[|]")[0]);
                }
                return ret;
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
