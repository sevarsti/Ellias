<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
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
        //        Calendar c = Calendar.getInstance();
//        while(c.get(Calendar.HOUR_OF_DAY) <= 7) {
//            Thread.sleep(60 * 1000);
//            c.setTime(new Date());
//        }
        Logger LOGGER = Logger.getLogger(this.getClass());
        List<String> ids = IDUtils.GETIDS();
        List<String[]> results = new ArrayList<String[]>();
        List<String> gets = new ArrayList<String>();
        for(String id : ids) {
            HttpPost pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
            List<NameValuePair> pp = new ArrayList<NameValuePair>();
            pp.add(new BasicNameValuePair("load", "1"));
            pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
            String ret = IDUtils.execute(id, pm);
            String[] ss = ret.split("[|]");
            List<String> sss = new ArrayList<String>();
            sss.add(id);
            for(int i = 0; i < 6; i++) {
                sss.add(ss[i]);
            }
            results.add(sss.toArray(new String[7]));
            if(ss[0].equals("0") && Integer.parseInt(ss[3]) > 0) {
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("clickToGetReward", "1"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                IDUtils.execute(id, pm);
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("getReward", "1"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                gets.add(id + "/15分钟::::::" + IDUtils.execute(id, pm));
            }
            if(ss[1].equals("0") && Integer.parseInt(ss[4]) > 0) {
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("clickToGetReward", "2"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                IDUtils.execute(id, pm);
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("getReward", "2"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                gets.add(id + "/ 6小时::::::" + IDUtils.execute(id, pm));
            }
            if(ss[2].equals("0") && Integer.parseInt(ss[5]) > 0) {
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("clickToGetReward", "3"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                IDUtils.execute(id, pm);
                pm = new HttpPost(OgzqURL.URL + "/GridShop.aspx");
                pp = new ArrayList<NameValuePair>();
                pp.add(new BasicNameValuePair("getReward", "3"));
                pm.setEntity(new UrlEncodedFormEntity(pp, HTTP.UTF_8));
                gets.add(id + "/12小时::::::" + IDUtils.execute(id, pm));
            }
        }
        request.setAttribute("res", results);
        for(String s : gets) {
            LOGGER.info(s);
        }
    %>
    <body>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th >email</th>
                <th >1</th>
                <th >2</th>
                <th >3</th>
                <th >4</th>
                <th >5</th>
                <th >6</th>
            </tr>
            <c:forEach items="${res}" var="r" varStatus="i">
                <tr class="row${i.index % 2 + 1}">
                    <c:forEach items="${r}" var="rr">
                        <td>
                            ${rr}
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <c:if test="<%=gets.size() > 0%>">
            <c:forEach items="<%=gets%>" var="r">
                ${r}<br/>
            </c:forEach>
        </c:if>
    </body>
<script type="text/javascript">
</script>
</html>