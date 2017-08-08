package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AutoLoopThread extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AutoLoopThread.class);
    private String id;
    private static int count;

    public AutoLoopThread(String id) {
        this.id = id;
        IDUtils.IDThreads.put(id, Long.valueOf(getId()));
    }

    public void run() {
        HttpPost pm = null;

        try {
            pm = new HttpPost(OgzqURL.URL + "/Activity.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadPual1", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(this.id, pm);

            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOTEAMGAME, new Date().getTime(), 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOGETTACTICPOINT, new Date().getTime(), 600 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOARENA, new Date().getTime(), 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOCHALLENGE, new Date().getTime(), 300 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOOGTRAININGMATCH, new Date().getTime(), 400 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOTRAININGMATCH, new Date().getTime(), 10 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOSEARCHPLAYER, new Date().getTime(), 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOTRAIN, new Date().getTime(), 600 * 1000);

            long now = new Date().getTime();
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DODEFAULT, now, 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOQUERYTASK, now, 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOWORLDCUP, now, 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOFUBEN, now, 300 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOSELLJIQING, now, 600 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOWORLDCUP32, now, 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DORIJKAARDCHALLENGE, now, 300 * 1000);
            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOGJXLS, now, 300 * 1000);
//            LoopUtils.getInstance().addEvent(this.id, LoopUtils.DOPELE, now, 300 * 1000);
            System.out.println(this.id + "Ω¯»Î—≠ª∑£∫" + count);
            count++;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}