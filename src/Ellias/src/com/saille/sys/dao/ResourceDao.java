package com.saille.sys.dao;

import com.saille.core.dao.BaseJdbcDao;
import com.saille.core.rowMapper.ObjectRowMapper;
import com.sinitek.dao.jdbc.helper.MapperUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import com.saille.sys.Resource;
import servlet.GlobalContext;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class ResourceDao extends BaseJdbcDao {
    private final Logger LOGGER = Logger.getLogger(getClass());

    public static ResourceDao getInstance() {
        return (ResourceDao)GlobalContext.getContextBean(ResourceDao.class);
    }

    public Resource get(int id) {
        String sql = "select * from SYS_Resource where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Resource> list = jt.query(sql, new Object[]{id}, new ObjectRowMapper(Resource.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public int save(Resource obj) {
        obj.setUpdateTime(new Date());
        if(obj.getId() > 0) {
            update(obj);
        } else {
            obj.setCreateTime(new Date());
            obj.setId(getId(MapperUtils.getTableNameFromAnnotation(obj.getClass())));
            insert(obj);
        }
        return obj.getId();
    }

    public void remove(int id) {
        String sql = "update `SYS_Resource` set removetag = 1 where id = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        jt.update(sql, new Object[]{id});
    }

    public List<Resource> findAll() {
        String sql = "select * from SYS_Resource where removetag = 0 order by id";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        return jt.query(sql, new ObjectRowMapper(Resource.class));
    }

    public List<Resource> findByParentId(Resource parent, boolean inherit) {
        if(inherit) {
            List<Resource> ret = new ArrayList<Resource>();
            List<Resource> list = findByParentId(parent, false);
            for(Resource r : list) {
                r.setLevel(parent.getLevel() + 1);
            }
            ret.addAll(list);
            for(Resource r : list) {
                ret.addAll(ret.indexOf(r) + 1, findByParentId(r, inherit));
            }
            return ret;
        } else {
            String sql = "select * from `SYS_Resource` where removetag = 0 and parentId = ? order by id";
            JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
            List<Resource> list = jt.query(sql, new Object[]{parent.getId()}, new ObjectRowMapper(Resource.class));
            for(Resource r : list) {
                r.setLevel(parent.getLevel() + 1);
            }
            return list;
        }
    }

    public Resource findByUrlMethod(String url, String method) {
        if(method == null || method.length() == 0) {
            String sql = "SELECT * FROM `SYS_RESOURCE` WHERE removetag = 0 and URL = ? and methodname is null order by url desc";
            JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
            List<Resource> list = jt.query(sql, new Object[]{url}, new ObjectRowMapper(Resource.class));
            return list.size() > 0 ? list.get(0) : null;
        } else {
            String sql = "SELECT * FROM `SYS_RESOURCE` WHERE removetag = 0 and URL = ? and (methodname = ? or methodname is null) order by url desc";
            JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
            List<Resource> list = jt.query(sql, new Object[]{url, method}, new ObjectRowMapper(Resource.class));
            return list.size() > 0 ? list.get(0) : null;
        }
    }

    public Resource findByNameParentId(String name, int parentId) {
        String sql = "select * from `SYS_Resource` where removetag = 0 and name = ? and parentId = ?";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Resource> list = jt.query(sql, new Object[]{name, parentId}, new ObjectRowMapper(Resource.class));
        return list.size() > 0 ? list.get(0) : null;
    }

    public List<Resource> findByUrl(String url) {
        String sql = "select * from `SYS_Resource` where removetag = 0 and url is not null and length(url) > 0 order by url desc";
        JdbcTemplate jt = new JdbcTemplate(this.getDataSource());
        List<Resource> list = jt.query(sql, new ObjectRowMapper(Resource.class));
        for(int i = list.size() - 1; i >= 0; i--) {
            if(!url.startsWith(list.get(i).getUrl())) {
                list.remove(i);
            }
        }
        return list;
    }
}
