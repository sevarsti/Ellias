<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­MilanPlayer</title>
    </head>
    <body>
        <c:set var="form" value="${milanPlayerForm}"/>
        <html:form action="/milan/milanPlayer.do">
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
                        nationality
                    </td>
                    <td class="fieldvalue">
                        <html:text property="nationality"/>
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
            </table>
            <input type="button" value="±£´æ" onclick="document.forms[0].submit();" class="otterbtn"/>
        </html:form>
    </body>
</html>
