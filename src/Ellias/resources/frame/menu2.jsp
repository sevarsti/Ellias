<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saille.sys.Employee" %>
<%@ page import="com.saille.sys.dao.RightDao" %>
<%@ page import="com.saille.sys.Resource" %>
<%@ page import="com.saille.sys.dao.ResourceDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.commons.lang.ArrayUtils" %>
<%@ page import="java.util.Collections" %>
<%@ include file="../include/include.jsp"%>
<html>
<head>
<title>系统管理</title>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
<script type="text/javascript" language="javascript">
    <!--
    function show(src, url) {
        var elements = document.getElementsByTagName('table');
        for(i = 0; i < elements.length; i++) {
            if(elements[i].className == 'BaseMenuItem' || elements[i].className == 'BaseMenuItemActive') {
                elements[i].className = 'BaseMenuItem';
            }
        }
        src.className = 'BaseMenuItemActive';
        top.right.location = url;
    }

    function updateShow(obj) {
        var currentstatus = obj.parentNode.rows[obj.rowIndex + 1].style.display;
        if(currentstatus == 'none') {
            currentstatus = '';
        } else {
            currentstatus = 'none';
        }
        for(var i = obj.rowIndex + 1; i < obj.parentNode.rows.length; i++) {
            if(obj.parentNode.rows[i].style.height == '1%') {
                obj.parentNode.rows[i].style.display = currentstatus;
            } else {
                break;
            }
        }
//        alert(this.style.height + ',' + this.parentNode.rows[this.rowIndex + 1].style.height)
    }
    //-->
</script>
</head>
<body>
<%!
    int getResourceId(ResourceDao dao, List<String> paths) {
        int parentId = Resource.ROOT_ID;
        for(int i = 0; i < paths.size(); i++) {
            String name = paths.get(i);
            Resource res = dao.findByNameParentId(name, parentId);
            if(res == null) {
                return 0;
            }
            parentId = res.getId();
        }
        return parentId;
    }
%>
<table width="100%" style="height:100%" border="0" cellpadding="0" cellspacing="1">
<%
    DataSource ds = (DataSource) GlobalContext.getContextBean("mysql_ds");
    RightDao rightDao = RightDao.getInstance();
    ResourceDao resourceDao = ResourceDao.getInstance();
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> list = jt.queryForList("select id, title, link, `right` from sys_menu where removetag = 0 order by `order`");
    Object empObj = request.getSession().getAttribute("employee");
    int empId = 0;
    if(empObj != null) {
        empId = ((Employee) empObj).getId();
    }
    List<String> parentPath = new ArrayList<String>();
    parentPath.add("菜单权限");
    boolean parentRight = true;
    for(Map<String, Object> map : list) {
        String menuname = map.get("title").toString();
        System.out.println(menuname);
        String link = map.get("link") == null ? "" : map.get("link").toString();
        if(link.length() == 0) {
            for(int i = parentPath.size() - 1; i > 0; i--) {
                parentPath.remove(i);
            }
            parentPath.add(menuname);
            boolean hasRight = true;
            if("1".equals(String.valueOf(map.get("right")))) {
                List<String> mypaths = new ArrayList<String>(parentPath);
                int resId = getResourceId(resourceDao, mypaths);
                if(resId == 0) {
                    hasRight = false;
                } else {
                    hasRight = rightDao.hasRight(resId, empId);
                }
                parentRight = hasRight;
            } else {
                parentRight = true;
            }
            if(hasRight) {
                out.print("<tr style=\"height:17px;cursor:pointer;\" onclick=\"updateShow(this);\">");
                out.print("<td>");
                out.print("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"");
                out.print("style=\"height:100%;background-image:url(../images/panelbar/sep_bg.gif)\">");
                out.print("<tr>");
                out.print("<td width=\"1\"><img src=\"../images/panelbar/sep_begin.gif\" border=\"0\"/></td>");
                out.print("<td align=\"center\" nowrap><b>" + menuname + "</b></td>");
                out.print("<td width=\"1\"><img src=\"../images/panelbar/sep_end.gif\" border=\"0\"/></td>");
                out.print("</tr>");
                out.print("</table>");
                out.print("</td>");
                out.print("</tr>");
            }
        } else {
            if(parentRight) {
                boolean hasRight = true;
                if("1".equals(String.valueOf(map.get("right")))) {
                    List<String> mypaths = new ArrayList<String>(parentPath);
                    mypaths.add(menuname);
                    int resId = getResourceId(resourceDao, mypaths);
                    if(resId == 0) {
                        hasRight = false;
                    } else {
                        hasRight = rightDao.hasRight(resId, empId);
                    }
                }
                if(hasRight) {
                    out.print("<tr style=\"height:1%\">");
                    out.print("<td>");
                    out.print("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"BaseMenuItem\"");
                    out.print("onclick=\"show(this, '" + link + "');\">");
                    out.print("<tr>");
                    out.print("<td align=\"center\" nowrap width=\"98%\">");
                    out.print(menuname);
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("</td>");
                    out.print("</tr>");
                }
            }
        }
    }
%>
    <tr>
        <td></td>
    </tr>
</table>
</body>
</html>