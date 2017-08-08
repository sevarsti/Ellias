package com.saille.bwdl.dao;

import com.saille.bwdl.BwdlSetting;
import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;

import java.util.List;

public class BwdlSettingDao extends BaseJdbcDao {
    public BwdlSetting getSetting(String setting, int version) {
        String sql = "SELECT * FROM SETTING WHERE SETTING = ?";
        List list = getJdbcTemplate().query(sql, new Object[]{setting}, new ObjectRowMapper(BwdlSetting.class));
        return list.size() > 0 ? (BwdlSetting) list.get(0) : null;
    }
}