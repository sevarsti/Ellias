/*
巅峰挑战活动  张旭
*/
function TopChallenge() {
    /*
    显示活动活动的界面  张旭
    */
    this.GotoTopChallenge = function () {
        $.post("TopChallenge.aspx", { type: 1 }, function (result) {
            //result = "大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@大内密探01|10@金块队|10@国米战队|10@国米战队|10^1500";
            //var ActivityTimeStr = result.split('╋')[1]; //活动时间
            //result = result.split('╋')[0];

            //比赛中
            if (result.indexOf('inmatch|') != -1) {// 有比赛，页面其他信息无需加载
                var matchinfo = result.split('|');
                showMatch(matchinfo[1], matchinfo[2]);
                return;
            }

            var last_times = result.split('^')[1];
            var playerNameArr = new Array("诺伊尔", "博阿滕", "拉姆", "丹特", "阿拉巴", "马丁内斯", "施魏因施泰格", "罗本", "穆勒", "里贝里", "曼朱基奇");
            var leftArr = new Array(382, 215, 316, 458, 573, 339, 465, 277, 395, 516, 396);
            var topArr = new Array(412, 358, 358, 358, 358, 282, 282, 221, 221, 221, 148);
            var playerNames = "";
            for (var i = 0; i < 11; i++) {
                playerNames += "<div  style='left:" + leftArr[i] + "px;top:" + topArr[i] + "px;width:72px;text-align:center;position:absolute;font-size:12px;color:#C9D8ED;'>" + playerNameArr[i] + "</div>";
            }
            //活动时间
            var activityTime = "<div style='position:absolute;left:100px;top:110px;width:318px;text-align:center;font-size:12px;font-weight:bold;color:#C9D8ED;'>【活动时间】:11月1日12:00至24:00,2日12:00至24:00</div>";
            //活动内容
            var activityDesc = "<div  style='left:15px;top:150px;width:200px;height:200px;position:absolute;font-size:14px;color:#C9D8ED;'>";
            activityDesc += "<div>活动内容：<br>每次战胜NPC队伍都会获得小奖励，Boss被最终战胜后排名前三的队伍都会获得相应的奖励：</div>";
            activityDesc += "<div  style='position:absolute;left:20px;top:80px;width:36px;height:36px;background:url(Images/Item/10295s.png);'onmouseover='ShowTipBox(\"欧冠之魂x10<br>1-8级合同<br>袖标4级x1<br>签名积分10000点\",$(this))'></div>";
            activityDesc += "<div  style='position:absolute;left:70px;top:80px;width:36px;height:36px;background:url(Images/Item/10296s.png);'onmouseover='ShowTipBox(\"欧冠之魂x5<br>1-7级合同<br>袖标4级x1<br>签名积分8000点\",$(this))'></div>";
            activityDesc += "<div  style='position:absolute;left:120px;top:80px;width:36px;height:36px;background:url(Images/Item/10297s.png);'onmouseover='ShowTipBox(\"欧冠之魂*1<br>1-7级合同<br>袖标4级x1<br>签名积分6000点\",$(this))'></div>";
            activityDesc += "<div  style='position:absolute;top:120px;'>而最后一个击败NPC队伍的球队将获得终极击败大奖：</div>";
            activityDesc += "<div  style='position:absolute;left:70px;top:160px;width:36px;height:36px;background:url(Images/Item/10299s.png);'onmouseover='ShowTipBox(\"袖标4级x1<br>1-7级合同<br>签名积分2000点\",$(this))'></div>";
            activityDesc += "</div>";
            //排名信息
            var rankHtml = "<div  style='left:680px;top:20px;width:215px;height:270px;position:absolute;color:#FCFF02;font-size:18px;'>";
            rankHtml += "<div style='position:absolute;left:0px;top:0px;'>排名</div>";
            rankHtml += "<div style='position:absolute;left:60px;top:0px;'>球队名</div>";
            rankHtml += "<div style='position:absolute;left:150px;top:0px;'>进球数</div>";
            if (result.split('^')[0].lastIndexOf('|') > 0) {
                var rankInfoArr = result.split('^')[0].split('@');
                var teamName = ""; var ballNum = "";
                for (var i = 0; i < (rankInfoArr.length > 10 ? 10 : rankInfoArr.length); i++) {
                    teamName = rankInfoArr[i].split('|')[0];
                    ballNum = rankInfoArr[i].split('|')[1];
                    rankHtml += "<div style='width:100%;height:24px;position:absolute;top:" + (30 + 24 * i) + "px;line-height:20px;font-size:14px;color:white;'>";
                    rankHtml += "<div style='position:absolute;left:12px;top:0px;'>" + (i + 1) + "</div>";
                    rankHtml += "<div style='position:absolute;left:60px;top:0px;width:85px;white-space:nowrap;overflow: hidden;'>" + teamName + "</div>";
                    rankHtml += "<div style='position:absolute;left:165px;top:0px;'>" + ballNum + "</div>";
                    rankHtml += "</div>";
                }
            }
            rankHtml += "</div>";
            //剩余挑战次数
            var lastTimes = "<div  style='left:700px;top:300px;position:absolute;color:#FCFF02;font-size:18px;'>剩余挑战次数:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</div>";
            lastTimes += "<div  style='left:820px;top:300px;width:60px;position:absolute;color:#FCFF02;font-size:18px;text-align:center;'>" + last_times + "</div>";
            //挑战按钮
            var challengeButton = "";
            if (last_times > 0) {
                challengeButton += "<div id='challengeButton' style='left:750px;top:360px;position:absolute;width:110px;height:37px;background:url(Images/TopChallenge/butter-1.png);color:#946000;font-weight:bold;font-size:20px;text-align:center;line-height:35px;cursor:pointer;' onclick='TopChallengeObj.ClickChallenge()'>挑战</div>";
            } else if (last_times <= 0) {
                challengeButton += "<div style='left:750px;top:360px;position:absolute;width:110px;height:37px;background:url(Images/TopChallenge/butter-1.png);color:#946000;font-weight:bold;font-size:20px;text-align:center;line-height:35px;cursor:pointer;'>挑战</div>";
            }


            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div style='width:909px;height:457px;background:url(Images/TopChallenge/bg_jq.jpg);position:absolute;left:6px;top:0px;'>" + playerNames + activityDesc + activityTime + rankHtml + lastTimes + challengeButton + "</div>"
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(40, 85, 925, 494, "巅峰挑战", html, $('#totalMainDiv'), "确定", null, "TopChallengeUI", false, true, null)
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件
            if (last_times > 0) {
                $("#challengeButton").hover(function () { $(this).css('background', 'url(Images/TopChallenge/butter.png)'); }, function () { $(this).css('background', 'url(Images/TopChallenge/butter-1.png)'); });
            }
            $("#TopChallengeUI").css('display', 'none').fadeIn("fast");
        });
    }

    //点击挑战按钮
    var timeId = 0;
    this.ClickChallenge = function () {
        $.post("TopChallenge.aspx", { type: 2 }, function (result) {
            //result = "1|110";
            //第一位是1时，表示可以挑战，后一位是metaID
            //第一位是0时，表示不立即挑战，后一位是冷却的秒数
            if (result.split('|')[0] == 1) {//可以直接挑战
                var matchId = result.split('|')[1];
                //弹出比赛引擎动画
                showMatch(matchId, 31);
                $("#TopChallengeUI").remove();
            } else if (result.split('|')[0] == 0) {//需要冷却
                //倒计时
                var secs = result.split('|')[1];
                var time = "<div style='position:absolute;left:110px;top:14px;color:black;font-size:14px;'>冷却时间：</div>";
                time += "<div id='time' style=';position:absolute;top:14px;left:175px;color:red;font-size:14px;Text-align:center'></div>";
                ShowMsg(time, "立即挑战", function () {
                    $("#divMsg2").remove();
                    window.clearInterval(TopChallengeObj.timeId);
                    TopChallengeObj.stopCD(); //调用结束冷却的方法

                }, "温馨提示", function () { window.clearInterval(TopChallengeObj.timeId); }, null, 270, 220, $('#TopChallengeUI'), 'divMsg2', false, '取消');
                //--------------------------------------倒计时--------------------------------
                window.clearInterval(TopChallengeObj.timeId);
                TopChallengeObj.timeId = window.setInterval(function () {
                    $("#time").html(getTimeStr(secs -= 1, 2));
                    if (secs == 0) {
                        window.clearInterval(TopChallengeObj.timeId);
                    }
                }, 1000);
                //--------------------------------------倒计时--------------------------------
            }
        });
    }

    //结束冷却
    this.stopCD = function () {
        $.post("TopChallenge.aspx", { type: 3 }, function (result3) {
            if (result3 == 0) {
                //弹出比赛引擎动画
                TopChallengeObj.ClickChallenge();
                $("#TopChallengeUI").remove();
            } else if (result3 > 0) {
                ShowMsg("是否花费" + result3 + "金币结束冷却？", "确定", function () {
                    $.post("TopChallenge.aspx", { type: 4 }, function (result4) {
                        if (result4 == -1) {//钱不够
                            OpenPayMsg();
                        } else {
                            publicAddGSEL(1, -parseInt(result3));
                            var matchId = result4;
                            //弹出比赛引擎动画
                            showMatch(matchId, 31);
                            $("#TopChallengeUI").remove();
                        }
                    });
                }, "温馨提示", function () { window.clearInterval(TopChallengeObj.timeId); }, null, 270, 220, $('#TopChallengeUI'), 'divMsg2', false, '取消');
            } else if (result3 == -1) {
                ShowMsg("上次挑战尚未结束，请稍后挑战！", null, function () { $("#divMsg2").remove(); }, "温馨提示", function () { }, null, 270, 220, $('#TopChallengeUI'), 'divMsg2');
            }

        });
    }

    //点击离开比赛时弹出奖励提示框
    this.LiveGameTip = function () {
        $.post("TopChallenge.aspx", { type: 5 }, function (result) {
            if (result == 1) {
                var sliverCoin = "";
                var trainPoint = "";
                var shengWang = "";
                publicAddGSEL(2, parseInt(sliverCoin));
                TopChallengeObj.GotoTopChallenge();
                ShowMsg("恭喜您获得" + sliverCoin + "银币," + trainPoint + "训练点," + shengWang + "声望！", null, function () { $("#divMsg2").remove(); }, "温馨提示", function () { }, null, 270, 220, $('#TopChallengeUI'), 'divMsg2');
            }
        });
    }
}