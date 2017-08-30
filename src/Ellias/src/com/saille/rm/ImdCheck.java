package com.saille.rm;

import com.GlobalConstant;

import java.io.FileInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2016-4-25
 * Time: 13:36:02
 * To change this template use File | Settings | File Templates.
 */
/* 检查imd谱面文件是否有问题 */
public class ImdCheck {
    public static void main(String[] args) {
        try {
//            File dir = new File("D:\\rm\\song");
//            run(dir);
            check(GlobalConstant.DISKPATH + "rm\\song\\laozishuo\\laozishuo_4k_hd.imd");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String run(File f) throws Exception {
        if(f.isDirectory()) {
            File[] ff = f.listFiles();
            for(File fff : ff) {
                run(fff);
            }
        } else {
            if(f.getAbsolutePath().endsWith(".imd")) {
                check(f.getAbsolutePath());
            }
        }
        return null;
    }

    public static String check(String path) throws Exception {
        System.out.println("path:" + path);
        String ret = "";
        FileInputStream fis;
        byte[] bb = new byte[4];

        for(;;) {
            boolean ok = true;
            List<byte[]> list = new ArrayList<byte[]>();
            if(ok) {
                /* 第一遍，检查key顺序是否有问题 */
                fis = skipHead(path);
                fis.read(bb);
                int length = KeyLoad.byte2int(bb);
                int offset = 0;
                for(int i = 0; i < length; i++) {
                    bb = new byte[11];
                    fis.read(bb);
                    int now = KeyLoad.byte2int(Arrays.copyOfRange(bb, 2,6));
//                    if(now < offset) {
//                        ret = "在" + now + "毫秒处时间序列有误，小于上一个键的时间：" + offset;
//                        ok = false;
//                        break;
//                    }
//                    offset = now;
                    list.add(bb);
                }
                list = sort(list, 0, list.size());
                fis.close();
            }
            if(ok) {
                /* 第2遍，检查是否有叠键 */
//                fis = skipHead(path);
//                bb = new byte[4];
//                fis.read(bb);
//                int length = KeyLoad.byte2int(bb);
                int last[] = new int[6];
                bb = new byte[11];
                for(int i = 0; i < list.size(); i++) {
//                    fis.read(bb);
                    bb = list.get(i);
                    int now = KeyLoad.byte2int(Arrays.copyOfRange(bb, 2,6));
                    if(last[bb[6]] == now) {
                        ret = "在" + now + "毫秒处有叠键";
                        ok = false;
                        break;
                    }
                    last[bb[6]] = now;
                }
//                fis.close();
            }
            if(ok) {
                /* 第3遍，检查有没有键超出1-6道范围 */
//                fis = skipHead(path);
//                bb = new byte[4];
//                fis.read(bb);
//                int length = KeyLoad.byte2int(bb);
                bb = new byte[11];
                int max = -1;
                for(int i = 0; i < list.size(); i++) {
//                    fis.read(bb);
                    bb = list.get(i);
                    int now = KeyLoad.byte2int(Arrays.copyOfRange(bb, 2,6));
                    if(bb[6] < 0 || bb[6] > 5) {
                        ret = "在" + now + "毫秒处键位不在0-5之间";
                        ok = false;
                        break;
                    }
                    max = Math.max(max, bb[6]);
                    if(bb[0] == 0x01 || //划键
                            bb[0] == -95 || //面条结尾划键
                            bb[0] == 0x21 || //面条中间划键
                            bb[0] == 0x61 //面条开始划键
                            ) {
                        int target = bb[6] + bb[7];
                        if(target < 0 || target > 5) {
                            ret = "在" + now + "毫秒处划键结果超出0-5键位";
                            ok = false;
                            break;
                        }
                        max = Math.max(max, target);
                    }
                }
//                System.out.println("最大键位：" + max);
//                fis.close();
            }
            if(ok) {
                /* 第4遍，检查有没有面条/划键中间的键 */
//                fis = skipHead(path);
//                bb = new byte[4];
//                fis.read(bb);
//                int length = KeyLoad.byte2int(bb);
                bb = new byte[11];
                int[] begin = new int[6], end = new int[6];
                for(int i = 0; i < list.size(); i++) {
//                    fis.read(bb);
                    bb = list.get(i);
                    int now = KeyLoad.byte2int(Arrays.copyOfRange(bb, 2,6));
                    if(end[bb[6]] > now) {
                        ret = "在" + now + "毫秒处面条中件有键";
                        ok = false;
                        break;
                    }
                    if(bb[0] == 2 ||
                            bb[0] == -94 || //结尾长键
                            bb[0] == 0x22 || //中间长键
                            bb[0] == 0x62 //开始长键
                            ) {
                        begin[bb[6]] = now;
                        end[bb[6]] = now + KeyLoad.byte2int(Arrays.copyOfRange(bb, 7,11));
                    }
                }
//                fis.close();
            }
            if(ok) {
                /* 第5遍，检查面条是不是都连在一起 */
            }
            break;
        }
        System.out.println(ret);
        return null;
    }

    private static List<byte[]> sort(List<byte[]> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(KeyLoad.byte2int(Arrays.copyOfRange(list.get(i),2,6)) < KeyLoad.byte2int(Arrays.copyOfRange(list.get(pos),2,6))) {
//            if(list.get(i)[1].compareTo(list.get(pos)[1]) > 0) {
                needSwap = true;
//            } else if(list.get(i)[1].compareTo(list.get(pos)[1]) == 0) {
//                if(list.get(i)[3].compareTo(list.get(pos)[3]) > 0) {
//                    needSwap = true;
//                }
            }

            if(needSwap) {
                byte[] tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sort(list, start, pos);
        sort(list, pos + 1, end);
        return list;
    }

    private static FileInputStream skipHead(String path) throws Exception {
        FileInputStream fis = new FileInputStream(path);
        fis.skip(4);
        byte[] bb = new byte[4];
        fis.read(bb);
        int length = KeyLoad.byte2int(bb);
        for(int i = 0; i < length; i++) {
            fis.skip(12);
        }
        fis.skip(2);
        return fis;
    }
}
