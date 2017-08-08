package com.saille.jy;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;

import java.util.Random;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-11-23
 * Time: 20:19:57
 * To change this template use File | Settings | File Templates.
 */
public class CalcUtils {
    private static Random r = new Random();

    public static double getRandom() {
        return r.nextDouble();
    }

    public static int getRandom(int min, int max) {
        return (int)(getRandom() * (max - min) + min);
    }

    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("D:\\qingdan.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Person[] pps = new Person[2];
            for(int i = 0; i < 2; i++) {
                HSSFRow row = sheet.getRow(i + 1);
                Person p  = new Person();
                p.setName(row.getCell(0).getRichStringCellValue().getString());
                p.setHp((int)row.getCell(1).getNumericCellValue());
                p.setLevel((int)row.getCell(3).getNumericCellValue());
                p.setGongji((int)row.getCell(4).getNumericCellValue());
                p.setFangyu((int)row.getCell(5).getNumericCellValue());
                p.setMingzhong((int)row.getCell(6).getNumericCellValue());
                p.setShanbi((int)row.getCell(7).getNumericCellValue());
                pps[i] = p;
            }
            sheet = workbook.getSheetAt(1);
            Wugong[] ww = new Wugong[3];
            for(int i = 0; i < 3; i++) {
                HSSFRow row = sheet.getRow(i + 1);
                
            }
            int[] rr = new int[3];
            for(int i = 0; i < 1000; i++) {
                int result = fight(pps[0], pps[1]);
                if(result > 0) {
                    rr[0]++;
                } else if(result < 0) {
                    rr[1]++;
                } else {
                    rr[2]++;
                }
            }
            System.out.println(rr[0] + ":" + rr[1]);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        int r2 = 50;
//        for(int i = 0; i < 100; i++) {
//            int r1 = r.nextInt(200);
//            r1 = i;
//            Person p1 = new Person(), p2 = new Person();
//        p1.setName("ÇÇ·å");
//            p1.setMingzhong(100);
//            p2.setShanbi(100);
//            p1.setLevel(r2);
//        p1 = Person.fromJSON(p1.toJSON());
//        System.out.println(p1.toJSON());
//            p2.setLevel(r1);
//            System.out.println(r1+"/"+r2+"="+isMingzhong(p1, p2));
//        }
    }

    public static int fight(Person p1, Person p2) {
        int round = 1;
        int hp1 = p1.getHp(), hp2 = p2.getHp();
        Person att, def;
        while(hp1 > 0 && hp2 > 0) {
//            System.out.println("µÚ" + round + "»ØºÏ");
            double firstatt = getRandom();
            if(firstatt >= 0.5) {
                att = p1;
                def = p2;
            } else {
                att = p2;
                def = p1;
            }
            if(isMingzhong(att, def)) {
                int damage = getDamage(att, def);
//                System.out.println(att.getName() + " ¹¥»÷ " + def.getName() + "£¬Ôì³É " + damage + " µãÉËº¦");
                if(firstatt >= 0.5) {
                    hp2 -= damage;
                } else {
                    hp1 -= damage;
                }
            } else {
//                System.out.println(att.getName() + " ¹¥»÷ " + def.getName() + "£¬µ«ÊÇ±»ÉÁ±Ü");
            }
            if(hp1 <= 0 || hp2 <= 0) {
                break;
            }
            if(isMingzhong(def, att)) {
                int damage = getDamage(def, att);
//                System.out.println(def.getName() + " ¹¥»÷ " + att.getName() + "£¬Ôì³É " + damage + " µãÉËº¦");
                if(firstatt >= 0.5) {
                    hp1 -= damage;
                } else {
                    hp2 -= damage;
                }
            } else {
//                System.out.println(def.getName() + " ¹¥»÷ " + att.getName() + "£¬µ«ÊÇ±»ÉÁ±Ü");
            }
//            System.out.println("µÚ" + round + "ÂÖ½áÊø£¬" + p1.getName() + " Ê£Óà " + hp1 + " Ñª£¬" + p2.getName() + " Ê£Óà " + hp2 + " Ñª");
            round++;
        }
//        System.out.println("±ÈÈü½áÊø£¬" + p1.getName() + " Ê£Óà " + hp1 + " Ñª£¬" + p2.getName() + " Ê£Óà " + hp2 + " Ñª");
        if(hp1 > 0) {
            return 1;
        }
        if(hp2 > 0) {
            return -1;
        }
        return 0;
    }
    //ÅÐ¶ÏÉËº¦
    //att.gongji * log(att.gongji) / log(def.fangyu) * 0.7 * random(0.8, 1.2)
    public static int getDamage(Person att, Person def) {
        double g = att.getGongji(), f = def.getFangyu();
        if(g < 10) {
            g = 10;
        }
        if(f < 10) {
            f = 10;
        }
        double result = g * Math.log(g) / Math.log(f) * 0.7;
        result = result * getRandom(80, 120) / 100;
        return (int)result;
//        return 0;
    }

    //ÅÐ¶Ï¹¥»÷ÊÇ·ñÃüÖÐ
    //(att.mingzhong / (att.mingzhong + def.shanbi) + 0.5) * 0.8 * ((att.lv - def.lv) / 100 + 1)
    //ÉÏÏÞ95%£¬ÏÂÏÞ5%
    public static boolean isMingzhong(Person att, Person def) {
        int lv1 = att.getLevel(), lv2 = def.getLevel();
        int mingzhong = att.getMingzhong(), shanbi = def.getShanbi();
        if(lv1 <= 0) {
            lv1 = 1;
        }
        if(lv2 <= 0) {
            lv2 = 1;
        }
        if(mingzhong <= 0) {
            mingzhong = 1;
        }
        if(shanbi <= 0) {
            shanbi = 1;
        }

        double standard = 0.8d;
        double leveldiff = lv1 - lv2;
        if(leveldiff > 100) {
            leveldiff = 20;
        }
        double mingzhongdiff = (double)mingzhong / ((double)mingzhong + (double)shanbi);
        double result = standard * (mingzhongdiff + 0.5);
        result = result * (leveldiff / 100 + 1);
        if(result > 0.95) {
            result = 0.95;
        }
        if(result < 0.05) {
            result = 0.05;
        }
//        return result;
        return getRandom() < result;
    }
}
