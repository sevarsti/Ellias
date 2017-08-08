<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ include file="../include/include.jsp"%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2016-11-21
  Time: 17:01:28
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
            if(id.startsWith("txjcf")) {
                continue;
            }
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            String[] r = new String[12];
            r[0] = id;
            r[1] = IDUtils.getNick(id);
            r[2] = IDUtils.IDInfos.get(id).get("silver");
            r[3] = IDUtils.IDInfos.get(id).get("shili");

            List<Map<String, String>> items = OperationUtils.listBags(id, "0");
            for(Map<String, String> m : items) {
                if(m.get("itemid").equals("24001")) { //初级球魂
                    r[4] = m.get("number");
                } else if(m.get("itemid").equals("24002")) { //中级球魂
                    r[5] = m.get("number");
                } else if(m.get("itemid").equals("24003")) { //高级球魂
                    r[6] = m.get("number");
                } else if(m.get("itemid").equals("24004")) { //超级球魂
                    r[7] = m.get("number");
                } else if(m.get("itemid").equals("24005")) { //大师球魂
                    r[8] = m.get("number");
                } else if(m.get("itemid").equals("24201")) { //幸运合剂
                    r[9] = m.get("number");
                }
            }
            results.add(r);
        }
        request.setAttribute("result", results);
    %>
        <table id="info" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">email</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">nick</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">银币</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">实力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">初级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">中级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, true)">高级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, true)">超级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 8, true)">大师</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 9, true)">幸运合剂</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${result}" var="r" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <c:forEach begin="0" end="9" var="ii">
                        <td <c:if test="${ii > 1}">align="right"</c:if>>${r[ii]}</td>
                    </c:forEach>
                    <td>
                        <input type="button" value="提升" onclick="javascript:window.open('chengwei.jsp?email=${r[0]}');"/>
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