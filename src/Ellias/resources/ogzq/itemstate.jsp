<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-12-2
  Time: 15:15:59
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
    <%
        List<String> ids = IDUtils.GETIDS();
//        Map<String, String> itemname = new HashMap<String, String>();
        Map<String, String[]> items = new HashMap<String, String[]>();
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            List<Map<String, String>> singleitems = OperationUtils.listBags(id, "0");
            for(Map<String, String> map : singleitems) {
                String itemcode = map.get("itemid");
                int count = Integer.parseInt(map.get("number"));
                String name = map.get("name");
                if(items.containsKey(itemcode)) {
                    items.get(itemcode)[1] = (Integer.parseInt(items.get(itemcode)[1]) + count) + "";
                } else {
                    items.put(itemcode, new String[]{name, count + ""});
                }
            }
        }
        List<String[]> details = new ArrayList<String[]>();
        for(String key : items.keySet()) {
            String[] value = items.get(key);
            details.add(new String[]{key, value[0], value[1]});
        }
    %>
    <body>
        <table bgcolor="black" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">
                    物品代码
                </th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">
                    物品名称
                </th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">
                    物品数量
                </th>
            </tr>
            <%for(int i = 0; i < details.size(); i++){%>
                <tr class="row<%=i % 2 + 1%>">
                    <td><%=details.get(i)[0]%></td>
                    <td><%=details.get(i)[1]%></td>
                    <td><%=details.get(i)[2]%></td>
                </tr>
            <%}%>
        </table>
    </body>
<script type="text/javascript">
</script>
</html>