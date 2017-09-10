<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.saille.rm.util.RMUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-09-10
  Time: 19:52
  To change this template use File | Settings | File Templates.
  生成自制谱xml及对应文件
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>生成自制谱</title>
</head>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> list = jt.queryForList("select songid, songname, author, length, path from rm_song order by songname");
    List<String[]> guanpu = new ArrayList<String[]>();
    for(Map<String, Object> m : list) {
        String songid = String.valueOf(((Number)m.get("songid")).intValue());
        String songname = String.valueOf(m.get("songname"));
        String author = String.valueOf(m.get("author"));
        String length = String.valueOf(((Number)m.get("length")).intValue());
        String path = String.valueOf(m.get("path"));
        guanpu.add(new String[]{songid, songname, path, length, author});
    }
%>
<body>
<input type="text" id="guanpu"/>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <tr class="head">
        <th>来源</th>
        <th>ID</th>
        <th>歌曲</th>
        <th>PATH</th>
        <th>长度</th>
        <th>作者</th>
    </tr>
    <%
        for(int i = 0; i < guanpu.size(); i++) {
            String[] ss = guanpu.get(i);
    %>
    <tr class="row<%=i % 2 + 1%>">
        <td>官铺</td>
        <td><%=ss[0]%></td>
        <td><%=ss[1]%></td>
        <td><%=ss[2]%></td>
        <td><%=RMUtils.convertLength(Integer.parseInt(ss[3]))%></td>
        <td><%=ss[4]%></td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
