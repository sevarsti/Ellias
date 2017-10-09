package com.saille.rm.loop;

import com.google.common.io.LittleEndianDataInputStream;
import com.saille.rm.RMConstant;
import com.saille.rm.util.RMUtils;
import com.saille.rm.DownloadZipUtil;
import com.saille.sys.BaseThread;
import com.saille.sys.Setting;
import com.saille.util.SendSMSUtils;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-14
 * Time: 16:53:22
 * To change this template use File | Settings | File Templates.
 */
public class FriendsongThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(FriendsongThread.class);
    public static void main(String[] args) {
    }

    private FriendsongThread() {}

    private static List<String> getKeys(Map<String, List<int[]>> list) {
        List<String> ret = new ArrayList<String>();
        for(String key : list.keySet()) {
            ret.add(key);
        }
        for(int i = 0; i < ret.size(); i++) {
            for(int j = i + 1; j < ret.size(); j++) {
                if(ret.get(j).compareTo(ret.get(i)) < 0) {
                    String tmp = ret.get(i);
                    ret.set(i, ret.get(j));
                    ret.set(j, tmp);
                }
            }
        }
        return ret;
    }
    public int execute() {
        try {
            DownloadZipUtil.download();
            Map<String, List<int[]>> newList = new HashMap<String, List<int[]>>();
            File f = new File(Setting.getSettingString("RM_PATH") + "TableComBin\\mrock_Map_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //ÿ����Ԫ�ĳ���
            int count = dis.readInt(); //��������
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
                if(i == 0) {
                    continue;
                }
//                RMUtils.printBytes(bytes, 0, bytes.length, true);
//                int index = RMUtils.getInt(bytes, 0, 4);
                int totalindex = RMUtils.getInt(bytes, 4, 8);
                int subindex = RMUtils.getInt(bytes, 13, 4);
                int targettype = RMUtils.getInt(bytes, 17, 4);
                int targetnumber = RMUtils.getInt(bytes, 21, 4);
                int songid = songIdChange(RMUtils.getInt(bytes, 25, 4));
                int level = RMUtils.getInt(bytes, 29, 4);
                int key = RMUtils.getInt(bytes, 33, 4);
                int special = RMUtils.getInt(bytes, 53, 4);
                if(!newList.containsKey(totalindex + "")) {
                    newList.put(totalindex + "", new ArrayList<int[]>());
                }
                newList.get(totalindex + "").add(new int[]{totalindex, subindex, targettype, targetnumber, songid, level, key, special});
            }
            dis.close();
            fis.close();

            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<String> keys = getKeys(newList);
            Map<String, List<int[]>> oldList = getCurrentList(jt, keys);
            List<int[][]> updatelist = checkExact(keys, oldList, newList);

            String sql = "update rm_friendsong set totalindex = ?, subindex = ?, targettype = ?, targetnumber = ?, songid = ?, `level` = ?, `key` = ?, `special` = ?" +
                    " where totalindex = ? and subindex = ? and targettype = ? and targetnumber = ? and songid = ? and `level` = ? and `key` = ? and `special` = ?";
            for(int i = 0; i < updatelist.size(); i++) {
                int[] oldobj = updatelist.get(i)[0];
                int[] newobj = updatelist.get(i)[1];
                Object[] params = new Object[oldobj.length + newobj.length];
                for(int j = 0; j < newobj.length; j++) {
                    params[j] = newobj[j];
                    params[j + newobj.length] = oldobj[j];
                }
                jt.update(sql, params);
            }
            boolean allfinish = true;
            for(int i = 0; i < keys.size(); i++) {
                if(keys.get(i) != null) {
                    allfinish = false;
                    break;
                }
            }

//            for(int i = 0; i < keys.size(); i++) {
//                int[] lastsong = newList.get(keys.get(i)).get(19);
//                int has = jt.queryForInt("select count(1) from rm_friendsong where totalindex = ? and subindex = ? and targettype = ? and targetnumber = ? and songid = ? and level = ? and `key` = ? and `special` = ?",
//                               new Object[]{lastsong[0], lastsong[1], lastsong[2], lastsong[3], lastsong[4], lastsong[5], lastsong[6], lastsong[7]});
//                if(has > 0) {
//                    keys.set(i, null);
//                }
//            }
//            boolean allfinish = true;
//            for(int i = 0; i < keys.size(); i++) {
//                if(keys.get(i) != null) {
//                    allfinish = false;
//                    break;
//                }
//            }
            if(allfinish) { //��Ҫ�����µ�һ���ǲ��ǻ�û����
                List<Map> max = jt.queryForList("select max(startdate) as startdate from rm_friendsong");
                String maxdate = max.get(0).get("startdate").toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(maxdate));
                c.add(Calendar.DATE, 7); //�õ�������00:00
                c.add(Calendar.HOUR, 8); //�õ�������08:00
                if(new Date().getTime() > c.getTimeInMillis()) { //��������8�㻹û���£���Ҫ�ֶ�����
                    LOGGER.info("�������������Ǻ��Ѵ���û�и��£��ֶ�������һ��");
                    jt.update("insert into rm_friendsong(startdate, totalindex, subindex, targettype, targetnumber, songid, level, `key`, `special`)(" +
                            "select ?, totalindex, subindex, targettype, targetnumber, songid, level, `key`, `special` from rm_friendsong where startdate = ?)",
                              new Object[]{sdf.format(c.getTime()), maxdate});

                    c.add(Calendar.DATE, -1);
                    jt.update("update rm_friendsong set enddate = ? where enddate is null", new Object[]{sdf.format(c.getTime())});
                    SendSMSUtils.sendSMS("58057113bb06414092b915a71b562978", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                } else {
                    LOGGER.info("���Ѵ���û�и���");
                }
            } else {
                List<List<int[]>> updateList = sortSaveList(newList, keys);
                List<Map> max = jt.queryForList("select max(startdate) as startdate from rm_friendsong");
                String maxdate = max.get(0).get("startdate").toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar c = Calendar.getInstance();
                c.setTime(sdf.parse(maxdate));
                for(List<int[]> list : updateList) {
                    c.add(Calendar.DATE, 6);
                    jt.update("update rm_friendsong set enddate = ? where enddate is null", new Object[]{sdf.format(c.getTime())});
                    c.add(Calendar.DATE, 1);
                    for(int[] obj : list) {
                        jt.update("insert into rm_friendsong(startdate, totalindex, subindex, targettype, targetnumber, songid, level, `key`, `special`) values" +
                                "(?,?,?,?,?,?,?,?,?)", new Object[]{sdf.format(c.getTime()), obj[0], obj[1], obj[2], obj[3], obj[4], obj[5], obj[6], obj[7]});
                    }
                }
                LOGGER.info("���Ѵ��ظ������");
                SendSMSUtils.sendSMS("58057113bb06414092b915a71b562978", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    /* ������д����Ƿ��и��£�������ݣ����ڸ����Ƿ���ȫһ�� */
    /* return: ��Ҫ���µļ�¼, int[old][new] */
    private List<int[][]> checkExact(List<String> keys, Map<String, List<int[]>> oldList, Map<String, List<int[]>> newList) {
        List<int[][]> ret = new ArrayList<int[][]>();
        for(int idx = 0; idx < keys.size(); idx++) {
            String key = keys.get(idx);
            List<int[]> oldWeek = oldList.get(key);
            List<int[]> newWeek = newList.get(key);
            boolean sameWeek = true;
            for(int i = 0; i < oldWeek.size(); i++) {
                int[] o = oldWeek.get(i);
                int[] n = newWeek.get(i);
                if(o[4] != n[4]) { //songid��һ��
                    LOGGER.info(key + "�ĵ�" + i + "�׸�����һ��:old=" + o[4] + ",new=" + n[4]);
                    sameWeek = false;
                    break;
                }
            }
            if(sameWeek) { //����һ�£����key/level/Ŀ����Ƿ����
                for(int i = 0; i < oldWeek.size(); i++) {
                    int[] o = oldWeek.get(i);
                    int[] n = newWeek.get(i);
                    boolean exact = true;
                    for(int j = 0; j < o.length; j++) {
                        if(o[j] != n[j]) {
                            LOGGER.info(key + "�ĵ�" + i + "�׸����Ĳ���" + j + "��һ��:old=" + o[j] + ",new=" + n[j]);
                            exact = false;
                            break;
                        }
                    }
                    if(!exact) {
                        ret.add(new int[][]{o,n});
                    } else {
                        keys.set(idx, null);
                    }
                }
            } else { //������һ�£���Ϊ�ǳ����µ�һ��

            }
        }
        return ret;
    }

    private Map<String, List<int[]>> getCurrentList(JdbcTemplate jt, List<String> keys) {
        Map<String, List<int[]>> ret = new HashMap<String, List<int[]>>();
        String sql = "select * from rm_friendsong where totalindex = ? and startdate = (select max(startdate) from rm_friendsong where totalindex = ?) order by subindex";
        String[] cols = new String[]{"totalindex", "subindex", "targettype", "targetnumber", "songid", "level", "key", "special"};
        for(String k : keys) {
            List<int[]> oldList = new ArrayList<int[]>();
            List<Map<String, Object>> list = jt.queryForList(sql, new Object[]{Integer.parseInt(k), Integer.parseInt(k)});
            for(Map<String, Object> obj : list) {
                int[] values = new int[cols.length];
                for(int i = 0; i < cols.length; i++) {
                    values[i] = ((Integer) obj.get(cols[i])).intValue();
                }
                oldList.add(values);
            }
            ret.put(k, oldList);
        }
        return ret;
    }

    private static List<List<int[]>> sortSaveList(Map<String, List<int[]>> list, List<String> keys) {
        List<List<int[]>> ret = new ArrayList<List<int[]>>();
        boolean start = false;
        int begin = -1;
        for(int i = 0; i < keys.size(); i++) {
            if(keys.get(i) == null) {
                start = true;
            } else if(start) {
                begin = i;
                break;
            } else {

            }
        }
        if(start && begin == -1) {
            begin = 0;
        }
        if(begin >= 0) {
            boolean end = false;
            for(int i = begin; i < keys.size(); i++) {
                if(keys.get(i) == null) {
                    end = true;
                    break;
                }
                ret.add(list.get(keys.get(i)));
            }
            if(!end) {
                for(int i = 0; i < begin; i++) {
                    if(keys.get(i) == null) {
                        end = true;
                        break;
                    }
                    ret.add(list.get(keys.get(i)));
                }
            }
        }
        return ret;
    }

    /* ���ȼ�����ת��Ϊ��ͨ���� */
    private static int songIdChange(int in) {
        switch(in) {
            case 107:return 253;
            case 109:return 262;
            case 147:return 251;
            case 153:return 252;
            case 165:return 260;
            case 174:return 257;
            case 176:return 261;
            case 177:return 259;
            case 181:return 254;
            case 197:return 255;
            case 202:return 258;
            case 205:return 256;
        }
        return in;
    }
}
