<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-20
  Time: 17:44:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.MonitorTeamgameThread" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.saille.ogzq.loop.ClubBuffThread" %>
<%@ include file="../include/include.jsp"%>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
  </head>
  <body>
  <%!
      public List<String[]> sortClubs(Map<String, String> clubs) {
          List<String[]> ret = new ArrayList<String[]>();
          if(clubs != null) {
              for(String key : clubs.keySet()) {
                  boolean found = false;
                  for(int i = 0; i < ret.size(); i++) {
                      if(Integer.parseInt(ret.get(i)[1].split("-")[1]) < Integer.parseInt(clubs.get(key).split("-")[1])) {
                          ret.add(i, new String[]{key, clubs.get(key)});
                          found = true;
                          break;
                      }
                  }
                  if(!found) {
                      ret.add(new String[]{key, "" + clubs.get(key)});
                  }
              }
          }
          return ret;
      }

      public void sort(List<String> key, List<String[]> list, List<String> append, int start, int end) {
          if(start >= end) {
              return;
          }
          int pos = start;
          for(int i = pos + 1; i < end; i++) {
              boolean needSwap = false;
              if(list.get(i)[6].compareTo(list.get(pos)[6]) < 0) {
                  needSwap = true;
              }

              if(needSwap) {
                  String[] tmp = list.get(i);
                  String k = key.get(i);
                  String a = null;
                  if(append != null) {
                      a = append.get(i);
                  }
                  for(int m = i; m > pos; m--) {
                      list.set(m, list.get(m - 1));
                      key.set(m, key.get(m - 1));
                      if(append != null) {
                          append.set(m, append.get(m - 1));
                      }
                  }
                  list.set(pos, tmp);
                  key.set(pos, k);
                  if(append != null) {
                      append.set(pos, a);
                  }
              }
              pos = i;
          }
          sort(key, list, append, start, pos);
          sort(key, list, append, pos + 1, end);
          return;
      }

      String convertStatus(String status) {
          if("0".equals(status)) {
              return "";
          } else if("1".equals(status)) {
              return "比赛";
          } else if("2".equals(status)) {
              return "休战";
          } else if("3".equals(status)) {
              return "打残";
          }
          return "未知";
      }
  %>
  <%
      MonitorTeamgameThread instance = MonitorTeamgameThread.getInstance();
      Map<Double, String[]> first = instance.first;
      Map<String, String[]> my = instance.myPlaces;
      Map<String, String[]> our = instance.ourPlaces;
      List<Double> firstKey = new ArrayList<Double>();
      for(Double k : first.keySet()) {
          firstKey.add(k);
      }
      Collections.sort(firstKey);
      List<String[]> places = new ArrayList<String[]>();
      for(Double k : firstKey) {
          places.add(first.get(k));
      }
      request.setAttribute("firstKey", firstKey);
      request.setAttribute("first", places);

      Map<String, String[]> allPlaces = instance.places;
      request.setAttribute("allPlaces", allPlaces);

      Map<String, String> paizicount = new HashMap<String, String>(); //Map<会, 人数-牌子>
      int pageC = -2, paiziC = 0;
      for(String key : allPlaces.keySet()) {
          if(key.endsWith("-1") || key.equals("1-2") || key.equals("1-3")) {
              pageC++;
              paiziC += Integer.parseInt(allPlaces.get(key)[5]);
              String club = allPlaces.get(key)[9];
              if(paizicount.containsKey(club)) {
                  paizicount.put(club, (Integer.parseInt(paizicount.get(club).split("-")[0]) + 1) + "-" + (Integer.parseInt(paizicount.get(club).split("-")[1]) + Integer.parseInt(allPlaces.get(key)[5])));
              } else {
                  paizicount.put(club, "1-" + allPlaces.get(key)[5]);
              }
          }
      }
      List<String[]> clubstatus = sortClubs(paizicount);
      request.setAttribute("clubstatus", clubstatus);

      //小号
      List<String> myKey = new ArrayList<String>();
      List<String> myBuffFull = new ArrayList<String>();
      for(String k : my.keySet()) {
//          if(k.startsWith("txjcf")) {
//              continue;
//          }
          myKey.add(k);
      }
      Collections.sort(myKey);
      List<String[]> myPlaces = new ArrayList<String[]>();
      for(String k : myKey) {
          myPlaces.add(my.get(k));
          myBuffFull.add(ClubBuffThread.checkAllFull(k) ? "0" : "1");
      }
      sort(myKey, myPlaces, myBuffFull, 0, myPlaces.size());
      request.setAttribute("myKey", myKey);
      request.setAttribute("myBuffFull", myBuffFull);
      request.setAttribute("my", myPlaces);
      List<String> ids = IDUtils.GETIDS();
      List<String[]> allIds = new ArrayList<String[]>();
      for(int i = 0; i < ids.size(); i++) {
          if(myKey.contains(ids.get(i))) {
          } else {
//              if(!ids.get(i).startsWith("txjcf")) {
              allIds.add(new String[]{ids.get(i), IDUtils.getNick(ids.get(i))});
//              }
          }
      }

      //囧/nb
      List<String[]> ourIds = instance.getOurIds();
      List<String> ourKey = new ArrayList<String>();
      for(String id : our.keySet()) {
          ourKey.add(id);
      }
      Collections.sort(ourKey);
      List<String[]> ourPlaces = new ArrayList<String[]>();
      for(String k : ourKey) {
          if(our.containsKey(k)) {
              ourPlaces.add(our.get(k));
          }
      }
      sort(ourKey, ourPlaces, null, 0, ourPlaces.size());
      request.setAttribute("ourKey", ourKey);
      request.setAttribute("our", ourPlaces);

      //小号对手
      Map<String, List<String[]>> allRelativePlayers = new HashMap<String, List<String[]>>();
      for(int i = 0; i < myPlaces.size(); i++) {
          String pp = myPlaces.get(i)[6];
          int pos = (Integer.parseInt(pp.split("-")[0]) - 1) * 10 + Integer.parseInt(pp.split("-")[1]);
          List<String[]> relativePlayers = new ArrayList<String[]>();
          for(int j = -10; j <= 10; j++) {
              if(j == 0) {
                  continue;
              }
              if((pos + j) <= 0) {
                  continue;
              }
              int nextPage = (pos + j) / 10 + 1;
              int nextPos = (pos + j) % 10;
              if(nextPos == 0) {
                  nextPos = 10;
                  nextPage -= 1;
              }
              String[] player = allPlaces.get(nextPage + "-" + nextPos);
              if(player != null) {
                  relativePlayers.add(new String[]{player[0], player[2], j > 0 ? "打残" : "打", convertStatus(player[7]), player[5], nextPage + "-" + nextPos}); //id, name, 打/打残, status, gift, pos
              }
          }
          allRelativePlayers.put(myPlaces.get(i)[0], relativePlayers);
      }
      request.setAttribute("allRelativePlayers", allRelativePlayers);

      //囧/nb对手
      Map<String, List<String[]>> ourRelativePlayers = new HashMap<String, List<String[]>>();
      for(int i = 0; i < ourPlaces.size(); i++) {
          String pp = ourPlaces.get(i)[6];
          int pos = (Integer.parseInt(pp.split("-")[0]) - 1) * 10 + Integer.parseInt(pp.split("-")[1]);
          List<String[]> relativePlayers = new ArrayList<String[]>();
          for(int j = -10; j <= 10; j++) {
              if(j == 0) {
                  continue;
              }
              if((pos + j) <= 0) {
                  continue;
              }
              int nextPage = (pos + j) / 10 + 1;
              int nextPos = (pos + j) % 10;
              if(nextPos == 0) {
                  nextPos = 10;
                  nextPage -= 1;
              }
              String[] player = allPlaces.get(nextPage + "-" + nextPos);
              if(player != null) {
                  relativePlayers.add(new String[]{player[0], player[2], j > 0 ? "打残" : "打", convertStatus(player[7]), player[5], nextPage + "-" + nextPos}); //id, name, 打/打残, status, gift, pos
              }
          }
          ourRelativePlayers.put(ourPlaces.get(i)[0], relativePlayers);
      }
      request.setAttribute("ourRelativePlayers", ourRelativePlayers);

      request.setAttribute("all", allIds);
      String showXh = request.getParameter("show");
      if("1".equals(showXh)) {
          request.setAttribute("add", true);
      } else {
          request.setAttribute("add", false);
      }
  %>
  最后页：<%=instance.lastSize%>牌子比例：<%=pageC%>/<%=paiziC%>，<%=new DecimalFormat("#,##0.00%").format(((double)paiziC-6) / ((double)pageC - 1) - 1)%>
  <table width="100%">
      <tr valign="top">
          <td>
              <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                  <tr class="head">
                      <th>页数</th>
                      <th>号</th>
                      <th>球会</th>
                      <th>牌子</th>
                      <th>拿牌剩余时间</th>
                      <th>比赛状态</th>
                      <th>剩余时间</th>
                      <th>详细</th>
                  </tr>
                  <c:forEach items="${first}" var="a" varStatus="i">
                      <tr class="row${i.index % 2 + 1}" <c:if test="${a[1]=='1' || a[0] == '中国龙'}"> style="background-color:red;"</c:if>>
