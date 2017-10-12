package com.saille.sys.util;

import com.saille.sys.Resource;
import com.saille.sys.Position;
import com.saille.sys.Employee;
import com.saille.sys.dao.ResourceDao;
import com.saille.sys.dao.PositionDao;
import com.saille.sys.dao.EmployeeDao;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.io.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2014-12-24
 * Time: 23:00:53
 * To change this template use File | Settings | File Templates.
 */
public class SysUtils {
    private final static Logger LOGGER = Logger.getLogger(SysUtils.class);
    public static Position getPosition(int posId) {
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        return dao.get(posId);
    }

    public static Employee getEmployee(int empId) {
        EmployeeDao dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        return dao.get(empId);
    }

    public static Resource getResource(int resId) {
        ResourceDao dao = (ResourceDao) GlobalContext.getContextBean(ResourceDao.class);
        return dao.get(resId);
    }

    public static String getPositionNames(String in) {
        PositionDao dao = (PositionDao) GlobalContext.getContextBean(PositionDao.class);
        StringBuffer sb = new StringBuffer();
        if(in == null || in.length() == 0) {
            return "";
        }
        String[] ids = in.split(",");
        for(String id : ids) {
            try {
                int i = Integer.parseInt(id);
                Position p = dao.get(i);
                if(p != null) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(p.getName()).append("(").append(p.getId()).append(")");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static String getEmployeeNames(String in) {
        EmployeeDao dao = (EmployeeDao) GlobalContext.getContextBean(EmployeeDao.class);
        StringBuffer sb = new StringBuffer();
        if(in == null || in.length() == 0) {
            return "";
        }
        String[] ids = in.split(",");
        for(String id : ids) {
            try {
                int i = Integer.parseInt(id);
                Employee e = dao.get(i);
                if(e != null) {
                    if(sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(e.getName()).append("(").append(e.getId()).append(")");
                }
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static boolean addTempFile(String filepath, byte[] content, long keepseconds) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        try {
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            Object[] param = new Object[3];
            param[0] = filepath;
            param[1] = sdf.format(now);
            param[2] = sdf.format(new Date(now.getTime() + keepseconds * 1000));
            jt.update("insert into sys_tempfile(filename, createtime, targettime, state) values(?,?,?,'1')", param);
            if(content != null) {
                File f = new File(filepath);
                if(f.exists()) {
                    LOGGER.error("�ļ��Ѵ���");
                    return false;
                }
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                fos.write(content);
                fos.close();
            }
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            LOGGER.error("�ļ�����ʧ��");
            return false;
        }
    }

    public static String[] callExternProgram(ProcessBuilder pb) {
        try {
            Process p = pb.start();
            String[] ret = new String[2];
            final InputStream is = p.getErrorStream();
            final StringBuilder sb = new StringBuilder();
            new Thread() {
                public void  run() {
                    try {
                        InputStreamReader isr = new InputStreamReader(is, Charset.forName("GBK"));
                        BufferedReader out = new BufferedReader(isr);
            //            BufferedReader out = new BufferedReader(new InputStreamReader(new BufferedInputStream(is), Charset.forName("GB2312")));
                        String temp;
                        System.out.println("====input stream====");
                        while((temp = out.readLine()) != null) {
                            System.out.println(temp);
                            sb.append(temp).append("\r\n");
                        }
                        out.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }.start();
            ret[1] = sb.toString();
            p.waitFor();
            InputStream is2 = p.getInputStream();
            InputStreamReader isr = new InputStreamReader(is2, Charset.forName("GBK"));
            BufferedReader out = new BufferedReader(isr);
            sb.delete(0, sb.length());
            System.out.println("====error stream====");
            String temp;
            while((temp = out.readLine()) != null) {
                System.out.println(temp);
                sb.append(temp).append("\r\n");
            }
            ret[0] = sb.toString();
            out.close();
            return ret;
        } catch (IOException ex) {
            ex.printStackTrace();
            return new String[2];
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return new String[2];
        }
    }
}
