package com.saille.rm.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ellias on 2017-09-07.
 */
public class ImdUtils {
    public static void main(String[] args) {
        try {
//            File f = new File("F:\\rm\\song\\canonrock\\canonrock_4k_hd.imd");
            File f = new File("F:\\temp\\‘≤÷‹¬ _4k_hd.imd");

            FileInputStream fis = new FileInputStream(f);
            byte[] bytes = new byte[(int)f.length()];
            fis.read(bytes);
            System.out.println(calcDifficult(bytes));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
//        System.out.println(bytes);
//        return 0;
    }

    private static double getLong(int i) {
        return ((double)i / 120.0) / 4.0;
    }

    private static int getKey(byte[] imds) {
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
