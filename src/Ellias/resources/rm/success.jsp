<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell" %>
<%@ page import="com.saille.weixin.WeixinHandler" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.saille.util.FileUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-4-15
  Time: 13:40:54
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
    <head><title>节奏大师歌曲变速</title></head>
    <body>
        剪切成功，请通过以下地址下载<br/>
        <a href="../musiccut/${mp3path}">MP3文件</a><br/>
        <a href="../musiccut/${imdpath}">IMD文件</a><br/>
    </body>
</html>