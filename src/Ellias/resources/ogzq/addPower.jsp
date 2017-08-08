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
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URLDecoder" %>
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
    <%!
        public String color(String in) {
            String[] p = in.split("-");
            if(Integer.parseInt(p[0]) < Integer.parseInt(p[1])) {
                return "<span style=\"color:red;\">" + in + "</span>";
            } else {
                return in;
            }
        }
    %>
    <%
        List<String> ids = new ArrayList<String>();
        String email = request.getParameter("email");
        if(email != null) {
            email = URLDecoder.decode(email);
        }
        if(email != null && IDUtils.IDInfos.containsKey(email)) {
            ids.add(email);
        } else {
            ids = IDUtils.GETIDS();
        }
        String player = request.getParameter("name");
        List<String[]> results = new ArrayList<String[]>();
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String[] r = new String[12];
            r[0] = id;
            r[1] = IDUtils.getNick(id);
            r[2] = IDUtils.IDInfos.get(id).get("shili");
            HttpPost pm = new HttpPost(OgzqURL.URL + "/" + player + "_AddPower.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String str = IDUtils.execute(id, pm);
//10075|3357|2|Super/10075.png|布雷默|6@
//10072|80|2|Super/10072.png|少年布雷默|4*4*3@
//               need current
//11202|欧冠之魂|40|126*
// 1406|飞机6级 |4 |26*
// 1306|游艇6级 |4 |25*
// 1206|汽车6级 |4 |23*
//30280|成长证书|40|1
//╋10月5日10:00至10月8日24:00
            boolean enough = true;
            if(str.indexOf("╋") >= 0) {
                str = str.substring(0, str.indexOf("╋"));
            }
            String[] parts =  str.split("@")[1].split("\\*"); //少年
            String ss = parts[2] + "-" + parts[1]; //现有-需要
            r[4] = color(ss);
            if(!ss.equals(r[4])) {
                enough = false;
            }
            parts = str.split("@")[2].split("\\*");
            for(int i = 0; i < 5; i++) {
                String sss = parts[i].split("\\|")[3] + "-" + parts[i].split("\\|")[2];
                r[i + 5] = color(sss);
                if(!sss.equals(r[i + 5])) {
                    enough = false;
                }
            }
            r[10] = enough ? "1" : "0";

            pm = new HttpPost(OgzqURL.URL + "/" + player + "_Challenge.aspx");
            params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            str = IDUtils.execute(id, pm);
            if(str.split("#")[0].equals("0")) {
                r[3] = str.split("#")[1].split("&")[0];
                String canget = str.split("#")[1].split("&")[1];
                r[11] = canget;
            } else if(str.indexOf("╋") == 0) {
                r[3] = "99";
            }

            results.add(r);
        }
        request.setAttribute("result", results);
    %>
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">email</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">nick</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">实力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">进度</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, false)">少年</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false)">欧冠之魂</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, false)">飞机</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, false)">游艇</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, false)">汽车</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, false)">成长证书</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 10, false)">操作</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 11, false)">操作</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${result}" var="r" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <c:forEach begin="0" end="9" var="ii">
                        <td>${r[ii]}</td>
                    </c:forEach>
                    <td>
                        <c:if test="${r[10] == '1'}">
                            <input type="button" value="成长" onclick="addPower('${r[0]}')"/>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${r[11] == '1'}">
                            <input type="button" value="签约" onclick="sign('${r[0]}')"/>
                        </c:if>
                        <c:if test="${r[11] == '2'}">
                            已签约
                        </c:if>
                    </td>
                    <td>
                        <c:set var="key" value="${r[0]}"/>
                        <input type="button" value="材料" onclick="javascript:window.open('bag.jsp?email=<%=URLEncoder.encode(pageContext.getAttribute("key").toString())%>');"/>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
<script type="text/javascript">
    function after(obj) {
        alert(obj);
    }

    function sign(email) {
        OgzqDwr.signChallengePlayer(email, '<%=player%>', after);
    }

    function addPower(email) {
        OgzqDwr.challengeAddPower(email, '<%=player%>', after);
    }
</script>
</html>