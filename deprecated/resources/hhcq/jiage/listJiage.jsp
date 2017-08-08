<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-2-24
  Time: 23:42:21
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/HhcqUtils.js'></script>
      <title>价格查询</title>
  </head>
    <script type="text/javascript">
        function updateLeibie()
        {
//            HhcqUtils.getShangpinByLeibie(document.getElementById('leibieId').value, afterUpdateLeibie);
            HhcqUtils.getShangpinByLeibie(0, afterUpdateLeibie);
        }

        function afterUpdateLeibie(list)
        {
            var _selection = document.getElementById("shangpinId");
            for(var i = _selection.length - 1; i >= 0; i--)
            {
                _selection.options[i] = null;
            }
            _selection.options[0] = new Option();
            _selection.options[0].value = 0;
            _selection.options[0].text  = '--全部--';
            for(var i = 0; i < list.length; i++)
            {
                _selection.options[i + 1] = new Option();
                _selection.options[i + 1].value = list[i].id;
                _selection.options[i + 1].text  = list[i].pinyin + list[i].name;
            }
        }

        function updateGuojia()
        {
//            HhcqUtils.getGangkouByGuojia(document.getElementById('guojiaId').value, afterUpdateGuojia);
            HhcqUtils.getGangkouByGuojia(0, afterUpdateGuojia);
        }

        function afterUpdateGuojia(list)
        {
            var _selection = document.getElementById("gangkouId");
            for(var i = _selection.length - 1; i >= 0; i--)
            {
                _selection.options[i] = null;
            }
            _selection.options[0] = new Option();
            _selection.options[0].value = 0;
            _selection.options[0].text  = '--全部--';
            for(var i = 0; i < list.length; i++)
            {
                _selection.options[i + 1] = new Option();
                _selection.options[i + 1].value = list[i].id;
                _selection.options[i + 1].text  = list[i].pinyin + list[i].name;
            }
        }

        function doQuery()
        {
            document.forms[0].submit();
        }

        function doSave()
        {
            document.getElementsByName('method')[0].value = 'save';
            document.forms[0].submit();
        }

