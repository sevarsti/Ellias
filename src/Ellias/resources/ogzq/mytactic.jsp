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
          HttpPost pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
          List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("type", "9"));
          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          String ret = IDUtils.execute(id, pm);
          if(ret.indexOf("DOCTYPE") >= 0) {
              list.add(new String[]{id, IDUtils.getNick(id)});
          } else {
              String[] v = ret.split("[*]");
              String[] l = new String[v.length + 2];
              l[0] = id;
              l[1] = IDUtils.getNick(id);
              for(int i = 0; i < 13; i++) {
                  l[i + 3] = v[i];
              }
              for(int i = 14; i < 17; i++) {
                  l[i + 2] = v[i];
              }
//          for(int i = 0; i < v.length; i++) {
//              l[i + 3] = v[i];
//          }

              pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
              params = new ArrayList<NameValuePair>();
              params.add(new BasicNameValuePair("type", "0"));
              pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
              ret = IDUtils.execute(id, pm);
              String currentTactical = ret.split("⊥")[1];
              l[2] = convert2TacticalDesc(currentTactical);

              list.add(l);
          }
      }
      request.setAttribute("atts", list);
  %>
  <br/>
  <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
      <tr class="head">
          <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">登录名</th>
          <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">游戏名</th>
          <th onclick="resort(this, 2, false)" style="cursor:pointer;text-decoration:underline;">当前<br/>阵容</th>
          <th onclick="resort(this, 3, false)" style="cursor:pointer;text-decoration:underline;">低攻</th>
          <th onclick="resort(this, 4, false)" style="cursor:pointer;text-decoration:underline;">低组</th>
          <th onclick="resort(this, 5, false)" style="cursor:pointer;text-decoration:underline;">低防</th>
          <th onclick="resort(this, 6, false)" style="cursor:pointer;text-decoration:underline;">高攻</th>
          <th onclick="resort(this, 7, false)" style="cursor:pointer;text-decoration:underline;">高组</th>
          <th onclick="resort(this, 8, false)" style="cursor:pointer;text-decoration:underline;">高防</th>
          <th onclick="resort(this, 9, true)" style="cursor:pointer;text-decoration:underline;">低攻经验</th>
          <th onclick="resort(this, 10, true)" style="cursor:pointer;text-decoration:underline;">低组经验</th>
          <th onclick="resort(this, 11, true)" style="cursor:pointer;text-decoration:underline;">低防经验</th>
          <th onclick="resort(this, 12, true)" style="cursor:pointer;text-decoration:underline;">高攻经验</th>
          <th onclick="resort(this, 13, true)" style="cursor:pointer;text-decoration:underline;">高组经验</th>
          <th onclick="resort(this, 14, true)" style="cursor:pointer;text-decoration:underline;">高防经验</th>
          <th onclick="resort(this, 15, true)" style="cursor:pointer;text-decoration:underline;">当前积分</th>
          <th onclick="resort(this, 16, false)" style="cursor:pointer;text-decoration:underline;">攻兼容</th>
          <th onclick="resort(this, 17, false)" style="cursor:pointer;text-decoration:underline;">组兼容</th>
          <th onclick="resort(this, 18, false)" style="cursor:pointer;text-decoration:underline;">防兼容</th>
          <th>变阵</th>
      </tr>
      <c:forEach items="${atts}" var="a" varStatus="i">
          <tr class="row${i.index % 2 + 1}">
              <c:choose>
                  <c:when test="${fn:length(a) > 2}">
                      <c:forEach items="${a}" var="aa" varStatus="i">
                          <c:choose>
                              <c:when test="${i.index > 2 && i.index < 9}">
                                  <td>
                                      <fmt:parseNumber value="${aa}" var="as" pattern="0"/>
                                      <c:choose>
                                          <c:when test="${as < 0}">
                                              ×
                                          </c:when>
                                          <c:otherwise>
                                              <c:choose>
                                                  <c:when test="${as==10}">
                                                      满
                                                  </c:when>
                                                  <c:otherwise>
                                                      <c:forEach begin="2" end="${as}" step="2">
                                                          ★
                                                      </c:forEach>
                                                      <c:if test="${as % 2 == 1}">
                                                          ☆
                                                      </c:if>
                                                  </c:otherwise>
                                              </c:choose>
                                          </c:otherwise>
                                      </c:choose>
                                  </td>
                              </c:when>
                              <c:when test="${i.index > 15}">
                                  <td>
                                      <c:choose>
                                          <c:when test="${aa == 1}">
                                              ☆
                                              <%
//                                          HttpPost pm = new HttpPost(OgzqURL.URL + "/Tactical.aspx");
//                                          ArrayList<NameValuePair> params = new ArrayList<NameValuePair>();
//                                          params.add(new BasicNameValuePair("type", "14"));
//                                          params.add(new BasicNameValuePair("tacticalIndex", "3"));
//                                          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//                                          String s = IDUtils.execute("robot0007@sina.com", pm);
                                              %>
                                          </c:when>
                                          <c:when test="${aa == -1}">
                                              ×
                                          </c:when>
                                          <c:when test="${aa == 0}">
                                              ★
                                          </c:when>
                                      </c:choose>
                                  </td>
                              </c:when>
                              <c:otherwise>
                                  <td align="right">${aa}</td>
                              </c:otherwise>
                          </c:choose>
                      </c:forEach>
                  </c:when>
                  <c:otherwise>
                      <td>${a[0]}</td>
                      <td>${a[1]}</td>
                      <td colspan="17">激情训练</td>
                  </c:otherwise>
              </c:choose>
              <td>
                  <input type="button" value="攻" onclick="changeTactic('${a[0]}', 1);" style="padding-left: 1px;padding-right: 1px;"/>
                  <input type="button" value="组" onclick="changeTactic('${a[0]}', 3);" style="padding-left: 1px;padding-right: 1px;"/>
                  <input type="button" value="防" onclick="changeTactic('${a[0]}', 5);" style="padding-left: 1px;padding-right: 1px;"/>
              </td>
          </tr>
      </c:forEach>
  </table>
  </body>
<script type="text/javascript">
    function changeTactic(email, tactic) {
        OgzqDwr.changeTactic(email, tactic, after);
    }

    function after(obj) {
        alert(obj);
    }
</script>
</html>