<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.springframework.util.FileCopyUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource" %>
<%@ page import="java.util.zip.ZipOutputStream" %>
<%@ page import="java.util.zip.ZipEntry" %>
<%
    String method = request.getMethod();
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    if("POST".equals(method)) {
        String full = request.getParameter("full");
        String[] tables = request.getParameterValues("table");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        byte[] bytes = (sdf.format(new Date()) + ".zip").getBytes("GBK");
        String encoded = new String(bytes, "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
        response.setHeader("Content-Type", " application/zip");

        List<String> commandstr = new ArrayList<String>();
        commandstr.add("mysqldump");
        commandstr.add("--default-character-set=gbk");
        commandstr.add("-uroot");
        commandstr.add("-p" + ((BasicDataSource) new InitialContext().lookup("java:comp/env/SinitekOtterapp")).getPassword());
        commandstr.add("ellias");
        if(full == null) {
            if(tables == null || tables.length == 0) {
                return;
            } else {
                for(String t : tables) {
                    commandstr.add(t);
                }
            }
        }
        String[] commands = new String[commandstr.size()];
        commandstr.toArray(commands);
        ProcessBuilder pb = new ProcessBuilder(commands);
        Process p = pb.start();
        InputStream is = p.getInputStream();
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        zos.putNextEntry(new ZipEntry(sdf.format(new Date()) + ".sql"));
        FileCopyUtils.copy(is, zos);
        is.close();
        p.destroy();
        out.clear();
        out = pageContext.pushBody();
        return;
    }
    JdbcTemplate jt = new JdbcTemplate(ds);
    List<Map<String, Object>> list = jt.queryForList("show tables");
    List<String> tables = new ArrayList<String>();
    for(Map<String, Object> m : list) {
        tables.add(m.get("TABLE_NAME").toString());
    }
%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-08-29
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<form action="" method="post">
    <table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
        <%
            int col = 0;
            int row = 1;
            for(int i = 0; i < tables.size(); i++) {
                String t = tables.get(i);
                if(i == 0) {
                    out.print("<tr class=\"head\"><td style=\"font-weight: bold;\" colspan=\"5\">" + t.toUpperCase().charAt(0) + "</td></tr>");
                    out.print("<tr class=\"row" + row + "\">");
                    row = 3 - row;
                } else if(t.charAt(0) != tables.get(i - 1).charAt(0)) {
                    out.print("<td colspan=\"" + (5 - col) + "\"></td></tr>");
                    out.print("<tr class=\"head\"><td style=\"font-weight: bold;\" colspan=\"5\">" + t.toUpperCase().charAt(0) + "</td></tr>");
                    out.print("<tr class=\"row1\">");
                    row = 2;
                    col = 0;
                } else if(col == 5) {
                    col = 0;
                    out.print("</tr>");
                    out.print("<tr class=\"row" + row + "\">");
                    row = 3 - row;
                }
                col++;
        %>
        <td>
            <input type="checkbox" name="table" value="<%=t%>" <%=(t.startsWith("sys_") || t.equalsIgnoreCase("setting")) ? "" : "checked"%> onclick="checkfull();"/><%=t%>
        </td>
        <%
            }
        %>
        <%
            if(col != 5) {
                out.print("<td colspan=\"" + (5 - col) + "\"></td>");
            }
        %>
        </tr>
    </table>
    <input type="checkbox" id="full" name="full" onclick="updatefull();"/>
    <input type="submit" value="È·¶¨"/>
</form>
<script type="text/javascript">
    function checkfull()
    {
        var boxes = document.getElementsByName("table");
        var full = true;
        for(var i = 0; i < boxes.length; i++)
        {
            if(!boxes[i].checked) {
                full = false;
                break;
            }
        }
        if(full)
        {
            document.getElementById("full").checked = true;
        }
        else
        {
            document.getElementById("full").checked = false;
        }
    }
    function updatefull()
    {
        var f = document.getElementById("full");
        var s = false;
        if(f.checked) {
            s = true;
        }
        var boxes = document.getElementsByName("table");
        for(var i = 0; i < boxes.length; i++)
        {
            boxes[i].checked = s;
        }
    }
</script>
</body>
</html>
