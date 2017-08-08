<%@ page import="com.saille.bwdl.service.BwdlService" %>
<%@ page import="com.saille.bwdl.service.ServiceHelper" %>
<%@ page import="com.saille.bwdl.Version" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-19
  Time: 19:05:09
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <%
      BwdlService service = ServiceHelper.getBwdlService();
      List<Version> versions = service.findAllVersions();
  %>
    <table border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>id</th>
            <th>名字</th>
            <th colspan="4">操作</th>
        </tr>
        <%
            for(int i = 0; i < versions.size(); i++) {
        %>
        <tr class="row<%=i % 2 + 1%>">
            <td align="right">
                <%=versions.get(i).getId()%>
            </td>
            <td>
                <%=versions.get(i).getName()%>
            </td>
            <td>
                <a href="shili.do?method=list&version=<%=versions.get(i).getId()%>">势力</a>
            </td>
            <td>
                <a href="wuqi.do?method=list&version=<%=versions.get(i).getId()%>">武器</a>
            </td>
            <td>
                <a href="fangju.do?method=list&version=<%=versions.get(i).getId()%>">防具</a>
            </td>
            <td>
                <a href="wujiang.do?method=list&version=<%=versions.get(i).getId()%>">武将</a>
            </td>
            <td>
                <a href="chengshi.do?method=list&version=<%=versions.get(i).getId()%>">城市</a>
            </td>
        </tr>
        <%
            }
        %>
    </table>
  <html:form action="/bwdl/version.do">
      <input type="hidden" name="method" value="save"/>
      新剧本名：<html:text property="name"/>
      <html:submit value="保存" styleClass="otterbtn"/>
  </html:form>
  </body>
</html>