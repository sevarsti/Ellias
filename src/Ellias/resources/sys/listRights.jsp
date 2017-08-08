<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>Right一览</title>
    </head>
    <body>
        <c:set var="form" value="${rightForm}"/>
        <div class="page_title">Right一览</div>
        <c:if test="${!empty form.msg}">
            ${form.msg}
        </c:if>
        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="frame">
            <tr class="head">
                <th>ID</th>
                <th>resourceId</th>
                <th>orgId</th>
                <th>orgType</th>
                <th>auth</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.rights}" var="obj" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${obj.id}</td>
                    <td>
                        <c:set var="resource" value="${sys:getResource(obj.resourceId)}"/>
                        ${resource.name}(${resource.id})
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${obj.orgType==1}">
                                ${sys:getPosition(obj.orgId).name}(${obj.orgId})
                            </c:when>
                            <c:when test="${obj.orgType==2}">
                                ${sys:getEmployee(obj.orgId).name}(${obj.orgId})
                            </c:when>
                            <c:otherwise>
                                ${obj.orgId}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${obj.orgType==1}">
                                部门
                            </c:when>
                            <c:when test="${obj.orgType==2}">
                                员工
                            </c:when>
                            <c:otherwise>
                                未知
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${obj.auth}</td>
                    <td>
                        <input type="button" class="otterbtn" value="修改" onclick="doModify(${obj.id})"/>
                        <input type="button" class="otterbtn" value="删除" onclick="doDelete(${obj.id})"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="doModify(0);" class="otterbtn"/>
        <br/>
        <input type="button" value="刷新缓存" onclick="document.location='/sys/right.do?method=refresh';" class="otterbtn"/>
        <script type="text/javascript">
            function doModify(id) {
                document.location = '<%=request.getContextPath()%>/sys/right.do?method=edit&id=' + id;
            }

            function doDelete(id) {
                if(confirm('确定要删除吗？')) {
                    document.location = '<%=request.getContextPath()%>/sys/right.do?method=delete&id=' + id;
                }
            }
        </script>
    </body>
</html>
