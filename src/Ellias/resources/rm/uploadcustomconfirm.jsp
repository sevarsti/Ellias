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
<%@ page import="com.saille.rm.util.RMUtils" %>
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
//    boolean checkPathValid(JdbcTemplate jt, String path, String md5) {
//        int count = jt.queryForInt("select count(*) from rm_customsong where path = ? and md5 <> ?", new Object[]{path, md5});
//        return count == 0;
//    }

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
    double bpm = 0d;
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
                    if(item.getName().length() > 0) {
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
                    ranks.put(tmpname, new double[]{ImdUtils.calcRank(filebytes), ImdUtils.calcDifficult(filebytes), ImdUtils.getKey(filebytes), ImdUtils.getTotalKeys(filebytes)});
                    imdmd5.put(tmpname, UtilFunctions.md5(filebytes));
                    maxlength = Math.max(maxlength, ImdUtils.getLength(filebytes) / 1000);
                    double newbpm = ImdUtils.getBpm(filebytes);
                    if(bpm == 0) {
                        bpm = newbpm;
                    } else if(bpm > 0 && bpm != newbpm) {
                        bpm = -1;
                    }
                }
            }
        }
        closeStream(fileItems);
        params.put("length", maxlength + "");
        if(bpm > 0) {
            params.put("bpm", bpm + "");
        } else {
            params.put("bpm", "0");
        }

    } else {
        out.print("请求格式不正确");
        return;
    }
    System.out.println("文件读取完毕，开始校验");
    DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
    JdbcTemplate jt = new JdbcTemplate(ds);

    /* 检查是否有mp3 MD5重复 */
    List<String[]> duplicatedMp3 = new ArrayList<String[]>();
    List<Map<String, Object>> list = jt.queryForList("select a.id, a.name, a.author, b.key, b.level, b.rank, b.difficulty, b.totalkey, b.imdmd5 from" +
                                     " (select id, name, author from rm_customsong where md5 = ?) a join rm_customsongimd b on a.id = b.songid order by a.name, b.key, b.level", new Object[]{params.get("md5")});
    List<String> existImdmd5 = new ArrayList<String>();
    for(Map<String, Object> m : list) {
        String id = m.get("id").toString();
        String name = m.get("name").toString();
        String author = m.get("author").toString();
        String key = m.get("key").toString();
        String level = m.get("level").toString();
        String rank = df.format(((Number) m.get("rank")).doubleValue());
        String difficulty = df.format(((Number)m.get("difficulty")).doubleValue());
        String totalkey = m.get("totalkey").toString();
        String md5 = m.get("imdmd5").toString();
        existImdmd5.add(md5);
        duplicatedMp3.add(new String[]{id, name, author, key, level, rank, difficulty, totalkey, md5});
    }

    request.getSession().setAttribute("rm_customsong_param", params);
    request.getSession().setAttribute("rm_customsong_mp3bytes", mp3Bytes);
    request.getSession().setAttribute("rm_customsong_imdbytes", files);
    request.getSession().setAttribute("rm_customsong_imdranks", ranks); //rank, difficulty, key,totalkey
    request.getSession().setAttribute("rm_customsong_imdmd5s", imdmd5);
    request.getSession().setAttribute("rm_customsong_imgs", pngBytes);
%>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
    <tr>
        <td class="fieldname">名字</td>
        <td class="fieldvalue" colspan="5">
            <%=params.get("name")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">作者</td>
        <td class="fieldvalue" colspan="5">
            <%=params.get("author")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">备注</td>
        <td class="fieldvalue" colspan="5">
            <%=params.get("memo")%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">长度</td>
        <td class="fieldvalue" colspan="5">
            <%=RMUtils.convertLength(maxlength)%>
        </td>
    </tr>
    <tr>
        <td class="fieldname">MD5</td>
        <td class="fieldvalue" colspan="5">
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
            键数
        </td>
        <td class="head">
            MD5
        </td>
    </tr>
    <%
        sortedkey = sortKey(ranks);
        for(String key : sortedkey) {

    %>
    <tr <c:if test="<%=existImdmd5.contains(imdmd5.get(key))%>">style="color:red;" </c:if>>
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
            <%=new DecimalFormat("0").format(ranks.get(key)[3])%>
        </td>
        <td class="fieldvalue">
            <%=imdmd5.get(key)%>
        </td>
    </tr>
    <%
        }
    %>
