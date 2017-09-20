package com.saille.rm.loop;

import com.GlobalConstant;
import com.saille.rm.RMConstant;
import com.saille.sys.BaseThread;
import com.saille.util.CommonUtils;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.io.File;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.Date;

import servlet.GlobalContext;
import jxl.Workbook;
import jxl.Sheet;
import jxl.Cell;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-17
 * Time: 13:22:13
 * To change this template use File | Settings | File Templates.
 */
public class UpdateScoreThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(UpdateScoreThread.class);
    public static void main(String[] args) {
    }

    public int execute() {
//        if(CommonUtils.hasSystemProcess("EXCEL")) {
//            return 5;
//        }
        try {
            File f = new File(GlobalConstant.DISKPATH + "excel\\" + RMConstant.RM_EXCEL);

            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList("select strvalue, pattern from setting where setting = 'RM_SCORE_LASTTIME'");
            Object strvalue = list.get(0).get("strvalue");
            String pattern = list.get(0).get("pattern").toString();
            Date lasttime = strvalue == null ? null : new SimpleDateFormat(pattern).parse(strvalue.toString());
            if(lasttime != null && (lasttime.getTime() + 1000) >= f.lastModified()) {
                LOGGER.info("分数歌曲文件没有变化");
                return 0;
            }
            Workbook workbook = Workbook.getWorkbook(f);

            /* 更新歌曲 */
            Sheet sheet = workbook.getSheet("歌曲");
            int count = 0;
            for(int i = 2; i < sheet.getRows(); i++) {
                if(i % 100 == 0) {
                    System.out.println(i + "/" + sheet.getRows());
                }
                Cell[] cells = sheet.getRow(i);
                String songname = cells[0].getContents().trim();
                int songid = Integer.parseInt(cells[3].getContents().trim());
                String author = cells[1].getContents().trim();
                String path = cells[2].getContents().trim();
                Double bpm = Double.parseDouble(cells[4].getContents().trim());
                String[] lengthStr = cells[5].getContents().trim().split("\\:");
                int length = Integer.parseInt(lengthStr[0]) * 60 + Integer.parseInt(lengthStr[1]);
                int lv41 = Integer.parseInt(cells[6].getContents().trim());
                int key41 = Integer.parseInt(cells[7].getContents().trim());
                int lv42 = Integer.parseInt(cells[14].getContents().trim());
                int key42 = Integer.parseInt(cells[15].getContents().trim());
                int lv43 = Integer.parseInt(cells[22].getContents().trim());
                int key43 = Integer.parseInt(cells[23].getContents().trim());

                int lv51 = Integer.parseInt(cells[30].getContents().trim());
                int key51 = Integer.parseInt(cells[31].getContents().trim());
                int lv52 = Integer.parseInt(cells[38].getContents().trim());
                int key52 = Integer.parseInt(cells[39].getContents().trim());
                int lv53 = Integer.parseInt(cells[46].getContents().trim());
                int key53 = Integer.parseInt(cells[47].getContents().trim());

                int lv61 = Integer.parseInt(cells[54].getContents().trim());
                int key61 = Integer.parseInt(cells[55].getContents().trim());
                int lv62 = Integer.parseInt(cells[62].getContents().trim());
                int key62 = Integer.parseInt(cells[63].getContents().trim());
                int lv63 = Integer.parseInt(cells[70].getContents().trim());
                int key63 = Integer.parseInt(cells[71].getContents().trim());
                String pinyin = cells[78].getContents().trim();
                int has = cells[80].getContents().trim().length() > 0 ? 1 : 0;

                List<Map<String, Object>> songs = jt.queryForList("select * from rm_song where songid = ?", new Object[]{songid});
                boolean needUpdate = false;
                if(songs.size() == 0) { //需要新增
                    needUpdate = true;
                } else {
                    Map<String, Object> map = songs.get(0);
                    if((!map.get("songname").equals(songname)) ||
                            (Integer.parseInt(map.get("songId").toString()) != songid) ||
                            (!map.get("author").equals(author)) ||
                            (!map.get("path").equals(path)) ||
                            (Double.parseDouble(map.get("bpm").toString()) != bpm) ||
                            (Integer.parseInt(map.get("length").toString()) != length) ||
                            (Integer.parseInt(map.get("lv41").toString()) != lv41) ||
                            (Integer.parseInt(map.get("key41").toString()) != key41) ||
                            (Integer.parseInt(map.get("lv42").toString()) != lv42) ||
                            (Integer.parseInt(map.get("key42").toString()) != key42) ||
                            (Integer.parseInt(map.get("lv43").toString()) != lv43) ||
                            (Integer.parseInt(map.get("key43").toString()) != key43) ||
                            (Integer.parseInt(map.get("lv51").toString()) != lv51) ||
                            (Integer.parseInt(map.get("key51").toString()) != key51) ||
                            (Integer.parseInt(map.get("lv52").toString()) != lv52) ||
                            (Integer.parseInt(map.get("key52").toString()) != key52) ||
                            (Integer.parseInt(map.get("lv53").toString()) != lv53) ||
                            (Integer.parseInt(map.get("key53").toString()) != key53) ||
                            (Integer.parseInt(map.get("lv61").toString()) != lv61) ||
                            (Integer.parseInt(map.get("key61").toString()) != key61) ||
                            (Integer.parseInt(map.get("lv62").toString()) != lv62) ||
                            (Integer.parseInt(map.get("key62").toString()) != key62) ||
                            (Integer.parseInt(map.get("lv63").toString()) != lv63) ||
                            (Integer.parseInt(map.get("key63").toString()) != key63) ||
                            (!map.get("pinyin").equals(pinyin)) ||
                            (Integer.parseInt(map.get("has").toString()) != has)) {
                        needUpdate = true;
                        jt.update("delete from rm_song where songid = ?", new Object[]{songid});
                    }
                }
                if(needUpdate) {
                    count++;
                    jt.update("insert into rm_song(songname,songid,author,path,bpm,length,lv41,key41,lv42,key42,lv43,key43,lv51,key51,lv52,key52,lv53,key53,lv61,key61,lv62,key62,lv63,key63,pinyin, has) values" +
                            "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new Object[]{
                            songname, songid, author, path, bpm, length,
                            lv41, key41, lv42, key42, lv43, key43,
                            lv51, key51, lv52, key52, lv53, key53,
                            lv61, key61, lv62, key62, lv63, key63,
                            pinyin, has});
                    jt.update("delete from rm_songkey where songid = ?", new Object[]{songid});
                    int[] songlevels = new int[]{lv41, lv42, lv43, lv51, lv52, lv53, lv61, lv62, lv63};
                    int[] keys = new int[]{key41, key42, key43, key51, key52, key53, key61, key62, key63};
                    for(int j = 0; j < 9; j++) {
                        if(keys[j] > 0) {
                            jt.update("insert into rm_songkey(songid, songlevel, `key`, `level`, totalkey, fullscore) values(?,?,?,?,?,?)",
                                      new Object[]{songid, songlevels[j], j / 3 + 4, j % 3 + 1, keys[j], keys[j] * 600 - 22740});
                        }
                    }
                }
            }
            LOGGER.info("一共更新了" + count + "条歌曲记录");

            /* 更新分数 */
            sheet = workbook.getSheet("KEY统计");
            count = 0;
            for(int i = 1; i < sheet.getRows(); i++) {
                if(i % 1000 == 0) {
                    System.out.println(i + "/" + sheet.getRows());
                }
                Cell[] cells = sheet.getRow(i);
                int id = Integer.parseInt(cells[1].getContents());
                int key = Integer.parseInt(cells[3].getContents());
                String lv = cells[4].getContents();
                int level = lv.equals("EZ") ? 1 : (lv.equals("NM") ? 2 : 3);
                String hasScoreStr = cells[16].getContents();
                String noScoreStr = cells[18].getContents();
                int hasScore = StringUtils.isBlank(hasScoreStr) ? 0 : Integer.parseInt(hasScoreStr.trim());
                int noScore = StringUtils.isBlank(noScoreStr) ? 0 : Integer.parseInt(noScoreStr.trim());
                if(hasScore >= 0) {
                    int hasHighscore;
                    if(hasScore == 0) {
                        hasHighscore = jt.queryForInt("select count(1) from rm_songscore where songid = ? and `key` = ? and `level` = ? and hasrole = 1 and score = 0 and removetag = 0",
                                       new Object[]{id, key, level});
                    } else {
                        hasHighscore = jt.queryForInt("select count(1) from rm_songscore where songid = ? and `key` = ? and `level` = ? and hasrole = 1 and score >= ? and removetag = 0",
                                       new Object[]{id, key, level, hasScore});
                    }
                    if(hasHighscore == 0) {
                        jt.update("update rm_songscore set removetag = 1 where songid = ? and `key` = ? and `level` = ? and hasrole = 1",
                                  new Object[]{id, key, level});
                        jt.update("insert into rm_songscore(songid, `key`, `level`, hasrole, score, updatetime, removetag) values(?,?,?,1,?,now(),0)",
                                  new Object[]{id, key, level, hasScore});
                        count++;
                    }
                }
                if(noScore >= 0) {
                    int hasHighscore;
                    if(noScore == 0) {
                        hasHighscore = jt.queryForInt("select count(1) from rm_songscore where songid = ? and `key` = ? and `level` = ? and hasrole = 0 and score = 0 and removetag = 0",
                                       new Object[]{id, key, level});
                    } else {
                        hasHighscore = jt.queryForInt("select count(1) from rm_songscore where songid = ? and `key` = ? and `level` = ? and hasrole = 0 and score >= ? and removetag = 0",
                                       new Object[]{id, key, level, noScore});
                    }
                    if(hasHighscore == 0) {
                        jt.update("update rm_songscore set removetag = 1 where songid = ? and `key` = ? and `level` = ? and hasrole = 0",
                                  new Object[]{id, key, level});
                        jt.update("insert into rm_songscore(songid, `key`, `level`, hasrole, score, updatetime, removetag) values(?,?,?,0,?,now(),0)",
                                  new Object[]{id, key, level, noScore});
                        count++;
                    }
                }
            }
            LOGGER.info("一共更新了" + count + "条分数记录");

            jt.update("update setting set strvalue = ? where `group` = 'RM' and setting = 'RM_SCORE_LASTTIME'", new Object[]{new SimpleDateFormat(pattern).format(f.lastModified())});
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private void updateStatus(JdbcTemplate jt) {
//        +----------+---------+------+-----+---------+-------+
//        | Field    | Type    | Null | Key | Default | Extra |
//        +----------+---------+------+-----+---------+-------+
//        | pubdate  | int(11) | YES  |     | NULL    |       |
//        | keytype  | int(11) | YES  |     | NULL    |       |0=4e,1=5e,2=6e,3=4n,4=5n,5=6n,6=4h,7=5h,8=6h
//        | playtype | int(11) | YES  |     | NULL    |       |0=三有全部,1=三无全部,2=三有拥有,3=三无拥有
//        | total    | int(11) | YES  |     | NULL    |       |
//        | done     | int(11) | YES  |     | NULL    |       |
//        | undone   | int(11) | YES  |     | NULL    |       |
//        | full     | int(11) | YES  |     | NULL    |       |
//        | sss      | int(11) | YES  |     | NULL    |       |
//        | ss       | int(11) | YES  |     | NULL    |       |
//        | other    | int(11) | YES  |     | NULL    |       |
//        +----------+---------+------+-----+---------+-------+
        int pubdate = Integer.parseInt(new SimpleDateFormat("yyyyMMdd").format(new Date()));

        List<String> querySqls = new ArrayList<String>();
        querySqls.add(null);
        querySqls.add("select `key`, `level`, count(1) as c from rm_songkey group by `key`, `level`"); //歌曲总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 " + "and a.hasrole = 1 and score > 0 group by a.key, a.level"); //已打总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songkey a where exists(select 1 from rm_songscore where score = 0 and songid = a.songid and `key` = a.key and `level` = a.level and hasrole = 1 and removetag = 0) group by a.key, a.level");//未打
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and a.score = b.fullscore group by a.key, a.level"); //满分
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and a.score >= b.fullscore * 0.995 and a.score <> b.fullscore group by a.key, a.level"); //SSS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and a.score >= b.fullscore * 0.975 and a.score < b.fullscore * 0.995 group by a.key, a.level"); //SS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and a.score > 0 and a.score < b.fullscore * 0.975 group by a.key, a.level"); //OTHER
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and score > 0 group by a.key, a.level"); //已打总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songkey a where exists(select 1 from rm_songscore where score = 0 and songid = a.songid and `key` = a.key and `level` = a.level and hasrole = 0 and removetag = 0) group by a.key, a.level");//未打
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and a.score = b.fullscore group by a.key, a.level"); //满分
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and a.score >= b.fullscore * 0.995 and a.score <> b.fullscore group by a.key, a.level"); //SSS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and a.score >= b.fullscore * 0.975 and a.score < b.fullscore * 0.995 group by a.key, a.level"); //SS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and a.score > 0 and a.score < b.fullscore * 0.975 group by a.key, a.level"); //OTHER

        querySqls.add("select `key`, `level`, count(1) as c from rm_songkey a where exists(select 1 from rm_song where has = 1 and songid = a.songid) group by `key`, `level`"); //歌曲总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and score > 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) group by a.key, a.level"); //已打总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songkey a where exists(select 1 from rm_songscore where score = 0 and songid = a.songid and `key` = a.key and `level` = a.level and hasrole = 1 and removetag = 0) and exists(select 1 from rm_song where songid = a.songid and has = 1)" + "group by a.key, a.level");//未打
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score = b.fullscore group by a.key, a.level"); //满分
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score >= b.fullscore * 0.995 and a.score <> b.fullscore group by a.key, a.level"); //SSS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score >= b.fullscore * 0.975 and a.score < b.fullscore * 0.995 group by a.key, a.level"); //SS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 1 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score > 0 and a.score < b.fullscore * 0.975 group by a.key, a.level"); //OTHER
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and score > 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) group by a.key, a.level"); //已打总数
        querySqls.add("select a.key, a.level, count(1) as c from rm_songkey a where exists(select 1 from rm_songscore where score = 0 and songid = a.songid and `key` = a.key and `level` = a.level and hasrole = 0 and removetag = 0) and exists(select 1 from rm_song where songid = a.songid and has = 1)" + "group by a.key, a.level");//未打
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score = b.fullscore group by a.key, a.level"); //满分
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score >= b.fullscore * 0.995 and a.score <> b.fullscore group by a.key, a.level"); //SSS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score >= b.fullscore * 0.975 and a.score < b.fullscore * 0.995 group by a.key, a.level"); //SS
        querySqls.add("select a.key, a.level, count(1) as c from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.hasrole = 0 and exists(select 1 from rm_song where has = 1 and songid = a.songid) and a.score > 0 and a.score < b.fullscore * 0.975 group by a.key, a.level"); //OTHER

        List<int[]> results = new ArrayList<int[]>();
        for(int i = 0; i < 15; i++) {
            int[] s = new int[27];
            results.add(s);
        }
        for(int i = 0; i < querySqls.size(); i++) {
            System.out.println(i + "/" + querySqls.size());
            String sql = querySqls.get(i);
            if(sql == null) {
                continue;
            }
            List<Map<String, Object>> list = jt.queryForList(sql);
            for(Map<String, Object> map : list) {
                int key = ((Integer) map.get("key")).intValue();
                int level = ((Integer) map.get("level")).intValue();
                int count = ((Long) map.get("c")).intValue();
                results.get((level - 1) * 3 + key - 4)[i] = count;
            }
        }
        for(int i = 1; i < 27; i++) {
            results.get(9)[i] = results.get(0)[i] + results.get(1)[i] + results.get(2)[i];
            results.get(10)[i] = results.get(3)[i] + results.get(4)[i] + results.get(5)[i];
            results.get(11)[i] = results.get(6)[i] + results.get(7)[i] + results.get(8)[i];
            results.get(12)[i] = results.get(0)[i] + results.get(3)[i] + results.get(6)[i];
            results.get(13)[i] = results.get(1)[i] + results.get(4)[i] + results.get(7)[i];
            results.get(14)[i] = results.get(2)[i] + results.get(5)[i] + results.get(8)[i];
            results.get(15)[i] = results.get(0)[i] + results.get(1)[i] + results.get(2)[i] +
                    results.get(3)[i] + results.get(4)[i] + results.get(5)[i] +
                    results.get(6)[i] + results.get(7)[i] + results.get(8)[i];
        }
        for(int i = 0; i < results.size(); i++) {
            Object[] params = new Object[10];
            params[0] = pubdate;
            params[1] = i;
            params[2] = 0;
            params[3] = results.get(i)[1];
            for(int j = 4; j < 10; j++) {
                params[j] = results.get(i)[j - 2];
            }
            jt.update("insert into rm_songstatus(pubdate, keytype, playtype, total, done, undone, full, sss, ss, other) values(?,?,?,?,?,?,?,?,?,?)", params);
            params[2] = 1;
            for(int j = 4; j < 10; j++) {
                params[j] = results.get(i)[j + 4];
            }
            jt.update("insert into rm_songstatus(pubdate, keytype, playtype, total, done, undone, full, sss, ss, other) values(?,?,?,?,?,?,?,?,?,?)", params);
            params[2] = 2;
            params[3] = results.get(i)[14];
            for(int j = 4; j < 10; j++) {
                params[j] = results.get(i)[j + 11];
            }
            jt.update("insert into rm_songstatus(pubdate, keytype, playtype, total, done, undone, full, sss, ss, other) values(?,?,?,?,?,?,?,?,?,?)", params);
            params[2] = 3;
            for(int j = 4; j < 10; j++) {
                params[j] = results.get(i)[j + 17];
            }
            jt.update("insert into rm_songstatus(pubdate, keytype, playtype, total, done, undone, full, sss, ss, other) values(?,?,?,?,?,?,?,?,?,?)", params);

            double[][] scores = new double[10][3];
            scores[0][1] = jt.queryForLong("select sum(fullscore) from rm_songkey a where exists (select 1 from rm_songscore where score > 0 and hasrole = 1 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)");
            scores[0][2] = jt.queryForLong("select sum(fullscore) from rm_songkey a where exists (select 1 from rm_songscore where score > 0 and hasrole = 0 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)");
            scores[0][0] = scores[0][1] + scores[0][2];
            scores[1][1] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and hasrole = 1 and removetag = 0");
            scores[1][2] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and hasrole = 0 and removetag = 0");
            scores[1][0] = scores[1][1] + scores[1][2];
            scores[2][0] = scores[1][0] / scores[0][0];
            scores[2][1] = Double.parseDouble(scores[1][1]) / Double.parseDouble(scores[0][1]));
            scores[2][2] = Double.parseDouble(scores[1][2]) / Double.parseDouble(scores[0][2]));
            scores[3][0] = "99.70%";
            scores[3][1] = "99.90%";
            scores[3][2] = "99.50%";
            scores[4][0] = (long)Math.ceil(Double.parseDouble(scores[0][0]) * df.parse(scores[3][0]).doubleValue()) - Long.parseLong(scores[1][0]) + "";
            scores[4][1] = (long)Math.ceil(Double.parseDouble(scores[0][1]) * df.parse(scores[3][1]).doubleValue()) - Long.parseLong(scores[1][1]) + "";//Long.parseLong(scores[0][1]) - Long.parseLong(scores[1][1]) + "";
            scores[4][2] = (long)Math.ceil(Double.parseDouble(scores[0][2]) * df.parse(scores[3][2]).doubleValue()) - Long.parseLong(scores[1][2]) + "";

            scores[5][1] = jt.queryForLong("select sum(fullscore) from rm_songkey a where songid not in(329, 289, 280) and exists (select 1 from rm_songscore where score > 0 and hasrole = 1 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)") + "";
            scores[5][2] = jt.queryForLong("select sum(fullscore) from rm_songkey a where songid not in(329, 289, 280) and exists (select 1 from rm_songscore where score > 0 and hasrole = 0 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)") + "";
            scores[5][0] = Long.parseLong(scores[5][1]) + Long.parseLong(scores[5][2]) + "";
            scores[6][1] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and songid not in(329, 289, 280) and hasrole = 1 and removetag = 0") + "";
            scores[6][2] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and songid not in(329, 289, 280) and hasrole = 0 and removetag = 0") + "";
            scores[6][0] = Long.parseLong(scores[6][1]) + Long.parseLong(scores[6][2]) + "";
            scores[7][0] = df.format(Double.parseDouble(scores[6][0]) / Double.parseDouble(scores[5][0]));
            scores[7][1] = df.format(Double.parseDouble(scores[6][1]) / Double.parseDouble(scores[5][1]));
            scores[7][2] = df.format(Double.parseDouble(scores[6][2]) / Double.parseDouble(scores[5][2]));
            scores[8][0] = "99.75%";
            scores[8][1] = "99.95%";
            scores[8][2] = "99.50%";
            scores[9][0] = (long)Math.ceil(Double.parseDouble(scores[5][0]) * df.parse(scores[8][0]).doubleValue()) - Long.parseLong(scores[6][0]) + "";
            scores[9][1] = (long)Math.ceil(Double.parseDouble(scores[5][1]) * df.parse(scores[8][1]).doubleValue()) - Long.parseLong(scores[6][1]) + "";//Long.parseLong(scores[4][1]) - Long.parseLong(scores[5][1]) + "";
            scores[9][2] = (long)Math.ceil(Double.parseDouble(scores[5][2]) * df.parse(scores[8][2]).doubleValue()) - Long.parseLong(scores[6][2]) + "";
        }
    }
}
