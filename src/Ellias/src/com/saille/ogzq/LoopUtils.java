package com.saille.ogzq;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.*;

import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-2-14
 * Time: 14:44:43
 * To change this template use File | Settings | File Templates.
 */
public class LoopUtils extends Thread{
    private final static Logger LOGGER = Logger.getLogger(LoopUtils.class);
    public final static int DOCHALLENGE = 1;
    public final static int DOARENA = 2;
    public final static int DOGETTACTICPOINT = 3;
    public final static int DOOGTRAININGMATCH = 4;
    public final static int DOTRAININGMATCH = 5;
    public final static int DOSEARCHPLAYER = 6;
    public final static int DOTEAMGAME = 7;
    public final static int DOTRAIN = 8;
    public final static int DODEFAULT = 9;
    public final static int DOQUERYTASK = 10;
    public final static int DOWORLDCUP = 11;
    public final static int DOFUBEN = 12;
    public final static int DOSELLJIQING = 13;
    public final static int DORIJKAARDCHALLENGE = 14;
    public final static int DOWORLDCUP32 = 15;
    public final static int DOGJXLS = 16;
    public final static int DOPELE = 17;

//    private Vector<Object[]> events = new Vector<Object[]>(); //obj: email, event, nextTime, loopDelay
    static Map<String, Map<String, Object[]>> events = new Hashtable<String, Map<String, Object[]>>(); //Map<email, Map<event, Object[]{nexttime, loopdelay}>>
    final static Map<String, Map<String, Object[]>> eventsBackup = new Hashtable<String, Map<String, Object[]>>(); //Map<email, Map<event, Object[]{nexttime, loopdelay}>>

    private static LoopUtils instance;

    private LoopUtils() {}

