<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2016-6-4
  Time: 0:27:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.dailyLoop.ParentThread" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <body>
    <%
        Map<String, ParentThread> threads = ParentThread.threads;
        List<String[]> results = new ArrayList<String[]>();
        for(String k : threads.keySet()) {
            String[] r = new String[4];
            r[0] = k;
            r[1] = threads.get(k).getLastCheckTime();
            r[2] = threads.get(k).getLastEndTime();
            r[3] = (threads.get(k).getWaitTime() / 1000) + "";
            results.add(r);
        }
    %>
    当前时间：<%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())%>
    <table border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>线程</th>
            <th>上次执行时间</th>
            <th>上次结束时间</th>
            <th>等待时间</th>
        </tr>
        <%for(int i = 0; i < results.size(); i++) {%>
        <tr class="row<%=i % 2 + 1%>">
            <%
                for(String s : results.get(i)) {
            %>
            <td><%=s%></td>
            <%
                }
            %>
        </tr>
        <%}%>
    </table>
    </body>
<script type="text/javascript">
</script>
</html>