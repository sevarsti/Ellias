function MiracleGoalkeeper() {
    this.GotoMiracleGoalkeeper = function () {
        $.post("MiracleGoalKeeper.aspx", { Load: 1 }, function (result) {
            //result = "1|1|1|1|0|0|0@7@500";  //圆圈信息@当前第几步@所拥有门将卡
            var ActivityTimeStr = result.split('╋')[1]; //活动时间
            result = result.split('╋')[0];

            var state = result.split('@')[0].split('|');
            var step = result.split('@')[1];
            var cardnum = result.split('@')[2];
            var html = "<div id='div_keeper' style='width:909px;height:457px;left:5px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/bj.jpg);position:absolute;'></div>";
            //       html += "<div id='div_keeper' style='left:738px;top:135px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/circle.png);position:absolute;width:119px;height:118px;'></div>";
            html += "<div style='left:325px;top:100px;position:absolute;color:yellow;font-size:11px'>【活动时间】" + ActivityTimeStr + "</div>";
            html += "<div style='left:784px;top:362px;position:absolute;color:white;font-size:14px;font-weight:bold;'>门将卡数量:" + cardnum + "</div>";
            html += "<div class='div_btn4' onclick='GoalkeeperObj.leap(" + cardnum + ")' style='left:790px;top:398px;position:absolute;color:white;font-size:12px;'>点此跃进</div>"
            html += "<div style='left:792px;top:399px;width:12px;height:17px;position:absolute;background:url(" + ImgPath + "Images/MiracleGoalkeeper/arrow.png)'></div>"
            html += "<div id='btn_go' onclick='GoalkeeperObj.ReadyGo(" + step + ")' onmouseover='GoalkeeperObj.onbutton(1)' onmouseout='GoalkeeperObj.onbutton(2)' style='left:530px;top:380px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/butter.png);position:absolute;width:156px;height:42px;color:#3d1400;font-size:24px;font-weight:bold;text-align:center;line-height:40px;cursor:pointer;'>前进&nbsp&nbsp" + step + "</div>";

            var cercleArr = new Array("196|66", "272|178", "320|314", "430|231", "522|132", "620|220", "738|135");
            var cardcountArr = new Array(1, 2, 3, 5, 10, 20, 30);
            var LeftPx = 0;
            var TopPx = 0;
            var cardcount = 0
            for (var i = 0; i < 7; i++) {
                LeftPx = cercleArr[i].split('|')[0];
                TopPx = cercleArr[i].split('|')[1];
                cardcount = cardcountArr[i];
                if (state[i] == 1) {
                    html += "<div style='left:" + LeftPx + "px;top:" + TopPx + "px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/circle.png);position:absolute;width:119px;height:118px;'></div>";
                }

                html += "<div style='left:" + (parseInt(LeftPx) + 26) + "px;top:" + (parseInt(TopPx) + 22) + "px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/k2.png);position:absolute;width:66px;height:66px;'></div>";
                html += "<div style = 'left:" + (parseInt(LeftPx) + 26) + "px;top:" + (parseInt(TopPx) + 22) + "px;position:absolute;width:64px;height:64px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/card.png)'></div>";
                if (i % 2 != 0) {
                    html += "<div class='div_btn2' onclick='GoalkeeperObj.Besafesure(" + (i + 1) + ")' style='left:" + (parseInt(LeftPx) + 36) + "px;top:" + (parseInt(TopPx) + 68) + "px;position:absolute;'>保险</div>";
                }
                html += "<div style='left:" + (parseInt(LeftPx) + 72) + "px;top:" + (parseInt(TopPx) + 28) + "px;position:absolute;width:66px;height:66px;font-size:14px;color:yellow;font-weight:bold;'>" + cardcount + "</div>";
            }


            AddBox(120, 70, 922, 492, "神奇门将大跃进", html, null, null, null, 'Goalkeeper', null, null, null);
        });
    }
    this.onbutton = function (type) {
        if (type == "1") {
            $('#btn_go').css("background", "url(" + ImgPath + "Images/MiracleGoalkeeper/butter2.png)");
        } else if (type == "2") {
            $('#btn_go').css("background", "url(" + ImgPath + "Images/MiracleGoalkeeper/butter.png)");
        }
    }
    this.ReadyGo = function (step) {
        var gold = 0;
        if (step == "1") {
            gold = 20
        } else if (step == "2") {
            gold = 35
        } else if (step == "3") {
            gold = 50
        } else if (step == "4") {
            gold = 80
        } else if (step == "5") {
            gold = 150
        } else if (step == "6") {
            gold = 200
        } else if (step == "7") {
            gold = 260
        }
        ShowMsg("确定花费" + gold + "金币前进吗", null, function () { GoalkeeperObj.GoAward(gold); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
    }
    this.GoAward = function (gold) {
        $.post("MiracleGoalKeeper.aspx", { GoAward: 1 }, function (result) {
            //result = 1;
            if (result == "1") {
                publicAddGSEL(1, -gold);
                ShowMsg("恭喜您前进一格", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            } else if (result == "-1") {
                OpenPayMsg();
            } else if (result == "-3") {
                publicAddGSEL(1, -gold);
                ShowMsg("很遗憾，前进失败，返回原点", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            } else if (result == "-2") {
                publicAddGSEL(1, -gold);
                ShowMsg("很遗憾，前进失败，返回临近保险点", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            }
        });
    }
    this.Besafesure = function (index) {
        var gold = 0;
        if (index == "2") {
            gold = 60;
        } else if (index == "4") {
            gold = 160;
        } else if (index == "6") {
            gold = 260;
        }
        ShowMsg("您确定花费" + gold + "金币保险吗？", null, function () { GoalkeeperObj.Besafe(index, gold); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
    }
    this.Besafe = function (index, gold) {
        $.post("MiracleGoalKeeper.aspx", { Besafe: index }, function (result) {
            if (result == "1") {
                publicAddGSEL(1, -gold);
                ShowMsg("恭喜您保险成功", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            } else if (result == "-1") {
                OpenPayMsg();
            } else if (result == "-2") {
                ShowMsg("您已投过保险，无需再投了哦", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            } else {
                ShowMsg("操作失败", null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_gold');
            }
        });
    }
    this.leap = function (cardnum) {
        var html = "<div id='div_keeper' style='width:909px;height:457px;left:5px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/bj2.jpg);position:absolute;'></div>";
        if (cardnum >= 700) {
            var stateArr = new Array(1, 1, 1, 1, 1);
        } else if (cardnum >= 300) {
            var stateArr = new Array(1, 1, 1, 0, 1);
        } else if (cardnum >= 260) {
            var stateArr = new Array(0, 1, 1, 0, 1);
        } else if (cardnum >= 200) {
            var stateArr = new Array(0, 0, 1, 0, 1);
        } else {
            var stateArr = new Array(0, 0, 0, 0, 0);
        }
        html += "<div style = 'left:84px;top:412px;position:absolute;color:yellow'>【活动时间】11月16日10:00-11月17日24:00</div>"
        //CreatePlayer(105, 5, "Super/10036.png", "金球梅西", 6, null, 0, 0, null); //综合能力,场上位置,头像图的名称,球员名字,球员品质,球员ID(null=不弹球员属性框),球员卡left,球员卡top,球员卡片div的ID
        var PlayerHtml = "";
        var playerinfo = new Array("97|门将|Spain/09/50901.png|卡西利亚斯|6", "93|门将|Italy/08/30801.png|布冯|6", "92|门将|England/08/40801.png|切赫|6", "95|门将|Super/10014.png|范德萨|6");
        var powerArr = new Array(102, 101, 100, 105);
        var playerpower = 0;
        var role = "";
        var imgpath = "";
        var playername = "";
        var quality = 0;
        for (var i = 0; i < 4; i++) {



            playerpower = playerinfo[i].split('|')[0];
            role = playerinfo[i].split('|')[1];
            imgpath = playerinfo[i].split('|')[2];
            playername = playerinfo[i].split('|')[3];
            quality = playerinfo[i].split('|')[4];
            PlayerHtml += "<div style = 'left:50px;top:" + (5 + i * 120) + "px;position:absolute;'>" + CreatePlayer(playerpower, 1, imgpath, playername, quality, null, 0, 0, null) + "</div>";
            PlayerHtml += "<div style = 'left:150px;top:" + (40 + i * 120) + "px;position:absolute;width:31px;height:31px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/+.png)'></div>";
            PlayerHtml += "<div style = 'left:200px;top:" + (26 + i * 120) + "px;position:absolute;width:64px;height:64px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/card.png)'></div>";
            PlayerHtml += "<div style = 'left:286px;top:" + (44 + i * 120) + "px;position:absolute;width:34px;height:22px;background:url(" + ImgPath + "Images/MiracleGoalkeeper/=.png)'></div>";
            if (i < 4) {
                PlayerHtml += "<div style = 'left:340px;top:" + (5 + i * 120) + "px;position:absolute;'>" + CreatePlayer(powerArr[i], 1, imgpath, playername, quality, null, 0, 0, null, "110000001") + "</div>";
            } else {
                PlayerHtml += "<div style = 'left:340px;top:" + (5 + i * 120) + "px;position:absolute;'>" + CreatePlayer(powerArr[i], 1, imgpath, playername, quality, null, 0, 0, null, "112") + "</div>";
            }
            if (stateArr[i] == "1") {
                PlayerHtml += "<div class='div_btn2' onclick='GoalkeeperObj.leapplayer(" + i + "," + cardnum + ")' style = 'left:440px;top:" + (44 + i * 120) + "px;position:absolute;'>跃进</div>";
            } else if (stateArr[i] == "0") {
                PlayerHtml += "<div class='div_btn2grey' style = 'left:440px;top:" + (44 + i * 120) + "px;position:absolute;cursor:default;'>跃进</div>";
            }

        }

        var Scrollbarjeap = "";
        Scrollbarjeap += "<div class='Container' style='position:absolute;left:386px;top:64px;width:500px;height:320px;'>";
        Scrollbarjeap += "<div id='Scroller-Game-ph' style='left:0px;width:500px;height:320px;position:absolute;overflow:hidden;'>";
        Scrollbarjeap += "<div class='Scroller-Container'>";
        Scrollbarjeap += "<div style='top:-7px;left:0px;height:550px;'></div>" + PlayerHtml + "</div></div>";
        Scrollbarjeap += "<div id='Scrollbar-Container-ph' style='width:10px;height:300px;position:absolute;left:500px;top:-4px;'><div class='Scrollbar-Up'></div>";
        Scrollbarjeap += "<div class='Scrollbar-Track2' style='height:320px;'></div><div class='Scrollbar-Down'></div>";
        Scrollbarjeap += "<div class='Scrollbar-Track' style='height:320px;'><div class='Scrollbar-Handle'></div></div>";
        Scrollbarjeap += "</div></div>";
        html += Scrollbarjeap;
        AddBox(0, 0, 922, 492, "神奇门将大跃进", html, $('#Goalkeeper'), null, function () { GoalkeeperObj.GotoMiracleGoalkeeper(); }, 'anagramsdiv2', null, null, null);
        scroller = new jsScroller(document.getElementById("Scroller-Game-ph"), 542, 292);
        scrollbar = new jsScrollbar(document.getElementById("Scrollbar-Container-ph"), scroller, false);
    }
    this.leapplayer = function (index, cardnum) {
        $.post("MiracleGoalKeeper.aspx", { leapplayer: index }, function (result) {
            if (result == "1") {
                ShowMsg("恭喜您跃进成功", null, function () { $('#anagramsdiv2').remove(); $('#div_leapplayer').remove(); GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_leapplayer');
            } else if (result == "-1") {
                ShowMsg("您没有此球员", null, function () { $('#anagramsdiv2').remove(); $('#div_leapplayer').remove(); GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_leapplayer');
            } else {
                ShowMsg("操作失败", null, function () { $('#anagramsdiv2').remove(); $('#div_leapplayer').remove(); GoalkeeperObj.GotoMiracleGoalkeeper(); }, null, null, 184, 340, 180, $('#totalMainDiv'), 'div_leapplayer');
            }
        });
    }
}