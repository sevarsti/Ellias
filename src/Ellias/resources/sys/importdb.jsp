<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.BufferedInputStream" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.springframework.util.FileCopyUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="org.apache.tomcat.dbcp.dbcp.BasicDataSource" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="java.util.zip.ZipEntry" %>
<%@ page import="java.nio.charset.Charset" %>
<%@ page import="java.util.zip.ZipInputStream" %>
<%@ page import="java.util.*" %>
<%@ page import="com.ibatis.db.util.ScriptRunner" %>
<%
    if(request.getContentType() != null && request.getContentType().indexOf("multipart/form-data") >= 0) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List fileItems = upload.parseRequest(request);
        DiskFileItem item = (DiskFileItem) fileItems.get(0);
        if(!item.getName().endsWith(".zip")) {
            out.println("文件名不正确");
            return;
        }
        ZipInputStream zis = new ZipInputStream(item.getInputStream(), Charset.forName("GBK"));
        ZipEntry entry = zis.getNextEntry();
        if(!entry.getName().endsWith(".sql")) {
            out.println("压缩包内文件名不正确");
            return;
        }
        BasicDataSource ds = ((BasicDataSource)GlobalContext.getContextBean("mysql_ds"));
        InputStreamReader isr = new InputStreamReader(zis);
        Map<String, String> params = new HashMap<String, String>();
        params.put("driver", ds.getDriverClassName());
        params.put("url", ds.getUrl());
        params.put("username", ds.getUsername());
        params.put("password", ds.getPassword());
        params.put("stopOnError", "true");
        params.put("autoCommit", "false");
        ScriptRunner runner = new ScriptRunner(params);
        runner.runScript(isr);

        return;
    }
%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>
<form action="" enctype="multipart/form-data" method="post">
    <input type="file" name="sql" class="inputbox"/><br/>
    <input type="checkbox" name="sychronize" value="1"/>立刻同步</br>
    <input type="submit" value="上传"/>
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
