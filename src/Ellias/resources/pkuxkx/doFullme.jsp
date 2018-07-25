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
<%@ include file="../include/include.jsp"%>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/FullmeDwr.js'></script>
<html>
<head>
    <title>Fullme</title>
</head>
<body>
<table border="0" cellpadding="1" cellspacing="1" bgcolor="black" id="table" width="100%">
    <tr class="head">
        <td width="80%">Õº∆¨</td>
        <td width="20%">OCR</td>
    </tr>
    <%
        List<String> keys = FullmeUtils.getUnfinished();
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
            <img src="http://pkuxkx.com/antirobot/b2evo_captcha_tmp/b2evo_captcha_<%=urls[j]%>.jpg" style="max-height: 100%;max-width: 100%"/>
            <br/>
            <%
                }
            %>
        </td>
        <td>
            <input type="text" name="<%=key%>" style="font-size: 20px;"/><br/>
            <input type="button" style="font-size: 20px;" value="±£¥Ê" onclick="doFullme('<%=key%>', document.getElementsByName('<%=key%>')[0].value)"/>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
<script type="text/javascript">
    function doFullme(url, value)
    {
        FullmeDwr.doFullme(url, value, after);
    }
    function after(done)
    {
        if(done)
        {
            alert('fullme≥…π¶');
        }
        else
        {
            alert('fullme ß∞‹');
        }
    }
</script>
</html>
