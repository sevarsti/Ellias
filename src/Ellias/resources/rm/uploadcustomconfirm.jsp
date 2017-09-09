<%@ page import="com.saille.rm.util.ImdUtils" %>
<%@ page import="com.saille.sys.util.SysUtils" %>
<%@ page import="com.saille.util.FFMpegUtils" %>
<%@ page import="com.saille.util.UtilFunctions" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.springframework.jdbc.core.JdbcTemplate" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="java.io.File" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.imageio.ImageIO" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="java.io.ByteArrayInputStream" %>
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
    boolean checkPathValid(JdbcTemplate jt, String path) {
        int count = jt.queryForInt("select count(1) from rm_customsong where path = ?", new Object[]{path});
        return count == 0;
    }

    String convertLength(int in) {
        String ret = "";
        if(in < 60) {
            return "0:" + in;
        } else {
            return (in / 60) + ":" + new DecimalFormat("00").format(in % 60);
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
    void closeStream(List<DiskFileItem> list) {
        for(DiskFileItem item : list) {
            try {
                item.getInputStream().close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
%>
<%
    request.getSession().removeAttribute("rm_customsong_param");
    request.getSession().removeAttribute("rm_customsong_mp3bytes");
    request.getSession().removeAttribute("rm_customsong_imdbytes");
    request.getSession().removeAttribute("rm_customsong_imdranks");
    request.getSession().removeAttribute("rm_customsong_imdmd5s");
    request.getSession().removeAttribute("rm_customsong_imgs");

    Map<String, String> params = new HashMap<String, String>();
    Map<String, byte[]> files = new HashMap<String, byte[]>();
    Map<String, double[]> ranks = new HashMap<String, double[]>();
    Map<String, String> imdmd5 = new HashMap<String, String>();
    List<String> sortedkey;
    DecimalFormat df = new DecimalFormat("#,##0.000");
    int maxlength = 0;
    byte[] mp3Bytes = new byte[0];
    byte[][] pngBytes = new byte[2][]; //0=小图，1=大图
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
                params.put(item.getFieldName(), new String(bytes, "GBK"));
            } else {
                if(item.getFieldName().equals("mp3")) {
                    if(!item.getName().toLowerCase().endsWith(".mp3")) {
                        out.println("mp3文件扩展名不正确");
                        closeStream(fileItems);
                        return;
                    }
                    System.out.println("读取mp3:" + item.getName());
                    mp3Bytes = new byte[(int)item.getSize()];
                    item.getInputStream().read(mp3Bytes);
                    String tmpname = "mp3_" + item.getName() + now;
                    String filepath = System.getProperty("java.io.tmpdir") + File.separator + tmpname;
                    SysUtils.addTempFile(filepath, mp3Bytes, 60 * 10);
                    System.out.println("解析mp3长度");
                    maxlength = Math.max(maxlength, FFMpegUtils.getAudioLength(filepath));
                    params.put("md5", UtilFunctions.md5(mp3Bytes));
                } else if(item.getFieldName().endsWith("png")) {
                    if(!item.getName().toLowerCase().endsWith(".png")) {
                        out.println("png文件扩展名不正确");
                        closeStream(fileItems);
                        return;
                    }
                    System.out.println("读取png:" + item.getName());
                    boolean hdPng = item.getFieldName().equals("hdpng"); //hdpng=小图
                    if(hdPng) {
                        pngBytes[0] = new byte[(int)item.getSize()];
                        item.getInputStream().read(pngBytes[0]);
                        ByteArrayInputStream bais = new ByteArrayInputStream(pngBytes[0]);
                        BufferedImage bi = ImageIO.read(bais);
                        bais.close();
                        int width = bi.getWidth();
                        int height = bi.getHeight();
//                        if(width != 140 || height != 60) {
//                            out.print("小图尺寸不是140x60");
//                            closeStream(fileItems);
//                            return;
//                        }
                    } else {
                        pngBytes[1] = new byte[(int)item.getSize()];
                        item.getInputStream().read(pngBytes[1]);
                        ByteArrayInputStream bais = new ByteArrayInputStream(pngBytes[1]);
                        BufferedImage bi = ImageIO.read(bais);
                        bais.close();
                        int width = bi.getWidth();
                        int height = bi.getHeight();
//                        if(width != 480 || height != 320) {
//                            out.print("大图尺寸不是480x320");
//                            closeStream(fileItems);
//                            return;
//                        }
                    }
                } else if(item.getFieldName().equals("imd")) {
                    if(StringUtils.isEmpty(item.getName())) {
                        continue;
                    }
                    if(!item.getName().toLowerCase().endsWith("imd")) {
                        out.println("imd文件扩展名不正确");
                        closeStream(fileItems);
                        return;
                    }
                    System.out.println("读取imd:" + item.getName());
                    byte[] filebytes = new byte[(int)item.getSize()];
                    item.getInputStream().read(filebytes);
                    String tmpname = files.size() + "_" + item.getName() + now;
                    files.put(tmpname, filebytes);
                    ranks.put(tmpname, new double[]{ImdUtils.calcRank(filebytes), ImdUtils.calcDifficult(filebytes), ImdUtils.getKey(filebytes)});
                    imdmd5.put(tmpname, UtilFunctions.md5(filebytes));
                    maxlength = Math.max(maxlength, ImdUtils.getLength(filebytes) / 1000);
                }
            }
        }
        closeStream(fileItems);
        params.put("length", maxlength + "");

    } else {
        out.print("请求格式不正确");
        return;
    }
    System.out.println("文件读取完毕，开始校验");
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);

    boolean valid = checkPathValid(jt, params.get("path"));
    if(!valid) {
        out.print("path重复");
        return;
    }
    /* 检查是否有MD5重复 */
    List<String[]> duplicatedMp3 = new ArrayList<String[]>();
    List<Map<String, Object>> list = jt.queryForList("select name, path, author from rm_customsong where md5 = ?", new Object[]{params.get("md5")});
    for(Map<String, Object> m : list) {
        String name = m.get("name").toString();
        String path = m.get("path").toString();
        String author = m.get("author").toString();
        duplicatedMp3.add(new String[]{name, path, author});
    }
    request.getSession().setAttribute("rm_customsong_param", params);
    request.getSession().setAttribute("rm_customsong_mp3bytes", mp3Bytes);
    request.getSession().setAttribute("rm_customsong_imdbytes", files);
    request.getSession().setAttribute("rm_customsong_imdranks", ranks); //key, rank, difficulty
    request.getSession().setAttribute("rm_customsong_imdmd5s", imdmd5);
    request.getSession().setAttribute("rm_customsong_imgs", pngBytes);
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
<%
    if(duplicatedMp3.size() > 0) {
%>
以下MP3文件MD5值重复：<br/>
<%
    for(int i = 0; i < duplicatedMp3.size(); i++) {
%>
<%=duplicatedMp3.get(i)[0]%>/<%=duplicatedMp3.get(i)[1]%>/<%=duplicatedMp3.get(i)[2]%><br/>
<%
        }
    }
%>
<form action="uploadcustomdone.jsp">
    <input type="submit" value="确认">
</form>
</body>
</html>
