package servlet;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.jdbc.Batcher;
import org.hibernate.type.Type;

public class IDGenerator implements IdentifierGenerator, Configurable {
    private static final Logger logger = Logger.getLogger(IDGenerator.class);
    private static final String DEFAULT_TABLE_NAME = "ENTITYCONFIG";
    private String tableName = null;

    private String name = null;
    private String sqlSelect;
    private String sqlUpdate;

    public void configure(Type type, Properties params, Dialect d) throws MappingException {
        this.tableName = params.getProperty("tableName");
        if(this.tableName == null) {
            this.tableName = "ENTITYCONFIG";
        }
        this.name = params.getProperty("name").toUpperCase();

        this.sqlSelect = ("select current_id + step from " + this.tableName + " where name=?");
        this.sqlUpdate = ("update " + this.tableName + " set current_id=current_id+step" + " where name=?");
    }

    public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
        try {
            synchronized(IDGenerator.class) {
                logger.info(this.sqlSelect);
                logger.info(this.sqlUpdate);
                PreparedStatement selectStmt = session.getBatcher().prepareStatement(this.sqlSelect);

                PreparedStatement updateStmt = session.getBatcher().prepareStatement(this.sqlUpdate);
                try {
                    selectStmt.setString(1, this.name);
                    ResultSet rs = selectStmt.executeQuery();
                    int result = 1;
                    if(rs.next()) {
                        result = rs.getInt(1);
                    } else {
                        String sql = "insert into " + this.tableName + "( name, begin_id," + "current_id,step) values(?,1,1,1)";

                        PreparedStatement insertStmt = session.getBatcher().prepareBatchStatement(sql);

                        insertStmt.setString(1, this.name);
                        insertStmt.execute();
                    }

                    updateStmt.setString(1, this.name);
                    updateStmt.execute();

                    session.getBatcher().executeBatch();

                    logger.debug("MetaDBId gen id (" + this.name + ")=" + result);
//                    String sql = new Integer(result);

                    session.getBatcher().closeStatement(selectStmt);
                    session.getBatcher().closeStatement(updateStmt);
                    return result;
                } catch(SQLException sqle) {
                    logger.fatal("MetaDBID generate error, (tableName,name)=(" + this.tableName + "," + this.name, sqle);

                    throw sqle;
                } finally {
                    session.getBatcher().closeStatement(selectStmt);
                    session.getBatcher().closeStatement(updateStmt);
                }
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}