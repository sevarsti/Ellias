<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.saille.rm.util.RMUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2017-09-10
  Time: 19:52
  To change this template use File | Settings | File Templates.
  生成自制谱xml及对应文件
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>生成自制谱</title>
</head>
<body>
<html:form action="/rm.do" enctype="multipart/form-data">
    <input type="hidden" name="method"/>
    请选择上传excel文件：
    <html:file property="uploadXls"/>
    <br/>
    <input type="button" onclick="downloadexample();" value="下载模板"/>
    <input type="button" onclick="doupload();" value="提交"/>
</html:form>
<script type="text/javascript">
    function downloadexample()
    {
        document.getElementsByName("method")[0].value = "example";
        document.forms[0].submit();
    }
    function doupload()
    {
        document.getElementsByName("method")[0].value = "generateXml";
        document.forms[0].submit();
    }
</script>
</body>
</html>