<td>
<fmt:formatNumber value="${firstKey[i.index]}" pattern="##0.#"/>
</td>
<td>${a[0]}</td><td>${a[1]}</td><td>${a[2]}</td><td>${a[3]}</td><td>${a[4]}</td><td>${a[5]}</td>
<td>
<c:if test="${firstKey[i.index] % 1 == 0}">
<input type="button" value="查看详细" onclick="detail(this);"/>
</c:if>
</td>
                      </tr>
<c:if test="${firstKey[i.index] % 1 == 0}">
<fmt:formatNumber value="${firstKey[i.index]}" var="p" pattern="##0.#"/>
<c:forEach begin="2" end="10" var="ps">
<tr class="row${i.index % 2 + 1}" style="display:none;">
<c:set var="pp" value="${p}-${ps}"/>
<td>${allPlaces[pp][1]}</td>
<td>${allPlaces[pp][2]}</td>
<td>${allPlaces[pp][9]}</td>
<td>${allPlaces[pp][5]}</td>
<td>${allPlaces[pp][6]}</td>
<td>
<c:choose>
<c:when test="${allPlaces[pp][7] == 0}">&nbsp;</c:when>
<c:when test="${allPlaces[pp][7] == 1}">比赛</c:when>
<c:when test="${allPlaces[pp][7] == 2}">休战</c:when>
<c:when test="${allPlaces[pp][7] == 3}">打残</c:when>
</c:choose>
</td>
<td>
${allPlaces[pp][8]}
</td>
<td>&nbsp;</td>
</tr>
</c:forEach>
</c:if>
                  </c:forEach>
              </table>
              监控：<br/>
              <table id="monitor" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                  <tr class="head">
                      <th>页数</th>
                      <th>号</th>
                      <th>球会</th>
                      <th>牌子</th>
                      <th>拿牌剩余时间</th>
                      <th>比赛状态</th>
                      <th>剩余时间</th>
                  </tr>
                  <%
                      Map<String, String> monitors = MonitorTeamgameThread.getInstance().place;
                      int index = 0;
                      for(String key : monitors.keySet()) {
                          index++;
                          if(StringUtils.isNotEmpty(monitors.get(key)) && MonitorTeamgameThread.getInstance().places.containsKey(monitors.get(key))) {
                              String[] values = MonitorTeamgameThread.getInstance().places.get(monitors.get(key));
                  %>
                        <tr class="row<%=index % 2 + 1%>">
                            <td>
                                <%=monitors.get(key)%>
                            </td>
                            <td>
                                <%=key%>
                            </td>
                            <td>
                                <%=values[9]%>
                            </td>
                            <td>
                                <%=values[5]%>
                            </td>
                            <td>
                                <%=values[6]%>
                            </td>
                            <td>
                                <%=MonitorTeamgameThread.convertGameStatus(values[7])%>
                            </td>
                            <td>
                                <%=values[8]%>
                            </td>
                        </tr>
                  <%
                          } else {
                  %>
                  <tr class="row<%=index % 2 + 1%>">
                      <td colspan="7">
                          <%=key%>
                      </td>
                  </tr>
                  <%
                          }
                      }
                  %>
              </table>
          </td>
          <td>
              <table border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                  <tr class="head">
                      <td>球会</td>
                      <td>牌子</td>
                      <td>球会</td>
                      <td>牌子</td>
                      <td>球会</td>
                      <td>牌子</td>
                      <td>球会</td>
                      <td>牌子</td>
                      <td>球会</td>
                      <td>牌子</td>
                  </tr>
                  <c:forEach items="${clubstatus}" var="s" varStatus="i">
                      <c:if test="${i.index % 5 == 0}">
                          <c:choose>
                            <c:when test="${i.index % 10 == 0}">
                              <tr class="row1">
                            </c:when>
                              <c:otherwise>
                                <tr class="row2">
                              </c:otherwise>
                          </c:choose>
                      </c:if>
                          <td>
                              <span style="color:red;font-weight:bold;">
                                  ${s[0]}
                              </span>
                          </td>
                          <td>${s[1]}</td>
                      <c:if test="${i.index % 5 == 4}">
                          </tr>
                      </c:if>
                  </c:forEach>
                  <c:if test="${(fn:length(clubstatus) % 5) != 0}">
                          <c:forEach begin="${fn:length(clubstatus) % 5}" end="4">
                              <td>&nbsp;</td><td>&nbsp;</td>
                          </c:forEach>
                    </tr>
                  </c:if>
              </table>
              <c:if test="${add}">
                  <table id="myinfo" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                      <tr class="head">
                          <th>email</th>
                          <th>号</th>
                          <th>球会</th>
                          <th>牌子</th>
                          <th>拿牌剩余时间</th>
                          <th>比赛状态</th>
                          <th>剩余时间</th>
                          <th>位置</th>
                          <th>&nbsp;</th>
                      </tr>
