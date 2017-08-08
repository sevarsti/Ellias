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
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">
                        email
                    </span>
                </th>
                <th>nick</th>
                <th>money</th>
                <!--<th>place</th>-->
                <!--<th>id</th>-->
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">
                        itemCode
                    </span>
                </th>
                <th>
                    itemId
                </th>
                <th>
                    <span style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false)">
                        name
                    </span>
                </th>
                <th>number</th>
                <th>操作</th>
            </tr>
            <%
                int i = 0;
                List<String> keys = new ArrayList<String>();
                String email = request.getParameter("email");
                if(email != null && !email.equals("")) {
                    String[] emails = email.split("\\,");
                    for(String e : emails) {
                        keys.add(e);
                    }
//                    keys.add(email);
                } else {
                    Set<String> ids = IDUtils.IDInfos.keySet();
                    for(String s : ids) {
                        keys.add(s);
                    }
                }
//                Map<String, String> allItems = new Hashtable<String, String>(); //key: email+itemcode
                List<Map<String, String>> allItems = new ArrayList<Map<String, String>>();
                for (String id : keys) {
                    System.out.println(keys.indexOf(id) + "/" + keys.size() + ":" + id);
                    List<Map<String, String>> items = OperationUtils.listBags(id, "0");
                    allItems.addAll(items);
                }
//                List<String> keys = IDUtils.GETIDS();
                for (Map<String, String> item : allItems) {
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td value="<%=item.get("email")%>"><%=item.get("email")%></td>
                    <td value="<%=IDUtils.getNick(item.get("email"))%>"><%=IDUtils.getNick(item.get("email"))%></td>
                    <td value="<%=IDUtils.getNick(item.get("email"))%>"><%=IDUtils.IDInfos.get(item.get("email")).get("silver")%></td>
                    <%--<td>--%>
                        <%--<%=item.get("place")%>--%>
                    <%--</td>--%>
                    <%--<td>--%>
                        <%--<%=item.get("id")%>--%>
                    <%--</td>--%>
                    <td>
                        <%=item.get("itemid")%>
                    </td>
                    <td>
                        <%=item.get("id")%>
                    </td>
                    <td value="<%=item.get("name")%>">
                        <%=item.get("name")%>
                    </td>
                    <td value="<%=item.get("number")%>">
                        <%=item.get("number")%>
                    </td>
                    <td>
                        <input type="button" value="使用" onclick="useItem('<%=item.get("email")%>', '<%=item.get("id")%>', 1);"/>
                        <input type="button" value="使用全部" onclick="useItem('<%=item.get("email")%>', '<%=item.get("id")%>', <%=item.get("number")%>);"/>
                    </td>
                </tr>
            <%
                    i++;
                }
            %>
        </table>
    </body>
<script type="text/javascript">
    function useItem(email, id, all)
    {
        OgzqDwr.useItem(email, id, all, after);
    }

    function after(obj)
    {
        alert(obj);
    }
//
//    function combine(email, id)
//    {
//        OgzqDwr.combine(email, id, after);
//    }
</script>
</html>