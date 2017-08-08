package com.saille.hhcq.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.saille.hhcq.Chushou;
import com.saille.hhcq.Gangkou;
import com.saille.hhcq.Guojia;
import com.saille.hhcq.Jiage;
import com.saille.hhcq.Juli;
import com.saille.hhcq.Leibie;
import com.saille.hhcq.Shangpin;
import com.sinitek.dao.jdbc.helper.MapperUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

public class HhcqDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public Guojia getGuojia(int id) {
        List list = getJdbcTemplate().query("select * from guojia where id = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Guojia.class));
        return list.size() > 0 ? (Guojia) list.get(0) : null;
    }

    public Leibie getLeibie(int id) {
        List list = getJdbcTemplate().query("select * from leibie where id = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Leibie.class));
        return list.size() > 0 ? (Leibie) list.get(0) : null;
    }

    public Gangkou getGangkou(int id) {
        List list = getJdbcTemplate().query("select * from gangkou where id = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Gangkou.class));
        return list.size() > 0 ? (Gangkou) list.get(0) : null;
    }

    public Shangpin getShangpin(int id) {
        List list = getJdbcTemplate().query("select * from shangpin where id = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Shangpin.class));
        return list.size() > 0 ? (Shangpin) list.get(0) : null;
    }

    private Chushou getChushou(int id) {
        List list = getJdbcTemplate().query("select * from chushou where id = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Chushou.class));
        return list.size() > 0 ? (Chushou) list.get(0) : null;
    }

    public Leibie getLeibieByName(String name) {
        List list = getJdbcTemplate().query("select * from leibie where name = ?", new Object[]{name}, new ObjectRowMapper(Leibie.class));
        return list.size() > 0 ? (Leibie) list.get(0) : null;
    }

    public List<Gangkou> getAllGangkou() {
        return getJdbcTemplate().query("select * from gangkou order by id desc", new ObjectRowMapper(Gangkou.class));
    }

    public List<Shangpin> getAllShangpin() {
        return getJdbcTemplate().query("select * from shangpin order by pinyin", new ObjectRowMapper(Shangpin.class));
    }

    public List<Guojia> getAllGuojia() {
        return getJdbcTemplate().query("select * from guojia order by pinyin", new ObjectRowMapper(Guojia.class));
    }

    public List<Leibie> getAllLeibie() {
        return getJdbcTemplate().query("select * from leibie order by pinyin", new ObjectRowMapper(Leibie.class));
    }

    public List<Jiage> getAllJiage() {
        return getJdbcTemplate().query("select * from jiage", new ObjectRowMapper(Jiage.class));
    }

    public List<Chushou> getAllChushou() {
        return getJdbcTemplate().query("select * from chushou", new ObjectRowMapper(Chushou.class));
    }

    public List<Gangkou> getGangkouByGuojia(int id) {
        return getJdbcTemplate().query("select * from gangkou where guojia = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Gangkou.class));
    }

    public Gangkou getGangkouByName(String name) {
        List list = getJdbcTemplate().query("select * from gangkou where name = ?", new Object[]{name}, new ObjectRowMapper(Gangkou.class));
        return list.size() > 0 ? (Gangkou) list.get(0) : null;
    }

    public List<Shangpin> getShangpinByLeibie(int id) {
        return getJdbcTemplate().query("select * from shangpin where leibie = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Shangpin.class));
    }

    public Shangpin getShangpinByName(String name) {
        List list = getJdbcTemplate().query("select * from shangpin where name = ?", new Object[]{name}, new ObjectRowMapper(Shangpin.class));
        return list.size() > 0 ? (Shangpin) list.get(0) : null;
    }

    public List<Chushou> getChushouByGangkou(int id) {
        return getJdbcTemplate().query("select * from chushou where gangkouId = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Chushou.class));
    }

    public List<Chushou> getChushouByShangpin(int id) {
        return getJdbcTemplate().query("select * from chushou where shangpinId = ?", new Object[]{Integer.valueOf(id)}, new ObjectRowMapper(Chushou.class));
    }

    public Juli getJuliByGangkou(int gangkou1, int gangkou2) {
        List list = getJdbcTemplate().query("select * from juli where gangkou1id = ? and gangkou2id = ?", new Object[]{Integer.valueOf(gangkou1), Integer.valueOf(gangkou2)}, new ObjectRowMapper(Juli.class));
        return list.size() > 0 ? (Juli) list.get(0) : null;
    }

    public List<Juli> getJuliByGangkou(int gangkou) {
        return getJdbcTemplate().query("select * from juli where gangkou1id = ?", new Object[]{Integer.valueOf(gangkou)}, new ObjectRowMapper(Juli.class));
    }

    public List<Jiage> getJiageByShangpin(int shangpinId) {
        return getJdbcTemplate().query("select * from jiage where shangpinId = ?", new Object[]{Integer.valueOf(shangpinId)}, new ObjectRowMapper(Jiage.class));
    }

    public int saveGuojia(Guojia guojia) {
        if(guojia.getId() > 0) {
            update(guojia);
        } else {
            guojia.setId(getId(MapperUtils.getTableNameFromAnnotation(guojia.getClass())));
            insert(guojia);
        }
        return guojia.getId();
    }

    public int saveGangkou(Gangkou gangkou) {
        if(gangkou.getId() > 0) {
            update(gangkou);
        } else {
            gangkou.setId(getId(MapperUtils.getTableNameFromAnnotation(gangkou.getClass())));
            insert(gangkou);
        }
        return gangkou.getId();
    }

    public int saveJiage(Jiage jiage) {
        if(jiage.getId() > 0) {
            update(jiage);
        } else {
            jiage.setId(getId(MapperUtils.getTableNameFromAnnotation(jiage.getClass())));
            insert(jiage);
        }
        return jiage.getId();
    }

    public int saveShangpin(Shangpin shangpin) {
        if(shangpin.getId() > 0) {
            update(shangpin);
        } else {
            shangpin.setId(getId(MapperUtils.getTableNameFromAnnotation(shangpin.getClass())));
            insert(shangpin);
        }
        return shangpin.getId();
    }

    public int saveLeibie(Leibie leibie) {
        if(leibie.getId() > 0) {
            update(leibie);
        } else {
            leibie.setId(getId(MapperUtils.getTableNameFromAnnotation(leibie.getClass())));
            insert(leibie);
        }
        return leibie.getId();
    }

    public int saveChushou(Chushou chushou) {
        if(chushou.getId() > 0) {
            update(chushou);
        } else {
            chushou.setId(getId(MapperUtils.getTableNameFromAnnotation(chushou.getClass())));
            insert(chushou);
        }
        return chushou.getId();
    }

    public int saveJuli(Juli juli) {
        if(juli.getId() > 0) {
            update(juli);
        } else {
            juli.setId(getId(MapperUtils.getTableNameFromAnnotation(juli.getClass())));
            insert(juli);
        }
        return juli.getId();
    }

    public List<Jiage> getJiage(int gangkouId, int shangpinId) {
        if((gangkouId == 0) && (shangpinId == 0)) {
            return getJdbcTemplate().query("select * from jiage", new ObjectRowMapper(Jiage.class));
        }
        String sql = "select * from jiage where 1=1";
        List params = new ArrayList();
        if(gangkouId != 0) {
            sql = sql + " and gangkouId = ?";
            params.add(Integer.valueOf(gangkouId));
        }
        if(shangpinId != 0) {
            sql = sql + " and shangpinId = ?";
            params.add(Integer.valueOf(shangpinId));
        }
        return getJdbcTemplate().query(sql, params.toArray(), new ObjectRowMapper(Jiage.class));
    }

    public List<Chushou> getChushou(int gangkouId, int shangpinId) {
        if((gangkouId == 0) && (shangpinId == 0)) {
            return getJdbcTemplate().query("select * from chushou", new ObjectRowMapper(Chushou.class));
        }
        String sql = "select * from chushou where 1=1";
        List params = new ArrayList();
        if(gangkouId != 0) {
            sql = sql + " and gangkouId = ?";
            params.add(Integer.valueOf(gangkouId));
        }
        if(shangpinId != 0) {
            sql = sql + " and shangpinId = ?";
            params.add(Integer.valueOf(shangpinId));
        }
        return getJdbcTemplate().query(sql, params.toArray(), new ObjectRowMapper(Chushou.class));
    }

    public int getIntBySql(String sql) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            rs.next();
            int ret = rs.getInt(1);
            rs.close();
            s.close();
            c.close();
            return ret;
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
        return 0;
    }

    public List<Object[]> getResultSetBySql(String sql) {
        try {
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery(sql);
            List ret = new ArrayList();
            try {
                int ii = rs.getMetaData().getColumnCount();
                while(rs.next()) {
                    Object[] obj = new Object[ii];
                    for(int i = 0; i < ii; i++) {
                        obj[i] = rs.getString(i + 1);
                    }

                    ret.add(obj);
                }
            } catch(Exception ex) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                ex.printStackTrace(pw);
                this.LOGGER.error(sw.toString());
            }
            rs.close();
            s.close();
            c.close();
            return ret;
        } catch(Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            this.LOGGER.error(sw.toString());
        }
        return null;
    }
}