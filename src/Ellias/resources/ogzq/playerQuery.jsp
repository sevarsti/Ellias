<%--
  Created by IntelliJ IDEA.
  User: Ellias
  Date: 2014-3-3
  Time: 16:38:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="com.saille.ogzq.IDUtils" %>
<%@ page import="com.saille.ogzq.ArenaThread" %>
<%@ page import="java.io.File" %>
<%@ page import="java.io.FileInputStream" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFWorkbook" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFSheet" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFRow" %>
<%@ page import="org.apache.poi.hssf.usermodel.HSSFCell" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="com.saille.util.UtilFunctions" %>
<%@ page import="org.apache.log4j.Logger" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.*" %>
<%@ include file="../include/include.jsp"%>
<html>
    <head>
        <META HTTP-EQUIV="Pragma" CONTENT="no-cache" />
        <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache" />
        <!--<script type='text/javascript' src='../include/json.js'> </script>-->
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
        <script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/OgzqDwr.js'></script>
    </head>
<%!
    Logger LOGGER = Logger.getLogger(this.getClass());
    String[] ability = new String[]{"shemen", "tupo", "duanqiu", "chanqiu", "chuanqiu", "sudu", "pujiu", "chuji", "menjiang", "houwei", "zhongchang", "qianfeng"};
    String[] desc = new String[]{"射门", "突破", "断球", "铲球", "传球", "速度", "扑救", "出击", "门将", "后卫", "中场", "前锋"};

    public Map<String, String> getPlayer(String name, int pos, int level) throws Exception {
        File f = new File("D:\\excel\\1.xls");
        if(!f.exists()) {
            f = new File("C:\\Documents and Settings\\Ellias\\桌面\\1.xls");
        }
        FileInputStream fis = new FileInputStream(f);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheet("球员明细");
        Map<String, String> ret = new HashMap<String, String>();
        for(int i = 2; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if(row == null || row.getCell(1) == null) {
                continue;
            }
            if(!row.getCell(1).getRichStringCellValue().getString().equals(name)) {
                continue;
            }
            if(pos > 0) {
                if(Integer.parseInt(row.getCell(3).getRichStringCellValue().getString().substring(0, 1)) != pos) {
                    continue;
                }
            }
            if(level > 0) {
                if(Integer.parseInt(row.getCell(2).getRichStringCellValue().getString().substring(0, 1)) != level) {
                    continue;
                }
            }
            ret.put("name", name);
            ret.put("pos", row.getCell(3).getRichStringCellValue().getString().substring(1));
            ret.put("level", row.getCell(2).getRichStringCellValue().getString().substring(1));
            for(int j = 0; j < 9; j++) {
                HSSFCell cell = row.getCell(j + 4);
                if(cell != null) {
                    if(cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                        ret.put("lv" + j, cell.getRichStringCellValue().getString());
                    } else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        ret.put("lv" + j, ((int) cell.getNumericCellValue()) + "");
                    }
                }
            }
            break;
        }

        sheet = workbook.getSheet("球员");
        for(int i = 1; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if(row == null || row.getCell(3) == null) {
                continue;
            }
            if(!row.getCell(3).getRichStringCellValue().getString().equals(name)) {
                continue;
            }
            if(pos > 0) {
                if(Integer.parseInt(row.getCell(5).getRichStringCellValue().getString().substring(0, 1)) != pos) {
                    continue;
                }
            }
            if(level > 0) {
                if(Integer.parseInt(row.getCell(4).getRichStringCellValue().getString().substring(0, 1)) != level) {
                    continue;
                }
            }
            for(int j = 0; j < ability.length; j++) {
                HSSFCell cell = row.getCell(16 + j);
                if(cell != null) {
                    ret.put(ability[j], ((int) cell.getNumericCellValue()) + "");
                } else {
                    ret.put(ability[j], "??");
                }
            }
            HSSFCell cell = row.getCell(28);
            if(cell != null) {
                String s = cell.getRichStringCellValue().getString();
                StringBuffer sb = new StringBuffer();
                while(s.length() > 0) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(s.substring(1, 3));
                    s = s.substring(3);
                }
                ret.put("bestPlace", sb.toString());
                if(cell.getRichStringCellValue().getString().equals(row.getCell(5).getRichStringCellValue().getString())) {
                    ret.put("cuowei", "否");
                } else {
                    ret.put("cuowei", "是");
                }
            }
            break;
        }
        return ret;
    }

    public void log(HttpServletRequest request, String name, String pos, String level, String nick) {
        try {
            File f = new File("D:\\playerQuery.log");
            if(!f.exists()) {
                f.createNewFile();
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            FileOutputStream fos = new FileOutputStream(f, true);
            StringBuffer sb = new StringBuffer(sdf.format(new Date()) + "\t" + nick + "\t" + request.getRemoteAddr() + "\t" + name + "\t" + pos + "\t" + level + "\r\n");
            fos.write(sb.toString().getBytes());
            fos.close();
        } catch(Exception ex) {
            UtilFunctions.LogError(LOGGER, ex);
        }
    }

    public String checkEmail(String email, String pwd) throws Exception {
        File f = new File("D:\\excel\\1.xls");
        if(!f.exists()) {
            f = new File("C:\\Documents and Settings\\Ellias\\桌面\\1.xls");
        }
        FileInputStream fis = new FileInputStream(f);
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        HSSFSheet sheet = workbook.getSheet("密码");
        for(int i = 2; i <= sheet.getLastRowNum(); i++) {
            HSSFRow row = sheet.getRow(i);
            if(row.getCell(1) == null || !row.getCell(1).getRichStringCellValue().getString().startsWith(email)) {
                continue;
            }
            if(row.getCell(2) == null) {
                continue;
            }
            String p = "";
            if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                p = (int) row.getCell(2).getNumericCellValue() + "";
            } else if(row.getCell(2).getCellType() == HSSFCell.CELL_TYPE_STRING) {
                p = row.getCell(2).getRichStringCellValue().getString();
            }
            if(!p.equals(pwd)) {
                continue;
            }
            fis.close();
            return row.getCell(0).getRichStringCellValue().getString();
        }
        fis.close();
        return null;
    }

