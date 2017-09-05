package com.saille.sys.loop;

import com.saille.sys.BaseThread;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Ellias on 2017-09-06.
 */
public class DeleteFileThread extends BaseThread {
    private final static Logger LOGGER = Logger.getLogger(DeleteFileThread.class);

    @Override
    protected int execute() {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String now = sdf.format(new Date());
        List<Map<String, Object>> list = jt.queryForList("select * from sys_tempfile where targettime <= ? and state = 1", new Object[]{now});
        for(Map<String, Object> map : list) {
            String filename = map.get("filename").toString();
            String targettime = map.get("targettime").toString();
            LOGGER.info("目标时间：" + targettime + "，当前时间：" + now + "，需要删除文件：\n" + filename);
            boolean done = delete(new File(filename));
            if(done) {
                jt.update("update sys_tempfile set state = 2, deletetime = ? where filename = ? and state = 1", new Object[]{now, filename});
            } else {
                LOGGER.info("文件删除失败");
            }
        }
        return 0;
    }

    private boolean delete(File f) {
        if(!f.exists()) {
            return true;
        }
        if(f.isDirectory()) {
            File[] files = f.listFiles();
            boolean alldone = true;
            for(File ff : files) {
                boolean ok = delete(ff);
                if(!ok) {
                    alldone = false;
                }
            }
            return alldone;
        } else {
            return f.delete();
        }
    }
}