    public synchronized static LoopUtils getInstance() {
        if(instance == null) {
            instance = new LoopUtils();
            instance.setName("Thread-LoopUtils");
//            instance.start();
        }
        return instance;
    }
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        SimpleDateFormat dateSdf = new SimpleDateFormat("yyyyMMdd");
        while(true) {
//            LOGGER.info("loop: " + new Date().getTime());
            Calendar c = Calendar.getInstance();
            String nowDate = dateSdf.format(c.getTime());
            if(Integer.parseInt(sdf.format(c.getTime())) > 1730 && Integer.parseInt(sdf.format(c.getTime())) <= 2000) {
//                String[] shoubianEmails = new String[]{"sevarsti@sina.com", "meijianbai@hotmail.com", "rentao@vip.sina.com"};
                String[] shoubianEmails = new String[]{"sevarsti@sina.com"};
//                String[] shoubianEmails = new String[]{};
                for(String email : shoubianEmails) {
                    if(!IDUtils.ShoubianThreads.containsKey(email) || !nowDate.equals(IDUtils.ShoubianTime.get(email))) {
                        ShoubianThread t = new ShoubianThread(email);
                        IDUtils.ShoubianThreads.put(email, t);
                        IDUtils.ShoubianTime.put(email, nowDate);
                        t.start();
                    }
                }
                if(!nowDate.equals(IDUtils.XiaohaoShoubianTime)) {
                    XiaohaoShoubianThread t = new XiaohaoShoubianThread();
                    t.start();
                    IDUtils.XiaohaoShoubianTime = nowDate;
                }
            } else if(Integer.parseInt(sdf.format(c.getTime())) < 25) {
                try {
                    LOGGER.info("00:30之前不做事情。。。");
                    List<String> ids = IDUtils.GETIDS();
                    if(c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                        for(String email : ids) {
                            if(email.startsWith("blue") || email.startsWith("orange") || email.startsWith("bixi") || email.startsWith("robot")) {
                                OperationUtils.doSellLv5(email);
                            }

                            if(email.startsWith("robot")) {
                                OperationUtils.doSellDaily(email);
                            }
                        }
                    }
                    Thread.sleep(1000);
                } catch(Exception ex) {}
                continue;
            }
//            if(Integer.parseInt(sdf.format(c.getTime())) >= 859 && Integer.parseInt(sdf.format(c.getTime())) <= 901) {
//                if(IDUtils.firstLoginTeamgameThread == null || !nowDate.equals(IDUtils.firstLoginTeamgameThreadTime)) {
//                    FirstLoginTeamgameThread t = new FirstLoginTeamgameThread(new String[]{"270373371@qq.com", "355537@qq.com", "xieqigan@qq.com", "101977723@qq.com", "156451865@qq.com", "1422485013@qq.com", "421623479@qq.com", "fanmingsuo3@163.com", "shmilyfc@vip.qq.com", "707000142@qq.com", "Lijian20060615@163.com", "1412913604@qq.com", "348794240@qq.com", "020637@163.com", "279512194@qq.com", "mahao4934@163.com", "suyupeng4411@126.com", "3897021733@qq.com", "liaoqing262@163.com", "stevending1@163.com", "xurui@cnstock.com", "110932097@qq.com", "630442575@qq.com", "zhlzxd@163.com"});
//                    t.start();
//                    IDUtils.firstLoginTeamgameThread = t;
//                    IDUtils.firstLoginTeamgameThreadTime = nowDate;
//                }
//            }

            if(Integer.parseInt(sdf.format(c.getTime())) >= 1005) {
                if(IDUtils.otherWorldcup32Thread == null || !nowDate.equals(IDUtils.otherWorldcup32ThreadTime)) {
//                    OtherWorldcup32Thread t = new OtherWorldcup32Thread();
//                    t.start();
//                    IDUtils.otherWorldcup32Thread = t;
//                    IDUtils.otherWorldcup32ThreadTime = nowDate;
                }
            }

            if(Integer.parseInt(sdf.format(c.getTime())) >= 1350 && Integer.parseInt(sdf.format(c.getTime())) < 1601) {
//                String[] oglmzdzEmails = new String[]{"yuliang0526@163.com*不及阁大学士", "leonis11@e7wan", "690496636@qq.com*Carlos特维斯"};
//                String[] oglmzdzPwds = new String[]{"yyx121101", "123789", "852456.."};
//                int[] min = new int[]{0, 60000, 0}, max = new int[]{60000, 150000, 95000};
//                for(int i = 0; i < oglmzdzEmails.length; i++) {
//                    if(!IDUtils.OTHEROGLMZDZThreads.containsKey(oglmzdzEmails[i]) || !nowDate.equals(IDUtils.OTHEROGLMZDZTime.get(oglmzdzEmails[i]))) {
//                        OtherLMZDThread t = new OtherLMZDThread(oglmzdzEmails[i], oglmzdzPwds[i], min[i], max[i]);
//                        IDUtils.OTHEROGLMZDZThreads.put(oglmzdzEmails[i], t);
//                        IDUtils.OTHEROGLMZDZTime.put(oglmzdzEmails[i], nowDate);
//                        t.start();
//                    }
//                }
            }

            if(Integer.parseInt(sdf.format(c.getTime())) >= 1950 && Integer.parseInt(sdf.format(c.getTime())) < 2201) {
                String[] oglmzdzEmails = new String[]{"sevarsti@sina.com", "meijianbai@hotmail.com"};
                int[] min = new int[]{0, 100001}, max = new int[]{100000, 150000};
                for(int i = 0; i < oglmzdzEmails.length; i++) {
                    if(!IDUtils.OGLMZDZThreads.containsKey(oglmzdzEmails[i]) || !nowDate.equals(IDUtils.OGLMZDZTime.get(oglmzdzEmails[i]))) {
                        OGLMZDZThread t = new OGLMZDZThread(oglmzdzEmails[i], min[i], max[i]);
                        IDUtils.OGLMZDZThreads.put(oglmzdzEmails[i], t);
                        IDUtils.OGLMZDZTime.put(oglmzdzEmails[i], nowDate);
                        t.start();
                    }
                }
            }
            try {
                long now = System.currentTimeMillis();
////                Map<String, Map<String, Object[]>> events = new Hashtable<String, Map<String, Object[]>>(); //Map<email, Map<event, Object[]{nexttime, loopdelay}>>
                List<String> keys = new ArrayList<String>();
                for(String k : events.keySet()) {
                    keys.add(k);
                }
                for(String email : keys) {
                    Map<String, Object[]> evs = LoopUtils.events.get(email);
                    LoopUtils.events.remove(email);
                    EventThread eventThread = new EventThread(this, email, evs);
                    eventThread.start();
//                    Map<String, Object[]> events = LoopUtils.events.get(email);
//                    for(String eventId : events.keySet()) {
//                        Object[] ev = events.get(eventId);
//                        if(((Long)ev[0]).longValue() <= now) {
//                            long wait = 0;
//                            try {
//                                wait = doEvent(email, Integer.valueOf(eventId), ev);
//                            } catch(Exception ex) {
//                                System.out.println("email: " + getEventDesc(Integer.valueOf(eventId)));
//                                ex.printStackTrace();
//                            }
//                            events.put(eventId, new Object[]{now + (wait != 0d ? wait : ((Long)ev[1]).longValue()), ((Long)ev[1]).longValue()});
//                            addEvent(email, Integer.parseInt(eventId), now + (wait != 0d ? wait : ((Long)ev[1]).longValue()), ((Long)ev[1]).longValue());
////                            addEvent((String) ev[0], ((Integer)ev[1]).intValue(), now + (wait != 0d ? wait : ((Long)ev[3]).longValue()), ((Long)ev[3]).longValue());
//                        }
//                    }
                }
                Set<String> keySet = eventsBackup.keySet();
                String s = "";
                while(keySet.size() != IDUtils.IDInfos.size()) {
                    keySet = eventsBackup.keySet();
                    String s2 = compare(keySet, IDUtils.IDInfos.keySet());
                    if(!s.equals(s2)) {
                        System.out.println("等待：" + s2);
                        s = s2;
                    }
                    Thread.sleep(1000);
                }
//                System.out.println("等待循环结束");
                for(String k : eventsBackup.keySet()) {
                    events.put(k, eventsBackup.get(k));
                }
                System.out.println("循环结束。等待10秒");
                eventsBackup.clear();
                Thread.sleep(10 * 1000);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private String compare(Set<String> current, Set<String> all) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(String s : all) {
            if(current.contains(s)) {
                continue;
            }
            count++;
            sb.append(",").append(s);
        }
        return count + "/" + sb.substring(1);
    }

    public long doEvent(String email, int ev, Object[] event) {
//        String email = (String) event[0];
//        int ev = ((Integer)event[1]).intValue();
        try {
            switch(ev) {
                case DOCHALLENGE:
                    String doChallenge = ConfigUtils.getConf(email, "是否踢巡回赛");
                    if(doChallenge.equals("1")) {
                        OperationUtils.doChallenge(email);
                    }
                    break;
                case DOARENA:
                    String doArena = ConfigUtils.getConf(email, "是否踢竞技场");
                    if(doArena.equals("1")) {
                        if(Integer.parseInt(new SimpleDateFormat("HHmm").format(new Date())) > 105) {
                            OperationUtils.doArena(email);
                        }
                    }
                    break;
                case DOGETTACTICPOINT:
                    long wait = OperationUtils.getTacticPoint(email);
                    return wait;
                case DOOGTRAININGMATCH:
                {
                    String ret = OperationUtils.doOgTrainingMatch(email);
                    if(StringUtils.isNotEmpty(ret)) {
                        return 5 * 60 * 1000;
                    }
                    break;
                }
                case DOTRAININGMATCH:
                    if(ConfigUtils.getConf(email, "是否踢训练赛").equals("1")) {
                        String ret = OperationUtils.doTrainingMatch(email);
                        if(StringUtils.isNotEmpty(ret)) {
                            return 1 * 10 * 1000;
                        } else {
                            return 10 * 1000;
                        }
                    }
                    break;
                case DOSEARCHPLAYER:
                    if(ConfigUtils.getConf(email, "是否搜索球员").equals("1")) {
                        OperationUtils.afterFindPlayer(email);
                        OperationUtils.findPlayer(email);
                    }
                    break;
                case DOTEAMGAME:
//                    Calendar c = Calendar.getInstance();
//                    int hour = c.get(Calendar.HOUR_OF_DAY);
//                    if((ConfigUtils.getConf(email, "是否踢球会大作战").equals("1")) && (hour < 20) && (hour > 9)) {
                        OperationUtils.teamGame(email, 1);
//                    }
                    break;
                case DOTRAIN:
                    if(ConfigUtils.getConf(email, "是否训练").equals("1")) {
                        OperationUtils.doTraining(email);
                    }
                    break;
                case DODEFAULT:
                    OperationUtils.defaults(email, 7);
                    break;
                case DOQUERYTASK:
                    OperationUtils.queryTask(email);
                    break;
                case DOWORLDCUP:
                    OperationUtils.doWorldCup(email);
                    break;
                case DOFUBEN:
                    OperationUtils.doFuben(email);
                    break;
                case DOSELLJIQING:
                    OperationUtils.autoSellJiqing(email);
                    break;
                case DORIJKAARDCHALLENGE:
                    String ret = OperationUtils.playerChallenge(email, "Bremer");
                    if(ret.equals("0")) {
                        return 1l;
                    } else {
                        return 0l;
                    }
                case DOWORLDCUP32:
                    int resttime = OperationUtils.doWorldcup32(email);
                    return resttime;
                case DOGJXLS:
                    return OperationUtils.gjxls(email);
                case DOPELE:
                    String peleType = OperationUtils.doPele(email);
                    if("-1".equals(peleType)) { //未知状态，延迟1分钟
                        return 1000 * 60;
                    } else if("0".equals(peleType)) { //全都打完了，延迟1小时
                        return 1000 * 60 * 60;
                    } else if("1".equals(peleType)) { //比赛中
                        return 1000 * 60;
                    } else if("2".equals(peleType)) { //正常状态，进入比赛
                        return 1000 * 60 * 5;
                    }
            }
        } catch(Exception ex) {
            LOGGER.error(email + ":" + ex.getMessage());
            ex.printStackTrace();
        }
        return 0l;
    }

    public void moveToTop(String email, int event) {
        Map<String, Object[]> events = LoopUtils.events.get(email);
        if(events == null) {
            return;
        }
        if(events.containsKey(String.valueOf(event))) {
            events.get(String.valueOf(event))[0] = new Date().getTime();
        }
//        for(int i = this.events.size() - 1; i >= 0; i--) {//obj: email, event, nextTime, loopDelay
//            if(events.get(i)[0].equals(email) && Integer.parseInt(String.valueOf(events.get(i)[1])) == event) {
//                this.events.get(i)[2] = new Date().getTime();
//                break;
//            }
//        }
//        quickSortEvent(this.events, 0, this.events.size());
    }

    public synchronized void addEvent(String email, int event, long nextTime, long loopDelay) {
        /* 检查是否有重复事件 */
        synchronized(LoopUtils.eventsBackup) {
            if(!LoopUtils.eventsBackup.containsKey(email)) {
                LoopUtils.eventsBackup.put(email, new Hashtable<String, Object[]>());
            }
            LoopUtils.eventsBackup.get(email).put(String.valueOf(event), new Object[]{nextTime, loopDelay});
        }
    }

    public Map<String, Map<String, Object[]>> getEvents() {
        return events;
    }

    public void setEvents(Map<String, Map<String, Object[]>> events) {
        LoopUtils.events = events;
    }

    public static String getEventDesc(int eventId) {
        switch(eventId) {
            case DOCHALLENGE:
                return "巡回赛";
            case DOARENA:
                return "竞技场";
            case DOGETTACTICPOINT:
                return "战术积分";
            case DOOGTRAININGMATCH:
                return "欧冠训练赛";
            case DOTRAININGMATCH:
                return "训练赛";
            case DOSEARCHPLAYER:
                return "搜寻球员";
            case DOTEAMGAME:
                return "球会大作战";
            case DOTRAIN:
                return "训练";
            case DODEFAULT:
                return "查询信息";
            case DOQUERYTASK:
                return "查询任务";
            case DOWORLDCUP:
                return "世界杯";
            case DOFUBEN:
                return "副本";
            case DOSELLJIQING:
                return "卖激情值";
            case DOWORLDCUP32:
                return "世界杯国旗比赛";
            case DOGJXLS:
                return "冠军训练赛";
            case DOPELE:
                return "贝利比赛";
        }
        return "未知: " + eventId;
    }

    public static Vector<Object[]> quickSortEvent(Vector<Object[]> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Long.parseLong(String.valueOf(list.get(i)[2])) < Long.parseLong(String.valueOf(list.get(pos)[2]))) {
//                needSwap = false;
//            } else if(Integer.parseInt(list.get(i)[2]) < Integer.parseInt(list.get(pos)[2])) {
                needSwap = true;
            }
            if(needSwap) {
                Object[] tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        quickSortEvent(list, start, pos);
        quickSortEvent(list, pos + 1, end);
        return list;
    }
}
//package com.saille.ogzq;
//
//import com.saille.ogzq.loop.MainThread;
//
//import java.util.*;
//
//import org.apache.log4j.Logger;
//import org.apache.commons.lang.StringUtils;
//
///**
// * Created by IntelliJ IDEA.
// * User: Ellias
// * Date: 2013-2-14
// * Time: 14:44:43
// * To change this template use File | Settings | File Templates.
// */
//public class LoopUtils extends Thread{
//    private final static Logger LOGGER = Logger.getLogger(LoopUtils.class);
//    public final static int DOCHALLENGE = 1;
//    public final static int DOARENA = 2;
//    public final static int DOGETTACTICPOINT = 3;
//    public final static int DOOGTRAININGMATCH = 4;
//    public final static int DOTRAININGMATCH = 5;
//    public final static int DOSEARCHPLAYER = 6;
//    public final static int DOTEAMGAME = 7;
//    public final static int DOTRAIN = 8;
//    public final static int DODEFAULT = 9;
//    public final static int DOQUERYTASK = 10;
//    public final static int DOWORLDCUP = 11;
//    public final static int DOFUBEN = 12;
//    public final static int DOSELLJIQING = 13;
//
////    private Vector<Object[]> events = new Vector<Object[]>(); //obj: email, event, nextTime, loopDelay
//    public Map<String, Map<String, Object[]>> events = new Hashtable<String, Map<String, Object[]>>(); //Map<email, Map<event, Object[]{nexttime, loopdelay}>>
//
//    private static LoopUtils instance;
//
//    private LoopUtils() {}
//
//    public synchronized static LoopUtils getInstance() {
//        if(instance == null) {
//            instance = new LoopUtils();
//            instance.start();
//        }
//        return instance;
//    }
//    public void run() {
//        while(true) {
////            LOGGER.info("loop: " + new Date().getTime());
//            try {
//                long now = System.currentTimeMillis();
//                Set<String> keys = this.events.keySet();
//                for(String email : keys) {
//                    EventThread eventThread = new EventThread(this, email, this.events.get(email));
//                    eventThread.start();
//                }
//                Thread.sleep(10 * 1000);
//            } catch(Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//    public static long doEvent(String email, int ev, Object[] event) {
////        String email = (String) event[0];
////        int ev = ((Integer)event[1]).intValue();
//        try {
//            switch(ev) {
//                case DOCHALLENGE:
//                    String doChallenge = ConfigUtils.getConf(email, "是否踢巡回赛");
//                    if(doChallenge.equals("1")) {
//                        OperationUtils.doChallenge(email);
//                    }
//                    break;
//                case DOARENA:
//                    String doArena = ConfigUtils.getConf(email, "是否踢竞技场");
//                    if(doArena.equals("1")) {
//                        OperationUtils.doArena(email);
//                    }
//                    break;
//                case DOGETTACTICPOINT:
//                    long wait = OperationUtils.getTacticPoint(email);
//                    return wait;
//                case DOOGTRAININGMATCH:
//                {
//                    String ret = OperationUtils.doOgTrainingMatch(email);
//                    if(StringUtils.isNotEmpty(ret)) {
//                        return 5 * 60 * 1000;
//                    }
//                    break;
//                }
//                case DOTRAININGMATCH:
//                    if(ConfigUtils.getConf(email, "是否踢训练赛").equals("1")) {
//                        String ret = OperationUtils.doTrainingMatch(email);
//                        if(StringUtils.isNotEmpty(ret)) {
//                            return 5 * 60 * 1000;
//                        }
//                    }
//                    break;
//                case DOSEARCHPLAYER:
//                    if(ConfigUtils.getConf(email, "是否搜索球员").equals("1")) {
//                        OperationUtils.afterFindPlayer(email);
//                        OperationUtils.findPlayer(email);
//                    }
//                    break;
//                case DOTEAMGAME:
////                    Calendar c = Calendar.getInstance();
////                    int hour = c.get(Calendar.HOUR_OF_DAY);
////                    if((ConfigUtils.getConf(email, "是否踢球会大作战").equals("1")) && (hour < 20) && (hour > 9)) {
//                        OperationUtils.teamGame(email, 1);
////                    }
//                    break;
//                case DOTRAIN:
//                    if(ConfigUtils.getConf(email, "是否训练").equals("1")) {
//                        OperationUtils.doTraining(email);
//                    }
//                    break;
//                case DODEFAULT:
//                    OperationUtils.defaults(email, 7);
//                    break;
//                case DOQUERYTASK:
//                    OperationUtils.queryTask(email);
//                    break;
//                case DOWORLDCUP:
//                    OperationUtils.doWorldCup(email);
//                    break;
//                case DOFUBEN:
//                    OperationUtils.doFuben(email);
//                case DOSELLJIQING:
//                    OperationUtils.autoSellJiqing(email);
//            }
//        } catch(Exception ex) {
//            ex.printStackTrace();
//        }
//        return 0l;
//    }
//
//    public void moveToTop(String email, int event) {
//        Map<String, Object[]> events = this.events.get(email);
//        if(events == null) {
//            return;
//        }
//        if(events.containsKey(String.valueOf(event))) {
//            events.get(String.valueOf(event))[0] = new Date().getTime();
//        }
//    }
//
//    public synchronized void addEvent(String email, int event, long nextTime, long loopDelay) {
//        /* 检查是否有重复事件 */
//        if(!this.events.containsKey(email)) {
//            this.events.put(email, new Hashtable<String, Object[]>());
//        }
//        this.events.get(email).put(String.valueOf(event), new Object[]{nextTime, loopDelay});
//    }
//
//    public Map<String, Map<String, Object[]>> getEvents() {
//        return events;
//    }
//
//    public void setEvents(Map<String, Map<String, Object[]>> events) {
//        this.events = events;
//    }
//
//    public static String getEventDesc(int eventId) {
//        switch(eventId) {
//            case DOCHALLENGE:
//                return "巡回赛";
//            case DOARENA:
//                return "竞技场";
//            case DOGETTACTICPOINT:
//                return "战术积分";
//            case DOOGTRAININGMATCH:
//                return "欧冠训练赛";
//            case DOTRAININGMATCH:
//                return "训练赛";
//            case DOSEARCHPLAYER:
//                return "搜寻球员";
//            case DOTEAMGAME:
//                return "球会大作战";
//            case DOTRAIN:
//                return "训练";
//            case DODEFAULT:
//                return "查询信息";
//            case DOQUERYTASK:
//                return "查询任务";
//            case DOWORLDCUP:
//                return "世界杯";
//            case DOFUBEN:
//                return "副本";
//            case DOSELLJIQING:
//                return "卖激情值";
//        }
//        return "未知";
//    }
//
//    public static Vector<Object[]> quickSortEvent(Vector<Object[]> list, int start, int end) {
//        if(start >= end) {
//            return list;
//        }
//        int pos = start;
//        for(int i = pos + 1; i < end; i++) {
//            boolean needSwap = false;
//            if(Long.parseLong(String.valueOf(list.get(i)[2])) < Long.parseLong(String.valueOf(list.get(pos)[2]))) {
////                needSwap = false;
////            } else if(Integer.parseInt(list.get(i)[2]) < Integer.parseInt(list.get(pos)[2])) {
//                needSwap = true;
//            }
//            if(needSwap) {
//                Object[] tmp = list.get(i);
//                for(int m = i; m > pos; m--) {
//                    list.set(m, list.get(m - 1));
//                }
//                list.set(pos, tmp);
//            }
//            pos = i;
//        }
//        quickSortEvent(list, start, pos);
//        quickSortEvent(list, pos + 1, end);
//        return list;
//    }
//}
//
class EventThread extends Thread {
    private final static Logger LOGGER = Logger.getLogger(EventThread.class);

