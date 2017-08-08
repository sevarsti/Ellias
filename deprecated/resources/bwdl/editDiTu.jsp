<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-19
  Time: 20:37:53
  To change this template use File | Settings | File Templates.
--%>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-19
  Time: 1:55:06
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>±à¼­µØÍ¼</title>
  </head>
  <body>
    <c:set var="form" value="${dituForm}"/>
    <html:form action="/bwdl/ditu.do">
        <input type="hidden" name="method"/>
        <input type="hidden" name="fangjuId"/>

        <table bgcolor="#EEEEEE" cellpadding="0" cellspacing="0" border="0">
            <c:forEach begin="0" end="19">
                <tr>
                    <c:forEach begin="0" end="31">
                        <td>
                            <img src="image/zc/white.PNG" width="20" height="20" onclick="updateMap(this);" style="cursor:pointer;"/>
                        </td>
                    </c:forEach>
                </tr>
            </c:forEach>
        </table>
        <table border="0">
            <tr>
                <td>
                    <img src="image/zc/white.PNG" alt="" id="current" width="24" height="24"/>
                </td>
            </tr>
        </table>
        <table id="table" bgcolor="#FFFFFF" cellpadding="0" cellspacing="1" border="0">
            <c:forEach items="${form.sucais}" var="s" varStatus="i">
                <c:if test="${i.index % 16 == 0}">
                    <tr>
                </c:if>
                <td>
                    <img src="${s}" alt="${i.index}" height="24" width="24" style="cursor:pointer;" onclick="updateCurrent(this);"/>
                </td>
                <c:if test="${i.index % 16 == 15}">
                    </tr>
                </c:if>
            </c:forEach>
        </table>
        <input type="button" value="±£´æ" onclick="saveFangJu();" class="otterbtn"/>
  </html:form>
  <script type="text/javascript">
      function updateMap(obj)
      {
          var img = document.getElementById('current');
          obj.src = img.src;
          obj.alt = img.alt;
      }
      function updateCurrent(obj)
      {
          var img = document.getElementById('current');
          img.src = obj.src;
          img.alt = obj.alt;
      }
  </script>
  </body>
</html>