package com.saille.util;

import com.saille.sys.Setting;
import com.saille.sys.util.SysUtils;

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
    private final static String ffmpegpath = "F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe";
    private final static String timiditypath = "F:\\software\\MidiConverter\\timidity.exe\\";
    private final static String lamepath = "F:\\software\\MidiConverter\\lame.exe";

//    private final static String ffmpegpath = Setting.getSettingString("FFMPEG_PATH");
//    private final static String timiditypath = Setting.getSettingString("TIMIDITY_PATH");
//    private final static String lamepath = Setting.getSettingString("LAME_PATH");
    public static void main(String[] args) {
        insertBlank("F:\\game\\VOS\\Album\\VPT\\B\\Laputa Theme.mp3", "F:\\game\\VOS\\Album\\VPT\\B\\Laputa2.mp3", 4800);
    }

    public static boolean convertMid2Mp3(String midifilepath, String mp3filepath, int startBlankMillSec) {
        String wavfile = midifilepath.substring(0, midifilepath.lastIndexOf(".")) + ".wav";
        ProcessBuilder pb = new ProcessBuilder();
        List<String> params = new ArrayList<String>();
        //1��midi->wav
        params.add(timiditypath);
        params.add(midifilepath);
        params.add("-Ow");
        params.add("-o");
        params.add("-o");
        params.add(wavfile);
        SysUtils.addTempFile(wavfile, null, 60 * 5); //5����
        //todo
        //2��wav->mp3
        String tempMp3File1 = System.getProperty("java.io.tmpdir") + File.separator + System.currentTimeMillis() + ".mp3";
        if(startBlankMillSec == 0) {
            tempMp3File1 = mp3filepath;
        }
        pb = new ProcessBuilder();
        params.clear();
        params.add(lamepath);
        params.add(wavfile);
        params.add(mp3filepath);
        if(startBlankMillSec == 0) {
            return true;
        }
        SysUtils.addTempFile(tempMp3File1, null, 60 * 5); //5����
        //todo
        //3��mp3ͷ���ӿհ�
//        insertBlank(mp3filepath)
        //4��mp3ͷɾ������հ�
        return true;
    }

    public static boolean insertBlank(String oldfilepath, String newfilepath, int length) {
        double d = length / 1000d;
        int count = (int)Math.ceil(d); //��Ҫ���뼸��
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
        pb = pb.directory(new File("F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin"));
        pb = pb.command(params);
        SysUtils.callExternProgram(pb);
        if(d == 0) {
            return true;
        } else {
            cut(tempfile, newfilepath, 1d + d, 1000000);
            SysUtils.addTempFile(tempfile, null, 60 * 5); //5����
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

    private static int getMidStartBlank(byte[] bytes) {

    }
}
