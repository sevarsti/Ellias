package com.saille.rm.loop;

import com.GlobalConstant;
import com.saille.rm.RMConstant;
import com.saille.sys.BaseThread;
import com.saille.rm.util.RMUtils;
import com.saille.rm.DownloadZipUtil;
import com.google.common.io.LittleEndianDataInputStream;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-14
 * Time: 11:24:52
 * To change this template use File | Settings | File Templates.
 * 战队竞技
 */
public class TeamchallengeThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(TeamchallengeThread.class);

    public static void main(String[] args) {
        try {
            File f = new File(GlobalConstant.DISKPATH + "rm\\TableComBin\\mrock_guild_song_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //每个单元的长度
            int count = dis.readInt(); //歌曲数量
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            List<int[]> newList = new ArrayList<int[]>();
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
                int totalIndex = RMUtils.getInt(bytes, 0, 4);
                int songId = RMUtils.getInt(bytes, 4, 4);
                int level = RMUtils.getInt(bytes, 8, 4);
                int key = RMUtils.getInt(bytes, 12, 4);
                int targetType = RMUtils.getInt(bytes, 16, 4);
                int targetNumber = RMUtils.getInt(bytes, 20, 4);
//                RMUtils.printBytes(bytes, 0, 4, false);
                System.out.print(totalIndex + "\t");
                System.out.print(songId + "\t");
//                RMUtils.printBytes(bytes, 4, 4, false);
                RMUtils.printBytes(bytes, 8, 4, true);
            }
            dis.close();
            fis.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    protected int execute() {
        try {
            DownloadZipUtil.download();
            File f = new File(RMConstant.RM_ROOT + "TableComBin\\mrock_guild_song_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //每个单元的长度
            int count = dis.readInt(); //歌曲数量
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            List<int[]> newList = new ArrayList<int[]>();
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
//                RMUtils.printBytes(bytes, 0, bytes.length, true);
                int totalIndex = RMUtils.getInt(bytes, 0, 4);
                int songId = RMUtils.getInt(bytes, 4, 4);
                int level = RMUtils.getInt(bytes, 8, 4);
                int key = RMUtils.getInt(bytes, 12, 4);
                int targetType = RMUtils.getInt(bytes, 16, 4);
                int targetNumber = RMUtils.getInt(bytes, 20, 4);
                newList.add(new int[]{totalIndex, songId, level, key, targetType, targetNumber});
            }
            dis.close();
            fis.close();

            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<int[]> currentList = new ArrayList<int[]>();
            List<Map> list = jt.queryForList("select * from rm_teamchallenge order by startdate desc limit " + newList.size());
            for(int i = 0; i < list.size(); i++) {
                Map<String, Object> m = list.get(i);
                int totalIndex = Integer.parseInt(m.get("totalIndex").toString());
                int songId = Integer.parseInt(m.get("songId").toString());
                int level = Integer.parseInt(m.get("level").toString());
                int key = Integer.parseInt(m.get("key").toString());
                int targettype = Integer.parseInt(m.get("targettype").toString());
                int targetnumber = Integer.parseInt(m.get("targetnumber").toString());
                currentList.add(new int[]{totalIndex, songId, level, key, targettype, targetnumber});
            }
            for(int i = 0; i < newList.size(); i++) {
                boolean found = false;
                for(int j = 0; j < currentList.size(); j++) {
                    int[] current = currentList.get(j);
                    if(current[0] == newList.get(i)[0] &&
                            current[1] == newList.get(i)[1] &&
                            current[2] == newList.get(i)[2] &&
                            current[3] == newList.get(i)[3] &&
                            current[4] == newList.get(i)[4] &&
                            current[5] == newList.get(i)[5]) {
                        found = true;
                        break;
                    }
                }
                if(found) {
                    newList.set(i, null);
                }
//                int has = jt.queryForInt("select count(1) from RM_TEAMCHALLENGE  where totalindex = ? and songid = ? and `level` = ? and `key` = ? and targettype = ? and targetnumber = ?",
//                               new Object[]{newList.get(i)[0], newList.get(i)[1], newList.get(i)[2], newList.get(i)[3], newList.get(i)[4], newList.get(i)[5]});
//                if(has > 0) {
//                    newList.set(i, null);
//                }
            }
            boolean needUpdate = false;
            for(int i = 0; i < newList.size(); i++) {
                if(newList.get(i) != null) {
                    needUpdate = true;
                    break;
                }
            }
            if(needUpdate) {
                LOGGER.info("战队竞技歌曲需要更新");
                Calendar c = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                list = jt.queryForList("select startdate from rm_teamchallenge where enddate is null");
                String startdate = list.get(0).get("startdate").toString();
                c.setTime(sdf.parse(startdate));
                newList = sortUpdateList(newList);
                for(int i = 0; i < newList.size(); i++) {
                    int[] obj = newList.get(i);
                    while(c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY && c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                        c.add(Calendar.DATE, 1);
                    }
                    jt.update("update rm_teamchallenge set enddate = ? where enddate is null", new Object[]{sdf.format(c.getTime())});
                    c.add(Calendar.DATE, 1);
                    jt.update("insert into rm_teamchallenge(startdate, totalindex, songid, level, `key`,targettype, targetnumber) values(?, ?,?,?,?,?,?)",
                              new Object[]{sdf.format(c.getTime()), obj[0], obj[1], obj[2], obj[3], obj[4], obj[5]});
                }
                LOGGER.info("战队竞技歌曲更新完毕");
            } else {
                LOGGER.info("战队竞技歌曲没有更新");
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private static List<int[]> sortUpdateList(List<int[]> list) {
        List<int[]> ret = new ArrayList<int[]>();
        boolean start = false;
        int begin = -1;
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i) == null) {
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
            for(int i = begin; i < list.size(); i++) {
                if(list.get(i) == null) {
                    end = true;
                    break;
                }
                ret.add(list.get(i));
            }
            if(!end) {
                for(int i = 0; i < begin; i++) {
                    if(list.get(i) == null) {
                        end = true;
                        break;
                    }
                    ret.add(list.get(i));
                }
            }
        }
        return ret;
    }

//    public void interrupt() {
//        System.out.println("interrupt");
//        super.interrupt();
//    }
//
//    public void destroy() {
//        System.out.println("destroy");
//        super.destroy();
//    }
}
