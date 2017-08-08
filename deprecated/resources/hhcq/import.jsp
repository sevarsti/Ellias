<%@ page import="java.io.File" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell" %>
<%@ page import="com.saille.hhcq.Shangpin" %>
<%@ page import="com.saille.hhcq.dao.HhcqDao" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="com.saille.hhcq.Leibie" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-2-24
  Time: 21:50:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);

    File f = new File("D:\\impShangpin.xls");
    InputStream is = new FileInputStream(f);
    HSSFWorkbook workbook = new HSSFWorkbook(is);
    HSSFSheet sheet = workbook.getSheetAt(0);
    HSSFRow row = null;
    HSSFCell cell = null;
    for (int i = 0; i <= sheet.getLastRowNum(); i++) {
        row = sheet.getRow(i);
        if (row != null) {
            Shangpin shangpin = new Shangpin();
            cell = row.getCell((short) 0);
            if (cell != null) {
                shangpin.setName(cell.getRichStringCellValue().toString());
                cell = row.getCell((short) 1);
                String leibieName = cell.getRichStringCellValue().toString();
                Leibie leibie = dao.getLeibieByName(leibieName);
                if(leibie != null) {
                    shangpin.setLeibie(leibie.getId());
                    dao.saveShangpin(shangpin);
                }
            }
        }
    }
%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>