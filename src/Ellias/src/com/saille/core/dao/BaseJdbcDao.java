package com.saille.core.dao;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.*;
import java.sql.Types;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sinitek.dao.BaseDao;
import com.sinitek.dao.AbstractJdbcQueryForm;
import com.sinitek.dao.AbstractJdbcQueryFormSimple;
import com.sinitek.dao.domain.BaseEntity;
import com.sinitek.dao.domain.Entity;
import com.sinitek.dao.domain.PropertyDescription;
import com.sinitek.dao.exception.DataStoreException;
import com.sinitek.dao.jdbc.helper.BaseCommonRowMapper;
import com.sinitek.dao.jdbc.helper.CommonEntityRowMapper;
import com.sinitek.dao.jdbc.helper.EntityRowMapper;
import com.sinitek.dao.jdbc.helper.GeneralRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;

public class BaseJdbcDao extends NamedParameterJdbcDaoSupport implements BaseDao {
    public BaseEntity load(final Class<? extends BaseEntity> cls, int id){
        String tableName = MapperUtils.getTableNameFromAnnotation(cls);
        String sql = "select * from " + tableName + " where objid=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
        logger.debug(sql + "\t" + id);
        BaseEntity obj = (BaseEntity)this.getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, new CommonEntityRowMapper(){
            @Override
            public BaseEntity getObject() throws Exception {
                return cls.newInstance();
            }
        });
        return obj;
    }

    public Object load(int id, final Class cls){
        String tableName = MapperUtils.getTableNameFromAnnotation(cls);
        String primaryKey = MapperUtils.getPrimaryKeyFromAnnotation(cls);
        String sql = "select * from " + tableName + " where " + primaryKey + "=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
        logger.debug(sql + "\t" + id);
        Object obj = this.getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, new GeneralRowMapper(){
            @Override
            public Object getObject() throws Exception {
                return cls.newInstance();
            }
        });
        return obj;
    }

    public void delete(BaseEntity obj){
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());
        String sql = "delete from " + tableName + " where objid=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",obj.getId());
        logger.debug(sql + "\t" + obj.getId());
        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
    }

    public void remove(BaseEntity obj){
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());
        String sql = "update `" + tableName + "` set removetag='1' where objid=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",obj.getId());
        logger.debug(sql + "\t" + obj.getId());
        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
    }

    public int getId(Class<? extends BaseEntity> cls) {
        return this.getId(MapperUtils.getTableNameFromAnnotation( cls));
    }

    public int getId(String tableName ) {
        synchronized(this){
            try{
                String sql = "select CURRENT_ID from entityconfig where name=:name";
                MapSqlParameterSource namedParameters = new MapSqlParameterSource("name",tableName.toUpperCase());

                int id = this.getNamedParameterJdbcTemplate().queryForInt(sql, namedParameters);

                id++;
                sql = "update entityconfig set CURRENT_ID=:id where name=:name";
                namedParameters.addValue("id", id);
                int i = this.getNamedParameterJdbcTemplate().update(sql, namedParameters);

                return id;
            }catch(EmptyResultDataAccessException e){
                //如果原来的entityconfig里面没有这个就创建一个记录
                String sql = "insert into entityconfig values(?,?,now(),now())";
                int i = this.getJdbcTemplate().update( sql, new Object[]{tableName.toUpperCase( ), 1});
                logger.info( "EntityConfig created for table:" + tableName);
                return 1;
            }

        }
    }

    public void update(BaseEntity obj) throws DataAccessException {
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());
        String entityName = MapperUtils.getEntityNameFromAnnotation(obj.getClass());

        if(StringUtils.isEmpty(tableName)){
            throw new DataStoreException("Class must has ClassDescription Annotation:" + obj.getClass());
        }

        int clsid = obj.getClsid();
        if(!StringUtils.isEmpty(entityName)){
            Entity en = this.findEntityByName( entityName);
            if(en!=null){
                clsid = en.getEntity_id();
            }
        }
        obj.setClsid( clsid);

        //update fields
        obj.setUpdatetimestamp(MapperUtils.getTimestamp());
        obj.setVersion( obj.getVersion()+1);


        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",obj.getId());
        String sql = this.getUpdateSql(obj, namedParameters);
