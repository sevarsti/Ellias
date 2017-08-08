<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­Right</title>
    </head>
    <body>
        <c:set var="form" value="${rightForm}"/>
        <html:form action="/sys/right.do">
            <input type="hidden" name="method" value="save"/>
            <html:hidden property="id"/>
            <table class="frame" border="0" cellpadding="1" cellspacing="1">
                <tr>
                    <td class="fieldname">
                        resourceId
                    </td>
                    <td class="fieldvalue">
                        <c:choose>
                            <c:when test="${form.id == 0}">
                                <sys:resSelect name="resourceId" rootId="1" value="${form.resourceId}"/>
                            </c:when>
                            <c:otherwise>
                                ${sys:getResource(form.resourceId).name}
                                <html:hidden property="resourceId"/>
                            </c:otherwise>
                        </c:choose>
                        <%--<html:text property="resourceId"/>--%>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        orgId
                    </td>
                    <td class="fieldvalue">
                        <sys:orgSelect name="org" rootId="1" value="${form.orgType}-${form.orgId}" includeEmployee="true"/>
                        <html:hidden property="orgId"/>
                        <html:hidden property="orgType"/>
                        <%--<html:text property="orgId"/>--%>
                    </td>
                </tr>
                <%--<tr>--%>
                    <%--<td class="fieldname">--%>
                        <%--orgType--%>
                    <%--</td>--%>
                    <%--<td class="fieldvalue">--%>
                        <%--<html:text property="orgType"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr>--%>
                    <%--<td class="fieldname">--%>
                        <%--auth--%>
                    <%--</td>--%>
                    <%--<td class="fieldvalue">--%>
                        <%--<html:text property="auth"/>--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
            <html:hidden property="auth" value="1"/>
            <input type="button" value="±£´æ" onclick="doSave();" class="otterbtn"/>
        </html:form>
    <script type="text/javascript">
        function doSave() {
            var org = document.getElementsByName('org')[0].value.split("-");
            document.getElementsByName('orgType')[0].value = org[0];
            document.getElementsByName('orgId')[0].value = org[1];
//            alert(document.getElementsByName('orgType')[0].value + "=" + document.getElementsByName('orgId')[0].value)
            document.forms[0].submit();
        }
    </script>
    </body>
</html>
