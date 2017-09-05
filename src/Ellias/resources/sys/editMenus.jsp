<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/09/05 0005
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>菜单管理</title>
</head>
<%!
    List<String> filterParams(Map<String, String> params, String key) {
        List<String> ret = new ArrayList<String>();
        for(String p : params.keySet()) {
            if(p.startsWith(key)) {
                ret.add(p);
            }
        }
        return ret;
    }
%>
<%
    String method = request.getMethod();
    if("POST".equals(method)) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        String changeright = request.getParameter("method");
        if("save".equals(changeright)) {
            boolean hasNewMenu = false;
            for(int i = -1; ; i--) { //处理新增菜单
                String menutitle = request.getParameter("menutitle" + i);
                if(menutitle == null) {
                    break;
                }
                hasNewMenu = true;
                String link = request.getParameter("link" + i);
                String right = request.getParameter("right" + i);
                int order = Integer.parseInt(request.getParameter("order" + i));
                jt.update("insert into sys_menu(title, link, `right`, `order`, removetag) values(?, ?, ?, ?, 0)", new Object[]{menutitle, link, StringUtils.isEmpty(right) ? 0 : 1, order});
            }
            List<String> links = filterParams(request.getParameterMap(), "link");
            for(String link : links) { //更新链接
                int id = Integer.parseInt(link.substring(4));
                if(id > 0) {
                    jt.update("update sys_menu set link = ? where id = ?", new Object[]{request.getParameter(link), id});
                }
            }
            if(!hasNewMenu) {
                String order = request.getParameter("order");
                if(order != null && order.length() > 0) {
                    String[] orders = order.split(",");
                    for(int i = 0; i < orders.length; i++) {
                        int id = Integer.parseInt(orders[i]);
                        jt.update("update sys_menu set `order` = ? where id = ?", new Object[]{i, id});
                    }
                }
            }
        } else {
            jt.update("update sys_menu set `right` = 1 - `right` where id = ?", new Object[]{Integer.parseInt(changeright)});
        }
        response.sendRedirect("editMenus.jsp");
    }
%>
<body>
<form action="" method="post">
    <input type="hidden" name="method" value="save"/>
<input type="hidden" name="order"/>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <tr class="head">
        <th>标题</th>
        <th>链接</th>
        <th>是否控制权限</th>
        <th>操作</th>
    </tr>
<%
    DataSource ds = (DataSource) GlobalContext.getContextBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> list = jt.queryForList("select * from sys_menu where removetag = 0 order by `order`");
    for(int i = 0; i < list.size(); i++) {
        Map<String, Object> m = list.get(i);
%>
    <tr class="row<%=i % 2 + 1%>" value="<%=m.get("id")%>">
        <td style="<%=StringUtils.isEmpty(String.valueOf(m.get("link"))) ? "font-weight: bold;color:red;" : ""%>">
            <%=StringUtils.isEmpty(String.valueOf(m.get("link"))) ? "" : "&nbsp;&nbsp;"%><%=m.get("title")%>
        </td>
        <td onclick="edit(this, <%=m.get("id")%>, '<%=m.get("link")%>', event);"><%=m.get("link")%></td>
        <td align="center">
            <input type="checkbox" <%="1".equals(String.valueOf(m.get("right"))) ? "checked" : ""%> name="right<%=m.get("id")%>" onclick="dochangeright(<%=m.get("id")%>)"/>
        </td>
        <td>
            <input type="button" onclick="moveup(this);" value="↑"/>
            <input type="button" onclick="movedown(this);" value="↓"/>
        </td>
    </tr>
<%
    }
%>
</table>
<input type="button" onclick="addrow()" value="增加"/>
<input type="submit" value="保存"/>
</form>
<script type="text/javascript">
    var id = -1;
    function addrow()
    {
        var table = document.getElementById('table');
        var row = table.insertRow(-1);
        row.className = 'row' + (table.rows.length % 2 + 1);
        var cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"hidden\" name=\"id" + id + "\" value=\"0\"/><input type=\"text\" name=\"menutitle" + id + "\" class='inputbox'/>" +
            "<input type=\"hidden\" name=\"order" + id + "\" value=\"" + (table.rows.length - 1) + "\"/>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"text\" name=\"link" + id + "\" size=\"40\" class='inputbox'>";
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"checkbox\" name=\"right" + id +"\" value=\"1\"/>";
        cell.align = 'center';
        cell = row.insertCell(-1);
        cell.innerHTML = "<input type=\"button\" onclick=\"moveup(this);\" value=\"↑\"/>\n<input type=\"button\" onclick=\"movedown(this);\" value=\"↓\"/>";
        id--;
    }
    function moveup(obj)
    {
        var table = document.getElementById('table');
        moveRow(table, obj.parentNode.parentNode.rowIndex, obj.parentNode.parentNode.rowIndex - 1);
        updateOrder();
        updateclass(table);
    }
    function movedown(obj)
    {
        var table = document.getElementById('table');
        moveRow(table, obj.parentNode.parentNode.rowIndex, obj.parentNode.parentNode.rowIndex + 1);
        updateOrder();
        updateclass(table);
    }
    function updateclass(table)
    {
        for(var i = 1; i < table.rows.length; i++)
        {
            table.rows[i].className = 'row' + (2 - i % 2);
        }
    }

    function updateOrder()
    {
        var table = document.getElementById('table');
        var value = '';
        for(var i = 1; i < table.rows.length; i++)
        {
            var v = table.rows[i].attributes['value'];
            if(v != null && v.value.length > 0)
            {
                if(value.length > 0)
                {
                    value = value + ",";
                }
                value = value + v.value;
            }
        }
        document.getElementsByName("order")[0].value = value;
    }

    function edit(obj, id, value, event) {
        if(event.target.tagName == 'INPUT') {
            return;
        }
        obj.innerHTML = "<input name=\"link" + id + "\" type='text' value='" + value + "' size='40' class='inputbox'/>";
    }

    function dochangeright(value)
    {
        document.getElementsByName("method")[0].value = value;
        document.forms[0].submit();

    }
</script>
</body>
</html>
