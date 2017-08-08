<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-7-26
  Time: 14:36:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="com.saille.util.FileUtils" %>
<%@ page import="java.io.File" %>
<%@ page import="com.saille.ogzq.ViewAbilityThread" %>
<%@ page import="java.util.Map" %>
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
    <%!
        public String[] getAbility(String id, String club) throws Exception {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Team.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("LoadTeam1", id));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String ret = IDUtils.execute("robot0001@sina.com", pm);
            String ability = ret.substring(0, ret.lastIndexOf("[" + club + "]"));
            ability = ability.substring(0, ability.lastIndexOf(".png"));
            ability = ability.substring(0, ability.lastIndexOf("#"));
            ability = ability.substring(ability.lastIndexOf("#") + 1);
            ret = ret.substring(ret.indexOf("#") + 1);
            ret = ret.substring(ret.indexOf("#") + 1);
            ret = ret.substring(ret.indexOf("#") + 1);
            ret = ret.substring(0, ret.indexOf("#"));
            return new String[]{ret, ability};
        }

        public List<String[]> getPlayerIds(String str) throws Exception {
            List<String[]> ret = new ArrayList<String[]>();
            String club = str.substring(str.indexOf("@") + 1);
            club = club.substring(0, club.indexOf("|"));
            str = str.substring(str.indexOf("!") + 1);
            str = str.substring(0, str.indexOf("!"));
            String[] players = str.split("&");
            int i = 0;
            for(String player : players) {
                i++;
                System.out.println(i + "/" + players.length + ": " + player);
                String[] atts = player.split("[|]");
//                9642|HDFC|75|0   |92551|5628|12918
//                id   name lv 职务 贡献  pk胜 徽章
                String[] abilities = getAbility(atts[0], club);
                ret.add(new String[]{atts[0], atts[1], club, abilities[0], abilities[1]});
                FileUtils.WriteFile(new File("D:\\allclub2.txt"), atts[0] + "\t" + atts[1] + "\t" + club + "\t" + abilities[0] + "\t" + abilities[1] + "\r\n", true);
            }
            return ret;
        }

        public List<String[]> quickSort(List<String[]> list, int start, int end) {
            if(start >= end) {
                return list;
            }
            int pos = start;
            for(int i = pos + 1; i < end; i++) {
                boolean needSwap = false;
                if(Integer.parseInt(list.get(i)[3]) < Integer.parseInt(list.get(pos)[3])) {
                    needSwap = true;
                }

                if(needSwap) {
                    String[] tmp = list.get(i);
                    for(int m = i; m > pos; m--) {
                        list.set(m, list.get(m - 1));
                    }
                    list.set(pos, tmp);
                }
                pos = i;
            }
            quickSort(list, start, pos);
            quickSort(list, pos + 1, end);
            return list;
        }
    %>
    <%
        //        色	1	132
//        金	5	585
//        忠	10	306
//        囧	13	106
//        威	14	4721
//        黯	16	27326
//        1	21	202
//        G	23	2329
//        G	23	256
//        JR	40	2797
//        皇	54	846
//        皇	54	8
//        GG	57	137
//        髳	63	24798
//        国	65	1303
//        FT	86	294
//        川	89	6501
//        恋	124	231
//        爆	131	861
//        霸	132	366
//        霸	132	79
//        S	134	2569
//        nb	137	868
//        M1	162	180
        //17=囧, 33=1, 144=nb, 20=黑, 42=国, 196=武, 83=江, 15=中, 6=錵
