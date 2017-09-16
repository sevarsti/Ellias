package com.saille.rm.loop;

import com.saille.rm.RMConstant;
import com.saille.sys.BaseThread;
import com.saille.rm.util.RMUtils;
import com.saille.rm.DownloadZipUtil;
import com.google.common.io.LittleEndianDataInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import com.saille.sys.Setting;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-22
 * Time: 14:16:11
 * To change this template use File | Settings | File Templates.
 */
public class ScoreChangeThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(ScoreChangeThread.class);
    public static void main(String[] args) {

    }

    protected int execute() {
        try {
            DownloadZipUtil.download();
            File f = new File(Setting.getSettingString("RM_PATH") + "TableComBin\\mrock.scoreexchange_client.bin");
            FileInputStream fis = new FileInputStream(f);
            LittleEndianDataInputStream dis = new LittleEndianDataInputStream(fis);
            dis.skip(8);
            int unitLength = dis.readInt(); //每个单元的长度
            int count = dis.readInt(); //歌曲数量
            dis.skip(120);
            byte[] bytes = new byte[unitLength];
            List<int[]> nowSongs = new ArrayList<int[]>();
            for(int i = 0; i < count; i++) {
                dis.read(bytes);
                int type = RMUtils.getInt(bytes, 4, 1);
                if(type != 3) {
                    continue;
                }
                int songId = RMUtils.getInt(bytes, 5, 4);
                int cost = RMUtils.getInt(bytes, 13, 4);
                nowSongs.add(new int[]{songId, cost});
//                RMUtils.printBytes(bytes, 0, bytes.length, true);
            }
            dis.close();
            fis.close();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar c = Calendar.getInstance();
            String today = sdf.format(c.getTime());
            c.add(Calendar.DATE, -1);
            String prvday = sdf.format(c.getTime());
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList("select * from rm_scorechange where enddate is null");
            List<int[]> oldSongs = new ArrayList<int[]>();
            for(Map<String, Object> map : list) {
                int songId = ((Integer) map.get("songid")).intValue();
                int cost = ((Integer) map.get("cost")).intValue();
                oldSongs.add(new int[]{songId, cost});
            }
            for(int i = oldSongs.size() - 1; i >= 0; i--) {
                int[] old = oldSongs.get(i);
                for(int j = 0; j < nowSongs.size(); j++) {
                    int[] now = nowSongs.get(j);
                    if(old[0] == now[0] && old[1] == now[1]) {
                        nowSongs.remove(j);
                        oldSongs.remove(i);
                        break;
                    }
                }
            }
            if(oldSongs.size() > 0) {
                LOGGER.info("需要删除旧的积分兑换");
                for(int i = 0; i < oldSongs.size(); i++) {
                    int songId = oldSongs.get(i)[0];
                    jt.update("update rm_scorechange set enddate = ? where enddate is null and songid = ?", new Object[]{prvday, songId});
                }
            }
            if(nowSongs.size() > 0) {
                LOGGER.info("有新的积分兑换");
                for(int i = 0; i < nowSongs.size(); i++) {
                    int songId = nowSongs.get(i)[0];
                    int cost = nowSongs.get(i)[1];
                    jt.update("insert into rm_scorechange(songid, cost, startdate) values(?,?,?)", new Object[]{songId, cost, today});
                }
            }
            LOGGER.info("积分兑换循环结束");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
