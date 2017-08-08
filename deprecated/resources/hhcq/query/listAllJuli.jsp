<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-3-3
  Time: 17:00:08
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-2-27
  Time: 1:57:22
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <%--<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>--%>
      <%--<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>--%>
      <%--<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/HhcqUtils.js'></script>--%>
      <title>距离查询</title>
  </head>
    <body>
    <div>
        <html:form action="/hhcq/query.do?method=listAllJuli">
            <table border="0" bgcolor="black" cellspacing="1">
                <tr bgcolor="#F9FFFF">
                    <td>
                        国家：
                    </td>
                    <td>
                        <c:forEach items="${hhcq:getAllGuojias()}" var="g">
                            <html:checkbox property="guojiaIds" value="${g.id}">${g.name}</html:checkbox>
                        </c:forEach>
                    </td>
                </tr>
                <%--<tr><td colspan="2" bgcolor="#F9FFFF">--%>
                    <%--<input type="checkbox" onclick="updateGuojia(1);">北欧--%>
                    <%--<input type="checkbox" onclick="updateGuojia(2);">地中海--%>
                    <%--<input type="checkbox" onclick="updateGuojia(3);">非洲--%>
                    <%--<input type="checkbox" onclick="updateGuojia(4);">印度洋--%>
                    <%--<input type="checkbox" onclick="updateGuojia(5);">东南亚--%>
                    <%--<input type="checkbox" onclick="updateGuojia(6);">亚洲--%>
                <%--</td></tr>--%>
                <tr><td colspan="2" bgcolor="#F9FFFF"><html:submit property="submit" value="查询"/></td></tr>
            </table>
        </html:form>
    </div>
    <c:set var="form" value="${queryForm}"/>
    <%--<html:form action="/hhcq/jiage.do">--%>
        <table border="0" bgcolor="black" cellspacing="1">
            <tr bgcolor="#F9FFFF">
                <th rowspan="2" colspan="2">&nbsp;</th>
                <c:forEach items="${form.guojias}" var="guojia" varStatus="i">
                    <th colspan="${form.guojiaLength[i.index]}">
                        ${guojia.name}
                    </th>
                </c:forEach>
                <td nowrap>&nbsp;</td>
            </tr>
            <tr bgcolor="#F9FFFF">
                <c:forEach items="${form.gangkous}" var="gk" varStatus="s">
                    <th>${gk.name}</th>
                </c:forEach>
                <td nowrap>&nbsp;</td>
            </tr>
            <c:forEach items="${form.gangkous}" var="gk" varStatus="g">
                <tr bgcolor="#F9FFFF">
                    <td>&nbsp;</td>
                    <td nowrap>${gk.name}</td>
                    <c:forEach begin="0" end="${fn:length(form.gangkous)-1}" var="i">
                        <c:if test="${!empty form.julis[g.index][i].juli}"><td nowrap align="right" bgcolor="yellow">&nbsp;</td></c:if>
                        <c:if test="${empty form.julis[g.index][i].juli}"><td nowrap align="right">&nbsp;</td></c:if>
                    </c:forEach><td nowrap>${gk.name}</td></tr></c:forEach>
            <tr bgcolor="#F9FFFF">
                <th rowspan="2" colspan="2">&nbsp;</th>
                <c:forEach items="${form.gangkous}" var="gk" varStatus="s">
                    <th>${gk.name}</th>
                </c:forEach>
                <td nowrap>&nbsp;</td>
            </tr>
            <tr bgcolor="#F9FFFF">
                <c:forEach items="${form.guojias}" var="guojia" varStatus="i">
                    <th colspan="${form.guojiaLength[i.index]}">
                        ${guojia.name}
                    </th>
                </c:forEach>
                <td nowrap>&nbsp;</td>
            </tr>
        </table>
    <%--</html:form>--%>
    </body>
</html>