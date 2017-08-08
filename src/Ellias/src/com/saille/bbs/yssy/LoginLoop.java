package com.saille.bbs.yssy;

import org.springframework.beans.factory.InitializingBean;
import org.apache.log4j.Logger;

import java.net.URL;
import java.io.InputStream;

import com.saille.html.HTMLUtil;
import com.saille.sys.BaseThread;

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
    private LoginLoop(int interval) {super(interval);}

    public static LoginLoop getInstance(int interval) {
        if(instance == null) {
            instance = new LoginLoop(interval);
            instance.setDaemon(true);
        }
        return instance;
    }

    public int execute() {
        HTMLUtil util = new HTMLUtil();
        try {
            LOGGER.info("loop login");
            util.logInYSSY("ir", "pmgkglor");
            LOGGER.info("loop login, sleep end");
        } catch(Exception ex) {
            System.out.println("error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return 0;
    }
}
