package com.saille.rm;

import com.google.common.io.LittleEndianDataInputStream;

import java.util.zip.ZipInputStream;
import java.util.zip.ZipEntry;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-12-25
 * Time: 2:08:20
 * To change this template use File | Settings | File Templates.
 */
public class WeekMatch {
    private int index; //�ļ��ڱ��
    private int totalIndex; //�����
    private int stage; //����
    private int targetType; //��������
    private int targetNumber; //����Ҫ��
    private int songId; //����ID
    private int level; //ez/nm/hd
    private int key; //456
    private int special; //�Ƿ�������Ч��
    private String startdate;
    private String enddate;

    public static void main(String[] args) {
        try {
            List<WeekMatch> list = load();
            Map<String, String> songs = loadSongs();
            for(WeekMatch obj : list) {
                System.out.println(obj.index + "\t" +
                        obj.totalIndex + "\t" +
                        obj.stage + "\t" +
                        songs.get(obj.songId + "") + "\t" +
                        convertMission(obj.targetType, obj.targetNumber) + "\t" +
                        (obj.key + 3) +
                        (obj.level == 1 ? "Easy" : (obj.level == 2 ? "Normal" : (obj.level == 3 ? "Hard" : "δ֪�Ѷ�"))) + "\t" +
                        convertSpecial(obj.special)
                );
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<WeekMatch> load() throws Exception {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);

        List<Map> list = jt.queryForList("select startdate from rm_friendsong group by startdate order by startdate desc limit 4");
        String begindate = list.get(list.size() - 1).get("startdate").toString();
        list = jt.queryForList("select * from rm_friendsong where startdate >= ? order by startdate desc, subindex", new Object[]{begindate});
        List<WeekMatch> ret = new ArrayList<WeekMatch>();
        for(int i = 0; i < list.size(); i++) {
            Map m = list.get(i);
            WeekMatch obj = new WeekMatch();
            obj.totalIndex = ((Integer) m.get("totalindex")).intValue();
            obj.stage = ((Integer) m.get("subindex")).intValue();
            obj.targetType = ((Integer) m.get("targetType")).intValue();
            obj.targetNumber = ((Integer) m.get("targetNumber")).intValue();
            obj.songId = ((Integer) m.get("songId")).intValue();
            obj.level = ((Integer) m.get("level")).intValue();
            obj.key = ((Integer) m.get("key")).intValue();
            obj.special = ((Integer) m.get("special")).intValue();
            obj.startdate = m.get("startdate").toString();
            Object o = m.get("enddate");
            if(o != null) {
                obj.enddate = o.toString();
            }
            ret.add(obj);
        }
        return ret;
    }

    private static Map<String, String> loadSongs() throws Exception {
        List<MrockSongClientAndroid> list = MrockSongClientAndroid.getFromUrl(true);
        Map<String, String> ret = new HashMap<String, String>();
        for(MrockSongClientAndroid obj : list) {
            ret.put(obj.getM_ushSongID() + "", obj.getM_szSongName());
        }
        return ret;
    }

    public static String convertMission(int type, int number) {
        switch(type) {
            case 3:
                return "Ѫ��������" + number + "%";
            case 4:
                return "Ѫ��������" + number + "%";
            case 5:
                return "��������ﵽ" + number + "";
            case 6:
                return "�������������" + number + "";
            case 7:
            {
                if(number == 1) {
                    return "�ɼ��ﵽD";
                } else if(number == 2) {
                    return "�ɼ��ﵽC";
                } else if(number == 3) {
                    return "�ɼ��ﵽB";
                } else if(number == 4) {
                    return "�ɼ��ﵽA";
                } else if(number == 5) {
                    return "�ɼ��ﵽS";
                } else if(number == 6) {
                    return "�ɼ��ﵽSS";
                } else if(number == 7) {
                    return "�ɼ��ﵽSSS";
                }
            }
            case 8:
                return "MISS������" + number + "��";
            case 9:
                return "MISS������" + number + "��";
            case 10:
                return "GOOD������" + number + "��";
            case 11:
                return "GOOD������" + number + "��";
            case 14:
                if(number == 0) {
                    return "�����ﵽAll Combo";
                } else {
                    return "�����ﵽAll Perfect";
                }
            case 15:
                return "��������";
            case 16:
                return "�����ﵽ" + number;
            case 17:
                return "����������" + number;
            case 18:
                return "��Ѫ����";
            case 19:
                return "Perfect�ﵽ" + number + "%";
            case 20:
                return "Perfect������" + number + "%";
            case 21:
                return "GOOD������" + number + "%";
            case 22:
                return "GOOD������" + number + "%";
            case 23:
                return "MISS������" + number + "%";
            case 24:
                return "MISS������" + number + "%";
            case 25:
                return "����������" + number + "%";
            case 26:
                return "����������" + number + "%";
            case 27:
                return "�����ﵽ" + number + "%";
            case 28:
                return "����������" + number + "%";
            default:
                return "δ֪����:" + type;
        }
    }

    public static String convertSpecial(int type) {
        switch(type) {
            case 0:
                return "��";
            case 1:
                return "����";
            case 2:
                return "����";
            case 3:
                return "��˸";
            default:
                return "δ֪��Ч:" + type;
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTotalIndex() {
        return totalIndex;
    }

    public void setTotalIndex(int totalIndex) {
        this.totalIndex = totalIndex;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    public int getTargetNumber() {
        return targetNumber;
    }

    public void setTargetNumber(int targetNumber) {
        this.targetNumber = targetNumber;
    }

    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getSpecial() {
        return special;
    }

    public void setSpecial(int special) {
        this.special = special;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
}
