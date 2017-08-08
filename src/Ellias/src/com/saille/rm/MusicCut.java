package com.saille.rm;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-5-25
 * Time: 22:22:11
 * To change this template use File | Settings | File Templates.
 */
public class MusicCut {
    private final static int startblank = 2000;
    public static void main(String[] args) {
        try {
//            System.out.println(byte2int(new byte[]{(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff}));
            String imdfile = "D:\\rm\\canonrock_4k_hd.imd";
            int begin = 60000;
            int end = 90000;
            String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            cut(new String[]{imdfile, "D:\\temp\\canonrock.mp3"}, now, begin, end);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void cut(String[] file, String now, int begin, int end) throws Exception {
        int[] time = changeImd(file[0], now, begin, end - begin);
        System.out.println(time[0] + "/" + time[1]);
        changemp3(file[1], now, time[0], time[1]);
    }

    private static String convertTime(int time) {
        DecimalFormat df = new DecimalFormat("00");
        int sec = time % 60;
        int min = time / 60;
        int hour = min / 60;
        min = min % 60;
        return df.format(hour) + ":" + df.format(min) + ":" + df.format(sec);
    }

    public static void changemp3(String path, String time, int start, int end) throws Exception {
        File f = new File("d:\\temp\\" + time + "_result.mp3");
        if(f.exists()) {
            f.delete();
        }
//        Runtime.getRuntime().exec("d:\\software\\ffmpeg\\ffmpeg.exe -i d:\\\\temp\\\\canonrock.mp3 -ss " + convertTime(start) + " -t " + convertTime(end) + " -vcodec copy -vn d:\\\\temp\\\\canon2.mp3");
        String cmd = "d:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe -i " + path.replaceAll("\\\\", "\\\\\\\\") + " -ss " + convertTime(start / 1000) + " -t " + convertTime((end - start) / 1000) + " -vcodec copy -vn d:\\\\temp\\\\" + time + "_result.mp3";
        System.out.println(cmd);
        Process p = Runtime.getRuntime().exec(cmd);
        InputStream is = p.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String tmp;
        while((tmp = br.readLine()) != null) {
            System.out.println(tmp);
        }
        p.getInputStream().close();
        p.getOutputStream().close();
        p.getErrorStream().close();
        p.destroy();
    }

    public static int[] changeImd(String path, String time, int start, int length) throws Exception {
        FileInputStream fis = new FileInputStream(path);
//        FileOutputStream fos1 = new FileOutputStream("D:\\temp\\change1.imd");
        FileOutputStream fos = new FileOutputStream("D:\\temp\\" + time + "_temp1.imd");

        int imdlength = 0;
        byte[] bb = new byte[4];
        fis.read(bb);
        imdlength = byte2int(bb);
        if((start + length) > imdlength) {
            length = imdlength - start;
        }
//        fis.skip(4);
        fis.read(bb);
        int count = byte2int(bb);
        fis.skip(12 * count);
        fis.skip(2); //0303

        fis.read(bb);
        count = byte2int(bb);
        int i1 = -1, i2 = -1, last = -1;
        int begin = start - startblank;
        int[] miantiao = new int[6];
        int keycount = 0, bpmcount = 0;
        for(int i = 0; i < count; i++) {
            bb = new byte[11];
            fis.read(bb);
            int type = bb[0];
            if(type < 0) {
                type += 256;
            }
            int offset = byte2int(new byte[]{bb[2], bb[3], bb[4], bb[5]});
            if(i1 < 0) {
                if(offset >= start) {
                    i1 = offset;
                    i2 = i1 + length;
                } else {
                    continue;
                }
            }
            boolean needwrite = false;
            if (type == 0x00) { //点
                if(offset < i2) {
                    needwrite = true;
                }
            } else if(type == 0x01) { //普通划键
                if(offset < i2) {
                    needwrite = true;
                }
            } else if(type == 0x02) { //普通面条
                if(offset < i2) {
                    i2 = Math.max(i2, offset + byte2int(new byte[]{bb[7], bb[8], bb[9], bb[10]}));
                    needwrite = true;
                }
            } else if(type == 0x21) { //水蛇中间划键
                if(miantiao[bb[6]] > 0) {
                    miantiao[bb[6] + bb[7]] = 1;
                    miantiao[bb[6]] = 0;
                    needwrite = true;
                }
            } else if(type == 0x22) { //水蛇中间面条
                if(miantiao[bb[6]] > 0) {
                    needwrite = true;
                }
            } else if(type == 0x61) { //水蛇开始划键
                if(offset < i2) {
                    i2 = Math.max(i2, offset);
                    miantiao[bb[6] + bb[7]] = 1;
                    needwrite = true;
                }
            } else if(type == 0x62) { //水蛇开始面条
                if(offset < i2) {
                    i2 = Math.max(i2, offset);
                    miantiao[bb[6]] = 1;
                    needwrite = true;
                }
            } else if(type == 0xa1) { //水蛇结尾划键
                if(miantiao[bb[6]] == 1) {
                    i2 = Math.max(i2, offset);
                    miantiao[bb[6]] = 0;
                    needwrite = true;
                }
            } else if(type == 0xa2) { //水蛇结尾面条
                if(miantiao[bb[6]] == 1) {
                    i2 = Math.max(i2, offset + byte2int(new byte[]{bb[7], bb[8], bb[9], bb[10]}));
                    miantiao[bb[6]] = 0;
                    needwrite = true;
                }
            }
            if(needwrite) {
                keycount++;
                for(byte b : bb) {
                    fos.write(b);
                }
            }
        }
        System.out.println("i1=" + i1 + ", i2=" + i2);

        fis.close();
        fos.close();

        //第二遍，写第一段
        fis = new FileInputStream(path);
        DataInputStream dis = new DataInputStream(fis);
        fos = new FileOutputStream("D:\\temp\\" + time + "_temp2.imd");

        fis.skip(4);
        bb = new byte[4];
        fis.read(bb);
        count = byte2int(bb);
//        i1 = -1; i2 = -1;
        i1 = i1 - startblank;
        i1 = (i1 / 1000) * 1000;
        if(i1 < 0) {
            i1 = 0;
        }
        i2 += startblank;
        i2 = (i2 / 1000) * 1000;
        for(int i = 0; i < count; i++) {
            bb = new byte[4];
            fis.read(bb);
            int offset = byte2int(bb);
//            if(i1 == -1) {
                if(offset < i1) {
                    fis.skip(8);
                    continue;
                }
//            }
            if(offset > i2) {
                break;
            }
            bpmcount++;
            fos.write(int2byte(offset - i1));
            for(int j = 0; j < 8; j++) {
                fos.write(fis.read());
            }
        }
        fis.close();
        fos.close();

        fis = new FileInputStream("D:\\temp\\" + time + "_temp2.imd");
        fos = new FileOutputStream("D:\\temp\\" + time + "_result.imd");
        fos.write(int2byte(i2 - i1));
        fos.write(int2byte(bpmcount));
        int tmp;
        while((tmp = fis.read()) >= 0) {
            fos.write(tmp);
        }
        fis.close();
        fos.write((byte)3);
        fos.write((byte)3);
        fos.write(int2byte(keycount));
        fis = new FileInputStream("D:\\temp\\" + time + "_temp1.imd");
        bb = new byte[4];
        for(int i = 0; i < keycount; i++) {
            fos.write(fis.read());
            fos.write(fis.read());
            fis.read(bb);
            fos.write(int2byte(byte2int(bb) - i1));
            for(int j = 0; j < 5; j++) {
                fos.write(fis.read());
            }
        }
//        while((tmp = fis.read()) >= 0) {
//            fos.write(tmp);
//        }
        fis.close();
        fos.close();
        return new int[]{i1, i2};
    }

    private static byte[] int2byte(int i) {
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

    private static int byte2int(byte[] bb) {
        int ret = 0;
        for(int i = 0; i < bb.length; i++) {
            ret += Math.pow(256, i) * (int)(bb[i] < 0 ? (256 + bb[i]) : bb[i]);
        }
        return ret;
    }
}
