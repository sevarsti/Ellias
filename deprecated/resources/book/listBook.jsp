<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2008-4-28
  Time: 17:42:50
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>
<%--<%@ page contentType="text/html;charset=GBK" language="java" %>--%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>书籍列表</title>
  </head>
  <body>
    <table border="1" width="80%">
        <tr>
            <td>书名</td>
            <td>作者</td>
        </tr>
        <c:forEach items="${bookForm.books}" var="book">
            <tr>
                <td>
                    <a href="/book.do?method=bookDetail&bookName=${book.title}">${book.title}</a>
                </td>
                <td>
                    ${book.author}
                </td>
            </tr>
        </c:forEach>
    </table>
    <script type="text/javascript">
        function addBook()
        {
            window.location = '/book.do?method=addBookPre';
        }
    </script>
    <input type="button" class="otterbtn" value="增加" onclick="addBook();"/>
  </body>
</html>