package com.saille.weixin;

import com.saille.util.UtilFunctions;
import com.saille.util.FileUtils;
import com.saille.util.CommonUtils;
import com.saille.ogzq.ArenaThread;
import com.saille.ogzq.OgzqUtils;
import com.saille.core.PropertyDescription;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import java.lang.reflect.Method;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONArray;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-1-12
 * Time: 1:07:57
 * To change this template use File | Settings | File Templates.
 */
public class WeixinHandler {
    private final static long EXPIRE_TIME = 1000 * 60 * 5;
    private static Map<String, String> userAction = new HashMap<String, String>();
    private static Map<String, Long> userActionTime = new HashMap<String, Long>();
    private final static Logger LOGGER = Logger.getLogger(WeixinHandler.class);

    public static void processRequest(HttpServletRequest request, HttpServletResponse response) {
        try {
            String echostr = request.getParameter("echostr");
            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            if(!verifyRequest(signature, timestamp, nonce)) {
                LOGGER.warn("=============verify failed=============");
                return;
            }
//            if(request.getParameterMap().size() == 4) { //只有四个参数，说明是测试验证连通性
//                response.getWriter().write(echostr);
//            }

            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            BufferedReader reader = request.getReader();
            String tmp = "";
            StringBuilder sb = new StringBuilder();
            while((tmp = reader.readLine()) != null) {
                sb.append(tmp);
            }
            tmp = sb.toString();
            System.out.println(tmp);

            try {
                Map map = new HashMap();
                Document doc = DocumentHelper.parseText(tmp);
                Element root = doc.getRootElement();
                for(Iterator iterator = root.elementIterator(); iterator.hasNext();) {
                    Element e = (Element) iterator.next();
                    //System.out.println(e.getName());
                    List list = e.elements();
                    if(list.size() > 0) {
                        map.put(e.getName(), Dom2Map(e));
                    } else {
                        map.put(e.getName(), e.getText());
                    }
                }
                sb = new StringBuilder();
                for(Object o : map.keySet()) {
                    sb.append(o).append("=").append(map.get(o)).append("\n");
                }
                LOGGER.info("params:\n" + sb.toString());
                String ret = processMap(map);
                if(StringUtils.isNotEmpty(ret)) {
                    LOGGER.info("return value: " + ret);
                    response.getWriter().write(ret);
                }
            } catch(Exception ex) {
                UtilFunctions.LogError(LOGGER, ex);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String processMap(Map<String, String> map) {
        String msgType = map.get("MsgType");
        String createTime = map.get("CreateTime"); //创建时间
        String from = map.get("FromUserName");
        String to = map.get("ToUserName");
        String msgId = map.get("MsgId");
        String content = map.get("Content");

        log(from, content, new Date(Integer.parseInt(createTime) * 1000), 1);
        String retText = null;
        try {
            if("text".equalsIgnoreCase(map.get("MsgType"))) {
                String context = map.get("Content");
                retText = processTextInstruction(from, context);
            }
            log(to, retText, new Date(), 2);
            return getSendString(to, from, createTime, retText, msgId);
        } catch(Exception ex) {
            ex.printStackTrace();
            log(to, "处理发生异常", new Date(), 2);
            return getSendString(to, from, createTime, "处理发生异常", msgId);
        }
    }

    private static String processTextInstruction(String from, String text) {
        if(text == null) {
            return "";
        }
//        long lastActionTime = userActionTime.get(from).longValue();
//        long now = new Date().getTime();
//        String currentSequence = userAction.get(from);
//        if(((now - lastActionTime) > EXPIRE_TIME) || currentSequence == null  || currentSequence.equals("")) { //超时
//            currentSequence = "";
//            userActionTime.put(from, now);
//            userAction.put(from, "");
//        }

        if(text.startsWith("欧冠")) {
            return processOgzqTextInstruction(text.substring("欧冠".length()));
        } else if(text.startsWith("节奏:") || text.startsWith("节奏：")) {
            return processRmTextInstruction(text.substring(3));
        } else if(text.startsWith("节奏活跃:") || text.startsWith("节奏活跃：")) {
            return processRmTeamTextInstruction(text.substring(5));
        } else if(text.endsWith("天气")) {
            return processWeatherTextInstruction(text.substring(0, text.length() - 2));
        } else if(text.startsWith("问路：") || text.startsWith("问路:")) {
            return processRouteTextInstruction(text.substring(3));
        }
        return "";
    }

    private static String processRouteTextInstruction(String text) {
        String ret = "";
        try {
            String[] places = text.split(" ");
            String ak = "AYdvDMsaGG9fYBw8blFPGkiL";
            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("mode", "walking");
            paramsMap.put("origin", places[0]);
            paramsMap.put("destination", places[1]);
            String region = places.length > 2 ? places[2] : "上海";
            paramsMap.put("origin_region", region);
            paramsMap.put("destination_region", region);
            paramsMap.put("output", "json");
            paramsMap.put("ak",ak);

            String paramsStr = toQueryString(paramsMap);
            HttpClient client = new DefaultHttpClient();
            HttpGet gm = new HttpGet("http://api.map.baidu.com/direction/v1?" + paramsStr);
            HttpResponse response = client.execute(gm);
            String msg = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            JSONObject obj = new JSONObject(msg);
            obj = obj.getJSONObject("result");
            System.out.println(obj.toString());
            JSONObject origin = obj.getJSONObject("origin");
            JSONObject dest = obj.getJSONObject("destination");
            String from = origin.getString("wd");
            String to = dest.getString("wd");
            obj = obj.getJSONArray("routes").getJSONObject(0);
            String distance = obj.getString("distance");
            List<String> routes = new ArrayList<String>();
            JSONArray steps = obj.getJSONArray("steps");
            for(int i = 0; i < steps.length(); i++) {
                obj = steps.getJSONObject(i);
                routes.add(obj.getString("instructions"));
            }
            ret = from + " 到 " + to + "，距离" + distance + "米，路线：";
            for(String s : routes) {
                ret += "<br/>" + s;
            }
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
            return "发生错误";
        }
    }

    private static String processWeatherTextInstruction(String text) {
        String ret = "";
        try {
            String ak = "AYdvDMsaGG9fYBw8blFPGkiL";
            String location = text;
//                String location = URLEncoder.encode("上海");
            Map<String, String> paramsMap = new HashMap<String, String>();
            paramsMap.put("location", location);
            paramsMap.put("output", "json");
            paramsMap.put("ak", ak);
            String paramsStr = toQueryString(paramsMap);
            String wholeStr = new String("/geocoder/v2/?" + paramsStr + ak);
            String tempStr = URLEncoder.encode(wholeStr, "UTF-8");
            String sn = UtilFunctions.md5(tempStr);
            String url = "http://api.map.baidu.com/telematics/v3/weather";
            HttpClient client = new DefaultHttpClient();
            HttpGet gm = new HttpGet(url + "?location=" + location + "&output=json&ak=" + ak + "&sn=" + sn);
            HttpResponse response = client.execute(gm);
            String msg = CommonUtils.getString(response.getEntity().getContent(), "utf-8");
            JSONObject obj = new JSONObject(msg);
            if("0".equals(obj.getString("error"))) {
                JSONObject result = obj.getJSONArray("results").getJSONObject(0);
                String city = result.getString("currentCity");
                String pm25 = result.getString("pm25");
                JSONArray list = result.getJSONArray("weather_data");
                JSONObject today = list.getJSONObject(0);
                JSONObject tomorrow = list.getJSONObject(1);
                JSONObject d2 = list.getJSONObject(2);
                JSONObject d3 = list.getJSONObject(3);
                String date = today.getString("date");
                String date2 = d2.getString("date"), date3 = d3.getString("date");
                String weather0 = today.getString("weather"), weather1 = tomorrow.getString("weather"), weather2 = d2.getString("weather"), weather3 = d3.getString("weather");
                String wind0 = today.getString("wind"), wind1 = tomorrow.getString("wind"), wind2 = d2.getString("wind"), wind3 = d3.getString("wind");
                String temperature0 = today.getString("temperature"), temperature1 = tomorrow.getString("temperature"),
                    temperature2 = d2.getString("temperature"), temperature3 = d3.getString("temperature");
                ret = city + date + weather0 + "，" + wind0 + "，" + temperature0 + "，明日" + weather1 + "，" + wind1 + "，" + temperature1 +
                        "，" + date2 + weather2 + "，" + wind2 + "，" + temperature2 + "，" + date3 + weather3 + "，" + wind3 + "，" + temperature3;
                return ret;
            } else {
                return obj.getString("status");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return "发生错误";
        }
    }

    private static String processRmTeamTextInstruction(String text) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            String filename = sdf.format(new Date());
            File f = new File("D:\\引领全服活跃" + filename + ".xls");
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("明细");
            int rownum = -1;
            for(int i = 3; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = row.getCell(1);
                if(cell != null && cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    if(text.equals(((int)cell.getNumericCellValue()) + "")) {
                        rownum = i;
                        break;
                    }
                }
            }
            if(rownum != -1) {
                sheet = workbook.getSheet("摘要");
                HSSFRow row = sheet.getRow(rownum);
                int d3 = 0, d7 = 0, dmonth = 0;
                double average = 0d;
                HSSFCell cell = row.getCell(0);
                String nick = cell.getRichStringCellValue().getString();
                cell = row.getCell(2);
                if(cell != null) {
                    d3 = (int)cell.getNumericCellValue();
                }
                cell = row.getCell(3);
                if(cell != null) {
                    d7 = (int)cell.getNumericCellValue();
                }
                cell = row.getCell(4);
                if(cell != null) {
                    dmonth = (int)cell.getNumericCellValue();
                }
                cell = row.getCell(5);
                if(cell != null) {
                    average = cell.getNumericCellValue();
                }
                DecimalFormat df = new DecimalFormat("#,##0.00");
                return nick + "(" + text + ")三日活跃：" + d3 + "，七日活跃：" + d7 + "，当月活跃：" + dmonth + "，当月平均每日活跃：" + df.format(average);
            } else {
                return "找不到该QQ号：" + text;
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            return "系统出错";
        }
    }

    private static String processRmTextInstruction(String text) {
        try {
            DecimalFormat df = new DecimalFormat("0");
            File f = new File("D:\\excel\\节奏大师歌曲.xls");
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("歌曲");
            String song = text;
            char key = '0';
            String lv = null;
            if(song.length() >= 2) {
                char keynum = text.charAt(text.length() - 2);
                String level = song.substring(song.length() - 1);
                if((keynum == '4' || keynum == '5' || keynum == '6')) {
                    if(level.equalsIgnoreCase("h") || level.equalsIgnoreCase("n") || level.equalsIgnoreCase("e")) {
                        key = keynum;
                        lv = level;
                        song = song.substring(0, song.length() - 2);
                    }
                }
            }
            List<String> matchsongs = new ArrayList<String>();
            String returnmsg = null;
            for(int i = 3; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                HSSFCell cell = row.getCell(0);
                String name = cell.getRichStringCellValue().getString();
                if(name.equalsIgnoreCase(song)) {
                    if(key != '0' && lv != null) {
                        int keyoffset = 0, lvoffset = 0;
                        if(key == '4') {
                            keyoffset = 0;
                        } else if(key == '5') {
                            keyoffset = 1;
                        } else if(key == '6') {
                            keyoffset = 2;
                        }
                        if(lv.equalsIgnoreCase("h")) {
                            lvoffset = 2;
                        } else if(lv.equalsIgnoreCase("n")) {
                            lvoffset = 1;
                        } else if(lv.equalsIgnoreCase("e")) {
                            lvoffset = 0;
                        }
                        cell = row.getCell(4);
                        double bpm = cell.getNumericCellValue();
                        cell = row.getCell(5);
                        String length = cell.getRichStringCellValue().getString();
                        int place = 6 + keyoffset * 24 + lvoffset * 8;
                        cell = row.getCell(place);
                        String nd = "";
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            returnmsg = song + "，长度=" + length + "，BPM=" + bpm + "，" + key + lv + "难度=" + cell.getNumericCellValue();
                        } else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                            returnmsg = song + "，长度=" + length + "，BPM=" + bpm + "，" + key + lv + "难度=" + cell.getRichStringCellValue().getString().trim();
                        } else {
                            returnmsg = song + "，长度=" + length + "，BPM=" + bpm + "，" + key + lv + "难度=" + "未知";
                        }
                        place++;
                        cell = row.getCell(place);
                        returnmsg += "，满键=" + df.format(cell.getNumericCellValue());
                        place++;
                        cell = row.getCell(place);
                        returnmsg += "，满分=" + df.format(cell.getNumericCellValue());
                        place++;
                        cell = row.getCell(place);
                        returnmsg += "，SSS=" + df.format(Math.ceil(cell.getNumericCellValue()));
                        place++;
                        cell = row.getCell(place);
                        returnmsg += "，SS=" + df.format(Math.ceil(cell.getNumericCellValue()));
                        place++;
                        cell = row.getCell(place);
                        returnmsg += "，S=" + df.format(Math.ceil(cell.getNumericCellValue()));
                        place++;
                        cell = row.getCell(place);
                        if(cell != null) {
                            if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                                returnmsg += "，得分：" + ((int)cell.getNumericCellValue()) + "/-";
                            } else if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                                returnmsg += "，得分：" + cell.getRichStringCellValue().getString().replaceAll("\n", "/");
                            }
                        }
                    } else {
                        //每种难度，满key
                        cell = row.getCell(4);
                        double bpm = cell.getNumericCellValue();
                        cell = row.getCell(5);
                        String length = cell.getRichStringCellValue().getString();
                        returnmsg = song + "，长度=" + length + "，BPM=" + bpm;
                        double curkey;
                        String curlv;
                        cell = row.getCell(6);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(7);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，4e=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(14);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(15);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，4n=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(22);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(23);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，4h=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(30);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(31);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，5e=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(38);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(39);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，5n=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(46);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(47);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，5h=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(54);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(55);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，6e=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(62);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(63);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，6n=lv" + curlv + "/" + df.format(curkey);
                        cell = row.getCell(70);
                        curlv = cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC ? df.format(cell.getNumericCellValue()) : cell.getRichStringCellValue().getString().trim();
                        cell = row.getCell(71);
                        curkey = cell.getNumericCellValue();
                        returnmsg = returnmsg + "，6h=lv" + curlv + "/" + df.format(curkey);
                    }
                    break;
                } else if(name.indexOf(song) >= 0) {
                    matchsongs.add(name);
                }
            }
            if(returnmsg == null) {
                returnmsg = "名字包含“" + song + "”的歌曲共有" + matchsongs.size() + "首:";
                for(String s : matchsongs) {
                    returnmsg += "《" + s + "》，";
                }
                returnmsg = returnmsg.substring(0, returnmsg.length() - 1) + "。";
            }
            return returnmsg;
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
        }
        return "程序出错";
    }

    private static String processOgzqTextInstruction(String text) {
        StringBuffer ret = new StringBuffer();
        if(text.startsWith("功能")) {
            text = text.substring(3);
            Method[] methods = OgzqUtils.class.getMethods();
            for(Method m : methods) {
                if(m.isAnnotationPresent(PropertyDescription.class)){
                    PropertyDescription annotation = m.getAnnotation(PropertyDescription.class);
                    if(annotation.desc().equals(text)) {
                        try {
                            Object obj = m.invoke(null, new Object[]{});
                            return obj.toString();
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }
                        return "done";
                    }
                }
            }
            return "找不到对应的功能";
        }
        if(text.startsWith("竞技：") || text.startsWith("竞技:")) {
            String name;
            if(text.startsWith("竞技：")) {
                name = text.substring(text.indexOf("竞技：") + "竞技：".length()).trim();
            } else {
                name = text.substring(text.indexOf("竞技:") + "竞技:".length()).trim();
            }
            List<String> keys = ArenaThread.GETIDS();
            for(String key : keys) {
                String[] against = ArenaThread.JJCAgainst.get(key);
                if(against != null) {
                    for(String a : against) {
                        if(a.split("[|]")[2].equals(name)) {
                            ret.append(",").append(key).append("/").append(ArenaThread.NICK.get(key));
                            break;
                        }
                    }
                }
            }
            if(ret.length() > 0) {
                return "竞技场遇到" + name + "的号有：" + ret.substring(1);
            } else {
                return "竞技场没有遇到" + name + "的号";
            }
        }
        return null;
    }

    private static String getSendString(String from, String to, String timestamp, String content, String msgId) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>").append("\n");
        sb.append("<ToUserName><![CDATA[").append(to).append("]]></ToUserName>").append("\n");
        sb.append("<FromUserName><![CDATA[").append(from).append("]]></FromUserName>").append("\n");
        sb.append("<CreateTime>").append((int)(new Date().getTime() / 1000)).append("</CreateTime>").append("\n");
        sb.append("<MsgType><![CDATA[text]]></MsgType>").append("\n");
        sb.append("<Content><![CDATA[").append(content).append("]]></Content>").append("\n");
        sb.append("<FuncFlag>0</FuncFlag>").append("</xml>").append("\n");
        return sb.toString();
    }

    private static boolean verifyRequest(String signature, String timestamp, String nonce) {
        String[] str = {"sevarsti", timestamp, nonce};
        Arrays.sort(str);
        String bigStr = str[0] + str[1] + str[2];
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(bigStr.getBytes());
            byte[] bb = md.digest();
            bigStr = bytes2Hex(bb);
            return bigStr.equals(signature);
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
            return true;
        }
    }

    public static String bytes2Hex(byte[] bts) {
        String des = "";
        String tmp = null;
        for(int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if(tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

    public static Map Dom2Map(Element e) {
        Map map = new HashMap();
        List list = e.elements();
        if(list.size() > 0) {
            for(int i = 0; i < list.size(); i++) {
                Element iter = (Element) list.get(i);
                List mapList = new ArrayList();

                if(iter.elements().size() > 0) {
                    Map m = Dom2Map(iter);
                    if(map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(m);
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(m);
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), m);
                    }
                } else {
                    if(map.get(iter.getName()) != null) {
                        Object obj = map.get(iter.getName());
                        if(!obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = new ArrayList();
                            mapList.add(obj);
                            mapList.add(iter.getText());
                        }
                        if(obj.getClass().getName().equals("java.util.ArrayList")) {
                            mapList = (List) obj;
                            mapList.add(iter.getText());
                        }
                        map.put(iter.getName(), mapList);
                    } else {
                        map.put(iter.getName(), iter.getText());
                    }
                }
            }
        } else {
            map.put(e.getName(), e.getText());
        }
        return map;
    }

    private static void log(String fakeId, String content, Date time, int type) { //type=1: 收到; type=2: 发送
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String route = WeixinHandler.class.getResource("").getPath();
        route = route.substring(0, route.indexOf("WEB-INF"));
        File f = new File(route + "\\log\\weixin.log");
        FileUtils.WriteFile(f, sdf.format(time) + "\t" + type + "\t" + fakeId + "\t" + content + "\r\n", true);
    }

    private static String toQueryString(Map<?, ?> data)
            throws UnsupportedEncodingException {
        StringBuffer queryString = new StringBuffer();
        for (Map.Entry<?, ?> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode((String) pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }
}
