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
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-17
  Time: 1:24:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
      <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
  </head>
  <body>
  <%
      String email = request.getParameter("email");
      List<String> ids;
      if(StringUtils.isEmpty(email)) {
          ids = IDUtils.GETIDS();
      } else {
          ids = new ArrayList<String>();
          ids.add(email);
      }
      List<String[]> list = new ArrayList<String[]>();
      for(String id : ids) {
          System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
          HttpPost pm = new HttpPost(OgzqURL.URL + "/SteelPower.aspx");
          List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("SJKLoad1", "0"));
          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          String ret = IDUtils.execute(id, pm);
          //0*
          // 5164|2|Super/10075.png|布雷默|6|630640|0|0|10075*
          // 5188|3|Super/10076.png|马特乌斯|6|642004|0|0|10076*
          // 4275|4|Super/10077.png|克林斯曼|6|686572|0|0|10077

          // 13*
          // 2917|3|Super/10041.png|里杰卡尔德|6|592570|13|1*
          // 2859|4|Super/10043.png|范·巴斯滕|6|610252|13|1*
          // 3188|2|Super/10045.png|古力特    |6|604384|13|1

          // 12*
          // 2862|3|Super/10041.png|里杰卡尔德|6|680620|12|0*
          // 3050|4|Super/10043.png|范·巴斯滕|6|611722|12|0*
          // 3796|2|Super/10045.png|古力特|6|608488|12|0
//          function CreatePlayer(average, position, head, playerName, level, playerid, left, top, id, playerdataid) {
//          CreatePlayer(List[0], List[1], List[2], List[3], List[4], null, 0, 0, 'divsjk' + i
          String[] parts = ret.split("\\*");
          IDUtils.IDInfos.get(id).put("sjk", parts[0]);
          String[] l = new String[]{
                  id,
                  IDUtils.getNick(id),
                  IDUtils.IDInfos.get(id).get("shili"),
                  IDUtils.IDInfos.get(id).get("silver"), parts[0],
                  parts[1].split("\\|")[6], parts[2].split("\\|")[6], parts[3].split("\\|")[6],
                  parts[1].split("\\|")[5], parts[2].split("\\|")[5], parts[3].split("\\|")[5]
          };
          list.add(l);
      }
      request.setAttribute("atts", list);
  %>
  <br/>
  <table width="100%" border="0" cellpadding="1" cellspacing="1">
      <tr>
          <td valign="top">
              <table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="full">
                  <tr class="head">
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">登录名</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">游戏名</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, true)">实力</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">银币</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 4, true)">等级</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 5, false)">布雷默</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 6, false)">马特乌斯</th>
                      <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 7, false)">克林斯曼</th>
                  </tr>
                  <c:forEach items="${atts}" var="a" varStatus="i">
                      <tr class="row${i.index % 2 + 1}">
                          <c:forEach begin="0" end="7" var="ii">
                              <c:set var="aa" value="${a[ii]}"/>
                              <td>
                                  <c:if test="${ii > 4 && aa < 13}">
                                      <input type="button" value="升" onclick="beforeUp(this, '${a[0]}', '${a[ii+3]}')"/>
                                  </c:if>
                                  ${aa}
                              </td>
                          </c:forEach>
                      </tr>
                  </c:forEach>
              </table>
          </td>
          <td valign="top" align="left">
              <span id='info'></span><br/>
              <table id="detail" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                  <tr class="head">
                      <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">姓名</th>
                      <th onclick="resort(this, 1, true)" style="cursor:pointer;text-decoration:underline;">位置</th>
                      <th onclick="resort(this, 2, true)" style="cursor:pointer;text-decoration:underline;">品质</th>
                      <th onclick="resort(this, 3, true)" style="cursor:pointer;text-decoration:underline;">实力</th>
                      <th>操作</th>
                  </tr>
              </table>
          </td>
      </tr>
  </table>
  </body>
<script type="text/javascript">
    function beforeUp(obj, email, playerId) {
        var table = document.getElementById('full');
        for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].style.color = 'black';
        }
        obj.parentNode.parentNode.style.color = 'red';
        OgzqDwr.steelpowerUpCost(email, playerId, showUp);
    }

    function showUp(list) {
        //email, 三剑客id，三剑客姓名，消耗球员实力，消耗银币
        document.getElementById('info').innerHTML = list[0][0] + "的" + list[0][2] + "升级需要" + list[0][4] + '银币，需要球员实力' + list[0][3];
        var table = document.getElementById('detail');
        for(var i = table.rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
        for(var i = 1; i < list.length; i++) {
            //球员id，球员名字，位置，品质，实力
            var row = table.insertRow(-1);
            row.className = 'row' + (2 - i % 2);
            var cell = row.insertCell(-1);
            cell.innerHTML = list[i][1];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i][2];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i][3];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i][4];
            cell = row.insertCell(-1);
            cell.innerHTML = "<input type=\"button\" value=\"升\" onclick=\"doUp('" + list[0][0] + "', '" + list[0][1] + "', '" + list[i][0] + "');\"/>";
        }
    }

    function doUp(email, player, costPlayer) {
        OgzqDwr.steelpowerUp(email, player, costPlayer, after);
    }

    function after(obj) {
        alert(obj);
    }
</script>
</html>