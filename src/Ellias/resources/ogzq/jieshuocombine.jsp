<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-9-9
  Time: 13:19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='../scripts/tablesort.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <%
        //进攻解说信息（-1：未获得） @ 组织解说信息（-1：未获得） @ 防守解说信息（-1：未获得）  （或 宋世雄信息）
//信息：是否开启（1、-1）&解说名*等级*星级 & 升星(升级)所需经验值*当前经验值 & 升星(升级)所需潜力卡Itemcode*所需潜力卡数*已有该卡数量 & 升星(升级)所需银币*已有银币数 & 开启后或下一星(等)级可提升实力
// 1&徐阳  *1*1& 2000*0& 5401*1*1&  1000*4679514& 500@
//-1&娄一辰*0*0&    0*0&    0*0*0&2 0000*4679514& 600@
//-1&何辛  *0*0&    0*0&    0*0*0& 20000*4679514& 600
        List<String> ids = new ArrayList<String>();
        String email = request.getParameter("email");
        if(email == null || email.length() == 0) {
            out.println("please input email");
            return;
        }
        List<Map<String, String>> currentItems = OperationUtils.listCoachBags(email, "5");
//        System.out.println("email=" + email);
        request.setAttribute("items", currentItems);
    %>
    <body>
        <table id='items' class="frame" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <td>名字</td>
                <td>code</td>
                <td>数量</td>
                <td>操作</td>
            </tr>
            <%
                for(int i = 0; i < currentItems.size(); i++) {
                    Map<String, String> item = currentItems.get(i);
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td><%=item.get("name")%></td>
                    <td><%=item.get("itemCode")%></td>
                    <td><%=item.get("count")%></td>
                    <td>
                        <%
                            if(item.get("itemCode").startsWith("54")) {
                        %>
                        <input type="button" value="合并" onclick="combine('<%=item.get("itemId")%>');"/>
                        <%
                            }
                        %>
                    </td>
                </tr>
            <%
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function combine(itemId)
    {
        OgzqDwr.combineJieshuo('<%=email%>', itemId, after);
    }

    function after(obj)
    {
        alert(obj);
    }
</script>
</html>