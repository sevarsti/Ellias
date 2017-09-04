package servlet;

import com.saille.ogzq.AutoReloginLoopThread;
import com.saille.ogzq.dailyLoop.ArenaThread;
import com.saille.baidu.bos.SynchronizeExcel;
import com.saille.aliyun.OssUtils;
import com.saille.sys.BaseThread;
import com.saille.sys.Setting;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.text.MessageFormat;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class GlobalContext implements ApplicationContextAware, InitializingBean {
    private static ApplicationContext springContext = null;
    private static final Logger LOGGER = Logger.getLogger(GlobalContext.class);
    private String[] threads;
    private Map<String, Integer> threadsInterval;
    private int defaultInterval = 60;

    public void setThreads(String[] threads) {
        this.threads = threads;
    }

    public void setThreadsInterval(Map<String, Integer> threadsInterval) {
        this.threadsInterval = threadsInterval;
    }

    public static ApplicationContext getSpringContext() {
        return springContext;
    }

    public void setApplicationContext(ApplicationContext context) throws BeansException {
        springContext = context;
    }

    public void afterPropertiesSet() throws Exception {
        DataSource ds = (DataSource)GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        {
            String accessKeyId = Setting.getSettingString("OSS_ACCESSKEYID");
            String accessKeySecret = Setting.getSettingString("OSS_ACCESSKEYSECRET");
            OssUtils.init(accessKeyId, accessKeySecret);
            LOGGER.info("阿里云OSS密钥加载完毕");
        }

        {
            LOGGER.info("节奏大师根目录加载完毕");
        }

        {
            boolean startthread = true;
            int intvalue = Setting.getSettingInteger("STARTTHREAD");
            if(intvalue != 1) {
                startthread = false;
            }
            if(startthread) {
                /* 查询需要启动的进程 */
                String sql = "select class from sys_thread where state = '1'";
                List<Map<String, Object>> list = jt.queryForList(sql);
                for(Map<String, Object> m : list) {
                    String s = m.get("class").toString();
//                if(threads != null && threads.length > 0) {
//                    for(String s : threads) {
                        int interval = defaultInterval;
                        if(threadsInterval.get(s) != null) {
                            interval = threadsInterval.get(s).intValue();
                        }
                        BaseThread.createInstance(s, interval);
                    }
//                }
                LOGGER.info("所有线程启动完毕");
            } else {
                LOGGER.info("线程设置为不启动");
            }
        }
//        SynchronizeExcel synchronizeExcel = SynchronizeExcel.getInstance();
//        synchronizeExcel.start();
//        Thread t = new AutoReloginLoopThread();
//        t.start();
    }

    public static Object getContextBean(String name) {
        ApplicationContext springContext = GlobalContext.getSpringContext();
        return springContext.getBean(name);
    }

    public static Object getContextBean(Class cls) {
       ApplicationContext springContext = getSpringContext();
       String[] ss = springContext.getBeanNamesForType(cls);
       if(ss!=null && ss.length>0){
           if(ss.length==1){
               return springContext.getBean(ss[0]);
           }else{
               LOGGER.error( MessageFormat.format("Duplicated definition: {0}",cls));
           }
       }else{
           LOGGER.error(MessageFormat.format("No class definition found: {0}",cls));
       }

        return null;
   }
}