<c:forEach items="${my}" var="a" varStatus="i">
<tr class="row${i.index % 2 + 1}">
<td>
    <c:if test="${myBuffFull[i.index] == '1'}">
        <span style="color:red;">
    </c:if>
${myKey[i.index]}
        <c:if test="${myBuffFull[i.index] == '1'}">
            </span>
        </c:if>
</td>
<td>${a[0]}</td>
<td>${a[1]}</td>
<td>${a[2]}</td>
<td>${a[3]}</td>
<td>${a[4]}</td>
<td>${a[5]}</td>
<td>${a[6]}</td>
<td><input type="button" value="操作" onclick="displayFight(this);"/></td>
</tr>
<tr class="row${i.index % 2 + 1}" style="display:none;">
<td colspan="9">
<table border="0" cellpadding="1" cellspacing="1" bgcolor="white">
<c:forEach items="${allRelativePlayers[a[0]]}" var="ppp"><tr><td>${ppp[5]}</td><td>${ppp[1]}</td><td>${ppp[3]}</td><td>${ppp[4]}</td>
<td>
<c:if test="${ppp[3] == '' || ppp[3] == '打残'}">
    <input type="button" value="${ppp[2]}" onclick="doMatch('${ppp[2]}', '${myKey[i.index]}', '${ppp[0]}')"/>
