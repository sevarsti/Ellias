<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-16
  Time: 22:33:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqDwr" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.saille.ogzq.loop.ClubBuffThread" %>
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
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">
                        email
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">
                        昵称
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">
                        球会名称
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">
                        球会ID
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">
                        贡献
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false)">
                       训练赛次数
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, false)">
                        训练赛恢复
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, false)">
                        竞技场积分
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, false)">
                        经纪人效率
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, false)">
                        训练赛银币
                    </span>
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 10, false)">
                        球会赛打残
                    </span>
                </th>
            </tr>
            <%
                List<String> ids = new ArrayList<String>();
                if(request.getParameter("email") != null) {
                    ids.add(URLDecoder.decode(request.getParameter("email"), "utf-8"));
                } else {
                    ids = IDUtils.GETIDS();
                }
                for(int i = ids.size() - 1; i >= 0; i--) {
                    if(ids.get(i).indexOf("txjcf") >= 0) {
                        ids.remove(i);
                    }
                }
                List<String[]> result = new ArrayList<String[]>();
                for(String id : ids) {
                    String[] ss = new String[11];
                    ss[0] = id;
                    ss[1] = IDUtils.getNick(id);

                    String[] buffs = ClubBuffThread.buff.get(id);
                    for(int i = 2; i < 5; i++) {
                        ss[i] = buffs[i - 2];
                    }
                    for(int i = 5; i < 11; i++) {
                        ss[i] = buffs[i - 2] + ClubBuffThread.upCost.get(id)[i - 2];
                    }
                    System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
                    boolean full = true;
                    for(int i = 5; i <= 10; i++) {
                        if(!ss[i].equals("9-9")) {
                            full = false;
                            break;
                        }
                    }
                    if(!full) {
                        result.add(ss);
                    }
                }
                for(int i = 0; i < result.size(); i++) {
                    String[] parts = result.get(i);
            %>
            <tr class="row<%=i % 2 + 1%>">
                <td><%=parts[0]%></td>
                <td><%=parts[1]%></td>
                <td><%=parts[2]%></td>
                <td><%=parts[3]%></td>
                <td><%=parts[4]%></td>
                <%
                    for(int j = 0; j < 6; j++) {
                %>
                <td>
                    <%=parts[5 + j].split(":")[0]%>
                    <%
                        if(parts[5 + j].split(":").length > 1 && Integer.parseInt(parts[5 + j].split(":")[1].trim()) <= Integer.parseInt(parts[4])) {
                    %>
                    <input type="button" value="升" onclick="uplevel('<%=parts[0]%>', <%=j%>, '<%=parts[3]%>')"/>
                    <%
                        }
                    %>
                </td>
                <%
                    }
                %>
            </tr>
            <%
                }
            %>
        </table>
    <br/>
    </body>
<script type="text/javascript">
    function after(obj)
    {
        alert(obj);
    }

    function uplevel(email, buffindex, teamid) {
        OgzqDwr.upClubBuff(email, buffindex, teamid, after);
    }
</script>
</html>