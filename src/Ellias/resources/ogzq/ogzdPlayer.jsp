<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-8-11
  Time: 2:04:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
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
    <body onload="updatecount();">
        <select id="pos" onchange="updatePlayer();">
            <option value="0">-全-</option>
            <option value="1">门将</option>
            <option value="2">后卫</option>
            <option value="3">中场</option>
            <option value="4">前锋</option>
        </select>
        <select id="level" onchange="updatePlayer();">
            <option value="0">-全-</option>
            <option value="1">普通</option>
            <option value="2">优秀</option>
            <option value="3">精英</option>
            <option value="4">杰出</option>
            <option value="5">大牌</option>
            <option value="6">巨星</option>
        </select>
        <select id="league" onchange="updatePlayer();">
            <option value="0">-全-</option>
            <option value="1">法</option>
            <option value="2">德</option>
            <option value="3">意</option>
            <option value="4">英</option>
            <option value="5">西</option>
        </select>
        <%!
            private int containPlayer(List<String[]> players, String name) {
                for(int i = 0; i < players.size(); i++) {
                    String[] p = players.get(i);
                    if(p[0].equals(name)) {
                        return i;
                    }
                }
                return -1;
            }
        %>
        <%
            List<String[]> players = new ArrayList<String[]>();
            players.add(new String[]{"鲁菲耶", "1普通", "1", "1"});
            players.add(new String[]{"谢朱", "1普通", "2", "1"});
            players.add(new String[]{"福法纳", "1普通", "3", "1"});
            players.add(new String[]{"博卡利", "1普通", "4", "1"});
            players.add(new String[]{"芒当达", "2优秀", "1", "1"});
            players.add(new String[]{"戈尼亚隆斯", "2优秀", "2", "1"});
            players.add(new String[]{"瓦尔布埃纳", "2优秀", "3", "1"});
            players.add(new String[]{"乌塔卡", "2优秀", "4", "1"});
            players.add(new String[]{"朗德洛", "3精英", "1", "1"});
            players.add(new String[]{"马克斯维尔", "3精英", "234", "1"});
            players.add(new String[]{"戈比", "3精英", "2", "3"});
            players.add(new String[]{"卡列洪", "3精英", "4", "3"});
            players.add(new String[]{"佩林", "4杰出", "1", "3"});
            players.add(new String[]{"什科琴尼", "4杰出", "1", "4"});
            players.add(new String[]{"卡瓦略", "4杰出", "2", "1"});
            players.add(new String[]{"梅策尔德", "4杰出", "2", "2"});
            players.add(new String[]{"帝亚戈·莫塔", "4杰出", "3", "1"});
            players.add(new String[]{"阿巴特", "4杰出", "3", "3"});
            players.add(new String[]{"贝尔巴托夫", "4杰出", "4", "4"});
            players.add(new String[]{"吉尼亚克", "4杰出", "4", "1"});
