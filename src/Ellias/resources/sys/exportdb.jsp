<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.springframework.util.FileCopyUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
    String method = request.getMethod();
    if("POST".equals(method)) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        byte[] bytes = (sdf.format(new Date()) + ".sql").getBytes("GBK");
        String encoded = new String(bytes, "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
        response.setHeader("Content-Type", "text/plain");

        ProcessBuilder pb = new ProcessBuilder();
        pb = pb.command("mysqldump", "--default-character-set=gbk", "-uroot", "-psjtuagent", "ellias");
        Process p = pb.start();
        InputStream is = p.getInputStream();
        FileCopyUtils.copy(is, response.getOutputStream());
        is.close();
        p.destroy();
        return;
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
    <input type="submit" value="È·¶¨"/>
</form>
</body>
</html>
