<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-9-9
  Time: 13:19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OperationUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.net.URLEncoder" %>
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
//进攻解说信息（-1：未获得） @ 组织解说信息（-1：未获得） @ 防守解说信息（-1：未获得）  （或 宋世雄信息）
//信息：是否开启（1、-1）&解说名*等级*星级 & 升星(升级)所需经验值*当前经验值 & 升星(升级)所需潜力卡Itemcode*所需潜力卡数*已有该卡数量 & 升星(升级)所需银币*已有银币数 & 开启后或下一星(等)级可提升实力
// 1&徐阳  *1*1& 2000*0& 5401*1*1&  1000*4679514& 500@
//-1&娄一辰*0*0&    0*0&    0*0*0&2 0000*4679514& 600@
//-1&何辛  *0*0&    0*0&    0*0*0& 20000*4679514& 600
        List<String> ids = new ArrayList<String>();
        String email = request.getParameter("email");
        if(email != null && !email.equals("")) {
            String[] emails = email.split("\\,");
            for(String e : emails) {
                ids.add(e);
            }
//                    keys.add(email);
        } else {
            ids = IDUtils.GETIDS();
        }
        List<Map<String, String>> results = new ArrayList<Map<String, String>>();
        for(String id : ids) {
            System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
            HttpPost pm = new HttpPost(OgzqURL.URL + "/JieShuo.aspx");
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("request", "Load"));
            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
            String s = IDUtils.execute(id, pm);
//            out.println(s);
            String[] infos = s.split("@");
            Map<String, String> map = new HashMap<String, String>();
            map.put("email", id);
            map.put("nick", IDUtils.getNick(id));
            for(int i = 0; i < 3; i++) {
                String[] info = infos[i].split("\\&");
                map.put("tactic" + i, info[0]);
                map.put("lv" + i, info[1].split("\\*")[1]);
                map.put("star" + i, info[1].split("\\*")[2]);
                map.put("exp" + i, info[2].split("\\*")[0]);
                map.put("curexp" + i, info[2].split("\\*")[1]);
                if(Integer.parseInt(info[2].split("\\*")[1]) >= Integer.parseInt(info[2].split("\\*")[0])) {
                    map.put("expfull" + i, "1");
                }
                if(Integer.parseInt(info[2].split("\\*")[1]) >= Integer.parseInt(info[2].split("\\*")[0]) &&
                        Integer.parseInt(info[3].split("\\*")[2]) >= Integer.parseInt(info[3].split("\\*")[1])) {
                    map.put("canup" + i, "1");
                }
                map.put("itemcode" + i, info[3].split("\\*")[0]);
                map.put("itemcount" + i, info[3].split("\\*")[2] + "/" + info[3].split("\\*")[1]);
                map.put("silver" + i, info[4].split("\\*")[0]);
                map.put("silver", info[4].split("\\*")[1]);
//                map.put("silver" + i, info[4].split("\\*")[1] + "/" + info[4].split("\\*")[0]);
                map.put("ability" + i, info[5]);
            }
            results.add(map);
        }

    %>
    <body>
        <table class="frame" id = "info" width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <!--<th rowspan="2">-->
                    <!--email-->
                <!--</th>-->
                <th rowspan="2">
                    nick
                </th>
                <th rowspan="2">
                    银币
                </th>
                <th colspan="7">徐阳</th>
                <th colspan="7">娄一辰</th>
                <th colspan="7">何辛</th>
            </tr>
            <tr class="head">
                <c:forEach begin="0" end="2">
                    <!--<td>开启</td>-->
                    <td>等级</td>
                    <td>星级</td>
                    <!--<td>升级所需经验</td>-->
                    <td>经验</td>
                    <td>升级道具</td>
                    <td>道具</td>
                    <td>银币</td>
                    <td>实力</td>
                </c:forEach>
            </tr>
            <c:forEach items="<%=results%>" var="m" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <%--<td>${m['email']}</td>--%>
                    <td>
                        <input type="button" onclick="autoUpJieshuo('${m['email']}');" value="自动升级"/>
                        ${m['nick']}
                    </td>
                    <td>${m['silver']}</td>

                    <%--<td>${m['tactic0']}</td>--%>
                    <c:choose>
                        <c:when test="${m['tactic0'] == '1'}">
                            <td>${m['lv0']}</td>
                            <td>${m['star0']}</td>
                            <td>
                                <c:if test="${m['expfull0'] != '1'}">
                                    <input type="button" value="用" onclick="listBag(this, '${m['email']}', '1')"/>
                                </c:if>
                                <c:if test="${m['canup0'] == '1'}">
                                    <input type="button" value="升级" onclick = "levelup('${m['email']}', '1')"/>
                                </c:if>
                                <c:if test="${m['canup0'] != '1' && m['expfull0'] == '1'}">
                                    <input type="button" value="合" style="color:red;" onclick="javascript:window.open('jieshuocombine.jsp?email=${m['email']}')"/>
                                </c:if>
                                ${m['curexp0']}/${m['exp0']}
                            </td>
                            <td>${m['itemcode0']}</td>
                            <td>${m['itemcount0']}</td>
                            <td>${m['silver0']}</td>
                            <td>${m['ability0']}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="7"><input type="button" value="开启" onclick="openjieshuo('${m['email']}', '1', '20000')"/></td>
                        </c:otherwise>
                    </c:choose>
                    <%--<td>${m['tactic1']}</td>--%>
                    <c:choose>
                        <c:when test="${m['tactic1'] == '1'}">
                            <td>${m['lv1']}</td>
                            <td>${m['star1']}</td>
                            <td>
                                <c:if test="${m['expfull1'] != '1'}">
                                    <input type="button" value="用" onclick="listBag(this, '${m['email']}', '2')"/>
                                </c:if>
                                    <c:if test="${m['canup1'] == '1'}">
                                        <input type="button" value="升级" onclick = "levelup('${m['email']}', '2')"/>
                                </c:if>
                                <c:if test="${m['canup1'] != '1' && m['expfull1'] == '1'}">
                                    <input type="button" value="合" style="color:red;" onclick="javascript:window.open('jieshuocombine.jsp?email=${m['email']}')"/>
                                </c:if>
                                ${m['curexp1']}/${m['exp1']}
                            </td>
                            <td>${m['itemcode1']}</td>
                            <td>${m['itemcount1']}</td>
                            <td>${m['silver1']}</td>
                            <td>${m['ability1']}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="7"><input type="button" value="开启" onclick="openjieshuo('${m['email']}', '2', '20000')"/></td>
                        </c:otherwise>
                    </c:choose>
                    <%--<td>${m['tactic2']}</td>--%>
                    <c:choose>
                        <c:when test="${m['tactic2'] == '1'}">
                            <td>${m['lv2']}</td>
                            <td>${m['star2']}</td>
                            <td>
                                <c:if test="${m['expfull2'] != '1'}">
                                    <input type="button" value="用" onclick="listBag(this, '${m['email']}', '3')"/>
                                </c:if>
                                <c:if test="${m['canup2'] == '1'}">
                                    <input type="button" value="升级" onclick = "levelup('${m['email']}', '3')"/>
                                </c:if>
                                <c:if test="${m['canup2'] != '1' && m['expfull2'] == '1'}">
                                    <input type="button" value="合" style="color:red;" onclick="javascript:window.open('jieshuocombine.jsp?email=${m['email']}')"/>
                                </c:if>
                                ${m['curexp2']}/${m['exp2']}
                            </td>
                            <td>${m['itemcode2']}</td>
                            <td>${m['itemcount2']}</td>
                            <td>${m['silver2']}</td>
                            <td>${m['ability2']}</td>
                        </c:when>
                        <c:otherwise>
                            <td colspan="7"><input type="button" value="开启" onclick="openjieshuo('${m['email']}', '3', '20000')"/></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <table id='items' class="frame" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
            <tr class="head">
                <td>名字</td>
                <td>code</td>
                <td>数量</td>
                <td>操作</td>
            </tr>
        </table>
    </body>
