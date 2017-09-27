package com.saille.vos;

import com.saille.rm.util.RMUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellias on 2017-09-24.
 */
public class VosLoad {
    public static void main(String[] args) {
        try {
//            File f = new File("F:\\game\\VOS\\Album\\LV8\\Emerald Sword.VOS");
//            File f = new File("D:\\Ellias\\VOS\\Album\\VPT\\B\\Canon_in_D_mikkel.VOS");
            File f = new File("F:\\game\\vos\\album\\VST\\Hungarian dance No.5_2loopers_Classical_7.VOS");
//            File f = new File("D:\\Ellias\\VOS\\Album\\LV8\\10-L-gaim.VOS");
//            File f = new File("D:\\Ellias\\VOS\\Album\\VPT\\B\\In the mirror.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\A\\rich17.VOS");
            System.out.println(f.getName());
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\B\\kks6428's mid .VOS");
            byte[] bytes = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(bytes);
            fis.close();
            int offset = 0;
            int header = RMUtils.getInt(bytes, offset, 4);
            offset += 4;
            int segmentinfoaddress = RMUtils.getInt(bytes, offset, 4); //info
            offset += 4;
            offset += 16;
            int segmentmidaddress = RMUtils.getInt(bytes, offset, 4); //mid
            offset += 4;
            offset += 16;
            int segmenteofaddress = RMUtils.getInt(bytes, offset, 4); //EOF
            offset += 4;
            offset += 16;
            if(bytes[offset] == 0x56 && bytes[offset + 1] == 0x4f && bytes[offset + 2] == 0x53) { //Rank曲，title从134开始，需要额外+70
                offset += 70;
                int unknownlength = RMUtils.getInt(bytes, offset, 1);
                offset += 1;
                String unknown = RMUtils.getString(bytes, offset, unknownlength);
                offset += unknownlength;
            }
            int titlelength = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            String title = RMUtils.getString(bytes, offset, titlelength);
            offset += titlelength;
            int artistlength = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            String artist = RMUtils.getString(bytes, offset, artistlength);
            offset += artistlength;
            int commentlength = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            String comment = RMUtils.getString(bytes, offset, commentlength);
            offset += commentlength;
            int authorlength = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            String author = RMUtils.getString(bytes, offset, authorlength);
            offset += authorlength;
            /*
            00:Pop
            01:New Age
            02:Techno
            03:Rock
            04:SoundTrack
            05:Game & Anime
            06:Jazz
            07:CenturyEnd
            08:Classical
            09:Other
             */
            int musictype = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            int extendedMusicType = RMUtils.getInt(bytes, offset, 1);
            offset += 1;
            int songLength = RMUtils.getInt(bytes, offset, 4);
            offset += 4;
            int level = RMUtils.getInt(bytes, offset, 1); //0=1级,9=10级
            offset += 1;

            offset += 1023; //space: 00 * 1023
            List<String> instruments = new ArrayList<String>();
            List<int[]> keys = new ArrayList<int[]>();
            List<int[]> bpms = new ArrayList<int[]>();
            int maxSequence = 0;
            while (offset < segmentmidaddress) {
                int instrument = RMUtils.getInt(bytes, offset, 4);
                offset += 4;
                int beatCount = RMUtils.getInt(bytes, offset, 4);
                offset += 4;
                System.out.print("beat count=" + beatCount);
                offset += 14;
                for(int i = 0; i < beatCount; i++) {
                    int sequence = RMUtils.getInt(bytes, offset, 4);
                    offset += 4;
                    if(i == 0) {
                        System.out.print("\tfirst = " + sequence);
                    } else if(i == beatCount - 1) {
                        System.out.println("\tlast = " + sequence);
                    }
                    int duration = RMUtils.getInt(bytes, offset, 4);
                    offset += 4;
                    int channel = RMUtils.getInt(bytes, offset, 1);
                    offset += 1;
                    int pitch = RMUtils.getInt(bytes, offset, 1); //音高
                    offset += 1;
                    int volume = RMUtils.getInt(bytes, offset, 1);
                    offset += 1;
                    int playkey = RMUtils.getInt(bytes, offset, 1);
                    offset += 1;
                    int notetype = RMUtils.getInt(bytes, offset, 1);
                    int foruser = (playkey & 0x80) >> 7;
                    int key = (playkey & 0x70) >> 4;
                    offset += 1;
                    insert(bpms, sequence, duration);
                    if(foruser == 1) {
                        insert(keys, new int[]{sequence, key, notetype == 0x80 ? duration : 1});
                    }
                    maxSequence = Math.max(maxSequence, sequence);
                }
                instruments.add(instrument + "");
            }
            System.out.println("=====================");
            for(int i = 0; i < keys.size(); i++) {
                System.out.println(keys.get(i)[0] + "\t" + keys.get(i)[1] + "\t" + keys.get(i)[2]);
            }
            //检查tempo
            List<int[]> tempos = new ArrayList<int[]>(); //int[]{tempo, size};
            List<String> others = new ArrayList<String>();
            int currenttempo = 0;
            int currenttemposizeoffset = 0;
            for(int i = segmentmidaddress; i < bytes.length - 2; i++) {
                if(bytes[i] == -1 && bytes[i + 1] == 0x51 && bytes[i + 2] == 0x03) {
                    byte[] tempobyte = ArrayUtils.subarray(bytes, i + 3, i + 6);
                    ArrayUtils.reverse(tempobyte);
                    currenttempo = RMUtils.getInt(tempobyte, 0, tempobyte.length);
                    currenttempo = (int)Math.round(60000000.0 / (double)currenttempo);
                    tempos.add(new int[]{currenttempo, 0, 0, 0, 0});
                    if(currenttemposizeoffset != 0) {
                        byte[] otherbytes = ArrayUtils.subarray(bytes, currenttemposizeoffset, i);
                        int s = getNumber(otherbytes);
                        tempos.get(tempos.size() - 2)[1] = s;
                    }
                    i += 6;
                    currenttemposizeoffset = i;
                } else if(bytes[i] == -1 && bytes[i + 1] == 0x2f) {
                    byte[] otherbytes = ArrayUtils.subarray(bytes, currenttemposizeoffset, i);
                    int s = getNumber(otherbytes);
                    tempos.get(tempos.size() - 1)[1] = s;
                    break;
                }
            }
            tempos.get(0)[2] = tempos.get(0)[1];
            for(int i = 1; i < tempos.size(); i++) {
                tempos.get(i)[2] = tempos.get(i)[1] + tempos.get(i - 1)[2];
                if(i == tempos.size() - 1) {
                    tempos.get(i)[1] = songLength - tempos.get(i - 1)[2];
                    tempos.get(i)[2] = songLength;
                }
            }
            for(int i = 0; i < tempos.size(); i++) {
                if(i == 0) {
                    tempos.get(i)[3] = 0;
                    tempos.get(i)[4] = (int)(tempos.get(i)[2] * tempos.get(i)[0] * 12.8 / 1000);// sec*bpm*12.8/1000
                } else {
                    tempos.get(i)[3] = tempos.get(i - 1)[4] + 1;
                    tempos.get(i)[4] = tempos.get(i)[3] + (int)(tempos.get(i)[1] * tempos.get(i)[0] * 12.8 / 1000);
                }
                System.out.println(tempos.get(i)[0] + "\t" + tempos.get(i)[1] + "\t" + tempos.get(i)[2] + "\t" + tempos.get(i)[3] + "\t" + tempos.get(i)[4]);
            }
            List<byte[]> bpmsImd = new ArrayList<byte[]>();
            int currentTimeMillSec = 0;
            for(int i = 0; i < tempos.size(); i++) {
                double bpm = tempos.get(i)[0];
                int duration = tempos.get(i)[1];
                int end = tempos.get(i)[2];
                double timechip = 60000 / bpm;
                int count = 0;
                while (currentTimeMillSec < end) {
                    bpmsImd.add(RMUtils.int2byte(end - duration + (int) (count * timechip)));
                    bpmsImd.add(RMUtils.longToBytes(Double.doubleToLongBits(bpm)));
                    count++;
                    currentTimeMillSec += timechip;
                }
            }
            int count = 0;
            for(int i = 0; i < bpmsImd.size(); i++) {
                count += bpmsImd.get(i).length;
            }
            System.out.println("totalcount=" + count);
            System.out.println("songLength=" + songLength);
            System.out.println("maxSequence="+maxSequence);
            File outfile = new File("F:\\temp\\a\\new.imd");
            if(!outfile.exists()) {
                outfile.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(outfile);
            fos.write(RMUtils.int2byte(songLength));
            fos.write(RMUtils.int2byte(count / 12));
            for(int i = 0; i < bpmsImd.size(); i++) {
                for(int j = 0; j < bpmsImd.get(i).length; j++) {
                    fos.write(bpmsImd.get(i)[j]);
                }
            }
            fos.write(0x03);
            fos.write(0x03);
            quicksort(keys, 0, keys.size());
            fos.write(RMUtils.int2byte(keys.size()));
            for(int i = 0; i < keys.size(); i++) {
                int o = keys.get(i)[0]; //offset
                int k = keys.get(i)[1]; //key
                int d = keys.get(i)[2]; //duration
                if(d > 1) {
                    fos.write(0x02);
                } else {
                    fos.write(0x00);
                }
                fos.write(0x00);
                fos.write(RMUtils.int2byte(o));
                fos.write(k);
                if(d > 1) {
                    fos.write(RMUtils.int2byte(d));
                } else {
                    fos.write(RMUtils.int2byte(0));
                }
            }
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static int getNumber(byte[] bytes) {
        int ret = 0;
        for(int i = 0; i < bytes.length; i++) {
            int v = bytes[i];
            if(v < 0) {
                v += 256;
            }
            ret = ret * 128 + v % 128;
            if(v < 128) {
                break;
            }
        }
        return ret;
    }
    private static String getByteStr(byte[] otherbytes) {
        String ret = "";
        for(byte b : otherbytes) {
            int i = (int) b;
            if(i < 0) {
                i += 256;
            }
            String s = Integer.toHexString(i);
            if(s.length() < 2) {
                s = "0" + s;
            }
            ret += s;
        }
        return ret;
    }

    private static void insert(List<int[]> bpms, int sequence, int duration) {
        for(int i = 0; i < bpms.size(); i++) {
            if(sequence < bpms.get(i)[0]) {
                bpms.add(i, new int[]{sequence, duration});
                return;
            }
            if(sequence == bpms.get(i)[0]) {
                if(duration < bpms.get(i)[1]) {
                    bpms.add(i, new int[]{sequence, duration});
                    return;
                } else if(duration == bpms.get(i)[1]) {
                    return;
                }
            }
        }
        bpms.add(new int[]{sequence, duration});
    }
    private static void insert(List<int[]> keys, int[] newkey) {
        for(int i = 0; i < keys.size(); i++) {
            if(newkey[0] < keys.get(i)[0]) {
                keys.add(i, newkey);
                return;
            } else if(newkey[0] == keys.get(i)[0]) {
                if(newkey[1] < keys.get(i)[1]) {
                    keys.add(i, newkey);
                    return;
                } else if(newkey[1] == keys.get(i)[1]) {
                    keys.get(i)[2] = Math.max(newkey[2], keys.get(i)[2]);
                    return;
                }
            }
        }
        keys.add(newkey);
    }
    private static String convert(String in) {
        String ret = in;
        while (ret.length() < 8) {
            ret = "0"+ret;
        }
        return ret;
    }

    private static List<int[]> quicksort(List<int[]> list, int start, int end) {
        if (list == null || list.size() == 0) {
            return list;
        }
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if((int)list.get(i)[0] > (int)list.get(j)[0]) {
                    int[] tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
    }
}
