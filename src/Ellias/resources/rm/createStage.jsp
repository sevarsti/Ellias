<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="com.saille.core.dao.BaseJdbcDao" %>
<%@ page import="com.saille.sys.dao.EmployeeDao" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-10-25
  Time: 23:58
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>创建闯关</title>
</head>
<body>
<%!
    public int convertTargetnumber(String type, String number) {
        int t = Integer.parseInt(type);
        List<String> rate = new ArrayList<String>();
        rate.add("D");
        rate.add("C");
        rate.add("B");
        rate.add("A");
        rate.add("S");
        rate.add("SS");
        rate.add("SSS");
        switch (t) {
            case 7:
                return rate.indexOf(number) + 1;
            case 14:
            case 18:
                return 0;
            default:
                return Integer.parseInt(number);
        }
    }
%>
<%
    if("post".equalsIgnoreCase(request.getMethod())) {
        Connection conn = null;
        try {
            String name = request.getParameter("stagename");
            String[] songids = request.getParameterValues("songid");
            String[] imdids = request.getParameterValues("imdid");
            String[] targettypes = request.getParameterValues("targettype");
            String[] targetnumbers = request.getParameterValues("targetnumber");
            if(songids == null || imdids == null || targettypes == null || targetnumbers == null) {
                out.println("参数不正确");
                return;
            }
            if(songids.length != imdids.length || songids.length != targettypes.length || songids.length != targetnumbers.length) {
                out.println("参数不正确");
                return;
            }
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            conn = jt.getDataSource().getConnection();
            conn.setAutoCommit(false);
            BaseJdbcDao dao = (BaseJdbcDao) GlobalContext.getContextBean(EmployeeDao.class);
            int id = dao.getId("RM_CUSTOMSTAGE");
            PreparedStatement ps = conn.prepareStatement("insert into rm_customstage(id, stagename, removetag) values(?, ?, 0)");
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
            ps = conn.prepareStatement("insert into rm_customstagedetail(stageid, `index`, songid, imdid, targettype, targetnumber) values(?,?,?,?,?,?)");
            ps.setInt(1, id);
            for(int i = 0; i < songids.length; i++) {
                ps.setInt(2, i + 1);
                ps.setInt(3, Integer.parseInt(songids[i]));
                ps.setInt(4, Integer.parseInt(imdids[i]));
                ps.setInt(5, Integer.parseInt(targettypes[i]));
                ps.setInt(6, convertTargetnumber(targettypes[i], targetnumbers[i]));
                ps.executeUpdate();
            }
            conn.commit();
            out.println("done<br/>");
        } catch (Exception ex) {
            conn.rollback();
            ex.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (Exception ex) {}
        }
        return;
    }
    String select = "<select name=\"targettype\"><option value=\"3\">血量不低于%</option><option value=\"4\">血量不超过%</option><option value=\"5\">最高连击达到</option><option value=\"6\">最高连击不超过</option><option value=\"7\">成绩达到7SSS1D</option><option value=\"8\">MISS不少于</option><option value=\"9\">MISS不超过</option><option value=\"10\">GOOD不少于</option><option value=\"11\">GOOD不超过</option><option value=\"14\">歌曲达到All Combo</option><option value=\"15\">不死过关</option><option value=\"16\">分数达到</option><option value=\"17\">分数不超过</option><option value=\"18\">满血过关</option><option value=\"19\">Perfect达到%</option><option value=\"20\">Perfect不超过%</option><option value=\"21\">GOOD不少于%</option><option value=\"22\">GOOD不超过%</option><option value=\"23\">MISS不少于%</option><option value=\"24\">MISS不超过%</option><option value=\"25\">分数不低于%</option><option value=\"26\">分数不高于%</option><option value=\"27\">连击达到%</option><option value=\"28\">连击不超过%</option></select>";
%>
<form action="" method="post">
闯关名称：<input name="stagename" type="text" maxlength="50" size="50"/>
<table class="black" id="table">
    <tr class="head">
        <td>歌曲md5</td>
        <td>歌曲名称</td>
        <td>作者</td>
        <td>长度</td>
        <td>谱面md5</td>
        <td>键位</td>
        <td>难度</td>
        <td>键数</td>
        <td>等级</td>
        <td>要求</td>
        <td>数值</td>
        <td>操作</td>
    </tr>
    <tr class="row1">
        <td>
            <input type="hidden" name="songid"/>
            <input type="text" name="mp3md5" maxlength="32" size="33" onchange="updatesong(this);" class="inputbox"/>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            <input type="hidden" name="imdid"/>
            <input type="text" name="imdmd5" maxlength="32" size="33" onchange="updateimd(this);" class="inputbox"/>
        </td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td>
            <%=select%>
        </td>
        <td>
            <input type="text" name="targetnumber" size="8" maxlength="7"/>
        </td>
        <td>
            <input type="button" value="增加" onclick="addrow(this)"/>
            <input type="button" value="删除"/>
        </td>
    </tr>