</table>
<form action="uploadcustomdone.jsp">
<%
    if(duplicatedMp3.size() > 0) {
%>
以下MP3文件MD5值重复，请选择是否要合并：<br/>
<table border="0" cellpadding="1" cellspacing="1" id="table">
    <tr class="head">
        <th>&nbsp;</th>
        <th>名字</th>
        <th>作者</th>
        <th>键数</th>
        <th>难度</th>
        <th>rank</th>
        <th>difficulty</th>
        <th>总键数</th>
        <th>md5</th>
    </tr>
    <tr class="row2">
        <td>
            <input name="songId" type="radio" value="0" checked/>
        </td>
        <td align="left" colspan="8">不合并</td>
    </tr>
<%
    for(int i = 0; i < duplicatedMp3.size(); i++) {
%>
<tr class="row<%=i % 2 + 1%>">
    <td>
        <input name="songId" type="radio" value="<%=-Integer.parseInt(duplicatedMp3.get(i)[0])%>"/>
    </td>
    <%
        for(int j = 1; j < 9; j++) {
    %>
    <td><%=duplicatedMp3.get(i)[j]%></td>
    <%
        }
    %>
</tr>
<%
    }
%>
</table>
<%
    }
    if(bpm <= 0) {
%>
谱面BPM不一致，请自行计算！<br/>
<%
    }
%>
    <input type="submit" value="确认">
</form>
<script type="text/javascript">
    function merge()
    {
        var table = document.getElementById("table");
        var startRow = 2, rowLength = 1;
        var rowclass = 1;

        startRow = 2;
        rowLength = 1;
        for(var i = 3; i < table.rows.length; i++)
        {
            if(table.rows[i].cells[0].innerHTML == table.rows[i - 1].cells[0].innerHTML && i != (table.rows.length - 1))
            {
                rowLength += 1;
            }
            else if(table.rows[i].cells[0].innerHTML == table.rows[i - 1].cells[0].innerHTML && i == (table.rows.length - 1))
            {
                table.rows[startRow].className = 'row' + rowclass;
                rowLength += 1;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].className = 'row' + rowclass;
                }
                for(var cols = 2; cols >= 0; cols--)
                {
                    table.rows[startRow].cells[cols].rowSpan = rowLength;
                    for(var j = startRow + 1; j < startRow + rowLength; j++)
                    {
                        table.rows[j].deleteCell(cols);
                    }
                }
                rowLength = 1;
                startRow = i;
                rowclass = 3 - rowclass;
            }
            else
            {
                table.rows[startRow].className = 'row' + rowclass;
                for(var j = startRow + 1; j < startRow + rowLength; j++)
                {
                    table.rows[j].className = 'row' + rowclass;
                }
                for(var cols = 2; cols >= 0; cols--)
                {
                    table.rows[startRow].cells[cols].rowSpan = rowLength;
                    for(var j = startRow + 1; j < startRow + rowLength; j++)
                    {
                        table.rows[j].deleteCell(cols);
                    }
                }
                rowLength = 1;
                startRow = i;
                rowclass = 3 - rowclass;
            }
        }
        if(rowLength > 1)
        {
            table.rows[startRow].cells[cols].rowSpan = rowLength;
            for(var j = startRow + 1; j < startRow + rowLength; j++)
            {
                table.rows[j].deleteCell(cols);
            }
        }
    }
    merge();
</script>
</body>
</html>