//            players.add(new String[]{"G·罗西", "4杰出"});
//            players.add(new String[]{"吉拉迪诺", "4杰出"});
            players.add(new String[]{"斯特克伦博格", "5大牌", "1", "4"});
            players.add(new String[]{"拉莫斯", "5大牌", "23", "5"});
            players.add(new String[]{"纳尼", "5大牌", "4", "4"});

        %>
        <table class="frame" width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="player">
            <tr class="head" valign="top" align="left">
                <th rowspan="2">
                    email
                </th>
                <th rowspan="2">
                    nick
                </th>
                <%
                    for(int i = 0; i < players.size(); i++) {
                        String[] player = players.get(i);
                %>
                <th pos="<%=player[2]%>" level="<%=player[1].substring(0, 1)%>" league="<%=player[3]%>"><%=player[0]%></th>
                <%
                    }
                %>
            </tr>
            <tr class="head" valign="top" align="left">
                <%
                    for(int i = 0; i < players.size(); i++) {
                        String level = players.get(i)[1];
                %>
                <td  ><%=level%></td>
                <%
                    }
                %>
            </tr>
        <%
            String email = request.getParameter("email");
            List<String> ids;
            if(StringUtils.isEmpty(email)) {
                ids = IDUtils.GETIDS();
            } else {
                ids = new ArrayList<String>();
                String[] emails = email.split("\\,");
                for(String e : emails) {
                    ids.add(e);
                }
//                ids.add(email);
            }
            for(int idx = 0; idx < ids.size(); idx++) {
                System.out.println(idx + "/" + ids.size() + ":" + ids.get(idx));
                String id = ids.get(idx);
                if(id.indexOf("txjcf") >= 0) {
                    continue;
                }
                if(idx > 0 && idx % 15 == 0) {
        %>
            <tr class="head" valign="top" align="left">
                <th>
                    email
                </th>
                <th>
                    nick
                </th>
                <%
                    for(int i = 0; i < players.size(); i++) {
                        String[] player = players.get(i);
                %>
                <td  ><%=player[0]%></td>
                <%
                    }
                %>
            </tr>
            <%
                }
                boolean[] has = new boolean[players.size()];
                for(int league = 1; league <= 5; league++) {
                    HttpPost pm = new HttpPost(OgzqURL.URL + "/Ogzd.aspx");
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("ShowOgzdGame1", league + ""));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(id, pm); //0@1@9@2@2*0@78438|圣埃蒂安   |3001|100........@18752|Ambrosini|npc53.png|14206|6

                    if(Integer.parseInt(s.substring(0, s.indexOf("@"))) < 0) {
                        continue;
                    }
                    if(s.split("@").length < 6) {
                        continue;
                    }
                    String team = s.split("@")[5];
                    String[] teams = team.split("&");
                    for(String t : teams) {
                        String player = t.substring(t.lastIndexOf("|") + 1);
                        String[] myPlayers = player.split("[\\^]");
                        for(String p : myPlayers) {
                            String[] pp = p.split("[*]");
                            if(pp.length < 6) {
                                continue;
                            }
                            if(pp[5].equals("1")) {
                                int index = containPlayer(players, pp[0]);
                                if(index >= 0) {
                                    has[index] = true;
                                }
                            }
                        }
                    }
                }
        %>
            <tr class="row<%=idx % 2 + 1%>">
                <td><%=id%></td>
                <td><%=IDUtils.getNick(id)%></td>
                <%
                    for(int i = 0; i < has.length; i++) {
                %>
                <td  >
                    <c:choose>
                        <c:when test="<%=has[i]%>">
                            ★
                        </c:when>
                        <c:otherwise>
                            &nbsp;
                        </c:otherwise>
                    </c:choose>
                </td>
                <%
                    }
                %>
            </tr>
        <%
            }
        %>
        </table>
    </body>
<script type="text/javascript">
    function updatePlayer() {
        var pos = document.getElementById('pos').value;
        var level = document.getElementById('level').value;
        var league = document.getElementById('league').value;
        var table = document.getElementById('player');
//        for(var i = 0; i < table.rows.length; i++) {
            for(var j = 2; j < table.rows[0].cells.length; j++) {
                if(!table.rows[0].cells[j].attributes['pos']) {
                    continue;
                }
                if(!table.rows[0].cells[j].attributes['level']) {
                    continue;
                }
                if(!table.rows[0].cells[j].attributes['league']) {
                    continue;
                }
                var show = false;
                if(pos == 0 || table.rows[0].cells[j].attributes['pos'].value.indexOf(pos) >= 0) {
                    if(level == 0 || level == table.rows[0].cells[j].attributes['level'].value) {
                        if(league == 0 || league == table.rows[0].cells[j].attributes['league'].value) {
                            show = true;
                        }
                    }
                }
                if(show) {
                    table.rows[0].cells[j].style.display = '';
                    table.rows[1].cells[j-2].style.display = '';
                    for(var i = 2; i < table.rows.length; i++)
                    {
                        table.rows[i].cells[j].style.display = '';
                    }
                } else {
                    table.rows[0].cells[j].style.display = 'none';
                    table.rows[1].cells[j-2].style.display = 'none';
                    for(var i = 2; i < table.rows.length; i++)
                    {
                        table.rows[i].cells[j].style.display = 'none';
                    }
                }
            }
//        }
    }

    function updatecount()
    {
        var table = document.getElementById('player');
        for(var j = 2; j < table.rows[0].cells.length; j++)
        {
            var count = 0;
            for(var i = 2; i < table.rows.length; i++)
            {
                if(table.rows[i].cells[j].innerHTML.indexOf("★") >= 0)
                {
                    count += 1;
                }
            }
            table.rows[1].cells[j - 2].innerHTML = count;
        }
    }
</script>
</html>