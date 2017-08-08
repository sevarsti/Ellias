<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­MilanGoal</title>
    </head>
    <body>
        <c:set var="form" value="${milanGoalForm}"/>
        <html:form action="/milan/milanGoal.do">
            <input type="hidden" name="method" value="save"/>
            <html:hidden property="id"/>
            <table>
                <tr>
                    <td class="fieldname">
                        matchId
                    </td>
                    <td class="fieldvalue">
                        <html:text property="matchId"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        minute
                    </td>
                    <td class="fieldvalue">
                        <html:text property="minute"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        playerId
                    </td>
                    <td class="fieldvalue">
                        <html:text property="playerId"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        playerName
                    </td>
                    <td class="fieldvalue">
                        <html:text property="playerName"/>
                    </td>
                </tr>
            </table>
            <input type="button" value="±£´æ" onclick="document.forms[0].submit();" class="otterbtn"/>
        </html:form>
    </body>
</html>
