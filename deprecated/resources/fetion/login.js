var loginUrl = "WebIM/Login.aspx";
var getPicCodeUrl = "WebIM/GetPicCode.aspx?Type={0}&{1}";
var getBannerPicUrl = "httpHandler/GetBannerPicHttpHandler.ashx?s={0}";
var mainPageUrl = "main.aspx";
var loginPageUrl = "login.aspx";
var loadingPanel;
var objPar = objects(window.location.search);
(function($) {
    $(function() {
        if(!!($.API) && $.API.app_360.is360DeskTop()) {
            Bridge.setup("6cb571fc8b72ccc8e7705e5bde0ee4b5");
            if(!$.openPlatLogin.hasLogin()) {
                $.API.app_360.fetionLogin();
            }
        }
        if($.openPlatLogin.isOpenPlatLogin()) {
            $.openPlatLogin.login(function() {
                window.location.href = mainPageUrl;
            });
            return;
        }
        initUrls();
        initNavigation();
        getBanner();
        loginIncreaseCounter();
        var usernameInput = $("#login_username");
        usernameInput.bind("focus", function() {
            if(!numberReg.test(usernameInput.val())) {
                usernameInput.val("").removeClass("co_b6");
            }
        }).bind("blur", function() {
            if(usernameInput.val() == "") {
                usernameInput.val("请输入手机号、飞信号或邮箱").addClass("co_b6");
            }
        }).bind("mouseover", function(e) {
            usernameInput.addClass("on");
        }).bind("mouseout", function(e) {
            usernameInput.removeClass("on");
        });
        $("#login_pass1").bind("focus", function(e) {
            $(this).addClass("none");
            $("#login_pass").removeClass("none");
            $("#login_pass").focus();
        });
        $("#login_pass").bind("blur", function(e) {
            if($(this).val() == "") {
                $(this).addClass("none");
                $("#login_pass1").removeClass("none");
            }
        }).bind("keydown", function(e) {
            e = window.event || e;
            if(e.keyCode == 13) {
                $("#btnlogin").click();
            }
        });
        if(__isProxyIp == 1) {
            createCcpUI();
        }
        var historyLoginStatus = getCookie("webim_login_status");
        if(historyLoginStatus == 1) {
            $("#login_chk_1").attr("checked", true);
        } else {
            $("#login_chk_1").attr("checked", false);
        }
        var historyLoginUserName = getCookie("webim_login_username");
        if(historyLoginUserName) {
            usernameInput.val(historyLoginUserName).removeClass("co_b6");
            $("#login_chk_2").attr("checked", true);
        } else {
            usernameInput.val("请输入手机号、飞信号或邮箱").addClass("co_b6");
        }
        $("#btnlogin").bind("click", function() {
            if(!checkCondition()) {
                return false;
            }
            $("#error_info").css("display", "none");
            login();
            return false;
        });
        loadingPanel = $.fn.loadingPanel({target:"login_area"});
    });
    function createCcpUI() {
        if($("#tr_code").find("td").length > 0) {
            resetCcp();
            return;
        }
        var td = $('<td class="h54"><input id="login_code" type="text" class="input2 co_b6" name="" value="验证码不区分大小写" style="ime-mode: disabled" tabindex="3" ' + 'maxlength="4" /><img id="ccpimg" class="ln_code" alt="" src="images/login20120903/ccploading.png" />' + '<a id="change_link" class="ln_change_code" title="" href="javascript:;">换一张</a></td>');
        $("#tr_code").append(td);
        $("#login_code").bind("focus", function() {
            if($(this).val() == "验证码不区分大小写") {
                $(this).val("").removeClass("co_b6");
            }
        }).bind("blur", function() {
            if($(this).val() == "") {
                $(this).val("验证码不区分大小写").addClass("co_b6");
            }
        }).bind("mouseover", function(e) {
            $(this).addClass("on2");
        }).bind("mouseout", function(e) {
            $(this).removeClass("on2");
        });
        $("#login_code").bind("keydown", function(e) {
            e = window.event || e;
            if(e.keyCode == 13) {
                $("#btnlogin").click();
            }
        });
        $("#change_link").bind("click", function() {
            resetCcp();
            return false;
        });
        setTimeout(function() {
            resetCcp();
        }, 500);
    }
    function initUrls() {
        mainPageUrl = __baseUrl + mainPageUrl;
        loginPageUrl = __baseUrl + loginPageUrl;
    }
    function initNavigation() {
        $("#feedback_link").attr("href", __feedbackUrl);
        $("#help_link").attr("href", __helpUrl);
        $("#findpwd_link").attr("href", __findPwdUrl);
        $("#download_link").attr("href", __downloadUrl);
        $("#pc_download_link").attr("href", __downloadUrl);
        $("#mac_download_link").attr("href", __macDownloadUrl);
        $("#download_link").bind("click", function() {
            $.increaseCounter(500500002);
        });
        $("#pc_download_link").bind("click", function() {
            $.increaseCounter(500500001);
        });
        $("#mac_download_link").bind("click", function() {
            $.increaseCounter(500500003);
        });
        var url = location.href;
        if(url.indexOf("?") != -1) {
            var paraString = url.substring(url.indexOf("?"), url.length);
            __registerUrl += paraString;
        }
        $("#register_link").attr("href", __registerUrl);
        $("#favorite_link").bind("click", function() {
            addFavourite(location.href, "官方网页版飞信——在网页上与飞信好友聊天、免费发短信");
            return false;
        });
    }
    function getBanner() {
        if(!!__bannerInfo) {
            var jsonObject = eval("(" + __bannerInfo + ")");
            if(jsonObject.length > 0) {
                for(var i = 0; i < jsonObject.length; i++) {
                    var item = jsonObject[i];
                    if(item.Key == "BannerImg_login" && item.OffShelfTime == 1) {
                        if($.browser.msie) {
                            var imgSrc = formatString(getBannerPicUrl, item.Img);
                            $("#banner").attr("src", imgSrc).attr("alt", item.Title);
                        } else {
                            $("#banner").attr("src", item.Img).attr("alt", item.Title);
                        }
                        if(!!item.Link) {
                            $("#bannerLink").css({"cursor":"pointer"}).attr("target", item.Target).attr("title", item.Title);
                            $("#bannerLink").bind("mousedown", function(e) {
                                $.increaseCounter(500900002);
                            }).bind("click", function(e) {
                                $("#bannerLink").attr("href", item.Link);
                            });
                        }
                        break;
                    }
                }
            }
        }
    }
    function loginIncreaseCounter() {
        var cookieValue = getCookie("webim_loginCounter");
        if(cookieValue == null || cookieValue == "") {
            var expireDate = new Date();
            expireDate.setHours(expireDate.getHours() + 1);
            setCookie("webim_loginCounter", expireDate.getTime(), expireDate.toGMTString(), "/");
            $.increaseCounter(500800001);
        }
    }
    function checkCondition() {
        var username = $("#login_username").val().trim();
        if(username == "") {
            showErrMsg("请输入手机号、飞信号或邮箱");
            return false;
        }
        if(!isMobileNoReg.test(username) && !isValidSid.test(username) && !emailReg.test(username)) {
            showErrMsg("请输入正确的手机号、飞信号或邮箱。");
            return false;
        }
        if($("#login_pass").val().trim() == "") {
            showErrMsg("请输入密码！");
            return false;
        }
        if($("#tr_code").find("td").length > 0) {
            if($("#login_code").val().trim() == "" || $("#login_code").val().trim() == "验证码不区分大小写") {
                showErrMsg("请输入验证码。");
                return false;
            }
        }
        return true;
    }
    function saveLoginStatus() {
        var status = 0;
        if($("#login_chk_1").attr("checked")) {
            status = 1;
        } else {
            status = 0;
        }
        var expireDate = new Date(2033, 1, 1);
        setCookie("webim_login_status", status, expireDate.toGMTString(), "/");
    }
    function saveLoginInfo(userName) {
        if(userName) {
            var expireDate = new Date(2033, 1, 1);
            setCookie("webim_login_username", escape(userName), expireDate.toGMTString(), "/");
        } else {
            deleteCookie("webim_login_username", "/");
        }
    }
    var __loginTimes = 1;
    function login() {
        var loginParams = new Object();
        loginParams.success = loginSuccess;
        loginParams.error = loginError;
        if(__calluser) {
            loginParams.url = loginUrl + "?calluser=" + __calluser;
        } else {
            loginParams.url = loginUrl;
            var url = location.href;
            if(url.indexOf("?") != -1) {
                var paraString = url.substring(url.indexOf("?"), url.length);
                loginParams.url += paraString;
            }
        }
        loginParams.type = "POST";
        var loginStatus = 0;
        if(!$("#login_chk_1").attr("checked")) {
            loginStatus = 400;
        }
        var userName = $("#login_username").val().trim();
        var accountType = 0;
        if(isValidSid.test(userName)) {
            accountType = 0;
        } else {
            if(isMobileNoReg.test(userName)) {
                accountType = 1;
            } else {
                if(emailReg.test(userName)) {
                    accountType = 2;
                }
            }
        }
        loginParams.data = {"UserName":userName,"Pwd":$("#login_pass").val(),"OnlineStatus":loginStatus,"AccountType":accountType};
        if(emailReg.test(userName)) {
            $.increaseCounter(500500004);
        }
        if($("#tr_code").find("td").length > 0) {
            loginParams.data["Ccp"] = $("#login_code").val();
        }
        loginParams.dataType = "json";
        loadingPanel.show();
        $.ajax(loginParams);
    }
    function loginSuccess(data, textStatus) {
        loadingPanel.hide();
        if(data.rc == 200) {
            saveLoginStatus();
            if($("#login_chk_2").attr("checked")) {
                saveLoginInfo($("#login_username").val());
            } else {
                saveLoginInfo(null);
            }
            var source = (!objPar["source"]) ? "" : objPar["source"];
            $.increaseCounter(osType() + "," + getScreen() + "," + "500800002", source);
            window.location.href = mainPageUrl;
        } else {
            if(data.rc == 999) {
                createCcpUI();
            } else {
                if(data.rc == 9311) {
                    window.location.reload();
                } else {
                    if(__loginTimes >= 3) {
                        createCcpUI();
                    } else {
                        resetCcp();
                    }
                    __loginTimes++;
                    showErrMsg(data.rc);
                }
            }
        }
    }
    function loginError(XMLHttpRequest, textStatus, errorThrown) {
        loadingPanel.hide();
        if(__loginTimes >= 3) {
            createCcpUI();
        } else {
            resetCcp();
        }
        __loginTimes++;
        showErrMsg("连接服务器失败，请您检查网络连接是否正常。");
    }
    function resetCcp() {
        $("#ccpimg").attr("src", formatString(getPicCodeUrl, "ccpsession", Math.random()));
        return false;
    }
    function showErrMsg(errInfo) {
        var errorDiv = $("#error_info");
        if(typeof errInfo == "string") {
            errorDiv.css("display", "block").html(errInfo);
        } else {
            if(typeof errInfo == "number") {
                var errMsg = "";
                switch(errInfo) {
                    case 311:errMsg = "请输入正确的手机号、飞信号和邮箱。";break;
                    case 312:$("#login_code").select();errMsg = "您输入的验证码有误，请重新输入。";break;
                    case 404:
                    case 403:
                    case 430:
                    case 435:errMsg = "您尚未开通飞信服务，请注册开通飞信。";break;
                    case 405:
                    case 504:errMsg = "您已主动申请停用飞信业务或您的账号有异常行为，您的飞信账号已被禁用。";break;
                    case 321:errMsg = "您输入密码错误，请重新输入。";break;
                    case 507:errMsg = "目前仅限中国大陆用户使用。";break;
                    case 406:errMsg = "您的帐号因在近期多次出现异常而被锁定，暂时无法登录，<a href='" + __unlockUrl + "' target='_blank'>点击此处解决。</a>";break;
                    case 500:
                    default:errMsg = "服务器内部发生错误，请重试。";break;
                }
                errorDiv.css("display", "block").html(errMsg);
            }
        }
    }
})(jQuery);