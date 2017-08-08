function Challenge2() {
    this.showmsgInt = -1;
    this.openedcardnum = 0;
    this.LeagueIndex = 0;
    this.MyVsInfo = "";
    this.load = function (leagueIndex) {
        Loading();
        challengeObj2.openedcardnum = 0;
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { load1: leagueIndex }, function (res) {
            if (res == "-999") {
                ShowMsg(MatchList_Challenge_FB1, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                return;
            }
            else {
                challengeObj2.LeagueIndex = leagueIndex;
                var type = res.split('#')[0]; //该显示的页面
                if (type == "0") {
                    challengeObj2.createTopHtml(leagueIndex, res.split('#')[2], res);
                }
                else if (type == "2" || type == "1") {
                    challengeObj2.createCardHtml(leagueIndex, res);
                }
                else if (type == "3") {
                    //                alert(res.split('#')[1]);
                    showMatch(res.split('#')[1], 26);
                }
                else if (res == "-1" || res == "-2") {
                    ShowMsg("加载失败", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
                }
            }

        });
        RemoveLoading();
    }
    this.createTopHtml = function (LeagueIndex, powerInfo, result) {
        var StrHtml = "<div><div style=\"width:912px;height:460px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.png);position:absolute;left:7px;\">";
        StrHtml += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/x" + (LeagueIndex - 10) + ".jpg);\"></div>";
        StrHtml += "<div style=\"position:absolute;left:22px;top:7px;width:58px;height:59px;background:url(" + ImgPath + "Images/ChallengeMatch/logo_" + LeagueIndex + ".png) no-repeat;\"><div style='font-size:30px;color:#f5dc27;font-style:italic;position:absolute;left:6px;top:5px;line-height:30px;font-weight:bold;width:70px;'>" + challengeObj.nameinfo1[LeagueIndex - 11] + "</div>";

        StrHtml += "<div id='chpower' style='position:absolute;left:736px;top:20px;width:250px;'>" + challengeObj2.dobindpowerch(powerInfo) + "</div>";

        StrHtml += "<div style='position:absolute;left:750px;top:229px;background:url(" + ImgPath + "Images/ChallengeMatch/arr.png);width:43px;height:46px;'></div>";

        var ListTeamStr = result.split('#')[4];
        var PlayerStr = result.split('#')[5].split('*');
        challengeObj2.MyVsInfo = result.split('#')[6];
        var str = "";
        for (var i = 0; i < 5; i++) {
            if (parseInt(result.split('#')[3]) > i) {
                str += "<div style='width:89px;height:24px;color:yellow;text-align:center;font-size:15px;font-style:italic;font-weight:bold;position:absolute;top:-22px;left:" + (parseInt(i * 89)) + "px;'>" + ListTeamStr.split('@')[i].split('*')[0] + "</div>";
                str += "<div id='showh" + i + "' onmouseout=\"challengeObj2.hideInfo()\" onmouseover=\"challengeObj2.ChallengeShowClubInfo('" + i + "','" + ListTeamStr.split('@')[i] + "')\" style='width:89px;height:312px;float:left;background:url(" + ImgPath + "Images/ChallengeMatch/c" + (LeagueIndex - 14) + ".jpg) -" + (parseInt(i * 89)) + "px 0px no-repeat;'></div>"; //胜利过的
                str += "<div style='background:url(" + ImgPath + "Images/ChallengeMatch/win.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/win.png);width:93px;height:92px;position:absolute;top:134px;left:" + (parseInt(i * 89)) + "px;'></div>";
            }
            else if (parseInt(result.split('#')[3]) < i) {
                str += "<div style='width:89px;height:24px;color:white;text-align:center;font-size:15px;font-style:italic;font-weight:bold;position:absolute;top:-22px;left:" + (parseInt(i * 89)) + "px;'>" + ListTeamStr.split('@')[i].split('*')[0] + "</div>";
                str += "<div id='showh" + i + "' onmouseout=\"challengeObj2.hideInfo()\" onmouseover=\"challengeObj2.ChallengeShowClubInfo('" + i + "','" + ListTeamStr.split('@')[i] + "')\" style='width:89px;height:312px;float:left;background:url(" + ImgPath + "Images/ChallengeMatch/c" + (LeagueIndex - 14) + ".jpg) -" + (parseInt(i * 89)) + "px 0px no-repeat;'></div>";
            }
            else {
                var ClubInfoStr = ListTeamStr.split('@')[i];
                var clubIndex = ClubInfoStr.split('*')[9];
                var clubName = ClubInfoStr.split('*')[0];
                var clubimg = ClubInfoStr.split('*')[8];
                var power = ClubInfoStr.split('*')[4];
                var tactics = ClubInfoStr.split('*')[10];
                str += "<div style='width:89px;height:24px;color:white;text-align:center;font-size:15px;font-style:italic;font-weight:bold;position:absolute;top:-22px;left:" + (parseInt(i * 89)) + "px;'>" + ListTeamStr.split('@')[i].split('*')[0] + "</div>";
                str += "<div id='showh" + i + "' onclick=\"beforeinsertMatch('" + challengeObj2.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj2','ChallengeDiv',-1,0,1,0)\" onmouseout=\"challengeObj2.hideInfo()\" onmouseover=\"challengeObj2.ChallengeShowClubInfo('" + i + "','" + ListTeamStr.split('@')[i] + "')\" style='width:89px;height:312px;float:left;cursor:pointer'></div>";
                str += "<div id=\"challengeBut" + i + "\" class='div_btn2_rank' onclick=\"beforeinsertMatch('" + challengeObj2.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj2','ChallengeDiv',-1,0,1,0)\" style='position:absolute;left:" + (parseInt(i * 89) + 1) + "px;top:320px;'>" + MatchList_Public_Challenge + "</div>";
            }

        }

        StrHtml += "<div style='width:445px;height:312px;position:absolute;left:306px;top:85px;'>" + str + "</div>";
        //StrHtml += "<div style='width:444px;height:312px;position:absolute;left:306px;top:85px;border:solid 1px green'></div>";

        StrHtml += "<div style='position:absolute;left:792px;top:168px;font-weight:bolder;color:white;font-size:18px;width:83px;height:46px;'>" + MatchList_Challenge_FB3 + "</div>";
        StrHtml += CreatePlayer(PlayerStr[0], PlayerStr[1], PlayerStr[2], PlayerStr[3], PlayerStr[4], PlayerStr[5], 792, 200, "s");
        if (result.split('#')[1] == "0") {
            StrHtml += "<div id='' class='div_btn2_rank' onclick=\"challengeObj2.showDealOP('"+LeagueIndex+"')\" style='position:absolute;left:789px;top:300px;'>签约</div>";
        }
        else if (result.split('#')[1] == "1") {
            StrHtml += "<div style='position:absolute;left:815px;top:300px;color:white;width:60px;'>已签约</div>";
        }
        else {
            StrHtml += "<div class='div_btn2grey_rank' style='position:absolute;left:789px;top:300px;'>签约</div>";
        }

        StrHtml += "<div id=\"infoDiv\" style=\"position:absolute;display:none;width:113px;height:118px;text-align:left\"></div>";
        StrHtml += "<div id='ClallengeGoningOn' class=\"infoSmall\" style=\"position:absolute;left:340px;top:190px; z-index:3;display:none\">" + MatchList_Challenge_challengemsg20 + ".&nbsp;&nbsp;</div>";
        StrHtml += "<div style='font-size:22px;color:#f5dc27;font-style:italic;position:absolute;left:210px;top:23px;width:250px;'>" + challengeObj.nameinfo2[LeagueIndex - 11] + "</div></div>";

        AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtml, null, null, null, 'ChallengeDiv', null, null, null);
    }

    this.ChallengeShowClubInfo = function (i, ListTeamStr) {
        var TeamStr = ListTeamStr.split('*');
        var StrHtml = "";
        var left = (parseInt(i) * 89 + 400);
        $("#infoDiv").css("display", "none").css("left", left + "px").css("top", "120px").bind("mouseover", function () { $("#infoDiv").css("display", "none"); });
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bg.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bg.png);width:130px;height:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_repeat.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_repeat.png) repeat-y;width:130px;height:270px;top:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_fenge.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_fenge.png) no-repeat;width:130px;height:2px;top:34px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bg.png) left -8px;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bg.png) left -8px no-repeat;width:130px;height:7px;top:277px;\"></div>";
        StrHtml += "<div style=\"font-size:14px; top: 7px; left:7px; font-weight:bold;color:Yellow\">" + TeamStr[0] + "</div>";
        StrHtml += "<div style=\"font-size:12px; top: 35px; left:7px; color:Yellow\"><font style=' color:Green'>" + MatchList_Challenge_CLNX + "：</font>" + TeamStr[1] + "<br />";

        StrHtml += "<font style=' color:green'>" + MatchList_Challenge_challengemsg22 + "：</font>" + TeamStr[2] + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg23 + "：</font>" + TeamStr[3] + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg24 + "：</font>" + TeamStr[4] + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg25 + "：</font>" + TeamStr[5].split('|')[0] + "<br />　　　" + TeamStr[5].split('|')[1] + "</div>";
        StrHtml += "<div style=\"font-size:12px; top: 145px; left:7px; color:Yellow;width:110px;\"><font style='color:green'>" + MatchList_Challenge_challengemsg27 + "：</font><br />" + TeamStr[6] + "</div>";

        StrHtml += "<div style=\"font-size:12px; top: 235px; left:7px; color:Yellow;width:110px;\"><font style='color:green'>" + TeamStr[7] + "</font></div>";
        clearTimeout(challengeObj2.showmsgInt);
        challengeObj2.showmsgInt = setTimeout(function () { $("#infoDiv").html(StrHtml).css("display", "block"); }, 500);

    }
    this.hideInfo = function () {
        try {
            clearTimeout(challengeObj2.showmsgInt);
            $("#infoDiv").css("display", "none");
        }
        catch (e) { }
    };
    this.Vip = "";
    this.createCardHtml = function (LeagueIndex, result) {
        challengeObj2.Vip = result.split('#')[4];
        var StrHtml = "<div><div style=\"width:912px;height:460px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.png);position:absolute;left:7px;\">";

        StrHtml += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/x" + (LeagueIndex - 10) + ".jpg);\"></div>";
        StrHtml += "<div style=\"width:923px; height:463px;position:absolute; background:black; top:0px; left:-9px; z-index:4;  filter:alpha(opacity=90);opacity:0.9;\"><div id=\"nameinfo\" style=\"width:912px; position:absolute; top:50px; height:22px; text-align:center; font-size:20px; bottom: 356px;color:white;font-weight:bold\">恭喜战胜" + result.split('#')[1] + "</div><div id='canOpeninfo' style=\"width:912px; position:absolute; top:405px;left:0px; height:22px; text-align:center; font-size:14px;color:white\">" + MatchList_Challenge_FB_info40 + "</div><div id='divCardPanl' style='width:923px;height:331px;position:absolute;left:0px;top:73px;'></div><div id='divlj' onclick='challengeObj2.AcceptCardAward()' class='div_btn2' style='position:absolute;left:440px;top:370px;display:none'>领取</div></div></div>";
        var Cardstr = "";
        var CardList = result.split('#')[2];
        var card = new Array();
        card = CardList.split('*');
        for (var i = 0; i < 4; i++) {
            var card1 = new Array();
            card1 = card[i].split('&');
            Cardstr += "<div class=\"card\" id=\"cardCard" + i + "\" onclick=\"challengeObj2.chose(" + i + ")\" onmouseover=\"challengeObj2.cardover('" + i + "')\" onmouseout=\"challengeObj2.cardout($(this))\" style=\"position:absolute;left:" + (i * 146 + 184) + "px;top:88px;width:127px;height:181px;cursor:pointer;\"><img class=\"cardbg\" src=\"" + GetImgPath() + "/ChallengeMatch/p1.png\"/><div  class=\"cardbg\" id=\"cardres" + (i) + "\" style=\"background:url('" + GetImgPath() + "/ChallengeMatch/p2.png');display:none;\"></div></div>";
        }
        AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtml, null, null, null, 'ChallengeDiv', null, null, null);
        if (result.split('#')[3] != "") {
            ShowMsg(result.split('#')[3], MatchList_Challenge_sure, function () {
                $("#cad").remove(); challengeObj2.AcceptAward();
            }, null, function () {
                $("#cad").remove(); challengeObj2.AcceptAward();
            }, null, 290, 175, $("#ChallengeDiv"), "cad", null);
        }
        $("#divCardPanl").html(Cardstr);
        for (var z = 0; z < 4; z++) {
            var card1 = new Array();
            card1 = card[z].split('&');
            if (card1[0] == "0") {
                challengeObj2.openedcardnum += 1;
                challengeObj2.fan(z, card[z], challengeObj2.Vip);
            }
        }
        if (challengeObj2.openedcardnum > 0 && challengeObj2.openedcardnum < 4) {
            $("#canOpeninfo").html("您可花费" + challengeObj2.openedcardnum * 25 + "金币再翻一张");
        }
        if (challengeObj2.openedcardnum >= 4) {
            $("#canOpeninfo").html("");
        }


    }

    // 翻牌相关方法
    this.cardover = function (cardindex) { $("#cardCard" + cardindex).stop().animate({ top: "78px" }, 150); }
    this.cardout = function (cardid) { cardid.stop().animate({ top: "88px" }, 150); }

    this.fan = function (z, cardList, vip) {
        $("#cardres" + z).prev().animate({ width: "0px", left: "62px" }, 500, function () {
            var Addstr = vip == "1" ? "X2" : "";
            $("#cardres" + z).css("display", "block");
            $("#cardres" + z).html("<div style=\"position:absolute;left:0px; top:30px;width:127px;color:black;font-size:15px;font-weight:bolder;width:127px;text-align:center\">" + cardList.split('&')[1] + Addstr + "</div><div style=\"position:absolute;left:24px;top:78px;width:82px;height:82px;\"><img src=\"" + ImgPath + "Images/Item/" + cardList.split('&')[2] + "\" style=\"position:absolute;left:6px;top:18px;\"/></div>");
        });
        $("#divlj").css("display", "block");
    }

    // 选中某张牌，点击事件
    this.chose = function (cardindex) {
        $(".card")[cardindex].onclick = null;
        $(".card")[cardindex].onmouseover = null;
        $(".card")[cardindex].onmouseout = null;
        if (challengeObj2.openedcardnum > 0) {
            ShowMsg(MatchList_Challenge_FB_info39.replace("{0}", challengeObj2.openedcardnum * 25), MatchList_Public_ok, function () {
                $("#blq").remove(); challengeObj2.FlopCard(cardindex);
            }, null, function () {
                $(".card")[cardindex].onclick = function () { challengeObj2.chose(cardindex); }
                $("#blq").remove();
            }, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
        }
        else {
            challengeObj2.FlopCard(cardindex);
        }
    }
    this.canChallenge = 1;
    //挑战Challenge
    this.insertMatch = function (homeClubIndex) {
        $("#imgArrow").remove();
        $("#ch").remove();
        if (challengeObj.canChallenge == 0) return;
        else {
            challengeObj.canChallenge = 0;
        }
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { Challenge1: challengeObj2.LeagueIndex }, function (type) {
            if (type == "-4") {
                ShowMsg("正在比赛中,不能挑战", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (type == "-5") {
                ShowMsg("体力不足", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (parseInt(type) > 0) {
                $("#ClallengeGoningOn").css("display", "block");
                setTimeout(function () { $("#ClallengeGoningOn").html("" + MatchList_Public_info7 + "..&nbsp;") }, 1000);
                setTimeout(function () { $("#ClallengeGoningOn").html("" + MatchList_Public_info7 + "...") }, 2000);
                // 拉出比赛引擎
                setTimeout(function () {
                    $("#ClallengeGoningOn").css("display", "none");
                    $("#ChallengeDiv").remove();
                    showMatch(type, 26);
                    challengeObj.canChallenge = 1;
                }, 3000);
            }
            else {
                ShowMsg("加载失败", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            challengeObj.canChallenge = 1;
        });
    }

    //领取弹出框奖
    this.AcceptAward = function () {
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { AcceptAward1: challengeObj2.LeagueIndex }, function (type) {
            if (parseInt(type)<0) {
                ShowMsg("领取失败", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else {
                publicAddGSEL(2, parseInt(type));
            }
        });
    }
    //翻牌领奖
    this.AcceptCardAward = function () {
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { AcceptCardAward1: challengeObj2.LeagueIndex }, function (type) {
            if (parseInt(type) < 0) {
                ShowMsg("领取失败", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else {
                publicAddGSEL(2, parseInt(type));
                ShowMsg(MatchList_Challenge_challengemsg38, MatchList_Challenge_sure, function () {
                    $("#cad").remove(); challengeObj2.load(challengeObj2.LeagueIndex);
                }, null, function () {
                    $("#cad").remove(); challengeObj2.load(challengeObj2.LeagueIndex);
                }, null, 290, 175, $("#ChallengeDiv"), "cad", null);
            }
        });
    }
    //翻牌
    this.FlopCard = function (Num) {
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { FlopCard1: challengeObj2.LeagueIndex + "*" + Num }, function (type) {
            if (type == "-1" || type == "-2" || type == "-3" || type == "-5") {
                ShowMsg("翻牌失败", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (type == "-4") {
                OpenPayMsg();
            }
            else {
                publicAddGSEL(1, -parseInt(challengeObj2.openedcardnum * 25));
                challengeObj2.openedcardnum += 1;
                if (challengeObj2.openedcardnum > 0 && challengeObj2.openedcardnum < 4) {
                    $("#canOpeninfo").html("您可花费" + challengeObj2.openedcardnum * 25 + "金币再翻一张");
                }
                if (challengeObj2.openedcardnum >= 4) {
                    $("#canOpeninfo").html("");
                }
                challengeObj2.fan(Num, type, challengeObj2.Vip);
            }
        });
    }

    this.dobindpowerch = function (powerInfo) {
        var StrHtml = "";
        var peopleCount = parseInt(powerInfo.split('|')[0]);
        var haveCount = parseInt(powerInfo.split('|')[1]);
        for (var i = 0; i < peopleCount; i++) {

            if (i < peopleCount - haveCount) {
                StrHtml += "<div style=\"position:absolute;left:" + ((peopleCount - i - 1) * 17) + "px;width:16px;height:30px;background:url(" + ImgPath + "Images/ChallengeMatch/life.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/life.png);\">&nbsp;</div>";
            }
            else {
                StrHtml += "<div style=\"position:absolute;left:" + ((peopleCount - i - 1) * 17) + "px;width:16px;height:30px;background:url(" + ImgPath + "Images/ChallengeMatch/life.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/life.png);background-position: -16px 0px\">&nbsp;</div>";
            }
        }
        StrHtml += "<div class=\"div_btn2Y\"  style=\"position:absolute;left:90px;\" id=\"addBBut\" style=\"font-size:12px;\" onclick=\"challengeObj2.showChallengeAdd(" + peopleCount + "," + haveCount + "," + challengeObj2.LeagueIndex + ")\">" + MatchList_Challenge_FB_info9 + "</div>";  //小人
        return StrHtml;
    }
    //补满
    this.showChallengeAdd = function (peopleCount, haveCount, LeagueIndex) {
        if (peopleCount <= haveCount) {
            ShowMsg(MatchList_Challenge_FB_info10, MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
            challengeObj2.dobindpowerch("5|5");
            return;
        } else {
            $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { ShowPowerCoin1: LeagueIndex + "*" + 1 }, function (res) {

                if (res == "-1" || res == "-4") {
                    ShowMsg("补满失败", MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                }
                else if (res == "-2") {
                    ShowMsg(MatchList_Challenge_FB_info11, MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                }
                else if (res == "-3") {
                    OpenPayMsg();
                }
                else {
                    ShowMsg(MatchList_Challenge_FB_info14.replace("{0}", res), MatchList_Public_ok, function () {
                        $("#bf").remove();
                        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { ShowPowerCoin1: LeagueIndex + "*" + 0 }, function (res1) {
                            if (res1 == "-1" || res1 == "-4") {
                                ShowMsg("补满失败", MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                            }
                            else if (res1 == "-2") {
                                ShowMsg(MatchList_Challenge_FB_info11, MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                            }
                            else if (res1 == "-3") {
                                OpenPayMsg();
                            }
                            else {
                                publicAddGSEL(1, -parseInt(res1));
                                $("#chpower").html(challengeObj2.dobindpowerch('5|5'));
                            }
                        });
                    }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                }
            });
        }
    }

    //签约弹出框
    this.showDealOP = function (LeagueIndex) {
        if ($("#deal")[0])
            return;
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { showDealOP1: LeagueIndex }, function (res) {
            if (res == "-10") {
                //替补席已经满了
                ShowMsg(MiddleMan_info29, MatchList_Public_ok, function () { $("#deal").remove(); }, null, null, null, 354, 160, $('#totalMainDiv'), 'deal', false);
                return;
            }
            var PIs = res.split('&'); // 球员信息&金银币&签约道具
            var allOk = 1;
            var htm = "<div style='background:url(" + ImgPath + "Images/MiddleMan/bg.png);width:431px;height:296px;position:absolute;left:2px;'>";
            var pinfo = PIs[0].split('|'); // 球员相关信息
            htm += "<div style='position:absolute;width:68px;height:68px;left:24px;top:19px;'><img src='" + ImgPath + "Images/PlayersHead/" + pinfo[5] + "'/></div>"; // 头像
            htm += "<div class='Line' style='top:96px'></div>"; // 线条
            htm += "<div class='Line' style='top:136px'></div>"; // 线条
            htm += "<div class='Line' style='top:243px'></div>"; // 线条
            htm += "<div style='position:absolute;left:103px;top:25px;color:#4f4700;line-height:23px;'><div style='float:left;font-size:17px;color:" + GetPlayerGradeColor(pinfo[2]) + "'>" + pinfo[1] + "</div><div  style='float:left;margin:4px 0px 0px 20px;'>" + getPlayerFlagByQuality(pinfo[2]) + "</div><br /><div style='float:left'>" + MiddleMan_info34 + "：</div><div style='color:black;float:left;'>" + pinfo[3] + "</div><br /><div style='float:left'>" + MiddleMan_info35 + "：</div><div style='color:black;margin-right:15px;float:left;'>" + GetCNPositionName(pinfo[4]) + "</div><div style='float:left'>" + MiddleMan_info36 + "：</div><div style='margin-right:15px;color:black;float:left;'>" + pinfo[7] + "cm</div><div style='float:left'>" + MiddleMan_info37 + "：</div><div style='color:black;float:left;'>" + pinfo[8] + "kg</div></div>"; // 头像右侧

            var coinInfo = PIs[1].split('*');
            htm += "<div style='position:absolute;left:28px;top:108px;color:#4f4700;line-height:23px;'><font style='color:#ff5301'>" + MiddleMan_info38 + "：</font></div><div style='position:absolute;left:103px;top:108px;color:#4f4700;line-height:23px;'>" + MatchList_Challenge_challengemsg55 + "：";
            if (coinInfo[1].split("|")[1] == "1") {
                // 银币够了
                htm += "<font style='color:#ff5301;margin-right:30px;'>" + coinInfo[1].split("|")[0];
            }
            else {
                allOk = 0;
                htm += "<font style='color:#aaa;margin-right:30px;'>" + coinInfo[1].split("|")[0];
            }

            htm += "</font>";
            if (parseInt(coinInfo[0].split("|")[0]) > 0) {
                // 有金币这个条件
                htm += "金币：";
                if (coinInfo[0].split("|")[1] == "1") {
                    // 金币够了
                    htm += "<font style='color:#ff5301;margin-right:15px;'>" + coinInfo[0].split("|")[0];
                }
                else {
                    allOk = 0;
                    htm += "<font style='color:#aaa;margin-right:15px;'>" + coinInfo[0].split("|")[0];
                }
                htm += "</font>";
            }
            htm += "</div>"; // 金币银币
            var iteminfo = PIs[2].split('*'); //1102|0|1102.png|2级合同|是否可以出手|描述|个数
            htm += "<div style='position:absolute;left:103px;top:153px;color:#4f4700;line-height:23px;'>";
            for (var i = 0; i < iteminfo.length; i++) {
                if (iteminfo[i] != "" && iteminfo[i] != "-1") {
                    var oneItem = iteminfo[i].split("|");
                    htm += "<img id='MiddleManImg" + i.toString() + "' src='" + ImgPath + "Images/Item/" + oneItem[2] + "'  onmouseover=\"ShowPorpInfo($('#MiddleManImg" + i + "'),'" + oneItem[0].toString() + "','" + oneItem[3].toString() + "','" + oneItem[6].toString() + "','" + oneItem[4].toString() + "','" + oneItem[5].toString() + "',40,20)\"  style='position:absolute;left:" + (iteminfo.length >= 5 ? parseInt(74 * (i - 1) + 9) : parseInt(74 * i)) + "px;top:0;";
                    if (oneItem[1] == "0") {
                        allOk = 0;
                        htm += " filter: Alpha(Opacity=40);-moz-opacity:0.4;opacity: 0.4;"; // 如果玩家没有此道具，透明度降低
                    }
                    htm += "'/>";
                }
            }
            htm += "</div>"; // 道具
            if (allOk == 0) {
                htm += "<div class=\"div_btn2grey\"  style='position:absolute;left:118px;top:254px;'\">" + MiddleMan_info24 + "</div>";
            }
            else {
                htm += "<div class=\"div_btn2\"  style='position:absolute;left:118px;top:254px;' onclick=\"challengeObj2.doDeal('" + LeagueIndex + "')\">" + MiddleMan_info24 + "</div>";
            }
            AddBox(308, 120, 440, 329, MiddleMan_info27, htm, $('#totalMainDiv'), null, null, 'pdeal', null, true, null);
        });

    }

    this.canDeal = 1;
    this.doDeal = function (LeagueIndex) {
        if (challengeObj2.canDeal == 0 || $("#deal")[0])
            return;
        challengeObj2.canDeal = 0;
        $.post("MatchList/ChallengeMatch/ChallengeList2.aspx", { doDeal1: LeagueIndex }, function (res) {
            if (res == "1") {
                publicAddGSEL(2, -8000);
                $("#challengeMakePL").remove(); //巡回赛签约框
                $("#pdeal").animate({ top: 520, left: 514, width: 0, height: 0 }, 600, function () { $("#pdeal").remove(); challengeObj2.canDeal = 1; });
                challengeObj2.load(LeagueIndex);
            }
            else if (res == "-2") {

                $("#pdeal").remove(); challengeObj2.canDeal = 1;
                ShowMsg(MiddleMan_info29, MatchList_Public_ok, function () { $("#deal").remove(); }, null, null, null, 354, 160, $('#totalMainDiv'), 'deal', false);
            }
            else {
                $("#pdeal").remove(); challengeObj2.canDeal = 1;
                ShowMsg(MiddleMan_info30, MatchList_Public_ok, function () { $("#deal").remove(); }, null, null, null, 354, 160, $('#totalMainDiv'), 'deal', false);
            }
        });
    }
}
