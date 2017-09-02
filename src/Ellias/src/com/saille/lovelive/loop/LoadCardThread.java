package com.saille.lovelive.loop;

import com.saille.sys.BaseThread;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.EofSensorInputStream;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

/**
 * Created by Ellias on 2017-08-31.
 */
public class LoadCardThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(LoadCardThread.class);

    public static void main(String[] args) {
        new LoadCardThread().execute();
    }

    @Override
    protected int execute() {
        try {
            String filepath = "F:" + "\\temp\\llcard.txt";
            File tempfile = new File(filepath);
            if(!tempfile.exists()) {
                tempfile.createNewFile();
            }
            downloadcard(filepath);
            parseCard(filepath);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void parseCard(String filepath) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellias", "root", "sjtuagent");

        PreparedStatement ps;
        String[] tables = new String[]{"ll_card","ll_functionvoice","ll_periodvoice","ll_randomvoice","ll_series","ll_skill","ll_subscenario","ll_timevoice","ll_touchvoice", "ll_specialvoice"};
        for(String t : tables) {
            ps = connection.prepareStatement("truncate table " + t);
            ps.executeUpdate();
            ps.close();
        }
        FileInputStream fis = new FileInputStream(filepath);
        int i;
        int bracecount = 0;
        StringBuffer sb = new StringBuffer();
        int count = 0;
        boolean checkQuote = false;
        while((i = fis.read()) != -1) {
            if(bracecount > 0) {
                char c = (char) i;
                sb.append(c);
                if(c == '\"') {
                    char prvChar = sb.charAt(sb.length() - 2);
                    if(prvChar != ' ' && prvChar != '[' && prvChar != '{') {
                        checkQuote = true;
                    } else {
                        checkQuote = false;
                    }
                } else {
                    if(checkQuote) {
                        if(c == ':' || c == ' ' || c == ',' || c == ']' || c == '}') {
                        } else {
                            sb.insert(sb.length() - 2, "\\");
                        }
                    }
                    checkQuote = false;
                }
            }
            if(((char) i) == '{') {
                if(bracecount == 0) {
                    sb.append("{");
                }
                bracecount++;
            } else if(((char) i) == '}') {
                bracecount--;
                if(bracecount == 0) {
                    String s = StringEscapeUtils.unescapeJava(sb.toString());
                    System.out.println(count++ + s);
                    JSONObject json = new JSONObject(s);
                    saveJSONObject(connection, json);
                    sb = new StringBuffer();
                }
            }
        }
        fis.close();
    }

    private void saveJSONObject(Connection conn, JSONObject obj) throws Exception {
        int id = obj.getInt("id");
        String upnavi = obj.getString("upnavi");
        String navi = obj.getString("navi");
        String attribute = obj.getString("attribute");
        int cool_max = obj.getInt("cool_max");
        String upicon = obj.getString("upicon");
        int lv_max = obj.getInt("lv_max");
        int pure_max = obj.getInt("pure_max");
        String type = obj.getString("type");
        String eponym = obj.optString("eponym");
        int smile_max = obj.getInt("smile_max");
        String icon = obj.getString("icon");
        String name = obj.getString("name");
        String rarity = obj.getString("rarity");
        int order = obj.getInt("order");
        JSONArray hpArray = obj.getJSONArray("hp");
        JSONArray smileArray = obj.getJSONArray("smile");
        JSONArray pureArray = obj.getJSONArray("pure");
        JSONArray coolArray = obj.getJSONArray("cool");
        JSONObject extraObj = obj.optJSONObject("extra");
        JSONObject leaderObj = obj.optJSONObject("leader");
        PreparedStatement ps = conn.prepareStatement("insert into ll_card(" +
                "`id`,`order`,`upnavi`,`navi`,`attribute`,`cool_max`,`upicon`,`lv_max`,`pure_max`,`type`,`eponym`,`smile_max`,`icon`,`name`,`rarity`," +
                "`hparray`, `smilearray`, `purearray`, `coolarray`, `extraval`, `extratag`, `extratype`, `leadername`, `leadertext`) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        ps.setInt(1, id);
        ps.setInt(2, order);
        ps.setString(3, upnavi);
        ps.setString(4, navi);
        ps.setString(5, attribute);
        ps.setInt(6, cool_max);
        ps.setString(7, upicon);
        ps.setInt(8, lv_max);
        ps.setInt(9, pure_max);
        ps.setString(10, type);
        ps.setString(11, eponym);
        ps.setInt(12, smile_max);
        ps.setString(13, icon);
        ps.setString(14, name);
        ps.setString(15, rarity);
        ps.setString(16, hpArray.toString());
        ps.setString(17, smileArray.toString());
        ps.setString(18, pureArray.toString());
        ps.setString(19, coolArray.toString());
        if(extraObj != null) {
            ps.setInt(20, extraObj.getInt("val"));
            ps.setString(21, extraObj.getString("tag"));
            ps.setString(22, extraObj.getString("type"));
        } else {
            ps.setNull(20, Types.INTEGER);
            ps.setNull(21, Types.VARCHAR);
            ps.setNull(22, Types.VARCHAR);
        }
        if(leaderObj != null) {
            ps.setString(23, leaderObj.getString("name"));
            ps.setString(24, leaderObj.getString("text"));
        } else {
            ps.setNull(23, Types.VARCHAR);
            ps.setNull(24, Types.VARCHAR);
        }
        ps.executeUpdate();
        /* ll_functionvoice */
        JSONArray functionVoiceArray = obj.getJSONArray("function_voice");
        PreparedStatement ps2 = conn.prepareStatement("insert into ll_functionvoice(id, content, voice) values(?,?,?)");
        for(int i = 0; i < functionVoiceArray.length(); i++) {
            JSONObject voiceObj = functionVoiceArray.getJSONObject(i);
            String content = voiceObj.optString("content");
            String voice = voiceObj.optString("voice");
            ps2.setInt(1, id);
            ps2.setString(2, content);
            ps2.setString(3, voice);
            ps2.executeUpdate();
        }
        ps2.close();
        /* ll_periodvoice */
        ps2 = conn.prepareStatement("insert into ll_periodvoice(id, month_from, month_to, day_from, day_to, content, voice)values(?,?,?,?,?,?,?)");
        JSONArray periodVoiceArray = obj.getJSONArray("period_voice");
        for(int i = 0; i < periodVoiceArray.length(); i++) {
            JSONObject subObj = periodVoiceArray.getJSONObject(i);
            int monthfrom = subObj.getInt("month_from");
            int monthto = subObj.getInt("month_to");
            int dayfrom = subObj.getInt("day_from");
            int dayto = subObj.getInt("day_to");
            String content = subObj.getString("content");
            String voice = subObj.optString("voice");
            ps2.setInt(1, id);
            ps2.setInt(2, monthfrom);
            ps2.setInt(3, monthto);
            ps2.setInt(4, dayfrom);
            ps2.setInt(5, dayto);
            ps2.setString(6, content);
            ps2.setString(7, voice);
            ps2.executeUpdate();
        }
        ps2.close();
        /* ll_randomvoice */
        ps2 = conn.prepareStatement("insert into ll_randomvoice(id, appearance, content,voice) values(?,?,?,?)");
        JSONArray randomVoiceArray = obj.getJSONArray("random_voice");
        for(int i = 0; i < randomVoiceArray.length(); i++) {
            JSONObject subObj = randomVoiceArray.getJSONObject(i);
            String content = subObj.getString("content");
            String apperance = subObj.getJSONArray("appearance").toString();
            String voice = subObj.optString("voice");
            ps2.setInt(1, id);
            ps2.setString(2, apperance);
            ps2.setString(3, content);
            ps2.setString(4, voice);
            ps2.executeUpdate();
        }
        ps2.close();
        /* ll_series */
        ps2 = conn.prepareStatement("insert into ll_series(id, series) values(?,?)");
        JSONArray seriesArray = obj.optJSONArray("series");
        ps2.setInt(1, id);
        for(int i = 0; i < seriesArray.length(); i++) {
            ps2.setString(2, seriesArray.getString(i));
            ps2.executeUpdate();
        }
        ps2.close();
        /* ll_skill */
        ps2 = conn.prepareStatement("insert into ll_skill(id, name, type, level_max, level, text,triggertype, `interval`, `ratio`, `value`, perval) values(?,?,?,?,?,?,?,?,?,?,?)");
        JSONObject skillObj = obj.optJSONObject("skill");
        if(skillObj != null) {
            String skillname = skillObj.getString("name");
            int level_max = skillObj.getInt("level_max");
            String skilltype = skillObj.getString("type");
            ps2.setInt(1, id);
            ps2.setString(2, skillname);
            ps2.setString(3, skilltype);
            ps2.setInt(4, level_max);
            JSONArray subArray = skillObj.getJSONArray("text");
            String triggertype = "未知";
            double perVal = 0;
            int interval = 0;
            int ratio = 0;
            int value = 0;
            for(int j = 0; j < subArray.length(); j++) {
                String text = subArray.getString(j);
                if(text.contains("PERFECT")) {
                    triggertype = "PERFECT";
                } else if(text.contains("秒")) {
                    triggertype = "秒";
                } else if(text.contains("连击")) {
                    triggertype = "连击";
                } else if(text.contains("节奏图示")) {
                    triggertype = "节奏图示";
                }
                Pattern p = Pattern.compile("\\D+(\\d+)\\D+(\\d+)\\D+(\\d+)\\D+");
                Matcher m = p.matcher(text);
                if(m.find()) {
                    interval = Integer.parseInt(m.group(1));
                    ratio = Integer.parseInt(m.group(2));
                    value = Integer.parseInt(m.group(3));
                    perVal = (double) value / (double) interval * (double) ratio / 100d;
                }
                ps2.setInt(5, j + 1);
                ps2.setString(6, text);
                ps2.setString(7, triggertype);
                ps2.setInt(8, interval);
                ps2.setInt(9, ratio);
                ps2.setInt(10, value);
                ps2.setDouble(11, perVal);
                ps2.executeUpdate();
            }
        }
        /* ll_subscenario */
        ps2 = conn.prepareStatement("insert into ll_subscenario(id, title, content, voice, `index`)values(?,?,?,?,?)");
        JSONArray subscenarioArray = obj.optJSONArray("subscenario");
        for(int i = 0; i < subscenarioArray.length(); i++) {
            JSONObject subObj = subscenarioArray.getJSONObject(i);
            String title = subObj.getString("title");
            JSONArray subArray = subObj.getJSONArray("scene");
            for(int j = 0; j < subArray.length(); j++) {
                String content = subArray.getJSONObject(j).getString("content");
                String voice = subArray.getJSONObject(j).optString("voice");
                ps2.setInt(1, id);
                ps2.setString(2, title);
                ps2.setString(3, content);
                ps2.setString(4, voice);
                ps2.setInt(5, j);
                ps2.executeUpdate();
            }
        }
        ps2.close();
        /* ll_timevoice */
        ps2 = conn.prepareStatement("insert into ll_timevoice(id, start, end, content, voice) values(?,?,?,?,?)");
        JSONArray timeVoiceArray = obj.optJSONArray("time_voice");
        for(int i = 0; i < timeVoiceArray.length(); i++) {
            JSONObject subobj = timeVoiceArray.getJSONObject(i);
            String start = subobj.getString("start");
            String end = subobj.getString("end");
            String content = subobj.getString("content");
            String voice = subobj.optString("voice");
            ps2.setInt(1, id);
            ps2.setString(2, start);
            ps2.setString(3, end);
            ps2.setString(4, content);
            ps2.setString(5, voice);
            ps2.executeUpdate();
        }
        ps2.close();
        /* ll_specialvoice */
        JSONArray specialVoiceArray = obj.getJSONArray("special_voice");
        if(specialVoiceArray.length() > 0) {
            ps2 = conn.prepareStatement("insert into ll_specialvoice(id, content,voice) values(?,?,?)");
            for(int i = 0; i < specialVoiceArray.length(); i++) {
                JSONObject subobj = specialVoiceArray.getJSONObject(i);
                String content = subobj.getString("content");
                String voice = subobj.optString("voice");
                ps2.setInt(1, id);
                ps2.setString(2, content);
                ps2.setString(3, voice);
                ps2.executeUpdate();
            }
            ps2.close();
        }
        /* ll_touchvoice */
        ps2 = conn.prepareStatement("insert into ll_touchvoice(id, appearance, content,voice) values(?,?,?,?)");
        JSONArray touchVoiceArray = obj.optJSONArray("touch_voice");
        for(int i = 0; i < touchVoiceArray.length(); i++) {
            JSONObject subObj = touchVoiceArray.getJSONObject(i);
            String content = subObj.getString("content");
            String apperance = subObj.getJSONArray("appearance").toString();
            String voice = subObj.optString("voice");
            ps2.setInt(1, id);
            ps2.setString(2, apperance);
            ps2.setString(3, content);
            ps2.setString(4, voice);
            ps2.executeUpdate();
        }
        ps2.close();
    }

    private boolean downloadcard(String filepath) throws Exception {
        File currentfile = new File(filepath);
        if(!currentfile.exists()) {
            currentfile.createNewFile();
        }
        DefaultHttpClient client = new DefaultHttpClient();
        client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
        client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
        HttpGet gm = new HttpGet("https://app.lovelivewiki.com/js/cards.js");
        gm.addHeader("Accept-Encoding", "gzip, deflate");
        CloseableHttpResponse response = client.execute(gm);
        InputStream is = response.getEntity().getContent();
        Header[] headers = response.getHeaders("Last-Modified");//Sun, 27 Aug 2017 13:29:13 GMT
        long lastmodifydt = 0;
        if(headers.length > 0) {
            String lastmodify = headers[0].getValue();
            try {
                lastmodifydt = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH).parse(lastmodify).getTime();
            } catch(Exception ex) {
                LOGGER.error("无法解析报文头Last-Modified: " + headers[0].getValue());
                ex.printStackTrace();
            }
            if(currentfile.exists() && currentfile.lastModified() == lastmodifydt) {
                LOGGER.info("文件已经下载过，不再处理，文件更新时间：" + new Date(currentfile.lastModified()));
                ((EofSensorInputStream)is).abortConnection();
//                is.close();
                response.close();
                gm.releaseConnection();
                return false;
            }
        }
        GZIPInputStream zis = new GZIPInputStream(is);
        FileOutputStream fos = new FileOutputStream(filepath);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] bytes = new byte[81920];
        int i;
        while((i = zis.read(bytes)) > 0) {
            bos.write(bytes, 0, i);
            bos.flush();
        }
        zis.close();
        is.close();
        response.close();
        gm.releaseConnection();
        bos.close();
        fos.close();
        if(lastmodifydt > 0) {
            new File(filepath).setLastModified(lastmodifydt);
            LOGGER.info("文件保存完毕，更新时间：" + new Date(lastmodifydt));
        }
        return true;
    }
}
