package com.saille.rm;

import com.saille.sys.Setting;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;

import java.util.*;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.io.*;
import java.lang.reflect.Field;

import com.google.common.io.LittleEndianDataInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2015-12-23
 * Time: 13:07:39
 * To change this template use File | Settings | File Templates.
 */
public class MrockSongClientAndroid {
    private final static Logger LOGGER = Logger.getLogger(MrockSongClientAndroid.class);

    private int m_ushSongID;
    private int m_iVersion;
    private String m_szSongName;
    private String m_szPath;
    private String m_szArtist;
    private String m_szComposer;
    private String m_szSongTime;
    private int m_iGameTime;
    private int m_iRegion;
    private int m_iStyle;
    private int m_ucIsNew;
    private int m_ucIsHot;
    private int m_ucIsRecommend;
    private String m_szBPM;
    private int m_ucIsOpen;
    private int m_ucCanBuy;
    private int m_iOrderIndex;
    private int m_bIsFree;
    private int m_bSongPkg;
    private String m_szFreeBeginTime;
    private String m_szFreeEndTime;
    private int m_ush4KeyEasy;
    private int m_ush4KeyNormal;
    private int m_ush4KeyHard;
    private int m_ush5KeyEasy;
    private int m_ush5KeyNormal;
    private int m_ush5KeyHard;
    private int m_ush6KeyEasy;
    private int m_ush6KeyNormal;
    private int m_ush6KeyHard;
    private int m_iPrice;
    private String m_szNoteNumber;
    private String m_szProductID;
    private String m_iVipFlag;
    private int m_bIsHide;
    private int m_bIsReward;
    private String m_bIsLevelReward;

