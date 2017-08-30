package com.saille.rm;

import com.GlobalConstant;
import com.baidu.aip.ocr.AipOcr;
import com.saille.baidu.BaiduUtils;
import com.saille.util.IOUtils;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONObject;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.text.SimpleDateFormat;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-1-24
 * Time: 13:27:32
 * To change this template use File | Settings | File Templates.
 */
public class Image2Score {
    private static List<String> unconfirmList = new ArrayList<String>();
    private static List<String[]> updateList = new ArrayList<String[]>();
    private static Sheet sheet;
    private static String scanDir = GlobalConstant.DISKPATH + "rm\\imageTemp";
    private static String fullDir = GlobalConstant.DISKPATH + "rm\\temp\\full\\";
    public static void main(String[] args) {
        String last = "";
        try {
            List<String[]> songs = getSongNames();
            File dir = new File(scanDir);
            File[] files = dir.listFiles();
            for(File f : files) {
                if(f.isDirectory()) {
                    continue;
                }
                /* 截取歌曲图片 */
                String newfile = cutNameImage(f.getPath());
                /* OCR歌曲名称 */
                String songname = ocrBaidu(newfile);
                File deletefile = new File(newfile);
                if(deletefile.exists()) {
                    deletefile.delete();
                }
                if(songname != null) {
                    if(songname.indexOf("限时") >= 0) {
                        songname = songname.substring(songname.indexOf("限时") + 3);
                    }
                    while(songname.endsWith(".")) {
                        songname = songname.substring(0, songname.length() - 1);
                    }
                    songname = songname.trim();
                }
                System.out.println("song=\t"+songname);

                /* 截取歌曲作者 */
                String authorfile = cutAuthorImage(f.getPath());
                String author = ocrBaidu(authorfile);
                deletefile = new File(authorfile);
                if(deletefile.exists()) {
                    deletefile.delete();
                }
                if(author != null) {
                    while(author.endsWith(".")) {
                        author = author.substring(0, author.length() - 1);
                    }
                    author = author.trim();
                }
                System.out.println("\t\t"+author);

                /* 识别分数 */
                int score = cutScoreImage(f.getPath());
                System.out.println("\t\t"+score);

                /* 判断三无 */
                boolean sanwu = isSanwu(f.getPath());
                System.out.println("\t\t"+(sanwu? "三无" : ""));

                /* 截取难度图片 */
                String levelfile = cutLevelImage(f.getPath());
                String level = ocrLocal(levelfile);
                File ff = new File(levelfile);
                if(ff.exists()) {
                    ff.delete();
                }
                System.out.println("\t\t"+level);
                deletefile = new File(levelfile);
                if(deletefile.exists()) {
                    deletefile.delete();
                }
                getDetail(f.getPath(), songname, author, score, sanwu, level, songs);
                last = f.getPath();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("计算结束，最后歌曲：" + last);
        System.out.println("更新分数：");
        for(String[] ss : updateList) {
            System.out.println("\t" + ss[0] + "|" + ss[1] + "|" + ss[3] + "|" + ss[2] + "|" + ss[4]);
        }
        System.out.println("未确认截图：");
        for(String ss : unconfirmList) {
            System.out.println("\t" + ss);
        }
    }

    private static double getMatchScore(String ori, String ocr) {
        int total = 0, match = 0;
        for(int i = 0; i < ocr.length(); i++) {
            total++;
            if(ori.indexOf(ocr.charAt(i)) >= 0) {
                match++;
            }
        }
        double ret = match * 100 / total;
        match = 0;
        for(int i = 0; i < ocr.length(); i++) {
            if(ori.indexOf(ocr.charAt(i)) >= 0) {
                match++;
                ori = ori.substring(ori.indexOf(ocr.charAt(i)) + 1);
            }
        }
        ret = ret + (((double) match) / (double) total);
        return ret;
    }

    private static void getDetail(String path, String name, String author, int score, boolean sanwu, String levelin, List<String[]> songs) throws Exception {
        if(name == null || name.length() == 0) {
            System.out.println("歌名为空");
            throw new Exception("歌名为空");
        }
        double checkScore = 0, exactCheckScore = 0;
        String lineIndex = null, songname = null, authorname = null;
        for(int i = 0; i < songs.size(); i++) {
            double songScore = getMatchScore(songs.get(i)[0], name);
            if(songScore == 101) {
                checkScore = songScore;
                exactCheckScore = 1;
                songname = songs.get(i)[0];
                authorname = songs.get(i)[1];
                lineIndex = songs.get(i)[2];
                break;
            } else {
//                if(author == null || author.length() == 0) {
                    double exactScore = songScore - (int) songScore;
                    if(exactScore > exactCheckScore && songScore > checkScore) {
                        checkScore = songScore;
                        exactCheckScore = exactScore;
                        songname = songs.get(i)[0];
                        authorname = songs.get(i)[1];
                        lineIndex = songs.get(i)[2];
                    } else if(exactCheckScore > exactCheckScore || songScore > checkScore) {
                        System.out.println("图片路径：" + path);
                        System.out.println("老歌：" + songname + "/" + authorname);
                        System.out.println("新歌：" + songs.get(i)[0] + "/" + songs.get(i)[1]);
                        System.out.println("1=老歌，2=新歌：");
                        String in = IOUtils.readLine();
                        if(in.equals("1")) {
                            continue;
                        } else {
                            checkScore = songScore;
                            exactCheckScore = exactScore;
                            songname = songs.get(i)[0];
                            authorname = songs.get(i)[1];
                            lineIndex = songs.get(i)[2];
                        }
                    }
//                } else {
//                    double authorScore = getMatchScore(songs.get(i)[1], author);
//                    double exactAuthorScore =
//                }
            }
        }
        char k = levelin.charAt(0);
        int key = 0, level = 0;
        if(k == '4') {
            key = 0;
        } else if(k == '5') {
            key = 1;
        } else if(k == '6') {
            key = 2;
        } else {
            System.out.println("图片路径：" + path);
            System.out.println("请选择KEY数：");
            key = Integer.parseInt(IOUtils.readLine());
        }
        if(levelin.indexOf("S") > 0) {
            level = 0;
        } else if(levelin.indexOf("N") > 0) {
            level = 1;
        } else if(levelin.indexOf("H") > 0) {
            level = 2;
        } else {
            System.out.println("图片路径：" + path);
            System.out.println("请选择难度：");
            key = Integer.parseInt(IOUtils.readLine());
        }
        int maxScore = Integer.parseInt(sheet.getRow(Integer.parseInt(lineIndex))[6 + key * 24 + level * 8 + 2].getContents());
        String currentScoreStr = sheet.getRow(Integer.parseInt(lineIndex))[6 + key * 24 + level * 8 + 6].getContents();
        int currentScore;
        if(sanwu) {
            if(currentScoreStr.indexOf("\n") >= 0) {
                currentScore = Integer.parseInt(currentScoreStr.substring(currentScoreStr.indexOf("\n") + 1));
            } else {
                currentScore = 0;
            }
        } else {
            if(currentScoreStr.indexOf("\n") >= 0) {
                if(currentScoreStr.indexOf("\n") == 0) {
                    currentScore = 0;
                } else {
                    currentScore = Integer.parseInt(currentScoreStr.substring(0, currentScoreStr.indexOf("\n")));
                }
            } else {
                if(currentScoreStr.length() == 0) {
                    currentScore = 0;
                } else {
                    currentScore = Integer.parseInt(currentScoreStr);
                }
            }
        }
        System.out.println(songname + "/" + authorname +
                (key == 0 ? "4" : (key == 1 ? "5" : "6")) +
                (level == 0 ? "EZ" : (level == 1 ? "NM" : "HD"))
                + "，满分=" + maxScore + "，当前分数：" + currentScore + "，最新分数：" + score);
        System.out.println("请确认，不认可请输入N");
        String confirm = IOUtils.readLine();
        if(confirm == null || confirm.length() == 0) {
            if(score > currentScore) {
                updateList.add(new String[]{songname, authorname, score + "",
                    (key == 0 ? "4" : (key == 1 ? "5" : "6")) +
                    (level == 0 ? "EZ" : (level == 1 ? "NM" : "HD")), sanwu ? "三无" : "有"});
            }
            if(score == maxScore) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
                File f = new File(fullDir + sdf.format(new Date(new File(path).lastModified())) + ".png");
                if(!f.exists()) {
                    f.createNewFile();
                }
                FileInputStream fis = new FileInputStream(path);
                FileOutputStream fos = new FileOutputStream(f);
                byte[] bytes = new byte[1024];
                int size = 0;
                while((size = fis.read(bytes)) > 0) {
                    fos.write(bytes, 0, size);
                }
                fos.close();
                fis.close();
                f.setLastModified(new File(path).lastModified());
            }
        } else {
            unconfirmList.add(path);
        }
    }

    private static String ocrLocal(String filename) throws Exception {
        String tempfile = GlobalConstant.DISKPATH + "temp\\ocr\\1";

        String cmd = GlobalConstant.DISKPATH + "java\\OCR\\tesseract\\tesseract.exe \"" + filename + "\" \"" + tempfile + "\" -l chi_sim";
//        System.out.println(cmd);
        Process p = Runtime.getRuntime().exec(cmd);
        InputStream is = p.getErrorStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String tmp;
        while((tmp = br.readLine()) != null) {
//            System.out.println(tmp);
        }
        p.getInputStream().close();
        p.getOutputStream().close();
        p.getErrorStream().close();
        p.destroy();
        br.close();
        isr.close();
        is.close();
//        return outfile + ".txt";
        File f = new File(tempfile + ".txt");
        FileInputStream fis = new FileInputStream(f);
        byte[] bytes = new byte[(int) f.length()];
        fis.read(bytes);
        fis.close();
        f.delete();
        String ret = new String(bytes).trim();
//        System.out.println(ret);
        return ret;
    }

    private static String ocrBaidu(String filename) throws Exception {
        String outfile = GlobalConstant.DISKPATH + "temp\\ocr\\" + filename.substring(filename.lastIndexOf("\\") + 1);
        outfile = outfile.substring(0, outfile.lastIndexOf("."));

        AipOcr client = new AipOcr(BaiduUtils.APPID_OCR, BaiduUtils.APIKEY, BaiduUtils.SECRETACCESSKEY);
        HashMap<String, String> m = new HashMap<String, String>();
        org.json.JSONObject ret = client.general(filename, m);
        JSONObject json = new JSONObject(ret.toString());
        if(StringUtils.isNotEmpty(json.optString("error_code"))) {
            System.out.println(json.getString("error_msg"));
            throw new Exception("OCR次数超限");
        }
        if(json.optJSONArray("words_result").length() > 0) {
            Object obj = json.optJSONArray("words_result").get(0);
            if(obj != null) {
                json = (JSONObject) obj;
                return json.getString("words");
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    private static String cutLevelImage(String filepath) throws Exception {
        BufferedImage bufImg = ImageIO.read(new File(filepath));
        boolean isIOS = false;
        /* 判断安卓还是IOS */
        boolean found = false;
        for(int i = 0; i <= 10; i++) {
            int color = bufImg.getRGB(i, 180);
            int red = (color & 0xFF0000) >> 16;
            int green = (color & 0xFF00) >> 8;
            int blue = color & 0xFF;
            if(red < 10 && (green > 80 && green < 110) && blue > 200) {
                found = true;
                break;
            }
        }
        if(found) {
            isIOS = false;
        } else {
            isIOS = true;
        }

        int x1, y1, x2, y2;
        if(isIOS) {
            x1 = 270;
            x2 = 890;
            y1 = 355;
            y2 = 422;
        } else {
            x1 = 320;
            x2 = 950;
            y1 = 280;
            y2 = 355;
        }
        File f = new File(filepath);
        String name = f.getName();
        name = name.substring(0, name.lastIndexOf("."));
        String ret = GlobalConstant.DISKPATH + "temp\\ocr\\"+name + "_level.png";
        File outfile = new File(ret);
        if(!outfile.exists()) {
            outfile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(outfile);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = readers.next();
        InputStream is = new FileInputStream(f);
        ImageInputStream imageStream = ImageIO.createImageInputStream(is);
        reader.setInput(imageStream, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "png", fos);
        return ret;
    }

    private static String cutAuthorImage(String filepath) throws Exception {
        boolean isIOS = false;
        BufferedImage bufImg = ImageIO.read(new File(filepath));
        /* 判断安卓还是IOS */
        boolean found = false;
        for(int i = 0; i <= 10; i++) {
            int color = bufImg.getRGB(i, 180);
            int red = (color & 0xFF0000) >> 16;
            int green = (color & 0xFF00) >> 8;
            int blue = color & 0xFF;
            if(red < 10 && (green > 80 && green < 110) && blue > 200) {
                found = true;
                break;
            }
        }
        if(found) {
            isIOS = false;
        } else {
            isIOS = true;
        }
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        if(isIOS) {
            x1 = 400;
            x2 = 640;
            y1 = 230;
            y2 = 290;
        }
        File f = new File(filepath);
        String name = f.getName();
        name = name.substring(0, name.lastIndexOf("."));
        String ret = GlobalConstant.DISKPATH + "temp\\ocr\\"+name + "_author.png";
        File outfile = new File(ret);
        if(!outfile.exists()) {
            outfile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(outfile);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = readers.next();
        InputStream is = new FileInputStream(f);
        ImageInputStream imageStream = ImageIO.createImageInputStream(is);
        reader.setInput(imageStream, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x1, y1, x2-x1, y2-y1);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "png", fos);
        fos.close();
        return ret;
    }

    private static String cutNameImage(String filepath) throws Exception {
        File f = new File(filepath);
        String name = f.getName();
        name = name.substring(0, name.lastIndexOf("."));
        String ret = GlobalConstant.DISKPATH + "temp\\ocr\\"+name + "_name.png";
        File outfile = new File(ret);
        if(!outfile.exists()) {
            outfile.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(outfile);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = readers.next();
        InputStream is = new FileInputStream(f);
        ImageInputStream imageStream = ImageIO.createImageInputStream(is);
        reader.setInput(imageStream, true);
        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(400, 160, 470, 60);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        ImageIO.write(bi, "png", fos);
        fos.close();
        return ret;
    }

    private static int cutScoreImage(String filepath) throws Exception {
        BufferedImage bufImg = ImageIO.read(new File(filepath));
        boolean isIOS = false;
        boolean isWeek = false;
        boolean isFriend = false; //友闯，20关的
        /* 判断安卓还是IOS */
        boolean found = false;
        for(int i = 0; i <= 10; i++) {
            int color = bufImg.getRGB(i, 180);
            int red = (color & 0xFF0000) >> 16;
            int green = (color & 0xFF00) >> 8;
            int blue = color & 0xFF;
            if(red < 10 && (green > 80 && green < 110) && blue > 200) {
                found = true;
                break;
            }
        }
        if(found) {
            isIOS = false;
        } else {
            isIOS = true;
        }

        if(isIOS) {
            /* 判断是周闯还是其他 */
            found = false;
            for(int i = 1220; i <= 1230; i++) {
                int color = bufImg.getRGB(i, 862);
                int red = (color & 0xFF0000) >> 16;
                int green = (color & 0xFF00) >> 8;
                int blue = color & 0xFF;
                if(red < 100 && green > 150 && blue < 100) {
                    found = true;
                    break;
                }
            }
            if(found) { //周闯
                isWeek = true;
            } else {
                for(int i = 980; i < 1000; i++) {
                    int color = bufImg.getRGB(i, 375);
                    int red = (color & 0xFF0000) >> 16;
                    int green = (color & 0xFF00) >> 8;
                    int blue = color & 0xFF;
                    if(red > 200 && green > 200 && blue > 200) {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    isFriend = true;
                } else {
                    isFriend = false;
                }
            }
        } else {
            /* 判断是周闯还是其他 */
            found = false;
            for(int i = 1195; i <= 1210; i++) {
                int color = bufImg.getRGB(i, 879);
                int red = (color & 0xFF0000) >> 16;
                int green = (color & 0xFF00) >> 8;
                int blue = color & 0xFF;
                if(red < 100 && green > 150 && blue < 100) {
                    found = true;
                    break;
                }
            }
            if(found) { //周闯
                isWeek = true;
            } else {
                isWeek = false;
            }
        }

        int x1, x2, y1, y2;
        if(isIOS) {
            if(isWeek) {
                x1 = 1360;
                x2 = 1590;
                y1 = 830;
                y2 = 880;
            } else if(isFriend) {
                x1 = 860;
                x2 = 1540;
                y1 = 665;
                y2 = 770;
            } else {
                x1 = 900;
                x2 = 1567;
                y1 = 685;
                y2 = 790;
            }
        } else {
            if(isWeek) {
                x1 = 1382;
                x2 = 1620;
                y1 = 845;
                y2 = 900;
            } else {
                x1 = 895;
                x2 = 1602;
                y1 = 665;
                y2 = 795;
            }
        }
        File f = new File(filepath);
        String name = f.getName();
        name = name.substring(0, name.lastIndexOf("."));
//        String ret = "D:\\temp\\ocr\\"+name + "_score.png";
//        File outfile = new File(ret);
//        if(!outfile.exists()) {
//            outfile.createNewFile();
//        }
//        FileOutputStream fos = new FileOutputStream(outfile);
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("png");
        ImageReader reader = readers.next();
        InputStream is = new FileInputStream(f);
        ImageInputStream imageStream = ImageIO.createImageInputStream(is);
        reader.setInput(imageStream, true);

        ImageReadParam param = reader.getDefaultReadParam();
        Rectangle rect = new Rectangle(x1, y1, x2 - x1, y2 - y1);
        param.setSourceRegion(rect);
        BufferedImage bi = reader.read(0, param);
        for(int i = 0; i < bi.getWidth(); i++) {
            for(int j = 0; j < bi.getHeight(); j++) {
                int color = bi.getRGB(i, j);
                int red = (color & 0xFF0000) >> 16;
                int green = (color & 0xFF00) >> 8;
                int blue = color & 0xFF;
                if(red < 100 && green < 100 && blue < 100) {
                    bi.setRGB(i,j, 0xFFFFFF);
                } else {
                    bi.setRGB(i,j,0x010101);
                }
            }
        }

        /* 识别分数 */
        String comparepath = GlobalConstant.DISKPATH + "rm\\OCR_src\\";
        if(isIOS) {
            comparepath += "IOS_";
        } else {
            comparepath += "android_";
        }
        if(isWeek) {
            comparepath += "week_";
        } else if(isFriend) {
            comparepath += "friend_";
        } else {
            comparepath += "free_";
        }
        int score = getScore(bi, comparepath);
        return score;
    }

    private static int getScore(BufferedImage bufImg, String comparepath) throws Exception {
        int width = bufImg.getWidth(), height = bufImg.getHeight();
        int cutbegin = 0;
        int ret = 0;
        boolean iscutting = false;
        for(int x = 0; x < width; x++) {
            boolean blank = true;
            for(int y = 0; y < height; y++) {
//                System.out.println(x + "\t"+y);
                int rgb = bufImg.getRGB(x,y);
                if((rgb & 0xffffff) == 0x10101) {
                    blank = false;
                    break;
                }
            }
            if(blank) {
                if(iscutting) {
                    BufferedImage subImage = bufImg.getSubimage(cutbegin, 0, x - cutbegin, height);
                    ret = ret * 10 + checkScore(subImage, comparepath);
                    iscutting = false;
                    cutbegin = x;
                } else {
                    cutbegin = x;
                }
            } else {
                if(iscutting) {
                } else {
                    iscutting = true;
                }
            }
        }
        return ret;
    }

    private static int checkScore(BufferedImage img, String path) throws Exception {
        int ret = 0;
        double matches = 0;
        for(int i = 0; i < 10; i++) {
            int match = 0, total = 0;
            String file = path + i + ".png";
            BufferedImage srcImg = ImageIO.read(new File(file));
            for(int x = 0; x < srcImg.getWidth(); x++) {
                for(int y = 0; y < srcImg.getHeight(); y++) {
                    int rgb1 = srcImg.getRGB(x, y) & 0xFFFFFF;
                    if(img.getWidth() <= x || img.getHeight() <= y) {
                        total++;
                    } else {
//                        System.out.println("x="+x+"\timgX="+img.getWidth()+"\ty="+y+"\timgY="+img.getHeight());
                        int rgb2 = img.getRGB(x, y) & 0xFFFFFF;
                        total++;
                        match += rgb1 == rgb2 ? 1 : 0;
                    }
                }
            }
            if((((double)match) / ((double)total)) > matches) {
                ret = i;
                matches = (((double)match) / ((double)total));
            }
//            System.out.println("i:" + i + "\t"+ );
        }
        if(matches < 0.8) {
            System.out.println("匹配度不够！" + ret+"=" + matches);
        }
//        System.out.println("return " + ret + ", match="+matches);
        return ret;
    }

    public static List<String[]> getSongNames() throws Exception {
        List<String[]> ret = new ArrayList<String[]>();
        File excel = new File(GlobalConstant.DISKPATH + "excel\\节奏大师歌曲.xls");
        FileInputStream excelis = new FileInputStream(excel);
        Workbook workbook = Workbook.getWorkbook(excelis);
        sheet = workbook.getSheet("歌曲");
        int maxrow = sheet.getRows() - 1;
        for(int j = 2; j <= maxrow; j++) {
            Cell[] cells = sheet.getRow(j);
            ret.add(new String[]{cells[0].getContents(), cells[1].getContents(), j + ""});
        }
        return ret;
    }

    private static boolean isSanwu(String filepath) throws Exception {
        boolean sanwu = true;
        BufferedImage img = ImageIO.read(new File(filepath));
        for(int x = 70; x <= 440; x++) {
            int rgb = img.getRGB(x, 1050);
            int red = (rgb & 0xFF0000) >> 16;
            int green = (rgb & 0xFF00) >> 8;
            int blue = (rgb & 0xFF);
            if(red > 50 || green > 40 || blue < 30 || blue > 110) {
//                System.out.println(x+"/"+red + "/"+green+"/"+blue+"\t"+filepath);
                sanwu = false;
                break;
            }
        }
        return sanwu;
    }
}
