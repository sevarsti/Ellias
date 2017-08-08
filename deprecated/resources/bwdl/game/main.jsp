<%@ page import="com.saille.bwdl.BwdlConstant" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-20
  Time: 22:47:41
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>
<html>
<head>
    <title>霸王的大陆</title>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/BwdlUtils.js'></script>
    <script type="text/javascript">
        String.format = function(src){
            if (arguments.length == 0)
            {
                return null;
            }
            var args = Array.prototype.slice.call(arguments, 1);
            return src.replace(/\{(\d+)\}/g, function(m, i){
                return args[i];
            });
        };
    </script>
</head>
<%
    request.setAttribute("version", 1);
%>
<body onload="initChengShi();flashChengshi();">
<%--<input type="button" onclick='alert(String.format("asdf{0}","aa"))'/>--%>
<script type="text/javascript">
    var actions = new Array(<%=BwdlConstant.ACTION.length%>);
    var action, chengshiId, wujiangId, jin;
    var chengshiName, chengshiTongzhi,chengshiJin, chengshiMi, chengshiBao, chengshiTudi, chengshiChanye, chengshiRenkou, chengshiFangzai;

    <%
        String[] actions = BwdlConstant.ACTION;
        for(int i = 0; i < actions.length; i++) {

    %>
            actions[<%=i%>] = '<%=actions[i]%>';
    <%
        }
    %>

    function flashChengshi()
    {
        if(chengshiId != null && chengshiId != 0)
        {
            var chengshis = document.getElementsByName('mapChengshi' + chengshiId);
            for(var i = 0; i < chengshis.length; i++)
            {
                if(chengshis[i].style.display == 'none')
                {
                    chengshis[i].style.display = '';
                }
                else
                {
                    chengshis[i].style.display = 'none';
                }
            }
        }
        setTimeout(flashChengshi, 500)
    }

    function showWord(word, place, notShowConfirm)
    {
        document.getElementById('showwords').value = word;
        var obj = document.getElementById(place);
        if(obj.rows.length > 0)
        {
            for(var i = obj.rows.length - 1; i >= 0; i--)
            {
                obj.deleteRow(i);
            }
        }
        var newRow = obj.insertRow();
        var newCell = newRow.insertCell();
        newCell.width = '80%';
        newCell.vAlign = 'top';
        newCell.id = 'word';
        newCell.style.fontSize = '13pt';
        typeit(0, 'word');
        if(!notShowConfirm)
        {
            newCell = newRow.insertCell();
            newCell.width = '10%';
            newCell.style.fontSize = '20pt';
            newCell.innerHTML = '<span style="cursor:pointer;" onclick="doAction();">是</span>';
            newCell = newRow.insertCell();
            newCell.width = '10%';
            newCell.style.fontSize = '20pt';
            newCell.innerHTML = '<span style="cursor:pointer;" onclick="showChengShi();showMenu();kaifa();">否</span>';
        }
    }

    function typeit(it, objName)
    {
        var obj = document.getElementById(objName);
        var word = document.getElementById('showwords').value;
        obj.insertAdjacentText("beforeEnd", word.charAt(it))
        if (it < word.length - 1)
        {
            it++;
            setTimeout("typeit(" + it + ",'" + objName + "')", 10);
        }
        else
        {
            return;
        }
    }
</script>
<c:set var="allChengShis" value="${bwdl:getAllChengShis(version)}"/>
<input type="hidden" id="showwords"/>
    <table width="812" border="0" cellspacing="1" bgcolor='#FFC8B8'>
        <tr height="304">
            <td width="300" id="info" valign="top">
            </td>
            <td width="512">
                <div style="position:relative;" id="map">
                    <c:forEach items="${allChengShis}" var="chengshi">
                        <img id='chengshi1${chengshi.id}' width='16' height='8' style='position:absolute;z-index:1;top:1px;left:1px'/>
                        <img id='chengshi2${chengshi.id}' width='8' height='4' style='position:absolute;z-index:1;top:1px;left:1px'/>
                    </c:forEach>
                    <img src="../image/map/full.PNG" alt="" width="512" height="304" border="0"/>
                </div>
            </td>
        </tr>
        <tr height="110">
            <td colspan="2">
                <table border="0" cellpadding="0" cellspacing="0" width="100%">
                    <td width="60%">
                        <table id="op" width="400">

                        </table>
                    </td>
                    <td width="40%">
                        <table id="opdetail" style='font-size:18pt;' width="100%">

                        </table>
                    </td>
                </table>
            </td>
        </tr>
    </table>
