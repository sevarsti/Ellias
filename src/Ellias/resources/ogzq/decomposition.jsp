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
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
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
    合成数量：<input type="text" size="5" id="num" value=""/>
    <input type="button" onclick="getGift();" value="领取欧冠魂"/>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th rowspan="2">登录名</th>
                <th rowspan="2">游戏名</th>
                <th rowspan="2">状态</th>
                <th rowspan="2">money</th>
                <th colspan="3">合同</th>
                <th colspan="3">汽车</th>
                <th colspan="3">游艇</th>
                <th colspan="3">飞机</th>
            </tr>
            <tr class="head">
                <c:forEach begin="1" end="3" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="3" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="3" var="i">
                    <th>${i}</th>
                </c:forEach>
                <c:forEach begin="1" end="3" var="i">
                    <th>${i}</th>
                </c:forEach>
            </tr>
            <%
                Logger LOGGER = Logger.getLogger(this.getClass());
                int i = 0;
                Set<String> ids = new HashSet<String>();
                if(request.getParameter("email") != null) {
                    ids.add(request.getParameter("email"));
                } else {
                    ids = IDUtils.IDInfos.keySet();
                }
                Map<String, String> allItems = new Hashtable<String, String>(); //key: email+itemcode
                Map<String, String> current = new Hashtable<String, String>();
                for(String id : ids) {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/ItemDecomposition.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("type", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    current.put(id, IDUtils.execute(id, pm).split("╋")[0]);

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
                    </td>
                    <td><%=IDUtils.getNick(key)%></td>
                    <td><%=current.get(key)%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("silver")%></td>
                    <%
                        for(int j = 1; j <= 3; j++) {
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
                        for(int j = 1; j <=3; j++) {
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
                        for(int j = 1; j <=3; j++) {
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
                        for(int j = 1; j <=3; j++) {
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
                </tr>
            <%
                    i++;
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function after(obj)
    {
        alert(obj);
    }

    function combine(email, id, count)
    {
        var num = document.getElementById('num').value;
        if(num == '') {
            alert('please choose number');
            return;
        } else {
            OgzqDwr.decomposition(email, id, num, after);
//            document.getElementById('num').value = '';
        }
    }

    function getGift()
    {
        OgzqDwr.getDecompositionGift('', after);
    }
</script>
</html>