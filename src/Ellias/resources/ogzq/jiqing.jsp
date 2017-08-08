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
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-8-17
  Time: 1:24:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
  <head></head>
  <body>
  <%
      List<String> ids = IDUtils.GETIDS();
      List<String[]> list = new ArrayList<String[]>();
      for(String id : ids) {
          List<Map<String, String>> players = new OgzqDwr().viewPlayer(id);
          Map<String, String> p = null;
          for(Map<String, String> pp : players) {
              if(players.indexOf(pp) == 0) {
                  continue;
              }
              if(Integer.parseInt(pp.get("pinzhi")) > 2) {
                  p = pp;
                  String playerId = p.get("id");
                  HttpPost pm = new HttpPost(OgzqURL.URL + "/TeamAndPlayer/Player.aspx");
                  List<NameValuePair> params = new ArrayList<NameValuePair>();
                  params.add(new BasicNameValuePair("JqXunlian1", playerId));
                  pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                  String ret = IDUtils.execute(id, pm);
                  if(ret.equals("-1")) {
                      pm = new HttpPost(OgzqURL.URL + "/Prop/Trade2.aspx");
                      params = new ArrayList<NameValuePair>();
                      params.add(new BasicNameValuePair("type", "0"));
                      params.add(new BasicNameValuePair("TradeNum", "0"));
                      params.add(new BasicNameValuePair("PageNumber", "1"));
                      pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                      ret = IDUtils.execute(id, pm);
                      if("-2".equals(ret)) {
                          continue;
                      }
                      ret = ret.substring(1);
                      ret = ret.substring(0, ret.indexOf("&"));
                      String[] v = ret.split("[|]");
                      list.add(new String[]{id, IDUtils.getNick(id), v[0], v[1], v[2], v[3]});
//              &0|0|0|13552&1/0/0
                  } else {
                      System.out.println(ret);
                      ret = ret.substring(ret.indexOf("@") + 1);
                      ret = ret.substring(0, ret.indexOf("@"));
                      String[] v = ret.split("[*]");
                      String[] l = new String[8];
                      l[0] = id;
                      l[1] = IDUtils.getNick(id);
                      l[2] = v[0].split("[|]")[1];
                      l[3] = v[1].split("[|]")[1];
                      l[4] = v[2].split("[|]")[1];
                      l[5] = v[3].split("[|]")[1];
                      String[] colors = new String[]{"蓝", "橙", "红", "紫"};
                      int now = 999999999;
                      String color = "";
                      for(int i = 0; i < 4; i++) {
                          if((Integer.parseInt(l[2 + i]) / Math.pow(2, (3 - i))) < now) {
                              color = colors[i];
                              now = (int) (Integer.parseInt(l[2 + i]) / Math.pow(2, (3 - i)));
                          }
                      }
                      l[6] = now + "";
                      l[7] = color;
                      list.add(l);
//          0@0|0|800*0|0|400*1|11688|200*1|360|100@10
                  }
                  break;
              }
          }
      }
      request.setAttribute("atts", list);
  %>
  <table id="info" border="0" cellpadding="1" cellspacing="1" bgcolor="black">
      <tr class="head">
          <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">登录名</th>
          <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">游戏名</th>
          <th onclick="resort(this, 2, false)" style="cursor:pointer;text-decoration:underline;">蓝色</th>
          <th onclick="resort(this, 3, false)" style="cursor:pointer;text-decoration:underline;">橙色</th>
          <th onclick="resort(this, 4, false)" style="cursor:pointer;text-decoration:underline;">红色</th>
          <th onclick="resort(this, 5, false)" style="cursor:pointer;text-decoration:underline;">紫色</th>
          <th onclick="resort(this, 6, true)" style="cursor:pointer;text-decoration:underline;">可用紫色</th>
          <th onclick="resort(this, 7, false)" style="cursor:pointer;text-decoration:underline;">瓶颈</th>
      </tr>
      <c:forEach items="${atts}" var="a" varStatus="i">
          <tr class="row${i.index % 2 + 1}">
              <c:forEach items="${a}" var="aa">
                  <td align="right">${aa}</td>
              </c:forEach>
          </tr>
      </c:forEach>
  </table>
  </body>
</html>