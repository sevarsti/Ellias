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
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/HhcqUtils.js'></script>
      <title>价格查询</title>
  </head>
    <body>
    <c:set var="form" value="${queryForm}"/>
    <%--<html:form action="/hhcq/jiage.do">--%>
        <table border="0" bgcolor="black" cellspacing="1">
            <tr bgcolor="#F9FFFF">
                <th>&nbsp;</th>
                <c:forEach items="${form.leibies}" var="leibie" varStatus="i">
                    <th colspan="${form.leibieLength[i.index]}">
                        ${leibie.name}
                    </th>
                </c:forEach>
            </tr>
            <tr bgcolor="#F9FFFF">
                <th nowrap>港口/商品</th>
                <!--<th>商品</th>-->
                <!--<th>价格</th>-->
                <c:forEach items="${form.shangpins}" var="sp" varStatus="s">
                    <th nowrap>${sp.name}</th>
                </c:forEach>
            </tr>
            <c:forEach items="${form.gangkous}" var="gk" varStatus="g"><tr bgcolor="#F9FFFF"><td nowrap>${gk.name}</td><c:forEach begin="0" end="${fn:length(form.shangpins)-1}" var="i"><c:choose><c:when test="${!empty form.chushous[g.index][i]}"><td bgcolor="yellow" nowrap align="right">${form.chushous[g.index][i].jiage}</td></c:when><c:otherwise><td nowrap align="right">${form.jiages[g.index][i].jiage}</td></c:otherwise></c:choose></c:forEach></tr></c:forEach>
        </table>
    <%--</html:form>--%>
    </body>
</html>