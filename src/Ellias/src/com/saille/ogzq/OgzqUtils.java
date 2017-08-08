package com.saille.ogzq;

import org.apache.http.NameValuePair;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.log4j.Logger;

import java.util.*;
import java.text.SimpleDateFormat;
import java.io.InputStream;

import com.saille.core.PropertyDescription;
import com.saille.util.CommonUtils;
import com.saille.util.UtilFunctions;
import com.saille.ogzq.activityLoop.DaliwanThread;
import com.saille.ogzq.activityLoop.GetGridGiftThread;
import com.saille.ogzq.activityLoop.LoginTeamgameThread;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-11-19
 * Time: 14:51:46
 * To change this template use File | Settings | File Templates.
 */
public class OgzqUtils {
    private final static Logger LOGGER = Logger.getLogger(OgzqUtils.class);

    @PropertyDescription(desc = "免费疯狂砸蛋")
    public static String crazyEggFree() throws Exception {
//        StringBuffer sb = new StringBuffer();
//        sb.append("<table id=\"info\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\">");
//        sb.append("<tr class=\"head\"><td>email</td><td>nick</td><td>结果</td></tr>");
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "结果"});
        for(int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            System.out.println("免费疯狂砸蛋:" + i + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/CrazyEggs2.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
//            sb.append(s).append("--------").append(id).append("============<br/>");
//            sb.append("<tr class=\"row").append(i % 2 + 1).append("\"><td>").append(id).append("</td><td>").append(IDUtils.getNick(id)).append("</td>");
            s = s.split("╋")[0];
            if(s.equals("0")) {
                pm = new HttpPost(OgzqURL.URL + "/CrazyEggs2.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                params.add(new BasicNameValuePair("index", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
                tables.add(new String[]{id, IDUtils.getNick(id), s});
            } else {
                tables.add(new String[]{id, IDUtils.getNick(id), "-"});
            }
//            sb.append("</tr>");
        }
//        sb.append("</table>");
        return UtilFunctions.convertList2Table(tables, new boolean[]{false, false, false});
    }

    @PropertyDescription(desc = "每日签到")
    public static String qiandao() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DAY_OF_MONTH);
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Check_In.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/Check_In.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("index", ""+d));
            params.add(new BasicNameValuePair("mode", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append(": ").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return sb.toString();
    }

    //油箱=0时使用增加50
    @PropertyDescription(desc = "使用邮箱")
    public static String useYouxiang() throws Exception {
        StringBuffer sb = new StringBuffer("done");
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            String s = IDUtils.IDInfos.get(id).get("search");
            s = s.substring(0, s.indexOf("/"));
            if(s.equals("0")) {
                List<Map<String, String>> list = OperationUtils.listBags(id, "0");
                for(Map<String, String> m : list) {
                    if(m.get("name").equals("大油桶")) {
                        OperationUtils.useItem(id, m.get("id"));
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "连锁店")
    public static String liansuo() throws Exception {
//        StringBuffer sb = new StringBuffer();
        HttpPost pm;
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "1", "2", "3", "4", "5", "6", "7"});
        for(String id : ids) {
            String[] table = new String[9];
            System.out.println("连锁店:" + ids.indexOf(id) + "/" + ids.size() + ": " + id);
            pm = new HttpPost(OgzqURL.URL + "/ChainStore.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            table[0] = id;
            table[1] = IDUtils.getNick(id);
            String s = IDUtils.execute(id, pm);
            if(s.indexOf("╋") >= 0) {
                s = s.substring(0, s.indexOf("╋"));
                s = s.split("\\@")[0];
                String[] parts = s.split("\\*");
                for(int i = 0; i < 7; i++) {
                    table[i+2] = parts[i].split("\\|")[1];
                }
            }
            tables.add(table);
//            sb.append("id:").append(id).append(":").append(IDUtils.execute(id, pm)).append("<br/>");
            pm = new HttpPost(OgzqURL.URL + "/ChainStore.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            params.add(new BasicNameValuePair("index", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            sb.append(id).append("------").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return UtilFunctions.convertList2Table(tables, new boolean[]{false,false,false,false,false,false,false,false,false} );
//        return sb.toString();
    }

    //球员集中营
    @PropertyDescription(desc = "集中营")
    public static String jizhongying() throws Exception {
        boolean worldcup = false;
        String url = OgzqURL.URL + "/PlayersCamp" + (worldcup ? "_WorldCup" : "") + ".aspx";
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            System.out.println("集中营:" + ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(url);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(id, pm); //0*0*0@5*3*1@50
            ret = ret.substring(ret.indexOf("@") + 1);
            String rest = ret.substring(ret.indexOf("@") + 1);
            ret = ret.substring(0, ret.indexOf("@"));
            String times = ret;
            if(!times.equals("0*0*0")) {
                times = "<span style=\"color:red;\">" + ret + "</span>";
            }
            sb.append(times).append("*").append(rest).append("-----").append(id).append("/").append(IDUtils.getNick(id)).append("<br/>");
            String[] ss = ret.split("[*]");
//            single:
            for(int i = ss.length - 1; i >= 0; i--) {
                int count = Integer.parseInt(ss[i]);
                while(count > 0) {
                    count = count - 1;
//                if(Integer.parseInt(ss[i]) > 0) {
                    pm = new HttpPost(url);
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "2"));
                    params.add(new BasicNameValuePair("index", (i + 1) + ""));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String result = IDUtils.execute(id, pm);
                    if(!result.equals("1")) {
                        break;
                    }
                }
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "贝克汉姆个人礼包")
    public static String getPersonalBeckhamGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        HttpPost pm;
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            pm = new HttpPost(OgzqURL.URL + "/Beckham_LevelUp.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/Beckham_LevelUp.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                sb.append("id:").append(id).append(", i=").append(i).append("---").append(IDUtils.execute(id, pm)).append("<br/>");
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "贝克汉姆球会礼包")
    public static String getBeckhamClubGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/BeckhamClub.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            pm = new HttpPost(OgzqURL.URL + "/BeckhamClub.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ShowReward", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/BeckhamClub.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = (IDUtils.execute(id, pm));
                sb.append(s).append("/");
            }
            sb.append("<br/>");
        }
        return sb.toString();
    }

    //领巴蒂个人战礼包
    @PropertyDescription(desc = "巴蒂个人礼包")
    public static String getBatiGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Batty_LevelUp.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 0; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/Batty_LevelUp.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", ""+i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                sb.append(IDUtils.execute(id, pm)).append("/");
            }
            sb.append("<br/>");
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "巴蒂球会礼包")
    public static String getBatiClubGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/BattyClub.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            pm = new HttpPost(OgzqURL.URL + "/BattyClub.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ShowReward", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/BattyClub.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = (IDUtils.execute(id, pm));
                sb.append(s).append("/");
            }
            sb.append("<br/>");
        }
        return sb.toString();
    }

    //领格子铺
    public static String getGridGift() throws Exception {
        GetGridGiftThread.getInstance().start();
        return "get grid gift thread started";
    }

    //连连看
    public static String lianliankan() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        for(int i = 0; i < 5; i++) {
            for(String id : ids) {
                HttpPost pm = new HttpPost(OgzqURL.URL + "/DuiDuiBao.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "4"));
                params.add(new BasicNameValuePair("index", i + ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                System.out.println(id + ", index=" + i + ": " + IDUtils.execute(id, pm));
            }
        }
        DuiduibaoThread t = DuiduibaoThread.getInstance();
        t.start();
        return "done";
    }

    //卖lv5道具
    public static String doSellLv5() throws Exception {
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
        Calendar c = Calendar.getInstance();
        List<String> ids = IDUtils.GETIDS();
        int count = 0;
        boolean cont = false;
        while(true) {
            try {
                if(count > 10000000) {
                    break;
                }
                if(c.get(Calendar.SECOND) == 0) {
                    System.out.println("wait sell lv5..." + c.getTime());
                }
                c = Calendar.getInstance();
                if(cont || Integer.parseInt(sdf.format(c.getTime())) >= 2358) {
                    cont = true;
                    for(String id : ids) {
                        if(id.startsWith("robot")) {
                            OperationUtils.doSellLv5(id);
                        }
                    }
                    count++;
                } else {
                    Thread.sleep(1000);
                }
            } catch(Exception ex) {
            }
        }
        return sb.toString();
    }

    public static String yaguanqiandao() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append(": ").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return sb.toString();
    }

    public static String yaguanGet() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "4"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append(": ").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return sb.toString();
    }

    public static String yaguanDuihuan() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "6"));
            params.add(new BasicNameValuePair("index", "3"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append(": ").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return sb.toString();
    }

    public static String yaguanVote() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append("--").append(IDUtils.execute(id, pm)).append("<br/>");

            pm = new HttpPost(OgzqURL.URL + "/AFC_CountDown.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "6"));
            params.add(new BasicNameValuePair("index", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(id).append("--").append(IDUtils.execute(id, pm)).append("<br/>");
        }
        return sb.toString();
    }

    public static String buyCoinDaoju() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Prop/Store.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("UpGameCoinAndMyItem1", "1101*" + 1 + "*" + 25 + "*" + 2));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(IDUtils.execute(id, pm) + "<br/>");

            pm = new HttpPost(OgzqURL.URL + "/Prop/Store.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("UpGameCoinAndMyItem1", "1301*" + 1 + "*" + 100 + "*" + 1));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            sb.append(IDUtils.execute(id, pm) + "<br/>");
        }
        return sb.toString();
    }

    public static String getGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/GiftBox.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadGiftBox1", ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] gifts = s.split("[|]");
            for(String g : gifts) {
                if(g.indexOf("objGiftBox.") == -1) {
                    continue;
                }
                g = g.substring(g.indexOf("objGiftBox.") + "objGiftBox.".length());
                g = g.substring(0, g.indexOf(")"));
                if(g.startsWith("GiftBoxGet4")) {
                    g = g.substring(g.indexOf("(") + 1);
                    String[] pp = g.split(",");
                    String name = pp[1].substring(1, pp[1].length() - 1);
                    String code = pp[3].substring(1, pp[3].length() - 1);
                    pm = new HttpPost(OgzqURL.URL + "/GiftBox.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("GiftBoxGet4", name + "*" + code));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    sb.append(id + "===" + IDUtils.execute(id, pm) + "<br/>");
                } else if(g.startsWith("GiftBoxGet2")) {
                    g = g.substring(g.indexOf("(") + 1);
                    String[] pp = g.split(",");
                    String name = pp[0].substring(1, pp[0].length() - 1);
                    String code = pp[2].substring(1, pp[2].length() - 1);
                    pm = new HttpPost(OgzqURL.URL + "/GiftBox.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("GiftBoxGet2", name + "*" + code));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    sb.append(id + "===" + IDUtils.execute(id, pm) + "<br/>");
                }
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "大折扣")
    public static String bigDiscount() throws Exception {
//        StringBuffer sb = new StringBuffer();
        List<String[]> ret = new ArrayList<String[]>();
        ret.add(new String[]{"email", "nick", "0", "0.1", "1", "5"});
        List<String> ids = IDUtils.GETIDS();
//        sb.append("<table bgcolor=\"black\" id=\"info\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\"><tr class=\"head\">" +
//                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 0, false)\">email</th>" +
//                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 1, false)\">nick</th>")
//                .append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 2, true)\">0</th>" +
//                        "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 3, true)\">0.1</th>" +
//                        "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 4, true)\">1</th>" +
//                        "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 5, true)\">5</th>");
        for(int j = 0; j < ids.size(); j++) {
            String id = ids.get(j);
            LOGGER.info(j + "/" + ids.size());
            HttpPost pm = new HttpPost(OgzqURL.URL + "/BigDiscount.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] pp = s.split("@");

            //免费翻牌
            for(int i = 0; i < (Integer.parseInt(pp[0]) - 20); i++) {
                pm = new HttpPost(OgzqURL.URL + "/BigDiscount.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(id, pm);
            }

            //免费拿7合同
            pm = new HttpPost(OgzqURL.URL + "/BigDiscount.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            pp = s.split("@");
            String free = pp[1].split("[|]")[0];
//            sb.append("<tr align=\"right\" class=\"row").append(j % 2 + 1).append("\"><td>").append(id).append("</td><td>").append(IDUtils.getNick(id)).append("</td>");
            String[] discounts = pp[1].split("\\|");
            ret.add(new String[]{id, IDUtils.getNick(id), discounts[0], discounts[1], discounts[2], discounts[3]});
//            for(String d : discounts) {
//                sb.append("<td>").append(d).append("</td>");
//            }
//            sb.append("</tr>");
//            sb.append(pp[1] + "---" + id + "/" + IDUtils.getNick(id) + "<br/>");
            int f = Integer.parseInt(free);
            for(int i = 0; i < f; i++) {
//                pm = new HttpPost(OgzqURL.URL + "/BigDiscount.aspx");
//                params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("type", "3"));
//                params.add(new BasicNameValuePair("ItemCode", "1107"));
//                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                IDUtils.execute(id, pm);
            }
        }
        return UtilFunctions.convertList2Table(ret, new boolean[]{false, false, true, true, true, true});
    }

    @PropertyDescription(desc = "刷刷乐")
    public static String shuashuale() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size());
            boolean isFree = true;
            while(isFree) {
                HttpPost pm = new HttpPost(OgzqURL.URL + "/ShuaShuaLe.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("load", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String ret = IDUtils.execute(id, pm);
/*
            6015|球员经验勿000点|800|128|2*
            2803|高级教练合同3级|90 |0  |0*
            1905|油箱           |100|0  |0*
            1704|战靴4级        |800|128|2*
            1905|油箱           |100|56 |6*
            1704|战靴4级        |800|368|5
*/
                String itemStr = ret.substring(0, ret.indexOf("@"));
                String freeRefresh = ret.substring(ret.indexOf("@") + 1); //Leen|战袍4縿YI兰帕德|高级教练合同3縿生詹姆士|球员经验勿000瀿U厄齐尔|高级教练合同3縿U厄齐尔|高级教练合同3縿申花是冠军|球员经验勿000瀿申花是冠军|战袍4级@10@0@1|1|1|1|1|1
                freeRefresh = freeRefresh.substring(freeRefresh.indexOf("@") + 1); //10@0@1|1|1|1|1|1
                freeRefresh = freeRefresh.substring(freeRefresh.indexOf("@") + 1); //0@1|1|1|1|1|1
                isFree = freeRefresh.substring(0, 1).equals("0"); //0: 免费，1：花金币
                String canBuyStr = freeRefresh.substring(freeRefresh.indexOf("@") + 1); //1|1|1|1|1|1
                String[] canBuy = canBuyStr.substring(0, canBuyStr.indexOf("╋")).split("[|]");
                String[] items = itemStr.split("[*]");
                for(int i = 0; i < items.length; i++) {
                    String item = items[i];
                    if(item.split("[|]")[4].equals("0") && canBuy[i].equals("1")) {
                        sb.append("id: " + id + ", item: " + item);
                        pm = new HttpPost(OgzqURL.URL + "/ShuaShuaLe.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("Buy", item.split("[|]")[0] + "*0*" + i));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        IDUtils.execute(id, pm);
                    }
                }
                if(isFree) {
                    pm = new HttpPost(OgzqURL.URL + "/ShuaShuaLe.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("refresh", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    sb.append(id + "免费刷新：" + IDUtils.execute(id, pm) + "<br/>");
                } else {
                    sb.append(id + "不能免费刷新，跳过" + "<br/>");
                }
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "欧冠对阵")
    public static String viewChampionCup() throws Exception {
        String id = IDUtils.GETIDS().get(0);
        HttpPost pm = new HttpPost(OgzqURL.URL + "/ChampionCup.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "6"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(id, pm);
        String[][] teams = new String[16][];
        for(int i = 0; i < 16; i++) {
            String s = ret.substring(0, ret.indexOf("*"));
            ret = ret.substring(ret.indexOf("*") + 1);
            String[] ss = s.split("[|]");
            teams[i] = new String[]{ss[0], ss[1]};
        }
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"frame\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        for(int i = 0; i < 8; i++) {
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\">");
            sb.append("<td>").append(teams[i][0]).append("-").append(teams[i][1]).append("</td>");
            sb.append("<td>").append(teams[i + 8][0]).append("-").append(teams[i + 8][1]).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    public static String voteChampionCup(String param) throws Exception {
        if(param == null) {
            //todo
        }
        String[] teams = param.split(",");
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size());
            for(int i = 0; i < teams.length; i++) {
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.CHAMPIONCUP);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "3"));
                params.add(new BasicNameValuePair("starClubID", teams[i]));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(id, pm);
            }
        }
        return "done";
    }

    public static String loadPlayer() throws Exception {
        IDUtils.loadPlayer();
        return "done";
    }

    @PropertyDescription(desc = "使用道具")
    public static String useItem() throws Exception {
//        StringBuffer sb = new StringBuffer();
        String ret = "";
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "result"});
        List<String> ids = IDUtils.GETIDS();
//        List<Map<String, String>> allItems = new ArrayList<Map<String, String>>();
        int totalcount = 0;
        for (String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String[] details = new String[3];
            details[0] = id;
            details[1] = IDUtils.getNick(id);
            String d = "";
            List<Map<String, String>> items = OperationUtils.listBags(id, "0");
//            allItems.addAll(items);
            for (Map<String, String> item : items) {
                totalcount++;
                if(item.get("name").startsWith("训练卡") ||
                        item.get("name").startsWith("战术积分卡") ||
                        item.get("name").indexOf("装备套") >= 0 ||
                        item.get("name").startsWith("欧联大赢家包") ||
                        item.get("name").indexOf("激情卡") >= 0 ||
                        item.get("name").indexOf("级礼包") >= 0 ||
                        item.get("name").startsWith("欧冠鸿运包") ||
                        item.get("name").startsWith("每日登录礼包") ||
                        item.get("name").startsWith("限量积分卡") ||
                        item.get("name").startsWith("突破积分") ||
                        item.get("name").startsWith("圣诞小礼包") ||
                        item.get("name").startsWith("圣诞大礼包") ||
                        item.get("name").indexOf("世界杯积分卡") >= 0 ||
                        item.get("name").indexOf("球员成长积分卡") >= 0 ||
                        item.get("name").indexOf("签名积分卡") >= 0 ||
                        item.get("name").startsWith("签到") ||
                        item.get("name").startsWith("中礼包") ||
                        item.get("name").startsWith("小礼包") ||
                        item.get("name").startsWith("冠军积分卡") ||
                        item.get("name").startsWith("银币兑换包")) {
                    int n = Integer.parseInt(item.get("number"));
                    d = d + item.get("name") + ":" + n + "<br/>";
//                    sb.append(item.get("email") + "/" + IDUtils.getNick(item.get("email")) + "使用：" + item.get("name") + "，" + n + "个<br/>\r\n");
                    System.out.println(item.get("email") + "use: " + items.indexOf(item) + "/" + items.size() + ":" + item.get("name") + ":" + n);
                    for(int j = 0; j < n; j++) {
                        OperationUtils.useItem(item.get("email"), item.get("id"));
                    }
                }
            }
            details[2] = d;
            tables.add(details);
        }
        ret = "一共有" + totalcount + "个道具<br/>\r\n";
        return ret + UtilFunctions.convertList2Table(tables, new boolean[]{false, false, false});
    }

    @PropertyDescription(desc = "卡福个人礼包")
    public static String getPersonalCafuGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        HttpPost pm;
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            pm = new HttpPost(OgzqURL.URL + "/Cafu_LevelUp.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/Cafu_LevelUp.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                sb.append("id:").append(id).append(", i=").append(i).append("=======").append(IDUtils.execute(id, pm)).append("<br/>");
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "卡福球会礼包")
    public static String getCafuClubGift() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/CafuClub.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            pm = new HttpPost(OgzqURL.URL + "/BeckhamClub.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("ShowReward", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            IDUtils.execute(id, pm);
            for(int i = 1; i <= 20; i++) {
                pm = new HttpPost(OgzqURL.URL + "/CafuClub.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("getReward", "" + i));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String s = (IDUtils.execute(id, pm));
                sb.append(s).append("/");
            }
            sb.append("<br/>");
        }
        return sb.toString();
    }

    public static String fishBlow() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Fish_Blow.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            if(s.indexOf("╋") >= 0) {
                s = s.substring(0, s.indexOf("╋"));
            }
            int times = Integer.parseInt(s.substring(s.lastIndexOf("@") + 1));
            for(int i = 0; i < times; i++) {
                pm = new HttpPost(OgzqURL.URL + "/Fish_Blow.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);

                s = s.substring(s.lastIndexOf("|") + 1); //type
                pm = new HttpPost(OgzqURL.URL + "/Fish_Blow.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "4"));
                params.add(new BasicNameValuePair("FishType", s));
                params.add(new BasicNameValuePair("FishSize", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
            }
            sb.append("<br/>");
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "联盟签到")
    public static String leagueSign() throws Exception {
        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
//        Random r = new Random();
        int qd = 1;
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);

            HttpPost pm = new HttpPost(OgzqURL.URL + "/LeagueSign.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/LeagueSign.aspx");
            params = new ArrayList<NameValuePair>();
            if(id.equals("sevarsti@sina.com")) {
                params.add(new BasicNameValuePair("GuessNum", "" + 0));
            } else {
                params.add(new BasicNameValuePair("GuessNum", "" + qd));
                qd++;
                if(qd > 9) {
                    qd = 1;
                }
            }
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            sb.append(qd).append("<br/>");
        }

        //日常签到
        for(String id : ids) {
            LOGGER.info(ids.indexOf(id) + "/" + ids.size() + ": " + id);
            sb.append(id).append(":");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "qianDao"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            sb.append(ids.indexOf(id)).append("/").append(ids.size()).append(": ").append(s).append("<br/>");

            if(str.indexOf("╋") >= 0) {
                str = str.substring(0, str.indexOf("╋"));
            }
            if(str.equals("")) {
                sb.append("no result<br/>");
                continue;
            }
            String needReset = str.split("@")[1];
            str = str.split("@")[2];
            //result = "1.26|1|0*1.27|1|0*1.28|1|1*1.29|0|0*1.30|0|0*1.31|0|0*2.1|0|0@1@2天奖励|1*4天奖励|0*7天奖励|0";
            for(int i = 0; i < 3; i++) { //2天奖励|1*4天奖励|0*7天奖励|0";
                if(str.split("\\*")[i].split("\\|")[1].equals("1")) {
                    pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("request", "lingQu"));
                    params.add(new BasicNameValuePair("index", "" + (i + 1)));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(id, pm);
                    sb.append("领奖").append(i).append(":").append(s).append("<br/>");
                    if(i == 2) { //

                    }
                }
            }
            if("0".equals(needReset)) {
                pm = new HttpPost(OgzqURL.URL + "/DailySignIn.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "reset"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
                sb.append("重置：").append(s).append("<br/>");
            }
        }
        return sb.toString();
    }

    @PropertyDescription(desc = "世俱小组投票")
    public static String worldclubGroupVote() throws Exception {
        List<String> keys = IDUtils.GETIDS();

        HttpPost pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(keys.get(0), pm);
        s = s.substring(s.indexOf("@") + 1);
        String[] teams = s.split("[*]"); //|-1|-247|黯之风翼|78|56806|0|0|1|0|npc59.png
        List<String> teamIds = new ArrayList<String>();
        List<String[]> restTeams = new ArrayList<String[]>();
        for(String t : teams) {
            String[] parts = t.split("[|]");
            restTeams.add(new String[]{parts[2], parts[6]});
        }
        while(restTeams.size() > 0) {
            String currentTeamId = null;
            int currentVote = -1;
            int currentIndex = -1;
            for(int i = 0; i < restTeams.size(); i++) {
                if(Integer.parseInt(restTeams.get(i)[1]) > currentVote) {
                    currentVote = Integer.parseInt(restTeams.get(i)[1]);
                    currentTeamId = restTeams.get(i)[0];
                    currentIndex = i;
                }
            }
            restTeams.remove(currentIndex);
            teamIds.add(currentTeamId);
        }
        LOGGER.info("投票顺序：" + teamIds);
        for(int ii = 0; ii < keys.size(); ii++) {
            String k = keys.get(ii);
            LOGGER.info(ii + "/" + keys.size() + ": " + k);
            pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(k, pm);
            ret = ret.substring(0, ret.indexOf("|"));
            LOGGER.info(k + "--" + IDUtils.getNick(k) + "---" + ret + "<br/>");
            int count = Integer.parseInt(ret);
            if(count <= 0) {
                continue;
            }
            int use = 0;
            for(int i = 0; i < teamIds.size(); i++) {
                if(count > 0) {
                    if(count > 1000) {
                        use = 1000;
                    } else {
                        use = count;
                    }
                    count = count - use;
                    pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "3"));
                    params.add(new BasicNameValuePair("starClubID", teamIds.get(i)));
                    params.add(new BasicNameValuePair("bettCount", String.valueOf(use)));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(k, pm);
                    if(s.equals("-2")) {
                        count += use;
                    }
                }
            }
        }
        return "done";
    }

    @PropertyDescription(desc = "世俱淘汰投票")
    public static String worldclubPlayOffVote() throws Exception {
        List<String> keys = IDUtils.GETIDS();

        HttpPost pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "2"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(keys.get(0), pm);
        s = s.substring(s.indexOf("@") + 1);
        String[] teams = s.split("[*]"); //|-1|-247|黯之风翼|78|56806|0|0|1|0|npc59.png
        List<String> teamIds = new ArrayList<String>();
        List<String[]> restTeams = new ArrayList<String[]>();
        for(String t : teams) {
            String[] parts = t.split("[|]");
            restTeams.add(new String[]{parts[2], parts[6]});
        }
        while(restTeams.size() > 0) {
            String currentTeamId = null;
            int currentVote = -1;
            int currentIndex = -1;
            for(int i = 0; i < restTeams.size(); i++) {
                if(Integer.parseInt(restTeams.get(i)[1]) > currentVote) {
                    currentVote = Integer.parseInt(restTeams.get(i)[1]);
                    currentTeamId = restTeams.get(i)[0];
                    currentIndex = i;
                }
            }
            restTeams.remove(currentIndex);
            teamIds.add(currentTeamId);
        }
        LOGGER.info("投票顺序：" + teamIds);
        for(int ii = 0; ii < keys.size(); ii++) {
            String k = keys.get(ii);
            LOGGER.info(ii + "/" + keys.size() + ": " + k);
            pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(k, pm);
            ret = ret.substring(0, ret.indexOf("|"));
            LOGGER.info(k + "--" + IDUtils.getNick(k) + "---" + ret + "<br/>");
            int count = Integer.parseInt(ret) - 32000;
            if(count <= 0) {
                continue;
            }
            int use = 0;
            for(int i = 0; i < teamIds.size(); i++) {
                if(count > 0) {
                    if(count > 5000) {
                        use = 5000;
                    } else {
                        use = count;
                    }
                    count = count - use;
                    pm = new HttpPost(OgzqURL.URL + "/ChampionCup3.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "3"));
                    params.add(new BasicNameValuePair("starClubID", teamIds.get(i)));
                    params.add(new BasicNameValuePair("bettCount", String.valueOf(use)));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(k, pm);
                    if(s.equals("-2")) {
                        count += use;
                    }
                }
            }
        }
        return "done";
    }

    @PropertyDescription(desc = "联盟冠军杯竞猜")
    public static String leagueChampionVote() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        HttpPost pm = new HttpPost(OgzqURL.URL + "/OGLM.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Quiz1", "-1*-1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(ids.get(0), pm);
        String he = s.split("@")[0];
        String lun = s.split("@")[1];
        String[] subs = s.split("@")[7].split("&");//-1020|大脸喵|85|罗马*-685|啊123456|85|马德里竞技*-1*-1*-1*0*0*0*2508
        String[][] teams = new String[subs.length][];
        for(int i = 0; i < subs.length; i++) {
            String[] parts = subs[i].split("[*]");
            teams[i] = new String[5];
            teams[i][0] = parts[0].split("[|]")[0];
            teams[i][2] = parts[1].split("[|]")[0];
            teams[i][4] = parts[8];
            pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadTeam9", teams[i][0]));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(ids.get(0), pm);
            teams[i][1] = s.split("#")[4];

            pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadTeam9", teams[i][2]));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(ids.get(0), pm);
            teams[i][3] = s.split("#")[4];
        }
        for(int j = 0; j < ids.size(); j++) {
            String id = ids.get(j);
            System.out.println(j + "/" + ids.size() + ": " + id);
            for(int i = 0; i < teams.length; i++) {
                pm = new HttpPost(OgzqURL.URL + "/OGLM.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("JcOk1", "0*" + teams[i][4] + "*" + he + "*" + lun + "*" + (Integer.parseInt(teams[i][1]) > Integer.parseInt(teams[i][3]) ? teams[i][0] : teams[i][2])));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
            }
        }
        return "done";
    }

    
    @PropertyDescription(desc = "玩个球状态")
    public static String playBallStatus() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"frame\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr class=\"head\"><th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 0, false)\">email</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 1, false)\">nick</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 2, true)\">silver</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 3, true)\">count</th></tr>");
        for(int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            System.out.println(i + "/" + ids.size() + ":" + id);
            String silver = IDUtils.IDInfos.get(id).get("silver");
            String nick = IDUtils.getNick(id);

            HttpPost pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load_ball", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] rows = s.substring(0, s.indexOf("@")).split("[*]");
            int count = 0;
            for(String r : rows) {
                if(r.indexOf("2") >= 0) {
                    count++;
                }
            }
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\"><td>").append(id).append("</td><td>").append(nick).append("</td><td>").append(silver).append("</td><td>").append(count).append("</td></tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    @PropertyDescription(desc = "玩个球")
    public static String playBall(String iid) throws Exception {
//        StringBuffer sb = new StringBuffer();
//        List<String> ids = new ArrayList<String>();
        String[] emails;
        if("all".equalsIgnoreCase(iid)) {
            List<String> ids = IDUtils.GETIDS();
            List<String> target = new ArrayList<String>();
            for(String id : ids) {
                int silver = Integer.parseInt(IDUtils.IDInfos.get(id).get("silver"));
                boolean add = false;
                if(id.startsWith("txjcf")) {
                    if(silver >= 2000000) {
                        add = true;
                    }
                } else {
                    if(silver >= 5000000) {
                        add = true;
                    }
                }
                if(add) {
                    target.add(id);
                }
            }
            emails = new String[target.size()];
            target.toArray(emails);
        } else {
            emails = iid.split(",");
        }
        List<String[]> retList = new ArrayList<String[]>();
        retList.add(new String[]{"email", "昵称", "银币", "数量"});
        for(String id : emails) {
            boolean found = IDUtils.GETIDS().contains(id);
            if(!found) {
                retList.add(new String[]{id, "?", "0", "0"});
//                sb.append("账号").append(id).append("没有登录<br/>");
            }
            String nick = IDUtils.getNick(id);
            int silverCount = 0, ballCount = 0;
            HttpPost pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load_ball", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            //"0|0|0|0|1|0|0|0|0*0|2|0|0|1|0|0|0|0*0|0|0|0|2|1|0|0|0*0|0|0|0|0|0|0|0|1*0|0|0|0|1|0|0|0|0*0|0|0|0|1|0|0|0|0*2|0|0|0|1|0|0|0|0*0|0|0|0|1|0|2|0|0*0|0|0|0|1|0|0|0|0";
            //0: 没翻, 1: 空, 2: 球
            String[] rows = s.substring(0, s.indexOf("@")).split("[*]");
            for(int xx = 0; xx < 9; xx++) {
                if(rows[xx].indexOf("2") >= 0) { //已经翻到球
                    continue;
                }
                String[] cols = rows[xx].split("[|]");
                for(int yy = 0; yy < 9; yy++) {
                    if(!"0".equals(cols[yy])) {
                        continue;
                    }
                    try {
                        pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("position_x_ball", "" + xx));
                        params.add(new BasicNameValuePair("position_y_ball", "" + yy));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        String ret = IDUtils.execute(id, pm);
                        if(!ret.startsWith("-1")) {
                            //   res = "1|700|sadsad|asd|2";
                            //res = "1：可以翻开，-1：钱不足|花费的金币银币|type（金币还是银币）"
                            String type = ret.split("[|]")[2]; //0:金币, 1:银币
//                        if("1".equals(type)) {
                                pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("x_position_ball", "" + xx));
                                params.add(new BasicNameValuePair("y_position_ball", "" + yy));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(id, pm);
                                System.out.println(id + "x/y: " + xx + "/" + yy + ":" + ret);
                                silverCount += Integer.parseInt(ret.split("[|]")[3]);
                                if("2".equals(ret.split("[|]")[0])) { //翻到球
                                    ballCount++;
                                    break;
                                }
//                        }
                        }
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            retList.add(new String[]{id, IDUtils.getNick(id), silverCount + "", ballCount + ""});
//            sb.append(IDUtils.getNick(id) + "共花费" + silverCount + "银币，翻到" + ballCount + "个球").append("<br/>");
        }
        return UtilFunctions.convertList2Table(retList, new boolean[]{false, false, true, true});
//        return sb.toString();
    }

    @PropertyDescription(desc = "马上乐和")
    public static String mashanglehe() throws Exception {
//        StringBuffer sb = new StringBuffer();
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "result"});
        for(int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            String[] r = new String[3];
            r[0] = id;
            r[1] = IDUtils.getNick(id);
            r[2] = "";
            System.out.println("马上乐和" + i + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/HorseLeHe.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            int coinCost = Integer.parseInt(s.split("@")[4]);
            //16的道具以“*”连接 @ 笑脸个数*酷脸个数*哭脸个数 @ 10金兑换档平台剩余次数|自己剩余次数*100金兑换档平台剩余次数|自己剩余次数*1000金兑换档平台剩余次数|自己剩余次数  @ 上次抽中的奖励的index(1-16；还没开始返回0；)  @ 本次抽奖花费银币数目 @ 是否已兑换大奖（1已兑换）
            //		道具格式：ItemCode|ItemName(笑脸-1，酷脸-2，哭脸-3)
            //result = "-1|笑脸*1105|合同5级*-2|酷脸*1206|汽车6级*-3|哭脸*1304|游艇4级*-1|笑脸*1105|合同5级*-2|酷脸*1206|汽车6级*-3|哭脸*1304|游艇4级*-1|笑脸*1105|合同5级*-2|酷脸*1206|汽车6级@20*30*40@2000|200*500|50*100|10@4@0@6688╋11月29日10:00至12月09日24:00";
            if(coinCost <= 0) {
                pm = new HttpPost(OgzqURL.URL + "/HorseLeHe.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "2"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
//                sb.append(id).append("/").append(IDUtils.getNick(id)).append(": ").append(s).append("<br/>");
                r[2] = s.split("\\|")[2];
            }
            tables.add(r);
        }
        return UtilFunctions.convertList2Table(tables, new boolean[]{false, false, false});
//        return sb.toString();
    }

    public static String viewTeamAbility(String param) throws Exception {
        List<String> ids = IDUtils.GETIDS();
        int i = new Random().nextInt(ids.size());
        String email = ids.get(i);

        HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("LoadTeam1", param));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(email, pm);

        String[] parts = ret.split("#");

        return parts[0] + "-" + parts[24];
//
//        String ability = ret.substring(0, ret.lastIndexOf("[" + club + "]"));
//        ability = ability.substring(0, ability.lastIndexOf(".png"));
//        ability = ability.substring(0, ability.lastIndexOf("#"));
//        ability = ability.substring(ability.lastIndexOf("#") + 1);
//        ret = ret.substring(ret.indexOf("#") + 1);
//        ret = ret.substring(ret.indexOf("#") + 1);
//        ret = ret.substring(ret.indexOf("#") + 1);
//        ret = ret.substring(0, ret.indexOf("#"));
    }

    public static String viewBuff(String email) throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TaskInfo.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "4"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(email, pm);
        s = s.replaceAll("[*]", "<br/>");
        return s;
    }

    public static String openBox() throws Exception {
        List<String> ids = IDUtils.GETIDS();
//        StringBuffer sb = new StringBuffer();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "银箱子", "银钥匙", "金箱子", "金钥匙", "白金箱子", "白金钥匙", "结果"});
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String[] items = new String[9];
            items[0] = id;
            items[1] = IDUtils.getNick(id);
//            sb.append(id).append(IDUtils.getNick(id)).append("<br/>");
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "3"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
            str = str.split("@")[0];
            String[] bags = str.split("\\|");
            int goldkey = 0, goldbox = 0, silverkey = 0, silverbox = 0, ptkey = 0, ptbox = 0;
            for(String b : bags) {
                String[] parts = b.split("\\*");
                if(parts[11].equals("5002")) { //银箱子
                    silverbox = Integer.parseInt(parts[7]);
                } else if(parts[11].equals("5003")) { //银钥匙
                    silverkey = Integer.parseInt(parts[7]);
                } else if(parts[11].equals("5004")) { //金箱子
                    goldbox = Integer.parseInt(parts[7]);
                } else if(parts[11].equals("5005")) { //金钥匙
                    goldkey = Integer.parseInt(parts[7]);
                } else if(parts[11].equals("5007")) { //白金箱子
                    ptbox = Integer.parseInt(parts[7]);
                } else if(parts[11].equals("5006")) { //白金钥匙
                    ptkey = Integer.parseInt(parts[7]);
                }
            }
//            String msg = "有银箱子" + silverbox + "个，银钥匙" + silverkey + "个，金箱子" + goldbox + "个，金钥匙" + goldkey + "个，白金箱子" + ptbox + "个，白金钥匙" + ptkey + "个";
            items[2] = silverbox + "";
            items[3] = silverkey + "";
            items[4] = goldbox + "";
            items[5] = goldkey + "";
            items[6] = ptbox + "";
            items[7] = ptkey + "";
//            System.out.println(msg);
//            sb.append(msg).append("<br/>");
            String open = "";
            for(int i = 0; i < Math.min(silverkey, silverbox) - 1; i++) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "15"));
                params.add(new BasicNameValuePair("itemCode", "5002"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                str = IDUtils.execute(id, pm);
                open += str + "<br/>";
//                sb.append(str).append("<br/>");
            }
            for(int i = 0; i < Math.min(goldkey, goldbox) - 1; i++) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "15"));
                params.add(new BasicNameValuePair("itemCode", "5004"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                str = IDUtils.execute(id, pm);
                open += str + "<br/>";
//                sb.append(str).append("<br/>");
            }
            for(int i = 0; i < Math.min(ptkey, ptbox) - 1; i++) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "15"));
                params.add(new BasicNameValuePair("itemCode", "5007"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                str = IDUtils.execute(id, pm);
                open += str + "<br/>";
//                sb.append(str).append("<br/>");
            }
            if(open.length() > 5) {
                items[8] = open.substring(0, open.length() - 5);
            } else {
                items[8] = open;
            }
            tables.add(items);
        }
        return UtilFunctions.convertList2Table(tables, new boolean[]{false,false,true,true,true,true,true,true,false});
