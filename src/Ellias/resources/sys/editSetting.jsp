<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>编辑Employee</title>
    </head>
    <body>
        <c:set var="form" value="${settingForm}"/>
        <html:form action="/sys/setting.do">
            <input type="hidden" name="method" value="save"/>
            <table>
                <tr>
                    <td class="fieldname">
                        配置项
                    </td>
                    <td class="fieldvalue">
                        <c:choose>
                            <c:when test="${empty form.settingName}">
                                <html:text property="settingName"/>
                            </c:when>
                            <c:otherwise>
                                ${form.settingName}
                                <html:hidden property="settingName"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        分组
                    </td>
                    <td class="fieldvalue">
                        <html:text property="group"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        名称
                    </td>
                    <td class="fieldvalue">
                        <html:text property="name"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        说明
                    </td>
                    <td class="fieldvalue">
                        <html:text property="memo"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        类型
                    </td>
                    <td class="fieldvalue">
                        <html:select property="type" onchange="updateType();">
                            <html:option value="0">--选--</html:option>
                            <html:option value="1">INTEGER</html:option>
                            <html:option value="2">STRING</html:option>
                            <html:option value="3">NUMBER</html:option>
                            <html:option value="4">DATE</html:option>
                        </html:select>
                    </td>
                </tr>
                <tr id='typeTr' <c:if test="${form.type == 1 || form.type == 2}">style="display:none;"</c:if>>
                    <td class="fieldname">
                        格式
                    </td>
                    <td class="fieldvalue">
                        <html:text property="pattern"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        值
                    </td>
                    <td class="fieldvalue">
                        <html:text property="settingValue"/>
                        <%--<input type="text" name="settingValue"/>--%>
                    </td>
                </tr>
            </table>
            <input type="button" value="保存" onclick="doSubmit();" class="otterbtn"/>
        </html:form>
    <script type="text/javascript">
        function doSubmit()
        {
//            document.getElementsByName('positionIds')[0].value = ids;
            document.forms[0].submit();
        }

        function updateType()
        {
            var type = '';
            var typeOption = document.getElementsByName('type')[0];
            for(var i = 0; i < typeOption.options.length; i++) {
                if(typeOption.options[i].selected) {
                    type = typeOption.options[i].value;
                    break;
                }
            }
            if(type == 1 || type == 2) {
                document.getElementById('typeTr').style.display = 'none';
            } else {
                document.getElementById('typeTr').style.display = '';
            }
        }
    </script>
    </body>
</html>