</c:if>
</td></tr></c:forEach>
</table>
</td>
</tr>
</c:forEach>
<c:forEach items="${all}" var="id" varStatus="i">
<tr class="row${i.index % 2 + 1}">
<td colspan="9">
${id[0]}---${id[1]}
<input type="button" value="报名" onclick="signupTeamGame('${id[0]}');"/>
</td>
</tr>
</c:forEach>
                  </table>
<br/>
<!--囧/nb-->
<table id="ourinfo" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
                      <tr class="head">
                          <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">email</th>
                          <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">号</th>
                          <th onclick="resort(this, 2, false)" style="cursor:pointer;text-decoration:underline;">球会</th>
                          <th onclick="resort(this, 3, false)" style="cursor:pointer;text-decoration:underline;">牌子</th>
                          <th>拿牌剩余时间</th>
                          <th onclick="resort(this, 5, false)" style="cursor:pointer;text-decoration:underline;">比赛状态</th>
                          <th>剩余时间</th>
                          <th onclick="resort(this, 7, false)" style="cursor:pointer;text-decoration:underline;">位置</th>
                          <th>&nbsp;</th>
                      </tr>
<c:forEach items="${our}" var="a" varStatus="i">
<tr class="row${i.index % 2 + 1}">
<td>
${ourKey[i.index]}
</td>
<td>${a[0]}</td>
<td>${a[1]}</td>
<td>${a[2]}</td>
<td>${a[3]}</td>
<td>${a[4]}</td>
<td>${a[5]}</td>
<td>${a[6]}</td>
<td><input type="button" value="操作" onclick="displayOurFight(this);"/></td>
</tr>
<tr class="row${i.index % 2 + 1}" style="display:none;">
<td colspan="9">
<table border="0" cellpadding="1" cellspacing="1" bgcolor="white">
<c:forEach items="${ourRelativePlayers[a[0]]}" var="ppp"><tr><td>${ppp[5]}</td><td>${ppp[1]}</td><td>${ppp[3]}</td><td>${ppp[4]}</td>
<td>
<c:if test="${ppp[3] == '' || ppp[3] == '打残'}">
    <input type="button" value="${ppp[2]}" onclick="doMatch('${ppp[2]}', '${ourKey[i.index]}', '${ppp[0]}')"/>
