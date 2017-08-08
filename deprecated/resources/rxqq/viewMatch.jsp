<%@ page import="com.saille.rxqq.RxqqInstance" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-9-27
  Time: 21:52:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
    <body>
        <%
            Map<String, Vector<String[]>> matches = RxqqInstance.togetPlayMatch;
            Set<String> ids = matches.keySet();
            for (String id : ids) {
                for(String[] match : matches.get(id)) {
        %>
        <%=id%>---<%=match[0]%>---<%=match[1]%><br/>
    <%
        }}
    %>
    </body>
</html>