//        int[] clubs = new int[]{132,585,306,106,4721,27326,202,2329,256,2797,846,8,137,24798,1303,294,6501,231,861,366,79,2569,868,180}; //实力榜
//        int[] clubs = new int[]{202,2329,137,2797,27326,1303,846,585,106,24798,132,4721,306}; //
//        int[] clubs = new int[]{106, 4721, 17033, 868, 124, 256, 1180, 502, 94, 40, 2866}; //自己人榜
//        int[] clubs = new int[]{132, 306}; //色忠
//        int[] clubs = new int[]{};
//        int[] clubs = new int[]{};
//        int[] clubs = new int[]{};
//        int[] clubs = new int[]{};
//        List<String[]> players = new ArrayList<String[]>();
//        for(int i = 0; i < clubs.length; i++) {
//            int id = clubs[i];
//            System.out.println(i + "/" + clubs.length + ":" + id);
//            HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamInfo.aspx");
//            List<NameValuePair> params = new ArrayList<NameValuePair>();
//            params.add(new BasicNameValuePair("type", "2"));
//            params.add(new BasicNameValuePair("TeamID", "" + id));
//            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//            String ret = IDUtils.execute("robot0001@sina.com", pm);
//            players.addAll(getPlayerIds(ret));
//        }
//        players = quickSort(players, 0, players.size());
        List<Map<String, String>> players = ViewAbilityThread.getPos();
        List<Map<String, String>> clubs = ViewAbilityThread.getClub();
        request.setAttribute("players", players);
        request.setAttribute("clubs", clubs);
    %>
    <body>
    <table border="0" cellpadding="1" cellspacing="1" width="100%">
        <tr>
            <td valign="top">
                <select id="refresh" onclick="updateList();">
                    <option selected="selected" value="--">--</option>
                    <option selected="selected" value="嫡系">嫡系</option>
                    <%
                        for(Map<String, String> club : clubs) {
                    %>
                    <option value="<%=club.get("id")%>"><%=club.get("abbri")%></option>
                    <%
                        }
                    %>
                </select>
                平均实力：<span id="avg"></span>
                <table id="info" border="0" cellpadding="1" cellspacing="1">
                    <tr class="head">
                        <th>序号</th>
                        <th>ID</th>
                        <th>游戏名</th>
                        <th>球会</th>
                        <th>名次</th>
                        <th>实力</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${players}" var="p" varStatus="i">
                        <tr class="row${i.index % 2 + 1}" value="${p['clubid']}">
                            <td>${i.index + 1}</td>
                            <td>${p['id']}</td>
                            <td>${p['name']}</td>
                            <td>${p['club']}</td>
                            <td>${p['pos']}</td>
                            <td>${p['ability']}</td>
                            <td>
                                <input type="button" value="更新" onclick="updatePlayerAbility('${p['id']}');"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
            <td valign="top">
                <table border="0" cellpadding="1" cellspacing="1">
                    <tr class="head">
                        <th>序号</th>
                        <th>名次</th>
                        <th>ID</th>
                        <th>等级</th>
                        <th>声望</th>
                        <th>缩写</th>
                        <th>名字</th>
                        <th>人数</th>
                        <th>会长</th>
                        <th>牌子</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${clubs}" var="c" varStatus="i">
                        <tr class="row${i.index % 2 + 1}">
                            <td>${i.index + 1}</td>
                            <td>${c['pos']+1}</td>
                            <td>${c['id']}</td>
                            <td>${c['level']}</td>
                            <td>${c['shengwang']}</td>
                            <td>${c['abbri']}</td>
                            <td>${c['name']}</td>
                            <td>${c['count']}</td>
                            <td>${c['captain']}</td>
                            <td>${c['paizi']}</td>
                            <td>
                                <input type="button" value="更新" onclick="updateClub('${c['id']}')"/>
                                <input type="button" value="查看" onclick="viewClub('${c['id']}')"/>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
    </table>
    </body>
<script type="text/javascript">
    function after(obj)
    {
        alert(obj);
    }

    function viewClub(id) {
        var select = document.getElementById('refresh');
        select.value = id;
        updateList();
//        for(var i = 0; i < select.options.length; i++) {
//            if
//        }
    }

    function updateClub(id) {
        OgzqDwr.updateClubAbility(id, after);
    }

    function updatePlayerAbility(id) {
        OgzqDwr.updatePlayerAbility(id, after);
    }

    function updateList() {
        var select = document.getElementById('refresh');
        var club = '--';
        for(var i = 0; i < select.options.length; i++) {
            if(select.options[i].selected) {
                club = select.options[i].value;
                break;
            }
        }
        var table = document.getElementById('info');
        var cls = 0;
        var total = 0;
        for(var i = 1; i < table.rows.length; i++) {
            if(club == '--') {
                table.rows[i].style.display = '';
                table.rows[i].className = 'row' + (cls % 2 + 1);
                cls++;
                table.rows[i].cells[0].innerHTML = cls;
                total += parseInt(table.rows[i].cells[5].innerHTML, 10);
            } else if(club == '嫡系') {
                if(table.rows[i].attributes['value'].value == '106' ||
                   table.rows[i].attributes['value'].value == '2329' ||
                   table.rows[i].attributes['value'].value == '4721' ||
                   table.rows[i].attributes['value'].value == '17033' ||
                   table.rows[i].attributes['value'].value == '868' ||
                   table.rows[i].attributes['value'].value == '2569' ||
                   table.rows[i].attributes['value'].value == '137' ||
                   table.rows[i].attributes['value'].value == '124' ||
                   table.rows[i].attributes['value'].value == '256' ||
                   table.rows[i].attributes['value'].value == '1180' ||
                   table.rows[i].attributes['value'].value == '502' ||
                   table.rows[i].attributes['value'].value == '40' ||
                   table.rows[i].attributes['value'].value == '94' ||
                   table.rows[i].attributes['value'].value == '2866' ||
                   table.rows[i].attributes['value'].value == '484' ||
                   table.rows[i].attributes['value'].value == '8584' ||
                   table.rows[i].attributes['value'].value == '2365') {
                    table.rows[i].style.display = '';
                    table.rows[i].className = 'row' + (cls % 2 + 1);
                    cls++;
                    table.rows[i].cells[0].innerHTML = cls;
                    total += parseInt(table.rows[i].cells[5].innerHTML, 10);
                } else {
                    table.rows[i].style.display = 'none';
                }
            } else if(club == table.rows[i].attributes['value'].value) {
                table.rows[i].style.display = '';
                table.rows[i].className = 'row' + (cls % 2 + 1);
                cls++;
                table.rows[i].cells[0].innerHTML = cls;
                total += parseInt(table.rows[i].cells[5].innerHTML, 10);
            } else {
                table.rows[i].style.display = 'none';
            }
        }
        document.getElementById('avg').innerHTML = total / cls;
    }
</script>
</html>