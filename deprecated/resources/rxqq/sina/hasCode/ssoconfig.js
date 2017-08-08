var _gourl='';
var _addserver='';
var _addurl='';
var _isLogin_Ww = '1';
var _iganmeurl = 'http://test.i.wanwan.sina.com.cn/header/wanwangame.php?gameurl=';
function _gogame_Ww(addserver,addurl){
	_addserver=addserver;
	_addurl=addurl;
	var js = sinaSSOController.getSinaCookie();
	if(js){
		var displayname = js.user;
		var lt = js.lt;
		if(displayname!='')
		{
			if(lt==1){
				_isLogin_Ww = '2' ;
			}
		}
	}
	if(_isLogin_Ww=='2'){
		getticket(_addurl, _addserver);
	}else{
		_isLogin_Ww='3';
		_wan_sina_login();
	}
}
//以下部分是获得进入游戏获得票据
function loadScript(url, callback, charset){
	charset = charset || 'utf-8';
	callback = callback ||
	function(){
	};
	var t = document.createElement("script");
	t.type = "text/javascript";
	t.charset = charset;
	t.src = url;
	var _fun = function(){
		if (t.onreadystatechange) {
			t.onreadystatechange = null;
		}
		else {
			t.onload = null;
		}
		_fun = null;
		document.getElementsByTagName("head")[0].removeChild(t);
		callback();
	};
	if (typeof t.onreadystatechange != "undefined") {
		t.onreadystatechange = function(){
			if (t.readyState == 'complete' || t.readyState == 'loaded') {
				_fun();
			}
		};
	}
	else {
		t.onload = _fun;
	}
	document.getElementsByTagName("head")[0].insertBefore(t,document.getElementsByTagName("head")[0].firstChild);
}
var A = "";
function getticket(a, service) {
	A = a;
	var dogameurl="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/dogame.php?service="+service;
	loadScript(dogameurl);
	var url = "http://login.sina.com.cn/sso/login.php?entry=wanwan&service="+service+"&returntype=TEXT&callback=func";
	loadScript(url);
}
function func(result) {
	if (result["retcode"] == 0 && result["ticket"]!='' && A!='') {
		if(A.indexOf('?')!=-1){
			var gameurl=A+'&ticket='+result["ticket"];
		}else{
			var gameurl=A+'?ticket='+result["ticket"];
		}
		window.location=gameurl;
		/*if(gameurl.indexOf('tc.wanwan.sina.com')!=-1){
		window.location=gameurl;
		}else{
		window.location=_iganmeurl+gameurl;
		}*/
	}else{
		alert('抱歉,网络繁忙请稍后再试!');
	}
}

