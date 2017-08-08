<%@ page import="com.saille.rxqq.RxqqInstance" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.saille.rxqq.RxqqUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-10-4
  Time: 0:17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <%
      Set<String> ids = RxqqInstance.togetPlayMatch.keySet();
      for (String id : ids) {
          if(RxqqInstance.id.containsKey(id)) {
              RxqqUtils.getMatchPlayer(id);
          }
      }
  %>
  <body>Place your content here</body>
</html>