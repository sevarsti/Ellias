package com.saille.sys.loop;

import com.saille.sys.BaseThread;
import org.apache.log4j.Logger;

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
    private final static String BACKUP_PATH = "D:\\dbbak";
    private final static Logger LOGGER = Logger.getLogger(DatabaseBackupThread.class);
    private static DatabaseBackupThread instance = null;
    public static DatabaseBackupThread getInstance(int interval) {
        if(instance == null) {
            instance = new DatabaseBackupThread(interval);
            instance.setDaemon(true);
        }
        return instance;
    }

    private DatabaseBackupThread(int interval) {
        super(interval);
    }
    public static void main(String[] args) {
        new DatabaseBackupThread(10).execute();
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
            pb = pb.command("mysqldump", "--default-character-set=gbk", "-uroot", "-psjtuagent", "ellias");
            Process p = pb.start();
            BufferedReader err = new BufferedReader(new InputStreamReader(new BufferedInputStream(p.getErrorStream())));

            FileOutputStream fos = new FileOutputStream(backupfile);
            InputStream is = p.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            while((len = is.read(bytes)) > 0) {
                fos.write(bytes, 0, len);
            }
            fos.close();
            String estr = err.readLine();
            if(estr != null) {
                System.out.println("\nError Info");
                System.out.println(estr);
            }
            LOGGER.info("数据库ellias备份完毕，备份时间：" + now);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
