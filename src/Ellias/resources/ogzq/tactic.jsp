<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2012-7-18
  Time: 0:25:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.OgzqURL" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpPost" %>
<%@ page import="org.apache.http.NameValuePair" %>
<%@ page import="org.apache.http.client.entity.UrlEncodedFormEntity" %>
<%@ page import="org.apache.http.message.BasicNameValuePair" %>
<%@ page import="org.apache.http.protocol.HTTP" %>
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
<%!
    List<String> generateEmail(String email) {
        List<String> ret = new ArrayList<String>();
        if(StringUtils.isNotEmpty(email)) {
            if(IDUtils.IDS.containsKey(email)) {
                ret.add(email);
            }
        } else {
            Set<String> keys = IDUtils.IDS.keySet();
            for(String k : keys) {
                ret.add(k);
            }
        }
        Collections.sort(ret);
        return ret;
    }

%>
    <body>
        <table id="info" width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr class="head">
                <th onclick="resort(this, 0, false)" style="cursor:pointer;text-decoration:underline;">登录名</th>
                <th onclick="resort(this, 1, false)" style="cursor:pointer;text-decoration:underline;">nick</th>
                <th onclick="resort(this, 2, true)" style="cursor:pointer;text-decoration:underline;">ID</th>
                <th onclick="resort(this, 3, false)" style="cursor:pointer;text-decoration:underline;">姓名</th>
                <th onclick="resort(this, 4, true)" style="cursor:pointer;text-decoration:underline;">位置</th>
                <th onclick="resort(this, 5, true)" style="cursor:pointer;text-decoration:underline;">实力</th>
                <th onclick="resort(this, 6, true)" style="cursor:pointer;text-decoration:underline;">颜色</th>
                <th onclick="resort(this, 7, true)" style="cursor:pointer;text-decoration:underline;">等级</th>
                <th onclick="resort(this, 8, false)" style="cursor:pointer;text-decoration:underline;">积分</th>
                <th onclick="resort(this, 9, false)" style="cursor:pointer;text-decoration:underline;">等待时间</th>
            </tr>
            <%
                Logger LOGGER = Logger.getLogger(this.getClass());

                String email = request.getParameter("email");
                List<String> ids = this.generateEmail(email);


                for(int i = 0; i < ids.size(); i++) {
                    String id = ids.get(i);
                    HttpPost pm = new HttpPost(OgzqURL.URL + OgzqURL.TACTIC);
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("Load", "1"));
                    pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                    String s = IDUtils.execute(id, pm);
                    String countStr = s.substring(s.indexOf("^") + 1);
                    countStr = countStr.substring(0, countStr.indexOf("@"));
                    int count = Integer.parseInt(countStr);

                    String s2 = s.substring(s.indexOf("@") + 1);
                    String[] players2 = s2.split("[*]");
                    for(int ii = 0; ii < players2.length; ii++) {
                        players2[ii] = players2[ii] + "|0|0|0|0|0|0";
                    }
                    s = s.substring(0, s.indexOf("^"));
                    List<String> listPlayers = new ArrayList<String>();
//                    listPlayers.addAll(Arrays.asList(players2));
                    String[] players = s.split("[*]");
                    listPlayers.addAll(Arrays.asList(players));

//                    if(count > 16) { //如果超过16个，需要把后面的也找出来
                        for(int j = 0; j <= (count - 1) / 16; j++) {
                            pm = new HttpPost(OgzqURL.URL + "/Tactics.aspx");
                            params = new ArrayList<NameValuePair>();
                            params.add(new BasicNameValuePair("PageIndex", "" + j));
                            pm.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
                            s = IDUtils.execute(id, pm);
                            players2 = s.split("[*]");
                            for(int ii = 0; ii < players2.length; ii++) {
                                players2[ii] = players2[ii] + "|0|0|0|0|0|0";
                            }
                            listPlayers.addAll(Arrays.asList(players2));
                        }
//                    }
                    players = new String[listPlayers.size()];
                    listPlayers.toArray(players);
                    for(String p : players) {
                        //2994110|阿什利·扬|3 |England/10/41006.png|534|4|260|3|    0|0|1*
                        //3004542|德米凯利斯|2 |Spain/05/50503.png  |492|4|260|4|86179|0|1*
                        //3187394|巴内加    |3 |Spain/06/50608.png  |529|4|260|3|    0|0|1*
                        //0      |          |-1|                    | -1|0|  0|1|    0|0|1*
                        //0      |          |-1|                    | -1|0|  0|1|    0|0|1*
                        //0      |          |-1|                    | -1|0|  0|1|    0|0|1*
                        //0      |          |-1|                    | -1|0|  0|1|    0|0|1^
                        String[] atts = p.split("[|]");
                        if(Integer.parseInt(atts[6]) <= 0) {
                            continue;
                        }
            %>
                <tr class="row<%=i % 2 + 1%>">
                    <td>
                        <%=id%>
                    </td>
                    <td>
                        <%=IDUtils.getNick(id)%>
                    </td>
                    <td><%=atts[0]%></td>
                    <td><%=atts[1]%></td>
                    <td><%=atts[2]%></td>
                    <td><%=atts[4]%></td>
                    <td><%=atts[5]%></td>
                    <td><%=atts[6]%></td>
                    <td><%=atts[7]%></td>
                    <td><%=Integer.parseInt(atts[9]) / 3600 + "小时" + ((Integer.parseInt(atts[8]) % 3600) / 60) + "分钟"%></td>
                </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
<script type="text/javascript">
</script>
</html>