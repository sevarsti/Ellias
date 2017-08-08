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
    <%!
        public String convert(String in) {
            Map<String, String> list = new HashMap<String, String>();
            list.put("绿茵传奇", "7");
            list.put("超级巨星", "6");
            list.put("大牌", "5");
            list.put("杰出", "4");
            list.put("精英", "3");
            list.put("优秀", "2");
            list.put("普通", "1");
            return list.get(in);
        }
    %>
    <table id="info" border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>邮箱</th>
            <th>昵称</th>
            <th>球员</th>
            <th>能力</th>
            <th>位置</th>
            <th>品质</th>
            <th>等级</th>
            <th>替补</th>
            <th>普通</th>
            <th>优秀</th>
            <th>精英</th>
            <th>杰出</th>
            <th>大牌</th>
            <th>巨星</th>
        </tr>
        <%
            String emailStr = request.getParameter("email");
            if(emailStr != null) {
                emailStr = URLDecoder.decode(emailStr, "utf-8");
            }
            List<String> ids;
            if(emailStr != null && IDUtils.IDInfos.containsKey(emailStr)) {
                ids = new ArrayList<String>();
                ids.add(emailStr);
            } else {
                ids = IDUtils.GETIDS();
            }
            for(String email : ids) {
                List<Map<String, String>> players = new OgzqDwr().viewPlayer(email);
                for(int i = 1; i < players.size(); i++) {
                    Map<String, String> p = players.get(i);
//                    Map<String, String> detail = OperationUtils.viewPlayer(email, p.get("id"));
                    Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("QyzxLoad1", p.get("id")));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String str = IDUtils.execute(email, pm);
//695956|220301|▲诺伊尔|6|3056|1|Activity1/220301.png@
//0@
//767314|10901|芒当达|2|264|1|France/09/10901.png@
//688036|11001|朗德洛|3|392|1|France/10/11001.png@
//678694|40601|什科琴尼|4|823|1|England/06/40601.png@
//0@
//-1
                    String[] subs = str.split("@");
                    List<String> showstr = new ArrayList<String>();
                    for(int j = 1; j < subs.length; j++) {
                        if(subs[j].equals("-1") || subs[j].equals("0")) {
                            showstr.add(subs[j]);
                        } else {
                            if("-2".equals(subs[j].split("[|]")[0])) {
                                showstr.add("-1");
                            } else {
                                String[] attrs = subs[j].split("[|]");
                                showstr.add(attrs[4] + attrs[2]);
                            }
                        }
                    }
        %>
        <tr class="row<%=i % 2 + 1%>">
            <td><%=email%></td>
            <td><%=IDUtils.getNick(email)%></td>
            <td>
                <%=p.get("name")%><br/>
            </td>
            <td>
                <%=p.get("ability")%>
                <%--<%=p.get("id")%>--%>
            </td>
            <td><%=detail.get("pos")%></td>
            <td><%=detail.get("pinzhi")%></td>
            <td><%=p.get("level")%></td>
            <td><%=p.get("tibu")%></td>
            <%
                for(int j = 0; j < showstr.size(); j++) {
                    String s = showstr.get(j);
            %>
                <td>
                    <%=s.equals("0") ? "无" : (s.equals("-1") ? "--" : s)%>
                </td>
                <%
                    }
                %>
            </tr>
        <%
            }
        %>
        <%
            }
        %>
    </table>
    <br/>
    </body>
</html>