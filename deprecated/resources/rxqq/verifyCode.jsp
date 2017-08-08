<%@ page import="com.saille.rxqq.RxqqInstance" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.saille.rxqq.RxqqDwr" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-12-17
  Time: 1:14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/RxqqDwr.js'></script>
<html>
  <head><title>验证码</title></head>
  <script type="text/javascript">
      function v(id)
      {
          var code = document.getElementById(id).value;
          RxqqDwr.veryfyCode(id, code);
      }
  </script>
  <body>
  <%
      Map<String, String> m = RxqqInstance.verifyFile;
//      List<String> ids = new ArrayList<String>();
      List<String> ids = new RxqqDwr().generateIds(null);
      for (String s : m.keySet()) {
          if(!ids.contains(s)) {
              ids.add(s);
          }
      }
      for (String s : ids) {
          if(m.get(s) == null) {
              continue;
          }
  %>
  <%=s%>: <img src="<%=m.get(s)%>" alt="s"/>
  <input type="text" id="<%=s%>"/>
  <input type="button" onclick="v('<%=s%>')" value="验证"/>
  <br/>
  <br/>
  <%
      }
  %>
  </body>
<script type="text/javascript">
    document.getElementsByTagName("input")[0].focus();
</script>
</html>