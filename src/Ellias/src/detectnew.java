import com.GlobalConstant;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class detectnew {
    private File f;
    private OutputStream os;
    private BufferedWriter writer;
    private List<Map<String, String>> allIds = new ArrayList<Map<String, String>>();
    private boolean cn = false;

    public detectnew(boolean type) {
        cn = type;
    }

    public static void main(String args[]) {
        new detectnew(args.length > 0).run();
    }

    private X509TrustManager xtm = new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) {
//             System.out.println("---cert: " + chain[0].toString() + ", 认证方式: " + authType);
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    private HostnameVerifier hnv = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
//        System.out.println("hostname:111111111111111111111        " + hostname);
            return true;
        }
    };

    public void run() {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
            if(sslContext != null) {
                HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            }
            HttpsURLConnection.setDefaultHostnameVerifier(hnv);
        } catch(GeneralSecurityException gse) {
            //打印出一些错误信息和处理这个异常
            gse.printStackTrace();
        }
        String URL = cn ? "https://bbs.sjtu.cn/" : "https://bbs.sjtu.edu.cn/";
        System.out.println(URL);
        init();
        List<String> ids = read();
        System.out.println("size: " + ids.size());
        for(int i = 0; i < ids.size(); i++) {
            boolean error = true;
            while(error) {
                String id = (String) ids.get(i);
                System.out.println(i + "/" + ids.size() + ": " + id);
                try {
                    StringBuffer sb = new StringBuffer();
                    URL url = new URL(URL + "bbsqry?userid=" + id);
//                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String tmp = null;
                    while((tmp = in.readLine()) != null) {
                        sb.append(tmp);
                    }
                    tmp = sb.toString();
                    tmp = getInfo(id, tmp);
                    log(tmp);
                    error = false;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        end();
        allIds = bubbleSort(allIds, 0, allIds.size());
        try {
            writeAll(allIds);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void init() {
        try {
            this.f = new File(GlobalConstant.DISKPATH + "vbs\\target1new.txt");
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
            File f = new File(GlobalConstant.DISKPATH + "vbs\\target_new.txt");
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
            this.writer.write(str);
            this.writer.write("\r\n");
            this.writer.flush();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void writeAll(List<Map<String, String>> list) throws Exception {
        File f = new File(GlobalConstant.DISKPATH + "vbs\\target1new.txt");
        FileOutputStream os = new FileOutputStream(f);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
        writer.write("id\tlogin time\tlife\tip\tpost\tlogintimes\tage\r\n");
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

    private static List<Map<String, String>> bubbleSort(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        for(int i = start; i < end; i++) {
            boolean needSwap;
            for(int j = i + 1; j < end; j++) {
                int s = 9999, e = 9999;
                try {
                    s = Integer.parseInt(list.get(j).get("life"));
                } catch(Exception ex) {
                }
                try {
                    e = Integer.parseInt(list.get(i).get("life"));
                } catch(Exception ex) {
                }
                if(s > e) {
                    needSwap = false;
                } else if(s < e) {
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

    private static void swap(List<Map<String, String>> a, int b, int c) {
        Map<String, String> temp = a.get(b);
        a.set(b, a.get(c));
        a.set(c, temp);
    }
}