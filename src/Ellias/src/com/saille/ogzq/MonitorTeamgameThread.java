package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;

import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-8-20
 * Time: 14:58:29
 * To change this template use File | Settings | File Templates.
 */
public class MonitorTeamgameThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(MonitorTeamgameThread.class);
    private static MonitorTeamgameThread instance = null;
    private boolean cont = true;
    public Map<String, Map<String, String>> list = new Hashtable<String, Map<String, String>>();
    public Map<String, Boolean> hasPaizi = new Hashtable<String, Boolean>();
    public Map<String, Boolean> hasLibao = new Hashtable<String, Boolean>();
    public Map<String, Boolean> inMatch = new Hashtable<String, Boolean>();
    public Map<String, Integer> restTime = new Hashtable<String, Integer>();
    public Map<String, String> place = new Hashtable<String, String>();
    public Map<Double, String[]> first = new Hashtable<Double, String[]>();
    public Map<String, String[]> places = new Hashtable<String, String[]>(); //从头到尾的详细位置
    public Map<String, String[]> myPlaces = new Hashtable<String, String[]>(); //小号的位置
    public Map<String, String[]> ourPlaces = new Hashtable<String, String[]>(); //囧/nb的位置
    public int lastSize = 0;

    private String dokill = "0";
    private String killPlayer;
    private String killer;
    private String killerPwd;
    private String killerNick;
    private HttpClient killerClient;
    private String killPos = null;
    private String killerPos = null;
    private String killerStatus; //追杀号状态，比赛，免战等

    public List<String[]> ourIds = new ArrayList<String[]>(); //需要手动调正位置的囧/nb号

    private MonitorTeamgameThread() {
        initOurIds();
        cont = true;
        String[] l = new String[]{"中央陆军","金阳高科","渔樵问答","雲起龍驤","神一样的凡人","特维兹","梦幻梅西","欧冠之夜","NOTH","黯之光芒","曼彻斯特罗那","SC厦门蓝狮","刀仔PP","慕尼黑拜仁","FIFA丨米兰","黯之风翼","TOT","全明星阵容","光远","八闽骏豪","宇宙VAC米兰","菜泥尼奥","菜菜123","GOAL爆射","幸村","中国龙"};
        try {
            PropertiesConfiguration conf = new PropertiesConfiguration("D:\\apache-tomcat\\webapps\\ROOT\\ogzq\\teamgame.ini");
//            conf.setAutoSave(true);
            try {
                dokill = conf.getString("dokill");
            } catch(Exception ex) {}
            killPlayer = conf.getString("target");
            killer = conf.getString("killer");
            killerPwd = conf.getString("killerPwd");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        for(String s : l) {
            list.put(s, new Hashtable<String, String>());
            hasPaizi.put(s, false);
            hasLibao.put(s, false);
            inMatch.put(s, false);
            restTime.put(s, 0);
            place.put(s, "");
        }
    }

    public static MonitorTeamgameThread getInstance() {
        if(instance == null) {
            instance = new MonitorTeamgameThread();
            instance.setName("Thread-MonitorTeamGame");
        }
        return instance;
    }

    public void run() {
        List<String> ids = IDUtils.GETIDS();
        while(ids.size() <= 0) {
            try {
                Thread.sleep(1000);
            } catch(Exception ex) {}
            ids = IDUtils.GETIDS();
        }
        int idx = 0;
        String id = ids.get(idx);
        idx++;
        HttpPost pm;
        int page = 0;
        int maxPage = 0;
        while(true) {
            try {
                Calendar c = Calendar.getInstance();
                if((!cont)) {
                    Thread.sleep(1000 * 60);
                    continue;
                }
                if(c.get(Calendar.HOUR_OF_DAY) < 9) {
//                if(c.get(Calendar.HOUR_OF_DAY) < 1) {
                    list.clear();
                    hasPaizi.clear();
                    hasLibao.clear();
                    inMatch.clear();
                    restTime.clear();
                    place.clear();
                    first.clear();
                    places.clear();
                    myPlaces.clear();
                    ourPlaces.clear();
                    String[] l = new String[]{"中央陆军","金阳高科","渔樵问答","雲起龍驤","神一样的凡人","特维兹","梦幻梅西","欧冠之夜","NOTH","黯之光芒","曼彻斯特罗那","SC厦门蓝狮","刀仔PP","慕尼黑拜仁","FIFA丨米兰","黯之风翼","TOT","全明星阵容","光远","八闽骏豪","宇宙VAC米兰","菜泥尼奥","菜菜123","GOAL爆射","幸村","中国龙"};
                    for(String s : l) {
                        list.put(s, new Hashtable<String, String>());
                        hasPaizi.put(s, false);
                        hasLibao.put(s, false);
                        inMatch.put(s, false);
                        restTime.put(s, 0);
                        place.put(s, "");
                    }
                    lastSize = 0;

                    this.killerClient = null;
                    this.killerPos = null;
                    this.killerStatus = null;
                    this.killPos = null;
                    Thread.sleep(1000 * 60);
                    continue;
                } else if(c.get(Calendar.HOUR_OF_DAY) >= 20) {
//                } else if(c.get(Calendar.HOUR_OF_DAY) >= 12) {
                    Thread.sleep(1000 * 60);
                    continue;
                }
                ids = IDUtils.GETIDS();
                if(idx >= ids.size()) {
                    idx = 0;
                }
                id = ids.get(idx);
                idx++;
                pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("pageindex", page + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = IDUtils.execute(id, pm);
                String pageCount = s.substring(s.indexOf("@") + 1);
                if(pageCount.equals("0")) {
                    Thread.sleep(1000);
                    continue;
                }
                pageCount = pageCount.substring(0, pageCount.indexOf("@"));
                page = Integer.parseInt(pageCount.substring(0, pageCount.indexOf("|")));
                maxPage = Integer.parseInt(pageCount.substring(pageCount.indexOf("|") + 1));
                if(page > maxPage) {
                    page = -1;
                }
                s = s.substring(0, s.indexOf("@"));
                String[] teams = s.split("[*]");
                for(int i = 0; i < teams.length; i++) {
                    String t = teams[i]; //6690|151|美丽小丫丫|3|3|1|452|1|299|囧|0|-35476|0
                    String[] aa = t.split("[|]");
                    if(this.killPlayer != null && this.killPlayer.equals(aa[2])) {
                        this.killPos = (page+1) + "-" + (i+1);
                    } else if(this.killerNick != null && this.killerNick.equals(aa[2])) {
                        this.killerPos = (page+1) + "-" + (i+1);
                        this.killerStatus = aa[7];
                    }
//ID    排名            胜 负            等待时间 平
//1655 |41|红歌会冠军  | 8|11| 1|-3089|0|-4229|维| 2|-42474|0*
//60025|42|申花是冠军  |22|35| 0|-3575|0|-3690|战| 0|-18770|0*
//8798 |43|QO皮耶罗    |27|10| 0|-3137|0|-4277|武| 5|-18010|1*
//874  |44|什么乱七八糟|11| 4| 0|-2855|0|-3995|武| 0|-37359|0*
//54317|45|我是传奇    |33| 8| 0|-2867|0|-4007|中| 2|-3767 |1*
//10138|46|缥缈过客    |14|34| 0|-4492|0|-5632|武| 3|-14724|1*
//35408|47|醉爱曼联    |19| 7| 0|-8217|0|-6946|中| 1|-34942|0*
//21888|48|卡卡VSC罗   |16|15| 0|-3963|0|-4626|nb| 0|-37146|0*
//35403|49|月影如霜    |29| 1|12|-2529|0|-3669| 1| 1|-14336|0*
//2265 |50|YI兰帕德    |40| 3|11|-2774|0|-3914|囧| 0|-32028|0
//@4|    31    @46  @10138@0@@0|1|0@1@0
// 第4页 总页数 排名 ID
                    this.updatePlace((page+1) + "-" + (i+1), aa);
                    if(i == 0) {
                        first.put((double)(page + 1), new String[]{aa[2], aa[9], aa[5], aa[6], convertGameStatus(aa[7]), aa[8], aa[0]});
                    } else if(page == 0 && i == 1) {
                        first.put(1.2, new String[]{aa[2], aa[9], aa[5], aa[6], convertGameStatus(aa[7]), aa[8], aa[0]});
                    } else if(page == 0 && i == 2) {
                        first.put(1.3, new String[]{aa[2], aa[9], aa[5], aa[6], convertGameStatus(aa[7]), aa[8], aa[0]});
                    }
                    if(list.containsKey(aa[2])) {
                        String nowPlace = (page + 1) + "-" + (i + 1);
                        if(!nowPlace.equals(place.get(aa[2])) && !"".equals(place.get(aa[2]))) {
                            sendMsg(aa[2] + "的位置发生变化：" + place.get(aa[2]) + "-->" + nowPlace);
                        }
                        place.put(aa[2], nowPlace);
                        int libao = Integer.parseInt(aa[5]);
                        if(libao > 0 && libao < 10) {
                            if(!hasPaizi.get(aa[2])) {
                                sendMsg(aa[2] + "在" + (page + 1) + "页有牌子");
                            }
                            hasPaizi.put(aa[2], true);
                        } else if(libao >= 10) {
                            if(!hasLibao.get(aa[2])) {
                                sendMsg(aa[2] + "在" + (page + 1) + "页有礼包");
                            }
                            hasLibao.put(aa[2], true);
                        } else {
                            hasPaizi.put(aa[2], false);
                            hasLibao.put(aa[2], false);
                        }
                        String match = aa[7];
                        if("1".equals(match)) {
                            if(!inMatch.get(aa[2])) {
                                sendMsg(aa[2] + "在" + (page + 1) + "页打比赛，剩余时间：" + aa[8] + "秒");
                            }
                            inMatch.put(aa[2], true);
                        } else {
                            if("2".equals(match)) { //免战期
                                if(inMatch.get(aa[2])) {
                                    sendMsg(aa[2] + "在" + (page + 1) + "页比赛结束，正在休战：剩余" + aa[8] + "秒");
                                }
                                restTime.put(aa[2], Integer.parseInt(aa[8]));
                            } else if("0".equals(match)) {
                                if(restTime.get(aa[2]) > 0) {
                                    sendMsg(aa[2] + "在" + (page + 1) + "页休战期结束可以比赛");
                                }
                                if(inMatch.get(aa[2])) {
                                    sendMsg(aa[2] + "在" + (page + 1) + "页比赛结束可以比赛");
                                }
                                restTime.put(aa[2], 0);
                            }
                            inMatch.put(aa[2], false);
                        }
                    }
                }
//                                          11小              0否1是2免战3残
//                id   名次 名字      胜 负 礼包 拿牌剩余时间 是否在比赛 免战时间 球会 平 打残剩余时间 是否残
//                1034  11  ZM渝菲联  69  0  2   882          2          55       囧   0 -21612        0
//                17662 12  BF许磊    13 37  0   -986 0 -2126 nb 0 -4263 0
//                1407  13  皇家豪情  10 17  0   -8529 0 -2265 黑 0 -21588 0
//                16993 14  越隆实业  17  0 11   670 0 -470 黑 0 -19813 0
//                8518  15  受到等不及 4 36  0   -1571 0 -258 wd 0 -2471 1
//                37278 16  气煞II     4  5  0   -11719 0 -8837 中 0 -21354 0
//                21888 17  卡卡VSC罗  4 11  0   -12343 0 -13483 nb 0 -21655 0
//                75674 18  H范德法特  7  3  0   -15618 0 -16758 参 0 -21400 0
//                32782 19  圣卡卡     3 26  0   670 0 55 亡 0 -21503 0
//                20724 20  FCB涛爷    2  6  0   -18730 0 -8841 中 0 -21350 0

//                var oneinfo = oppss[i].split('|');
//                var rank = oneinfo[1];
//                var clubid = parseInt(oneinfo[0]);
//                var clubname = oneinfo[2];
//                var win = oneinfo[3];
//                var lose = oneinfo[4];
//                var take = oneinfo[5];
//                var takeotime = oneinfo[6];
//                if (takeotime <= 0)
//                    takeotime = 1200;
//                var state = oneinfo[7];
//                var freeendtime = oneinfo[8];
//                var teamname = oneinfo[9];
//                var same = oneinfo[10];
//                var weekTime = oneinfo[11];
//                var weekTimes = oneinfo[12];
                if(page >= maxPage) {
//                    System.out.println("到达最大页" + page + "/" + maxPage + "，重新循环");
                    lastSize = teams.length;
                    page = 0;

                    if("1".equals(dokill)) {
                        if(!checkLogin()) {
                            this.killerClient = LoginUtils.Login(killer, killerPwd);
                            this.setNick();
                            continue;
                        }
                        this.checkKill();
                    }
                } else {
                    page++;
                }
            } catch(Exception ex) {
                ex.printStackTrace();
                try {
                    Thread.sleep(1000);
                } catch(Exception exx) {}
            }
        }
    }

    private void updatePlace(String place, String[] aa) {
        this.places.put(place, aa);
        String nick = aa[2];
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            if(IDUtils.IDInfos.containsKey(id) && IDUtils.IDInfos.get(id).containsKey("nick")) {
                if(nick.equals(IDUtils.getNick(id))) {
                    this.myPlaces.put(id, new String[]{aa[2], aa[9], aa[5], aa[6], convertGameStatus(aa[7]), aa[8], place});
                }
            }
        }
        for(String[] ourId : ourIds) {
            if(nick.equals(ourId[2])) {
                this.ourPlaces.put(ourId[0], new String[]{nick, aa[9], aa[5], aa[6], convertGameStatus(aa[7]), aa[8], place});
            }
        }
    }

    private void sendMsg(String msg) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()) + msg);
