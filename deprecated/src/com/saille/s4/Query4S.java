package com.saille.s4;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Query4S {
    private static String LINK = "http://4s.pcauto.com.cn/shangjia/";
    private static Logger LOGGER = Logger.getLogger(Query4S.class);

    public static void main(String[] args) {
        try {
            DD d = new DD(new int[]{20});
            d.start();
            Thread.sleep(5000L);
            DD d2 = new DD(new int[]{56});
            d2.start();
            Thread.sleep(5000L);
            DD d3 = new DD(new int[]{66});
            d3.start();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void export(List<List<String>> list) {
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
            File f = new File("D:\\4s\\4sss.xls");
            FileOutputStream fos = new FileOutputStream(f);
            workbook.write(fos);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}