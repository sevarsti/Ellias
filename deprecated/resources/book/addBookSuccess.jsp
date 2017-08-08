<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2008-4-28
  Time: 18:03:43
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<html>
<head>
    <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
    <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
    <title>书籍添加成功</title>
</head>
<body>
    <div class="page_title">书籍添加成功</div>
    <br/>
    书籍添加成功。
    <br/>
    <br/>
    <script type="text/javascript">
        function clickButton()
        {
            window.location = '/book.do?method=listBook';
        }
    </script>
    <input type="button" value="查看书籍列表" onclick="clickButton();"/>
</body>
</html>