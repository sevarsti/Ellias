package com.saille.ogzq.activityLoop;

import com.saille.ogzq.dailyLoop.ParentThread;
import com.saille.ogzq.IDUtils;
import com.saille.ogzq.OgzqURL;
import org.apache.log4j.Logger;
import org.apache.http.protocol.HTTP;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-5-30
 * Time: 10:29:02
 * To change this template use File | Settings | File Templates.
 */
public class BayernThread extends ParentThread {
    private final static Logger LOGGER = Logger.getLogger(BayernThread.class);
    private static BayernThread instance;

    private BayernThread() {
    }

    public synchronized static BayernThread getInstance() {
        if(instance == null) {
            instance = new BayernThread();
        }
        return instance;
    }

    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HHmm");
        String lastDate = "";
        boolean end = false;
        List<String> ids = IDUtils.GETIDS();
        SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmm");
        for(String id : ids) {
            try {
                if(id.indexOf("txjcf") >= 0) {
                    continue;
                }
                targetTime.put(id, sdf3.parse(sdf.format(new Date()) + "1200").getTime());
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        while(!end) {
            try {
                String today = sdf.format(new Date());
                String now = sdf2.format(new Date());
                long sysTime = new Date().getTime();

                if(now.compareTo("1200") <= 0) {
                    LOGGER.info("当前时间：" + now + "，小于设定的时间：" + "1200" + "，等待5秒");
                    Thread.sleep(1000 * 5);
                    continue;
                }

                ids = IDUtils.GETIDS();
                LOGGER.info("拜仁巅峰循环" + ids.size() + "个号");
                for(String id : ids) {
                    if(id.startsWith("txjcf")) {
                        continue;
                    }
                    if(targetTime.containsKey(id) && targetTime.get(id).longValue() > sysTime) {
                        continue;
                    }
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(id, pm);
                    if(s.indexOf("inmatch") >= 0) {
                        targetTime.put(id, sysTime + 1000 * 30);
                        continue;
                    }
                    pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "2"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(id, pm);
                    if(s.length() == 0) {
                        LOGGER.info("活动还没开始或者已经结束");
                        if(now.compareTo("1759") >= 0) {
                            end = true;
                            break;
                        } else {
                            if(lastDate.equals(today)) {
                                LOGGER.info("今天踢过比赛，活动结束");
                                end = true;
                                break;
                            } else {
                                targetTime.put(id, sysTime + 1000 * 5 + 5);
                            }
                        }
                    } else if(s.indexOf("inmatch") >= 0) {
                        targetTime.put(id, sysTime + 1000 * 30);
                        continue;
                    } else if(s.indexOf("|") > 0) {
                        if(s.split("\\|")[0].equals("1")) {
                            LOGGER.info(id + "/" + IDUtils.getNick(id) + "挑战巅峰赛：" + s.split("\\|")[1]);
                            lastDate = today;
                        } else {
                            int waitSecond = Integer.parseInt(s.split("\\|")[1]);
                            targetTime.put(id, sysTime + 1000 * waitSecond + 5);
                        }
                    } else {
                        LOGGER.info(id + "/" + IDUtils.getNick(id) + "挑战巅峰赛：" + s);
                        targetTime.put(id, sysTime + 1000 * 5);
                    }
                }
                long minvalue = getWaitTime(sysTime);
                if(minvalue < 0) {
                    minvalue = 1000;
                }
                LOGGER.info("拜仁巅峰循环结束，等待" + minvalue / 1000 + "秒");
                Thread.sleep(minvalue);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        BayernThread.instance = null;
    }
}