    public static void main(String[] args) {
        try {
            List<MrockSongClientAndroid> list = getFromUrl(true);
            for(MrockSongClientAndroid obj : list) {
                Map<String, String> map = convert2Map(obj);
            }
            System.out.println(list);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Map<String, String>> getMapFromUrl(boolean includelevel) throws Exception {
        List<MrockSongClientAndroid> list = getFromUrl(includelevel);
        List<Map<String, String>> ret = new ArrayList<Map<String, String>>();
        for(MrockSongClientAndroid obj : list) {
            Map<String, String> map = convert2Map(obj);
            ret.add(map);
        }
        return ret;
    }

    public static List<MrockSongClientAndroid> getFromUrl(boolean includelevel) {
        List<MrockSongClientAndroid> ret = new ArrayList<MrockSongClientAndroid>();
        try {
            DownloadZipUtil.download();
            String[] files;
            if(includelevel) {
                files = new String[]{Setting.getSettingString("RM_PATH") + "TableComBin\\mrock_song_client_android.bin", Setting.getSettingString("RM_PATH") + "TableComBin\\mrock_songlevel_client.bin"};
            } else {
                files = new String[]{Setting.getSettingString("RM_PATH") + "TableComBin\\mrock_song_client_android.bin"};
            }
            for(String f : files) {
                FileInputStream fis = new FileInputStream(f);
                LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
                dis.skip(8);
                int unitLength = dis.readInt(); //每个单元的长度
                int songCount = dis.readInt(); //歌曲数量
                dis.skip(120);
                for(int i = 0; i < songCount; i++) {
                    byte[] b = new byte[unitLength];
                    dis.read(b);
                    ByteArrayInputStream bais = new ByteArrayInputStream(b);
                    LittleEndianDataInputStream singleDis = new LittleEndianDataInputStream(bais);
                    MrockSongClientAndroid obj = new MrockSongClientAndroid();
                    int valInt = singleDis.readChar();
                    obj.setM_ushSongID(valInt);
                    valInt = singleDis.readInt();
                    obj.setM_iVersion(valInt);
                    byte[] chars = new byte[64];
                    singleDis.read(chars);//70
                    String valStr = getStringFromBytes(chars);
                    obj.setM_szSongName(valStr);
                    singleDis.read(chars);//134
                    valStr = getStringFromBytes(chars);
                    obj.setM_szPath(valStr);
                    singleDis.read(chars);//198
                    valStr = getStringFromBytes(chars);
                    obj.setM_szArtist(valStr);
                    singleDis.skip(64); //262
                    chars = new byte[18];
                    singleDis.read(chars); //280
                    obj.setM_szSongTime(getStringFromBytes(chars));
                    singleDis.skip(46); //326
                    valInt = singleDis.readInt();
                    obj.setM_iGameTime(valInt);
                    ret.add(obj);
                    valInt = singleDis.readInt(); //330
                    obj.setM_iRegion(valInt);
                    valInt = singleDis.readInt(); //334
                    obj.setM_iStyle(valInt);
                    valInt = singleDis.readChar(); //338
                    obj.setM_ucIsNew(valInt);
                    valInt = singleDis.readChar(); //340
                    obj.setM_ucIsHot(valInt);
                    valInt = singleDis.readChar(); //342
                    obj.setM_ucIsRecommend(valInt);
                    chars = new byte[18];
                    singleDis.read(chars); //344-362
                    obj.setM_szBPM(getStringFromBytes(chars));
                    singleDis.skip(46); //408
                    obj.setM_ucIsOpen(singleDis.readChar()); //408
                    obj.setM_ucCanBuy(singleDis.readByte()); //410
                    obj.setM_iOrderIndex(singleDis.readInt()); //411-414
                    obj.setM_bIsFree(singleDis.readByte()); //415
                    obj.setM_bSongPkg(singleDis.readByte()); //416
                    singleDis.skip(128); //545
                    obj.setM_ush4KeyEasy(singleDis.readChar()); //545
                    obj.setM_ush4KeyNormal(singleDis.readChar()); //547
                    obj.setM_ush4KeyHard(singleDis.readChar()); //549
                    obj.setM_ush5KeyEasy(singleDis.readChar()); //551
                    obj.setM_ush5KeyNormal(singleDis.readChar()); //553
                    obj.setM_ush5KeyHard(singleDis.readChar()); //555
                    obj.setM_ush6KeyEasy(singleDis.readChar()); //557
                    obj.setM_ush6KeyNormal(singleDis.readChar()); //559
                    obj.setM_ush6KeyHard(singleDis.readChar()); //561
                    obj.setM_iPrice(singleDis.readInt()); //563
                    chars = new byte[113];
                    singleDis.read(chars);
                    obj.setM_szNoteNumber(getStringFromBytes(chars)); //567-680
                    singleDis.skip(15); //695
                    chars = new byte[100];
                    singleDis.read(chars); //695-794
                    obj.setM_szProductID(getStringFromBytes(chars));
                    singleDis.skip(32); //827
                    obj.setM_bIsHide(singleDis.readByte()); //827
                    obj.setM_bIsReward(singleDis.readByte()); //828

                    singleDis.close();
                    bais.close();
                }
                dis.close();
            }
//                        System.out.println(entry);
//                        entry.
        } catch(Exception ex) {
            LOGGER.warn("读取mrock_song_client_android.xml失败");
            ex.printStackTrace();
        }
        return ret;
    }

    public static String getStringFromBytes(byte[] chars) throws Exception {
        byte[] newBytes = null;
        int begin = -1, end = -1;
        for(int i = chars.length - 1; i >= 0; i--) {
            if(chars[i] != 0) {
                end = i + 1;
//                newBytes = Arrays.copyOfRange(chars, 0, i+1);
                break;
            }
        }
        for(int i = 0; i < chars.length; i++) {
            if(chars[i] != 0) {
                begin = i;
                break;
            }
        }
        if(begin >= 0 && end >= 0) {
            newBytes = Arrays.copyOfRange(chars, begin, end);
        }
        if(newBytes == null) {
            return null;
        }
        return new String(newBytes, "UTF-8");
    }

    private static Map<String, String> convert2Map(MrockSongClientAndroid obj) throws Exception {
        Map<String, String> ret = new HashMap<String, String>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field f : fields) {
            String fieldName = f.getName();
            Class fieldType = f.getType();
            if(fieldType.equals(int.class)) {
                ret.put(fieldName, f.getInt(obj) + "");
            } else if(fieldType.equals(String.class)) {
                Object o = f.get(obj);
                if(o == null) {
                    ret.put(fieldName, "");
                } else {
                    ret.put(fieldName, f.get(obj) + "");
                }
            }
        }
        return ret;
    }

    public int getM_ushSongID() {
        return m_ushSongID;
    }

    public void setM_ushSongID(int m_ushSongID) {
        this.m_ushSongID = m_ushSongID;
    }

    public int getM_iVersion() {
        return m_iVersion;
    }

    public void setM_iVersion(int m_iVersion) {
        this.m_iVersion = m_iVersion;
    }

    public String getM_szSongName() {
        return m_szSongName;
    }

    public void setM_szSongName(String m_szSongName) {
        this.m_szSongName = m_szSongName;
    }

    public String getM_szPath() {
        return m_szPath;
    }

    public void setM_szPath(String m_szPath) {
        this.m_szPath = m_szPath;
    }

    public String getM_szArtist() {
        return m_szArtist;
    }

    public void setM_szArtist(String m_szArtist) {
        this.m_szArtist = m_szArtist;
    }

    public String getM_szComposer() {
        return m_szComposer;
    }

    public void setM_szComposer(String m_szComposer) {
        this.m_szComposer = m_szComposer;
    }

    public String getM_szSongTime() {
        return m_szSongTime;
    }

    public void setM_szSongTime(String m_szSongTime) {
        this.m_szSongTime = m_szSongTime;
    }

    public int getM_iGameTime() {
        return m_iGameTime;
    }

    public void setM_iGameTime(int m_iGameTime) {
        this.m_iGameTime = m_iGameTime;
    }

    public int getM_iRegion() {
        return m_iRegion;
    }

    public void setM_iRegion(int m_iRegion) {
        this.m_iRegion = m_iRegion;
    }

    public int getM_iStyle() {
        return m_iStyle;
    }

    public void setM_iStyle(int m_iStyle) {
        this.m_iStyle = m_iStyle;
    }

    public int getM_ucIsNew() {
        return m_ucIsNew;
    }

    public void setM_ucIsNew(int m_ucIsNew) {
        this.m_ucIsNew = m_ucIsNew;
    }

    public int getM_ucIsHot() {
        return m_ucIsHot;
    }

    public void setM_ucIsHot(int m_ucIsHot) {
        this.m_ucIsHot = m_ucIsHot;
    }

    public int getM_ucIsRecommend() {
        return m_ucIsRecommend;
    }

    public void setM_ucIsRecommend(int m_ucIsRecommend) {
        this.m_ucIsRecommend = m_ucIsRecommend;
    }

    public String getM_szBPM() {
        return m_szBPM;
    }

    public void setM_szBPM(String m_szBPM) {
        this.m_szBPM = m_szBPM;
    }

    public int getM_ucIsOpen() {
        return m_ucIsOpen;
    }

    public void setM_ucIsOpen(int m_ucIsOpen) {
        this.m_ucIsOpen = m_ucIsOpen;
    }

    public int getM_ucCanBuy() {
        return m_ucCanBuy;
    }

    public void setM_ucCanBuy(int m_ucCanBuy) {
        this.m_ucCanBuy = m_ucCanBuy;
    }

    public int getM_iOrderIndex() {
        return m_iOrderIndex;
    }

    public void setM_iOrderIndex(int m_iOrderIndex) {
        this.m_iOrderIndex = m_iOrderIndex;
    }

    public int getM_bIsFree() {
        return m_bIsFree;
    }

    public void setM_bIsFree(int m_bIsFree) {
        this.m_bIsFree = m_bIsFree;
    }

    public int getM_bSongPkg() {
        return m_bSongPkg;
    }

    public void setM_bSongPkg(int m_bSongPkg) {
        this.m_bSongPkg = m_bSongPkg;
    }

    public String getM_szFreeBeginTime() {
        return m_szFreeBeginTime;
    }

    public void setM_szFreeBeginTime(String m_szFreeBeginTime) {
        this.m_szFreeBeginTime = m_szFreeBeginTime;
    }

    public String getM_szFreeEndTime() {
        return m_szFreeEndTime;
    }

    public void setM_szFreeEndTime(String m_szFreeEndTime) {
        this.m_szFreeEndTime = m_szFreeEndTime;
    }

    public int getM_ush4KeyEasy() {
        return m_ush4KeyEasy;
    }

    public void setM_ush4KeyEasy(int m_ush4KeyEasy) {
        this.m_ush4KeyEasy = m_ush4KeyEasy;
    }

    public int getM_ush4KeyNormal() {
        return m_ush4KeyNormal;
    }

    public void setM_ush4KeyNormal(int m_ush4KeyNormal) {
        this.m_ush4KeyNormal = m_ush4KeyNormal;
    }

    public int getM_ush4KeyHard() {
        return m_ush4KeyHard;
    }

    public void setM_ush4KeyHard(int m_ush4KeyHard) {
        this.m_ush4KeyHard = m_ush4KeyHard;
    }

    public int getM_ush5KeyEasy() {
        return m_ush5KeyEasy;
    }

    public void setM_ush5KeyEasy(int m_ush5KeyEasy) {
        this.m_ush5KeyEasy = m_ush5KeyEasy;
    }

    public int getM_ush5KeyNormal() {
        return m_ush5KeyNormal;
    }

    public void setM_ush5KeyNormal(int m_ush5KeyNormal) {
        this.m_ush5KeyNormal = m_ush5KeyNormal;
    }

    public int getM_ush5KeyHard() {
        return m_ush5KeyHard;
    }

    public void setM_ush5KeyHard(int m_ush5KeyHard) {
        this.m_ush5KeyHard = m_ush5KeyHard;
    }

    public int getM_ush6KeyEasy() {
        return m_ush6KeyEasy;
    }

    public void setM_ush6KeyEasy(int m_ush6KeyEasy) {
        this.m_ush6KeyEasy = m_ush6KeyEasy;
    }

    public int getM_ush6KeyNormal() {
        return m_ush6KeyNormal;
    }

    public void setM_ush6KeyNormal(int m_ush6KeyNormal) {
        this.m_ush6KeyNormal = m_ush6KeyNormal;
    }

    public int getM_ush6KeyHard() {
        return m_ush6KeyHard;
    }

    public void setM_ush6KeyHard(int m_ush6KeyHard) {
        this.m_ush6KeyHard = m_ush6KeyHard;
    }

    public int getM_iPrice() {
        return m_iPrice;
    }

    public void setM_iPrice(int m_iPrice) {
        this.m_iPrice = m_iPrice;
    }

    public String getM_szNoteNumber() {
        return m_szNoteNumber;
    }

    public void setM_szNoteNumber(String m_szNoteNumber) {
        this.m_szNoteNumber = m_szNoteNumber;
    }

    public String getM_szProductID() {
        return m_szProductID;
    }

    public void setM_szProductID(String m_szProductID) {
        this.m_szProductID = m_szProductID;
    }

    public String getM_iVipFlag() {
        return m_iVipFlag;
    }

    public void setM_iVipFlag(String m_iVipFlag) {
        this.m_iVipFlag = m_iVipFlag;
    }

    public int getM_bIsHide() {
        return m_bIsHide;
    }

    public void setM_bIsHide(int m_bIsHide) {
        this.m_bIsHide = m_bIsHide;
    }

    public int getM_bIsReward() {
        return m_bIsReward;
    }

    public void setM_bIsReward(int m_bIsReward) {
        this.m_bIsReward = m_bIsReward;
    }

    public String getM_bIsLevelReward() {
        return m_bIsLevelReward;
    }

    public void setM_bIsLevelReward(String m_bIsLevelReward) {
        this.m_bIsLevelReward = m_bIsLevelReward;
    }
}
