<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2016-7-8
  Time: 9:01:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.net.URLEncoder" %>
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
    %>
    <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
        <tr class="head">
            <th>email</th>
            <th>nick</th>
            <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">银币</th>
            <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">限量积分</th>
            <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">成长积分</th>
            <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">世界杯积分</th>
        </tr>
        <%
            List<String> ids = IDUtils.GETIDS();
            for(int i = ids.size() - 1; i >= 0; i--) {
                if(ids.get(i).indexOf("txjcf") == 0) {
                    ids.remove(i);
                }
            }
            for(int i = 0; i < ids.size(); i++) {
                String id = ids.get(i);
                System.out.println(i + "/" + ids.size() + ": " + id);

                //限量积分
                HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("EuroleaguePoint1", ""));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                String xianliangPoint = IDUtils.execute(id, pm);

                //成长积分
                Map<String, String> detail = CacheManager.loadPlayer(id, new OgzqDwr().viewPlayer(id).get(1).get("id"), false);
                String chengzhangjifen = detail.get("chengzhangjifen");

                //世界杯积分
                String shijiebeijifen = detail.get("shijiebeijifen");
        %>
        <tr class="row<%=i % 2 + 1%>">
            <td><%=id%></td>
            <td><%=IDUtils.getNick(id)%></td>
            <td><%=IDUtils.IDInfos.get(id).get("silver")%></td>
            <td value="<%=xianliangPoint%>" style="cursor:pointer;" onclick="window.open('equip.jsp?email=<%=URLEncoder.encode(id)%>')"><%=xianliangPoint%></td>
            <td value="<%=chengzhangjifen%>" style="cursor:pointer;" onclick="window.open('chengzhang.jsp?email=<%=URLEncoder.encode(id)%>')"><%=chengzhangjifen%></td>
            <td value="<%=shijiebeijifen%>" style="cursor:pointer;" onclick="window.open('shijiebeijifen.jsp?email=<%=URLEncoder.encode(id)%>')"><%=shijiebeijifen%></td>
        </tr>
        <%
            }
        %>
    </table>
    </body>
<script type="text/javascript">
</script>
</html>