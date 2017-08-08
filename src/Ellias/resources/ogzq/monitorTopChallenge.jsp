<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-20
  Time: 17:44:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.MonitorTeamgameThread" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.saille.ogzq.TopChallengeMonitorThread" %>
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
      public void sort(List<String> key, List<String[]> list, int start, int end) {
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
                  for(int m = i; m > pos; m--) {
                      list.set(m, list.get(m - 1));
                      key.set(m, key.get(m - 1));
                  }
                  list.set(pos, tmp);
                  key.set(pos, k);
              }
              pos = i;
          }
          sort(key, list, start, pos);
          sort(key, list, pos + 1, end);
          return;
      }
  %>
  <%
      TopChallengeMonitorThread instance = TopChallengeMonitorThread.getInstance();
      List<String[]> places = instance.places;
      request.setAttribute("places", places);
  %>
  当前挑战的email：<%=instance.email%><br/>
  当前外挂剩余要踢场次：<%=TopChallengeMonitorThread.restChangci%><br/>
  活动剩余场次：<%=TopChallengeMonitorThread.restTime%><br/>
  增加挑战：<input type="text" id="changci" value="20" size="3" class="inputbox"/>
  <input type="button" value="增加" onclick="addChangci();"/><br/>
  <input type="button" value="关闭挑战" onclick="closeTopChallenge();"/><br/>
      <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
          <tr class="head">
              <th>名次</th>
              <th>号</th>
              <th>进球数</th>
          </tr>
          <c:forEach items="${places}" var="p" varStatus="i">
              <tr class="row${i.index % 2 + 1}">
<td>
${i.index + 1}
</td>
<td>${p[0]}</td>
<td>${p[1]}</td>
              </tr>
</c:forEach>
      </table>
  <script type="text/javascript">
      function addChangci(obj) {
          var cc = document.getElementById('changci').value;
          if(cc == '') {
              alert('请输入场次');
          }
          OgzqDwr.addTopChallenge(parseInt(cc, 10), after);
      }

      function after(obj) {
          alert(obj);
      }

      function closeTopChallenge() {
          OgzqDwr.closeTopChallenge(after);
      }
  </script>
  </body>
</html>