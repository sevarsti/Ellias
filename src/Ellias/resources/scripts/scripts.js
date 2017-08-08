///////////////////////////////////////////////////////////////////////////////
//
//	本js中大部分函数都使用了AppRootPath这个变量，所以在开头检查一下
//
///////////////////////////////////////////////////////////////////////////////
try
{
	if (AppRootPath == "")
	{
		alert("Must define appRootPath! [" + document.location.pathname + "]");
	}
}
catch(ex)
{
	alert("Must include defines.aspx! [" + document.location.pathname + "]");
}

///////////////////////////////////////////////////////////////////////////////
//
//	当页面提交时，先禁止页面中的所有元素，以防止用户多次提交，造成错误。
//	<form id="Form1" method="post" runat="server" onsubmit="__Form_Submit__();">
//
///////////////////////////////////////////////////////////////////////////////
var __Disable_Elements_on_Submit__ = false; // true: 当页面提交时，先禁止页面中的所有元素, false: 不做任何处理
window.attachEvent("onload", __Form_Submit_BindHandler__);
document.writeln('<div id="__divCover__" style="position:absolute; z-index: 10000; display: none; left: 0; top: 0; width: 100%; height: 100%; background-color:#F0FFFF; filter:progid:DXImageTransform.Microsoft.Alpha(opacity=50)">');
document.writeln('</div>');
document.writeln('<table id="__tabMsg__" border="0" style="position:absolute; z-index: 10001; display: none; background-color: white;">');
document.writeln('<tr><td align="center" valign="middle">');
document.writeln('<img src="' + ImagePathForCommon + 'waiting.gif"><br>');
document.writeln('<b>处理中，请稍候... ...</b>');
document.writeln('</td></tr>');
document.writeln('</table>');
function __Form_Submit_BindHandler__()
{
	try
	{
		document.forms[0].attachEvent("onsubmit", __Form_Submit__);
	}
	catch (ex)
	{
	}
}
function __Form_Submit__()
{
	if (__Disable_Elements_on_Submit__)
	{
		var divCover = document.all("__divCover__");
		divCover.style.display = "";
		divCover.focus();

		var tabMsg = document.all("__tabMsg__");
		var w = 150;
		var h = 80;
		var t = document.body.scrollTop + (document.body.clientHeight - h) / 2 - 50;
		var l = document.body.scrollLeft + (document.body.clientWidth - w) / 2;
		tabMsg.style.top = t;
		tabMsg.style.left = l;
		tabMsg.style.width = w;
		tabMsg.style.height = h;
		tabMsg.style.display = "";

		//alert("a");
		setTimeout(__Form_Submit_DisableAllElement__, 1);
	}
}
function __Form_Submit_DisableAllElement__()
{
	//alert("b");
	var frm = document.forms[0];
	//alert(frm.all.length);
	for (i = 0; i < frm.elements.length; i ++)
	{
		//alert(frm.elements[i].name);
		frm.elements[i].disabled = true;
	}
}
///////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////
//
//	在页面中截获回车键<Enter>，并根据设置来决定变换为Tab、禁止Submit或什么都不做
//
///////////////////////////////////////////////////////////////////////////////
var __Filter_Enter_Type__ = 2; // 1: Enter变Tab, 2: 禁止Submit, 其它: 什么都不做
function __Filter_Enter__()
{
	//alert(event.srcElement.type);
	if (event.keyCode == 13 &&
		event.srcElement.type != "textarea" &&
		event.srcElement.type != "submit" &&
		event.srcElement.type != "reset" &&
		event.srcElement.type != "button")
	{
		//alert("Enter catched");
		if (__Filter_Enter_Type__ == 1)
		{
			event.keyCode = 9;	// 改为“Tab”键，跳转到后一个页面元素
		}
		else if (__Filter_Enter_Type__ == 2)
		{
			return false;	// 禁止SUBMIT FORM
		}
	}
}
document.onkeydown = __Filter_Enter__;

///////////////////////////////////////////////////////////////////////////////

