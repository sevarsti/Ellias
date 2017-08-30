package com.saille.rm.loop;

import com.saille.rm.MrockSongClientAndroid;
import com.saille.rm.DownloadZipUtil;
import com.saille.sys.BaseThread;
import com.saille.util.SendSMSUtils;

import java.util.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;

import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;

import javax.sql.DataSource;

import servlet.GlobalContext;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-9
 * Time: 13:47:29
 * To change this template use File | Settings | File Templates.
 * 检查限时歌曲是否更新
 */
public class LimitSongThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(LimitSongThread.class);
    public static void main(String[] args) {
    }
    public int execute() {
        DownloadZipUtil.download();
        List<String> current = loadCurrent();
        List<String[]> newSongs = new ArrayList<String[]>();
        List<MrockSongClientAndroid> list = MrockSongClientAndroid.getFromUrl(false);

        for(MrockSongClientAndroid obj : list) {
            if(obj.getM_szSongName().indexOf("【限时】") >= 0) {
                newSongs.add(new String[]{obj.getM_ushSongID() + "", obj.getM_szSongName().substring(obj.getM_szSongName().indexOf("【限时】") + "【限时】".length())});
            }
        }
        boolean exact = true;
        for(String c : current) {
            boolean found = false;
            for(String[] n : newSongs) {
                if(n[0].equals(c)) {
                    found = true;
                    break;
                }
            }
            if(!found) {
                exact = false;
                break;
            }
        }
        if(exact) {
            for(String[] n : newSongs) {
                boolean found = false;
                for(String c : current) {
                    if(n[0].equals(c)) {
                        found = true;
                        break;
                    }
                }
                if(!found) {
                    exact = false;
                    break;
                }
            }
        }
        if(exact) {
            LOGGER.info("限时歌曲没有变化");
        } else {
            LOGGER.info("限时歌曲发生变化，需要更新");
            update(newSongs);
            SendSMSUtils.sendSMS("6fe2cc63746b4f7db93a9d3138dd8a50", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        }
        return 0;
    }

    private static void update(List<String[]> newSongs) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String today = sdf.format(c.getTime());
        c.add(Calendar.DATE, -1);
        String prvDay = sdf.format(c.getTime());
        jt.update("update rm_xianshisongs set enddate = ? where enddate is null", new Object[]{prvDay});
        for(String[] s : newSongs) {
            jt.update("insert into rm_xianshisongs(startdate, songid, songname) values(?,?,?)", new Object[]{today, s[0], s[1]});
        }
    }

    private static List<String> loadCurrent() {
        List<String> ret = new ArrayList<String>();
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Map> list = jt.queryForList("select * from rm_xianshisongs where startdate = (select max(startdate) from rm_xianshisongs)");
        if(list.size() > 0) {
            for(int i = 0; i < list.size(); i++) {
                Map<String, Object> obj = list.get(i);
                int id = ((Integer)obj.get("songid")).intValue();
                ret.add(id + "");
            }
        }
        return ret;
    }
}
