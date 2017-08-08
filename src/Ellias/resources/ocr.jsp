<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2009-11-28
  Time: 23:01:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    doc = new ActiveXObject("MODI.Document");
doc.Create("D:\\v\\new.jpg");
doc.PrintOut(0, -1, 1, "Microsoft Office Document Image Writer","D:\\v\\test.jpg", 0 ,1);
doc.Close();
doc.Create("D:\\v\\test.jpg");
try{
doc.OCR(0x09, true, true);
var tex = doc.Images(0).Layout.Text;
doc.Close();
alert(tex);
}catch(e)
{
  doc.Close();
alert(e.description);
}
</script>
<html>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>