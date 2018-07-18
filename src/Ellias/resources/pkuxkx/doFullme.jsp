<%@ page import="com.saille.pkuxkx.FullmeUtils" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.http.client.methods.HttpGet" %>
<%@ page import="org.apache.http.impl.client.DefaultHttpClient" %>
<%@ page import="org.apache.http.HttpResponse" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="com.saille.util.CommonUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2018-07-19
  Time: 00:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fullme</title>
</head>
<%
//    Map<String, String> map = FullmeUtils.FULLME;
//    List<String> keys = new ArrayList<String>(map.keySet());
//    Collections.sort(keys);
    List<String> keys = FullmeUtils.getUnfinished();
%>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table">
    <%
        Random r = new Random();
        Pattern p = Pattern.compile("^.+/b2evo_captcha_([A-F0-9]+)\\.jpg.+\\n.+$");
        DefaultHttpClient client = new DefaultHttpClient();
outer:
        for(int i = keys.size() - 1; i >= 0; i--) {
            String key = keys.get(i);
//            String url = FullmeUtils.get(key);
            String[] urls = new String[3];
            for(int j = 0; j < 3; j++) {
                HttpGet gm = new HttpGet("http://pkuxkx.com/antirobot/robot.php?filename=" + key);
                HttpResponse resp = client.execute(gm);
                InputStream is = resp.getEntity().getContent();
                String content = CommonUtils.getString(is, "GBK");
                if(StringUtils.isBlank(content)) {
                    System.out.print("fullme expired:" + key);
//                    FullmeUtils.FULLME.remove(key);
                    keys.remove(i);
                    continue outer;
                }
                Matcher m = p.matcher(content);
                if(!m.matches()) {
                    System.out.println("parse url failed:" + key);
//                    FullmeUtils.FULLME.remove(key);
                    keys.remove(i);
                    continue outer;
                }
                urls[j] = m.group(1);
            }
    %>
    <tr class="row<%=i % 2 + 1%>">
        <td>
            <%
                for(int j = 0; j < 3; j++) {
            %>
            <img src="http://pkuxkx.com/antirobot/b2evo_captcha_tmp/b2evo_captcha_<%=urls[j]%>.jpg?"/>
            <br/>
            <%
                }
            %>
        </td>
        <td>
            <input type="text" name="<%=key%>"/><input type="button" value="����"/>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
