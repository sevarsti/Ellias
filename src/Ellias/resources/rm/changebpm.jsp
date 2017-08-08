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
    <%!
        void log(String ip, String song, String ratio) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String route = WeixinHandler.class.getResource("").getPath();
            route = route.substring(0, route.indexOf("WEB-INF"));
            File f = new File(route + "\\log\\changebpm.log");
            FileUtils.WriteFile(f, sdf.format(new Date()) + "\t" + ip + "\t" + song + "_" + ratio + "\r\n", true);
        }

        List<String[]> sort(List<String[]> list, int start, int end) {
            if(start >= end) {
                return list;
            }
            int pos = start;
            for(int i = pos + 1; i < end; i++) {
                boolean needSwap = false;
                if(list.get(i)[0].compareTo(list.get(pos)[0]) > 0) {
                    needSwap = true;
                }
                if(needSwap) {
                    String[] tmp = list.get(i);
                    for(int m = i; m > pos; m--) {
                        list.set(m, list.get(m - 1));
                    }
                    list.set(pos, tmp);
                }
                pos = i;
            }
            sort(list, start, pos);
            sort(list, pos + 1, end);
            return list;
        }

    %>
    <%
//        File f = new File("D:\\节奏大师歌曲.xls");
//        FileInputStream fis = new FileInputStream(f);
//        HSSFWorkbook workbook = new HSSFWorkbook(fis);
//        HSSFSheet sheet = workbook.getSheet("歌曲");
//        List<String[]> songs = new ArrayList<String[]>();
//        for(int i = 2; i <= sheet.getLastRowNum(); i++) {
//            HSSFRow row = sheet.getRow(i);
//            HSSFCell cell = row.getCell(0);
//            String name = cell.getRichStringCellValue().getString();
//            cell = row.getCell(1);
//            if(cell != null) {
//                name = name + "(" + cell.getRichStringCellValue().getString() + ")";
//            }
//            cell = row.getCell(2);
//            String path = cell.getRichStringCellValue().getString();
//            songs.add(new String[]{name, path});
//        }
//        songs = sort(songs, 0, songs.size());
//        request.setAttribute("songs", songs);
    %>
    <body>
    <c:if test="${!empty changeBpmForm.msg}">
        <div class="alert">
            ${changeBpmForm.msg}
        </div>
    </c:if>
    <form action="/rm.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="method" value="change"/>
        <%--<table class="frame" border="0" cellpadding="1" cellspacing="1">--%>
            <%--<tr>--%>
                <%--<td>--%>
                    <%--请选择歌曲：--%>
                    <%--<select name="song" id="song">--%>
                        <%--<c:forEach items="${songs}" var="s">--%>
                            <%--<option value="${s[1]}">${s[0]}</option>--%>
                        <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--请选择速度：--%>
                    <%--<select name="ratio" id="ratio">--%>
                        <%--<option value="0.5">x0.5</option>--%>
                        <%--<option value="0.8">x0.8</option>--%>
                        <%--<option value="1.2">x1.2</option>--%>
                        <%--<option value="1.5">x1.5</option>--%>
                        <%--<option value="2.0">x2.0</option>--%>
                    <%--</select>--%>
                    <%--<input type="submit" value="转换" class="otterbtn"/>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</table>--%>
        <%--<br/><br/>--%>
        <table class="frame" border="0" cellpadding="1" cellspacing="1">
            <tr>
                <td class="fieldname">转换imd</td>
                <td class="fieldvalue">
                    <input type="file" name="cutimd" accept="imd"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">转换mp3</td>
                <td class="fieldvalue">
                    <input type="file" name="cutmp3" accesskey="*.mp3"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">开始时间(秒)</td>
                <td class="fieldvalue">
                    <input type="text" size="4" name="begin"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">结束时间(秒)</td>
                <td class="fieldvalue">
                    <input type="text" size="4" name="end"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">加/减速</td>
                <td class="fieldvalue">
                    <input type="text" size="4" id="ratio" value="1.0" name="ratio"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="转换" onclick="dochange();"/>
                </td>
            </tr>
        </table>
        <script type="text/javascript">
            function dochange()
            {
                document.forms[0].method.value = 'cut';
                document.forms[0].submit();
            }
        </script>
    </form>
    </body>
</html>