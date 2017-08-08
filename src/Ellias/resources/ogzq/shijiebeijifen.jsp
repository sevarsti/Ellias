<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-16
  Time: 22:33:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.net.URLEncoder" %>
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
    <body>
    <%
        String email = request.getParameter("email");
        email = URLDecoder.decode(email, "utf-8");
        List<Map<String, String>> players = new OgzqDwr().viewPlayer(email);
    %>
        <%=email%>剩余积分：<span id="jifen"></span>，剩余银币：<%=IDUtils.IDInfos.get(email).get("silver")%>
    <br/>
        <input type="button" value="训练" onclick="javascript:window.open('train.jsp?email=<%=URLEncoder.encode(email)%>')"/>
    <br/>
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <!--<th>ID</th>-->
                <th>球员</th>
                <th>ID</th>
                <th>位置</th>
                <th>替补</th>
                <th>升级次数</th>
                <th>下一级消耗点数</th>
                <th>下一级消耗银币</th>
                <th>操作</th>
            </tr>
            <%
                String totaljifen = "";
                for(int i = 1; i < players.size(); i++) {
                    Map<String, String> p = players.get(i);
                    Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);
                    totaljifen = detail.get("shijiebeijifen");

//                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
//                    List<NameValuePair> params = new ArrayList<NameValuePair>();
//                    params.add(new BasicNameValuePair("LoadPlayer1", p.get("id")));
//                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                    String str = IDUtils.execute(email, pm);
//                    String[] atts = str.split("\\*");
                    boolean isZijin = IDUtils.isZijinPlayer(p.get("id"));
                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("UpdateWorldCup_Break1", p.get("id")));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String str = IDUtils.execute(email, pm);
                    String jifencost = "-", silvercost = "-";
                    if(!str.equals("-1")) {
                        jifencost = str.split("\\|")[0];
                        silvercost = str.split("\\|")[1];
                    }
            %>
            <tr class="row<%=i % 2 + 1%>">
                <%--<td><%=p.get("id")%></td>--%>
                <td>
                    <span <%=isZijin ? ("style='color:red;'") : ""%>>
                        <%=p.get("name")%><br/>
                    </span>
                </td>
                <td>
                    <%=p.get("id")%><br/>
                </td>
                <td><%=detail.get("pos")%></td>
                <td><%=p.get("tibu")%></td>
                <td><%=detail.get("guojiaduizhilu")%></td>
                <td><%=jifencost%></td>
                <td><%=silvercost%></td>
                <td>
                    <%if(!str.equals("-1")){%>
                        <input type="button" value="成长" onclick="chengzhang('<%=p.get("id")%>')"/>
                    <%}%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    <br/>
    </body>
<script type="text/javascript">
    document.getElementById('jifen').innerHTML = '<%=totaljifen%>';
    function after(obj)
    {
        alert(obj);
    }

    function chengzhang(playerId) {
        OgzqDwr.upgradePlayerShijiebei('<%=email%>', playerId, after);
    }
</script>
</html>