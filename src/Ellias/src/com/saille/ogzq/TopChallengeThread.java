package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-10-6
 * Time: 23:41:48
 * To change this template use File | Settings | File Templates.
 */
public class TopChallengeThread extends Thread{
    private static final Logger LOGGER = Logger.getLogger(TopChallengeThread.class);
    public static String PLAYER = "Bremer";
    private static TopChallengeThread instance = null;
    public List<String[]> emails = new ArrayList<String[]>();
    public Map<String, String> pwds = new HashMap<String, String>();
    public Map<String, Integer> count = new HashMap<String, Integer>();
    public Map<String, List<HttpClient>> clients = new HashMap<String, List<HttpClient>>();
    private TopChallengeThread() {

    }

    public synchronized static TopChallengeThread getInstance() {
        if(instance == null) {
            instance = new TopChallengeThread();
            List<String[]> newIds = new ArrayList<String[]>();
//            List<String[]> ids;
            List<String> myId = IDUtils.GETIDS();
            for(String id : myId) {
                if(id.indexOf("txjcf") >= 0) {
                    continue;
                }
                newIds.add(new String[]{id, IDUtils.IDPWDS.get(id), IDUtils.getNick(id), "云"});
                instance.clients.put(id, new ArrayList<HttpClient>());
            }
//            ids = IDUtils.JIONGIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "囧"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
//            ids = IDUtils.GIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "G"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
//            ids = IDUtils.WEIIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "威"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
//            ids = IDUtils.XYIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "XY"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
//            ids = IDUtils.NBIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "NB"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
//            ids = IDUtils.SIDS;
//            for(String[] id : ids) {
//                newIds.add(new String[]{id[0], id[1], id[2], "S"});
//                instance.clients.put(id[0], new ArrayList<HttpClient>());
//            }
            for(int j = 0; j < newIds.size(); j++) {
                String[] id = newIds.get(j);
//                if(id[0].indexOf("sevarsti@sina.com") >= 0) {
//                    instance.count.put(id[0], 5);
//                    for(int i = 0; i < 5; i++) {
//                        LOGGER.info(j + "/" + newIds.size() + "，球会巅峰登录：" + id[0] + "/" + id[2]);
//                        HttpClient client = LoginUtils.Login(id[0], id[1]);
//                        instance.pwds.put(id[0], id[1]);
//                        instance.clients.get(id[0]).add(client);
//                    }
//                } else {
                    instance.count.put(id[0], 1);
                for(int i = 0; i < 1; i++) {
                    LOGGER.info(j + "/" + newIds.size() + "，球会巅峰登录：" + id[0] + "/" + id[2]);
                    if(IDUtils.IDS.containsKey(id[0])) {
                        instance.pwds.put(id[0], IDUtils.IDPWDS.get(id[0]));
                        instance.clients.get(id[0]).add(IDUtils.IDS.get(id[0]));
                    } else {
                        HttpClient client = LoginUtils.Login(id[0], id[1]);
                        instance.pwds.put(id[0], id[1]);
                        instance.clients.get(id[0]).add(client);
                    }
                }
//                }
            }
            instance.emails = newIds;
        }
        return instance;
    }

    public void run() {
        boolean goon = true;
        while(goon) {
            long now = System.currentTimeMillis();
            for(int j = 0; j < this.emails.size(); j++) {
                String[] email = this.emails.get(j);
                System.out.println("循环球会战：" + j + "/" + this.emails.size() + ":" + email[0]);
                try {
                    int threadCount = count.get(email[0]).intValue(); //并行数量
                    /* 检查线程数是否达到要求 */
                    if(clients.get(email[0]) == null || clients.get(email[0]).size() < threadCount) {
                        int current = clients.get(email[0]) == null ? 0 : clients.get(email[0]).size();
                        for(int i = current; i < threadCount; i++) {
                            LOGGER.info("球会巅峰登录：" + email[0] + "/" + email[2]);
                            HttpClient client = LoginUtils.Login(email[0], email[1]);
                            clients.get(email[0]).add(client);
                        }
                    }
                    /* 检查是否可以比赛 */
                    HttpClient firstclient = clients.get(email[0]).get(0);
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/" + PLAYER + "_Top_Club.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(firstclient, email[0], pm);
                    if(s.startsWith("1@")) { //正在比赛
                        continue;
                    } else if("-2".equals(s)) { //没有球会，表示登录状态丢失
                        firstclient = LoginUtils.Login(email[0], email[1]);
                        clients.get(email[0]).set(0, firstclient);
                        continue;
                    }
                    int rest = Integer.parseInt(s);
                    if(rest > 0) { //需要等待
//                        continue;
                    } else if(rest == -1) { //活动结束
                        goon = false;
                        break;
                    } else if(rest == 0) {
                        Thread.sleep(1000);
                        for(int i = 0; i < threadCount; i++) {
                            DoTopChallengeSingleThread t = new DoTopChallengeSingleThread(i, email[0], email[2]);
                            t.start();
//                            Thread.sleep(10);
                        }
                    }
                    /* 保持每个client登录状态 */
                    for(int i = 1; i < clients.get(email[0]).size(); i++) {
                        HttpClient client = clients.get(email[0]).get(i);
                        pm = new HttpPost(OgzqURL.URL + "/" + PLAYER + "_Top_Club.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("type", "1"));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(client, email[0], pm);
                    }
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
            try {
                now = System.currentTimeMillis() - now;
                now = 20000 - now;
                System.out.println("循环巅峰战，等待下次循环......................" + now);
                if(now > 0) {
                    Thread.sleep(now);
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        TopChallengeThread.instance = null;
    }

    public String deleteSingle(String email) {
        boolean found = false;
        for(String emails[] : this.emails) {
            if(emails[0].equals(email)) {
                found = true;
                break;
            }
        }
        if(!found) {
            return "没有找到对应的号";
        }
        int current = this.count.get(email).intValue();
        if(current > 0) {
            this.count.put(email, this.count.get(email).intValue() - 1);
            if(this.count.get(email).intValue() > 0) { //保持最后一个client永远不会删掉
                this.clients.get(email).remove(this.clients.get(email).size() - 1);
            }
            return null;
        } else {
            return email + "的线程数已经为0";
        }
    }

    public String addSingle(String email) {
        String pwd = null;
        for(String emails[] : this.emails) {
            if(emails[0].equals(email)) {
                pwd = emails[1];
                break;
            }
        }
        if(pwd == null) {
            return "找不到帐号密码";
        }
        if(this.clients.get(email).size() < (this.count.get(email).intValue() + 5)) {
            LOGGER.info("球会巅峰登录：" + email + "/" + pwd);
            HttpClient client = LoginUtils.Login(email, pwd);
            this.clients.get(email).add(client);
        }
        this.count.put(email, this.count.get(email).intValue() + 5);
        return null;
    }
}

class DoTopChallengeSingleThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(DoTopChallengeSingleThread.class);
    private int clientIdx;
    private String email;
    private String nick;
    public DoTopChallengeSingleThread(int clientIdx, String email, String nick) {
        this.clientIdx = clientIdx;
        this.email = email;
        this.nick = nick;
    }

    public void run() {
        try {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/" + TopChallengeThread.PLAYER + "_Top_Club.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(TopChallengeThread.getInstance().clients.get(email).get(clientIdx), email, pm);
            LOGGER.info(nick + "打巅峰战：" + s);
            if(s.equals("-3")) {
                HttpClient client = LoginUtils.Login(email, TopChallengeThread.getInstance().pwds.get(email));
                TopChallengeThread.getInstance().clients.get(email).set(clientIdx, client);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
