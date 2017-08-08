package com.saille.ogzq;

import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import java.util.*;

import com.saille.util.SortUtils;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-9-12
 * Time: 15:36:07
 * To change this template use File | Settings | File Templates.
 */
public class ViewAbilityThread extends Thread{
    public static Map<String, Map<String, String>> SHILI = new HashMap<String, Map<String, String>>();
    private int[] currStatus = new int[2];
    private boolean cont = true;
    private static ViewAbilityThread instance = null;
    public static Map<String, Map<String, String>> CLUBS = new HashMap<String, Map<String, String>>();

    private ViewAbilityThread() {}

    public static ViewAbilityThread getInstance() {
        if(instance == null) {
            instance = new ViewAbilityThread();
        }
        return instance;
    }
    public void run() {
        while(cont) {
            try {
                HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "0"));
                params.add(new BasicNameValuePair("pgIndex", "" + currStatus[0]));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(getEmail(), pm);
                String[] clubs = ret.split("@")[1].split("&");
                String[] parts = clubs[currStatus[1]].split("[*]");
                Map<String, String> attrs = new HashMap<String, String>();
                attrs.put("id", parts[0]);
                attrs.put("level", parts[1]);
                attrs.put("name", parts[3]);
                attrs.put("abbri", parts[2]);
                attrs.put("count", parts[4]);
                attrs.put("captain", parts[5].split("[|]")[1]);
                attrs.put("shengwang", parts[6]);
                attrs.put("pos", parts[8]);
                CLUBS.put(parts[0], attrs);
//                117*6*刃*皇家马德里_s6*6|18*141969|尤文吐丝|67*1492448*0*44
                loadClub(parts[0]);
                currStatus[1]++;
                if(currStatus[1] > 10) {
                    currStatus[0]++;
                    currStatus[1] = 0;
                }
                if(currStatus[0] > 10) {
                    currStatus[0] = 0;
                }
                Thread.sleep(1000 * 60);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void loadClub(String clubId) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        params.add(new BasicNameValuePair("TeamID", "" + clubId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(getEmail(), pm);

        String attrStr = ret.split("!")[0].split("@")[1];
        String[] parts = attrStr.split("[|]");
        Map<String, String> attrs = new HashMap<String, String>();
        attrs.put("id", clubId);
        attrs.put("level", parts[4]);
        attrs.put("name", parts[1]);
        attrs.put("abbri", parts[0]);
        attrs.put("count", parts[6]);
        attrs.put("captain", parts[2]);
        attrs.put("shengwang", parts[11]);
        attrs.put("pos", "" + (Integer.parseInt(parts[7]) - 1));
        attrs.put("paizi", parts[5]);
        CLUBS.put(clubId, attrs);

        ret = ret.split("!")[1];
        String[] teams = ret.split("&");
        for(int i = 0; i < teams.length; i++) {
            String team = teams[i];
//            System.out.println(i + "/" + teams.length + "更新球会：" + team);
            String teamId = team.split("[|]")[0];
            Map<String, String> details = loadPlayer(teamId);
            details.put("clubid", clubId);
        }
    }

    public static Map<String, String> loadPlayer(String playerId) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadTeam1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(getEmail(), pm);
        String[] parts = s.split("#");
        String ability = parts[24];
        String name = parts[0];
        String pos = parts[3];
        ret.put("id", playerId);
        ret.put("name", name);
        ret.put("pos", pos);
        ret.put("ability", ability);
        String club = parts[28];
        if(club.indexOf("[") >= 0) {
            club = club.substring(1);
            club = club.substring(0, club.length() - 1);
            ret.put("club", club);
        } else {
            ret.put("club", "");
        }
        SHILI.put(playerId, ret);
        return ret;
    }

    private static String getEmail() {
        List<String> ids = IDUtils.GETIDS();
        return ids.get(new Random().nextInt(ids.size()));
    }

    public static List<Map<String, String>> getPos() {
        Set<String> keys = SHILI.keySet();
        List<Map<String, String>> players = new ArrayList<Map<String, String>>();
        for(String key : keys) {
            players.add(SHILI.get(key));
        }
        players = SortUtils.sortPlayerAbility(players, 0, players.size());
        return players;
    }

    public static List<Map<String, String>> getClub() {
        Set<String> keys = CLUBS.keySet();
        List<Map<String, String>> clubs = new ArrayList<Map<String, String>>();
        for(String key : keys) {
            clubs.add(CLUBS.get(key));
        }
        clubs = SortUtils.sortClubAbility(clubs, 0, clubs.size());
        return clubs;
    }
}
