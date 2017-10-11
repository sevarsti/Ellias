package com.saille.rm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-14
 * Time: 13:37:15
 * To change this template use File | Settings | File Templates.
 */
public class RMUtils {
    public static void main(String[] args) {
        try {
            File f = new File("F:\\rm\\ipad\\song\\shangxindrbtmg\\shangxindrbtmg_4k_ez.imd");
            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[(int)f.length()];
            fis.read(bytes);
            fis.close();
            byte[] ret = changeBpm(bytes, 0.8);
            f = new File("F:\\rm\\ipad\\song\\wubainian\\new.imd");
            if(!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(ret);
            fos.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /* 将imd变速后返回,ratio=1表示不变速,ratio=2表示加速2倍 */
    public static byte[] changeBpm(byte[] inBytes, double ratio) {
        List<byte[]> list = new ArrayList<byte[]>();
        /* 时长 */
        int length = getInt(inBytes, 0, 4);
        int newLength = (int)(((double)length) / ratio);
        list.add(int2byte(newLength)); //曲长
        int count = getInt(inBytes, 4, 4);
        list.add(Arrays.copyOfRange(inBytes, 4, 8)); //bpm数
        for(int i = 0; i < count; i++) {
            int begin = getInt(inBytes, 8 + i * 12, 4);
            int newBegin = (int)(((double)begin) / ratio);
            list.add(int2byte(newBegin));
            double bpm = getDouble(inBytes, 8 + i * 12 + 4, 8);
            bpm = bpm * ratio;
            list.add(longToBytes(Double.doubleToLongBits(bpm)));
        }
        int keybegin = 8 + count * 12 + 2 + 4;
        count = getInt(inBytes, keybegin - 4, 4);
        list.add(new byte[]{3, 3});
        list.add(Arrays.copyOfRange(inBytes, keybegin - 4, keybegin));
        for(int i = 0; i < count; i++) {
            list.add(Arrays.copyOfRange(inBytes, keybegin + i * 11, keybegin + i * 11 + 2));
            int offset = getInt(inBytes, keybegin + i * 11 + 2, 4);
            offset = (int)(((double)offset) / ratio);
            list.add(int2byte(offset));
            byte keytype = inBytes[keybegin + i * 11];
            list.add(new byte[]{inBytes[keybegin + i * 11 + 6]});
            if(keytype % 16 == 2) {
                int time = getInt(inBytes, keybegin + i * 11 + 7, 4);
                time = (int)(((double)time) / ratio);
                list.add(int2byte(time));
            } else {
                list.add(Arrays.copyOfRange(inBytes, keybegin + i * 11 + 7, keybegin + i * 11 + 11));
            }
        }
        int size = 0;
        for(byte[] b : list) {
            size += b.length;
        }
        byte[] ret = new byte[size];
        int i = 0;
        for(byte[] bb : list) {
            for(byte b : bb) {
                ret[i] = b;
                i++;
            }
        }
        return ret;
    }

    public static String getString(byte[] bytes, int start, int length) {
        try {
            String ret = new String(bytes, start, length, "UTF-8").trim();
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static double getDouble(byte[] bytes, int start, int length) {
        byte[] bb = new byte[length];
        for(int i = start; i < start + length; i++) {
            bb[i - start] = bytes[i];
        }
        return Double.longBitsToDouble(Long.reverseBytes(bytesToLong(bb)));
    }

    /**
     * 将一个8位字节数组转换为长整数。<br>
     * 注意，函数中不会对字节数组长度进行判断，请自行保证传入参数的正确性。
     *
     * @param b
     *            字节数组
     * @return 长整数
     */
    private static long bytesToLong(byte[] b) {
        long l = ((long) b[0] << 56) & 0xFF00000000000000L;
        // 如果不强制转换为long，那么默认会当作int，导致最高32位丢失
        l |= ((long) b[1] << 48) & 0xFF000000000000L;
        l |= ((long) b[2] << 40) & 0xFF0000000000L;
        l |= ((long) b[3] << 32) & 0xFF00000000L;
        l |= ((long) b[4] << 24) & 0xFF000000L;
        l |= ((long) b[5] << 16) & 0xFF0000L;
        l |= ((long) b[6] << 8) & 0xFF00L;
        l |= (long) b[7] & 0xFFL;
        return l;
    }

    /**
     * 将一个长整数转换位字节数组(8个字节)，b[0]存储高位字符，大端
     *
     * @param l
     *            长整数
     * @return 代表长整数的字节数组
     */
    public static byte[] longToBytes(long l) {
        byte[] b = new byte[8];
        b[7] = (byte) (l >>> 56);
        b[6] = (byte) (l >>> 48);
        b[5] = (byte) (l >>> 40);
        b[4] = (byte) (l >>> 32);
        b[3] = (byte) (l >>> 24);
        b[2] = (byte) (l >>> 16);
        b[1] = (byte) (l >>> 8);
        b[0] = (byte) (l);
        return b;
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

    public static byte[] int2byte(int i) {
        List<Byte> list = new ArrayList<Byte>();
        while(i > 0) {
            int r = i % 256;
            list.add((byte)r);
            i = i / 256;
        }
        byte[] ret = new byte[4];
        for(int j = 0; j < list.size(); j++) {
            ret[j] = list.get(j).byteValue();
        }
        return ret;
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

    public static String convertLength(int length) {
        DecimalFormat df = new DecimalFormat("00");
        return length / 60 + ":" + df.format(length % 60);
    }
}
