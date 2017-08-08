<%@ page import="servlet.GlobalContext" %>
<%@ page import="com.saille.hhcq.dao.HhcqDao" %>
<%@ page import="java.io.File" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="com.saille.hhcq.Shangpin" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="com.saille.hhcq.Gangkou" %>
<%@ page import="com.saille.hhcq.Chushou" %>
<%@ page import="com.saille.hhcq.Jiage" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2010-3-1
  Time: 23:21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    try {
        boolean ok = true;
        Logger LOGGER = Logger.getLogger(this.getClass());
        HhcqDao dao = (HhcqDao) GlobalContext.getSpringContext().getBean("hhcqDao", HhcqDao.class);
//        File f = new File("C:\\Documents and Settings\\Ellias\\×ÀÃæ\\price.xls");
        File f = new File("D:\\impJiage.xls");
        InputStream is = new FileInputStream(f);
        HSSFWorkbook workbook = new HSSFWorkbook(is);
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        List<Shangpin> shangpins = new ArrayList<Shangpin>();
        row = sheet.getRow(1);
        for (int i = 1; i < 131; i++) {
            cell = row.getCell((short) i);
            LOGGER.info(i);
            String shangpinName = cell.getRichStringCellValue().getString();
            LOGGER.info(shangpinName);
            Shangpin shangpin = dao.getShangpinByName(shangpinName);
            if (shangpin == null) {
                LOGGER.info("i: " + i + "," + shangpinName);
                ok = false;
                break;
            } else {
                shangpins.add(shangpin);
            }
        }
        List<Gangkou> gangkous = new ArrayList<Gangkou>();
        for (int i = 2; i <= sheet.getLastRowNum(); i++) {
            if(sheet.getRow(i).getCell((short) 0).getRichStringCellValue().getString() == null ||
                    sheet.getRow(i).getCell((short) 0).getRichStringCellValue().getString().trim().equals("")) {
                break;
            }
            String gangkouName = sheet.getRow(i).getCell((short) 0).getRichStringCellValue().getString();
            Gangkou gangkou = dao.getGangkouByName(gangkouName);
            if (gangkou == null) {
                LOGGER.info("i: " + i + "," + gangkouName);
                ok = false;
                break;
            } else {
                List<Chushou> chushous = dao.getChushouByGangkou(gangkou.getId());
                if (chushous.size() == 0 || (chushous.size() % 4 != 0)) {
                    LOGGER.info("ii: " + i + "," + gangkouName);
                    ok = false;
                    break;
                } else {
                    gangkous.add(gangkou);
                }
            }
        }
        int count = 0;
        if(ok) {
            for (int i = 2; i <= sheet.getLastRowNum(); i++) {
                if(sheet.getRow(i).getCell((short) 0).getRichStringCellValue().getString() == null ||
                        sheet.getRow(i).getCell((short) 0).getRichStringCellValue().getString().trim().equals("")) {
                    break;
                }
                row = sheet.getRow(i);
                for (int j = 1; j < 131; j++) {
                    int value = (int) row.getCell((short) j).getNumericCellValue();
                    List<Chushou> chushou = dao.getChushou(gangkous.get(i-2).getId(), shangpins.get(j-1).getId());
                    if(chushou.size() > 0) {
                        LOGGER.info("skip" + count);
                        continue;
                    }
                    List<Jiage> l = dao.getJiage(gangkous.get(i-2).getId(), shangpins.get(j-1).getId());
                    Jiage jiage = l.size() > 0 ? l.get(0) : null;
                    if(jiage == null) {
                        jiage = new Jiage();
                        jiage.setGangkouId(gangkous.get(i-2).getId());
                        jiage.setShangpinId(shangpins.get(j-1).getId());
                    }
                    jiage.setJiage(Math.max(value,jiage.getJiage()));
                    LOGGER.info("save" + count);
                    dao.saveJiage(jiage);
                    count++;
                }
            }
        }
        LOGGER.info("total " + count + " items saved");
    } catch(Exception ex) {
        ex.printStackTrace();
    }
%>
<html>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>