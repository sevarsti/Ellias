package com.saille.util;

import org.apache.log4j.Logger;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.Invocable;
import java.security.MessageDigest;
import java.io.*;
import java.util.List;
import java.util.ArrayList;

public class UtilFunctions {
    private final static Logger LOGGER = Logger.getLogger(UtilFunctions.class);
    public static String cutString(String str, int len) {
        if(str == null) {
            return null;
        }
        if(str.length() <= len) {
            return str;
        }
        return str.substring(0, len) + "...";
    }

    public static boolean greaterThan(String str, double d) {
        double d1 = Double.parseDouble(str);
        return d1 > d;
    }

    public static boolean lessThan(String str, double d) {
        if(str == null || str.length() == 0) {
            return false;
        }
        double d1 = Double.parseDouble(str);
        return d1 < d;
    }

    public static String md5(String s) throws Exception {
        return md5(s.getBytes());
    }
    public static String md5(byte[] s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(s);
        byte b[] = md.digest();
        int i;
        StringBuffer buf = new StringBuffer("");
        for(int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if(i < 0) {
                i += 256;
            }
            if(i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        return buf.toString();
    }

    public static void LogError(Logger LOGGER, Exception ex) {
        if(LOGGER != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            LOGGER.error("·¢Éú´íÎó£º" + sw.toString());
        } else {
            ex.printStackTrace();
        }
    }

    public static Object fullyCopy(Object in) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream ops = new ObjectOutputStream(baos);
            ops.writeObject(in);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            Object cloneObj = ois.readObject();
            return cloneObj;
        } catch(Exception ex) {
            LogError(LOGGER, ex);
            return null;
        }
    }

    public static String reptString(String text, int time) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < time; i++) {
            sb.append(text);
        }
        return sb.toString();
    }

    public static String convertList2Table(List list, boolean[] number) {
        StringBuffer sb = new StringBuffer();
        sb.append("<table border=\"0\" bgcolor=\"black\" cellpadding=\"1\" cellspacing=\"1\">");
        if(list.size() > 0) {
            sb.append("<tr class=\"head\">");
            Object obj = list.get(0);
            if(obj instanceof Object[]) {
                Object[] titles = (Object[]) obj;
                for(int i = 0; i < titles.length; i++) {
                    Object t = titles[i];
                    sb.append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, " + i + ", ").append(number.length > i ? number[i] : false).append(")\">").append(t.toString()).append("</th>");
                }
            } else if(obj instanceof List) {
                List titles = (List) obj;
                for(int i = 0; i < titles.size(); i++) {
                    Object t = titles.get(i);
                    sb.append("<th style=\"cursor:pointer;text-decoration:underline;\" onclick=\"resort(this, " + i + ", ").append(number.length > i ? number[i] : false).append(")\">").append(t.toString()).append("</th>");
                }
            }
            sb.append("</tr>");
        }
        for(int i = 1; i < list.size(); i++) {
            sb.append("<tr class=\"row").append(i % 2 + 1).append("\">");
            Object obj = list.get(i);
            if (list.get(i) instanceof Object[]) {
                Object[] titles = (Object[]) obj;
                for(Object t : titles) {
                    sb.append("<td>").append(t.toString()).append("</td>");
                }
            } else if(obj instanceof List) {
                List titles = (List) obj;
                for(Object t : titles) {
                    sb.append("<td>").append(t.toString()).append("</td>");
                }
            }
            sb.append("</tr>");
        }
        sb.append("</table>");
        return sb.toString();
    }

    public static String getJsByFile(String[] filename, String funcName, Object... params) {
        try {
            StringBuilder sb = new StringBuilder();
            for(String fname : filename) {
                File f = new File(fname);
                FileInputStream fis = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
                BufferedReader br = new BufferedReader(isr);
                String tmp;
                while((tmp = br.readLine()) != null) {
                    sb.append(tmp).append("\r\n");
                }
                br.close();
                isr.close();
                fis.close();
            }
            ScriptEngineManager sem = new ScriptEngineManager();
            ScriptEngine se = sem.getEngineByName("js");
            se.eval(sb.toString());
            Invocable inv = (Invocable) se;
            List<Object> pp = new ArrayList<Object>();
            for(Object o : params) {
                pp.add(o);
            }
            Object[] ppp = new Object[pp.size()];
            pp.toArray(ppp);
            String res = (String) inv.invokeFunction(funcName, ppp);
            return res;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}