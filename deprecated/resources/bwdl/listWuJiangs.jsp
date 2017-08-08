<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-18
  Time: 0:01:41
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>武将一览</title>
  </head>
  <body>
    <c:set var="form" value="${wujiangForm}"/>
    <html:form action="/bwdl/wujiang.do">
        <input type="hidden" name="method"/>
        <html:hidden property="version"/>
        <input type="hidden" name="wujiangId"/>
        名字：
        类别：
        <html:submit value="查询"/>
        <table width="40%" id="table" cellpadding="0" cellspacing="1">
            <tr class="head">
                <th width="10%">姓名</th>
                <th>体</th>
                <th>武</th>
                <th>知</th>
                <th>忠</th>
                <th>德</th>
                <th>经</th>
                <th>兵种</th>
                <th>武器</th>
                <th>防具</th>
                <th>兵</th>
                <th>登场年份</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.wujiangs}" var="wujiang" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${wujiang.name}</td>
                    <td>${wujiang.ti}</td>
                    <td>${wujiang.wu}</td>
                    <td>${wujiang.zhi}</td>
                    <td>${wujiang.zhong}</td>
                    <td>${wujiang.de}</td>
                    <td>${wujiang.jing}</td>
                    <td>
                        ${bwdl:getBingzhong(wujiang.bingzhong)}
                    </td>
                    <td>
                        ${bwdl:getWuQi(wujiang.wuqi).name}
                    </td>
                    <td>
                    ${bwdl:getFangJu(wujiang.fangju).name}
                    </td>
                    <td>${wujiang.shibing}</td>
                    <td>${wujiang.chuchang}</td>
                    <td>
                        <input type="button" class="otterbtn" onclick="edit(${wujiang.id});" value="编辑"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="addWuJiang();" class="otterbtn"/>
  </html:form>
  <script type="text/javascript">
      function addWuJiang()
      {
          document.getElementsByName('method')[0].value = 'edit';
          document.forms[0].submit();
      }

      function edit(id)
      {
          document.getElementsByName('wujiangId')[0].value = id;
          document.getElementsByName('method')[0].value = 'edit';
          document.forms[0].submit();
      }
  </script>
  </body>
</html>