<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>MilanPlayer一览</title>
    </head>
    <body>
        <c:set var="form" value="${milanPlayerForm}"/>
        <div class="page_title">MilanPlayer一览</div>
        <c:if test="${!empty form.msg}">
            <div class="alert">${form.msg}</div>
        </c:if>
        <input type="button" value="比赛一览" onclick="document.location.href='<%=request.getContextPath()%>/milan/milanMatch.do?method=list'"/>
        <br/>
        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="frame">
            <tr class="head">
                <th>ID</th>
                <th>name</th>
                <th>nationality</th>
                <th>birth</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.milanPlayers}" var="obj" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${obj.id}</td>
                    <td>${obj.name}</td>
                    <td>${obj.nationality}</td>
                    <td>${obj.birth}</td>
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
                document.location = '<%=request.getContextPath()%>/milan/milanPlayer.do?method=edit&id=' + id;
            }

            function doDelete(id) {
                if(confirm('确定要删除吗？')) {
                    document.location = '<%=request.getContextPath()%>/milan/milanPlayer.do?method=delete&id=' + id;
                }
            }
        </script>
    </body>
</html>
