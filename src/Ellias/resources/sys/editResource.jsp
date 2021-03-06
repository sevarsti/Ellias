<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>�༭Resource</title>
    </head>
    <body>
        <c:set var="form" value="${resourceForm}"/>
        <html:form action="/sys/resource.do">
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
                        parentId
                    </td>
                    <td class="fieldvalue">
                        <sys:resSelect name="parentId" rootId="1" value="${form.parentId}"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        url
                    </td>
                    <td class="fieldvalue">
                        <html:text property="url"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        methodname
                    </td>
                    <td class="fieldvalue">
                        <html:text property="methodname"/>
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
            </table>
            <input type="button" value="����" onclick="document.forms[0].submit();" class="otterbtn"/>
        </html:form>
    </body>
</html>
