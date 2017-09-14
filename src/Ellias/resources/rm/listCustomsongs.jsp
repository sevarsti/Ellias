<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.saille.rm.util.RMUtils" %>
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
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false,0);merge();">歌曲</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false,0);merge();">路径</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false,0);merge();">作者</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true,0);merge();">长度</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true,0);merge();">BPM</td>
        <td style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false,0);merge();">md5</td>
        <td>键数</td>
        <td>难度</td>
        <td>Rank</td>
        <td>Difficulty</td>
        <td>最大连击</td>
        <td>md5</td>
    </tr>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    DecimalFormat df = new DecimalFormat("0.#");
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> songs = jt.queryForList("select a.*, b.key, b.level, b.rank, b.difficulty, b.imdmd5, b.totalkey from rm_customsong a left join rm_customsongimd b on a.id = b.songid order by a.name, a.author, b.key, b.level");
    for(int i = 0; i < songs.size(); i++) {
        Map<String, Object> m = songs.get(i);
%>
    <tr class="row<%=i % 2 + 1%>">
        <td><%=m.get("name")%></td>
        <td><%=m.get("path")%></td>
        <td><%=m.get("author")%></td>
        <td value="<%=((Number) m.get("length")).intValue()%>"><%=RMUtils.convertLength(((Number) m.get("length")).intValue())%></td>
        <td value="<%=((Number)m.get("BPM")).doubleValue()%>"><%=df.format(((Number)m.get("BPM")).doubleValue())%></td>
        <td><%=m.get("md5")%></td>
        <td><%=m.get("key")%></td>
        <td><%=convertLevel(((Number)m.get("level")).intValue())%></td>
        <td><%=m.get("rank")%></td>
        <td><%=m.get("difficulty")%></td>
        <td><%=m.get("totalkey")%></td>
        <td><%=m.get("imdmd5")%></td>
    </tr>
<%
    }
%>
<script type="text/javascript">
    function merge()
    {
        var table = document.getElementById("table");
        var startRow = 1, rowLength = 1;
        var rowclass = 1;

        for(var i = 2; i < table.rows.length; i++)
        {
            if(table.rows[i].cells.length < 12)
            {
                for(var j = 5; j >= 0; j--)
                {
                    table.rows[i].insertCell(0);
                    table.rows[i].cells[0].innerHTML = table.rows[i - 1].cells[j].innerHTML;
                    var atts = table.rows[i - 1].cells[j].attributes;
                    for(var k = 0; k < atts.length; k++) {
                        table.rows[i].cells[0].setAttribute(atts[k].name, atts[k].value);
                    }
                    table.rows[startRow].cells[j].rowspan = table.rows[startRow].cells[j].rowspan - 1;
                }
            }
            else
            {
                startRow = i;
            }
        }
        startRow = 1, rowLength = 1;
        for(var i = 2; i < table.rows.length; i++)
        {
            if(table.rows[i].cells[1].innerHTML == table.rows[i - 1].cells[1].innerHTML && i != (table.rows.length - 1))
            {
                rowLength += 1;
            }
            else if(table.rows[i].cells[1].innerHTML == table.rows[i - 1].cells[1].innerHTML && i == (table.rows.length - 1))
            {
                table.rows[startRow].className = 'row' + rowclass;
                rowLength += 1;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].className = 'row' + rowclass;
                }
                for(var cols = 5; cols >= 0; cols--)
                {
                    table.rows[startRow].cells[cols].rowSpan = rowLength;
                    for(var j = startRow + 1; j < startRow + rowLength; j++)
                    {
                        table.rows[j].deleteCell(cols);
                    }
                }
                rowLength = 1;
                startRow = i;
                rowclass = 3 - rowclass;
            }
            else
            {
                table.rows[startRow].className = 'row' + rowclass;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].className = 'row' + rowclass;
                }
                for(var cols = 5; cols >= 0; cols--)
                {
                    table.rows[startRow].cells[cols].rowSpan = rowLength;
                    for(var j = startRow + 1; j < startRow + rowLength; j++)
                    {
                        table.rows[j].deleteCell(cols);
                    }
                }
                rowLength = 1;
                startRow = i;
                rowclass = 3 - rowclass;
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
    merge();
</script>
</body>
</html>
