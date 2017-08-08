<%@ page import="com.saille.bwdl.service.BwdlService" %>
<%@ page import="com.saille.bwdl.service.ServiceHelper" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-26
  Time: 15:46:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    BwdlService service = ServiceHelper.getBwdlService();
    service.refreshCache();
%>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>