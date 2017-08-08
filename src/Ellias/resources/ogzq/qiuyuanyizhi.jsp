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
                <th rowspan="2">球员</th>
                <th rowspan="2">ID</th>
                <th rowspan="2">位置</th>
                <th rowspan="2">替补</th>
                <th colspan="2">进攻</th>
                <th colspan="2">组织</th>
                <th colspan="2">防守</th>
            </tr>
            <tr class="head">
                <!--<th>ID</th>-->
                <th>等级</th>
                <th>升级需要经验</th>
                <th>等级</th>
                <th>升级需要经验</th>
                <th>等级</th>
                <th>升级需要经验</th>
            </tr>
            <%
                int[] needexp = new int[]{0, 200, 900, 2300, 4600, 8000, 12700, 18900, 26800, 36600, 48500, 62700, 79400, 98800, 121100, 146500, 175200, 207400, 243300, 283100, 327000, 375200, 427900, 485300, 547600, 615000, 687700, 765900, 849800, 939600};
                String totaljifen = "";
                for(int i = 1; i < players.size(); i++) {
                    Map<String, String> p = players.get(i);
//                    Map<String, String> detail = OperationUtils.viewPlayer(email, p.get("id"));
                    Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);
                    boolean isZijin = IDUtils.isZijinPlayer(p.get("id"));
            %>
            <tr class="row<%=i % 2 + 1%>">
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
            <%
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/Gjxls.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("GameLoad1", p.get("id")));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(email, pm);
                    if("-1".equals(s)) {
            %>
                <td colspan="6">--</td>
            <%
                    } else {
                        String[] parts = s.split("@");
                        totaljifen = parts[4];
//范·巴斯滕|Super/10043.png|6|612178|10043@
//6|8047|34|34|2@ //进攻 现在等级|现在经验|增加属性1|增加属性2|加成倍数(当前阵容)
//1|0|0|0|1@      //组织
//1|0|0|0|1@      //防守
//127093@//当前意识
//695956|619156|630640|712132|602404|690094|642004|521326|569284|612178|686572|529546|566818|599530|2024995
//                    out.println(s);
            %>
                <td>
                    <%=parts[1].split("[|]")[0]%>
                </td>
                <td>
                    <%=needexp[Integer.parseInt(parts[1].split("[|]")[0])] - Integer.parseInt(parts[1].split("[|]")[1])%>
                    <%
                        if(needexp[Integer.parseInt(parts[1].split("[|]")[0])] <= Integer.parseInt(totaljifen)) {
                    %>
                    <input type="button" value="升级" onclick="dolevelup('<%=p.get("id")%>', '<%=needexp[Integer.parseInt(parts[1].split("[|]")[0])]%>', '<%=Integer.parseInt(parts[2].split("[|]")[1])%>', '<%=Integer.parseInt(parts[3].split("[|]")[1])%>')"
                    <%
                        }
                    %>
                </td>
                <td>
                    <%=parts[2].split("[|]")[0]%>
                </td>
                <td>
                    <%=needexp[Integer.parseInt(parts[2].split("[|]")[0])] - Integer.parseInt(parts[2].split("[|]")[1])%>
                    <%
                        if(needexp[Integer.parseInt(parts[2].split("[|]")[0])] <= Integer.parseInt(totaljifen)) {
                    %>
                    <input type="button" value="升级" onclick="dolevelup('<%=p.get("id")%>', '<%=Integer.parseInt(parts[1].split("[|]")[1])%>', '<%=needexp[Integer.parseInt(parts[2].split("[|]")[0])]%>', '<%=Integer.parseInt(parts[3].split("[|]")[1])%>')"
                    <%
                        }
                    %>
                </td>
                <td>
                    <%=parts[3].split("[|]")[0]%>
                </td>
                <td>
                    <%=needexp[Integer.parseInt(parts[3].split("[|]")[0])] - Integer.parseInt(parts[3].split("[|]")[1])%>
                    <%
                        if(needexp[Integer.parseInt(parts[3].split("[|]")[0])] <= Integer.parseInt(totaljifen)) {
                    %>
                    <input type="button" value="升级" onclick="dolevelup('<%=p.get("id")%>', '<%=Integer.parseInt(parts[1].split("[|]")[1])%>', '<%=Integer.parseInt(parts[2].split("[|]")[1])%>', '<%=needexp[Integer.parseInt(parts[3].split("[|]")[0])]%>')"
                    <%
                        }
                    %>
                </td>
            </tr>
            <%
                    }
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

    function dolevelup(playerId, jingong, zuzhi, fangshou) {
        OgzqDwr.upQiuyuanyishi('<%=email%>', playerId, jingong, zuzhi, fangshou, after);
    }
</script>
</html>