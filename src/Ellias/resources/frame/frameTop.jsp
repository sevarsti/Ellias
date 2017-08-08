
<%@ include file="/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>FrameTop</title>
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
		<link href="/include/main.css" rel="stylesheet" type="text/css">
        <script language="javascript" src="scripts/defines.jsp"></script>
		<script language="javascript" src="scripts/scripts.js"></script>
        <script language="javascript">
    //-->
		</script>
	</HEAD>
	<body>
    欢迎使用<br/>
    <table width="100%" border="0">
        <tr>
            <script type="text/javascript">
                function changeColor()
                {
                    this.background = '/image/bg_red.jpg';
//                    alert('done');
                }
            </script>
            <a href="/book.do?method=listBook" target="main"><td id="td1" onmousemove="changeColor();" style="cursor:hand;background:url('/image/bg_blue.jpg');background-position:center;" align="center" valign="center" width="25%">场地预定</td></a>
            <td style="cursor:hand;" align="center" valign="center" width="25%"><a href="/book/addBook.jsp" target="main">商品销售</a></td>
            <td style="cursor:hand;" align="center" valign="center" width="25%"><a href="membermanage.jsp" target="main">会员管理</a></td>
            <td style="cursor:hand;" align="center" valign="center" width="25%"><a href="report.jsp" target="main">报表查询</a></td>
        </tr>
    </table>

            <%--<table width="100%" height="56" border="0" cellpadding="0" cellspacing="0" background='images/frameWork/frm_top_bg.gif'>--%>
				<%--<tr>--%>
					<%--<td width="1%"><img src='images/frameWork/frm_top_left.GIF' border="0"></td>--%>
					<%--<td width="1%"><img src='images/frameWork/frm_top_logo.gif' border="0"></td>--%>
					<%--<td width="100%">--%>
						<%----%>
					<%--</td>--%>
					<%--<td width="400" nowrap>--%>
						<%--<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">--%>
							<%--<tr height="28">--%>
								<%--<td colspan="3" align="right" nowrap>--%>
									<%--<!-- <span style="FONT-WEIGHT:bold"></span> <input name="textfield" type="text" class="inputbox" size="12">--%>
									<%--<input name="search" type="button" title="" class="search" onclick="goSearch();"> -->--%>
                                    <%--<!--<a href="http://req.cjsc.com.cn" target="_blank"><font color="red">需求管理平台</font></a>-->--%>
                                <%--</td>--%>
								<%--<td>&nbsp;</td>--%>
							<%--</tr>--%>
							<%--<tr height="22">--%>
								<%--<td><img src='images/frameWork/frm_top_msg_left.GIF'></td>--%>
								<%--<td width="67%" valign="bottom" align="center" nowrap background='images/frameWork//frm_top_msg_bg.GIF'>--%>
									<%--<span>欢迎您<b>${sessionContainer.currentEmployee.empName}</b>！ 您现在已登录到系统！--%>
									<%--</span>--%>
								<%--</td>--%>
								<%--<td width="33%" align=right background='images/frameWork/frm_top_msg_bg.GIF'>--%>
									<%--<table border="0" cellpadding="0" cellspacing="0">--%>
										<%--<tr height="5">--%>
											<%--<td nowrap></td>--%>
										<%--</tr>--%>
										<%--<tr>--%>
											<%--<td nowrap>--%>
												<%--<!-- <a href="/Forums/" target="_blank"><img src='Images/Framework//frm_top_forum.gif' border="0"></a> -->--%>
												<%--<!--<a href="javascript:modifyPwd();"><img alt="更改密码" src='images/frameWork/frm_top_change.gif' border="0"></a>-->--%>
												<%--<a href="javascript:exit()"><img src='images/frameWork/frm_top_exit.gif' border="0"></a>--%>
											<%--</td>--%>
										<%--</tr>--%>
									<%--</table>--%>
								<%--</td>--%>
								<%--<td><img src='images/frameWork/frm_top_msg_right.GIF'></td>--%>
							<%--</tr>--%>
							<%--<tr>--%>
								<%--<td></td>--%>
							<%--</tr>--%>
						<%--</table>--%>
					<%--</td>--%>
					<%--<td width="1%"><img src='images/frameWork/frm_top_right.GIF'></td>--%>
				<%--</tr>--%>
			<%--</table>--%>
	</body>
</HTML>
