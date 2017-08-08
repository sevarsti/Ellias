<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-5-3
  Time: 23:58:11
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <%
      DecimalFormat df = new DecimalFormat("0.00");

      Class.forName("com.mysql.jdbc.Driver");
      Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellias", "root", "sjtuagent");
      String sql = "select b.*, a.* from rm_teammember b left join rm_teamhuoyue a on a.qq = b.qq where a.pubdate in";
      PreparedStatement stmt = connection.prepareStatement("select distinct(pubdate) from rm_teamhuoyue order by pubdate desc limit 31");
      ResultSet rs = stmt.executeQuery();
      String subsql = "(";

      String[] pubdates = new String[31];
      int idx = 0;
      while(rs.next()) {
          subsql += rs.getString(1) + ",";
          pubdates[idx] = rs.getString(1);
          idx++;
      }
      subsql = subsql.substring(0, subsql.length() - 1) + ")";
      stmt = connection.prepareStatement(sql + subsql + " order by pubdate desc, idx, totalhuoyue desc");
      rs = stmt.executeQuery();
      String[] columns = new String[rs.getMetaData().getColumnCount()];
      for(int i = 0; i < columns.length; i++) {
          columns[i] = rs.getMetaData().getColumnName(i + 1);
      }

      List<String[]> results = new ArrayList<String[]>(); //nick, qq, enterdate, three, seven, month, average, day1, day2..., day7

      String nowpubdate = null;
      int day = 6;
//      while(rs.next()) {
//          for(int i = 1; i <= columns.length; i++) {
//              System.out.print(rs.getString(i) + "\t");
//          }
//          System.out.println();
//      }

      while(rs.next()) {
          String qq = rs.getString("qq");
          String pubdate = rs.getString("pubdate");
          if(!pubdate.equals(nowpubdate)) {
              day++;
              nowpubdate = pubdate;
          }
          boolean found = false;
          int index = 0;
          for(index = 0; index < results.size(); index++) {
              String[] s = results.get(index);
//              System.out.println(s[0] + "/" + s[1]);
              if(s[1].equals(qq)) {
                  found = true;
                  break;
              }
          }
          if(!found) {
              results.add(new String[39]);
              index = results.size() - 1;
              results.get(index)[0] = rs.getString("nick");
              results.get(index)[1] = rs.getString("qq");
              results.get(index)[2] = rs.getString("enterdate");
              results.get(index)[38] = rs.getString("vip");
          }
          results.get(index)[day] = rs.getString("dayhuoyue");
      }

      int daycount = 31;
      for(int i = 1; i < pubdates.length; i++) {
          if(!pubdates[i].substring(0, 6).equals(pubdates[0].substring(0, 6))) {
              daycount = i;
              break;
          }
      }

      for(int i = results.size() - 1; i >= 0; i--) {
          if(results.get(i)[7] == null) {
              results.remove(i);
              continue;
          }
          int d3 = 0, d7 = 0;
          for(int j = 0; j < 7; j++) {
              if(results.get(i)[j + 7] == null) {
                  if(j < 3) {
                      d3 = -1;
                  }
                  if(j < 7) {
                      d7 = -1;
                  }
                  break;
              }
              if(j < 3) {
                  d3 += Integer.parseInt(results.get(i)[j + 7]);
              }
              d7 += Integer.parseInt(results.get(i)[j + 7]);
          }
          if(d3 >= 0) {
              results.get(i)[3] = d3 + "";
          }
          if(d7 >= 0) {
              results.get(i)[4] = d7 + "";
          }
          int monthsum = 0, monthcount = 0;
          for(int j = 0; j < daycount; j++) {
              if(results.get(i)[j + 7] != null) {
                  monthsum += Integer.parseInt(results.get(i)[j + 7]);
                  monthcount++;
              }
          }
          results.get(i)[5] = monthsum + "";
          results.get(i)[6] = df.format(((double)monthsum) / ((double)monthcount));
      }
      request.setAttribute("results", results);
      request.setAttribute("pubdates", pubdates);

  %>
  <table border="0" cellpadding="1" cellspacing="1">
      <tr class="head" style="font-weight:bold;">
          <td>昵称</td>
          <td>QQ</td>
          <td>进队时间</td>
          <td>三日</td>
          <td>七日 </td>
          <td>本月</td>
          <td>平均</td>
          <c:forEach begin="1" end="31" var="i">
              <td>${pubdates[i-1] % 1000}</td>
          </c:forEach>
      </tr>
      <c:forEach items="${results}" var="r" varStatus="i">
          <c:choose>
              <c:when test="${r[3] == '0' && '1' != r[38]}">
                  <c:set var="color" value="red"/>
                  <tr class="row${i.index % 2 + 1}" style="background-color:${color}">
              </c:when>
              <c:when test="${functions:lessThan(r[4],1000) && '1' != r[38]}">
                  <c:set var="color" value="yellow"/>
                  <tr class="row${i.index % 2 + 1}" style="background-color:${color}">
              </c:when>
              <c:otherwise>
                  <c:set var="color" value="black"/>
                  <tr class="row${i.index % 2 + 1}">
              </c:otherwise>
          </c:choose>
              <td align="left" style="font-weight:bold;" nowrap>${r[0]}</td>
              <td align="left" style="font-weight:bold;" nowrap>${r[1]}</td>
              <td align="left" style="font-weight:bold;" nowrap>${r[2]}</td>
              <td align="right">
                  <fmt:formatNumber value="${r[3]}" pattern="0"/>
              </td>
              <td align="right">
                  <fmt:formatNumber value="${r[4]}" pattern="0"/>
              </td>
              <td align="right">
                  <fmt:formatNumber value="${r[5]}" pattern="0"/>
              </td>
              <td align="right" style="border-right-style:double;">
                  <fmt:formatNumber value="${r[6]}" pattern="0.00"/>
              </td>
              <c:forEach begin="7" end="37" var="ii">
                  <td align="right">
                      <fmt:formatNumber value="${r[ii]}" pattern="0"/>
                  </td>
              </c:forEach>
          </tr>
      </c:forEach>
  </table>
  </body>
</html>