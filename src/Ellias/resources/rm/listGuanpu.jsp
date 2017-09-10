<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="com.saille.rm.util.RMUtils" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-09-10
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>节奏大师官铺</title>
</head>
<%
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> list = jt.queryForList("select * from rm_song order by songname");
    List<String[]> guanpu = new ArrayList<String[]>();
    Map<String, Map<String, String>> maps = new HashMap<String, Map<String, String>>();
    for(Map<String, Object> m : list) {
        String songid = String.valueOf(((Number)m.get("songid")).intValue());
        String songname = String.valueOf(m.get("songname"));
        String path = String.valueOf(m.get("path"));
        String author = String.valueOf(m.get("author"));
        String bpm = String.valueOf(m.get("bpm"));
        String length = String.valueOf(((Number)m.get("length")).intValue());
        String has = String.valueOf(m.get("has")).equals("1") ? "是" : "";
        String hide = String.valueOf(m.get("hide")).equals("1") ? "是" : "";
        String isfree = String.valueOf(m.get("isfree")).equals("1") ? "是" : "";
        String ishide = String.valueOf(m.get("ishide")).equals("1") ? "是" : "";
        String isopen = String.valueOf(m.get("isopen")).equals("1") ? "是" : "";
        String isreward = String.valueOf(m.get("isreward")).equals("1") ? "是" : "";
        guanpu.add(new String[]{songid, songname, path, author, bpm, length, isfree, ishide, isreward, isopen, has, hide});
        Map<String, String> map = new HashMap<String, String>();
        map.put("pinyin", String.valueOf(m.get("pinyin")));
        map.put("has", String.valueOf(m.get("has")));
        map.put("hide", String.valueOf(m.get("hide")));
        map.put("isfree", String.valueOf(m.get("isfree")));
        map.put("ishide", String.valueOf(m.get("ishide")));
        map.put("isopen", String.valueOf(m.get("isopen")));
        map.put("isreward", String.valueOf(m.get("isreward")));
        maps.put(songid, map);
    }
%>
<body>
缩写：<input type="text" id="pinyin" onchange="filter();"/>
是否拥有：<select id="has" onchange="filter();"><option value="-1">全部</option><option value="1">是</option><option value="0">否</option></select>
是否免费：<select id="isfree" onchange="filter();"><option value="-1">全部</option><option value="1">是</option><option value="0">否</option></select>
是否隐藏：<select id="ishide" onchange="filter();"><option value="-1">全部</option><option value="1">是</option><option value="0">否</option></select>
是否奖励：<select id="isreward" onchange="filter();"><option value="-1">全部</option><option value="1">是</option><option value="0">否</option></select>
是否开放：<select id="isopen" onchange="filter();"><option value="-1">全部</option><option value="1">是</option><option value="0">否</option></select>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <tr class="head">
        <th>ID</th>
        <th>歌曲</th>
        <th>PATH</th>
        <th>作者</th>
        <th>BPM</th>
        <th>长度</th>
        <th>免费歌曲</th>
        <th>隐藏歌曲</th>
        <th>奖励歌曲</th>
        <th>开放</th>
        <th>是否拥有</th>
        <th>是否隐藏</th>
    </tr>
    <%
        for(int i = 0; i < guanpu.size(); i++) {
            String[] ss = guanpu.get(i);
            StringBuilder sb = new StringBuilder();
            Map<String, String> m = maps.get(ss[0]);
            for(String key : m.keySet()) {
                sb.append(" ").append(key).append("=\"").append(m.get(key)).append("\"");
            }
    %>
    <tr class="row<%=i % 2 + 1%>"<%=sb.toString()%>>
        <td><%=ss[0]%></td>
        <td><%=ss[1]%></td>
        <td><%=ss[2]%></td>
        <td><%=ss[2]%></td>
        <td><%=ss[4]%></td>
        <td><%=RMUtils.convertLength(Integer.parseInt(ss[5]))%></td>
        <td><%=ss[6]%></td>
        <td><%=ss[7]%></td>
        <td><%=ss[8]%></td>
        <td><%=ss[9]%></td>
        <td><%=ss[10]%></td>
        <td><%=ss[11]%></td>
    </tr>
    <%
        }
    %>
</table>
<script type="text/javascript">
    function filter()
    {
        var pinyin = document.getElementById('pinyin');
        var has = document.getElementById('has');
        has = has.options[has.selectedIndex].value;
        var isfree = document.getElementById('isfree');
        isfree = isfree.options[isfree.selectedIndex].value;
        var ishide = document.getElementById('ishide');
        ishide = ishide.options[ishide.selectedIndex].value;
        var isreward = document.getElementById('isreward');
        isreward = isreward.options[isreward.selectedIndex].value;
        var isopen = document.getElementById('isopen');
        isopen = isopen.options[isopen.selectedIndex].value;

        var table = document.getElementById('table');
        var rowclass = 1;
        for(var i = 1; i < table.rows.length; i++)
        {
            if(pinyin.length > 0 && table.row[i].attributes['pinyin'].value.indexOf(pinyin) < 0)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            if(has >= 0 && parseInt(table.rows[i].attributes['has'].value) != has)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            if(isfree >= 0 && parseInt(table.rows[i].attributes['isfree'].value) != isfree)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            if(ishide >= 0 && parseInt(table.rows[i].attributes['ishide'].value) != ishide)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            if(isreward >= 0 && parseInt(table.rows[i].attributes['isreward'].value) != isreward)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            if(isopen >= 0 && parseInt(table.rows[i].attributes['isopen'].value) != isopen)
            {
                table.rows[i].style.display = 'none';
                continue;
            }
            table.rows[i].style.display = '';
            table.rows[i].className = 'row' + rowclass;
            rowclass = 3 - rowclass;
        }
    }
</script>
</body>
</html>
