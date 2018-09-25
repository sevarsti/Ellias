package com.saille.toutou;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.GlobalContext;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
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
    private final static Logger LOGGER = Logger.getLogger(HMFDwr.class);
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
//        String sql = "select english, chinese from hmf_english order by rand() limit " + count;
        String sql = "select aa.*, aa.correct / aa.c as rate, (case when aa.c = 0 then 1 else 1.2-aa.correct / aa.c end) * rand() as rate from (select a.id, a.english, a.chinese, case when b.c is null then 0 else b.c end as c, case when b.correct is null then 0 else b.correct end as correct from hmf_english a left join (select english, sum(correct) as correct, count(1) as c from hmf_englishresult group by english) b on a.english = b.english) aa order by 7 desc limit " + count;
        String errorsql = "select chinese from hmf_english where english <> ? order by rand() limit 3";
        List<Map<String, String>> list = jt.queryForList(sql);
        List<String[]> ret = new ArrayList<String[]>();
        for(int i = 0; i < list.size(); i++) {
            Map<String, String> m = list.get(i);
            String[] obj = new String[5];
            obj[0] = m.get("english");
            obj[1] = m.get("chinese");
            List<Map<String, String>> errors = jt.queryForList(errorsql, new Object[]{obj[0]});
            String logstr = "";
            for(int j = 0; j < 3; j++) {
                obj[j + 2] = errors.get(j).get("chinese");
                logstr += "/"+obj[j + 2];
            }
            LOGGER.info(obj[0] +"/"+obj[1]+logstr);
            ret.add(obj);
        }
        return ret;
    }

    public void recordEnglishe2cResult(List<String> obj) {
        String sql = "insert into hmf_englishresult(english, answer, myanswer, correct, updatetime) values(?,?,?,?,now())";
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        JdbcTemplate jt = new JdbcTemplate(ds);
        for(int i = 0; i < obj.size(); i++) {
            String[] parts = obj.get(i).split("_");
            String english = parts[0];
            String answer = parts[1];
            String myanswer = parts[2];
            int correct = 0;
            if(answer.equals(myanswer)) {
                correct = 1;
            }
            jt.update(sql, new Object[]{english, answer, myanswer, correct});
        }
    }

    public boolean saveEnglishWord(int id, String english, String chinese) {
        try {
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            if(id > 0) {
                jt.update("update hmf_english set chinese = ?, english = ? where id = ?", new Object[]{chinese, english, id});
            } else {
                jt.update("insert into hmf_english (chinese, english) values(?, ?)", new Object[]{chinese, english});
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean deleteEnglishWord(int id) {
        try {
            DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
            JdbcTemplate jt = new JdbcTemplate(ds);
            jt.update("delete from hmf_english where id = ?", new Object[]{id});
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
