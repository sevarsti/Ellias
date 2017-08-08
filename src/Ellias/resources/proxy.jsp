<%@ page import="java.net.URL" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.OutputStream" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2016-5-18
  Time: 17:39:00
  To change this template use File | Settings | File Templates.
--%>
<%
    String u = request.getParameter("url");
    if(u == null || u.length() == 0) {
        return;
    }
    URL url = new URL(u);
    InputStream is = url.openStream();
    byte[] bytes = new byte[1024];
    int count = 0;
    while((count = is.read(bytes)) > 0) {
        response.getOutputStream().write(bytes, 0, count);
    }
%>