function _wan_sina_login(gourl){
	_gourl = '';
	if(gourl){
		_gourl = gourl;
		var js = sinaSSOController.getSinaCookie();
		if(js){
			var displayname = js.user;
			var lt = js.lt;
			if(displayname!='' && lt==1)
			{
				if(!_gourl || _gourl =='') return ;
				if(typeof(_gourl) == 'string'){
					_gourl=replaceurl_ww(_gourl);
					window.open(_gourl);
				}else{
					//关闭浮层
					sinaGameDialogwwlogin.close();
					try{
						_gourl();
					}catch(e){}
				}
				return false;
			}
		}
	}
	//var show_html = '<div id="LoginQrCord_Ww"><form action="#" method="post" name="memberlogin_Ww" id="memberlogin_Ww" onsubmit="_checklogin_wan();return false;"><ul><li>用户名：<input type="text" autocomplete="off" name="username_Ww" class="txtBox2" value=""  id="username_Ww"/></li><li>密　码：<input type="password" name="password_Ww" class="txtBox2" value=""  id="password_Ww"/></li><li id="errmsg_Ww" style="color:red;">&nbsp;</li><li class="Sub_Ww"><input  type="hidden" value="0.0069"  name="savestate_Ww" id="savestate_Ww" /><input type="submit" name="login" class="login" value="登录" />   <input type="reset" name="login" class="login" value="重置" /><A href="http://wanwan.sina.com.cn/wanreg/wanreg.php" target="_blank"><font color="red">注册玩玩</font></A>&nbsp;<A href="https://login.sina.com.cn/getpass.html" target="_blank">找回密码</A></li></ul></form></div>';
	var show_html =
            '<div  id="LoginQrCord_Ww">' +
                '<form action="#" method="post" name="memberlogin_Ww" id="memberlogin_Ww" onsubmit="_checklogin_wan();return false;">' +
                    '<div class="wanwanHead Fblack">' +
                        '<div class="wanwanClose">' +
                            '<a href="javascript:;" onclick="sinaGameDialogwwlogin.close();return false;">' +
                                '<img src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/images/close.gif" width="9" height="11" border="0" alt="关闭">' +
                            '</a>' +
                        '</div>' +
                    '</div>' +
                    '<div class="wanwanCont wanwanFred" >' +
                        '<div class="wanwanLeft">' +
                            '<div class="wanwanContA">' +
                                '<ul>' +
                                    '<li class="wanwanFred">已有新浪博客、新浪邮箱账号，可直接登录</li>' +
                                    '<li class="wanwanFblack">' +
                                        '<span>用户名：</span>' +
                                        '<input autocomplete="off" name="username_Ww" id="username_Ww" title="用户名"  type="text" onClick="_wan_check_wanloginname();" value="邮箱\\会员账号\\手机号"  style="width:166px;padding:5px 0 0 2px;"/>' +
                                    '</li>' +
                                    '<li class="wanwanFblack">' +
                                        '<span>密　码：</span>' +
                                        '<input id="password_Ww" name="password_Ww" title="密　码"  type="password" value="" size="32" maxlength="128" style="width:166px;padding:5px 0 0 2px;"/>' +
                                    '</li>' +
                                    '<li class="wanwanFblack" id="doorli" style="display:none;">' +
                                        '<span>验证码：</span>' +
                                        '<input id="door" type="text" style="width:66px; padding:5px 0 0 2px;float:left; margin-left:16px;" maxlength="128" size="32" value="" title="验证码">' +
                                        '<img src="http://login.sina.com.cn/cgi/pin.php?s=0&r=123"  id="doorimg" width="70" style="float:left; margin-left:10px" height="24" alt="图片说明" onclick="this.src=\'http://login.sina.com.cn/cgi/pin.php?s=0&r=\' + new Date().getTime();"/>' +
                                    '</li>' +
                                    '<li id="errmsg_Ww" style="color:red;height:20px;">&nbsp;</li>' +
                                '</ul>' +
                                '<div class="wanwanbuTT">' +
                                    '<input  type="hidden" value="0.0069"  name="savestate_Ww" id="savestate_Ww" />' +
                                    '<input type="submit" value=" 登 录 " style="background:url(http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/images/botton1.gif)  bottom center no-repeat;width:78px;height:24px;border:0;cursor:pointer"> ' +
                                    '<input type="button" onclick="_wan_reset_form();" style="background:url(http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/images/botton1.gif)  bottom center no-repeat;width:78px;height:24px;border:0;cursor:pointer" value=" 重 置 ">' +
                                '</div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="wanwanRight">' +
                            '<div class="wanwanContB">' +
                                '<ul><li class="wanwanFred">还未开通？赶紧注册一个吧</li><li><A href="http://wanwan.sina.com.cn/wanreg/wanreg.php" target="_blank"><img src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/images/botton2.gif" width="120" height="37" border="0" alt=""></A></li></ul>' +
                                '<div class="wanwanContc">' +
                                    '<div class="wanwanContca"></div>' +
                                    '<img src="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/images/xlang.gif" width="73" height="112" border="0" alt="" >' +
                                '</div></div></div></div></form></div>';
	sinaGameDialogwwlogin.show("登录新浪玩玩", show_html,540);
	var wanloginname = getCookiew('wanloginname');
	if(document.getElementById("username_Ww") && wanloginname)
	document.getElementById("username_Ww").value = unescape(wanloginname);

	initUserName();
}

function _wan_check_wanloginname(){
	var wanloginname = getCookiew('wanloginname');
	if(wanloginname) {

	}else{
		document.getElementById("username_Ww").value = '';
	}
}
function _wan_reset_form(){
	document.getElementById("username_Ww").value ='';
	document.getElementById("password_Ww").value = '';
	document.getElementById("errmsg_Ww").innerHTML = '';
}
//可复制过去改下下面参数就可以了
function initUserName(){
	passcardOBJ.init(
	// FlashSoft 注意,最好这个input的autocomplete设定为off
	// 需要有下拉框的input对象
	document.getElementById("username_Ww"),
	{
		// 鼠标经过字体颜色
		overfcolor: "#666",
		// 鼠标经过背景颜色
		overbgcolor: "#d6edfb",
		// 鼠标离开字体颜色
		outfcolor: "#000000",
		// 鼠标离开背景颜色
		outbgcolor: ""
	},
	// 输入完成后,自动需要跳到的input对象[备选]
	document.getElementById("password_Ww"),
	null,
	document.getElementById("SSOTipsContainer")
	);

	setTimeout("var obj=document.getElementById(\"username_Ww\");if(obj.value==\"\")obj.focus();",1000);
}

