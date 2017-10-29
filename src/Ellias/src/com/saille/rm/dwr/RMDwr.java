package com.saille.rm.dwr;

import com.saille.rm.util.ImdUtils;
import com.saille.sys.Setting;
import org.apache.log4j.Logger;
import servlet.GlobalContext;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import com.saille.util.UtilFunctions;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-3-31
 * Time: 9:56:22
 * To change this template use File | Settings | File Templates.
 */
public class RMDwr {
    private final static Logger LOGGER = Logger.getLogger(RMDwr.class);
    public void fullKeyDetail() {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        String dir = Setting.getSettingString("RM_PATH") + "zizhi" + File.separator;
        List<Map<String, Object>> list = jt.queryForList("select id, songid, `key`, `level` from rm_customsongimd where" +
                                                                 " k0 is null or" +
                                                                 " k1 is null or" +
                                                                 " k2 is null or" +
                                                                 " k21 is null or" +
                                                                 " k22 is null or" +
                                                                 " k61 is null or" +
                                                                 " k62 is null or" +
                                                                 " ka1 is null or" +
                                                                 " ka2 is null or" +
                                                                 " k3 is null or" +
                                                                 " ka0 is null or" +
                                                                 " ka3 is null");
        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            int id = ((Number)m.get("id")).intValue();
            int songId = ((Number)m.get("songid")).intValue();
            int key = ((Number)m.get("key")).intValue();
            int level = ((Number)m.get("level")).intValue();
            String lv = "";
            switch(level) {
                case 1:lv = "ez";break;
                case 2:lv = "nm";break;
                case 3:lv = "hd";break;
                default:throw new RuntimeException("未知难度：" + level);
            }
            String filename = songId + "_" + key + "k_" + lv + ".imd";
            File f = new File(dir + songId + File.separator + filename);
            LOGGER.info(i + "/" + list.size() + ":" + filename);
            if(!f.exists()) {
                LOGGER.warn(f.getPath() + "不存在");
                continue;
            }
            byte[] bytes = new byte[(int)f.length()];
            try {
                FileInputStream fis = new FileInputStream(f);
                fis.read(bytes);
                fis.close();
                Map<Integer, Integer> map = new HashMap<Integer, Integer>();
                ImdUtils.getTotalKeys(bytes, map);
                Object[] params = new Object[13];
                params[0] = map.get(0);
                params[1] = map.get(1);
                params[2] = map.get(2);
                params[3] = map.get(33);
                params[4] = map.get(4);
                params[5] = map.get(97);
                params[6] = map.get(98);
                params[7] = map.get(161);
                params[8] = map.get(162);
                params[9] = map.get(3);
                params[10] = map.get(160);
                params[11] = map.get(163);
                params[12] = id;
                for(int j = 0; j < params.length; j++) {
                    if(params[j] == null) {
                        params[j] = 0;
                    }
                }
                jt.update("update rm_customsongimd set k0 = ?, k1 = ?, k2 = ?, k21 = ?, k22 = ?, k61 = ?, k62 = ?," +
                                  "ka1 = ?, ka2 = ?, k3 = ?, ka0 = ?, ka3 = ? where id = ?", params);
            } catch(Exception ex) {
                ex.printStackTrace();
                break;
            }
        }
    }
    public Object[] getSongdetailById(int songId) {
        try {
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList("select * from rm_song where songid = ?", new Object[]{songId});
            Map<String, Object> m = list.get(0);
            Object[] ret = new Object[2];
            Map<String, String> ret0 = new HashMap<String, String>();
            ret0.put("songname", m.get("songname").toString());
            ret0.put("songid", m.get("songid").toString());
            ret0.put("songauthor", m.get("author").toString());
            ret0.put("songpath", m.get("path").toString());
            ret0.put("songbpm", m.get("bpm").toString());
            ret0.put("songlength", m.get("length").toString());
            ret0.put("songhas", m.get("has").toString());
            ret[0] = ret0;

            String[][] scores = new String[9][11];
            DecimalFormat df = new DecimalFormat("0");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            list = jt.queryForList("select b.totalkey, a.hasrole, a.key, a.level, a.score, a.updatetime from rm_songscore a join rm_songkey b on a.songid = b.songid and a.key = b.key and a.level = b.level and a.removetag = 0 and a.songid = ?", new Object[]{songId});
            for(int i = 0; i < list.size(); i++) {
                m = list.get(i);
                int totalkey = (Integer)m.get("totalkey");
                int has = (Integer)m.get("hasrole");
                int key = (Integer)m.get("key");
                int level = (Integer)m.get("level");
                int score = (Integer)m.get("score");
                double full = totalkey * 600 - 22740;
                double sss = ((double)(totalkey * 600 - 22740)) * 0.995;
                double ss = ((double)(totalkey * 600 - 22740)) * 0.975;
                double s = ((double)(totalkey * 600 - 22740)) * 0.95;
                scores[(level - 1) * 3 + key - 4][0] = totalkey + "";
                scores[(level - 1) * 3 + key - 4][1] = df.format(full) + "";
                scores[(level - 1) * 3 + key - 4][2] = df.format(sss);
                scores[(level - 1) * 3 + key - 4][3] = df.format(ss);
                scores[(level - 1) * 3 + key - 4][4] = df.format(s);

                if(score == 0) {
                    continue;
                }
                scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8)] = score + "";
                if(score == full) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "FULL";
                } else if(score >= sss) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "SSS";
                } else if(score >= ss) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "SS";
                } else if(score >= s) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "S";
                } else if(score >= full * 0.9) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "A";
                } else if(score >= full * 0.8) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "B";
                } else if(score >= full * 0.6) {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "C";
                } else {
                    scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 1] = "D";
                }
                scores[(level - 1) * 3 + key - 4][(has == 1 ? 5 : 8) + 2] = sdf.format((Date) m.get("updatetime"));
            }
            ret[1] = scores;
            return ret;
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public String getSongdetail(int i, int j) {
//全部歌曲
//2	三有	已打总数
//3	三有	未打
//4	三有	FULL
//5	三有	SSS
//6	三有	SS
//7	三有	OTHER
//8	三无	已打总数
//9	三无	未打
//10	三无	FULL
//11	三无	SSS
//12	三无	SS
//13	三无	OTHER
//已有歌曲
//15	三有	已打总数
//16	三有	未打
//17	三有	FULL
//18	三有	SSS
//19	三有	SS
//20	三有	OTHER
//21	三无	已打总数
//22	三无	未打
//23	三无	FULL
//24	三无	SSS
//25	三无	SS
//26	三无	OTHER
        try {
            String sql = "select a.songid, c.songname, a.key, a.level, b.hasrole, a.fullscore, b.score from rm_songkey a left join rm_songscore b on a.songid = b.songid and " +
                    "a.key = b.key and a.level = b.level and b.removetag = 0";
            sql += " join rm_song c on a.songid = c.songid where 1=1";
            if(j < 2 || j > 26 || j == 14) {
                return "";
            }
            List<Object> params = new ArrayList<Object>();
            if(i < 9) {
                int key = i % 3 + 4;
                int level = i / 3 + 1;
                sql += " and a.key = ? and a.level = ?";
                params.add(key);
                params.add(level);
            } else {
                if(i >= 9 && i < 12) {
                    int level = i - 8;
                    sql += " and a.level = ?";
                    params.add(level);
                } else if(i >= 12 && i < 15) {
                    int key = i - 8;
                    sql += " and a.key = ?";
                    params.add(key);
                } else if(i == 15) {

                } else {
                    return "";
                }
            }
            if(j > 14) {
                sql += " and c.has = 1";
                j = j - 13;
            }
            if(j <= 7) {
                sql += " and b.hasrole = 1";
            } else {
                sql += " and b.hasrole = 0";
                j = j - 6;
            }
            switch(j) {
                case 2:
                    sql += " and b.score > 0";
                    break;
                case 3:
                    sql += " and b.score = 0";
                    break;
                case 4:
                    sql += " and b.score = a.fullscore";
                    break;
                case 5:
                    sql += " and b.score < a.fullscore and b.score >= (a.fullscore * 0.995)";
                    break;
                case 6:
                    sql += " and b.score < (a.fullscore * 0.995) and b.score >= (a.fullscore * 0.975)";
                    break;
                case 7:
                    sql += " and b.score > 0 and b.score < (a.fullscore * 0.975)";
                    break;
            }
            sql += " ORDER BY (SCORE / FULLSCORE) DESC, SCORE DESC";
            System.out.print("params = ");
            for(Object o : params) {
                System.out.print(o + ",");
            }
            System.out.println("\nsql=\n" + sql);
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            List<Map<String, Object>> list = jt.queryForList(sql, params.toArray());
            List<String[]> tables = new ArrayList<String[]>();
            tables.add(new String[]{"ID", "名称", "键数", "难度", "角色", "满分", "得分", "评价", "差距", "百分比"});
            for(Map<String, Object> m : list) {
                int songid = ((Integer) m.get("songid")).intValue();
                String songname = m.get("songname").toString();
                int key = ((Integer) m.get("key")).intValue();
                int level = ((Integer) m.get("level")).intValue();
                int hasrole = 0;
                if(m.get("hasrole") != null) {
                    hasrole = ((Integer) m.get("hasrole")).intValue();
                }
                int fullscore = ((Integer) m.get("fullscore")).intValue();
                int score = 0;
                if(m.get("score") != null) {
                    score = ((Integer) m.get("score")).intValue();
                }
                String[] obj = new String[10];
                obj[0] = songid + "";
                obj[1] = songname;
                obj[2] = key + "";
                obj[3] = level == 1 ? "EZ" : (level == 2 ? "NM" : "HD");
                obj[4] = hasrole > 0 ? "有" : "";
                obj[5] = fullscore + "";
                obj[6] = score == 0 ? "" : (score + "");
                if(score == 0) {
                    obj[7] = "";
                }else if(score == fullscore) {
                    obj[7] = "FULL";
                } else if(score * 1000 > fullscore * 995) {
                    obj[7] = "SSS";
                } else if(score * 1000 > fullscore * 975) {
                    obj[7] = "SS";
                } else if(score * 1000 > fullscore * 950) {
                    obj[7] = "S";
                } else if(score * 1000 > fullscore * 900) {
                    obj[7] = "A";
                } else if(score * 1000 > fullscore * 800) {
                    obj[7] = "B";
                } else if(score * 1000 > fullscore * 600) {
                    obj[7] = "C";
                } else {
                    obj[7] = "D";
                }
                obj[8] = fullscore - score + "";
                obj[9] = new DecimalFormat("00.00%").format((score + 0d) / fullscore);
                tables.add(obj);
            }
            return UtilFunctions.convertList2Table(tables, new boolean[]{true, false, true, false, false, true, true, false, true, false});
        } catch(Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public List<Object> getSongByMd5(String md5, int rowindex) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Map<String, Object>> list = jt.queryForList("select name, author, length from rm_customsong where md5 = ?", new Object[]{md5});
        List<Object> ret = new ArrayList<Object>();
        ret.add(rowindex);
        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> m = list.get(i);
            ret.add(m);
        }
        return ret;
    }

    public Map<String, Object> getImdByMd5(String mp3md5, String imdmd5, int rowindex) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Map<String, Object>> list = jt.queryForList("select a.name, a.author, a.length, b.key, b.level, b.difficulty, b.totalkey, b.songid, b.id from rm_customsong a, rm_customsongimd b where a.id = b.songid and a.md5 = ? and b.imdmd5 = ?", new Object[]{mp3md5, imdmd5});
        if(list.size() > 1) {
            throw new RuntimeException("出现重复IMD:mp3 md5=[" + mp3md5 + "],imd md5=[" + imdmd5 + "]");
        }
        if(list.size() == 0) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("rowindex", rowindex);
            return m;
        }
        Map<String, Object> m = list.get(0);
        m.put("rowindex", rowindex);
        return m;
    }
}
