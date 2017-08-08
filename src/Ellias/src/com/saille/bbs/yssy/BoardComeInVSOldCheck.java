package com.saille.bbs.yssy;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ArrayUtils;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.*;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import com.saille.util.DateUtils;

public class BoardComeInVSOldCheck {
    private static HttpClient client;
    private static GetMethod gm;

    private static String readLine() {
        try {
            InputStreamReader stdin = new InputStreamReader(System.in);
            BufferedReader bufin = new BufferedReader(stdin);
            String str = bufin.readLine();
            return str;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        client = new HttpClient();
        try {
            System.out.print("请输入结束时间（默认为当天）：");
            String str = readLine();
            Integer end = null;
            Integer start = null;
            if(!StringUtils.isEmpty(str)) {
                end = DateUtils.convert2Int(str);
            }
            if(end == null) {
                end = DateUtils.convert2Int(new Date());
            }
            System.out.println("请输入年份（默认为1年前）：");
            str = readLine();
            if(!StringUtils.isEmpty(str)) {
                start = Integer.parseInt(str) * 10000 + (end % 10000);
            } else {
                start = end - 10000;
            }
            if(start == null) {
                start = end - 10000;
            }
            System.out.println("日期：" + start + "-" + end);
            Map<Integer, Map<String, Map<String, String>>> dateChecks = new HashMap<Integer, Map<String, Map<String, String>>>();
            List<Integer> years = new ArrayList<Integer>();
            while(end >= start) {
                Map<String, Map<String, String>> startList = genIncomes(end);
                dateChecks.put(end / 10000, startList);
                years.add(end / 10000);
                end -= 10000;
            }
            for(int i = 0; i < years.size() - 1; i++) {
                checkIncome(dateChecks.get(years.get(i + 1)), dateChecks.get(years.get(i)));
            }
            for(int i = 0; i < years.size() - 1; i++) {
                List<Map<String, String>> yearList = new ArrayList<Map<String, String>>();
                for(String key : dateChecks.get(years.get(i)).keySet()) {
                    yearList.add(dateChecks.get(years.get(i)).get(key));
                }
                quickSortResult(yearList, 0, yearList.size(), 2);
                int totalCount = 0, mid = 0;
                for(Map<String, String> m : yearList) {
                    totalCount += Integer.parseInt(m.get("between"));
                }
                if(yearList.size() % 2 == 0) {
                    mid = (Integer.parseInt(yearList.get(yearList.size() / 2).get("between")) + Integer.parseInt(yearList.get(yearList.size() / 2 - 1).get("between"))) / 2;
                } else {
                    mid = Integer.parseInt(yearList.get(yearList.size() / 2 + 1).get("between"));
                }
                System.out.println("year: " + years.get(i) + "，板面数：" + yearList.size() + "，平均进板：" + (totalCount / yearList.size()) + "，中位数：" + mid);
                for(int j = 0; j < 10; j++) {
                    System.out.println((j + 1) + ": " + yearList.get(j).get("board") + "/" + yearList.get(j).get("name") + "/" + yearList.get(j).get("between"));
                }
            }

            System.out.println("查询区间：" + start + "-" + end);
//            for(String s : output) {
//                System.out.println(s);
//            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void checkIncome(Map<String, Map<String, String>> start, Map<String, Map<String, String>> end) throws Exception {
        URL url = BoardComeInVSOldCheck.class.getResource("/config.properties");
        InputStream inputstream = url.openStream();
        Properties properties = new Properties();
        properties.load(inputstream);
        for(String boardName : end.keySet()) {
            int endIncome = Integer.parseInt(end.get(boardName).get("count"));
            int startIncome = 0;
            if(start.containsKey(boardName)) {
                startIncome = Integer.parseInt(start.get(boardName).get("count"));
            }
            int between = endIncome - startIncome;
            if(between < 0) {
                between += Integer.MAX_VALUE;
            }
            end.get(boardName).put("between", between + "");
            end.get(boardName).put("district", String.valueOf(properties.get(boardName)));
        }
    }

    private static List<Map<String, String>> generateIndex(List<Map<String, String>> list) {
        for(int i = 0; i < list.size(); i++) {
            int count = 1;
            for(int j = 0; j < list.size(); j++) {
                if(j == i) {
                    continue;
                }
                if(Integer.parseInt((String) ((Map) list.get(i)).get("between")) < Integer.parseInt((String) ((Map) list.get(j)).get("between"))) {
                    count++;
                }
            }
            ((Map) list.get(i)).put("betweenindex", String.valueOf(count));
        }
        return list;
    }

    private static List<String> generateResult(List<Map<String, String>> list) throws Exception {
        List ret = new ArrayList();
        String format = "%4s|%s|%-15s|%8s|%6s|%9s|%4s";
        ret.add("排名| |板面           |累计进板|  区间| 平均时间|区间排名");
        for(Map s : list) {
            if(((String) s.get("district")).equals("-")) {
                continue;
            }
            Object[] params = {s.get("index"), s.get("district"), s.get("board"), s.get("count"), s.get("between"), s.get("avg"), s.get("betweenindex")};
            String ss = String.format(format, params);
            ret.add(new String(ss.getBytes("ISO-8859-1"), "GBK"));
        }
        return ret;
    }

    private static List<Map<String, String>> quickSortResult(List<Map<String, String>> list, int start, int end, int type) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(type == 1) {
                if(((String) ((Map) list.get(i)).get("district")).compareTo((String) ((Map) list.get(pos)).get("district")) < 0) {
                    needSwap = true;
                } else
                if(((String) ((Map) list.get(i)).get("district")).compareTo((String) ((Map) list.get(pos)).get("district")) > 0) {
                    needSwap = false;
                } else
                if(Integer.parseInt((String) ((Map) list.get(i)).get("between")) < Integer.parseInt((String) ((Map) list.get(pos)).get("between"))) {
                    needSwap = false;
                } else
                if(Integer.parseInt((String) ((Map) list.get(i)).get("between")) > Integer.parseInt((String) ((Map) list.get(pos)).get("between"))) {
                    needSwap = true;
                }
            } else if(type == 2) {
                if(Integer.parseInt((String) ((Map) list.get(i)).get("between")) < Integer.parseInt((String) ((Map) list.get(pos)).get("between"))) {
                    needSwap = false;
                } else
                if(Integer.parseInt((String) ((Map) list.get(i)).get("between")) > Integer.parseInt((String) ((Map) list.get(pos)).get("between"))) {
                    needSwap = true;
                }
            }
            if(needSwap) {
                Map tmp = (Map) list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        quickSortResult(list, start, pos, type);
        quickSortResult(list, pos + 1, end, type);
        return list;
    }

    private static Map<String, Map<String, String>> genIncomes(Integer d) throws Exception {
        String title = generateTitle(d);
        title = URLEncoder.encode(title, "gb2312");
        String url = "http://bbs.sjtu.edu.cn/bbsbfind?type=1&board=BBSLists&title=" + title + "&dt=" + checkDayCount(d.intValue());
        System.out.println(url);
        gm = new GetMethod(url);
        client.executeMethod(gm);
        String content = BMLoginCheck.getString(gm.getResponseBodyAsStream(), gm.getResponseCharSet());
        if(content.indexOf("共找到 0 篇文章符合条件") >= 0) {
            System.out.println("--------------error--------------");
            System.exit(1);
        }
        url = "http://bbs.sjtu.edu.cn/" + content.substring(content.indexOf("bbscon?board=BBSLists&file="), content.indexOf("&num="));
        gm = new GetMethod(url);
        client.executeMethod(gm);
        content = BMLoginCheck.getString(gm.getResponseBodyAsStream(), gm.getResponseCharSet());
        content = content.replaceAll("\r", "");
        String[] contents = content.split("\n");
        List<String> boards = new ArrayList();
        boolean begin = false;
        for(String s : contents) {
            if(begin) {
                if(s.indexOf("总平均") != -1) {
                    break;
                }
                boards.add(s);
            } else if(s.indexOf("累积时间") != -1) {
                begin = true;
            }
        }

        Map<String, Map<String, String>> ret = new HashMap<String, Map<String, String>>();
        for(String s : boards) {
            Map m = new HashMap();
            s = s.substring(30);
            String boardname = s.substring(0, 15).trim();
            m.put("board", boardname);
            s = s.substring(17);
            String chinesename = new String(ArrayUtils.subarray(s.getBytes(), 0, 26));
            chinesename = chinesename.trim();
            m.put("name", chinesename);
            s = new String(ArrayUtils.subarray(s.getBytes(), 26, s.getBytes().length)).trim();
            String count = s.substring(0, s.indexOf(" "));
            m.put("count", count);
            s = s.substring(s.indexOf(" ") + 1);
            String avg = s.substring(s.lastIndexOf(" ") + 1);
            String time = s.substring(0, s.lastIndexOf(" ")).trim();
            m.put("time", time);
            m.put("avg", avg);
            ret.put(boardname, m);
        }
        return ret;
    }

    private static String generateTitle(Integer d) {
        Date date = DateUtils.convert2Date(d);
        if(date == null) {
            return "";
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日");
        String s = "[" + sdf.format(date) + " " + getDay(c.get(7)) + "] 本站各讨论区排行榜";
        return s;
    }

    private static int checkDayCount(int d) {
        long now = new Date().getTime();
        long start = DateUtils.convert2Date(Integer.valueOf(d)).getTime();
        return (int) ((now - start) / 1000L / 60L / 60L / 24L + 1L);
    }

    private static String getDay(int d) {
        switch(d) {
            case 1:
                return "星期天";
            case 2:
                return "星期一";
            case 3:
                return "星期二";
            case 4:
                return "星期三";
            case 5:
                return "星期四";
            case 6:
                return "星期五";
            case 7:
                return "星期六";
        }
        return "";
    }
}
