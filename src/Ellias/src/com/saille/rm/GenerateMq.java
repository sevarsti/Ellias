package com.saille.rm;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-12-14
 * Time: 8:45:00
 * To change this template use File | Settings | File Templates.
 */
public class GenerateMq {
    private final static String PATH = "D:" + File.separator + "rm" + File.separator + "song";
    public static void main(String[] args) {
        try {
            File file = new File(getFilePath("daybyday", "4", "hd"));
            FileInputStream fis = new FileInputStream(file);
            byte[] b = new byte[4];
            fis.read(b); //length
            fis.read(b); //jiepai
            int skip = byte2int(b);
            fis.skip(skip * 12);
            fis.skip(2);//0303
            fis.read(b);
            int count = byte2int(b);
            List<String[]> keys = new ArrayList<String[]>();
            b = new byte[11];
            int longidx = 7;
            int[] longs = new int[6];
            for(int i = 0; i < count; i++) {
                fis.read(b);
                byte action = b[0];
                int offset = byte2int(new byte[]{b[2], b[3], b[4], b[5]});
                //offset press place time
                //offset down  place idx
                //offset move  place idx moveto
                //offset up    place idx
                switch(action) {
                    case 0x00: //单键
                        keys.add(new String[]{action + "", offset + "", "press", b[6] + "", "1"});
                        break;
                    case 0x01: //划键
                        keys.add(new String[]{action + "", offset + "", "down", longidx + "", b[6] + ""});
                        keys.add(new String[]{action + "", offset + "", "move", b[6] + "", longidx + "", byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + ""});
                        keys.add(new String[]{action + "", offset + "", "up", longidx + ""});
                        longidx++;
                        break;
                    case 0x02: //面条
                        keys.add(new String[]{action + "", offset + "", "press", b[6] + "", byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + ""});
                        break;
                    case 0x21: //中间划键
                        keys.add(new String[]{action + "", offset + "", "move", b[6] + "", longs[b[6]] + "", byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + ""});
                        longs[b[6] + byte2int(new byte[]{b[7], b[8], b[9], b[10]})] = longs[b[6]];
                        longs[b[6]] = 0;
                        break;
                    case 0x22: //中间长键
                        break;
                    case 0x61: //开始划键
                        keys.add(new String[]{action + "", offset + "", "down", longidx + "", b[6] + ""});
                        keys.add(new String[]{action + "", offset + "", "move", b[6] + "", longidx + "", byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + ""});
                        longs[b[6] + byte2int(new byte[]{b[7], b[8], b[9], b[10]})] = longidx;
                        longidx++;
                        break;
                    case 0x62: //开始长键
                        keys.add(new String[]{action + "", offset + "", "down", longidx + "", b[6] + ""});
                        longs[b[6]] = longidx;
                        longidx++;
                        break;
                    case -94: //结束长键
//                        keys.add(new String[]{offset + "", "up", longidx + ""});
                        keys.add(new String[]{action + "", offset + byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + "", "up", longs[b[6]] + ""});
                        longs[b[6]] = 0;
                        break;
                    case -95: //结束划键
                    {
                        keys.add(new String[]{action + "", offset + "", "move", b[6] + "", longs[b[6]] + "", byte2int(new byte[]{b[7], b[8], b[9], b[10]}) + ""});
                        keys.add(new String[]{action + "", offset + "", "up", longs[b[6]] + ""});
                        longs[b[6]] = 0;
                        break;
                    }
                    default:
                        System.out.println("unknown!");
                }
            }
            for(int i = 1; i < keys.size(); i++) {
                if(Integer.parseInt(keys.get(i)[1]) < Integer.parseInt(keys.get(i - 1)[1])) {
                    String[] tmp = keys.get(i);
                    keys.remove(i);
                    keys.add(i - 1, tmp);
                    i--;
                }
            }
            for(int i = 0; i < keys.size(); i++) {
                String[] kk = keys.get(i);
                for(String k : kk) {
                    System.out.print(k + "\t");
                }
                System.out.println();
            }
            StringBuffer sb = new StringBuffer();
            int begin = Integer.parseInt(keys.get(0)[1]);
            begin = 0;
            for(int i = 0; i < keys.size(); i++) {
                String[] kk = keys.get(i);
                if(i > 0) {
                    if(!kk[1].equals(keys.get(i - 1)[1])) {
                        sb.append("delay (" + (Integer.parseInt(kk[1]) - begin) + "- TickCount() + begintime)\r\n");
//                        sb.append("delay " + (Integer.parseInt(kk[1]) - Integer.parseInt(keys.get(i - 1)[1])) + "\r\n");
                    }
                } else {
                    sb.append("delay (" + (Integer.parseInt(kk[1]) - begin) + "- TickCount() + begintime)\r\n");
                }
                if(kk[2].equals("press")) {
//                    sb.append("Thread.Start(press, 165, " + (Integer.parseInt(kk[3]) * 500 + 245) + ", " + kk[3] + ", " + kk[4] + ")");
                    sb.append("press(165, " + (Integer.parseInt(kk[3]) * 500 + 245) + ", " + kk[3] + ", " + kk[4] + ")");
                } else if(kk[2].equals("down")) {
//                    sb.append("a = Thread.Start(down, 165," + (Integer.parseInt(kk[4]) * 500 + 245) + "," + kk[3] + ")");
                    sb.append("down(165," + (Integer.parseInt(kk[4]) * 500 + 245) + "," + kk[3] + ")");
//                    sb.append("\r\ndelay(100)");
                } else if(kk[2].equals("move")) {
                    sb.append("move(165," + ((Integer.parseInt(kk[3]) + Integer.parseInt(kk[5])) * 500 + 245) + "," + kk[4] + "");
//                    sb.append("Thread.Start(move, 165," + ((Integer.parseInt(kk[3]) + Integer.parseInt(kk[5])) * 500 + 245) + "," + kk[4] + ")");
                    if(i < keys.size() - 1) {
                        if(keys.get(i + 1)[2].equals("up") && keys.get(i + 1)[3].equals(kk[4]) &&
                                keys.get(i + 1)[1].equals(kk[1])){
                            sb.append(", true)");
//                            sb.append(", true)\r\nup(" + kk[4] + ")");
                        } else {
                            sb.append(", false)");
                        }
                    } else {
                        sb.append(", false)");
                    }
                } else if(kk[2].equals("up")) {
                    if(keys.get(i - 1)[2].equals("move") && kk[3].equals(keys.get(i - 1)[4]) &&
                            keys.get(i - 1)[1].equals(kk[1])) {
                    } else {
                        sb.append("up(" + kk[3] + ")");
//                    sb.append("Thread.Start(up, " + kk[3] + ")");
                    }
                } else {
                    System.out.println("!!");
                }
                sb.append("\r\n");
            }
            System.out.println(sb.toString());
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getFilePath(String song, String key, String lv) {
        return PATH + File.separator + song + File.separator + song + "_" + key + "k_" + lv + ".imd";
    }

    public static int byte2int(byte[] bb) {
        int ret = 0;
        for(int i = 0; i < bb.length; i++) {
            ret += Math.pow(256, i) * (int)(bb[i] < 0 ? (256 + bb[i]) : bb[i]);
        }
        if(ret > Integer.MAX_VALUE / 2) {
            ret = Integer.MIN_VALUE + ret;
        }
        return ret;
    }
}