function _checklogin_wan(){
	var uname=document.getElementById("username_Ww");
	var upwd=document.getElementById("password_Ww");
	if(uname.value==''){
		document.getElementById("errmsg_Ww").innerHTML = "提　示：用户名不能为空!";
		uname.focus();
		return false;
	}
	if(uname.value.indexOf('<')!=-1 || uname.value.indexOf('>')!=-1){
		document.getElementById("errmsg_Ww").innerHTML = "提　示：用户名含有非法字符!";
		uname.focus();
		return false;
	}
	if(upwd.value==''){
		document.getElementById("errmsg_Ww").innerHTML = "提　示：密码不能为空!";
		upwd.focus();
		return false;
	}
	if(upwd.value.indexOf('<')!=-1 || upwd.value.indexOf('>')!=-1){
		document.getElementById("errmsg_Ww").innerHTML = "提　示：密码含有非法字符!";
		upwd.focus();
		return false;
	}
	if(document.getElementById("doorli") && document.getElementById("doorli").style.display == 'block'){
		if( document.getElementById("door").value == ''){
			document.getElementById('errmsgdiv').style.display = 'block';
			document.getElementById("errmsg_Ww_k").innerHTML = "提　示：验证码不能为空!";
			document.getElementById("door").focus();
			return false;
		}
		var mydoor = document.getElementById("door").value;
		sinaSSOController.loginExtraQuery={door:mydoor};
	}
	SetCookiew('wanloginname',document.getElementById("username_Ww").value);
	sinaSSOController.login(document.getElementById("username_Ww").value,document.getElementById("password_Ww").value, document.getElementById("savestate_Ww").value);
}

//javascript 配置样例,sso回调函数
sinaSSOConfig = new function() {
	this.service = 'wanwan';
	this.setDomain = true;
	this.pageCharset = 'GB2312';
	this.useTicket = false;
	this.customLoginCallBack = function(loginStatus) {
		if (!loginStatus.result) {
			if(loginStatus.errno=='5' || loginStatus.errno=='2091'){
				this.$("errmsg_Ww").innerHTML = "用户不存在!";
			}else if(loginStatus.errno=='80' || loginStatus.errno=='101'){
				this.$("errmsg_Ww").innerHTML = "密码错误!";
			}else if(document.getElementById("doorli") && loginStatus.errno==4049) {
                document.getElementById('doorli').style.display = 'block';
            } else if(document.getElementById("doorli") && loginStatus.errno==2070){
				document.getElementById('doorimg').src = "http://login.sina.com.cn/cgi/pin.php?s=0&r="+new Date().getTime();
				document.getElementById("errmsg_Ww").innerHTML = "提　示：验证码错误!";
			}else{
				this.$("errmsg_Ww").innerHTML = "用户名或密码错误!";
			}
		} else {
			var dologinurl="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/dologin.php";
			loadScript(dologinurl,function(){
				if(!_gourl || _gourl =='') return ;
				if(typeof(_gourl) == 'string'){
					_gourl=replaceurl_ww(_gourl);
					window.location.href=_gourl;
				}else{
					sinaGameDialogwwlogin.close();
					try{
						_gourl();
					}catch(e){}
				}
			});

			if(_gourl!=''){
				//window.location.href=_gourl;
			}else{
				if(_isLogin_Ww=='3' && _addserver!='' && _addurl!=''){
					getticket(_addurl, _addserver);
				}else{
					parent.location.reload();
				}
			}
		}
	};
	this.customLogoutCallBack = function(logoutStatus) {
		//请求清除玩玩cookie操作
		var dologouturl="http://wanwan.games.sina.com.cn/wanlogout.php?flag=js";
		loadScript(dologouturl,function(){
			parent.location.reload();
		});
		//用户退出后的操作
		document.getElementById("loginbtu_Ww").style.display = 'block';
		document.getElementById("welcomeMessage_Ww").style.display = 'none';
		if(document.getElementById("loginShow_Ww")){
			document.getElementById("loginShow_Ww").style.display = 'none';
		}
	};
};

