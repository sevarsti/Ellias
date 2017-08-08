<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saille.ogzq.ArenaThread" %>
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
<%
    List<String> keys = ArenaThread.GETIDS();
    List<String> nicks = new ArrayList<String>();
    for(String s : keys) {
        nicks.add(ArenaThread.NICK.get(s));
    }
    List<String[][]> values = new ArrayList<String[][]>();
    for(String k : keys) {
        String[] against = ArenaThread.JJCAgainst.get(k);
        String[][] atts = new String[6][];
        if(against != null) {
            atts = new String[against.length][];
            for(int i = 0; i < against.length; i++) {
                atts[i] = against[i].split("[|]");
            }
        }
        values.add(atts);
    }
    request.setAttribute("key", keys);
    request.setAttribute("nick", nicks);
    request.setAttribute("value", values);
%>
    <body>
    增加新帐号：
    <input type="text" id="email"/>
    密码：
    <input type="password" id="pwd"/>
    <input type="button" value="增加" onclick="doAddMonitor();"/>
    <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>登录名</th>
            <th>游戏名</th>
            <th>ID</th>
            <th>等级</th>
            <th>昵称</th>
            <th>--</th>
            <th>？？</th>
            <th>实力</th>
            <th>？？</th>
            <th>声望</th>
            <th>？？</th>
            <th>通过</th>
        </tr>
<c:forEach items="${key}" var="k" varStatus="i">
<c:forEach items="${value[i.index]}" var="v" varStatus="ii">
<c:if test="${ii.index < 3}">
<tr class="row${i.index % 2 + 1}">
<c:if test="${ii.index == 0}">
<td rowspan="3" valign="top">
${k}
</td>
<td rowspan="3" valign="top">
${nick[i.index]}
</td>
</c:if>
<c:forEach items="${v}" var="vv">
<td>
${vv}
</td>
</c:forEach>
</tr>
</c:if>
</c:forEach>
        </c:forEach>
    </table>
    </body>
<script type="text/javascript">
    function doAddMonitor() {
        var id = document.getElementById('email').value;
        var pwd = document.getElementById('pwd').value;
        OgzqDwr.addArenaMonitor(id, pwd, after);
    }

    function after(obj) {
        alert(obj);
    }
</script>
</html>