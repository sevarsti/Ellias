package com.saille.ogzq;

import com.saille.util.UtilFunctions;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-4-2
 * Time: 21:58:39
 * To change this template use File | Settings | File Templates.
 * 检查对阵村夫是否需要变阵
 * todo:不考虑8点重置
 */
public class ChampionshipThread extends Thread {
    private final static Logger LOGGER = Logger.getLogger(ChampionshipThread.class);
    private boolean cont = true;
    private List<String> excludeArenaEmail = new ArrayList<String>(); //已经检查过的搞定村夫的email
    private List<String> hasArenaEmails = new ArrayList<String>(); //jjc有村夫的而且是我挂的email，未检查是否通过，但是肯定已经过了的去除
    private List<String> otherArenaEmails = new ArrayList<String>(); //jjc有村夫的别人的号，未检查是否通过，但是肯定已经过了的去除
    private List<String> myWeixingEmails = new ArrayList<String>(); //村夫的卫星
    private List<String> otherWeixingEmails = new ArrayList<String>();

    private List<String[]> otherIds = new ArrayList<String[]>(); //所有其他号的信息，List<String[]{nick, email, pwd}>
    private Map<String, String> myIds = new Hashtable<String, String>();

    public void run() {
        while(cont) {
            try {
                String cunfuTactic = null;
                /************检查竞技场对手，因为不需要访问网页，消耗时间可以忽略不计，所以不再判断状态直接检查*************/
                List<String> keys = ArenaThread.GETIDS();
                List<String> nicks = new ArrayList<String>();
                for(String s : keys) {
                    nicks.add(ArenaThread.NICK.get(s));
                }
                for(String k : keys) {
                    if(excludeArenaEmail.contains(k)) {
                        continue;
                    }
                    String[] against = ArenaThread.JJCAgainst.get(k);
                    if(against != null) {
                        String[][] atts = new String[against.length][];
                        for(int i = 0; i < against.length; i++) {
                            atts[i] = against[i].split("[|]");
                            if("幸村".equals(atts[i][2]) && "0".equals(atts[i][9])) {
                                if(IDUtils.IDInfos.containsKey(k)) {
                                    if(!hasArenaEmails.contains(k)) {
                                        hasArenaEmails.add(k);
                                        /* 将该ID设置为不需要打竞技场 */
                                        ConfigUtils.saveConf("是否踢竞技场." + k, "0");
                                    }
                                } else {
                                    if(!otherArenaEmails.contains(k)) {
                                        otherArenaEmails.add(k);
                                    }
                                }
                                break;
                            }
                        }
                    }
                }
                /************检查我的竞技场对手*************/
                if(hasArenaEmails.size() > 0) { //我的竞技场有村夫，就不需要其它工作了，不过之前需要保证自动打竞技被关掉了

                } else if(otherArenaEmails.size() > 0) { //别人的竞技场有村夫
                    for(int i = otherArenaEmails.size() - 1; i >= 0; i--) {
                        String email = otherArenaEmails.get(i);
                        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("load", "1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                        String ret = IDUtils.execute(email, pm);
                        if(ret.indexOf("inmatch") != -1) {
                            continue;
                        }
                        try {
                            int retValue = Integer.parseInt(ret);
                            if(retValue < 0) {
                                continue;
                            }
                        } catch(NumberFormatException ex) {}
                        ret = ret.substring(0, ret.indexOf("&"));
                        String[] teams = ret.split("[*]");
                        for(String t : teams) {
                            String[] atts = t.split("[|]");
                            if("幸村".equals(atts[2]) && "1".equals(atts[9])) {
                                excludeArenaEmail.add(email);
                                otherArenaEmails.remove(i);
                            }
                        }
                    }
                } else { //都没有村夫，需要检查卫星
                    if(Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date())) >=1830) {
                        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("LoadTeam1", "26048"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        String ret = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
                        String weixingstr = ret.split("#")[30];
                        String hasWeixing = weixingstr.split("@")[0];
                        if(hasWeixing.equals("3") || hasWeixing.equals("4") || hasWeixing.equals("5")) {
                            weixingstr = weixingstr.split("@")[1];
                            String[] weixings = weixingstr.split("[*]");
//                747|鞑靼骑士|npc58.png|26530|75|战术积分200*
//                12138|XD孔帕尼|npc54.png|18653|74|*
//                517|弑天|npc54.png|18429|74|
                            for(String weixing: weixings) {
                                String[] parts = weixing.split("[|]");
                                if(this.isMyId(parts[1])) {
                                    myWeixingEmails.add(this.myIds.get(parts[1]));
                                } else {
                                    otherWeixingEmails.add(parts[1]);
                                }
                            }
                        }
                    }
                }

                /*
                step:
                1、如果开赛时间在10分钟以外，等待
                2、检查村夫下一轮对手，如果实力差>8000，那就等待
                3、如果实力差<8000，那就依次从我的竞技场、别人的竞技场、卫星打村夫找阵容
                4、找到之后，登录下一轮对手号，等到最后10秒钟，变阵
                 */
                HttpPost pm = new HttpPost(OgzqURL.URL + "/ChampionCup.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "6"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
                s = s.substring(0, s.indexOf("^"));
                String[] teams = s.split("[*]");
                String opponent = null;
                String opponentId = null;
                String cunfuId = null;
                if(teams[16].equals("||||||||||")) { //16强
                    for(int i = 0; i < 16; i++) {
                        if(teams[i].split("[|]")[1].equals("幸村")) {
                            cunfuId = teams[i].split("[|]")[0];
                            if(i % 2 == 0) {
                                opponent = teams[i + 1].split("[|]")[1];
                                opponentId = teams[i + 1].split("[|]")[0];
                            } else {
                                opponent = teams[i - 1].split("[|]")[1];
                                opponentId = teams[i - 1].split("[|]")[0];
                            }
                            break;
                        }
                    }
                } else if(teams[24].equals("||||||||||")) { //8强
                    for(int i = 16; i < 24; i++) {
                        if(teams[i].split("[|]")[1].equals("幸村")) {
                            if(i % 2 == 0) {
                                opponent = teams[i + 1].split("[|]")[2];
                            } else {
                                opponent = teams[i - 1].split("[|]")[2];
                            }
                            break;
                        }
                    }
                } else if(teams[28].equals("||||||||||")) { //四强，半决赛
                    for(int i = 24; i < 28; i++) {
                        if(teams[i].split("[|]")[1].equals("幸村")) {
                            if(i % 2 == 0) {
                                opponent = teams[i + 1].split("[|]")[2];
                            } else {
                                opponent = teams[i - 1].split("[|]")[2];
                            }
                            break;
                        }
                    }
                }
                //检查对手实力
                System.out.println("opponent: " + opponent + ", id=" + opponentId);
                int cunfuShili = 0, opponentShili = 0;
                pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("LoadTeam1", cunfuId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ret = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
                cunfuShili = Integer.parseInt(ret.split("#")[24]);

                pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("LoadTeam1", opponentId));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                ret = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
                opponentShili = Integer.parseInt(ret.split("#")[24]);

                if(cunfuShili > (opponentShili + 10000)) {
//                    差距明显，啥都不用做了
                } else {
                    Date now = new Date();
                    SimpleDateFormat sdf2 = new SimpleDateFormat("mmss");
                    if(Integer.parseInt(sdf2.format(now)) % 3000 > 2945) { //离半点差15秒以内
                        if(hasArenaEmails.size() > 0) {
                            pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("load", "1"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

                            ret = IDUtils.execute(hasArenaEmails.get(0), pm);
                            if(ret.indexOf("inmatch") != -1) {
//                                inmatch|1|80864874|67545a
                                pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("ImaEndMatchID", ret.split("[|]")[2]));
                                params.add(new BasicNameValuePair("ImaMatchCategory", "5"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(hasArenaEmails.get(0), pm);
                                Thread.sleep(2000);

                                pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("load", "1"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(hasArenaEmails.get(0), pm);
                                System.out.println(ret);
                            }
                            ret = ret.substring(0, ret.indexOf("&"));
                            String[] tts = ret.split("[*]");
                            for(String t : tts) {
                                if(t.split("[|]")[2].equals("幸村")) {
                                    pm = new HttpPost(OgzqURL.URL + OgzqURL.ARENA);
                                    params = new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("insertMatch", t.split("[|]")[0]));
                                    params.add(new BasicNameValuePair("prop", "2"));
                                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    String matchId = IDUtils.execute(hasArenaEmails.get(0), pm);

                                    pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                                    params = new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("MatchID", matchId));
                                    params.add(new BasicNameValuePair("MatchCategory", "5"));
                                    params.add(new BasicNameValuePair("KFC", "0"));
                                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    String matchDetail = IDUtils.execute(myWeixingEmails.get(0), pm);
                                    cunfuTactic = getTactic(matchDetail);
                                }
                            }
                        } else if(otherArenaEmails.size() > 0) {

                        } else { //用卫星反抗
                            if(myWeixingEmails.size() > 0) {
                                pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("BeginGame1", cunfuId));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String matchId = IDUtils.execute(myWeixingEmails.get(0), pm);

                                pm = new HttpPost(OgzqURL.URL + "/MatchEngine.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("MatchID", matchId));
                                params.add(new BasicNameValuePair("MatchCategory", "14"));
                                params.add(new BasicNameValuePair("KFC", "0"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String matchDetail = IDUtils.execute(myWeixingEmails.get(0), pm);
                                cunfuTactic = getTactic(matchDetail);
                            }
                        }
                    }
                }
            } catch(Exception ex) {
                UtilFunctions.LogError(LOGGER, ex);
            }
        }
    }

    private String getTactic(String in) {
//        20140405/80871784~1758~21888|
//        卡卡VSC罗|
//        npc58.png|
//        36512|
//        Donadoni|
//        npc53.png|
//        2|
//        4|
//        69|
//        74|
//        0a2873|
//        ffffff|
//        17161|
//        17161|
//        18075|
//        15665|
//        0~5~0|
//        0|
//        0~0~1~1~0~74
        String[] parts = in.split("[|]");
        if(parts[1].equals("幸村")) {
            return parts[6];
        } else if(parts[4].equals("幸村")) {
            return parts[7];
        }
        return null;
    }

    private boolean isMyId(String nick) {
        if(this.myIds == null || this.myIds.size() == 0) {
            List<String> ids = IDUtils.GETIDS();
            for(String id : ids) {
                String n = IDUtils.getNick(id);
                myIds.put(n, id);
            }
        }
        return myIds.containsKey(nick);
    }

    private boolean checkOtherIds(String nick) {
        if(this.otherIds == null || this.otherIds.size() == 0) {
            File f = new File("D:\\excel\\1.xls");
            if(!f.exists()) {
                f = new File("C:\\Users\\ELLIAS\\Desktop\\1.xls");
            }
            try {
                FileInputStream fis = new FileInputStream(f);
                HSSFWorkbook workbook = new HSSFWorkbook(fis);
                HSSFSheet sheet = workbook.getSheet("密码");
                for(int i = 2; i <= sheet.getLastRowNum(); i++) {
                    HSSFRow row = sheet.getRow(i);
//                    String nick =
                }
            } catch(Exception ex) {
                UtilFunctions.LogError(LOGGER, ex);
            }
        }
//        todo
        return false;
    }
}
