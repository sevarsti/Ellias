package servlet;

import com.saille.ogzq.AutoReloginLoopThread;
import com.saille.ogzq.dailyLoop.ArenaThread;
import com.saille.baidu.bos.SynchronizeExcel;
import com.saille.aliyun.OssUtils;
import com.saille.sys.BaseThread;
import com.saille.sys.Setting;
import com.saille.util.SendSMSUtils;
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
            LOGGER.info("������OSS��Կ�������");
        }

        {
            String accessKeySecret = Setting.getSettingString("MONYUN_APPKEY");
            SendSMSUtils.init(accessKeySecret);
            LOGGER.info("������Կ�������");
        }

        {
            LOGGER.info("�����ʦ��Ŀ¼�������");
        }

        {
            boolean startthread = true;
            int intvalue = Setting.getSettingInteger("STARTTHREAD");
            if(intvalue != 1) {
                startthread = false;
            }
            if(startthread) {
                /* ��ѯ��Ҫ�����Ľ��� */
                String sql = "select class, `interval`, state from sys_thread";
                List<Map<String, Object>> list = jt.queryForList(sql);
                for(Map<String, Object> m : list) {
                    String s = m.get("class").toString();
                    int interval = 0;
                    int start = ((Number) m.get("state")).intValue();
                    try {
                        interval = ((Number) m.get("interval")).intValue();
                    } catch (Exception ex) {}
                        if(interval == 0) {
                            interval = defaultInterval;
                        }
                        BaseThread.createInstance(s, start, interval);
                    }
                LOGGER.info("�����߳��������");
            } else {
                LOGGER.info("�߳�����Ϊ������");
            }
        }
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