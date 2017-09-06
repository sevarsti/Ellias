<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/09/06 0006
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>上传自制谱</title>
</head>
<%
    if("POST".equals(request.getMethod()) && request.getContentType() != null &&
            request.getContentType().indexOf("multipart/form-data") >= 0) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List<DiskFileItem> fileItems = upload.parseRequest(request);
        Map<String, String> params = new HashMap<String, String>();
        Map<String, byte[]> files = new HashMap<String, byte[]>();
        for(DiskFileItem item : fileItems) {
            if(item.getName() == null) {
                byte[] bytes = new byte[(int)item.getSize()];
                item.getInputStream().read(bytes);
                item.getInputStream().close();
                params.put(item.getFieldName(), new String(bytes, "GBK"));
            } else {
                byte[] bytes = new byte[(int)item.getSize()];
                item.getInputStream().read(bytes);
                item.getInputStream().close();
                files.put(item.getName(), bytes);
            }
        }
    }
%>
<body>
<form action="" method="post" enctype="multipart/form-data" >
<table border="0" cellpadding="1" cellspacing="1">
    <tr>
        <td class="fieldname">名字</td>
        <td class="fieldvalue">
            <input type="text" name="name"/>
        </td>
    </tr>
    <tr>
        <td class="fieldname">路径</td>
        <td class="fieldvalue">
            <input type="text" name="path"/>
        </td>
    </tr>
    <tr>
        <td class="fieldname">作者</td>
        <td class="fieldvalue">
            <input type="text" name="author"/>
        </td>
    </tr>
    <tr>
        <td class="fieldname">备注</td>
        <td class="fieldvalue">
            <input type="text" name="memo"/>
        </td>
    </tr>
    <tr>
        <td class="fieldname" valign="top">IMD</td>
        <td class="fieldvalue">
            <input type="file" name="imd"/><input type="button" onclick="doadd(this)" value="增加" id="btn"/>
        </td>
    </tr>
</table>
    <input type="button" value="保存" onclick="beforeSave();"/>
</form>
<script type="text/javascript">
    function beforeSave()
    {
        document.forms[0].submit();
    }
    function doadd(obj)
    {
        var imds = document.getElementsByName("imd");
        if(imds.length < 9)
        {
//            obj.parentNode.appendText("<br/>");
            var span = document.createElement("span");
            span.innerHTML = "<br/>";
            obj.parentNode.appendChild(span);
            var file = document.createElement("input");
            file.type = "file";
            file.name = "imd";
            obj.parentNode.appendChild(file);
            var button = document.createElement("input");
            button.type = "button";
            button.onclick = document.getElementById('btn').onclick;
            button.value = "增加";
            obj.parentNode.appendChild(button);
//            obj.parentNode.innerHTML = obj.parentNode.innerHTML + "<br/>" +
//                    "<input type=\"file\" name=\"imd\"/>\n" +
//                    "<input type=\"button\" onclick=\"doadd(this)\" value=\"增加\"/>";
        }
    }
</script>
</body>
</html>
