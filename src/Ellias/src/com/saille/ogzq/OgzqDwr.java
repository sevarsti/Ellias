package com.saille.ogzq;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.protocol.HTTP;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.util.*;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import com.saille.util.UtilFunctions;
import com.saille.util.SortUtils;
import com.saille.ogzq.loop.AutoFinishTaskThread;
import com.saille.ogzq.loop.ClubBuffThread;
import com.saille.ogzq.dailyLoop.*;
import com.saille.ogzq.activityLoop.ActivityLoopThread;

public class OgzqDwr {
    private static final Logger LOGGER = Logger.getLogger(OgzqDwr.class);

    public static void main(String[] args) {
        new OgzqDwr().init();
    }

    public void init() {
        try {
            CheckAllTopThread.getInstance().start();
            new AutoFinishTaskThread().start();
            ActivityLoopThread.getInstance().start();
            
            SellJiqingThread.getInstance().start();

            String[] ids = new String[0];
            PropertiesConfiguration conf = new PropertiesConfiguration(ConfigUtils.class.getResource("../../../../../ogzq/ids.ini"));
            ids = conf.getStringArray("id");
            MonitorTeamgameThread t = MonitorTeamgameThread.getInstance();
            try {
                t.start();
            } catch(Exception ex) {}
            for(String id : ids) {
                try {
                    HttpClient client;
                    if(id.indexOf("sevarsti") != -1) {
                        client = LoginUtils.Login(id, "pmgkpmgk");
                    } else {
                        client = LoginUtils.Login(id, "lspmgk");
                    }
                    if(client == null) {
                        LOGGER.warn(id + "密码错误！！！！！！！！！！！！");
                        continue;
                    }
                    IDUtils.IDS.put(id, client);
                    try {
                        OperationUtils.defaults(id, 7);
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                    IDUtils.WorldCupFinished.put(id, false);
    //                IDUtils.FubenStatus.put(email, new int[]{5,5,5,5,5});

                    AutoLoopThread thread = new AutoLoopThread(id);
                    thread.start();

                    List<String> list = new ArrayList<String>();
                    list.add(id);
                    TeamXiangqianCacheThread thread2 = new TeamXiangqianCacheThread(list, 0, false);
                    thread2.start();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            ViewAbilityThread.getInstance().start();
            LoopUtils.getInstance().start();
            SearchPlayerThread.getInstance().start();
            com.saille.ogzq.dailyLoop.ArenaThread.getInstance().start();
            PeleThread.getInstance().start();
            com.saille.ogzq.dailyLoop.Worldcup32Thread.getInstance().start();
            ClubBuffThread.getInstance().start();
            TrainingmatchThread.getInstance().start();
//            ArenaThread.getInstance().start();
//            MonitorTeamgameThread.getInstance().start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Map<String, String>> viewMiddleMan(String email) {
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
        List<String> ids = generateEmail(email);
        for(String id : ids) {
            System.out.println("查看候选球员大厅:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            List<String[]> xiangqians = IDUtils.getXiangqianPlayer(id, 0);
            try {
                List<Map<String, String>> list = OperationUtils.middleman(id);
                for(Map<String, String> m : list) {
                    m.put("email", id);
                    m.put("nick", IDUtils.getNick(id));
                    m.put("silver", IDUtils.IDInfos.get(id).get("silver"));
                    int count = 0;
                    for(String[] xq : xiangqians) {
                        if(xq[1].equals(m.get("name")) && xq[2].equals(m.get("level"))) {
                            count++;
                        }
                    }
                    if(m.get("name").equals("斯特克伦博格")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[0];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 5);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("纳尼")) {
                            int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[2];
                            Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 5);
                            String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                            m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("拉莫斯")) {
                        int[] counts = OperationUtils.getFullXiangqianCount(m, xiangqians);
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name") + "后", 5);
                        String c0 = objs[0] == null ? ("" + counts[1]) : ("<span style=\"color:red;\">" + counts[1] + "</span>");
                        objs = OperationUtils.getXiangqianIds(id, m.get("name") + "中", 5);
                        String c1 = objs[0] == null ? ("" + counts[2]) : ("<span style=\"color:red;\">" + counts[2] + "</span>");
                        m.put("count", count + "/" + c0 + "/" + c1);
                    } else if(m.get("name").equals("阿巴特")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[2];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 4);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("贝尔巴托夫")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[3];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 4);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("梅策尔德")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[1];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 4);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("什科琴尼")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[0];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 4);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("戈比")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[1];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 3);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("卡列洪")) {
                        int[] counts = OperationUtils.getFullXiangqianCount(m, xiangqians);
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name") + "中", 3);
                        String c0 = objs[0] == null ? ("" + counts[2]) : ("<span style=\"color:red;\">" + counts[2] + "</span>");
                        objs = OperationUtils.getXiangqianIds(id, m.get("name") + "前", 3);
                        String c1 = objs[0] == null ? ("" + counts[3]) : ("<span style=\"color:red;\">" + counts[3] + "</span>");
                        m.put("count", count + "/" + c0 + "/" + c1);
                    } else if(m.get("name").equals("朗德洛")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[0];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 3);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("乌塔卡")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[3];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 2);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("戈尼亚隆斯")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[1];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 2);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("瓦尔布埃纳")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[2];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 2);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else if(m.get("name").equals("芒当达")) {
                        int counts = OperationUtils.getFullXiangqianCount(m, xiangqians)[0];
                        Object[] objs = OperationUtils.getXiangqianIds(id, m.get("name"), 2);
                        String c = objs[0] == null ? ("" + counts) : ("<span style=\"color:red;\">" + counts + "</span>");
                        m.put("count", count + "/" + c);
                    } else {
                        m.put("count", count + "");
                    }
                    ret.add(m);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return ret;
    }

    public String dropPlayer(String email, String id) {
        try {
            OperationUtils.dropPlayer(email, id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String finishTaskAll(String taskType) {
        try {
            List<String> ids = generateEmail("");
            for(String id : ids) {
                System.out.println("finish task:" + ids.indexOf(id) + "/" + ids.size() + ": " + id);
                OperationUtils.taskInfo(id);
                List<Map<String, String>> tasks = IDUtils.IDTaskInfos.get(id);
                for(Map<String, String> t : tasks) {
                    String taskId = t.get("id");
                    if("0".equals(taskType)) { //完成所有可以完成的任务
                        if(t.get("finished").equals("是")) {
                            this.finishTask(id, taskId);
                        }
                    } else if("1".equals(taskType)) { //训练球员
                        if("3010".equals(taskId) ||
                                "3020".equals(taskId) ||
                                "3030".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("2".equals(taskType)) { //竞技赛
                        if("3040".equals(taskId) ||
                                "3050".equals(taskId) ||
                                "3060".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("3".equals(taskType)) { //球会大作战
                        if("3070".equals(taskId) ||
                                "3080".equals(taskId) ||
                                "3090".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("4".equals(taskType)) { //搜索球员
                        if("3100".equals(taskId) ||
                                "3110".equals(taskId) ||
                                "3120".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("5".equals(taskType)) { //训练赛
                        if("3130".equals(taskId) ||
                                "3140".equals(taskId) ||
                                "3150".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("6".equals(taskType)) { //欧冠之魂
                        if("3160".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("7".equals(taskType)) { //欧冠之巅
                        if("5404".equals(taskId) ||
                                "5405".equals(taskId) ||
                                "5406".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    } else if("8".equals(taskType)) { //国旗巡回赛
                        if("5401".equals(taskId) ||
                                "5402".equals(taskId) ||
                                "5403".equals(taskId)) {
                            this.finishTask(id, taskId);
                        }
                    }
                }
            }
//            OperationUtils.finishTask(email, id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "done";
    }

    public String finishTask(String email, String id) {
        try {
            OperationUtils.finishTask(email, id);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String doSellLv5(String email) {
        List<String> ids = generateEmail(email);
        StringBuffer sb = new StringBuffer();
        for(String id : ids) {
            try {
                OperationUtils.doSellLv5(id);
            } catch(Exception ex) {
                sb.append("\n").append(id).append(" failed");
                ex.printStackTrace();
            }
        }
        return sb.length() > 0 ? sb.toString() : "success";
    }

    public List<Map<String, String>> viewPlayer(String email) {
//        LOGGER.info("view player");
        try {
            List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
            List<String> ids = generateEmail(email);
            for(String id : ids) {
                System.out.println("查看球员:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
                try {
                    ret.addAll(OperationUtils.viewTeam(id));
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Map<String, String>> viewUpgradePlayer(String email) {
        LOGGER.info("view player");
        try {
            List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
            List<String> ids = generateEmail(email);
            for(String id : ids) {
                System.out.println("查看可升级球员:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
                try {
                    List<Map<String, String>> list = OperationUtils.viewTeam(id);
                    for(Map<String, String> m : list) {
                        if(m.containsKey("exp")) {
                            String exp = m.get("exp");
                            String[] ee = exp.split("/");
                            if(Integer.parseInt(ee[0]) >= Integer.parseInt(ee[1])) {
                                m.put("nick", IDUtils.getNick(m.get("email")));
                                ret.add(m);
                            }
                        }
                    }
//                    ret.addAll(OperationUtils.viewTeam(id));
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<Map<String, String>> viewTaskinfo(String email, int type) {
        LOGGER.info("view taskinfo: " + email);
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
        List<String> ids = generateEmail(email);
        for(String id : ids) {
            System.out.println("查看任务:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            try {
                OperationUtils.taskInfo(id);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        Map<String, List<Map<String, String>>> list = IDUtils.IDTaskInfos;
        for(String id : ids) {
            List<Map<String, String>> l = list.get(id);
            for(Map<String, String> m : l) {
                m.put("email", id);
                m.put("nick", IDUtils.getNick(id));
                if(type == 1) { //已完成任务
                    if(m.containsKey("finished") && StringUtils.isNotEmpty(m.get("finished"))) {
                        ret.add(m);
                    }
                } else if(type == 2) { //未完成任务
                    if((!m.containsKey("finished")) || m.get("finished") == null || StringUtils.isEmpty(m.get("finished").trim())) {
                        ret.add(m);
                    }
                } else {
                    ret.add(m);
                }
            }
        }
        String id;
        return ret;
    }

    public String trainPlayer(String email, String detail) {
        try {
            OperationUtils.trainPlayer(email, detail);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }

    public String[] beforeSign(String email, String playerId) {
        try {
            List<String[]> items = OperationUtils.beforeSignPlayer(email, playerId);
            if(items == null) { //替补席满了
                return null;
            }
            List<Map<String, String>> currentItems = OperationUtils.listBags(email, "1");
            String ret = "";
            for(String[] i : items) {
                String code = i[0];
                String name = i[1];
                if(!ret.equals("")) {
                    ret = (new StringBuilder()).append(ret).append(", ").toString();
                }
                ret = (new StringBuilder()).append(ret).append(name).append("(").toString();
                boolean found = false;
                Iterator j$ = currentItems.iterator();
                do {
                    if(!j$.hasNext()) {
                        break;
                    }
                    Map<String, String> m = (Map) j$.next();
                    if(!m.get("itemid").equals(code)) {
                        continue;
                    }
                    ret = (new StringBuilder()).append(ret).append(m.get("number")).toString();
                    found = true;
                    break;
                } while(true);
                if(!found) {
                    ret = (new StringBuilder()).append(ret).append("0").toString();
                }
                ret = (new StringBuilder()).append(ret).append(")").toString();
            }

            return (new String[]{email, playerId, ret});
        } catch(Exception ex) {
            ex.printStackTrace();
            return (new String[]{""});
        }
    }

    public String signPlayer(String email, String playerId) {
        try {
            OperationUtils.signPlayer(email, playerId);
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public String signAndFirePlayer(String email, String playerId) throws Exception {
        try {
            return OperationUtils.signAndFirePlayer(email, playerId);
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
            return "出现异常：" + ex.getMessage();
        }
    }

    public String signAndTrainAndFirePlayer(String email, String playerName, String playerId, int level) throws Exception {
        try {
            return OperationUtils.signAndTrainAndFirePlayer(email, playerName, playerId, level);
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
            return "出现异常：" + ex.getMessage();
        }
    }

    public String upgradePlayer(String email, String playerId) {
        try {
            OperationUtils.upgradePlayer(email, playerId);
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public String combine(String email, String itemId, int count) {
        try {
            List<Map<String, String>> currentItems = OperationUtils.listBags(email, "1");
            for(Map<String, String> item : currentItems) {
                if(item.get("itemid").equals(itemId)) {
                    String id = item.get("id");
                    if(count == 0) {
                        OperationUtils.combine(email, id);
                    } else {
                        for(int i = 0; i < count / 5; i++) {
                            OperationUtils.combine(email, id);
                        }
                    }
                    break;
                }
            }
            OperationUtils.defaults(email, 7);
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public String autoTrainTask(String email) {
        try {
            LOGGER.info("auto train task: " + email);
            List ret = new ArrayList();
            List<String> ids = generateEmail(email);
            for(String id : ids) {
                int count = 0;
                List mapList = viewPlayer(id);
                for(int i = 1; i < mapList.size(); i++) {
                    Map player = (Map) mapList.get(i);
                    if((Integer.parseInt((String) player.get("pinzhi")) < 3) || (!StringUtils.isNotEmpty((String) player.get("tibu")))) {
                        continue;
                    }
                    if(OperationUtils.autoTrainOnePoint(id, player)) {
                        count++;
                    }
                }

                if(count < 5) {
                    for(int i = 1; i < mapList.size(); i++) {
                        Map player = (Map) mapList.get(i);
                        OperationUtils.autoTrainOnePoint(id, player);
                    }
                }
            }
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public String useItem(String email, String id, int all) {
        try {
            System.out.println("使用物品: " + email + "/" + id + "/all: " + all);
            StringBuffer ret = new StringBuffer("");
            for(int i = 0; i < all; i++) {
                ret.append(OperationUtils.useItem(email, id)).append("\r\n");
            }
            return ret.toString();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return "fail";
    }

    public String signupTeamGame(String email) {
        List<String> ids = this.generateEmail(email);
        for(String id : ids) {
            try {
                OperationUtils.signupTeamGame(id);
                IDUtils.JOINEDTEAMGAME.put(id, true);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return "done";
    }

    public String getTacticPoint(String email) {
        LOGGER.info("获取战术积分");
        List<String> ids = this.generateEmail(email);
        for(String id : ids) {
            System.out.println("获取战术积分" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            try {
                OperationUtils.getTacticPoint(id);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return "done";
    }

    public String cheDan(String email, String id) {
        try {
            return OperationUtils.cheDan(email, id);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String addArenaMonitor(String email, String pwd) {
        try {
            ArenaThread.getInstance().doAdd(email, pwd);
            return "done";
        } catch(Exception ex) {
            return "出现错误";
        }
    }

    public String fireToTactic(String email, String playerId) {
        try {
            return OperationUtils.fireToTactic(email, playerId);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String fireToHalfBack(String email, String playerId) {
        try {
            return OperationUtils.fireToHalfBack(email, playerId);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String useTrainCard(String email, String playerId, String itemId, int count) throws Exception {
        try {
            return OperationUtils.useTrainCard(email, playerId, itemId, count);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String decomposition(String email, String itemId, int count) throws Exception {
        try {
            return OperationUtils.decomposition(email, itemId, count);
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String getDecompositionGift(String email) throws Exception {
        try {
            List<String> ids = generateEmail(email);
            for(String id : ids) {
                OperationUtils.getDecompositionGift(id);
            }
            return "success";
        } catch(Exception ex) {
            ex.printStackTrace();
            return "exception";
        }
    }

    public String addTopChallenge(int count) {
        TopChallengeMonitorThread.getInstance().addChangci(count);
        return "done";
    }

    public String closeTopChallenge() {
        TopChallengeMonitorThread.getInstance().closeChallenge();
        return "done";
    }

    public String moveEventToTop(String email, int event) {
        LoopUtils.getInstance().moveToTop(email, event);
        return "done";
    }

    public String saveConfig(String key, String value) {
        ConfigUtils.saveConf(key, value);
        return "success";
    }

    public String deleteConfig(String key) {
        ConfigUtils.deleteConf(key);
        return "success";
    }

    private List<String> generateEmail(String email) {
        List<String> ret = new ArrayList();
        if(StringUtils.isNotEmpty(email)) {
            if(IDUtils.IDS.containsKey(email)) {
                ret.add(email);
            }
        } else {
            Set<String> keys = IDUtils.IDS.keySet();
            for(String k : keys) {
                ret.add(k);
            }
        }
        Collections.sort(ret);
        return ret;
    }

    public String removeEquipment(String email, String player, String type) throws Exception {
        return OperationUtils.removeEquipment(email, player, type);
    }

    public String wearEquipment(String email, String playerId, String itemId, String itemCode) throws Exception {
        return OperationUtils.wearEquipment(email, playerId, itemId, itemCode);
    }

    public String equipBonus(String email, String itemId) throws Exception {
        return OperationUtils.equipBonus(email, itemId);
    }

    public String equipRefresh(String email, String itemId, String itemLevel) throws Exception {
        return OperationUtils.equipRefresh(email, itemId, itemLevel);
    }

    public String changePlayerPos(String email, String p1, String p2) throws Exception {
        return OperationUtils.changePlayerPos(email, p1, p2);
    }

    public String changeTactic(String email, int tactic) throws Exception {
        return OperationUtils.changeTactic(email, tactic);
    }

    public static String doTeamGame(String email, String opponent, String matchPrice) throws Exception {
        if(IDUtils.GETIDS().contains(email)) {
            return OperationUtils.doTeamGame(email, opponent, matchPrice);
        } else {
            MonitorTeamgameThread instance = MonitorTeamgameThread.getInstance();
            if(instance == null) {
                return "球会战监控未启动";
            }
            List<String[]> ids = instance.ourIds;
            for(String[] i : ids) {
                if(i[0].equals(email)) {
                    String pwd = i[1];
                    return OperationUtils.doOtherTeamGame(email, pwd, opponent, matchPrice);
                }
            }
            return "未找到" + email + "对应的号";
        }
    }

    public String qiuyuanshengjie(String email, String playerId) throws Exception {
        return OperationUtils.qiuyuanshengjie(email, playerId);
    }

    public String qiuyuanchengzhang(String email, String playerId) throws Exception {
        return OperationUtils.qiuyuanchengzhang(email, playerId);
    }

    public String xiangqian(String email, String playerId, String xiangqianId) throws Exception {
        return OperationUtils.xiangqian(email, playerId, xiangqianId);
    }

    public String fullTrain(String email, String playerId, int level) throws Exception {
        return OperationUtils.fullTrain(email, playerId, level);
    }

    public List<String[]> loadXiangqianTactic(String email, int level) throws Exception {
        return TeamXiangqianCacheThread.loadSinglePlayerLevelXiangqian(email, level);
    }

    public String openXiubiao(String email, String playerId) throws Exception {
        return OperationUtils.openXiubiao(email, playerId);
    }

    public static String backFromTacti(String email, String playerId)  throws Exception {
        return OperationUtils.backFromTacti(email, playerId);
    }

    /////Ogzq Utils///////////////
    public String callFunction(String methodName, String param) {
        try {
            Object ret = "can not find method";
            Method[] methods = OgzqUtils.class.getDeclaredMethods();
            for(int i = 0; i < methods.length; i++) {
                if(methods[i].getName().equals(methodName)) {
                    if(StringUtils.isEmpty(param)) {
                        ret = methods[i].invoke(null, new Object[]{});
                    } else {
                        ret = methods[i].invoke(null, new Object[]{param});
                    }
                }
            }
            return String.valueOf(ret);
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace();
            ex.printStackTrace(pw);
            return sw.toString().replaceAll("\r\n", "<br/>");
        }
    }

    public String updateClubAbility(String clubId) throws Exception {
        ViewAbilityThread.loadClub(clubId);
        return "done";
    }

    public String updatePlayerAbility(String playerId) throws Exception {
        ViewAbilityThread.loadPlayer(playerId);
        return "done";
    }

    public static String upQiuyuanyishi(String email, String playerId, String jingong, String zuzhi, String fangshou) throws Exception {
        return OperationUtils.upQiuyuanyishi(email, playerId, jingong, zuzhi, fangshou);
    }

    public static String stopTrainingBase2(String email, String level) throws Exception {
        return OperationUtils.stopTrainingBase2(email, level);
    }

    public static List<String[]> sjkUpCost(String email, String playerId) throws Exception {
        return OperationUtils.sjkUpCost(email, playerId);
    }

    public static List<String[]> steelpowerUpCost(String email, String playerId) throws Exception {
        return OperationUtils.steelpowerUpCost(email, playerId);
    }

    public static String sjkUp(String email, String playerId, String costPlayerId) throws Exception {
        return OperationUtils.sjkUp(email, playerId, costPlayerId);
    }

    public static String steelpowerUp(String email, String playerId, String costPlayerId) throws Exception {
        return OperationUtils.steelpowerUp(email, playerId, costPlayerId);
    }

    public static List<Map<String, String>> listCoachBags(String email, String itemtype) throws Exception {
        return OperationUtils.listCoachBags(email, itemtype);
    }

    public static String coachLevelUp(String email, String coachIndex) throws Exception {
        return OperationUtils.coachLevelUp(email, coachIndex);
    }

    public static String useCoachItem(String email, String coachIndex, String itemCode) throws Exception {
        return OperationUtils.useCoachItem(email, coachIndex, itemCode);
    }

    public static String combineCoach(String email, String itemId) throws Exception {
        return OperationUtils.combineCoach(email, itemId);
    }

    public static String hireCoach(String email, String coachIndex) throws Exception {
        return OperationUtils.hireCoach(email, coachIndex);
    }

    public static String upClubBuff(String email, String buffIndex, String teamId) throws Exception {
        return OperationUtils.upClubBuff(email, buffIndex, teamId);
    }

    public static String jiqingxunlian(String email, String player) throws Exception {
        return OperationUtils.jiqingxunlian(email, player);
    }

    public static String signChallengePlayer(String email, String player) throws Exception {
        return OperationUtils.signChallengePlayer(email, player);
    }

    public static String challengeAddPower(String email, String player) throws Exception {
        return OperationUtils.challengeAddPower(email, player);
    }

    public static String changeCoach(String email, String coach) throws Exception {
        return OperationUtils.changeCoach(email, coach);
    }

    public static String openjieshuo(String email, String index) throws Exception {
        return OperationUtils.openjieshuo(email, index);
    }

    public static String useJieshuoItem(String email, String itemcode, String index) throws Exception {
        return OperationUtils.useJieshuoItem(email, itemcode, index);
    }

    public static String xiangqianGem(String email, String playerId, String gemId, String itemType) throws Exception {
        return OperationUtils.xiangqianGem(email, playerId, gemId, itemType);
    }

    public static String jieshuoLevelup(String email, String index) throws Exception {
        return OperationUtils.jieshuoLevelup(email, index);
    }

    public static String autoUpJieshuo(String email) throws Exception {
        return OperationUtils.autoUpJieshuo(email);
    }

    public static String combineJieshuo(String email, String itemId) throws Exception {
        return OperationUtils.combineJieshuo(email, itemId);
    }

    public static String upgradePlayerShijiebei(String email, String playerId) throws Exception {
        return OperationUtils.upgradePlayerShijiebei(email, playerId);
    }

    public static void updateAllTop(String platform, String server) {
        CheckAllTopThread.getInstance().updateServer(platform, server);
    }

    public static String upgradeTitle(String email, String playerId, String isLucky) throws Exception {
        return OperationUtils.upgradeTitle(email, playerId, isLucky);
    }
}
