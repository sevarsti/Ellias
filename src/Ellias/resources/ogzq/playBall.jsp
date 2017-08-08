<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.commons.configuration.PropertiesConfiguration" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
    <%
        Logger LOGGER = Logger.getLogger(this.getClass());
        List<String> ids = IDUtils.GETIDS();

        PropertiesConfiguration conf = new PropertiesConfiguration("D:\\work\\Ellias\\web\\ROOT\\ogzq\\ids.ini");
        HttpPost pm;
        for(String id : ids) {
            if(id.indexOf("sevarsti") > -1) {
                continue;
            }
            String pwd = "lspmgk";
            String p = conf.getString(id);
            if(StringUtils.isNotEmpty(p)) {
                pwd = p;
            }
            if(!pwd.equals("www123") && !pwd.equals("880612")) {
                pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("load_ball", "1"));
                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                IDUtils.execute(id, pm);
                for(int y = 0; y < 3; y++) {
                    for(int x = 0; x < 9; x++) {
                        try {
                            pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("position_x_ball", "" + x));
                            params.add(new BasicNameValuePair("position_y_ball", "" + y));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            String ret = IDUtils.execute(id, pm);
                            if(!ret.startsWith("-1")) {
                                pm = new HttpPost(OgzqURL.URL + "/RPcup.aspx");
                                params = new ArrayList<NameValuePair>();
                                params.add(new BasicNameValuePair("x_position_ball", "" + x));
                                params.add(new BasicNameValuePair("y_position_ball", "" + y));
                                pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                                ret = IDUtils.execute(id, pm);
                                System.out.println(id + ":" + ret);
                                out.println(id + ":" + ret + "<br/>");
                            }
                        } catch(Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }
    %>
    <body>
        <%--<table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">--%>
            <%--<tr class="head">--%>
                <%--<th >email</th>--%>
                <%--<th >1</th>--%>
                <%--<th >2</th>--%>
                <%--<th >3</th>--%>
                <%--<th >4</th>--%>
                <%--<th >5</th>--%>
                <%--<th >6</th>--%>
            <%--</tr>--%>
            <%--<c:forEach items="${res}" var="r" varStatus="i">--%>
                <%--<tr class="row${i.index % 2 + 1}">--%>
                    <%--<c:forEach items="${r}" var="rr">--%>
                        <%--<td>--%>
                            <%--${rr}--%>
                        <%--</td>--%>
                    <%--</c:forEach>--%>
                <%--</tr>--%>
            <%--</c:forEach>--%>
        <%--</table>--%>
        <%--<c:if test="<%=gets.size() > 0%>">--%>
            <%--<c:forEach items="<%=gets%>" var="r">--%>
                <%--${r}<br/>--%>
            <%--</c:forEach>--%>
        <%--</c:if>--%>
    </body>
<script type="text/javascript">
</script>
</html>