import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2018-05-08
 * Time: 10:34
 * 读取excel文件并导入pkuxkx.db
 * To change this template use File | Settings | File Templates.
 */
public class ImportData {
    public static void main(String[] args) {
        try {
            String mushpath = "D:\\Ellias\\mushclient";
            Class.forName("org.sqlite.JDBC");

            while(true) {
                System.out.print("请输入需要导入的excel(不含.xlsx)：");
                String m = readLine();
                List<String> modules = new ArrayList<String>();
                if(m == null || m.length() == 0) {
                    File dir = new File(mushpath + "\\lua");
                    File[] files = dir.listFiles();
                    for(File f : files) {
                        String name = f.getName();
                        if(name.startsWith("~")) {
                            continue;
                        }
                        if(name.getBytes("GBK").length != name.length()) {
                            continue;
                        }
                        if(name.toLowerCase().endsWith(".xlsx")) {
                            String module = name.substring(0, name.length() - 5);
                            if(module.equalsIgnoreCase("dati")) {
                                continue;
                            }
                            modules.add(module);
                        }
                    }
                } else {
                    modules.add(m);
                }

                Connection conn = DriverManager.getConnection("jdbc:sqlite:" + mushpath + "\\data\\pkuxkx.db");
                for(int i = 0; i < modules.size(); i++) {
                    String module = modules.get(i);
                    System.out.println("(" + i + "/" + modules.size() + ")读取excel文件: " + module);
                    FileInputStream fis = new FileInputStream(new File(mushpath + "\\lua\\" + module + ".xlsx"));
                    XSSFWorkbook workbook = new XSSFWorkbook(fis);
                    System.out.println("导入trigger");
                    XSSFSheet sheet = workbook.getSheetAt(0);
                    importTrigger(module, sheet, conn);
                    System.out.println("导入alias");
                    sheet = workbook.getSheetAt(1);
                    if(sheet != null) {
                        importAlias(module, sheet, conn);
                    }
                    fis.close();

                }
                conn.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void importAlias(String module, XSSFSheet sheet, Connection conn) throws Exception {
        PreparedStatement ps2 = conn.prepareStatement("delete from `alias` where module = ?");
        ps2.setBytes(1, module.getBytes());
        ps2.executeUpdate();
        ps2.close();
        PreparedStatement ps = conn.prepareStatement("insert into `alias`(`module`,`group`,`name`,`text`,`command`,`sendto`) values(?,?,?,?,?,?)");
        int count = (int)sheet.getRow(0).getCell(0).getNumericCellValue();
        for(int i = 2;;i++) {
            System.out.println((i-1) + "/" + count);
            XSSFRow row = sheet.getRow(i);
            if(row == null) {
                break;
            }
            XSSFCell cell = row.getCell(0);
            if(cell == null) {
                break;
            }
            String group = cell.getRichStringCellValue().getString();
            String text = row.getCell(1).getRichStringCellValue().getString();
            String command = row.getCell(2).getRichStringCellValue().getString();
            String name = row.getCell(3).getRichStringCellValue().getString();
            int sendto = (int)row.getCell(4).getNumericCellValue();
            ps.setBytes(1, module.getBytes("GBK"));
            ps.setBytes(2, group.getBytes("GBK"));
            ps.setBytes(3, name.getBytes("GBK"));
            ps.setBytes(4, text.getBytes("GBK"));
            ps.setBytes(5, command.getBytes("GBK"));
            ps.setInt(6, sendto);
            ps.executeUpdate();
        }
    }

    private static void importTrigger(String module, XSSFSheet sheet, Connection conn) throws Exception {
        PreparedStatement ps2 = conn.prepareStatement("delete from `triggers` where module = ?");
        ps2.setBytes(1, module.getBytes());
        ps2.executeUpdate();
        ps2.close();

        PreparedStatement ps = conn.prepareStatement("insert into `triggers`(module,`group`,name,`text`,`command`,`enabled`,regex,expand,ignorecase,gag,sendto,callback,matchlines) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
        int count = (int)sheet.getRow(0).getCell(0).getNumericCellValue();
        for(int i = 2;;i++) {
            System.out.println((i-1) + "/" + count);
            XSSFRow row = sheet.getRow(i);
            if(row == null) {
                break;
            }
            XSSFCell cell = row.getCell(0);
            if(cell == null) {
                break;
            }
            String group = cell.getRichStringCellValue().getString();
            String text = row.getCell(1).getRichStringCellValue().getString();
            String command = row.getCell(2) == null ? "" : row.getCell(2).getRichStringCellValue().getString();
            cell = row.getCell(3);
            int enabled = cell == null ? 0 : (int)cell.getNumericCellValue();
            cell = row.getCell(4);
            int regex = cell == null ? 0 : (int)cell.getNumericCellValue();
            cell = row.getCell(5);
            int expand = cell == null ? 0 : (int)cell.getNumericCellValue();
            cell = row.getCell(6);
            int ignorecase = cell == null ? 0 : (int)cell.getNumericCellValue();
            cell = row.getCell(7);
            int gag = cell == null ? 0 : (int)cell.getNumericCellValue();
            String name = row.getCell(8).getRichStringCellValue().getString();
            int sendto = (int)row.getCell(9).getNumericCellValue();
            cell = row.getCell(10);
            String callback = cell == null ? "" : cell.getRichStringCellValue().getString();
            cell = row.getCell(11);
            int matchlines = cell == null ? 0 : (int)cell.getNumericCellValue();
            ps.setBytes(1, module.getBytes("GBK"));
            ps.setBytes(2, group.getBytes("GBK"));
            ps.setBytes(3, name.getBytes("GBK"));
            ps.setBytes(4, text.getBytes("GBK"));
            ps.setBytes(5, command.getBytes("GBK"));
            ps.setInt(6, enabled);
            ps.setInt(7, regex);
            ps.setInt(8, expand);
            ps.setInt(9, ignorecase);
            ps.setInt(10, gag);
            ps.setInt(11, sendto);
//                        ps.setString(12, callback);
            ps.setBytes(12, callback.getBytes("GBK"));
            ps.setInt(13, matchlines);
            ps.executeUpdate();
        }
        ps.close();
    }

    public static String readLine() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String ret = br.readLine();
        return ret;
    }
}
