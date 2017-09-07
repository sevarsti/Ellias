<%@ page import="javax.sql.DataSource"%>
<%@ page import="java.io.PrintWriter"%>
<%@ page import="java.io.StringWriter"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
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
        String sql = request.getParameter("sql");
        if(sql != null) {
            try {
                DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
                JdbcTemplate jt = new JdbcTemplate(ds);
                jt.update(sql);
            } catch(Exception ex) {
                sw = new StringWriter( );
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                ex.printStackTrace();
                pw.close();
            }
        }
    %>
        <form action="" method="post" name="list_form" target="_self">
            <%--<input type="text" name="sql" id="sql" style="width:200;"/>--%>
            <textarea name="sql" rows="10" cols="100"><%=sql%></textarea>
            <br/>
            <input type="submit" value="È·¶¨"/>
        </form>
    <%
        if(sw != null) {
    %>
        <br/>
    <%=sw.toString()%>
    <%
        sw.close();
        } else {
    %>
    success
    <%
        }
    %>
    </body>
</html>