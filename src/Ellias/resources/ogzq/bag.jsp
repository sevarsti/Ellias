<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.*" %>
<%@ page import="java.net.URLDecoder" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <body>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th rowspan="2">登录名</th>
                <th rowspan="2">游戏名</th>
                <th rowspan="2">money</th>
                <th colspan="9">合同</th>
                <th colspan="7">汽车</th>
                <th colspan="7">游艇</th>
                <th colspan="7">飞机</th>
                <th rowspan="2">碎片</th>
                <th rowspan="2">魂</th>
            </tr>
            <tr class="head">
                <c:forEach begin="1" end="9" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="7" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="7" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="7" var="i">
                    <th>${i}</th>
                </c:forEach>
            </tr>
            <%
                Logger LOGGER = Logger.getLogger(this.getClass());
                int i = 0;
                List<String> ids = new ArrayList<String>();
                String email = request.getParameter("email");
                if(email != null && !email.equals("")) {
                    String[] emails = email.split("\\,");
                    for(String e : emails) {
                        ids.add(URLDecoder.decode(e.trim(), "utf-8"));
                    }
//                    keys.add(email);
                } else {
                    ids = IDUtils.GETIDS();
                }
                Map<String, String> allItems = new Hashtable<String, String>(); //key: email+itemcode
                for(String id : ids) {
                    List<Map<String, String>> items = null;
                    try {
                        items = OperationUtils.listBags(id, "1");
                    } catch(Exception ex) {
                    }
                    if(items != null) {
                        for(Map<String, String> m : items) {
                            allItems.put(id + m.get("itemid"), m.get("number"));
                        }
                    }
                }
                List<String> keys = (List) Arrays.asList(ids.toArray());
                Collections.sort(keys);
//                List<String> keys = IDUtils.GETIDS();
                for(String key : keys) {
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td>
                        <%=key%>
                        <input type="button" value="卖道具" onclick="sellLv5('<%=key%>')"/>
                    </td>
                    <td><%=IDUtils.getNick(key)%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("silver")%></td>
                    <%
                        for(int j = 1; j <= 9; j++) {
                            String count = allItems.get(key + (1100 + j)) == null ? "0" : allItems.get(key + (1100 + j));
                    %>
                    <td>
                        <a style="cursor:pointer;" onclick="javascript:combine('<%=key%>','<%=(1100 + j)%>', <%=count%>)">
                            <%=count%>
                        </a>
                    </td>
                    <%
                        }
                    %>
                    <%
                        for(int j = 1; j <=7; j++) {
                            String count = allItems.get(key + (1200 + j)) == null ? "0" : allItems.get(key + (1200 + j));
                    %>
                    <td>
                        <a style="cursor:pointer;" onclick="javascript:combine('<%=key%>','<%=(1200 + j)%>', <%=count%>)">
                            <%=count%>
                        </a>
                    </td>
                    <%
                        }
                    %>
                    <%
                        for(int j = 1; j <=7; j++) {
                            String count = allItems.get(key + (1300 + j)) == null ? "0" : allItems.get(key + (1300 + j));
                    %>
                    <td>
                        <a style="cursor:pointer;" onclick="javascript:combine('<%=key%>','<%=(1300 + j)%>', <%=count%>)">
                            <%=count%>
                        </a>
                    </td>
                    <%
                        }
                    %>
                    <%
                        for(int j = 1; j <=7; j++) {
                            String count = allItems.get(key + (1400 + j)) == null ? "0" : allItems.get(key + (1400 + j));
                    %>
                    <td>
                        <a style="cursor:pointer;" onclick="javascript:combine('<%=key%>','<%=(1400 + j)%>', <%=count%>)">
                            <%=count%>
                        </a>
                    </td>
                    <%
                        }
                    %>
                    <td>
                        <a style="cursor:pointer;" onclick="javascript:combine('<%=key%>','11201', <%=allItems.get(key + "11201") == null ? "0" : allItems.get(key + "11201")%>)">
                            <%=allItems.get(key + "11201") == null ? "0" : allItems.get(key + "11201")%>
                        </a>
                    </td>
                    <td><%=allItems.get(key + "11202") == null ? "0" : allItems.get(key + "11202")%></td>
                </tr>
            <%
                    i++;
                }
            %>
        </table>
        合成数量：<input type="text" size="5" id="num" value=""/>
        <input type="checkbox" id="all"/><label for="all">卖出全部</label>
        <c:set var="row" value="1"/>
        <table id="sell" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th>登录名</th>
                <th>游戏名</th>
                <th>物品</th>
                <th>CODE</th>
                <th>价格</th>
                <th>剩余/挂单数量</th>
                <th>卖出数量</th>
                <th>剩余时间</th>
            </tr>
            <%
                for (int j = 0; j < keys.size(); j++) {
                    String key = keys.get(j);
                    List<Map<String, String>> items = OperationUtils.viewSell(key);
                    for(Map<String, String> item : items) {
            %>
            <tr class="row<%=j % 2 + 1%>">
                <td><%=key%></td>
                <td><%=IDUtils.getNick(key)%></td>
                <td><%=OgzqURL.ITEMCODE.get(item.get("itemcode"))%></td>
                <td><%=item.get("itemcode")%></td>
                <td><%=item.get("price")%></td>
                <td><%=item.get("maxcount")%></td>
                <td><%=item.get("sellcount")%></td>
                <td>
                    <%=item.get("lefttime")%>
                    <input type="button" value="撤单" onclick="doCheDan(this, '<%=key%>', '<%=item.get("sellId")%>');"/>
                    <c:set var="row" value="${row + 1}"/>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function doCheDan(input, email, id) {
        var table = document.getElementById('sell');
        table.deleteRow(input.parentNode.parentNode.rowIndex);
        OgzqDwr.cheDan(email, id, after);
    }

    function after(obj)
    {
        alert(obj);
    }

    function combine(email, id, count)
    {
        if(document.getElementById('all').checked) {
            OgzqDwr.combine(email, id, count, after);
        } else {
            var num = document.getElementById('num').value;
            if(num == '') {
                OgzqDwr.combine(email, id, 0, after);
            } else {
                var c = parseInt(num, 10) * 5;
                OgzqDwr.combine(email, id, c, after);
                document.getElementById('num').value = '';
            }
        }
    }

    function sellLv5(email)
    {
        OgzqDwr.doSellLv5(email, afterSellLv5);
    }

    function afterSellLv5(str)
    {
        alert(str);
    }
</script>
</html>