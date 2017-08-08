//聊天 侯克磊
function Chat() {
    var chathtml = '', rowcol, sendtxt, txtlength = 0, isbig = false, channel = 0, timeScBotm = null, fontsize = 40, pianyi = 0, content_w = 0;
    this.isscroll = true;
    this.issend = true;
    this.timeChatSpeed = null;
    this.clickclubid = 0;
    this.ChatTime = null;
    /*加载聊天*/
    var arr_channel;
    this.LoadPage = function () {
        //        var html = "<div id='equipment1' style='width:64px;height:64px;position:absolute;left:375px;top:89px;background:green;cursor:pointer;' class='div_equipment' onclick='OpenBagByPlayers($(this))'></div><div id='equipment2' style='width:64px;height:64px;position:absolute;left:449px;top:89px;background:green;cursor:pointer;' class='div_equipment' onclick='OpenBagByPlayers($(this))'></div><div id='equipment3' style='width:64px;height:64px;position:absolute;left:523px;top:89px;background:green;cursor:pointer;' class='div_equipment' onclick='OpenBagByPlayers($(this))'></div><div id='equipment4' style='width:64px;height:64px;position:absolute;left:597px;top:89px;background:green;cursor:pointer;' class='div_equipment' onclick='OpenBagByPlayers($(this))'></div>";
        //        AddBox(180, 87, 700, 423, null, html, null, null, null, 'PlayerBox', true, true, null);
        //选频道鼠标悬停和离开效果
        $('#divChatlist div').bind('mouseover', function () { $(this).attr('class', 'div_chatsel'); $(this).css('color', '#d2ff00') });
        $('#divChatlist div').bind('mouseout', function () { $(this).attr('class', null); $(this).css('color', 'white') });
        $('#divChatlist div').bind('click', function () { objChat.ChangeSure($(this)) });
        //$('#hidri').val('10');
        //$('#hidti').val('10');
        objChat.GetChat();
        if (objChat.ChatTime == null)
            objChat.ChatTime = setInterval("objChat.GetChat()", 4000);
        arr_channel = new Array(Default_Chat6, Default_Chat7, Default_Chat8, Default_Chat9)
    }
    /*获取聊天信息*/
    this.GetChat = function () {
        var channelRead = $('#divChangeBtn').html();
        channelRead = channelRead == Default_Chat1 ? 0 : channelRead == Default_Chat2 ? 1 : channelRead == Default_Chat3 ? 2 : 3;
        $.post('Default.aspx', { type: 0, channel: channelRead, sendclub: strClubName, teamid: $('#hidti').val(), roomid: $('#hidri').val(), isBig: (isbig ? 1 : 0), ai: $('#hidAccountID').val() }, function (result) {
            if (result == '-2') {
                CreateCovering(0, 0, 1000, 570, 1000, 1, $('#totalMainDiv'));
                ShowMsg(Default_Chat4, Default_Chat5, function () {
                    location.href = gameAddress;
                }, null, -1, null, 340, 200, null, 'chatMsg1', false);
                $('#chatMsg1 .divMove').unbind().css({ cursor: 'default' });
                $('#chatMsg1').css({ zIndex: 1001 });
                clearInterval(objChat.ChatTime);
                objChat.ChatTime = null;
                return;
            } else if (result == '-3') {
                CreateCovering(0, 0, 1000, 570, 1000, 1, $('#totalMainDiv'));
                ShowMsg(Chat_1, Default_Sure, function () {
                    location.href = gameAddress;
                }, null, -1, null, 340, 200, null, 'chatMsg1', false);
                $('#chatMsg1 .divMove').unbind().css({ cursor: 'default' });
                $('#chatMsg1').css({ zIndex: 1001 });
                clearInterval(objChat.ChatTime);
                objChat.ChatTime = null;
                return;
            }
            result = result.substring(0, result.length - 1);
            result = result.split("∽");
            if (isbig && !objChat.isscroll) {
                if ($('#divContent div:last')[0]) {
                    if (result[result.length - 1].split('⊥')[5] != $('#divContent div:last a:last').html()) {
                        $('.div_down').css('display', 'block');
                    }
                }
            }
            chathtml = "";
            txtlength = 0;
            if (parseInt($('#divChat').css('height')) == 30) {
                $('#txtSend').val(result[0].split('⊥')[3]);
            }
            for (var v = txtlength; v < result.length; v++) {
                chathtml += objChat.GetHtml(result[result.length - 1 - v]);
            }
            $('#divContent').html(chathtml);
            /*根据是否是大窗口和是否可以滚动来绑定内容框*/
            if (objChat.isscroll)
                objChat.ScrollBotm();
        });
    }
    /*返回一条信息的html*/
    var arr_fontcolor = new Array('#ffef6a', '#00ff36', '#ffb283', '#14ecff', '#ff6598', '#fc00ff');
    this.clickclubname = "";
    var isHS = "class='a_clubname' style='cursor:pointer;color:#ffffff' onclick='objChat.SiLiao(this)' onmouseover='$(this).css({color:\"#d2ff00\",textDecoration:\"underline\"})' onmouseout='$(this).css({color:\"#ffffff\",textDecoration:\"none\"})'";
    this.GetHtml = function (dtchat) {
        var ishavesl = "style='color:#ffffff'";
        dtchat = dtchat.split('⊥');
        if (dtchat[1] == "-1") {
            $.post('Default.aspx', { type: 5 }, function () { });
            return;
        }
        var isSelf = strClubName != dtchat[1] ? false : true;
        var shadow = "position:absolute;top:0;left:-2px;color:#000000;z-index:-1;display:none;";
        if (dtchat[0] == '0') {//世界
            if (isSelf)//自己世界说话
                return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[0] + "'>【" + arr_channel[0] + "】<a " + ishavesl + ">" + dtchat[1] + "</a>：" + dtchat[3] + "</div>";
            else {//别人世界说话
                try {
                    if (dtchat[1].indexOf(Default_Chat10) < 0)
                        ishavesl = isHS;
                } catch (e) { }
                return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[0] + "'>【" + arr_channel[0] + "】<a " + ishavesl + ">" + dtchat[1] + "</a><a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a>：" + dtchat[3] + "</div>";
            }
        }
        if (dtchat[0] == '1') {//广播
            if (!isSelf)
                ishavesl = isHS;
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[1] + "'>【" + arr_channel[1] + "】<a " + ishavesl + ">" + dtchat[1] + "</a><a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a>：<a style='color:" + arr_fontcolor[1] + "'>" + dtchat[3] + "</a></div>";
        }
        if (dtchat[0] == '2') {//组队说话
            if (!isSelf)
                ishavesl = isHS;
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[2] + "'>【" + arr_channel[2] + "】<a " + ishavesl + ">" + dtchat[1] + "</a>：<a style='color:" + arr_fontcolor[2] + "'>" + dtchat[3] + "</a><a style='display:none'>" + dtchat[5] + "</a><a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a></div>";
        }
        if (dtchat[0] == '3') {//球会说话
            if (!isSelf)
                ishavesl = isHS;
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[3] + "'>【" + arr_channel[3] + "】<a " + ishavesl + ">" + dtchat[1] + "</a><a style='display:none'>" + dtchat[4] + "</a>：<a style='color:" + arr_fontcolor[3] + "'>" + dtchat[3] + "</a><a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a></div>";
        }
        if (dtchat[0] == '4')//系统公告
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:#f0ff00'>【" + Default_Chat11 + "】" + dtchat[3] + "</a></div>";
        if (dtchat[0] == '5')//错误信息
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:red'>&nbsp&nbsp" + dtchat[3] + "</a></div>";
        if (dtchat[0] == '6') {//开出奢侈品
            if (dtchat[1].indexOf(Default_Chat10) < 0 && !isSelf)
                ishavesl = isHS;
            return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[5] + "'>&nbsp;&nbsp;<a " + ishavesl + ">" + dtchat[1] + "</a>" + dtchat[3] + "<a class='a_ClubID' style='display:none'>" + dtchat[4] + "</a></div>";
        }
        if (dtchat[0] == '7') {//私聊时说话
            if (isSelf)//自己对别人说
                return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[4] + "'>【" + Default_Chat12 + "】" + Default_Chat13 + "<a style='color:white'>" + dtchat[2] + "</a>" + Default_Chat14 + "：" + dtchat[3] + "<a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a></div>";
            else {//别人对自己说
                if (dtchat[1].indexOf(Default_Chat10) < 0)
                    ishavesl = isHS;
                return "<div class='rowcss' style='width:" + (content_w - 4) + "px;color:" + arr_fontcolor[4] + "'>【" + Default_Chat12 + "】<a " + ishavesl + ">" + dtchat[1] + "</a><a style='display:none'>" + dtchat[4] + "</a>" + Default_Chat15 + "：" + dtchat[3] + "</a><a style='display:none' class='a_ClubID'>" + dtchat[4] + "</a></div>";
            }
        }
    }
    /*屏蔽玩家*/
    this.ShieldUser = function () {
        $.post('Default.aspx', { type: 16, shieldClubID: objChat.clickclubid }, function (result) {
            ShowMsg('举报成功', null, null, null, null, null, 10, 370, null, 'chatMsg1', false);
        });
    }
    /*私聊*/
    this.SiLiao = function (thename) {
        objChat.clickclubname = $($(thename).parent().find('a').get(0)).html();
        cName = objChat.clickclubname;
        objChat.clickclubid = $(thename).parent().find('.a_ClubID').html();
        $('.div_tipbox').css({ left: $(thename).offset().left - $('#totalMainDiv').offset().left + 40, top: $(thename).offset().top - $('#totalMainDiv').offset().top - 12, display: 'block' });
        return false;
    }
    /*私聊*/
    this.PrivateChat = function () {
        receiobj = objChat.clickclubname;
        $('#txtSend').val(Default_Chat16 + '[' + objChat.clickclubname + ']' + Default_Chat14 + '：');
        $('.div_tipbox').css('display', 'none');
        objChat.setCursorPos();
    }
    this.RemovePrivateChat = function (e) {
        var tag = e.target || window.event.srcElement;
        if (tag.className == 'divContent' || tag.className == 'rowcss')
            $('.div_tipbox').css({ display: 'none' });
    }
    //发送信息
    var receiobj = -1, resinfo = '', chann;
    this.Chat = function () {
        if (parseInt($('#divChat').css('height')) == 30)
            return;
        if ($('#divChannel').html() == Default_Chat1)
            channel = 0;
        var ct = $('#txtSend').val();
        ct = ct.substring(0, ct.indexOf('：'));
        if (ct != '') {
            var i1 = ct.indexOf('['), i2 = ct.indexOf(']');
            if (i1 >= 0 && i2 >= 0) {
                receiobj = ct.substring(i1 + 1, i2);
                channel = 7;
            }
        }
        chann = channel;
        resinfo = objChat.CheckSend();
        if (resinfo != '') {
            receiobj = chann;
            chann = 5;
            sendtxt = resinfo;
        }
        objChat.issend = false;
        objChat.ScrollBotm();
        if (chann == 2)
            receiobj = $('#hidri').val();
        else if (chann == 3)
            receiobj = $('#hidti').val();
        if (strClubName == '')
            return;
        var content = '';
        if (chann == 7)
            content = sendtxt.substring(sendtxt.indexOf('：') + 1, sendtxt.length)
        else
            content = sendtxt;
        content = ClearLanguage(content);
        var ai = $('#hidAccountID').val();
        $.post('Default.aspx', { type: 1, channel: chann, sendclub: strClubName, receiobj: receiobj == null ? -1 : receiobj, content: content, ai: ai }, function (result) {
            if (result == '-1')
                $.post('Default.aspx', { type: 1, channel: 5, sendclub: strClubName, receiobj: 0, content: Default_Chat17, ai: ai });
            else if (result == '-2')
                location.href = 'MainError.aspx';
            else if (result == '-3')
                $.post('Default.aspx', { type: 1, channel: 5, sendclub: strClubName, receiobj: 1, content: Default_Chat18, ai: ai });
            else if (result == '-4') {
                ShowMsg(Chat_2, null, null, Chat_3, null, null, 10, 370, null, 'chatMsg1', false);
            }
            else if (result == '1') {
                CreateCovering();
                ShowMsg(Default_Chat19, null, function () {
                    $('#divCovering,#chatMsg1').remove();
                    $.post('Default.aspx', { type: 1, channel: 1, sendclub: strClubName, receiobj: -1, content: content, ai: ai, isSend: 1 });
                    objChat.ChatTxtHandle();
                }, Default_Chat20, function () { $('#divCovering').remove(); objChat.ChatTxtHandle(); }, null, 10, 370, null, 'chatMsg1', false, Default_Cancel);
                return;
            }
            objChat.ChatTxtHandle();
        });
    }
    //发送完信息输入框处理
    this.ChatTxtHandle = function () {
        if (channel == 7)
            $('#txtSend').val($('#txtSend').val().substring(0, $('#txtSend').val().indexOf('：') + 1));
        else if (chann == 5)
        { }
        else
            $('#txtSend').val('');
        objChat.setCursorPos();
        if (objChat.timeChatSpeed == null)
            objChat.timeChatSpeed = setTimeout("objChat.issend=true;objChat.timeChatSpeed=null;", 2000);
        objChat.GetChat();
    }
    //显示频道列表
    this.ChangeChannel = function () {
        //$('#divChangeBtn').css('visibility', 'hidden');
        if ($('#divChatlist').css('display') == 'none')
            $('#divChatlist').css('display', 'block');
        else
            $('#divChatlist').css('display', 'none');
    }
    //改换频道
    this.ChangeSure = function (thediv) {
        channel = $('#divChatlist div').index(thediv);
        //        channel = channel == 4 ? 0 : channel;
        //        if (channel == 3)
        //            $('#txtSend').val('对[姓名]说：');
        //        else
        $('#txtSend').val('');
        $('#divChatlist div:eq(4)').html(thediv.html());
        $('#divChangeBtn').html(thediv.html());
        $('#divChangeBtn').css('visibility', 'visible');
        $('#divChatlist').css('display', 'none');
        objChat.setCursorPos();
    }
    //判断是否能够发送和处理发送的文本
    this.CheckSend = function () {
        sendtxt = $('#txtSend').val();
        if ($('#hidri').val() == '-1' && channel == 2)
            return Default_Chat21;
        if ($('#hidti').val() == '-1' && channel == 3)
            return Default_Chat22;
        else if (!objChat.issend)
            return Default_Chat23;
        else if (sendtxt.length > fontsize)
            return Default_Chat24;
        else if (sendtxt == "" || $.trim(sendtxt).length < 1)
            return Default_Chat25;
        else if (channel == 7) {
            var content = sendtxt.substring(sendtxt.indexOf('：') + 1, sendtxt.length);
            if (content == '')
                return Default_Chat25;
        }
        else if (sendtxt.indexOf('<') >= 0 && sendtxt.indexOf('>') >= 0) {
            while (sendtxt.indexOf('<') >= 0 && sendtxt.indexOf('>') >= 0) {
                sendtxt = sendtxt.replace('<', '');
                sendtxt = sendtxt.replace('>', '');
            }
            $('#txtSend').val(sendtxt);
        }
        return '';
    }
    /*检测回车*/
    this.KeyDown = function (event) {
        var code = event.keyCode ? event.keyCode : event.which ? event.which : event.keyCode;
        if (code == 13) {
            objChat.Chat();
            event.returnValue = false;
        }
        return false;
    }
    /*光标移动到最后面*/
    this.setCursorPos = function () {
        try {
            $('#txtSend').focus();
            var x = $('#txtSend')[0];
            var txtRange = x.createTextRange();
            txtRange.moveStart("character", x.value.length);
            txtRange.moveEnd("character", 0);
            txtRange.select();
        } catch (e) { }
    }
    //变大变小窗口
    var chang_h = 108; //改变聊天窗口的大小
    this.ChangeChat = function () {
        objChat.RemoveChatlist()
        if ($('#divChat').css('height') == '249px') {
            $('#divChat').animate({ top: parseInt($('#divChat').css('top')) - chang_h, height: parseInt($('#divChat').css('height')) + chang_h }, 200, function () { });
            $('#divContent').animate({ height: parseInt($('#divContent').css('height')) + chang_h }, 200);
            $('#divChatlist').css('top', 253);
            isbig = true;
            objChat.setCursorPos();
        }
        else {
            $('#divChat').animate({ top: parseInt($('#divChat').css('top')) + chang_h, height: parseInt($('#divChat').css('height')) - chang_h }, 200, function () { });
            $('#divContent').animate({ height: parseInt($('#divContent').css('height')) - chang_h }, 200);
            $('#divChatlist').css('top', 145);
            isbig = false;
            $('.divScroll').css({ display: 'none' });
            objChat.setCursorPos();
        }
    }
    //显示滚动条
    this.ShowScroll = function () {
        clearTimeout(timeScroll);
        //        if (!isbig || $('.divScroll').css('display') == 'block')
        //            return;
        $('.divScroll').stop();
        $('.divScroll').css({ display: 'block', opacity: 0.01 });
        $('.divScroll').animate({ opacity: 1 }, 500);
    }
    var timeScroll = null;
    //隐藏滚动条
    this.RemoveScroll = function (e) {
        if (!isbig)
            return;
        if (!IsIn(e))
            timeScroll = setTimeout(function () { objChat.RemoveS() }, 500);
    }
    //隐藏滚动条
    this.RemoveS = function () {
        $('.divScroll').stop();
        $('.divScroll').animate({ opacity: 0 }, 500, function () { $('.divScroll').css({ display: 'none' }); objChat.ScrollBotm() });
    }
    //判断鼠标是否在聊天窗口范围内
    this.IsIn = function (e) {
        GetXY(e);
        if (m_x > 18 && m_x < 240 && m_y > 218 && m_y < 573)
            return true;
        return false;
    }
    this.timeSl = null;
    this.ScrollUp = function () {
        clearInterval(objChat.timeSl);
        objChat.timeSl = setInterval('objChat.UpMethod()', 40);
    }
    this.ScrollDown = function () {
        clearInterval(objChat.timeSl);
        objChat.timeSl = setInterval('objChat.DownMethod()', 40);
    }
    this.UpMethod = function () {
        objChat.isscroll = false;
        $('#divContent')[0].scrollTop = $('#divContent')[0].scrollTop - 8;
    }
    this.DownMethod = function () {
        objChat.isscroll = false;
        $('#divContent')[0].scrollTop = $('#divContent')[0].scrollTop + 8;
    }
    this.DivScrollUp = function () {
        objChat.isscroll = true;
        clearInterval(objChat.timeSl);
    }
    //移除频道列表
    this.RemoveChatlist = function () {
        if (parseInt($('#divChat').css('height')) == 30) {
            $('#txtSend').val('');
            $('#divChatlist').css({ top: 141 });
            $('#divChat').css({ height: 244, top: 328 });
            $('#divContent').css({ display: 'block' });
            $('#divContent').html('');
            $('#btnOpenChat').css({ display: 'none' });
            objChat.ScrollBotm();
            objChat.GetChat();
        }
        $('#divChatlist').css('display', 'none');
        $('#divChangeBtn').css('visibility', 'visible');
        objChat.setCursorPos();
    }
    //将滚动条滑动到最下面
    this.ScrollBotm = function () { $('#divContent')[0].scrollTop = 1000; }
    //缩小聊天框
    this.ChangeChatSize = function () {
        if (parseInt($('#divChat').css('height')) == 450) {
            $('#txtSend').val('');
            $('#divChatlist').css({ top: 141 });
            $('#divChat').animate({ height: 244, top: 328 }, function () {
                $('#divTaskShow1,#divTaskShow').css({ display: 'block' });
                $('#divChatTop').css({ background: 'url(' + ImgPath + 'Images/Default/chatbg.png)' });
//                if (parseInt($('#hidPlatForm').val()) != 7 && parseInt($('#hidPlatForm').val()) != 12)
//                    $('#divGuess').css({ display: 'block' });
                objChat.GetChat();
            });
            $('#divContent').css({ display: 'block' });
            $('#divContent').animate({ height: 218 });
            $('#divScrollUp').css({ top: 80 });
            $('#divScrollDown').css({ top: 130 });
            $('#divCSize').css({ top: 20 });
            $('#divChatlist,.divScroll,#divCSize,#btnOpenChat').css({ display: 'none' });
            $('#divChangeBtn').css('visibility', 'visible');
            $('#divChatlist').css({ top: 141 });
            objChat.setCursorPos();
        } else {
            $('#divChat').css({ height: 30, top: 546 });
            $('#divContent,#divCSize,.divScroll,#divCSizeBig').css({ display: 'none' });
            $('#divChatlist').css({ top: -77 });
            $('#btnOpenChat').css({ display: 'block' });
            objChat.GetChat();
        }
    }
    //放大聊天框
    this.ChangeChatSizeBig = function () {
        $('#divChat').animate({ height: 450, top: 120 });
        $('#divContent').animate({ height: 426 });
        $('#divCSize,#divCSizeBig,#divTaskShow1,#divTaskShow,.divScroll,#divGuess').css({ display: 'none' });
        $('#divChatTop').css({ background: 'url(' + ImgPath + 'Images/Default/chatbg1.png)' });
        $('#divScrollUp').css({ top: 200 });
        $('#divScrollDown').css({ top: 250 });
        $('#divCSize').css({ top: 0 });
        $('#divChatlist').css({ top: 347 });
        objChat.GetChat();
    }
    //显示聊天右侧按钮
    this.ShowSizePos = function () {
        if (parseInt($('#divChat').css('height')) == 244) {
            $('#divCSize,.divScroll,#divCSizeBig').css('display', 'block');
        } else if (parseInt($('#divChat').css('height')) == 450) {
            $('#divCSize').css('display', 'block');
        }
    }
}
