<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type="text/javascript" src="../scripts/swfobject.js"></script>
    </head>
    <body>
        <table>
            <tr>
                <td>
                    <input type="button" value="查看事件" onclick="javascript:window.open('events.jsp');"/>
                </td>
            </tr>
        </table>
        <br/>
        <table>
            <tr>
                <td>
                    群集操作
                </td>
                <td>
                    <input type="button" value="查看候选球员" onclick="viewMiddleMan('');"/>
                    <input type="button" value="查看任务" onclick="viewTaskinfo('', 0);"/>
                    <input type="button" value="查看完成任务" onclick="viewTaskinfo('', 1);"/>
                    <input type="button" value="查看未完成任务" onclick="viewTaskinfo('', 2);"/>
                    <input type="button" value="查看球员" onclick="viewPlayer('');"/>
                    <input type="button" value="查看可升级球员" onclick="viewUpgradePlayer('');"/>
                    <%--<input type="button" value="查看签约道具" onclick="javascript:window.open('bag.jsp');"/>--%>
                    <%--<input type="button" value="查看其他道具" onclick="javascript:window.open('item.jsp');"/>--%>
                    <%--<input type="button" value="自动训练" onclick="autoTrainTask('');"/>--%>
                    <input type="button" value="报名球会大作战" onclick="signupTeamGame('');"/>
                    <input type="button" value="参数设置" onclick="javascript:window.open('config.jsp');"/>
                    <input type="button" value="查看战术" onclick="javascript:window.open('mytactic.jsp');"/>
                    <input type="button" value="其他功能" onclick="javascript:window.open('ogzqUtils.jsp');"/>
                </td>
            </tr>
            <tr>
                <td>
                    自动完成
                </td>
                <td>
                    <input type="button" value="全部" onclick="finishTaskAll('0');"/>
                    <input type="button" value="训练球员" onclick="finishTaskAll('1');"/>
                    <input type="button" value="竞技场" onclick="finishTaskAll('2');"/>
                    <input type="button" value="球会大作战" onclick="finishTaskAll('3');"/>
                    <input type="button" value="搜索球员" onclick="finishTaskAll('4');"/>
                    <input type="button" value="训练赛" onclick="finishTaskAll('5');"/>
                    <input type="button" value="欧冠之魂" onclick="finishTaskAll('6');"/>
                    <input type="button" value="欧冠之巅" onclick="finishTaskAll('7');"/>
                    <input type="button" value="国旗巡回" onclick="finishTaskAll('8');"/>
                </td>
            </tr>
        </table>
        <br/>
        <input type="button" value="展开/收起信息" onclick="showTable('info');"/>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">登录名</th>
                <th>游戏名</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">黄金</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">银币</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">等级</th>
                <!--<th>经验</th>-->
                <!--<th>下一级经验</th>-->
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">实力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, true)">声望</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, false)">球会</th>
                <th>训练赛</th>
                <th>竞技场</th>
                <!--<th>联赛</th>-->
                <th>球员搜索</th>
            </tr>
            <%
                int i = 0;
                List<String> keys = IDUtils.GETIDS();
                for (String key : keys) {
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td>
                        <input type="button" value="训练" onclick="javascript:window.open('train.jsp?email=<%=URLEncoder.encode(key)%>')" style="padding-left: 1px;padding-right: 1px;"/>
                        <%--<input type="button" value="装备" onclick="javascript:window.open('equip.jsp?email=<%=URLEncoder.encode(key)%>')" style="padding-left: 1px;padding-right: 1px;"/>--%>
                        <input type="button" value="任务" onclick="viewTaskinfo('<%=key%>', 0)" style="padding-left: 1px;padding-right: 1px;"/>
                        <input type="button" value="候选球员" onclick="viewMiddleMan('<%=key%>');" style="padding-left: 1px;padding-right: 1px;"/>
                        <input type="button" value="查看战术中心" onclick="javascript:window.open('tactic.jsp?email=<%=URLEncoder.encode(key)%>');" style="padding-left: 1px;padding-right: 1px;"/>
                        <%--<input type="button" value="报名球会战" onclick="signupTeamGame('<%=key%>');"/>--%>
                        <input type="button" value="材料" onclick="javascript:window.open('bag.jsp?email=<%=URLEncoder.encode(key)%>');" style="padding-left: 1px;padding-right: 1px;"/>
                        <input type="button" value="战术" onclick="javascript:window.open('mytactic.jsp?email=<%=key%>');" style="padding-left: 1px;padding-right: 1px;"/>
                        <%--<input type="button" value="升阶" onclick="javascript:window.open('shengjie.jsp?email=<%=key%>');" style="padding-left: 1px;padding-right: 1px;"/>--%>
                        <%=key%>
                    </td>
                    <td><%=IDUtils.getNick(key)%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("gold")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("silver")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("level")%></td>
                    <%--<td><%=IDUtils.IDInfos.get(key).get("exp")%></td>--%>
                    <%--<td><%=IDUtils.IDInfos.get(key).get("nextlevel")%></td>--%>
                    <td><%=IDUtils.IDInfos.get(key).get("shili")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("shengwang")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("club")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("xunliansai")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("arena")%></td>
                    <%--<td><%=(IDUtils.IDTasks.containsKey(key) && IDUtils.IDTasks.get(key).containsKey("league")) ? IDUtils.IDTasks.get(key).get("league") : "未知"%></td>--%>
                    <td><%=IDUtils.IDInfos.get(key).get("search")%></td>
                </tr>
            <%
                    i++;
                }
            %>
        </table>
        <br/>
        <input type="button" value="展开/收起候选球员" onclick="showTable('middleman');"/>
        <table id="middleman" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">email</th>
                <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">昵称</th>
                <th onclick="resort(this, 2, true)" style="cursor:pointer;text-decoration:underline;">银币</th>
                <th onclick="resort(this, 3, true)" style="cursor:pointer;text-decoration:underline;">ID</th>
                <th onclick="resort(this, 4, false)" style="cursor:pointer;text-decoration:underline;">姓名</th>
                <th onclick="resort(this, 5, true)" style="cursor:pointer;text-decoration:underline;">等级</th>
                <th onclick="resort(this, 6, true)" style="cursor:pointer;text-decoration:underline;">能力</th>
                <th onclick="resort(this, 7, true)" style="cursor:pointer;text-decoration:underline;">位置</th>
                <th onclick="resort(this, 8, true)" style="cursor:pointer;text-decoration:underline;">剩余时间</th>
                <th onclick="resort(this, 9, true)" style="cursor:pointer;text-decoration:underline;">存货</th>
                <th>操作</th>
            </tr>
        </table>
        <br/>
        <input type="button" value="展开/收起任务" onclick="showTable('taskinfo');"/>
        <table id="taskinfo" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">email</th>
                <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">nick</th>
                <th onclick="resort(this, 2, false)" style="cursor:pointer;text-decoration:underline;">ID</th>
                <th onclick="resort(this, 3, false)" style="cursor:pointer;text-decoration:underline;">类型</th>
                <th onclick="resort(this, 4, false)" style="cursor:pointer;text-decoration:underline;">描述</th>
                <th onclick="resort(this, 5, false)" style="cursor:pointer;text-decoration:underline;">状态</th>
                <th onclick="resort(this, 6, false)" style="cursor:pointer;text-decoration:underline;">已完成</th>
                <th onclick="resort(this, 7, false)" style="cursor:pointer;text-decoration:underline;">操作</th>
            </tr>
        </table>
        <br/>
        <input type="button" value="展开/收起球员" onclick="showTable('player');"/>
        <table id="player" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">email</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">nick</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">ID</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, false)">名称</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, false)">等级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false)">品质</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, false)">经验</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, false)">位置</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, true)">能力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, false)">是否替补</th>
                <th>操作</th>
            </tr>
        </table>
    </body>
