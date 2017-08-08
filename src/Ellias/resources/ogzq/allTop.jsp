<%@ page import="com.saille.ogzq.CheckAllTopThread" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ include file="../include/include.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2016-6-24
  Time: 14:04:11
  To change this template use File | Settings | File Templates.
--%>
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
    目前支持的平台：官服，9377，百度，紫霞，05玩，开心，平安，搜狗<br/>
    如需支持其他平台，请联系作者<br/>
    注：官服46没有统计在内<br/>
    <table border="0" cellpadding="1" cellspacing="1" width="100%">
        <tr>
            <td>
                <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                    <tr class="head">
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, true)">
                                排名
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">
                                平台
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">
                                服务器
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">
                                球会
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">
                                实力
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">
                                本服排名
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, false)">
                               更新时间
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, true)">
                                一日
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, true)">
                                三日
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, true)">
                                一周
                            </span>
                        </th>
                        <th>
                            <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 10, true)">
                                一月
                            </span>
                        </th>
                    </tr>
                    <%!
                        public int getPreviousAbility(String date, List<String[]> ability) {
                            if(ability == null) {
                                return -1;
                            }
                            String ret = "";
                            for(int i = 0; i < ability.size(); i++) {
                                if(ability.get(i)[6].compareTo(date) >= 0) {
                                    return Integer.parseInt(ability.get(i)[1]);
                                }
                            }
                            return -1;
                        }
                    %>
                    <%
                        Map<String, List<String[]>> allAbility = CheckAllTopThread.detailAbility;
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                        Calendar c = Calendar.getInstance();
                        c.add(Calendar.DATE, -1);
                        String d1 = sdf.format(c.getTime());

                        c.setTime(new Date());
                        c.add(Calendar.DATE, -3);
                        String d3 = sdf.format(c.getTime());

                        c.setTime(new Date());
                        c.add(Calendar.DATE, -7);
                        String w1 = sdf.format(c.getTime());

                        c.setTime(new Date());
                        c.add(Calendar.MONTH, -1);
                        String m1 = sdf.format(c.getTime());

                        c.setTime(new Date());
                        c.add(Calendar.MONTH, -3);
                        String m3 = sdf.format(c.getTime());
                        List<String[]> teams = CheckAllTopThread.ranks;
                        for(int i = 0; i < teams.size(); i++) {
                            String[] parts = teams.get(i);
                            int prvD1 = getPreviousAbility(d1, allAbility.get(parts[4]+parts[5]+parts[0]));
                            int prvD3 = getPreviousAbility(d3, allAbility.get(parts[4]+parts[5]+parts[0]));
                            int prvW1 = getPreviousAbility(w1, allAbility.get(parts[4]+parts[5]+parts[0]));
                            int prvM1 = getPreviousAbility(m1, allAbility.get(parts[4]+parts[5]+parts[0]));
                    %>
                    <tr class="row<%=i % 2 + 1%>">
                        <td><%=i+1%></td>
                        <td><%=parts[4]%></td>
                        <td value="<%=parts[4]+parts[5]%>"><%=parts[5]%></td>
                        <td><%=parts[0]%></td>
                        <td><%=parts[1]%></td>
                        <td><%=parts[2]%></td>
                        <td><%=parts[7]%></td>
                        <td><%=prvD1 == -1 ? "0" : (Integer.parseInt(parts[1]) - prvD1)%></td>
                        <td><%=prvD3 == -1 ? "0" : (Integer.parseInt(parts[1]) - prvD3)%></td>
                        <td><%=prvW1 == -1 ? "0" : (Integer.parseInt(parts[1]) - prvW1)%></td>
                        <td><%=prvM1 == -1 ? "0" : (Integer.parseInt(parts[1]) - prvM1)%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
            <td valign="top">
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr class="head">
                        <th>平台</th>
                        <th>服务器</th>
                        <th>更新时间</th>
                        <th>操作</th>
                    </tr>
                    <%
                        List<String[]> servers = CheckAllTopThread.getInstance().servers;
                        for(int i = 0; i < servers.size(); i++) {
                            String[] s = servers.get(i);
                    %>
                    <tr class="row<%=i % 2 + 1%>">
                        <td><%=s[2]%></td>
                        <td><%=s[3]%></td>
                        <td><%=CheckAllTopThread.lastCheckTime.get(s[2]+s[3])%></td>
                        <td>
                            <c:if test="<%="1".equals(request.getParameter("show"))%>">
                                <input type="button" value="更新" onclick="doUpdate('<%=s[2]%>', '<%=s[3]%>')"/>
                            </c:if>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </td>
        </tr>
    </table>
    <br/>
    </body>
<script type="text/javascript">
    <c:if test="<%="1".equals(request.getParameter("show"))%>">
            function doUpdate(platform, server)
            {
                OgzqDwr.updateAllTop(platform,server, after);
            }
            function after()
            {
                alert('done');
            }
    </c:if>
</script>
</html>