<script type="text/javascript">
    var email, jieshuo;

String.prototype.startWith=function(str){
  var reg=new RegExp("^"+str);
  return reg.test(this);
}

String.prototype.endWith=function(str){
  var reg=new RegExp(str+"$");
  return reg.test(this);
}

    function openjieshuo(email, tactic, silver)
    {
        OgzqDwr.openjieshuo(email, tactic, after);
    }

    function levelup(email, index)
    {
        OgzqDwr.jieshuoLevelup(email, index, after);
    }

    function autoUpJieshuo(email)
    {
        OgzqDwr.autoUpJieshuo(email, after);
    }

    function after(obj)
    {
        alert(obj);
    }

    function listBag(obj, inputemail, index)
    {
        var table = document.getElementById('info');
        for(var i = 2; i < table.rows.length; i++) {
            table.rows[i].style.color = 'black';
        }
        obj.parentNode.parentNode.style.color = 'red';

        email = inputemail;
        jieshuo = index;
        OgzqDwr.listCoachBags(inputemail, "5", afterShowBag);
    }

    function afterShowBag(list)
    {
        var table = document.getElementById('items');
        for(var i = table.rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
        for(var i = 0; i < list.length; i++) {
            var row = table.insertRow(-1);
            row.className = 'row' + (2 - i % 2);
            var cell = row.insertCell(-1);
            cell.innerHTML = list[i]['name'];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i]['itemCode'];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i]['count'];
            if(list[i]['itemCode'].startWith("55"))
            {
                cell = row.insertCell(-1);
                cell.innerHTML = "<input type=\"button\" value=\"用\" onclick=\"useExpItem('" + list[i]['itemCode'] + "');\"/>";
            }
            else
            {
                cell = row.insertCell(-1);
                cell.innerHTML = '&nbsp;';
            }
        }
//        alert(list);
    }

    function useExpItem(itemcode)
    {
        OgzqDwr.useJieshuoItem(email, itemcode, jieshuo, after);
    }
</script>
</html>