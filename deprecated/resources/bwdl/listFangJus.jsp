<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-19
  Time: 1:54:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>防具一览</title>
  </head>
  <body>
    <c:set var="form" value="${fangjuForm}"/>
    <html:form action="/bwdl/fangju.do">
        <input type="hidden" name="method"/>
        <html:hidden property="version"/>
        <input type="hidden" name="fangjuId"/>
        名字：
        类别：
        <html:submit value="查询"/>
        <table width="40%" id="table">
            <tr class="head">
                <th width="10%">名字</th>
                <th>防御</th>
                <th>重量</th>
                <th>价格</th>
            </tr>
            <c:forEach items="${form.fangjus}" var="fangju">
                <tr>
                    <td>${fangju.name}</td>
                    <td>${fangju.fangyu}</td>
                    <td>${fangju.weight}</td>
                    <td>${fangju.price}</td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="addFangJu();" class="otterbtn"/>
  </html:form>
  <script type="text/javascript">
      function addFangJu()
      {
          document.getElementsByName('method')[0].value = 'edit';
          document.forms[0].submit();
      }
  </script>
  </body>
</html>