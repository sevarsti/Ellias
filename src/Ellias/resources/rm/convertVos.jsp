<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="com.saille.util.FFMpegUtils" %>
<%@ page import="com.saille.vos.VosLoad" %>
<%@ page import="java.util.zip.ZipOutputStream" %>
<%@ page import="java.util.zip.ZipEntry" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="org.springframework.util.FileCopyUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/10/13 0013
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title>Vos转imd</title>
</head>
<%
    if(request.getContentType() != null && request.getContentType().contains("multipart/form-data")) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        List fileItems = upload.parseRequest(request);
        if(fileItems.size() != 1) {
            out.println("文件数量不正确");
            return;
        }
        DiskFileItem item;
        item = (DiskFileItem) fileItems.get(0);
        if(!item.getName().toLowerCase().endsWith(".vos")) {
            out.println("文件类型不正确");
            return;
        }
        String name = item.getName().substring(0, item.getName().length() - 4);
        byte[] vosBytes = new byte[(int)item.getSize()];
        item.getInputStream().read(vosBytes);
        String[] files = VosLoad.convert(vosBytes);
        String encoded = new String((name + ".zip").getBytes(), "ISO-8859-1");
        response.addHeader("Content-Disposition", "attachment; filename=" + encoded);
        response.setHeader("Content-Type", " application/zip");

        byte[] tmp = new byte[1024];
        int i = 0;
        ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
        zos.putNextEntry(new ZipEntry(name + ".imd"));
        FileInputStream is = new FileInputStream(files[0]);
        while((i = is.read(tmp)) > 0) {
            zos.write(tmp, 0, i);
        }
//        FileCopyUtils.copy(is, zos);
        is.close();

        zos.putNextEntry(new ZipEntry(name + ".mp3"));
        is = new FileInputStream(files[1]);
        while((i = is.read(tmp)) > 0) {
            zos.write(tmp, 0, i);
        }
//        FileCopyUtils.copy(is, zos);
        is.close();
        zos.close();

        out.clear();
        out = pageContext.pushBody();
        return;
//        ZipInputStream zis = new ZipInputStream(item.getInputStream(), Charset.forName("GBK"));
//        ZipEntry entry = zis.getNextEntry();
//        if(!entry.getName().equals("节奏大师歌曲.xls")) {
//            out.println("压缩包内文件名不正确");
//            return;
//        }
    }
%>
<body>
<form action="" enctype="multipart/form-data" method="post">
    <input type="file" name="vos" class="inputbox" accept=".vos"/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
