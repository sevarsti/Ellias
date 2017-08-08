//欧冠联赛类
function ChampionCup3() {
    this.btn4 = "onmouseover='$(this).css({backgroundPosition:\"-79px 0\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})'";
    this.btnClub = "onmouseover='$(this).css({backgroundPosition:\"0 -65px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})'";
    this.btn4Css = "position:absolute;left:828px;top:425px;background:url(" + ImgPath + "images/ChampionCup3/button.png);width:79px;height:26px;line-height:26px;text-align:center;color:black;cursor:pointer;";
    this.pageIndex = 0;
    if (ChampionCup3.isAttach != 'true') {//对象产生时不重复复制方法代码
        this.LoadPage = function () {
            if ($('#divChampionCup')[0]) {
                $('#divChampionCup').remove();
                return;
            }
            objChampionCup3.ShowEntrance(); //参赛页
            //objChampionCup3.ShowGroupRank();//小组赛排名
            //objChampionCup3.ShowExplain(); //赛会说明
            //objChampionCup3.ShowBetting();//投注页
            //objChampionCup3.ShowShootRank();//射手榜
            //objChampionCup3.ShowGroupHome(-1);//小组赛首页
            //objChampionCup3.ShowAgainstPlan();//显示16强对阵图
            //objChampionCup3.ShowChampion(0,0,0,0,0); //显示冠军页面
        }
        //显示欧冠联赛入口---------------------------------------------------------------------------------------------------------------------------
        this.ShowEntrance = function () {
            $.post('ChampionCup3.aspx', { type: 0 }, function (result) {
                //result = "1*3|3|3";
                //alert(result);
                result = result.split('*');
                var userType = parseInt(result[0]); //玩家类型
                if (userType == -10) {
                    ShowMsg('周二00:00可进入', Default_Sure, null, null, null, null, 350, 220, null, 'divCup1', true);
                } else if (userType == -2 || userType == 1) {//观众 || 参赛者
                    var arr1 = result[1].split('|');
                    var fristState = parseInt(arr1[0]);
                    var secondState = parseInt(arr1[1]);
                    var thirdState = parseInt(arr1[2]);
                    //alert(fristState + "*" + secondState + "*" + thirdState);
                    var str1 = '', str2 = '', str3 = '', str4 = '', str5 = '', str6 = '';
                    //小组赛的情况
                    if (fristState == 0) {
                        str1 = "今日8:00出赛程";
                        str4 = "objChampionCup3.ShowMsg('今日8:00出赛程',null,$('#divShowEntrance'))";
                    } else if (fristState == 1) {
                        str1 = "小组赛于周三18:00开始";
                        str4 = "objChampionCup3.ShowGroupHome(-1,1)";
                    } else if (fristState == 2) {
                        str1 = "小组赛比赛进行中…";
                        str4 = "objChampionCup3.ShowGroupHome(-1,1)";
                    } else if (fristState == 3) {
                        str1 = "小组赛结束";
                        str4 = "objChampionCup3.ShowGroupRank()";
                    }
                    //淘汰赛情况
                    if (secondState == 0) {
                        str2 = "16强未产生";
                        str5 = "objChampionCup3.ShowMsg('16强未产生',null,$('#divShowEntrance'))";
                    } else if (secondState == 1) {
                        str2 = "淘汰赛周四19:00开始";
                        str5 = "objChampionCup3.ShowAgainstPlan()";
                    } else if (secondState >= 2 && secondState <= 6) {
                        if (secondState == 2)
                            str2 = "1/8决赛进行中…";
                        else if (secondState == 3)
                            str2 = "1/4决赛进行中…";
                        else if (secondState == 4)
                            str2 = "半决赛进行中…";
                        else if (secondState == 5)
                            str2 = "决赛进行中…";
                        else if (secondState == 6)
                            str2 = "世俱杯冠军产生！";
                        if (userType == 1)//参赛者
                            str5 = "objChampionCup3.ShowGroupHome(-1,0)";
                        else
                            str5 = "objChampionCup3.ShowAgainstPlan()";
                    }
                    //投票情况
                    if (thirdState == 0) {
                        str3 = "今日8:00开始竞猜";
                        str6 = "objChampionCup3.ShowMsg('今日8:00开始竞猜',null,$('#divShowEntrance'))";
                    } else if (thirdState >= 1 && thirdState <= 5) {
                        if (thirdState == 1)
                            str3 = "小组赛竞猜进行中…";
                        else if (thirdState == 2)
                            str3 = "今日21:00小组赛竞猜兑奖";
                        else if (thirdState == 3)
                            str3 = "淘汰赛竞猜进行中…";
                        else if (thirdState == 4)
                            str3 = "今日22:30淘汰赛竞猜兑奖";
                        else if (thirdState == 5)
                            str3 = "竞猜已结束";
                        str6 = "objChampionCup3.ShowBetting()";
                    } else {
                        str3 = "竞猜已结束";
                    }
                    //                    if (arr1[0] == '0') {//正在打小组赛
                    //                        str1 = ChampionCup_info_KFC_1.replace('{0}', (parseInt(arr1[1]) + 1));
                    //                        str4 = "objChampionCup3.ShowGroupHome(-1)";
                    //                        str2 = ChampionCup_info_KFC_2;
                    //                        str5 = "objChampionCup3.ShowMsg(ChampionCup_info_KFC_3,null,$('#divShowEntrance'))";
                    //                    } else if (arr1[0] == '3') {//16强开赛
                    //                        str1 = ChampionCup_info_KFC_4;
                    //                        if (userType == -2)
                    //                            str4 = "objChampionCup3.ShowGroupRank()";
                    //                        else
                    //                            str4 = "objChampionCup3.ShowGroupHome(-1)";
                    //                        str2 = objChampionCup3.GetRoundDes(parseInt(arr1[1]));
                    //                        str5 = "objChampionCup3.ShowAgainstPlan()";
                    //                    }
                    //                    if (arr1[2] == '-10') {//投票信息
                    //                        str3 = ChampionCup_info_KFC_5;
                    //                        str6 = "objChampionCup3.ShowMsg(ChampionCup_info_KFC_6,null,$('#divShowEntrance'))";
                    //                    } else if (arr1[2] == '-9') {
                    //                        str3 = ChampionCup_info_KFC_7;
                    //                        str6 = "objChampionCup3.ShowMsg(ChampionCup_info_KFC_7,null,$('#divShowEntrance'))";
                    //                    } else if (arr1[2] == '-8') {
                    //                        str3 = ChampionCup_info_KFC_8;
                    //                        str6 = "objChampionCup3.ShowBetting()";
                    //                    } else {
                    //                        str3 = ChampionCup_info_KFC_9.replace('{0}', arr1[2]);
                    //                        str6 = "objChampionCup3.ShowBetting()";
                    //                    }
                    var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/open.jpg);position:absolute;left:5px;top:0'></div><div style='left:334px;top:77px;' class='font1'>" + ChampionCup_info_KFC_10 + "</div><div class='font1' style='left:333px;top:75px;color:white'>" + ChampionCup_info_KFC_10 + "</div><div class='font2' style='left:411px;top:156px;'>Club World Cup</div><div class='font2' style='left:410px;top:155px;color:white'>Club World Cup</div><div class='font2' style='left:370px;top:175px;'><a style='color:red'>" + (userType == 1 ? "" : ChampionCup_info_KFC_11) + "</a></div><div onmouseover='$(this).css({backgroundPosition:\"0 -78px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' style='cursor:pointer;position:absolute;left:46px;top:202px;width:273px;height:78px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton.png);line-height:54px;font-size:24px;text-align:center;color:black;font-weight:bold;font-style:italic' onclick=" + str4 + ">" + ChampionCup_info_KFC_12 + "<div style='position:absolute;left:0;top:37px;width:100%;text-align:center;font-size:12px;font-weight:normal;color:white;cursor:pointer'>" + str1 + "</div></div><div onmouseover='$(this).css({backgroundPosition:\"0 -78px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' style='cursor:pointer;position:absolute;left:325px;top:202px;width:273px;height:78px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton.png);line-height:54px;font-size:24px;text-align:center;color:black;font-weight:bold;font-style:italic' onclick=" + str5 + ">" + ChampionCup_info_KFC_13 + "<div style='position:absolute;left:0;top:37px;width:100%;text-align:center;font-size:12px;font-weight:normal;color:white;cursor:pointer'>" + str2 + "</div></div><div onmouseover='$(this).css({backgroundPosition:\"0 -78px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' style='cursor:pointer;position:absolute;left:604px;top:202px;width:273px;height:78px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton.png);line-height:54px;font-size:24px;text-align:center;color:black;font-weight:bold;font-style:italic' onclick=" + str6 + ">" + ChampionCup_info_KFC_14 + "<div style='position:absolute;left:0;top:37px;width:100%;text-align:center;font-size:12px;font-weight:normal;color:white;cursor:pointer'>" + str3 + "</div></div><div style='left:330px;top:330px;font-size:18px;text-align:center' class='font1'>" + ChampionCup_info_KFC_15 + "</div><div class='font1' style='left:329px;top:329px;font-size:18px;text-align:center;color:white'>" + ChampionCup_info_KFC_15 + "</div><div style=" + objChampionCup3.btn4Css + " " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowExplain()'>" + ChampionCup_info_KFC_16 + "</div>";
                    //html += "<div style='" + objChampionCup3.btn4Css + "background:url(" + ImgPath + "images/ChampionCup3/buttonRed.png);left:826px;top:10px' " + objChampionCup3.btn4 + " onclick='GotoChampionCup1()'>欧联</div>";
                    AddBox(85, 77, 923, 493, '', html, null, null, null, 'divShowEntrance', false);
                    $('#divShowEntrance').attr('class', '').css({ overflow: 'visible', position: 'absolute' });
                }
                //                else {//参赛者
                //                    var arr1 = result[1].split('|');
                //                    var str1, str2, str3, str4 = '', str5 = '', str6 = '';
                //                    if (arr1[0] == '0') {//正在打小组赛
                //                        str1 = ChampionCup_info_KFC_1.replace('{0}', (parseInt(arr1[1]) + 1));
                //                        str4 = "objChampionCup3.ShowGroupHome(-1)";
                //                    } else if (arr1[0] == '3') {//16强开赛
                //                        str1 = objChampionCup3.GetRoundDes(parseInt(arr1[1]));
                //                        str4 = "objChampionCup3.ShowGroupHome(-1)";
                //                    }
                //                    var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/open.jpg);position:absolute;left:5px;top:0'></div><div style='left:331px;top:112px;' class='font1'>" + ChampionCup_info_KFC_17 + "</div><div class='font1' style='left:330px;top:110px;color:white'>" + ChampionCup_info_KFC_17 + "</div><div class='font2' style='left:371px;top:193px;'>UEFA CHAMPIONS LEAGUE</div><div class='font2' style='left:370px;top:192px;color:white'>UEFA CHAMPIONS LEAGUE</div><div onmouseover='$(this).css({backgroundPosition:\"0 -78px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' style='cursor:pointer;position:absolute;left:326px;top:266px;width:273px;height:78px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton.png);line-height:54px;font-size:24px;text-align:center;color:white;font-weight:bold;font-style:italic' onclick=" + str4 + ">" + ChampionCup_info_KFC_18 + "<div style='position:absolute;left:0;top:37px;width:100%;text-align:center;font-size:12px;font-weight:normal;color:white;cursor:pointer'>" + str1 + "</div></div><div style=" + objChampionCup3.btn4Css + " " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowExplain()'>" + ChampionCup_info_KFC_16 + "</div>";
                //                    html += "<div style='" + objChampionCup3.btn4Css + "background:url(" + ImgPath + "images/ChampionCup3/buttonRed.png);left:826px;top:10px' " + objChampionCup3.btn4 + " onclick='GotoChampionCup1()'>欧联</div>";
                //                    AddBox(85, 77, 923, 493, '', html, null, null, null, 'divShowEntrance', false);
                //                    $('#divShowEntrance').attr('class', '').css({ overflow: 'visible', position: 'absolute' });
                //                }
            });
        }
        //根据轮数返回比赛阶段的描述
        this.GetRoundDes = function (round) {
            if (round == 0)
                return ChampionCup_info_KFC_19;
            else if (round == 1)
                return ChampionCup_info_KFC_20;
            else if (round == 2)
                return ChampionCup_info_KFC_21;
            else if (round == 3)
                return ChampionCup_info_KFC_22;
            else
                return ChampionCup_info_KFC_22;
        }
        var scroller = null;
        var scrollbar = null;
        //显示赛会说明---------------------------------------------------------------------------------------------------------------------------
        this.ShowExplain = function () {
            var content = ChampionCup_info_KFC_23;
            var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/info.jpg);position:absolute;left:5px;top:0'></div><div style='" + objChampionCup3.btn4Css + "left:830px;top:82px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowEntrance()'>" + ChampionCup_info_KFC_24 + "</div>";
            html += "<div class='Container' style='width:890px;height:335px;position:absolute;left:27px;top:112px;'><div id='TaskInfoScroller' style='width:890px;height:335px;position:absolute;overflow:hidden;'><div class='Scroller-Container' style='line-height:20px;color:black;width:864px'>" + content + "</div></div></div><div id='TaskInfoScroller-Container' style='width:10px;position:absolute;height:335px;left:900px;top:112px;'><div class='Scrollbar-Up'></div><div class='Scrollbar-Track2' style='height:332px;'></div><div class='Scrollbar-Down' style='top:335px'></div><div class='Scrollbar-Track' style='height:338px;'><div class='Scrollbar-Handle'></div></div></div>";
            html += "<div style='position:absolute;left:424px;top:29px;font-size:12px;font-style:italic;color:#00137c'>Club World Cup</div><div style='position:absolute;left:423px;top:27px;font-size:12px;font-style:italic;color:white'>Club World Cup</div><div style='left:330px;top:48px;font-size:34px' class='font1'>" + ChampionCup_info_KFC_25 + "</div><div class='font1' style='left:329px;top:46px;color:white;font-size:34px'>" + ChampionCup_info_KFC_25 + "</div>";
            AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
            setTimeout(function () { scroller = new jsScroller(document.getElementById("TaskInfoScroller"), 890, 335); scrollbar = new jsScrollbar(document.getElementById("TaskInfoScroller-Container"), scroller, false); }, 200);
        }
        //显示小组排名---------------------------------------------------------------------------------------------------------------------------
        this.ShowGroupRank = function () {
            $.post('ChampionCup3.aspx', { type: 1 }, function (result) {
                var str1 = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/rank.jpg);position:absolute;left:5px;top:0'></div><div style='left:105px;top:38px;font-size:34px' class='font1'>" + ChampionCup_info_KFC_26 + "</div><div class='font1' style='left:104px;top:36px;color:white;font-size:34px'>" + ChampionCup_info_KFC_26 + "</div><div class='font2' style='left:103px;top:81px;font-size:7px;font-weight:bold'>Club World Cup</div><div class='font2' style='left:102px;top:80px;color:white;font-size:7px;font-weight:bold'>Club World Cup</div>";
                //8个表头
                var titleList = "";
                for (var v = 0; v < 8; v++) {
                    titleList += v == 0 ? "<div style='position:absolute;left:22px;top:110px'>" : v == 4 ? "<div style='position:absolute;left:22px;top:273px'>" : "";
                    titleList += "<div style='color:black;float:left;width:223px'><div style='font-weight:bold;font-size:17px;color:black;width:13px;float:left'>" + (v == 0 ? 'A' : v == 1 ? 'B' : v == 2 ? 'C' : v == 3 ? 'D' : v == 4 ? 'E' : v == 5 ? 'F' : v == 6 ? 'G' : 'H') + "</div><div style='float:left;padding-top:5px'><div style='float:left;width:22px;'>" + ChampionCup_info_KFC_27 + "</div><div style='float:left;width:104px'>" + ChampionCup_info_KFC_28 + "</div><div style='float:left;'>" + ChampionCup_info_KFC_29 + "</div></div></div>";
                    titleList += (v == 3 || v == 7) ? "</div>" : "";
                }
                html += titleList;
                //所有球队的信息
                var clubInfo = "";
                result = result.split('*');
                var arr1;
                for (var v = 0; v < result.length; v++) {
                    arr1 = result[v].split('|');
                    clubInfo += v == 0 ? "<div style='position:absolute;left:22px;top:138px'>" : v == 16 ? "<div style='position:absolute;left:22px;top:300px'>" : "";
                    if (v % 4 == 0)
                        clubInfo += "<div style='float:left;width:223px;height:120px'>";
                    clubInfo += "<div style='color:#3e499b;float:left;width:223px;height:32px'><img src='" + ImgPath + "images/UserAvatar/" + arr1[9] + "' style='float:left;height:26px;width:26px;margin:1px 2px 0 0;display:inline' /><div style='width:105px;float:left;line-height:28px;color:#dfe2e7;'><a>" + (arr1[0] != '' ? "[" + arr1[0] + "]" : '') + "</a><a style='cursor:pointer;" + (arr1[8] == '1' ? 'color:#df450f;' : '') + "' " + mActive + " onclick='objTeam2.ShowClub2(" + parseInt(arr1[2]) + ")'>" + arr1[3] + "</a></div><div style='float:left;width:20px;text-align:center;line-height:28px;color:#3673e6'>" + arr1[4] + "</div><div style='float:left;width:20px;text-align:center;line-height:28px;color:#3673e6'>" + arr1[5] + "</div><div style='float:left;width:20px;text-align:center;line-height:28px;color:#3673e6'>" + arr1[6] + "</div><div style='float:left;width:20px;text-align:center;line-height:28px;color:#feffff'>" + arr1[7] + "</div></div>";
                    if (v % 4 == 3)
                        clubInfo += "</div>";
                    clubInfo += (v == 15 || v == 31) ? "</div>" : "";
                }
                html += clubInfo;
                html += "<div style='" + objChampionCup3.btn4Css + "left:749px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowAgainstPlan()'>" + ChampionCup_info_KFC_30 + "</div><div style='" + objChampionCup3.btn4Css + "left:830px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowGroupHome(-1,1)'>" + ChampionCup_info_KFC_31 + "</div>";
                AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
            });
        }
        //显示16强投注站---------------------------------------------------------------------------------------------------------------------------
        this.ShowBetting = function () {
            $.post('ChampionCup3.aspx', { type: 2 }, function (result) {
                result = result.split('@');
                var arr1 = result[0].split('|'), arr2 = result[1].split('*'), arr3;
                var isGroup = arr1[3] == '1' ? true : false;
                var maxBett = isGroup ? 1000 : 5000;
                var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/groupstage.jpg);position:absolute;left:5px;top:0'></div><div class='font2' style='left:425px;top:21px;font-size:11px;font-weight:bold'>Club World Cup</div><div class='font2' style='left:424px;top:20px;color:white;font-size:11px;font-weight:bold'>Club World Cup</div><div style='left:210px;top:40px;font-size:34px;text-align:center;width:500px' class='font1'>" + (isGroup ? ChampionCup_info_KFC_33 : ChampionCup_info_KFC_32) + "</div><div class='font1' style='left:209px;top:39px;color:white;font-size:34px;text-align:center;width:500px'>" + (isGroup ? ChampionCup_info_KFC_33 : ChampionCup_info_KFC_32) + "</div>";
                html += "<div style='" + objChampionCup3.btn4Css + "left:749px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowAgainstPlan()'>" + ChampionCup_info_KFC_34 + "</div><div style='" + objChampionCup3.btn4Css + "left:830px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowGroupRank()'>" + ChampionCup_info_KFC_35 + "</div>";
                var bettTip1 = "";
                if (arr1[4] == '0')
                    bettTip1 = "<div style='float:left'>小组赛竞猜已结束，将在21:00兑奖</div>";
                else if (arr1[4] == '1')
                    bettTip1 = "<div style='float:left'>淘汰赛竞猜已结束，将在22:30兑奖</div>";
                else if (arr1[4] == '2')
                    bettTip1 = "<div style='float:left'>淘汰赛竞猜已结束</div>";
                else
                    bettTip1 = "<div style='float:left'>" + ChampionCup_info_KFC_36.replace('{0}', "<a id='aMyBett'>" + arr1[0] + "</a>").replace('{1}', arr1[1]) + "</div>";
                html += "<div style='font-weight:bold;color:black;font-size:18px;position:absolute;left:27px;top:84px'>" + bettTip1 + "<div style='" + objChampionCup3.btn4Css + "position:static;float:left;font-size:12px;display:inline;margin-left:4px' " + objChampionCup3.btn4 + " onclick='guessobj.gettip($(\"#divChampionCup\"),300)'>球票兑换</div></div>";
                $('#aTickets').html(arr1[0]);
                var clubInfo = ""; //"<div style='position:absolute;left:12px;top:124px'>";
                var mCss = '', isLost, myBett = '', btnEvent = "onmouseover='objChampionCup3.BettingOver($(this))' onmouseout='objChampionCup3.BettingOut($(this))'";
                if (arr1[2] == '-8')
                    btnEvent = "";
                for (var v = 0; v < arr2.length; v++) {
                    arr3 = arr2[v].split('|');
                    isLost = parseInt(arr3[8]) == -1 ? true : false;
                    mCss = (parseInt(arr3[7]) >= 3 || isLost) ? '' : 'cursor:pointer';
                    myBett = parseInt(arr3[7]) >= maxBett ? '(' + ChampionCup_info_KFC_37 + ')' : '';
                    if ((parseInt(arr3[7]) * parseInt(arr3[9]) > 0)) {
                        myBett = " <img src='" + ImgPath + "images/Item/10070s.png' style='width:15px;height:15px' />×" + parseInt(arr3[7]) * parseInt(arr3[9]);
                    }
                    myBett = "";
                    if (arr2.length > 16) {
                        if (v == 0) {
                            clubInfo += "<div style='height:20px;width:890px;padding-top:5px'><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>A组</div><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>B组</div></div>";
                        } else if (v == 8) {
                            clubInfo += "<div style='height:20px;width:890px;'><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>C组</div><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>D组</div></div>";
                        } else if (v == 16) {
                            clubInfo += "<div style='height:20px;width:890px;'><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>E组</div><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>F组</div></div>";
                        } else if (v == 24) {
                            clubInfo += "<div style='height:20px;width:890px;'><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>G组</div><div style='float:left;width:443px;height:20px;background:url(" + ImgPath + "images/ChampionCup3/hr.png) 9px 7px no-repeat;text-align:center;font-size:16px;color:white;line-height:16px;font-weight:bold'>H组</div></div>";
                        }
                    }
                    clubInfo += "<div style='" + (isLost ? 'background:url(' + ImgPath + 'images/ChampionCup3/gray.png) no-repeat;' : 'background:url(' + ImgPath + 'images/ChampionCup3/touzhubg.png) no-repeat;') + "float:left;width:103px;height:172px;display:inline;margin:0 0 0 8px;" + mCss + "' " + ((parseInt(arr3[7]) >= maxBett || isLost) ? "" : btnEvent) + "><div style='width:103px;line-height:25px;text-align:center;height:22px;" + mCss + "'><a style='color:black'>" + (arr3[0] != '' ? "[" + arr3[0] + "]" : '') + "</a><a style='cursor:pointer;color:" + (isLost ? 'yellow' : 'black') + ";' " + mActive + " onclick='objTeam2.ShowClub2(" + parseInt(arr3[2]) + ")'>" + arr3[3] + "</a></div><div style='width:64px;height:64px;border:solid 1px #030a4e;background:url(" + ImgPath + "images/UserAvatar/" + arr3[10] + ");margin:0 0 0 17px;cursor:pointer;" + mCss + "' onclick='objTeam2.ShowClub2(" + parseInt(arr3[2]) + ")'></div><div style='text-align:center;line-height:21px;color:" + (isLost ? 'yellow' : 'black') + ";" + mCss + "'>lv." + arr3[4] + "&nbsp;&nbsp;&nbsp;" + arr3[5] + "</div><div style='text-align:center;line-height:25px;height:22px;color:black;" + mCss + "'>" + ChampionCup_info_KFC_38 + "<a class='a_totalBetting'>" + arr3[6] + "</a>" + ChampionCup_info_KFC_39 + "</div><div style='text-align:center;line-height:30px;color:yellow;" + mCss + "' class='myBetting'>" + (parseInt(arr3[7]) > 0 ? ChampionCup_info_KFC_40 + "<a class='a_myBetting' style='font-size:12px;font-weight:bold'>" + arr3[7] + "</a>" + ChampionCup_info_KFC_39 : "") + myBett + "</div><div style='" + objChampionCup3.btn4Css + "display:none;margin:4px 0 0 10px;position:static' " + objChampionCup3.btn4 + " onclick='objChampionCup3.RdBetting($(this).parent()," + parseInt(arr3[2]) + "," + maxBett + ")' class='btnC'>" + ChampionCup_info_KFC_41 + "</div></div>";
                }
                clubInfo += ""; //"</div>";
                html += "<div class='Container' style='width:900px;height:330px;position:absolute;left:12px;top:124px;'><div id='TaskInfoScroller' style='width:900px;height:328px;position:absolute;overflow:hidden;'><div class='Scroller-Container' style='line-height:20px;color:black;width:900px'>" + clubInfo + "</div></div></div><div id='TaskInfoScroller-Container' style='width:10px;position:absolute;height:315px;left:904px;top:125px;display:none'><div class='Scrollbar-Up'></div><div class='Scrollbar-Track2' style='height:312px;'></div><div class='Scrollbar-Down' style='top:315px'></div><div class='Scrollbar-Track' style='height:318px;'><div class='Scrollbar-Handle'></div></div></div>";
                AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
                if (arr2.length > 16)
                    $('#TaskInfoScroller-Container').css({ display: 'block' });
                setTimeout(function () { scroller = new jsScroller(document.getElementById("TaskInfoScroller"), 890, 324); scrollbar = new jsScrollbar(document.getElementById("TaskInfoScroller-Container"), scroller, false); }, 200);
            });
        }
        this.BettingOver = function (theBetting) {
            theBetting.find('.myBetting').css({ display: 'none' });
            if (theBetting.find('.a_myBetting')[0]) {
                theBetting.find('.btnC').html("追加");
            } else {
                theBetting.find('.btnC').html("下注");
            }
            theBetting.find('.btnC').css({ display: 'block' });
        }
        this.BettingOut = function (theBetting) {
            theBetting.find('.myBetting').css({ display: 'block' });
            theBetting.find('.btnC').css({ display: 'none' });
        }
        //准备下注
        this.RdBetting = function (theBetting, starClubID, maxBett) {
            var isZJia = false;
            if (theBetting.find('.a_myBetting')[0]) {
                isZJia = true;
            }
            var myBet = theBetting.find('.a_myBetting')[0] ? parseInt(theBetting.find('.a_myBetting').html()) : 0;
            ShowMsg("<div style='position:absolute;left:0;top:8px;width:342px;text-algin:center;line-height:26px'>共有球票" + $('#aMyBett').html() + "张" + (isZJia ? ",已投注" + myBet + "张<br>追加数" : "<br>投注数") + "</div><input id='txtBett' type='text' style='position:absolute;left:114px;top:70px;width:110px;height:23px;border:solid 1px #195201' onkeyup='objChampionCup3.PressInfo($(this)," + maxBett + "," + myBet + ")' />", isZJia ? '追加' : '投注', function () { objChampionCup3.Betting(theBetting, starClubID, $('#txtBett').val()); }, null, null, 200, 290, 160, $('#divChampionCup'), 'divRdBetting', true);
            $('#txtBett').focus();
        }
        this.PressInfo = function (theText, maxBett, myBet) {
            MoveStr(theText);
            if (theText.val() == '')
                return;
            var x = parseInt(theText.val());
            if (x > maxBett - myBet)
                x = maxBett - myBet;
            theText.val(x);
        }
        //下注
        this.Betting = function (theBetting, starClubID, bettCount) {
            if (bettCount < 1) {
                objChampionCup3.ShowMsg('投票数不能小于1票');
                return;
            }
            $.post('ChampionCup3.aspx', { type: 3, starClubID: starClubID, bettCount: bettCount }, function (result) {
                if (result == '1')
                    objChampionCup3.ShowBetting();
                else if (result == '-1')
                    objChampionCup3.ShowMsg(ChampionCup_info_KFC_42);
                else if (result == '-2')
                    objChampionCup3.ShowMsg(ChampionCup_info_KFC_43);
                else if (result == '-3')
                    objChampionCup3.ShowMsg(ChampionCup_info_KFC_44);
                else if (result == '-5')
                    objChampionCup3.ShowMsg('对一只球队的投票数不能大于5000');
                else if (result == '-6')
                    objChampionCup3.ShowMsg('第一次投票数不能低于10票');
                else
                    objChampionCup3.ShowMsg(ChampionCup_info_KFC_45);
            });
        }
        //显示射手榜---------------------------------------------------------------------------------------------------------------------------
        this.ShowShootRank = function () {
            $.post('ChampionCup3.aspx', { type: 4 }, function (result) {
                var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/showall.jpg);position:absolute;left:5px;top:0'></div><div class='font2' style='left:385px;top:28px;font-size:11px;font-weight:bold'>UEFA CHAMPIONS LEAGUE</div><div class='font2' style='left:384px;top:27px;color:white;font-size:11px;font-weight:bold'>UEFA CHAMPIONS LEAGUE</div><div style='left:371px;top:47px;font-size:34px' class='font1'>" + ChampionCup_info_KFC_46 + "</div><div class='font1' style='left:370px;top:46px;color:white;font-size:34px'>" + ChampionCup_info_KFC_46 + "</div>";
                html += "<div style='position:absolute;left:67px;top:124px'><div style='float:left;color:white;width:66px'>" + ChampionCup_info_KFC_47 + "</div><div style='float:left;color:white;width:111px'>" + ChampionCup_info_KFC_48 + "</div><div style='float:left;color:white;width:101px'>" + ChampionCup_info_KFC_49 + "</div><div style='float:left;color:white;width:116px'>" + ChampionCup_info_KFC_50 + "</div><div style='float:left;color:white;width:119px'>" + ChampionCup_info_KFC_51 + "</div><div style='float:left;color:white;width:122px'>" + ChampionCup_info_KFC_52 + "</div><div style='float:left;color:white;width:125px'>" + ChampionCup_info_KFC_53 + "</div><div style='float:left;color:white;width:66px'>" + ChampionCup_info_KFC_54 + "</div></div>";
                var rankInfo = "<div style='position:absolute;left:66px;top:147px;width:836px;height:304px;'>";
                if (result == "") {
                    rankInfo += "<div style='width:820px;height:30px;line-height:30px;text-align:center;color:white'>" + ChampionCup_info_KFC_55 + "</div>";
                } else {
                    html += "<img src='" + ImgPath + "images/ChampionCup3/golden.png' style='position:absolute;left:22px;top:148px' />";
                    result = result.split('*');
                    var arr1;
                    for (var v = 0; v < result.length; v++) {
                        arr1 = result[v].split('|');
                        rankInfo += "<div style='float:left;width:836px;color:white'><div style='float:left;width:25px;line-height:30px;text-align:center;'>" + (v + 1) + "</div><div style='float:left;width:120px;line-height:29px;text-align:left;display:inline;padding-left:25px'><a style='cursor:pointer' " + mActive + " onclick='ShowPlayer(" + parseInt(arr1[0]) + ")'>" + arr1[1] + "</a><a>lv." + arr1[2] + "</a></div><div style='float:left;width:64px;line-height:29px;text-align:center;padding-top:7px'>" + getPlayerFlagByQuality(parseInt(arr1[3])) + "</div><div style='float:left;width:64px;line-height:29px;text-align:center;padding-left:36px'>" + arr1[4] + "</div><div style='float:left;width:168px;line-height:29px;text-align:center;cursor:pointer' " + mActive + " onclick='objTeam2.ShowClub2(" + parseInt(arr1[5]) + ")'>" + arr1[6] + "</div><div style='float:left;width:94px;line-height:29px;text-align:center;'>" + arr1[7] + "</div><div style='float:left;width:154px;line-height:29px;text-align:center;'>" + arr1[8] + "</div><div style='float:left;width:70px;line-height:29px;text-align:center;'>" + arr1[9] + "</div></div>";
                    }
                }
                rankInfo += "</div>";
                html += rankInfo;
                AddBox(85, 77, 923, 493, '', html, $('#totalMainDiv'), null, null, 'divShowShootRank', false);
            });
        }
        //小组赛+淘汰赛---------------------------------------------------------------------------------------------------------------------------
        //小组赛首页
        var scroller, scrollbar;
        this.publicMatchType = 0;
        this.ShowGroupHome = function (groupIndex, isGroup) {
            objChampionCup3.pageIndex = 0;
            isGroup = (isGroup == null || isGroup == 0) ? 0 : 1;
            objChampionCup3.publicMatchType = isGroup;
            //哪个阶段|第几级别|组|状态提示被淘汰了或者晋级或者总冠军|16强的开赛时间@matchID|状态|比分|比赛时间|主场ID|主场球队名|主场球队等级|主场球队实力|0|客场ID|客场球队名|客场球队等级|客场球队实力|0&老邢 2球|刘 2球*大熊 0球|侯 0球^
            $.post('ChampionCup3.aspx', { type: 5, groupIndex: groupIndex, isGroup: isGroup }, function (result) {
                result = result.split('@');
                var arr1 = result[0].split('|'), arr2 = result[1].split('^'), arr3, arr4;
                var rounds = parseInt(arr1[1]) + 1;
                var roundsDes = rounds == 1 ? ChampionCup_info_KFC_56 : rounds == 2 ? ChampionCup_info_KFC_57 : rounds == 3 ? ChampionCup_info_KFC_21 : ChampionCup_info_KFC_22;
                var title = "", str1 = '';
                if (parseInt(arr1[0]) == 0) {
                    rounds = rounds < 1 ? 1 : rounds;
                    title = ChampionCup_info_KFC_58.replace('{0}', GetEgl(parseInt(arr1[2]))).replace('{1}', rounds);
                    str1 = "<div id='divBtnName' style='" + objChampionCup3.btn4Css + "left:749px;top:80px;background:url(" + ImgPath + "images/ChampionCup3/listbutton.png);text-align:left;padding-left:18px;width:61px' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowGroupList()'>" + GetEgl(parseInt(arr1[2])) + ChampionCup_info_KFC_59 + "</div>";
                }
                else if (parseInt(arr1[0]) == 3) {
                    title = ChampionCup_info_KFC_60 + " " + roundsDes;
                    str1 = "<div style='" + objChampionCup3.btn4Css + "left:749px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowBetting()'>" + ChampionCup_info_KFC_14 + "</div>";
                }
                var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/groupstage.jpg);position:absolute;left:5px;top:0'></div><div class='font2' style='left:425px;top:28px;font-size:11px;font-weight:bold'>Club World Cup</div><div class='font2' style='left:424px;top:27px;color:white;font-size:11px;font-weight:bold'>Club World Cup</div><div style='width:450px;left:261px;top:47px;font-size:34px;text-align:center' class='font1'>" + title + "</div><div class='font1' style='width:450px;left:260px;top:46px;color:white;font-size:34px;text-align:center'>" + title + "</div>" + str1 + "<div style='" + objChampionCup3.btn4Css + "left:830px;top:80px;' " + objChampionCup3.btn4 + " onclick='objChampionCup3.ShowGroupRank()'>" + ChampionCup_info_KFC_62 + "</div><div id='divBtnList' style='width:79px;height:168px;background:url(" + ImgPath + "images/ChampionCup3/listbg.png);position:absolute;left:750px;top:105px;border-bottom:solid 1px #001227;z-index:1;display:none'>" + objChampionCup3.BindCroupList() + "<div style='position:absolute;left:0;top:168px;width:79px;height:3px;background:url(" + ImgPath + "images/ChampionCup3/bottom.png)'></div></div>";
                var groupInfo = "<div id='divCroup' style='position:absolute;left:43px;top:123px;width:874px;height:327px;'>";
                var scrollerInfo = "";
                var x = 0;
                if (result[1].split('*')[0].split('|').length > 5) {
                    for (var v = 0; v < arr2.length; v++) {
                        arr3 = arr2[v].split('&');
                        arr4 = arr3[0].split('|');
                        if (v % 2 == 0)
                            x++;
                        if (v % 2 == 0 || parseInt(arr1[0]) == 3) {
                            var tip2 = parseInt(arr1[0]) == 3 ? ChampionCup_info_KFC_63.replace('{0}', (v + 1)) : ChampionCup_info_KFC_64.replace('{0}', x);
                            scrollerInfo += "<div style='float:left;color:black;font-weight:bold;font-size:14px;width:840px;font-style:italic;text-align:center;height:24px;padding-top:20px;'>" + tip2 + " " + arr4[3] + "</div>" + objChampionCup3.CreateClubDiv(parseInt(arr4[0]), arr4[1], arr4[2], arr4[4], arr4[5], arr4[6], arr4[7], arr4[8], arr4[9], arr4[10], arr4[11], arr4[12], arr4[13], arr3[1]);
                        } else {
                            scrollerInfo += objChampionCup3.CreateClubDiv(parseInt(arr4[0]), arr4[1], arr4[2], arr4[4], arr4[5], arr4[6], arr4[7], arr4[8], arr4[9], arr4[10], arr4[11], arr4[12], arr4[13], arr3[1]);
                        }
                    }
                }
                if (arr1[3] == '-1') {//小组赛或16强被淘汰
                    var tip1 = '', t = 0;
                    if (arr1[0] == 0) {
                        tip1 = ChampionCup_info_KFC_65;
                    }
                    else if (arr1[0] == 3) {
                        if (rounds == 4)
                            tip1 = ChampionCup_info_KFC_66;
                        else
                            tip1 = ChampionCup_info_KFC_67.replace('{0}', roundsDes);
                    }
                    scrollerInfo += "<div style='font-size:31px;width:830px;text-align:center;color:white;float:left;color:black;position:static;padding-top:4px' class='font1'>" + tip1 + "</div><div style='width:193px;height:46px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton2.png);text-align:center;line-height:43px;font-size:22px;color:black;cursor:pointer;float:left;margin:6px 0 0 320px;display:inline;font-style:italic;font-weight:bold' onmouseover='$(this).css({backgroundPosition:\"0 -44px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' onclick='objChampionCup3.ShowAgainstPlan()'>" + ChampionCup_info_KFC_68 + "</div><div style='float:left;height:16px;width:830px;'></div>";
                    //<div style='position:absolute;left:370px;top:390px;width:187px;height:43px;background:green;text-align:center;line-height:43px;font-size:22px;color:yellow;cursor:pointer'>淘汰赛对阵图</div>
                } else if (arr1[3] == '2') {//晋级16强
                    var tip1 = '';
                    if (arr1[0] == 0) {
                        tip1 = ChampionCup_info_KFC_69;
                        scrollerInfo += "<div style='font-size:31px;width:830px;text-align:center;float:left;color:yellow;position:static;padding-top:30px' class='font1'>" + tip1 + "</div><div style='font-size:13px;width:820px;text-align:center;color:white;float:left;position:static;padding-top:0px' class='font1'>" + ChampionCup_info_KFC_70.replace('{0}', arr1[4]) + "</div><div style='width:193px;height:46px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton2.png);text-align:center;line-height:43px;font-size:22px;color:black;cursor:pointer;float:left;margin:30px 0 0 320px;display:inline;font-style:italic;font-weight:bold' onmouseover='$(this).css({backgroundPosition:\"0 -44px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' onclick='objChampionCup3.ShowGroupHome(-1,0)'>" + ChampionCup_info_KFC_71 + "</div><div style='float:left;height:16px;width:830px;'></div>";
                    } else if (arr1[0] == 3) {
                        tip1 = ChampionCup_info_KFC_73;
                        var championInfo = result[1]; //"123456|必须响亮|45|5872|8888";
                        championInfo = championInfo.split('|');
                        objChampionCup3.ShowChampion(championInfo[0], championInfo[1], championInfo[2], championInfo[3], championInfo[4]);
                        return;
                    }
                } else {
                    if (arr1[0] == 3)
                        scrollerInfo += "<div style='width:193px;height:46px;background:url(" + ImgPath + "images/ChampionCup3/bigbutton2.png);text-align:center;line-height:43px;font-size:22px;color:black;cursor:pointer;float:left;margin:30px 0 0 320px;display:inline;font-style:italic;font-weight:bold' onmouseover='$(this).css({backgroundPosition:\"0 -44px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' onclick='objChampionCup3.ShowAgainstPlan()'>" + ChampionCup_info_KFC_72 + "</div><div style='float:left;height:16px;width:830px;'></div>";
                }
                if (arr1[0] != 3) {//小组赛有滚动条，淘汰赛没有滚动条
                    scrollerInfo = "<div class='Container' style='left:0;top:0;width:874px;height:327px'><div id='Scroller-1' style='width:874px;height:327px;position:absolute;overflow:hidden'><div id='nn' class='Scroller-Container'>" + scrollerInfo + "</div></div></div><div id='Scrollbar-Container' style='width:10px;position:absolute;height:320px;left:862px;top:0'><div class='Scrollbar-Up'></div><div class='Scrollbar-Track2' style='height:321px;'></div><div class='Scrollbar-Down' style='top:324px'></div><div class='Scrollbar-Track' style='height:327px'><div class='Scrollbar-Handle'></div></div></div>";

                    //                    scrollerInfo = "<div class='New-Container' style='left:0;top:0;width:874px;height:327px'><div id='Scroller-1' style='width:874px;height:327px;position:absolute;overflow:hidden'><div id='nn' class='New-Scroller-Container'>" + scrollerInfo + "</div></div></div><div id='Scrollbar-Container' style='width:10px;position:absolute;height:320px;left:862px;top:0'><div style='top:0px;' class='New-Scrollbar-BtnUp'></div><div class='New-Scrollbar-Up' style='top:13px'></div><div class='New-Scrollbar-Track2' style='height:291px;top:18px;'></div><div class='New-Scrollbar-Down' style='top:309px'></div><div class='New-Scrollbar-Track' style='top:13px;height:301px'><div class='New-Scrollbar-Handle'></div></div><div class='New-Scrollbar-BtnDown' style='top:316'></div></div>";
                }
                groupInfo += scrollerInfo;
                groupInfo += "</div>";
                html += groupInfo;
                AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
                if (arr1[0] != 3) {
                    scroller = new jsScroller(document.getElementById("Scroller-1"), 874, 327);
                    scrollbar = new jsScrollbar(document.getElementById("Scrollbar-Container"), scroller, false);
                    //scrollbar = new jsScrollbar2(document.getElementById("Scroller-1"), 874, 327, document.getElementById("Scrollbar-Container"), false);
                    scrollbar.scrollTo(0, rounds == 1 ? 0 : 44 + 182 * (rounds - 1));
                }
            });
        }
        //更新被淘汰球队的状态
        this.UpdateClubStatus = function (t) {
            $.post("ChampionCup3.aspx", { type: t }, function (result) {

            });
        }
        //恭喜你获得欧冠冠军
        this.ShowChampion = function (clubID, clubName, clubLevel, clubPower, Ticket) {

            var html = "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/champion.jpg);position:absolute;left:5px;top:0'></div><div style='width:273px;height:65px;position:absolute;left:332px;top:160px;background:url(" + ImgPath + "images/ChampionCup3/vsbutton.png);text-align:center;line-height:41px;font-size:23px;font-weight:bold;font-style:italic;cursor:pointer;color:black' " + objChampionCup3.btnClub + " onclick='objTeam2.ShowClub2(" + clubID + ")'>" + clubName + "<div style='font-size:12px;color:#0a2399;line-height:24px;cursor:pointer'>lv" + clubLevel + "&nbsp;&nbsp;&nbsp;&nbsp;" + Default_Strength + clubPower + (parseInt(Ticket) >= 0 ? "&nbsp;&nbsp;&nbsp;&nbsp;" + Ticket + ChampionCup_info_KFC_39 : "") + "</div></div><div style='font-size:36px;left:301px;top:251px;width:400px;position:absolute' class='font1'>" + ChampionCup_info_KFC_73 + "</div><div style='font-size:36px;color:#f31d03;left:300px;top:250px;width:400px;position:absolute' class='font1'>" + ChampionCup_info_KFC_73 + "</div><div style='width:193px;height:46px;background:url(Images/ChampionCup3/bigbutton2.png);text-align:center;line-height:43px;font-size:22px;color:black;cursor:pointer;font-style:italic;font-weight:bold;left:368px;top:376px;position:absolute' onmouseover='$(this).css({backgroundPosition:\"0 -44px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})' onclick='objChampionCup3.ShowAgainstPlan()'>" + ChampionCup_info_KFC_72 + "</div>";
            AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
        }
        //鼠标移出
        this.groupTime = null;
        this.GrouMOut = function (theBtn) {
            theBtn.css({ background: 'none' });
            objChampionCup3.groupTime = setTimeout("$('#divBtnList').css({display:\"none\"})", 200);
        }
        this.GrouMOver = function (theBtn) {
            clearTimeout(objChampionCup3.groupTime);
            theBtn.css({ background: 'url(' + ImgPath + 'Images/ChampionCup3/hover.png) no-repeat center center' });
        }
        //绑定小组列表
        this.BindCroupList = function () {
            var html = '';
            for (var v = 0; v < 8; v++) {
                html += "<div style='width:61px;height:20px;line-height:20px;text-align:left;color:black;padding-left:18px;cursor:pointer' onmouseover='objChampionCup3.GrouMOver($(this))' onmouseout='objChampionCup3.GrouMOut($(this))' onclick='objChampionCup3.ChangeGroup(" + v + ",$(this))'>" + GetEgl(v) + ChampionCup_info_KFC_59 + "</div><div class='horSeparator' style='width:73px;background:#133aa8;overflow:hidden;height:1px;border:0;margin-left:3px' onmouseover='clearTimeout(objChampionCup3.groupTime);'></div>";
            }
            return html;
        }
        //转小组
        this.ChangeGroup = function (groupIndex, theBtn) {
            $('#divBtnName').html(theBtn.html());
            $('#divBtnList').css({ display: 'none' });
            objChampionCup3.ShowGroupHome(groupIndex, objChampionCup3.publicMatchType);
        }
        //显示小组列表
        this.ShowGroupList = function () {
            $('#divBtnList').css({ display: 'block' });
        }
        //生成一场比赛信息
        this.CreateClubDiv = function (matchID, matchStatus, matchScore, homeClubID, homeClubName, homeClubLevel, homeClubPower, homeTicket, awayClubID, awayClubName, awayClubLevel, awayClubPower, awayTicket, shootInfo) {
            var homePlayerShootInfo = "", awayPlayerShootInfo = "";
            //shootInfo = "12345|老邢|3*12346|侯克磊|5^12345|东杰|3*12346|大姚|5";
            if (shootInfo != "" && shootInfo != null) {
                var arr1 = shootInfo.split('~'), arr2;
                if (arr1[0] != "") {
                    for (var v = 0; v < arr1[0].split('*').length; v++) {
                        arr2 = arr1[0].split('*')[v].split('|');
                        homePlayerShootInfo += "<a style='cursor:pointer' " + mActive + " onclick='ShowPlayer(" + parseInt(arr2[0]) + ")'>" + arr2[1] + " " + arr2[2] + ChampionCup_info_KFC_74 + "</a><br>";
                    }
                }
                if (arr1[1] != "") {
                    for (var v = 0; v < arr1[1].split('*').length; v++) {
                        arr2 = arr1[1].split('*')[v].split('|');
                        awayPlayerShootInfo += "<a style='cursor:pointer' " + mActive + " onclick='ShowPlayer(" + parseInt(arr2[0]) + ")'>" + arr2[1] + " " + arr2[2] + ChampionCup_info_KFC_74 + "</a><br>";
                    }
                }
            }
            if (matchStatus == 2) {//直播时不显示球员进球信息
                homePlayerShootInfo = "";
                awayPlayerShootInfo = "";
            }
            return "<div style='float:left'><div style='width:115px;height:69px;float:left;color:white;line-height:14px;'>" + homePlayerShootInfo + "</div>" + objChampionCup3.CreateClubCard(homeClubID, homeClubName, homeClubLevel, homeClubPower, homeTicket, 'right') + objChampionCup3.CreateScore(matchScore, matchID, matchStatus) + objChampionCup3.CreateClubCard(awayClubID, awayClubName, awayClubLevel, awayClubPower, awayTicket, 'left') + "<div style='width:101px;height:69px;float:left;color:white;padding-left:20px;line-height:14px'>" + awayPlayerShootInfo + "</div></div>";
        }
        //生成球队卡html
        this.CreateClubCard = function (ClubID, ClubName, ClubLevel, ClubPower, Ticket, homeAwayCss) {
            return "<div style='width:227px;height:65px;background:url(" + ImgPath + "images/ChampionCup3/vsbutton.png);float:left;text-align:" + homeAwayCss + ";padding-right:23px;padding-left:23px;font-size:23px;font-weight:bold;line-height:41px;color:black;font-style:italic;cursor:pointer' " + objChampionCup3.btnClub + " onclick='objTeam2.ShowClub2(" + ClubID + ")'>" + ClubName + "<div style='font-size:12px;color:black;line-height:24px;cursor:pointer'>lv" + ClubLevel + "&nbsp;&nbsp;&nbsp;&nbsp;" + Default_Strength + ClubPower + (parseInt(Ticket) >= 0 ? "&nbsp;&nbsp;&nbsp;&nbsp;" + ChampionCup_info_KFC_75 + Ticket + ChampionCup_info_KFC_39 : "") + "</div></div>";
        }
        //生成比分html
        this.CreateScore = function (score, matchID, matchStatus) {
            if (matchStatus == 0) {//未开打
                return "<div style='float:left;color:black;font-weight:bold;font-size:25px;width:54px;padding:10px 0 0 14px;font-style:italic;text-align:left;'>VS</div>";
            } else if (matchStatus == 1) {//打完
                return "<div style='float:left;color:black;font-weight:bold;font-size:28px;width:60px;padding:14px 0 0 8px;font-style:italic;text-align:left;cursor:pointer;' onmouseover='$(this).css({color:\"yellow\",textDecoration:\"underline\"})' onmouseout='$(this).css({color:\"black\",textDecoration:\"none\"})' onclick='showMatch(" + matchID + ", 0,0,1)'>" + score + "</div>";
            } else {//直播
                return "<div style='float:left;color:black;font-weight:bold;font-size:25px;width:54px;padding:10px 0 0 14px;font-style:italic;text-align:left;'>VS<div style=" + objChampionCup3.btn4Css + "position:static;font-size:14px;color:black;text-align:left;padding-left:32px;width:47px;margin-top:0;margin-left:-20px;background:url(" + ImgPath + "images/ChampionCup3/button1.png) " + objChampionCup3.btn4 + " onclick='showMatch(" + matchID + ", 0,0,1)'>" + ChampionCup_info_KFC_76 + "</div></div>";
            }
        }
        //16强对阵图---------------------------------------------------------------------------------------------------------------------------
        this.ShowAgainstPlan = function () {
            objChampionCup3.pageIndex = 1;
            //主场ID|主场NAME|主场level|主场积分|工会ID|工会简称|公会等级|是否晋级（0暗，1亮）|matchid|matchid2|比赛状态（0没打1打完了2正在打）
            $.post('ChampionCup3.aspx', { type: 6 }, function (result) {
                if (result.split('*')[0].split('|')[0] == '') {
                    objChampionCup3.ShowMsg(ChampionCup_info_KFC_77);
                    return;
                }
                //result += "^1&月日|1时|2时*月日|1时|2时*月日|1时|2时";
                var arr2 = result.split('^');
                result = arr2[0].split('*');
                var arr3 = arr2[1].split('&');
                var arr4 = arr3[1].split('*');
                var html = "";
                var clubList = "", Champion = '', championClubInfo = '';
                var arr1;
                for (var v = 0; v < result.length - 1; v++) {
                    arr1 = result[v].split('|');
                    clubList += objChampionCup3.ShowSmallClubCard(v, arr1[5], parseInt(arr1[6]), parseInt(arr1[0]), arr1[1], arr1[3], arr1[2], parseInt(arr1[7]), parseInt(arr1[8]), parseInt(arr1[9]), parseInt(arr1[11]), parseInt(arr1[10]));
                }
                if (result[30] != "") {
                    arr1 = result[30].split('|');
                    var shortName = arr1[5];
                    if (shortName != '')
                        shortName = "[" + shortName + "]";
                    Champion = "<a style='cursor:pointer' onclick='objTeam2.ShowClub2(" + parseInt(arr1[0]) + ")'>" + shortName + arr1[1] + "</a>";
                    championClubInfo = "<div style='position:absolute;left:274px;top:2px;width:361px;height:190px;background:url(" + ImgPath + "images/ChampionCup3/champion.png);'></div>";
                }

                var dateInfo = "", progress = "", dateInfo1 = "", progress1 = "", arr5, arr6;
                for (var v = 0; v < arr4.length; v++) {
                    arr5 = arr4[v].split('|');
                    arr6 = objChampionCup3.GetProgressName(4 - v);
                    if (parseInt(arr3[0]) == 3 - v) {
                        progress += "<a style='color:yellow'>" + arr6 + "</a><br>";
                        dateInfo += "<a style='color:yellow'>" + arr5[0] + "&nbsp;" + arr5[1] + "&nbsp;&nbsp;&nbsp;" + arr5[2] + "</a><br>";
                        progress1 += "<a style='color:#00137c'>" + arr6 + "</a><br>";
                        dateInfo1 += "<a style='color:#00137c'>" + arr5[0] + "&nbsp;" + arr5[1] + "&nbsp;&nbsp;&nbsp;" + arr5[2] + "</a><br>";
                    } else {
                        progress += arr6 + "<br>";
                        dateInfo += arr5[0] + "&nbsp;" + arr5[1] + "&nbsp;&nbsp;&nbsp;" + arr5[2] + "<br>";
                        progress1 += arr6 + "<br>";
                        dateInfo1 += arr5[0] + "&nbsp;" + arr5[1] + "&nbsp;&nbsp;&nbsp;" + arr5[2] + "<br>";
                    }
                }
                html += "<div style='width:909px;height:457px;background:url(" + ImgPath + "images/ChampionCup3/playoff.jpg);position:absolute;left:5px;top:0'>" + championClubInfo + clubList + "<div style='position:absolute;left:377px;top:146px;color:yellow;font-size:19px;font-weight:bold;width:150px;text-align:center;'>" + Champion + "</div><div style='font-size:28px;position:absolute;left:436px;top:216px;color:white'>VS</div><div style='position:absolute;left:371px;top:309px;color:#00137c;line-height:24px;text-align:right'>" + progress1 + "</div><div style='position:absolute;left:419px;top:309px;color:#00137c;line-height:24px;text-align:left'>" + dateInfo1 + "</div><div style='position:absolute;left:370px;top:308px;color:white;line-height:24px;text-align:right'>" + progress + "</div><div style='position:absolute;left:418px;top:308px;color:white;line-height:24px;text-align:left'>" + dateInfo + "</div></div>";
                AddBox(85, 77, 923, 493, '', html, null, null, null, 'divChampionCup', false);
            });
        }
        this.GetProgressName = function (rounds) {
            var roundsDes = rounds == 1 ? ChampionCup_info_KFC_56 : rounds == 2 ? ChampionCup_info_KFC_57 : rounds == 3 ? ChampionCup_info_KFC_21 : ChampionCup_info_KFC_22;
            return roundsDes;
        }
        this.positionArr = new Array(7, 11, 7, 59, 7, 116, 7, 164, 7, 248, 7, 296, 7, 352, 7, 400, 783, 11, 783, 59, 783, 116, 783, 164, 783, 248, 783, 296, 783, 352, 783, 400, 135, 59, 135, 116, 135, 296, 135, 352, 655, 59, 655, 116, 655, 296, 655, 352, 174, 179, 174, 232, 616, 179, 616, 232, 305, 206, 485, 206);
        //生成小的球队卡
        this.ShowSmallClubCard = function (clubIndex, shortName, teamLevel, clubID, clubName, integral, Bett, islose, matchid1, matchid2, screenings, matchState) {
            //matchState = 2;
            if (clubName == '') {
                return "";
            } else {
                if (shortName != '')
                    shortName = "[" + shortName + "]";
                if (clubName.length > 5)
                    clubName = clubName.substring(0, 5);
                var score = "";
                if (matchState == 2)
                    score = "<div style='position:absolute;left:94px;top:12px;width:15px;height:8px;background:url(" + ImgPath + "Images/ChampionCup3/video.png);cursor:pointer' onclick='objChampionCup3.ShowMatchList(" + matchid1 + "," + matchid2 + ")'></div>";
                else
                    score = "<div style='position:absolute;left:91px;top:3px;font-size:19px;font-weight:bold;text-align:right;width:20px;cursor:pointer;color:" + (screenings < 2 ? 'black' : 'black') + "'>" + (integral < 0 ? "" : "<a style='cursor:pointer' " + mActive + " onclick='objChampionCup3.ShowMatchList(" + matchid1 + "," + matchid2 + ")'>" + integral + "</a>") + "</div>";
                return "<div style='width:120px;height:49px;background:" + (islose == -1 ? 'green' : 'url(' + ImgPath + 'Images/ChampionCup3/playoffbg.png)') + ";position:absolute;left:" + objChampionCup3.positionArr[clubIndex * 2] + "px;top:" + objChampionCup3.positionArr[clubIndex * 2 + 1] + "px;color:black;' onmouseover='$(this).css({backgroundPosition:\"0 -49px\"})' onmouseout='$(this).css({backgroundPosition:\"0 0\"})'><div style='position:absolute;left:11px;top:7px;cursor:pointer'>" + shortName + "<a " + mActive + " style='cursor:pointer' onclick='objTeam2.ShowClub2(" + clubID + ")'>" + clubName + "</a></div>" + score + "<div style='position:absolute;left:12px;top:30px;color:white;cursor:pointer'>" + Bett + ChampionCup_info_KFC_39 + "</div></div>";
            }
        }
        //去掉比分战报
        this.RemoveMatchList = function () {
            $('#tip1').remove();
        }
        //显示某球队的比赛列表
        this.ShowMatchList = function (matchid1, matchid2) {
            $.post('ChampionCup3.aspx', { type: 7, matchids: matchid1 + "," + matchid2 }, function (result) {
                result = result.split('*');
                //var matchStatus=parseInt(result[2]);//比赛状态（0没打1打完了2正在打）
                var html = "<div style='background:url(" + ImgPath + "images/ChampionCup3/livebg.jpg);width:402px;height:184px;position:absolute;left:4px;top:0'></div><div style='left:140px;top:0px;font-size:30px;color:black;font-style:normal' class='font1'>" + ChampionCup_info_KFC_78 + "</div><div style='overflow:hidden;height:1px;background:#af7005;width:375px;position:absolute;left:20px;top:44px'></div>";
                if (result[0] == '-1' && result[1] == '-1') {
                    html += "<div style='left:30px;top:60px;font-size:20px' class='font1'>" + ChampionCup_info_KFC_79 + "</div><div style='left:29px;top:59px;font-size:20px;color:black' class='font1'>" + ChampionCup_info_KFC_79 + "</div>";
                } else {
                    var arr1, matchbtn = "", str1 = "";
                    for (var v = 0; v < result.length; v++) {
                        arr1 = result[v].split('|');
                        str1 = (arr1[5] == '2' ? 'VS' : arr1[2] + ":" + arr1[4]);
                        html += "<div style='padding-bottom:2px;height:24px;line-height:24px;font-size:22px;width:400px;left:4px;top:" + (v == 0 ? 54 : 113) + "px;font-style:normal;color:black' class='font1'><div style='float:left;width:165px;text-align:right;'>" + arr1[1] + "</div><div style='float:left;width:70px;text-align:center'>" + str1 + "</div><div style='float:left;width:165px;text-align:left'>" + arr1[3] + "</div></div>";
                        if (arr1[5] == '2')
                            html += "<div style='" + objChampionCup3.btn4Css + "left:165px;top:" + (v == 0 ? 82 : 142) + "px;background:url(" + ImgPath + "images/ChampionCup3/button1.png);text-align:left;padding-left:38px;width:41px;color:black' " + objChampionCup3.btn4 + " onclick='showMatch(" + arr1[0] + ", 0,0,1)'>" + ChampionCup_info_KFC_80 + "</div>";
                        else
                            html += "<div style='" + objChampionCup3.btn4Css + "left:165px;top:" + (v == 0 ? 82 : 142) + "px;' " + objChampionCup3.btn4 + " onclick='showMatch(" + arr1[0] + ", 0,0,1)'>" + ChampionCup_info_KFC_81 + "</div>";
                    }
                }
                AddBox(250, 200, 414, 220, ChampionCup_info_KFC_82, html, $('#divChampionCup'), null, null, 'tip1', false, true);
            });
        }
        //提示框统一样式
        this.ShowMsg = function (content, title, store) {
            store = store == null ? $('#divChampionCup') : store;
            ShowMsg(content, Default_Sure, null, title, null, null, 292, 180, store, 'divCCTip1', true);
        }
    }
}
