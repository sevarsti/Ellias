<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­Employee</title>
    </head>
    <body>
        <c:set var="form" value="${employeeForm}"/>
        <html:form action="/sys/employee.do">
            <input type="hidden" name="method" value="save"/>
            <html:hidden property="id"/>
            <table>
                <tr>
                    <td class="fieldname">
                        name
                    </td>
                    <td class="fieldvalue">
                        <html:text property="name"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        µÇÂ¼Ãû
                    </td>
                    <td class="fieldvalue">
                        <html:text property="loginname"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        ÃÜÂë
                    </td>
                    <td class="fieldvalue">
                        <html:password property="pwd"/>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td class="fieldname">--%>
                        <%--positionId--%>
                    <%--</td>--%>
                    <%--<td class="fieldvalue">--%>
                        <%--<sys:orgSelect name="positionId" rootId="1"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr>
                    <td class="fieldname">
                        gender
                    </td>
                    <td class="fieldvalue">
                        <html:text property="gender"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        birth
                    </td>
                    <td class="fieldvalue">
                        <html:text property="birth"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        mobile
                    </td>
                    <td class="fieldvalue">
                        <html:text property="mobile"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        email
                    </td>
                    <td class="fieldvalue">
                        <html:text property="email"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        memo
                    </td>
                    <td class="fieldvalue">
                        <html:text property="memo"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        ËùÊô¸ÚÎ»
                    </td>
                    <td class="fieldvalue">
                        <html:hidden property="positionIds"/>
                        <sys:multiPositionSelectionTag name="ids" rootId="1" size="5" select="${form.positionIds}"/>
                    </td>
                </tr>
            </table>
            <input type="button" value="±£´æ" onclick="doSubmit();" class="otterbtn"/>
        </html:form>
    <script type="text/javascript">
        function doSubmit()
        {
            var ids = "";
            var select = document.getElementById("ids");
            for(var i = 0; i < select.options.length; i++) {
                if(select.options[i].selected) {
                    if(ids.length > 0) {
                        ids = ids + ",";
                    }
                    ids = ids + select.options[i].value;
                }
            }
            document.getElementsByName('positionIds')[0].value = ids;
            document.forms[0].submit();
        }
    </script>
    </body>
</html>
