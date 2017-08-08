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
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
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
        <table class="frame" width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <th rowspan="2">
                    email
                </th>
                <th rowspan="2">
                    nick
                </th>
                <th colspan="10">法甲</th>
                <th colspan="10">德甲</th>
                <th colspan="10">意甲</th>
                <th colspan="10">英超</th>
                <th colspan="10">西甲</th>
                <th rowspan="2" style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 52, true)">共计</th>
                <th rowspan="2" style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 53, true)">剩余场次</th>
            </tr>
            <tr class="head">
                <c:forEach begin="0" end="49" var="i">
                    <th>${(i+1) % 10}</th>
                </c:forEach>
            </tr>
            <%
                List<String> ids = new ArrayList<String>();
                String email = request.getParameter("email");
                if(email != null && !email.equals("")) {
                    String[] emails = email.split("\\,");
                    for(String e : emails) {
                        ids.add(e);
                    }
                } else {
                    ids = IDUtils.GETIDS();
                }
                for(int i = 0; i < ids.size(); i++) {
                    String id = ids.get(i);
                    if(id.indexOf("txjcf") >= 0) {
                        continue;
                    }
                    if(
                            id.equals("101977723@qq.com") ||
                            id.equals("115271540@qq.com") ||
                            id.equals("124906932@qq.com") ||
                            id.equals("156451865@qq.com") ||
                            id.equals("276300227@qq.com") ||
                            id.equals("279512194@qq.com") ||
                            id.equals("316639404@qq.com") ||
                            id.equals("36386426@qq.com") ||
                            id.equals("3897021733@qq.com") ||
                                    id.equals("421623479@qq.com") ||
                            id.equals("444825566@qq.com") ||
                            id.equals("472545875@qq.com") ||
                            id.equals("7125608@163.com") ||
                            id.equals("823820371@qq.com") ||
                            id.equals("82382037111@qq.com") ||
                            id.equals("bixi2@sina.com") ||
                            id.equals("blue001@sina.com") ||
                            id.equals("blue002@sina.com") ||
                            id.equals("fanmingsuo@163.com") ||
                            id.equals("jiangleiyy1986@126.com") ||
                            id.equals("karen525@tom.com") ||
                            id.equals("meijianbai@hotmail.com") ||
                            id.equals("mrshanyao@163.com") ||
                            id.equals("orange001@sina.com") ||
                            id.equals("orange002@sina.com") ||
                            id.equals("orange003@sina.com") ||
                            id.equals("robot0001@sina.com") ||
                            id.equals("robot0002@sina.com") ||
                            id.equals("robot0004@sina.com") ||
                            id.equals("robot0005@sina.com") ||
                            id.equals("robot0006@sina.com") ||
                            id.equals("robot0007@sina.com") ||
                            id.equals("robot0008@sina.com") ||
                            id.equals("robot0009@sina.com") ||
                            id.equals("robot0010@sina.com") ||
                            id.equals("robot0011@sina.com") ||
                            id.equals("robot0012@sina.com") ||
                            id.equals("sevarsti@sina.com") ||
                            id.equals("stevending1@163.com") ||
                            id.equals("www.569323373@qq.163.com") ||
                                    id.equals("xieqigan@qq.com") ||
                            id.equals("zhao6527@163.com") ||
                            id.equals("zhhz1226@163.com") ||
                            (1==0)) {
                        continue;
                    }
                    System.out.println("查看欧冠之巅" + i + "/" + ids.size() + ":" + id);
                    int total = 0;
                    int restMatch = 0;

                    HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("OgzdGameLoad1", "0"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(id, pm);
                    restMatch = Integer.parseInt(s.split("@")[0]) + 1;
            %>
            <tr class="row<%=i % 2 + 1%>" align="right">
                <td><%=id%></td>
                <td><%=IDUtils.getNick(id)%></td>
            <%
                    for(int league = 1; league <= 5; league++) {
                        pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                        params = new ArrayList<NameValuePair>();
                        params.add(new BasicNameValuePair("ShowOgzdGame1", league + ""));
                        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                        s = IDUtils.execute(id, pm);
                        int matchId = Integer.parseInt(s.substring(0, s.indexOf("@")));
                        if(matchId > 0 || s.split("@").length <= 5) {
            %>
                <td colspan="10"><%=matchId%></td>
            <%
                        } else {
                            String team = s.split("@")[5];
                            String[] teams = team.split("&");
                            for(int j = 0; j < teams.length; j++) {
                                String[] subs = teams[j].split("[|]");
                                int c = Integer.parseInt(subs[8]);
                                total += c;
            %>
                <td<c:if test="<%=c==11%>"> style="background-color:black;"</c:if>>
                    <c:if test="<%=c < 11%>">
                        <%=c%>
                    </c:if>
                </td>
            <%
                            }
            %>
            <%
                        }
                    }
            %>
                <td><%=total%></td>
                <td><%=restMatch%></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
<script type="text/javascript">
</script>
</html>