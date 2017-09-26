package com.saille.vos;

import com.saille.rm.util.RMUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ellias on 2017-09-24.
 */
public class VosLoad {
    public static void main(String[] args) {
        try {
//            File f = new File("F:\\game\\VOS\\Album\\LV8\\Emerald Sword.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\B\\Canon_in_D_mikkel.VOS");
            File f = new File("F:\\game\\VOS\\Album\\VPT\\B\\In the mirror.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\B\\kks6428's mid .VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\A\\rich17.VOS");
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
//                    System.out.println(Integer.toBinaryString(notetype));
//                    int color = (playkey & 0xE0) >> 5;
                    int foruser = (playkey & 0x80) >> 7;
                    int key = (playkey & 0x70) >> 4;
//                    int longnote = playkey & 1;
                    offset += 1;
//                    if(foruser == 1) {
                    insert(bpms, sequence, duration);
                    if(foruser == 1) {
                        insert(keys, new int[]{sequence, key, notetype == 0x80 ? duration : 1});
//                        System.out.println(i + "\tsequence=" + sequence + "\tduration=" + duration + "\toffset=" + offset + "\tforuser=" + foruser + "\t"+key);
//                        System.out.println(i + "\tsequence=" + sequence + "\tduration=" + duration + "\toffset=" + offset + "\tnotebyte=" + notetype +"\tforuser="+foruser);
                    }
                    maxSequence = Math.max(maxSequence, sequence);
                }
//                System.out.println("============instrument=" + instrument + "end============");
                instruments.add(instrument + "");
            }
            System.out.println("=====================");
//            for(int i = 0; i < bpms.size(); i++) {
//                System.out.println(bpms.get(i)[0] + "\t" + bpms.get(i)[1]);
//            }
            for(int i = 0; i < keys.size(); i++) {
                System.out.println(keys.get(i)[0] + "\t" + keys.get(i)[1] + "\t" + keys.get(i)[2]);
            }
            //检查tempo
            List<int[]> tempos = new ArrayList<int[]>(); //int[]{tempo, size};
            int currenttempo = 0;
            int currenttemposizeoffset = 0;
            for(int i = segmentmidaddress; i < bytes.length - 2; i++) {
                if(bytes[i] == -1 && bytes[i + 1] == 0x51 && bytes[i + 2] == 0x03) {
                    byte[] tempobyte = ArrayUtils.subarray(bytes, i + 3, i + 6);
                    ArrayUtils.reverse(tempobyte);
//                    currenttempo = RMUtils.getInt(tempobyte, 0, tempobyte.length);
//                    currenttempo = (int)Math.round(60000000.0 / (double)tempo);
                    i += 6;
                    currenttemposizeoffset = i;
                } else if(bytes[i] == -1 && bytes[i + 1] == 0x2f) {
                    break;
                }
            }
//            System.out.println("tempo=" + tempo);
            System.out.println("songLength=" + songLength);
            System.out.println("maxSequence="+maxSequence);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
}