//		sql = sql + "where objid=:id";
        logger.debug(sql);

        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
    }

    public void update(Object obj ) throws DataAccessException {
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());

        if(StringUtils.isEmpty(tableName)){
            throw new DataStoreException("Class must has ClassDescription Annotation:" + obj.getClass());
        }

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String sql = this.getUpdateSql(obj, namedParameters);
        logger.debug(sql);
        Iterator<Map.Entry> it = namedParameters.getValues().entrySet().iterator();
        while(it.hasNext()){
            Map.Entry entry = it.next();
            logger.debug(entry.getKey() + "\t" + entry.getValue());
        }


        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);

    }

    public void update(Object obj, String tableName) throws DataAccessException {
        if(StringUtils.isEmpty(tableName)) {
            throw new DataStoreException("Class must has ClassDescription Annotation:" + obj.getClass());
        }

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        String sql = getUpdateSql(obj, tableName, namedParameters);
        this.logger.debug(sql);
        Iterator it = namedParameters.getValues().entrySet().iterator();
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            this.logger.debug(entry.getKey() + "\t" + entry.getValue());
        }

        getNamedParameterJdbcTemplate().update(sql, namedParameters);
    }

    private String getUpdateSql(Object obj, MapSqlParameterSource namedParameters){
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());
        return getUpdateSql(obj, tableName, namedParameters);
    }

    private String getUpdateSql(Object obj, String tableName, MapSqlParameterSource namedParameters) {
        String ups = "";
        String wheres = "";

        for(Method m : obj.getClass().getMethods()) {
            if((m.getName().startsWith("get")) || (m.getName().startsWith("is"))) {
                PropertyDescription ann = null;
                if(m.isAnnotationPresent(PropertyDescription.class)) {
                    ann = (PropertyDescription) m.getAnnotation(PropertyDescription.class);
                }

                if((ann == null) || (!ann.persistant())) {
                    continue;
                }
                String paramName = MapperUtils.getPropertyName(m.getName());
                String columnName = ann != null ? ann.columnName() : null;

                this.logger.debug(paramName);
                if(StringUtils.isEmpty(columnName)) {
                    columnName = paramName;
                }

                if(!ann.primaryKey()) {
                    ups = ups + "`" + columnName + "`=:" + paramName + ",";
                } else {
                    if(!wheres.equals("")) {
                        wheres = wheres + " AND ";
                    }
                    wheres = wheres + columnName + "=:" + paramName;
                }
                try {
                    Object v = m.invoke(obj, new Object[0]);
                    this.logger.debug(paramName + "\t" + v);
                    namedParameters.addValue(paramName, v);
                } catch(Exception e) {
                    throw new DataStoreException("invoke:" + m.getName() + " failed!", e);
                }
            }
        }
        if(ups.length() > 0) {
            ups = ups.substring(0, ups.length() - 1);
        }
        if(ups.length() > 0) {
            String sql = "update `" + tableName + "` set " + ups + " where " + wheres;
            return sql;
        }
        throw new DataStoreException("Can not find any field to save." + obj.getClass());
    }

    public void insert(BaseEntity obj) throws DataAccessException {
        if(obj.getId()==0){
            throw new DataStoreException("No ID Set!");
        }

        String entityName = MapperUtils.getEntityNameFromAnnotation(obj.getClass());
//        logger.info("entityName:" + entityName);
        int clsid = obj.getClsid();
        if(!StringUtils.isEmpty(entityName)){
            Entity en = this.findEntityByName( entityName);
            if(en!=null){
                clsid = en.getEntity_id();
            }
        }
        logger.debug("clsid:" + clsid);
        obj.setRemovetag( "0");
        obj.setClsid( clsid);

        //update
        obj.setCreatetimestamp(MapperUtils.getTimestamp());
        obj.setUpdatetimestamp(MapperUtils.getTimestamp());

        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String sql = getInsertSql(obj, namedParameters);
        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
        logger.debug(sql + " saved as:" + obj.getId());

    }

    public void insert(Object obj) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String sql = getInsertSql(obj, namedParameters);

        this.getNamedParameterJdbcTemplate().update(sql, namedParameters);
        logger.debug(sql + " saved.");
    }

    public void insert(Object obj, String tableName) throws DataAccessException {
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        String sql = getInsertSql(obj, tableName, namedParameters);

        getNamedParameterJdbcTemplate().update(sql, namedParameters);
        this.logger.debug(sql + " saved.");
    }

    private String getInsertSql(Object obj, MapSqlParameterSource namedParameters) {
        String tableName = MapperUtils.getTableNameFromAnnotation(obj.getClass());
        this.logger.debug("tablename:" + tableName);
        if(StringUtils.isEmpty(tableName)) {
            throw new DataStoreException("Class must has ClassDescription Annotation:" + obj.getClass());
        }

        return getInsertSql(obj, tableName, namedParameters);
    }

    /**
     *
     * @param obj
     * @param namedParameters, will return in this map
     * @return String
     */
    private String getInsertSql(Object obj, String tableName, MapSqlParameterSource namedParameters) {
        logger.debug("tablename:" + tableName);
        if(StringUtils.isEmpty(tableName)){
            throw new DataStoreException("Class must has ClassDescription Annotation:" + obj.getClass());
        }

//		column names
        String fields="";

        //parameter holders
        String ups = "";

        for (Method m : obj.getClass().getMethods()) {
            if (m.getName().startsWith("get") || m.getName().startsWith("is")) {
                logger.debug("process:" + m.getName());
                PropertyDescription ann = null;
                if(m.isAnnotationPresent(PropertyDescription.class)){
                    ann = (PropertyDescription) m
                        .getAnnotation(PropertyDescription.class);
                }
                logger.debug(m.getName() + "\t" + ann);
                if(ann==null || !ann.persistant()){
                    continue;
                }

                String paramName = MapperUtils.getPropertyName(m.getName());
                String columnName = ann!=null ? ann.columnName() : null;

                if (StringUtils.isEmpty(columnName)) {
                    columnName = paramName;
                }

                if (!StringUtils.isEmpty(columnName)) {
                    fields += columnName + ",";
                    ups += ":" + paramName + ",";
                    try{
                        Object v = m.invoke(obj, new Object[] {});
                        logger.debug(paramName + "\t" + v);

//                        Class cls = m.getReturnType();
//                        logger.debug("return type:" + cls);
                        int _sqlType = ann.sqlType();
//                        if(_sqlType<=0){
//                            if(cls==String.class){
//                                _sqlType = Types.VARCHAR;
//                            }else if(cls==Integer.class){
//                                _sqlType = Types.INTEGER;
//                            }else if(cls==Double.class || cls==Float.class){
//                                _sqlType = Types.FLOAT;
//                            }
//                        }
                        if(_sqlType>0){
                            logger.debug(paramName + "\t使用SQLTYPE：" + _sqlType);
                            if(v!=null){
                                if(v.getClass().equals(Boolean.class)
                                        && (_sqlType == Types.CHAR || _sqlType == Types.VARCHAR)){
                                    v = ((Boolean)v) ? "1" : "0";
                                    logger.debug("把Boolean转换为Char:" + v);
                                }
                            }
                            namedParameters.addValue(paramName, v, _sqlType);
                        }else{
                            namedParameters.addValue(paramName, v);
                        }
                    }catch(Exception e){
                        throw new DataStoreException("invoke:" + m.getName() + " failed!", e);
                    }
                }
            }
        }
        if(fields.length()>0){
            fields = fields.substring(0,fields.length()-1);
        }

        if(ups.length()>0){
            ups = ups.substring(0,ups.length()-1);
        }

        if(fields.length()>0 && ups.length()>0){
            String sql = "insert into `" + tableName + "` (" + fields + ") values(" + ups + ")";

            return sql;
        }else{
            throw new DataStoreException( "Can not find any field to save." + obj.getClass());
        }
    }

    public Entity getEntity(int clsId){
        String sql = "select * from entity where ENTITY_ID=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id",clsId);

        Entity obj = (Entity)this.getNamedParameterJdbcTemplate().queryForObject(
                sql, namedParameters, new EntityRowMapper());

        return obj;
    }

    public List<Entity> getEntityList(){
        String sql = "select * from entity";

        List<Entity> entities = this.getJdbcTemplate().query(
                sql,new EntityRowMapper());

        return entities;
    }

    public Entity findEntityByTable(String tablename) {
        String sql = "select * from entity where DB_TABLENAME=:name";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("name",tablename.toUpperCase());

        Entity obj = (Entity)this.getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, new EntityRowMapper());

        return obj;
    }

    public Entity findEntityByName(String entityName ){
        String sql = "select * from entity where ENTITY_NAME=:name";
        logger.debug( sql);
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("name",entityName.toLowerCase( ));

        try{
            Entity obj = (Entity)this.getNamedParameterJdbcTemplate().queryForObject(sql, namedParameters, new EntityRowMapper());
            if(obj!=null){
                logger.debug("found entity:" + obj.getEntity_name() + " " + obj.getEntity_id());
            }
            return obj;
        }catch( EmptyResultDataAccessException e ){
            logger.error(e.getMessage());
        }

        return null;
    }

    /**
     * insert or update
     * @param be
     */
    public void saveBaseEntity(BaseEntity be){
        if(be.getId()==0){
            int objid = this.getId(be.getClass() );
            be.setId( objid);
            this.insert( be);
        }else{
            this.update( be);
        }
    }

    public void createTable(String filename) throws Exception {


        FileInputStream fis = new FileInputStream(filename);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[100];
        int i = -1;
        while((i=fis.read(buffer, 0, buffer.length))!=-1){
            bos.write(buffer, 0, i);
        }

        String text = new String(bos.toByteArray(),"GBK");

        bos.close();
        fis.close();

        List<String> sqls = new ArrayList<String>();
        StringReader sr = new StringReader(text);
        BufferedReader br = new BufferedReader(sr);
        String _b = "";
        String line = null;
        while((line = br.readLine())!=null){
            if(StringUtils.isEmpty(line.trim()))
                continue;
            _b += line;
            if(line.endsWith(";")){
                sqls.add(_b.replace(";", ""));
                _b = "";
            }
        }

        for(String sql : sqls){
            logger.debug(sql);
            try{
                this.getJdbcTemplate().execute(sql);
            }catch(DataAccessException e){
                logger.error(e.getMessage());
            }
        }
    }

    public boolean checkSimpleType(PropertyDescriptor pd){
        Class pc = pd.getPropertyType();
        boolean validType =
                (pc==Integer.TYPE ||
                        pc==Double.TYPE ||
                        pc==Float.TYPE ||
                pc==Integer.class ||
                pc==Double.class ||
                pc==Float.class ||
                pc==String.class ||
                pc== Date.class
                );
        if(validType){
            Method m = pd.getReadMethod();

            if(m.isAnnotationPresent(PropertyDescription.class)){
                PropertyDescription ann = (PropertyDescription) m
                    .getAnnotation(PropertyDescription.class);

                validType = ann.persistant();
            }
        }

        return validType;
    }


    public String getColumnName(PropertyDescriptor pd){
        String columnName = pd.getName();
         Method m = pd.getReadMethod();
            if(m.isAnnotationPresent(PropertyDescription.class)){
                PropertyDescription ann = (PropertyDescription) m
                    .getAnnotation(PropertyDescription.class);
                if(ann.columnName()!=null){
                    columnName = ann.columnName();
                }
            }

        return columnName;
    }

    /**
     * insert
     * @param obj
     * @param tablename - table
     */
     public void saveCommon(Object obj, String tablename){

            List<Object> params = new ArrayList<Object>();
            String _sql = "";
            String _values = "";
            try{
                PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(obj );

                for(PropertyDescriptor pd : pds){
                    if(checkSimpleType(pd)){
                        _sql += getColumnName(pd) + ",";
                        _values += "?,";
                        Object v = PropertyUtils.getProperty( obj, pd.getName());
                        params.add( v);
//                        LOGGER.info( pd.getName() + "==>" + v);
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
            if(params.size()>0){
                _sql = _sql.substring(0, _sql.length()-1);
                _values = _values.substring(0, _values.length()-1);
                String sql = "insert into `" +  tablename + "` (" + _sql + ") values(" + _values + ")";

                logger.debug(sql + "\tParams:" + params.size());
                this.getJdbcTemplate().update(sql, params.toArray() );

                logger.debug("Insert Success!");
            }
    }

    /**
     * update into database
     * @param obj
     * @param tablename
     * @param where clause, no "where"
     */
     public void updateCommon(Object obj, String tablename, String where){

         List<Object> params = new ArrayList<Object>();
         String _sql = "";

         try{
             PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(obj );

             for(PropertyDescriptor pd : pds){
                 if(checkSimpleType(pd)){
                     _sql += pd.getName() + "=?,";

                     Object v = PropertyUtils.getProperty( obj, pd.getName());
                     params.add( v);
//                     LOGGER.info( pd.getName() + "==>" + v);
                 }
             }
         }catch(Exception e){
             e.printStackTrace();
         }
         if(params.size()>0){
             _sql = _sql.substring(0, _sql.length()-1);

             String sql = "update `" +  tablename + "` set " + _sql + " where " + where;

             logger.debug(sql + "\tParams:" + params.size());
             this.getJdbcTemplate().update(sql, params.toArray() );

             logger.debug("Update Success!");
         }
 }

    public Object loadCommon(final Class clz, String from, BaseCommonRowMapper rowmapper){
        String _sql = "";

        try{
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clz );

            for(PropertyDescriptor pd : pds){
                if(checkSimpleType(pd)){
                    _sql += getColumnName(pd) + ",";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        _sql = _sql.substring(0, _sql.length()-1);
        String sql = "select " + _sql +  " from " + from;

        logger.debug(sql);
        Object ret = this.getJdbcTemplate().queryForObject( sql, rowmapper);

        logger.debug("Select Success!" + ret);

        return ret;
    }

    public List findCommon(final Class clz, String from, BaseCommonRowMapper rowmapper){
        String _sql = "";

        try{
            PropertyDescriptor[] pds = PropertyUtils.getPropertyDescriptors(clz );

            for(PropertyDescriptor pd : pds){
//                logger.info( pd.getName() + "\t" + pd.getPropertyType());
                if(checkSimpleType(pd)){
                    _sql += getColumnName(pd) + ",";
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        _sql = _sql.substring(0, _sql.length()-1);
        String sql = "select " + _sql +  " from " + from;

        logger.debug(sql);
        List ret = this.getJdbcTemplate().query( sql, rowmapper);

        logger.debug("Select Success!");

        return ret;
    }

    public <T> List searchPage(T clz, AbstractJdbcQueryForm queryForm, RowMapper rowMapper){
        queryForm.processSql();
        List<Object> params = queryForm.getSqlParams();
        Object[] p = params.toArray( new Object[]{});

        logger.info(queryForm.getCountSql() + "\t" + Arrays.toString( p) );
        JdbcTemplate _t = this.getJdbcTemplate();
        int rowCount =  _t.queryForInt( queryForm.getCountSql(), p);
        queryForm.setNrOfElements( rowCount);

        params.add(queryForm.getRowStart());
        params.add(queryForm.getPageSize());
        p = params.toArray( new Object[]{});
        logger.info(queryForm.getPageSql() + "\t" + Arrays.toString( p) );
        List r = _t.query(
                queryForm.getPageSql(),p, rowMapper);
        queryForm.setPageList( r);
        queryForm.setResultSize(r.size());
        logger.info("return:" + r.size());

        return r;
    }

    public List<Map> searchPage(AbstractJdbcQueryFormSimple queryForm){
        queryForm.processSql();

        List<Object> params = queryForm.getSqlParams();
        Object[] p = params.toArray();

        logger.info(queryForm.getCountSql() + "\t" + Arrays.toString( p) );
        JdbcTemplate _t = this.getJdbcTemplate();
        int rowCount =  _t.queryForInt( queryForm.getCountSql(), p);
        queryForm.setNrOfElements( rowCount);

        params.add(queryForm.getRowStart());
        params.add(queryForm.getPageSize());
        p = params.toArray( new Object[]{});
        logger.info(queryForm.getPageSql() + "\t" + Arrays.toString( p) );
        List<Map> r = _t.queryForList(
                queryForm.getPageSql(),p);
        queryForm.setResultSize(r.size());
        logger.info("return:" + r.size());

        return r;
    }


    public String getTableName(Class cls){
        return MapperUtils.getTableNameFromAnnotation(cls);
    }

    public void execSqlCommon(String sql){
        this.getJdbcTemplate().execute( sql);
    }
}
