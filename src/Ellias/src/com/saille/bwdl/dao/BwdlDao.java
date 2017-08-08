package com.saille.bwdl.dao;

import com.saille.bwdl.ChengShi;
import com.saille.bwdl.FangJu;
import com.saille.bwdl.ShiLi;
import com.saille.bwdl.Version;
import com.saille.bwdl.WuJiang;
import com.saille.bwdl.WuJiangChengShiRela;
import com.saille.bwdl.WuQi;
import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class BwdlDao extends BaseJdbcDao {
    public List<Version> findAllVersions() {
        return getJdbcTemplate().query("SELECT * FROM VERSION ORDER BY ID", new ObjectRowMapper(Version.class));
    }

    public Version getVersion(int versionId) {
        List list = getJdbcTemplate().query("SELECT * FROM VERSION WHERE ID = ?", new Object[]{Integer.valueOf(versionId)}, new ObjectRowMapper(Version.class));
        return list.size() > 0 ? (Version) list.get(0) : null;
    }

    public int saveVersion(Version version) {
        version.setUpdateTime(new Date());
        if(version.getId() > 0) {
            update(version);
        } else {
            version.setId(getId(MapperUtils.getTableNameFromAnnotation(version.getClass())));
            insert(version);
        }
        return version.getId();
    }

    public List<WuJiang> findAllWuJiangs() {
        List<WuJiang> list = getJdbcTemplate().query("SELECT * FROM WUJIANG ORDER BY NAME", new ObjectRowMapper(WuJiang.class));
        for(WuJiang w : list) {
            w.setInit(false);
        }
        return list;
    }

    public List<WuJiang> findAllWuJiangs(int version) {
        List<WuJiang> list = getJdbcTemplate().query("SELECT * FROM WUJIANG WHERE VERSION = ? ORDER BY NAME", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(WuJiang.class));
        for(WuJiang w : list) {
            w.setInit(false);
        }
        return list;
    }

    public List<WuJiang> findAllWuJiangsInit(int version) {
        List<WuJiang> list = getJdbcTemplate().query("SELECT * FROM WUJIANG_INIT WHERE VERSION = ? ORDER BY NAME", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(WuJiang.class));
        for(WuJiang w : list) {
            w.setInit(true);
        }
        return list;
    }

    public List<WuQi> findAllWuQis(int version) {
        return getJdbcTemplate().query("SELECT * FROM WUQI WHERE VERSION = ? ORDER BY TYPE, GONGJI", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(WuQi.class));
    }

    public WuQi getWuQi(int wuqiId) {
        List list = getJdbcTemplate().query("SELECT * FROM WUQI WHERE ID = ?", new Object[]{Integer.valueOf(wuqiId)}, new ObjectRowMapper(WuQi.class));
        return list.size() > 0 ? (WuQi) list.get(0) : null;
    }

    public int saveWuQi(WuQi wuqi) {
        wuqi.setUpdateTime(new Date());
        if(wuqi.getId() > 0) {
            update(wuqi);
        } else {
            wuqi.setId(getId(MapperUtils.getTableNameFromAnnotation(wuqi.getClass())));
            insert(wuqi);
        }
        return wuqi.getId();
    }

    public List<FangJu> findAllFangJus(int version) {
        return getJdbcTemplate().query("SELECT * FROM FANGJU WHERE VERSION = ? ORDER BY FANGYU", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(FangJu.class));
    }

    public FangJu getFangJu(int fangjuId) {
        List list = getJdbcTemplate().query("SELECT * FROM FANGJU WHERE ID = ?", new Object[]{Integer.valueOf(fangjuId)}, new ObjectRowMapper(FangJu.class));
        return list.size() > 0 ? (FangJu) list.get(0) : null;
    }

    public int saveFangJu(FangJu fangju) {
        fangju.setUpdateTime(new Date());
        if(fangju.getId() > 0) {
            update(fangju);
        } else {
            fangju.setId(getId(MapperUtils.getTableNameFromAnnotation(fangju.getClass())));
            insert(fangju);
        }
        return fangju.getId();
    }

    public WuJiang getWuJiang(int wujiangId) {
        List list = getJdbcTemplate().query("SELECT * FROM WUJIANG WHERE ID = ?", new Object[]{Integer.valueOf(wujiangId)}, new ObjectRowMapper(WuJiang.class));
        WuJiang ret = list.size() > 0 ? (WuJiang) list.get(0) : null;
        if(ret != null) {
            ret.setInit(false);
        }
        return ret;
    }

    public WuJiang getWuJiangInit(int wujiangId) {
        List list = getJdbcTemplate().query("SELECT * FROM WUJIANG_INIT WHERE ID = ?", new Object[]{Integer.valueOf(wujiangId)}, new ObjectRowMapper(WuJiang.class));
        WuJiang ret = list.size() > 0 ? (WuJiang) list.get(0) : null;
        if(ret != null) {
            ret.setInit(true);
        }
        return ret;
    }

    public int saveWuJiang(WuJiang wujiang) {
        wujiang.setUpdateTime(new Date());
        if(wujiang.isInit()) {
            String tableName = MapperUtils.getTableNameFromAnnotation(wujiang.getClass()) + "_INIT";
            if(wujiang.getId() > 0) {
                update(wujiang, tableName);
            } else {
                wujiang.setId(getId(tableName));
                insert(wujiang, tableName);
            }
        } else if(wujiang.getId() > 0) {
            update(wujiang);
        } else {
            wujiang.setId(getId(MapperUtils.getTableNameFromAnnotation(wujiang.getClass())));
            insert(wujiang);
        }

        return wujiang.getId();
    }

    public List<ChengShi> findAllChengShis() {
        List<ChengShi> list = getJdbcTemplate().query("SELECT * FROM CHENGSHI ORDER BY NAME", new ObjectRowMapper(ChengShi.class));
        for(ChengShi c : list) {
            c.setInit(false);
        }
        return list;
    }

    public List<ChengShi> findAllChengShis(int version) {
        List<ChengShi> list = getJdbcTemplate().query("SELECT * FROM CHENGSHI WHERE VERSION = ? ORDER BY NAME", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(ChengShi.class));
        for(ChengShi c : list) {
            c.setInit(false);
        }
        return list;
    }

    public List<ChengShi> findAllChengShisInit(int version) {
        List<ChengShi> list = getJdbcTemplate().query("SELECT * FROM CHENGSHI_INIT WHERE VERSION = ? ORDER BY NAME", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(ChengShi.class));
        for(ChengShi c : list) {
            c.setInit(true);
        }
        return list;
    }

    public ChengShi getChengShi(int chengshiId) {
        List list = getJdbcTemplate().query("SELECT * FROM CHENGSHI WHERE ID = ?", new Object[]{Integer.valueOf(chengshiId)}, new ObjectRowMapper(ChengShi.class));
        ChengShi ret = list.size() > 0 ? (ChengShi) list.get(0) : null;
        if(ret != null) {
            ret.setInit(false);
        }
        return ret;
    }

    public ChengShi getChengShiInit(int chengshiId) {
        List list = getJdbcTemplate().query("SELECT * FROM CHENGSHI_INIT WHERE ID = ?", new Object[]{Integer.valueOf(chengshiId)}, new ObjectRowMapper(ChengShi.class));
        ChengShi ret = list.size() > 0 ? (ChengShi) list.get(0) : null;
        if(ret != null) {
            ret.setInit(true);
        }
        return ret;
    }

    public int saveChengShi(ChengShi chengshi) {
        chengshi.setUpdateTime(new Date());
        if(chengshi.isInit()) {
            String tableName = MapperUtils.getTableNameFromAnnotation(chengshi.getClass()) + "_INIT";
            if(chengshi.getId() > 0) {
                update(chengshi, tableName);
            } else {
                chengshi.setId(getId(tableName));
                insert(chengshi, tableName);
            }
        } else if(chengshi.getId() > 0) {
            update(chengshi);
        } else {
            chengshi.setId(getId(MapperUtils.getTableNameFromAnnotation(chengshi.getClass())));
            insert(chengshi);
        }

        return chengshi.getId();
    }

    public List<WuJiangChengShiRela> findAllWuJiangChengShiRelas() {
        List<WuJiangChengShiRela> list = getJdbcTemplate().query("SELECT * FROM WUJIANGCHENGSHIRELA ORDER BY CHENGSHIID", new ObjectRowMapper(WuJiangChengShiRela.class));
        for(WuJiangChengShiRela w : list) {
            w.setInit(false);
        }
        return list;
    }

    public List<WuJiangChengShiRela> findAllWuJiangChengShiRelasInit() {
        List<WuJiangChengShiRela> list = getJdbcTemplate().query("SELECT * FROM WUJIANGCHENGSHIRELA_INIT ORDER BY CHENGSHIID, `INDEX`", new ObjectRowMapper(WuJiangChengShiRela.class));
        for(WuJiangChengShiRela w : list) {
            w.setInit(false);
        }
        return list;
    }

    public List<WuJiangChengShiRela> findAllWuJiangChengShiRelasInit(int version) {
        List<WuJiangChengShiRela> list = getJdbcTemplate().query("SELECT * FROM WUJIANGCHENGSHIRELA_INIT WHERE VERSION = ? ORDER BY CHENGSHIID, `INDEX`", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(WuJiangChengShiRela.class));
        for(WuJiangChengShiRela w : list) {
            w.setInit(true);
        }
        return list;
    }

    public List<WuJiang> findWuJiangsByChengShiInit(int chengshiId) {
        String sql = "SELECT A.* FROM WUJIANG_INIT A JOIN WUJIANGCHENGSHIRELA_INIT B ON A.ID = B.WUJIANGID WHERE B.CHENGSHIID = ? ORDER BY B.`INDEX`";
        List<WuJiang> ret = getJdbcTemplate().query(sql, new Object[]{Integer.valueOf(chengshiId)}, new ObjectRowMapper(WuJiang.class));
        for(WuJiang w : ret) {
            w.setInit(true);
        }
        return ret;
    }

    public List<WuJiangChengShiRela> findWuJiangChengShiRelasByChengShiInit(int chengshiId) {
        List<WuJiangChengShiRela> list = getJdbcTemplate().query("SELECT * FROM WUJIANGCHENGSHIRELA_INIT WHERE CHENGSHIID = ? ORDER BY `INDEX`", new Object[]{Integer.valueOf(chengshiId)}, new ObjectRowMapper(WuJiangChengShiRela.class));
        for(WuJiangChengShiRela r : list) {
            r.setInit(true);
        }
        return list;
    }

    public int deleteWuJiangChengShiRela(WuJiangChengShiRela rela) {
        String tableName = MapperUtils.getTableNameFromAnnotation(rela.getClass());
        if(rela.isInit()) {
            tableName = tableName + "_INIT";
        }
        String sql = "delete from " + tableName + " where id=:id";
        MapSqlParameterSource namedParameters = new MapSqlParameterSource("id", Integer.valueOf(rela.getId()));
        this.logger.debug(sql + "\t" + rela.getId());
        getNamedParameterJdbcTemplate().update(sql, namedParameters);
        return rela.getId();
    }

    public int saveWuJiangChengShiRela(WuJiangChengShiRela rela) {
        rela.setUpdateTime(new Date());
        String tableName = MapperUtils.getTableNameFromAnnotation(rela.getClass());
        if(rela.isInit()) {
            tableName = tableName + "_INIT";
        }
        if(rela.getId() > 0) {
            update(rela, tableName);
        } else {
            rela.setId(getId(tableName));
            insert(rela, tableName);
        }
        return rela.getId();
    }

    public List<ShiLi> findAllShilisInit(int version) {
        List<ShiLi> list = getJdbcTemplate().query("SELECT * FROM SHILI_INIT WHERE VERSION = ? ORDER BY `ID`", new Object[]{Integer.valueOf(version)}, new ObjectRowMapper(ShiLi.class));
        for(ShiLi s : list) {
            s.setInit(true);
        }
        return list;
    }

    public ShiLi getShiLiInit(int shiliId) {
        List list = getJdbcTemplate().query("SELECT * FROM SHILI_INIT WHERE ID = ?", new Object[]{Integer.valueOf(shiliId)}, new ObjectRowMapper(ShiLi.class));
        ShiLi ret = list.size() > 0 ? (ShiLi) list.get(0) : null;
        if(ret != null) {
            ret.setInit(true);
        }
        return ret;
    }

    public int saveShiLi(ShiLi shili) {
        shili.setUpdateTime(new Date());
        String tableName = MapperUtils.getTableNameFromAnnotation(shili.getClass());
        if(shili.isInit()) {
            tableName = tableName + "_INIT";
        }
        if(shili.getId() > 0) {
            update(shili, tableName);
        } else {
            shili.setId(getId(tableName));
            insert(shili, tableName);
        }
        return shili.getId();
    }
}