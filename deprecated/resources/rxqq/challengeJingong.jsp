<%@ page import="com.saille.rxqq.RxqqInstance" %>
<%@ page import="com.saille.rxqq.RxqqUtils" %>
<%@ page import="com.saille.rxqq.RxqqDwr" %>
<%@ page import="java.util.List" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-10-13
  Time: 18:09:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    List<String> ids = new RxqqDwr().generateIds(null);
    boolean ok = false;
    for (String id : ids) {
        if (!ok) {
            int money = new JSONObject(RxqqUtils.myInfo(id)).optInt("Money", 0);
            if (money > 10000) {
                String s = RxqqInstance.SERVER + "/Challenge.do?Password=1111&action=Challenge&Idx=1245";
                RxqqUtils.execute(id, s);
                ok = true;
            }
        }
    }
%>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>