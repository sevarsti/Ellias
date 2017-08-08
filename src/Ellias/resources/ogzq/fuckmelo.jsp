<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpRequestRetryHandler" %>
<%@ page import="org.apache.http.params.CoreProtocolPNames" %>
<%@ page import="org.apache.http.params.CoreConnectionPNames" %>
<%@ page import="org.apache.http.client.params.ClientPNames" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.http.client.methods.CloseableHttpResponse" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="com.saille.util.CommonUtils" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2015-9-16
  Time: 13:29:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>
  <%
      for(int i = 0; i < 7; i++) {
          DefaultHttpClient client = new DefaultHttpClient();
          DefaultHttpRequestRetryHandler handler = new DefaultHttpRequestRetryHandler(0, false);
          client.setHttpRequestRetryHandler(handler);
          client.getParams().setParameter(CoreProtocolPNames.USER_AGENT, "user_agent");
          client.getParams().setParameter("http.protocol.single-cookie-header", true);
          client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 25000);
          client.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 25000);
          client.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true); //启用重定向

          HttpPost pm = new HttpPost("http://play.ifeng.com/?_a=Dologin");
          List<NameValuePair> params = new ArrayList<NameValuePair>();
          params.add(new BasicNameValuePair("username", "melo_07"));
          params.add(new BasicNameValuePair("password", "asdf"));
          params.add(new BasicNameValuePair("inpass", "on"));
          params.add(new BasicNameValuePair("game", "ogzq"));
          pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

          CloseableHttpResponse res = client.execute(pm);
          InputStream is = res.getEntity().getContent();
          String ret = CommonUtils.getString(res.getEntity().getContent(), "utf-8");
          is.close();
          res.close();
          pm.releaseConnection();
      }

//      HttpGet gm = new HttpGet("http://play.ifeng.com/?_c=ogzq&_a=enterGame&area=7");
//      res = client.execute(gm);
//      ret = CommonUtils.getString(res.getEntity().getContent(), "utf-8");
//      System.out.println(ret);
//      res.getEntity().getContent().close();
//      res.close();
//      gm.releaseConnection();
  %>
  帐号已锁，oh yeah
  </body>
</html>