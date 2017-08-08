package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import com.saille.util.CommonUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-10-26
 * Time: 18:02:32
 * To change this template use File | Settings | File Templates.
 */
public class RobNaiBa extends Thread {
    public static String[] first = new String[3];
    public static int[] firstScore = new int[3];
    public static int[] myScore = new int[3];
    public static int[] lingxian = new int[3];
    public static int[] max = new int[3]; //超过这个数量就放弃
    public static int[] currentLingxian = new int[3];
    public static int[] restTime = new int[3];
    public static String[] nick = new String[3];
    private static HttpClient[] client = new HttpClient[3];
    private Logger LOGGER = Logger.getLogger(this.getClass());
    private int robIndex = -1;
    private String id;
//    private String nick;
//    private int lingxian = 20;
    private int robType; //1: 最后几秒买一定数量礼包，不管其他；其他值：领先一定范围后不管
//    private int max; //超过这个数量就放弃
    public boolean cont = true;

    public static void main(String[] args) {
        RobNaiBa instance;
        instance = new RobNaiBa("270738347@qq.com", "zmi198678", 0, 1, 0, 2000);
        instance.start();
        instance = new RobNaiBa("super88man66@126.com", "072022021", 1, 1, 0, 2000);
        instance.start();
        instance = new RobNaiBa("leonis11@e7wan", "123678", 2, 1, 0, 2000);
        instance.start();
        System.out.println("end");
    }
    public RobNaiBa(String id, String pwd, int robIndex, int lingxian, int robType, int max) {
        this.robIndex = robIndex;
        this.id = id;
        RobNaiBa.lingxian[robIndex] = lingxian;
        this.robType = robType;
        RobNaiBa.max[robIndex] = max;
        try {
            String server = id.substring(id.indexOf("@") + 1);
            if(server.startsWith("hupu")) {
                int s = Integer.parseInt(server.substring(4));
                client[robIndex] = LoginUtils.LoginHupu(id.substring(0, id.indexOf("@")), pwd, s);
            } else if(server.startsWith("e7wan")) {
                client[robIndex] = LoginUtils.LoginE7Wan(id.substring(0, id.indexOf("@")), pwd);
            } else {
                client[robIndex] = LoginUtils.Login(id, pwd);
            }
            this.setNick();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    public void run() {
        while(true) {
            try {
                if(!cont) {
                    Thread.sleep(1000);
                }
                HttpPost pm = new HttpPost(OgzqURL.URL + "/Activity.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("NaiBaLoad1", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = execute(pm);
                if(s.equals("")) {
                    LOGGER.info("奶爸结束");
                    cont = false;
                    break;
                }
                String[] ss = s.split("@");
                int restTime = Integer.parseInt(ss[ss.length - 1]);
                RobNaiBa.restTime[robIndex] = restTime;
                LOGGER.info("剩余时间：" + restTime);
                if(restTime <= 0) {
                    LOGGER.info("剩余时间是0，结束");
                    cont = false;
                    break;
                }
                s = ss[robIndex];//1|10051|U厄齐尔|260*2|32722|皇之皇马|240*3|1084|完美时空|20^-1|31960|Tassotti|0
                s = s.substring(0, s.indexOf("^"));//1|10051|U厄齐尔|260*2|32722|皇之皇马|240*3|1084|完美时空|20
                ss = s.split("[*]");
                String[] atts = ss[0].split("[|]");
                first[robIndex] = atts[2];
                firstScore[robIndex] = Integer.parseInt(atts[3]);
                if(Integer.parseInt(atts[3]) >= RobNaiBa.max[robIndex]) {
                    LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "，当前竞拍积分（" + atts[3] + "）超过设定的上限：" + RobNaiBa.max[robIndex] + "，放弃竞拍");
                    cont = false;
                    break;
                }
                if(!atts[2].equals(RobNaiBa.nick[robIndex])) {
                    currentLingxian[robIndex] = 0;
                    if(restTime > 5) {
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "，剩余时间：" + restTime + "秒，超过5秒，不买");
                        if(restTime > 20) {
                            Thread.sleep(5000);
                        }
                    } else {
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "===奶爸第一是" + atts[2] + "，不是本号，需要买礼包！===");
                        pm = new HttpPost(OgzqURL.URL + "/Activity.aspx");
                        params = new ArrayList<NameValuePair>();
//                        att: 30204, 30205; mid: 30206, 30207; def: 30208, 30209
//                        att: 30312, 30313; mid: 30314, 30315; def: 30316, 30317
                        params.add(new BasicNameValuePair("BuyNaiBa1", (30313 + this.robIndex*2) + ""));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + ":" + parseResult(execute(pm)));
                    }
                } else if((Integer.parseInt(atts[3]) - (ss.length <= 1 ? 0 : Integer.parseInt(ss[1].split("[|]")[3]))) <= RobNaiBa.lingxian[robIndex]) {
                    currentLingxian[robIndex] = Integer.parseInt(atts[3]) - (ss.length <= 1 ? 0 : Integer.parseInt(ss[1].split("[|]")[3]));
                    if(restTime > 5) {
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "，剩余时间：" + restTime + "秒，超过5秒，不买");
                        if(restTime > 20) {
                            Thread.sleep(5000);
                        }
                    } else {
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "===奶爸领先优势不超过" + RobNaiBa.lingxian[robIndex] + "积分，需要买礼包！===");
                        pm = new HttpPost(OgzqURL.URL + "/Activity.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("BuyNaiBa1", (30313 + this.robIndex*2) + ""));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + ":" + parseResult(execute(pm)));
                    }
                } else {
                    currentLingxian[robIndex] = Integer.parseInt(atts[3]) - (ss.length <= 1 ? 0 : Integer.parseInt(ss[1].split("[|]")[3]));
                    LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "===领先优势大于" + RobNaiBa.lingxian[robIndex] + "分，等待===");
                    if(restTime > 20) {
                        Thread.sleep(5000);
                    }
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        LOGGER.info(this.id + "/" + RobNaiBa.nick[robIndex] + "===奶爸循环结束===");
    }

    private String parseResult(String s) {
        String ret;
        if(s.equals("-1") || s.equals("-2") || s.equals("-3")) {
            if("-1".equals(s)) {
                ret = ("===金币不够===");
            } else {
                ret = ("===购买失败===");
            }
        } else {
            ret = s;
        }
        return s + ":" + ret;
    }

    private String execute(HttpPost pm) {
        int count = 0;
        while(count < 3) {
            try {
                if(client == null) {
                    return "";
                }
                String ret = "";
                synchronized(client[robIndex]) {
                    pm.getParams().setParameter("Connection", "keep-alive");
                    HttpResponse response = client[robIndex].execute(pm);
                    ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
//                    pm.abort();
                    pm.releaseConnection();
                    if(ret.indexOf("ASP.NET") != -1 || ret.indexOf("未将对象引用设置到对象的实例") != -1) {

                    }
                }
                return ret;
            } catch(Exception ex) {
                System.out.println(this.id + "/" + pm.getURI().getPath() + " occurs error: " + ex.getMessage());
//                ex.printStackTrace();
                count++;
                continue;
            }
        }
        return null;
    }

    private void setNick() throws Exception {
        HttpPost pm = new HttpPost("http://f7.ogzq.xdgame.cn/ChooseRole.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        HttpResponse response = client[robIndex].execute(pm);

        String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
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
        RobNaiBa.nick[robIndex] = nick;
        pm.releaseConnection();
    }
}
