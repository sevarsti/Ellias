package com.saille.util;

import com.saille.rm.util.RMUtils;
import com.saille.sys.Setting;
import com.saille.sys.util.SysUtils;
import org.apache.commons.lang.ArrayUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ellias on 2017-09-07.
 */
public class FFMpegUtils {
//    private final static String ffmpegpath = "D:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe";
//    private final static String timiditypath = "D:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\timidity.exe";
//    private final static String lamepath = "D:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\lame.exe";

    private final static String ffmpegpath = Setting.getSettingString("FFMPEG_PATH");
    private final static String timiditypath = Setting.getSettingString("TIMIDITY_PATH");
    private final static String lamepath = Setting.getSettingString("LAME_PATH");
    public static void main(String[] args) {
        try {
//            File f = new File("D:\\Ellias\\VOS\\Album\\VPT\\B\\Canon_in_D_mikkel.mid");
            File f = new File("D:\\Ellias\\VOS\\Album\\Speed Domination_5148.mid");
//            byte[] bytes = new byte[(int)f.length()];
//            FileInputStream fis = new FileInputStream(f);
//            fis.read(bytes);
//            System.out.println(getMidStartBlank(bytes));
            convertMid2Mp3(f.getCanonicalPath(), "D:\\Ellias\\VOS\\Album\\Speed Domination_5148.mp3");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
//        insertBlank("F:\\game\\VOS\\Album\\VPT\\B\\Laputa Theme.mp3", "F:\\game\\VOS\\Album\\VPT\\B\\Laputa2.mp3", 4800);
    }

    public static boolean convertMid2Mp3(String midifilepath, String mp3filepath) {
        try {
            File f = new File(midifilepath);
            byte[] midiBytes = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(midiBytes);
            fis.close();
            int startBlank = getMidStartBlank(midiBytes);
            String wavfile = midifilepath.substring(0, midifilepath.lastIndexOf(".")) + ".wav";
            ProcessBuilder pb = new ProcessBuilder();
            List<String> params = new ArrayList<String>();
            //1、midi->wav
            params.add(timiditypath);
            params.add(midifilepath);
            params.add("-Ow");
            params.add("-o");
            params.add(wavfile.replaceAll("\\\\", "\\\\\\\\"));
            pb = pb.command(params);
            SysUtils.callExternProgram(pb);
            SysUtils.addTempFile(wavfile, null, 60 * 5); //5分钟
            //todo
            //2、wav->mp3
            String tempMp3File1 = System.getProperty("java.io.tmpdir") + File.separator + System.currentTimeMillis() + ".mp3";
            if(startBlank == 0) {
                tempMp3File1 = mp3filepath;
            }
            pb = new ProcessBuilder();
            params.clear();
            params.add(lamepath);
            params.add(wavfile.replaceAll("\\\\", "\\\\\\\\"));
            params.add(tempMp3File1.replaceAll("\\\\", "\\\\\\\\"));
            pb = pb.command(params);
            SysUtils.callExternProgram(pb);
            SysUtils.addTempFile(wavfile, null, 60 * 5); //5分钟
            if(startBlank == 0) {
                return true;
            }
            //3、mp3头增加空白
            insertBlank(tempMp3File1, mp3filepath, startBlank);
            SysUtils.addTempFile(tempMp3File1, null, 60 * 5); //5分钟
            return true;
        } catch(FileNotFoundException ex) {
            ex.printStackTrace();
            return false;
        } catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean insertBlank(String oldfilepath, String newfilepath, int length) {
        double d = length / 1000d;
        int count = (int)Math.ceil(d); //需要插入几条
        d = d - count;
        //ffmpeg.exe -i blank.mp3 -i blank.mp3 -i blank.mp3 -i new.mp3 -filter_complex "concat=n=4:v=0:a=1" out.mp3
        ProcessBuilder pb = new ProcessBuilder();
        List<String> params = new ArrayList<String>();
        params.add(ffmpegpath);
        String tempfile = System.getProperty("java.io.tmpdir") + File.separator + System.currentTimeMillis() + ".mp3";
        for(int i = 0; i < count; i++) {
            params.add("-i");
            params.add("blank.mp3");
        }
        params.add("-i");
        params.add(oldfilepath.replaceAll("\\\\", "\\\\\\\\"));
        params.add("-filter_complex");
        params.add("concat=n=" + (count + 1) + ":v=0:a=1");
        if(d == 0) {
            params.add(newfilepath.replaceAll("\\\\", "\\\\\\\\"));
        } else {
            params.add(tempfile);
        }
//        pb = pb.directory(new File("F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin"));
        pb = pb.directory(new File(new File(ffmpegpath).getParent()));
        pb = pb.command(params);
        SysUtils.callExternProgram(pb);
        if(d == 0) {
            return true;
        } else {
            cut(tempfile, newfilepath, Math.abs(d), 1000000);
            SysUtils.addTempFile(tempfile, null, 60 * 5); //5分钟
        }
        return true;
    }

    public static boolean cut(String oldfilepath, String newfilepath, double begin, double length) {
        ProcessBuilder pb = new ProcessBuilder();
        pb = pb.command(ffmpegpath, "-i", oldfilepath.replaceAll("\\\\", "\\\\\\\\"), "-ss", begin + "", "-t", length + "", newfilepath.replaceAll("\\\\", "\\\\\\\\"));
        SysUtils.callExternProgram(pb);
        //ffmpeg.exe -i out.mp3 -ss 0.333333333 -t 5000 out2.mp3
        return true;
    }

    public static boolean changeSpeed(String oldfilepath, String newfilepath, double ratio) {
        try {
//            String ffmpegpath = "F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe";
            System.out.println("src file=" + oldfilepath);
            System.out.println("des file=" + newfilepath);
            File newfile = new File(newfilepath);
            if(newfile.exists()) {
                newfile.delete();
            }
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command(ffmpegpath, "-i", oldfilepath.replaceAll("\\\\", "\\\\\\\\"), "-filter:a", "\"atempo=" + ratio + "\"", "-vcodec", "copy", "-vn", "\"" + newfilepath.replaceAll("\\\\", "\\\\\\\\") + "\"");
    //        String cmd = GlobalConstant.DISKPATH + "software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe -i " + filepath + " -filter:a \"atempo=" + ratio + "\" -vcodec copy -vn \"" + outpath + "\"";
            Process p = pb.start();
            InputStream is = p.getInputStream();
            InputStream errStream = p.getErrorStream();
            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), Charset.forName("GB2312")));
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(errStream)));
            String ostr;
