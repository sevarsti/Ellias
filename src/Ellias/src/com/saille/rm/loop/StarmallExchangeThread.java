package com.saille.rm.loop;

import com.saille.rm.DownloadZipUtil;
import com.saille.rm.RMConstant;
import com.saille.rm.StarmallExchange;
import com.saille.rm.WeekMatch;
import com.saille.rm.util.RMUtils;
import com.saille.sys.BaseThread;
import com.saille.sys.Setting;
import com.saille.util.SendSMSUtils;
import com.google.common.io.LittleEndianDataInputStream;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.text.SimpleDateFormat;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-13
 * Time: 14:42:38
 * To change this template use File | Settings | File Templates.
 * ÿ����ֵ�һ�
 //0-3:idx
 //4-7:����ţ�
 //8-11:�������
 //12-16:?
 //17-80:����
 //81-84:����ID/��Ʒ���ͣ�1=���ߣ�3=��ɫ
 //85-88:��Ҫ����
 //89-92:blank
 */
public class StarmallExchangeThread extends BaseThread {
    
    private final static Logger LOGGER = Logger.getLogger(StarmallExchangeThread.class);
    public static void main(String[] args) {
        try {
            //mrock.scorebuy_client.bin:��ʯ������
            //mrock_room_client:PK������
            File f = new File(Setting.getSettingString("RM_PATH") + "TableComBin\\mrock.mission_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //ÿ����Ԫ�ĳ���
            int count = dis.readInt(); //��������
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
                RMUtils.printBytes(bytes, 0, bytes.length, true);
            }
            dis.close();
            fis.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public int execute() {
        try {
            Map<String, List<StarmallExchange>> newList = new HashMap<String, List<StarmallExchange>>();
            Map<String, String> newListSong = new HashMap<String, String>();
            DownloadZipUtil.download();
            File f = new File(Setting.getSettingString("RM_PATH") + "TableComBin\\mrock_starmall_exchange_client.bin");
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
                StarmallExchange obj = new StarmallExchange();
                obj.setTotalIndex(RMUtils.getInt(bytes, 4, 4));
                obj.setSubIndex(RMUtils.getInt(bytes, 8, 4));
                obj.setName(RMUtils.getString(bytes, 17, 64));
                obj.setType(RMUtils.getInt(bytes, 81, 4));
                obj.setCost(RMUtils.getInt(bytes, 85, 4));
                if(!newList.containsKey(obj.getTotalIndex() + "")) {
                    newList.put(obj.getTotalIndex() + "", new ArrayList<StarmallExchange>());
                }
                if(obj.getType() > 3) {
                    newListSong.put(obj.getTotalIndex() + "", obj.getType() + "");
                }
                newList.get(obj.getTotalIndex() + "").add(obj);
            }
            dis.close();
            fis.close();

            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<String> keys = getKeys(newList);
            boolean allExact = true;
            for(int i = 0; i < keys.size(); i++) {
                String key = keys.get(i);
                List<Map> list = jt.queryForList("select `type` from rm_starmall where totalindex = ? and `type` > 3 order by startdate desc", new Object[]{key});
                boolean exact = false;
                if(list.size() > 0) {
                    String current = ((Integer)list.get(0).get("type")).intValue() + "";
                    if(current.equals(newListSong.get(key))) {
                        exact = true;
                    }
                }
                if(exact) {
                    newList.put(key, null);
                    keys.set(i, null);
                } else {
                    allExact = false;
                }
            }
            if(!allExact) {
                LOGGER.info("��ֵ�һ������仯��");
                List<List<StarmallExchange>> saveList = sortSaveList(newList, keys);
                List<Map> maxdates = jt.queryForList("select max(startdate) as startdate from rm_starmall");
                String maxdate = maxdates.get(0).get("startdate").toString();
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                c.setTime(sdf.parse(maxdate));
                for(List<StarmallExchange> list : saveList) {
                    c.add(Calendar.DATE, 6);
                    jt.update("update rm_starmall set enddate = ? where enddate is null", new Object[]{sdf.format(c.getTime())});
                    c.add(Calendar.DATE, 1);
                    for(StarmallExchange obj : list) {
                        LOGGER.info(obj.getName() + "/" + obj.getTotalIndex() + "/" + obj.getType());
                        jt.update("insert into rm_starmall(startdate, totalindex, subindex, `type`, cost, `desc`) values(?,?,?,?,?,?)",
                                  new Object[]{sdf.format(c.getTime()), obj.getTotalIndex(), obj.getSubIndex(), obj.getType(), obj.getCost(), obj.getName()});
                    }
//                    c.add(Calendar.DATE, 7);
                }
//                SendSMSUtils.sendSMS("a09c3a5bfec541b4b6078ea6f56de6b8", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                SendSMSUtils.sendSMS("�����ʦ��ֵ�����������£�����ʱ�䣺" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            } else {
                LOGGER.info("��ֵ�һ�û�б仯");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private static List<List<StarmallExchange>> sortSaveList(Map<String, List<StarmallExchange>> list, List<String> dates) {
        List<List<StarmallExchange>> ret = new ArrayList<List<StarmallExchange>>();
        boolean start = false;
        int begin = -1;
        for(int i = 0; i < dates.size(); i++) {
            if(dates.get(i) == null) {
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
            for(int i = begin; i < dates.size(); i++) {
                if(dates.get(i) == null) {
                    end = true;
                    break;
                }
                ret.add(list.get(dates.get(i)));
            }
            if(!end) {
                for(int i = 0; i < begin; i++) {
                    if(dates.get(i) == null) {
                        end = true;
                        break;
                    }
                    ret.add(list.get(dates.get(i)));
                }
            }
        }
        return ret;
    }

    private static List<String> getKeys(Map<String, List<StarmallExchange>> list) {
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
}
