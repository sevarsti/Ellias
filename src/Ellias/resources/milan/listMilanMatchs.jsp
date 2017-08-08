<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>MilanMatch一览</title>
    </head>
    <body>
        <div class="page_title">MilanMatch一览</div>
        <c:set var="form" value="${milanMatchForm}"/>
        <c:if test="${!empty form.msg}">
            ${form.msg}
        </c:if>
        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="frame">
            <tr class="head">
                <th>ID</th>
                <th>against</th>
                <th>statium</th>
                <th>city</th>
                <th>type</th>
                <th>round</th>
                <th>比分</th>
                <th>时间</th>
                <th>homeawy</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.milanMatchs}" var="obj" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${obj.id}</td>
                    <td>${obj.against}</td>
                    <td>${obj.statium}</td>
                    <td>${obj.city}</td>
                    <td>${obj.type}</td>
                    <td>${obj.round}</td>
                    <td>${obj.goal}:${obj.goaled}</td>
                    <td>${obj.year}-${obj.date}&nbsp;${obj.time}</td>
                    <td>${obj.homeawy}</td>
                    <td>
                        <input type="button" class="otterbtn" value="修改" onclick="doModify(${obj.id})"/>
                        <input type="button" class="otterbtn" value="删除" onclick="doDelete(${obj.id})"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="doModify(0);" class="otterbtn"/>
        <script type="text/javascript">
            function doModify(id) {
                document.location = '<%=request.getContextPath()%>/milan/milanMatch.do?method=edit&id=' + id;
            }

            function doDelete(id) {
                if(confirm('确定要删除吗？')) {
                    document.location = '<%=request.getContextPath()%>/milan/milanMatch.do?method=delete&id=' + id;
                }
            }
        </script>
    </body>
</html>
