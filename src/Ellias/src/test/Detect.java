package test;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Detect {
    private File f;
    private OutputStream os;
    private BufferedWriter writer;
    private List<Map<String, String>> allIds = new ArrayList<Map<String, String>>();

    public static void main(String args[]) {
        new Detect().run();
    }
    public void run() {
//        init();
        List<String> ids = read();
        System.out.println("size: " + ids.size());
        for(int i = 0; i < ids.size(); i++) {
            boolean error = true;
            while(error) {
                String id = ids.get(i);
                System.out.println(i + "/" + ids.size() + ": " + id);
                try {
                    StringBuffer sb = new StringBuffer();
                    URL url = new URL("http://bbs.sjtu.cn/bbsqry?userid=" + id);
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String tmp = null;
                    while((tmp = in.readLine()) != null) {
                        sb.append(tmp);
                    }
                    tmp = sb.toString();
                    tmp = getInfo(id, tmp);
//                    System.out.print(id + "\t");
//                    if(tmp.indexOf("有事请联系") != -1) {
//                        tmp = tmp.substring(tmp.indexOf("有事请联系") + "有事请联系".length());
//                        System.out.println(tmp.substring(0, tmp.indexOf("。")));
//                    } else {
//                        System.out.println("--");
//                    }
                    log(tmp);
                    error = false;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
//        end();
        quickSort(allIds, 0, allIds.size());
        try {
            writeAll(allIds);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void init() {
        try {
            this.f = new File("D:\\vbs\\target1_new.txt");
            this.os = new FileOutputStream(this.f);
            this.writer = new BufferedWriter(new OutputStreamWriter(this.os));
            this.writer.write("id\tlogin time\tlife\tip\tpost\tlogintimes\tage\r\n");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void end() {
        try {
            this.writer.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<String> read() {
        List<String> list = new ArrayList<String>();
        String tmp;
        try {
            File f = new File("D:\\vbs\\target_new.txt");
            InputStream is = new FileInputStream(f);
            BufferedReader r = new BufferedReader(new InputStreamReader(is));
            r.readLine();
            while((tmp = r.readLine()) != null) {
                list.add(tmp.split("\t")[0]);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

    private String getInfo(String id, String in) {
        StringBuffer sb = new StringBuffer();
        sb.append(id).append("\t");
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        int place = in.indexOf("网龄[</font><font class='c32'>");
        String strValue = in.substring(place + "网龄[</font><font class='c32'>".length());
        String age = strValue.substring(0, strValue.indexOf("<"));
        place = in.indexOf("上 次 在: [</font><font class='c32'>");
        strValue = in.substring(place + 33, place + 33 + 24);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("生命力:[</font><font class='c32'>");
        strValue = in.substring(place + 30, place + 30 + 3);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }

        map.put("life", strValue);
        sb.append(strValue).append("\t");

        place = in.indexOf("从 [</font><font class='c32'>");
        strValue = in.substring(place + 28, place + 28 + 15);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("文章:[</font><font class='c32'>");
        strValue = in.substring(place + 29, place + 29 + 5);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue).append("\t");

        place = in.indexOf("共上站 </font><font class='c32'>");
        strValue = in.substring(place + 29, place + 29 + 6);
        if(strValue.indexOf("<") != -1) {
            strValue = strValue.substring(0, strValue.indexOf("<"));
        }
        sb.append(strValue);
        sb.append("\t").append(age);
        map.put("info", sb.toString());
        allIds.add(map);
        return sb.toString();
    }

    private void log(String str) {
        try {
            System.out.println(str);
//            this.writer.write(str);
//            this.writer.write("\r\n");
//            this.writer.flush();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void writeAll(List<Map<String, String>> list) throws Exception{
        File f = new File("D:\\vbs\\target1_new.txt");
        FileOutputStream os = new FileOutputStream(f);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write("id\tlogin time\tlife\tip\tpost\tlogintimes\r\n");
        for(Map<String, String> s : list) {
            writer.write(s.get("info") + "\r\n");
        }
        writer.close();
        os.close();
    }

    private static List<Map<String, String>> quickSort(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap;
            if(Integer.parseInt(list.get(i).get("life")) > Integer.parseInt(list.get(pos).get("life"))) {
                needSwap = false;
            } else if(Integer.parseInt(list.get(i).get("life")) < Integer.parseInt(list.get(pos).get("life"))) {
                needSwap = true;
            } else {
                needSwap = list.get(i).get("id").toUpperCase().compareTo(list.get(pos).get("id").toUpperCase()) < 0;
            }
            if(needSwap) {
                Map<String, String> tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        if(pos - start > 100) {
            quickSort(list, start, pos);
        } else {
            bubbleSort(list, start, pos);
        }
        if(end - pos - 1 > 100) {
            quickSort(list, pos + 1, end);
        } else {
            bubbleSort(list, pos + 1, end);
        }
        return list;
    }

    public static List<Map<String, String>> bubbleSort(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        for(int i = start; i < end; i++) {
            boolean needSwap;
            for(int j = i + 1; j < end; j++) {
                if(Integer.parseInt(list.get(j).get("life")) > Integer.parseInt(list.get(i).get("life"))) {
                    needSwap = false;
                } else if(Integer.parseInt(list.get(j).get("life")) < Integer.parseInt(list.get(i).get("life"))) {
                    needSwap = true;
                } else {
                    needSwap = list.get(j).get("id").toUpperCase().compareTo(list.get(i).get("id").toUpperCase()) < 0;
                }
                if(needSwap) {
                    swap(list, i, j);
                }
            }
        }
        return list;
    }

    public static void swap(List<Map<String, String>> a, int b, int c) {
        Map<String, String> temp = a.get(b);
        a.set(b, a.get(c));
        a.set(c, temp);
    }
}