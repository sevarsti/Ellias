package com.saille.util;

import com.saille.sys.Setting;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ellias on 2017-09-07.
 */
public class FFMpegUtils {
    public static void main(String[] args) {
        getAudioLength("F:\\rm\\song\\daybyday\\daybyday.mp3");
    }
    public static int getAudioLength(String filepath) {
        try {
//            String ffmpegpath = Setting.getSettingString("FFMPEG_PATH");
            String ffmpegpath = "F:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe";
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command(ffmpegpath, "-i", filepath);
            Process p = pb.start();
            InputStream is = p.getInputStream();
            InputStream errStream = p.getErrorStream();
            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), Charset.forName("GB2312")));
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(errStream)));
            String ostr;
            while((ostr = out.readLine()) != null) {
                System.out.println(ostr);
            }
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
}
