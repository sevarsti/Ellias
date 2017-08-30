package com.saille.rm;

import com.GlobalConstant;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import java.util.*;
import java.util.List;
import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.net.URLConnection;

import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;
import com.saille.util.IOUtils;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2014-12-5
 * Time: 8:51:00
 * To change this template use File | Settings | File Templates.
 */
public class KeyLoad extends Thread {
    private static List<Map<String, String>> allSongs;
    private static List<String[]> songcellList = new ArrayList<String[]>();
    private static boolean finish = false;
    private static int i;
    private static int songmaxrow;
    private static Workbook workbook;
    private static Sheet songsheet;
    private static Sheet statisticsheet;
    private static int statisticsheetrows;
    private static int compareCells[] = new int[]{3, 4, 6, 7, 14, 15, 22, 23, 30, 31, 38, 39, 46, 47, 54, 55, 62, 63, 70, 71};
    private static String compareMaps[] = new String[]{"m_szBPM", "m_iGameTime", "m_ush4KeyEasy", "4e", "m_ush4KeyNormal", "4n", "m_ush4KeyHard", "4h", "m_ush5KeyEasy", "5e", "m_ush5KeyNormal", "5n", "m_ush5KeyHard", "5h", "m_ush6KeyEasy", "6e", "m_ush6KeyNormal", "6n", "m_ush6KeyHard", "6h"};

    private static Map<String, int[]> keydetails = new HashMap<String, int[]>();//Map<song_key_lv, [00,01,02,21,22,61,62,a1,a2]

    private static List<String> newSongs = new ArrayList<String>();
    private static List<String> newKeys = new ArrayList<String>();
    private static List<String> newImds = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            loadSongs(); //refresh via xml
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private synchronized static Map<String, String> getNextSong() {
        if(finish) {
            return null;
        }
        Map<String, String> ret = allSongs.get(0);
        allSongs.remove(0);
        i++;
        if(allSongs.size() == 0) {
            finish = true;
        }
        return ret;
    }

    private static void loadSongs() throws Exception {
        File excel = new File(GlobalConstant.DISKPATH + "excel\\" + RMConstant.RM_EXCEL);
        FileInputStream excelis = new FileInputStream(excel);
        workbook = Workbook.getWorkbook(excelis);
        statisticsheet = workbook.getSheet("KEY统计");
        statisticsheetrows = statisticsheet.getRows();
        songsheet = workbook.getSheet("歌曲");
        songmaxrow = songsheet.getRows() - 1;

        keydetails.clear();
        allSongs = MrockSongClientAndroid.getMapFromUrl(false);
        for(int j = 2; j <= songmaxrow; j++) {
            Cell[] cells = songsheet.getRow(j);
            String[] cellstr = new String[72];
            for(int i = 0; i < cellstr.length; i++) {
                cellstr[i] = cells[i].getContents();
            }
            songcellList.add(cellstr);
        }
        int nums = 20;
        KeyLoad[] instances = new KeyLoad[nums];
        for(int i = 0; i < instances.length; i++) {
            instances[i] = new KeyLoad();
            instances[i].start();
        }
        boolean isrunning = true;
        while(isrunning) {
            isrunning = false;
            for(int i = 0; i < instances.length; i++) {
                isrunning |= instances[i].running;
//                if(instances[i].running) {
//                    break;
//                }
//                allfinish = true;
            }
//            Thread.sleep(1000);
        }

        System.out.println("新歌曲：");
        for(String song : newSongs) {
            System.out.println(song);
        }
        System.out.println("新谱面：");
        for(String key : newKeys) {
            System.out.println(key);
        }

        System.out.println("新imd：");
        for(String imd : newImds) {
            System.out.println(imd);
        }
    }

