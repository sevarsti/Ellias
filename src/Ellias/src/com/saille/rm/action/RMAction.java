package com.saille.rm.action;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ellias on 2017-09-11.
 */
public class RMAction extends AbstractDispatchAction{
    private JdbcTemplate jt;
    public RMAction() {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        jt = new JdbcTemplate(ds);
    }
    public ActionForward generateXml(ActionMapping mapping,
                                ActionForm _form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        int count = Integer.parseInt(request.getParameter("totalcount"));
        List<Map<String, Object>> songs = new ArrayList<Map<String, Object>>();
        for(int i = 0; i < count; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            String songtype = request.getParameter("type" + i);
            String order = request.getParameter("order" + i);
            String ratio = request.getParameter("ratio" + i);
            if("1".equals(songtype)) { //¹ÙÆ×
                String songid = request.getParameter("songid" + i);
                Map<String, Object> info = jt.queryForMap("select path from rm_song where songid = ?", new Object[]{Integer.parseInt(songid)});
                String path = info.get("path").toString();
                String length = info.get("length").toString();
                map.put("type", "1");
                map.put("songid", songid);
                map.put("order", order);
                map.put("ratio", ratio);
                map.put("path", path);
                map.put("length", length);
                songs.add(map);
            } else { //×ÔÖÆ
                String path = request.getParameter("path" + i);
                Map<String, Object> info = jt.queryForMap("select * from rm_customsong where path = ?", new Object[]{path});
                String length = info.get("length").toString();
                map.put("type", "2");
                map.put("order", order);
                map.put("ratio", ratio);
                map.put("path", path);
                map.put("length", length);
                songs.add(map);
            }
        }
        return mapping.findForward("confirm");
    }
}
