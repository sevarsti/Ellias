<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-2-14
  Time: 16:08:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.LoopUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <%!
        private List<Object[]> sort(List<Object[]> list, int start, int end) {
            return null;
        }
    %>
    <%
        Map<String, Map<String, Object[]>> allEvents = LoopUtils.getInstance().getEvents();
        List<Object[]> events = new ArrayList<Object[]>();
        for(String email : allEvents.keySet()) {
            Map<String, Object[]> singleEvents = allEvents.get(email);
            for(String eventId : singleEvents.keySet()) {
                Object[] ev = singleEvents.get(eventId);
                events.add(new Object[]{email, Integer.parseInt(eventId), ev[0], ev[1]});
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        List<String> emails = new ArrayList<String>();
        for(Object[] obj : events) {
            if(!emails.contains(String.valueOf(obj[0]))) {
                emails.add(String.valueOf(obj[0]));
            }
        }
        Collections.sort(emails);
        List<Integer> eventIds = new ArrayList<Integer>();
        eventIds.add(LoopUtils.DOCHALLENGE);
        eventIds.add(LoopUtils.DOARENA);
        eventIds.add(LoopUtils.DOGETTACTICPOINT);
        eventIds.add(LoopUtils.DOOGTRAININGMATCH);
        eventIds.add(LoopUtils.DOTRAININGMATCH);
        eventIds.add(LoopUtils.DOSEARCHPLAYER);
        eventIds.add(LoopUtils.DOTEAMGAME);
        eventIds.add(LoopUtils.DOTRAIN);
        eventIds.add(LoopUtils.DODEFAULT);
        eventIds.add(LoopUtils.DOQUERYTASK);
        eventIds.add(LoopUtils.DOWORLDCUP);
        eventIds.add(LoopUtils.DOFUBEN);
        eventIds.add(LoopUtils.DOWORLDCUP32);
        eventIds.add(LoopUtils.DOGJXLS);
        eventIds.add(LoopUtils.DOPELE);
    %>
    <body>
        <select id="email" onchange="updateList();">
            <option value="-1">--全部--</option>
            <c:forEach var="e" items="<%=emails%>">
                <option value="${e}">${e}</option>
            </c:forEach>
        </select>
        <select id="event" onchange="updateList();">
            <option value="-1">--全部--</option>
            <%
                for(Integer id : eventIds) {
            %>
                <option value="<%=id%>"><%=LoopUtils.getEventDesc(id)%></option>
            <%
                }
            %>
        </select>
        一共有<%=events.size()%>个事件
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1" class="rowover">
            <tr class="head">
                <th>email</th>
                <th>事件</th>
                <th>下次执行时间</th>
                <th>间隔时间</th>
            </tr>
            <%
                for(int i = 0; i < events.size(); i++) {
                    Object[] obj = events.get(i);
                    String email = (String) obj[0];
                    int eventId = ((Integer) obj[1]).intValue();
                    long nextTime = ((Long) obj[2]).longValue();
                    long delaTime = ((Long) obj[3]).longValue();
            %>
            <tr class="row<%=i % 2 + 1%>">
                <td value="<%=email%>">
                    <%=email%>
                    <input type="button" value="立刻执行" onclick="moveEventToTop('<%=email%>', <%=eventId%>);"/>
                </td>
                <td value="<%=eventId%>"><%=LoopUtils.getEventDesc(eventId)%></td>
                <td><%=sdf.format(new Date(nextTime))%></td>
                <td><%=delaTime%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function moveEventToTop(email, id)
    {
        OgzqDwr.moveEventToTop(email, id, alertObj);
    }

    function alertObj(obj)
    {
        alert(obj);
    }

    function updateList()
    {
        var email = document.getElementById('email').value;
        var event = document.getElementById('event').value;
        var table = document.getElementById('info');
        var match = true;
        for(var i = 1; i < table.rows.length; i++) {
            match = true;
            if((email != '-1') && (email != table.rows[i].cells[0].attributes['value'].value)) {
                match = false;
            }
            if((event != '-1') && (event != table.rows[i].cells[1].attributes['value'].value)) {
                match = false;
            }
            if(match) {
                table.rows[i].style.display = '';
            } else {
                table.rows[i].style.display = 'none';
            }
        }
    }
</script>
</html>