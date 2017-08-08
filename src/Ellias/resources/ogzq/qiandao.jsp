<%@ page import="java.util.List" %>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-10-1
  Time: 15:56:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <%
      List<String> ids = IDUtils.GETIDS();
      for(String id : ids) {
          System.out.println(ids.indexOf(id) + "/" + ids.size() + ":" + id);
          HttpPost pm = new HttpPost(OgzqURL.URL + "/HappySevenDays.aspx");
          List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("type", "1"));
          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
          out.println(id + "---" + IDUtils.execute(id, pm));
//          pm = new HttpPost(OgzqURL.URL + "/HappySevenDays.aspx");
//          params = new ArrayList<NameValuePair>();
//          params.add(new BasicNameValuePair("type", "2"));
//          params.add(new BasicNameValuePair("index", "7"));
//          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//          out.println("---" + IDUtils.execute(id, pm) + "<br/>");
          for(int i = 1; i <= 7; i++) {
              pm = new HttpPost(OgzqURL.URL + "/HappySevenDays.aspx");
              params = new ArrayList<NameValuePair>();
              params.add(new BasicNameValuePair("type", "3"));
              params.add(new BasicNameValuePair("index", ""+i));
              pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
              out.println("---" + IDUtils.execute(id, pm) + "<br/>");
          }
      }
  %>

  </body>
</html>