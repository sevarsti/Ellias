<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.saille.sys.util.SysUtils" %>
<%@ page import="com.saille.rm.util.ImdUtils" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/09/07 0007
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<%
    Map<String, String> params = new HashMap<String, String>();
    Map<String, byte[]> files = new HashMap<String, byte[]>();
    Map<String, double[]> ranks = new HashMap<String, double[]>();
    DecimalFormat df = new DecimalFormat("#,##0.000");
    byte[] mp3Bytes;
    if("POST".equals(request.getMethod()) && request.getContentType() != null &&
            request.getContentType().indexOf("multipart/form-data") >= 0) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<DiskFileItem> fileItems = upload.parseRequest(request);
        for(int i = 0; i < fileItems.size(); i++) {
            DiskFileItem item = fileItems.get(i);
            if(item.getName() == null) {
                byte[] bytes = new byte[(int)item.getSize()];
                item.getInputStream().read(bytes);
                item.getInputStream().close();
                params.put(item.getFieldName(), new String(bytes, "GBK"));
            } else {
                if(item.getFieldName().equals("mp3")) {
                    if(!item.getName().toLowerCase().endsWith(".mp3")) {
                        out.println("mp3文件扩展名不正确");
                        item.getInputStream().close();
                        return;
                    }
                    mp3Bytes = new byte[(int)item.getSize()];
                    item.getInputStream().read(mp3Bytes);
                    item.getInputStream().close();
                    String tmpname = "mp3_" + item.getName();
                    String filepath = System.getProperty("java.io.tmpdir") + File.separator + tmpname;
                    SysUtils.addTempFile(filepath, mp3Bytes, 60 * 10);
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(mp3Bytes);
                } else if(item.getFieldName().equals("imd")) {
                    if(!item.getName().toLowerCase().endsWith("imd")) {
                        out.println("imd文件扩展名不正确");
                        item.getInputStream().close();
                        return;
                    }
                    byte[] filebytes = new byte[(int)item.getSize()];
                    item.getInputStream().read(filebytes);
                    item.getInputStream().close();
                    String tmpname = files.size() + "_" + item.getName();
                    String filepath = System.getProperty("java.io.tmpdir") + File.separator + tmpname;
                    SysUtils.addTempFile(filepath, filebytes, 60 * 10);
                    files.put(tmpname, filebytes);
                    ranks.put(tmpname, new double[]{ImdUtils.calcRank(filebytes), ImdUtils.calcDifficult(filebytes)});
                }
            }
        }
    } else {
        out.print("请求格式不正确");
        return;
    }
%>
<body>
<table border="0" cellpadding="1" cellspacing="1">
    <tr>
        <td class="fieldname">名字</td>
        <td class="fieldvalue" colspan="2">
            <%=params.get("name")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">路径</td>
        <td class="fieldvalue" colspan="2">
            <%=params.get("path")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">作者</td>
        <td class="fieldvalue" colspan="2">
            <%=params.get("author")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">备注</td>
        <td class="fieldvalue" colspan="2">
            <%=params.get("memo")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">长度</td>
        <td class="fieldvalue" colspan="2">
            todo
        </td>
    </tr>
    <%
        int count = 0;
        for(String key : files.keySet()) {

    %>
    <tr>
        <%
            if(count == 0) {
        %>
        <td rowspan="<%=files.size()%>" class="fieldname">
            IMD
        </td>
        <%
            }
        %>
        <td class="fieldvalue">
            <%=df.format(ranks.get(key)[0])%>
        </td>
        <td class="fieldvalue">
            <%=df.format(ranks.get(key)[1])%>
        </td>
    </tr>
    <%
            count++;
        }
    %>
</table>
</body>
</html>
