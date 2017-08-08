package com.saille.rm.loop;

import com.saille.sys.BaseThread;
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
    private static UpdateScoreThread instance;
    private UpdateScoreThread(int interval){super(interval);}
    public static void main(String[] args) {
//        new UpdateScoreThread().execute();
    }

    public static UpdateScoreThread getInstance(int interval) {
        if(instance == null) {
            instance = new UpdateScoreThread(interval);
            instance.setDaemon(true);
        }
        return instance;
    }

    public int execute() {
        if(checkExistExcelThread()) {
            return 5;
        }
        try {
            File f = new File("D:\\excel\\节奏大师歌曲.xls");

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

    private boolean checkExistExcelThread() {
        try {
            ProcessBuilder pb = new ProcessBuilder();
//            pb = pb.command("tasklist", "/FI", "\"username", "eq", "ellias");
            pb = pb.command("tasklist");
            Process p = pb.start();
            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getInputStream()), Charset.forName("GB2312")));
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));
            String ostr;

            while((ostr = out.readLine()) != null) {
                if(ostr.indexOf("没有运行的任务匹配指定标准") >= 0) {
                    return true;
                }
                if(ostr.indexOf("EXCEL") >= 0) {
                    return true;
                }
            }
            String estr = err.readLine();
            if(estr != null) {
                System.out.println("\nError Info");
                System.out.println(estr);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
