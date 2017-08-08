<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>Resource一览</title>
    </head>
    <body>
        <c:set var="form" value="${resourceForm}"/>
        <div class="page_title">Resource一览</div>
        <c:if test="${!empty form.msg}">
            ${form.msg}
        </c:if>
        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="frame">
            <tr class="head">
                <th>ID</th>
                <th>name</th>
                <th>parentId</th>
                <th>url</th>
                <th>methodname</th>
                <th>memo</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.resources}" var="obj" varStatus="i">
                <tr class="row${i.index % 2 + 1}" onclick="viewEmps(this);">
                    <td>${obj.id}</td>
                    <td>${functions:reptString("&nbsp;",obj.level * 2)}${obj.name}</td>
                    <td>
                        <c:set var="parent" value="${sys:getResource(obj.parentId)}"/>
                        ${parent.name}(${parent.id})
                    </td>
                    <td>${obj.url}</td>
                    <td>${obj.methodname}</td>
                    <td>${obj.memo}</td>
                    <td>
                        <input type="button" class="otterbtn" value="修改" onclick="doModify(${obj.id})"/>
                        <input type="button" class="otterbtn" value="删除" onclick="doDelete(${obj.id})"/>
                    </td>
                </tr>
                <tr class="row${i.index % 2 + 1}" style="display:none;">
                    <td colspan="7">
                        <c:choose>
                            <c:when test="${empty obj.rights}">
                                所有员工
                            </c:when>
                            <c:otherwise>
                                ${sys:getEmployeeNames(obj.rights)}
                            </c:otherwise>
                        </c:choose>
                        &nbsp;
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="doModify(0);" class="otterbtn"/>
        <script type="text/javascript">
            function doModify(id) {
                document.location = '<%=request.getContextPath()%>/sys/resource.do?method=edit&id=' + id;
            }

            function doDelete(id) {
                if(confirm('确定要删除吗？')) {
                    document.location = '<%=request.getContextPath()%>/sys/resource.do?method=delete&id=' + id;
                }
            }
            
            function viewEmps(obj) {
                var table = obj.parentNode;
                var row = table.rows[obj.rowIndex + 1];
                if (row.style.display == 'none') {
                    row.style.display = '';
                } else {
                    row.style.display = 'none';
                }
            }
        </script>
    </body>
</html>