    private String email;
    private Map<String, Object[]> events;
    private LoopUtils instance;
    public EventThread(LoopUtils instance, String email, Map<String, Object[]> events) {
        this.instance = instance;
        this.email = email;
        this.events = events;
        this.setName("Thread-" + this.email);
    }

    public void run() {
        int[] events = new int[this.events.size()];
        long[] nextTimes = new long[this.events.size()];
        long[] loopDelays = new long[this.events.size()];
        int count = 0;
        try {
            for(String eventId : this.events.keySet()) {
                Object[] ev = this.events.get(eventId);
                if(((Long)ev[0]).longValue() <= new Date().getTime()) {
                    long wait = 0;
                    try {
                        wait = instance.doEvent(email, Integer.valueOf(eventId), ev);
                    } catch(Exception ex) {
                        System.out.println("email: " + LoopUtils.getEventDesc(Integer.valueOf(eventId)));
                        ex.printStackTrace();
                    }
                    long now = new Date().getTime();
//                events.put(eventId, new Object[]{now + (wait != 0d ? wait : ((Long)ev[1]).longValue()), ((Long)ev[1]).longValue()});
                    events[count] = Integer.parseInt(eventId);
                    nextTimes[count] = now + (wait != 0d ? wait : ((Long) ev[1]).longValue());
                    loopDelays[count] = ((Long) ev[1]).longValue();
//                instance.addEvent(email, Integer.parseInt(eventId), now + (wait != 0d ? wait : ((Long)ev[1]).longValue()), ((Long)ev[1]).longValue());
                } else {
                    events[count] = Integer.parseInt(eventId);
                    nextTimes[count] = ((Long) ev[0]).longValue();
                    loopDelays[count] = ((Long) ev[1]).longValue();
                }
                count++;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        for(int i = 0; i < count; i++) {
            try {
                instance.addEvent(email, events[i], nextTimes[i], loopDelays[i]);
            } catch(Exception ex) {
                UtilFunctions.LogError(LOGGER, ex);
                ex.printStackTrace();
            }
        }
    }
}