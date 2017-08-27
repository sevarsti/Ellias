<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-3-23
  Time: 14:05:01
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
        <%
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);

            List<String[]> results = new ArrayList<String[]>();
            for(int i = 0; i < 16; i++) {
                String[] s = new String[27];
                results.add(s);
            }
            results.get(0)[0] = "4 Easy";
            results.get(1)[0] = "5 Easy";
            results.get(2)[0] = "6 Easy";
            results.get(3)[0] = "4 Normal";
            results.get(4)[0] = "5 Normal";
            results.get(5)[0] = "6 Normal";
            results.get(6)[0] = "4 Hard";
            results.get(7)[0] = "5 Hard";
            results.get(8)[0] = "6 Hard";
            results.get(9)[0] = "Easy";
            results.get(10)[0] = "Normal";
            results.get(11)[0] = "Hard";
            results.get(12)[0] = "4 Key";
            results.get(13)[0] = "5 Key";
            results.get(14)[0] = "6 Key";
            results.get(15)[0] = "Total";

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
                    results.get((level - 1) * 3 + key - 4)[i] = count + "";
                }
            }
            for(int i = 1; i < 27; i++) {
                for(int j = 0; j < 9; j++) {
                    if(results.get(j)[i] == null) {
                        results.get(j)[i] = "0";
                    }
                }
                results.get(9)[i] = Integer.parseInt(results.get(0)[i]) + Integer.parseInt(results.get(1)[i]) + Integer.parseInt(results.get(2)[i]) + "";
                results.get(10)[i] = Integer.parseInt(results.get(3)[i]) + Integer.parseInt(results.get(4)[i]) + Integer.parseInt(results.get(5)[i]) + "";
                results.get(11)[i] = Integer.parseInt(results.get(6)[i]) + Integer.parseInt(results.get(7)[i]) + Integer.parseInt(results.get(8)[i]) + "";
                results.get(12)[i] = Integer.parseInt(results.get(0)[i]) + Integer.parseInt(results.get(3)[i]) + Integer.parseInt(results.get(6)[i]) + "";
                results.get(13)[i] = Integer.parseInt(results.get(1)[i]) + Integer.parseInt(results.get(4)[i]) + Integer.parseInt(results.get(7)[i]) + "";
                results.get(14)[i] = Integer.parseInt(results.get(2)[i]) + Integer.parseInt(results.get(5)[i]) + Integer.parseInt(results.get(8)[i]) + "";
                results.get(15)[i] = Integer.parseInt(results.get(0)[i]) + Integer.parseInt(results.get(1)[i]) + Integer.parseInt(results.get(2)[i]) +
                        Integer.parseInt(results.get(3)[i]) + Integer.parseInt(results.get(4)[i]) + Integer.parseInt(results.get(5)[i]) +
                        Integer.parseInt(results.get(6)[i]) + Integer.parseInt(results.get(7)[i]) + Integer.parseInt(results.get(8)[i]) + "";
            }

            results.get(9)[0] = "Easy";
            results.get(10)[0] = "Normal";
            results.get(11)[0] = "Hard";
            results.get(12)[0] = "4 Key";
            results.get(13)[0] = "5 Key";
            results.get(14)[0] = "6 Key";
            results.get(15)[0] = "Total";

            /* 统计总分 */
            DecimalFormat df = new DecimalFormat("#,##0.0000%");
            String[][] scores = new String[10][3];
            scores[0][1] = jt.queryForLong("select sum(fullscore) from rm_songkey a where exists (select 1 from rm_songscore where score > 0 and hasrole = 1 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)") + "";
            scores[0][2] = jt.queryForLong("select sum(fullscore) from rm_songkey a where exists (select 1 from rm_songscore where score > 0 and hasrole = 0 and songid = a.songid and `key` = a.key and `level` = a.level and removetag = 0)") + "";
            scores[0][0] = Long.parseLong(scores[0][1]) + Long.parseLong(scores[0][2]) + "";
            scores[1][1] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and hasrole = 1 and removetag = 0") + "";
            scores[1][2] = jt.queryForLong("select sum(score) from rm_songscore a where score > 0 and hasrole = 0 and removetag = 0") + "";
            scores[1][0] = Long.parseLong(scores[1][1]) + Long.parseLong(scores[1][2]) + "";
            scores[2][0] = df.format(Double.parseDouble(scores[1][0]) / Double.parseDouble(scores[0][0]));
            scores[2][1] = df.format(Double.parseDouble(scores[1][1]) / Double.parseDouble(scores[0][1]));
            scores[2][2] = df.format(Double.parseDouble(scores[1][2]) / Double.parseDouble(scores[0][2]));
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
        %>
        <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <th rowspan="3" style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">&nbsp;</th>
                <th colspan="13" style="border-right-style:solid;border-right-color:black;">总数</th>
                <th colspan="13" style="border-right-style:solid;border-right-color:black;">已有歌曲</th>
            </tr>
            <tr class="head">
                <th rowspan="2" style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">歌曲总数</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三有</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三无</th>
                <th rowspan="2" style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">歌曲总数</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三有</th>
                <th colspan="6" style="border-right-style:solid;border-right-color:black;">三无</th>
            </tr>
            <tr class="head">
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">已打总数</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">未打</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">FULL</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SSS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;">SS</th>
                <th style="border-bottom-style:solid;border-bottom-color:black;border-right-style:solid;border-right-color:black;">OTHER</th>
            </tr>
            <%
                for(int i = 0; i < 16; i++) {
                    String[] strs = results.get(i);
            %>
            <tr class="row<%=(i / 3) % 2 + 1%>">
                <%
                    for(int j = 0; j < strs.length; j++) {
                        boolean needRight = false;
                        if(j == 0 || j == 1 || j == 7 || j == 13 || j == 14 || j == 20 || j == 21 || j == 26) {
                            needRight = true;
                        }
                %>
                    <td onclick="showdetail(<%=i%>, <%=j%>);" style="cursor:pointer;<%=(i % 3 == 2 || i == 15) ? "border-bottom-style:solid;border-bottom-color:black;" : ""%><%=needRight ? "border-right-style:solid;border-right-color:black;" : ""%>" align="right"><%=strs[j] == null ? "0" : strs[j]%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
        <br/>
        <br/>
        <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <td>&nbsp;</td>
                <td>共计</td>
                <td>三有</td>
                <td>三无</td>
            </tr>
            <tr class="row1">
                <td class="fieldname">已打歌曲满分</td>
                <td><%=scores[0][0]%></td>
                <td><%=scores[0][1]%></td>
                <td><%=scores[0][2]%></td>
            </tr>
            <tr class="row2">
                <td class="fieldname">已打歌曲总分</td>
                <td><%=scores[1][0]%></td>
                <td><%=scores[1][1]%></td>
                <td><%=scores[1][2]%></td>
            </tr>
            <tr class="row1">
                <td class="fieldname">百分比</td>
                <td><%=scores[2][0]%></td>
                <td><%=scores[2][1]%></td>
                <td><%=scores[2][2]%></td>
            </tr>
            <tr class="row2">
                <td class="fieldname">目标</td>
                <td><%=scores[3][0]%></td>
                <td><%=scores[3][1]%></td>
                <td><%=scores[3][2]%></td>
            </tr>
            <tr class="row1">
                <td class="fieldname">差距</td>
                <td><%=scores[4][0]%></td>
                <td><%=scores[4][1]%></td>
                <td><%=scores[4][2]%></td>
            </tr>
            <tr class="head">
                <th colspan="4">去除极限</th>
            </tr>
            <tr class="row2">
                <td class="fieldname">已打歌曲满分</td>
                <td><%=scores[5][0]%></td>
                <td><%=scores[5][1]%></td>
                <td><%=scores[5][2]%></td>
            </tr>
            <tr class="row1">
                <td class="fieldname">已打歌曲总分</td>
                <td><%=scores[6][0]%></td>
                <td><%=scores[6][1]%></td>
                <td><%=scores[6][2]%></td>
            </tr>
            <tr class="row2">
                <td class="fieldname">百分比</td>
                <td><%=scores[7][0]%></td>
                <td><%=scores[7][1]%></td>
                <td><%=scores[7][2]%></td>
            </tr>
            <tr class="row1">
                <td class="fieldname">目标</td>
                <td><%=scores[8][0]%></td>
                <td><%=scores[8][1]%></td>
                <td><%=scores[8][2]%></td>
            </tr>
            <tr class="row2">
                <td class="fieldname">差距</td>
                <td><%=scores[9][0]%></td>
                <td><%=scores[9][1]%></td>
                <td><%=scores[9][2]%></td>
            </tr>
        </table>
        <br/>
        <br/>
  <span id="content"></span>
  </body>
<script type="text/javascript">
    function showdetail(i, j)
    {
        RMDwr.getSongdetail(i, j, after);
    }

    function after(obj)
    {
        document.getElementById('content').innerHTML = obj;
//        alert('done');
    }
</script>
</html>