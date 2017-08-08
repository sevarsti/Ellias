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
            <%
                int i = 0;
                List<String> ids = IDUtils.GETIDS();
                List<Map<String, String>> allItems = new ArrayList<Map<String, String>>();
                for (String id : ids) {
                    System.out.println(ids.indexOf(id) + "/" + ids.size());
                    List<Map<String, String>> items = OperationUtils.listBags(id, "0");
                    allItems.addAll(items);
                }
                for (Map<String, String> item : allItems) {
                    System.out.println(allItems.indexOf(item) + "/" + allItems.size());
                    if(item.get("name").startsWith("训练卡") ||
                            item.get("name").startsWith("战术积分卡") ||
                            item.get("name").indexOf("装备套") >= 0 ||
                            item.get("name").startsWith("欧联大赢家包") ||
                            item.get("name").indexOf("激情卡") >= 0 ||
                            item.get("name").indexOf("级礼包") >= 0 ||
                            item.get("name").startsWith("欧冠鸿运包") ||
                            item.get("name").startsWith("每日登录礼包") ||
                            item.get("name").startsWith("限量积分卡") ||
                            item.get("name").startsWith("突破积分") ||
                            item.get("name").startsWith("圣诞小礼包") ||
                            item.get("name").startsWith("圣诞大礼包") ||
                            item.get("name").indexOf("世界杯积分卡") >= 0 ||
                            item.get("name").indexOf("球员成长积分卡") >= 0 ||
                            item.get("name").indexOf("签名积分卡") >= 0 ||
                            item.get("name").startsWith("签到") ||
                            item.get("name").startsWith("中礼包") ||
                            item.get("name").startsWith("小礼包") ||
                            item.get("name").startsWith("银币兑换包")) {
                        int n = Integer.parseInt(item.get("number"));
                        for(int j = 0; j < n; j++) {
                            System.out.println(item.get("email") + ",use:" + item.get("name") + ":" + j + "/" + n);
                            OperationUtils.useItem(item.get("email"), item.get("id"));
                        }
                    }
                    i++;
                }
            %>
</html>