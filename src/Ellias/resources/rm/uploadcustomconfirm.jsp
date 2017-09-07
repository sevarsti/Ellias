<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.saille.sys.util.SysUtils" %>
<%@ page import="com.saille.rm.util.ImdUtils" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.saille.util.FFMpegUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.codec.digest.Md5Crypt" %>
<%@ page import="com.saille.util.UtilFunctions" %>
<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/09/07 0007
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <title></title>
</head>
<%!
    String convertLength(int in) {
        String ret = "";
        if(in < 60) {
            return "0:" + in;
        } else {
            return (in / 60) + ":" + (in % 60);
        }
    }

    List<String> sortKey(Map<String, double[]> map) {
        List<String> ret = new ArrayList<String>();
        for(String k : map.keySet()) {
            boolean added = false;
            double[] src = map.get(k);
            for(int i = 0; i < ret.size(); i++) {
                double[] queue = map.get(ret.get(i));
                if(src[2] < queue[2]) {
                    ret.add(i, k);
                    added = true;
                    break;
                } else if(src[2] > queue[2]) {
                    continue;
                } else {
                    if(src[1] < queue[1]) {
                        ret.add(i, k);
                        added = true;
                        break;
                    } else if(src[1] > queue[1]) {
                        continue;
                    } else {
                        if(src[0] < queue[0]) {
                            ret.add(i, k);
                            added = true;
                            break;
                        }
                    }
                }
                continue;
            }
            if(!added) {
                ret.add(k);
            }
        }
        return ret;
    }
%>
<%
    Map<String, String> params = new HashMap<String, String>();
    Map<String, byte[]> files = new HashMap<String, byte[]>();
    Map<String, double[]> ranks = new HashMap<String, double[]>();
    Map<String, String> imdmd5 = new HashMap<String, String>();
    List<String> sortedkey;
    DecimalFormat df = new DecimalFormat("#,##0.000");
    int maxlength = 0;
    byte[] mp3Bytes;
    String now = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
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
                    maxlength = Math.max(maxlength, FFMpegUtils.getAudioLength(filepath));
                    params.put("md5", UtilFunctions.md5(mp3Bytes));
//                    FileOutputStream fos = new FileOutputStream(f);
//                    fos.write(mp3Bytes);
                } else if(item.getFieldName().equals("imd")) {
                    if(StringUtils.isEmpty(item.getName())) {
                        continue;
                    }
                    if(!item.getName().toLowerCase().endsWith("imd")) {
                        out.println("imd文件扩展名不正确");
                        item.getInputStream().close();
                        return;
                    }
                    byte[] filebytes = new byte[(int)item.getSize()];
                    item.getInputStream().read(filebytes);
                    item.getInputStream().close();
                    String tmpname = files.size() + "_" + item.getName() + now;
                    String filepath = System.getProperty("java.io.tmpdir") + File.separator + tmpname;
                    SysUtils.addTempFile(filepath, filebytes, 60 * 10);
                    files.put(tmpname, filebytes);
                    ranks.put(tmpname, new double[]{ImdUtils.calcRank(filebytes), ImdUtils.calcDifficult(filebytes), ImdUtils.getKey(filebytes)});
                    imdmd5.put(tmpname, UtilFunctions.md5(filebytes));
                    maxlength = Math.max(maxlength, ImdUtils.getLength(filebytes) / 1000);
                }
            }
        }
    } else {
        out.print("请求格式不正确");
        return;
    }
%>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
    <tr>
        <td class="fieldname">名字</td>
        <td class="fieldvalue" colspan="4">
            <%=params.get("name")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">路径</td>
        <td class="fieldvalue" colspan="4">
            <%=params.get("path")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">作者</td>
        <td class="fieldvalue" colspan="4">
            <%=params.get("author")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">备注</td>
        <td class="fieldvalue" colspan="4">
            <%=params.get("memo")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">长度</td>
        <td class="fieldvalue" colspan="4">
            <%=convertLength(maxlength)%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">MD5</td>
        <td class="fieldvalue" colspan="4">
            <%=params.get("md5")%>
        </td>
    </tr>
    <tr>
        <td rowspan="<%=files.size() + 1%>" class="fieldname">
            IMD
        </td>
        <td class="head">KEY</td>
        <td class="head">
            等级
        </td>
        <td class="head">
            难度
        </td>
        <td class="head">
            MD5
        </td>
    </tr>
    <%
        int count = 0;
        sortedkey = sortKey(ranks);
        for(String key : sortedkey) {

    %>
    <tr>
        <td class="fieldvalue">
            <%=(int)ranks.get(key)[2]%>
        </td>
        <td class="fieldvalue">
            <%=df.format(ranks.get(key)[0])%>
        </td>
        <td class="fieldvalue">
            <%=df.format(ranks.get(key)[1])%>
        </td>
        <td class="fieldvalue">
            <%=imdmd5.get(key)%>
        </td>
    </tr>
    <%
            count++;
        }
    %>
</table>
</body>
</html>
