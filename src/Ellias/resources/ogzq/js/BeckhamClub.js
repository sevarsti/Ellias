/*
贝克汉姆退役 打副本集奖杯活动  张旭  张旭
*/
function BeckhamClub() {
    /*
    显示活动活动的界面1  张旭
    */
    this.GotoBeckhamClub = function () {
        $.post("BeckhamClub.aspx", { load: 1 }, function (result) {
            //result = "1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1@9901|球队名1|2*9901|球队名2|2*9901|球队名3|2*9901|球队名4|2*9901|球队名5|2*9901|球队名6|2*9901|球队名7|2*9901|球队名8|2*9901|球队名9|2*9901|球队名10|2*9901|球队名11|2";
            if (result == -1) {
                ShowMsg("只有球会成员才可以参与本活动哦！", "确定", function () { $("#divMsg").remove(); }, "温馨提示", null, null, 380, 150, $('#totalMainDiv'), 'divMsg', false);
                return;
            }
            //result = "1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1@9901|球队名1|2*9901|球队名2|2*9901|球队名3|2*9901|球队名4|2*9901|球队名5|2*9901|球队名6|2*9901|球队名7|2*9901|球队名8|2*9901|球队名9|2*9901|球队名10|2*9901|球队名11|2";
            var getterArr = result.split('@')[0].split('|');
            //19个奖杯的图片（亮或者不亮）
            var cupImgs = "<div style='position:absolute;left:195px;top:78px;width:385px;height:274px;'>";
            for (var i = 0; i < getterArr.length; i++) {
                var getterName = getterArr[i];
                if (getterName != "-1") {
                    cupImgs += "<div style='position:absolute;left:" + (0 + parseInt(i % 7) * 58) + "px;top:" + (17 + parseInt(i / 7) * 96) + "px;width:35px;height:64px;background:url(Images/BeckhamClub/cup.png);' onmouseover='ShowTipBox(\"" + getterName + "\",$(this))'></div>";
                }
            }
            cupImgs += "</div>";
            //球会贡献
            var clubMembers = "<div style='position:absolute;left:735px;top:20px;width:80px;color:white;font-weight:bold;font-size:20px;'>球会贡献</div>";
            clubMembers += "<div style='position:absolute;left:670px;top:68px;width:200px;color:#FDE101;font-weight:bold;font-size:15px;'>";
            clubMembers += "<div style='position:absolute;left:0px;'>球队名</div><div style='position:absolute;left:120px;'>激活奖杯数</div></div>";
            //球队贡献的信息，多于10条需要显示滚动条
            var MembersInfo = "<div style='position:absolute;left:0px;top:0px;width:200px;height:270px;color:white;font-weight:bold;font-size:14px;'>";
            if (result.split('@')[1] != "") {// || result.split('@')[1] != null || typeof (result.split('@')[1]) != 'undefined'
                var clubInfoArr = result.split('@')[1].split('*');
                for (var j = 0; j < clubInfoArr.length; j++) {
                    var MemberArr = clubInfoArr[j].split('|');
                    MembersInfo += "<div style='position:absolute;left:0px;top:" + (0 + j * 27) + "px;width:200px;height:27px;color:white;font-weight:bold;font-size:15px;'>";
                    MembersInfo += "<div class='clubName_b' style='position:absolute;left:0px;top:0px;cursor:pointer;'onclick='objTeam.ShowClub(" + MemberArr[0] + ")'>" + MemberArr[1] + "</div>";
                    MembersInfo += "<div style='position:absolute;left:152px;'>" + MemberArr[2] + "</div></div>";
                }
            }
            MembersInfo += "</div>";

            //放入滚动条中
            var MembersInfoSc = "";
            if (result.split('@')[1] != "" || result.split('@')[1] != null || typeof (result.split('@')[1]) != 'undefined') {
                var clubInfoArr = result.split('@')[1].split('*');
                if (clubInfoArr.length > 10) {
                    MembersInfoSc = "";
                    MembersInfoSc += "<div class='Container' style='position:absolute;top:100px;left:670px;width:200px;height:270px;'>";
                    MembersInfoSc += "<div id='Scroller-Game-ph' style='left:0px;width:200px;height:270px;position:absolute;overflow:hidden;'>";
                    MembersInfoSc += "<div class='Scroller-Container'>";
                    MembersInfoSc += "<div style='top:-7px;left:0px;height:" + 27 * (clubInfoArr.length) + "px;'></div>" + MembersInfo + "</div></div>";
                    MembersInfoSc += "<div id='Scrollbar-Container-ph' style='width:10px;height:270px;position:absolute;left:200px;top:-4px;'><div class='Scrollbar-Up'></div>";
                    MembersInfoSc += "<div class='Scrollbar-Track2' style='height:267px;'></div><div class='Scrollbar-Down'></div>";
                    MembersInfoSc += "<div class='Scrollbar-Track' style='height:273px;'><div class='Scrollbar-Handle'></div></div>";
                    MembersInfoSc += "</div></div>";
                } else {//不满一页，不显示滚动条
                    MembersInfoSc += "<div style='position:absolute;top:100px;left:670px;'>" + MembersInfo + "</div>";
                }
            }
            //奖励图片+排名按钮+领奖按钮+进入副本按钮
            var rewardImg = "<div style='position:absolute;left:488px;top:393px;width:36px;height:36px;background:url(Images/item/1107s.png);' onmouseover='ShowTipBox(\"合同7级\",$(this))'></div>";
            rewardImg += "<div style='position:absolute;left:536px;top:393px;width:36px;height:36px;background:url(Images/item/1806s.png);' onmouseover='ShowTipBox(\"手套6级\",$(this))'></div>";
            rewardImg += "<div class='div_btn4' style='position:absolute;left:790px;top:400px;font-size:13px;'onclick='BeckhamClubObj.ShowClubRank()'>球会排名</div>";
            rewardImg += "<div class='div_btn4' style='position:absolute;left:680px;top:400px;font-size:13px;'onclick='challengeObj.GoToChallenge()'>进入副本</div>";
            rewardImg += "<div class='div_btn4' style='position:absolute;left:508px;top:330px;font-size:13px;' onclick='BeckhamClubObj.ShowReward()'>领取奖励</div>";
            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div style='width:910px;height:459px;background:url(Images/BeckhamClub/BeckhamClubBg1.jpg);position:absolute;left:6px;top:0px;'>" + cupImgs + clubMembers + MembersInfoSc + rewardImg + "</div>"
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(40, 85, 925, 494, "印象•万人迷", html, $('#totalMainDiv'), "确定", null, "BeckhamClubUI", false, true, null)
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件
            $(".clubName_b").hover(function () { $(this).css({ "text-decoration": "underline", "color": "#F255B3" }); }, function () { $(this).css({ "text-decoration": "none", "color": "white" }); });
            //添加滚动条时 必要的代码
            if (result.split('@')[1] != "" || result.split('@')[1] != null || typeof (result.split('@')[1]) != 'undefined') {
                var clubInfoArr = result.split('@')[1].split('*');
                if (clubInfoArr.length > 10) {
                    var scrollbar = null;
                    scroller = new jsScroller(document.getElementById("Scroller-Game-ph"), 200, 270);
                    scrollbar = new jsScrollbar(document.getElementById("Scrollbar-Container-ph"), scroller, false);
                }
            }
        });
    }

    /*
    显示球会排名界面  张旭
    */
    this.ShowClubRank = function () {
        $.post("BeckhamClub.aspx", { ShowClubRank: 1 }, function (result) {
            // result = "101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12*101|球会名|9910|会长名|12";
            var titles = "<div style='position:absolute;left:313px;top:75px;width:563px;color:white;font-weight:bold;font-size:16px;'>";
            titles += "<div style='position:absolute;left:50px;'>排名</div><div style='position:absolute;left:170px;'>球会名</div>";
            titles += "<div style='position:absolute;left:315px;'>会长</div><div style='position:absolute;left:440px;'>点亮奖杯数</div></div>";

            var rankInfo = "";
            var rankInfoSc = "";
            if (result != -2) {
                rankInfo += "<div style='position:absolute;left:0px;top:0px;width:563px;height:300px;color:gold;font-weight:bold;font-size:14px;'>";
                var rankArr = result.split('*');
                for (var i = 0; i < rankArr.length; i++) {
                    var oneRank = rankArr[i].split('|');
                    rankInfo += "<div style='position:absolute;left:0px;top:" + (0 + 30 * i) + "px;width:563px;height:30px;color:gold;font-weight:bold;font-size:14px;line-height:35px'>";
                    rankInfo += "<div style='position:absolute;left:50px;'>" + (i + 1) + "</div>";
                    rankInfo += "<div style='position:absolute;left:170px;'>" + oneRank[1] + "</div>";
                    rankInfo += "<div style='position:absolute;left:315px;cursor:pointer;'onclick='objTeam.ShowClub(" + oneRank[2] + ")'>" + oneRank[3] + "</div>";
                    rankInfo += "<div style='position:absolute;left:440px;'>" + oneRank[4] + "</div></div>";
                }
                rankInfo += "</div>";

                //放入滚动条中   313 112
                if (rankArr.length > 10) {
                    rankInfoSc += "<div class='Container' style='position:absolute;left:313px;top:112px;width:563px;height:300px;'>";
                    rankInfoSc += "<div id='Scroller-Game-ph' style='left:0px;width:563px;height:300px;position:absolute;overflow:hidden;'>";
                    rankInfoSc += "<div class='Scroller-Container'>";
                    rankInfoSc += "<div style='top:-7px;left:0px;height:" + 30 * (rankArr.length) + "px;'></div>" + rankInfo + "</div></div>";
                    rankInfoSc += "<div id='Scrollbar-Container-ph' style='width:10px;height:300px;position:absolute;left:550px;top:-4px;'><div class='Scrollbar-Up'></div>";
                    rankInfoSc += "<div class='Scrollbar-Track2' style='height:297px;'></div><div class='Scrollbar-Down'></div>";
                    rankInfoSc += "<div class='Scrollbar-Track' style='height:303px;'><div class='Scrollbar-Handle'></div></div>";
                    rankInfoSc += "</div></div>";
                } else {//不满一页，不显示滚动条
                    rankInfoSc += "<div style='position:absolute;left:313px;top:112px;'>" + rankInfo + "</div>";
                }
            }
            var backBtn = "<div class='div_btn4' style='position:absolute;left:805px;top:425px;font-size:13px;' onclick='BeckhamClubObj.GotoBeckhamClub()'>返回</div>";
            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div style='width:910px;height:459px;background:url(Images/BeckhamClub/BeckhamClubBg2.jpg);position:absolute;left:6px;top:0px;'>" + titles + backBtn + rankInfoSc + "</div>"
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(40, 85, 925, 494, "印象•万人迷", html, $('#totalMainDiv'), "确定", null, "BeckhamClubUI", false, true, null)
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件
            // $(".rankshow").hover(function () { $(this).css("font-style", "oblique"); }, function () { $(this).css("font-style", "normal"); });
            //添加滚动条时 必要的代码
            if (result != -2) {
                if (rankArr.length > 10) {
                    var scrollbar = null;
                    scroller = new jsScroller(document.getElementById("Scroller-Game-ph"), 563, 300);
                    scrollbar = new jsScrollbar(document.getElementById("Scrollbar-Container-ph"), scroller, false);
                }
            }
        });
    }


    /*
    显示奖励界面  张旭
    */
    this.ShowReward = function () {
        var bestRewardImgArr = new Array(1108, 9206, 10012, 3307);
        var bestRewardNameArr = new Array('合同8级x2', '6级装备套x2', '银币兑换礼包（中）x5', '战术积分卡10000点x5');
        var bestRewardNumArr = new Array(2, 2, 5, 5);
        var cupImg1 = new Array(1105, 7006);
        var cupImg2 = new Array(1804, 6017);
        var cupImg3 = new Array(1705, 1905);
        var cupImg4 = new Array(2603, 6017);
        var cupImg5 = new Array(1605, 7006);
        var cupImg6 = new Array(2104, 6017);
        var cupImg7 = new Array(10012, 1905);
        var cupImg8 = new Array(2403, 6017);
        var cupImg9 = new Array(1505, 7006);
        var cupImg10 = new Array(2503, 6017);
        var cupImg11 = new Array(3305, 6017);
        var cupImg12 = new Array(2304, 1905);
        var cupImg13 = new Array(1106, 7006);
        var cupImg14 = new Array(2204, 1905);
        var cupImg15 = new Array(2704, 1905);
        var cupImg16 = new Array(2803, 7006);
        var cupImg17 = new Array(1806, 7006);
        var cupImg18 = new Array(1205, 1305, 1405, 7006);
        var cupImg19 = new Array(2911, 1905);
        var itemname1 = new Array('合同5级', '训练卡5000点');
        var itemname2 = new Array('手套4级', '球员经验卡2000点');
        var itemname3 = new Array('战靴5级', '油箱');
        var itemname4 = new Array('狂人风衣3级', '球员经验卡2000点');
        var itemname5 = new Array('护腿5级', '训练卡5000点');
        var itemname6 = new Array('神奇球靴4级', '球员经验卡2000点');
        var itemname7 = new Array('银币兑换包（中）', '油箱');
        var itemname8 = new Array('国王领带3级', '球员经验卡2000点');
        var itemname9 = new Array('战袍5级', '训练卡5000点');
        var itemname10 = new Array('爵士眼镜3级', '球员经验卡2000点');
        var itemname11 = new Array('战术积分2000点', '球员经验卡2000点');
        var itemname12 = new Array('银狐口香糖4级', '油箱');
        var itemname13 = new Array('合同6级', '训练卡5000点');
        var itemname14 = new Array('希式秒表4级', '油箱');
        var itemname15 = new Array('初级教练合同4级', '油箱');
        var itemname16 = new Array('高级教练合同3级', '训练卡5000点');
        var itemname17 = new Array('手套6级', '训练卡5000点');
        var itemname18 = new Array('汽车5级', '游艇5级', '飞机5级', '训练卡5000点');
        var itemname19 = new Array('教练经验卡1000', '油箱');

        var itemNameArr = new Array(itemname1, itemname2, itemname3, itemname4, itemname5, itemname6, itemname7, itemname8, itemname9, itemname10, itemname11, itemname12, itemname13, itemname14, itemname15, itemname16, itemname17, itemname18, itemname19);
        var itemCodeArr = new Array(cupImg1, cupImg2, cupImg3, cupImg4, cupImg5, cupImg6, cupImg7, cupImg8, cupImg9, cupImg10, cupImg11, cupImg12, cupImg13, cupImg14, cupImg15, cupImg16, cupImg17, cupImg18, cupImg19);
        $.post("BeckhamClub.aspx", { ShowReward: 1 }, function (result) {
            //result = "1|1|1|1|0|1|1|1|1|1|1|1|1|1|1|1|1|1|1|1"; //标志19个按钮+1个大奖的按钮是否亮
            stateArr = result.split('|');
            var bestReward = "<div style='position:absolute;left:320px;top:30px;width:542px;height:81px;background:url(Images/BeckhamClub/tiao_b.png);'></div>";
            bestReward += "<div style='position:absolute;left:340px;top:35px;width:75px;height:75px;background:url(Images/BeckhamClub/dajiang.png);' onmouseover='ShowTipBox(\"最终大奖\",$(this))'></div>";
            //大奖奖励图片显示
            for (var i = 0; i < bestRewardImgArr.length; i++) {
                var itemcode = bestRewardImgArr[i];
                var itemname = bestRewardNameArr[i];
                var itemNum = bestRewardNumArr[i];
                bestReward += "<div style='position:absolute;left:" + (420 + i * 50) + "px;top:60px;width:36px;height:36px;background:url(Images/item/" + itemcode + "s.png);' onmouseover='ShowTipBox(\"" + itemname + "\",$(this))'>";
                if (itemNum > 1) {
                    bestReward += "<div style='text-align:right;font-weight:bold;font-size:10px;color:white;position:absolute;left:0px;top:25px;width:36px;'>x" + itemNum + "</div>";
                }
                bestReward += "</div>";
            }
            if (stateArr[19] == 1) {
                bestReward += "<div class='div_btn2' style='position:absolute;left:790px;top:65px;font-size:13px;' onclick='BeckhamClubObj.getReward(20)'>领取</div>";
            } else if (stateArr[19] == 2) {
                bestReward += "<div class='div_btn2grey' style='position:absolute;left:790px;top:65px;font-size:13px;'>已领取</div>";
            } else {
                bestReward += "<div class='div_btn2grey' style='position:absolute;left:790px;top:65px;font-size:13px;'>领取</div>";
            }
            var nineteenCups = "<div style='position:absolute;left:0px;top:0px;width:542px;height:292px;'>";
            for (var i = 0; i < stateArr.length - 1; i++) {
                nineteenCups += "<div style='position:absolute;left:0px;top:" + (0 + 42 * i) + "px;width:542px;height:42px;'>"; //包含一行里面所有元素的DIV
                nineteenCups += "<div style='position:absolute;left:0px;top:0px;width:542px;height:40px;background:url(Images/BeckhamClub/tiao_s.png);'></div>";
                nineteenCups += "<div style='position:absolute;left:20px;top:5px;height:40px;font-size:25px;font-weight:bold;color:white;'>" + (i + 1) + "<span style='font-size:14px;'>号奖杯</span></div>";
                //奖励图片显示
                for (var j = 0; j < itemCodeArr[i].length; j++) {
                    var itemcode = itemCodeArr[i][j];
                    var itemname = itemNameArr[i][j];
                    nineteenCups += "<div style='position:absolute;left:" + (150 + j * 50) + "px;top:3px;width:36px;height:36px;background:url(Images/item/" + itemcode + "s.png);' onmouseover='ShowTipBox(\"" + itemname + "\",$(this))'>";
                    nineteenCups += "<div style='text-align:right;font-weight:bold;font-size:10px;color:white;position:absolute;left:0px;top:25px;width:36px;'>x2</div></div>";
                }

                if (stateArr[i] == 1) {
                    nineteenCups += "<div class='div_btn2' style='position:absolute;left:470px;top:8px;font-size:13px;' onclick='BeckhamClubObj.getReward(" + (i + 1) + ")'>领取</div>";
                } else if (stateArr[i] == 2) {
                    nineteenCups += "<div class='div_btn2grey' style='position:absolute;left:470px;top:8px;font-size:13px;'>已领取</div>";
                } else {
                    nineteenCups += "<div class='div_btn2grey' style='position:absolute;left:470px;top:8px;font-size:13px;'>领取</div>";
                }
                nineteenCups += "</div>";
            }
            nineteenCups += "</div>";

            //放入滚动条中  320 113
            var nineteenCupsSc = "";
            nineteenCupsSc += "<div class='Container' style='position:absolute;left:320px;top:113px;width:542px;height:292px;'>";
            nineteenCupsSc += "<div id='Scroller-Game-ph' style='left:0px;width:542px;height:292px;position:absolute;overflow:hidden;'>";
            nineteenCupsSc += "<div class='Scroller-Container'>";
            nineteenCupsSc += "<div style='top:-7px;left:0px;height:" + 42 * (19) + "px;'></div>" + nineteenCups + "</div></div>";
            nineteenCupsSc += "<div id='Scrollbar-Container-ph' style='width:10px;height:292px;position:absolute;left:550px;top:-4px;'><div class='Scrollbar-Up'></div>";
            nineteenCupsSc += "<div class='Scrollbar-Track2' style='height:290px;'></div><div class='Scrollbar-Down'></div>";
            nineteenCupsSc += "<div class='Scrollbar-Track' style='height:295px;'><div class='Scrollbar-Handle'></div></div>";
            nineteenCupsSc += "</div></div>";

            var readme = "<div style='position:absolute;left:25px;top:305px;width:275px;height:125px;font-size:16px;line-height:30px;color:white;' onclick='BeckhamClubObj.GotoBeckhamClub()'>球会成员通过打小贝副本获得奖杯，根据奖杯数可以获得对应的奖励，全球会成员共享领取权限，激活所有奖杯，所有球会成员均可获得最终大奖！</div>";
            var backBtn = "<div class='div_btn4' style='position:absolute;left:783px;top:425px;font-size:13px;'  onclick='BeckhamClubObj.GotoBeckhamClub()'>返回</div>";
            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div style='width:910px;height:459px;background:url(Images/BeckhamClub/BeckhamClubBg3.jpg);position:absolute;left:6px;top:0px;'>" + bestReward + nineteenCupsSc + readme + backBtn + "</div>"
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(40, 85, 925, 494, "印象•万人迷", html, $('#totalMainDiv'), "确定", null, "BeckhamClubUI", false, true, null)
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件

            //添加滚动条时 必要的代码
            var scrollbar = null;
            scroller = new jsScroller(document.getElementById("Scroller-Game-ph"), 542, 292);
            scrollbar = new jsScrollbar(document.getElementById("Scrollbar-Container-ph"), scroller, false);
        });
    }

    /*
    领取事件
    */
    this.getReward = function (index) {
        $.post("BeckhamClub.aspx", { getReward: index }, function (result) {
            //result = 1;
            if (result == 1) {
                ShowMsg("<div style='width:100%;text-align:center;font-size:14px;'>领取成功！</div>", null, function () { BeckhamClubObj.ShowReward(); }, "温馨提示", function () { BeckhamClubObj.ShowReward(); }, null, 340, 120, $('#BeckhamClubUI'), 'divMsg2');
            } else if (result == -1) {
                ShowMsg("<div style='font-size:13px;'>很抱歉，您入会不满48小时，<br>不能领取奖励！</div>", null, function () { BeckhamClubObj.ShowReward(); }, "温馨提示", function () { BeckhamClubObj.ShowReward(); }, null, 325, 120, $('#BeckhamClubUI'), 'divMsg2');
            } else {
                ShowMsg("领取失败，请重新操作", null, function () { BeckhamClubObj.ShowReward(); }, "温馨提示", function () { BeckhamClubObj.ShowReward(); }, null, 325, 120, $('#BeckhamClubUI'), 'divMsg2');
            }
        });
    }
}