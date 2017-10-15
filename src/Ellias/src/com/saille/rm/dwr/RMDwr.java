package com.saille.rm.dwr;

import servlet.GlobalContext;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

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
//ȫ������
//2	����	�Ѵ�����
//3	����	δ��
//4	����	FULL
//5	����	SSS
//6	����	SS
//7	����	OTHER
//8	����	�Ѵ�����
//9	����	δ��
//10	����	FULL
//11	����	SSS
//12	����	SS
//13	����	OTHER
//���и���
//15	����	�Ѵ�����
//16	����	δ��
//17	����	FULL
//18	����	SSS
//19	����	SS
//20	����	OTHER
//21	����	�Ѵ�����
//22	����	δ��
//23	����	FULL
//24	����	SSS
//25	����	SS
//26	����	OTHER
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
            tables.add(new String[]{"ID", "����", "����", "�Ѷ�", "��ɫ", "����", "�÷�", "����", "���", "�ٷֱ�"});
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
                obj[4] = hasrole > 0 ? "��" : "";
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
}
