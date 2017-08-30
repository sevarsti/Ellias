package com.saille.sys;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.util.Date;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-13
 * Time: 23:15:36
 * To change this template use File | Settings | File Templates.
 */
public class Setting implements Serializable {
    private final static Logger LOGGER = Logger.getLogger(Setting.class);
    public final static int TYPE_INT = 1;
    public final static int TYPE_STRING = 2;
    public final static int TYPE_NUMBER = 3;
    public final static int TYPE_DATE = 4;
    
    private String setting;
    private String group;
    private String name;
    private String memo;
    private int type;
    private Integer intValue;
    private Double numberValue;
    private String strValue;
    private Date dateValue;
    private String pattern;

    public static String getSettingString(String key) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        String sql = "select strvalue from setting where setting = ?";
        List<Map<String, Object>> list = jt.queryForList(sql, new Object[]{key});
        if(list.size() != 1) {
            LOGGER.warn("配置项" + key + "的记录条数不正确：" + list.size());
            return null;
        }
        Map<String, Object> map = list.get(0);
        Object obj = map.get("strvalue");
        if(obj == null) {
            LOGGER.warn("配置项" + key + "的值为空");
            return null;
        }
        String value = obj.toString();
        return value;
    }

    public static int getSettingInteger(String key) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        String sql = "select intvalue from setting where setting = ?";
        List<Map<String, Object>> list = jt.queryForList(sql, new Object[]{key});
        if(list.size() != 1) {
            LOGGER.warn("配置项" + key + "的记录条数不正确：" + list.size());
            return 0;
        }
        Map<String, Object> map = list.get(0);
        Object obj = map.get("intvalue");
        if(obj == null) {
            LOGGER.warn("配置项" + key + "的值为空");
            return 0;
        }
        int intvalue = ((Number) map.get("intvalue")).intValue();
        return intvalue;
    }

    public String getSetting() {
        return setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getNumberValue() {
        return numberValue;
    }

    public void setNumberValue(Double numberValue) {
        this.numberValue = numberValue;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
