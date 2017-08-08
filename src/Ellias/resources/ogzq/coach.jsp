<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2014-3-26
  Time: 21:01:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
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
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th rowspan="2">登录名</th>
                <th rowspan="2">游戏名</th>
                <th rowspan="2">当前</th>
                <th colspan="5">初攻-米卢</th>
                <th colspan="5">初组-希丁克</th>
                <th colspan="5">初防-里皮</th>
                <th colspan="5">高攻-瓜迪奥拉</th>
                <th colspan="5">高组-弗格森</th>
                <th colspan="5">高防-穆里尼奥</th>
                <th rowspan="2">高教</th>
            </tr>
            <tr class="head">
                <c:forEach begin="1" end="6">
                    <th>可用</th>
                    <th>等级</th>
                    <!--<th>经验</th>-->
                    <!--<th>下一级经验</th>-->
                    <th>攻击</th>
                    <th>组织</th>
                    <th>防守</th>
                </c:forEach>
            </tr>
    <%
        String[] desc = new String[]{"初攻", "初组", "初防", "高攻", "高组", "高防"};
        List<String> ids = new ArrayList<String>();
        if(StringUtils.isNotEmpty(request.getParameter("email"))) {
            ids.add(request.getParameter("email"));
        } else {
            ids = IDUtils.GETIDS();
        }
        for(int i = 0; i < ids.size(); i++) {
            String id = ids.get(i);
            System.out.println(i + "/" + ids.size() + ": " + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
            ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadCoach1", ""));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute(id, pm);
//4|瓜迪奥拉|7*1|米卢|7*2|希丁克|7*3|里皮|7*5|弗格森|4*6|穆里尼奥|3@
//1|0|2|1|米卢|7|9400|-999|0|44|30|18|10|0|0|0|0|0|0|0|0^
//1|0|2|2|希丁克|7|9400|-999|1|30|44|18|10|0|0|0|0|0|0|0|0^
//1|0|2|3|里皮|7|9400|-999|2|18|30|44|10|0|0|0|0|0|0|0|0^
//1|1|0|4|瓜迪奥拉|7|118531|168000|0|108|72|42|20|12|0|0|0|0|0|0|0^
//1|0|0|5|弗格森|4|38220|43000|1|54|84|30|14|63|0|0|0|0|0|0|0^
//1|0|0|6|穆里尼奥|3|20302|25000|2|26|48|76|12|52|0|0|0|0|0|0|0
//@@1
            if(ret.indexOf("action=\"Coach.aspx\"") >= 0) {
                out.println("<tr class=\"row" + (i % 2 + 1) + "\"><td>" + id + "</td><td>" + IDUtils.getNick(id) + "</td><td colspan=\"32\">--</td></tr>");
                continue;
            }
            ret = ret.substring(ret.indexOf("@") + 1);
            ret = ret.substring(0, ret.indexOf("@"));
            String[] coaches = ret.split("[\\^]");
            boolean hasHighCoach = false;
            String current = "";
            for(int j = 0; j < 6; j++) {
                String coach = coaches[j];
                if(coach.split("[|]")[1].equals("1")) {
                    current = desc[j];
                    break;
                }
            }
//        out.println(id);
    %>
            <tr class="row<%=i % 2 + 1%>">
                <td><%=id%></td>
                <td><%=IDUtils.getNick(id)%></td>
                <td><%=current%></td>
    <%
            for(int j = 0; j < 6; j++) {
                String coach = coaches[j];
                String[] atts = coach.split("[|]");
                if(j > 2 && atts[0].equals("1")) {
                    hasHighCoach = true;
                }
    %>
                <td>
                    <%=atts[0].equals("1") ? "" : "<span style=\"color:red;\">☆</span>"%>
                </td>
                <td>
                    <%=atts[5]%>
                </td>
                <%--<td>--%>
                    <%--<%=atts[6]%>--%>
                <%--</td>--%>
                <%--<td>--%>
                    <%--<%=Integer.parseInt(atts[7]) > 0 ? atts[7] : "--"%>--%>
                <%--</td>--%>
                <td>
                    <%=atts[9]%>
                </td>
                <td>
                    <%=atts[10]%>
                </td>
                <td>
                    <%=atts[11]%>
                </td>
    <%
            }
    %>
                <td>
                    <%=hasHighCoach ? "★" : "<span style=\"color:red;\">☆</span>"%>
                </td>
            </tr>
    <%
        }
    %>
        </table>
    </body>
<script type="text/javascript">
    function doCheDan(input, email, id) {
        var table = document.getElementById('sell');
        table.deleteRow(input.parentNode.parentNode.rowIndex);
        OgzqDwr.cheDan(email, id, after);
    }

    function after(obj)
    {
        alert(obj);
    }

    function combine(email, id, count)
    {
        if(document.getElementById('all').checked) {
            OgzqDwr.combine(email, id, count, after);
        } else {
            var num = document.getElementById('num').value;
            if(num == '') {
                OgzqDwr.combine(email, id, 0, after);
            } else {
                var c = parseInt(num, 10) * 5;
                OgzqDwr.combine(email, id, c, after);
                document.getElementById('num').value = '';
            }
        }
    }

    function sellLv5(email)
    {
        OgzqDwr.doSellLv5(email, afterSellLv5);
    }

    function afterSellLv5(str)
    {
        alert(str);
    }
</script>
</html>