function fPopUpCalendarDlg(ctrlobj) {

  showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
  showy = event.screenY - event.offsetY + 18; // + deltaY;
  newWINwidth = 210 + 4 + 18;

  retval = window.showModalDialog(AppRootPath + "calendar/CalendarDlg.htm", "", "dialogWidth:197px; dialogHeight:210px; dialogLeft:" + showx + "px; dialogTop:" + showy + "px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
  if( retval != null )
  {
		ctrlobj.value = retval;
  }
}

function fPopUpCalendarDlgWithTime(ctrlobj) {

  showx = event.screenX - event.offsetX - 4 - 210 ; // + deltaX;
  showy = event.screenY - event.offsetY + 18; // + deltaY;
  newWINwidth = 210 + 4 + 18;

  retval = window.showModalDialog(AppRootPath + "calendar/calendarWithTime.htm", "", "dialogWidth:197px; dialogHeight:230px; dialogLeft:" + showx + "px; dialogTop:" + showy + "px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );
  if( retval != null ) {
	ctrlobj.value = retval;
  }
}
//--------------------------------------------------------------------------
//
//		Open Window
//
//--------------------------------------------------------------------------
function ShowModalWindow(url, title, left, top, width, height, scrollbar, resizable)
{
	if (!title)	title = "";
	if (!left) left = 100;
	if (!top) top = 100;
	if (!width) width = 600;
	if (!height) height = 400;
	if (!scrollbar) scrollbar = "yes";
	if (!resizable) resizable = "no";

    var _url = url.replace(/\&/g, "%26");
    ret = window.showModalDialog(AppRootPath+"frameWrapper.jsp?pageLinker=" + _url, title,
		"dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogLeft:"+left+"px; dialogTop:"+top+"px; status:no; directories:no; scrollbars:"+scrollbar+"; resizable="+resizable+";" );
	return ret;
}

function ShowWindow(url, title, left, top, width, height, scrollbar, resizable)
{
	if (!title)	title = "";
	if (!left) left = 100;
	if (!top) top = 100;
	if (!width) width = 400;
	if (!height) height = 400;
	if (!scrollbar) scrollbar = "yes";
	if (!resizable) resizable = "no";        
    ret = window.open(AppRootPath+"frameWrapper.jsp?pageLinker=" + url, title,
		"width="+width+" height="+height+" left="+left+" top="+top+" status=yes directories=no scrollbars="+scrollbar+" resizable="+resizable );
	return ret;
}

function ShowModalessWindow(url, left, top, width, height, scrollbar, resizable, title)
{
	alert("this function is renamed: Modaless->Medeless");
	ShowModelessWindow(url, left, top, width, height, scrollbar, resizable, title)
}
function ShowModelessWindow(url, left, top, width, height, scrollbar, resizable, title)
{
	if (!title)	title = "";
	if (!left) left = 100;
	if (!top) top = 100;
	if (!width) width = 600;
	if (!height) height = 400;
	if (!scrollbar) scrollbar = "yes";
	if (!resizable) resizable = "no";

	ret = window.showModelessDialog(AppRootPath+"frameWrapper.jsp?pageLinker=" + url, title,
		"dialogWidth:"+width+"px; dialogHeight:"+height+"px; dialogLeft:"+left+"px; dialogTop:"+top+"px; status:no; directories:no; scrollbars:"+scrollbar+"; resizable="+resizable+";" );
	return ret;
}

function OpenWindow(url, left, top, width, height,
		scrollbar, resizable, status, directories, location, menubar, titlebar, toolbar)
{
	if (!left) left = 100;
	if (!top) top = 100;
	if (!width) width = 600;
	if (!height) height = 400;
	if (!scrollbar) scrollbar = "yes";
	if (!resizable) resizable = "yes";
	if (!status) status = "yes";
	if (!directories) directories = "no";
	if (!location) location = "no";
	if (!menubar) menubar = "no";
	if (!titlebar) titlebar = "no";
	if (!toolbar) toolbar = "no";

	var win = window.open(url, "_blank",
		"left=" + left + ", top=" + top + ", width=" + width + ", height=" + height
		+ ", scrollbars=" + scrollbar + ", resizable=" + resizable
		+ ", status="+ status +", directories=" + directories
		+ ", location=" + location + ", menubar=" + menubar
		+ ", titlebar=" + titlebar + ", toolbar=" + toolbar);
	return win;
}

function PopWindow(URL,width,height)
{
	pop = window.open(URL,"_blank","toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=" + width + ",height=" + height);
	pop.focus();
}

function RefreshWindow()
{
	window.location = TrimURL(window.location.href);
}

function TrimURL(url)
{
	loc = url;
	pos = loc.lastIndexOf('#');
	if (pos >= 0)
	{
		loc = loc.substr(0, pos);
	}
	/*
	if (loc.charAt(loc.length-1) == '#')
	{
		loc = loc.substr(0, loc.length - 1);
	}
	*/
	return loc;
}

function RefreshLeftMenuWindow()
{
	window.top.left.document.all("navigator").contentWindow.document.all("_navigator_area").contentWindow.location.reload();
}

//--------------------------------------------------------------------------
//
//		Select Organization Node
//
//--------------------------------------------------------------------------
function SelectSingleOrgNode(ctrlId,ctrlName)
{
	showx = event.screenX - event.offsetX - 200  ; // + deltaX;
	showy = event.screenY - event.offsetY - 200; // + deltaY;
	retval = ShowModalWindow(UtilPath + "SelectOrgNode.aspx",
		"选择", showx, showy, 600, 400, "yes", "no");

	if ( retval != null )
	{
		var a = retval.split("|");
		if (ctrlId != null)
		{
			ctrlId.value = a[1];
		}
		if (ctrlName != null)
		{
			if (ctrlName.tagName=='SPAN')
			{
				ctrlName.innerHTML = a[0];
			}
			else
			{
				ctrlName.value = a[0];
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

function SelectMultipleOrgNodes(ctrlId,ctrlName)
{
	showx = event.screenX - event.offsetX - 200  ; // + deltaX;
	showy = event.screenY - event.offsetY - 200; // + deltaY;
	retval = ShowModalWindow(UtilPath + "SelectOrgNodes.aspx",
		"选择", showx, showy, 600, 400, "yes", "no");

	if( retval != null )
	{
		var a = retval.split(";");
		if (ctrlId != null)
		{
			ctrlId.value = a[0];
		}
		if (ctrlName != null)
		{
			if (ctrlName.tagName=='SPAN')
			{
				ctrlName.innerHTML = a[1];
			}
			else
			{
				ctrlName.value = a[1];
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

//--------------------------------------------------------------------------
//
//		Select User
//
//--------------------------------------------------------------------------
function SelectSingleUser(ctrlName,ctrlId,UnitId)
{
	//Summary: 显示用户选择ModelDialog，单选
    if(!UnitId) UnitId="0";
	showx = 100;//event.screenX - event.offsetX - 200  ; // + deltaX;
	showy = 100;//event.screenY - event.offsetY - 200; // + deltaY;
    retval = ShowModalWindow(UtilPath + "selectUsers.do?method=selectUsers%26type=2%26unitId=" + UnitId + "%26dimis='0'",
		"选择用户", showx, showy, 600, 400, "yes", "no");

	if( retval != "" )
	{
        var a = retval.split(";");
		if (ctrlId != null)
		{
            ctrlId.value = a[0];
		}
		if (ctrlName != null)
		{
			if (ctrlName.tagName=='SPAN')
			{
				ctrlName.innerHTML = a[1];
			}
			else
			{
				ctrlName.value = a[1];
				ctrlName.value = a[1];
			}
		}
		return true;
	}
	else
	{
        return false;
	}
}

function SelectMultipleUsers(ctrlName,ctrlId,UnitId)
{
	//Summary: 显示用户选择ModelDialog，多选
    if(!UnitId) UnitId="0";
	showx = 100;//event.screenX - event.offsetX - 200  ; // + deltaX;
	showy = 100;//event.screenY - event.offsetY - 200; // + deltaY;
    retval = ShowModalWindow(UtilPath + "selectUsers.do?method=selectUsers%26type=1%26unitId=" + UnitId + "%26dimis='0'",
		"选择用户", showx, showy, 600, 400, "yes", "no");

	if( retval != null )
	{
		var a = retval.split(";");
		if (ctrlId != null)
		{
			ctrlId.value = a[0];
		}
		if (ctrlName != null)
		{
			if (ctrlName.tagName=='SPAN')
			{
				ctrlName.innerHTML = a[1];
			}
			else
			{
				ctrlName.value = a[1];
			}
		}
		return true;
	}
	else
	{
		return false;
	}
}

//--------------------------------------------------------------------------
//
//		Set Authorization
//
//--------------------------------------------------------------------------
function SetAuthorization(resid)
{
	return ShowModalWindow(ConfigurationPathForRight + "company.do?method=showAuthData%26resId=" + resid + "%26selectAuthId=0", "权限设置", null, null, 400, 450);
}


//--------------------------------------------------------------------------
//
//		CheckBox list select
//
//--------------------------------------------------------------------------
function CheckAll(chkAllId, chkOneId)
{
	var chkAll = document.all(chkAllId);
	var chkOneList = document.all(chkOneId);
	if (!chkAll || !chkOneList)
		return;
	if (!chkOneList.length)
	{
		chkOneList.checked = chkAll.checked;
	}
	else
	{
		for (var i = 0; i < chkOneList.length; i ++)
		{
			chkOneList[i].checked = chkAll.checked;
		}
	}
}

function CheckOne(chkAllId)
{
	var chkAll = document.all(chkAllId);
	if (!chkAll)
		return;
	if (!event.srcElement.checked)
	{
		chkAll.checked = false;
	}
}

function GetAllCheckedValue(chkOneId)
{
	var chkOneList = document.all(chkOneId);
	if (!chkOneList)
		return;
	if (!chkOneList.length)
	{
		return chkOneList.value;
	}
	else
	{
		var ret = "";
		for (var i = 0; i < chkOneList.length; i ++)
		{
			if (chkOneList[i].checked)
			{
				ret = ret + chkOneList[i].value + ";";
			}
		}
		return ret;
	}
}

function trim( text )
{
    var s = text.replace(/(^\s*)|(\s*$)/g, "");
    return s;
}

// 检查传入的数据是否是合法的日期[yyyy-mm-dd]或[yyyy/mm/dd]
function isDate( str )
{
    var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
    result = str.match(reg);
    if(result == null) return false;
    var d = new Date(result[1],result[3]-1,result[4])
    var oldstr = parseFloat(result[1]) + result[2] + parseFloat(result[3]) + result[2] + parseFloat(result[4]);
    var newstr = d.getFullYear() + result[2] + ( d.getMonth() + 1 ) + result[2] + d.getDate();
    return oldstr == newstr
}
//--------------------------------------------------------------------------
//
//		Download CommonAttach
//
//--------------------------------------------------------------------------
function CommonAttachDownload(id)
{
	PopWindow(UtilPath + "dlCommonAttach.aspx?Id=" + id);
}

// 四舍五入
// num-数字; precision-保留小数位数
function round( num, precision )
{
    var val = 1;
    for ( var i = 0; i < precision; i++ )
    {
        val *= 10;
    }
    return Math.round(num * val) / val;
}




function getPositionIE(e){
    t=e.offsetTop + 24;
    l=e.offsetLeft-10;
//		while(e=e.offsetParent){
//			t+=e.offsetTop;
//			l+=e.offsetLeft;
//		}
    window.status = "top="+t+" left="+l;
}


/*********************** 通用函数 ***********************/
//function getPositionIE( ctrl )
//{
//
//    l = ctrl.offsetLeft;
//    t = ctrl.offsetTop;
//
//    var lc_parent = ctrl.offsetParent;
//    while ( lc_parent )
//    {
//        l += lc_parent.offsetLeft;
//        t += lc_parent.offsetTop;
//
//        lc_parent = lc_parent.offsetParent;
//    }
//
//    window.status = "top="+t+" left="+l;
//}



function trim(sString)
{
while (sString.substring(0,1) == ' ')
{
    sString = sString.substring(1, sString.length);
}
while (sString.substring(sString.length-1, sString.length) == ' ')
{
    sString = sString.substring(0,sString.length-1);
}
return sString;
}



var RschDocPath="/rschDoc/";
function RschDocDisplay(docid)
{
    OpenWindow(RschDocPath + "docAction.do?method=viewDoc&docId=" + docid,
		1, 1, 850, 600);
}

function checkBetweenDateValidity(begin, end)
{
    if(begin != null && begin.length != 10 && begin.length != 0)
    {
        return('请输入正确的开始时间或留空，格式：yyyy-MM-dd');
    }
    if(end != null && end.length != 10 && end.length != 0)
    {
        return('请输入正确的结束时间或留空，格式：yyyy-MM-dd');
    }
    if(begin != null && begin.length == 10)
    {
        if(begin.substr(4,1) != '-' || begin.substr(7,1) != '-')
        {
            return('请输入正确的开始时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(begin.substr(0,4)) || begin.substr(0,4) != parseInt(begin.substr(0,4),10))
        {
            return('请输入正确的开始时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(begin.substr(5,2)) || begin.substr(5,2) != parseInt(begin.substr(5,2),10) || parseInt(begin.substr(5,2),10) < 1 || parseInt(begin.substr(5,2),10) > 12)
        {
            return('请输入正确的开始时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(begin.substr(8,2)) || begin.substr(8,2) != parseInt(begin.substr(8,2),10) || parseInt(begin.substr(8,2),10) < 1 || parseInt(begin.substr(8,2),10) > 31)
        {
            return('请输入正确的开始时间或留空，格式：yyyy-MM-dd');
        }
    }

    if(end != null && end.length == 10)
    {
        if(end.substr(4,1) != '-' || end.substr(7,1) != '-')
        {
            return('请输入正确的结束时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(end.substr(0,4)) || end.substr(0,4) != parseInt(end.substr(0,4),10))
        {
            return('请输入正确的结束时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(end.substr(5,2)) || end.substr(5,2) != parseInt(end.substr(5,2),10) || parseInt(end.substr(5,2),10) < 1 || parseInt(end.substr(5,2),10) > 12)
        {
            return('请输入正确的结束时间或留空，格式：yyyy-MM-dd');
        }
        if(isNaN(end.substr(8,2)) || end.substr(8,2) != parseInt(end.substr(8,2),10) || parseInt(end.substr(8,2),10) < 1 || parseInt(end.substr(8,2),10) > 31)
        {
            return('请输入正确的结束时间或留空，格式：yyyy-MM-dd');
        }
    }

    if(begin != null && begin.length == 10 && end != null && end.length == 10)
    {
        if(begin > end)
        {
            return('开始时间必须小于结束时间');
        }
    }
    return null;
}