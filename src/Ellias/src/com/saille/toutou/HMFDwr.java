package com.saille.toutou;

import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2018-09-06
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class HMFDwr {
    public void recordMath99Error(String str) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(d);
        String[] parts = str.split(",");
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        String sql = "insert into hmf_99error(a, b, time) values(?, ?, ?)";
        for(String p : parts) {
            int a = Integer.parseInt(p.split("-")[0]);
            int b = Integer.parseInt(p.split("-")[1]);
            jt.update(sql, new Object[]{a, b, now});
        }
    }
    public List<String[]> getEnglishRandomWords(int count) {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        if(count <= 0) {
            count = 10;
        }
        String sql = "select english, chinese from hmf_english order by rand() limit " + count;
        String errorsql = "select chinese from hmf_english where english <> ? order by rand() limit 3";
        List<Map<String, String>> list = jt.queryForList(sql);
        List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < list.size(); i++) {
            Map<String, String> m = list.get(i);
            String[] obj = new String[5];
            obj[0] = m.get("english");
            obj[1] = m.get("chinese");
            List<Map<String, String>> errors = jt.queryForList(errorsql, new Object[]{obj[0]});
            for(int j = 0; j < 3; j++) {
                obj[j + 2] = errors.get(j).get("chinese");
            }
            ret.add(obj);
        }
        return ret;
    }
}
