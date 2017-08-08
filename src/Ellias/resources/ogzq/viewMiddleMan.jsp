<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2014-2-8
  Time: 15:03:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Hashtable" %>
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
    Map<String, String> level = new Hashtable<String, String>();
    level.put("1", "1普通");
    level.put("2", "2优秀");
    level.put("3", "3精英");
    level.put("4", "4杰出");
    level.put("5", "5大牌");
    level.put("6", "6巨星");
    Map<String, String> pos = new Hashtable<String, String>();
    pos.put("1", "1门将");
    pos.put("2", "2后卫");
    pos.put("3", "3中场");
    pos.put("4", "4前锋");
    List<String> ids = IDUtils.GETIDS();
    List<String[]> allPlayers = new ArrayList<String[]>();
    for(String id : ids) {
        System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.MIDDLE_MAN);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("getPlayerHole", "1"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String ret = IDUtils.execute(id, pm);
        ret = ret.substring(ret.indexOf("&") + 1);
        ret = ret.substring(ret.indexOf("&") + 1);
        if(ret.equals("-1")) {
            continue;
        }
        String[] players = ret.split("[*]");
        for(String p : players) {
            String[] atts = p.split("[|]");
            pm = new HttpPost(OgzqURL.URL + OgzqURL.VIEW_PLAYER);
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadPlayer1", atts[0]));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            ret = IDUtils.execute(id, pm);
            String[] player = new String[11];
            player[0] = atts[1];
            player[1] = pos.get(atts[4]);
            player[2] = level.get(atts[2]);
            atts = ret.split("[*]");
            for(int i = 0; i < 8; i++) {
                player[3 + i] = atts[9 + i];
            }
            allPlayers.add(player);
        }
//        out.println(ret + "<br/>");
    }
    request.setAttribute("pp", allPlayers);
%>
    <body>
    <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>姓名</th>
            <th>位置</th>
            <th>等级</th>
            <th>射门</th>
            <th>突破</th>
            <th>断球</th>
            <th>铲球</th>
            <th>传球</th>
            <th>速度</th>
            <th>扑救</th>
            <th>出击</th>
        </tr>
        <c:forEach items="${pp}" var="p" varStatus="i">
            <tr class="row${i.index % 2 + 1}">
                <c:forEach items="${p}" var="ppp">
                    <td>${ppp}</td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
    </body>
<script type="text/javascript">
</script>
</html>