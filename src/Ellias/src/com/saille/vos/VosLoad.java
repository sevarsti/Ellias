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
//            File f = new File("D:\\ellias\\VOS\\Album\\Emerald Sword .VOS");
            File f = new File("D:\\Ellias\\VOS\\Album\\VPT\\B\\Canon_in_D_mikkel.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\Death Practice\\Major Demon-2185.vos");
//            File f = new File("D:\\ellias\\VOS\\Album\\Death Practice\\First_Song.vos");
//            File f = new File("D:\\Ellias\\vos\\albumbackup\\VST\\Hungarian dance No.5_2loopers_Classical_7.VOS");
//            File f = new File("F:\\game\\vos\\album\\VPT\\B\\Laputa Theme.VOS");
//            File f = new File("D:\\Ellias\\VOS\\Album\\LV8\\10-L-gaim.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\B\\In the mirror.VOS");
//            File f = new File("F:\\game\\VOS\\Album\\VPT\\A\\rich17.VOS");
//            File f = new File("D:\\Ellias\\VOS\\Album\\dx617\\ez\\Rusty Nail.vos");
            System.out.println(f.getName());
            byte[] bytes = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(bytes);
            fis.close();
            byte[] midiBytes = convertVos032Midi(bytes);
            FileOutputStream fos = new FileOutputStream("D:\\Ellias\\VOS\\Album\\VPT\\B\\Canon_in_D_mikkel_new.mid");
            fos.write(midiBytes);
            fos.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @param bytes vos文件内容
     * @return imd文件路径, mp3文件路径
     */
    public static String[] convert(byte[] bytes) {
        if(bytes[0] == 0x03) {
            loadVos03(bytes);
        }
        return null;
    }

    public static byte[] convertVos032Midi(byte[] bytes) {
        List<byte[]> list = new ArrayList<byte[]>();
        int segmentmidaddress = RMUtils.getInt(bytes, 24, 4);
        list.add(ArrayUtils.subarray(bytes, segmentmidaddress, bytes.length));
        /* 检查midi信息中MTrk次数，并更新对应字段(第11个字节) */
        int mtrkCount = 0;
        for(int i = segmentmidaddress; i < bytes.length - 3; i++) {
            if(bytes[i] == 0x4d && bytes[i + 1] == 0x54 && bytes[i + 2] == 0x72 && bytes[i + 3] == 0x6b) {
                mtrkCount++;
            }
        }
//        list.get(0)[11] = (byte)(list.get(0)[11] + mtrkCount - 1);

        /* 检查音轨开始字节 */
        int offset = 0;
        int header = RMUtils.getInt(bytes, offset, 4);
        offset += 4;
        int segmentinfoaddress = RMUtils.getInt(bytes, offset, 4); //info
        offset += 4;
        offset += 16;
//        int segmentmidaddress = RMUtils.getInt(bytes, offset, 4); //mid
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
//        String title = RMUtils.getString(bytes, offset, titlelength);
        offset += titlelength;
        int artistlength = RMUtils.getInt(bytes, offset, 1);
        offset += 1;
//        String artist = RMUtils.getString(bytes, offset, artistlength);
        offset += artistlength;
        int commentlength = RMUtils.getInt(bytes, offset, 1);
        offset += 1;
//        String comment = RMUtils.getString(bytes, offset, commentlength);
        offset += commentlength;
        int authorlength = RMUtils.getInt(bytes, offset, 1);
        offset += 1;
//        String author = RMUtils.getString(bytes, offset, authorlength);
        offset += authorlength;
//        int musictype = RMUtils.getInt(bytes, offset, 1);
        offset += 1;
//        int extendedMusicType = RMUtils.getInt(bytes, offset, 1);
        offset += 1;
//        int songLength = RMUtils.getInt(bytes, offset, 4);
        offset += 4;
//        int level = RMUtils.getInt(bytes, offset, 1); //0=1级,9=10级
        offset += 1;

        offset += 1023; //space: 00 * 1023
        int maxSequence = 0;
        int trackCount = 0;
//        for(int i = 0; i < list.get(0)[11] - 1; i++) { //循环每个音轨
        while(true){ //循环每个音轨
            int instrument = RMUtils.getInt(bytes, offset, 4);
            trackCount++;
            offset += 4;
            int beatCount = RMUtils.getInt(bytes, offset, 4);
            offset += 4;
            System.out.println("beat count=" + beatCount);
            offset += 14;
            int endOffset = beatCount * 13 + offset;
            List<int[]> trackDetails = new ArrayList<int[]>(); //sequence, duration, channel, pitch, volume
            for(int j = 0; j < beatCount; j++) {
                int sequence = RMUtils.getInt(bytes, offset, 4);
                int duration = RMUtils.getInt(bytes, offset + 4, 4);
                int channel = RMUtils.getInt(bytes, offset + 8, 1); //轨道
                int pitch = RMUtils.getInt(bytes, offset + 9, 1); //音高
                int volume = RMUtils.getInt(bytes, offset + 10, 1); //力度
                trackDetails.add(new int[]{sequence, channel, pitch, volume}); //按键
                trackDetails.add(new int[]{sequence + duration, channel, pitch, 0}); //松键
                offset += 13;
            }
            sortTrackDetails(trackDetails, 0, trackDetails.size());
            List<byte[]> trackBytes = new ArrayList<byte[]>(); //时间差,音轨,音高,力度
            int prvSequence = 0;
            int trackLength = 0;
            for(int j = 0; j < trackDetails.size(); j++) {
                byte[] trackByte = getDeltatime(trackDetails.get(j)[0], prvSequence);
                prvSequence = trackDetails.get(j)[0];
                trackByte[trackByte.length - 3] = (byte)trackDetails.get(j)[1];
                trackByte[trackByte.length - 2] = (byte)trackDetails.get(j)[2];
                trackByte[trackByte.length - 1] = (byte)trackDetails.get(j)[3];
                trackBytes.add(trackByte);
                trackLength += trackByte.length;
            }
            trackBytes.add(new byte[]{0x00, (byte)0xff, 0x2f, 0x00});
            trackLength += 4;
            list.add(new byte[]{0x4d, 0x54, 0x72, 0x6b});
            byte[] lengthByte = RMUtils.int2byte(trackLength + 3); //00,音轨,乐器，共3位
            ArrayUtils.reverse(lengthByte);
            list.add(lengthByte);
            list.add(new byte[]{0, (byte)(192 + (trackCount - 1)), (byte)instrument});
            list.addAll(trackBytes);
            if(segmentmidaddress <= endOffset) {
                break;
            }
        }
        //        for(int i = 0; i < list.get(0)[11] - 1; i++) { //循环每个音轨
        list.get(0)[11] = (byte)(list.get(0)[11] + trackCount);
        int totalCount = 0;
        for(byte[] b : list) {
            totalCount += b.length;
        }
        byte[] ret = new byte[totalCount];
        int i = 0;
        for(byte[] b : list) {
            for(byte bb : b) {
                ret[i] = bb;
                i++;
            }
        }
        //seq->节拍:节拍=seq/1.6
        return ret;
    }

    private static byte[] getDeltatime(int sequence, int prvSequence) {
        List<Byte> bytes = new ArrayList<Byte>();
        sequence = (int)(sequence / 1.6);
        prvSequence = (int)(prvSequence / 1.6);
        String str = Integer.toBinaryString(sequence - prvSequence);
        while(str.length() > 7) {
            String substr = str.substring(str.length() - 7, str.length());
            str = str.substring(0, str.length() - 7);
            byte b = (byte)Integer.parseInt(substr, 2);
            bytes.add(b);
        }
        if(str.length() > 0) {
            byte b = (byte)Integer.parseInt(str, 2);
            bytes.add(b);
        }
        byte[] ret = new byte[bytes.size() + 3]; //预留音轨,音高,力度
        for(int i = bytes.size() - 1; i >= 0; i--) {
            if(i == 0) {
                ret[bytes.size() - 1 - i] = bytes.get(i);
            } else {
                ret[bytes.size() - 1 - i] = (byte)(bytes.get(i) + 128);
            }
        }
        return ret;
    }

    private static List<int[]> sortTrackDetails(List<int[]> list, int start, int end) {
        if (list == null || list.size() == 0) {
            return list;
        }
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if(list.get(i)[0] > list.get(j)[0]) {
                    int[] tmp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, tmp);
                }
            }
        }
        return list;
    }

    public static void loadVos03(byte[] bytes) {
        try {
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
                    if(foruser == 1 && key < 6) {
                        insert(keys, new int[]{sequence, key, notetype == 0x80 ? duration : 1});
//                        insert(keys, new int[]{sequence, key, 1});
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
            byte[] ticks = ArrayUtils.subarray(bytes, segmentmidaddress + 12, segmentmidaddress + 14);
            ArrayUtils.reverse(ticks);
            int tickinnote = RMUtils.getInt(ticks, 0, 2);
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
                    tempos.add(new int[]{currenttempo, 0, 0, 0, 0, 0, 0});
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
            if(tempos.size() == 1) {
                tempos.get(0)[1] = (int)((double)songLength * tickinnote * tempos.get(0)[0] / 60000); //延续ticket
                tempos.get(0)[2] = tempos.get(0)[1]; //结束ticket
                tempos.get(0)[3] = 0; //起始毫秒数
                tempos.get(0)[4] = songLength; //结束毫秒数
            } else {
                tempos.get(0)[3] = 0; //起始毫秒数
                tempos.get(0)[4] = tempos.get(0)[3] + (int)((double)tempos.get(0)[1] / tempos.get(0)[0] * 60000 / tickinnote); //结束毫秒数
                tempos.get(0)[5] = 0; //起始seq
                tempos.get(0)[6] = (int)((double)tempos.get(0)[4] * tempos.get(0)[0] * 384 / 30 / 1000); //结束seq
                for(int i = 1; i < tempos.size(); i++) {
                    tempos.get(i)[2] = tempos.get(i)[1] + tempos.get(i - 1)[2]; //结束ticket
                    tempos.get(i)[3] = tempos.get(i - 1)[4]; //起始毫秒数
                    tempos.get(i)[4] = tempos.get(i)[3] + (int)((double)tempos.get(i)[1] / tempos.get(i)[0] * 60000 / tickinnote); //结束毫秒数
                    tempos.get(i)[5] = tempos.get(i - 1)[6]; //起始seq
                    tempos.get(i)[6] = tempos.get(i)[5] + (int)((tempos.get(i)[4] - tempos.get(i)[3]) * (double)tempos.get(i)[0] * 384 / 30 / 1000); //Q3+(P3-O3)*L3*384/30/1000
                    if(i == tempos.size() - 1) {
                        tempos.get(i)[1] = (songLength - tempos.get(i)[3]) * tickinnote * tempos.get(i)[0] / 60000; //延续ticket
                        tempos.get(i)[2] = tempos.get(i)[1] + tempos.get(i - 1)[2];  //结束ticket
                        tempos.get(i)[4] = songLength; //结束毫秒数
                    }
                }
            }
            System.out.println("bpm\tlength\ttotallength\tbeginsec\tendsec");
            for(int i = 0; i < tempos.size(); i++) {
                System.out.print(tempos.get(i)[0] + "\t" + tempos.get(i)[1] + "\t" + tempos.get(i)[2] + "\t" + tempos.get(i)[3] + "\t" + tempos.get(i)[4]);
                if(tempos.size() > 1) {
                    System.out.print("\t" + tempos.get(i)[5] + "\t" + tempos.get(i)[6]);
                }
                System.out.println("");
            }
            List<byte[]> bpmsImd = new ArrayList<byte[]>();
            int currentTimeMillSec = 0;
            if(tempos.size() == 1) {
                double bpm = tempos.get(0)[0];
                int count = 0;
                while (currentTimeMillSec < songLength) {
                    bpmsImd.add(RMUtils.int2byte(currentTimeMillSec));
                    bpmsImd.add(RMUtils.longToBytes(Double.doubleToLongBits(bpm)));
                    count++;
                    currentTimeMillSec = (int)(60000d / bpm * count);
                }
            } else {
                for(int i = 0; i < tempos.size(); i++) {
                    double bpm = tempos.get(i)[0];
                    int duration = tempos.get(i)[1];
                    int end = tempos.get(i)[4];
                    int begin = tempos.get(i)[3];
                    double timechip = 60000 / bpm;
                    int count = 0;
                    while (currentTimeMillSec < end) {
                        bpmsImd.add(RMUtils.int2byte(begin + (int) (count * timechip)));
                        bpmsImd.add(RMUtils.longToBytes(Double.doubleToLongBits(bpm)));
                        count++;
                        currentTimeMillSec = begin + (int) (count * timechip);
                    }
                }
            }
            int count = 0;
            for(int i = 0; i < bpmsImd.size(); i++) {
                count += bpmsImd.get(i).length;
            }
            System.out.println("totalcount=" + count);
            System.out.println("songLength=" + songLength);
            System.out.println("maxSequence="+maxSequence);
//            System.exit(0);
            File outfile = new File("D:\\temp\\a\\new_7k_ez.imd");
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
//                fos.write(RMUtils.int2byte(o));
                fos.write(RMUtils.int2byte(getOffsetSec(o, tempos)));
                fos.write(k);
                if(d > 1) {
//                    fos.write(RMUtils.int2byte(d));
                    fos.write(RMUtils.int2byte(getDurationSec(o, d, tempos)));
                } else {
                    fos.write(RMUtils.int2byte(0));
                }
            }
            fos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private static int getDurationSec(int sequence, int duration, List<int[]> tempos) {
        if(tempos.size() == 1) {
            return (int)(duration * 1000d * 30d / 384d / tempos.get(0)[0]);
        }
        for(int i = 0; i < tempos.size(); i++) {
            int[] tempo = tempos.get(i);
            if(sequence < tempo[6] || i == tempos.size() - 1) {
                return (int)((double)duration / tempo[0] * 30 * 1000 / 384);
            }
        }
        throw new RuntimeException("未找到tempo");
    }

    private static int getOffsetSec(int sequence, List<int[]> tempos) {
        if(tempos.size() == 1) {
            return (int)(sequence * 1000d * 30d / 384d / tempos.get(0)[0]);
        }
        for(int i = 0; i < tempos.size(); i++) {
            int[] tempo = tempos.get(i);
            if(sequence < tempo[6] || i == tempos.size() - 1) {
//                (A10-E10)/C10/384*30*1000+F10
                return (int)((double)(sequence - tempo[5]) / tempo[0] * 30 * 1000 / 384 + tempo[3]);
//                return (int)((sequence - (tempo[2] - tempo[1])) * 30d * 1000 / 384 / tempo[0] + tempo[3]);
            }
        }
        throw new RuntimeException("未找到tempo");
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
