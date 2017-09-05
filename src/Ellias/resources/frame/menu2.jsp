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
    String parentPath = null;
    for(Map<String, Object> map : list) {
        String menuname = map.get("title").toString();
        System.out.println(menuname);
        String link = map.get("link") == null ? "" : map.get("link").toString();
        if(link == null || link.length() == 0) {
            parentPath = null;
        }
        boolean checkright = map.get("right") != null && ((Number)map.get("right")).intValue() > 0;
        List<String> menus = new ArrayList<String>();
        menus.add("菜单权限");
        if(parentPath != null) {
            menus.add(parentPath);
        }
        menus.add(menuname);
        parentPath = menuname;
        boolean hasRight = false;
        if(checkright) {
            int resourceId = getResourceId(resourceDao, menus);
            if(resourceId > 0 && empId > 0) {
                hasRight = rightDao.hasRight(resourceId, empId);
            }
        }
        System.out.println(menuname + "是否有权限：" + hasRight);
        if(hasRight) {
            if(link == null || link.length() == 0) {
%>
<tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%"
               style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
            <tr>
                <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                <td align="center" nowrap><b><%=menuname%></b></td>
                <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
            </tr>
        </table>
    </td>
</tr>
<%
            } else {
%>
<tr style="height:1%">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                onclick="show(this, '<%=link%>');">
            <tr>
                <td align="center" nowrap width="98%">
                    <%=menuname%>
                </td>
            </tr>
        </table>
    </td>
</tr>
<%
            }
        }
    }
%>
<table width="100%" style="height:100%" border="0" cellpadding="0" cellspacing="1">
<%--
    <tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
        <td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%"
                   style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
                <tr>
                    <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                    <td align="center" nowrap><b>消息</b></td>
                    <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr style="height:1%"><td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"onclick="show(this, '/msg/privateMsg.do?method=write');">
            <tr><td align="center" nowrap width="98%">
                    写消息
            </td></tr>
        </table>
    </td></tr>
    <tr style="height:1%"><td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"onclick="show(this, '/msg/privateMsg.do?method=myMsg&readflag=-1');">
            <tr><td align="center" nowrap width="98%">
                    我的消息
            </td></tr>
        </table>
    </td></tr>
    <sys:CheckMenuRightTag resourcePath="菜单权限/个人工作室">
        <tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%"
                       style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
                    <tr>
                        <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                        <td align="center" nowrap><b>个人工作室</b></td>
                        <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </sys:CheckMenuRightTag>
--%>
    <sys:CheckMenuRightTag resourcePath="菜单权限/欧冠足球">
        <tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%"
                       style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
                    <tr>
                        <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                        <td align="center" nowrap><b>欧冠足球</b></td>
                        <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/ogzq/main.jsp');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            主页面
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/ogzq/uplevel.jsp');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            提升实力
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/ogzq/monitor.jsp?show=1');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            球会战监控
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/ogzq/monitor.jsp');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            球会战查看
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </sys:CheckMenuRightTag>
    <sys:CheckMenuRightTag resourcePath="菜单权限/系统设置">
    <tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
        <td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%"
                   style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
                <tr>
                    <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                    <td align="center" nowrap><b>系统设置</b></td>
                    <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
                </tr>
            </table>
        </td>
    </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/position.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            岗位查询
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/employee.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            员工查询
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/resource.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            资源查询
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/right.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            权限查询
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/right.do?method=table');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            权限表
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/workflow/workflow.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            工作流
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/workflow/task.do?method=myTask');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            我的任务
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/setting.do?method=list');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            参数设置
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/aliyun/buckets.jsp');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            OSS
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr style="height:1%">
            <td>
                <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                        onclick="show(this, '/sys/exportdb.jsp');">
                    <tr>
                        <td align="center" nowrap width="98%">
                            数据库导出
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </sys:CheckMenuRightTag>
    <tr style="height:1%">
        <td>
            <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                    onclick="show(this, '/login.do?method=logout');">
                <tr>
                    <td align="center" nowrap width="98%">
                        注销
                    </td>
                </tr>
            </table>
        </td>
    </tr>
<tr style="height:17px;cursor:pointer;" onclick="updateShow(this);">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%"
               style="height:100%;background-image:url(../images/panelbar/sep_bg.gif)">
            <tr>
                <td width="1"><img src="../images/panelbar/sep_begin.gif" border="0"/></td>
                <td align="center" nowrap><b>节奏大师</b></td>
                <td width="1"><img src="../images/panelbar/sep_end.gif" border="0"/></td>
            </tr>
        </table>
    </td>
</tr>
<tr style="height:1%">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                onclick="show(this, '/rm/weekstage.jsp?my');">
            <tr>
                <td align="center" nowrap width="98%">
                    好友闯关
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr style="height:1%">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                onclick="show(this, '/rm/songStatus.jsp');">
            <tr>
                <td align="center" nowrap width="98%">
                    歌曲统计
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr style="height:1%">
    <td>
        <table border="0" cellpadding="0" cellspacing="0" width="100%" class="BaseMenuItem"
                onclick="show(this, '/rm/songScore.jsp');">
            <tr>
                <td align="center" nowrap width="98%">
                    分数查询
                </td>
            </tr>
        </table>
    </td>
</tr>
    <tr>
        <td></td>
    </tr>
</table>
</body>
</html>