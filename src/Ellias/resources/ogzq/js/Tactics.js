function Tactics() {
        this.KCarr = new Array("21|90", "163|10", "305|90", "163|172", "20|252", "305|252", "163|335");
        this.seconds = new Array(0, 0, 0, 0, 0, 0, 0);
        this.ClickCount = 1, this.PgIndex = 0, this.PageCounts = 0, this.TableCounts = 16;   //点击页数，当前页数, 总道具个数，单页总道具个数,
        this.GotoTactics = function () {
            $.post("Tactics.aspx", { Load: 1 }, function (res) {

                if (res.split('^')[0] == -1) {
                    ShowMsg("40级才可进入", null, null, null, null, 184, 300, 150, null, 'enterdiv');
                    return;
                }

                //                res = "1|阿韦洛亚|后卫|France/01/10104.png|577|3|2000|10001|100000|1|2*1|阿韦洛亚|后卫|France/01/10104.png|577|3|2000|10001|100000|1|2*1|阿韦洛亚|后卫|France/01/10104.png|577|3|2000|10001|100000|1|2*1|阿韦洛亚|后卫|France/01/10104.png|577|3|2000|10001|100000|1|2*1*1|阿韦洛亚|后卫|France/01/10104.png|577|3|2000|10001|100000|1|2*1^8@10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5";
                //
                //        res = "球员id|球员姓名|球员位置|球员头像地址|球员综合实力|球员品质|球员playerdataid|总产出值|1：未解锁,2：解锁无球员,3：有球员未采集,4：有球员采集中|剩余采集时间|是否可升级|卡槽等级^卡片数量@球员id|球员姓名|球员位置|球员头像地址|球员综合实力|球员品质"
                var arr = res.split('^');
                var arr1 = arr[1].split('@');

                Tacticsobj.PageCounts = arr1[0];

                var playerKC = arr[0].split('*');
                var playerPL = arr1[1].split('*');

                Tacticsobj.PgIndex = 0;

                var html = "<div id='div_tactics' style='width:883px;height:497px;left:5px;background:url(Images/Tactics/bg.jpg);position:absolute;'></div>";
                html += "<div id='showDiv' style='position:absolute;display:none;z-index:10'></div>";

                for (var i = 0; i < 7; i++) {
                    html += Tacticsobj.BindKC(playerKC[i], i);
                }

                html += "<div id='divFlip' style='left:588px;top:503px;position:absolute;color:white;'></div>"
                html += "<div  style='left:506px;top:0px;width:384px;position:absolute;'>";
                if (arr1[1] != '') {
                    html += "<div id = 'BindLBdiv'>" + Tacticsobj.BindLB(playerPL) + "</div>";
                }
                html += "</div>";
                AddBox(120, 50, 895, 560, "<div style='position:absolute;left:215px;top:4px'>战术中心</div>", html, null, null, null, 'tacticdiv1', null, null, null);
                $('#divFlip').html(PagingHTML1(Tacticsobj.PgIndex, Tacticsobj.PageCounts, Tacticsobj.TableCounts, 'Tacticsobj', false) + "<input id='txtGoIndex' type='text' onkeyup='MoveStr($(\"#txtGoIndex\"))' onkeypress='if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false' style='width:29px;height:15px;overflow:hidden;border:0;position:absolute;left:227px;top:2px' />");
                $('#tacticdiv1 .div_player').bind('mouseover', function () {
                    Tacticsobj.fanchang(1, $('#tacticdiv1 .div_player').index($(this)));
                });
            });
        }
        this.TurnPage = function (intPgIndex) {

            Tacticsobj.PgIndex = intPgIndex;
            $.post("Tactics.aspx", { PageIndex: Tacticsobj.PgIndex }, function (res) {
//                res = "10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5*10001|阿韦洛亚|后卫|France/01/10104.png|577|5";

                var playerPL = res.split('*');

                $('#BindLBdiv').html(Tacticsobj.BindLB(playerPL)); //通用分页HTML（当前页索引,总道具数,每页的显示数量,类的实例化名,是否有首页末页按钮）
                $('#divFlip').html(PagingHTML1(Tacticsobj.PgIndex, Tacticsobj.PageCounts, Tacticsobj.TableCounts, 'Tacticsobj', false) + "<input id='txtGoIndex' type='text' onkeyup='MoveStr($(\"#txtGoIndex\"))' onkeypress='if((event.keyCode<48 || event.keyCode>57) && event.keyCode!=46 || /\.\d\d$/.test(value))event.returnValue=false' style='width:29px;height:15px;overflow:hidden;border:0;position:absolute;left:227px;top:2px' />");
            })
        }
        this.BindLB = function (playerPL) {
            var html = "";

            for (var v = 0; v < playerPL.length; v++) {
                html += "<div style='left:" + (16 + (v % 4) * 90) + "px;top:" + ((v >= 12) ? 383 : (v >= 8) ? 260 : (v >= 4) ? 137 : 14) + "px;position:absolute;'>" + Tacticsobj.BindPL(playerPL[v], v) + "</div>";
            }
            return html;
        }
        this.BindKC = function (playerKC, i) {
            var arr = playerKC.split('|');
            var name = arr[1];
            var position = arr[2];
            var headurl = arr[3];
            var power = parseInt(arr[4]);
            var level = arr[5];
            var playerdataid = arr[6];
            var produce = arr[7];
            var state = arr[8];
            var time = arr[9];
            var levelup = arr[10];
            var KClevel = arr[11];


            var leftPX = parseInt(Tacticsobj.KCarr[i].split('|')[0]);
            var topPX = parseInt(Tacticsobj.KCarr[i].split('|')[1]);
            var needlevel = 0;
            var needcoin = 0;
            var needtraining = 0;

            var html = "<div style='position:absolute;width:172px;height:151px;left:" + leftPX + "px;top:" + topPX + "px';border:1px red solid;>";

            if (i == 3) {
                needlevel = 45;
                needcoin = 5000;
                needtraining = 10000;
            } else if (i == 4) {
                needlevel = 50;
                needcoin = 10000;
                needtraining = 30000;
            } else if (i == 5) {
                needlevel = 55;
                needcoin = 20000;
                needtraining = 60000;
            } else if (i == 6) {
                needlevel = 60;
                needcoin = 50000;
                needtraining = 100000;
            }
            if (state != 4) {
                Tacticsobj.seconds[i] = 0;
            }
            if (state == 1) {
                html += "<div id='lockbgdiv" + i + "' style='width:172px;height:151px;background:url(Images/Tactics/lockbg.png);'></div>";
                html += "<div id='div_lock1" + i + "' onmouseover='Tacticsobj.showVS(" + needlevel + ",1," + leftPX + "," + topPX + ")' onmouseout='Tacticsobj.showVS(0,0,0,0)' onclick='Tacticsobj.KClock(" + needlevel + "," + needcoin + "," + needtraining + "," + i + ")' style='left:70px;top:75px;position:absolute;width:30px;height:29px;background:url(Images/MiddleMan/lock.png);cursor:pointer;'></div>";

            } else {
                html += "<div id='level_div" + i + "' style='width:172px;height:151px;background:url(Images/Tactics/lv" + KClevel + "bg.png);'></div>";
                if (state != 2) {
                    if (levelup == 1) {
                        html += "<div onclick='Tacticsobj.levelup(" + (parseInt(KClevel) + 1) + "," + state + "," + i + ")' id='uplevel_div1" + i + "' style='left:142px;top:72px;width:11px;height:14px;position:absolute;background:url(Images/Training/up.png);cursor:pointer'></div>";
                    }

                    if (state == 3 || state == 4) {
                        html += "<div id='gatherdiv3" + i + "' class = 'div_btn2' onclick='Tacticsobj.gather(" + produce + "," + i + ")' style='left:60px;top:122px;position:absolute;display:" + (state == 3 ? "block" : "none") + ";'>采集</div>";
                        html += "<div id='timediv1" + i + "' style='display:" + (state == 3 ? "none" : "block") + ";'><div id='timediv2" + i + "' style='left:55px;top:122px;position:absolute;color:white;font-size:15px;font-weight:bold;'>" + getTimeStr(Tacticsobj.seconds[i], 5) + "</div><div class='div_btnArrow' onclick='Tacticsobj.cooling(" + i + ")' style='left:67px;top:2px;position:absolute;left:125px;top:122px'></div></div>";

                        if (state == 4) {
                            Tacticsobj.setinterval(time, i);
                        }
                    }
                    html += "<div onmouseover='Tacticsobj.fanchang(1, " + i + ")' onmouseout='Tacticsobj.fanchang(0, " + i + ")' style='left:46px;top:8px;height:100px;width:75px;position:absolute;display:block;overflow:hidden;' class='div_store1'>" + CreatePlayer(power, position, headurl, name, level, null, 0, 0, 'div1', playerdataid) + "</div><div style='left:32px;top:102px;width:108px;text-align:center;color:white;font-size:15px;font-weight:bold;position:absolute;'>" + produce + "积分</div>"
                    html += "<div class='div_store1' style='position:absolute;left:48px;top:96px;width:74px;'><div id='button" + i + "'  class='div_btn4' onclick='Tacticsobj.getbacksure(1," + i + ")' onmouseover='Tacticsobj.fanchang(1, " + i + ")' onmouseout='Tacticsobj.fanchang(0, " + i + ")' style='left:0px;top:0px;width:74px;position:absolute;display:none'>重返赛场</div></div>";
                } else {
                    if (levelup == 1) {
                        html += "<div onclick='Tacticsobj.levelup(" + (parseInt(KClevel) + 1) + "," + state + "," + i + ")' id='uplevel_div2" + i + "' class='div_btn2' style='left:60px;top:70px;position:absolute;'>升级</div>";
                    }

                }

            }
            html += "</div>";
            return html;
        }
        this.getbacksure = function (type, i) {

            $.post("Tactics.aspx", { getbacksure: type, KCindex: i }, function (res) {
                if (type == 1) {
                    if (res == "0") {
                        ShowMsg("您确认返场？", null, function () { Tacticsobj.getback(i, res); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbacksurediv1');
                    } else {
                        ShowMsg("确认花费" + res + "金币返场？", null, function () { Tacticsobj.getback(i, res); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbacksurediv1');
                    }
                } else if (type == 2) {
                    if (res == "0") {
                        ShowMsg("您确认返场？", null, function () { Tacticsobj.getbackLB(i, res); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbacksurediv2');
                    } else {
                        ShowMsg("确认花费" + res + "金币返场？", null, function () { Tacticsobj.getbackLB(i, res); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbacksurediv2');
                    }
                }
            });

        }
    this.getback = function (KCindex, gold) {

        $.post("Tactics.aspx", { getback: 1, KCindex: KCindex }, function (res) {

            if (res == -1) {
                OpenPayMsg("您的金币不足，需要立即充值吗？");
            } else if (res == -10) {
                ShowMsg("替补位已满", null, function () { Tacticsobj.GotoTactics(); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbackdiv1');
            } else if (res == -8) {
                ShowMsg("该球员已挂靴，不能返场", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv1');
            } else if (res == 1) {
                publicAddGSEL(1, -gold);
                ShowMsg("返场成功", null, function () { Tacticsobj.GotoTactics(); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbackdiv1');
            }
        });
    }
    this.getbackLB = function (playerid, gold) {
        $.post("Tactics.aspx", { getbackLB: 1, playerid: playerid }, function (res) {
            if (res == -1) {
                OpenPayMsg("您的金币不足，需要立即充值吗？");
            } else if (res == -10) {
                ShowMsg("替补位已满", null, function () { Tacticsobj.GotoTactics(); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbackdiv1');
            } else if (res == -8) {
                ShowMsg("该球员已挂靴，不能返场", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv1');
            } else if (res == 1) {
                publicAddGSEL(1, -gold);
                ShowMsg("返场成功", null, function () { Tacticsobj.GotoTactics(); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'getbackLBdiv1');
            }
        });
    }
    this.fanchang = function (type, i) {
        if (type == 1) {
            clearInterval(Tacticsobj.time_out);
            $('#tacticdiv1 .div_store1 .div_btn4').css("display", "none");
            $('#button' + i).css("display", "block");
        } else if (type == 0) {
            Tacticsobj.setTimeout(i);
        } else if (type == 3) {
            clearInterval(Tacticsobj.time_out);
            $('#tacticdiv1 .div_store1 .div_btn4').css("display", "none");
            $('#button1' + i).css("display", "block");
        } else if (type == 2) {
            Tacticsobj.setTimeout(i);
        }
    }
    this.setTimeout = function (i) {
        clearInterval(Tacticsobj.time_out);
        Tacticsobj.time_out = setTimeout(function () { Tacticsobj.buttonmove(i) }, 500);
    }
    this.buttonmove = function (i) {
        $('#button' + i).css("display", "none");
        $('#button1' + i).css("display", "none");
    }
    this.setinterval = function (time, i) {
        Tacticsobj.seconds[i] = time;
        clearInterval(Tacticsobj.time_end);
        Tacticsobj.time_end = setInterval(function () { Tacticsobj.timeminus(i) }, 1000);
    }
    this.time_out;
    this.time_end;
    this.timeminus = function (i) {
        for (var v = 0; v < 7; v++) {
            if (Tacticsobj.seconds[v] != 0) {
                $('#timediv2' + v).html(getTimeStr(Tacticsobj.seconds[v] -= 1, 5));
                if (Tacticsobj.seconds[v] == 0) {
                    Tacticsobj.GotoTactics();
                }
            }
        }
    }
    this.BindPL = function (playerPL, v) {
        var arr = playerPL.split('|');
        var name = arr[1];
        var position = arr[2];
        var headurl = arr[3];
        var power = parseInt(arr[4]);
        var level = arr[5];
        var playerdataid = arr[6];
        var playerid = arr[7];
        var html = "<div onmouseover='Tacticsobj.fanchang(3, " + v + ")' onmouseout='Tacticsobj.fanchang(2, " + v + ")' style='left:0px;top:0px;height:119px;width:75px;position:absolute;display:block;overflow:hidden' class='div_store1'>" + CreatePlayer(power, position, headurl, name, level, null, 0, 0, 'div1', playerdataid) + "<div id='button1" + v + "'  class='div_btn4' onclick='Tacticsobj.getbacksure(2," + playerid + ")' onmouseover='Tacticsobj.fanchang(3, " + v + ")' onmouseout='Tacticsobj.fanchang(2, " + v + ")' style='left:0px;top:96px;width:74px;position:absolute;display:none'>重返赛场</div></div>";
        return html;
    }
    this.levelup = function (KClevel, state, i) {
        var gold = 0;
        if (KClevel == 2) {
            gold = 200;
        } else if (KClevel == 3) {
            gold = 400;
        }
        ShowMsg("确认花费" + gold + "金币，升级该卡槽？", null, function () { Tacticsobj.levelupsure(KClevel, state, i,gold); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'levelupdiv1');
    }
    this.levelupsure = function (KClevel, state, i, gold) {
        $('#levelupdiv1').remove();
        $.post("Tactics.aspx", { Levelup: 1, KClevel: KClevel, KCindex: i }, function (res) {
            if (res == -1) {
                OpenPayMsg("您的金币不足，需要立即充值吗？");
            } else {
                publicAddGSEL(1, -gold);
                Tacticsobj.GotoTactics();
                $('#level_div' + i).css("background", "url(Images/Tactics/lv" + KClevel + "bg.png)");
                if (state == 2) {
                    $('#uplevel_div2' + i).remove();
                } else {
                    $('#uplevel_div1' + i).remove();
                }
            }
        });
    }
    this.check = 0;
    this.gather = function (produce, i) {
        $.post("Tactics.aspx", { Gathertime: 1 }, function (res) {
            Tacticsobj.check = 0;
            var msg = "<div style='top:10px;width:328px;left:0;position:absolute;text-align:center;font-size:14px;'>你收获" + produce + "战术积分</div>";
            msg += "<div style='position:absolute;left:0;top:44px;width:334px;height:2px;background:url(Images/Tactics/oneline.png) no-repeat;overflow:hidden;text-align:center'></div>"
            msg += "<div style='top:50px;width:328px;left:0;position:absolute;text-align:center;font-size:12px;color:#005c01;'>花费100金币</div>";
            if (res == 0) {
                msg += "<div style='position:absolute;width:334px;top:70px;left:0;color:#005b04;font-size:16px;font-weight:bold;text-align:center'><input onchange='Tacticsobj.checked();' id='checkbox1' type='checkbox' name='produce' />可收获双倍" + (produce * 2) + "战术积分</div>"
            } else if (res == 1) {
                msg += "<div style='position:absolute;width:334px;top:70px;left:0;color:#005b04;font-size:16px;font-weight:bold;text-align:center'><input onchange='Tacticsobj.checked();' id='checkbox1' type='checkbox' name='produce' />可收获三倍" + (produce * 3) + "战术积分</div>"
            }
            ShowMsg(msg, null, function () { Tacticsobj.gathersure(produce, i,res); }, "采集积分", null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv');
        });
    }
    this.checked = function () {

        var temp = document.getElementsByName("produce");

        if (temp[0].checked) {
            Tacticsobj.check = 1;
        } else {
            Tacticsobj.check = 0;
        }

    }
    this.gathersure = function (produce, i, state) {
        $.post("Tactics.aspx", { Gather: 1, gatheraward: produce, checkbox: Tacticsobj.check, Gatherindex: i }, function (res) {
            if (res == -1) {
                OpenPayMsg("您的金币不足，需要立即充值吗？");
            }  else {
                $('#gatherdiv').remove();
                $('#gatherdiv3' + i).css("display", "none");
                $('#timediv1' + i).css("display", "block");
                Tacticsobj.seconds[i] = 86400;
                $('#timediv2' + i).html(getTimeStr(Tacticsobj.seconds[i], 5));
                Tacticsobj.setinterval(Tacticsobj.seconds[i], i)
                if (Tacticsobj.check == 0) {
                    ShowMsg("基础采集成功", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv1');
                } else if (Tacticsobj.check == 1) {
                    publicAddGSEL(1, -100);
                    if (state == 1) {
                        ShowMsg("三倍采集成功", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv2');
                    } else if (state == 0) {
                        ShowMsg("双倍采集成功", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'gatherdiv2');
                    }
                }
            }
        });

    }

    this.cooling = function (i) {
        $.post("Tactics.aspx", { Coolingbefore: 1, KCindex: i }, function (res) {
            var gold = res;
            ShowMsg("花费" + gold + "金币，立即消除冷却？", null, function () { Tacticsobj.coolingsure(gold, i); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'coolingdiv1');
        });
    }
    this.coolingsure = function (gold, i) {
        $('#coolingdiv1').remove();
        Tacticsobj.seconds[i] = 0;
        $.post("Tactics.aspx", { Cooling: 1, KCindex: i }, function (res) {

            if (res == -1) {
                OpenPayMsg("您的金币不足，需要立即充值吗？");
            } else {
                publicAddGSEL(1, -gold);
                $('#timediv1' + i).css("display", "none");
                $('#gatherdiv3' + i).css("display", "block");
                publicAddGSEL(1, -gold);
            }
        });

    }

    this.showVS = function (level, type, leftPX, topPX) {
        if (type == 1) {

            var strHtml = "<div style='background:url(Images/Arena/ifo_bg.png);width:92px;height:28px;line-height:24px;position:absolute;color:white;font-size:14;font-weight:bold;text-align:center;'>" + level + "级开启</div>";

            $("#showDiv").css('display', 'block').html(strHtml).css('left', (parseInt(leftPX) + 100) + "px").css('top', parseInt(topPX) + 72 + "px");

        }
        else {
            $("#showDiv").css('display', 'none');
        }
    }
    this.KClock = function (level, coin, training, i) {

        $.post("Tactics.aspx", { Lock: 1, KCindex: i }, function (res) {
            if (res == 1) {
                ShowMsg("确认花费" + coin + "银币、" + training + "训练点解锁该卡槽？", null, function () { Tacticsobj.locksure(coin,i); }, null, null, 184, 300, 150, $('#totalMainDiv'), 'lockdiv2');
            } else {
                ShowMsg("" + level + "级开启", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'lockdiv3');
            }
        });

    }
    this.locksure = function (gamecoin, i) {
        $.post("Tactics.aspx", { Locksure: 1, KCindex: i }, function (res) {

            if (res == -1) {
                ShowMsg("您的银币与训练点不足", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'locksurediv1');
                $('#lockdiv2').remove();
            } else if (res == -2) {
                ShowMsg("您的银币不足", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'locksurediv2');
                $('#lockdiv2').remove();
            } else if (res == -3) {
                ShowMsg("您的训练点不足", null, null, null, null, 184, 300, 150, $('#totalMainDiv'), 'locksurediv3');
                $('#lockdiv2').remove();
            } else {
                publicAddGSEL(2, -gamecoin);
                Tacticsobj.GotoTactics();
                $('#lockdiv2').remove();
                $('#div_lock1' + i).remove();
                $('#lockbgdiv' + i).css("background", "url(Images/Tactics/lv1bg.png)");
            }

        });
    }

}