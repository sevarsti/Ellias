<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-2-24
  Time: 0:28:17
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>港口一览</title>
  </head>
  <body>
    <c:set var="form" value="${gangkouForm}"/>
    <html:form action="/hhcq/gangkou.do?method=saveGangkou">
        名字：
        <html:text property="name"/><br/>
        国家：
        <html:select property="guojiaId">
            <html:option value="0">--</html:option>
            <c:forEach items="${form.guojias}" var="guojia">
                <html:option value="${guojia.id}">${guojia.pinyin}${guojia.name}</html:option>
            </c:forEach>
        </html:select>
        <html:submit value="保存"/>
        <table>
            <tr>
                <th>编号</th>
                <th>港口</th>
                <th>所属国家</th>
            </tr>
            <c:forEach items="${form.gangkous}" var="g">
                <tr>
                    <td>${g.id}</td>
                    <td>${g.name}</td>
                    <td>
                        ${hhcq:getGuojia(g.guojia).name}
                    </td>
                </tr>
            </c:forEach>
        </table>
  </html:form>
  <script type="text/javascript">
      document.getElementById('name').focus();
  </script>
  </body>
</html>