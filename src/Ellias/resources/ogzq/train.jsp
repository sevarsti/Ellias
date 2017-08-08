<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-16
  Time: 22:33:25
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-15
  Time: 20:01:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.NameValuePair" %>
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
    <%!
        String convert2TacticalDesc(String in) {
            if(in == null) {
                return "未知";
            } else if("1".equals(in)) {
                return "高攻";
            } else if("3".equals(in)) {
                return "高组";
            } else if("5".equals(in)) {
                return "高防";
            } else {
                return in;
            }
        }

        List<String[]> insertItems(List<String[]> items, String[] newItem) {
            boolean insert = false;
            for(int i = 0; i < items.size(); i++) {
                String[] item = items.get(i);
                if(item[1].compareTo(newItem[3]) >= 0) {
                    items.add(i, new String[]{newItem[0], newItem[3], newItem[11], newItem[7]});
                    insert = true;
                    break;
                }
            }
            if(!insert) {
                items.add(new String[]{newItem[0], newItem[3], newItem[11], newItem[7]});
            }
            return items;
        }

        List<String[]> getItems(String email) throws Exception {
            List<String[]> ret = new ArrayList<String[]>();
            HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.BAGS);
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("type", "0"));
            params.add(new BasicNameValuePair("itemtype", "2"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(email, pm);
            String[] items = s.split("[|]");
            List<String[]> xianliang = new ArrayList<String[]>();
            List<String[]> qiuyi = new ArrayList<String[]>();
            List<String[]> huxi = new ArrayList<String[]>();
            List<String[]> qiuxie = new ArrayList<String[]>();
            List<String[]> shoutao = new ArrayList<String[]>();
            for(String item : items) {
                String[] atts = item.split("[*]");
                if(atts[3].startsWith("限量")) {
                    xianliang.add(new String[]{atts[0], atts[3], atts[11], atts[7]});
                }else if(atts[3].startsWith("战袍")) {
                    insertItems(qiuyi, atts);
                } else if(atts[3].startsWith("护腿")) {
                    insertItems(huxi, atts);
                } else if(atts[3].startsWith("战靴")) {
                    insertItems(qiuxie, atts);
                } else if(atts[3].startsWith("手套")) {
                    insertItems(shoutao, atts);
                }
            }
            ret.addAll(xianliang);
            ret.addAll(qiuyi);
            ret.addAll(huxi);
            ret.addAll(qiuxie);
            ret.addAll(shoutao);
            return ret;
        }
    %>
    <%
        String email = request.getParameter("email");
        List<Map<String, String>> players = new OgzqDwr().viewPlayer(email);
        List<Map<String, String>> items = OperationUtils.listBags(email, "0");
        Map<String, String[]> cards = new HashMap<String, String[]>();

        for(Map<String, String> item : items) {
            if(item.get("name").startsWith("球员经验卡")) {
                String key = item.get("name");
                key = key.substring(key.indexOf("球员经验卡") + "球员经验卡".length());
                key = key.substring(0, key.indexOf("点"));
                cards.put(key, new String[]{item.get("id"), item.get("number")});
            }
        }
        List<String[]> daoju = new ArrayList<String[]>();
        List<Integer> keys = new ArrayList<Integer>();
        for(String s : cards.keySet()) {
            keys.add(Integer.parseInt(s));
        }
        Collections.sort(keys);
        int sumTrainPoint = 0;
        for(Integer ss : keys) {
            String s = String.valueOf(ss);
            daoju.add(new String[]{s, cards.get(s)[0], cards.get(s)[1]});
            sumTrainPoint += Integer.parseInt(s) * Integer.parseInt(cards.get(s)[1]);
        }
        request.setAttribute("daoju", daoju);

        HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", "0"));
        pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
        String str = IDUtils.execute(email, pm);
        String currentTactical = str.split("⊥")[1];
        str = str.split("⊥")[0];
        String[] currentOnPlayers = str.split("∟");
    %>
        <%=email%>
    <br/>
    训练点：<%=players.get(0).get("point")%>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <!--<th>ID</th>-->
                <th>名称</th>
                <th>等级</th>
                <th>品质</th>
                <th>经验</th>
                <th>位置</th>
                <th>能力</th>
                <th>替补</th>
                <th>射门</th>
                <th>突破</th>
                <th>断球</th>
                <th>铲球</th>
                <th>传球</th>
                <th>速度</th>
                <th>扑救</th>
                <th>出击</th>
                <th>操作</th>
            </tr>
            <%
                for(int i = 1; i < players.size(); i++) {
                    Map<String, String> p = players.get(i);
//                    Map<String, String> detail = OperationUtils.viewPlayer(email, p.get("id"));
                    Map<String, String> detail = CacheManager.loadPlayer(email, p.get("id"), false);
                    String color = "black";
            %>
            <tr class="row<%=i % 2 + 1%>">
                <%--<td><%=p.get("id")%></td>--%>
                <td>
                    <input type="button" value="解" onclick="doFire(<%=p.get("id")%>);" style="padding-left: 1px;padding-right: 1px;"/>
                    <input type="button" value="训" onclick="doTrain(<%=p.get("id")%>);" style="padding-left: 1px;padding-right: 1px;"/>
                    <%
                        if(!"0".equals(detail.get("jiqing")) || !("-1".equals(detail.get("promotion")) || "联赛之星".equals(detail.get("promotion")))) {
                    %>
                    <span style="color:red;">
                    <%
                        }
                    %>
                    <input type="checkbox" name="changeIndex" value="<%=p.get("id")%>" style="padding-left: 1px;padding-right: 1px;"/>
                    <%--<input type="radio" name="equipPlayer" value="<%=p.get("id")%>" style="padding-left: 1px;padding-right: 1px;"/>--%>
                    <%=p.get("name")%>
                        <%
                            if(!"0".equals(detail.get("jiqing")) || !("-1".equals(detail.get("promotion")) || "联赛之星".equals(detail.get("promotion")))) {
                        %>
                    </span>
                        <%
                            }
                        %>
                </td>
                <td><%=p.get("level")%></td>
                <td><%=detail.get("pinzhi")%></td>
                <td>
                    <input type="button" value="U" onclick="upgrade('<%=p.get("id")%>')" style="padding-left: 1px;padding-right: 1px;"/>
                    <%
                        color="black";
                        String[] ab = p.get("exp").split("/");
                        if(Integer.parseInt(ab[0]) >= Integer.parseInt(ab[1]) && !(p.get("level").equals("7") && ab[0].equals("37500")) &&
                                !(p.get("level").equals("9") && ab[0].equals("122500")) &&
                                !(p.get("level").equals("8") && ab[0].equals("75000"))) {
                            color = "red";
                        }
                    %>
                    <span style="color:<%=color%>">
                        <%=p.get("exp")%>
                    </span>
                </td>
                <td><%=detail.get("pos")%></td>
                <td>
                    <%
                        String param = detail.get("shemen") + "-" + detail.get("shemenm") + ";" +detail.get("tupo") + "-" + detail.get("tupom") + ";" +
                                detail.get("duanqiu") + "-" + detail.get("duanqium") + ";" +detail.get("chanqiu") + "-" + detail.get("chanqium") + ";" +
                                detail.get("chuanqiu") + "-" + detail.get("chuanqium") + ";" +detail.get("sudu") + "-" + detail.get("sudum") + ";" +
                                detail.get("pujiu") + "-" + detail.get("pujium") + ";" +detail.get("chuji") + "-" + detail.get("chujim");
                    %>
                    <span onclick="fullSk(this, '<%=p.get("id")%>', '<%=detail.get("pos")%>', '<%=param%>');" value="0" style="cursor:pointer;">
                        <%=detail.get("ability")+"/"+p.get("ability")%>
                    </span>
                </td>
                <td><%=p.get("tibu")%></td>
                <td>
                    <input type="text" id="<%=p.get("id")%>shemen" value="<%=detail.get("shemen")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("shemen").equals(detail.get("shemenm"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>shemen').value = <%=detail.get("shemenm")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("shemen")%>/<%=detail.get("shemenm")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>tupo" value="<%=detail.get("tupo")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("tupo").equals(detail.get("tupom"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>tupo').value = <%=detail.get("tupom")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("tupo")%>/<%=detail.get("tupom")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>duanqiu" value="<%=detail.get("duanqiu")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("duanqiu").equals(detail.get("duanqium"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>duanqiu').value = <%=detail.get("duanqium")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("duanqiu")%>/<%=detail.get("duanqium")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>chanqiu" value="<%=detail.get("chanqiu")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("chanqiu").equals(detail.get("chanqium"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>chanqiu').value = <%=detail.get("chanqium")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("chanqiu")%>/<%=detail.get("chanqium")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>chuanqiu" value="<%=detail.get("chuanqiu")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("chuanqiu").equals(detail.get("chuanqium"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>chuanqiu').value = <%=detail.get("chuanqium")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("chuanqiu")%>/<%=detail.get("chuanqium")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>sudu" value="<%=detail.get("sudu")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("sudu").equals(detail.get("sudum"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>sudu').value = <%=detail.get("sudum")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("sudu")%>/<%=detail.get("sudum")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>pujiu" value="<%=detail.get("pujiu")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("pujiu").equals(detail.get("pujium"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>pujiu').value = <%=detail.get("pujium")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("pujiu")%>/<%=detail.get("pujium")%>
                    </span>
                </td>
                <td>
                    <input type="text" id="<%=p.get("id")%>chuji" value="<%=detail.get("chuji")%>" size="2"/>
                    <%
                        color = "black";
                        if(detail.get("chuji").equals(detail.get("chujim"))) {
                            color = "red";
                        }
                    %>
                    <span onclick="document.getElementById('<%=p.get("id")%>chuji').value = <%=detail.get("chujim")%>;" style="cursor:pointer;color:<%=color%>">
                        <%=detail.get("chuji")%>/<%=detail.get("chujim")%>
                    </span>
                </td>
                <td>
                    <input type="button" value="存" onclick="savePlayer('<%=p.get("id")%>')" style="padding-left: 1px;padding-right: 1px;"/>
                </td>
            </tr>
            <tr class="row<%=i % 2 + 1%>" id="eq<%=p.get("id")%>" style="display:none;">
            </tr>
            <%
                }
            %>
        </table>
    <br/>
    当前阵容：<%=convert2TacticalDesc(currentTactical)%><br/>
    <%
        for(int i = 0; i < 11; i++) {
    %>
    <%=currentOnPlayers[i].split("∠")[3]%>－
    <%
        }
    %>
    <br/>
    <input type="button" value="球员换位" onclick="changeInd();"/>
    <br/>
    次数：<input type="text" size="3" id="times" value="1"/><br/>
    <br/>
    累计训练点：<%=sumTrainPoint%>
    <br/>
    <c:forEach items="${daoju}" var="d">
        <input type="radio" name="dj" value="${d[1]}"/>${d[0]}点，数量：${d[2]}<br/>
    </c:forEach>
    <br/>
    </body>
<script type="text/javascript">
    function savePlayer(id)
    {
//        UpPlayer1=793231*62*164*395*429*159*307*50*50*3
        var str = id + "*" +
                  document.getElementById(id + "shemen").value + "*" +
                  document.getElementById(id + "tupo").value + "*" +
                  document.getElementById(id + "duanqiu").value + "*" +
                  document.getElementById(id + "chanqiu").value + "*" +
                  document.getElementById(id + "chuanqiu").value + "*" +
                  document.getElementById(id + "sudu").value + "*" +
                  document.getElementById(id + "pujiu").value + "*" +
                  document.getElementById(id + "chuji").value + "*3";
        OgzqDwr.trainPlayer('<%=email%>', str, afterSavePlayer);
    }

    function afterSavePlayer(obj)
    {
        alert(obj);
    }

    function upgrade(id) {
        OgzqDwr.upgradePlayer('<%=email%>', id, afterSavePlayer);
    }

    function doFire(id) {
        if(confirm('解雇到战术中心请选是，返回一半训练点请选否')) {
            if(confirm('确定要解雇到战术中心？')) {
                OgzqDwr.fireToTactic('<%=email%>', id, afterSavePlayer);
            }
        } else {
            if(confirm('确定要解雇返还一半训练点？')) {
                OgzqDwr.fireToHalfBack('<%=email%>', id, afterSavePlayer);
            }
        }
    }

    function doTrain(id) {
        var times = document.getElementById('times').value;
        if(times < 1) {
            alert('至少使用一次');
            return;
        }
        var list = document.getElementsByName('dj');
        var itemId = '';
        for(var i = 0; i < list.length; i++) {
            if(list[i].checked) {
                itemId = list[i].value;
                break;
            }
        }
        if(itemId == '') {
            alert('请选择道具');
            return;
        }
        OgzqDwr.useTrainCard('<%=email%>', id, itemId, times, afterSavePlayer());
    }

    function changeInd() {
        var p1 = '';
        var p2 = '';
        var list = document.getElementsByName('changeIndex');
        for(var i = 0; i < list.length; i++) {
            if(list[i].checked) {
                if(p1 == '') {
                    p1 = list[i].value;
                } else if(p2 == '') {
                    p2 = list[i].value;
                } else {
                    alert('只能选择两个球员');
                    return;
                }
            }
        }
        if(p1 == '' || p2 == '') {
            alert('必须选择两个球员');
            return;
        }
        OgzqDwr.changePlayerPos('<%=email%>', p1, p2, afterSavePlayer);
    }

    function fullSk(obj, playerId, pos, str)
    {
        var value = obj.attributes['value'].value;
        obj.attributes['value'].value = parseInt(value,8) + 1;
        if(obj.attributes['value'].value > 2)  {
            obj.attributes['value'].value = 0;
        }
        var attris = str.split(";");
        if(value == 0) { //普通训练，除前锋/门将属性外训满
            if(pos != '门将') {
                document.getElementById(playerId + 'shemen').value = attris[0].split("-")[1];
                document.getElementById(playerId + 'tupo').value = attris[1].split("-")[1];
            }
            document.getElementById(playerId + 'duanqiu').value = attris[2].split("-")[1];
            document.getElementById(playerId + 'chanqiu').value = attris[3].split("-")[1];
            document.getElementById(playerId + 'chuanqiu').value = attris[4].split("-")[1];
            document.getElementById(playerId + 'sudu').value = attris[5].split("-")[1];
            if(pos == '门将') {
                document.getElementById(playerId + 'pujiu').value = attris[6].split("-")[1];
                document.getElementById(playerId + 'chuji').value = attris[7].split("-")[1];
            }
        } else if(value == 1) { //满训，所有能力满
            document.getElementById(playerId + 'shemen').value = attris[0].split("-")[1];
            document.getElementById(playerId + 'tupo').value = attris[1].split("-")[1];
            document.getElementById(playerId + 'duanqiu').value = attris[2].split("-")[1];
            document.getElementById(playerId + 'chanqiu').value = attris[3].split("-")[1];
            document.getElementById(playerId + 'chuanqiu').value = attris[4].split("-")[1];
            document.getElementById(playerId + 'sudu').value = attris[5].split("-")[1];
            document.getElementById(playerId + 'pujiu').value = attris[6].split("-")[1];
            document.getElementById(playerId + 'chuji').value = attris[7].split("-")[1];
        }else if(value == 2) { //恢复初始
            document.getElementById(playerId + 'shemen').value = attris[0].split("-")[0];
            document.getElementById(playerId + 'tupo').value = attris[1].split("-")[0];
            document.getElementById(playerId + 'duanqiu').value = attris[2].split("-")[0];
            document.getElementById(playerId + 'chanqiu').value = attris[3].split("-")[0];
            document.getElementById(playerId + 'chuanqiu').value = attris[4].split("-")[0];
            document.getElementById(playerId + 'sudu').value = attris[5].split("-")[0];
            document.getElementById(playerId + 'pujiu').value = attris[6].split("-")[0];
            document.getElementById(playerId + 'chuji').value = attris[7].split("-")[0];
        }
    }
</script>
</html>