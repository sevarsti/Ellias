<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2013-6-14
  Time: 23:37:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>µÇÂ¼</title></head>
<body onload="initTop();">
<form action="/login.do" method="post">
    <input type="hidden" name="method" value="login"/>
    user=<input type="text" name="name"/>
    pwd=<input type="password" name="pwd"/>
    <input type="submit" onclick="doS();" value="µÇÂ¼"/>
</form>
<script type="text/javascript">
    function initTop()
    {
        if(top.location.href != location.href) {
            top.location.href = location.href;
        } else {
            document.getElementsByName('name')[0].focus();
        }
     }
    function doS()
    {
//        document.forms[0].submit();
    }
</script>
</body>
</html>