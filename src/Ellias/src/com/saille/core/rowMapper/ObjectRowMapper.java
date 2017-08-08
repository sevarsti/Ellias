package com.saille.core.rowMapper;

import com.sinitek.dao.jdbc.helper.BaseEntityRowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ObjectRowMapper extends BaseEntityRowMapper implements ParameterizedRowMapper {
    private Class c;

    public ObjectRowMapper(Class clazz) {
        this.c = clazz;
    }

    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Object doc = this.c.newInstance();
            super.mapRow(doc, rs, rowNum);
            return doc;
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}