<script type="text/javascript">
    function loadChengShi(id)
    {
        BwdlUtils.getChengShi(id, afterLoadChengShi);
    }

    function showMenu()
    {
        var op = document.getElementById('op');
        if(op.rows.length > 0)
        {
            for(var i = op.rows.length - 1; i >= 0; i--)
            {
                op.deleteRow(i);
            }
        }
        var newRow = op.insertRow();
        var newCell = newRow.insertCell();
        newCell.innerHTML = "<img src='../image/action1.PNG' alt='' width='96' height='96' onclick='action1();' style='cursor:pointer;'/>";
        newCell = newRow.insertCell();
        newCell.innerHTML = "<img src='../image/action2.PNG' alt='' width='96' height='96' onclick='action2();' style='cursor:pointer;'/>";
        newCell = newRow.insertCell();
        newCell.innerHTML = "<img src='../image/action3.PNG' alt='' width='96' height='96' onclick='action3();' style='cursor:pointer;'/>";
        newCell = newRow.insertCell();
        newCell.innerHTML = "<img src='../image/action4.PNG' alt='' width='96' height='96' onclick='action4();' style='cursor:pointer;'/>";
    }

    function afterLoadChengShi(obj, notReloadMenu)
    {
        if(obj != null)
        {
            chengshiId = obj.id;
            chengshiName = obj.name;
            chengshiTongzhi = obj.tongzhi;
            chengshiJin = obj.jin;
            chengshiMi = obj.mi;
            chengshiBao = obj.bao;
            chengshiTudi = obj.tudi;
            chengshiChanye = obj.chanye;
            chengshiRenkou = obj.renkou;
            chengshiFangzai = obj.fangzai;

            showChengShi();

            if(notReloadMenu == null && !notReloadMenu)
            {
                showMenu();
            }
        }
        var opdetail = document.getElementById('opdetail');
        for(var i = opdetail.rows.length - 1; i >= 0; i--)
        {
            opdetail.deleteRow(i);
        }
    }

    function showChengShi()
    {
        var info = document.getElementById('info');
        var fieldstyle = " style='font-size:18pt;'";
        var html = "<table width='100%'><tr><td width='30%'" + fieldstyle + ">城市</td><td width='70%'" + fieldstyle + ">" + chengshiName + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">统治度</td><td" + fieldstyle + ">" + chengshiTongzhi + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">金</td><td" + fieldstyle + ">" + chengshiJin + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">米</td><td" + fieldstyle + ">" + chengshiMi + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">土地</td><td" + fieldstyle + ">" + chengshiTudi + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">产业</td><td" + fieldstyle + ">" + chengshiChanye + "</td></tr>";
        html += "<tr><td" + fieldstyle + ">人口</td><td" + fieldstyle + ">" + chengshiRenkou + "00</td></tr>";
        html += "<tr><td" + fieldstyle + ">防灾</td><td" + fieldstyle + ">" + chengshiFangzai + "</td></tr>";
        html += "</table>"
        info.innerHTML = html;
    }

    function kaifa()
    {
        var opdetail = document.getElementById('opdetail');
        for(var i = opdetail.rows.length - 1; i >= 0; i--)
        {
            opdetail.deleteRow(i);
        }
        var newRow = opdetail.insertRow();
        var newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"kaifaTuDi();\">土地开垦</span>";
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"alert('鼓励工商开发中');\">鼓励工商</span>";
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"alert('市集开发开发中');\">市集开发</span>";
        newCell.style.fontSize = '20pt';
    }

    function kaifaTuDi()
    {
        action = 'tudi';
        BwdlUtils.loadWuJiangsByChengshi(chengshiId, selectWuJiang);
    }

    function yidong()
    {
        alert('移动开发中...')
    }

    function souji()
    {
        alert('搜集开发中...')
    }

    function fangzai()
    {
        alert('防灾开发中...')
    }

    function celue()
    {
        alert('策略开发中...')
    }

    function shice()
    {
        alert('史册开发中...')
    }

    function selectWuJiang(wujiangs)
    {
        var html = "<table width='100%'><tr align='right'><td align='left'>武将</td><td>体</td><td>武</td><td>知</td><td>忠</td><td>德</td><td>经</td></tr>";
        for(var i = 0; i < wujiangs.length; i++)
        {
            html += "<tr align='right'><td align='left'><span style='cursor:pointer;' onclick='afterSelectWuJiang(" + wujiangs[i].id + ")'>" + wujiangs[i].name + "</span></td><td>" + wujiangs[i].ti + "</td><td>" + wujiangs[i].wu + "</td><td>" + wujiangs[i].zhi + "</td>";
            html += "<td>" + (wujiangs[i].zhong == 100 ? "--" : wujiangs[i].zhong) + "</td><td>" + wujiangs[i].de + "</td><td>" + wujiangs[i].jing + "</td></tr>";
        }
        html += "</table>";
        var info = document.getElementById('info');
        info.innerHTML = html;
    }

    function afterSelectWuJiang(id)
    {
        wujiangId = id;
        BwdlUtils.preAction(action, id, chengshiId, preAction);
    }

    function preAction(obj)
    {
        if(obj[0] == -1)
        {
//            todo
        }
        else
        {
            jin = obj[0];
            showWord(obj[1], 'op');
            afterLoadChengShi(null, true);
        }
    }

    function doAction()
    {
        BwdlUtils.doAction(action, wujiangId, chengshiId, jin, afterDoAction);
    }

    function afterDoAction(objs)
    {
        showWord(objs[0], 'op', true);
        afterLoadChengShi(objs[1], true);
    }

    function action1()
    {
        var opdetail = document.getElementById('opdetail');
        for(var i = opdetail.rows.length - 1; i >= 0; i--)
        {
            opdetail.deleteRow(i);
        }
        var newRow = opdetail.insertRow();
        var newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"kaifa();\">城镇开发</span>";
        newCell.style.fontSize = '20pt';
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"yidong();\">武将移动</span>";
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"souji();\">情报搜集</span>";
        newCell.style.fontSize = '20pt';
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"fangzai();\">防灾</span>";
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"celue();\">策略</span>";
        newCell.style.fontSize = '20pt';
        newCell = newRow.insertCell();
        newCell.innerHTML = "<span style=\"cursor:pointer;\" onclick=\"shice();\">史册</span>";
        newCell.style.fontSize = '20pt';
    }

    function action2()
    {
        var opdetail = document.getElementById('opdetail');
        for(var i = opdetail.rows.length - 1; i >= 0; i--)
        {
            opdetail.deleteRow(i);
        }
        var newRow = opdetail.insertRow();
        var newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = kaifa;
        newCell.innerHTML = '出征';
        newCell.style.fontSize = '20pt';
        newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = yidong;
        newCell.innerHTML = '征兵';
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = souji;
        newCell.innerHTML = '侦查';
        newCell.style.fontSize = '20pt';
        newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = fangzai;
        newCell.innerHTML = '任命';
        newCell.style.fontSize = '20pt';
    }

    function action3()
    {
        var opdetail = document.getElementById('opdetail');
        for(var i = opdetail.rows.length - 1; i >= 0; i--)
        {
            opdetail.deleteRow(i);
        }
        var newRow = opdetail.insertRow();
        var newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = kaifa;
        newCell.innerHTML = '物资运送';
        newCell.style.fontSize = '20pt';
        newRow = opdetail.insertRow();
        newCell = newRow.insertCell();
        newCell.style.cursor = 'pointer';
        newCell.onclick = souji;
        newCell.innerHTML = '赏赐';
        newCell.style.fontSize = '20pt';
    }

    function action4()
    {
        
    }

    function initChengShi()
    {
        var chengshi1;
        var chengshi2;
        <c:forEach items="${allChengShis}" var="c">
            chengshi1 = document.getElementById('chengshi1${c.id}');
            chengshi1.src = '../image/map/shili${c.shili}2.PNG';
            chengshi1.style.top = '${c.locationX + 3}px';
            chengshi1.style.left = '${c.locationY - 1}px';
            chengshi1.width = 16;
            chengshi1.height = 8;
            chengshi1.alt = '${c.name}';
            chengshi1.style.cursor = 'pointer';
            chengshi1.onclick = function() {loadChengShi(${c.id});};
            chengshi1.id = 'mapChengshi${c.id}';
            chengshi1.name = 'mapChengshi${c.id}';
            chengshi2 = document.getElementById('chengshi2${c.id}');
            chengshi2.src = '../image/map/shili${c.shili}1.PNG';
            chengshi2.style.top = '${c.locationX - 1}px';
            chengshi2.style.left = '${c.locationY + 3}px';
            chengshi2.width = 8;
            chengshi2.height = 4;
            chengshi2.alt = '${c.name}';
            chengshi2.style.cursor = 'pointer';
            chengshi2.onclick = function() {loadChengShi(${c.id});};
            chengshi2.id = 'mapChengshi${c.id}';
            chengshi2.name = 'mapChengshi${c.id}';
        </c:forEach>
    }
</script>
</body>
</html>