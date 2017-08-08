function Challenge() {
    this.showmsgInt = -1;
    this.LeagueIndex = 0;
    this.nextName = "";
    this.MyVsInfo = "";
    this.finish1001 = 0;
    this.finish1004 = 0;
    this.showGotoNextBut = 0;
    this.intvelLeagueIndex = -1; //位置类型 倒计时
    this.intvelCateGoryIndex = -1; //球员类型 倒计时
    this.typeVal = 0; //类型的选项
    this.CateVal = 1; //球员的具体类型
    this.LeagueName = new Array("", MatchList_Challenge_FJ, MatchList_Challenge_DJ, MatchList_Challenge_YJ, MatchList_Challenge_YC, MatchList_Challenge_XJ);
    this.ma = "";
    this.Buttuns = new Array("1|0|0", "1|1|0", "1|1|0", "1|1|1", "1|1|1", "1|1|1", "1|1|1", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0", "1|0|0"); // 第一位是领取按钮，第二位是重翻按钮，第三位是洗牌按钮
    this.discribe = new Array(MatchList_Challenge_FSMS.replace(/\{0}/g, "</br>"),
            MatchList_Challenge_DJMS.replace(/\{0}/g, "</br>"),
            MatchList_Challenge_YJMS.replace(/\{0}/g, "</br>"),
            MatchList_Challenge_YCMS.replace(/\{0}/g, "</br>"),
            MatchList_Challenge_XJMS.replace(/\{0}/g, "</br>"),
            MatchList_Challenge_DFMS.replace(/\{0}/g, "</br>"));
    this.load = function (type) {
        challengeObj.openedcardnum = 0;
        var StrHtml = "";
        var PointsHtml = "";
        var CardHtml = "";
        this.LeagueIndex = type;
        Loading();
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "loadClubList": 1, "LeagueIndex": type }, function (res) {
            RemoveLoading();
            if (res == "-999") {
                ShowMsg(MatchList_Challenge_FB1, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                return;
            }

            var isHasReward_ballack = res.split('╋')[3].split('|')[0]; //巴拉克升级活动是否有提示（-1没有）
            var ballackActivityInfo = res.split('╋')[3].split('|')[1]; //巴拉克升级活动提示道具或“字”

            //---------------------------巴蒂升级活动，需要打副本集奖杯，奖杯号需要提示--------------------------
            var BattyCupIndex = -1; //巴蒂所需的奖杯编号
            try {
                BattyCupIndex = res.split('╋')[4];
            }
            catch (e) {
                BattyCupIndex = -1;
            }
            //---------------------------以上为巴蒂升级活动添加代码-----------------------------------------------
            //---------------------------球会力量活动，需要打副本集球衣和文字-----------------------------------
            var ClubPower_PlayerName = ''; //球会力量球员的名字(球会力量活动)
            var ClubPower_PlayerNameWord = ''; //球会力量球员名字的某一个字
            var ClubPower_PlayerNameIndex = '-1';
            try {
                // ClubPower_PlayerNameIndex = res.split('╋')[3].split('|')[0];
                // ClubPower_PlayerNameWord = res.split('╋')[3].split('|')[1];
            }
            catch (e) {
                ClubPower_PlayerNameIndex = -1;
            }
            if (ClubPower_PlayerNameIndex == 1)
                ClubPower_PlayerName = '贝克汉姆';
            else if (ClubPower_PlayerNameIndex == 2)
                ClubPower_PlayerName = '斯科尔斯';
            else if (ClubPower_PlayerNameIndex == 3)
                ClubPower_PlayerName = '吉格斯';
            else if (ClubPower_PlayerNameIndex == 4)
                ClubPower_PlayerName = 'P内维尔';
            else if (ClubPower_PlayerNameIndex == 5)
                ClubPower_PlayerName = '加里内维尔';
            else if (ClubPower_PlayerNameIndex == 6)
                ClubPower_PlayerName = '巴特';
            //---------------------------以上球会力量活动-------------------------------------------------------
            //---------------------------小贝升级活动，需要打副本集奖杯，奖杯号需要提示--------------------------
            var BeckhamCupIndex = -1; //小贝退役 升级所需的奖杯编号
            try {
                BeckhamCupIndex = res.split('╋')[2];
            }
            catch (e) {
                BeckhamCupIndex = -1;
            }
            //---------------------------以上为小贝升级活动添加代码-----------------------------------------------
            //---------------------------小贝（印象·万人迷）球会活动  打副本集奖杯，奖杯号需要提示---------------------
            var awardindex = -1;
            try {
                awardindex = res.split('╋')[1];
                res = res.split('╋')[0];
            }
            catch (e) {
                awardindex = -1;
            }
            //---------------------------以上为小贝（万人迷）球会活动添加代码--------------------------------------

            $.post('Default.aspx', { type: 3 }, function (result) {
                var ress = res.split('*');
                var playerInfo = ress[9]; //副本可签约的球员信息
                //状态（0不能签，1可签，2签过了）|playerid|playername|playerquality|playeravgpower|role|photo|clubname
                StrHtml += createTopHtml(type, result, ress[8]);
                challengeObj.MyVsInfo = ress[5];

                //比赛信息*处在第几幕*完成的未完成的球队index*正在领奖的那支球队*下一个要打的球队*我的信息*星级奖励信息
                if (ress[1] == "-7") {
                    ShowMsg(MatchList_Challenge_TZSX, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                    return;
                }
                else if (ress[1] == "-6") {
                    ShowMsg(MatchList_Challenge_TGSX, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                    return;
                }
                var next = ress[4];
                var rewardIndex = ress[3];
                if (ress[0] == "-1") {
                    // 没有比赛
                    $("#FBProject1").css("display", "none").html("");
                    var htmls = createClubListHtml(ress[2], next, rewardIndex, type, playerInfo).split('*');
                    StrHtml += htmls[0];
                    PointsHtml += htmls[1];
                    if (next == rewardIndex) {
                        //说明有没领奖的
                        //初始化牌
                        CardHtml += createCardHtml();
                    }

                    StrHtml += "</div>";
                    StrHtml += "<div class=\"points\">" + PointsHtml + "<div id=\"infoDiv\" style=\"position:absolute;display:none;width:113px;height:118px;text-align:left\"></div></div>";
                    StrHtml += CardHtml;
                    StrHtml += "</div></div>";
                    if (challengeObj.showGotoNextBut == 1 && type == "5" && ress[7] == "1" && next != rewardIndex) {
                        var StrHtmlSignM = "<div><div style=\"width:912px;height:460px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.png);position:absolute;left:7px;\">";
                        StrHtmlSignM += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/Manbg6.jpg);\"></div>";
                        var pin = challengeObj.ma.split('|');
                        StrHtmlSignM += "<div id='phead'style='position:absolute;'>" + CreatePlayer(pin[0], pin[1], pin[2], pin[3], pin[4], null, 792, 72, "s") + "</div>"; //综合能力,位置,头像名称,球员名字,球员等级,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID;
                        //StrHtmlSignM += CreatePlayer(99, 4, "", "马拉多纳", 7, 12, 348, 72, "s"); //综合能力,位置,头像名称,球员名字,球员等级,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID;

                        StrHtmlSignM += "<div id='big' style='position:absolute'><div style='font-size:68px;color:black;font-style:italic;position:absolute;left:476px;top:61px;font-weight:bold;width:280px'>我是传奇</div><div style='font-size:18px;color:black;font-style:italic;position:absolute;left:543px;top:149px;width:380px;font-weight:bold'>I Am Legend</div><div style='font-size:68px;color:white;font-style:italic;position:absolute;left:475px;top:60px;font-weight:bold;width:280px'>" + MatchList_Challenge_WSCQ + "</div><div style='font-size:18px;color:white;font-style:italic;position:absolute;left:542px;top:148px;width:380px;font-weight:bold'>" + MatchList_Challenge_IAMLEGEND + "</div></div><div id='w1' style='font-size:18px;color:#f5dc27;font-style:italic;position:absolute;left:542px;top:205px;width:480px;font-weight:bold'>" + MatchList_Challenge_JSQY + "</div><div id='w3' style='font-size:18px;color:black;font-style:italic;position:absolute;left:543px;top:241px;width:480px;font-weight:bold'>" + MatchList_Challenge_ZGPZ.replace(/\{0}/g, "</br>") + "</div><div id='w2' style='font-size:18px;color:#f5dc27;font-style:italic;position:absolute;left:542px;top:240px;width:480px;font-weight:bold'>" + MatchList_Challenge_ZGPZ.replace(/\{0}/g, "</br>") + "</div>";
                        StrHtmlSignM += "<div id='comeinbut' style='position:absolute;left:507px;top:365px;width:313px;height:59px;font-weight:bold;overflow:hidden;text-align:center;font-size:36px;font-style:italic;color:white;line-height:48px;background:url(" + ImgPath + "Images/ChallengeMatch/Mbutton.png);cursor:pointer' onmouseover='challengeObj.comein(1)' onmouseout='challengeObj.comein(0)' onclick='challengeObj.beforecreateSuper();'>" + MatchList_Challenge_CJCQ + "</div>";
                        StrHtmlSignM += "<div style='color:white;position:absolute;font-style:italic;font-weight:bold'><div id='w11' style='display:none;font-size:24px;left:583px;top:58px;position:absolute;width:200px'>" + MatchList_Challenge_CJWDCQ + "</div>";
                        StrHtmlSignM += "<div id='w12' style='display:none;font-size:18px;left:533px;top:163px;position:absolute;width:232px;'><div style='position:absolute;width:90px;left:0px;'>" + MatchList_Challenge_playerName + "：</div><div style='position:absolute;width:136px;left:97px;background:url(" + ImgPath + "Images/ChallengeMatch/input.png)'><input style='position:absolute;;left:0px;top:0px;width:130px;' maxlength='6' id='PlayerDataNameVal' type='text' style='position:absolute;width:136px;left:0px;'></div></div>";


                        StrHtmlSignM += "<div id='ImageURLHZ' style='display:none;font-size:18px;left:533px;top:276px;position:absolute;width:232px;'><div style='position:absolute;width:90px;left:0px;'>球员头像：</div><div id='SaveImageURL' class='div_btn4' style='left:97px;position:absolute;' onclick='ShowLegendHead()'>选择头像</div></div>";

                        StrHtmlSignM += "<div id='w13' style='display:none;font-size:18px;left:533px;top:200px;position:absolute;width:90px'>" + MatchList_Challenge_Position + "：</div>;";
                        StrHtmlSignM += "<div id='w13a' style='display:none;font-size:18px;color:#1d6100;font-family:normal;font-weight:bold;left:630px;top:200px;position:absolute;width:136px;height:25px;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png) 0px -25px  no-repeat;z-index:2'><div id='type0' style='position:absolute;width:100px;height:25px;left:0px;'> &nbsp;" + challengeObj.PlayerDataName[challengeObj.typeVal].split('|')[0] + "</div><div id='w13b' style='position:absolute;width:22px;height:25px;left:112px;background:url(" + ImgPath + "Images/ChallengeMatch/listhover.png)'></div></div>";



                        StrHtmlSignM += "<div id='w14' style='display:none;font-size:18px;left:533px;top:237px;position:absolute;width:90px'>" + MatchList_Challenge_playerType + "：</div>;";
                        StrHtmlSignM += "<div id='w14a' style='display:none;font-size:18px;color:#1d6100;font-family:normal;font-weight:bold;left:630px;top:237px;position:absolute;width:136px;height:25px;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png) 0px -25px  no-repeat;'><div id='Category0' style='position:absolute;width:100px;height:25px;left:0px;'>  &nbsp;" + challengeObj.PlayerDataName[challengeObj.typeVal].split('|')[challengeObj.CateVal] + "</div><div id='w14b' style='position:absolute;width:22px;height:25px;left:112px;background:url(" + ImgPath + "Images/ChallengeMatch/listhover.png)'></div></div>";
                        //传奇球员头像





                        //StrHtmlSignM += "<div id='w15' style='display:none;font-size:12px;left:260px;top:435px;position:absolute;width:649px;font-style:normal;font-weight:normal'>" + MatchList_Challenge_MadeImage + "</div></div>";
                        StrHtmlSignM += "<div id='w15' style='display:none;font-size:12px;left:260px;top:435px;position:absolute;width:649px;font-style:normal;font-weight:normal'></div></div>";
                        StrHtmlSignM += "</div>";
                        AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtmlSignM, null, null, function () { selLegendHeadName = ""; }, 'ChallengeDiv', null, null, null);
                        //点击下拉扭  让下拉显示
                        $("#w13b").bind("click", function () { challengeObj.ShowChengeButton(1); }).bind("mouseover", function () {
                            $("#w13b").css("cursor", "pointer");
                            challengeObj.nextOver(1); $(this).css({ backgroundPosition: '0px -25px' });
                        }).bind("mouseout", function () { $(this).css({ backgroundPosition: '0px 0px' }) });

                        $("#w13a").bind("mouseover", function () { challengeObj.nextOver(1); })
                        .bind("mouseout", function () { challengeObj.intvelLeagueIndex = setTimeout(function () { challengeObj.NextClick(1, 0) }, 500); })
                        //点击具体信息的下拉列表

                        $("#w14b").bind("click", function () { challengeObj.ShowChengeButton(2); }).bind("mouseover", function () {
                            $("#w14b").css("cursor", "pointer");
                            challengeObj.nextOver(2); $(this).css({ backgroundPosition: '0px -25px' });
                        }).bind("mouseout", function () { $(this).css({ backgroundPosition: '0px 0px' }) });

                        $("#w14a").bind("mouseover", function () { challengeObj.nextOver(2); })
                        .bind("mouseout", function () { challengeObj.intvelCateGoryIndex = setTimeout(function () { challengeObj.NextClick(2, 0) }, 500); })
                    }
                    else {
                        AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtml, null, null, function () {
                            $.post('Default.aspx', { type: 3 }, function (result) {
                                if ((parseInt(result) == 1001 || parseInt(result) == 5998) && challengeObj.finish1001 == 1) {
                                    // 处在新手期，挑战按钮上方显示绿箭头

                                    $.post('Default.aspx', { type: 6, intTaskIndex: 1001 }, function (res) {
                                        $("#imgArrow").remove();
                                        LoadPageInfo();
                                    });
                                }
                                if (parseInt(type) >= 11) {
                                    challengeObj.GoToChallenge();
                                }
                            });
                        }, 'ChallengeDiv', null, null, null);
                    }

                    if (next == 3 && type == 1 && ress[6] == "-1" && next != rewardIndex) {
                        ShowMX(MatchList_Challenge_DJZS, "", function () { $("#divMX").css("display", "none") });
                        $("#divMX").css("z-index", "10000");
                    }
                    //ress[6] = "star|itemCode|itemName|[count]|imgURL&star|itemCode|itemName|[count]|imgURL@homescore|awayscore|homeclubname";
                    if (ress[6] != "-1") {
                        var hasaward = -1;
                        var starInfo = "";
                        var imgInfo = "";
                        var noInfo = "";
                        var name = ress[6].split('@')[1].split('|'); //homescore|awayscore|homeclubname
                        var ss = ress[6].split('@')[0].split('&');
                        var exp = ress[6].split('@')[2]; //球员经验
                        starInfo += "<div style='background:url(" + ImgPath + "Images/Public/border1.png);_background:none;_filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src=\"" + ImgPath + "Images/Public/border1.png\",sizingMethod=\"crop\");width:475px; height:223px;position:absolute;left:5px;'></div><div style='background:url(" + ImgPath + "Images/Public/bg.jpg);width:468px; height:217px;position:absolute;left:8px;top:3px;'><div style='background:url(" + ImgPath + "Images/Bag/getbg.png);width:224px;height:44px;position:absolute;top:116px;left:128px'></div><div style='position:absolute;left:30px;top:" + (ss.length >= 3 ? "62" : "74") + "px;font-size:14px;font-weight:bolder;color:black;text-align:center;width:420px;'>";
                        if (ress[6].split('|')[1] == "5001") {
                            //如果是银币  特殊处理
                            starInfo += MatchList_Challenge_FB2.replace("{0}", ress[6].split('|')[3]);
                            starInfo += "<br />";
                            noInfo += MatchList_Challenge_FB2.replace("{0}", ress[6].split('|')[3]);
                            noInfo += "<br />";
                        } else {
                            for (var ii = 0; ii < ss.length; ii++) {
                                if (ss[ii] != "") {
                                    var onestar = ss[ii].split('|'); //star|itemCode|itemName|[count]|imgURL
                                    if (parseInt(onestar[3]) > 0) {
                                        hasaward = 1;
                                        starInfo += onestar[0] + MatchList_Challenge_XJL + "：" + onestar[2];
                                        if (ii < ss.length - 1) {
                                            starInfo += "　";
                                        }
                                        if (ii == 1) {
                                            starInfo += "<br />";
                                        }
                                        imgInfo += "<img src='" + ImgPath + "Images/Item/" + onestar[4] + "' width='42' height='42' style='position:absolute;left:" + (129 + ii * 44) + "px;top:117px;'/>";
                                    }
                                    else {
                                        if (ii == 0) {
                                            noInfo += MatchList_Challenge_GXHS;
                                        }
                                    }
                                }
                            }
                        }
                        if (parseInt(exp) > 0) {
                            starInfo += MatchList_Challenge_ZLHDJY.replace("{0}", exp);
                            noInfo += MatchList_Challenge_ZLHDJY.replace("{0}", exp);
                        }
                        if (parseInt(awardindex) > 0) {
                            starInfo += "</br>获得" + awardindex + "号奖杯 一个。";
                            noInfo += "</br>获得" + awardindex + "号奖杯 一个。";
                        }
                        if (parseInt(BeckhamCupIndex) > 0) {
                            starInfo += "</br>获得" + BeckhamCupIndex + "号奖杯 一个。";
                            noInfo += "</br>获得" + BeckhamCupIndex + "号奖杯 一个。";
                        }
                        //if (ClubPower_PlayerNameIndex > 0) {
                        //  if (ClubPower_PlayerNameWord == '斯1') {
                        //    starInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的第一个\"斯\"";
                        //    noInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的第一个\"斯\"";
                        //   } else if (ClubPower_PlayerNameWord == '斯2') {
                        //     starInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的第二个\"斯\"";
                        //    noInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的第二个\"斯\"";
                        //   } else {
                        //    starInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的\"" + ClubPower_PlayerNameWord + "\"";
                        //    noInfo += "<br>恭喜您获得" + ClubPower_PlayerName + "的\"" + ClubPower_PlayerNameWord + "\"";
                        //   }
                        // }
                        if (parseInt(isHasReward_ballack) > 0) {
                            starInfo += "</br>获得【" + ballackActivityInfo + "】。";
                            noInfo += "</br>获得【" + ballackActivityInfo + "】。";
                        } BattyCupIndex
                        if (parseInt(BattyCupIndex) > 0) {
                            starInfo += "</br>获得" + BattyCupIndex + "号奖杯 一个。";
                            noInfo += "</br>获得" + BattyCupIndex + "号奖杯 一个。";
                        }

                        starInfo += "</div>" + imgInfo;
                        $("#ChallengeDiv").append("<div id='zhe2' style=\"width:960px; height:542px;position:absolute; background:black; top:25px; left:-10px; z-index:4;  filter:alpha(opacity=0);opacity:0;\"></div>");

                        if (hasaward > 0) {
                            starInfo += "<div class='div_btn2' style='position:absolute;left:209px;top:185px;' onclick='$(\"#zhe2\").remove(); $(\"#cad\").remove(); challengeObj.ra(" + parseInt(result) + "); '>" + MatchList_Challenge_LQ + "</div>";
                            starInfo += "</div>";
                            AddBox(279, 152, 490, 282, MatchList_Challenge_HDJL, starInfo, $("#totalMainDiv"), null, function () { $("#zhe2").remove(); $("#cad").remove(); challengeObj.ra(parseInt(result)); }, 'cad', null, null, null);
                        }
                        else {
                            if (parseInt(isHasReward_ballack) > 0) {//如果获得巴拉克升级的道具或“字”，提示上移
                                noInfo = "<div style='margin-top:-15px;'>" + noInfo + "</div>";
                            }
                            //if (parseInt(ClubPower_PlayerNameIndex) > 0) {//如果获得“球会力量”活动的道具，提示上移
                            //    noInfo = "<div style='margin-top:-15px;'>" + noInfo + "</div>";
                            // }
                            if (parseInt(awardindex) > 0) {//如果获得小贝(印象·万人迷活动)的奖杯，提示上移
                                noInfo = "<div style='margin-top:-15px;'>" + noInfo + "</div>";
                            }
                            if (parseInt(BeckhamCupIndex) > 0) {//如果获得小贝升级的奖杯，提示上移
                                noInfo = "<div style='margin-top:-15px;'>" + noInfo + "</div>";
                            }
                            if (parseInt(BattyCupIndex) > 0) {//如果获得巴蒂个人升级的奖杯，提示上移
                                noInfo = "<div style='margin-top:-15px;'>" + noInfo + "</div>";
                            }
                            ShowMsg(noInfo, MatchList_Challenge_sure, function () {
                                $("#zhe2").remove(); $("#cad").remove(); challengeObj.ra(parseInt(result));
                            }, null, function () {
                                $("#zhe2").remove(); $("#cad").remove(); challengeObj.ra(parseInt(result));
                            }, null, 290, 175, $("#ChallengeDiv"), "cad", null);
                        }
                        if (parseInt(result) == 1001 || parseInt(result) == 5998) {
                            // 处在新手期，显示绿箭头
                            ShowArrow(220, 252, $("#cad"), 90, 30, 2);
                        }

                    }
                    if (!$("#cad")[0]) {// 如果没有奖励信息
                        if (next != rewardIndex) {// 如果没有翻牌信息。
                            if (parseInt(result) == 1001) {
                                // 处在新手期，挑战按钮上方显示绿箭头
                                // 846 415
                                if (next == 1 && parseInt(result) == 1001) {
                                    ShowArrow(39, 371, $("#ChallengeDiv"), 100, 40);
                                }
                                else { ShowArrow(942, 7, null, 30, 30); }

                            } else if (parseInt(result) == 5998) {
                                ShowArrow(942, 7, null, 30, 30);
                            }

                        }
                    }
                    if (challengeObj.showGotoNextBut == 1 && type != "6") {// 不等于5，因为最后一幕没有去下一幕的功能
                        $("#gotonext").css("display", "block");
                        $("#gotonext").bind("mouseover", function () { $("#gotonext").css("background", "url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -86px -24px"); }).bind("mouseout", function () { $("#gotonext").css("background", "url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -86px 0px"); }).bind("click", function () { challengeObj.GoToChallengePage(parseInt(type) + 1); });
                        var ii = "";
                        switch (type) {
                            case "1": case 1: ii = MatchList_Challenge_qw1; break;
                            case "2": case 2: ii = MatchList_Challenge_qw2; break;
                            case "3": case 3: ii = MatchList_Challenge_qw3; break;
                            case "4": case 4: ii = MatchList_Challenge_qw4; break;
                            case "5": case 5: ii = MatchList_Challenge_qw5; break;
                            default: ii = ""; break;
                        }
                        $("#gotonext").html(ii);
                    }
                    $("#gotomap").bind("mouseover", function () { $("#gotomap").css("background", "url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -172px -24px"); }).bind("mouseout", function () { $("#gotomap").css("background", "url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -172px 0px"); }).bind("click", function () { challengeObj.GoToChallenge(); });
                    if (challengeObj.nextName != "") {
                        $("#nameinfo").html(MatchList_Challenge_GXZS.replace("{0}", challengeObj.nextName));
                    }
                    // 如果有翻牌信息
                    if (next == rewardIndex) {
                        bindcardinfo();
                    }
                }
                else {
                    $("#ChallengeDiv").remove();
                    var matchinfo = ress[0].split('|');
                    showMatch(matchinfo[1], 1);
                }

            });
        });
    };
    this.refreshDeal = function () {
        $("#s")[0].onclick = null;
        $("#challengeMakePL2").css("display", "block");
        //$("#challengeMakePL").css("left", "800px").css("top", "200px").css("width", "78px").css("font-size", "12px").css("color", "white").html("已签约");
    }
    this.PlayerDataName = new Array(MatchList_Challenge_NYT1, MatchList_Challenge_NYT2, MatchList_Challenge_NYT3);
    this.beforecreateSuper = function () {
        $("#comeinbut")[0].onclick = null;
        $("#comeinbut")[0].onclick = function () { challengeObj.createSuper() }; //创建动作
        $("#big").stop().animate({ left: -390, top: 100 }, 500, function () { });
        $("#phead").stop().animate({ left: -605, top: -10 }, 500, function () { });
        $("#w2").stop().animate({ left: 150, top: 280 }, 500, function () { });
        $("#w3").stop().animate({ left: 151, top: 281 }, 500, function () { });
        $("#w1").css("display", "none");
        setTimeout(function () { $("#w11").css("display", "block"); }, 800);
        setTimeout(function () {
            $("#w12").css("display", "block");

        }, 900); //球员名称
        setTimeout(function () { $("#w13").css("display", "block"); $("#w13a").css("display", "block"); }, 1000); //场上位置
        setTimeout(function () { $("#w14").css("display", "block"); $("#w14a").css("display", "block"); $("#ImageURLHZ").css("display", "block"); }, 1100); //球员类型
        setTimeout(function () { $("#w15").css("display", "block"); }, 1200);

    }
    //点击位置下拉框弹出
    this.ShowChengeButton = function (theBT) {
        if (theBT == 1) {
            $("#w13a").css("height", "100px");
            var htmlStr = "";
            htmlStr += "<div id='TypeC1' style='width:136px;height:25px;position:absolute;z-index:100;left:0px;top:25px;display:block;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png)' onclick='challengeObj.NextClick(1,1)' onmouseover='challengeObj.nextOver(1);challengeObj.UpdateBgOnOver(this)' onmouseout='challengeObj.nextOver(1);challengeObj.UpdateBgOnOut(this)'> &nbsp;" + challengeObj.PlayerDataName[0].split('|')[0] + "</div>";
            htmlStr += "<div id='TypeC2' style='width:136px;height:25px;position:absolute;z-index:100;left:0px;top:50px;display:block;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png)' onclick='challengeObj.NextClick(1,2)'  onmouseover='challengeObj.nextOver(1);challengeObj.UpdateBgOnOver(this)'  onmouseout='challengeObj.nextOver(1);challengeObj.UpdateBgOnOut(this)'> &nbsp;" + challengeObj.PlayerDataName[1].split('|')[0] + "</div>";
            htmlStr += "<div id='TypeC3' style='width:136px;height:25px;position:absolute;z-index:100;left:0px;top:75px;display:block;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png)' onclick='challengeObj.NextClick(1,3)'  onmouseover='challengeObj.nextOver(1);challengeObj.UpdateBgOnOver(this)'  onmouseout='challengeObj.nextOver(1);challengeObj.UpdateBgOnOut(this)'> &nbsp;" + challengeObj.PlayerDataName[2].split('|')[0] + "</div>";
            $("#w13a").append(htmlStr);
        } else if (theBT == 2) {
            var index = parseInt(challengeObj.typeVal);
            $("#w14a").css("height", "75px");
            var htmlStr = "";
            htmlStr += "<div id='Category1' style='width:136px;height:25px;position:absolute;z-index:100;top:25px;display:block;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png)' onclick='challengeObj.NextClick(2,1)' onmouseover='challengeObj.nextOver(2);challengeObj.UpdateBgOnOver(this)' onmouseout='challengeObj.nextOver(2);challengeObj.UpdateBgOnOut(this)'> &nbsp;" + challengeObj.PlayerDataName[index].split('|')[1] + "</div>";
            htmlStr += "<div id='Category2' style='width:136px;height:25px;position:absolute;z-index:100;top:50px;display:block;background:url(" + ImgPath + "Images/ChallengeMatch/listbg.png)' onclick='challengeObj.NextClick(2,2)'  onmouseover='challengeObj.nextOver(2);challengeObj.UpdateBgOnOver(this)'  onmouseout='challengeObj.nextOver(2);challengeObj.UpdateBgOnOut(this)'> &nbsp;" + challengeObj.PlayerDataName[index].split('|')[2] + "</div>";
            $("#w14a").append(htmlStr);
        }
    }
    //手表移动事件
    this.UpdateBgOnOver = function (thisBG) {
        $(thisBG).css('backgroundPosition', '0px -25px');
        $(thisBG).css("cursor", "pointer");
    };
    //手表移动事件
    this.UpdateBgOnOut = function (thisBG) {
        $(thisBG).css('backgroundPosition', '0px 0px');
    };
    //清除收回事件
    this.nextOver = function (type) {
        if (type == 1) {
            //场上位置下拉框
            clearTimeout(challengeObj.intvelLeagueIndex);
        } else if (type == 2) {
            //场上位置下拉框
            clearTimeout(challengeObj.intvelCateGoryIndex);
        }
    }
    //鼠标移动到下拉选项中
    this.NextClick = function (type, Index) {
        if (type == 1) {
            //场上位置下拉框
            if (Index == 1) {
                challengeObj.typeVal = 0;
                $("#type0").html($("#TypeC1").text());
                $("#Category0").html(" &nbsp;" + challengeObj.PlayerDataName[0].split('|')[1]);
            } else if (Index == 2) {
                challengeObj.typeVal = 1;
                $("#type0").html($("#TypeC2").text());
                $("#Category0").html(" &nbsp;" + challengeObj.PlayerDataName[1].split('|')[1]);
            } else if (Index == 3) {
                challengeObj.typeVal = 2;
                $("#type0").html($("#TypeC3").text());
                $("#Category0").html(" &nbsp;" + challengeObj.PlayerDataName[2].split('|')[1]);
            }
            $("#TypeC1").remove();
            $("#TypeC2").remove();
            $("#TypeC3").remove();
            $("#w13a").css("height", "25px");
        } else if (type == 2) {
            //场上位置下拉框
            if (Index == 1) {
                $("#Category0").html($("#Category1").text());
                challengeObj.CateVal = 1;
            } else if (Index == 2) {
                $("#Category0").html($("#Category2").text());
                challengeObj.CateVal = 2;
            }
            $("#Category1").remove();
            $("#Category2").remove();
            $("#w14a").css("height", "25px");
        }
    }
    this.createSuper = function () {
        var createsupername = $("#PlayerDataNameVal")[0].value;
        if (selLegendHeadName == "") {
            ShowMsg("请选择您的头像", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
        }
        else if (ClearLanguage(createsupername).indexOf('***') >= 0) {
            ShowMsg(MatchList_Challenge_challengemsg1, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
        } else if (Name_check($("#PlayerDataNameVal")[0]) == "1") {
            ShowMsg(MatchList_Challenge_challengemsg2, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
        } else {
            $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "createsupername": createsupername, "role": challengeObj.typeVal, "type": challengeObj.CateVal, "selLegendHeadName": selLegendHeadName }, function (res) {
                if (res == "-1") {
                    // 替补为已满
                    ShowMsg(MatchList_Challenge_challengemsg3, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (res == "-2") {
                    // 创建失败
                    ShowMsg(MatchList_Challenge_challengemsg4, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                } else if (res == "-3") {
                    // 名字相同
                    ShowMsg("这个名字太火了，请换一个吧！", MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else {
                    //成功
                    ShowMsg(MatchList_Challenge_challengemsg5, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(6); }, null, function () { challengeObj.GoToChallengePage(6); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                    selLegendHeadName = ""
                }
            });
        }
    }
    this.ra = function (result) {//领取星级奖励
        $("#ra").remove();
        $("#zhe2").remove();
        $.post('Default.aspx', { type: 3 }, function (result) {
            if (parseInt(result) == 1001 || parseInt(result) == 5998) {
                $("#ChallengeDiv").remove();
                $.post('Default.aspx', { type: 6, intTaskIndex: 1001 }, function (res) {
                    $("#imgArrow").remove();
                    LoadPageInfo();
                    //publicAddGSEL(2, 1000); // 加银币动画
                    //publicAddGSEL(4, 10); // 加经验动画
                });
            }
        });
        //RemoveTipBox();
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "ra": 1 }, function (res) {
            //challengeObj.GoToChallengePage(challengeObj.LeagueIndex);
        });
    }
    // 生成左侧球队列表，以及地图上的点，悬停事件等等
    function createClubListHtml(clubsStr, next, rewardIndex, type, playerinfo) {
        //alert(clubsStr);
        // 没有比赛
        var clubs = clubsStr.split('^');
        var StrHtml = "<div style='width:889px;height:312px;position:absolute;left:-26px;top:5px;'></div>";
        var PointsHtml = "";
        var finishedNum = 0;
        for (var i = clubs.length - 1; i >= 0; i--) {
            if (clubs[i] != "") {
                var oneClubInfo = clubs[i].split('|');
                var clubState = oneClubInfo[0];
                var clubIndex = oneClubInfo[1];
                var clubName = oneClubInfo[2];
                var vinAfter = oneClubInfo[3];
                var power = oneClubInfo[8];
                var tactics = oneClubInfo[9];
                var gift = oneClubInfo[4];
                var left = oneClubInfo[5];
                var top = oneClubInfo[6];
                var bgIndex = oneClubInfo[7];
                var chengli = oneClubInfo[11];
                var zhuchang = oneClubInfo[12];
                var chuohao = oneClubInfo[13];
                var g1 = oneClubInfo[14];
                var g2 = oneClubInfo[15];
                var clubimg = oneClubInfo[16];
                var killclubname = oneClubInfo[18];
                var killclubid = oneClubInfo[17];
                var star = oneClubInfo[19];
                var time = oneClubInfo[20];
                var cointype = oneClubInfo[21]; //1 金币，2 银币
                var coinnum = oneClubInfo[22];

                var divleft = (i * 89 - 26);
                var divtop = 5;

                if (challengeObj.LeagueIndex >= 11) {
                    divleft += 317;
                    divtop -= 5;
                }
                StrHtml += "<div id='showh" + i + "' style='background:url(" + ImgPath + "Images/ChallengeMatch/dark" + type + ".jpg) -" + (parseInt(i * 89)) + "px 0px no-repeat;position:absolute;left:" + divleft + "px;top:" + divtop + "px;width:89px;height:312px;'></div>"; // 背景图
                StrHtml += "<div style='position:absolute;left:" + (divleft - 2) + "px;top:-21px;width:89px;height:350px;'";
                if (clubState == 0 && next != clubIndex) {
                    // 未完成的
                    StrHtml += "onmouseout=\"challengeObj.hideInfo(-1,-1)\" onmouseover=\"challengeObj.ChallengeShowClubInfo('" + i + "','" + clubName + "','" + vinAfter + "','" + power + "','" + tactics + "','" + gift + "','" + chengli + "','" + zhuchang + "','" + chuohao + "','" + g1 + "','" + g2 + "',0,0,0,0,'" + killclubname + "','" + killclubid + "')\">";
                    if (challengeObj.LeagueIndex <= 5) {
                        StrHtml += "<div class='CMclubname' >" + clubName + "</div></div>";
                    }
                    else {
                        StrHtml += "<div style='color:white;text-align:center;font-size:14px;font-style:italic;font-weight:bold' >" + clubName + "</div></div>";
                    }
                }
                else if (clubState == 0 && next == clubIndex && next != rewardIndex) {
                    // 下一个要挑战的
                    StrHtml += "  onmouseover=\"challengeObj.ChallengeShowClubInfo('" + i + "','" + clubName + "','" + vinAfter + "','" + power + "','" + tactics + "','" + gift + "','" + chengli + "','" + zhuchang + "','" + chuohao + "','" + g1 + "','" + g2 + "',0,0,0,0,'" + killclubname + "','" + killclubid + "')\" onmouseout=\"challengeObj.hideInfo(-1,-1)\">";
                    StrHtml += "<div>";
                    if (challengeObj.LeagueIndex <= 5) {
                        StrHtml += "<div class='CMclubname' >" + clubName + "</div>";
                    }
                    else {
                        StrHtml += "<div style='color:white;text-align:center;font-size:14px;font-style:italic;font-weight:bold'>" + clubName + "</div>";
                    }
                    StrHtml += "<div style='background:url(" + ImgPath + "Images/ChallengeMatch/light" + type + ".jpg) " + (-i * 89) + "px top;cursor:pointer;width:89px;height:312px;position:absolute;top:" + (divtop + 21) + "px;left:2px;'  onclick=\"beforeinsertMatch('" + challengeObj.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj','ChallengeDiv',-1,'" + time + "','" + cointype + "','" + coinnum + "','" + killclubname + "','" + killclubid + "')\"></div>";
                    StrHtml += "<div id=\"challengeBut\" class='div_btn2_rank' style='position:absolute;left:1px;top:349px;'  onclick=\"beforeinsertMatch('" + challengeObj.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj','ChallengeDiv',-1,'" + time + "','" + cointype + "','" + coinnum + "')\">";
                    StrHtml += MatchList_Public_Challenge + "</div></div></div>";

                }
                else {
                    // 完成的
                    if (challengeObj.LeagueIndex >= 10) {
                        StrHtml += " onmouseout=\"challengeObj.hideInfo('" + type + "','" + i + "')\" onmouseover=\"challengeObj.ChallengeShowClubInfo('" + i + "','" + clubName + "','" + vinAfter + "','" + power + "','" + tactics + "','" + gift + "','" + chengli + "','" + zhuchang + "','" + chuohao + "','" + g1 + "','" + g2 + "',1,'" + type + "','" + clubIndex + "','" + clubimg + "','" + killclubname + "','" + killclubid + "')\">";
                    }
                    else {
                        StrHtml += " onmouseout=\"challengeObj.hideInfo('" + type + "','" + i + "')\" onmouseover=\"challengeObj.ChallengeShowClubInfo('" + i + "','" + clubName + "','" + vinAfter + "','" + power + "','" + tactics + "','" + gift + "','" + chengli + "','" + zhuchang + "','" + chuohao + "','" + g1 + "','" + g2 + "',1,'" + type + "','" + clubIndex + "','" + clubimg + "','" + killclubname + "','" + killclubid + "')\" onclick=\"beforeinsertMatch('" + challengeObj.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj','ChallengeDiv',-1,'" + time + "','" + cointype + "','" + coinnum + "')\" style='cursor:pointer'>";
                    }
                    StrHtml += "<div style='background:url(" + ImgPath + "Images/ChallengeMatch/win.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/win.png);width:93px;height:92px;position:absolute;top:134px;left:0px;'></div>";
                    if (parseInt(star) > 0) {// 星星
                        StrHtml += "<div style='width:72px;height:23px;position:absolute;top:115px;left:10px;'>";
                        for (var jj = 1; jj <= 3; jj++) {
                            StrHtml += "<div style='" + (parseInt(star) >= jj ? "background:url(" + ImgPath + "Images/ChallengeMatch/staryellow.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/staryellow.png);" : "background:url(" + ImgPath + "Images/ChallengeMatch/starwhite.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/starwhite.png);") + "width:24px;height:23px;float:left'></div>";
                        }
                        StrHtml += "</div>";
                    }
                    StrHtml += "<div>";
                    if (challengeObj.LeagueIndex <= 5) {
                        StrHtml += "<div class='CMclubname' >" + clubName + "</div>";
                    }
                    else {
                        StrHtml += "<div style='color:#f5dc27;text-align:center;font-size:14px;font-style:italic;font-weight:bold' >" + clubName + "</div>";

                    }
                    if (challengeObj.LeagueIndex >= 11) {
                        //副本，打过的没有挑战按钮
                    } else {
                        StrHtml += "<div id=\"challengeBut" + i + "\" class='div_btn2_rank' style='position:absolute;left:1px;top:349px;display:none'  onclick=\"beforeinsertMatch('" + challengeObj.MyVsInfo + "','" + clubIndex + "','" + clubimg + "','" + clubName + "','" + power + "','" + tactics + "','challengeObj','ChallengeDiv',-1,'" + time + "','" + cointype + "','" + coinnum + "')\">" + MatchList_Public_Challenge + "</div>";
                    }
                    StrHtml += "</div></div>";
                    finishedNum++;
                    if (i == 0) {
                        challengeObj.finish1001 = 1;
                    }
                    else if (i == 1) {
                        challengeObj.finish1004 = 1;
                    }
                }
                if (clubIndex == rewardIndex) {
                    challengeObj.nextName = clubName;
                }
            }
            if (finishedNum >= 10) {
                challengeObj.showGotoNextBut = 1;
            }
            else {
                challengeObj.showGotoNextBut = 0;
            }
        }
        if (challengeObj.LeagueIndex >= 11) {
            // 副本
            StrHtml += "<div style='position:absolute;left:735px;top:128px;background:url(" + ImgPath + "Images/ChallengeMatch/arr.png);width:43px;height:46px;'></div>";
            StrHtml += "<div style='position:absolute;left:780px;top:68px;font-weight:bolder;color:white;font-size:18px;width:83px;height:46px;'>" + MatchList_Challenge_FB3 + "</div>";
            // 副本球员
            var pinfo = playerinfo.split("|");
            //状态（0不能签，1可签，2签过了）|playerid|playername|playerquality|playeravgpower|role|photo|clubid|clubname
            var state = pinfo[0]; // 0不能签，1可以签，2签过了
            if (state == 1 || state == 0) {
                //显示头像
                StrHtml += CreatePlayer(pinfo[4], pinfo[5], pinfo[6], pinfo[2], pinfo[3], pinfo[1], 778, 100, "s");
                if (state == 1) {
                    //绑定签约事件
                    StrHtml += "<div id='challengeMakePL' class=\"div_btn2_rank\" style='position:absolute;left:775px;top:207px;' onclick=\"middleManObj.showDealOP('" + pinfo[1] + "',11)\">" + MiddleMan_info24 + "</div>";
                    StrHtml += "<div id='challengeMakePL2' style='display:none;position:absolute;left:800px;top:200px;width:78px;font-size:12px;color:white;'>" + MatchList_Challenge_FB4 + "</div>";
                }
            } else if (state == 2) {
                //已签约
                StrHtml += CreatePlayer(pinfo[4], pinfo[5], pinfo[6], pinfo[2], pinfo[3], null, 778, 100, "s");
                StrHtml += "<div  id='challengeMakePL2' style='position:absolute;left:800px;top:200px;width:78px;font-size:12px;color:white;'>" + MatchList_Challenge_FB4 + "</div>";
            }
            StrHtml += "";
        }
        return StrHtml + "*" + PointsHtml;
    }
    //    this.showDealOP = function (playerid, type) {
    //        $.post("MatchList/ChallengeMatch/ChallengeList.aspx", { "MakeContractPlayerInChallenge": 1, "playerid": playerid }, function (ress) {
    //            alert(ress);
    //        });
    //    }
    // 根据这一幕共有多少张牌，有多少功能按键，生成牌
    function createCardHtml() {
        var CardHtml = "<div style=\"position:absolute;left:0px;top:0px;\">";
        CardHtml += "<div id=\"awardCards\">";
        CardHtml += "<div style=\"width:923px; height:463px;position:absolute; background:black; top:0px; left:-9px; z-index:4;  filter:alpha(opacity=90);opacity:0.9;\"></div>";
        CardHtml += "<div style=\"width:912px; height:398px; position:absolute; top:36px; left:0px; z-index:5;\">";
        CardHtml += "<div id=\"nameinfo\" style=\"width:912px; position:absolute; top:10px; height:22px; text-align:center; font-size:20px; bottom: 356px;color:white;font-weight:bold\"></div>";
        if (challengeObj.LeagueIndex < 10) {
            CardHtml += "<div id='canOpeninfo' style=\"width:912px; position:absolute; top:45px;left:0px; height:22px; text-align:center; font-size:14px; z-index:5;color:white\">" + MatchList_Challenge_challengemsg6.replace("{0}", "<span id=\"cardspan\"></span>") + "</div>";
        }
        else {
            CardHtml += "<div id='canOpeninfo' style=\"width:912px; position:absolute; top:367px;left:0px; height:22px; text-align:center; font-size:14px; z-index:5;color:white\"></div>";
        }
        CardHtml += "<div style=\"width:912px; position:absolute; top:81px; height:38px; text-align:center; font-size:14px; z-index:4; left: 0px;\">";
        CardHtml += "<div class=\"tool\" id=\"reminddiv\" style=\"display:none;left:276px;\" onmouseover=\"ShowTipBox('" + MatchList_Challenge_challengemsg7 + "')\">" + MatchList_Challenge_challengemsg8 + "</div>";
        CardHtml += "<div class=\"tool\" id=\"seediv\" style=\"display:none;left:416px;\" onmouseover=\"ShowTipBox('" + MatchList_Challenge_challengemsg9 + "')\">" + MatchList_Challenge_challengemsg10 + "</div>";
        CardHtml += "<div class=\"tool\" id=\"retrydiv\" style=\"display:none;left:556px;\" onmouseover=\"ShowTipBox('" + MatchList_Challenge_challengemsg11 + "')\">" + MatchList_Challenge_challengemsg12 + "</div>";
        CardHtml += "</div>";
        CardHtml += "<div id=\"cardsdiv\">";
        CardHtml += "</div>";
        CardHtml += "<div id=\"cardInfodiv\" style=\" position:absolute; background:url(" + ImgPath + "Images/ChallengeMatch/info.png);_background:none;filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(src='" + ImgPath + "Images/ChallengeMatch/info.png', sizingMethod='crop');width:92px;height:68px;display:none;color:Black;line-height:51px;text-align:center\"></div>";
        CardHtml += "</div>";
        CardHtml += "<div  style=\"position:absolute;left:0px;width:926px;top:370px; z-index:5;\">";
        CardHtml += "<div class=\"div_btn2\" id=\"recievebut\" style=\"position:absolute;z-index:5;display:none;left:290px;\" onclick=\"challengeObj.beforeLingqu(1)\">" + MatchList_Challenge_challengemsg17 + "</div>";
        CardHtml += "<div class=\"div_btn2\" id=\"againbut\" style=\"position:absolute;z-index:5;display:none;left:430px;\" onclick=\"challengeObj.beforeLingqu(2)\" onmouseover=\"ShowTipBox('" + MatchList_Challenge_challengemsg13 + "')\">" + MatchList_Challenge_challengemsg14 + "</div>";
        CardHtml += "<div class=\"div_btn2\" id=\"washbut\" style=\"position:absolute;z-index:5;display:none;left:570px;\" onclick=\"challengeObj.beforeLingqu(3)\" onmouseover=\"ShowTipBox('" + MatchList_Challenge_challengemsg15 + "')\">" + MatchList_Challenge_challengemsg16 + "</div>";
        CardHtml += "</div>"; //buttons
        CardHtml += "</div>";
        CardHtml += "</div>";

        //ShowArrow(437, 0, $("#ChallengeDiv"), 500, 400);

        $("#imgArrow").remove();
        return CardHtml;
    }
    this.nameinfo1 = new Array(MatchList_Challenge_FB5, MatchList_Challenge_FB6, MatchList_Challenge_FB7, MatchList_Challenge_FB8, "卡福副本");
    this.nameinfo2 = new Array("Didier Deschamps", "Thomas Hassler", "Gabriel Omar Batistuta", "David Backham", "Marcos Evangelista de Morais");
    // 生成页面头部的html信息
    function createTopHtml(LeagueIndex, result, powerInfo) {
        var StrHtml = "<div><div style=\"width:912px;height:460px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.png);position:absolute;left:7px;\">";
        if (LeagueIndex <= 5) {
            StrHtml += "<div style=\"position:absolute;left:8px;top:8px;width:899px;height:446px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.jpg);\"></div>";
            StrHtml += "<div style=\"position:absolute;left:48px;top:11px;width:193px;height:53px;background:url(" + ImgPath + "Images/ChallengeMatch/logo_" + LeagueIndex + ".png);\"></div>";
        }
        else if (LeagueIndex == 6) {
            StrHtml += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/Mbg2.jpg);\"></div>";
            StrHtml += "<div style=\"position:absolute;left:22px;top:7px;width:58px;height:59px;background:url(" + ImgPath + "Images/ChallengeMatch/logo_" + LeagueIndex + ".png) no-repeat;\"><div style='font-size:34px;color:#f5dc27;font-style:italic;position:absolute;left:63px;top:1px;font-weight:bold;width:141px'>" + MatchList_Challenge_challengemsg18 + "</div><div style='font-size:10px;color:#f5dc27;font-style:italic;position:absolute;left:110px;top:43px;width:250px;font-weight:bold'>The Legend of UEFA champions league</div><div style='overflow:hidden;position:absolute;left:58px;top:58px;background:url(" + ImgPath + "Images/ChallengeMatch/line.jpg) no-repeat;width:468px;height:1px;'></div></div>";
        }
        else {
            StrHtml += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/x" + (LeagueIndex - 10) + ".jpg);\"></div>";
            StrHtml += "<div style=\"position:absolute;left:22px;top:7px;width:58px;height:59px;background:url(" + ImgPath + "Images/ChallengeMatch/logo_" + LeagueIndex + ".png) no-repeat;\"><div style='font-size:30px;color:#f5dc27;font-style:italic;position:absolute;left:6px;top:5px;line-height:30px;font-weight:bold;width:70px;'>" + challengeObj.nameinfo1[LeagueIndex - 11] + "</div>";

            StrHtml += "<div id='chpower' style='position:absolute;left:736px;top:20px;width:250px;'>" + challengeObj.dobindpowerch(powerInfo) + "</div>";

            StrHtml += "<div style='font-size:22px;color:#f5dc27;font-style:italic;position:absolute;left:210px;top:23px;width:250px;'>" + challengeObj.nameinfo2[LeagueIndex - 11] + "</div></div>";
        }
        StrHtml += "<div style=\"position:absolute;left:707px;top:12px;width:190px;height:24px;\">";
        StrHtml += "<div id='gotonext' class='nextbackbtn' style='background:url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -86px 0px;display:none'></div>";

        if (parseInt(result) == 1001) {
            // 处在新手期，不显示返回地图
        }
        else if (LeagueIndex < 11) {
            StrHtml += "<div id='gotomap' class='nextbackbtn' style=\"left:104px;background:url(" + ImgPath + "Images/ChallengeMatch/btn11.png) -172px 0px;\">" + MatchList_Challenge_challengemsg19 + "</div>";
        }
        StrHtml += "</div>";
        StrHtml += "<div id='ClallengeGoningOn' class=\"infoSmall\" style=\"position:absolute;left:340px;top:190px; z-index:3;display:none\">" + MatchList_Challenge_challengemsg20 + ".&nbsp;&nbsp;</div>";
        StrHtml += "<div style=\"position:absolute;left:38px;top:92px; width:247px;height:349px\">";
        return StrHtml;


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
        StrHtml += "<div class=\"div_btn2Y\"  style=\"position:absolute;left:90px;\" id=\"addBBut\" style=\"font-size:12px;\" onclick=\"challengeObj.showChallengeAdd(" + peopleCount + "," + haveCount + "," + challengeObj.LeagueIndex + ")\">" + MatchList_Challenge_FB_info9 + "</div>";  //小人
        return StrHtml;
    }

    this.showChallengeAdd = function (peopleCount, haveCount, LeagueIndex) {
        if (peopleCount <= haveCount) {
            ShowMsg(MatchList_Challenge_FB_info10, MatchList_Public_ok, function () { $("#bf").remove(); AddFun(); }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
            challengeObj.dobindpowerch("5|5");
            return;
        } else {
            $.post("MatchList/ChallengeMatch/ChallengeList.aspx", { "ShowPowerCoin": 1, "LeagueIndex": LeagueIndex }, function (ress) {
                var res = ress.split('|')[0];
                var htmlstr = "";
                if (res == "-1") {
                    htmlstr = MatchList_Challenge_FB_info11;
                    ShowMsg(htmlstr, MatchList_Public_ok, null, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                    return;
                } else if (res == "-2") {
                    htmlstr = MatchList_Challenge_FB_info12.replace("{0}", ress.split('|')[1]);
                    OpenPayMsg(htmlstr);
                    return;
                } else if (res == "0") {
                    htmlstr = MatchList_Challenge_FB_info13;
                    ShowMsg(htmlstr, MatchList_Public_ok, null, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
                    return;
                } else {
                    htmlstr = MatchList_Challenge_FB_info14.replace("{0}", ress.split('|')[1]);
                }
                ShowMsg(htmlstr, MatchList_Public_ok, function () {
                    $("#bf").remove();
                    challengeObj.UpdateChallengeFBInfo(LeagueIndex);
                }, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
            });
        }
    }
    this.UpdateChallengeFBInfo = function (LeagueIndex) {
        $.post("MatchList/ChallengeMatch/ChallengeList.aspx", { "UpdateChallengeFBInfo": 1, "LeagueIndex": LeagueIndex }, function (ress) {
            //alert("a" + ress);
            if (parseInt(ress.split('|')[0]) < 0) {
                ShowMsg("补满失败", MatchList_Public_ok, null, null, null, null, 330, 200, $('#ChallengeDiv'), 'bf', false);
            }
            else {
                publicAddGSEL(1, 0 - parseInt(ress.split('|')[2]));
                $("#chpower").html(challengeObj.dobindpowerch(ress.split('|')[0]));
            }
        });
    }
    // 转到国家列表页执行的方法，初始化
    this.GoToChallenge = function () {
        $('#imgArrow').remove();
        Loading();
        $.post("MatchList/ChallengeMatch/ChallengeList.aspx", { "load": 1 }, function (ores) {
            challengeObj.ma = ores.split('@')[1];
            var res = ores.split('@')[0];
            RemoveLoading();
            var StrHtml = "<div style=\"width:909px;height:457px;background:url(" + ImgPath + "Images/ChallengeMatch/bg.png) no-repeat;position:absolute;left:4px;\">";
            StrHtml += "<div style='position:absolute;left:5px;top:5px;background:url(" + ImgPath + "Images/ChallengeMatch/bg.jpg);width:899px;height:446px;'></div>";
            StrHtml += "<div id=\"infoDivFlag\" style=\"position:absolute;display:none;text-align:left;z-index:11;\"></div>";
            StrHtml += "<img style=\"position:absolute;left:19px;top:16px;\" src='" + ImgPath + "Images/ChallengeMatch/name.png' />";
            if (res.indexOf('10|0') != -1) {
                res = res.replace('10|0', '10|1');
            }
            if (res.indexOf('0|0|0|0|0|0') != -1) {
                res = res.replace('0|0|0|0|0|0', '1|0|0|0|0|0');
            }
            var ress = res.split('|');
            var cloning = ores.split('@')[2];
            var cloning2 = ores.split('@')[3]; //卡福
            $.post('Default.aspx', { type: 3 }, function (result) {
                if (parseInt(result) == 1001) {
                    // 处在新手期，显示绿箭头
                    StrHtml += challengeObj.CreateFlagDiv(1, 297, 153, ress[0], 95, 48, MatchList_Challenge_FJ, 1);
                    StrHtml += challengeObj.CreateFlagDiv(1, 297, 153, ress[0], 95, 48, MatchList_Challenge_FJ, 1);
                    StrHtml += challengeObj.CreateFlagDiv(2, 468, 78, ress[1], 44, 38, MatchList_Challenge_DJ, 1);
                    StrHtml += challengeObj.CreateFlagDiv(3, 479, 231, ress[2], 87, 39, MatchList_Challenge_YJ, 1);
                    StrHtml += challengeObj.CreateFlagDiv(4, 290, 8, ress[3], 78, 58, MatchList_Challenge_YC, 1);
                    StrHtml += challengeObj.CreateFlagDiv(5, 218, 297, ress[4], 81, 32, MatchList_Challenge_XJ, 1);

                }
                else {
                    StrHtml += challengeObj.CreateFlagDiv(1, 297, 153, ress[0], 95, 48, MatchList_Challenge_FJ, 0);
                    StrHtml += challengeObj.CreateFlagDiv(1, 297, 153, ress[0], 95, 48, MatchList_Challenge_FJ, 0);
                    StrHtml += challengeObj.CreateFlagDiv(2, 468, 78, ress[1], 44, 38, MatchList_Challenge_DJ, 0);
                    StrHtml += challengeObj.CreateFlagDiv(3, 479, 231, ress[2], 87, 39, MatchList_Challenge_YJ, 0);
                    StrHtml += challengeObj.CreateFlagDiv(4, 290, 8, ress[3], 78, 58, MatchList_Challenge_YC, 0);
                    StrHtml += challengeObj.CreateFlagDiv(5, 218, 297, ress[4], 81, 32, MatchList_Challenge_XJ, 0);
                    if (ress[4] == "10") {
                        StrHtml += challengeObj.CreateFlagDiv(6, 475, 0, ress[5], 81, 32, MatchList_Challenge_qw5, 1);
                    }

                }
                StrHtml += challengeObj.createCloning(cloning);
                StrHtml += "<div style='position:absolute;z-index:10;left:12px;top:165px;background:url(" + ImgPath + "Images/ChallengeMatch/" + (cloning2 == "0" ? "copylight.png" : "copydark.png") + ") -312px top;width:78px;height:93px";
                if (cloning2 == "0") {
                    //开开了
                    StrHtml += ";cursor:pointer' onclick='challengeObj2.load(15)'";
                }
                StrHtml += ";'  onmouseover=\"challengeObj.showcopyFlag(4,95,155)\"  onmouseout=\"challengeObj.hideInfoFlag()\"><div style='position:absolute;left:0px;top:66px;width:78px;height:20px;text-align:center;line-height:27px;font-size:14px;font-weight:bold;font-style:italic;color:" + (cloning2 == "0" ? "#07206e" : "#252820") + "'>" + challengeObj.nameinfo1[4] + "</div></div>";
                StrHtml += "</div>";
                AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtml, null, null, null, 'ChallengeDiv', null, null, null);
                if (parseInt(result) == 1001) {
                    // 处在新手期，显示绿箭头
                    ShowArrow(168 + 241, 150, $("#ChallengeDiv"), 90, 100);
                }
            });
        });

    }
    this.copyInfo1 = new Array(MatchList_Challenge_FB_info15, MatchList_Challenge_FB_info16, MatchList_Challenge_FB_info17, MatchList_Challenge_FB_info18, "巴西后防悍将，一人当关万夫莫开");
    this.copyInfo2 = new Array(MatchList_Challenge_FB_info19, MatchList_Challenge_FB_info20, MatchList_Challenge_FB_info21, MatchList_Challenge_FB_info22, "袖标1级,袖标2级");
    this.copyInfo3 = new Array(MatchList_Challenge_FB_info23, MatchList_Challenge_FB_info24, MatchList_Challenge_FB_info25, MatchList_Challenge_FB_info26, "通关可签约超级巨星卡福");
    this.copyInfo4 = new Array(MatchList_Challenge_FB_info27, MatchList_Challenge_FB_info28, MatchList_Challenge_FB_info29, MatchList_Challenge_FB_info30, "等级达到60级,通关小贝副本,巅峰欧冠获得5星后开启");
    this.createCloning = function (cloneStr) {
        var strHtml = "";
        var eachClone = cloneStr.split("*");
        for (var i = 0; i < eachClone.length; i++) {
            var left = ((i % 2) * 79 + 12);
            var top = (parseInt((3 - i) / 2) * 93 + 259);
            strHtml += "<div style='position:absolute;z-index:10;left:" + left + "px;top:" + top + "px;background:url(" + ImgPath + "Images/ChallengeMatch/" + (eachClone[i] == "1" ? "copylight.png" : "copydark.png") + ") -" + (i * 78) + "px top;width:78px;height:93px";
            if (eachClone[i] == "1") {
                //开开了
                strHtml += ";cursor:pointer' onclick=\"challengeObj.GoToChallengePage('" + (i + 11) + "')\"";
            }

            strHtml += ";'  onmouseover=\"challengeObj.showcopyFlag('" + i + "','" + (left + 83) + "','" + (top - 10) + "')\"  onmouseout=\"challengeObj.hideInfoFlag()\"><div style='position:absolute;left:0px;top:66px;width:78px;height:20px;text-align:center;line-height:27px;font-size:14px;font-weight:bold;font-style:italic;color:" + (eachClone[i] == "1" ? "#07206e" : "#252820") + "'>" + challengeObj.nameinfo1[i] + "</div></div>";
        }
        return strHtml;
    }
    // 生成每一个国家的国旗，进度条等等html信息
    this.CreateFlagDiv = function (index, left, top, pro, huiLeft, huiTop, name, isnew) {
        var StrHtml = "";
        var eff = " onclick=\"challengeObj.GoToChallengePage('" + index + "')\"  onmouseover=\"challengeObj.showFlag('" + index + "','" + (left + huiLeft) + "','" + (top + huiTop) + "')\"  onmouseout=\"challengeObj.hideInfoFlag()\"";
        if (index <= 5) {
            if (pro == 0) {
                // 这一幕还没开始
                StrHtml += "<div style='cursor:pointer;position:absolute;width:20px;height:20px;left:" + (left + 5) + "px; top:" + (top + 7) + "px;'>";
                if (isnew != 1)
                    StrHtml += "<div style='cursor:pointer;position:absolute;left:" + huiLeft + "px;top:" + huiTop + "px;background:url(" + ImgPath + "Images/ChallengeMatch/Logo1.png) " + (-54 * (index - 1)) + "px top;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/Logo1.png) " + (-54 * (index - 1)) + "px top;width:54px;height:70px;'" + eff + "></div>";
                StrHtml += "<div style='cursor:pointer;position:absolute;left:" + (huiLeft - 32) + "px;top:" + (huiTop + 20) + "px;font-size:20px;color:black;width:25px;line-height:20px;font-style:italic;font-weight:bold' " + eff + ">" + name + "</div>";
                StrHtml += "</div>";
            }
            else if (pro > 0 && pro < 10) {
                // 这一幕到了一半
                StrHtml += "<div style='cursor:pointer;width:256px;height:232px;position:absolute;left:" + left + "px; top:" + top + "px;background:url(" + ImgPath + "Images/ChallengeMatch/" + index + ".png) no-repeat'>";
                StrHtml += "<div style='cursor:pointer;position:absolute;left:" + huiLeft + "px;top:" + huiTop + "px;background:url(" + ImgPath + "Images/ChallengeMatch/LogoBig1.png) " + (-74 * (index - 1)) + "px top;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/LogoBig1.png) " + (-74 * (index - 1)) + "px top;width:74px;height:92px;' " + eff + "></div>";
                StrHtml += "<div style='cursor:pointer;position:absolute;left:" + (huiLeft - 34) + "px;top:" + (huiTop + 20) + "px;font-size:25px;color:black;width:25px;line-height:25px;font-style:italic;font-weight:bold' " + eff + ">" + name + "</div>";
                StrHtml += "</div>";
            }
            else {
                // 已结束
                if (index == 1)
                    top += 2;
                if (index == 2)
                    left -= 1;
                if (index == 3) {
                    left += 1;
                    top += 1;
                }
                StrHtml += "<div style='cursor:pointer;position:absolute;width:20px;height:20px;left:" + (left + 5) + "px; top:" + (top + 7) + "px;'>";
                StrHtml += "<div style='cursor:pointer;position:absolute;left:" + (huiLeft - 32) + "px;top:" + (huiTop + 20) + "px;font-size:20px;color:black;width:25px;line-height:20px;font-style:italic;font-weight:bold' " + eff + ">" + name + "</div>";
                StrHtml += "<div style='cursor:pointer;position:absolute;left:" + (huiLeft - 18) + "px;top:" + (huiTop - 12) + "px;background:url(" + ImgPath + "Images/ChallengeMatch/win.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/win.png);width:93px;height:92px;'" + eff + "></div>";
                StrHtml += "</div>";
            }
            return StrHtml;
        }
        else {
            StrHtml += "<div style='position:absolute;left:825px;top:30px;background:url(" + ImgPath + "Images/ChallengeMatch/mbg6.png);width:78px;height:92px;cursor:pointer'" + eff + "><div style='color:#285512;font-weight:bold;position:absolute;width:79px;height:21px;text-align:center;left:0px;top:72px;font-style:italic;cursor:pointer'>" + MatchList_Challenge_qw5 + "</div></div>";
            return StrHtml;
        }
    }
    this.showFlag = function (index, left, top) {
        $("#infoDivFlag").css("display", "block").css("left", (parseInt(left) + 70) + "px").css("top", (top - 20) + "px").css("z-index", "20");
        var info = challengeObj.discribe[index - 1].split('|');
        var StrHtml = "<div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bgl.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bgl.png) no-repeat;width:200px;height:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_repeatl.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_repeatl.png) repeat-y;width:200px;height:135px;top:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_fengel.png) no-repeat;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_fengel.png) no-repeat;width:200px;height:2px;top:34px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bgl.png) left -8px;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bgl.png) left -8px no-repeat;width:200px;height:7px;top:142px;\"></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:8px;width:184px;'><font style='color:green'>" + MatchList_Challenge_FB_info31 + "：</font><font style='color:yellow'>" + info[0] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:28px;width:184px;'><font style='color:green'>" + MatchList_Challenge_FB_info32 + "：</font><font style='color:yellow'>" + info[1] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:68px;width:184px;'><font style='color:green'>" + MatchList_Challenge_FB_info33 + "：</font><font style='color:yellow'>" + info[2] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:88px;width:184px;'><font style='color:green'>" + MatchList_Challenge_FB_info34 + "：</font><font style='color:yellow'>" + info[3] + "</font></div>";
        $("#infoDivFlag").html(StrHtml);
    }
    this.showcopyFlag = function (index, left, top) {
        var StrHtml = "<div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bgl.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bgl.png) no-repeat;width:200px;height:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_repeatl.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_repeatl.png) repeat-y;width:200px;height:115px;top:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bgl.png) left -8px;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bgl.png) left -8px no-repeat;width:200px;height:7px;top:122px;\"></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:8px;width:184px;'><font style='color:yellow;font-size:15px;font-style:italic;font-weight:bolder'>" + challengeObj.copyInfo1[index] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:51px;width:184px;'><font style='color:green'>" + MatchList_Challenge_FB_info34 + "：</font><font style='color:yellow'>" + challengeObj.copyInfo2[index] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:68px;width:184px;'><font>　　　</font><font style='color:#eb3505'>" + challengeObj.copyInfo3[index] + "</font></div>";
        StrHtml += "<div style='position:absolute;left:8px;top:88px;width:184px;'><font style='color:green'>" + challengeObj.copyInfo4[index] + "</font></div>";
        $("#infoDivFlag").html(StrHtml).css("display", "block").css("left", left + "px").css("top", top + "px").css("z-index", "20");
    }

    // 跳到某一幕
    this.GoToChallengePage = function (type) {
        //this.load(type);
        if (type != 5) {
            this.load(type); // 不是第五幕
        }
        else {
            $.post('MatchList/ChallengeMatch/ChallengeList.aspx', { "hasSuper": 1 }, function (result) {
                // 第五幕需要提示创建未来之星
                if (result == "0") {
                    var StrHtml = "<div><div style=\"width:912px;height:460px;background:url(" + ImgPath + "Images/ChallengeMatch/n_bg1.png);position:absolute;left:7px;\">";
                    StrHtml += "<div style=\"position:absolute;left:3px;top:3px;width:909px;height:459px;background:url(" + ImgPath + "Images/ChallengeMatch/Manbg6.jpg);\"></div>";
                    var pin = challengeObj.ma.split('|');
                    StrHtml += CreatePlayer(pin[0], pin[1], pin[2], pin[3], pin[4], null, 792, 72, "s"); //综合能力,位置,头像名称,球员名字,球员等级,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID;
                    StrHtml += "<div style='font-size:68px;color:white;font-style:italic;position:absolute;left:475px;top:60px;font-weight:bold;width:280px'>" + MatchList_Challenge_WSCQ + "</div><div style='font-size:18px;color:white;font-style:italic;position:absolute;left:542px;top:148px;width:380px;font-weight:bold'>I Am Legend</div><div style='font-size:18px;color:#f5dc27;font-style:italic;position:absolute;left:542px;top:205px;width:480px;font-weight:bold'>" + MatchList_Challenge_JSQY + "</div><div style='font-size:18px;color:#f5dc27;font-style:italic;position:absolute;left:542px;top:240px;width:480px;font-weight:bold'>" + MatchList_Challenge_ZGPZ.replace(/\{0}/g, "</br>") + "</div>";
                    StrHtml += "<div id='comeinbut' style='position:absolute;left:507px;top:365px;width:313px;height:59px;font-weight:bold;overflow:hidden;text-align:center;font-size:36px;font-style:italic;color:white;background:url(" + ImgPath + "Images/ChallengeMatch/Mbutton.png);cursor:pointer' onmouseover='challengeObj.comein(1)' onmouseout='challengeObj.comein(0)' onclick='challengeObj.load(5);'>" + MatchList_Challenge_challengemsg21 + "</div>";
                    StrHtml += "</div>";
                    AddBox(84, 77, 925, 494, MatchList_Challenge_XHS, StrHtml, null, null, null, 'ChallengeDiv', null, null, null);
                }
                else {
                    challengeObj.load(type);
                }
            });
        }
    }
    this.comein = function (type) {
        if (type == 1) {
            $("#comeinbut").css("background", "url(" + ImgPath + "Images/ChallengeMatch/Mbutton.png) 0px -60px");
        }
        else {
            $("#comeinbut").css("background", "url(" + ImgPath + "Images/ChallengeMatch/Mbutton.png) 0px 0px");
        }
    }
    // 鼠标悬停到球队名上冒出的悬停框信息
    this.ChallengeShowClubInfo = function (i, clubname, vinafter, power, tactics, gift, chengli, zhuchang, chuohao, g1, g2, needHight, type, clubIndex, clubimg, kclubname, kclubid) {
        var StrHtml = "";
        var left = (parseInt(i) * 89 + 110);
        if (i >= 7) {
            left = (parseInt(i) * 89 - 120);
        }
        if (challengeObj.LeagueIndex >= 11)
            left += 317;
        $("#infoDiv").css("display", "none").css("left", left + "px").css("top", "120px").bind("mouseover", function () { $("#infoDiv").css("display", "none"); });
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bg.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bg.png);width:130px;height:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_repeat.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_repeat.png) repeat-y;width:130px;height:270px;top:7px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_fenge.png);_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_fenge.png) no-repeat;width:130px;height:2px;top:34px;\"></div>";
        StrHtml += "<div style=\"background:url(" + ImgPath + "Images/ChallengeMatch/ifo_bg.png) left -8px;_background:url(" + ImgPath + "IE6Images/ChallengeMatch/ifo_bg.png) left -8px no-repeat;width:130px;height:7px;top:277px;\"></div>";
        StrHtml += "<div style=\"font-size:14px; top: 7px; left:7px; font-weight:bold;color:Yellow\">" + clubname + "</div>";
        if (vinafter != "")
            StrHtml += "<div style=\" top:245px; left:7px;color:green\">" + vinafter + "</div>";
        if (kclubid != "-1") {
            // 被首杀了
            StrHtml += "<div style=\"font-size:12px; top: 35px; left:7px; color:Yellow\"><font style=' color:Green'>" + MatchList_Challenge_FB_info35 + "：</font>" + kclubname + "<br />";
        }
        else {
            StrHtml += "<div style=\"font-size:12px; top: 35px; left:7px; color:Yellow\"><font style=' color:Green'>" + MatchList_Challenge_CLNX + "：</font>" + chengli + "<br />";
        }

        StrHtml += "<font style=' color:green'>" + MatchList_Challenge_challengemsg22 + "：</font>" + zhuchang + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg23 + "：</font>" + chuohao + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg24 + "：</font>" + power + "<br /><font style='color:Green'>" + MatchList_Challenge_challengemsg25 + "：</font>" + g1 + "<br />　　　" + g2 + "</div>";
        var gifts = gift.split('@');
        var giftInfo = "";
        for (var j = gifts.length - 1; j >= 0; j--) {
            if (gifts[j] == "0") {
                //如果没道具  则不显示
            } else {
                var onegift = gifts[j].split('&');
                if (onegift[0] == 0) {
                    giftInfo += onegift[2];
                }
                else {
                    giftInfo += "<br />" + MatchList_Challenge_challengemsg26 + "：" + onegift[2];
                }
                if (j != 0) {
                    giftInfo += "、";
                }
            }
        }
        StrHtml += "<div style=\"font-size:12px; top: 145px; left:7px; color:Yellow;width:110px;\"><font style='color:green'>" + MatchList_Challenge_challengemsg27 + "：</font><br />" + giftInfo + "</div>";
        clearTimeout(challengeObj.showmsgInt);
        challengeObj.showmsgInt = setTimeout(function () { $("#infoDiv").html(StrHtml).css("display", "block"); }, 500);

        if (needHight == 1) {
            challengeObj.showHight(i, type); //
        }
    };
    this.showHight = function (i, type) {
        try {
            $("#challengeBut" + i.toString()).css("display", "block");
            $("#showh" + i.toString()).css("background", "url(" + ImgPath + "Images/ChallengeMatch/light" + type + ".jpg) -" + (parseInt(i * 89)) + "px 0px no-repeat;");
        }
        catch (e) { }
    }
    this.hideInfo = function (type, i) {
        try {
            clearTimeout(challengeObj.showmsgInt);
            $("#challengeBut" + i.toString()).css("display", "none");
            $("#showh" + i.toString()).css("background", "url(" + ImgPath + "Images/ChallengeMatch/dark" + type + ".jpg) -" + (parseInt(i * 89)) + "px 0px no-repeat;");
            $("#infoDiv").css("display", "none");
        }
        catch (e) { }
    };
    this.hideInfoFlag = function () {
        $("#infoDivFlag").css("display", "none");
    };
    this.canChallenge = 1;
    // 点击挑战，插入比赛
    this.insertMatch = function (homeClubIndex) {
        $("#imgArrow").remove();
        $("#ch").remove();
        if (challengeObj.canChallenge == 0) return;
        else {
            challengeObj.canChallenge = 0;
        }
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "insertMatch": homeClubIndex }, function (res) {

            if (res == -1) {
                OpenPayMsg();

                // ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#ch').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'ch', null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -2) {
                ShowMsg(MatchList_Challenge_challengemsg28, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -10) {
                ShowMsg(MatchList_Challenge_challengemsg29, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -12) {
                ShowMsg(MatchList_Challenge_challengemsg30, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -20) {
                ShowMsg(MatchList_Challenge_FB_info38, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -21) {
                ShowMsg(MatchList_Challenge_FB_info36, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else if (res == -999) {
                ShowMsg(MatchList_Challenge_FB_info37, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), null, null);
                challengeObj.canChallenge = 1;
            }
            else {
                var matchid = res.split('@')[0]; //matchID
                if (res.split('@')[1] != "") {
                    var priceInfo = res.split('@')[1].split('|'); //coin|count
                    if (priceInfo[0] == "coin") {
                        publicAddGSEL(1, -parseInt(priceInfo[1]));
                    } else if (priceInfo[0] == "gamecoin") {
                        if (parseInt(priceInfo[1]) != 0) {
                            if (priceInfo.length > 1)
                                publicAddGSEL(2, -parseInt(priceInfo[1]));
                        }
                    }
                }
                $("#ClallengeGoningOn").css("display", "block");
                setTimeout(function () { $("#ClallengeGoningOn").html("" + MatchList_Public_info7 + "..&nbsp;") }, 1000);
                setTimeout(function () { $("#ClallengeGoningOn").html("" + MatchList_Public_info7 + "...") }, 2000);
                // 拉出比赛引擎
                setTimeout(function () {
                    $("#ClallengeGoningOn").css("display", "none");
                    $("#ChallengeDiv").remove();
                    showMatch(matchid, 1);
                    challengeObj.canChallenge = 1;
                }, 3000);
            }
        });
    }
    this.showMatchEngine = function (matchID) { }


    // 翻牌相关方法
    this.cardover = function (cardindex) { $("#cardCard" + cardindex).stop().animate({ top: "115px" }, 150); }
    this.cardout = function (cardid) { cardid.stop().animate({ top: "125px" }, 150); }

    // 翻牌功能按键效果
    function seeh(cardid) { cardid.unbind(); cardid.css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left -75px").css("display", "block").css("color", "white"); }
    function remindh(cardid) { cardid.unbind(); cardid.css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left -75px").css("display", "block").css("color", "white"); }
    function retryh(cardid) { cardid.unbind(); cardid.css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left -75px").css("display", "block").css("color", "white"); }
    function btnout(cardid) {
        cardid.css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left top");
    }
    function btnover(cardid) {
        cardid.css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left -25px");
    }

    function unbindcardfun() {
        for (var i = 0; i < $(".card").length; i++) {
            $(".card")[i].onclick = null;
            $(".card")[i].onmouseover = null;
            $(".card")[i].onmouseout = null;
        }
    }
    // 选中某张牌，点击事件
    this.chose = function (cardindex) {
        if ($("#seediv").css("display") != "none") seeh($("#seediv"));
        if ($("#reminddiv").css("display") != "none") remindh($("#reminddiv"));
        if ($("#retrydiv").css("display") != "none") retryh($("#retrydiv"));
        $(".card")[cardindex].onclick = null;
        $(".card")[cardindex].onmouseover = null;
        $(".card")[cardindex].onmouseout = null;
        if (challengeObj.LeagueIndex >= 11) {
            var coinnum = 0;
            var base = 0;
            switch (parseInt(challengeObj.LeagueIndex)) {
                case 11: base = 5; break;
                case 12: base = 10; break;
                case 13: base = 15; break;
                case 14: base = 20; break;
                default: base = 0; break;
            }
            coinnum = base * (challengeObj.openedcardnum);
            challengeObj.publicneedcoin = coinnum;
            if (parseInt(coinnum) > 0) {
                ShowMsg(MatchList_Challenge_FB_info39.replace("{0}", coinnum), MatchList_Public_ok, function () { $("#blq").remove(); challengeObj.afterdoopen(cardindex); }, null, function () {

                    $(".card")[cardindex].onclick = function () { challengeObj.chose(cardindex); }
                    //$(".card")[cardindex].onmouseover = function () { challengeObj.cardover(cardindex.toString()); }
                    //$(".card")[cardindex].onmouseout = challengeObj.cardout($(".card")[cardindex]);
                    $("#blq").remove();

                }, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
            }
            else {
                challengeObj.afterdoopen(cardindex);
            }

        }
        else {
            challengeObj.afterdoopen(cardindex);
        }
    }
    this.publicneedcoin = 0;
    this.afterdoopen = function (cardindex) {
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "OpenCard": 1, "cardindex": cardindex }, function (result) {
            if (result == -1000) {
                if (challengeObj.LeagueIndex >= 11) {
                    OpenPayMsg();
                    $("#recievebut").css("display", "block"); // 领取
                    $("#recievebut").css("left", "430px");
                }
            }
            else {
                //3|1|2|大礼包|4|libao7.png|13002^1|4|1|2|934.png|帕克|4|33|18|934^2|1|2|传球(限时)|5|4109.png|4109^4|1|2|幸运符|2|8008.png|8008@1|0|0
                try {
                    var ress = result.split('@');
                    if (ress[0] == "retry") {
                        fan("||" + ress[1], cardindex, 1, 2);
                        if (challengeObj.LeagueIndex >= 11) {
                            $("#recievebut").css("display", "block"); // 领取
                            $("#recievebut").css("left", "430px");
                            challengeObj.openedcardnum++;
                            if (challengeObj.publicneedcoin != 0)
                                publicAddGSEL(1, 0 - challengeObj.publicneedcoin);
                            challengeObj.showFanInfo();
                        }
                    }
                    else {
                        showFanpai(ress[0], 0);
                        bindStep(ress[1]);
                        unbindcardfun();
                        if (challengeObj.publicneedcoin != 0)
                            publicAddGSEL(1, 0 - challengeObj.publicneedcoin);
                    }
                }
                catch (ex) { }
            }

        });
    }
    // 点击领取，重翻，洗牌等先判断金币数方法
    this.beforeLingqu = function (lod) {
        //        $("#recievebut")[0].onclick = null;
        //        $("#againbut")[0].onclick = null;
        //        $("#washbut")[0].onclick = null;
        if ($("blq")[0])
            return;
        if (lod == 2) {
            ShowMsg(MatchList_Challenge_challengemsg31.replace("{0}", "<font style='color:green'>").replace("{1}", "</font>"), MatchList_Public_ok, function () { lingqu(lod); }, null, null, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
        }
        else if (lod == 3) {
            ShowMsg(MatchList_Challenge_challengemsg32.replace("{0}", "<font style='color:green'>").replace("{1}", "</font>"), MatchList_Public_ok, function () { lingqu(lod); }, null, null, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
        }
        else {
            lingqu(lod);
        }
    }
    // 执行领取，重翻，洗牌等方法
    function lingqu(lod) {
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "getAward": 1, "lod": lod }, function (res) {
            var ress = res.split('|');
            var result = ress[0]; //状态
            var GameCoin = ress[1]; //银币
            if (result == "-10") {
                ShowMsg(MatchList_Challenge_challengemsg33, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (result == "-21") {
                OpenPayMsg();
                //ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#blq').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
            }
            else if (result == "-31") {
                OpenPayMsg();
                //ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#blq').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'blq', null);
            }
            else if (result == "-2") {
                ShowMsg('<div class="ShowMsgInfo" >' + MatchList_Challenge_challengemsg34 + '，<a class=\'aClick\' style=\'text-decoration:none;cursor:pointer\' ' + mActive + ' onclick=\'needbacktochallenge=1;GotoTraining();\'>' + MatchList_Challenge_challengemsg35 + '</a>!</div>', '关闭', null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (result == "-3") {
                ShowMsg(MatchList_Challenge_challengemsg36, MatchList_Public_ok, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (result == "-5") {
                ShowMsg('<div class="ShowMsgInfo" >' + MatchList_Challenge_challengemsg34 + '，<a class=\'aClick\' style=\'text-decoration:none;cursor:pointer\' ' + mActive + ' onclick=\'window.location.href="GotoTraining()"\'>' + MatchList_Challenge_challengemsg35 + '</a>!</div>', '关闭', null, null, null, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (result == "23") {
                ShowMsg(MatchList_Challenge_challengemsg37 + '，' + MatchList_Challenge_challengemsg38 + '！', MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else if (result == "33")
            { ShowMsg(MatchList_Challenge_challengemsg38 + '！', MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null); }
            else if (result == "32")
            { ShowMsg(MatchList_Challenge_challengemsg38 + '，' + MatchList_Challenge_challengemsg37 + '！', MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null); }
            else if (result == "41") {
                challengeObj.GoToChallengePage(challengeObj.LeagueIndex);
            }
            else if (result == "51") {
                challengeObj.GoToChallengePage(challengeObj.LeagueIndex);
            }
            else if (result == "2")
            { ShowMsg(MatchList_Challenge_challengemsg37 + '！', MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null); }
            else if (result == "3") {
                ShowMsg(MatchList_Challenge_challengemsg38 + '！', MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), 'a', null);
            }
            else {
                challengeObj.GoToChallengePage(challengeObj.LeagueIndex);
            }
            if (parseInt(result) > 0) {
                if (lod == 2) {
                    // 重翻扣金币动画
                    publicAddGSEL(1, -100);
                }
                else if (lod == 3) {
                    // 洗牌扣金币动画
                    publicAddGSEL(1, -200);
                }
            }
            if (GameCoin == "-99" || GameCoin == "") {
                //没有得到银币，什么也不做
            } else {
                if (ress.length > 1)
                    publicAddGSEL(2, parseInt(GameCoin));
            }
            $.post('Default.aspx', { type: 3 }, function (result) {
                if (parseInt(result) == 1001) {
                    // 处在新手期，确定按钮显示绿箭头
                    ShowArrow(439, 336, $("#ChallengeDiv"), 90, 30, 2);
                }
            });
        });
    }
    // 使用“提示一张”之前提示信息
    function befroeRemind(cardid) {
        ShowMsg('<div class="ShowMsgInfo" >' + MatchList_Challenge_challengemsg39.replace("{0}", "<font style='color:green'>").replace("{1}", "</font>") + '</div><div class=\"div_btn2\" style=\"position:absolute;left:105px;top:86px;\" onclick="$(\'#br\').remove();challengeObj.remindfun(\'' + cardid + '\');">' + MatchList_Public_ok + '</div><div class=\"div_btn2\" style=\"position:absolute;left:175px;top:86px;\" onclick=\'$("#br").remove()\'>' + MatchList_Challenge_challengemsg42 + '</div>', -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'br', null);
    }
    // 使用“偷看一张”之前提示信息
    function befroeSee(cardid) {
        ShowMsg('<div class="ShowMsgInfo" >' + MatchList_Challenge_challengemsg40.replace("{0}", "<font style='color:green'>").replace("{1}", "</font>") + '</div><div class=\"div_btn2\" style=\"position:absolute;left:105px;top:86px;\" onclick="$(\'#bs\').remove();challengeObj.seefun(\'' + cardid + '\');">' + MatchList_Public_ok + '</div><div class=\"div_btn2\" style=\"position:absolute;left:175px;top:86px;\" onclick=\'$("#bs").remove()\'>' + MatchList_Challenge_challengemsg42 + '</div>', -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'bs', null);
    }
    // 使用“翻两张” 之前提示信息
    function befroeRetry(cardid) {
        ShowMsg('<div class="ShowMsgInfo" >' + MatchList_Challenge_challengemsg41.replace("{0}", "<font style='color:green'>").replace("{1}", "</font>") + '</div><div class=\"div_btn2\" style=\"position:absolute;left:105px;top:86px;\" onclick="$(\'#bt\').remove();challengeObj.retryfun(\'' + cardid + '\');">' + MatchList_Public_ok + '</div><div class=\"div_btn2\" style=\"position:absolute;left:175px;top:86px;\" onclick=\'$("#bt").remove()\'>' + MatchList_Challenge_challengemsg42 + '</div>', -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'bt', null);
    }
    // 偷看一张牌
    this.seefun = function (cardid) {
        try {
            seeh($("#seediv"));
            $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "see": 1 }, function (result) {
                //-1 已经翻开一张牌了，所以不能偷看；-2 已经偷看过一张牌了；-4 已经选中一张牌了
                if (result == "-100") {
                    OpenPayMsg();
                    //ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#bs').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'bs', null);
                    $("#seediv").css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left top").bind("mouseover", function () { btnover($("#seediv")) }).bind("mouseout", function () { btnout($("#seediv")) }).bind("click", function () { befroeSee($("#seediv")) });
                }
                else if (result == "-1" || result == "-4") {
                    ShowMsg(MatchList_Challenge_challengemsg33, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);

                }
                else if (result == "-2") {
                    ShowMsg(MatchList_Challenge_challengemsg43, MatchList_Public_ok, function () { bindcardinfo(); }, null, function () { bindcardinfo(); }, null, 290, 175, $("#ChallengeDiv"), null, null);

                }
                else if (result != "") {
                    // 偷看成功
                    aftersee(result);
                    publicAddGSEL(1, -100);
                }
                else {
                    // 翻牌失败
                    ShowMsg(MatchList_Challenge_challengemsg44, MatchList_Challenge_challengemsg45, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
            });
        }
        catch (ex) {
            //seeh($("#seediv"));
            //ShowMsg('<div class="ShowMsgInfo" >翻牌失败！</div>', '重试', function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
        }
    }
    // 偷看之后效果
    function aftersee(onecard) {
        var oo = onecard.split('*');
        //var onecardinfo = onecard.split('|');
        //3|2|2|大礼包|4|libao7.png|13002
        var cardindex = oo[0];
        var result = oo[1].substring(oo[1].indexOf('|') + 1, oo[1].length - oo[1].indexOf('|'));
        result = result.substring(result.indexOf('|') + 1, result.length - result.indexOf('|'));
        $("#cardres" + cardindex).prev().animate({ width: "0px", left: "62px" }, 500, function () {
            $("#cardres" + cardindex).css("display", "block").css("background", "url('" + GetImgPath() + "/ChallengeMatch/p3.png')");
            $("#cardres" + cardindex).animate({ width: "0px" }, { queue: true, duration: 100 }).animate({ width: "127px" }, 10, function () {
                creategiftdiv(result, cardindex, 0);
                $("#cardres" + cardindex).animate({ width: "127px" }, { queue: true, duration: 1000 }).animate({ width: "0px", left: "62px" }, 500, function () { $("#cardres" + cardindex).css("display", "none").css("left", "0px"); $("#cardres" + cardindex).prev().animate({ width: "127px", left: "0px" }, 100, function () { }); });
            });
        });
    }
    // 提示一张之后效果
    this.afterremind = function (onecard) {
        var infos = onecard.split('|');
        var initLeft = (912 - ((parseInt(infos[1]) - 1) * 146 + 124)) / 2 + 16;
        var info = "";
        if (parseInt(infos[2]) == 1) {
            info = MatchList_Challenge_challengemsg46;
        } else if (parseInt(infos[2]) == 2) {
            info = MatchList_Challenge_challengemsg47;
        }
        else if (parseInt(infos[2]) == 3) {
            info = MatchList_Challenge_challengemsg48;
        }
        $("#cardInfodiv").css("display", "block").css("top", "165px").css("left", (initLeft + infos[0] * 146) + "px").html(info);
    }
    // 翻两张
    this.retryfun = function (cardid) {
        try {
            retryh($("#retrydiv"));
            $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "retry": 1 }, function (result) {
                //retryh($("#retrydiv"));
                if (result == "-1000") {
                    OpenPayMsg();
                    //ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#br').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'br', null);

                    $("#retrydiv").css("display", "block").css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left top").bind("mouseover", function () { btnover($("#retrydiv")) }).bind("mouseout", function () { btnout($("#retrydiv")) }).bind("click", function () { befroeRetry($("#retrydiv")) });
                }
                else if (result == "-2") {
                    ShowMsg(MatchList_Challenge_challengemsg49, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (result == "2") {
                    ShowMsg(MatchList_Challenge_challengemsg50, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (result == "-1") {
                    ShowMsg(MatchList_Challenge_challengemsg51, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (result == "1") {
                    publicAddGSEL(1, -300);
                    $("#canOpeninfo").html(MatchList_Challenge_challengemsg52.replace(/\{0}/g, "&nbsp;"));
                    //ShowMsg('您现在可以翻两张牌了！', '确定', function () { $("#t").remove(); }, null, function () { $("#t").remove(); }, null, 290, 175, $("#ChallengeDiv"), 't', null);

                }
                else { }

            });
        }
        catch (ex) {
            remindh($("#reminddiv"));
            ShowMsg(MatchList_Challenge_challengemsg44, MatchList_Challenge_challengemsg45, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
        }
    }
    // 提示一张
    this.remindfun = function (cardid) {
        try {
            remindh($("#reminddiv"));
            $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "remind": 1 }, function (result) {
                //remindh($("#reminddiv"));
                //-1 已经翻开一张牌了，所以不能偷看；-2 已经偷看过一张牌了；-4 已经选中一张牌了
                if (result == "-50") {
                    OpenPayMsg();
                    //ShowMsg("<div>您的金币不足，需要立即充值吗？</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:102px;\" onclick=\"gotoPay()\">充值</div><div class=\"div_btn2\" style=\"position:absolute;top:85px;left:175px;\" onclick=\"$('#bt').remove()\">取消</div>", -1, null, null, null, null, 290, 175, $("#ChallengeDiv"), 'bt', null);
                    $("#reminddiv").css("display", "block").css("background", "url(" + GetImgPath() + "/ChallengeMatch/btn.png) left top").bind("mouseover", function () { btnover($("#reminddiv")) }).bind("mouseout", function () { btnout($("#reminddiv")) }).bind("click", function () { befroeRemind($("#reminddiv")) });
                }
                else if (result == "-1" || result == "-4") {
                    ShowMsg(MatchList_Challenge_challengemsg33, MatchList_Public_ok, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (result == "-3") {
                    ShowMsg(MatchList_Challenge_challengemsg53, MatchList_Public_ok, function () { bindcardinfo(); }, null, function () { bindcardinfo(); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
                else if (result != "") {
                    // 偷看成功
                    challengeObj.afterremind(result);
                    publicAddGSEL(1, -50);
                    //parent.frames[1].AddAwardByType(0 - 50, 4);
                }
                else {
                    // 翻牌失败
                    ShowMsg(MatchList_Challenge_challengemsg44, MatchList_Challenge_challengemsg45, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
                }
            });
        }
        catch (ex) {
            remindh($("#reminddiv"));
            ShowMsg(MatchList_Challenge_challengemsg44, MatchList_Challenge_challengemsg45, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, function () { challengeObj.GoToChallengePage(challengeObj.LeagueIndex); }, null, 290, 175, $("#ChallengeDiv"), null, null);
        }
    }
    // 绑定一张牌上的信息
    function showFanpai(result, isrefresh) {
        if (result != "") {
            var ress = result.split('^');
            for (var i = 0; i < ress.length; i++) {
                var resss = ress[i].split('|');
                if (resss[1] == "4") {
                    // 我翻到的牌
                    fan(ress[i], i, 1, isrefresh);
                }
                else {
                    // 没翻到
                    fan(ress[i], i, 0, isrefresh);
                }
            }
            unbindcardfun();
            var buts = challengeObj.Buttuns[challengeObj.LeagueIndex].split('|');
            try {
                if (challengeObj.Buttuns[challengeObj.LeagueIndex] == "1|0|0") {
                    // 只有领取按钮，剧中显示
                    $("#recievebut").css("left", "430px");
                    $.post('Default.aspx', { type: 3 }, function (result) {
                        if (parseInt(result) == 1001) {
                            // 处在新手期，挑战按钮上方显示绿箭头
                            ShowArrow(39 + 403, 322, $("#ChallengeDiv"), 90, 30); ///////////////////////////////////////////////////
                        }
                    });
                }
                else if (challengeObj.Buttuns[challengeObj.LeagueIndex] == "1|1|0") {
                    $("#recievebut").css("left", "360px");
                    $("#againbut").css("left", "500px");
                }
                if (buts[0] == 1)
                    $("#recievebut").css("display", "block"); // 领取
                else
                    $("#recievebut").css("display", "none"); // 领取
                if (buts[1] == 1)
                    $("#againbut").css("display", "block"); // 重翻
                else {
                    $("#againbut").css("display", "none"); // 重翻
                }
                if (buts[2] == 1)
                    $("#washbut").css("display", "block"); // 洗牌
                else
                    $("#washbut").css("display", "none"); // 洗牌

            }
            catch (ex) { }
            $("#cardInfodiv").css("display", "none");
            BtnEffect();
        }
    }
    // 翻开牌的效果
    function fan(result, i, ismine, isrefresh) {
        var card = result.substring(result.indexOf('|') + 1, result.length - result.indexOf('|'));
        card = card.substring(card.indexOf('|') + 1, card.length - card.indexOf('|'));
        if (ismine == 1) {
            $("#cardres" + i).prev().animate({ width: "0px", left: "62px" }, 500, function () {
                $("#cardres" + i).css("display", "block").css("background", "url('" + GetImgPath() + "/ChallengeMatch/p2.png')");
                if (isrefresh == 1)
                    $("#cardres" + i).animate({ width: "0px", top: "0px" }, { queue: true, duration: 100 }).animate({ width: "127px", top: "-10px" }, 10, function () { creategiftdiv(card, i, 1); });
                else if (isrefresh == 2)// 翻两张时，翻得第一张效果
                    $("#cardres" + i).animate({ width: "0px" }, { queue: true, duration: 100 }).animate({ width: "127px", top: "0px" }, 10, function () {
                        creategiftdiv(card, i, 1);
                    });
                else
                    $("#cardres" + i).animate({ width: "0px" }, { queue: true, duration: 100 }).animate({ width: "127px" }, 10, function () { creategiftdiv(card, i, 1); });
            });
        }
        else {
            $("#cardres" + i).prev().animate({ width: "127px" }, { queue: true, duration: 400 }).animate({ width: "0px", left: "62px" }, 500, function () {
                $("#cardres" + i).css("display", "block").css("background", "url('" + GetImgPath() + "/ChallengeMatch/p3.png')");
                $("#cardres" + i).animate({ width: "0px" }, { queue: true, duration: 100 }).animate({ width: "127px", top: "0px" }, 10, function () { creategiftdiv(card, i, 0); });
            });
        };
    }
    this.openedcardnum = 0;
    function bindcardinfo() {
        $.post("MatchList/ChallengeMatch/Challenge1.aspx", { "initCards": 1, "LeagueIndex": challengeObj.LeagueIndex }, function (result) {
            if (result != "-1") {
                $("#awardCards").css("display", "block");
                var ress = result.split('*');
                var cardinfo = ress[0];
                var step = ress[1];
                var totalcardnum = ress[2];
                $("#cardspan").html(totalcardnum);
                var cards = cardinfo.split('^');
                var steps = step.split('|');
                if (steps[3] == 1 && cardinfo.indexOf('-1|-1') == -1) {
                    // 翻完了

                    createcard(cards, totalcardnum);
                    bindStep(step);
                    //alert(cardinfo);
                    showFanpai(cardinfo, 1);

                }
                else if (steps[3] == 1 && cardinfo.indexOf('-1|-1') != -1) {

                    // 翻两张，翻了一张后刷新页面
                    createcard(cards, totalcardnum);
                    var ress = cardinfo.split('^');
                    for (var i = 0; i < ress.length; i++) {
                        var resss = ress[i].split('|');
                        if (resss[1] == "4") {
                            challengeObj.openedcardnum++;
                            fan(ress[i], i, 1, 2);
                        }
                    }
                    bindStep(step);
                    if (challengeObj.LeagueIndex >= 11) {
                        $("#recievebut").css("display", "block"); // 领取
                        $("#recievebut").css("left", "430px");
                    }
                }
                else {
                    // 还没翻
                    createcard(cards, totalcardnum);
                    bindStep(step);
                }
            }
            else {
                // 没有翻盘信息
                $("#awardCards").css("display", "none");
            }

            $('#divLoading').css({ display: 'none' });
            challengeObj.showFanInfo();

        });
    }
    this.showFanInfo = function () {
        if (challengeObj.LeagueIndex >= 11) {
            var coinnum = 0;
            var base = 0;
            switch (parseInt(challengeObj.LeagueIndex)) {
                case 11: base = 5; break;
                case 12: base = 10; break;
                case 13: base = 15; break;
                case 14: base = 20; break;
                default: base = 0; break;
            }
            coinnum = base * (challengeObj.openedcardnum);
            if (coinnum == 0) {
                $("#canOpeninfo").html(MatchList_Challenge_FB_info40).css("display", "block");
            }
            else {
                $("#canOpeninfo").html(MatchList_Challenge_FB_info41.replace("{0}", coinnum)).css("display", "block");
            }
        }
    }
    // 牌上的人头，奖品等
    function createcard(cards, totalCardNum) {
        var cardshtml = "";
        var initLeft = (912 - ((totalCardNum - 1) * 146 + 124)) / 2;
        for (var i = 0; i < totalCardNum; i++) {
            cardshtml += "<div class=\"card\" id=\"cardCard" + i + "\" style=\"left:" + (initLeft + i * 146) + "px;\" onmouseover=\"challengeObj.cardover('" + i + "')\" onmouseout=\"challengeObj.cardout($(this))\" onclick=\"challengeObj.chose(" + i + ")\"><img class=\"cardbg\" src=\"" + GetImgPath() + "/ChallengeMatch/p1.png\"/><div  class=\"cardbg\" id=\"cardres" + (i) + "\" style=\"background:url('" + GetImgPath() + "/ChallengeMatch/p2.png'); width:0; display:none;\"></div></div>";
        }
        $("#cardsdiv").html(cardshtml);
    }


    function creategiftdiv(result, cardindex, info) {
        try {
            var ress = result.split('|');
            if (ress[0] == "1") {
                // 开出的事球员
                //"1|2|934.png|帕克|4|33|18|934"; //是否球员|球员位置|头像|名字|等级|综合实力|0|球员ID
                //综合能力,位置,头像名称,球员名字,球员等级,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID
                var playerhtml = CreatePlayer(ress[5], ress[1], ress[2], ress[3], ress[4], null, 0, 0, null);
                if (info == 1) {
                    $("#cardres" + cardindex).css("display", "block").css("width", "127px");
                    $("#cardres" + cardindex).html("<div style=\" position:absolute;left:0px; top:20px;width:127px;text-align:center; color:#d50000;font-size:15px;font-weight:bolder\">" + MatchList_Challenge_challengemsg54.replace("{0}", "<br />" + getQualityNameFlagByQuality(ress[4])) + "</div><div style=\"position:absolute;left:23px;top:72px;\">" + playerhtml + "</div>");
                }
                else {
                    $("#cardres" + cardindex).html("<div style=\" position:absolute;left:0px; top:30px;width:127px;text-align:center; color:black;font-size:15px;font-weight:bolder\">" + getQualityNameFlagByQuality(ress[4]) + MatchList_Challenge_challengemsg26 + "</div><div style=\"position:absolute;left:23px;top:72px;\">" + playerhtml + "</div>");
                }
            }
            else {
                var info = "";
                if (ress[3].indexOf("5001.png") != -1) {
                    info += ress[1] + ress[2] + MatchList_Challenge_challengemsg56;
                }
                else {
                    info += ress[1];
                }
                if (info == 1) {
                    $("#cardres" + cardindex).css("display", "block").css("width", "127px");

                    $("#cardres" + cardindex).html("<div style=\"position:absolute;left:0px; top:20px;width:127px;color:#d50000;font-size:15px;font-weight:bolder\">" + MatchList_Challenge_challengemsg57 + "<br />" + info + "</div><div style=\"position:absolute;left:24px;top:78px;width:82px;height:82px;\"><img style=\"position:absolute;left:6px;top:18px;\" src=\"" + ImgPath + "Images/Item/" + ress[3] + "\"/></div>");

                }
                else {
                    $("#cardres" + cardindex).html("<div style=\"position:absolute;left:0px; top:30px;width:127px;color:black;font-size:15px;font-weight:bolder\">" + info + "</div><div style=\"position:absolute;left:24px;top:78px;width:82px;height:82px;\"><img src=\"" + ImgPath + "Images/Item/" + ress[3] + "\" style=\"position:absolute;left:6px;top:18px;\"/></div>");
                }
            }
        }
        catch (ee) { }
    }

    function bindStep(step) {
        var steps = step.split('|');
        if (steps[3] == "0") {
            // 尚未翻牌
            if (steps[2] == "0") {
                // 翻两张可用
                $("#retrydiv").css("display", "block").bind("mouseover", function () { btnover($(this)) }).bind("mouseout", function () { btnout($(this)) }).bind("click", function () { befroeRetry($(this)) });
            }
            else if (steps[2] == "-1") {
                $("#seediv").css("left", "486px");
                $("#reminddiv").css("left", "346px");
                $("#retrydiv").css("display", "none");
            }
            else {
                $("#canOpeninfo").html(MatchList_Challenge_challengemsg52.replace(/\{0}/g, "&nbsp;"));
                retryh($("#retrydiv"));
            }
            if (steps[1] == "0") {
                // 偷看按钮可用
                $("#seediv").css("display", "block");

                $("#seediv").bind("mouseover", function () { btnover($(this)) }).bind("mouseout", function () { btnout($(this)) }).bind("click", function () { befroeSee($(this)) });

            }
            else if (steps[1] == "-1") {
                $("#seediv").css("display", "none");
            }
            else {
                seeh($("#seediv"));
            }
            if (steps[0] == "0") {
                // 提示一张可用
                $("#reminddiv").css("display", "block").bind("mouseover", function () { btnover($(this)) }).bind("mouseout", function () { btnout($(this)) }).bind("click", function () { befroeRemind($(this)) });
            }
            else if (steps[0] == "-1") {
                $("#reminddiv").css("display", "none");
            }
            else {
                remindh($("#reminddiv"));
            }

        }
        else {
            $("#canOpeninfo").html("");
            // 已经翻了牌了
            if (steps[1] == "-1")
                $("#seediv").css("display", "none");
            else
                seeh($("#seediv"));
            if (steps[0] == "-1")
                $("#reminddiv").css("display", "none");
            else
                remindh($("#reminddiv"));
            if (steps[2] == "-1") {
                $("#seediv").css("left", "486px");
                $("#reminddiv").css("left", "346px");
                $("#retrydiv").css("display", "none");
            }
            else {
                retryh($("#retrydiv"));
            }

        }
    }


}