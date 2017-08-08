<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2008-6-24
  Time: 23:12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
    <body>
    <%
        StringWriter sw = null;
        String[] columns = new String[0];
        String sql = request.getParameter("sql");
        String db = request.getParameter("db");
        ResultSet rs = null;
        if(sql != null) {
            try {
                if(db.equals("otterapp")) {
//                    Class.forName( "oracle.jdbc.OracleDriver" );
                    Connection connection = ((DataSource) GlobalContext.getSpringContext().getBean("mysql_ds")).getConnection();
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery(sql);
                } else if(db.equals("wind")) {
                    Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//                    Connection connection = (( BasicDataSource )UserContext.getSpringContext().getBean( "wd_ds")).getConnection();
                    Connection connection = DriverManager.getConnection("jdbc:microsoft:Sqlserver://172.16.2.3:1433;databaseName=windnet_db;autoReconnect=true", "sa", "ytsnsa");
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery(sql);
                } else if(db.equals("tianx")) {
                    Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver");
//                    Connection connection = (( BasicDataSource )UserContext.getSpringContext().getBean( "wd_ds")).getConnection();
                    Connection connection = DriverManager.getConnection("jdbc:microsoft:Sqlserver://172.16.2.28:1433;databaseName=TXDB;autoReconnect=true", "km", "km1234");
                    Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    rs = stmt.executeQuery(sql);
                }
                columns = new String[rs.getMetaData().getColumnCount()];
                for(int i = 0; i < columns.length; i++) {
                    columns[i] = rs.getMetaData().getColumnName(i + 1);
                }
            } catch(Exception ex) {
                sw = new StringWriter();
                ex.printStackTrace(new PrintWriter(sw));
                ex.printStackTrace();
            }
        }
    %>
        <form action="" method="post" name="list_form" target="_self">
            <%--<input type="text" name="sql" id="sql" style="width:200;"/>--%>
            <textarea name="sql" rows="10" cols="100"><%=sql%></textarea>
            <br/>
            <select name="db">
                <option value="otterapp">otterapp</option>
                <option value="wind">wind</option>
                <option value="tianx">tianx</option>
            </select>
            <input type="submit" value="È·¶¨"/>
            <table border="1">
                <tr>
                    <%
                        for(String col : columns) {
                    %>
                    <td>
                        <%=col%>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <%
                    while(rs != null && rs.next()) {
                %>
                <tr>
                    <%
                        for(int i = 1; i <= columns.length; i++) {
                            try {
                    %>
                    <td><%=rs.getString(i)%></td>
                    <%
                            }catch(Exception ex) {}
                        }
                    %>
                </tr>
                <%
                    }
                %>
            </table>
        </form>
    <%
        if(sw != null) {
    %>
        <br/>
    <%=sw.toString()%>
    <%
        }
    %>
    </body>
</html>