//        SendQQMsg.sendMsg(sdf.format(new Date()) + msg);
    }

    public static String convertGameStatus(String in) {
        if(in == null) {
            return "??";
        } else if(in.equals("1")) {
            return "比赛";
        } else if(in.equals("2")) {
            return "休战";
        } else if(in.equals("0")) {
            return "";
        } else if(in.equals("3")) {
            return "打残";
        } else {
            return "未知";
        }
    }

    private boolean checkLogin() {
        try {
            if(this.killerClient == null) {
                return false;
            }
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse response = this.killerClient.execute(pm);
            String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            if(ret.indexOf("任务页") >= 0) {
                return false;
            } else {
                return true;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private void setNick() throws Exception {
        HttpPost pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(this.killerClient, this.killer, pm);
        String[] ids = ret.split("@");
        int shili = 0;
        int idx = -1;
        for(int i = 0; i < ids.length; i++) {
            if(Integer.parseInt(ids[i].split("[*]")[2]) > shili) {
                shili = Integer.parseInt(ids[i].split("[*]")[2]);
                idx = i;
            }
        }
        String nick = ids[idx].split("[*]")[1];
        this.killerNick = nick;
    }

    private void checkKill() {
        if(StringUtils.isEmpty(this.killer) || StringUtils.isEmpty(this.killerNick)) { //没有设置追杀目标或者没有设置追杀号
            return;
        }
        if(StringUtils.isEmpty(this.killPos)) { //追杀目标没有进球会战
            return;
        }
        try {
            if(StringUtils.isEmpty(this.killerPos)) { //追杀号没有进球会战
                this.loginTeamgame();
                return;
            }
            if("1".equals(this.killerStatus) || "3".equals(this.killerStatus)) {
                return;
            }
            int killPage = Integer.parseInt(this.killPos.split("-")[0]);
            int killIndex = Integer.parseInt(this.killPos.split("-")[1]);
            int killerPage = Integer.parseInt(this.killerPos.split("-")[0]);
            int killerIndex = Integer.parseInt(this.killerPos.split("-")[1]);
            if(killerPage == 1) {
//                todo
                return;
            }
            if(killPage == killerPage) { //在同一页
                if(killerIndex != 1) { //追杀号不在牌子位
                    String[] pageFirst = first.get((double)killPage);
                    if("比赛".equals(pageFirst[4]) || "休战".equals(pageFirst[4])) { //本页牌子位在比赛或者免战，跳过
                        return;
                    } else { //本页牌子位不在比赛或者免战，打下来
                        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("insertMatch", pageFirst[6]));
                        params.add(new BasicNameValuePair("MatchPrice", "0"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info("追杀-本页牌子位不在比赛或者免战，打下来");
                        IDUtils.execute(killerClient, killer, pm);
                    }
                } else { //追杀号在牌子位，不需要做事情
                    return;
                }
            } else if(killerPage < killPage) { //追杀号在追杀目标前面，那么什么都做不了
                return;
            } else { //追杀号在追杀目标后面n页
                if(killerPage == killPage + 1) { //就在后面那页
                    String[] status = this.places.get(this.killPos);
                    if("1".equals(status[7])) { //如果追杀目标在比赛，等比完再说
                        return;
                    }
                    if(killerIndex != 1) { //追杀号没有牌子，就先把本页的囧/nb的号打下来打到牌子，如果没有则随机选一个
                        String[] pageFirst = first.get((double)killerPage);
                        //todo: 本页牌子是囧nb的情况
                        if("比赛".equals(pageFirst[4]) || "休战".equals(pageFirst[4])) { //本页牌子位在比赛或者免战，跳过
                            return;
                        } else { //本页牌子位不在比赛或者免战，打下来
                            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("insertMatch", pageFirst[6]));
                            params.add(new BasicNameValuePair("MatchPrice", "0"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            IDUtils.execute(killerClient, killer, pm);
                            LOGGER.info("追杀-就在后面那页，本页牌子位不在比赛或者免战，打下来");
                        }
                    } else { //追杀号在牌子位
                        String jiongId = null, nbId = null, backupId = null;
                        boolean jiongMatch = false, nbMatch = false;
                        String jiongNick = null, nbNick = null, backupNick = null;
                        for(int i = 1; i <= 10; i++) {
                            if(jiongId != null) {
                                break;
                            }
                            String[] previous = this.places.get((killerPage - 1) + "-" + i);
                            if("囧".equals(previous[9]) && !jiongMatch) {
                                jiongId = previous[0];
                                jiongMatch = previous[7].equals("1") || previous[7].equals("2");
                                jiongNick = previous[2];
                                break;
                            } else if("nb".equals(previous[9])) {
                                nbId = previous[0];
                                nbMatch = previous[7].equals("1") || previous[7].equals("2");
                                nbNick = previous[2];
                            } else {
                                if(!previous[2].equals(this.killPlayer)) { //必须找非追杀目标打
                                    if(previous[7].equals("1") || previous[7].equals("2")) { //比赛中或免战，跳过该球队

                                    } else {
                                        backupId = previous[0];
                                        backupNick = previous[2];
                                    }
                                }
                            }
                        }
                        String matchId = null, matchNick = null;
                        if(jiongId != null) {
                            if(!jiongMatch) {
                                matchId = jiongId;
                                matchNick = "囧-" + jiongNick;
                            }
                        } else if(nbId != null) {
                            if(!nbMatch) {
                                matchId = nbId;
                                matchNick = "nb-" + nbNick;
                            }
                        } else if(backupId != null) {
                            matchId = backupId;
                            matchNick = "--" + backupNick;
                        }
                        if(matchId != null) {
                            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("insertMatch", matchId));
                            params.add(new BasicNameValuePair("MatchPrice", "0"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            LOGGER.info("追杀-就在后面那页牌子位，打目标：" + matchNick);
                            IDUtils.execute(killerClient, killer, pm);
                        }
                    }
                } else { //在后面不知道多少页
                    if(killerIndex != 1) {
                        String[] pageFirst = first.get((double)killerPage);
                        if("比赛".equals(pageFirst[4]) || "休战".equals(pageFirst[4])) { //本页牌子位在比赛或者免战，跳过
                            return;
                        } else { //本页牌子位不在比赛或者免战，打下来
                            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("insertMatch", pageFirst[6]));
                            params.add(new BasicNameValuePair("MatchPrice", "0"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            IDUtils.execute(killerClient, killer, pm);
                            LOGGER.info("在后面很多页，先打牌子位");
                        }
                    } else {
                        String[] pageFirst = first.get((double)killerPage - 1);
                        if("比赛".equals(pageFirst[4]) || "休战".equals(pageFirst[4])) { //上页牌子位在比赛或者免战，跳过
                            return;
                        } else { //上一页页牌子位不在比赛或者免战，打下来
                            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
                            List<NameValuePair> params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("insertMatch", pageFirst[6]));
                            params.add(new BasicNameValuePair("MatchPrice", "0"));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            IDUtils.execute(killerClient, killer, pm);
                            LOGGER.info("在后面很多页的牌子位，打前一页牌子位");
                        }
                    }
                }
            }
        } catch(Exception ex) {
        }
    }

    private void loginTeamgame() throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TEAMGAME);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("checkSignup", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        IDUtils.execute(this.killerClient, this.killer, pm);
    }

    private void initOurIds() {
        ourIds.clear();
        try {
            File f = new File(ConfigUtils.class.getResource("../../../../../ogzq/monitor.ini").getFile());
            if(f.exists()) {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String tmp;
                while((tmp = br.readLine()) != null) {
                    ourIds.add(tmp.split("\t"));
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
//        ourIds.addAll(IDUtils.JIONGIDS);
//        ourIds.addAll(IDUtils.NBIDS);
//        ourIds.addAll(IDUtils.GIDS);
//        ourIds.addAll(IDUtils.WEIIDS);
//        ourIds.addAll(IDUtils.XYIDS);
//        ourIds.addAll(IDUtils.OTHERIDS);
//        ourIds.add(new String[]{"song11@163.com*荷兰建业FC","leewei917129","荷兰建业FC"});
    }

    public List<String[]> getOurIds() {
        return this.ourIds;
    }
}
