package com.saille.s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DD extends Thread {
    private Logger LOGGER = Logger.getLogger(getClass());
    private static String LINK = "http://4s.pcauto.com.cn/shangjia/";
    private Connection connection;
    private PreparedStatement stmt;
    int[] ids;

    public DD(int[] num) {
        this.ids = new int[num.length];
        for(int i = 0; i < num.length; i++) {
            this.ids[i] = num[i];
        }
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellias", "root", "sjtuagent");

            this.stmt = this.connection.prepareStatement("insert into car4s (`car`, `city`, `type`, `link`, `name`, `phone`, `addr`) values(?, ?, ?, ?, ?, ?, ?)");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        List results = new ArrayList();
        try {
            File f = new File("D:\\4s\\4s.xls");
            FileInputStream fis = new FileInputStream(f);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            this.LOGGER.info("load cars");
            HSSFSheet sheet = workbook.getSheet("car");
            List cars = new ArrayList();

            for(int i = 0; i < this.ids.length; i++) {
                if(sheet.getRow(this.ids[i]) != null) {
                    Object[] obj = new Object[2];
                    obj[0] = sheet.getRow(this.ids[i]).getCell(1).getRichStringCellValue().getString();
                    obj[1] = Integer.valueOf((int) sheet.getRow(this.ids[i]).getCell(2).getNumericCellValue());

                    cars.add(obj);

                    this.LOGGER.info(obj[0]);
                }
            }
            this.LOGGER.info("total " + cars.size() + " cars, load cities");
            sheet = workbook.getSheet("city");
            List cities = new ArrayList();
            for(int i = 0; i < 339; i++) {
                Object[] obj = new Object[3];
                obj[0] = sheet.getRow(i).getCell(0).getRichStringCellValue().getString();
                obj[1] = sheet.getRow(i).getCell(2).getRichStringCellValue().getString();
                obj[2] = Integer.valueOf((int) sheet.getRow(i).getCell(3).getNumericCellValue());

                cities.add(obj);
            }

            this.LOGGER.info("total " + cities.size() + " cities");
            this.LOGGER.info("begin get content");
            for(int i = 0; i < cars.size(); i++) {
                for(int j = 0; j < cities.size(); j++) {
                    this.LOGGER.info("car: " + i + "/" + cars.size() + ((Object[]) cars.get(i))[0] + ";\tcity: " + j + "/" + cities.size() + ((Object[]) cities.get(j))[1]);
                    String url = LINK + "c" + ((Object[]) cities.get(j))[2] + "/nb" + ((Object[]) cars.get(i))[1] + "/";
                    URL u = new URL(url);
                    StringBuffer sb = new StringBuffer();
                    boolean error = true;
                    while(error) {
                        try {
                            BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                            String tmp = null;
                            while((tmp = in.readLine()) != null) {
                                sb.append(tmp).append("\r\n");
                            }
                            error = false;
                        } catch(Exception ex) {
                            this.LOGGER.info("retry, " + ex.getMessage());
                        }
                    }
                    String content = sb.toString();
                    content = content.substring(content.indexOf("按条件筛选经销商"));
                    content = content.substring(content.indexOf("找到<span class=\"yellow\">") + "找到<span class=\"yellow\">".length());
                    String number = content.substring(0, content.indexOf("<"));
                    this.LOGGER.info("共有" + number + "条记录");
                    this.LOGGER.info(url);
                    if(Integer.parseInt(number) > 0) {
                        content = content.substring(content.indexOf("syBodyAll"));
                        content = content.substring(content.indexOf("<div class=\"dTxt\">") + "<div class=\"dTxt\">".length());
                        for(int k = 0; k < Math.min(Integer.parseInt(number), 10); k++) {
                            List atom = new ArrayList();
                            String subContent;
                            if(content.indexOf("<div class=\"dTxt\">") != -1) {
                                subContent = content.substring(0, content.indexOf("<div class=\"dTxt\">"));
                                content = content.substring(content.indexOf("<div class=\"dTxt\">") + "<div class=\"dTxt\">".length());
                            } else {
                                subContent = content.substring(0, content.indexOf("<div class=\"syPageBox\">"));
                            }
                            atom.add(String.valueOf(((Object[]) cars.get(i))[0]));
                            atom.add(String.valueOf(((Object[]) cities.get(j))[1]));
                            if(subContent.indexOf("<span class=\"sTitle\">") != -1) {
                                String nameContent = subContent.substring(subContent.indexOf("<span class=\"sTitle\">") + "<span class=\"sTitle\">".length());
                                nameContent = nameContent.substring(0, nameContent.indexOf("</span>"));
                                String type = nameContent.substring(nameContent.indexOf("[") + 1, nameContent.indexOf("]"));
                                String link = nameContent.substring(nameContent.indexOf("<a href=\"") + "<a href=\"".length(), nameContent.indexOf("\" target=\"_blank\""));
                                nameContent = nameContent.substring(nameContent.indexOf(" target=\"_blank\">") + " target=\"_blank\">".length());
                                String name = nameContent.substring(0, nameContent.indexOf("<"));
                                atom.add(type);
                                atom.add(link);
                                atom.add(name);
                            } else {
                                atom.add("");
                                atom.add("");
                                atom.add("");
                            }
                            if(subContent.indexOf("<span class=\"sPhone\">") != -1) {
                                String phoneContent = subContent.substring(subContent.indexOf("<span class=\"sPhone\">") + "<span class=\"sPhone\">".length());
                                phoneContent = phoneContent.substring(0, phoneContent.indexOf("</span>"));
                                phoneContent = phoneContent.substring(phoneContent.indexOf("<strong class=\"") + "<strong class=\"".length());
                                phoneContent = phoneContent.substring(phoneContent.indexOf(">") + 1);
                                String phone = phoneContent.substring(0, phoneContent.indexOf("<"));
                                atom.add(phone);
                            } else {
                                atom.add("");
                            }
                            if(subContent.indexOf("<span class=\"sAddr\">") != -1) {
                                String addrContent = subContent.substring(subContent.indexOf("<span class=\"sAddr\">") + "<span class=\"sAddr\">".length());
                                addrContent = addrContent.substring(0, addrContent.indexOf("</span>"));
                                String addr = addrContent.substring(addrContent.indexOf("target=\"_blank\">") + "target=\"_blank\">".length(), addrContent.indexOf("</a>"));
                                atom.add(addr);
                            } else {
                                atom.add("");
                            }

                            for(int l = 0; l < 7; l++) {
                                this.stmt.setString(l + 1, (String) atom.get(l));
                            }
                            this.stmt.executeUpdate();
                            this.LOGGER.info("saved: " + (String) atom.get(6));
                        }

                        if(Integer.parseInt(number) > 10) {
                            for(int l = 1; l <= (Integer.parseInt(number) - 1) / 10; l++) {
                                String newURL = url + "10-" + (l + 1) + ".html";
                                this.LOGGER.info(newURL);

                                u = new URL(newURL);
                                sb = new StringBuffer();
                                BufferedReader in = new BufferedReader(new InputStreamReader(u.openStream()));
                                String tmp = null;
                                while((tmp = in.readLine()) != null) {
                                    sb.append(tmp).append("\r\n");
                                }
                                content = sb.toString();
                                content = content.substring(content.indexOf("按条件筛选经销商"));
                                content = content.substring(content.indexOf("找到<span class=\"yellow\">") + "找到<span class=\"yellow\">".length());
                                number = content.substring(0, content.indexOf("<"));

                                content = content.substring(content.indexOf("syBodyAll"));
                                content = content.substring(content.indexOf("<div class=\"dTxt\">") + "<div class=\"dTxt\">".length());
                                for(int k = 1; k <= Math.min(Integer.parseInt(number) - 10 * l, 10); k++) {
                                    List atom = new ArrayList();
                                    String subContent;
                                    if(content.indexOf("<div class=\"dTxt\">") != -1) {
                                        subContent = content.substring(0, content.indexOf("<div class=\"dTxt\">"));
                                        content = content.substring(content.indexOf("<div class=\"dTxt\">") + "<div class=\"dTxt\">".length());
                                    } else {
                                        subContent = content.substring(0, content.indexOf("<div class=\"syPageBox\">"));
                                    }
                                    atom.add(String.valueOf(((Object[]) cars.get(i))[0]));
                                    atom.add(String.valueOf(((Object[]) cities.get(j))[1]));
                                    if(subContent.indexOf("<span class=\"sTitle\">") != -1) {
                                        String nameContent = subContent.substring(subContent.indexOf("<span class=\"sTitle\">") + "<span class=\"sTitle\">".length());
                                        nameContent = nameContent.substring(0, nameContent.indexOf("</span>"));
                                        String type = nameContent.substring(nameContent.indexOf("[") + 1, nameContent.indexOf("]"));
                                        String link = nameContent.substring(nameContent.indexOf("<a href=\"") + "<a href=\"".length(), nameContent.indexOf("\" target=\"_blank\""));
                                        nameContent = nameContent.substring(nameContent.indexOf(" target=\"_blank\">") + " target=\"_blank\">".length());
                                        String name = nameContent.substring(0, nameContent.indexOf("<"));
                                        atom.add(type);
                                        atom.add(link);
                                        atom.add(name);
                                    } else {
                                        atom.add("");
                                        atom.add("");
                                        atom.add("");
                                    }
                                    if(subContent.indexOf("<span class=\"sPhone\">") != -1) {
                                        String phoneContent = subContent.substring(subContent.indexOf("<span class=\"sPhone\">") + "<span class=\"sPhone\">".length());
                                        phoneContent = phoneContent.substring(0, phoneContent.indexOf("</span>"));
                                        phoneContent = phoneContent.substring(phoneContent.indexOf("<strong class=\"") + "<strong class=\"".length());
                                        phoneContent = phoneContent.substring(phoneContent.indexOf(">") + 1);
                                        String phone = phoneContent.substring(0, phoneContent.indexOf("<"));
                                        atom.add(phone);
                                    } else {
                                        atom.add("");
                                    }
                                    if(subContent.indexOf("<span class=\"sAddr\">") != -1) {
                                        String addrContent = subContent.substring(subContent.indexOf("<span class=\"sAddr\">") + "<span class=\"sAddr\">".length());
                                        addrContent = addrContent.substring(0, addrContent.indexOf("</span>"));
                                        String addr = addrContent.substring(addrContent.indexOf("target=\"_blank\">") + "target=\"_blank\">".length(), addrContent.indexOf("</a>"));
                                        atom.add(addr);
                                    } else {
                                        atom.add("");
                                    }
                                    for(int ll = 0; ll < 7; ll++) {
                                        this.stmt.setString(ll + 1, (String) atom.get(ll));
                                    }
                                    this.stmt.executeUpdate();
                                    this.LOGGER.info("saved: " + (String) atom.get(6));
                                }
                            }
                        }
                    }
                }
            }

            this.LOGGER.info("total " + results.size() + " records");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private void export(List<List<String>> list, String filename) {
        try {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            HSSFRow row = sheet.createRow(0);
            HSSFCell cell = row.createCell(0);
            cell.setCellType(1);
            cell.setCellValue(new HSSFRichTextString("car"));
            for(int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                for(int j = 0; j < ((List) list.get(i)).size(); j++) {
                    cell = row.createCell((short) j);
                    cell.setCellType(1);
                    cell.setCellValue(new HSSFRichTextString((String) ((List) list.get(i)).get(j)));
                }
            }
            File f = new File("D:\\4s\\" + filename + ".xls");
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}