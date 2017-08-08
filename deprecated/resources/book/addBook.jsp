<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2008-4-28
  Time: 16:47:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<%@ include file="../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>增加书籍</title>
  </head>
  <body>
    <html:form action="/book.do">
        <input type="hidden" name="method" value="addBook"/>
        <table border="1" width="80%">
            <tr>
                <td>题目：</td>
                <td>
                    <html:text property="book.title" size="80"/>
                </td>
            </tr>
            <tr>
                <td>作者：</td>
                <td>
                    <html:text property="book.author"/>
                </td>
            </tr>
            <tr>
                <td>摘要：</td>
                <td>
                    <html:textarea property="book.summary" rows="10" cols="80"/>
                </td>
            </tr>
        </table>
        <script type="text/javascript">
            function checkSubmit()
            {
                var title = document.getElementsByName("book.title")[0].value;
                if(title == '')
                {
                    alert('标题不能为空');
                    return false;
                }
                document.forms[0].submit();
            }
        </script>
        <input type="button" value="提交" onclick="checkSubmit();"/>
    </html:form>
  </body>
</html>