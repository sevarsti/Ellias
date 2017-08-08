<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell" %>
<%@ page import="com.saille.bwdl.WuJiang" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.saille.bwdl.service.BwdlService" %>
<%@ page import="servlet.GlobalContext" %>
<%@ page import="com.saille.bwdl.WuQi" %>
<%@ page import="java.util.List" %>
<%@ page import="com.saille.bwdl.FangJu" %>
<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2011-6-19
  Time: 14:40:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    try {
        File file = new File("D:\\work\\Ellias\\resources\\bwdl\\doc\\wujiang.xls");
        FileInputStream fis = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheet("Sheet1");
        HSSFRow row = null;
        HSSFCell cell = null;
        Map<String, Integer> wuqis = new HashMap<String, Integer>();
        Map<String, Integer> fangjus = new HashMap<String, Integer>();
        Map<String, Integer> bingzhong = new HashMap<String, Integer>();
        bingzhong.put("ƽ", 1);
        bingzhong.put("ɽ", 2);
        bingzhong.put("ˮ", 3);
        BwdlService service = (BwdlService) GlobalContext.getSpringContext().getBean("bwdlService", BwdlService.class);
        List<WuQi> wuqi = service.findAllWuQis();
        for (WuQi w : wuqi) {
            wuqis.put(w.getName(), w.getId());
        }
        List<FangJu> fangju = service.findAllFangJus();
        for(FangJu f : fangju) {
            fangjus.put(f.getName(), f.getId());
        }
        for (int i = 1; i < 238; i++) {
            row = sheet.getRow(i);
            WuJiang wujiang = new WuJiang();
            wujiang.setName(row.getCell((short) 10).getRichStringCellValue().getString());
            System.out.println(i + "/237: " + wujiang.getName());
            wujiang.setTi((int) row.getCell((short) 1).getNumericCellValue());
            wujiang.setWu((int) row.getCell((short) 2).getNumericCellValue());
            wujiang.setZhi((int) row.getCell((short) 3).getNumericCellValue());
            wujiang.setZhong((int) row.getCell((short) 4).getNumericCellValue());
            wujiang.setDe((int) row.getCell((short) 5).getNumericCellValue());
            wujiang.setJing((int) row.getCell((short) 6).getNumericCellValue());
            wujiang.setBingzhong(bingzhong.get(row.getCell((short) 7).getRichStringCellValue().getString()));
            wujiang.setWuqi(wuqis.get(row.getCell((short) 8).getRichStringCellValue().getString()));
            wujiang.setFangju(fangjus.get(row.getCell((short) 9).getRichStringCellValue().getString()));
            wujiang.setChuchang((int) row.getCell((short) 11).getNumericCellValue());
            service.saveWuJiang(wujiang);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>
  <head><title>Simple jsp page</title></head>
  <body>Place your content here</body>
</html>