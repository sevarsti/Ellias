package com.saille.rm;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-30
 * Time: 14:37:43
 * To change this template use File | Settings | File Templates.
 */
public class WeekSLoad {
    public static void main(String[] args) {
        try {
            File f = new File("D:\\rm\\TableComBin\\mrock.mission_client.bin");
            FileInputStream fis = new FileInputStream(f);
            fis.skip(8);
            byte[] bb = new byte[4];
            fis.read(bb);
            int size = KeyLoad.byte2int(bb);
            fis.read(bb);
            int count = KeyLoad.byte2int(bb);
            System.out.println("count="+count+",size="+size);
            fis.skip(120);
            List<String> list = new ArrayList<String>();
            for(int i = 0; i < size; i++) {
                System.out.print(convert2Hex((byte)i) + " ");
            }
            System.out.println();
            for(int i = 0; i < count; i++) {
                bb = new byte[size];
                fis.read(bb);
                String s = new String(bb, "utf-8");
                list.add(s);
                for(int j = 132; j < 136; j++) {
                    System.out.print(convert2Hex(bb[j]) + " ");
                }
                System.out.print(" ");
                System.out.print(new String(Arrays.copyOfRange(bb, 200, 208), "utf-8").trim());
                System.out.print(" ");
                for(int j = 392; j < 415; j++) {
                    System.out.print(convert2Hex(bb[j]) + " ");
                }
                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 0, 4)) + "\t");
                System.out.print(new String(Arrays.copyOfRange(bb, 4, 132), "utf-8").trim() + "\t");
                System.out.print(new String(Arrays.copyOfRange(bb, 136, 144), "utf-8").trim() + "\t");
                System.out.print(new String(Arrays.copyOfRange(bb, 264, 283), "utf-8").trim() + "\t");
                System.out.print(new String(Arrays.copyOfRange(bb, 328, 347), "utf-8").trim());
                //0-3:idx
                //4-131: name
                //132-135:图标?
                //136-143:难度
                //144-199:blank
                //200-207:和难度对应?
                //208-263:blank
                //264-282:startdate
                //283-327:blank
                //328-346:enddate
                //347-391:blank
                //392-414:?
                System.out.println();
            }
//                for(String s : list) {
//                    System.out.println(s);
//                }
            fis.close();
            System.exit(0);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String convert2Hex(byte b) {
        int i = b;
        if(i < 0) {
            i = 256 + i;
        }
        int h = i / 16, l = i % 16;
        String ret = ((h > 9) ? (""+((char)(55+h))) : (""+h)) + ((l > 9) ? (""+((char)(55+l))) : (""+l));
        return ret;
    }
}
