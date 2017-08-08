<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-2-18
  Time: 0:36:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.pampers.DetectPampers" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-2-10
  Time: 1:17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/PampersDwr.js'></script>
    </head>
    <%
        DetectPampers service = (DetectPampers) GlobalContext.getSpringContext().getBean("pampersDetect", DetectPampers.class);
        List<Map<String, Object>> list = service.currentItems;
    %>
    <c:set var="ids" value="<%=list%>"/>
    <body>
    <input type="button" onclick="init();" value="初始化"/>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1" class="rowover">
            <tr class="head">
                <th>ID</th>
                <th>名称</th>
                <th>库存</th>
                <th>更新时间</th>
                <th>补货时间</th>
            </tr>
            <c:forEach items="<%=list%>" var="item" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${item['ITEMID']}</td>
                    <td>${item['NAME']}</td>
                    <td>${item['COUNT']}</td>
                    <td><fmt:formatDate value="${item['UPDATETIME']}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${item['ADDTIME']}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>