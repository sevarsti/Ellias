<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-20
  Time: 17:44:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.TopChallengeThread" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
  <%
      TopChallengeThread instance = TopChallengeThread.getInstance();

      String msg = null;
      String email = request.getParameter("email");
      String operate = request.getParameter("op");
      if(StringUtils.isNotEmpty(email)) {
          if("add".equals(operate)) {
              msg = instance.addSingle(email);
          } else if("delete".equals(operate)) {
              msg = instance.deleteSingle(email);
          }
      }

      List<String[]> ids = instance.emails;
      List<String[]> results = new ArrayList<String[]>();
      for(String[] id : ids) {
          String[] r = new String[4];
          r[0] = id[0];
          r[1] = id[2];
          r[2] = id[3];
          r[3] = instance.count.get(id[0]).intValue() + "";
          results.add(r);
      }
      request.setAttribute("results", results);
  %>
      <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
          <tr class="head">
              <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 0, false)">邮箱</th>
              <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 1, false)">号</th>
              <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 2, false)">球会</th>
              <th style="cursor:pointer;text-decoration:underline;" onclick="resort(this, 3, true)">线程数</th>
              <th>操作</th>
          </tr>
          <c:forEach items="${results}" var="r" varStatus="i">
              <tr class="row${i.index % 2 + 1}">
                  <c:forEach items="${r}" var="rr">
                      <td>${rr}</td>
                  </c:forEach>
                  <td>
                      <input type="button" value="增" onclick="doadd('${r[0]}')"/>
                      <input type="button" value="减" onclick="dodelete('${r[0]}')"/>
                  </td>
              </tr>
          </c:forEach>
      </table>
  <form action="" method="post">
      <input type="hidden" id="email" name="email" value=""/>
      <input type="hidden" id="op" name="op" value=""/>
  </form>
  </body>
<script type="text/javascript">
    <%=msg == null ? "" : "alert('" + msg + "');"%>

    function doadd(email) {
        document.getElementById('email').value = email;
        document.getElementById('op').value = 'add';
        document.forms[0].submit();
//        var location = document.location.href;
//        if(location.indexOf("?") > 0) {
//            location = location.substring(0, location.indexOf("?"));
//        }
//        location = location + "?email=" + email + "&op=add";
////        alert(location);
//        document.location = location;
    }

    function dodelete(email) {
        document.getElementById('email').value = email;
        document.getElementById('op').value = 'delete';
        document.forms[0].submit();
//        var location = document.location.href;
//        if(location.indexOf("?") > 0) {
//            location = location.substring(0, location.indexOf("?"));
//        }
//        location = location + "?email=" + email + "&op=delete";
////        alert(location);
//        document.location = location;
    }
</script>
</html>