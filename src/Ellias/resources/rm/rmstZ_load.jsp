<%--
  Created by IntelliJ IDEA.
  User: H00672
  Date: 2017/12/28 0028
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
    <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/RMDwr.js'></script>
</head>
<body>
<input type="file" name="imdfile"/>
<input type="button" onclick="loadPng(event, document.getElementsByName('imdfile')[0]);">
<img id="img">
</body>
<script type="text/javascript">
    function loadPng(event, obj)
    {
        var file = dwr.util.getValue(obj);
        RMDwr.fromClient(obj.value.substring(obj.value.lastIndexOf("\\") + 1), 0, file, after);
    }
    function after(str) {
        document.getElementById('img').src = str;
    }
</script>
</html>
