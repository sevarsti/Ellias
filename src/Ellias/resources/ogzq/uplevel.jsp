<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type="text/javascript" src="../scripts/swfobject.js"></script>
    </head>
    <%!
        List<String> sortKey(List<String> list, int start, int end) {
            if(start >= end) {
                return list;
            }
            int pos = start;
            for(int i = pos + 1; i < end; i++) {
                boolean needSwap = false;
                if(Integer.parseInt(IDUtils.IDInfos.get(list.get(i)).get("shili"))> Integer.parseInt(IDUtils.IDInfos.get(list.get(pos)).get("shili"))) {
                    needSwap = true;
                }

                if(needSwap) {
                    String tmp = list.get(i);
                    for(int m = i; m > pos; m--) {
                        list.set(m, list.get(m - 1));
                    }
                    list.set(pos, tmp);
                }
                pos = i;
            }
            sortKey(list, start, pos);
            sortKey(list, pos + 1, end);
            return list;
        }
    %>
    <body>
        <table id="info" border="0" cellpadding="1" cellspacing="1" class="rowover">
            <tr class="head">
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">登录名</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">游戏名</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">黄金</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">银币</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">等级</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, true)">实力</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, true)">声望</th>
                <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, true)">三剑</th>
            </tr>
            <%
                int i = 0;
                List<String> keys = IDUtils.GETIDS();
                keys = sortKey(keys, 0, keys.size());
                for (String key : keys) {
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td>
                        <input type="button" value="训练" onclick="javascript:window.open('train.jsp?email=<%=URLEncoder.encode(key)%>')"/>
                        <input type="button" value="装备" onclick="javascript:window.open('equip.jsp?email=<%=URLEncoder.encode(key)%>')"/>
                        <input type="button" value="查看战术中心" onclick="javascript:window.open('tactic.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="战术" onclick="javascript:window.open('mytactic.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="升阶" onclick="javascript:window.open('shengjie.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="成长" onclick="javascript:window.open('chengzhang.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="世界杯积分" onclick="javascript:window.open('shijiebeijifen.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="镶嵌" onclick="javascript:window.open('doxiangqian.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="材料" onclick="javascript:window.open('bag.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="三剑" onclick="javascript:window.open('qgqs.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="教练" onclick="javascript:window.open('upCoach.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <input type="button" value="激情" onclick="javascript:window.open('jiqingtrain.jsp?email=<%=URLEncoder.encode(key)%>');"/>
                        <%=key%>
                    </td>
                    <td><%=IDUtils.getNick(key)%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("gold")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("silver")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("level")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("shili")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("shengwang")%></td>
                    <td><%=IDUtils.IDInfos.get(key).get("sjk") == null ? "&nbsp;" : IDUtils.IDInfos.get(key).get("sjk")%></td>
                </tr>
            <%
                    i++;
                }
            %>
        </table>
        <br/>
    </body>
<script type="text/javascript">
    function afterDo(obj)
    {
        alert(obj);
    }

    function signupTeamGame(id)
    {
        OgzqDwr.signupTeamGame(id, afterDo);
    }

    function getTacticPoint(id)
    {
        OgzqDwr.getTacticPoint(id, afterDo);
    }
</script>
</html>