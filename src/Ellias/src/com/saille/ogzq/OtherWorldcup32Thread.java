package com.saille.ogzq;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;

import java.util.*;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-7-6
 * Time: 18:38:20
 * To change this template use File | Settings | File Templates.
 */
public class OtherWorldcup32Thread extends Thread{
    private static final Logger LOGGER = Logger.getLogger(OtherWorldcup32Thread.class);
    private Map<String, HttpClient> clients = new HashMap<String, HttpClient>();
    private String startdate = "";

    public OtherWorldcup32Thread() {
        startdate = new SimpleDateFormat("yyyyMMdd").format(new Date());
        this.setName("Worldcup32Thread");
    }

    public void run() {
        List<String[]> jiongids = new ArrayList<String[]>();
        jiongids.addAll(IDUtils.JIONGIDS);
        jiongids.addAll(IDUtils.GIDS);
        jiongids.addAll(IDUtils.WEIIDS);
        jiongids.addAll(IDUtils.XYIDS);
//        jiongids.add(new String[]{"kkoanbfm@vip.qq.com", "yuliang83012", "sky爆射"});
//        jiongids.add(new String[]{"hahako@yeah.net", "593425kk", "SD狼耶"});
        while(jiongids.size() > 0) {
            if(!new SimpleDateFormat("yyyyMMdd").format(new Date()).equals(startdate)) {
                LOGGER.info("24点已过，跳出循环");
                break;
            }
            StringBuffer sb = new StringBuffer();
            for(String[] s : jiongids) {
                sb.append(s[2]).append(",");
            }
            LOGGER.info("循环世界杯32强，剩余球队：" + jiongids.size() + "=" + sb.toString());
            try {
                for(int i = jiongids.size() - 1; i >= 0; i--) {
                    String email = jiongids.get(i)[0];
                    if(email.equals("sevarsti@sina.com") ||
                            email.equals("rentao@vip.sina.com") ||
                            email.equals("meijianbai@hotmail.com")) {
                        jiongids.remove(i);
                        continue; //我挂的号，不需要这里打
                    }
                    LOGGER.debug("世界杯32强：" + jiongids.get(i)[2]);
                    HttpClient client = clients.get(email);
                    if(client == null) {
                        client = LoginUtils.Login(email, jiongids.get(i)[1]);
                        clients.put(email, client);
                    }

                    boolean cont = true;
                    int page = 0;
                    while(cont) {
                        HttpPost pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                        List<NameValuePair> params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("Load1", "" + page));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        String ret = IDUtils.execute(clients.get(email), email, pm);
                        String[] parts = ret.split("#");
                        if(parts[0].indexOf("inmatch|") >= 0) { //比赛中
                            break;
                        } else if(parts[0].split("@").length > 3 && (!"".equals(parts[0].split("@")[3]))) { //需要领奖
                            String[] cards = parts[0].split("@")[3].split("[|]");
                            boolean hasOpened = false;
                            for(String card : cards) {
                                if(card.split("[*]")[0].equals("0")) {
                                    hasOpened = true;
                                    break;
                                }
                            }
                            if(!hasOpened) {
                                pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("FlopCard1", "" + ((new Random().nextInt() % 5) + 1)));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(client, email, pm);
                                LOGGER.info(jiongids.get(i)[2] + "奖励：" + ret);
                            }
                            pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("AcceptCardAward1", ""));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            ret = IDUtils.execute(clients.get(email), email, pm);
                            continue;
                        } else { //打比赛
                            if(parts[0].split("@").length < 3) {
                                continue;
                            }
                            String[] teams = parts[0].split("@")[2].split("[\\^]");
                            boolean startmatch = false;
                            for(String t : teams) {
                                int totaltime = Integer.parseInt(t.split("[*]")[4]);
                                if(totaltime == 0) {
                                    cont = false;
                                    break;
                                }
                                int resttime = Integer.parseInt(t.split("[*]")[3]);
                                if(resttime > 0) {
                                    pm = new HttpPost(OgzqURL.URL + "/WorldCup32.aspx");
                                    params = new ArrayList<NameValuePair>();
                                    params.add(new BasicNameValuePair("Challenge1", t.split("[*]")[0]));
                                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                    ret = IDUtils.execute(clients.get(email), email, pm);
                                    if(Integer.parseInt(ret) > 0) {
                                        LOGGER.info(jiongids.get(i)[2] + "比赛：" + ret);
                                        startmatch = true;
                                        break;
                                    }
                                }
                            }
                            if(!cont) {
                                //能打的都打完了
                                LOGGER.info(jiongids.get(i)[2] + "场次=0，退出");
                                jiongids.remove(i);
                            } else {
//                                if(startmatch) {
//                                    break;
//                                }
                            }
                        }
                        page++;
                        if(page > 3) {
                            cont = false;
                        }
                    }
                }
                Thread.sleep(1000 * 10);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