function getCookieValw(offset) {
	var endstr = document.cookie.indexOf(";", offset);
	if(endstr == -1) {
		endstr = document.cookie.length;
	}
	return document.cookie.substring(offset, endstr);
}
function getCookiew(name) {
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	var j = 0;
	while(i < clen) {
		j = i + alen;
		if(document.cookie.substring(i, j) == arg){
			return getCookieValw(j);
		}
		i = document.cookie.indexOf(" ", i) + 1;
		if(i === 0){
			break;
		}
	}
	return '';
}
function SetCookiew(name,value)//两个参数，一个是cookie的名子，一个是值
{
	var Days = 30; //此 cookie 将被保存 30 天
	var exp  = new Date();    //new Date("December 31, 9998");
	exp.setTime(exp.getTime() + Days*24*60*60*1000);
	document.cookie = name + "="+ escape (value) + ";path=/;domain=sina.com.cn;expires=" + exp.toGMTString();
}

var _jifen_ww = '';
var _username_ww = '';
var _mail_ww = '';
var _level_name_ww = '';
var _medal_ww = ''; //成就
var _uid_ww = '';
function wan_user_data_ww(uid,fun){
	var dologinurl="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/getwandata.php?uid="+uid;
	loadScript(dologinurl,function(){
		if(fun) fun();
	}
	);
}

var _sso_onload = function(){
	var js = sinaSSOController.getSinaCookie();
	if(js){
		var displayname = js.user;
		var lt = js.lt;
		var uid = js.uid;
		if(displayname!='')
		{
			if(lt==1){
				var currunt_url=window.location.href;
				var currunt_refer=document.referrer;
				if(currunt_url=='http://wanwan.games.sina.com.cn/wanoutpage.php' || currunt_url=='http://games.sina.com.cn/o/kb/10499.shtml')	{
					window.location.href='http://wanwan.games.sina.com.cn/my.php';
				}
				var wanuser_cookie=getCookiew('_wanusr_');
				if(!wanuser_cookie){
					var dologinurl="http://wanwan.games.sina.com.cn/wanloginreg/wanlogin_fd/dologin.php";
					loadScript(dologinurl,function(){
						wan_user_data_ww(uid,function(){
							if(_username_ww==''){
								parent.location.reload();
								document.getElementById("welcomeMessage_Ww").style.display = 'none';
								document.getElementById("loginbtu_Ww").style.display = 'block';
							}else{
								if(document.getElementById('welcomeMessage_Ww')){
									document.getElementById('welcomeMessage_Ww').innerHTML = _username_ww + ',' + '<a href="#" onclick="javascript:sinaSSOController.logout();return false;" >退出</a>';
									document.getElementById("welcomeMessage_Ww").style.display = 'block';
									document.getElementById("loginbtu_Ww").style.display = 'none';
								}
							}
						});
					});
				}else{
					wan_user_data_ww(uid,function(){
						if(_username_ww==''){
							document.getElementById("welcomeMessage_Ww").style.display = 'none';
							document.getElementById("loginbtu_Ww").style.display = 'block';
						}else{
							if(document.getElementById('welcomeMessage_Ww')){
								document.getElementById('welcomeMessage_Ww').innerHTML = _username_ww + ',' + '<a href="#" onclick="javascript:sinaSSOController.logout();return false;" >退出</a>';
								document.getElementById("welcomeMessage_Ww").style.display = 'block';
								document.getElementById("loginbtu_Ww").style.display = 'none';
							}
						}
					});
				}
			}else{
				if(document.getElementById("welcomeMessage_Ww")){
					document.getElementById("welcomeMessage_Ww").style.display = 'none';
				}
			}
		}else{
			if(document.getElementById("welcomeMessage_Ww")){
				document.getElementById("welcomeMessage_Ww").style.display = 'none';
			}
		}
	}else{
		if(document.getElementById("welcomeMessage_Ww")){
			document.getElementById("welcomeMessage_Ww").style.display = 'none';
		}
	}
}

function replaceurl_ww(u){
	var Cts = u;
	var current_url = window.location.href;
	if(current_url.indexOf('http://wanwan.games.sina.com.cn')!=-1 && Cts.indexOf('http://wanwan.sina.com.cn')!=-1){
		return Cts.replace('http://wanwan.sina.com.cn','http://wanwan.games.sina.com.cn');
	}else if(current_url.indexOf('http://wanwan.sina.com.cn')!=-1 && Cts.indexOf('http://wanwan.games.sina.com.cn')!=-1){
		return Cts.replace('http://wanwan.games.sina.com.cn','http://wanwan.sina.com.cn');
	}else{
		return Cts;
	}
}

var addEvent_Ww = function(obj,eventType,func){
    if(obj.attachEvent){
        obj.attachEvent("on" +eventType,func);
    }else{
        obj.addEventListener(eventType,func,false)
    }
};
addEvent_Ww(window,'load',function(){_sso_onload();});