package com.saille.s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Nissan {
    public static Connection connection;
    public static PreparedStatement stmt;

    public static void main(String[] args) {
        load();
    }

    public static void load() {
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        List list = new ArrayList();

        List result = new ArrayList();
        try {
            File f = new File("D:\\4s\\Nissan\\cities.xls");
            FileInputStream fis = new FileInputStream(f);
            workbook = new HSSFWorkbook(fis);
            sheet = workbook.getSheet("Sheet0");
            for(int i = 0; i < 2932; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] s = new String[6];
                for(int j = 0; j < 6; j++) {
                    s[j] = row.getCell((short) j).getRichStringCellValue().getString();
                }
                list.add(s);
            }
            for(int i = 0; i < list.size(); i++) {
                System.out.println(i + "/" + list.size());
                URL url = new URL("http://www.nissan.com.cn/SearchResults.php?CAR_TP_ID=" + ((String[]) list.get(i))[0] + "&SP_CANTON=" + ((String[]) list.get(i))[2] + "&SP_CITY=" + ((String[]) list.get(i))[4]);
                InputStream is = url.openStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                StringBuffer sb = new StringBuffer();

                String tmp;
                while((tmp = br.readLine()) != null) {
                    sb.append(tmp).append("\r\n");
                }
                tmp = sb.toString();
                WriteToFile(tmp, ((String[]) list.get(i))[0] + "_" + ((String[]) list.get(i))[2] + "_" + ((String[]) list.get(i))[4] + "_" + ((String[]) list.get(i))[1] + "_" + ((String[]) list.get(i))[3] + "_" + ((String[]) list.get(i))[5] + "_" + i);
                while(tmp.indexOf("dealer_dszd") != -1) {
                    tmp = tmp.substring(tmp.indexOf("dealer_dszd\">") + "dealer_dszd\">".length());
                    String name = tmp.substring(0, tmp.indexOf("<")).trim();
                    tmp = tmp.substring(tmp.indexOf("dealer_dazdtext\">") + "dealer_dazdtext\">".length());
                    String info = tmp.substring(0, tmp.indexOf("</td>")).trim();
                    String[] s = new String[9];
                    for(int j = 0; j < 6; j++) {
                        s[j] = ((String[]) list.get(i))[j];
                    }
                    s[6] = name;
                    s[7] = info;
                    s[8] = String.valueOf(i);
                    for(int j = 0; j < 7; j++) {
                        System.out.print(s[j] + "\t");
                    }
                    System.out.println();
                    result.add(s);
                    WriteToDB(s);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        try {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet();
            for(int i = 0; i < result.size(); i++) {
                HSSFRow row = sheet.createRow(i);
                for(int j = 0; j < ((String[]) result.get(i)).length; j++) {
                    HSSFCell cell = row.createCell((short) j);
                    cell.setCellType(1);
                    cell.setCellValue(new HSSFRichTextString(((String[]) result.get(i))[j]));
                }
            }
            File out = new File("D:\\4s\\Nissan\\out.xls");
            FileOutputStream fos = new FileOutputStream(out);
            workbook.write(fos);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void genExcel() {
        try {
            File f = new File("D:\\4s\\Nissan\\param.xls");
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("car");
            List cars = new ArrayList();
            List provinces = new ArrayList();

            for(int i = 0; i < 14; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] s = new String[2];
                HSSFCell cell = row.getCell(0);
                s[0] = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
                cell = row.getCell(1);
                s[1] = cell.getRichStringCellValue().getString();
                cars.add(s);
            }
            sheet = workbook.getSheet("province");
            for(int i = 0; i < 31; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] s = new String[2];
                HSSFCell cell = row.getCell(0);
                s[0] = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
                cell = row.getCell(1);
                s[1] = cell.getRichStringCellValue().getString();
                provinces.add(s);
            }
            List list = new ArrayList();
            for(int i = 0; i < cars.size(); i++) {
                for(int j = 0; j < provinces.size(); j++) {
                    System.out.println("i: " + i + ", j: " + j);
                    URL url = new URL("http://www.nissan.com.cn/page/processdata.php?cartypeid=" + ((String[]) cars.get(i))[0] + "&provid=" + ((String[]) provinces.get(j))[0] + "&rand=" + Math.random());
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    StringBuffer sb = new StringBuffer();
                    String tmp;
                    while((tmp = br.readLine()) != null) {
                        sb.append(tmp).append("\r\n");
                    }
                    tmp = sb.toString();
                    tmp = tmp.substring(tmp.indexOf("<option value=\"\">") + "<option value=\"\">".length());
                    while(tmp.indexOf("<option") != -1) {
                        tmp = tmp.substring(tmp.indexOf("<option value=\"") + "<option value=\"".length());
                        String value = tmp.substring(0, tmp.indexOf("\""));
                        tmp = tmp.substring(tmp.indexOf(">") + 1);
                        String name = tmp.substring(0, tmp.indexOf("<"));
                        String[] s = new String[6];
                        s[0] = ((String[]) cars.get(i))[0];
                        s[1] = ((String[]) cars.get(i))[1];
                        s[2] = ((String[]) provinces.get(j))[0];
                        s[3] = ((String[]) provinces.get(j))[1];
                        s[4] = value;
                        s[5] = name;
                        for(int k = 0; k < 6; k++) {
                            System.out.print(s[k] + "\t");
                        }
                        System.out.println();
                        list.add(s);
                    }
                }
            }
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet();
            for(int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i);
                for(int j = 0; j < 6; j++) {
                    HSSFCell cell = row.createCell((short) j);
                    cell.setCellType(1);
                    cell.setCellValue(new HSSFRichTextString(((String[]) list.get(i))[j]));
                }
            }
            File out = new File("D:\\4s\\Nissan\\cities.xls");
            FileOutputStream fos = new FileOutputStream(out);
            workbook.write(fos);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void loadCity() {
        try {
            File f = new File("D:\\4s\\Nissan\\param.xls");
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheet("car");
            List cars = new ArrayList();
            List provinces = new ArrayList();

            for(int i = 0; i < 14; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] s = new String[2];
                HSSFCell cell = row.getCell(0);
                s[0] = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
                cell = row.getCell(1);
                s[1] = cell.getRichStringCellValue().getString();
                cars.add(s);
            }
            sheet = workbook.getSheet("province");
            for(int i = 0; i < 31; i++) {
                HSSFRow row = sheet.getRow(i);
                String[] s = new String[2];
                HSSFCell cell = row.getCell(0);
                s[0] = String.valueOf(new Double(cell.getNumericCellValue()).intValue());
                cell = row.getCell(1);
                s[1] = cell.getRichStringCellValue().getString();
                provinces.add(s);
            }
            for(int i = 0; i < cars.size(); i++) {
                for(int j = 0; j < provinces.size(); j++) {
                    System.out.println("i: " + i + ", j: " + j);
                    URL url = new URL("http://www.nissan.com.cn/page/processdata.php?cartypeid=" + ((String[]) cars.get(i))[0] + "&provid=" + ((String[]) provinces.get(j))[0] + "&rand=" + Math.random());
                    InputStream is = url.openStream();
                    File out = new File("D:\\4s\\Nissan\\1\\" + i + "_" + j + "_" + ((String[]) cars.get(i))[1] + "_" + ((String[]) provinces.get(j))[1] + ".txt");
                    FileOutputStream fos = new FileOutputStream(out);
                    int b;
                    while((b = is.read()) >= 0) {
                        fos.write(b);
                    }
                    fos.close();
                    is.close();
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void WriteToDB(String[] s) {
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellias", "root", "sjtuagent");
            }
            if(stmt == null) {
                stmt = connection.prepareStatement("insert into nissan (`a0`, `a1`, `a2`, `a3`, `a4`, `a5`, `a6`, `a7`, `a8`) values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
            }
            for(int i = 0; i < 9; i++) {
                stmt.setString(i + 1, s[i]);
            }
            stmt.executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void WriteToFile(String s, String filename) throws Exception {
        File f = new File("D:\\4s\\Nissan\\2\\" + filename + ".txt");
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(s.getBytes());
        fos.close();
    }
}