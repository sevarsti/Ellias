package com.saille.ogzq.loop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-27
 * Time: 13:15:56
 * To change this template use File | Settings | File Templates.
 */
public class ClubBuffThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ClubBuffThread.class);
    //0=球会名称,1=球会ID,2=贡献,3=训练赛次数,4=训练赛恢复,5=竞技场积分,6=经纪人效率,7=训练赛银币,8=球会赛打残,9=全都升满
    public static Map<String, String[]> buff = new HashMap<String, String[]>();
    public static Map<String, String[]> upCost = new HashMap<String, String[]>();
    private static ClubBuffThread instance;
    private ClubBuffThread() {}

    public synchronized static ClubBuffThread getInstance() {
        if(instance == null) {
            instance = new ClubBuffThread();
        }
        return instance;
    }

    public void run() {
        LOGGER.info("球会福利线程启动");
        while(true) {
            try {
                List<String> ids = IDUtils.GETIDS();
                for(String id : ids) {
                    try {
                        updateClub(id);
                    } catch(Exception ex) {
                        System.out.println(id + "/" + IDUtils.getNick(id) + "读取球会buff失败：");
                        ex.printStackTrace();
                    }
                }
                LOGGER.info("球会福利更新完毕，等待1小时");
                Thread.sleep(1000 * 60 * 60);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void updateClub(String id) throws Exception {
        String[] ss = new String[10];
        String[] ssCost = new String[10];

        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "12"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(id, pm);
        ss[0] = s.split("\\|")[4] + "|" + s.split("\\|")[1];
        s = s.split("\\|")[0];
        ss[1] = s;

        //club
        pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("TeamId", s));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(id, pm);
        String[] parts = s.split("!")[2].split("@")[1].split("&");
        for(int i = 0; i < 6; i++) {
            String maxlv = parts[i].split("\\*")[0].split("\\|")[4];
            ss[i + 3] = maxlv;
        }

        //my
        pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "23"));
        params.add(new BasicNameValuePair("TeamId", s));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(id, pm);
        ss[2] = s.split("@")[0];
        parts = s.split("@")[1].split("&");
        boolean allfull = true;
        for(int i = 0; i < 6; i++) {
            String max = ss[i + 3];
            String currentlv = parts[i].split("\\|")[4];
            String cost = "";
            if(Integer.parseInt(currentlv) < Integer.parseInt(max)) {
                cost = parts[i].split("\\*")[1].split("\\|")[2];
            }
            ss[i + 3] = currentlv + "-" + max;
            ssCost[i + 3] = cost.equals("") ? ("") : (": " + cost);
            if(!currentlv.equals(max)) {
                allfull = false;
            }
//            ss[i + 5] = currentlv + "-" + max + (cost.equals("") ? ("") : (": " + cost));
        }
        ss[9] = allfull ? "1" : "0";
        buff.put(id, ss);
        upCost.put(id, ssCost);
        //0=球会名称,1=球会ID,2=贡献,3=训练赛次数,4=训练赛恢复,5=竞技场积分,6=经纪人效率,7=训练赛银币,8=球会赛打残,9=全都升满
    }

    public static boolean checkAllFull(String id) {
        if(buff == null) {
            return false;
        }
        if(!buff.containsKey(id)) {
            return false;
        }
        String full = buff.get(id)[9];
        return full.equals("1");
    }
}
