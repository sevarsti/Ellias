<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-12-20
  Time: 1:11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head>
      <title>Simple jsp page</title>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
  </head>
    <body>
        <html:form action="/msn.do">
            <input type="hidden" name="method" value="logon"/>
            <html:text property="username"/>
            <html:password property="password"/>
            <input type="submit"/>
        </html:form>
    </body>
</html>