<%@ include file="/include/include.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" >
<HTML>
	<HEAD>
		<title>FrameLeft</title>
		<meta name="vs_defaultClientScript" content="JavaScript">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
		<script language="javascript" src="scripts/defines.jsp"></script>
		<script language="javascript">
		<!--
		function splitRedirect( frmName, targetUrl )
		{
			parent.splitLoad( frmName,targetUrl );
		}

		var objNav;

		function navigatorCreate(objNavigator, id)
		{
	    objNavigator.displaymode = "2";
			objNavigator.design = "1";
	    objNavigator.tabdesign = "1";
	    objNavigator.loadOnStartup = false;
			// now build the navigator
            <xn:panelItem/>
			objNavigator.create();

			objNav = objNavigator;
		}


        function check_frame()
        {
            if ( top.location == location )
            {
                document.location = 'start.jsp';
            }
        }
        check_frame();

        //-->
		</script>
	</HEAD>
	<body style="MARGIN: 0px">
		<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" id="Table1">
			<tr height="100%">
				<td>
					<iframe id="navigator" name="navleft" src="pinNavigator.html?id=navigator" marginheight="0" frameborder="0"
						tabIndex="0" align="top" marginWidth="0" width="100%" height="100%" scrolling="no"></iframe>
				</td>
			</tr>
		</table>
	</body>
</HTML>