<script type="text/javascript">
    function init(type)
    {
//        OgzqDwr.init();
    }

    function viewTaskinfo(email, type)
    {
        var table = document.getElementById('taskinfo');
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        OgzqDwr.viewTaskinfo(email, type, afterViewTaskinfo);
    }

    function sellLv5(email)
    {
        OgzqDwr.doSellLv5(email, afterDo);
    }

    function afterViewTaskinfo(list)
    {
        if(list != null) {
            var table = document.getElementById('taskinfo');
            var currentemail = '';
            var bgcolor = 1;
            for(var i = 0;i < list.length; i++) {
                var row = table.insertRow(-1);
                if(list[i]['email'] != currentemail) {
                    bgcolor = 3 - bgcolor;
                    currentemail = list[i]['email'];
                }
                row.className = 'row' + bgcolor;
                var cell = row.insertCell(-1);
                cell.innerHTML = list[i]['email'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['nick'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['type'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['id'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['desc'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['status'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['finished'];
                cell = row.insertCell(-1);
                cell.innerHTML = "<input type=\"button\" value=\"完成\" onclick=\"finishTask(this, '" + list[i]['email'] + "', '" + list[i]['id'] + "');\"/>"
            }
        }
    }

    function finishTask(obj, email, id) {
        OgzqDwr.finishTask(email, id);
        var table = document.getElementById('taskinfo');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);
    }

    function finishTaskAll(type) {
        OgzqDwr.finishTaskAll(type, afterDo);
    }

    function viewPlayer(email)
    {
        var table = document.getElementById('player');
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        OgzqDwr.viewPlayer(email, afterViewPlayer);
    }

    function viewUpgradePlayer(email)
    {
        var table = document.getElementById('player');
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        OgzqDwr.viewUpgradePlayer(email, afterViewPlayer);
    }

    function afterViewPlayer(list) {
        if(list) {
            var table = document.getElementById('player');
            var currentemail = '';
            var bgcolor = 1;
            for(var i = 0;i < list.length; i++) {
                var row = table.insertRow(-1);
                if(list[i]['email'] != currentemail) {
                    bgcolor = 3 - bgcolor;
                    currentemail = list[i]['email'];
                }
                row.className = 'row' + bgcolor;
                var cell = row.insertCell(-1);
                cell.innerHTML = list[i]['email'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['nick'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['id'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['name'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['level'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['pinzhi'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['exp'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['place'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['ability'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['tibu'];
//                cell = row.insertCell(-1);
//                cell.innerHTML = "<input type=\"button\" value=\"放弃\" onclick=\"dropPlayer(" + (i + 1) + ", '" + list[i]['email'] + "', '" + list[i]['id'] + "');\"/>";
            }
        }
    }

    function viewMiddleMan(email)
    {
        var table = document.getElementById('middleman');
        for(var i = table.rows.length - 1; i > 0; i--)
        {
            table.deleteRow(i);
        }
        OgzqDwr.viewMiddleMan(email, afterViewMiddleMan);
    }

    function afterViewMiddleMan(list)
    {
        if(list != null) {
            var table = document.getElementById('middleman');
            var currentemail = '';
            var bgcolor = 1;
            for(var i = 0;i < list.length; i++) {
                var row = table.insertRow(-1);
                if(list[i]['email'] != currentemail) {
                    bgcolor = 3 - bgcolor;
                    currentemail = list[i]['email'];
                }
                row.className = 'row' + bgcolor;
                if(isKey(list[i]['name']) || list[i]['name'].indexOf("少年") >= 0) {
                    row.style.color = 'red';
                } else if(list[i]['level'] < 5) {
                    row.style.color = 'blue';
                }
                var cell = row.insertCell(-1);
                cell.noWrap = 'true';
                cell.innerHTML = "<input type=\"button\" value=\"训练\" onclick=\"javascript:window.open('train.jsp?email=" + list[i]['email'] + "')\"/>" +
                        "<input type=\"button\" value=\"战术中心\" onclick=\"javascript:window.open('tactic.jsp?email=" + list[i]['email'] + "')\"/>" +
                        "<input type=\"button\" value=\"材料\" onclick=\"javascript:window.open('bag.jsp?email=" + encodeURI(encodeURI(list[i]['email'])) + "')\"/>" +
                        "<input type=\"button\" value=\"镶嵌\" onclick=\"javascript:window.open('doxiangqian.jsp?email=" + encodeURI(encodeURI(list[i]['email'])) + "')\"/>" + list[i]['email'] +
                        "<span id=\"email-" + list[i]['email'] + "\"></span>";

//                if(!!navigator.userAgent.match(/AppleWebKit.*Mobile.*/)||!!navigator.userAgent.match(/AppleWebKit/)) {
//
//                } else {
//                    var params = {menu:"false",
//                        scale:"noScale",
//                        allowFullscreen:"false",
//                        allowScriptAccess:"always",
//                        bgcolor:"#FFFFFF",
//                        wmode:"transparent",
//                        FlashVars:"text=复制&data=" + list[i]['email']};
//                    var attributes = {id:"clipboard",style:"margin:0 0 -5px 10px;"};
//                    swfobject.embedSWF("../include/clip.swf", "email-" + list[i]['email'], "30px", "20px", "9.0.0", "", null, params, attributes);
//                }

                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['nick'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['silver'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['id'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['name'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['level'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['value'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['place'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['lefttime'];
                cell = row.insertCell(-1);
                cell.innerHTML = list[i]['count'];
                cell = row.insertCell(-1);
                cell.noWrap = "nowrap";
                var giveup = "<input type=\"button\" value=\"放弃\" onclick=\"dropPlayer(this, '" + list[i]['email'] + "', '" + list[i]['id'] + "');\"/>";
                var beforesign = "<input type=\"button\" value=\"签约\" onclick=\"beforeSign('" + list[i]['email'] + "', '" + list[i]['id'] + "');\"/>";
                var signAndFire = "<input type=\"button\" value=\"签-解\" onclick=\"signFire(this, '" + list[i]['email'] + "', '" + list[i]['id'] + "');\"/>"
                var signAndTrainAndFire = "<input type=\"button\" value=\"签-训-解\" onclick=\"signTrainFire(this, '" + list[i]['email'] + "', '" + list[i]['name'] + "', '" + list[i]['id'] + "','" + list[i]['level'] + "');\"/>"
                var deleterow = "<input type=\"button\" value=\"D\" onclick=\"document.getElementById('middleman').deleteRow(this.parentNode.parentNode.rowIndex);\"/>"
                cell.innerHTML = giveup + beforesign + signAndFire + signAndTrainAndFire + deleterow;
            }
        }
    }

    function beforeSign(email, id)
    {
        OgzqDwr.beforeSign(email, id, afterBeforeSign);
    }

    function afterBeforeSign(obj)
    {
        if(obj == null || obj.length == 0) {
            alert('替补满了');
        }
        if(confirm(obj[2])) {
            OgzqDwr.signPlayer(obj[0], obj[1], afterDo);
        }
    }

    function signFire(obj, email, id)
    {
        var table = document.getElementById('middleman');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);
        OgzqDwr.signAndFirePlayer(email, id, afterDo);
    }

    function signTrainFire(obj, email, name, id, level)
    {
        if("马克斯维尔" == name) {
            if(confirm('镶嵌为后卫？')) {
                name = "马克斯维尔后";
            } else if(confirm('镶嵌为中场？')) {
                name = "马克斯维尔中";
            } else if(confirm('镶嵌为前锋？')) {
                name = "马克斯维尔前";
            } else {
                alert('不镶嵌');
                return;
            }
        }
        var table = document.getElementById('middleman');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);
        OgzqDwr.signAndTrainAndFirePlayer(email, name, id, level, afterDo);
    }

    function afterDo(obj)
    {
        alert(obj);
    }

    function dropPlayer(obj, email, id)
    {
        OgzqDwr.dropPlayer(email, id);
        var table = document.getElementById('middleman');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);
    }

    function showTable(id)
    {
        var table = document.getElementById(id);
        if(table) {
            if(table.style.display == 'none') {
                table.style.display = '';
            } else {
                table.style.display = 'none';
            }
        }
    }

    function autoTrainTask(id)
    {
        OgzqDwr.autoTrainTask(id, afterDo);
    }

    function doVoteChampion(id)
    {
        OgzqDwr.doVoteChampion(id, afterDo);

    }

    function signupTeamGame(id)
    {
        OgzqDwr.signupTeamGame(id, afterDo);
    }

    function getTacticPoint(id)
    {
        OgzqDwr.getTacticPoint(id, afterDo);
    }

    var keyPlayers = new Array();
    var keyIdx = 0;
    keyPlayers[keyIdx] = '劳尔';
    keyIdx++;
    keyPlayers[keyIdx] = '伊布';
    keyIdx++;
    keyPlayers[keyIdx] = '因扎吉';
    keyIdx++;
    keyPlayers[keyIdx] = '阿奎罗';
    keyIdx++;
    keyPlayers[keyIdx] = '范佩西';
    keyIdx++;
    keyPlayers[keyIdx] = '哈维';
    keyIdx++;
    keyPlayers[keyIdx] = '加图索';
    keyIdx++;
    keyPlayers[keyIdx] = '皮尔洛';
    keyIdx++;
    keyPlayers[keyIdx] = '施魏因斯泰格';
    keyIdx++;
    keyPlayers[keyIdx] = '罗本';
    keyIdx++;
    keyPlayers[keyIdx] = '内斯塔';
    keyIdx++;
    keyPlayers[keyIdx] = '卢西奥';
    keyIdx++;
    keyPlayers[keyIdx] = '特里';
    keyIdx++;
    keyPlayers[keyIdx] = 'T·席尔瓦';
    keyIdx++;
    keyPlayers[keyIdx] = '拉姆';
    keyIdx++;
    keyPlayers[keyIdx] = 'W·萨穆埃尔';
    keyIdx++;
    keyPlayers[keyIdx] = '赞布罗塔';
    keyIdx++;
    keyPlayers[keyIdx] = 'A·科尔';
    keyIdx++;
//    keyPlayers[keyIdx] = '拉莫斯';
//    keyIdx++;
    keyPlayers[keyIdx] = '基耶利尼';
    keyIdx++;
    keyPlayers[keyIdx] = '西多夫';
    keyIdx++;
    keyPlayers[keyIdx] = '兰帕德';
    keyIdx++;
    keyPlayers[keyIdx] = '萨内蒂';
    keyIdx++;
    keyPlayers[keyIdx] = '坎比亚索';
    keyIdx++;
    keyPlayers[keyIdx] = '蒙托利沃';
    keyIdx++;
    keyPlayers[keyIdx] = '鲁尼';
    keyIdx++;
    keyPlayers[keyIdx] = '托蒂';
    keyIdx++;
    keyPlayers[keyIdx] = '皮耶罗';
    keyIdx++;
    keyPlayers[keyIdx] = '伊瓜因';
    keyIdx++;
    keyPlayers[keyIdx] = 'F·托雷斯';
    keyIdx++;

    function isKey(p) {
        var ret = false;
        for(var i = 0; i < keyPlayers.length; i++) {
            if(p == keyPlayers[i]) {
                return true;
            }
        }
        return false;
    }

    function copySuccess() {
        alert("复制成功");
    }

</script>
</html>