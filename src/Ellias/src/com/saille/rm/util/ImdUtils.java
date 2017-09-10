package com.saille.rm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

/**
 * Created by Ellias on 2017-09-07.
 */
public class ImdUtils {
    public static void main(String[] args) {
        try {
            File f = new File("F:\\rm\\song\\canonrock\\canonrock_4k_hd.imd");
            FileInputStream fis = new FileInputStream(f);
            fis.skip(12);
            byte[] bytes = new byte[8];
            fis.read(bytes);
            fis.close();
//            System.out.println(RMUtils(bytes));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static int getLength(byte[] imds) {
        int length = RMUtils.getInt(imds, 0, 4);
        return length;
    }

    public static double calcRank(byte[] imds) {
        int skip = RMUtils.getInt(imds, 4, 4);
        int length = RMUtils.getInt(imds, 8 + skip * 12 + 2, 4);
        double r = 0;
        for(int i = 0; i < length; i++) {
            byte[] bytes = Arrays.copyOfRange(imds, 8 + skip * 12 + 2 + 4 + i * 11, 8 + skip * 12 + 2 + 4 + i * 11 + 11);
            switch(bytes[0]) {
                case 0x00:
                    r += 1;
                    break;
                case 0x01:
                    r += Math.abs(RMUtils.getInt(bytes, 7, 4)) + 2;
                    break;
                case 0x61:
                case -95:
                    r += Math.abs(RMUtils.getInt(bytes, 7, 4)) + 1;
                    break;
                case 0x21:
                    r += Math.abs(RMUtils.getInt(bytes, 7, 4));
                    break;
                case 0x02:
                case 0x62:
                case 0x22:
                case -94:
                    r += Math.floor((double)RMUtils.getInt(bytes, 7, 4) / 120.0) + 1;
                    break;
            }
        }
        return Math.sqrt(r / 16);
    }

    public static double calcDifficult(byte[] imds) {
        int skip = RMUtils.getInt(imds, 4, 4);
        int length = RMUtils.getInt(imds, 8 + skip * 12 + 2, 4);
        int key = getKey(imds);
        List<double[]> d = new ArrayList<double[]>();
        for(int i = 0; i < length; i++) {
            byte[] bytes = Arrays.copyOfRange(imds, 8 + skip * 12 + 2 + 4 + i * 11, 8 + skip * 12 + 2 + 4 + i * 11 + 11);
            int timeoffset = RMUtils.getInt(bytes, 2, 4);
            int slidewidth = RMUtils.getInt(bytes, 7, 4);
            switch(bytes[0]) {
                case 0x00:
                    addData(timeoffset, 1, d);
                    break;
                case 0x01:
                    addData(timeoffset, 2 + getSlide(slidewidth, key), d);
                    break;
                case 0x61:case -95: //A1
                    addData(timeoffset, 1 + getSlide(slidewidth, key), d);
                    break;
                case 0x21:
                    addData(timeoffset, 0 + getSlide(slidewidth, key), d);
                    break;
                case 0x02:case 0x62:case 0x22:case -94: //A2
                    addData(timeoffset, 1 + getLong(slidewidth), d);
                    break;
            }
        }
        d = quicksort(d, 0, d.size());
        d.add(0, new double[]{0, 0});
        double r = 0;
        for(int i = 1; i < d.size(); i++) {
            r += d.get(i)[1] / (d.get(i)[0] - d.get(i-1)[0]) * 120;
        }
        return Math.sqrt(r / 16);
    }

    private static double getLong(int i) {
        return ((double)i / 120.0) / 4.0;
    }

    public static double getBpm(byte[] bytes) {
        Map<Double, Integer> bpms = new HashMap<Double, Integer>();
        int count = RMUtils.getInt(bytes, 4, 4);
        for(int i = 0; i < count; i++) {
            double bpm = RMUtils.getDouble(bytes, 12 + i * 12, 8);
            if(bpms.containsKey(bpm)) {
                bpms.put(bpm, bpms.get(bpm).intValue() + 1);
            } else {
                bpms.put(bpm, 1);
            }
        }
        double retBpm = 0d;
        count = 0;
        for(Double d : bpms.keySet()) {
            int c = bpms.get(d);
            if(c > count) {
                retBpm = d.doubleValue();
                count = c;
            }
        }
        return retBpm;
    }

    /* 获取imd的最大连击数 */
    public static int getTotalKeys(byte[] imds) {
        int ret = 0;
        int skip = RMUtils.getInt(imds, 4, 4);
        double bpm = getBpm(imds);
        int length = RMUtils.getInt(imds, 8 + skip * 12 + 2, 4);
        for(int i = 0; i < length; i++) {
            int t = imds[8 + skip * 12 + 2 + 4 + i * 11];
            switch (t) {
                case 0: //单键
                case -95: //面条结尾划键
                case 0x21: //面条中间划键
                case 0x61: //面条开始划键
                    ret += 1;
                    break;
                case 1: //划键
                    ret += 1;
                    break;
                case -94: //面条结尾长键
                case 0x22: //面条中间长键
                    ret += ((RMUtils.getInt(imds, 8 + skip * 12 + 2 + 4 + i * 11 + 7, 4) / ((int) (60000d / bpm / 4d))));
                    break;
                case 2: //长键
                case 0x62: //面条开始长键
                    ret += ((RMUtils.getInt(imds, 8 + skip * 12 + 2 + 4 + i * 11 + 7, 4) / ((int) (60000d / bpm / 4d)))) + 1;
                    break;
                case -96: //a0
                    ret--;
            }
        }
        return ret;
    }

    /* 判断imd是4k还是5k还是6k */
    public static int getKey(byte[] imds) {
        int r = 0;
        int skip = RMUtils.getInt(imds, 4, 4);
        int length = RMUtils.getInt(imds, 8 + skip * 12 + 2, 4);
        for(int i = 0; i < length; i++) {
            int t = 0;
            switch(imds[8 + skip * 12 + 2 + 4 + i * 11]) {
                case 0x00:
                case 0x02:
                case 0x62:
                case 0x22:
                case -94:
                    t = imds[8 + skip * 12 + 2 + 4 + i * 11 + 6] + 1;
                    break;
                case 0x01:
                case 0x61:
                case 0x21:
                case -95:
                    int slide = RMUtils.getInt(imds, 8 + skip * 12 + 2 + 4 + i * 11 + 7, 4);
                    t = imds[8 + skip * 12 + 2 + 4 + i * 11 + 6] + 1;
                    if(slide > 0) {
                        t += slide;
                    }
                    break;
            }
            if(r < t) {
                r = t;
            }
        }
        return r;
    }

    private static double getSlide(int t, int key) {
        return Math.abs((double)t) / (double)key;
    }

    private static void addData(int t, double v, List<double[]> d) {
        int l = indexOf(t, d);
        if(l != -1) {
            d.get(l)[1] += v;
        } else {
            d.add(new double[]{t, v});
        }
    }

    private static int indexOf(int t, List<double[]> d) {
        for (int i = 0; i < d.size(); i++) {
            if (d.get(i)[0] == t) {
                return i;
            }
        }
        return -1;
    }

    private static List<double[]> quicksort(List<double[]> list, int start, int end) {
        if (list == null || list.size() == 0) {
            return list;
        }
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if((int)list.get(i)[0] > (int)list.get(j)[0]) {
                    double[] tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
//        if(start >= end) {
//            return list;
//        }
//        int pos = start;
//        for(int i = pos + 1; i < end; i++) {
//            boolean needSwap = false;
//            if((int)list.get(i)[0] < (int)list.get(pos)[0]) {
//                needSwap = true;
//            }
//
//            if(needSwap) {
//                double[] tmp = list.get(i);
//                for(int m = i; m > pos; m--) {
//                    list.set(m, list.get(m - 1));
//                }
//                list.set(pos, tmp);
//            }
//            pos = i;
//        }
//        quicksort(list, start, pos);
//        quicksort(list, pos + 1, end);
//        return list;
    }
}
