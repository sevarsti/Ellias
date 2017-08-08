<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
        String player = request.getParameter("name");
        HttpPost pm = new HttpPost(OgzqURL.URL + "/" + player + "_Top_Club.aspx");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "3"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String s = IDUtils.execute("robot0001@sina.com", pm);
//        String s = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
        String[] parts = s.split("@");
        String[] all = parts[0].split("[*]");

        pm = new HttpPost(OgzqURL.URL + "/" + player + "_Top_Club.aspx");
        params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "3"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        s = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
//        String s = IDUtils.execute(IDUtils.GETIDS().get(0), pm);
        String[] parts2 = s.split("@");
        String[] all2 = parts[0].split("[*]");
    %>
    <body>
    <table width="100%">
        <tr>
            <td valign="top">
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr class="head">
                        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, true)">名次</th>
                        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, true)">服务器</th>
                        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">球会</th>
                        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, false)">会长</th>
                        <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">进球</th>
                    </tr>
                    <%
                        for(int i = 0; i < all.length; i++) {
                            String a = all[i];
                            String[] aa = a.split("[|]");
                            if(aa.length < 5) {
                                aa = new String[]{aa[0], aa[1],"","",aa[3]};
                            }
                    %>
                        <tr class="row<%=i % 2 + 1%>">
                            <td><%=aa[0]%></td>
                            <td><%=aa[1]%></td>
                            <td><%=aa[2]%></td>
                            <td><%=aa[3]%></td>
                            <td><%=aa[4]%></td>
                        </tr>
                    <%
                        }
                    %>
                </table>
            </td>
            <td valign="top">
                <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                    <tr class="head">
                        <th>名次</th>
                        <th>球队</th>
                        <th>进球</th>
                        <th>名次</th>
                        <th>球队</th>
                        <th>进球</th>
                    </tr>
                    <%
                        String[] players1 = parts[1].split("[*]");
                        String[] players2 = parts2[1].split("[*]");
                        for(int i = 0; i < Math.max(players1.length, players2.length); i++) {
                    %>
                    <tr class="row<%=i % 2 + 1%>">
                        <td>
                            <%=players1.length > i ? players1[i].split("[|]")[0] : "&nbsp;"%>
                        </td>
                        <td>
                            <%=players1.length > i ? players1[i].split("[|]")[1] : "&nbsp;"%>
                        </td>
                        <td>
                            <%=players1.length > i ? players1[i].split("[|]")[2] : "&nbsp;"%>
                        </td>
                        <td>
                            <%=players2.length > i ? players2[i].split("[|]")[0] : "&nbsp;"%>
                        </td>
                        <td>
                            <%=players2.length > i ? players2[i].split("[|]")[1] : "&nbsp;"%>
                        </td>
                        <td>
                            <%=players2.length > i ? players2[i].split("[|]")[2] : "&nbsp;"%>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    <%--<%
                        String[] players = parts[1].split("[*]");
                        for(int i = 0; i < players.length; i++) {
                            if(players[i].equals("")) {
                                continue;
                            }
                            String[] pp = players[i].split("[|]");
                    %>
                    <tr class="row<%=i % 2 + 1%>">
                        <td><%=pp[0]%></td>
                        <td><%=pp[1]%></td>
                        <td><%=pp[2]%></td>
                    </tr>
                    <%
                        }
                    %>--%>
                </table>
            </td>
        </tr>
    </table>
    </body>
</html>