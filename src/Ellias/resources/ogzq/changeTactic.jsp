<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-20
  Time: 17:44:33
  To change this template use File | Settings | File Templates.
  变阵
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.MonitorTeamgameThread" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.saille.ogzq.TopChallengeMonitorThread" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
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
      public String getPosDesc(String in) {
          if(StringUtils.isEmpty(in)) {
              return "null";
          }
          if("1".equals(in)) {
              return "门将";
          }
          if("2".equals(in)) {
              return "后卫";
          }
          if("3".equals(in)) {
              return "中场";
          }
          if("4".equals(in)) {
              return "前锋";
          }
          return "未知";
      }

      public int getMaxLength(String[] players) {
          int ret = 0;
          for(String p : players) {
              String name = p.split("[*]")[1];
              int len = name.getBytes().length;
              ret = Math.max(ret, len);
          }
          return ret + 1;
      }

      public String getName(String in, int len) {
          StringBuffer ret = new StringBuffer(in);
          for(int i = 0; i < len - in.getBytes().length; i++) {
              ret.append("&nbsp;");
          }
          return ret.toString();
      }
  %>
<%
    String email = request.getParameter("email");
    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TRAINING);
    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("type", "0"));
    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
    String ret = IDUtils.execute(email, pm);
    ret = ret.substring(ret.indexOf("^") + 1);
    String[] players = ret.split("[|]");
    int max = getMaxLength(players);
%>
  <table border=0>
      <tr>
          <td>
              <select id="full" size="25" ondblclick="addPlayer(this);">
                  <%
                      for(String p : players) {
                          String[] sub = p.split("[*]");
                          String id = sub[0];
                          String place = sub[5];
                          String name = sub[1];
                          String ability = sub[6];
                  %>
                  <option value="<%=id%>"><%=id + " " + getName(name, max) + " " + getPosDesc(place) + " " + ability%></option>
                  <%
                      }
                  %>
              </select>
          </td>
          <td>
              <select id="now" size="25" ondblclick="dropPlayer(this);">

              </select>
          </td>
      </tr>
  </table>
<script type="text/javascript">
    function addPlayer(obj) {
        var item = obj.options[obj.selectedIndex].value;
        var newTable = document.getElementById('now');
        var idx = newTable.options.length;
        newTable.options[idx] = new Option();
        newTable.options[idx].value = obj.options[obj.selectedIndex].value;
        newTable.options[idx].text  = obj.options[obj.selectedIndex].text;
        obj.options[obj.selectedIndex] = null;
    }

    function dropPlayer(obj) {
        var item = obj.options[obj.selectedIndex].value;
        var newTable = document.getElementById('full');
        var idx = newTable.options.length;
        newTable.options[idx] = new Option();
        newTable.options[idx].value = obj.options[obj.selectedIndex].value;
        newTable.options[idx].text  = obj.options[obj.selectedIndex].text;
        obj.options[obj.selectedIndex] = null;
    }
</script>
</body>
</html>