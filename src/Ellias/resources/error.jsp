<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-09-02
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include/include.jsp"%>
<html>
  <head>
      <title>错误</title>
  </head>
<body>
<%
    Map<String, String> errors = new HashMap<String, String>();
    errors.put("noright", "对不起，您没有权限");
    String errormsg = request.getParameter("noright");
    if(errormsg == null) {
        errormsg = "未知错误";
    }
    errormsg = errors.get(errormsg);
%>
<%=errormsg%>
</body>
</html>