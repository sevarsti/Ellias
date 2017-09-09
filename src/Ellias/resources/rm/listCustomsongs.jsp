<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-09-09
  Time: 00:44
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>自制谱查询</title>
</head>
<%!
    String convertLength(int in) {
        return (in / 60) + ":" + new DecimalFormat("00").format(in % 60);
    }
    String convertLevel(int in) {
        switch (in) {
            case 1:
                return "EZ";
            case 2:
                return "NM";
            case 3:
                return "HD";
            default:
                return "??";
        }
    }
%>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <tr class="head">
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false,0)">歌曲</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false,0)">路径</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false,0)">作者</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, false,0)">长度</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, false,0)">md5</td>
        <td>键数</td>
        <td>难度</td>
        <td>Rank</td>
        <td>Difficulty</td>
        <td>md5</td>
    </tr>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> songs = jt.queryForList("select a.*, b.key, b.level, b.rank, b.difficulty, b.imdmd5 from rm_customsong a left join rm_customsongimd b on a.id = b.songid order by a.name, a.author, b.key, b.level");
    for(int i = 0; i < songs.size(); i++) {
        Map<String, Object> m = songs.get(i);
%>
    <tr class="row<%=i % 2 + 1%>">
        <td><%=m.get("name")%></td>
        <td><%=m.get("path")%></td>
        <td><%=m.get("author")%></td>
        <td><%=convertLength(((Number)m.get("length")).intValue())%></td>
        <td><%=m.get("md5")%></td>
        <td><%=m.get("key")%></td>
        <td><%=convertLevel(((Number)m.get("level")).intValue())%></td>
        <td><%=m.get("rank")%></td>
        <td><%=m.get("difficulty")%></td>
        <td><%=m.get("imdmd5")%></td>
    </tr>
<%
    }
%>
<script type="text/javascript">
    var table = document.getElementById("table");
    for(var cols = 3; cols >= 0; cols--) {
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
</script>
</body>
</html>
