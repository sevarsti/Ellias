import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\ellias\\mushclient\\data\\pkuxkx.db");
//            PreparedStatement ps = conn.prepareStatement("select count(1) from place");
//            ResultSet rs = ps.executeQuery();
//            if(rs.next()) {
//                System.out.println(rs.getInt(1));
//            }
//            PreparedStatement ps3 = conn.prepareStatement("select area_name from area limit 1");
//            ResultSet rs = ps3.executeQuery();
//            rs.next();
//            System.out.println(rs.getString(1));
            PreparedStatement ps = conn.prepareStatement("insert into `triggers`(module,`group`,name,`text`,`command`,`enabled`,regex,expand,ignorecase,gag,sendto,callback,matchlines) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            File dir = new File("D:\\ellias\\mushclient\\lua");
            File[] files = dir.listFiles();
            for(File f : files) {
                if(f.getName().toLowerCase().endsWith(".xlsx")) {
                    String module = f.getName().substring(0, f.getName().length() - 5);
                    if(module.equalsIgnoreCase("dati") || module.equalsIgnoreCase("newbie")) {
                        continue;
                    }
                    System.out.println("读取excel文件: " + f.getName());
                    PreparedStatement ps2 = conn.prepareStatement("delete from `triggers` where module = ?");
                    ps2.setBytes(1, module.getBytes());
                    ps2.executeUpdate();
                    ps2.close();
                    FileInputStream fis = new FileInputStream(f);
                    XSSFWorkbook workbook = new XSSFWorkbook(fis);
                    XSSFSheet sheet = workbook.getSheetAt(0);
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
//                        ps.setString(1, module);
                        ps.setBytes(1, module.getBytes("GBK"));
//                        ps.setString(2, group);
                        ps.setBytes(2, group.getBytes("GBK"));
//                        ps.setString(3, name);
                        ps.setBytes(3, name.getBytes("GBK"));
//                        ps.setString(4, text);
                        ps.setBytes(4, text.getBytes("GBK"));
//                        ps.setString(5, command);
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
                }
            }
            ps.close();
            conn.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
