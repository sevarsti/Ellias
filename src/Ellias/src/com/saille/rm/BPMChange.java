package com.saille.rm;

import com.GlobalConstant;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Adler32;
import java.util.zip.ZipOutputStream;
import java.util.zip.ZipEntry;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-3-6
 * Time: 9:47:55
 * To change this template use File | Settings | File Templates.
 */
public class BPMChange {
    public static void main(String[] args) {
        try {
//            changeImd("can", "4", "hd", 0.9);

            File f = new File(GlobalConstant.DISKPATH + "rm\\can\\0.8.mp3");
            if(f.exists()) {
                f.delete();
            }
            String cmd = "D:\\software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe -i d:\\\\rm\\\\can\\\\can.mp3 -filter:a \"atempo=0.9\" -vcodec copy -vn d:\\\\temp\\\\0.9.mp3";
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
        } catch(Exception ex) {
            ex.printStackTrace();
        }
//        private static void changeImd(String song, String keys, String lv, double ratio) throws Exception {
//            File f = new File("d:\\rm\\" + song + "\\" + song + "_" + keys + "k_" + lv + ".imd");
//        change("daybyday", 2, "localhost");
    }

    public static void changeMp3(String filepath, String outpath, double ratio) throws Exception {
        File f = new File(outpath);
        if(f.exists()) {
            f.delete();
        }
        filepath = filepath.replaceAll("\\\\", "\\\\\\\\");
        outpath = outpath.replaceAll("\\\\", "\\\\\\\\");
        String cmd = GlobalConstant.DISKPATH + "software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe -i " + filepath + " -filter:a \"atempo=" + ratio + "\" -vcodec copy -vn \"" + outpath + "\"";
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

    public static String change(String song, double ratio, String from) {
        try {
            //0、获取歌曲名称
            String songname = getSongName(song);

            //1、改imd

            //2、改mp3
            File f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + ".mp3");
            if(f.exists()) {
                f.delete();
            }
            String cmd = GlobalConstant.DISKPATH + "software\\ffmpeg-20150414-git-013498b-win32-static\\bin\\ffmpeg.exe -i " + GlobalConstant.DISKPATH +  "\\rm\\\\" + song + "\\\\" + song + ".mp3 -filter:a \"atempo=" + ratio + "\" -vcodec copy -vn " + GlobalConstant.DISKPATH + "\\temp\\\\" + song + "-" + ratio + ".mp3";
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
//            Runtime.getRuntime().exec("d:\\software\\ffmpeg\\ffmpeg.exe -i d:\\\\Anlin.mp3 -ss 00:00:15 -t 00:00:40 -vcodec copy -vn d:\\\\Anlin1.mp3");
//            -filter:a "atempo=2.0" 變速 -filter:a "atempo=2.0,atempo=2.0" 

            //5、readme
//            f = new File("d:\\temp\\" + song + "-" + ratio + "_readme.txt");
//            FileWriter fw = new FileWriter(f);
//            BufferedWriter bw = new BufferedWriter(fw);
//            bw.write("说明" + "\r\n");
//            bw.write(songname + " " + ratio + "速\r\n");
//            bw.write("作者：Ellias" + "\r\n");
//            bw.close();
//            fw.close();
            
            //4、zip
            String zipFilename = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + from + "_" + song + "-" + ratio;
            doZip(song + "-" + ratio, zipFilename);

            //5、delete files
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_readme.txt");
            if(f.exists()) {
                f.delete();
            }

            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + ".mp3");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_4k_ez.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_4k_nm.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_4k_hd.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_5k_ez.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_5k_nm.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_5k_hd.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_6k_ez.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_6k_nm.imd");
            if(f.exists()) {
                f.delete();
            }
            f = new File(GlobalConstant.DISKPATH + "temp\\" + song + "-" + ratio + "_6k_hd.imd");
            if(f.exists()) {
                f.delete();
            }
            return zipFilename;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private static void doZip(String songpath, String filename) throws Exception {
        FileOutputStream f = new FileOutputStream(GlobalConstant.DISKPATH + "temp\\" + filename + ".zip");
        // 输出校验流,采用Adler32更快
       CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
       //创建压缩输出流
       ZipOutputStream zos = new ZipOutputStream(csum);
       BufferedOutputStream out = new BufferedOutputStream(zos);
       //设置Zip文件注释
       zos.setComment("Create by Ellias");
//       String[] filepaths = new String[]{"D:\\dl\\movie.swf", "d:\\dl\\debian_sn.txt"};
        File dir = new File(GlobalConstant.DISKPATH + "temp");
        File[] files = dir.listFiles();
       for(File s : files) {
           if(!s.getName().startsWith(songpath)) {
               continue;
           }
           System.out.println("Writing file " + s.getName());
           //针对单个文件建立读取流
           BufferedReader bin = new BufferedReader(new FileReader(s));
           //ZipEntry ZIP 文件条目
           //putNextEntry 写入新条目，并定位到新条目开始处
           zos.putNextEntry(new ZipEntry(s.getName()));
           int c;
           while((c = bin.read()) != -1) {
               out.write(c);
           }
           bin.close();
           out.flush();
       }
       out.close();
       zos.close();
    }

    public static void changeImd(String filepath, String now, double ratio) throws Exception {
        FileInputStream fis = new FileInputStream(filepath);
        changeImd(fis, now, ratio);
    }

    public static void changeImd(InputStream is, String now, double ratio) throws Exception {
//    private static void changeImd(String song, String keys, String lv, double ratio) throws Exception {
//        File f = new File(songpath);
//        if(!f.exists()) {
//            return;
//        }

        File outf = new File(GlobalConstant.DISKPATH + "temp\\" + now + "_changebpm.imd");
//        File outf = new File("d:\\temp\\" + song + "-" + ratio + "_" + keys + "k_" + lv + ".imd");
//        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(is);
        FileOutputStream fos = new FileOutputStream(outf);
        DataOutputStream dos = new DataOutputStream(fos);

        byte[] bb = new byte[4];
        is.read(bb);
        int length = byte2int(bb);
        length = (int)(((double) length) / ratio);
        fos.write(int2byte(length));

//        for(int i = 0; i < 4; i++) {
//            fos.write(fis.read());
//        }
        is.read(bb);
        int count = byte2int(bb);
        fos.write(bb);
        for(int i = 0; i < count; i++) {
            is.read(bb);
            double time = byte2int(bb);
            time = time / ratio;
            fos.write(int2byte((int)time));
            long l = dis.readLong();
            double newd = Double.longBitsToDouble(Long.reverseBytes(l));
            newd = newd * 0.8;
            dos.writeLong(Long.reverseBytes(Double.doubleToLongBits(newd)));
        }
        fos.write(is.read());
        fos.write(is.read());
        is.read(bb);
        fos.write(bb);
        count = byte2int(bb);
        for(int i = 0; i < count; i++) {
            int type = is.read();
            fos.write(type);
            fos.write(is.read());
            is.read(bb);
            double time = byte2int(bb);
            time = time / ratio;
            fos.write(int2byte((int)time));
            fos.write(is.read());
            if(type % 16 == 2) {
                is.read(bb);
                time = byte2int(bb);
                time = time / ratio;
                fos.write(int2byte((int)time));
            } else {
                for(int j = 0; j < 4; j++) {
                    fos.write(is.read());
                }
            }
        }
        is.close();
        fos.close();
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

    private static String getSongName(String path) throws Exception {
        File f = new File(GlobalConstant.DISKPATH + "节奏大师歌曲.xls");
        FileInputStream fis = new FileInputStream(f);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheet("歌曲");
        for(int i = 2; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            HSSFCell cell = row.getCell(2);
            if(cell.getRichStringCellValue().getString().equals(path)) {
                cell = row.getCell(0);
                return cell.getRichStringCellValue().getString();
            }
        }
        return path;
    }
}
