<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­EmpPosition</title>
    </head>
    <body>
        <c:set var="form" value="${empPositionForm}"/>
        <html:form action="/sys/empPosition.do">
            <input type="hidden" name="method" value="save"/>
            <html:hidden property="id"/>
            <table>
                <tr>
                    <td class="fieldname">
                        empId
                    </td>
                    <td class="fieldvalue">
                        <html:text property="empId"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        positionId
                    </td>
                    <td class="fieldvalue">
                        <html:text property="positionId"/>
                    </td>
                </tr>
            </table>
            <input type="button" value="±£´æ" onclick="document.forms[0].submit();" class="otterbtn"/>
        </html:form>
    </body>
</html>
