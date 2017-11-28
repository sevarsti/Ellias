<%@ page import="javax.sql.DataSource" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saille.rm.WeekMatch" %>
<%@ page import="java.math.BigDecimal" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/11/28 0028
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>自制闯关</title>
</head>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <tr class="head">
        <th>名称</th>
        <th>序号</th>
        <th>歌曲</th>
        <th>键位</th>
        <th>键数</th>
        <th>要求</th>
    </tr>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    String sql = "select a.stagename, b.index, b.targettype, b.targetnumber, c.name, c.length, c.bpm, c.md5, d.imdmd5, d.totalkey, d.key, d.level\n" +
            " from rm_customstage a join rm_customstagedetail b on a.id = b.stageid \n" +
            "join rm_customsong c on b.songid = c.id \n" +
            "join rm_customsongimd d on b.songid = d.songid and b.imdid = d.id";
    List<Map<String, Object>> list = jt.queryForList(sql);
    for(int i = 0; i < list.size(); i++) {
        Map<String, Object> map = list.get(i);
%>
    <tr class="row<%=i % 2 + 1%>">
        <td><%=map.get("stagename")%></td>
        <td><%=map.get("index")%></td>
        <td><%=map.get("name")%></td>
        <td><%=map.get("key")%></td>
        <td><%=map.get("totalkey")%></td>
        <td><%=WeekMatch.convertMission(((Integer)map.get("targettype")).intValue(), ((Integer)map.get("targetnumber")).intValue())%></td>
    </tr>
    <%
        }
    %>
</table>
<script type="text/javascript">
    function merge()
    {
        var table = document.getElementById("table");
        var startRow = 1, rowLength = 1;
        var rowclass = 1;

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
//    merge();
</script>
</body>
</html>