//        return sb.toString();
    }

    public static String viewActivity() throws Exception {
        String email = IDUtils.GETIDS().get(0);
        HttpClient client = IDUtils.IDS.get(email);
        if(client == null) {
            return "exception: no client";
        }
        HttpGet gm = new HttpGet(OgzqURL.URL + "/Default.aspx");
        gm.getParams().setParameter("Connection", "keep-alive");
        CloseableHttpResponse response = (CloseableHttpResponse) client.execute(gm);
        String ret = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
        InputStream is = response.getEntity().getContent();
        is.close();
        response.close();
        gm.releaseConnection();
        if(ret.indexOf("<!--各种活动的入口--><!--  最右侧6个活动 -->") < 0) {
            return "exception: not found activity";
        }
        List<String> activityList = new ArrayList<String>();
        ret = ret.substring(ret.indexOf("<!--各种活动的入口--><!--  最右侧6个活动 -->") + "<!--各种活动的入口--><!--  最右侧6个活动 -->".length());
        ret = ret.substring(0, ret.indexOf("<!--今日活动的入口 -->")).trim();
        String[] parts = ret.split("<div id=\\\"Activity_Enter");
        for(String p : parts) {
            if(p.trim().equals("")) {
                continue;
            }
            if(p.indexOf("<div") == -1) {
                continue;
            }
            p = p.substring(p.indexOf("<div"));
            p = p.substring(0, p.lastIndexOf("/div"));
            String[] pp = p.split("<div");
            for(String ppp : pp) {
                if(ppp.trim().equals("")) {
                    continue;
                }
                String activity = ppp.substring(ppp.indexOf("onclick='") + "onclick='".length());
                activity = activity.substring(0, activity.indexOf("' style="));
                activityList.add(activity);
            }
        }
        HttpPost pm = new HttpPost(OgzqURL.URL + "/AllEnters.aspx");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        ret = IDUtils.execute(email, pm);
        String[] p = ret.split("\\*");
        for(String pp : p) {
            activityList.add(pp.split("\\|")[1]);
        }
        ret = "";
        for(String s : activityList) {
            if(s.indexOf("BejeweledObj") >= 0) {
                ret += "宝石迷阵<br/>";
            } else if(s.indexOf("ChongZhiYFLObj") >= 0) {
                    ret += "充值摇返利<br/>";
            } else if(s.indexOf("KahnObj") >= 0) {
                    ret += "狮王卡恩<br/>";
            } else if(s.indexOf("GoalkeeperObj") >= 0) {
                    ret += "门将大跃进<br/>";
            } else if(s.indexOf("ChongZhiFanHuanObj") >= 0) {
                    ret += "充值钜惠<br/>";
            } else if(s.indexOf("Lantern_MoneyObj") >= 0) {
                    ret += "聚宝盆<br/>";
            } else if(s.indexOf("BeiLiObj") >= 0) {
                ret += "贝利比赛<br/>";
            } else if(s.indexOf("ShowNaiBa") >= 0) {
                ret += "超级奶爸<br/>";
            } else if(s.indexOf("DailySignInObj") >= 0) {
                ret += "日常签到<br/>";
            } else if(s.indexOf("Emigrated_NewObj") >= 0) {
                ret += "过关斩将<br/>";
            } else if(s.indexOf("QianLiZhiXing") >= 0) {
                ret += "潜力之星<br/>";
            } else if(s.indexOf("HorseLeHeObj") >= 0) {
                ret += "<span style=\"color:red;\">马上乐和</span><br/>";
            } else if(s.indexOf("DengLuYaoJiangObj") >= 0) {
                pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "Load"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(email, pm);
                if(s.indexOf("╋") >= 0) {
                    s = s.substring(s.indexOf("╋") + 1);
                } else {
                    s = "";
                }

                ret += "<span style=\"color:red;\">登录摇奖</span>" + s + "<br/>";
            } else if(s.indexOf("ShenMiDaTaoBaoObj") >= 0) {
                ret += "神秘大掏宝<br/>";
            } else if(s.indexOf("SuperZhuanZhuanObj") >= 0) {
                ret += "<span style=\"color:red;\">超级转转</span><br/>";
            } else if(s.indexOf("Fish_BlowObj") >= 0) {
                ret += "吹龙鱼<br/>";
            } else if(s.indexOf("BigDiscountObj") >= 0) {
                ret += "大折扣<br/>";
            } else if(s.indexOf("ItemDecompositionObj") >= 0) {
                ret += "道具分解<br/>";
            } else if(s.indexOf("PlayersCampObj") >= 0) {
                ret += "<span style=\"color:red;\">集中营</span><br/>";
            } else if(s.indexOf("Messi19Obj") >= 0) {
                ret += "梅西19连斩<br/>";
            } else if(s.indexOf("CrazyEggs12Obj") >= 0) {
                ret += "彩蛋连连敲<br/>";
            } else if(s.indexOf("CrazyEggsObj") >= 0) {
                ret += "疯狂砸金蛋<br/>";
            } else if(s.indexOf("MessiObj.GotoMessi345()") >= 0) {
                ret += "<span style=\"color:red;\">玩个球</span><br/>";
            } else if(s.indexOf("GoStarObj") >= 0) {
                ret += "升星豪礼<br/>";
            } else if(s.indexOf("PyramidObj") >= 0) {
                ret += "金字塔<br/>";
            } else if(s.indexOf("EquipGameObj") >= 0) {
                ret += "装备大比拼<br/>";
            } else if(s.indexOf("ChainStoreObj") >= 0) {
                ret += "<span style=\"color:red;\">欧冠连锁店</span><br/>";
            } else if(s.indexOf("ShuaShuaLeObj") >= 0) {
                ret += "<span style=\"color:red;\">刷刷乐</span><br/>";
            } else if(s.indexOf("HongFuBagsObj") >= 0) {
                ret += "洪福袋<br/>";
            } else if(s.indexOf("_AddPowerObj") >= 0) {
                ret += "<span style=\"color:red;\">三剑三马成长</span><br/>";
            } else if(s.indexOf("_ChallengeObj") >= 0) {
                ret += "<span style=\"color:red;\">三剑三马闯关: " + s.substring(0, s.indexOf("_ChallengeObj")) + "</span><br/>";
            } else if(s.indexOf("_Top_ClubObj") >= 0) {
                ret += "<span style=\"color:red;\">三剑三马球会巅峰</span><br/>";
            } else if(s.indexOf("_Top_PersonObj") >= 0) {
                ret += "<span style=\"color:red;\">三剑三马个人巅峰</span><br/>";
            } else if(s.indexOf("TopChallengeObj") >= 0) {
                ret += "<span style=\"color:red;\">拜仁巅峰</span><br/>";
            } else if(s.indexOf("GuaGuaCardObj") >= 0) {
                ret += "刮刮卡<br/>";
            } else if(s.indexOf("ItemAssemblyObj") >= 0) {
                ret += "道具集结号<br/>";
            } else if(s.indexOf("RichManCupObj") >= 0) {
                ret += "大富翁<br/>";
            } else if(s.indexOf("JiaNianHuaObj") >= 0) {
                ret += "嘉年华<br/>";
            } else if(s.indexOf("LaoBingChuanQiObj") >= 0) {
                ret += "老兵传奇<br/>";
            } else if(s.indexOf("CrazyEggs2Obj") >= 0) {
                ret += "<span style=\"color:red;\">疯狂砸蛋蛋</span><br/>";
            } else if(s.indexOf("MerryChristmasObj") >= 0) {
                ret += "充值返还<br/>";
            } else if(s.indexOf("RichManObj") >= 0) {
                ret += "大富翁<br/>";
            } else if(s.indexOf("ShowChongYang") >= 0) {
                ret += "九重天<br/>";
            } else if(s.indexOf("DaLiWanObj") >= 0) {
                ret += "<span style=\"color:red;\">大力丸</span><br/>";
            } else if(s.indexOf("GridShopObj") >= 0) {
                ret += "<span style=\"color:red;\">格子铺</span><br/>";
            } else if(s.indexOf("CaptainObj") >= 0) {
                ret += "我是队长<br/>";
            } else if(s.indexOf("GotoActivity(1)") >= 0) {
                ret += "万圣节、砸蛋、摘南瓜<br/>";
            } else if(s.indexOf("GotoActivity(2)") >= 0) {
                ret += "超级奶爸<br/>";
            } else if(s.indexOf("GotoActivity(4)") >= 0) {
                ret += "保罗任务<br/>";
            } else if(s.indexOf("GotoActivity(8)") >= 0) {
                ret += "世俱杯大卖场<br/>";
//                else if (methodIndex == 3)
//                    objActivity.ShowActivity1();
//                else if (methodIndex == 5)//外星人1
//                    objActivity.ShowLNND(1);
//                else if (methodIndex == 6)//外星人2
//                    objActivity.ShowLNND(2);
//                else if (methodIndex == 7)//欧冠好礼
//                    objActivity.ShowOGHL(1);
//                else if (methodIndex == 9)
//                    objActivity.ShowOGHL(2);
//                else if (methodIndex == 10)//欧冠竞猜
//                    objActivity.ShowOGJC(1);
            } else {
                ret += s + "<br/>";
            }
        }
        return ret;
    }

    public static String topChallengePersonStatus() throws Exception {
        HttpClient client = LoginUtils.Login("www.273919473@qq.com*幸隆", "lspmgk");
        HttpPost pm = new HttpPost(OgzqURL.URL + "/" + TopChallengeThread.PLAYER + "_Top_Person.aspx");
        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute(client, "www.273919473@qq.com*幸隆", pm);
        if(s.indexOf("╋") > 0) {
            s = s.substring(0, s.indexOf("╋"));
        }
        String[] teams = s.split("\\^")[0].split("@");
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"frame\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr class=\"head\">").append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 0, true)\">服务器</th>")
                .append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 1, false)\">号</th>")
                .append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 2, true)\">进球</th>");
        sb.append("</tr>");
        for(int i = 0; i < teams.length; i++) {
            String[] p = teams[i].split("\\|");
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\">")
                    .append("<td>").append(p[0]).append("</td>")
                    .append("<td>").append(p[1]).append("</td>")
                    .append("<td>").append(p[2]).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        //13|中国恒大队|57@2|NOTH|55@2|绿茵messi|53@13|有个女人|53@13|我也玩玩|52@2|菜泥尼奥|51@2|缥缈过客|51@13|正宗球球老头|51@13|标准11人|51@2|米兰goal|50^1╋10月7日12:00-18:00 10月8日12:00-18:00
        return sb.toString();
    }

    public static String viewNews() throws Exception {
        HttpGet gm = new HttpGet("http://ogzq.xdgame.cn/News.aspx?ID=-1");
        HttpResponse response = new DefaultHttpClient().execute(gm);
        String s = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
        s = s.substring(s.indexOf("<a class='div_NFont'"));
        s = s.substring(0, s.lastIndexOf("div_line"));
        String[] news = s.split("div_line");
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"frame\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr class=\"head\"><th>日期</th><th>标题</th></tr>");
        for(int i = 0; i < news.length; i++) {
            String n = news[i];
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\">");
            n = n.substring(n.indexOf("<a class='div_NFont' href=\"") + "<a class='div_NFont' href=\"".length());
            String link = n.substring(0, n.indexOf("\""));
            n = n.substring(n.indexOf(">") + 1);
            String title = n.substring(0, n.indexOf("<")).trim();
            n = n.substring(n.indexOf("<span style='color:") + "<span style='color:".length());
            n = n.substring(n.indexOf("'>") + "'>".length());
            String date = n.substring(0, n.indexOf("<"));
            sb.append("<td>").append(date).append("</td>");
            sb.append("<td><a href=\"").append(link).append("\" target=\"_blank\">").append(title).append("</a></td>");
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    public static String viewLMGX() throws Exception {
        HttpPost pm = new HttpPost(OgzqURL.URL + "/OGLM.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("Gxianbang1", ""));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute("sevarsti@sina.com", pm);
        s = s.split("@")[1];
        String[] teams = s.split("\\*");
        StringBuffer sb = new StringBuffer();
        sb.append("<table class=\"frame\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr class=\"head\"><th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 0, true)\">名次</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 1, false)\">号</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 2, false)\">服务器</th>" +
                "<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, 3, true)\">贡献</th></tr>");
        for(int i = 0; i < teams.length; i++) {
            String[] p = teams[i].split("\\|");
            String style = "";
            if(p[2].equals("官方2服")) {
                style = " style=\"color:red;\"";
            }
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\"").append(style).append(">");
            sb.append("<td>").append(p[0]).append("</td><td>").append(p[1]).append("</td><td>").append(p[2]).append("</td><td>").append(p[3]).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table");
        return sb.toString();
    }

    public static String ronglianGem(String leftmoney) throws Exception {
        List<String> ids = IDUtils.GETIDS();
//        ids.clear();ids.add("robot0001@sina.com");
        String ret = "";
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);

            if(Integer.parseInt(IDUtils.IDInfos.get(id).get("silver")) < Integer.parseInt(leftmoney)) {
                ret += id + "/" + IDUtils.getNick(id) + "剩余银币" + IDUtils.IDInfos.get(id).get("silver") + "小于阈值" + leftmoney + "<br/>";
                continue;
            }

            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] items = s.split("[|]");
            int cloth4 = 0, shoes4 = 0, hand4 = 0, leg4 = 0, cloth5 = 0, shoes5 = 0, hand5 = 0, leg5 = 0;
            List<String> lv4Items = new ArrayList<String>();
            List<String> lv5Items = new ArrayList<String>();
            for(String item : items) {
                String itemname = item.split("[*]")[3];
                String itemid = item.split("[*]")[0];
                int itemcount = Integer.parseInt(item.split("[*]")[7]);
                if(itemname.equals("战靴5级") || itemname.equals("手套5级") || itemname.equals("战袍5级") || itemname.equals("护腿5级")) {
                    for(int i = 0; i < itemcount; i++) {
                        lv5Items.add(itemid);
                    }
                } else if(itemname.equals("战靴4级") || itemname.equals("手套4级") || itemname.equals("战袍4级") || itemname.equals("护腿4级")) {
                    for(int i = 0; i < itemcount; i++) {
                        lv4Items.add(itemid);
                    }
                }
            }
            int lv5count = 0, lv4count = 0;
            while(lv5Items.size() > 4) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Ronglian1", lv5Items.get(0) + "*" + lv5Items.get(1) + "*" + lv5Items.get(2) + "*" + lv5Items.get(3) + "*" + lv5Items.get(4)));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String result = IDUtils.execute(id, pm);
                System.out.println(id + "合成2级宝石：" + result);
                lv5count++;
                for(int i = 0; i < 5; i++) {
                    lv5Items.remove(0);
                }
            }
            while(lv4Items.size() > 4) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("Ronglian1", lv4Items.get(0) + "*" + lv4Items.get(1) + "*" + lv4Items.get(2) + "*" + lv4Items.get(3) + "*" + lv4Items.get(4)));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String result = IDUtils.execute(id, pm);
                System.out.println(id + "合成1级宝石：" + result);
                lv4count++;
                for(int i = 0; i < 5; i++) {
                    lv4Items.remove(0);
                }
            }
            ret += id + "/" + IDUtils.getNick(id) + "共合成2级宝石" + lv5count + "个，1级宝石" + lv4count + "个<br/>";
        }
        return ret;
    }

    public static String superZhuanzhuan() throws Exception {
        String ret = "";
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "结果", "旗帜数量"});
        for(String id : ids) {
            System.out.println("超级转转:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/SuperZhuanZhuan.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] parts = s.split("\\@");
            int freetimes = Integer.parseInt(parts[1]);
            String[] results = new String[4];
            results[0] = id;
            results[1] = IDUtils.getNick(id);
            String r3 = "";
            for(int i = 0; i < freetimes; i++) {
                pm = new HttpPost(OgzqURL.URL + "/SuperZhuanZhuan.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "3"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
                String[] aaarr = s.split("\\|");
                String money = aaarr[0];
                String canMoney = aaarr[1];
                if("1".equals(canMoney) && "0".equals(money)) {
                    pm = new HttpPost(OgzqURL.URL + "/SuperZhuanZhuan.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "2"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(id, pm);
                    System.out.println(id + "/" + IDUtils.getNick(id) + "转转：" + s);
                    if(r3.length() > 0) {
                        r3 += "<br/>";
                    }
                    r3 += s;
//                    ret += id + "/" + IDUtils.getNick(id) + "获得========" + s + "<br/>";
                }
            }
            results[2] = r3;
            results[3] = "";
            pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
            String[] items = str.split("[|]");
            for(String item : items) {
                if(item.split("\\*")[11].equals("500177")) {
                    results[3] = item.split("\\*")[7];
                    break;
                }
            }
            tables.add(results);
//9月17日 10 : 00至9月19日 23 : 59@
//2@
//0@
//5@
//2@
//500177*冠军旗帜*1|5505*解说经验卡500点*1|5504*解说经验卡400点*1|5503*解说经验卡300点*1|5502*解说经验卡200点*1|5501*解说经验卡100点*1|5405*解说潜力卡5级*1|5404*解说潜力卡4级*1|5403*解说潜力卡3级*1|5301*防守解说技能卡1级*1|5201*组织解说技能卡1级*1|5101*进攻解说技能卡1级*1
//0N月N日00：00至N月N日00：00@1免费转动的次数@2已经转动的次数@3再转动几次@4就可以获得的奖励是翻几倍@5itemcode|itemcode|itemcode|itemcode.....
        }
        return UtilFunctions.convertList2Table(tables, new boolean[]{false, false, false, true});
//        return ret;
    }

    public static String dengluyaojiang() throws Exception {
        String ret = "";
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String msg = "";
            HttpPost pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "jiHuo"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);

            pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            if(s.indexOf("╋") >= 0) {
                s = s.substring(0, s.indexOf("╋"));
            }
            msg = s.split("@")[3] + "x" + s.split("@")[2];
            if(s.split("@")[1].equals("1") && s.split("@")[2].equals("0")) {
                pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "yao"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);

                pm = new HttpPost(OgzqURL.URL + "/DengLuYaoJiang.aspx");
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("request", "Load"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                s = IDUtils.execute(id, pm);
                if(s.indexOf("╋") >= 0) {
                    s = s.substring(0, s.indexOf("╋"));
                }
                msg = s.split("@")[3] + "x" + s.split("@")[2];
            }
            ret += msg + "----" + id + IDUtils.getNick(id) + "<br/>";
        }
        return ret;
    }

    public static String custTopChallenge() throws Exception {
        new CustomTopChallengeThread().start();
        return "巅峰球会战启动";
    }

    public static String beginDaliwan() throws Exception {
        DaliwanThread.getInstance().start();
        return "大力丸启动";
    }

    public static String daliwanStatus() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "比赛场次", "大力丸数量", "可升级球员"});
        String status = null;
        for(String id : ids) {
            if(id.indexOf("txjcf") >= 0) {
                continue;
            }
            System.out.println("大力丸状态:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            if(s.indexOf("inmatch") >= 0) {
                tables.add(new String[]{id, IDUtils.getNick(id), "-1", "-1", ""});
                continue;
            }
            if(s.indexOf("╋") >= 0) {
                s = s.substring(0, s.indexOf("╋"));
            }
            String[] parts = s.split("\\@");
            if(status == null) {
                status = parts[0].equals("1") ? "挑战时间" : "加成时间";
            }
            if(parts[1].equals("1")) {
                String playername = parts[4].split("\\|")[5];
                String oldshili = parts[4].split("\\|")[2];
                String newshili = parts[5].split("\\|")[2];
                tables.add(new String[]{id, IDUtils.getNick(id), Integer.parseInt(parts[6]) / 1000 + "", parts[2], oldshili + "->" + newshili + "," + playername});
            } else {
                String[] players = parts[3].split("\\*");
                String info = "";
                List<String[]> upgradePlayers = new ArrayList<String[]>();
                for(String p : players) {
                    String[] subs = p.split("\\|");
                    pm = new HttpPost(OgzqURL.URL + "/DaLiWan.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "3"));
                    params.add(new BasicNameValuePair("playerid", subs[0]));
                    params.add(new BasicNameValuePair("playerDataId", subs[1]));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    s = IDUtils.execute(id, pm);
                    subs = s.split("\\|");
                    if(upgradePlayers.size() == 0) {
                        upgradePlayers.add(new String[]{subs[2], subs[5]});
                    } else {
                        for(int i = 0; i < upgradePlayers.size(); i++) {
                            if(Integer.parseInt(subs[2]) <= Integer.parseInt(upgradePlayers.get(i)[0])) {
                                upgradePlayers.add(i, new String[]{subs[2], subs[5]});
                                break;
                            } else if(i == upgradePlayers.size() - 1) {
                                upgradePlayers.add(new String[]{subs[2], subs[5]});
                                break;
                            }
                        }
                    }
                    //712132|110000072|131     |2|Super/10018.png|巴雷西  |6@       712132                  |110000072|132|2|Super/10018.png|巴雷西|6
                    //                 综合能力,位置,头像,        球员名字,球员品质,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID,playerDateId
                }
                for(String[] p : upgradePlayers) {
                    if(info.length() > 0) {
                        info += "；";
                    }
                    info += p[0] + p[1];
                }
                tables.add(new String[]{id, IDUtils.getNick(id), Integer.parseInt(parts[6]) / 1000 + "", parts[2], info});
            }
        }
        return status + "<br/>" + UtilFunctions.convertList2Table(tables, new boolean[]{false, false, true, true, false});
    }

    public static String beiliStatus() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        List<String[]> tables = new ArrayList<String[]>();
        tables.add(new String[]{"email", "nick", "排名", "排名奖励", "徽章", "贝利积分", "成长"});
        String status = null;
        for(String id : ids) {
            if(id.indexOf("txjcf") >= 0) {
                continue;
            }
            System.out.println("贝利积分状态:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String[] result = new String[7];
            result[0] = id;
            result[1] = IDUtils.getNick(id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load_ZBS"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            String[] parts = s.split("\\@");
            result[2] = parts[0].split("\\|")[3];
            result[3] = parts[4];

            pm = new HttpPost(OgzqURL.URL + "/BeiLi.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load_DHBL"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            result[4] = s.split("\\@")[1];

            pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            

            pm = new HttpPost(OgzqURL.URL + "/Pele.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            s = IDUtils.execute(id, pm);
            result[5] = s.split("\\@")[1];
            result[6] = s.split("\\@")[0].replaceAll("\\*", "<br/>");
            tables.add(result);
        }
        return UtilFunctions.convertList2Table(tables, new boolean[]{false, false, true, true, true, true, false});
    }

    public static String bayernChallengeStatus() throws Exception {
        StringBuffer sb = new StringBuffer();
        HttpPost pm = new HttpPost(OgzqURL.URL + "/TopChallenge.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        List<String> ids = IDUtils.GETIDS();
        String id = "";
        for(String i : ids) {
            if(i.indexOf("txjcf") == 0) {
                id = i;
                break;
            }
        }
        String s = IDUtils.execute(id, pm);
        if(s.indexOf("╋") > 0) {
            s = s.substring(0, s.indexOf("╋"));
        }
        if(s.indexOf("inmatch") >= 0) {
            return "正在比赛中";
        }
        String rest = s.split("\\^")[1];
        s = s.split("\\^")[0];
        String[] teams = s.split("\\@");
        sb.append("<table id=\"info\" border=\"0\" cellpadding=\"1\" cellspacing=\"1\">");
        sb.append("<tr class=\"head\"><th>号</th><th>进球</th></tr>");
        for(int i = 0; i < teams.length; i++) {
            String[] parts = teams[i].split("\\|");
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\">");
            sb.append("<td>").append(parts[0]).append("</td><td>").append(parts[1]).append("</td></tr>");
        }
        sb.append("<tr class=\"head\">");
        sb.append("<td>").append("剩余").append("</td><td>").append(rest).append("</td></tr>");
        sb.append("</table>");
        return sb.toString();
    }

    public static String upPele() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        StringBuffer sb = new StringBuffer();
        for(String id : ids) {
            System.out.println("升级贝利初始能力:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);

            /* 获取需要升级的金人 */
            String playerId = null;
            String playerName = null;
            int count = 0;
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
            String[] playsers = str.split("\\^")[1].split("\\|");
            for(String p : playsers) {
                if(p.split("\\*")[3].equals("7")) {
                    if(!"罗纳尔多".equals(p.split("\\*")[1]) &&
                            !"马尔蒂尼".equals(p.split("\\*")[1]) &&
                            !"舒梅切尔".equals(p.split("\\*")[1]) &&
                            !"齐达内".equals(p.split("\\*")[1])) {
                        playerId = p.split("\\*")[0];
                        playerName = p.split("\\*")[1];
                        break;
                    }
                }
            }

            /* 获取贝利积分 */
            pm = new HttpPost(OgzqURL.URL + "/Pele.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("Load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            count = Integer.parseInt(s.split("\\@")[1]);

            System.out.println("需要升级的球员：" + playerId + "/" + playerName + ",贝利积分有" + count + "，需要升级" + (count / 150) + "次");
            count = count / 150;
            if(playerId != null && count > 0) {
                for(int i = 0; i < count; i++) {
                    pm = new HttpPost(OgzqURL.URL + "/Pele.aspx");
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("yangCheng", playerId));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    IDUtils.execute(id, pm);
                }
                OperationUtils.trainFull(id, playerId, true);
            } else {
                continue;
            }
            sb.append(id).append("/").append(IDUtils.getNick(id)).append("升级").append(playerName).append("，共").append(count).append("次<br/>");
        }
        return sb.toString();
    }

    public static String useOgHuidou() throws Exception {
        List<String> ids = IDUtils.GETIDS();
        for(String id : ids) {
            if(id.indexOf("txjcf") == 0) {
                continue;
            }
            if(
                    id.equals("101977723@qq.com") ||
                    id.equals("115271540@qq.com") ||
                    id.equals("124906932@qq.com") ||
                    id.equals("156451865@qq.com") ||
                    id.equals("276300227@qq.com") ||
                    id.equals("279512194@qq.com") ||
                    id.equals("316639404@qq.com") ||
                    id.equals("36386426@qq.com") ||
                    id.equals("3897021733@qq.com") ||
                            id.equals("421623479@qq.com") ||
                    id.equals("444825566@qq.com") ||
                    id.equals("472545875@qq.com") ||
                    id.equals("7125608@163.com") ||
                    id.equals("823820371@qq.com") ||
                    id.equals("82382037111@qq.com") ||
                    id.equals("bixi2@sina.com") ||
                    id.equals("blue001@sina.com") ||
                    id.equals("blue002@sina.com") ||
                    id.equals("fanmingsuo@163.com") ||
                    id.equals("jiangleiyy1986@126.com") ||
                    id.equals("karen525@tom.com") ||
                    id.equals("meijianbai@hotmail.com") ||
                    id.equals("mrshanyao@163.com") ||
                    id.equals("orange001@sina.com") ||
                    id.equals("orange002@sina.com") ||
                    id.equals("orange003@sina.com") ||
                    id.equals("robot0001@sina.com") ||
                    id.equals("robot0002@sina.com") ||
                    id.equals("robot0004@sina.com") ||
                    id.equals("robot0005@sina.com") ||
                    id.equals("robot0006@sina.com") ||
                    id.equals("robot0007@sina.com") ||
                    id.equals("robot0008@sina.com") ||
                    id.equals("robot0009@sina.com") ||
                    id.equals("robot0010@sina.com") ||
                    id.equals("robot0011@sina.com") ||
                    id.equals("robot0012@sina.com") ||
                            id.equals("sevarsti@sina.com") ||
                    id.equals("stevending1@163.com") ||
                    id.equals("www.569323373@qq.163.com") ||
                            id.equals("xieqigan@qq.com") ||
                    id.equals("zhao6527@163.com") ||
                    id.equals("zhhz1226@163.com") ||
                    (1==0)) {
                continue;
            }
            System.out.println("使用巅峰回豆:" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("OgzdGameLoad1", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
            int restMatch = Integer.parseInt(s.split("@")[0]) + 1;
            if(restMatch > 0) {
                System.out.println(id + "剩余场次：" + restMatch + "，不使用巅峰回豆");
                continue;
            }

            pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "0"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
            String[] items = str.split("[|]");
            String huidouId = "";
            int count = 0;
            for(String ii : items) {
                String[] i = ii.split("[*]");
                if(i[3].equals("巅峰回豆卡")) {
                    huidouId = i[0];
                    count = Integer.parseInt(i[7]);
                    break;
                }
            }
            if(count > 0) {
                pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("type", "7"));
                params.add(new BasicNameValuePair("aii", huidouId));
                params.add(new BasicNameValuePair("isAll", "0"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String ss = IDUtils.execute(id, pm);
                System.out.println(id + "使用巅峰回豆卡：" + ss);
            }
        }
        return "done";
    }

    public static String doLoginTeamgame() throws Exception {
        new LoginTeamgameThread().start();
        return "done";
    }
}
