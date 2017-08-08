<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-8-11
  Time: 2:04:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='../scripts/tablesort.js'></script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <body>
    <%
        String email = request.getParameter("email");
        List<String> ids = new ArrayList<String>();
        if(StringUtils.isNotEmpty(email)) {
            ids.add(email);
        } else {
            ids = IDUtils.GETIDS();
        }
        List<String[]> values = new ArrayList<String[]>();
        for(String id : ids) {
            System.out.println("查看国旗" + ids.indexOf(id) + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "4"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
            if(str.equals("-1")) {
                values.add(new String[]{id, IDUtils.getNick(id), "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
                continue;
            }
            String[] parts = str.split("@")[0].split("[|]");
            String[] v = new String[34];
            v[0] = id;
            v[1] = IDUtils.getNick(id);
            for(int i = 2; i < v.length; i++) {
                v[i] = "";
            }
            for(String p : parts) {
                String[] pp = p.split("[*]");
                v[Integer.parseInt(pp[11]) - 8899] = pp[7];
            }
            values.add(v);
        }
    %>
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th>操作</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">
                        email
                </th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">nick</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">阿尔及利亚</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">加纳</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">喀麦隆</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, true)">科特迪瓦</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, true)">伊朗</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, true)">洪都拉斯</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, true)">哥斯达黎加</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 10, true)">哥伦比亚</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 11, true)">波黑</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 12, true)">尼日利亚</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 13, true)">澳大利亚</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 14, true)">韩国</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 15, true)">日本</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 16, true)">墨西哥</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 17, true)">美国</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 18, true)">厄瓜多尔</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 19, true)">智利</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 20, true)">乌拉圭</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 21, true)">克罗地亚</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 22, true)">希腊</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 23, true)">俄罗斯</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 24, true)">瑞士</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 25, true)">比利时</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 26, true)">英格兰</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 27, true)">法国</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 28, true)">葡萄牙</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 29, true)">荷兰</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 30, true)">阿根廷</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 31, true)">意大利</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 32, true)">德国</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 33, true)">西班牙</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 34, true)">巴西</th>
            </tr>
            <%
                for (int i = 0; i < values.size(); i++) {
                    String[] v = values.get(i);
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td>
                        <input type="button" value="删" onclick="removeRow(this);"/>
                        <input type="button" value="升阶" onclick="javascript:window.open('shengjie.jsp?email=<%=URLEncoder.encode(v[0])%>');"/>
                    </td>
                    <%
                        for(int j = 0; j < v.length; j++) {
                            String vv = v[j];
                    %>
                    <td style="<%=j % 3 == 1 ? "border-right:black;border-right-style:solid;border-right-width:1px;" : ""%>"><%=vv%></td>
                    <%
                        }
                    %>
                </tr>
            <%
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function removeRow(obj) {
        var table = document.getElementById('info');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);

//        for(var i = 1; i < table.rows.length; i++) {
//            table.rows[i].className = 'row' + (2 - (i % 2));
//        }
    }
</script>
</html>