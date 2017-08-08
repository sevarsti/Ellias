<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-1-5
  Time: 17:01:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <table border="1">
      <tr>
          <td>Warehouse</td>
          <td>Date</td>
          <td>DOCNO</td>
          <td>Item</td>
          <td>Quantity</td>
          <td>Price/unit</td>
          <td>Amount</td>
          <td>Pay Code</td>
      </tr>
  <%
      try {
          String type = request.getParameter("type");
          Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
          Connection connection = DriverManager.getConnection( "jdbc:microsoft:sqlserver://172.28.44.50:1433;databaseName=pos","pos", "pos");
          PreparedStatement stmt = connection.prepareStatement("select * from XF_EXPORTHISTORY where XF_INVTRANSTYPE = ?");
          stmt.setString(1, type);
          ResultSet rs = stmt.executeQuery();
          while(rs.next()) {
              Double num = rs.getDouble("XF_QTY");
              Double price = rs.getDouble("XF_SELUPRICE");
  %>
      <tr>
          <td>
              <%=rs.getString("XF_STORECODE")%>
          </td>
          <td>
              <%=rs.getInt("XF_POSTDATE")%>
          </td>
          <td>
              <%=rs.getString("XF_DOCNO")%>
          </td>
          <td>
              <%=rs.getString("XF_STYLE")%>
          </td>
          <td>
              <%=num%>
          </td>
          <td>
              <%=price%>
          </td>
          <td>
              <%=num * price%>
          </td>
          <td>
              <%=rs.getString("XF_TENDERCODE")%>
          </td>
      </tr>
  <%
          }
      } catch(Exception ex) {
          ex.printStackTrace();
      }
  %>
  </table>
  </body>
</html>