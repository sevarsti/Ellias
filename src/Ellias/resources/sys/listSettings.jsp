<%@ include file="../include/include.jsp"%>

<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>设置一览</title>
    </head>
    <body>
        <c:set var="form" value="${settingForm}"/>
        <div class="page_title">设置一览</div>
        <c:if test="${!empty form.msg}">
            ${form.msg}
        </c:if>
        <table width="100%" border="0" cellpadding="1" cellspacing="1" class="frame">
            <tr class="head">
                <th>setting</th>
                <th>group</th>
                <th>name</th>
                <th>memo</th>
                <th>type</th>
                <th>value</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${form.settings}" var="obj" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <td>${obj.setting}</td>
                    <td>${obj.group}</td>
                    <td>${obj.name}</td>
                    <td>${obj.memo}</td>
                    <td>
                        <c:choose>
                            <c:when test="${obj.type == '1'}"><!--intvalue-->
                                INTEGER
                            </c:when>
                            <c:when test="${obj.type == '2'}"><!--strvalue-->
                                STRING
                            </c:when>
                            <c:when test="${obj.type == '3'}"><!--numbervalue-->
                                NUMBER
                            </c:when>
                            <c:when test="${obj.type == '4'}"><!--datevalue-->
                                DATE
                            </c:when>
                            <c:otherwise>
                                未知
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${obj.type == '1'}"><!--intvalue-->
                                ${obj.intValue}
                            </c:when>
                            <c:when test="${obj.type == '2'}"><!--strvalue-->
                                ${obj.strValue}
                            </c:when>
                            <c:when test="${obj.type == '3'}"><!--numbervalue-->
                                <fmt:formatNumber value="${obj.numberValue}" pattern="${obj.pattern}"/>
                            </c:when>
                            <c:when test="${obj.type == '4'}"><!--datevalue-->
                                <fmt:formatDate value="${obj.dateValue}" pattern="${obj.pattern}"/>
                            </c:when>
                            <c:otherwise>
                                未知参数类型
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <input type="button" class="otterbtn" value="修改" onclick="doModify('${obj.setting}')"/>
                        <input type="button" class="otterbtn" value="删除" onclick="doDelete('${obj.setting}')"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <input type="button" value="增加" onclick="doModify('');" class="otterbtn"/>
        <script type="text/javascript">
            function doModify(setting) {
                document.location = '<%=request.getContextPath()%>/sys/setting.do?method=edit&settingName=' + setting;
            }

            function doDelete(setting) {
                document.location = '<%=request.getContextPath()%>/sys/setting.do?method=delete&settingName=' + setting;
            }
        </script>
    </body>
</html>
