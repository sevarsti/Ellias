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
  %>
  <%
      List<String> ids = new ArrayList<String>();
      String email = request.getParameter("email");
      if(email != null && !email.equals("")) {
          String[] emails = email.split("\\,");
          for(String e : emails) {
              ids.add(e.trim());
          }
//                    keys.add(email);
      } else {
          ids = IDUtils.GETIDS();
      }
      List<String[]> list = new ArrayList<String[]>();
      for(String id : ids) {
          System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
          HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTICAL);
          List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("type", "0"));
          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          String str = IDUtils.execute(id, pm);
          if(str.indexOf("DOCTYPE") >= 0) {
              list.add(new String[]{id, IDUtils.getNick(id)});
          } else {
              String currentTactical = str.split("⊥")[1];

              pm = new HttpPost(OgzqURL.URL + "/Coach/Coach.aspx");
              params = new ArrayList<NameValuePair>();
              params.add(new BasicNameValuePair("LoadCoach1", ""));
              pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
              String ret = IDUtils.execute(id, pm);
//4|瓜迪奥拉|5*
//1|米卢|7*
//2|希丁克|7*
//3|里皮|7*
//5|弗格森|1*
//6|穆里尼奥|1@
//1|0|2|1|米卢|7|9400|-999|0|44|30|18|10|0|0|0|0|0|0|0|0^
//1|0|2|2|希丁克|7|9400|-999|1|30|44|18|10|0|0|0|0|0|0|0|0^
//1|0|2|3|里皮|7|9400|-999|2|18|30|44|10|0|0|0|0|0|0|0|0^
//1|1|0|4|瓜迪奥拉|5|65000|70000|0|92|60|34|16|70|0|0|0|0|0|0|0^
//1|0|0|5|弗格森|1|0|5000|1|36|60|18|8|0|0|0|0|0|0|0|0^
//1|0|0|6|穆里尼奥|1|0|5000|2|18|36|60|8|0|0|0|0|0|0|0|0@
//@
//1
              ret = ret.split("@")[1];
              String[] coaches = ret.split("\\^");
              String[] add = new String[15];//email, nick, 当前，(教练等级，需要经验) * 6
              add[0] = id;
              add[1] = IDUtils.getNick(id);
              for(int i = 0; i < coaches.length; i++) {
                  String c = coaches[i];
                  String[] parts = c.split("\\|");
                  if(parts[1].equals("1")) {
                      add[2] = convert2TacticalDesc(currentTactical) + "<br/>" + parts[5] + parts[4];
                  }
                  if(parts[0].equals("0")) {
                      add[3 + i * 2] = null;
                      continue;
                  }
                  add[3 + i * 2] = parts[5];
                  add[3 + i * 2 + 1] = Integer.parseInt(parts[7]) < 0 ? "&nbsp;" : ((Integer.parseInt(parts[7]) - Integer.parseInt(parts[6])) + "");
//            1|0      |2|1|米卢    |7 |9400 |-999 |0   |44 |30 |18 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |2|2|希丁克  |7 |9400 |-999 |1   |30 |44 |18 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |2|3|里皮    |7 |9400 |-999 |2   |18 |30 |44 |10|0 |0   |0|0|0|0|0|0^
//            1|0      |1|4|瓜迪奥拉|5 |73618|70000|0   |92 |60 |34 |16|86|0   |0|0|0|0|0|0^
//            1|0      |0|5|弗格森  |2 |5987 |13000|1   |42 |68 |22 |10|10|0   |0|0|0|0|0|0^
//            1|1      |0|6|穆里尼奥|1 |3316 |5000 |2   |18 |36 |60 |8 |57|0   |0|0|0|0|0|0
//     是否聘用 current     name     lv exp   next  type att mid def       卫星
//            0 1       2 3 4        5  6     7     8    9   10  11  12 13 14
              }
              list.add(add);
          }
      }
      request.setAttribute("atts", list);
  %>
  <br/>
  <table width="100%" border="0" cellpadding="1" cellspacing="1">
      <tr>
          <td valign="top">
              <table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="full">
                  <tr class="head">
                      <th rowspan="2">登录名</th>
                      <th rowspan="2">游戏名</th>
                      <th rowspan="2" style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">当前教练</th>
                      <th colspan="2">米卢</th>
                      <th colspan="2">希丁克</th>
                      <th colspan="2">里皮</th>
                      <th colspan="2">瓜迪奥拉</th>
                      <th colspan="2">弗格森</th>
                      <th colspan="2">穆里尼奥</th>
                  </tr>
                  <tr class="head">
                      <c:forEach begin="0" end="5">
                          <th>等级</th>
                          <th>升级经验</th>
                      </c:forEach>
                  </tr>
                  <c:forEach items="${atts}" var="a" varStatus="i">
                      <tr class="row${i.index % 2 + 1}">
                          <c:choose>
                              <c:when test="${fn:length(a) > 2}">
                                  <c:forEach items="${a}" var="aa" varStatus="ii">
                                      <td>
                                          <c:if test="${ii.index == 0}">
                                              <input type="button" value="看" onclick="javascript:window.open('upCoach.jsp?email=${aa}');"/>
                                              <input type="button" value="D" onclick="removeRow(this);"/>
                                          </c:if>
                                          <c:if test="${ii.index > 2 && ii.index % 2 == 1}">
                                              <c:choose>
                                                  <c:when test="${empty aa}">
                                                      <input style="padding-left: 1px;padding-right: 1px;" type="button" value="雇" onclick="hire(this, '${a[0]}', '${(ii.index - 3) / 2 + 1}')"/>
                                                  </c:when>
                                                  <c:otherwise>
                                                      <input style="padding-left: 1px;padding-right: 1px;" type="button" value="用" onclick="showBag(this, '${a[0]}', '${(ii.index - 3) / 2 + 1}')"/>
                                                      <input style="padding-left: 1px;padding-right: 1px;" type="button" value="升" onclick="levelup('${a[0]}', '${(ii.index - 5) / 2 + 1}')"/>
                                                      <c:if test="${ii.index == 3}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '米卢')"/>
                                                      </c:if>
                                                      <c:if test="${ii.index == 5}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '希丁克')"/>
                                                      </c:if>
                                                      <c:if test="${ii.index == 7}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '里皮')"/>
                                                      </c:if>
                                                      <c:if test="${ii.index == 9}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '瓜迪奥拉')"/>
                                                      </c:if>
                                                      <c:if test="${ii.index == 11}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '弗格森')"/>
                                                      </c:if>
                                                      <c:if test="${ii.index == 13}">
                                                          <input style="padding-left: 1px;padding-right: 1px;" type="button" value="换" onclick="change('${a[0]}', '穆里尼奥')"/>
                                                      </c:if>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:if>
                                          ${aa}
                                      </td>
                                  </c:forEach>
                              </c:when>
                              <c:otherwise>
                                  <td>${a[0]}</td>
                                  <td>${a[1]}</td>
                                  <td colspan="13">激情训练</td>
                              </c:otherwise>
                          </c:choose>
                      </tr>
                  </c:forEach>
              </table>
          </td>
          <td valign="top" align="left">
              <span id='info'></span><br/>
              <table id="detail" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                  <tr class="head">
                      <td>名字</td>
                      <td>代码</td>
                      <td>数量</td>
                      <td>操作</td>
                  </tr>
              </table>
          </td>
      </tr>
  </table>
  </body>
