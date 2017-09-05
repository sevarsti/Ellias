<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.saille.sys.BaseThread" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="../include/include.jsp"%>
<%
    String interruptThread = request.getParameter("thread");
    if(StringUtils.isNotEmpty(interruptThread)) {
        BaseThread t = BaseThread.threads.get(interruptThread);
        if(t != null) {
            t.interrupt();
//            Thread.sleep(1000);
        }
    }
    Map<String, BaseThread> threads = BaseThread.threads;
    List<String[]> tables = new ArrayList<String[]>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    for(String key : threads.keySet()) {
        BaseThread thread = threads.get(key);
        tables.add(new String[]{key, thread.getInterval() + "", thread.getLastExecuteTime() == null ? "" : sdf.format(thread.getLastExecuteTime()), thread.getNextExecuteTime() == null ? "" : sdf.format(thread.getNextExecuteTime())});
    }
%>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
    <tr class="head">
        <th>name</th>
        <th>间隔</th>
        <th>上次</th>
        <th>下次</th>
        <th>操作</th>
    </tr>
    <%
        for(int i = 0; i < tables.size(); i++) {
            String[] objs = tables.get(i);
    %>
    <tr class="row<%=i % 2 + 1%>">
        <c:forEach items="<%=objs%>" var="o">
            <td align="left">${o}</td>
        </c:forEach>
        <td>
            <input TYPE="button" onclick="doRun('<%=objs[0]%>');" value="立即执行"/>
        </td>
    </tr>
    <%
        }
    %>
</table>
<form action="" method="post">
    <input type="hidden" name="thread" id="thread"/>
</form>
<script type="text/javascript">
    function doRun(thread)
    {
        document.getElementById('thread').value = thread;
        document.forms[0].submit();
    }
</script>