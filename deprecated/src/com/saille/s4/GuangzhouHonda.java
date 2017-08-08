package com.saille.s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class GuangzhouHonda {
    public static void main(String[] args) {
        writeXls();
    }

    public static void combine() {
        try {
            File out = new File("D:\\4s\\广州本田\\full.txt");
            FileOutputStream fos = new FileOutputStream(out);
            byte[] b = new byte[1024];
            for(int i = 0; i < 79; i++) {
                File in = new File("D:\\4s\\广州本田\\out" + i + ".txt");
                FileInputStream fis = new FileInputStream(in);
                while(fis.read(b) > 0) {
                    fos.write(b);
                }
                fis.close();
            }
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeXls() {
        List list = new ArrayList();
        try {
            for(int i = 0; i < 79; i++) {
                File f = new File("D:\\4s\\广州本田\\out" + i + ".txt");
                FileInputStream fis = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String tmp;
                while((tmp = br.readLine()) != null) {
                    sb.append(tmp).append("\r\n");
                }
                String content = sb.toString();
                while(content.indexOf("<dl>") != -1) {
                    content = content.substring(content.indexOf("<dl>") + "<dl>".length());
                    String sub = content.substring(0, content.indexOf("</dl>"));
                    sub = sub.substring(sub.indexOf("<dt>") + "<dt>".length());
                    String title = sub.substring(0, sub.indexOf("<")).trim();
                    String address = null;
                    if(sub.indexOf("地址：") != -1) {
                        sub = sub.substring(sub.indexOf("地址：</span>") + "地址：</span>".length());
                        address = sub.substring(0, sub.indexOf("<")).trim();
                    }
                    String quhao = null;
                    if(sub.indexOf("电话区号：") != -1) {
                        sub = sub.substring(sub.indexOf("电话区号：</span>") + "电话区号：</span>".length());
                        quhao = sub.substring(0, sub.indexOf("<")).trim();
                    }
                    String sos = null;
                    if(sub.indexOf("24小时救援电话：") != -1) {
                        sub = sub.substring(sub.indexOf("24小时救援电话：</span>") + "24小时救援电话：</span>".length());
                        sos = sub.substring(0, sub.indexOf("<")).trim();
                    }
                    String query = null;
                    if(sub.indexOf("咨询电话：") != -1) {
                        sub = sub.substring(sub.indexOf("咨询电话：</span>") + "咨询电话：</span>".length());
                        query = sub.substring(0, sub.indexOf("<")).trim();
                    }
                    String service = null;
                    if(sub.indexOf("售后服务电话：") != -1) {
                        sub = sub.substring(sub.indexOf("售后服务电话：</span>") + "售后服务电话：</span>".length());
                        service = sub.substring(0, sub.indexOf("<")).trim();
                    }
                    String[] s = new String[6];
                    s[0] = title;
                    s[1] = address;
                    s[2] = quhao;
                    s[3] = sos;
                    s[4] = query;
                    s[5] = service;
                    list.add(s);
                }
            }
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            for(int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i);
                HSSFCell cell = null;
                for(int j = 0; j < ((String[]) list.get(i)).length; j++) {
                    cell = row.createCell((short) j);
                    cell.setCellType(1);
                    cell.setCellValue(new HSSFRichTextString(((String[]) list.get(i))[j]));
                }
            }
            File f = new File("D:\\4s\\广州本田\\out.xls");
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void shortHtml() {
        try {
            for(int i = 0; i < 79; i++) {
                System.out.println(i);
                File f = new File("D:\\4s\\广州本田\\" + i + ".txt");
                FileInputStream fis = new FileInputStream(f);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();
                String tmp;
                while((tmp = br.readLine()) != null) {
                    sb.append(tmp).append("\r\n");
                }
                String content = sb.toString();
                content = content.substring(content.indexOf("<!--repeat-->") + "<!--repeat-->".length());
                content = content.substring(0, content.indexOf("<!--repeat-->"));
                File of = new File("D:\\4s\\广州本田\\out" + i + ".txt");
                FileOutputStream fos = new FileOutputStream(of);
                fos.write(content.getBytes());
                fos.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void loadHtml() {
        try {
            for(int i = 0; i < 79; i++) {
                System.out.println(i);
                String url = "http://www.ghac.cn/sales/serviceshop_list.jsp?pageno=" + i;
                System.out.println(url);
                URL u = new URL(url);
                InputStream is = u.openStream();
                File outfile = new File("D:\\4s\\广州本田\\" + i + ".txt");
                FileOutputStream fos = new FileOutputStream(outfile);
                int b;
                while((b = is.read()) >= 0) {
                    fos.write(b);
                }
                fos.close();
                is.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}