    private static int[] loadImd(Workbook workbook, Map<String, String> map) throws Exception {
        int[] ret = new int[9];
        String song = map.get("m_szPath");
        String name = map.get("m_szSongName");
        double bpm = Double.parseDouble(map.get("m_szBPM"));
        String filename = GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + ".mp3";
        download(song, song + ".mp3");
        download(song, song + ".jpg");
        download(song, song + ".jpg");
        download(song, song + "_title_ipad.jpg");
        ret[0] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_4k_ez.imd", map);
        ret[3] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_5k_ez.imd", map);
        ret[6] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_6k_ez.imd", map);
        ret[1] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_4k_nm.imd", map);
        ret[4] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_5k_nm.imd", map);
        ret[7] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_6k_nm.imd", map);
        ret[2] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_4k_hd.imd", map);
        ret[5] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_5k_hd.imd", map);
        ret[8] = loadSingleImd(workbook, GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + song + "_6k_hd.imd", map);
        return ret;
    }

    private static int loadSingleImd(Workbook workbook, String path, Map<String, String> map) throws Exception {
        boolean showlog = false;
        File f = new File(path);
        boolean newimd = download(map.get("m_szPath"), path.substring(path.lastIndexOf("\\") + 1));
        if(!f.exists()) {
            return 0;
        }
        byte[] bb = new byte[4];

        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        fis.skip(4);
        fis.read(bb);
        int length = byte2int(bb);
        double bpm = 0d;
        for(int i = 0; i < length; i++) {
            fis.skip(4);
            double tempbpm = Double.longBitsToDouble(Long.reverseBytes(dis.readLong()));
            if(bpm == 0) {
                bpm = tempbpm;
            } else if(bpm != tempbpm) {
                if(path.indexOf("numbernine") >= 0 || path.indexOf("number9_") >= 0) {
                    bpm = 128d;
                    break;
                } else {
                    System.out.print(path + " bpm發生變化，" + bpm + "->" + tempbpm + "請輸入正確的bpm:");
                    bpm = Double.parseDouble(IOUtils.readLine());
                    break;
                }
            }
        }
        map.put("m_szBPM", bpm + "");

        //todo:檢查bpm
        fis = new FileInputStream(f);
        fis.skip(4);

        fis.read(bb);
        fis.skip(12 * byte2int(bb));
        fis.skip(2);
        fis.read(bb);
        int size = byte2int(bb);
        int key = 0;
        int k00 = 0, k01 = 0, k02 = 0, ka1 = 0, k21 = 0, k61 = 0, ka2 = 0, k22 = 0, k62 = 0;
        for(int i = 0; i < size; i++) {
            bb = new byte[11];
            fis.read(bb);
            byte b = bb[0];
            if(b == 0x00) { //00
                key++;
                k00++;
                if(showlog) {
                    System.out.println(key + "，单键");
                }
            } else if(b == 0x01) { //01
                key++;
                k01++;
                if(showlog) {
                    System.out.println(key + "，划键");
                }
            } else if(b == 3 || b == -93) { //03, a3
//                System.out.println("03/a3");
//                return 0;
//                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
//                key += Math.round(((bb[8] * 256 + ii) / (60000d / bpm / 4d))) + 1;
            } else if(b == 0x02) { //02, ok
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int) (((bb[8] * 256 + ii) / ((int) (60000d / bpm / 4d)))) + 1;
                key += c;
                k02++;
                if(showlog) {
                    System.out.println(key + "，面条");
                }
            } else if(b == -95) { //a1面条结尾划键,
                key += 1;
                ka1 += 1;
                if(showlog) {
                    System.out.println(key + "，面条结尾划键");
                }
            } else if(b == 0x21) { //21划键，
                key += 1;
                k21 += 1;
                if(showlog) {
                    System.out.println(key + "，面条中间划键");
                }
            } else if(b == 0x61) { //61面条开始的划键,
                k61 += 1;
                key += 1;
                if(showlog) {
                    System.out.println(key + "，面条开始划键");
                }
            } else if(b == -94) { //a2,长键结尾
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int) (((bb[8] * 256 + ii) / ((int) (60000d / bpm / 4d))));
                key += c;
//                ka2+=c;
                ka2++;
                if(showlog) {
                    System.out.println(key + "，面条结束长键");
                }
            } else if(b == 0x22) { //22，长键中间的面条
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int) (((bb[8] * 256 + ii) / ((int) (60000d / bpm / 4d))));
                key += c;
                k22 += c;
                if(showlog) {
                    System.out.println(key + "，面条中间长键");
                }
            } else if(b == 0x62) { //62，长键开始
                int ii = bb[7] >= 0 ? bb[7] : (256 + bb[7]);
                int c = (int) (((bb[8] * 256 + ii) / ((int) (60000d / bpm / 4d)))) + 1;
                key += c;
//                k62+=c;
                k62++;
                if(showlog) {
                    System.out.println(key + "，面条开始长键");
                }
            } else if(b == -96) { //a0
                key--;
//                key++;
//                System.out.print("");
//                return 0;
            } else {
                System.out.println("!!!!!!b=" + b);
            }
        }