</table>
    <input type="submit" value="保存"/>
</form>
</body>
<script type="text/javascript">
    function addrow(obj)
    {
        var row = document.getElementById("table").insertRow(obj.parentNode.parentNode.rowIndex + 1);
        var clazz = parseInt(obj.parentNode.parentNode.className.substr(3, 1), 10);

        for(var i = obj.parentNode.parentNode.rowIndex + 1; i < document.getElementById("table").rows.length; i++)
        {
            clazz = 3 - clazz;
            document.getElementById("table").rows[i].className = 'row' + clazz;
        }
        var cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"hidden\" name=\"songid\"/><input type=\"text\" name=\"mp3md5\" maxlength=\"32\" size=\"33\" onchange=\"updatesong(this);\" class=\"inputbox\"/>";
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"hidden\" name=\"imdid\"/><input type=\"text\" name=\"imdmd5\" maxlength=\"32\" size=\"33\" onchange=\"updateimd(this);\" class=\"inputbox\"/>";
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell = row.insertCell(-1);
        cell.innerHTML = "<%=select.replaceAll("\"", "\\\\\"")%>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"text\" name=\"targetnumber\" size=\"8\" maxlength=\"7\"/>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"button\" value=\"增加\" onclick=\"addrow(this)\"/>\n<input type=\"button\" value=\"删除\"/>"
    }
    function updatesong(obj)
    {
        var row = obj.parentNode.parentNode;
        var songmd5 = obj.value;
        RMDwr.getSongByMd5(songmd5, row.rowIndex, aftersong);
    }
    function aftersong(obj)
    {
        var rowindex = obj[0];
        var name = '', author = '', length = '';
        for(var i = 1; i < obj.length; i++)
        {
            if(i > 1)
            {
                name += "<br/>";
                author += "<br/>";
                length += "<br/>";
            }
            name += obj[i]['name'];
            author += obj[i]['author'];
            length += obj[i]['length'];
        }
        document.getElementById("table").rows[rowindex].cells[1].innerHTML = name;
        document.getElementById("table").rows[rowindex].cells[2].innerHTML = author;
        document.getElementById("table").rows[rowindex].cells[3].innerHTML = length;
    }

    function updateimd(obj)
    {
        var row = obj.parentNode.parentNode;
        var imdmd5 = obj.value;
        var mp3md5 = row.cells[0].getElementsByTagName("input")[1].value;
        RMDwr.getImdByMd5(mp3md5, imdmd5, row.rowIndex, afterimd);
    }

    function afterimd(map)
    {
        var rowindex = map['rowindex'];
        if(map['songid'] == undefined)
        {
            document.getElementById('table').rows[rowindex].cells[0].getElementsByTagName('input')[0].value = '';
            document.getElementById('table').rows[rowindex].cells[4].getElementsByTagName('input')[0].value = '';
            document.getElementById("table").rows[rowindex].cells[5].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[6].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[7].innerHTML = '';
            document.getElementById("table").rows[rowindex].cells[8].innerHTML = '';
            return;
        }
        document.getElementById('table').rows[rowindex].cells[0].getElementsByTagName('input')[0].value = map['songid'];
        document.getElementById('table').rows[rowindex].cells[4].getElementsByTagName('input')[0].value = map['id'];
        document.getElementById("table").rows[rowindex].cells[1].innerHTML = map['name'];
        document.getElementById("table").rows[rowindex].cells[2].innerHTML = map['author'];
        document.getElementById("table").rows[rowindex].cells[3].innerHTML = map['length'];
        document.getElementById("table").rows[rowindex].cells[5].innerHTML = map['key'];
        document.getElementById("table").rows[rowindex].cells[6].innerHTML = map['level'];
        document.getElementById("table").rows[rowindex].cells[7].innerHTML = map['totalkey'];
        document.getElementById("table").rows[rowindex].cells[8].innerHTML = map['difficulty'];
    }
</script>
</html>
