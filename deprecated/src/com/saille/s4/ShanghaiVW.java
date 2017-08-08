package com.saille.s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ShanghaiVW {
    public static void main(String[] args) {
        load();
    }

    public static void load() {
        try {
            File f = new File("D:\\4s\\上海大众\\orig.txt");
            FileInputStream fis = new FileInputStream(f);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            StringBuffer sb = new StringBuffer();
            List list = new ArrayList();
            String tmp;
            while((tmp = br.readLine()) != null) {
                String[] s = new String[6];
                s[0] = tmp.substring("名称: ".length());
                for(int i = 1; i < 6; i++) {
                    tmp = br.readLine();
                    System.out.println(tmp);
                    s[i] = tmp.substring("名称: ".length());
                }
                br.readLine();
                list.add(s);
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
            File out = new File("D:\\4s\\上海大众\\out.xls");
            FileOutputStream fos = new FileOutputStream(out);
            workbook.write(fos);
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}