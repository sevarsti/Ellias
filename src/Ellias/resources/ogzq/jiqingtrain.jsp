<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.saille.ogzq.*" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <%
        String email = URLDecoder.decode(request.getParameter("email"));
        List<Map<String, String>> players = OperationUtils.viewTeam(email);
        List<String[]> results = new ArrayList<String[]>();
        String blue = "", orange = "", red = "", purple = "";
        for(int i = 1; i < players.size(); i++) {
            Map<String, String> p = players.get(i);
            String[] r = new String[10]; //playerid, playername, pos, ability, tibu, blue, orange, red, purple
//            Map<String, String> detail = OperationUtils.viewPlayer(email, p.get("id"));
            Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);

            HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Player.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("JqXunlian1", p.get("id")));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(email, pm);
            r[0] = p.get("id");
            r[1] = p.get("name");
            r[2] = detail.get("pos");
            r[3] = detail.get("ability") + "/" + p.get("ability");
            r[4] = p.get("tibu");
            r[5] = detail.get("jiqing");

            if(str.equals("-1")) {
                r[6] = "--";
                r[7] = "--";
                r[8] = "--";
                r[9] = "--";
            } else {
                String[] parts = str.split("@")[1].split("\\*");
                r[6] = parts[0].split("\\|")[2];
                blue = parts[0].split("\\|")[1];
                r[7] = parts[1].split("\\|")[2];
                orange = parts[1].split("\\|")[1];
                r[8] = parts[2].split("\\|")[2];
                red = parts[2].split("\\|")[1];
                r[9] = parts[3].split("\\|")[2];
                purple = parts[3].split("\\|")[1];
            }
            results.add(r);
        }
        request.setAttribute("players", results);
    %>
    <body>
    <%=email%>: <%=IDUtils.getNick(email)%><br/>
    蓝：<%=blue%>，橙：<%=orange%>，红：<%=red%>，紫：<%=purple%><br/>
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, true)">id</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">球员</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">位置</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, false)">实力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, false)">替补</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">激情等级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, true)">蓝色</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, true)">橙色</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, true)">红色</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, true)">紫色</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${players}" var="p" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <c:forEach items="${p}" var="pp">
                        <td>${pp}</td>
                    </c:forEach>
                    <td>
                        <input type="button" value="升级" onclick="doup('${p[0]}')"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
<script type="text/javascript">
    function doup(id) {
        OgzqDwr.jiqingxunlian('<%=email%>', id, after);
    }

    function after(obj) {
        alert(obj);
    }
</script>
</html>