<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-20
  Time: 22:19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="../include/include.jsp"%>

<html>
  <head>
      <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
      <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
      <title>²é¿´µØÍ¼</title>
  </head>
  <body>
  <img src="image/map/full.PNG" alt="" usemap="#Map" width="500" height="200"/>
  <map name="Map" id="Map">
      <area shape="rect" coords="10,10,20,30" href="#" onclick="cl();" style="cursor:pointer;" alt="asdf">
  </map>
  <script type="text/javascript">
      function cl()
      {
          alert("xxx");
      }
  </script>
  </body>
</html>