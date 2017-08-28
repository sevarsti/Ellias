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