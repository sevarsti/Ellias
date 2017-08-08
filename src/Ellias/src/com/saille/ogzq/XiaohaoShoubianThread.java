package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-9-10
 * Time: 18:40:12
 * To change this template use File | Settings | File Templates.
 */
public class XiaohaoShoubianThread extends Thread {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private List<String> emails = new ArrayList<String>();
    private SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
    private String[] coaches = new String[]{"瓜迪奥拉", "穆里尼奥", "弗格森", "米卢", "里皮", "希丁克"};
    private Map<String, List<String[]>> coachStatus = new HashMap<String, List<String[]>>();
    private Map<String, Integer> abilities = new Hashtable<String, Integer>();
    private List<String> cannotShoubian = new ArrayList<String>();
    public boolean cont = true;
    public boolean running = false;
    private int sevarstiPlace = 0;

    public XiaohaoShoubianThread() {
        this.setName("XiaohaoShoubianThread");
        this.emails = IDUtils.GETIDS();
    }

    public void run() {
        while(cont) {
            if(this.emails.size() == 0) {
                LOGGER.info("所有号收编完成，收编结束");
                cont = false;
                break;
            }
            this.running = true;
            String sevarstiPlace = MonitorTeamgameThread.getInstance().myPlaces.get("sevarsti@sina.com")[6];
            this.sevarstiPlace = Integer.parseInt(sevarstiPlace.split("-")[0]) * 10 + Integer.parseInt(sevarstiPlace.split("-")[1]);
            try {
                Calendar c = Calendar.getInstance();
                if(Integer.parseInt(sdf.format(c.getTime())) < 1829) {
                    try{
                        LOGGER.info("小号时间未到，延时30秒");
                        Thread.sleep(30 * 1000);
                        continue;
                    } catch(Exception ex) {}
                } else if(Integer.parseInt(sdf.format(c.getTime())) >= 2001) {
                    for(int i = 0; i < emails.size(); i++) {
                        changeToNormal(emails.get(i));
                    }
                    LOGGER.info("球会战结束，退出收编");
                    break;
                }
                for(int i = emails.size() - 1; i >= 0; i--) {
                    String email = emails.get(i);
                    if(email.startsWith("txjcf")) {
                        LOGGER.info(email + "资料号不收编");
                        emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                    if(MonitorTeamgameThread.getInstance().myPlaces.get(email) == null) {
                        LOGGER.info(email + "没有进场，不收编");
                        emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                    String myPlace = MonitorTeamgameThread.getInstance().myPlaces.get(email)[6];
                    int placeInt = Integer.parseInt(myPlace.split("-")[0]) * 10 + Integer.parseInt(myPlace.split("-")[1]);
                    if(Math.abs(placeInt - this.sevarstiPlace) <= 10) {
                        LOGGER.info(email + "球会战在主号附近，不收编");
                        emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                    ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("load", "1"));
                    params.add(new BasicNameValuePair("rank", myPlace.split("-")[0]));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String sss = IDUtils.execute(email, pm);
                    if(sss.indexOf("inmatch") != -1) {
                        continue;
                    }

                    getCurrentCoach(email);
                    int idx = -1;
                    boolean noWeixing = false;
                    if(coachStatus.size() == 0 || !coachStatus.containsKey(email) || coachStatus.get(email).size() == 0) {
                        continue;
                    }
                    for(String coachName : coaches) {
                        for(idx = 0; idx < 6; idx++) {
                            if(coachStatus.get(email).get(idx)[4].equals(coachName)) {
                                if(coachStatus.get(email).get(idx)[0].equals("0")) { //没有教练
                                } else if(coachStatus.get(email).get(idx)[14].equals("0")) { //没有卫星
                                    noWeixing = true;
                                }
                                break;
                            }
                        }
                        if(noWeixing) {
                            break;
                        }
                    }
                    if(!noWeixing) { //都收满了
                        LOGGER.info(email + "收满卫星，退出循环");
//                        OperationUtils.changeTactic(email, 1);
                        this.emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                    LOGGER.info(email + "需要收编卫星的教练：" + idx + "/" + coachStatus.get(email).get(idx)[4]);
                    String[] coach = coachStatus.get(email).get(idx);
                    changeCoach(email, coach[4]);

                    String[] nextClubId = getNextOpponent(email);
                    if(nextClubId == null) { //没有下个对手，说明没办法收编了
                        LOGGER.info(email + "没有下一个对手，跳出循环");
                        this.emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                    LOGGER.info(email + "下一个对手：" + nextClubId[0] + "/" + nextClubId[1]);
                    Boolean inMatch = doMatch(email, nextClubId[0], nextClubId[1]);
                    if(inMatch == null) {
                        LOGGER.info(email + "是卫星，不能收编");
                        this.emails.remove(i);
                        changeToNormal(email);
                        continue;
                    }
                }
                Thread.sleep(10000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        this.running = false;
    }

    //返回值：是否进入比赛
    private Boolean doMatch(String email, String clubId, String nick) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/MatchList/TeamGame/TeamGame.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("insertMatch", clubId));
        params.add(new BasicNameValuePair("MatchPrice", "-1200"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        int intRet = Integer.parseInt(ret);
        LOGGER.info(email + "进行比赛结果：" + intRet);
        if(intRet > 0) {
            return true;
        } else if(intRet == -1) { //挑战失败。
        } else if(intRet == -2) { //球会大作战每天9：00~20：00开启。
        } else if(intRet == -3) { //您不能挑战此名次的球队。
        } else if(intRet == -4) { //您的球队正在比赛，暂时不能挑战。
            return true;
        } else if(intRet == -5) { //对方正在比赛，暂时不能挑战。
        } else if(intRet == -100) { //您今日挑战次数已达60次上限，请明日再来挑战。
        } else if(intRet == -10 || intRet == -8) { //挑战失败。
        } else if(intRet == -9) { //请您选择打残程度
        } else if(intRet == -6) { //金币不足
        } else if(intRet == -7) { //您处于伤残模式不能邀战
        } else if(intRet == -11) { //该玩家已被打残
        } else if(intRet == -10001) { //您当前主教练已有卫星队，请更换主教练。
        } else if(intRet == -10002) { //您的身份是卫星队，不能收编其他玩家。
            LOGGER.info(email + "是卫星，不能收编，退出循环");
            return null;
        } else if(intRet == -10003) { //此玩家是主人队，不能收编。
            cannotShoubian.add(nick);
        } else if(intRet == -10004) { //此玩家已经是其他玩家的卫星队，不能收编。
            cannotShoubian.add(nick);
        } else if(intRet == -100041) { //此玩家已经是您的卫星队，不能收编。
            cannotShoubian.add(nick);
        } else if(intRet == -10005) { //此玩家今日已不能成为卫星队。
            cannotShoubian.add(nick);
        } else if(intRet == -10006) { //您没有主教练。
        } else if(intRet == -10008) { //您正在使用夺取功能，不能执行当前任务。
        } else if(intRet == -1000) { //收编伤残
        }
        return false;
    }

    private String[] getNextOpponent(String email) throws Exception {
        String myPlace = MonitorTeamgameThread.getInstance().myPlaces.get(email)[6];
        int myPage = Integer.parseInt(myPlace.substring(0, myPlace.indexOf("-")));
        int myIndex = Integer.parseInt(myPlace.substring(myPlace.indexOf("-") + 1));
        int count = 0;
        int myShili = Integer.parseInt(IDUtils.IDInfos.get(email).get("shili"));
        String[] retClubId = null;
        String[] retBackup = null;
        for(int i = 0; i < 10; i++) {
            int page = myPage - 1, idx = myIndex + 1 + i;
            if(idx > 10) {
                page += 1;
                idx -= 10;
            }
            String[] opp = MonitorTeamgameThread.getInstance().places.get((page+1) + "-" + (idx));
            if(opp == null) {
                return null;
            }
            if(opp[9].equals("囧")) {
                continue;
            }
            if(cannotShoubian.contains(opp[2])) {
                continue;
            }
            int oppAbility = 0;
            if(abilities.containsKey(opp[2])) {
                oppAbility = abilities.get(opp[2]);
            } else {
                oppAbility = getPlayerAbility(email, opp[0], opp[9]);
                abilities.put(opp[2], oppAbility);
            }
            if(oppAbility > (myShili - 5000)) {
                continue;
            }
            if(opp[7].equals("3")) { //打残中，直接返回
                return new String[]{opp[0], opp[2]};
            } else if(opp[7].equals("0")) { //无比赛
                retClubId = new String[]{opp[0], opp[2]};
            } else {
                retBackup = new String[]{opp[0], opp[2]}; //有比赛的队伍作为备选，避免未收编的球队都在比赛中是直接跳出
            }
        }
        if(retClubId == null) {
            if(retBackup == null) {
                LOGGER.info(email + "没有可以打的比赛，也没有备用队");
                return null;
            } else {
                LOGGER.info(email + "没有可以打的比赛，返回备用球队：" + retBackup == null ? "" : (retBackup[0] + "/" + retBackup[1]));
                retClubId = retBackup;
            }
        }
        LOGGER.info(email + "对手：" + retClubId[0] + "/" + retClubId[1]);
        return retClubId;
    }

    private int getPlayerAbility(String email, String playerId, String club) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadTeam1", playerId));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        String ability = ret.substring(0, ret.lastIndexOf("[" + club + "]"));
        ability = ability.substring(0, ability.lastIndexOf(".png"));
        ability = ability.substring(0, ability.lastIndexOf("#"));
        ability = ability.substring(ability.lastIndexOf("#") + 1);
        return Integer.parseInt(ability);
    }

    private void changeCoach(String email, String coachName) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("UpdateMyTrainMan1", coachName));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(email, pm);
        LOGGER.info(email + "教练更改为：" + coachName);
    }

    private void getCurrentCoach(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadCoach1", ""));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);
        if(StringUtils.isEmpty(ret)) {
            return;
        }
        ret = ret.substring(ret.indexOf("@") + 1);
        ret = ret.substring(0, ret.indexOf("@"));
        String[] coaches = ret.split("\\^");
        List<String[]> emailCoaches = new ArrayList<String[]>();
        for(String c : coaches) {
//            1|0      |2|1|米卢    |7 |9400 |-999 |0   |44 |30 |18 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |2|2|希丁克  |7 |9400 |-999 |1   |30 |44 |18 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |2|3|里皮    |7 |9400 |-999 |2   |18 |30 |44 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |1|4|瓜迪奥拉|5 |73618|70000|0   |92 |60 |34 |16|86|0   |0|0|0|0|0|0^
//            1|0      |0|5|弗格森  |2 |5987 |13000|1   |42 |68 |22 |10|10|0   |0|0|0|0|0|0^
//            1|1      |0|6|穆里尼奥|1 |3316 |5000 |2   |18 |36 |60 |8 |57|0   |0|0|0|0|0|0
//     是否聘用 current     name     lv exp   next  type att mid def       卫星
//            0 1       2 3 4        5  6     7     8    9   10  11  12 13 14
            emailCoaches.add(c.split("[|]"));
        }
        coachStatus.put(email, emailCoaches);
    }

    private void changeToNormal(String email) {
        try {
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(email, pm);
            String currentTactical = str.split("⊥")[1];
            if(currentTactical.equals("1")) {
                changeCoach(email, "瓜迪奥拉");
            } else if(currentTactical.equals("3")) {
                changeCoach(email, "弗格森");
            } else if(currentTactical.equals("5")) {
                changeCoach(email, "穆里尼奥");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
