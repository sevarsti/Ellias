package servlet;

import net.sf.hibernate.SessionFactory;
import org.apache.log4j.Logger;

public class MetaDBConfig {
    protected static final Logger logger = Logger.getLogger(MetaDBConfig.class);

    public static SessionFactory dbSessionFactory = null;

    public static SessionFactory cacheSessionFactory = null;

    public static String dataBaseType = "MYSQL";

    public static String leftSeparator = "";

    public static String rightSeparator = "";

    public static SessionFactory getCacheSessionFactory() {
        return cacheSessionFactory;
    }

    public static void setCacheSessionFactory(SessionFactory cacheSessionFactory) {
        cacheSessionFactory = cacheSessionFactory;
    }

    public static void setDbSessionFactory(SessionFactory dbSessionFactory) {
        dbSessionFactory = dbSessionFactory;
    }

    public static SessionFactory getDbSessionFactory() {
        return dbSessionFactory;
    }
}