%>
<%
    String playerName = request.getParameter("playerName");
    String posStr = request.getParameter("pos");
    String levelStr = request.getParameter("level");
    String email = request.getParameter("email");
    String pwd = request.getParameter("pwd");
    int pos = 0, level = 0;
    try {
        pos = Integer.parseInt(posStr);
    } catch(Exception ex) {
    }
    try {
        level = Integer.parseInt(levelStr);
    } catch(Exception ex) {
    }
    Map<String, String> info = new HashMap<String, String>();
    String msg = "";
    if(StringUtils.isNotEmpty(playerName)) {
        String nick = this.checkEmail(email, pwd);
        if(nick == null) {
            msg = "帐号或密码错误，请联系缥缈过客";
        } else {
            log(request, playerName, posStr, levelStr, nick);
            info = this.getPlayer(playerName, pos, level);
        }
    }
%>
    <body>
    <form action="playerQuery.jsp" method="post">
        <%=msg%><br/>
        <table width="100%" border="0" cellpadding="1" cellspacing="1">
            <tr>
                <td class="fieldname">帐号：</td>
                <td class="fieldvalue">
                    <input type="text" name="email"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">密码：</td>
                <td class="fieldvalue">
                    <input type="password" name="pwd"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">姓名：</td>
                <td class="fieldvalue">
                    <input type="text" name="playerName"/>
                </td>
            </tr>
            <tr>
                <td class="fieldname">位置：</td>
                <td class="fieldvalue">
                    <select name="pos" id="pos">
                        <option value="0">不限</option>
                        <option value="1">门将</option>
                        <option value="2">后卫</option>
                        <option value="3">中场</option>
                        <option value="4">前锋</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="fieldname">品质：</td>
                <td class="fieldvalue">
                    <select name="level" id="level">
                        <option value="0">不限</option>
                        <option value="1">普通</option>
                        <option value="2">优秀</option>
                        <option value="3">精英</option>
                        <option value="4">杰出</option>
                        <option value="4">大牌</option>
                        <option value="4">巨星</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="查询" onclick="beforeSubmit();"/>
                </td>
            </tr>
        </table>
    </form>
    <table id="info" border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
            <th>姓名</th>
            <th>位置</th>
            <th>品质</th>
            <th>初始</th>
            <th>1级</th>
            <th>2级</th>
            <th>3级</th>
            <th>4级</th>
            <th>5级</th>
            <th>6级</th>
            <th>7级</th>
            <th>8级</th>
        </tr>
        <tr class="row1">
            <td><%=info.get("name")%></td>
            <td><%=info.get("pos")%></td>
            <td><%=info.get("level")%></td>
            <%
                for(int i = 0; i <= 8; i++) {
            %>
            <td><%=info.get("lv" + i)%></td>
            <%
                }
            %>
        </tr>
    </table>
    满级实力：
    <table border="0" cellpadding="1" cellspacing="1">
        <tr class="head">
                <%
                    for(int i = 0; i < this.ability.length; i++) {
                %>
            <td>
                <%=desc[i]%>
            </td>
                <%
                    }
                %>
            <td>最佳位置</td>
            <td>是否错位</td>
        </tr>
        <tr class="row1">
                <%
                    for(int i = 0; i < this.ability.length; i++) {
                %>
            <td>
                <%=info.get(ability[i])%>
            </td>
                <%
                    }
                %>
            <td><%=info.get("bestPlace")%></td>
            <td><%=info.get("cuowei")%></td>
        </tr>
    </table>
    </body>
<script type="text/javascript">
    function beforeSubmit() {
        var playerName = document.getElementsByName('playerName')[0].value;
        if(playerName == '') {
            alert('必须输入球员姓名');
            return;
        }
        document.forms[0].submit();
    }
</script>
</html>