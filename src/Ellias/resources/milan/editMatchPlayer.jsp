<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <title>±à¼­MatchPlayer</title>
    </head>
    <body>
        <c:set var="form" value="${matchPlayerForm}"/>
        <html:form action="/milan/matchPlayer.do">
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
                        playerId
                    </td>
                    <td class="fieldvalue">
                        <html:text property="playerId"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        substitude
                    </td>
                    <td class="fieldvalue">
                        <html:text property="substitude"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        onTime
                    </td>
                    <td class="fieldvalue">
                        <html:text property="onTime"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        offTime
                    </td>
                    <td class="fieldvalue">
                        <html:text property="offTime"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        goal
                    </td>
                    <td class="fieldvalue">
                        <html:text property="goal"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        yellowCard
                    </td>
                    <td class="fieldvalue">
                        <html:text property="yellowCard"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        redCard
                    </td>
                    <td class="fieldvalue">
                        <html:text property="redCard"/>
                    </td>
                </tr>
                <tr>
                    <td class="fieldname">
                        ownGoal
                    </td>
                    <td class="fieldvalue">
                        <html:text property="ownGoal"/>
                    </td>
                </tr>
            </table>
            <input type="button" value="±£´æ" onclick="document.forms[0].submit();" class="otterbtn"/>
        </html:form>
    </body>
</html>
