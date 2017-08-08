package com.saille.ogzq.loop;

import org.apache.log4j.Logger;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.NameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-12-1
 * Time: 11:13:47
 * To change this template use File | Settings | File Templates.
 */
public class AutoFinishTaskThread extends Thread{
    private final static Logger LOGGER = Logger.getLogger(AutoFinishTaskThread.class);
    public void run() {
        while(true) {
            LOGGER.info("循环结束日常任务。。。");
            try {
                String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
                List<String> ids = IDUtils.GETIDS();
                for(String id : ids) {
                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String rett = IDUtils.execute(id, pm);
                    String[] dailytasks = rett.split("\\|")[2].split("∠");
                    for(String task : dailytasks) {
                        String[] parts = task.split("\\*");
                        boolean canfinish = false;
                        if(parts[6].equals("1")) {
                            if("3010".equals(parts[0]) ||
                                "3020".equals(parts[0]) ||
                                "3030".equals(parts[0]) || //训练球员
                                "3040".equals(parts[0]) ||
                                "3050".equals(parts[0]) ||
                                "3060".equals(parts[0]) || //竞技
                                "3070".equals(parts[0]) ||
                                "3080".equals(parts[0]) ||
                                "3090".equals(parts[0]) ||//球会战
                                "5400".equals(parts[0]) ||
                                "5401".equals(parts[0]) ||
                                "5402".equals(parts[0]) ||
                                "5403".equals(parts[0]) //国旗巡回赛
                                    ) {
                                canfinish = true;
                            } else if("3100".equals(parts[0]) ||
                                "3110".equals(parts[0]) ||
                                "3120".equals(parts[0])
                                    ) { //搜索球员
                                int add = 200;
                                if("3100".equals(parts[0])) {
                                    add = 10;
                                } else if("3110".equals(parts[0])) {
                                    add = 20;
                                } else if("3120".equals(parts[0])) {
                                    add = 30;
                                }
                                pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("loadPower", "1"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String s = IDUtils.execute(id, pm);
                                String[] subs = s.split("\\|");
                                int rest = Integer.parseInt(subs[0]);
                                if((rest + add <= Integer.parseInt(subs[1])) || now.compareTo("2340") >= 0) {
                                    canfinish = true;
                                }

                            } else if("3130".equals(parts[0]) ||
                                "3140".equals(parts[0]) ||
                                "3150".equals(parts[0])
                                    ) { //训练赛
                                int add = 20;
                                if("3130".equals(parts[0])) {
                                    add = 1;
                                } else if("3140".equals(parts[0])) {
                                    add = 2;
                                } else if("3150".equals(parts[0])) {
                                    add = 3;
                                }
                                pm = new HttpPost(OgzqURL.URL + "/MatchList/TrainMatch/TrainMatch2.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("TrainMatchload1", "1*0*0"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String s = IDUtils.execute(id, pm);
                                if(s.indexOf("inmatch") >= 0) {
                                    canfinish = false;
                                } else {
                                    String[] subs = s.split("&");
                                    int restMatch = Integer.parseInt(subs[5]);
                                    if((add + restMatch <= Integer.parseInt(subs[4])) || now.compareTo("2340") >= 0) {
                                        canfinish = true;
                                    }
                                }

                            } else if("3160".equals(parts[0])) { //欧冠魂
                                canfinish = true;
                            } else if("5404".equals(parts[0]) ||
                                "5405".equals(parts[0]) ||
                                "5406".equals(parts[0]) ||
                                "5407".equals(parts[0])
                                    ) { //欧冠之巅
                                int add = 10;
                                if(parts[0].equals("5404")) {
                                    add = 1;
                                } else if(parts[0].equals("5405")) {
                                    add = 2;
                                } else if(parts[0].equals("5406")) {
                                    add = 3;
                                } else if(parts[0].equals("5407")) {
                                    add = 5;
                                }
                                pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("OgzdGameLoad1", "0"));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String s = IDUtils.execute(id, pm);
                                int restMatch = Integer.parseInt(s.split("@")[0]) + 1;
                                if((add + restMatch <= 10) || now.compareTo("2340") >= 0) {
                                    canfinish = true;
                                }
                            }
                            if(canfinish) {
                                pm = new HttpPost(OgzqURL.URL + OgzqURL.TASK);
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("type", "1"));
                                params.add(new BasicNameValuePair("taskIndex", parts[0]));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                String s = IDUtils.execute(id, pm);
                                LOGGER.info(id + "/" + IDUtils.getNick(id) + "结束任务" + id + ":" + s);
                            }
                        }
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            try {
                Thread.sleep(1000 * 60 * 10);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
