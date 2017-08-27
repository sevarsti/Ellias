<%@ page import="com.saille.rm.MrockSongClientAndroid" %>
<%@ page import="com.saille.rm.WeekMatch" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-12-25
  Time: 10:06:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
    <head><title>好友闯关</title></head>
    <%!
        private String safeGetMap(Map<String, String[]> map, String key, int index) {
            if(!map.containsKey(key)) {
                return "";
            }
            String[] strs = map.get(key);
            return strs[index];
        }

        private Map<String, String> getScoresTeamchallenge(List<Map<String, Object>> list) {
            List<int[]> songs = new ArrayList<int[]>();
            for(Map<String, Object> obj : list) {
                songs.add(new int[]{((Integer) obj.get("songid")).intValue(),
                        ((Integer) obj.get("key")).intValue(),
                        ((Integer) obj.get("level")).intValue()});
            }
            return getScores(songs);
        }

        private Map<String, String> getScoresWeek(List<WeekMatch> list) {
            List<int[]> songs = new ArrayList<int[]>();
            for(WeekMatch obj : list) {
                songs.add(new int[]{obj.getSongId(), obj.getKey(), obj.getLevel()});
            }
            return getScores(songs);
        }

        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        private Map<String, String> getScores(List<int[]> list) {
            Map<String, String> ret = new HashMap<String, String>();
            for(int i = 0; i < list.size(); i++) {
                int songid = list.get(i)[0];
                int key = list.get(i)[1];
                int level = list.get(i)[2];
                int totalKey = 0;
                try {
                    totalKey = ((Integer) ((Map)jt.queryForList("select key" + (key + 3 + ""+level) + " from rm_song where songid = ?", new Object[]{songid}).get(0)).get("key" + (key + 3 + ""+level))).intValue();
                } catch(Exception ex) {}
                List<Map> l = jt.queryForList("select score from rm_songscore where removetag = 0 and hasrole = 1 and songid = ? and `key` = ? and `level` = ?", new Object[]{songid, key + 3, level});
                int myScore = l.size() > 0 ? ((Integer) l.get(0).get("score")).intValue() : 0;
//                Map<String, Object> scores = (Map<String, Object>) jt.queryForList("select a.*, b.score from rm_song a left join rm_songscore b on a.songid = b.songid " +
//                        "where a.songid = ? and b.hasrole = 1 and b.removetag = 0 and b.key = ? and b.level = ?", new Object[]{songid, key + 3, level}).get(0);
//                int myScore = scores.containsKey("score") ? ((Integer)scores.get("score")).intValue() : 0;
//                int totalScore = ((Integer) scores.get("key" + (key + 3) + level)).intValue() * 600 - 22740;
                ret.put(songid + "_" + key + "_" + level, myScore + "/" + (totalKey == 0 ? 0 : (totalKey * 600 - 22740 - myScore)));
            }
            return ret;
        }

        private Map<String, String> loadSongs() {
            List<MrockSongClientAndroid> list = MrockSongClientAndroid.getFromUrl(true);
            Map<String, String> ret = new HashMap<String, String>();
            for(MrockSongClientAndroid obj : list) {
                String name = obj.getM_szSongName();
                if(name.indexOf("【限时】") >= 0) {
                    name = name.substring(name.indexOf("【限时】"));
                }
                ret.put(obj.getM_ushSongID() + "", name);
            }
            return ret;
        }

    %>
    <body onload="combineLimit();combineWeeks();">
    <%
        boolean loadMyScore = request.getParameter("my") != null;
        List<WeekMatch> weeklist = WeekMatch.load();
        Map<String, String> allSongs = loadSongs();
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Map<String, Object>> list = jt.queryForList("select a.*, b.key41, b.key42, b.key43, b.key51, b.key52, b.key53, b.key61, b.key62, b.key63, c.score from rm_teamchallenge a join rm_song b on a.songid = b.songid left join rm_songscore c on a.songid = c.songid and (a.key + 3 = c.key) and a.level = c.level and c.hasrole = 1 and c.removetag = 0 where enddate is null or enddate >= date_format(now(), '%Y%m%d') order by startdate desc");
        List<String[]> teamchallenges = new ArrayList<String[]>();
        for(Map<String, Object> obj : list) {
            String songId = ((Integer) obj.get("songid")).intValue() + "";
            String totalIndex = ((Integer) obj.get("totalIndex")).intValue() + "";
            String songName = allSongs.get(songId);
            String start = String.valueOf(obj.get("startdate"));
            String end = obj.get("enddate") == null ? "" : (obj.get("enddate").toString());
            int lv = ((Integer) obj.get("level")).intValue();
            String level = lv == 1 ? "EASY" : (lv == 2 ? "NORMAL" : "HARD");
            int k = ((Integer) obj.get("key")).intValue();
            String key = k == 1 ? "4 KEY" : (k == 2 ? "5 KEY" : "6 KEY");
            int targetType = ((Integer) obj.get("targettype")).intValue();
            int targetNumber = ((Integer) obj.get("targetnumber")).intValue();
            String target = WeekMatch.convertMission(targetType, targetNumber);
            int score = ((Integer) obj.get("score")).intValue();
            if(loadMyScore) {
                int full = Integer.parseInt(obj.get("key" + (k + 3) + lv).toString()) * 600 - 22740;
                teamchallenges.add(new String[]{start, end, totalIndex, songId, songName, level, key, target, score + "/" + (full - score)});
            } else {
                teamchallenges.add(new String[]{start, end, totalIndex, songId, songName, level, key, target});
            }
        }

        Map<String, String> myScores = null;
        if(loadMyScore) {
            myScores = getScoresWeek(weeklist);
        }

        /* 星值兑换 */
        list = jt.queryForList("select startdate from rm_starmall where startdate <= date_format(now(),'%Y%m%d') order by startdate desc limit 1");
        String startdate = list.get(0).get("startdate").toString();
        list = jt.queryForList("select a.*, b.has from rm_starmall a join rm_song b on a.type = b.songid where `type` > 3 and startdate >= ? order by startdate desc limit 10", new Object[]{startdate});
        List<String[]> starmallSongs = new ArrayList<String[]>();
        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            boolean has = map.get("has").toString().equals("1");
            startdate = map.get("startdate").toString();
            Object obj = map.get("enddate");
            String enddate = obj == null ? "" : obj.toString();
            String songname = loadMyScore ? (has ? ("<b>" + map.get("desc").toString() + "</b>") : map.get("desc").toString()) : map.get("desc").toString();
            starmallSongs.add(new String[]{startdate, enddate, songname});
        }

        /* 限时歌曲 */
        list = jt.queryForList("select startdate from rm_xianshisongs where startdate <= date_format(now(),'%Y%m%d') order by startdate desc limit 1");
        startdate = list.get(0).get("startdate").toString();
        list = jt.queryForList("select a.*, b.has from rm_xianshisongs a join rm_song b on a.songid = b.songid where startdate >= ? order by startdate desc, songid", new Object[]{startdate});
        List<String[]> xianshiSongs = new ArrayList<String[]>();
        List<String[]> myXianshiSongs = new ArrayList<String[]>();
        
        String prvStartdate = null;
        for(Map<String, Object> obj : list) {
            boolean has = obj.get("has").toString().equals("1");
            if(obj.get("startdate").toString().equals(prvStartdate)) {
                xianshiSongs.get(xianshiSongs.size() - 1)[2] += "<br/>" + obj.get("songname").toString();
            } else {
                prvStartdate = obj.get("startdate").toString();
                xianshiSongs.add(new String[]{obj.get("startdate").toString(), obj.get("enddate") == null ? "" : obj.get("enddate").toString(), obj.get("songname").toString()});
            }
            myXianshiSongs.add(new String[]{obj.get("startdate").toString(), obj.get("enddate") == null ? "" : obj.get("enddate").toString(), loadMyScore ? (has ? ("<b>" + obj.get("songname").toString() + "</b>") : obj.get("songname").toString()) : obj.get("songname").toString(), ((Integer) obj.get("songid")).intValue() + ""});
        }
        Map<String, String[]> xianshiScores = new HashMap<String, String[]>();
        if(loadMyScore) {
            for(String[] songs : myXianshiSongs) {
                int id = Integer.parseInt(songs[3]);
                list = jt.queryForList("select a.*, b.* from rm_songscore a join rm_song b on a.songid = b.songid where a.songid = ? and removetag = 0", new Object[]{id});
                for(Map<String, Object> m : list) {
                    int hasRole = Integer.parseInt(m.get("hasrole").toString());
                    int key = Integer.parseInt(m.get("key").toString());
                    int level = Integer.parseInt(m.get("level").toString());
                    int score = Integer.parseInt(m.get("score").toString());
                    int totalKey = Integer.parseInt(m.get("key" + key + level).toString());
                    int fullScore = totalKey * 600 - 22740;
                    double r = ((double) score) / ((double) fullScore);
                    String rank;
                    if(r == 1) {
                        rank = "FULL";
                    } else if(r >= 0.995) {
                        rank = "SSS";
                    } else if(r >= 0.975) {
                        rank = "SS";
                    } else if(r >= 0.95) {
                        rank = "S";
                    } else if(r >= 0.9) {
                        rank = "A";
                    } else if(r >= 0.8) {
                        rank = "B";
                    } else if(r >= 0.6) {
                        rank = "C";
                    } else {
                        rank = "D";
                    }
                    xianshiScores.put(id + "_" + key + "_" + level + "_" + hasRole, new String[]{score + "", rank});
                }
            }
        }

        //S周闯关
        List<String[]> weeks = new ArrayList<String[]>();
        list = jt.queryForList("select * from rm_weeks where enddate >= now() and removetag = 0 order by startdate desc, subindex");
        for(Map<String, Object> m : list) {
            startdate = m.get("startdate").toString();
            String enddate = m.get("enddate").toString();
            String level = m.get("level").toString();
            String name = m.get("name").toString();
            int targetType = Integer.parseInt(m.get("targettype").toString());
            int targetNumber = Integer.parseInt(m.get("targetnumber").toString());
            weeks.add(new String[]{startdate, enddate, level, name, WeekMatch.convertMission(targetType, targetNumber)});
        }

        String[] keys = new String[]{"4 KEY", "5 KEY", "6 KEY"};
        String[] levels = new String[]{"EASY", "NORMAL", "HARD"};

        String now = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    %>
    <table border="0" cellpadding="1" cellspacing="1">
        <tr valign="top" align="left">
            <td>
                <table border="0" bgcolor="black" cellpadding="1" cellspacing="1">
                    <%
                        for(int i = 0; i < 4; i++) {
                            String style;
                            if(weeklist.get(i * 20).getStartdate().compareTo(now) <= 0 && (weeklist.get(i * 20).getEnddate() == null || weeklist.get(i * 20).getEnddate().compareTo(now) >= 0)) {
                                style = "color:red;";
                            } else {
                                style = "";
                            }
                    %>
                    <tr class="head"<%=style%>>
                        <th colspan="<%=loadMyScore?"7":"6"%>"><%=weeklist.get(i * 20).getStartdate()%>~<%=weeklist.get(i * 20).getEnddate() == null ? "" : weeklist.get(i * 20).getEnddate()%></th>
                    </tr>
                    <tr class="head"<%=style%>>
                        <th>关数</th>
                        <th>歌曲ID</th>
                        <th>歌曲名称</th>
                        <th>难度</th>
                        <th>特殊效果</th>
                        <th>要求</th>
                        <%if(loadMyScore){%>
                        <th>分数</th>
                        <%}%>
                    </tr>
                    <%
                        for(int j = 0; j < 20; j++) {
                            WeekMatch obj = weeklist.get(i * 20 + j);
                            int has = 0;
                            try {
                                has = jt.queryForInt("select has from rm_song where songid = " + obj.getSongId());
                            } catch(Exception ex) {}
                            int level = obj.getLevel();
                            String levelStr;
                            if(level == 1) {
                                levelStr = "EZ";
                            } else if(level == 2) {
                                levelStr = "NM";
                            } else if(level == 3) {
                                levelStr = "HD";
                            } else {
                                levelStr = "unknown";
                            }
                    %>
                    <tr class="row<%=j % 2 + 1%>" style="<%=has == 1 ? "font-weight:bold;" : ""%><%=style%>">
                        <td><%=obj.getStage()%></td>
                        <td><%=obj.getSongId()%></td>
                        <td><%=allSongs.get(obj.getSongId()+"")%></td>
                        <td><%=(obj.getKey() + 3) + "&nbsp;" + levelStr%></td>
                        <td><%=WeekMatch.convertSpecial(obj.getSpecial())%></td>
                        <td><%=WeekMatch.convertMission(obj.getTargetType(), obj.getTargetNumber())%></td>
                        <%if(loadMyScore){%>
                        <td><%=myScores.get(obj.getSongId() + "_" + obj.getKey() + "_" + obj.getLevel())%></td>
                        <%}%>
                    </tr>
                    <%
                        }
                    %>
                    <%
                        }
                    %>
                </table>
            </td>
            <td>
                <table border="0" bgcolor="black" cellpadding="1" cellspacing="1">
                    <tr class="head">
                        <th colspan="<%=loadMyScore ? "9" : "8"%>">战队竞技</th>
                    </tr>
                    <tr class="head">
                        <th>开始</th>
                        <th>结束</th>
                        <th>总序号</th>
                        <th>ID</th>
                        <th>名称</th>
                        <th>难度</th>
                        <th>键数</th>
                        <th>要求</th>
                        <%if(loadMyScore){%>
                        <th>分数</th>
                        <%}%>
                    </tr>
                    <%
                        for(int i = 0; i < teamchallenges.size(); i++) {
                            String[] obj = teamchallenges.get(i);
                    %>
            <tr class="row<%=i % 2 + 1%>" <%=(obj[0].compareTo(now) <= 0 && (StringUtils.isBlank(obj[1]) || obj[1].compareTo(now) >= 0)) ? " style=\"color:red;\"" : ""%>>
                    <%
                            for(int j = 0; j < obj.length; j++) {
                    %>
                    <td><%=obj[j]%></td>
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
                <table border="0" bgcolor="black" cellpadding="1" cellspacing="1">
                    <tr class="head"><th colspan="3">星值兑换</th></tr>
                    <tr class="head">
                        <th>开始日期</th>
                        <th>结束日期</th>
                        <th>歌曲</th>
                    </tr>
                    <%
                        for(int i = 0; i < starmallSongs.size(); i++) {
                            String[] obj = starmallSongs.get(i);
                            String style;
                            if(obj[0].compareTo(now) <= 0 && (StringUtils.isBlank(obj[1]) || obj[1].compareTo(now) >= 0)) {
                                style = " style=\"color:red;\"";
                            } else {
                                style = "";
                            }
                    %>
                    <tr class="row<%=i % 2 + 1%>"<%=style%>>
                        <td><%=obj[0]%></td>
                        <td><%=obj[1]%></td>
                        <td><%=obj[2]%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <br/>
                <br/>
                <table border="0" bgcolor="black" cellpadding="1" cellspacing="1" id="limit">
                    <tr class="head"><th colspan="<%=loadMyScore ? "8" : "3"%>">限时歌曲</th></tr>
                    <%
                        if(loadMyScore) {
                    %>
                    <%
                            for(int i = 0; i < myXianshiSongs.size(); i++) {
                                String[] obj = myXianshiSongs.get(i);
                                String style;
                                if(obj[0].compareTo(now) <= 0 && (StringUtils.isBlank(obj[1]) || obj[1].compareTo(now) >= 0)) {
                                    style = " style=\"color:red;\"";
                                } else {
                                    style = "";
                                }
                                for(int j = 0; j < 9; j++) {
                    %>
                    <tr class="row<%=(i % 2 + j / 3) % 2 + 1%>" align="right"<%=style%>>
                        <td align="left"><%=obj[0] + "~" + obj[1]%></td>
                        <td align="left"><%=obj[2]%></td>
                        <td align="left"><%=levels[j / 3]%></td>
                        <td><%=keys[j % 3]%></td>
                        <td <%="FULL".equals(safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_1", 1)) ? "style=\"font-weight:bold;\"" : ""%>><%=safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_1", 0)%></td>
                        <td <%="FULL".equals(safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_1", 1)) ? "style=\"font-weight:bold;\"" : ""%>><%=safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_1", 1)%></td>
                        <td <%="FULL".equals(safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_0", 1)) ? "style=\"font-weight:bold;\"" : ""%>><%=safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_0", 0)%></td>
                        <td <%="FULL".equals(safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_0", 1)) ? "style=\"font-weight:bold;\"" : ""%>><%=safeGetMap(xianshiScores, obj[3] + "_" + (j % 3 + 4) + "_" + (j / 3 + 1) + "_0", 1)%></td>
                    </tr>
                    <%
                                }
                            }
                        } else {
                    %>
                    <tr class="head">
                        <th>开始日期</th>
                        <th>结束日期</th>
                        <th>歌曲</th>
                    </tr>
                    <%
                        for(int i = 0; i < xianshiSongs.size(); i++) {
                            String[] obj = xianshiSongs.get(i);
                            String style;
                            if(obj[0].compareTo(now) <= 0 && (obj[1].equals("") || obj[1].compareTo(now) >= 0)) {
                                style = " style=\"color:red;\"";
                            } else {
                                style = "";
                            }
                    %>
                    <tr class="row<%=i % 2 + 1%>"<%=style%>>
                        <td><%=obj[0]%></td>
                        <td><%=obj[1]%></td>
                        <td><%=obj[2]%></td>
                    </tr>
                    <%
                        }
                    %>
                    <%
                        }
                    %>
                </table>
                <br/>
                <br/>
                <table border="0" bgcolor="black" cellpadding="1" cellspacing="1" id="weeks">
                    <tr class="head">
                        <th>开始时间</th>
                        <th>结束时间</th>
                        <th>难度</th>
                        <th>名称</th>
                        <th>要求</th>
                    </tr>
                    <%
                        for(int i = 0; i < weeks.size(); i++) {
                            String[] week = weeks.get(i);
                            String style;
                            if(week[0].compareTo(nowtime) <= 0 && (week[1].compareTo(nowtime) >= 0)) {
                                style = " style=\"color:red;\"";
                            } else {
                                style = "";
                            }
                    %>
                    <tr class="row<%=i % 2 + 1%>"<%=style%>>
                        <td><%=week[0]%></td>
                        <td><%=week[1]%></td>
                        <td><%=week[2]%></td>
                        <td><%=week[3]%></td>
                        <td><%=week[4]%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
    </table>
    </body>
<script type="text/javascript">
    function combineLimit()
    {
        combineTable('limit');
    }
    function combineWeeks()
    {
        var table = document.getElementById('weeks');
        var startRow = 1, rowLength = 1;
        for(var i = 2; i < table.rows.length; i++)
        {
            if(table.rows[i].cells[0].innerHTML == table.rows[i - 1].cells[0].innerHTML &&
                    table.rows[i].cells[1].innerHTML == table.rows[i - 1].cells[1].innerHTML)
            {
                rowLength += 1;
            }
            else
            {
                table.rows[startRow].cells[0].rowSpan = rowLength;
                table.rows[startRow].cells[1].rowSpan = rowLength;
                table.rows[startRow].cells[2].rowSpan = rowLength;
                table.rows[startRow].cells[3].rowSpan = rowLength;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].deleteCell(3);
                    table.rows[j].deleteCell(2);
                    table.rows[j].deleteCell(1);
                    table.rows[j].deleteCell(0);
                }
                rowLength = 1;
                startRow = i;
            }
        }
        if(rowLength > 1)
        {
            table.rows[startRow].cells[0].rowSpan = rowLength;
            table.rows[startRow].cells[1].rowSpan = rowLength;
            table.rows[startRow].cells[2].rowSpan = rowLength;
            table.rows[startRow].cells[3].rowSpan = rowLength;
            for(var j = startRow + 1; j < startRow + rowLength; j++)
            {
                table.rows[j].deleteCell(3);
                table.rows[j].deleteCell(2);
                table.rows[j].deleteCell(1);
                table.rows[j].deleteCell(0);
            }
        }
    }
    function combineTable(tableId)
    {
        var table = document.getElementById(tableId);
        for(var cols = 2; cols >= 0; cols--) {
            var startRow = 1, rowLength = 1;
            for(var i = 2; i < table.rows.length; i++)
            {
                if(table.rows[i].cells[cols].innerHTML == table.rows[i - 1].cells[cols].innerHTML)
                {
                    rowLength += 1;
                }
                else
                {
                    table.rows[startRow].cells[cols].rowSpan = rowLength;
                    for(var j = startRow + 1; j < startRow + rowLength; j++)
                    {
                        table.rows[j].deleteCell(cols);
                    }
                    rowLength = 1;
                    startRow = i;
                }
            }
            if(rowLength > 1)
            {
                table.rows[startRow].cells[cols].rowSpan = rowLength;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].deleteCell(cols);
                }
            }
        }
    }
</script>
</html>