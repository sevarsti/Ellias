package com.saille.sys.dao.mapper;

import com.sinitek.dao.jdbc.helper.BaseEntityRowMapper;
import com.saille.sys.Setting;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-1-24
 * Time: 19:49:47
 * To change this template use File | Settings | File Templates.
 */
public class SettingRowMapper extends BaseEntityRowMapper
            implements ParameterizedRowMapper<Setting> {
    public Setting mapRow( ResultSet rs, int rowNum) throws SQLException {
        Setting setting = new Setting();
        setting.setSetting(rs.getString("setting"));
        Object obj = rs.getObject("group");
        if(obj != null) {
            setting.setGroup(obj.toString());
        }
        setting.setName(rs.getString("name"));
        setting.setMemo(rs.getString("memo"));
        setting.setType(rs.getInt("type"));
        obj = rs.getObject("intValue");
        if(obj instanceof Integer) {
            setting.setIntValue(((Integer)obj).intValue());
        }
        obj = rs.getObject("numberValue");
        if(obj instanceof Number) {
            setting.setNumberValue(((Number)obj).doubleValue());
        }
        setting.setStrValue(rs.getString("strValue"));
        obj = rs.getObject("dateValue");
        if(obj instanceof Date) {
            setting.setDateValue((Date) obj);
        }
        obj = rs.getObject("pattern");
        if(obj instanceof String) {
            setting.setPattern(obj.toString());
        }
        return setting;
    }
}