<script type="text/javascript">
    var coachId, e;

    function change(email, name) {
        OgzqDwr.changeCoach(email, name, after);
    }

    function levelup(email, coachId) {
        OgzqDwr.coachLevelUp(email, parseInt(coachId, 10), after);
    }

    function showBag(obj, email, playerId) {
        coachId = parseInt(playerId, 10);
        e = email;
        var table = document.getElementById('full');
        for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].style.color = 'black';
        }
        obj.parentNode.parentNode.style.color = 'red';
        OgzqDwr.listCoachBags(email, "3", afterShowBag);
    }

    function hire(obj, email, playerId) {
        coachId = parseInt(playerId, 10);
        e = email;
        var table = document.getElementById('full');
        for(var i = 1; i < table.rows.length; i++) {
            table.rows[i].style.color = 'black';
        }
        obj.parentNode.parentNode.style.color = 'red';
        OgzqDwr.hireCoach(email, parseInt(playerId, 10), after);
    }

    function afterShowBag(list) {
        var table = document.getElementById('detail');
        for(var i = table.rows.length - 1; i > 0; i--) {
            table.deleteRow(i);
        }
        for(var i = 0; i < list.length; i++) {
//            m.put("itemId", parts[0]);
//            m.put("name", parts[3]);
//            m.put("count", parts[7]);
//            m.put("canuse", parts[8]);
//            m.put("itemCode", parts[11]);
            var row = table.insertRow(-1);
            row.className = 'row' + (2 - i % 2);
            var cell = row.insertCell(-1);
            cell.innerHTML = list[i]['name'];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i]['itemCode'];
            cell = row.insertCell(-1);
            cell.innerHTML = list[i]['count'];
            cell = row.insertCell(-1);
            cell.innerHTML = "<input type=\"button\" value=\"用\" onclick=\"useCoachItem('" + e + "', '" + list[i]['itemCode'] + "');\"/>" +
                    "<input type=\"button\" value=\"合\" onclick=\"combine('" + e + "', '" + list[i]['itemId'] + "');\"/>";
        }
    }

    function combine(email, itemId) {
        OgzqDwr.combineCoach(email, itemId, after);
    }

    function useCoachItem(email, itemCode) {
        OgzqDwr.useCoachItem(email, coachId, itemCode, after);
    }

    function after(obj) {
        alert(obj);
    }
    function removeRow(obj) {
        var table = document.getElementById('full');
        table.deleteRow(obj.parentNode.parentNode.rowIndex);

        for(var i = 2; i < table.rows.length; i++) {
            table.rows[i].className = 'row' + (2 - (i % 2));
        }
    }
</script>
</html>