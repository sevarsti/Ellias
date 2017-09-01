package com.saille.sys.loop;

import com.GlobalConstant;
import com.saille.sys.BaseThread;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2017-4-12
 * Time: 17:25:17
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseBackupThread extends BaseThread {
    private final static String BACKUP_PATH = GlobalConstant.DISKPATH + "dbbak";
    private final static Logger LOGGER = Logger.getLogger(DatabaseBackupThread.class);
    public static void main(String[] args) {
        new DatabaseBackupThread().execute();
    }
    protected int execute() {
        try {
            Date now = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String pubdate = sdf.format(now);
            File backupfile = new File(BACKUP_PATH + "\\" + pubdate + ".sql");
            if(backupfile.exists()) {
                LOGGER.info("当天已经备份过数据库，不再备份");
                return 0;
            }
            backupfile.createNewFile();
            ProcessBuilder pb = new ProcessBuilder();
            pb = pb.command("mysqldump", "--default-character-set=gbk", "-uroot", "-phuaan", "ellias", "--ignore-table", "ellias.setting");
            Process p = pb.start();
            InputStream errorStream = p.getErrorStream();
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(errorStream)));

            FileOutputStream fos = new FileOutputStream(backupfile);
            InputStream is = p.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            is.close();

            String estr = err.readLine();
            if(estr != null) {
                System.out.println("\nError Info");
                System.out.println(estr);
            }
            errorStream.close();
            /* 备份setting */
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ellias", "root", "huaan");
//            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
//            Connection conn = ds.getConnection();
            PreparedStatement ps = conn.prepareStatement("select * from setting");
            ResultSet rs = ps.executeQuery();
            File settingfile = new File(BACKUP_PATH + "\\" + pubdate + "setting.txt");
            fos = new FileOutputStream(settingfile);
            while(rs.next()) {
                String s;
                s = rs.getString("SETTING");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("GROUP");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("NAME");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("MEMO");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("TYPE");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("INTVALUE");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("NUMBERVALUE");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("STRVALUE");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\t".getBytes());
                Date d = rs.getDate("DATEVALUE");
                if(d == null) {
                } else {
                    fos.write((d.getTime() + "").getBytes());
                }
                fos.write("\t".getBytes());
                s = rs.getString("PATTERN");
                if(s != null) {
                    fos.write(s.getBytes());
                }
                fos.write("\r\n".getBytes());
            }
            rs.close();
            ps.close();
            fos.close();
            LOGGER.info("数据库ellias备份完毕，备份时间：" + now);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
