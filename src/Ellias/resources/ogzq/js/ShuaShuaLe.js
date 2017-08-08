//刷刷乐 活动
//每次重上需要注意修改的地方：1.活动时间activityTime 2.ZheKouImgs数组是否有变化(该数组的下标要和后台传过来的整数数字对应起来，); 3.活动时间：activityTime
function ShuaShuaLe() {

    //-----------------------------------------------以下是加强版的刷刷乐-----只是截面图改了，其他方法一样------------------------------------------------------------------------
    this.GotoShuaShuaLe = function (typeIndex) {
        //typeIndex:1普通版    2加强版    3豪华版
        var Name = "刷刷乐";
        var Bg_Main = 'ShuaShuaLeBg_jq'; var Frame_Card = 'Frame_jq';
        if (typeIndex == null) { typeIndex = 2; }
        if (typeIndex == 1) {
            Bg_Main = 'ShuaShuaLeBg'; Frame_Card = 'Frame';
        } else if (typeIndex == 2) {
            Bg_Main = 'ShuaShuaLeBg_jq'; Name = '刷刷乐加强版';
        } else if (typeIndex == 3) {
            Bg_Main = 'ShuaShuaLeBg_hh'; Name = '刷刷乐豪华版';
        }
        $.post("ShuaShuaLe.aspx", { load: 1 }, function (result) {
            //result格式：左侧6个道具的信息@荣耀榜玩家信息@剩余次数@刷新（0免费,1花金币）@6个道具是否已购买
            //            道具信息格式： ItemCode|des|原价|现价|折扣（0-8）*ItemCode|des|原价|现价|折扣（0-8）*ItemCode|des|原价|现价|折扣（0-8）*...ItemCode|des|原价|现价|折扣（0-8）
            //            荣耀榜格式：clubname|itemname*clubname|itemname*....
            //ItemCode|des|原价|现价|折扣（0-8）*...*ItemCode|des|原价|现价|折扣（0-8）@clubname|itemname*...*clubname|itemname@剩余次数@刷新（0免费,1花金币）@1|1|1|1|1|1（0已购买，1可购买）
            //result = "1704|战靴4级|800|208|2*1905|油箱|100|46|4*1705|战靴5级|4000|1440|0*1505|战袍5级|4000|640|0*2803|高级教练合同3级|90|33|3*1505|战袍5级|4000|1040|2@球队名|欧冠之魂*球队名2|合同1级@10@0@1|1|1|1|1|1╋12月29日10点|12月30日24点";
            var ActivityBeginTime = result.split('╋')[1].split('|')[0]; //活动开始时间
            var ActivityEndTime = result.split('╋')[1].split('|')[1]; //活动结束时间
            result = result.split('╋')[0];

            var cardLefts = new Array('25', '230', '435');
            var cardTops = new Array('157', '285');
            var ZheKouImgs = new Array('0', '0.6', '1.6', '2.6', '3.6', '4.6', '5.6', '6.6', '7.6', '8.6');
            if (result == -1) {
                return;
            }
            var resultArr = result.split('@');
            var allTools = resultArr[0].split('*'); //六个卡片上的道具的信息
            var buyNum = resultArr[2]; // 剩余购买的次数
            var isFree = resultArr[3]; //是否免费刷新
            var isBuyed = resultArr[4].split('|'); //六个道具是否已购买

            //所有的六个卡片
            var Cards = "";
            for (var i = 0; i < allTools.length; i++) {
                var oneCard = allTools[i].split('|');
                Cards += "<div style='width:194px;height:119px;background:url(" + ImgPath + "Images/ShuaShuaLe/" + Frame_Card + ".png);position:absolute;left:" + cardLefts[i % 3] + "px;top:" + cardTops[parseInt(i / 3)] + "px;'>";
                Cards += "<div style='width:64px;height:64px;background:url(" + ImgPath + "Images/Item/" + oneCard[0] + ".png);position:absolute;left:15px;top:17px;'></div>";
                Cards += "<div style='width:47px;height:47px;background:url(" + ImgPath + "Images/ShuaShuaLe/ZheKou/" + ZheKouImgs[oneCard[4]] + ".png);position:absolute;left:148px;top:0px;'></div>";
                Cards += "<div style='position:absolute;left:88px;top:21px;font-size:12px;color:white;line-height:20px;font-weight:bold;width:105px;'>原价：<span style='color:gold;'>" + oneCard[2] + "</span>金币<br>现价：<span style='color:gold;'>" + oneCard[3] + "</span>金币</div>";
                Cards += "<div style='position:absolute;left:15px;top:92px;width:64px;font-size:14px;color:#FFFF10;text-align:center;font-weight:bold;white-space: nowrap;'>" + oneCard[1] + "</div>";
                if (isBuyed[i] == 1) {//itemcode, itemdes, zhekou, price_now, num_buy, i
                    Cards += "<div class='div_btn2Y' style='position:absolute;left:105px;top:65px;font-weight:bold;' onclick='ShuaShuaLeObj.beforeBuy(" + oneCard[0] + ",\"" + oneCard[1] + "\"," + oneCard[3] + "," + oneCard[4] + "," + buyNum + "," + i + "," + typeIndex + ")' >购买</div>";
                } else if (isBuyed[i] == 0) {
                    Cards += "<div class='div_btn2grey' style='position:absolute;left:105px;top:65px;font-weight:bold;'>已购买</div>";
                }
                Cards += "</div>";
            }
            //显示0金币购买到道具的玩家
            var playersMsg = "<div style='width:198px;height:162px;position:absolute;left:685px;top:54px;text-align:left;font-size:12px;color:black;overflow:hidden;'>";
            var freePlayers = ''; //0金币购买到道具的玩家信息
            if (resultArr[1].length > 0) {
                freePlayers = resultArr[1].split('*');
                for (var i = 0; i < freePlayers.length; i++) {
                    var onePlayer = freePlayers[i].split('|');
                    playersMsg += "<div style='width:100%;height:21px;overflow:hidden;white-space: nowrap;letter-spacing:-1px;'><span style='color:white;'>" + onePlayer[0] + "</span>&nbsp;<span style='color:red;font-weight:bold;'>0金币</span>购买到了<span style='color:green;'>" + onePlayer[1] + "</span></div>";
                }
            }
            playersMsg += "</div>";
            //刷新按钮
            var refreshBtn = "<div id='id_refreshBtn' style='position:absolute;left:732px;top:320px;width:106px;height:41px;background:url(" + ImgPath + "Images/ShuaShuaLe/button01.png);letter-spacing:20px;font-weight:bold;font-size:25px;color:#000032;line-height:52px;cursor:pointer;' onclick='ShuaShuaLeObj.Refresh(" + isFree + "," + typeIndex + ")'>";
            refreshBtn += "<div style='position:absolute;left:25px;width:106px;height:41px;letter-spacing:15px;font-weight:bold;font-size:20px;color:#000032;line-height:40px;cursor:pointer;'>刷新</div></div>";
            //页面内的其他文字信息
            var otherFonts = "<div style='text-align:center;font-size:14px;color:white;letter-spacing:4px;position:absolute;left:680px;top:250px;width:212px;line-height:30px;'>剩余购买次数：<span style='color:red;font-weight:bold;'>" + buyNum + "</span>次";
            var font1 = "本次刷新<span style='color:yellow;font-weight:bold;font-size:15px'>免费</span>";
            if (isFree == 1) { font1 = "本次刷新需<span style='color:gold;font-weight:bold;'>10</span>金币"; }
            otherFonts += "<br>" + font1 + "</div>";
            otherFonts += "<div style='text-align:center;font-size:12px;color:yellow;letter-spacing:2px;position:absolute;left:685px;top:385px;width:212px;'>刷新后会出现其他神秘大礼哦！</div>";
            //活动说明
            var activityDesc = "<div class='div_btn4'  style='position:absolute;left:825px;top:430px;' onclick='ShuaShuaLeObj.ShowActivityDes()'>活动说明</div>";
            //活动时间
            var activityTime = "<div style='text-align:center;font-size:14px;font-weight:bold;color:#8C2671;position:absolute;left:155px;top:425px;'>活动时间：" + ActivityBeginTime + "至" + ActivityEndTime + "</div>";
            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div style='width:909px;height:457px;background:url(Images/ShuaShuaLe/" + Bg_Main + ".jpg) -2px -2px;position:absolute;left:5px;top:0px;'>" + Cards + playersMsg + otherFonts + refreshBtn + activityDesc + activityTime + "</div>";
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(60, 80, 922, 492, Name, html, $('#totalMainDiv'), null, null, "ShuaShuaLeUI", false, true, null);
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件
            //$("#ShuaShuaLeUI").css('display', 'none').fadeIn("fast");

            $("#id_refreshBtn").bind({ mouseover: function () { $(this).css({ background: 'url(Images/ShuaShuaLe/button02.png)' }); }, mouseout: function () { $(this).css({ background: 'url(Images/ShuaShuaLe/button01.png)' }); } });
        });
    }

    /*
    显示活动说明
    */
    this.ShowActivityDes = function () {
        var desc = "";
        desc += "<div style = 'position:absolute;top:35px;left:35px;width:520px;font-size:14px;line-height:35px;'>";
        desc += "<div style = 'position:absolute;width:400px;font-size:14px;'>1、点击“刷新”按钮可刷出6款随机道具及随机折扣，玩家有可能刷出<span style = 'color:red;font-weight:bold;font-size:16px;'>免费</span>购买道具的机会。<br>2、每天不限制刷新次数。每天前三次可<span style = 'color:red;font-weight:bold;font-size:16px;'>免费</span>刷新，此后每次刷新需消耗10金币。<br>3、在活动期间内，每人每天限购20次道具。</div></div>";
        var html = createBg(5, 0, 450, 260) + desc;
        AddBox(160, 80, 465, 297, "刷刷乐活动说明", html, $('#ShuaShuaLeUI'), null, null, "ShuaShuaLeDes", false, true, null);
        $("#ShuaShuaLeDes").css('display', 'none').fadeIn("fast");
    }
    /*
    点击刷新前
    */
    this.Refresh = function (isFree, typeIndex) {
        if (parseInt(isFree) == 0) {//免费刷新
            ShuaShuaLeObj.doRefresh(isFree, typeIndex);
        }
        else if (parseInt(isFree) == 1) {//消耗金币刷新
            ShowMsg("<div style='width:100%;text-align:center'>本次刷新将消耗10金币，<br>是否继续？</div>", "确定", function () { ShuaShuaLeObj.doRefresh(isFree, typeIndex); }, "温馨提示", null, null, 220, 160, $('#ShuaShuaLeUI'), 'divChangEMsg', false, '取消');
        }
    }
    /*
    开始刷新
    */
    this.doRefresh = function (isFree, typeIndex) {
        $.post("ShuaShuaLe.aspx", { refresh: 1 }, function (result) {
            //-1金币不足，-2刷新失败，1成功
            //result = "1";
            if (parseInt(result) == 1) {//刷新成功
                if (isFree == 1) {//耗10金
                    ShowMsg("<div style='width:100%;text-align:center'>刷新成功！</div>", "确定", function () {
                        publicAddGSEL(1, -parseInt(10));
                        ShuaShuaLeObj.GotoShuaShuaLe(typeIndex);
                    }, "温馨提示", function () {
                        publicAddGSEL(1, -parseInt(10));
                        ShuaShuaLeObj.GotoShuaShuaLe(typeIndex);
                    }, null, 221, 159, $('#ShuaShuaLeUI'), 'divMsg2', false);
                } else if (isFree == 0) {//免费
                    ShowMsg("<div style='width:100%;text-align:center'>刷新成功！</div>", "确定", function () {
                        ShuaShuaLeObj.GotoShuaShuaLe(typeIndex);
                    }, "温馨提示", function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, null, 221, 159, $('#ShuaShuaLeUI'), 'divMsg2', false);
                }
            } else if (parseInt(result) == -1) {//金币不足
                $("#divChangEMsg").remove();
                OpenPayMsg();
            } else {
                ShowMsg("刷新失败", null, function () { $("#divMsg2").remove(); }, "温馨提示", null, null, 221, 159, $('#ShuaShuaLeUI'), 'divMsg2');
            }
        });
    }

    /*
    购买前
    */
    this.beforeBuy = function (itemcode, itemdes, price_now, zhekou, num_buy, i, typeIndex) {
        if (num_buy == "0" || num_buy == 0) {
            ShowMsg("您的当天购买次数已用光哦！", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, null, 221, 159, $('#ShuaShuaLeUI'), 'divMsg');
        }
        else if (zhekou != "0" || zhekou != 0) {
            ShowMsg("购买此道具需要花费" + price_now + "金币，<br>确定购买？", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.doBuy(itemcode, itemdes, price_now, zhekou, i, typeIndex); }, "温馨提示", null, null, 221, 159, $('#ShuaShuaLeUI'), 'divMsg');
        } else {
            ShuaShuaLeObj.doBuy(itemcode, itemdes, price_now, zhekou, i, typeIndex);
        }
    }
    /*
    确认购买
    */
    this.doBuy = function (itemcode, itemdes, price_now, zhekou, i, typeIndex) {
        $.post("ShuaShuaLe.aspx", { Buy: itemcode + "*" + zhekou + "*" + i }, function (result) {
            //-1金币不足，-2购买失败，1成功，2次数用完
            //result = "1";
            switch (result) {
                case "1":
                    publicAddGSEL(1, -parseInt(price_now));
                    ShowMsg("恭喜您，购买" + itemdes + "成功", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, null, 220, 160, $('#ShuaShuaLeUI'), 'divMsg');
                    break;
                case "-1":
                    OpenPayMsg();
                    break;
                case "-2":
                    ShowMsg("购买失败", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, null, 220, 160, $('#ShuaShuaLeUI'), 'divMsg');
                    break;
                case "2":
                    ShowMsg("您的当天购买次数已用光哦！", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", null, function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, 220, 160, $('#ShuaShuaLeUI'), 'divMsg');
                    break;
                case "-5":
                    ShowMsg("不在活动时间内！", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", null, function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, 220, 160, $('#ShuaShuaLeUI'), 'divMsg');
                    break;
                default:
                    ShowMsg("购买失败", null, function () { $("#divMsg").remove(); ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, "温馨提示", function () { ShuaShuaLeObj.GotoShuaShuaLe(typeIndex); }, null, 220, 160, $('#ShuaShuaLeUI'), 'divMsg');
            }
        });
    }


}



  