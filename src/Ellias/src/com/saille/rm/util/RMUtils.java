package com.saille.rm.util;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-14
 * Time: 13:37:15
 * To change this template use File | Settings | File Templates.
 */
public class RMUtils {
    public static String getString(byte[] bytes, int start, int length) throws Exception {
        String ret = new String(bytes, start, length, "UTF-8").trim();
        return ret;
    }

    public static int getInt(byte[] bytes, int start, int length) {
        long ret = 0;
        for(int i = start + length - 1; i >= start; i--) {
            long b = (int) bytes[i];
            if(b < 0) {
                b = b + 256;
            }
            ret = (ret << 8) + b;
//            ret = ret * 256 + b;
        }
        return (int)ret;
    }

    public static void printBytes(byte[] bytes, int start, int length, boolean enter) {
        for(int ii = start; ii < start + length; ii++) {
            byte b = bytes[ii];
            int i = (int) b;
            if(i < 0) {
                i = i + 256;
            }
            if(i >= 16) {
                if(i > 0x9F) {
                    System.out.print((char)(i / 16 - 10 + 65));
                } else {
                    System.out.print(i / 16);
                }
            } else {
                System.out.print("0");
            }
            i = i % 16;
            if(i > 9) {
                System.out.print((char)(i - 10 + 65));
            } else {
                System.out.print(i);
            }
            System.out.print(" ");
        }
        if(enter) {
            System.out.println();
        }
    }
}
