package com.saille.bbs.yssy;

import com.saille.html.HTMLUtil;
import com.saille.sys.BaseThread;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Ellias
 * Date: 2013-3-5
 * Time: 23:59:03
 * To change this template use File | Settings | File Templates.
 */
public class LoginLoop extends BaseThread {
    private final Logger LOGGER = Logger.getLogger(this.getClass());
    private static LoginLoop instance = null;
    private static String id;
    private static String pwd;
    private LoginLoop() {
        super();
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        List<Map<String, Object>> list = jt.queryForList("select strvalue from setting where setting = 'YSSY_LOG_ID'");
        String value = list.get(0).get("strvalue").toString();
        String id = value.split("-")[0];
        String pwd = value.split("-")[1];
        this.id = id;
        this.pwd = pwd;
    }

    public int execute() {
        HTMLUtil util = new HTMLUtil();
        try {
            LOGGER.info("loop login");
            util.logInYSSY(id, pwd);
            LOGGER.info("loop login, sleep end");
        } catch(Exception ex) {
            System.out.println("error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
}