//        function initList()
//        {
//            updateGuojia();
//            updateLeibie();
//        }

        function loadChushou(v)
        {
            HhcqUtils.getChushouByGangkou(v, afterLoadChushou);
//            alert('in load,' + v);
            HhcqUtils.getMaxSellProfit(v, afterLoadMaxSellProfit);
        }

        function loadShangpin(v)
        {
            HhcqUtils.getChushouByShangpin(v, afterLoadChushou2);
            updateTopJiage(v);
        }

        function afterLoadChushou(list)
        {
            var str = '港口出售<br/>';
            for(var i = 0; i < list.length; i++)
            {
                str = str + list[i][0] + ',' + list[i][1] + "<br/>";
            }
            document.getElementById('showChushou').innerHTML = str;
        }

        function afterLoadMaxSellProfit(list)
        {
//            alert(list.length);
            var str = '<table width="100%" cellspacing="1" bgcolor="white"><tr bgcolor="black"><td>商品</td><td>港口</td><td>卖价</td><td>距离</td><td>利润</td><td>收益比</td><td>危险</td></tr>';
            for(var i = 0; i < list.length; i++)
            {
                str += '<tr bgcolor="black">';
                for(var j = 0; j < 7; j++)
                {
                    str += '<td>' + list[i][j] + '</td>';
                }
                str += "</tr>";
            }
            str += '</table>';
            document.getElementById('maxSell').innerHTML = str;
        }
        function afterLoadChushou2(list)
        {
            var str = '商品有售<br/>';
            for(var i = 0; i < list.length; i++)
            {
                str = str + list[i][0] + ',' + list[i][1] + "<br/>";
            }
            document.getElementById('showChushou2').innerHTML = str;
        }

        var gangkou = 0;
        var shangpin = 0;
        function updateHasJiage(type, value)
        {
            if(type == 1)   //gangkou
            {
                gangkou = value;
            }
            else if(type == 2)  //shangpin
            {
                shangpin = value;
            }
            if(gangkou != 0 && shangpin != 0)
            {
                HhcqUtils.getJiage(gangkou, shangpin, afterGetJiage);
            }
            if(type == 1 && value != 0)
            {
                loadChushou(value);
            }
            else if(type == 2 && value != 0)
            {
                updateTopJiage(value);
                loadShangpin(value);
            }
        }

        function afterGetJiage(value)
        {
            if(value != null)
            {
                document.getElementsByName('curJiage')[0].value = value.jiage;
                document.getElementsByName('weixian')[0].value = value.weixian;
            }
            else
            {
                document.getElementsByName('curJiage')[0].value = 0;
                document.getElementsByName('weixian')[0].value = 0;
            }
        }

        function updateTopJiage(value)
        {
            HhcqUtils.getTopJiage(value, afterGetTopJiage);
        }

        function afterGetTopJiage(list)
        {
            var str = '最高售价<br/>';
            for(var i = 0; i < list.length; i++)
            {
                str += list[i][0] + ',' + list[i][1] + "<br/>";
            }
            document.getElementById('showTopSell').innerHTML = str;
        }

        var juli1 = 0;
        var juli2 = 0;
        function updateJuli(v)
        {
            juli1 = document.getElementById('gangkou7a').value;
            juli2 = document.getElementById('gangkou7b').value;
            if(juli1 != 0 && juli2 != 0)
            {
                HhcqUtils.getJuli(juli1, juli2, afterGetJuli);
                HhcqUtils.getProfitBetweenGangkou(juli1, juli2, afterGetBetweenProfit);
            }
            if(v = 1)
            {
                updateHasJiage(1,juli1);
                updateNoJuli(juli1);
            }
        }

        function updateNoJuli(id)
        {
            HhcqUtils.getNoJuli(id,afterUpdateNoJuli);
        }

        function afterUpdateNoJuli(list)
        {
            var s = document.getElementById('nojuli');
            var html = '';
            for(var i = 0; i < list.length; i++)
            {
                html += list[i][0] + '/' + list[i][1] + '<br/>';
            }
            s.innerHTML = html;
        }

        function afterGetBetweenProfit(list)
        {
            var str = '<table width="100%" cellspacing="1" bgcolor="white"><tr bgcolor="black"><td>商品</td><td>卖价</td><td>利润</td><td>危险</td></tr>';
            for(var i = 0; i < list.length; i++)
            {
                str += '<tr bgcolor="black">';
                for(var j = 0; j < 4; j++)
                {
                    str += '<td>' + list[i][j] + '</td>';
                }
                str += "</tr>";
            }
            str += '</table>';
            document.getElementById('betweenProfit').innerHTML = str;
        }

        function afterGetJuli(value)
        {
            document.getElementById('curJuli').value = value;
        }
    </script>
    <body>
    <font color="white">
        当前共有${gangkou}个港口，理论上有${maxjuli}条航线，实际有${juli}条航线
        <fmt:formatNumber value="${juli / maxjuli}" pattern="#,##0.000000%"/>
        <br/>
        当前共有${shangpin}个商品，理论上有${maxjiage}个价格，实际有${jiage}个价格
        <fmt:formatNumber value="${jiage / maxjiage}" pattern="#,##0.000000%"/>
    </font>
    <c:set var="form" value="${jiageForm}"/>
    <html:form action="/hhcq/jiage.do">
    <table width="100%" cellspacing="1" bgcolor="white">
    <c:set var="allGangkous" value="${hhcq:getAllGangkous()}"/>
    <c:set var="allShangpins" value="${hhcq:getAllShangpins()}"/>
    <tr bgcolor="black" valign="top">
    <!--<tr bgcolor="#F9FFFF" valign="top">-->
            <td width="60%" rowspan="2" valign="top">
                <table width="100%" cellspacing="1" bgcolor="black">
                    <tr bgcolor="black" valign="top">
                        <td>
                        <input type="hidden" name="method" value="list"/>
                        <p/>
                        港口：
                        <%--<select id="guojiaId" onchange="updateGuojia();">--%>
                            <%--<option value="0">--全部--</option>--%>
                            <%--<c:forEach items="${hhcq:getAllGuojias()}" var="guojia">--%>
                                <%--<option value="${guojia.id}">${guojia.pinyin}${guojia.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <html:select property="gangkouId" onchange="updateHasJiage(1,this.value);">
                            <option value="0">--全部--</option>
                            <c:forEach items="${allGangkous}" var="gangkou">
                                <html:option value="${gangkou.id}">${gangkou.pinyin}${gangkou.name}</html:option>
                            </c:forEach>
                        </html:select>
                        商品：
                        <%--<select id="leibieId" onchange="updateLeibie();">--%>
                            <%--<option value="0">--全部--</option>--%>
                            <%--<c:forEach items="${hhcq:getAllLeibies()}" var="leibie">--%>
                                <%--<option value="${leibie.id}">${leibie.pinyin}${leibie.name}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                        <html:select property="shangpinId" onchange="updateHasJiage(2,this.value);">
                            <option value="0">--全部--</option>
                            <c:forEach items="${allShangpins}" var="shangpin">
                                <html:option value="${shangpin.id}">${shangpin.pinyin}${shangpin.name}</html:option>
                            </c:forEach>
                        </html:select>
                        价格：<html:text property="jiage" size="5"/>
                        危险：
                        <html:select property="weixian">
                            <c:forEach begin="0" end="5" var="i">
                                <html:option value="${i}">${i}</html:option>
                            </c:forEach>
                        </html:select>
                        <input type="button" onclick="doSave();" value="增加"/>
                        <br/>
                        <input type="text" id="curJiage"/>
                        <br/>

<%--
                        <p/>
                        增加国家<br/>
                        国家名称
                        <input type="text" name="guojia3"/>
                        拼音
                        <input type="text" name="pinyin3"/>
                        <input type="button" value="保存" onclick="addGuojia();"/>

                        <p/>
                        增加港口
                        <br/>
                        港口<input type="text" name="gangkou2"/>
                        所属国家
                        <select name="guojia2">
                            <option value="0">无</option>
                            <c:forEach items="${hhcq:getAllGuojias()}" var="g">
                                <option value="${g.id}">${g.pinyin}${g.name}</option>
                            </c:forEach>
                        </select>
                        拼音<input type="text" name="pinyin2" size="7"/>
                        <input type="button" value="保存" onclick="addGangkou();"/>

<%--
                        <p/>
                        增加类别
                        <br/>
                        类别
                        <input type="text" name="leibie4"/>
                        拼音
                        <input type="text" name ="pinyin4" size="7"/>
                        <input type="button" value="保存" onclick="addLeibie();"/>

                        <p/>
                        增加商品
                        <br/>
                        商品
                        <input type="text" name="shangpin5"/>
                        所属类别
                        <select name="leibie5">
                            <option value="0">无</option>
                            <c:forEach items="${hhcq:getAllLeibies()}" var="l">
                                <option value="${l.id}">${l.pinyin}${l.name}</option>
                            </c:forEach>
                        </select>
                        拼音
                        <input type="text" name="pinyin5" size="7"/>
                        <input type="button" value="保存" onclick="addShangpin();"/>

                        <p/>
                        增加出售
                        <br/>
                        港口
                        <select name="gangkou6" onchange="loadChushou(this.value);">
                            <option value="0">无</option>
                            <c:forEach items="${hhcq:getAllGangkous()}" var="g">
                                <option value="${g.id}">${g.pinyin}${g.name}</option>
                            </c:forEach>
                        </select>
                        商品
                        <select name="shangpin6" onchange="loadShangpin(this.value);">
                            <option value="0">无</option>
                            <c:forEach items="${hhcq:getAllShangpins()}" var="g">
                                <option value="${g.id}">${g.pinyin}${g.name}</option>
                            </c:forEach>
                        </select>
                        价格
                        <input type="text" name="jiage6" size="5"/>
                        <input type="button" value="保存" onclick="addChushou();"/>

--%>
                        <p/>
                        增加距离
                        <br/>
                        港口1
                        <select name="gangkou7a" onchange="updateJuli(1);">
                            <option value="0">无</option>
                            <c:forEach items="${allGangkous}" var="g">
                                <option value="${g.id}">${g.pinyin}${g.name}</option>
                            </c:forEach>
                        </select>
                        港口2
                        <select name="gangkou7b" onchange="updateJuli(2);">
                            <option value="0">无</option>
                            <c:forEach items="${allGangkous}" var="g">
                                <option value="${g.id}">${g.pinyin}${g.name}</option>
                            </c:forEach>
                        </select>
                        距离
                        <input type="text" name="juli7" size="5"/>
                        <input type="button" value="保存" onclick="addJuli();"/>
                        <br/>
                        当前距离
                        <input type="text" id="curJuli"/>
                        </td>
                    </tr>
                    <tr bgcolor="#F9FFFF" valign="top">
                        <td id="betweenProfit">

                        </td>
                    </tr>
                    <tr valign="top" bgcolor="black">
                        <td id="nojuli" color="white">
                        </td>
                    </tr>
                </table>
            </td>
            <td id='showChushou' width="20%">
            </td>
            <td id='showChushou2' width="20%">
            </td>
        </tr>
        <tr bgcolor="black" valign="top" style="color:white;">
            <td id="showTopSell">
            </td>
            <td id="maxSell">
                <input type="radio" name="distance" value="6000">6000
            </td>
        </tr>
    </table>
    </html:form>
    <script type="text/javascript">
        function addGuojia()
        {
            window.location = '/hhcq/jiage.do?method=addGuojia&guojiaName=' + document.getElementsByName('guojia3')[0].value +
                              '&pinyin=' + document.getElementsByName('pinyin3')[0].value;
        }

        function addGangkou()
        {
            window.location = '/hhcq/jiage.do?method=addGangkou&gangkouName=' + document.getElementsByName('gangkou2')[0].value +
                               '&guojiaId=' + document.getElementsByName('guojia2')[0].value + '&pinyin=' +
                               document.getElementsByName('pinyin2')[0].value;
        }

        function addLeibie()
        {
            window.location = '/hhcq/jiage.do?method=addLeibie&leibieName=' + document.getElementsByName('leibie4')[0].value +
                              '&pinyin=' + document.getElementsByName('pinyin4')[0].value;
        }

        function addShangpin()
        {
            window.location = '/hhcq/jiage.do?method=addShangpin&shangpinName=' + document.getElementsByName('shangpin5')[0].value +
                               '&leibieId=' + document.getElementsByName('leibie5')[0].value + '&pinyin=' +
                               document.getElementsByName('pinyin5')[0].value;
        }

        function addChushou()
        {
            window.location = '/hhcq/jiage.do?method=addChushou&gangkouId=' + document.getElementsByName('gangkou6')[0].value +
                              '&shangpinId=' + document.getElementsByName('shangpin6')[0].value + '&jiage=' +
                              document.getElementsByName('jiage6')[0].value;
        }

        function addJuli()
        {
            window.location = '/hhcq/jiage.do?method=addJuli&gangkou1=' + document.getElementsByName('gangkou7a')[0].value +
                              '&gangkou2=' + document.getElementsByName('gangkou7b')[0].value + '&juli=' +
                              document.getElementsByName('juli7')[0].value;
        }
    </script>
    </body>
</html>
<%--select cc.name,bb.name,aa.sellprice,dd.name,aa.buyprice,aa.p,ee.juli from (select b.jiage-a.jiage as p,a.gangkouid as sell,a.jiage as sellprice,b.gangkouid as buy,b.jiage as buyprice,a.shangpinid from chushou a join jiage b on a.shangpinid=b.shangpinid where (b.jiage-a.jiage) > 4000) aa join gangkou bb on aa.sell = bb.id join shangpin cc on aa.shangpinid=cc.id join gangkou dd on aa.buy = dd.id left join juli ee on bb.id=ee.gangkou1id and dd.id=ee.gangkou2id order by p desc--%>
<!--
地中海
http://localhost:7800/hhcq/query.do?method=listAllJuli&guojiaIds=12&guojiaIds=7&guojiaIds=14&guojiaIds=10&guojiaIds=8&guojiaIds=13&guojiaIds=9&guojiaIds=11&guojiaIds=15
东亚
http://localhost:7800/hhcq/query.do?method=listAllJuli&guojiaIds=1&guojiaIds=2&guojiaIds=3
印度洋
http://localhost:7800/hhcq/query.do?method=listAllJuli&guojiaIds=6&guojiaIds=4&guojiaIds=5
加勒比
http://localhost:7800/hhcq/query.do?method=listAllJuli&guojiaIds=31
-->