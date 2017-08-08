<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.util.ArrayList"%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-1-5
  Time: 16:44:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="g" %>
<html>
<head><title>Simple jsp page</title></head>
<body>
<form action="main.jsp">
    查询起始日期：<input name="startdate"/><br/>
    查询结束日期：<input name="enddate"/>&nbsp;&nbsp;<input type="submit" value="查询"/>
</form>
<table border="1">
    <tr>
        <td>Type</td>
        <td>Description</td>
        <td>Quantity</td>
        <td>Amount(RMB)</td>
        <td>Amount(K USD)</td>
    </tr>
<%
    String startDate = request.getParameter("startdate");
    String endDate = request.getParameter("enddate");
    if(startDate != null &&startDate.equals("")) {
        startDate = null;
    }
    if(endDate != null && endDate.equals("")) {
        endDate = null;
    }
    try {
        ArrayList<Object[]> list = new ArrayList<Object[]>();
        Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
        Connection connection = DriverManager.getConnection( "jdbc:microsoft:sqlserver://172.28.44.50:1433;databaseName=pos","pos", "pos");
        String sql = "select * from XF_EXPORTHISTORY where XF_STORECODE < 801";
        if(startDate != null) {
            sql += " and XF_POSTDATE >= " + startDate;
        }
        if(endDate != null) {
            sql += " and XF_POSTDATE <=" + endDate;
        }
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        int curNum = 0;
        while(rs.next()) {
            curNum++;
            String type = rs.getString("XF_INVTRANSTYPE");
            int index = 0;
            for(index = 0; index < list.size(); index++) {
                if(list.get(index)[0].equals(type)) {
                    double num = Double.parseDouble(String.valueOf(list.get(index)[1]));
                    list.get(index)[1] = num + rs.getDouble("XF_QTY");
                    double sum = Double.parseDouble(String.valueOf(list.get(index)[2]));
                    list.get(index)[2] = sum + rs.getDouble("XF_SELUPRICE") * num;
                    break;
                }
            }
            if(index >= list.size()) {   //没找到
                Object[] obj = new Object[3];
                obj[0] = type;
                obj[1] = rs.getDouble("XF_QTY");
                obj[2] = rs.getDouble("XF_SELUPRICE") * Double.parseDouble(String.valueOf(obj[1]));
                list.add(obj);
            }
        }
        for(Object[] obj : list) {
%>
    <tr>
        <td>
            <a href="detail.jsp?type=<%=obj[0]%>" target="_blank">
                <%=obj[0]%>
            </a>
        </td>
        <td>description</td>
        <td><%=Double.parseDouble(String.valueOf(obj[1]))%></td>
        <td><%=Double.parseDouble(String.valueOf(obj[2]))%></td>
        <td><%=Double.parseDouble(String.valueOf(obj[2])) / 7.5%></td>
    </tr>
<%
    }
%>
    </table>
total records: <%=curNum%>
</body>
<%
    } catch(Exception ex) {
        ex.printStackTrace();
    }
%>
</html>