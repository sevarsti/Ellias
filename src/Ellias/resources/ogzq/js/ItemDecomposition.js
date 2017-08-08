/*
道具分解 活动 张旭
*/
function ItemDecomposition() {
    /*
    显示活动的界面  张旭
    */
    this.GotoItemDecomposition = function () {
        $.post("ItemDecomposition.aspx", { type: 1 }, function (result) {
            //result:已分解数量*是否可领取欧冠之魂（0：不可领取，1可领取）*是否VIP（0不是，1是）
            //result = "2995*0*1╋12月29日10点|12月30日24点";
            var ActivityBeginTime = result.split('╋')[1].split('|')[0]; //活动开始时间
            var ActivityEndTime = result.split('╋')[1].split('|')[1]; //活动结束时间
            result = result.split('╋')[0];
            var resultArr = result.split('*');
            var DecompositionNum = resultArr[0] <= 0 ? 0 : resultArr[0]; //已分解数量
            var canGet = resultArr[1]; //是否可领取
            var isVIP = resultArr[2]; //是否VIP --------------------------------------------------------------------------------------------------2013.11.11日后改为 没有VIP限制，此变量给他赋予固定值：1
            isVIP = 1;
            //--------------------------------------------以后增加道具和更改分解的道具等级时，只需要修改这三个数组即可
            var ItemNameArr = new Array('合同', '汽车', '游艇', '飞机', '神奇球靴', '希式秒表', '银狐口香糖', '国王领带', '爵士眼镜', '狂人风衣', '初级教练合同', '高级教练合同', '战袍', '护腿', '战靴', '手套');
            var ItemNameValueArr = new Array(110, 120, 130, 140, 210, 220, 230, 240, 250, 260, 270, 280, 150, 160, 170, 180);
            var LevelArr = new Array('1级', '2级', '3级', '4级', '5级', '6级');

            var mainHtml = "<div style='position:absolute;left:210px;top:180px;font-size:18px;color:white;'>选择道具类型</div>";
            mainHtml += "<select id='ItemName'  style='position:absolute;left:330px;top:178px;width:100px;height:27px;font-size:14px;' onchange='ItemDecompositionObj.changeNum()'>";
            for (var i = 0; i < ItemNameArr.length; i++) {
                mainHtml += "<option value='" + ItemNameValueArr[i] + "'>" + ItemNameArr[i] + "</option> ";
            }
            mainHtml += "</select>";
            mainHtml += "<div style='position:absolute;left:210px;top:232px;font-size:18px;color:white;'>选择道具等级</div>";
            mainHtml += "<select id='ItemLevel'  style='position:absolute;left:330px;top:238px;width:100px;height:27px;font-size:14px;letter-spacing:2px' onchange='ItemDecompositionObj.changeNum()'>";
            for (var i = 0; i < LevelArr.length; i++) {
                mainHtml += "<option value='" + (i + 1) + "'>" + LevelArr[i] + "</option> ";
            }
            mainHtml += "</select>";
            mainHtml += "<input id='hidden' type='hidden' value=''/>"; //隐藏域，value用于保存要分解的道具在背包中的总数量
            mainHtml += "<div style='position:absolute;left:210px;top:284px;font-size:18px;color:white;'>输入分解数量</div>";
            mainHtml += "<input id='InputNum' style='position:absolute;left:330px;top:283px;width:98px;height:20px;text-align:left;font-size:14px;' value='1' onkeyup='ItemDecompositionObj.formatNum(this)' onclick='ItemDecompositionObj.GetNum()'/>";
            mainHtml += "<div class='div_btn4' style='left:245px;top:330px;font-size:14px;color:white;position:absolute;cursor:pointer;'onclick='ItemDecompositionObj.doItemDecomposition(2," + isVIP + ")'>100%分解</div>";
            mainHtml += "<div class='div_btn2' style='left:370px;top:330px;font-size:14px;color:white;position:absolute;cursor:pointer;'onclick='ItemDecompositionObj.doItemDecomposition(1," + isVIP + ")'>分解</div>";
            mainHtml += "<div style='position:absolute;left:485px;top:180px;font-size:18px;color:white;'>已分解总数</div>";
            mainHtml += "<div style='position:absolute;left:585px;top:178px;width:102px;height:27px;background:url(Images/ItemDecomposition/whiteBg.png);'>";
            mainHtml += "<div id='AllTime' style='position:absolute;top:3px;width:102px;text-align:center;font-size:18px;'>" + DecompositionNum + "</div></div>";
            mainHtml += "<div style='position:absolute;left:505px;top:230px;width:74px;height:74px;background:url(Images/ItemDecomposition/Frame.png);'>";
            mainHtml += "<div style='position:absolute;left:4px;top:4px;width:64px;height:64px;background:url(Images/Item/11202.png);'></div></div>";
            if (canGet == 1) {
                mainHtml += "<div id='getBtn' class='div_btn2' style='left:606px;top:260px;font-size:14px;color:white;position:absolute;cursor:pointer;'onclick='ItemDecompositionObj.getReward()'>领取</div>";
            } else if (canGet == 0) {
                mainHtml += "<div id='getBtn' class='div_btn2grey' style='left:606px;top:260px;font-size:14px;color:white;position:absolute;'>领取</div>";
            } else {
                mainHtml += "<div id='getBtn' class='div_btn2grey' style='left:606px;top:260px;font-size:14px;color:white;position:absolute;'>领取</div>";
            }

            //活动时间
            var activityTime = "<div style='position:absolute;left:315px;top:99px;font-size:14px;color:#FFFF00;width:275px;text-align:center;'>活动时间：" + ActivityBeginTime + "至" + ActivityEndTime + "</div>";
            //活动说明
            var activityDesc = "<div style='position:absolute;left:125px;top:400px;width:690px;height:50px;font-size:12px;color:white;line-height:18px;'>";
            activityDesc += "活动说明：<br>活动期间内,玩家可以将不需要的签约道具进行分解，道具分解可以获得战术积分卡或训练卡或球员经验卡，一个道具分解对应一张卡片，有几率分解翻倍哦~！当然也有几率分解失败，失败后道具消失。每分解2000个道具更可以免费领取欧冠之魂一个";
            //在弹出的主窗口中要显示的所有内容的html
            var html = "<div id='mainHTML' style='width:909px;height:457px;background:url(Images/ItemDecomposition/Bg.jpg);position:absolute;left:6px;top:0px;'>" + mainHtml + activityTime + activityDesc + "</div>"
            //弹出活动的主窗口  张旭  ************************************************************************************************************************************************************************************
            AddBox(40, 85, 925, 494, "分解•多多益善", html, $('#totalMainDiv'), "确定", null, "ItemDecompositionUI", false, true, null);
            //距左,距上,宽,高,top选项卡内容,弹出框内容,要放入的容器,确定按钮事件(null=删除当前弹出框,-1=没有任何操作,其他=自定义执行的方法),关闭按钮事件(-1=没有关闭按钮,其他=自定义执行的方法),弹出框id(如果不写将自动生成id),是否是有标签的（true：球在右,false：球在左）,弹出框是否可移动,关闭按钮事件
            $("#InputNum").blur(function () {
                var InputVal = $("#InputNum").val();
                if (InputVal == '' || InputVal == null || InputVal <= 0) {
                    $("#InputNum").val('1'); alert123();
                }
            });
        });
    }
    //去除不合法字符，只保留数字
    this.formatNum = function (dom) {
        var val = $(dom).val();
        $(dom).val(val.replace(/\D/g, ''));
        var newVal = $(dom).val();
        var allNum = $("#hidden").val();
        if (allNum == 0 || parseInt(newVal) == 0) {
            $(dom).val(1);
        }
        else if (parseInt(newVal) > parseInt(allNum)) {
            $(dom).val(allNum);
        }
    }
    this.changeNum = function () {
        $("#InputNum").val(1);
    }
    //根据ItemCode获得在背包中的数量,并赋值到隐藏域
    this.GetNum = function () {
        var ItemNameVal = $("#ItemName").val();
        var ItemLevelVal = $("#ItemLevel").val();
        var ItemCode = ItemNameVal + "" + ItemLevelVal;
        $.post("ItemDecomposition.aspx", { type: 4, ItemCode: ItemCode }, function (result) {
            $("#hidden").val(result);
        });
    }
    //分解  mold=1,普通分解；mold=2,100%分解
    this.doItemDecomposition = function (mold, isVIP) {
        if (isVIP == 0) {
            ShowMsg("使用VIP卡激活此功能？", null, function () { $("#divMsg2").remove(); GotoStore(); }, "温馨提示", function () { $("#divMsg2").remove(); }, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
        } else {
            var num = parseInt($("#InputNum").val()); //数量
            var ItemNameVal = $("#ItemName").val();
            var ItemLevelVal = $("#ItemLevel").val(); //等级
            var ItemCode = ItemNameVal + "" + ItemLevelVal; //Itemcode
            //var ItemName = document.all.ItemName.options[document.all.ItemName.selectedIndex].text + ItemLevelVal + "级"; //道具的名字
            var ItemName = $("#ItemName").find("option:selected").text() + ItemLevelVal + "级";  //获取Select选择的Text

            var needGameCoin = parseInt(ItemLevelVal) * 10 * num; //普通的分解需要花费的银币-----------------------------------------------------------很有可能需要改的地方----------------------
            if (mold == 2) {//100%分解需要花费的银币
                needGameCoin = 6 * needGameCoin; //----------------------------------------------------------------------------------------------------很有可能需要改的地方----------------------
            }
            ShowMsg("将" + num + "个" + ItemName + "进行分解，<br>消耗" + needGameCoin + "银币，确定？", null, function () {
                $.post("ItemDecomposition.aspx", { type: 2, mold: mold, itemCode: ItemCode, count: num }, function (result) {
                    //1@获得的道具名|数量*获得的道具名|数量
                    //result = "1@合同1级|1";
                    if (result == -1) {
                        ShowMsg("使用VIP卡激活此功能？", null, function () { GotoStore(); }, "温馨提示", null, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
                    } else if (result == -2) {
                        ShowMsg("很抱歉，你的银币不足！", null, function () { $("#divMsg2").remove(); }, "温馨提示", null, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
                    } else if (result == -3) {
                        ShowMsg("请修改分解数量，输入数量多于您拥有的数量！", null, function () { $("#divMsg2").remove(); }, "温馨提示", null, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
                    } else if (result == -4) {
                        ShowMsg("背包中没有该道具，请分解其他道具！", null, function () { $("#divMsg2").remove(); }, "温馨提示", null, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
                    } else if (result.lastIndexOf("@") > 0) {
                        publicAddGSEL(2, -parseInt(needGameCoin));
                        var gettedItemInfoArr = "";
                        if (result.split('@')[1].lastIndexOf("*") > 0) {
                            gettedItemInfoArr = result.split('@')[1].split('*');
                        } else {
                            gettedItemInfoArr = new Array(result.split('@')[1]);
                        }
                        ItemDecompositionObj.splitArr(gettedItemInfoArr, num);
                    }
                });
            }, "温馨提示", function () { $("#divMsg2").remove(); }, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
        }
    }
    //分解数组的小方法
    this.splitArr = function (arr, num) {
        var item = "";
        var getNum = 0; // 记录分解获得道具种类数，等于0表示分解失败，大于0，表示分解获得了道具
        var html = "恭喜您分解获得";
        for (var i = 0; i < arr.length; i++) {
            item = arr[i].split('|');
            if (item[0] != -1) {
                getNum = getNum + 1;
                html += item[0] + "*" + item[1] + ",";
            }
        }
        if (getNum == 0) {
            html = "很遗憾，分解碎了！";
        } else if (getNum >= 1) {
            html = html.substring(0, html.length - 1);
        }
        ShowMsg(html, null, function () { $("#divMsg2").remove(); ItemDecompositionObj.updateAllTime(num); }, "温馨提示", function () { ItemDecompositionObj.updateAllTime(num); }, null, 180, 180, $('#ItemDecompositionUI'), 'divMsg2');
    }
    //分解完成后（无论失败与否）点击确定或关闭的事件:更新总数量，领取按钮是否可点，输入分解数量改为1
    this.updateAllTime = function (num) {
        num = parseInt(num);
        var AllTime = num + parseInt($("#AllTime").text());
        $("#AllTime").text(AllTime);
        var cha = parseInt(AllTime / 2000) - parseInt((AllTime - num) / 2000);
        if (cha >= 1) {
            $("#getBtn").remove();
            $("#mainHTML").append("<div id='getBtn' class='div_btn2' style='left:606px;top:260px;font-size:14px;color:white;position:absolute;cursor:pointer;'onclick='ItemDecompositionObj.getReward()'>领取</div>");
            $("#InputNum").val('1');
        }
    }

    //点击领取按钮时触发的事件
    this.getReward = function () {
        $.post("ItemDecomposition.aspx", { type: 3 }, function (result) {
            if (result == 1) {
                ShowMsg("领取成功，已发放到背包中，注意查收！", null, function () { $("#divMsg2").remove(); ItemDecompositionObj.GotoItemDecomposition(); }, "温馨提示", function () { ItemDecompositionObj.GotoItemDecomposition(); }, null, 270, 220, $('#ItemDecompositionUI'), 'divMsg2');
            } else if (result == -1) {
                ShowMsg("可领取次数已用光，<br>继续努力分解吧！", null, function () { $("#divMsg2").remove(); ItemDecompositionObj.GotoItemDecomposition(); }, "温馨提示", function () { ItemDecompositionObj.GotoItemDecomposition(); }, null, 270, 220, $('#ItemDecompositionUI'), 'divMsg2');
            }
        });
    }

}

