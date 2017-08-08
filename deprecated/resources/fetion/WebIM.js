var mainPageUrl = "main.aspx";
var loginPageUrl = "login.aspx";
var addBuddyUrl = "WebIM/AddBuddy.aspx?Version={0}";
var getConnectUrl = "WebIM/GetConnect.aspx?Version={0}";
var getContactInfoUrl = "WebIM/GetContactInfo.aspx?UserType={0}&UserValue={1}&Version={2}";
var getContactListUrl = "WebIM/GetContactList.aspx?Version={0}";
var getGroupListUrl = "WebIM/GetGroupList.aspx?Version={0}";
var getGroupPortraitUrl = "WebIM/GetGroupPortrait.aspx?Uri={0}&Size={1}&Crc={2}&mid={3}";
var opInviteJoinUrl = "WebIM/PGHandleInviteJoinGroup.aspx?Version={0}";
var PGHandleApplicationUrl = "WebIM/PGHandleApplication.aspx?Version={0}";
var getGroupMemUrl = "WebIM/GetGroupMembers.aspx?Version={0}";
var SendGroupMsgUrl = "WebIM/SendGroupMsg.aspx?Version={0}";
var getGroupPerInfoUrl = "WebIM/PGGetPersonalInfo.aspx?Version={0}";
var approveInviteUrl = "WebIM/ApproveInviteJoin.aspx?Version={0}";
var GroupSubscribeUrl = "WebIM/GroupSubscribe.aspx?Version={0}";
var getPersonalInfoUrl = "WebIM/GetPersonalInfo.aspx?Version={0}";
var handleAddBuddyUrl = "WebIM/HandleAddBuddy.aspx?Version={0}";
var logoutUrl = "WebIM/Logout.aspx?Version={0}";
var opBuddyUrl = "WebIM/OpBuddy.aspx?Version={0}";
var sendMessageUrl = "WebIM/SendMsg.aspx?Version={0}";
var setPersonalInfoUrl = "WebIM/SetPersonalInfo.aspx?Version={0}";
var setContactInfoUrl = "WebIM/SetContactInfo.aspx?Version={0}";
var setPresenceUrl = "WebIM/SetPresence.aspx?Version={0}";
var setSmsOnlineUrl = "WebIM/SetSmsOnline.aspx?Version={0}";
var setScheduleSmsUrl = "WebIM/SetScheduleSms.aspx?Version={0}&Type={1}";
var sendSMSUrl = "WebIM/SendSMS.aspx?Version={0}";
var addChatFriendUrl = "WebIM/AddChatFriend.aspx?Version={0}";
var getCredUrl = "WebIM/GetCred.aspx?Version={0}";
var registerUrl = "WebIM/Register.aspx?Version={0}";
var registerValidateUrl = "WebIM/RegisterValidate.aspx?Version={0}";
var SetMessageReadUrl = "WebIM/SetMessageReadHttpHandler.aspx?Version={0}";
var SendDirectSMSUrl = "WebIM/SendDirectSMSHttpHandler.aspx?Version={0}";
var getBannerPicUrl = "httpHandler/GetBannerPicHttpHandler.ashx?s={0}";
var getPcRecommendBuddyListV2Url = "WebIM/GetPcRecommendBuddyListV2Handler.aspx?Version={0}";
var AddRecommendBuddyNotifyUrl = "WebIM/AddRecommendBuddyNotifyHttpHandler.aspx?Version={0}";
var getPortraitUrl = "WebIM/GetPortrait.aspx?did={0}&Size={1}&Crc={2}&mid={3}";
var getPicCodeUrl = "WebIM/GetPicCode.aspx?Type={0}&{1}";
var ValidatePicCodeUrl = "WebIM/ValidatePicCodeHttpHandler.aspx?Version={0}";
var GetSmsCodeCodeUrl = "WebIM/GetSmsCodeHttpHandler.aspx?Version={0}";
var getALInfoUrl = "WebIM/GetALInfoHandler.aspx?Version={0}";
var addALInfoOneUrl = "WebIM/AddALInfoOneHandler.aspx?Version={0}";
var updateALInfoOneUrl = "WebIM/UpdateALInfoOneHandler.aspx?Version={0}";
var deleteALInfoOneUrl = "WebIM/DeleteALInfoOneHandler.aspx?Version={0}";
var portrait = {defaultIcon:"images/portraits/fetion.jpg",group:"images/fetion.jpg",groupMsg:"images/groupSysMsg.png",groupList:"images/fetion(29).jpg",mobile:"images/portraits/mobile_user_head.jpg",offline_16:"images/status16/offline_16.jpg",offline_32:"images/status32/offline_32.jpg",robot_online_16:"images/status16/robot.jpg",robot_online_32:"images/status32/robot.jpg",robot_offline_16:"images/status16/robot_offline.jpg",robot_offline_32:"images/status32/robot_offline.jpg",verify:"images/status16/waiting.jpg",close:"images/status16/Closed.jpg",refuse:"images/status16/block.jpg",fetion_16:"images/status16/fetion_16.jpg",fetion_32:"images/status32/fetion_32.jpg"};
var __version = 0;
var __user = new UserDefault();
var __contactMap = new jHashMap();
var __groupMap = new jHashMap();
var __groupMsgMap = new jHashMap();
var __buddyList;
var __mainTabWindow;
var __contactTree;
var __moreMsgListDiv;
var __remindMsgDiv;
var __chatWindowMap = new jHashMap();
var __chatFooterItemMap = new jHashMap();
var __remindMsgItemMap = new jHashMap();
var __chatWinInitPosition = {top:102,left:474};
var __chatWinTopOffset = 26;
var __chatWinLeftOffset = 26;
var __lastPos = {top:102,left:0};
var __isLeft = true;
var __chatWinDefaultHeight = 365;
var __chatWinDefaultWidth = 468;
var __groupTree;
var __groupChatWinDefaultHeight = 365;
var __groupChatWinDefaultWidth = 480;
var __group_mem = new jHashMap();
var __phoneMap = new jHashMap();
var __maxMsgListOnTaskbar = 0;
var __isNormalLogout = false;
var __hideUserStatusSetterTimer = 0;
var __hideContactInfoTimer = 0;
var __showContactInfoTimer = 0;
var __initContactInfoTimer = 0;
var __hideOperateInfoTimer = 0;
var __tmpTimer = 0;
var __hideMoreMsgListTimer = 0;
var __hideRemindMsgTimer = 0;
var __setSmsWindow = null;
var __sessionId = "";
var __loginProcess = 0;
var __msgBeforeUnload = "此操作将退出网页版飞信，您确认要继续吗？";
var __searchPrompt = "查找好友...";
var __isFirstSearch = true;
var __isOnfocus = true;
var __staticTitle = "官方网页版飞信——在网页上与飞信好友聊天、免费发短信";
var __msgcomingTitle = "新消息";
var __callmeTitle = "新会话";
var __readmsgTitle = "未读消息";
var __flashTitleArray = new Array();
var __titleflashInterval = 0;
var __isOpenSound = true;
var __isOkGroupList = false;
var __isOkBuddyList = false;
var __leftSmsCount = 10000;
var isRefreshRecommendBuddyList = false;
var currentPageNum = 0;
var loadingPanel;
var divMask = $("#divMask");
function swfinfo(a) {
    sendToSwfJD(0);
}
function thisMovie(a) {
    if(navigator.appName.indexOf("Microsoft") != -1) {
        return window[a];
    } else {
        return document[a];
    }
}
function sendToSwfJD(b) {
    if(__loginProcess <= b) {
        __loginProcess = b;
    }
    if($("#logging").css("display") != "none") {
        var a = thisMovie("onlogin");
        if(a && a.setpercent) {
            a.setpercent(__loginProcess);
        }
    }
}
function sendToSwfTxt(b) {
    var a = thisMovie("onlogin");
    if($("#logging").css("display") != "none") {
        if(a && a.setmessage) {
            a.setmessage(b);
        }
    }
}
(function(c) {
    c(window).bind("load", function() {
        if(c.openPlatLogin.isOpenPlatLogin()) {
            c.openPlatLogin.login(b);
        } else {
            b();
        }
    }).bind("focus", function() {
        if(!c.browser.msie) {
            a();
        }
    }).bind("blur", function() {
        if(!c.browser.msie) {
            __isOnfocus = false;
        }
    });
    if(c.browser.msie) {
        c("input:checkbox").click(function() {
            this.blur();
            this.focus();
        });
    }
    c(document).bind("focusout", function() {
        __isOnfocus = false;
    }).bind("focusin", function() {
        a();
    });
    function b() {
        __sessionId = getCookie("webim_sessionid");
        deleteCookie("webim_sessionid", "/");
        if(!__sessionId) {
            c("#logging").css("display", "none");
            c("#maincontent").css("display", "none");
            window.location.href = loginPageUrl;
        } else {
            c("#logging").css("display", "");
            c(initialize);
            c.cacheImages();
        }
    }
    function a() {
        if(typeof __titleflashInterval != "undefined") {
            window.clearInterval(__titleflashInterval);
        }
        __flashTitleArray = new Array();
        __isOnfocus = true;
        if(document.title != __staticTitle) {
            document.title = __staticTitle;
        }
        var d = __chatWindowMap.values();
        var f = d.length;
        for(var e = 0; e < f; e++) {
            if(d[e].isOnFocus()) {
                d[e].focus();
                break;
            }
        }
    }
})(jQuery);
function initialize() {
    initUrls();
    $.initMaskPage();
    initNavigation();
    initMainTabWindow();
    initContentHeight();
    initUserStatusDiv();
    initContactCard();
    initOperateCard();
    initi__searchInput();
    initChatFooter();
    bindWindowEvent();
    sendToSwfJD(10);
    getPersonalInfo();
    IMSound.ajaxInit();
    getCred();
    setInterval("getCred()", 300000);
    getBanner();
}
function bindWindowEvent() {
    window.onbeforeunload = function(a) {
        a = window.event || a;
        if(!__isNormalLogout) {
            if(window.event) {
                a.returnValue = __msgBeforeUnload;
            }
            return(__msgBeforeUnload);
        }
    };
    $(window).bind("unload", function(a) {
        if(!__isNormalLogout) {
            logout(false);
        }
        deleteRemindMsgCookie(__user.sid);
    });
    $(window).bind("resize", onWindowResize);
}
function onWindowResize(f) {
    $.initMaskPage();
    initContentHeight();
    initGroupListHeight();
    var a = __chatWindowMap.values();
    var c = a.length;
    for(var b = 0; b < c; b++) {
        if(a[b].maxed) {
            a[b].max();
        }
    }
    var d = getMaxMsglistCount();
    if(__maxMsgListOnTaskbar != d) {
        __maxMsgListOnTaskbar = d;
        reInitialFooter();
        __moreMsgListDiv.hide();
    }
    $("#search_result").css("display", "none");
}
function initUrls() {
    loginPageUrl = __baseSSLUrl + loginPageUrl;
}
function initContentHeight() {
    var a = getViewportHeight() - $("#frame_top").outerHeight() - $("#personal_info").outerHeight() - $("#taskbar").outerHeight();
    $("#main_window").height(a);
    if(__mainTabWindow) {
        __mainTabWindow.adjustContentHeight();
    }
    if(__contactTree) {
        __contactTree.adjustHeight(0);
    }
    $("#main_contact_list").height(a);
    if(__contactTree) {
        __contactTree.adjustHeight();
    }
}
function initMainTabWindow() {
    __mainTabWindow = $.fn.jTabWindow({target:"main_window",id:"main_content"});
    $("#addbuddy_link").bind("click", function() {
        createAddBuddyTab();
        return false;
    });
    loadingPanel = $("#loadDiv");
    $("#btnRefreshRecommend").bind("click", function(b) {
        var a = $("#side").find(".text_box");
        if(a.hasClass("none")) {
            loadingPanel.removeClass("none");
            currentPageNum++;
            isRefreshRecommendBuddyList = true;
            GetPcRecommendBuddyListV2(8);
        }
        stopPropagation(b);
        preventDefault(b);
    });
    $("#topTabDownload").bind("click", function() {
        $.increaseCounters("500400003");
    });
    $(".phone_list").find(".phone_new > a").bind("click", function(a) {
        $.increaseCounter(500400014);
        createAddTXLTab();
        stopPropagation(a);
        preventDefault(a);
    });
    initTXLlist();
}
function createAddBuddyTab(c, d) {
    var b = "content/addBuddy.htm";
    if(c) {
        b += "?username=" + c;
        if(d) {
            b += "&addtype=" + d;
        }
    }
    var a = __mainTabWindow.getTabByUrl(b);
    if(!a) {
        __mainTabWindow.createTab("添加好友", b, true);
    } else {
        a.topTab.click();
    }
    hideAllChatWindow();
}
function createAddBuddyBatchTab(c) {
    var b = "content/addBuddyBatch.htm";
    if(c) {
        b += "?username=" + c;
    }
    var a = __mainTabWindow.getTabByUrl(b);
    if(!a) {
        __mainTabWindow.createTab("添加好友", b, true);
    } else {
        a.topTab.click();
    }
    hideAllChatWindow();
}
function createAddTXLTab(c) {
    var b = "content/addAndEditTXL.htm",d = "新建联系人";
    if(!!c) {
        b += "?guid=" + c;
        d = "编辑联系人";
    }
    var a = __mainTabWindow.getTabByUrl(b);
    if(!a) {
        __mainTabWindow.createTab(d, b, true);
    } else {
        a.topTab.click();
    }
    hideAllChatWindow();
}
function createFreeSMSTab(c, d, e) {
    var b = "content/freeSms.htm?guid=" + c + "&uid=" + d + "&mn=" + e;
    var a = __mainTabWindow.getTabByUrl(b);
    if(!a) {
        __mainTabWindow.createTab("发短信", b, true);
    } else {
        a.topTab.click();
    }
    hideAllChatWindow();
}
function createCustomPresTab() {
    var a = __mainTabWindow.getTabByUrl("content/customPresence.htm");
    if(!a) {
        __mainTabWindow.createTab("设置自定义状态", "content/customPresence.htm", true);
    } else {
        a.topTab.click();
    }
    hideAllChatWindow();
}
function hideAllChatWindow() {
    var a = __chatWindowMap.values();
    var c = a.length;
    for(var b = 0; b < c; b++) {
        if(a[b].isShow) {
            a[b].hide();
        }
    }
}
function openChatWindow(c, b) {
    var f = __contactMap.get(b);
    var d = f.status;
    if(d == ContactStatus.Verify || d == ContactStatus.Verify_Bklist) {
        $.fn.fetionHintWindow({modal:false}).show("待验证好友，无法与之会话", "提示", "OK");
        return;
    }
    if(d == ContactStatus.Refuse || d == ContactStatus.Refuse_Bklist) {
        $.fn.fetionHintWindow({modal:false}).show("对方尚未成为您的好友，不能与之会话", "提示", "OK");
        return;
    }
    if((d & ContactStatus.CloseService) == ContactStatus.CloseService || (d & ContactStatus.CloseService_Bklist) == ContactStatus.CloseService_Bklist) {
        $.fn.fetionHintWindow({modal:false}).show("对方已关闭飞信服务，您将无法与他/她进行任何会话。", "提示", "OK");
        return;
    }
    if(getContactType(f) == ContactType.Robot) {
        inviteRobot(b);
    }
    var a = getChatWindow(b);
    if(!a.isActived) {
        a.active();
        var e = getChatWinPosition();
        a.show(e.top, e.left);
    } else {
        if(!a.hasShown) {
            var e = getChatWinPosition();
            a.show(e.top, e.left);
        } else {
            a.show();
        }
    }
    if(typeof __isIE6 != "undefined" && __isIE6) {
        a.fixPosition();
    }
}
function getChatWindow(b) {
    var a;
    if(!__chatWindowMap.containsKey(b)) {
        a = $.fn.jChatWindow({
            maxRelative:"main_content",
            user:__user,
            buddy:__contactMap.get(b),
            defaultHeight:__chatWinDefaultHeight,
            defaultWidth:__chatWinDefaultWidth,
            onActive:onActiveChatWindow,
            onClosed:onCloseChatWindow,
            onFocus:onFocusChatWindow,
            onSendMessage:onSendMsg});
        __chatWindowMap.put(b, a);
    } else {
        a = __chatWindowMap.get(b);
    }
    return a;
}
function getChatWinPosition() {
    var b = new Object();
    b.top = __chatWinInitPosition.top;
    b.left = __chatWinInitPosition.left;
    var e = true;
    var a = __chatWindowMap.values();
    var d = a.length;
    while(e) {
        e = false;
        for(var c = 0; c < d; c++) {
            if(a[c].isShow && !a[c].maxed) {
                if(isLapOver(b, {"top":parseInt(a[c].css("top")),"left":parseInt(a[c].css("left"))})) {
                    b = adjustPosition(b);
                    if(__lastPos.left < b.left) {
                        __lastPos.left = b.left;
                    } else {
                        if(__lastPos.top > b.top) {
                            __lastPos.top = b.top;
                        }
                    }
                    e = true;
                    break;
                }
            }
        }
    }
    b.left -= parseInt($(".side").css("width"));
    return b;
}
function adjustPosition(a) {
    if((a.top + __chatWinTopOffset < __chatWinInitPosition.top - Math.abs(__chatWinTopOffset)) || (a.top + __chatWinTopOffset + 50 > getViewportHeight() - __chatWinDefaultHeight)) {
        a.top = __lastPos.top - 6;
        a.left = __lastPos.left + __chatWinLeftOffset;
        __chatWinInitPosition = a;
        if(getViewportWidth() - a.left - 450 < 230 && __isLeft) {
            __chatWinInitPosition.left = __chatWinInitPosition.left == 48 ? __chatWinInitPosition.left + 6 : 48;
            __isLeft = false;
        }
    } else {
        a.top += __chatWinTopOffset;
        a.left += __chatWinLeftOffset;
    }
    return a;
}
function isLapOver(b, a) {
    return(Math.abs(b.top - a.top) < 5) && (Math.abs(b.left - a.left) < 5);
}
function onActiveChatWindow(a) {
    if(!__chatFooterItemMap.containsKey(this.buddy.uid)) {
        var c = getUserPortraitUrl(this.buddy, "5");
        var b = $.fn.jFooterItem(this.buddy, a, c);
        __chatFooterItemMap.put(this.buddy.uid, b);
        reInitialFooter(b);
    }
}
function onCloseChatWindow(a) {
    if(__chatFooterItemMap.containsKey(this.buddy.uid)) {
        __chatFooterItemMap.get(this.buddy.uid).remove();
        __chatFooterItemMap.remove(this.buddy.uid);
        reInitialFooter();
    }
}
function onFocusChatWindow(a) {
    if(__chatFooterItemMap.containsKey(this.buddy.uid)) {
        __chatFooterItemMap.get(this.buddy.uid).removeClass("call");
    }
    if(__remindMsgItemMap.containsKey(this.buddy.uid)) {
        __remindMsgItemMap.get(this.buddy.uid).remove();
        __remindMsgItemMap.remove(this.buddy.uid);
        setRemindCountTotal();
    }
}
function onActiveGroupChatWindow(a) {
    if(!__chatFooterItemMap.containsKey("group_" + this.group.id)) {
        this.group.uid = this.group.id;
        this.group.statusClass = "af-group";
        this.group.displayName = this.group.n;
        var c = getGroupItemPortraitUrl(this.group, "1");
        var b = $.fn.jFooterItem(this.group, a, c);
        __chatFooterItemMap.put("group_" + this.group.id, b);
        reInitialFooter(b);
    }
}
function onFocusGroupChatWindow(a) {
    if(__chatFooterItemMap.containsKey("group_" + this.group.id)) {
        __chatFooterItemMap.get("group_" + this.group.id).removeClass("call");
    }
    if(__remindMsgItemMap.containsKey("group_" + this.group.id)) {
        __remindMsgItemMap.get("group_" + this.group.id).remove();
        __remindMsgItemMap.remove("group_" + this.group.id);
        setRemindCountTotal();
    }
}
function onCloseGroupChatWindow(a) {
    if(__chatFooterItemMap.containsKey("group_" + this.group.id)) {
        __chatFooterItemMap.get("group_" + this.group.id).remove();
        __chatFooterItemMap.remove("group_" + this.group.id);
        reInitialFooter();
    }
}
function onActiveGroupSysChatWindow(a) {
    if(!__chatFooterItemMap.containsKey("group_sys_" + a.attr("id"))) {
        this.group.uid = "group_sys_" + a.attr("id");
        this.group.statusClass = "af-groupSysMsg";
        this.group.displayName = a.find("h3").text();
        var b = $.fn.jFooterItem(this.group, a, portrait.groupMsg);
        b.removeClass().addClass("call");
        __chatFooterItemMap.put("group_sys_" + a.attr("id"), b);
        reInitialFooter(b);
    }
}
function onFocusGroupSysChatWindow(a) {
    if(__chatFooterItemMap.containsKey("group_sys_" + a.attr("id"))) {
        __chatFooterItemMap.get("group_sys_" + a.attr("id")).removeClass("call");
    }
}
function onCloseGroupSysChatWindow(b) {
    if(__chatFooterItemMap.containsKey("group_sys_" + b.attr("id"))) {
        __chatFooterItemMap.get("group_sys_" + b.attr("id")).remove();
        __chatFooterItemMap.remove("group_sys_" + b.attr("id"));
        reInitialFooter();
        var a = b.html();
        if(__groupMsgMap.containsKey(a)) {
            __groupMsgMap.remove(a);
        }
        if($("#" + b.attr("id")).attr("id")) {
            $("#" + b.attr("id")).remove();
        }
    }
}
function getPersonalInfo() {
    var a = new Object();
    a.success = getPersonalInfoSuccess;
    a.error = getPersonalInfoError;
    a.url = formatString(getPersonalInfoUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
var __gpiRetry = 0;
function getPersonalInfoSuccess(b, c) {
    var a = b;
    if(typeof a.rc == "number" && a.rc == 200) {
        __gpiRetry = 0;
        $.extend(__user, a.rv);
        extendUser();
        getGroupList();
        initUserInfo();
        sendToSwfJD(30);
        setRemindMsgCookie(__user.sid, __user.displayName, 0, __sessionId);
        getContactList();
        GetPcRecommendBuddyListV2(8);
    } else {
        if(typeof a.rc == "number" && a.rc == 310) {
            redirectLogin();
        } else {
            if(__gpiRetry < 3) {
                __gpiRetry++;
                window.setTimeout("getPersonalInfo()", 500);
            } else {
                __gpiRetry = 0;
                alert(formatString("获取用户个人信息失败，请重新登录。错误代码：{0}", typeof a.rc == "number" ? a.rc : 500));
                redirectLogin();
            }
        }
    }
}
function getPersonalInfoError(a, c, b) {
    if(__gpiRetry < 3) {
        __gpiRetry++;
        window.setTimeout("getPersonalInfo()", 500);
    } else {
        __gpiRetry = 0;
        alert(formatString("获取用户个人信息失败，请重新登录。错误原因：{0}", c));
        redirectLogin();
    }
}
function getBanner() {
    if(__bannerInfo == "") {
        $("#banner_list").css("display", "none");
    } else {
        var jsonObject = eval("(" + __bannerInfo + ")");
        if(jsonObject.length > 0) {
            $("#banner_list").css("display", "block");
            var fragment = "";
            var interval = 3000;
            var imgNum = 0;
            if(__isMsie) {
                for(var i = 0; i < jsonObject.length; i++) {
                    if(jsonObject[i].Key == "BannerImg_login") {
                        continue;
                    }
                    interval = jsonObject[0].Interval;
                    if(jsonObject[i].OffShelfTime == 1) {
                        var imgSrc = formatString(getBannerPicUrl, jsonObject[i].Img);
                        fragment += '<a href="' + jsonObject[i].Link + '" target="' + jsonObject[i].Target + '"><img src="' + imgSrc + '"  width="360" height="60" alt="' + jsonObject[i].Title + '" title="' + jsonObject[i].Title + '" /></a>';
                        imgNum++;
                    }
                }
            } else {
                for(var i = 0; i < jsonObject.length; i++) {
                    if(jsonObject[i].Key == "BannerImg_login") {
                        continue;
                    }
                    interval = jsonObject[0].Interval;
                    if(jsonObject[i].OffShelfTime == 1) {
                        fragment += '<a href="' + jsonObject[i].Link + '" target="' + jsonObject[i].Target + '"><img src="' + jsonObject[i].Img + '"  width="360" height="60" alt="' + jsonObject[i].Title + '" title="' + jsonObject[i].Title + '" /></a>';
                        imgNum++;
                    }
                }
            }
            fragment += '<div id="banner_info"></div>';
            $("#banner_list").html(fragment);
            imageCarousel.scroll(imgNum, "banner_list", interval, "banner_info");
        } else {
            $("#banner_list").css("display", "none");
        }
    }
}
function getContactList() {
    var a = new Object();
    a.success = getContactListSuccess;
    a.error = getContactListError;
    a.url = formatString(getContactListUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
var __gclRetry = 0;
function getContactListSuccess(e, g) {
    __contactTree = $.fn.jContactTree({target:"main_contact_list",isSinglePattern:true,user:__user,onBuddyItemDbClick:openChatWindow,onBuddyItemMouseOver:showContactCard,onBuddyItemMouseOut:hideContactCard,onBuddyItemRightClick:showOperateCard});
    sendToSwfJD(60);
    var b = e;
    if(typeof b.rc == "number" && b.rc == 200) {
        __gclRetry = 0;
        var c = b.rv.bds;
        c = c || (new Array());
        var f = c.length;
        for(var d = 0; d < f; d++) {
            var a = new ContactDefault();
            $.extend(a, c[d]);
            extendContact(a);
            __contactMap.put(c[d].uid.toString(), a);
        }
        __buddyList = b.rv.bl;
        __buddyList = __buddyList || (new Array());
        __buddyList.push({"id":0,"n":"未分组好友"});
        __buddyList.push({"id":-1,"n":"陌生人"});
        initContactList();
    } else {
        if(typeof b.rc == "number" && b.rc == 310) {
            redirectLogin();
        } else {
            if(__gclRetry < 3) {
                __gclRetry++;
                window.setTimeout("getContactList();", 500);
            } else {
                __gclRetry = 0;
                alert("由于网络原因，获取好友列表失败，请重新登录。");
                redirectLogin();
            }
        }
    }
}
function getContactListError(a, c, b) {
    if(__gclRetry < 3) {
        __gclRetry++;
        window.setTimeout("getContactList();", 500);
    } else {
        __gclRetry = 0;
        alert("由于网络原因，获取好友列表失败，请重新登录。");
        redirectLogin();
    }
}
function getRecommendReason(a) {
    var d = "你可能认识的人";
    if(!!a) {
        var e = a.split(";");
        var g = 0;
        for(var c = 0,b = e.length; c < b; c++) {
            var f = e[c].split(":");
            switch(f[0]) {case"want-to-meet":g = 5;d = "你的通讯录好友";break;case"recent-contact":if(g < 5) {
                g = 4;
                d = "你最近联系过的人";
            }break;case"have-same-friend":if(g < 4) {
                g = 3;
                d = f[1] + "位共同好友";
            }break;case"near":if(g < 3) {
                g = 2;
                d = "你附近的好友";
            }break;case"robot":if(g < 2) {
                g = 1;
                d = "有缘人";
            }break;default:d = "你可能认识的人";break;}
        }
    }
    return d;
}
function GetPcRecommendBuddyListV2(a) {
    var b = {};
    b.url = formatString(getPcRecommendBuddyListV2Url, __version++);
    b.type = "POST";
    b.dataType = "json";
    b.data = {"ssid":__sessionId,"c":a,"isIP":1,page:currentPageNum};
    b.cache = false;
    b.success = GetPcRecommendBuddyListV2Success;
    b.error = GetPcRecommendBuddyListV2Error;
    $.ajax(b);
}
function GetPcRecommendBuddyListV2Success(e, b) {
    if(e.rc == 200 && (!!e.rv)) {
        var k = $(".side"),h,m;
        if(isRefreshRecommendBuddyList) {
            k.find("ul").remove();
            isRefreshRecommendBuddyList = false;
        }
        h = k.find("ul");
        var g = h.length;
        if(g <= 0) {
            h = $('<ul class="List"></ul>');
        }
        for(var f = 0,d = e.rv.length; f < d; f++) {
            var n = e.rv[f];
            var c = formatString(getPortraitUrl, n.UserId, 3, 0, __user.uid);
            var a = "";
            if(f >= 4 || g > 0) {
                a = "none";
            }
            m = $('<li class="' + a + '" uid="' + n.UserId + '" sid="' + n.FetionId + '"><div class="image"><img src="' + c + '" alt="' + n.NickName + '" title="' + n.NickName + '" /></div>' + "<dl><dt>" + cnSubstr(n.NickName, 20, false) + "</dt><dd>" + getRecommendReason(n.Reason) + '</dd><dd><a href="javascript:;">加好友</a><a href="javascript:;">打招呼</a></dd></dl></li>');
            h.append(m);
            var l = m.find("dl > dd:last");
            l.find("a:first").bind("click", function(j) {
                $.increaseCounter(501900002);
                var i = $(this).parent().parent();
                AddBuddy({t:"1",a:0,u:i.parent().attr("sid"),p:0,d:__user.displayName.substr(0, 5),l:i.find("dt").text(),b:1,s:0,c:"",cid:"",successCallback:function() {
                    i.find("dd:last").html("").html('<span style="color:#000;">请求已发送</span>');
                    var o = i.parent().attr("uid");
                    AddRecommendBuddyNotify(o);
                    setTimeout(function() {
                        var q = $(".side > ul");
                        $(i.parent()).remove();
                        q.find("li[class='none']:first").removeClass("none");
                        var p = q.find("li[class='none']").length;
                        if(p <= 1) {
                            GetPcRecommendBuddyListV2(3);
                        }
                    }, 3000);
                }});
                stopPropagation(j);
                preventDefault(j);
            });
            l.find("a:last").bind("click", function(i) {
                $.increaseCounter(501900001);
                var o = $(this).parent().parent().parent();
                var j = __contactMap.get(o.attr("uid"));
                if(!!j) {
                    openChatWindow(this, j.uid);
                } else {
                    addChatFriendV2(2, o.attr("sid"), function(q, s) {
                        if(typeof q.rc == "number" && q.rc == 200) {
                            var r = q.rv;
                            var p = new ContactDefault();
                            if(!buddyListContains("-1")) {
                                __buddyList.push({"id":-1,"n":"陌生人"});
                                __contactTree.insertBuddyList({"id":-1,"n":"陌生人"});
                            }
                            p.uid = r.uid;
                            p.uri = r.uri;
                            p.bl = "-1";
                            p.ct = 2;
                            extendContact(p);
                            __contactMap.put(p.uid, p);
                            __contactTree.insertBuddyItem(p, p.bl);
                            openChatWindow(this, p.uid);
                        }
                    });
                }
                stopPropagation(i);
                preventDefault(i);
            });
        }
        if(g <= 0) {
            if(e.rv.length <= 0) {
                k.find(".text_box").removeClass("none");
            } else {
                k.find(".text_box").addClass("none");
                k.append(h);
            }
        } else {
            if(h.find("li").length <= 0) {
                k.find(".text_box").removeClass("none");
            }
        }
    } else {
        var k = $(".side");
        if(k.find("ul").length <= 0 || k.find("ul>li").length <= 0) {
            k.find(".text_box").removeClass("none");
        }
    }
    loadingPanel.addClass("none");
}
function GetPcRecommendBuddyListV2Error(b, d, c) {
    var a = $(".side");
    if(a.find("ul").length <= 0 || a.find("ul>li").length <= 0) {
        a.find(".text_box").removeClass("none");
    }
    loadingPanel.addClass("none");
    isRefreshRecommendBuddyList = false;
}
function AddRecommendBuddyNotify(a) {
    var b = {};
    b.url = formatString(AddRecommendBuddyNotifyUrl, __version++);
    b.type = "post";
    b.dataType = "json";
    b.data = {"uid":__user.uid,"duid":a,"ssid":__sessionId};
    b.cache = false;
    b.success = function(c, d) {
    };
    b.error = function(c, e, d) {
    };
    $.ajax(b);
}
function getConnect() {
    var a = new Object();
    a.success = getConnectSuccess;
    a.error = getConnectError;
    a.url = formatString(getConnectUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId,"reported":getReportedData()};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
var __getConnErrorTimes = 0;
var __getCoonTimes = 0;
function getConnectSuccess(e, d) {
    var j = e;
    __getCoonTimes++;
    if(__getCoonTimes % 3 == 0) {
        activeRemindMsgCookie();
    }
    if(typeof j.rc == "number" && j.rc == 200) {
        __getConnErrorTimes = 0;
        var h = j.rv;
        var c = h.length;
        var a = false;
        if(c < 10) {
            a = false;
        } else {
            var b = 0;
            for(var f = 0; f < c; f++) {
                if(h[f].DataType == 2 || h[f].DataType == 6) {
                    b++;
                }
                if(b >= 10) {
                    a = true;
                    break;
                }
            }
        }
        function g(B) {
            if(B >= c) {
                if(a) {
                    insertBuddyToList(__contactMap.values(), function() {
                        window.setTimeout(getConnect, 100);
                    });
                } else {
                    window.setTimeout(getConnect, 100);
                }
                return;
            }
            switch(h[B].DataType) {
                case 1:updatePersonalInfo(h[B].Data);break;
                case 2:
                    if(h[B].Data.uid == __user.uid) {
                        updatePersonalInfo(h[B].Data);
                    } else {
                        updateContactInfo(h[B].Data, !a);
                    }
                    break;
                case 3:
                    if(h[B].Data.msgType == 2) {
                        receiveMsgs(h[B].Data);
                        startSound("newmessage");
                    } else {
                        if(h[B].Data.msgType == 3 || h[B].Data.msgType == 4) {
                            sendMsgFailed(h[B].Data);
                        }
                    }
                    break;
                case 4:
                    if(h[B].Data.ec == 900) {
                        logout(false);
                        startSound("sys");
                        $.fn.fetionHintWindow().show("您已经从其他终端登录！", "提示", "OK", function() {
                            __isNormalLogout = true;
                            window.location.href = __officialSiteUrl + "/account/loginout?ul=" + loginPageUrl;
                        });
                    } else {
                        if(h[B].Data.ec >= 902 && h[B].Data.ec <= 905) {
                            logout(false);
                            $.fn.fetionHintWindow().show("您已退出网页版飞信。<br/>单击确定后返回登录页面。", "提示", "OK", function() {
                                redirectLogin();
                            });
                        }
                    }
                    return;
                    break;
                case 5:
                    receiveInvite(h[B].Data);
                    startSound("sys");
                    break;
                case 6:
                    if(h[B].Data.ba == 1) {
                        addBuddyWithReply(h[B].Data, !a);
                    } else {
                        if(h[B].Data.ba == 2) {
                            removeBuddyWithReply(h[B].Data, !a);
                        } else {
                            if(h[B].Data.ba == 3) {
                                updateBuddyWithReply(h[B].Data, !a);
                            }
                        }
                    }
                    break;
                case 7:
                    if(h[B].Data.InfoType == 1) {
                        dealWithCallme(h[B].Data.Info);
                    }break;
                case 8:
                    if(h[B].Data.length > 0) {
                        var m = h[B].Data;
                        m.sort(sort_by("n", false));
                        for(var y = 0; y < m.length; y++) {
                            var z = m[y].GData;
                            switch(m[y].GDataType) {case 1:var n = z;n.crc = "1234";$.popWinMsg({gName:n.gN,fName:n.iNn,group:n,msgN:"msg4",isLink:true,handleEntity:n,consent:handleInviteGroup,isClose:false});break;case 2:var x = z;exitGroup(x.gUri, x);break;case 3:var s = z;apvResult(s);break;case 4:var C = z;var t = jGroupUriToId(C.GroupUri);$.popWinMsg({gName:C.GroupName,group:__groupMap.get(t),msgN:"msg6"});removeGroupUi(t, 2);break;case 5:var D = z.DeleteMemberEntity;for(var A = 0; A < D.length; A++) {
                                deleteGroupMember(D[A]);
                            }break;case 6:var v = z.ApplyGroupEntity[0];$.popWinMsg({gName:v.n,fName:v.ApplyGroupUserEntity[0].Nickname,group:v,iR:v.ApplyGroupUserEntity[0].Note == null ? "" : v.ApplyGroupUserEntity[0].Note,msgN:"msg13",handleEntity:v,consent:handleApplication,isClose:false});break;case 7:var u = z;var l = new GroupMemDefault();l.MemberUri = u.MemberUri;l.minn = u.minn;l.i = 3;initGroupMemList(u.gUri, l);var t = jGroupUriToId(u.gUri);var r = __groupMap.get(t);var p = jMemUriToId(u.MemberUri);if(p != __user.sid) {
                                $.popWinMsg({gName:u.n,fName:l.minn,group:r,msgN:"msg5"});
                            }break;case 9:updateGroupInfo(z, true);if(z.members) {
                                for(var E = 0; E < z.members.length; E++) {
                                    initGroupMemList(z.gUri, z.members[E]);
                                }
                            }break;case 10:var q = z.PermissionChangedGroupEntity;groupMemPriChange(q);break;case 14:var w = z;w.crc = "1234";$.popWinMsg({gName:w.n,fName:w.iNn,group:w,msgN:"msg10",iR:w.iR,joinItems:w.JoinItem,handleEntity:w,consent:approveInviteGroup,isClose:false});break;case 16:var o = z;var t = jGroupUriToId(o.gUri);var r = __groupMap.get(t);$.popWinMsg({gName:r.n,fName:o.iNn,group:r,msgN:"msg5"});break;case 17:updateGroupInfo(z, false, true);break;case 21:receiveGroupMsgs(z);break;default:break;}
                        }
                    }
                    break;
                case 9:
                    break;
                default:break;
            }
            B++;
            window.setTimeout(function() {
                g(B);
            }, 20);
        }
        g(0);
    } else {
        if(typeof j.rc == "number" && j.rc == 310) {
            redirectLogin();
        } else {
            if(typeof j.rc == "number" && j.rc == 304) {
                window.setTimeout(getConnect, 1000);
            } else {
                window.setTimeout(getConnect, 1000);
            }
        }
    }
}
function getConnectError(a, c, b) {
    if(__getConnErrorTimes <= 60) {
        __getConnErrorTimes++;
        window.setTimeout(getConnect, 1000);
    } else {
        alert("网络连接已断开，单击确定后返回登录页面。");
        redirectLogin();
    }
}
function getReportedData() {
    var c = __dataReportMap;
    if(__user && !!c && c.size() > 0) {
        var a = __user.uid + "|" + "default";
        var b = c.keys();
        $.each(b, function(e, d) {
            a += "|" + d + "_" + c.get(d);
        });
        c.clear();
        return a;
    } else {
        return"";
    }
}
function getGroupList() {
    var a = new Object();
    a.success = getGroupListSuccess;
    a.error = getGroupListError;
    a.url = formatString(getGroupListUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
    __groupTree = $.fn.jGroupTree({target:"main_contact_list",isSinglePattern:true,onBuddyItemDbClick:openGroupChatWindow,onBuddyItemClick:function() {
    },onBuddyItemMouseOver:function() {
    }});
}
var __gglRetry = 0;
function getGroupListSuccess(c, a) {
    var j = c;
    if(typeof j.rc == "number" && j.rc == 200) {
        __gglRetry = 0;
        if(c.rv.GroupListInfo != null) {
            var g = "";
            for(var d = 0; d < c.rv.GroupListInfo.length; d++) {
                var b = c.rv.GroupListInfo[d].GroupUri;
                g += b + ",";
            }
            getGroupPerInfo(g);
            startGroupSubscribe(g);
            var e = j.rv.GroupListInfo;
            e = e || (new Array());
            var f = e.length;
            for(var d = 0; d < f; d++) {
                var h = new GroupDefault();
                h.gUri = e[d].GroupUri;
                h.i = e[d].Identity;
                extendGroupItem(h);
                __groupMap.put(h.id, h);
            }
        }
        initGroupList();
    } else {
        if(typeof j.rc == "number" && j.rc == 310) {
            redirectLogin();
        } else {
            if(__gglRetry < 3) {
                __gglRetry++;
                window.setTimeout("getGroupList();", 500);
            } else {
                __gglRetry = 0;
                alert(formatString("由于网络原因，获取群组列表失败，请重新登录。错误代码：{0}", j.rc));
                redirectLogin();
            }
        }
    }
}
function getGroupListError(a, c, b) {
    if(__gglRetry < 3) {
        __gglRetry++;
        window.setTimeout("getGroupList();", 500);
    } else {
        __gglRetry = 0;
        alert(formatString("由于网络原因，获取群组列表失败，请重新登录。错误原因：{0}", c));
        redirectLogin();
    }
}
function initGroupListHeight() {
    if(__groupTree) {
        __groupTree.adjustHeight();
    }
}
function initGroupList() {
    var a = __groupMap.values();
    insertGroupToList(a, initMainUi);
}
function insertGroupToList(c, h) {
    var g = new Object();
    if(c.length == 0) {
        g[0] = __groupTree.createBuddyList(false);
    } else {
        g[0] = __groupTree.createBuddyList(true);
    }
    var d = c.length;
    var e = g[0];
    for(var b = 0; b < d; b++) {
        var j = __groupTree.createGroupItem(c[b]);
        e.pushContent(j);
    }
    var f = "";
    var a = g[0];
    f += a.toHtmlString();
    __groupTree.setInnerHtml(f);
    __isOkGroupList = true;
    if(typeof h == "function") {
        if(__isOkBuddyList && __isOkGroupList) {
            h();
        }
    }
    f = null;
}
var __spRetry = 0;
function setPersonalInfo(b) {
    var a = new Object();
    a.url = formatString(setPersonalInfoUrl, __version++);
    a.type = "POST";
    a.data = {"Impresa":b,"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    a.success = function(d, e) {
        var c = d;
        if(c.rc && c.rc == 200) {
            __spRetry = 0;
            $("#user_impresa").text(!b ? "请输入心情短语..." : cnSubstr(b, 40, false));
            __user.i = b;
        } else {
            retrySetPersonInfo(b, c.rc);
        }
    };
    a.error = function(c, e, d) {
        retrySetPersonInfo(b, e);
    };
    $.ajax(a);
}
function updateLocalName(a) {
    $.ajax({type:"POST",url:formatString(setContactInfoUrl, __version++),data:{"ssid":__sessionId,"UserId":a,"localName":"testss" + __version},success:function(b) {
        var c = __contactMap.get(a);
        updateContactInfo(c);
    }});
}
function retrySetPersonInfo(a, b) {
    if(__spRetry < 3) {
        __spRetry++;
        var c = this.data;
        window.setTimeout(function() {
            setPersonalInfo(a);
        }, 500);
    } else {
        __spRetry = 0;
        alert("设置个人资料失败，错误原因：" + b);
    }
}
function setSmsOnline(b) {
    var a = new Object();
    a.success = setSmsOnlineSuccess;
    a.error = setSmsOnlineError;
    a.url = formatString(setSmsOnlineUrl, __version++);
    a.type = "POST";
    a.data = b;
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
function setSmsOnlineSuccess(b, c) {
    var a = b;
    if(typeof a.rc == "number" && a.rc == 200) {
    } else {
    }
}
function setSmsOnlineError(a, c, b) {
}
var __setPresRetry = 0;
function setPresence(c, b) {
    var a = new Object();
    a.url = formatString(setPresenceUrl, __version++);
    a.type = "POST";
    a.data = c;
    a.dataType = "json";
    a.cache = false;
    a.success = function(e, g) {
        var d = e;
        if(typeof d.rc == "number" && d.rc == 200) {
            __setPresRetry = 0;
            updateStatusDesc(b);
        } else {
            if(__setPresRetry < 3) {
                __setPresRetry++;
                var f = objects(this.data);
                window.setTimeout(function() {
                    setPresence(f, b);
                }, 500);
            } else {
                __setPresRetry = 0;
                alert("设置状态失败, 错误原因：" + d.rc);
            }
        }
    };
    a.error = function(d, g, f) {
        if(__setPresRetry < 3) {
            __setPresRetry++;
            var e = objects(this.data);
            window.setTimeout(function() {
                setPresence(e, b);
            }, 500);
        } else {
            __setPresRetry = 0;
            alert("设置状态失败, 错误原因：" + g);
        }
    };
    $.ajax(a);
}
function sendDirectSMS(d, c, a) {
    var b = new Object();
    b.type = "POST";
    b.dataType = "json";
    b.cache = false;
    b.data = {"UserName":__user.uid,"msg":c,"receivers":a,"ssid":__sessionId};
    b.url = formatString(SendDirectSMSUrl, __version++);
    b.success = function(e, f) {
        if(!!e && typeof e.rc == "number") {
            switch(e.rc) {case 280:break;case 401:d.logErrorInfo("没有给对方发送短信的授权");break;case 403:d.logErrorInfo("服务器关闭直接短信功能");break;case 405:d.logErrorInfo("目的用户不接受");break;case 406:d.logErrorInfo("消息内容包含敏感词");break;case 422:d.logErrorInfo("未开通直接短信服务");break;case 486:d.logErrorInfo("您暂时无法发送短信，请稍后再试。");break;case 488:d.logErrorInfo("用户停机");break;case 494:d.logErrorInfo("您的飞信账号处于系统保护状态，限制了该功能的使用。请手机发送短信U到12520解除限制后重新登录。");break;default:d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));break;}
        }
    };
    b.error = function() {
        d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));
    };
    $.ajax(b);
}
function sendSMS(d, c, a) {
    var b = new Object();
    b.type = "POST";
    b.dataType = "json";
    b.cache = false;
    b.data = {"UserName":__user.uid,"Message":c,"Receivers":a,"ssid":__sessionId};
    b.url = formatString(sendSMSUrl, __version++);
    b.success = function(e, f) {
        if(typeof e.rc == "number") {
            switch(e.rc) {
                case 200:break;
                case 310:d.logErrorInfo("Session会话过期！");break;
                case 311:d.logErrorInfo("客户端请求参数错误！");break;
                case 400:d.logErrorInfo("信令参数错误！");break;
                case 406:d.logErrorInfo("消息有敏感词！");break;
                case 422:d.logErrorInfo("没有开通直接短信！");break;
                case 486:
                    __leftSmsCount = e.rv.day;
                    if(!__user.isCM) {
                        d.logErrorInfo("您的短信配额已用完，暂时无法发送短信。");
                    } else {
                        d.logErrorInfo("对不起，今日您发送短信数量已达上限，请明天再发");
                    }
                    break;
                case 4861:
                    __leftSmsCount = e.rv.day;
                    if(!__user.isCM) {
                        d.logErrorInfo("您的短信配额已用完，暂时无法发送短信。");
                    } else {
                        d.logErrorInfo("对不起，今日您发送短信数量已达上限，请明天再发");
                    }
                    break;
                case 4862:
                    __leftSmsCount = e.rv.month;
                    if(!__user.isCM) {
                        d.logErrorInfo("您的短信配额已用完，暂时无法发送短信。");
                    } else {
                        d.logErrorInfo("对不起，本月您发送短信数量已达上限，请下月再发");
                    }
                    break;
                case 4863:
                    d.logErrorInfo("超过未发送限制！");
                    break;
                case 4864:d.logErrorInfo("接收人数限制！");break;
                case 4865:d.logErrorInfo("发送时间限制！");break;
                case 494:d.logErrorInfo("您的飞信账号处于系统保护状态，限制了该功能的使用。请手机发送短信U到12520解除限制后重新登录。");break;
                default:d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));break;
            }
        }
    };
    b.error = function(e, g, f) {
        d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));
    };
    $.ajax(b);
}
function sendMessage(g, b, f, a, e) {
    var d = __contactMap.get(b);
    if(__user.isCM && (d.isCT || d.isCU) && a) {
        sendDirectSMS(g, f, b);
        return;
    }
    if((__user.isCT || __user.isCU) && d.isCM && a) {
        sendSMS(g, f, b);
        return;
    }
    var c = new Object();
    c.success = function(i, j) {
        var h = i;
        if(typeof h.rc == "number" && h.rc >= 200 && h.rc < 300) {
        } else {
            if(typeof h.rc == "number" && h.rc == 420) {
                g.logErrorInfo("对方版本过旧，您无法向其发起陌生人消息。");
                g.inputPermission = 420;
                g.enableInput(false);
            } else {
                if(typeof h.rc == "number" && h.rc == 486) {
                    if(!__user.isCM) {
                        g.logErrorInfo("您的短信配额已用完，暂时无法发送短信。");
                    } else {
                        g.logErrorInfo("您暂时无法发送短信，请稍后再试。");
                    }
                } else {
                    if(typeof h.rc == "number" && h.rc == 494) {
                        g.logErrorInfo("您的飞信账号处于系统保护状态，限制了该功能的使用。请手机发送短信U到12520解除限制后重新登录。");
                    } else {
                        if(typeof h.rc == "number" && h.rc == 405) {
                        } else {
                            g.logErrorInfo("消息发送失败: " + cnSubstr(f, 18));
                        }
                    }
                }
            }
        }
    };
    c.error = function(h, j, i) {
        g.logErrorInfo("消息发送失败: " + cnSubstr(f, 18));
    };
    c.url = formatString(sendMessageUrl, __version++);
    c.type = "POST";
    c.data = {"To":b,"IsSendSms":a ? "1" : "0","msg":f,"ssid":__sessionId};
    c.dataType = "json";
    c.cache = false;
    $.ajax(c);
}
function inviteRobot(b) {
    var a = new Object();
    a.url = formatString(sendMessageUrl, __version++);
    a.type = "POST";
    a.data = {"To":b,"IsSendSms":"0","msg":"WEB","ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
function OpBuddy(c, b, e, d) {
    var a = new Object();
    a.success = function(g, k) {
        var f = g;
        if(typeof f.rc == "number" && f.rc == 200) {
            var i = __contactMap.get(c);
            if(b == "0") {
                __contactMap.remove(c);
                extendContact(i);
                eachBuddyList(i, function(l) {
                    __contactTree.deleteBuddyItem(parseInt(l), i.uid);
                });
            } else {
                if(i) {
                    i.isBk = (b == "2" ? 0 : 1);
                    extendContact(i);
                    eachBuddyList(i, function(l) {
                        __contactTree.updateBuddyItem(parseInt(l), i);
                    });
                    var h = getUserPortraitUrl(i, "5");
                    if(__chatFooterItemMap.containsKey(i.uid)) {
                        __chatFooterItemMap.get(i.uid).updateItem(i, h);
                    }
                    if(__remindMsgItemMap.containsKey(i.uid)) {
                        __remindMsgItemMap.get(i.uid).updateItem(i, h);
                    }
                    if(__chatWindowMap.containsKey(i.uid)) {
                        __chatWindowMap.get(i.uid).setBuddyStatus(i);
                    }
                } else {
                    if(b == "1") {
                        var j = new ContactDefault();
                        j.uid = parseInt(c);
                        j.isBk = 1;
                        j.ct = -1;
                        __contactMap.put(c, j);
                    }
                }
            }
            if(typeof e == "function") {
                e(g, k);
            }
        }
    };
    a.error = function(f, h, g) {
        if(typeof d == "function") {
            d(f, h, g);
        }
    };
    a.url = formatString(opBuddyUrl, __version++);
    a.type = "POST";
    a.data = {"To":c,"Op":b,"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
function handleAddBuddy(c, a) {
    if(typeof a == "undefined" || a == null) {
        a = 0;
    }
    var b = new Object();
    b.success = function(e, f) {
        var d = e;
        if(typeof d.rc == "number" && d.rc == 200 && c.data.Result == 1) {
            $.extend(d.rv, c.data.Buddy);
            addBuddySuccess(d.rv);
        }
        if(c.data.Blocked == 1 && c.data.Result == 0) {
            OpBuddy(c.data.Buddy.uid, "1");
        }
    };
    b.error = function(d, f, e) {
        if(a < 3) {
            a++;
            handleAddBuddy(c, a);
        }
    };
    b.url = formatString(handleAddBuddyUrl, __version++);
    b.type = "POST";
    b.data = {"BuddyId":c.data.Buddy.uid,"Result":c.data.Result,"LocalName":c.data.LocalName,"BuddyList":c.data.BuddyList,"ssid":__sessionId};
    b.dataType = "json";
    b.cache = false;
    $.ajax(b);
}
function addChatFriend(c, d, b) {
    var a = new Object();
    if(typeof d == "function") {
        a.success = d;
    }
    if(typeof b == "function") {
        a.error = b;
    }
    a.url = formatString(addChatFriendUrl, __version++);
    a.type = "POST";
    a.data = {"Uri":c,"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
function addChatFriendV2(a, e, d, b) {
    var c = {};
    c.url = formatString(addChatFriendUrl, __version++);
    c.type = "post";
    c.dataType = "json";
    c.data = {"addType":a,"userValue":e,"ssid":__sessionId};
    c.cache = false;
    if(typeof d == "function") {
        c.success = d;
    }
    if(typeof b == "function") {
        c.error = b;
    }
    $.ajax(c);
}
function AddBuddy(b) {
    var c = $.extend({t:0,a:0,u:"",p:0,d:__user.displayName.substr(0, 5),l:"",b:1,s:0,c:"",cid:"",successCallback:function() {
    },failCallback:function() {
    },successCallback2:function(d) {
    }}, b);
    var a = new Object();
    a.url = formatString(addBuddyUrl, __version++);
    a.type = "POST";
    a.dataType = "json";
    a.cache = false;
    a.data = {"type":c.t,"UserName":c.u,"AddType":c.a,"PhraseId":c.p,"Desc":c.d,"LocalName":c.l,"BuddyLists":c.b,"SubscribeFlag":c.s,"Ccp":c.c,"CcpId":c.cid,"ssid":__sessionId};
    a.success = function(d, e) {
        c.successCallback2(d);
        if(typeof d.rc == "number" && d.rc == 200) {
            addBuddySuccess(d.rv);
            c.successCallback();
        } else {
            if(typeof d.rc == "number" && d.rc == 520) {
                alert("您的好友数已达上限。");
            } else {
                if(typeof d.rc == "number" && d.rc == 521) {
                    alert(c.u + "已在您的好友列表中。");
                } else {
                    if(typeof d.rc == "number" && d.rc == 522) {
                        alert("对不起，您是飞信互联网用户（未绑定手机号），不能添加未开通移动业务的移动手机号为好友。");
                    } else {
                        if(typeof d.rc == "number" && d.rc == 523) {
                            alert("不能将自己添加为好友。");
                        } else {
                            if(typeof d.rc == "number" && d.rc == 312) {
                                alert("您输入的验证码有误，请重新输入。");
                                $("#addBuddy_ccps").select();
                            } else {
                                if(typeof d.rc == "number" && d.rc == 404) {
                                    alert("您所输入的号码不存在。");
                                } else {
                                    if(typeof d.rc == "number" && d.rc == 486) {
                                        alert("您添加好友的请求过于频繁，请稍后再试。");
                                    } else {
                                        if(typeof d.rc == "number" && d.rc == 495) {
                                            alert("对不起，您权限不足，不能添加此好友。");
                                        } else {
                                            if(typeof d.rc == "number" && d.rc == 494) {
                                                alert("您的飞信账号处于系统保护状态，限制了该功能的使用。请手机发送短信U到12520解除限制后重新登录。");
                                            } else {
                                                if(typeof d.rc == "number" && d.rc == 400) {
                                                    alert("添加好友失败，请您检查对方的手机号或者飞信号是否填写正确。");
                                                } else {
                                                    alert("添加好友失败，请重试。错误代码：800000" + d.rc);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    };
    a.error = function(d, f, e) {
        c.failCallback();
        alert("添加好友失败，请重试。错误原因：" + f);
    };
    $.ajax(a);
}
function logout(b) {
    __isNormalLogout = true;
    var a = new Object();
    a.url = formatString(logoutUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    if(b) {
        window.setTimeout("window.location.href = '" + loginPageUrl + "'", 500);
    }
    $.ajax(a);
}
function initUserInfo() {
    var a = cnSubstr(__user.displayName, 18);
    $("#welcome_name").text(a);
    $("#user_name").text(a);
    $("#user_sid").text(__user.sid);
    $("#user_impresa").text(cnSubstr(__user.i, 40, false) || "请输入心情短语...");
    $("#user_portrait").attr("src", getUserPortraitUrl(__user, "3"));
    if(__user.isCM && __user.cas == "0") {
        if(__isSmsOnline.test(__user.sms)) {
            $("#input_rec_msg").removeAttr("disabled").attr("checked", "true");
        } else {
            $("#input_rec_msg").removeAttr("disabled").removeAttr("checked");
        }
    } else {
        if(__user.isCT || __user.isCU) {
            $("#input_rec_msg").css({"display":"none"});
            $("#input_rec_msg").next().css({"display":"none"});
            $("#input_rec_msg").parent().prev().addClass("none");
        } else {
            $("#input_rec_msg").removeAttr("checked").attr("disabled", "disabled");
        }
    }
    if(__user.ca.toLowerCase() == "cmcc") {
        if(!$("#vip_icon").attr("id")) {
            $(".service_icon").append('<li><a id="vip_icon" href="' + __vipUrl + '" title="会员" class="sicon-vip" target="_blank"></a></li>');
        }
    }
}
function initUserStatusDiv() {
    var b = $("#user_impresa");
    var i = $("#user_impresa_input");
    var e = i.find("input:first");
    var f = $("#user_status");
    var d = $("#set_user_status");
    var c = getCookie("webim_usersid");
    $("#welcome_name").text(c);
    $("#user_name").text(c);
    $("#user_sid").text(c);
    var a = getCookie("webim_userstatus");
    switch(a) {case"0":f.find("em").text("( 隐身 )");break;case"100":f.find("em").text("( 离开 )");break;case"400":f.find("em").text("( 在线 )");break;case"600":f.find("em").text("( 忙碌 )");break;default:f.find("em").text("( 在线 )");break;}
    b.text("请输入心情短语...");
    b.bind("click", function() {
        b.css("display", "none");
        i.css("display", "");
        e.focus();
    }).bind("mouseover", function() {
        b.removeClass().addClass("sword_chg");
    }).bind("mouseout", function() {
        b.removeClass();
    });
    e.bind("focus", function() {
        e.val(__user.i).select();
    }).bind("blur", function() {
        var j = e.val().trim();
        if(j != __user.i) {
            setPersonalInfo(j);
        }
        b.css("display", "");
        i.css("display", "none");
    }).bind("keydown", function(k) {
        k = window.event || k;
        if(k.keyCode == 13) {
            var j = e.val().trim();
            if(j != __user.i) {
                setPersonalInfo(j);
            }
            b.css("display", "");
            i.css("display", "none");
        }
    });
    f.bind("mouseover", function() {
        if(this.className != "focus") {
            this.className = "hover";
        }
    }).bind("mouseout", function() {
        if(this.className != "focus") {
            this.className = "";
        } else {
            __hideUserStatusSetterTimer = setTimeout(function() {
                f.removeClass();
                d.css("display", "none");
            }, 500);
        }
    }).bind("click", function() {
        if(this.className != "focus") {
            this.className = "focus";
            var j = $(this).offset();
            d.css({"display":"block","top":"61px","left":(j.left + 3) + "px"});
        } else {
            this.className = "hover";
            d.css("display", "none");
        }
    });
    d.bind("mouseover", function(j) {
        clearTimeout(__hideUserStatusSetterTimer);
    }).bind("mouseout", function(j) {
        if(isMouseOut(this, j)) {
            f.removeClass();
            d.css("display", "none");
        }
    });
    (d.find("li")).bind("mouseover", function() {
        this.className = "hover";
    }).bind("mouseout", function() {
        this.className = "";
    }).bind("click", function() {
        f.removeClass();
        d.css("display", "none");
        if(this.id == "userdefined_status") {
            createCustomPresTab();
        } else {
            setPresence({"Presence":$(this).attr("status"),"Custom":$(this).text(),"ssid":__sessionId}, $(this).text());
        }
    });
    var g = $("#input_rec_msg");
    g.removeAttr("checked").attr("disabled", "disabled");
    g.bind("click", function() {
        d.css({"display":"none"});
        if(!this.checked) {
            this.checked = "";
            if(!__setSmsWindow) {
                __setSmsWindow = $.fn.setSmsWindow({onConfirm:function(j, l) {
                    var k = "1.00:00:00";
                    switch(l) {case"1":k = "1.00:00:00";break;case"2":k = "3.00:00:00";break;case"3":k = "7.00:00:00";break;case"4":k = "36500.00:00:00";break;default:break;}
                    setSmsOnline({"ReceiveSms":"0","Time":k,"ssid":__sessionId});
                },onCancel:function() {
                    g.attr("checked", "true");
                }});
                __setSmsWindow.show();
            } else {
                __setSmsWindow.show();
            }
        } else {
            this.checked = "checked";
            setSmsOnline({"ReceiveSms":"0","ssid":__sessionId});
        }
    });
    var h = $("#input_rec_sound");
    h.click(function() {
        if(h.attr("checked") == true) {
            __isOpenSound = true;
        } else {
            __isOpenSound = false;
        }
    });
}
function initContactList() {
    var a = __contactMap.values();
    insertBuddyToList(a, initMainUi);
}
function initMainUi() {
    $("#logging").css("display", "none");
    getConnect();
    var a;
    if(__user.creds && __user.creds.length > 0) {
        a = formatString(__activityUrl, encodeURIComponent(__user.creds[0].c));
    } else {
        a = formatString(__activityUrl, "");
    }
    __mainTabWindow.createTab("发短信", "content/freeSms.htm", false);
    $("#tab_1").click();
    $("#tab_1").click(function() {
        $.increaseCounters("500400001");
    });
    initContentHeight();
    initGroupListHeight();
    setTimeout(function() {
        getALInfo();
    }, 1000);
}
function insertBuddyToList(e, m) {
    __buddyList.sort(buddyListComparator);
    var k = new Object();
    var j = __buddyList.length;
    for(var d = 0; d < j; d++) {
        var c = __contactTree.isBuddyListOpen(__buddyList[d].id);
        k[__buddyList[d].id] = __contactTree.createBuddyList(__buddyList[d], c);
    }
    var f = e.length;
    var h = new Array();
    var g = 50;
    var b = Math.floor((f - 1) / g) + 1;
    for(var d = 0; d < b; d++) {
        h.push(false);
    }
    function a() {
        var n = 0;
        var i = window.setInterval(function() {
            if(n >= f) {
                window.clearInterval(i);
                return;
            }
            var o = Math.min(f, n + g);
            for(var p = n; p < o; p++) {
                if(e[p].isBk == 1 && e[p].ct == -1) {
                    continue;
                } else {
                    eachBuddyList(e[p], function(s) {
                        var r = k[s];
                        if(r) {
                            var q = __contactTree.createBuddyItem(e[p]);
                            r.pushContent(q);
                        }
                    });
                }
            }
            sendToSwfJD(__loginProcess + Math.round(30 * (o - n) / f));
            h[n / g] = true;
            n += g;
        }, 50);
    }
    a();
    var l = window.setInterval(function() {
        var p = 0;
        var o = b - 1;
        while(p <= o) {
            if(!h[p] || !h[o]) {
                break;
            }
            p++;
            o--;
        }
        if(p > o) {
            window.clearInterval(l);
            var r = "";
            for(var q = 0; q < j; q++) {
                var n = k[__buddyList[q].id];
                r += n.toHtmlString();
            }
            __contactTree.setInnerHtml(r);
            __isOkBuddyList = true;
            if(typeof m == "function") {
                if(__isOkBuddyList && __isOkGroupList) {
                    m();
                }
            }
            r = null;
        }
    }, 1000);
}
function updatePersonalInfo(d) {
    $.extend(__user, d);
    extendUser();
    setRemindMsgCookie(__user.sid, __user.displayName, null, __sessionId);
    initUserInfo();
    var a = __chatWindowMap.values();
    var c = a.length;
    for(var b = 0; b < c; b++) {
        if(a[b].setUserInfo) {
            a[b].setUserInfo(__user);
        }
    }
}
function addBuddyWithReply(e, b) {
    var a = null;
    if(__contactMap.containsKey(e.uid)) {
        a = __contactMap.get(e.uid);
        var d = (a.isBk == 1 && a.ct == -1);
        $.extend(a, e);
        switch(e.ut) {case 1:a.ct = 0;break;case 2:a.ct = 2;break;case 3:a.isBk = 1;break;default:break;}
        extendContact(a);
        if(b) {
            eachBuddyList(a, function(c) {
                if(!(a.isBk == 1 && a.ct == -1)) {
                    if(d) {
                        __contactTree.insertBuddyItem(a, parseInt(c));
                    } else {
                        __contactTree.updateBuddyItem(parseInt(c), a);
                    }
                }
            });
        }
    } else {
        a = new ContactDefault();
        $.extend(a, e);
        switch(e.ut) {case 1:a.ct = 0;break;case 2:a.ct = 2;break;case 3:a.isBk = 1;a.ct == -1;break;default:break;}
        extendContact(a);
        __contactMap.put(a.uid, a);
        if(b) {
            eachBuddyList(a, function(c) {
                __contactTree.insertBuddyItem(a, parseInt(c));
            });
        }
    }
}
function removeBuddyWithReply(g, d) {
    var b = __contactMap.get(g.uid) || g;
    if(!b) {
        return;
    }
    if(1 == g.ut) {
        if(1 == b.isBk) {
            var a = new ContactDefault();
            a.uid = g.uid;
            a.isBk = 1;
            a.ct = -1;
            __contactMap.remove(g.uid);
            __contactMap.put(a.uid, a);
        } else {
            for(var e = 0,f = __buddyList.length; e < f; e++) {
                __contactTree.deleteBuddyItem(parseInt(__buddyList[e].id), g.uid);
            }
            __contactMap.remove(g.uid);
        }
        if(d) {
            for(var e = 0,f = __buddyList.length; e < f; e++) {
                __contactTree.deleteBuddyItem(parseInt(__buddyList[e].id), g.uid);
            }
        }
    } else {
        if(2 == g.ut) {
            if(2 == b.ct) {
                if(1 == b.isBk) {
                    var a = new ContactDefault();
                    a.uid = g.uid;
                    a.isBk = 1;
                    a.ct = -1;
                    __contactMap.remove(g.uid);
                    __contactMap.put(a.uid, a);
                } else {
                    __contactMap.remove(g.uid);
                }
            }
            if(d) {
                __contactTree.deleteBuddyItem(-1, g.uid);
            }
        } else {
            if(3 == g.ut) {
                if(-1 == b.ct && 1 == b.isBk) {
                    __contactMap.remove(g.uid);
                } else {
                    b.isBk = 0;
                    extendContact(b);
                    if(d) {
                        eachBuddyList(b, function(c) {
                            __contactTree.updateBuddyItem(parseInt(c), b);
                        });
                    }
                }
            }
        }
    }
}
function updateBuddyWithReply(e, b) {
    var a = __contactMap.get(e.uid);
    if(!a) {
        return;
    }
    var d = "";
    if(a.ln) {
        d = a.ln;
    } else {
        if(a.mn && a.sid) {
            d = a.mn + "(" + a.sid + ")";
        } else {
            if(a.mn) {
                d = a.mn;
            } else {
                if(a.sid) {
                    d = a.sid;
                } else {
                    if(a.uri) {
                        d = (new jCompactUri(a.uri)).mobileNoOrSid;
                    }
                }
            }
        }
    }
    if(typeof e.rs == "number" && e.rs == 1) {
        a.ct = a.bss == 0 ? 1 : 0;
        updateContactInfo(e, b);
        $.fn.fetionHintWindow({modal:false}).show("(" + d + ")同意添加为好友。 ", "提示", "OK");
    } else {
        if(typeof e.rs == "number" && e.rs == 2) {
            updateContactInfo(e, b);
            $.fn.fetionHintWindow({modal:false}).show("(" + d + ")拒绝添加为好友。 ", "提示", "OK");
        } else {
            updateContactInfo(e, b);
        }
    }
}
function updateContactInfo(f, b) {
    var a = __contactMap.get(f.uid);
    if(!a) {
        return;
    }
    $.extend(a, f);
    extendContact(a);
    if(b) {
        eachBuddyList(a, function(c) {
            __contactTree.updateBuddyItem(parseInt(c), a);
        });
    }
    var e = getUserPortraitUrl(f, "5");
    if(__chatFooterItemMap.containsKey(f.uid)) {
        __chatFooterItemMap.get(f.uid).updateItem(a, e);
    }
    if(__remindMsgItemMap.containsKey(f.uid)) {
        __remindMsgItemMap.get(f.uid).updateItem(a, e);
    }
    var d = __chatWindowMap.get(f.uid);
    if(d) {
        d.setBuddyInfo(a);
        d.setBuddyStatus(a);
    }
}
var __searchKeyword = "";
function initi__searchInput() {
    $("#inputSearchFriends").bind("focus", searchFocus).bind("blur", searchBlur).bind("keydown", searchKeydown).bind("keyup", searchKeyup);
    $("#btnSearchClear").bind("click", function(a) {
        clearSearch();
        this.style.display = "";
        stopPropagation(a);
        return false;
    });
}
function searchFocus() {
    this.value = "";
    $("#search_result").css("display", "none");
    $("#btnSearchClear").css("display", "block");
}
function searchBlur() {
    this.className = "";
    if(this.value == "") {
        this.value = __searchPrompt;
        $("#search_result").css("display", "none");
        $("#btnSearchClear").css({"display":"none"});
    }
}
function searchKeydown(a) {
    a = window.event || a;
    switch(a.keyCode) {case 38:moveSelectedContact("up");break;case 40:moveSelectedContact("down");break;case 13:if(__searchKeyword == this.value) {
        openSelectedContact(this, a);
    } else {
        searchContact(this, a);
    }break;default:searchContact(this, a);break;}
}
function searchKeyup(a) {
    a = window.event || a;
    switch(a.keyCode) {case 38:case 40:break;case 13:if(__searchKeyword == this.value) {
        openSelectedContact(this, a);
    } else {
        searchContact(this, a);
    }break;default:searchContact(this, a);break;}
}
function moveSelectedContact(e) {
    var c = $("#search_result").find("div > ul");
    var b = c.find("li");
    var d = b.length;
    var g = 0;
    if(e == "up") {
        g = -1;
    } else {
        if(e == "down") {
            g = 1;
        }
    }
    for(var a = 0; a < d; a++) {
        if(b.eq(a).attr("class") == "hover") {
            b.eq(a).removeClass("hover");
            a += g;
            break;
        }
    }
    var f = (a + d) % d;
    b.eq(f).addClass("hover");
    c.scrollTop(Math.floor(f / 4) * 4 * b.eq(0).height());
}
function openSelectedContact(a, b) {
    a.blur();
    setTimeout(function() {
        var c = $("#search_result").find("div > ul > .hover:first");
        if(c) {
            c.click();
        }
    }, 300);
}
function searchContact(a, p) {
    if(__searchKeyword == a.value) {
        return;
    }
    var k = $("#search_result");
    k.find("div > ul > li").remove();
    __searchKeyword = a.value;
    if(a.value == "") {
        k.css("display", "none");
        $("#btnSearchClear").css({"display":"none"});
        return;
    }
    __searchKeyword = a.value;
    var o = __contactMap.values();
    var b = o.length;
    var n = new Array();
    if(__isFirstSearch) {
        for(var m = 0; m < b; m++) {
            var j = searchHelper.MakeSpellCodeAsWordsAll(o[m].is);
            o[m].is = j.bodyContent;
        }
        __isFirstSearch = false;
    }
    for(var m = 0; m < b; m++) {
        var r = __searchKeyword.toLowerCase();
        var d = new RegExp("[*]+", "gi");
        var g = __searchKeyword.toLowerCase().replace(d, "");
        var f = "";
        var c = "";
        try {
            f = searchHelper.MakeSpellCodeAsWordsAll(o[m].is).bodyContent;
            c = searchHelper.MakeSpellCodeAsWordsAll(o[m].pis).bodyContent;
        } catch(p) {
        }
        if((o[m].nn && o[m].nn.toLowerCase().indexOf(r) >= 0) || (o[m].ln && o[m].ln.toLowerCase().indexOf(r) >= 0) || (o[m].mn && o[m].mn.toLowerCase().indexOf(r) >= 0) || (f && f.toLowerCase().indexOf(g) >= 0) || (c && c.toLowerCase().indexOf(g) >= 0)) {
            n.push(o[m]);
        }
    }
    var h = n.length;
    if(h <= 0) {
        k.find("div > ul:first").append("<li>没找到匹配的联系人</li>");
    }
    n.sort(buddyComparator);
    for(var m = 0; m < h; m++) {
        var q;
        var t = htmlEncode(n[m].displayName);
        if(m == 0) {
            q = $("<li class='hover'><i class='" + n[m].statusClass + "' /><b>" + t + "</b> <em>" + getStatusDesc(n[m]) + "</em> </li>");
            k.find("div > ul:first").append(q);
        } else {
            q = $("<li><i class='" + n[m].statusClass + "' /><b>" + t + "</b> <em>" + getStatusDesc(n[m]) + "</em> </li>");
            k.find("div > ul:first").append(q);
        }
        q.bind("mouseover", function() {
            $("#search_result").find("div > ul > .hover").removeClass("hover");
            $(this).removeClass().addClass("hover");
        }).bind("click", {buddyId:n[m].uid}, function(i) {
            openChatWindow(this, i.data.buddyId);
            clearSearch();
            $("#btnSearchClear").css({"display":"none"});
        });
    }
    var l = $(a);
    var s = l.offset();
    k.css("display", "block").css({"top":s.top + l.innerHeight(),"left":s.left - 26});
    $("#btnSearchClear").css({"display":"block"});
}
function clearSearch() {
    var b = $("#search_result");
    b.css("display", "none");
    b.find("div > ul > li").remove();
    var a = $("#inputSearchFriends");
    if(a.val() != __searchPrompt) {
        a.val(__searchPrompt);
    }
    __searchKeyword = "";
}
function initContactCard() {
    $("#contact_info").bind("mouseover", function(a) {
        clearTimeout(__hideContactInfoTimer);
        if(isMouseOver(this, a)) {
            this.style.display = "block";
        }
    }).bind("mouseout", function(a) {
        if(isMouseOut(this, a)) {
            this.style.display = "none";
        }
    });
}
function showContactCard(b, a) {
    clearTimeout(__hideContactInfoTimer);
    var c = __contactMap.get(a);
    if(!c) {
        return;
    }
    __initContactInfoTimer = window.setTimeout(function() {
        var h = $("#contact_info");
        h.css("display", "none");
        var q = h.find("#contact_portrait");
        var e = h.find("#contact_status");
        var j = h.find("#contact_name");
        q.attr("src", getDefaultPortraitUrl(c));
        var o = getUserPortraitUrl(c, "3");
        cacheUerPortrait(c, o, function(p, r) {
            if(c == r) {
                q.attr("src", o);
            }
        });
        j.get(0).innerHTML = EmotionNonhtmlReplace(c.displayName, 18, true);
        if(c.isVip) {
            j.css("color", "red");
        } else {
            j.css("color", "");
        }
        var l = "";
        if(c.ct == 1) {
            l = "尚未开通飞信";
        } else {
            var k = new jCompactUri(c.uri);
            l = c.sid || (!k.isMobileNo ? k.mobileNoOrSid : "");
        }
        h.find("#contact_id").text(l);
        e.attr("class", c.statusClass);
        if(c.uid) {
            h.find("#contact_space").attr("href", formatString(__contactSpaceUrl, c.uid)).css("display", "").click(function() {
                if(__user.c) {
                    var p = formatString(__contactSpaceUrl, c.uid, __user.c);
                    $(this).attr("href", p);
                }
            });
        } else {
            h.find("#contact_space").attr("href", "#").css("display", "none");
        }
        if(!c.i || c.ct == 1) {
            h.find("#contact_impresa").text("").css("display", "none");
            h.find("#contact_impresa_line").css("display", "none");
        } else {
            var m = EmotionNonhtmlReplace(c.i, 106, true);
            var d = h.find("#contact_impresa");
            d.get(0).innerHTML = m;
            d.css("display", "");
            h.find("#contact_impresa_line").css("display", "");
        }
        if(c.bl == "0") {
            h.find("#contact_list").text("未分组");
        } else {
            if(c.bl == "-1") {
                h.find("#contact_list").text("陌生人");
            } else {
                var g = "";
                var n = __buddyList.length;
                eachBuddyList(c, function(r) {
                    for(var p = 0; p < n; p++) {
                        if(__buddyList[p].id.toString() == r) {
                            g += __buddyList[p].n + ";";
                        }
                    }
                });
                g = g.trim(";");
                h.find("#contact_list").text(g);
            }
        }
        try {
            var f = $(b).offset();
            if(f.top + h.height() > getViewportHeight() - 20) {
                f.top = f.top - h.height() + $(b).innerHeight();
            }
        } catch(i) {
        }
        __showContactInfoTimer = window.setTimeout(function() {
            h.css({"display":"block","top":f.top,"left":f.left - h.outerWidth()});
        }, 300);
    }, 500);
}
function hideContactCard(b, a) {
    clearTimeout(__initContactInfoTimer);
    clearTimeout(__showContactInfoTimer);
    __hideContactInfoTimer = setTimeout(function() {
        var c = $("#contact_info");
        c.css({"display":"none"});
    }, 200);
}
function initOperateCard() {
    var a = $("#operate_info");
    a.bind("mouseover", function(b) {
        clearTimeout(__hideOperateInfoTimer);
        this.style.display = "block";
    }).bind("mouseout", function(b) {
        if(isMouseOut(this, b)) {
            this.style.display = "none";
        }
    });
    a.find("div>ul>li").bind("mouseover", function() {
        this.className = "hover";
    }).bind("mouseout", function() {
        this.className = "";
    });
}
function showOperateCard(e, k) {
    var a = __contactMap.get(k);
    var c = $("#operate_info");
    var f = $("#operate_items");
    f.find("#operate_msg").unbind().bind("click", function() {
        openChatWindow(e, k);
        c.css({"display":"none"});
        return false;
    });
    f.find("#operate_block").unbind().text(a.isBk == 1 ? "移出黑名单" : "加入黑名单").bind("click", function() {
        c.css({"display":"none"});
        OpBuddy(a.uid, a.isBk == 1 ? "2" : "1");
        __group_mem.containsKey("");
        return false;
    });
    f.find("#operate_updateLocalName").unbind().bind("click", function() {
        c.css({"display":"none"});
        updateLocalName(a.uid);
        return false;
    });
    var j = f.find("#operate_add");
    j.unbind();
    var d = a.status;
    if(a.ct == 2 || d == ContactStatus.Verify || d == ContactStatus.Verify_Bklist || d == ContactStatus.Refuse_Bklist || d == ContactStatus.Refuse) {
        var b = (a.ct == 2 ? "添加好友" : "重新添加好友");
        j.parent().css("display", "");
        j.text(b).bind("click", function() {
            if(a.sid) {
                createAddBuddyTab(a.sid);
            } else {
                if(a.mn) {
                    createAddBuddyTab(a.mn, "1");
                } else {
                    if(a.uri) {
                        var l = new jCompactUri(a.uri);
                        createAddBuddyTab(l.mobileNoOrSid, l.isMobileNo ? "1" : "0");
                    } else {
                        createAddBuddyTab();
                    }
                }
            }
            return false;
        });
    } else {
        j.parent().css("display", "none");
    }
    var h = f.find("#operate_space");
    h.unbind();
    if(a.sid) {
        h.attr("href", formatString(__contactSpaceUrl, a.uid));
        h.parent().css("display", "");
        h.bind("click", function() {
            c.css({"display":"none"});
            if(__user.c) {
                var l = formatString(__contactSpaceUrl, a.uid, __user.c);
                $(this).attr("href", l);
            }
        });
    } else {
        h.parent().css("display", "none");
    }
    var g = $(e);
    var i = g.offset();
    if(i.top + c.height() > getViewportHeight() - 20) {
        i.top = i.top - c.height();
    }
    c.css({"display":"block","top":i.top + g.height(),"left":i.left - (c.outerWidth() - g.width())});
    __hideOperateInfoTimer = setTimeout(function() {
        c.css({"display":"none"});
    }, 2000);
}
function updateStatusDesc(b) {
    b = b || "";
    b = cnSubstr2(b.trim(), 6);
    var a = "";
    if(cnLength2(b) < 8) {
        a = "( " + b + " )";
    } else {
        a = "(" + b + ")";
    }
    $("#user_status > em").text(a);
}
function initChatFooter() {
    __maxMsgListOnTaskbar = getMaxMsglistCount();
    __moreMsgListDiv = $.fn.jMoreMsgLists({target:$(document.body),maxHold:5});
    __moreMsgListDiv.hide();
    $("#msg_more").bind("click", function() {
        this.className = "msg_more hover_msg";
        if(__chatFooterItemMap.size() > __maxMsgListOnTaskbar) {
            if(__moreMsgListDiv.isShow) {
                __moreMsgListDiv.hide();
            } else {
                __moreMsgListDiv.show();
            }
        }
    }).bind("mouseout", function(a) {
        this.className = "msg_more";
        if(__moreMsgListDiv.isShow && isMouseOut(this, a)) {
            __hideMoreMsgListTimer = setTimeout(function() {
                __moreMsgListDiv.hide();
            }, 500);
        }
    }).bind("mouseover", function() {
        this.className = "msg_more hover";
    });
    __remindMsgDiv = $.fn.jRemindMsgList({target:$(document.body),onClearAll:clearAllRemindMsg});
    __remindMsgDiv.hide();
    $("#msg_remind").bind("click", function(a) {
        $.increaseCounters("500400002");
        if(!__remindMsgDiv.isShow && __remindMsgItemMap.size() > 0) {
            __remindMsgDiv.show();
        } else {
            __remindMsgDiv.hide();
        }
        stopPropagation(a);
        return false;
    }).bind("mouseout", function(a) {
        if(__remindMsgDiv.isShow && isMouseOut(this, a)) {
            __hideRemindMsgTimer = setTimeout(function() {
                __remindMsgDiv.hide();
            }, 500);
        }
    }).bind("mousedown", function() {
        this.className = "msg_remind active";
    }).bind("mouseup", function() {
        this.className = "msg_remind";
    });
}
function getMaxMsglistCount() {
    var a = $("#taskbar").width() - $("#cm_logo").width() - $("#msg_remind").width() - $("#msg_more").width();
    return Math.floor(a / 96);
}
function reInitialFooter(a) {
    var d = $("#msg_list");
    $("#msg_more").css("display", "none");
    if(a) {
        if(__moreMsgListDiv.getItems().size() == 0 && d.find("li").size() < __maxMsgListOnTaskbar) {
            d.append(a);
        } else {
            $("#msg_more").css("display", "");
            __moreMsgListDiv.addItem(a);
        }
        return;
    }
    var f = new Array();
    d.find("li").each(function() {
        f.push($(this));
    });
    var b = __moreMsgListDiv.getItems();
    for(var e = b.size() - 1; e >= 0; e--) {
        f.push(b.eq(e));
    }
    var g = f.length;
    for(var c = 0; c < g; c++) {
        f[c].css("display", "block");
        if(c < __maxMsgListOnTaskbar) {
            f[c].inMore = false;
            d.append(f[c]);
        } else {
            f[c].inMore = true;
            $("#msg_more").css("display", "");
            __moreMsgListDiv.addItem(f[c]);
            __moreMsgListDiv.hide();
        }
    }
}
function clearAllRemindMsg() {
    __remindMsgItemMap.clear();
    var b = __chatFooterItemMap.values();
    for(var a = b.length - 1; a >= 0; a--) {
        b[a].removeClass("call");
    }
    setRemindCountTotal();
}
function receiveMsgs(i) {
    var a = __contactMap.get(i.fromUid);
    if(!a || a.ct == 2) {
        if(getUserPermission(__user.gp, "contact") != 1) {
            return;
        }
    }
    if(!a) {
        addChatFriend(i.fromUri, function(d, n) {
            var c = d;
            if(typeof c.rc == "number" && c.rc == 200) {
                var b = new ContactDefault();
                if(!buddyListContains("-1")) {
                    __buddyList.push({"id":-1,"n":"陌生人"});
                    __contactTree.insertBuddyList({"id":-1,"n":"陌生人"});
                }
                b.uid = i.fromUid;
                b.uri = i.fromUri;
                b.bl = "-1";
                b.ct = 2;
                extendContact(b);
                __contactMap.put(b.uid, b);
                __contactTree.insertBuddyItem(b, b.bl);
                receiveMsgs(i);
            }
        });
        return;
    }
    var g = getChatWindow(i.fromUid);
    if(!g.isOnFocus() || !__isOnfocus) {
        if(__remindMsgItemMap.containsKey(i.fromUid)) {
            var e = __remindMsgItemMap.get(i.fromUid);
            e.msgCount(e.msgCount() + 1);
            e.updateItem(__contactMap.get(i.fromUid));
        } else {
            var l = __contactMap.get(i.fromUid);
            var k = __chatWindowMap.get(l.uid);
            var m = getUserPortraitUrl(l, "5");
            var e = $.fn.jRemindMsgItem(l, k, m);
            __remindMsgItemMap.put(i.fromUid, e);
            __remindMsgDiv.addItem(e);
        }
        setRemindCountTotal();
    }
    if(!g.isActived) {
        g.active();
        var f = __chatFooterItemMap.get(i.fromUid);
        f.removeClass().addClass("call");
        if(f.isInMoreMsgList()) {
            $("#msg_more").removeClass().addClass("msg_more hover_msg");
        }
    } else {
        if(!g.isOnFocus()) {
            var f = __chatFooterItemMap.get(i.fromUid);
            f.removeClass().addClass("call");
            if(f.isInMoreMsgList()) {
                $("#msg_more").removeClass().addClass("msg_more hover_msg");
            }
        }
    }
    g.logFriendMsg(i.msg);
    if(g.inputPermission == 420 && !g.enablechatInput) {
        g.enableInput(true);
    }
    if(!__isOnfocus) {
        if(typeof __titleflashInterval != "undefined") {
            window.clearInterval(__titleflashInterval);
        }
        __flashTitleArray.push(__msgcomingTitle);
        __flashTitleArray.push("【" + __msgcomingTitle + "】");
        var j = __flashTitleArray.length;
        var h = j - 1;
        __titleflashInterval = window.setInterval(function() {
            var b = __flashTitleArray[h];
            document.title = b;
            h = (h + 1) % j;
        }, 1000);
    }
}
function sendMsgFailed(b) {
    var a = getChatWindow(b.fromUid);
    if(a) {
        a.logErrorInfo("消息发送失败: " + b.msg);
    }
}
function receiveInvite(f) {
    var e = "你好，我是" + f.desc + "，";
    switch(f.phrid) {case 0:e += "正在用中国移动飞信业务，想加你为好友。";break;case 1:e += "想加你为飞信好友，方便咱们联系。";break;case 2:e += "飞信挺好用的，想加你为好友。";break;case 3:e += "希望你成为我的飞信好友，常联系。";break;case 4:e += "成为我的飞信好友，能免费给你发短信。";break;default:break;}
    var a = $("#invited_" + f.uid);
    if(a.size() > 0) {
        a.remove();
        a = null;
    }
    a = $.fn.friendInviteWindow({buddy:f,inviteType:f.t,onConfirm:processorFriendInvite});
    var c = __buddyList.length;
    if(c > 0) {
        __buddyList.sort(buddyListComparator);
        for(var b = 0; b < c; b++) {
            if(parseInt(__buddyList[b].id) > 0) {
                a.insertBuddyList(__buddyList[b].id, __buddyList[b].n);
            }
        }
    }
    a.insertDesc(e);
}
function setRemindCountTotal() {
    var c = 0;
    var b = __remindMsgItemMap.values();
    for(var a = b.length - 1; a >= 0; a--) {
        if(b[a].find("i").attr("class") == "ff-pc-group") {
            c += 1;
        } else {
            c += b[a].msgCount();
        }
    }
    setRemindMsgCookie(__user.sid, null, c, __sessionId);
    if(c > 0) {
        $("#msg_remind").removeClass("none");
    } else {
        $("#msg_remind").addClass("none");
    }
    $("#msg_remind").find("b:first").text(c.toString());
}
function onSendMsg(g, d, f, b, e) {
    var c = f.length;
    var a = (b || e);
    if(a && c > 180) {
        alert("温馨提示：长短信不能超过180个字符。");
    } else {
        if(c > 400) {
            alert("温馨提示：消息不能超过400个字符。");
        } else {
            g.logSelfMsg(f);
            sendMessage(g, d, f, b, e);
        }
    }
}
function processorFriendInvite(a) {
    handleAddBuddy(a, 0);
}
function redirectLogin() {
    __isNormalLogout = true;
    window.location.href = loginPageUrl;
}
function startSound(a) {
    if(__isOpenSound) {
        if(a == "newmessage") {
            IMSound.playSound(a);
        } else {
            if(a == "sys") {
                IMSound.playSound(a);
            }
        }
    }
}
function showPanel(a) {
    $("#group_func").find("li").removeClass("on");
    $("#friends_list").addClass("none");
    $("#group_box").addClass("none");
    $("#phone_list").addClass("none");
    if(a == "group") {
        $("#li_group").addClass("on");
        $("#group_box").removeClass("none");
    } else {
        if(a == "buddy") {
            $("#li_buddy").addClass("on");
            $("#friends_list").removeClass("none");
        } else {
            if(a == "gountxun") {
                $("#li_gountxun").addClass("on");
                $("#phone_list").removeClass("none");
            }
        }
    }
}
function addBuddySuccess(b) {
    var a;
    if(__contactMap.containsKey(b.uid)) {
        a = __contactMap.get(b.uid);
        if(a.isBk == 1 && a.ct == -1) {
        } else {
            if(1 != a.rs || 2 == a.ct) {
                __contactMap.remove(a.uid.toString());
                eachBuddyList(a, function(c) {
                    __contactTree.deleteBuddyItem(parseInt(c), a.uid);
                }, true);
                if(2 == a.ct) {
                    a.ct = 0;
                }
            } else {
                alert("对方已经是你好友。");
                return;
            }
        }
    } else {
        a = new ContactDefault();
        a.ln = b.ln;
        a.ct = 0;
    }
    $.extend(a, b);
    extendContact(a);
    __contactMap.put(a.uid, a);
    eachBuddyList(a, function(c) {
        __contactTree.insertBuddyItem(a, parseInt(c));
    });
}
function initNavigation() {
    $("#quitIm").attr("href", __officialSiteUrl + "/account/loginout?ul=" + loginPageUrl);
    $("#officalsite").attr("href", __officialSiteUrl);
    $("#myspace").attr("href", __mySpaceUrl);
    $("#downloadpc").attr("href", __downloadUrl);
    $("#myspace_icon").attr("href", __mySpaceUrl);
    $("#feedback").attr("href", __feedbackUrl);
    $("#downloadpc").bind("click", function() {
        $.increaseCounters(500400004);
    });
    $("#rt_img_link").click(function() {
        addFavourite(location.href, "官方网页版飞信——在网页上与飞信好友聊天、免费发短信");
    });
    $("#quitIm").bind("click", function() {
        __isNormalLogout = true;
        logout(__isNormalLogout);
    });
}
function buddyListContains(b) {
    for(var a = 0; a < __buddyList.length; a++) {
        if(__buddyList[a].id.toString() == b) {
            return true;
        }
    }
    return false;
}
function dealWithCallme(f) {
    var b = "";
    if(f.toUid && f.toUri) {
        if(f.toUid == __user.uid) {
            return;
        }
        b = __callmeTitle;
        if(!__contactMap.containsKey(f.toUid)) {
            addChatFriend(f.toUri, function(i, j) {
                var h = i;
                if(typeof h.rc == "number" && h.rc == 200) {
                    var g = new ContactDefault();
                    if(!buddyListContains("-1")) {
                        __buddyList.push({"id":-1,"n":"陌生人"});
                        __contactTree.insertBuddyList({"id":-1,"n":"陌生人"});
                    }
                    g.uid = f.toUid;
                    g.uri = f.toUri;
                    g.bl = "-1";
                    g.ct = 2;
                    extendContact(g);
                    __contactMap.put(g.uid.toString(), g);
                    __contactTree.insertBuddyItem(g, g.bl);
                    dealWithCallme(f);
                }
            });
            return;
        }
        var a = getChatWindow(f.toUid);
        if(!a.isActived) {
            a.active();
            var e = getChatWinPosition();
            a.show(e.top, e.left);
        } else {
            if(!a.hasShown) {
                var e = getChatWinPosition();
                a.show(e.top, e.left);
            } else {
                a.show();
            }
        }
    } else {
        b = __readmsgTitle;
    }
    if(!__isOnfocus) {
        if(typeof __titleflashInterval != "undefined") {
            window.clearInterval(__titleflashInterval);
        }
        __flashTitleArray.push(b);
        __flashTitleArray.push("【" + b + "】");
        var d = __flashTitleArray.length;
        var c = d - 1;
        __titleflashInterval = window.setInterval(function() {
            var g = __flashTitleArray[c];
            document.title = g;
            c = (c + 1) % d;
        }, 1000);
    }
}
function updateGroupInfo(i, b, f) {
    if(i == null) {
        return;
    }
    var h = jGroupUriToId(i.gUri);
    var d = __groupMap.get(h);
    if(!d) {
        return;
    }
    if(d.members && i.members && i.members.length < d.members.length) {
        if(i.members.length == 1) {
            var e = jMemUriToId(i.members[0].MemberUri);
            if(e == __user.sid) {
                return;
            }
        }
    }
    $.extend(d, i);
    extendGroupItem(d);
    if(f && (d.i > 3 || d.msgRecv > 1)) {
        __groupTree.updateGroupItem(d);
        return;
    }
    if(b) {
        __groupTree.updateGroupItem(d);
        var a = __chatWindowMap.get("group_" + d.id);
        if(a) {
            a.setGroupInfo(d);
        }
        if(d.n) {
            d.displayName = d.n;
            var g = getGroupItemPortraitUrl(d, "1");
            if(__chatFooterItemMap.containsKey("group_" + h)) {
                __chatFooterItemMap.get("group_" + h).updateItem(d, g);
            }
            if(__remindMsgItemMap.containsKey("group_" + h)) {
                __remindMsgItemMap.get("group_" + h).updateItem(d, g);
            }
        }
    }
}
function deleteGroupMember(d) {
    var c = jGroupUriToId(d.GroupUri);
    var b = jMemUriToId(d.MemberUri);
    if(b == jMemUriToId(__user.uri)) {
        var a = __groupMap.get(c);
        $.popWinMsg({gName:d.GroupName,group:a,msgN:"msg1"});
        removeGroupUi(c, 1);
    } else {
        chatWinDelMem(c, b);
    }
}
function removeGroupUi(c, b) {
    __groupTree.deleteGroupItem(c);
    var a = __chatWindowMap.get("group_" + c);
    if(a) {
        a.enableInput(false);
        a.logSuggetInfo(b);
        a.isUsable = false;
    }
    if(__chatFooterItemMap.containsKey("group_" + c)) {
        __chatFooterItemMap.get("group_" + c).remove();
        __chatFooterItemMap.remove("group_" + c);
        reInitialFooter();
    }
}
function openGroupChatWindow(d, c) {
    var b = __groupMap.get(c);
    if(b.i > 3) {
        return;
    }
    var a = getGroupChatWindow(c);
    if(!a.isActived) {
        a.active();
        var e = getChatWinPosition();
        a.show(e.top, e.left);
    } else {
        if(!a.hasShown) {
            var e = getChatWinPosition();
            a.show(e.top, e.left);
        } else {
            a.show();
        }
    }
    if(typeof __isIE6 != "undefined" && __isIE6) {
        a.fixPosition();
    }
    if(b.msgRecv == 2) {
        __groupTree.clearGroupMsgCount(b);
    }
}
function getGroupChatWindow(c) {
    var a;
    var b = __groupMap.get(c);
    if(!__chatWindowMap.containsKey("group_" + c)) {
        a = createGroupWin(b, c);
    } else {
        a = __chatWindowMap.get("group_" + c);
        if(!a.isUsable) {
            __chatWindowMap.remove("group_" + c);
            a.remove();
            a = null;
            a = createGroupWin(b, c);
        }
    }
    return a;
}
function createGroupWin(b, c) {
    var a;
    a = $.fn.jGroupChatWindow({maxRelative:"main_content",user:__user,group:b,groupMap:__groupMap,defaultHeight:__groupChatWinDefaultHeight,defaultWidth:__groupChatWinDefaultWidth,onActive:onActiveGroupChatWindow,onClosed:onCloseGroupChatWindow,onFocus:onFocusGroupChatWindow,onSendMessage:onSendGroupMsg});
    getGroupMemList(b, a.memMap, a);
    __chatWindowMap.put("group_" + c, a);
    return a;
}
function getGroupMemList(c, a, b) {
    var d = new Object();
    d.success = function(f, e) {
        getGroupMemListSuccess(f, e, c, a, b);
    };
    d.error = function(e, g, f) {
        getGroupMemListError(e, g, f, c, a, b);
    };
    d.url = formatString(getGroupMemUrl, __version++);
    d.type = "POST";
    d.data = {"ssid":__sessionId,"Uri":c.gUri};
    d.dataType = "json";
    d.cache = false;
    $.ajax(d);
}
var __gmlRetry = 0;
function getGroupMemListSuccess(j, c, p, e, g) {
    var m;
    m = $.fn.jGroupMemTree({isSinglePattern:true,user:__user,group:p,onBuddyItemDbClick:openChatWindow,onBuddyItemMouseOver:showContactCard,onBuddyItemMouseOut:hideContactCard,onBuddyItemRightClick:showOperateCard});
    g.groupMemTree = m;
    var r = j;
    if(typeof r.rc == "number" && r.rc == 200) {
        __gmlRetry = 0;
        var k = r.rv.GroupMembersInfo;
        k = k || (new Array());
        var l = k.length;
        var f = jGroupUriToId(r.rv.gUri);
        for(var h = 0; h < l; h++) {
            var a = jMemUriToId(k[h].MemberUri);
            var q = __group_mem.get(f + "_" + a);
            if(q == null) {
                q = new GroupMemDefault();
            }
            $.extend(q, k[h]);
            extendMemItem(q);
            e.put(q.id, q);
        }
        g.memMap = e;
        var n = insertGroupMemToList(e, m, p);
        if(g.isLogShowNumber) {
            var b = g.find("dt:first> span").eq(0);
            var d = b.text();
            var o = e.get(d).displayName;
            b.text(o);
        }
    } else {
        if(typeof r.rc == "number" && r.rc == 310) {
            redirectLogin();
        } else {
            if(typeof r.rc == "number" && r.rc == 403) {
            } else {
                if(__gmlRetry < 3) {
                    __gmlRetry++;
                    window.setTimeout(function() {
                        getGroupMemList(p, e, g);
                    }, 500);
                } else {
                    __gmlRetry = 0;
                    alert(formatString("由于网络原因,获取群成员列表失败，请重新登录。错误代码：{0}", typeof r.rc == "number" ? r.rc : 500));
                }
            }
        }
    }
}
function getGroupMemListError(d, f, e, c, a, b) {
    if(__gmlRetry < 3) {
        __gclRetry++;
        window.setTimeout(function() {
            getGroupMemList(c, a, b);
        }, 500);
    } else {
        __gmlRetry = 0;
        alert(formatString("由于网络原因,获取群成员列表失败，请重新登录。错误原因：{0}", f));
    }
}
function getGroupMemPortrait(e, b) {
    var i = e.find(".pop_group_list");
    var g = parseInt(i.css("height")),d = i.find("li:first").offset().top,c = d + g,a = false;
    if(i.find("li[on=1]").size() <= 0) {
        var h = i.find("li");
        var f;
        h.each(function(j, k) {
            $(k).attr("on", "1");
            var m = $(k).attr("id").substring(10);
            if(b.containsKey(m)) {
                f = b.get(m);
                var l = buddyPor(f, f.statusClass, 1);
                $(k).find("img").attr("src", l);
            }
            if(j >= 19) {
                return false;
            }
        });
    }
    i.bind("scroll", function() {
        var k = $(this).find("li[on!=1]");
        if(k.size() > 0 && k.first().offset().top <= c) {
            var j;
            k.each(function(l, m) {
                $(m).attr("on", "1");
                var o = $(m).attr("id").substring(10);
                if(b.containsKey(o)) {
                    j = b.get(o);
                    var n = buddyPor(j, j.statusClass, 1);
                    $(m).find("img").attr("src", n);
                }
                if(l >= 19) {
                    if(!$(m).next().attr("id") || $(m).next().offset().top > c) {
                        return false;
                    }
                }
            });
        }
    });
}
function insertGroupMemToList(b, d, j) {
    var h = b.values();
    var k = new Object();
    k[0] = d.createBuddyList(j);
    var e = h.length;
    var f = k[0];
    for(var c = 0; c < e; c++) {
        var m = d.createBuddyItem(h[c], c);
        f.pushContent(m);
    }
    var g = "";
    var a = k[0];
    g += a.toHtmlString();
    d.setInnerHtml(g);
    getGroupMemPortrait(d, b);
    if(typeof callback == "function") {
        callback();
    }
    g = null;
}
function onSendGroupMsg(d, b, c) {
    var a = c.length;
    if(a > 400) {
        alert("温馨提示：消息不能超过400个字符。");
    } else {
        d.logSelfMsg(c);
        sendGroupMessage(d, b, c);
    }
}
function sendGroupMessage(d, b, c) {
    var a = new Object();
    a.url = formatString(SendGroupMsgUrl, __version++);
    a.type = "POST";
    a.dataType = "json";
    a.cache = false;
    a.data = {"Uri":b,"msg":c,"ssid":__sessionId};
    a.success = function(f, g) {
        var e = f;
        if(typeof e.rc == "number" && e.rc >= 200 && e.rc < 300) {
        } else {
            if(typeof e.rc == "number" && e.rc == 420) {
                d.logErrorInfo("对方版本过旧，您无法向其发起陌生人消息。");
                d.inputPermission = 420;
                d.enableInput(false);
            } else {
                if(typeof e.rc == "number" && e.rc == 486) {
                    d.logErrorInfo("您暂时无法发送短信，请稍后再试。");
                } else {
                    if(typeof e.rc == "number" && e.rc == 494) {
                        d.logErrorInfo("您的飞信账号处于系统保护状态，限制了该功能的使用。请手机发送短信U到12520解除限制后重新登录。");
                    } else {
                        if(typeof e.rc == "number" && e.rc == 405) {
                        } else {
                            d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));
                        }
                    }
                }
            }
        }
    };
    a.error = function(e, g, f) {
        d.logErrorInfo("消息发送失败: " + cnSubstr(c, 18));
    };
    $.ajax(a);
}
function receiveGroupMsgs(l) {
    l.id = jGroupUriToId(l.FromGroupUri);
    var h = __groupMap.get(l.id);
    if(h == null) {
        return;
    }
    var i = getGroupChatWindow(l.id);
    if(h.msgRecv == "2") {
        var p = __chatFooterItemMap.containsKey("group_" + l.id);
        if(p && !i.isOnFocus()) {
            var f = __chatFooterItemMap.get("group_" + l.id);
            f.removeClass().addClass("call");
            if(f.isInMoreMsgList()) {
                $("#msg_more").removeClass().addClass("msg_more hover_msg");
            }
        } else {
            if(!p) {
                __groupTree.updateGroupMsgCount(h);
            }
        }
    } else {
        if(!i.isOnFocus() || !__isOnfocus) {
            if(__remindMsgItemMap.containsKey("group_" + l.id)) {
                var e = __remindMsgItemMap.get("group_" + l.id);
                var g;
                var a = e.msgCount();
                if(a + 1 > 99) {
                    g = ">99";
                } else {
                    g = a + 1;
                }
                e.msgCount(g);
                var o = __groupMap.get(l.id);
                o.uid = o.id;
                o.statusClass = "ff-pc-group";
                o.displayName = o.n;
                var q = getGroupItemPortraitUrl(o, "1");
                e.updateItem(o, q);
            } else {
                var o = __groupMap.get(l.id);
                o.uid = o.id;
                o.statusClass = "ff-pc-group";
                o.displayName = o.n;
                var n = __chatWindowMap.get("group_" + l.id);
                var q = getGroupItemPortraitUrl(o, "1");
                var e = $.fn.jRemindMsgItem(o, n, q);
                var k = jGroupUriToId(l.FromGroupUri);
                __remindMsgItemMap.put("group_" + k, e);
                __remindMsgDiv.addItem(e);
                setRemindCountTotal();
            }
        }
        if(!__chatFooterItemMap.containsKey("group_" + l.id)) {
            startSound("newmessage");
        }
        if(!i.isActived) {
            i.active();
            var f = __chatFooterItemMap.get("group_" + l.id);
            f.removeClass().addClass("call");
            if(f.isInMoreMsgList()) {
                $("#msg_more").removeClass().addClass("msg_more hover_msg");
            }
        } else {
            if(!i.isOnFocus()) {
                var f = __chatFooterItemMap.get("group_" + l.id);
                f.removeClass().addClass("call");
                if(f.isInMoreMsgList()) {
                    $("#msg_more").removeClass().addClass("msg_more hover_msg");
                }
            }
        }
    }
    if(l.StatusCode == 433) {
        i.logErrorInfo("消息”" + cnSubstr(l.Message, 18) + "“发送失败:您说话太快了，请休息一下吧。");
    } else {
        i.logFriendMsg(l);
    }
    if(i.inputPermission == 420 && !i.enablechatInput) {
        i.enableInput(true);
    }
    if(!__isOnfocus && h.msgRecv != "2") {
        if(typeof __titleflashInterval != "undefined") {
            window.clearInterval(__titleflashInterval);
        }
        __flashTitleArray.push(__msgcomingTitle);
        __flashTitleArray.push("【" + __msgcomingTitle + "】");
        var m = __flashTitleArray.length;
        var j = m - 1;
        __titleflashInterval = window.setInterval(function() {
            var b = __flashTitleArray[j];
            document.title = b;
            j = (j + 1) % m;
        }, 1000);
    }
}
function groupMemPriChange(h) {
    for(var b = 0; b < h.length; b++) {
        var e = jGroupUriToId(h[b].GroupUri);
        var d = h[b].GroupUri;
        var i = __groupMap.get(e);
        var k;
        var g = h[b].PermissionChangedMemberEntity;
        for(var j = 0; j < g.length; j++) {
            var f = g[j].Identity;
            var a = jMemUriToId(g[j].MemberUri);
            var k = new Object();
            k.i = g[j].Identity;
            k.MemberUri = g[j].MemberUri;
            if(jMemUriToId(g[j].MemberUri) == __user.sid) {
                if(f == 1) {
                    $.popWinMsg({gName:i.n,group:i,msgN:"msg3"});
                } else {
                    if(f == 2) {
                        $.popWinMsg({gName:i.n,group:i,msgN:"msg2"});
                    } else {
                        if(f == 3) {
                            $.popWinMsg({gName:i.n,group:i,msgN:"msg7"});
                        }
                    }
                }
                var c = __groupMap.get(e);
                c.i = k.i;
                extendGroupItem(c);
                __groupTree.updateGroupItem(c);
            }
            initGroupMemList(d, k);
        }
    }
}
function exitGroup(c, e) {
    var b = jGroupUriToId(c);
    var a = jMemUriToId(e.MemberUri);
    var d = b + "_" + a;
    __group_mem.remove(d);
    var a = jMemUriToId(e.MemberUri);
    chatWinDelMem(b, a);
}
function apvResult(d) {
    var c = jGroupUriToId(d.gUri);
    var a = __groupMap.get(c);
    if(a) {
        a.apvRs = d.r;
        extendGroupItem(a);
        __groupTree.updateGroupItem(a);
    }
    if(d.r == 1) {
        __groupTree.updateGroupItem(a);
    } else {
        if(d.r == 2) {
        }
    }
    var e = c + "_" + b;
    __group_mem.remove(e);
    var b = jMemUriToId(memItem.MemberUri);
    chatWinDelMem(c, b);
}
function chatWinDelMem(d, c) {
    var b = __chatWindowMap.get("group_" + d);
    if(b) {
        var a = b.groupMemTree;
        memItem = b.memMap.get(c);
        a.deleteBuddyItem(d, memItem);
    }
}
function initGroupMemList(e, j) {
    var f = jGroupUriToId(e);
    var c = jMemUriToId(j.MemberUri);
    var b = f + "_" + c;
    var i = __group_mem.get(b);
    if(i == null) {
        i = new GroupMemDefault();
        $.extend(i, j);
        extendMemItem(i);
        __group_mem.put(b, i);
    } else {
        $.extend(i, j);
        extendMemItem(i);
    }
    var g = __chatWindowMap.get("group_" + f);
    if(g) {
        var h = g.groupMemTree;
        var a = g.memMap.get(j.id);
        if(a) {
            $.extend(i, a);
            a = i;
        } else {
            g.memMap.put(i.id, i);
        }
        var d = h.getBuddyItem(f, i.id);
        if(d.size() > 0) {
            h.updateBuddyItem(f, i);
        } else {
            h.insertBuddyItem(i, f);
        }
    }
}
function handleInviteGroup(b) {
    var a = new Object();
    a.success = function(c, d) {
        handleInviteGroupSuccess(c, d, b);
    };
    a.error = function(c, d) {
        handleInviteGroupError(c, d, b);
    };
    a.url = formatString(opInviteJoinUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId,"inteeNn":__user.nn,"gUri":b.gUri,"iUri":b.iUri,"interNn":b.iNn};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
function handleInviteGroupSuccess(b, d, c) {
    var a = b;
    if(typeof a.rc == "number" && a.rc == 200) {
        startGroupSubscribe(c.gUri, function() {
            var f = new GroupDefault();
            f.id = jGroupUriToId(c.gUri);
            f.n = c.gN;
            f.gUri = c.gUri;
            f.crc = "1234";
            f.i = 3;
            extendGroupItem(f);
            if(__groupMap.containsKey(f.id)) {
                __groupMap.remove(f.id);
                __groupTree.deleteGroupItem(f.id);
            }
            __groupMap.put(f.id, f);
            __groupTree.insertGroupItem(f);
            $.popWinMsg({gName:f.n,group:f,msgN:"msg8",openWin:openGroupChatWindow});
            var e = __chatWindowMap.get("group_" + f.id);
            if(e && e.isShow) {
                openGroupChatWindow(this, f.id);
            }
        });
    } else {
        if(typeof a.rc == "number" && a.rc == 437) {
            if(__user.isVip) {
                $.popWinMsg({msgN:"msg11"});
            } else {
                $.popWinMsg({msgN:"msg12",isClose:false});
            }
        }
    }
}
var __higRetry = 0;
function handleInviteGroupError(a, c, b) {
    if(__higRetry < 3) {
        __higRetry++;
        window.setTimeout(function() {
            handleInviteGroup(b);
        }, 500);
    } else {
        __higRetry = 0;
        alert("处理加入群失败，请重新登录");
    }
}
function approveInviteGroup(e, c) {
    var b = new Object();
    var a = "";
    for(var d = 0; d < e.JoinItem.length; d++) {
        a += e.JoinItem[d].Uri + "-" + e.JoinItem[d].nn + "|";
    }
    b.success = function(f, g) {
        approveInviteGroupSuccess(f, g, e);
    };
    b.error = handleInviteGroupError;
    b.url = formatString(approveInviteUrl, __version++);
    b.type = "POST";
    b.data = {"ssid":__sessionId,"appAd":c,"gUri":e.gUri,"iNn":e.iNn,"iR":e.iR,"iUri":e.iUri,"joinItem":a};
    b.dataType = "json";
    b.cache = false;
    $.ajax(b);
}
function approveInviteGroupSuccess(a, c, b) {
}
function approveInviteGroupError(a, c, b) {
}
function handleApplication(c, a) {
    var b = {};
    b.success = function(d, e) {
        handleApplicationSuccess(d, e, c);
    };
    b.error = handleApplicationError;
    b.url = formatString(PGHandleApplicationUrl, __version++);
    b.type = "POST";
    b.data = {"ssid":__sessionId,"appAd":a,"gUri":c.GroupUri,"note":"","userUri":c.ApplyGroupUserEntity[0].Uri};
    b.dataType = "json";
    b.cache = false;
    $.ajax(b);
}
function handleApplicationSuccess(a, c, b) {
    if(typeof a.rc == "number" && a.rc == 432) {
        $.popWinMsg({msgN:"msg14",isClose:false});
    }
}
function handleApplicationError(a, c, b) {
}
function getGroupPerInfo(a) {
    var b = new Object();
    b.success = getGroupPerInfoSuccess;
    b.error = getGroupPerInfoError;
    b.url = formatString(getGroupPerInfoUrl, __version++);
    b.type = "POST";
    b.data = {"ssid":__sessionId,"gUri":a};
    b.dataType = "json";
    b.cache = false;
    $.ajax(b);
}
var __gpiRetry = 0;
function getGroupPerInfoSuccess(d, f) {
    var a = d;
    if(typeof a.rc == "number" && a.rc == 200) {
        var e = a.rv;
        for(var c = 0; c < e.length; c++) {
            var b = e[c];
            updateGroupInfo(b.rv, false, true);
        }
    } else {
        if(typeof a.rc == "number" && a.rc == 310) {
            redirectLogin();
        } else {
            if(__gpiRetry < 3) {
                __gpiRetry++;
            } else {
                __gpiRetry = 0;
                alert(formatString("获取群组个人信息失败，请重新登录。错误代码：{0}", typeof a.rc == "number" ? a.rc : 500));
                redirectLogin();
            }
        }
    }
}
function getGroupPerInfoError(a, c, b) {
    if(__gpiRetry < 3) {
        __gpiRetry++;
    } else {
        __gpiRetry = 0;
        alert(formatString("获取用户群组个人信息失败，请重新登录。错误原因：{0}", c));
        redirectLogin();
    }
}
function startGroupSubscribe(b, c) {
    var a = new Object();
    a.success = function(d, e) {
        startGroupSubscribeSuccess(c, b, d, e);
    };
    a.error = function(d, f, e) {
        startGroupSubscribeError(d, f, e, b);
    };
    a.url = formatString(GroupSubscribeUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId,"GroupUris":b,"Type":1};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
var __sgssRetry = 0;
function startGroupSubscribeSuccess(e, c, b, d) {
    var a = b;
    if(typeof a.rc == "number" && a.rc == 200) {
        if(typeof e == "function") {
            e();
        }
    } else {
        if(typeof a.rc == "number" && a.rc == 310) {
            redirectLogin();
        } else {
            if(__sgssRetry < 3) {
                __sgssRetry++;
                window.setTimeout(function() {
                    startGroupSubscribe(c);
                }, 500);
            } else {
                __sgssRetry = 0;
                alert(formatString("由于网络原因,订阅群组失败，请重新登录。错误代码：{0}", typeof a.rc == "number" ? a.rc : 500));
                redirectLogin();
            }
        }
    }
}
function startGroupSubscribeError(a, d, b, c) {
    if(__sgssRetry < 3) {
        __sgssRetry++;
        window.setTimeout(function() {
            startGroupSubscribe(c);
        }, 500);
    } else {
        __sgssRetry = 0;
        alert(formatString("订阅群组失败，请重新登录。错误原因：{0}", d));
        redirectLogin();
    }
}
function getCred() {
    var a = new Object();
    a.success = getCredSuccess;
    a.error = getCredError;
    a.url = formatString(getCredUrl, __version++);
    a.type = "POST";
    a.data = {"ssid":__sessionId};
    a.dataType = "json";
    a.cache = false;
    $.ajax(a);
}
var __gcRetry = 0,__gcRetry1 = 0;
function getCredSuccess(c, d) {
    var a = c;
    if(!!a && typeof a.rc == "number" && a.rc == 200) {
        __user.c = a.rv.c;
        __user.c = encodeURIComponent(__user.c);
        if(__user.c) {
            var b = formatString(__mySpaceUrl, __user.c);
            $("#myspace_icon").attr("href", b);
            $("#myspace").attr("href", b);
        }
        __gcRetry1 = 0;
    } else {
        if(__gcRetry1 < 3) {
            __gcRetry1++;
            getCred();
        }
    }
    __gcRetry = 0;
}
function getCredError(a, c, b) {
    if(__gcRetry < 3) {
        __gcRetry++;
        getCred();
    }
}
var code_LoadingPanel = null;
var codeImgFlag = true;
function createCcpUI(j, d) {
    divMask.removeClass("none");
    if($("#codeDiv").length > 0) {
        $("#codeDiv").removeClass("none");
        $("#txtCode").focus();
        return;
    }
    var k = $('<div id="codeDiv" class="hint" style="top: 10px;z-index: 10002;">');
    var f = $("<h3><span></span><i></i><b>输入验证码</b></h3>");
    var b = $('<div class="hint_btn_new1"><a href="" class="mr5">确 定</a></div>');
    var g = $('<div class="hint_cont_new1 pb8"></div>');
    var e = $('<div class="modify_new2"><label for="textfield">' + d + "</label><br /></div>");
    if(j == 0) {
        e.append($('<div class="input_text_c"><input type="text" name="txtCode" id="txtCode" class="frame_246-24 w125" maxlength="4" />' + '<img id="ccpimg"title="如果您无法识别验证码，点击图片更换" src="images/login20120903/ccploading.png" /><a id="change_link"' + 'href="#">看不清，换一张</a><p id="error" class="none">输入的验证码有误，请重新输入</p></div>'));
    } else {
        if(j == 1) {
            e.append($('<div class="input_text_c"><input type="text" name="txtCode" id="txtCode" class="frame_246-24 w125" maxlength="4" />' + '<p id="error" class="none">输入的验证码有误，请重新输入</p></div>'));
        }
    }
    g.append(e).append(b);
    k.append(f).append(g);
    k.appendTo(document.body);
    code_LoadingPanel = $.fn.loadingPanel({target:"codeDiv"});
    var i = getViewportCenter();
    var a = {"top":i.y - k.height() / 2,"left":i.x - k.width() / 2};
    if(a.top < 50) {
        a.top = 50;
    }
    if(a.left < 50) {
        a.left = 50;
    }
    k.css({"top":a.top + "px","left":a.left + "px"});
    b.find("a").bind("click", function(c) {
        if(j == 0) {
            validateCode();
        } else {
            if(j == 1) {
            }
        }
        return false;
    });
    f.find("span").bind("click", function(c) {
        $("#codeDiv").addClass("none");
        divMask.addClass("none");
    });
    if(j == 0) {
        $("#change_link").bind("click", function() {
            resetCcp();
            return false;
        });
        setTimeout(function() {
            resetCcp();
        }, 500);
    }
}
function resetCcp() {
    $("#ccpimg").attr("src", formatString(getPicCodeUrl, "ccpsession", Math.random()));
    return false;
}
function validateCode() {
    code_LoadingPanel.show();
    var a = {};
    a.type = "POST";
    a.dataType = "json";
    a.url = formatString(ValidatePicCodeUrl, __version++);
    a.data = {"Ccp":$("#txtCode").val()};
    a.cache = false;
    a.success = function(b) {
        code_LoadingPanel.hide();
        if(b.rc == 200) {
            $("#codeDiv").addClass("none");
            divMask.addClass("none");
            codeImgFlag = false;
        } else {
            $("#error").removeClass("none");
            resetCcp();
        }
        $("#txtCode").val("");
    };
    a.error = function() {
        code_LoadingPanel.hide();
        $("#txtCode").val("").focus();
        $("#error").removeClass("none");
        resetCcp();
    };
    $.ajax(a);
}
function sendSmsCode() {
    var a = {};
    a.type = "POST";
    a.dataType = "json";
    a.url = formatString(GetSmsCodeCodeUrl, __version++);
    a.data = {"mn":__user.mn};
    a.cache = false;
    a.success = function(b) {
        if(b.rc == 0) {
        } else {
        }
    };
    a.error = function() {
    };
    $.ajax(a);
}
var tryGetALInfoTimes = 0;
function getALInfo() {
    var a = {};
    a.url = formatString(getALInfoUrl, __version++);
    a.type = "POST";
    a.dataType = "json";
    a.data = {"ssid":__sessionId};
    a.cache = false;
    a.success = getALInfoSuccess;
    a.error = getALInfoError;
    $.ajax(a);
}
function getALInfoSuccess(f) {
    if(!!f && f.rc == 200) {
        tryGetALInfoTimes = 0;
        var e = f.rv;
        var d = $("#phone_list_content"),a;
        $(".phone_leisure").addClass("none");
        d.removeClass("none");
        for(var c = 0,b = e.length; c < b; c++) {
            a = createItem(e[c]);
            d.append(a);
        }
    } else {
        if(tryGetALInfoTimes < 3) {
            getALInfo();
            tryGetALInfoTimes++;
        }
    }
}
function getALInfoError() {
    if(tryGetALInfoTimes < 3) {
        getALInfo();
        tryGetALInfoTimes++;
    }
}
function initTXLlist() {
    var a = $("#phone_list_content");
    a.bind("mouseover", function(d) {
        d = window.event || d;
        var c = d.target || d.srcElement;
        var b = $(c).closest("li");
        b.addClass("hover");
        stopPropagation(d);
        preventDefault(d);
    }).bind("mouseout", function(d) {
        d = window.event || d;
        var c = d.target || d.srcElement;
        var b = $(c).closest("li");
        b.removeClass("hover");
        stopPropagation(d);
        preventDefault(d);
    }).bind("click", function(h) {
        h = window.event || h;
        var g = h.target || h.srcElement;
        var b = $(g).closest("li");
        var c = b.attr("guid");
        var d = b.attr("uid");
        var f = b.attr("mn");
        if(g.className.toLowerCase() == "friend_control") {
            $.increaseCounter(500400026);
            createPopForTXL(b, c);
        } else {
            if(g.className.toLowerCase() == "friend_add") {
                $.increaseCounter(500400024);
                AddBuddy({a:"1",u:f,successCallback2:function(e) {
                    if(typeof e.rc == "number" && (e.rc == 521 || e.rc == 200)) {
                        $.increaseCounter(500400025);
                        $(g).remove();
                    }
                }});
            } else {
                if(g.className.toLowerCase() == "friend_news") {
                    $.increaseCounter(500400023);
                    createFreeSMSTab(c, d, f);
                }
            }
        }
        stopPropagation(h);
        preventDefault(h);
    }).bind("dblclick", function(i) {
        i = window.event || i;
        var h = i.target || i.srcElement;
        var c = $(h).closest("h5");
        if(c.length > 0) {
            var b = $(h).closest("li");
            var d = b.attr("guid");
            var f = b.attr("uid");
            var g = b.attr("mn");
            if(!isMobileNoReg.test(g) || __user.ca.toLowerCase() != "cmcc") {
                createAddTXLTab(d);
            } else {
                createFreeSMSTab(d, f, g);
            }
        }
        stopPropagation(i);
        preventDefault(i);
    });
}
function createItem(i) {
    var d = new PhoneDefault();
    $.extend(d, i);
    __phoneMap.put(d.GUID.toString(), d);
    var a = isMobileNoRegForTXL.test(d.HMobile) ? d.HMobile : (isMobileNoRegForTXL.test(d.OMobile) ? d.OMobile : ((isMobileNoRegForTXL.test(d.OPhone) ? d.OPhone : ((isMobileNoRegForTXL.test(d.HPhone) ? d.HPhone : (d.HMobile || d.OMobile || d.OPhone || d.HPhone))))));
    var h = d.FN.length;
    var f = h > 8 ? cnSubstr(a, 11, false) : cnSubstr(a, 21 - h, false);
    var g = $('<li guid="' + d.GUID + '" uid="" mn="' + a + '" class="" style="cursor:default;"><div class="friends_info"><h5><strong class="BuddyList_Container"><b>●</b></strong>' + cnSubstr(d.FN, 8, false) + "<span>" + f + "</span></h5></div>" + '<div class="friend_control"></div></li>');
    if(isMobileNoRegForTXL.test(a)) {
        if(a.indexOf("+") == 0) {
            a = a.substr(3);
            g.find(".friends_info span").text(a);
            g.attr("mn", a);
        }
        if(__user.ca.toLowerCase() == "cmcc") {
            d.tel = {"uid":"","mn":a};
            g.append('<div class="friend_news" title="联系对方">');
        }
    }
    d.arr = [],len = 0;
    if(isMobileNoReg.test(a)) {
        var c,e = false;
        function b(j) {
            if(isMobileNoRegForTXL.test(j)) {
                if(j.indexOf("+") == 0) {
                    j = j.substr(3);
                }
                c = isBuddy(j);
                if(!e && !!c) {
                    if(g.attr("mn") != j) {
                        d.arr[len++] = "|" + g.attr("mn");
                        g.find(".friends_info span").text(j);
                        g.attr("mn", j);
                    }
                    g.attr("uid", c);
                    if(!!d.tel) {
                        d.tel.uid = c;
                        d.tel.mn = j;
                    }
                    e = true;
                } else {
                    if(g.attr("mn") != j) {
                        d.arr[len++] = c + "|" + j;
                    }
                }
            }
        }
        b(d.HMobile);
        b(d.OMobile);
        b(d.OPhone);
        b(d.HPhone);
        if(!e) {
            g.append('<div class="friend_add" title="加为好友"></div>');
        }
    }
    return g;
}
function delAlinfo(a) {
    var c = $("#phone_list_content");
    var b = {};
    b.type = "POST";
    b.dataType = "json";
    b.data = {"ssid":__sessionId,"guid":a};
    b.url = formatString(deleteALInfoOneUrl, __version++);
    b.cache = false;
    b.success = function(d) {
        c.find('li[guid="' + a + '"]').remove();
        if(c.find("li").length <= 0) {
            c.addClass("none");
            c.prev().removeClass("none");
        }
    };
    b.error = function() {
    };
    $.ajax(b);
}
function isBuddy(e) {
    var c = "",d = __contactMap.values();
    if(e == __user.mn) {
        c = __user.uid;
    }
    for(var b = 0,a = d.length; b < a; b++) {
        var f = d[b];
        if(e == f.mn) {
            c = f.uid;
            break;
        }
    }
    return c;
}
var PopForTXLFlag = false;
function createPopForTXL(b, g) {
    var k = $("#popForTongXunLu"),e = k.find("ul");
    k.removeClass("none");
    e.attr("guid", g);
    e.find('li[t=""]').remove();
    var f = __phoneMap.get(g);
    if(f.arr.length > 0) {
        var d,l;
        for(var c = 0,a = f.arr.length; c < a; c++) {
            d = f.arr[c].split("|");
            l = $('<li t="" uid="' + d[0] + '" mn="' + d[1] + '"><a class="icon_contact" href="javascript:;">' + d[1] + "</a></li>");
            k.find("ul").prepend(l);
        }
    }
    e.find("li").bind("mouseover", function() {
        this.className = "hover";
    }).bind("mouseout", function() {
        this.className = "";
    });
    if(!PopForTXLFlag) {
        PopForTXLFlag = true;
        k.bind("mouseover", function(i) {
            clearTimeout(__tmpTimer);
            k.removeClass("none");
        }).bind("mouseout", function(i) {
            if(isMouseOut(this, i)) {
                clearTimeout(__tmpTimer);
                k.addClass("none");
            }
        });
        e.bind("click", function(o) {
            o = window.event || o;
            var n = o.target || o.srcElement;
            var i = $(n).closest("li");
            var j = $(this).attr("guid");
            var p = __phoneMap.get(j);
            switch(i.attr("t").trim()) {case"del":$.increaseCounter(500400020);$.fn.fetionHintWindow({modal:true}).show('你确定要删除"' + p.FN + '"吗', "删除联系人", "OKCancel", function() {
                $.increaseCounter(500400021);
                delAlinfo(j);
            }, function() {
                $.increaseCounter(500400022);
            }, "images/icon_wenhao.png");break;case"edit":$.increaseCounter(500400017);createAddTXLTab(j);break;default:createFreeSMSTab(j, i.attr("uid").trim(), i.attr("mn").trim());break;}
            k.addClass("none");
            stopPropagation(o);
            preventDefault(o);
        });
    }
    var h = $(b);
    var m = h.offset();
    if(m.top + k.height() > getViewportHeight() - 20) {
        m.top = m.top - k.height();
    }
    k.css({"top":m.top + h.height(),"left":m.left - (k.outerWidth() - h.width())});
    clearTimeout(__tmpTimer);
    __tmpTimer = setTimeout(function() {
        k.addClass("none");
        clearTimeout(__tmpTimer);
    }, 2000);
}
var imageCarousel = function() {
    function d(f, j, h) {
        if(f.forEach) {
            f.forEach(j, h);
        } else {
            for(var g = 0,e = f.length; g < e; g++) {
                j.call(h, f[g], g, f);
            }
        }
    }
    function a(f) {
        b(f, 0);
        for(var e = 0; e < 20; e++) {
            (function() {
                var g = e * 5;
                setTimeout(function() {
                    b(f, g);
                }, e * 25);
            })(e);
        }
    }
    function c(f) {
        for(var e = 0; e <= 20; e++) {
            (function() {
                var g = 100 - e * 5;
                setTimeout(function() {
                    b(f, g);
                }, e * 25);
            })(e);
        }
    }
    function b(e, f) {
        if(e.filters) {
            e.style.filter = "alpha(opacity=" + f + ")";
        } else {
            e.style.opacity = f / 100;
        }
    }
    return{scroll:function(j, i, g, l) {
        var f = this;
        var k = 0;
        var h = 0;
        this.info = $("#banner_info");
        this.img = document.getElementById(i).getElementsByTagName("a");
        d(this.img, function(o, n, m) {
            if(n != 0) {
                b(o, 0);
            }
        });
        var e = setInterval(function() {
            if(k < j - 1) {
                k++;
            } else {
                k = 0;
            }
            f.fade(k, h);
            h = k;
        }, g);
        document.getElementById(i).onmouseover = function() {
            clearInterval(e);
        };
        document.getElementById(i).onmouseout = function() {
            e = setInterval(function() {
                if(k < j - 1) {
                    k++;
                } else {
                    k = 0;
                }
                f.fade(k, h);
                h = k;
            }, g);
        };
    },fade:function(e, g) {
        if(e == g) {
            return;
        }
        var f = this;
        c(f.img[g]);
        a(f.img[e]);
        this.info.html('<a href="' + f.img[e].href + '" target="' + f.img[e].target + '"><img src="' + f.img[e].firstChild.src + '" title="' + f.img[e].firstChild.title + '" alt="' + f.img[e].firstChild.title + '" /></a>');
        $("#banner_info > a").bind("click", function() {
            $.increaseCounter(500900001);
        });
    }};
}();