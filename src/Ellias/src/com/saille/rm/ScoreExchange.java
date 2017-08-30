package com.saille.rm;

import com.GlobalConstant;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-6-30
 * Time: 14:59:01
 * To change this template use File | Settings | File Templates.
 * »ý·Ö¶Ò»»
 */
public class ScoreExchange {
    public static void main(String[] args) {
        try {
            File f = new File(GlobalConstant.DISKPATH + "rm\\TableComBin\\mrock_scoreexchange_client.bin");
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
            System.out.println();
            for(int i = 0; i < count; i++) {
                bb = new byte[size];
                fis.read(bb);
                String s = new String(bb, "utf-8");
                list.add(s);
                for(int j = 0; j < bb.length; j++) {
                    System.out.print(convert2Hex(bb[j]) + " ");
                }
//                for(int j = 12; j < 17; j++) {
//                    System.out.print(convert2Hex(bb[j]) + " ");
//                }
//                System.out.print(" ");
//
//                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 0, 4)) + "\t");
//                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 4, 8)) + "\t");
//                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 8, 12)) + "\t");
//                System.out.print(new String(Arrays.copyOfRange(bb, 17, 81), "utf-8").trim() + "\t");
//                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 81, 85)) + "\t");
//                System.out.print(KeyLoad.byte2int(Arrays.copyOfRange(bb, 85, 89)) + "\t");
                System.out.println("");
            }
//            for(String s : list) {
//                System.out.println(s);
//            }
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
