<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-11-29
  Time: 13:52:29
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../include/include.jsp"%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>Simple jsp page${voteForm.index}</title>
  </head>
  <body>
  <img src="../${voteForm.index}.jpg" alt=""/>
  <html:form action="/vote.do?method=firstStep">
      <input type="text" name="verify" id="verify"/>
      <input type="submit" value="vote"/>
  </html:form>
  current: ${voteForm.names[voteForm.index][0]},${voteForm.names[voteForm.index][1]},${voteForm.names[voteForm.index][2]}
  <br/>
  last: ${voteForm.names[voteForm.index-1][0]},${voteForm.names[voteForm.index-1][1]},${voteForm.names[voteForm.index-1][2]}
  <br/>
  ${voteForm.logs[voteForm.index-1]}
  <table border="1">
      <tr>
          <td>name</td>
          <td>id</td>
          <td>phone</td>
          <td>result</td>
      </tr>
      <c:forEach items="${voteForm.names}" var="name" varStatus="i">
          <tr>
              <td>${name[0]}</td>
              <td>${name[1]}</td>
              <td>${name[2]}</td>
              <td>${voteForm.logs[i.index]}</td>
          </tr>
      </c:forEach>
  </table>
  </body>
<script type="text/javascript">
    document.getElementById('verify').focus();
  </script>
</html>