//        System.out.println("k00="+k00+", k01="+k01+", k02="+k02+", k21="+k21+", k22="+k22+", ka1="+ka1+", k61="+k61+", ka2="+ka2+", k62="+k62);
        boolean found = false;
        for(int i = 1; i < songmaxrow; i++) {
            if(songcellList.get(i-1)[2].equals(map.get("m_szPath"))) {
//            if(sheet.getRow(i)[2].getContents().equals(map.get("m_szPath"))) {
                found = true;
                break;
            }
        }
        if(!found) {
            String songpath = path.substring(path.lastIndexOf(File.separator) + File.separator.length());
            songpath = songpath.substring(0, songpath.indexOf("."));
            String newKey = map.get("m_szSongName") + "\t" + map.get("m_ushSongID") + "\t" + map.get("m_szPath") + "\t" + songpath.split("\\_")[1].substring(0, 1) + "\t" + songpath.split("\\_")[2].toUpperCase();
            int[] keys = new int[]{key, k00, k01, k02, k21, k22, k61, k62, ka1, ka2};
            for(int i = 0; i < keys.length; i++) {
                newKey += "\t" + keys[i];
            }
            newKeys.add(newKey);
        }
        return key;
    }

    public static int byte2int(byte[] bb) {
        int ret = 0;
        for(int i = 0; i < bb.length; i++) {
            ret += Math.pow(256, i) * (int) (bb[i] < 0 ? (256 + bb[i]) : bb[i]);
        }
        return ret;
    }

    private static boolean download(String song, String filename) throws Exception {
        URL url = new URL("http://game.ds.qq.com/Com_SongRes/song/" + song + "/" + filename);
        try {
            File dir = new File(GlobalConstant.DISKPATH + "rm\\song\\" + song);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            URLConnection conn = url.openConnection();
            InputStream is = conn.getInputStream();
            int i;
            SimpleDateFormat sdf = new SimpleDateFormat("EE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
            String d = conn.getHeaderField("Last-Modified");
            if(filename.endsWith(".jpg")) {
                File f = new File(GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + filename.substring(0, filename.length() - 4) + ".png");
                if(d != null && f.exists() && sdf.parse(d).getTime() <= f.lastModified()) {
                    return false;
                }
                System.out.println("download: " + filename);
                boolean update = false;
                if(f.exists()) {
                    String oldname = f.getAbsolutePath();
                    update = true;
                    f.renameTo(new File(oldname + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(f.lastModified()))));
                    f = new File(oldname);
                }
                newImds.add((update ? "update " : "       ") + filename);
                BufferedImage src = ImageIO.read(is);
                f.createNewFile();
                ImageIO.write(src, "png", f);
                is.close();
                if(d != null) {
                    try {
                        f.setLastModified(sdf.parse(d).getTime());
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            } else {
                File f = new File(GlobalConstant.DISKPATH + "rm\\song\\" + song + "\\" + filename);
                if(d != null && f.exists() && sdf.parse(d).getTime() <= f.lastModified()) {
                    return false;
                }
                System.out.println("download: " + filename);
                boolean update = false;
                if(f.exists()) {
                    String oldname = f.getAbsolutePath();
                    update = true;
                    f.renameTo(new File(oldname + "." + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date(f.lastModified()))));
                    f = new File(oldname);
                }
                newImds.add((update ? "update " : "       ") + filename);
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                while((i = is.read()) >= 0) {
                    fos.write(i);
                }
                fos.close();
                is.close();
                if(d != null) {
                    try {
                        f.setLastModified(sdf.parse(d).getTime());
                    } catch(Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            return true;
        } catch(FileNotFoundException ex) {
//            ex.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean running = true;
    public void run() {
        try {
            while(!finish) {
                Map<String, String> map = getNextSong();
                System.out.println(i + "\t" + map.get("m_szSongName"));
                map.put("m_szSongName", map.get("m_szSongName").replaceAll("【.+】", ""));
                int[] ret = loadImd(workbook, map);
                map.put("4e", ret[0] + "");
                map.put("4n", ret[1] + "");
                map.put("4h", ret[2] + "");
                map.put("5e", ret[3] + "");
                map.put("5n", ret[4] + "");
                map.put("5h", ret[5] + "");
                map.put("6e", ret[6] + "");
                map.put("6n", ret[7] + "");
                map.put("6h", ret[8] + "");
                boolean found = false;
                boolean exact = true;
                for(int j = 2; j <= songmaxrow; j++) {
//                    Cell[] cells = sheet.getRow(j);
                    String[] cells = songcellList.get(j - 2);
                    if(cells[0].equals(map.get("m_szSongName")) && cells[1].equals(map.get("m_szArtist")) && cells[2].equals(map.get("m_szPath"))) {
                        found = true;
                        String cell = cells[4];
                        exact &= Double.parseDouble(map.get("m_szBPM")) == Double.parseDouble(cell);
                        if(!exact) {
                            System.out.println("bpm, xml=" + map.get("m_szBPM") + ", excel=" + cell);
                        }

                        cell = cells[5];
                        int length = Integer.parseInt(map.get("m_iGameTime").trim());
                        DecimalFormat df = new DecimalFormat("00");
                        exact &= ((length / 60) + ":" + df.format(length % 60)).equals(cell);
                        if(!exact) {
                            System.out.println("time");
                        }
                        for(int k = 2; k < compareCells.length; k++) {
                            cell = cells[compareCells[k]];
                            exact &= map.get(compareMaps[k]).trim().equals(cell);
                            if(!exact) {
                                System.out.println(compareMaps[k]);
                                break;
                            }
                        }
                        break;
                    }
                }
                if((!found) || (!exact)) {
                    DecimalFormat df = new DecimalFormat("00");
                    int length = Integer.parseInt(map.get("m_iGameTime").trim());
                    System.out.println(map.get("m_szSongName") + "没有找到！");
                    String newSong = map.get("m_szSongName") + "\t" + map.get("m_szArtist") + "\t" + map.get("m_szPath") + "\t" + map.get("m_ushSongID") + "\t" + map.get("m_szBPM") + "\t" + (length / 60) + ":" + df.format(length % 60) + "\t" + map.get("m_ush4KeyEasy").trim() + "\t" + map.get("4e") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush4KeyNormal").trim() + "\t" + map.get("4n") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush4KeyHard").trim() + "\t" + map.get("4h") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush5KeyEasy").trim() + "\t" + map.get("5e") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush5KeyNormal").trim() + "\t" + map.get("5n") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush5KeyHard").trim() + "\t" + map.get("5h") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush6KeyEasy").trim() + "\t" + map.get("6e") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush6KeyNormal").trim() + "\t" + map.get("6n") + "\t" + "\t\t\t\t\t\t" + map.get("m_ush6KeyHard").trim() + "\t" + map.get("6h");
                    newSongs.add(newSong);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
            finish = true;
        }
        running = false;
    }
}
