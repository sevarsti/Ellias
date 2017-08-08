package com.saille.s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DongFengHonda {
    public static void main(String[] args) {
        try {
            File inFile = new File("D:\\4s\\东风本田\\bookingdata.txt");
            File outFile = new File("D:\\4s\\东风本田\\out.txt");
            FileInputStream fis = new FileInputStream(inFile);

            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String s = null;
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet();
            List list = new ArrayList();

            while((s = br.readLine()) != null) {
                String[] l = new String[8];
                String area = s.substring(1, s.indexOf("\"city"));
                s = s.substring(s.indexOf("\"city"));
                l[0] = area.substring(8, area.length() - 2);
                String city = s.substring(0, s.indexOf("\"shop"));
                s = s.substring(s.indexOf("\"shop"));
                l[1] = city.substring(8, city.length() - 2);
                String shop = s.substring(0, s.indexOf("\"founddate"));
                s = s.substring(s.indexOf("\"founddate"));
                l[2] = shop.substring(8, shop.length() - 2);
                String founddate = s.substring(0, s.indexOf("\"shopname"));
                s = s.substring(s.indexOf("\"shopname"));
                l[3] = founddate.substring(13, founddate.length() - 2);
                String shopname = s.substring(0, s.indexOf("\"shopaddress"));
                s = s.substring(s.indexOf("\"shopaddress"));
                l[4] = shopname.substring(12, shopname.length() - 2);
                String shopaddress = s.substring(0, s.indexOf("\"sellline"));
                s = s.substring(s.indexOf("\"sellline"));
                l[5] = shopaddress.substring(15, shopaddress.length() - 2);
                String sellline = s.substring(0, s.indexOf("\"hotline"));
                s = s.substring(s.indexOf("\"hotline"));
                l[6] = sellline.substring(12, sellline.length() - 2);
                String hotline = s;
                l[7] = hotline.substring(11, hotline.length() - 2);
                list.add(l);
            }

            write(list, sheet, workbook);

            br.close();
            isr.close();
            fis.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void write(List<String[]> list, HSSFSheet sheet, HSSFWorkbook workbook) {
        for(int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i);
            HSSFCell cell = null;
            for(int j = 0; j < ((String[]) list.get(i)).length; j++) {
                cell = row.createCell((short) j);
                cell.setCellType(1);
                cell.setCellValue(new HSSFRichTextString(((String[]) list.get(i))[j]));
            }
        }
        File f = new File("D:\\4s\\东风本田\\out.xls");
        try {
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}