<%@ page import="java.util.List" %>
<%@ page import="com.saille.rxqq.RxqqDwr" %>
<%@ page import="com.saille.rxqq.RxqqUtils" %>
<%@ page import="org.codehaus.jettison.json.JSONObject" %>
<%@ page import="org.codehaus.jettison.json.JSONArray" %>
<%@ page import="com.saille.rxqq.RxqqInstance" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-10-10
  Time: 22:00:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<%
    List<String> ids = new RxqqDwr().generateIds(null);
    for (String id : ids) {
        String value = RxqqUtils.execute(id, RxqqInstance.SERVER + "Activity.do?action=ItemStore");
        JSONObject obj = new JSONObject(value);
        JSONArray array = obj.getJSONArray("ItemList");
        int c1, c2, c3, c4, c5;
        c1 = c2 = c3 = c4 = c5 = 0;
        for (int i = 0; i < array.length(); i++) {
            int itemcode = array.getJSONObject(i).getInt("ItemCode");
            switch (itemcode) {
                case 8600401:
                    c1 = array.getJSONObject(i).getInt("ItemCount");
                    break;
                case 8600402:
                    c2 = array.getJSONObject(i).getInt("ItemCount");
                    break;
                case 8600403:
                    c3 = array.getJSONObject(i).getInt("ItemCount");
                    break;
                case 8600404:
                    c4 = array.getJSONObject(i).getInt("ItemCount");
                    break;
            }
        }
        c5 = c1 + c2 + c3 + c4;
        out.println(id + "\t" + c1 + "\t" + c2 + "\t" + c3 + "\t" + c4 + "\t" + c5 + "<br/>");
    }
%>
</body>
</html>