//                while((ostr = out.readLine()) != null) {
//                    System.out.println(ostr);
//                }
//            p.waitFor();
            System.out.println("============================");
            while((ostr = err.readLine()) != null) {
                System.out.println(ostr);
            }
            out.close();
            err.close();
            is.close();
            errStream.close();
            p.destroy();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    public static int getAudioLength(String filepath) {
        try {
//            String ffmpegpath = "F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe";
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command(ffmpegpath, "-i", filepath);
            Process p = pb.start();
            InputStream is = p.getInputStream();
            InputStream errStream = p.getErrorStream();
            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), Charset.forName("GB2312")));
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(errStream)));
            String ostr;
//            while((ostr = out.readLine()) != null) {
//                System.out.println(ostr);
//            }
            System.out.println("============================");
            Pattern pattern = Pattern.compile("\\s*Duration: ([0-9]{2}):([0-9]{2}):([0-9]{2})\\.[0-9]{2},.+");
            while((ostr = err.readLine()) != null) {
                System.out.println(ostr);
                Matcher m = pattern.matcher(ostr);
                if(m.find()) {
                    out.close();
                    err.close();
                    is.close();
                    errStream.close();
                    return Integer.parseInt(m.group(1)) * 3600 + Integer.parseInt(m.group(2)) * 60 + Integer.parseInt(m.group(3));
                }
            }
            out.close();
            err.close();
            is.close();
            errStream.close();
            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    /* 获取mid文件第一个音符的起始时间，单位毫秒 */
    private static int getMidStartBlank(byte[] bytes) {
        int offset = 14; //第一个MTrk起始位置
        int minBeat = Integer.MAX_VALUE;
        List<int[]> bpms = new ArrayList<int[]>();
        int tickinnote = bytes[12] * 256 + (bytes[13] & 0xff);
        while(offset < bytes.length) {
            byte[] lengthBytes = ArrayUtils.subarray(bytes, offset + 4, offset + 8);
            ArrayUtils.reverse(lengthBytes);
            int length = RMUtils.getInt(lengthBytes, 0, 4);
            if(offset == 14) { //第一个音轨额外获取bpm信息，此处默认ff 51 03必定在第一个音轨，否则需要修改
                for(int i = 8; i < length + 8 - 3; i++) {
                    if((bytes[offset + i] & 0xff) == 0xff && bytes[offset + i + 1] == 0x51 && bytes[offset + i + 2] == 0x03) {
                        int bpmLength = 0;
                        for(int j = 0; j < 4; j++) {
                            int v = bytes[offset + i + 6 + j];
                            v = v & 0xFF;
                            bpmLength = bpmLength * 128 + (v % 128);
                            if(v < 128) {
                                break;
                            }
                        }
                        int[] bpm = new int[4]; //bpm，ticket数，seq数，毫秒数
                        bpm[0] = (bytes[offset + i + 3] & 0xFF) * 256 * 256 + (bytes[offset + i + 4] & 0xff) * 256 + (bytes[offset + i + 5] & 0xff);
//                        bpm[0] = 60000000 / bpm[0];
                        bpm[1] = bpmLength;
                        bpm[2] = bpm[1] * 768 / tickinnote;
                        bpm[3] = bpm[1] * 60000 / (tickinnote * bpm[0]);
                        bpms.add(bpm);
                        i = i + 6;
                    }
                }
            }
            offset += 4 + 4;
            if(bytes[offset + 2] == 0) {
                offset += length;
                continue;
            }
            int track = bytes[offset + 1];
            track = track & 0xFF;
            if(track >= 0xC0 && track <= 0xCF) { //是音轨不是控制信息
                /* 获取长度 */
                int trackMinBeat = 0;
                for(int i = 0; i < 4; i++) {
                    int v = bytes[offset + 3 + i];
                    v = v & 0xFF;
                    trackMinBeat = trackMinBeat * 128 + (v % 128);
                    if(v < 128) {
                        break;
                    }
                }
                minBeat = Math.min(minBeat, trackMinBeat);
            }
            offset += length;
        }
        int seq = (int)(minBeat * 1.6 * 480 / tickinnote);
        if(bpms.size() == 1) {
            return (int)(seq * 30d *  bpms.get(0)[0] / 384 / 60000);
        } else {
            int ret = 0;
            int beatSum = 0;
            for(int i = 0; i < bpms.size(); i++) {
                int[] bpm = bpms.get(i);
                beatSum += bpm[2];
                if(beatSum > minBeat || i == bpms.size() - 1) {
                    ret += (int)((seq - (beatSum - bpm[2])) * 30d * bpm[0] / 384 / 60000d);
                    break;
                } else {
                    ret += bpm[3];
                }
            }
            return ret;
        }
    }
}
