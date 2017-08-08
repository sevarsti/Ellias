package com.saille.rxqq;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jettison.json.JSONObject;

class DoChangGui extends Thread {
    private final Logger LOGGER = Logger.getLogger(getClass());
    private String id;

    public DoChangGui(String id) {
        this.id = id;
    }

    private void changgui() {
        this.LOGGER.info(this.id + "常规赛");
        JSONObject json = (JSONObject) RxqqInstance.info.get(this.id);
        try {
            if(!json.has("Area")) {
                this.LOGGER.warn(this.id + ", key: Area没有对应的内容！");
                Iterator it = json.keys();
                while(it.hasNext()) {
                    String key = (String) it.next();
                    this.LOGGER.info(key + ": " + json.getString(key));
                }
            } else {
                Integer area = (Integer) json.get("Area");
                String url = null;
                if(area.intValue() == 1) {
                    url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
                } else if(area.intValue() == 2) {
                    url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=b790d339%2Dbe78%2D4cd2%2Da7c2%2D4da1fbba32d5&action=creatematchdaily";
                } else if(area.intValue() == 3) {
                    url = "http://s3.qiuqiu.2010.sina.com.cn/Match.do?AwayManager=5a28732e%2Dcbe5%2D442b%2D9765%2D499b96106ba6&action=creatematchdaily";
                }
                String ret = RxqqUtils.execute(this.id, url);
                JSONObject j = new JSONObject(ret);
                if(!j.has("MatchId")) {
                    this.LOGGER.warn(this.id + ", key: MatchId没有对应的内容！");
                    Iterator it = j.keys();
                    while(it.hasNext()) {
                        String key = (String) it.next();
                        this.LOGGER.info(key + ": " + json.getString(key));
                    }
                } else {
                    String matchId = j.getString("MatchId");
                    RxqqInstance.updateMatch(this.id, matchId, 1);
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + this.id + "," + sw.toString());
        }
    }

    public void run() {
        try {
            String s = RxqqUtils.myInfo(this.id);
            JSONObject info = new JSONObject(s);
            if(!info.has("MatchTime")) {
                this.LOGGER.warn(this.id + ", key: MatchTime没有对应的内容！");
                Iterator it = info.keys();
                while(it.hasNext()) {
                    String key = (String) it.next();
                    this.LOGGER.info(key + ": " + info.getString(key));
                }
            } else {
                int leftTimes = info.getInt("MatchTime");
                while(leftTimes > 0) {
                    for(int i = 0; i < leftTimes; i++) {
                        changgui();
                        this.LOGGER.info(this.id + "剩余场次：" + (leftTimes - i));
                    }
                    s = RxqqUtils.myInfo(this.id);
                    info = new JSONObject(s);
                    leftTimes = info.getInt("MatchTime");
                    this.LOGGER.info(this.id + "剩余场次：" + leftTimes);
                }
            }
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error("id: " + this.id + "," + sw.toString());
        }
    }
}