</c:if>
</td></tr></c:forEach>
</table>
</td>
</tr>
</c:forEach>
                  </table>
              </c:if>
          </td>
      </tr>
  </table>
  <script type="text/javascript">
      function signupTeamGame(id)
      {
          OgzqDwr.signupTeamGame(id, afterDo);
      }

      function afterDo(obj)
      {
          alert(obj);
      }

      function detail(obj)
      {
          var needOpen = true;
          if(obj.value == '查看详细') {
              needOpen = true;
              obj.value = '收起详细';
          } else {
              needOpen = false;
              obj.value = '查看详细';
          }
          var idx = obj.parentNode.parentNode.rowIndex;
          var table = document.getElementById('info');
          for(var i = idx + 1; i < idx + 10; i++) {
              if(needOpen) {
                  table.rows[i].style.display = '';
              } else {
                  table.rows[i].style.display = 'none';
              }
          }
      }


      function displayOurFight(obj) {
          var idx = obj.parentNode.parentNode.rowIndex;
          var table = document.getElementById('ourinfo');
          for(var i = idx + 1; i < idx + 2; i++) {
              if(table.rows[i].style.display == 'none') {
                  table.rows[i].style.display = '';
              } else {
                  table.rows[i].style.display = 'none';
              }
          }
      }

      function displayFight(obj) {
          var idx = obj.parentNode.parentNode.rowIndex;
          var table = document.getElementById('myinfo');
          for(var i = idx + 1; i < idx + 2; i++) {
              if(table.rows[i].style.display == 'none') {
                  table.rows[i].style.display = '';
              } else {
                  table.rows[i].style.display = 'none';
              }
          }
      }

      function doMatch(matchtype, email, opponent) {
          OgzqDwr.doTeamGame(email, opponent, matchtype == '打残' ? '15' : '0', after);
      }

      function after(obj) {
          alert(obj);
      }
  </script>
  </body>
</html>