<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-11-7
  Time: 23:27:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqDwr" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.saille.ogzq.RobNaiBa" %>
<%@ include file="../include/include.jsp"%>
<html>
  <head></head>
  <body>
  <%

  %>
  <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
      <tr class="head">
          <th>教练</th>
          <th>瓜迪奥拉</th>
          <th>弗格森</th>
          <th>穆里尼奥</th>
      </tr>
      <tr class="row1">
          <td>第一名</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.first[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row2">
          <td>第一名积分</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.firstScore[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row1">
          <td>竞拍号</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.nick[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row2">
          <td>要求领先优势（领先小于等于该分数则买礼包）</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.lingxian[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row2">
          <td>当前领先优势</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.currentLingxian[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row1">
          <td>积分上限（超过该积分则放弃竞拍）</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.max[i]%></td>
          <%
              }
          %>
      </tr>
      <tr class="row2">
          <td>剩余时间（秒）</td>
          <%
              for(int i = 0; i < 3; i++) {
          %>
          <td><%=RobNaiBa.restTime[i]%></td>
          <%
              }
          %>
      </tr>
  </table>
  </body>
</html>