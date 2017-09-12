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
        List<Map<String, String>> songs = new ArrayList<Map<String, String>>();
        for(int i = 0; i < count; i++) {
            Map<String, String> map = new HashMap<String, String>();
            String songtype = request.getParameter("type" + i);
            String order = request.getParameter("order" + i);
            String ratio = request.getParameter("ratio" + i);
            if("1".equals(songtype)) { //官谱
                String songid = request.getParameter("songid" + i);
                Map<String, Object> info = jt.queryForMap("select path, songname from rm_song where songid = ?", new Object[]{Integer.parseInt(songid)});
                String path = info.get("path").toString();
                String length = info.get("length").toString();
                map.put("name", info.get("songname").toString());
                map.put("type", "1");
                map.put("songid", songid);
                map.put("order", order);
                map.put("ratio", ratio);
                map.put("path", path);
                map.put("length", length);
                songs.add(map);
            } else { //自制
                String path = request.getParameter("path" + i);
                Map<String, Object> info = jt.queryForMap("select * from rm_customsong where path = ?", new Object[]{path});
                String length = info.get("length").toString();
                map.put("type", "2");
                map.put("order", order);
                map.put("ratio", ratio);
                map.put("path", path);
                map.put("length", length);
                map.put("name", info.get("name").toString());
                map.put("objid", info.get("id").toString());
                songs.add(map);
            }
        }
        List<Map<String, Object>> list = jt.queryForList("select songid, path, length from rm_song where has = 1 order by `length`");
        Map<String, Map<String, Object>> songIdMap = new HashMap<String, Map<String, Object>>();
        for(Map<String, Object> m : list) {
            String songId = m.get("songid").toString();
            songIdMap.put(songId, m);
        }
        int hasCount = 0;
        for(int i = 0; i < songs.size(); i++) {
            if(songs.get(i).get("type").equals("1") && songIdMap.containsKey(songs.get(i).get("songid"))) {
                Map<String, String> m = songs.remove(i);
                songs.add(0, m);
                hasCount++;
            }
        }
        for(int i = list.size() - 1; i >= 0; i--) {
            if(songIdMap.containsKey(list.get(i).get("songid").toString())) {
                list.remove(i);
            }
        }
        quickSortToAddSongsByLength(songs, hasCount, songs.size());
        for(int i = 0; i < hasCount; i++) {
            songs.get(i).put("targetid", songs.get(i).get("songid"));
            songs.get(i).put("targetpath", songs.get(i).get("songid"));
        }
        for(int i = hasCount; i < songs.size(); i++) {
            songs.get(i).put("targetid", list.get(i - hasCount).get("songid").toString());
            songs.get(i).put("targetpath", list.get(i - hasCount).get("path").toString());
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < songs.size(); i++) {
            Map<String, String> m = songs.get(i);
            if(m.get("type").equals("1")) {

            } else {
                list = jt.queryForList("select difficulty, `key`, `level` from rm_customsongimd where songid = ?", new Object[]{Integer.parseInt(m.get("objid"))});
            }
            sb.append("<SongConfig_Client version=\"1\">\r\n");
            sb.append("    <m_ushSongID>").append(m.get("targetid")).append("</m_ushSongID>");
            sb.append("    <m_iVersion>0 </m_iVersion>\r\n");
            sb.append("    <m_szSongName>").append(m.get("name")).append("</m_szSongName>\r\n");
            sb.append("    <m_szPath>").append(m.get("targetpath")).append("</m_szPath>\r\n");
            sb.append("    <m_szArtist></m_szArtist>\r\n");
            sb.append("    <m_szComposer></m_szComposer>\r\n");
            sb.append("    <m_szSongTime>0.104861</m_szSongTime>\r\n");
            if(Double.parseDouble(m.get("ratio")) == 1d) {
                sb.append("    <m_iGameTime>").append(m.get("length")).append(" </m_iGameTime>\r\n");
            } else {
                sb.append("    <m_iGameTime>").append(((int)(Double.parseDouble(m.get("length")) / Double.parseDouble(m.get("ratio")))) + "").append(" </m_iGameTime>\r\n");
            }
            sb.append("    <m_iRegion>1 </m_iRegion>\r\n");
            if(Double.parseDouble(m.get("ratio")) == 1d) {
                sb.append("    <m_iStyle>6 </m_iStyle>\r\n");
            } else { //变速
                sb.append("    <m_iStyle>11 </m_iStyle>\r\n");
            }
            sb.append("    <m_ucIsNew>0 </m_ucIsNew>\r\n");
            if(m.get("type").equals("1")) {
                sb.append("    <m_ucIsHot>0 </m_ucIsHot>\r\n");
            } else {
                sb.append("    <m_ucIsHot>1 </m_ucIsHot>\r\n");
            }
            sb.append("    <m_ucIsRecommend>0 </m_ucIsRecommend>\r\n");
            sb.append("    <m_szBPM>152</m_szBPM>\r\n");//todo
            sb.append("    <m_ucIsOpen>1 </m_ucIsOpen>\r\n");
            sb.append("    <m_ucCanBuy>0x0 </m_ucCanBuy>\r\n");
            sb.append("    <m_iOrderIndex>").append(m.get("order")).append(" </m_iOrderIndex>\r\n");
            sb.append("    <m_bIsFree>0x1 </m_bIsFree>\r\n");
            sb.append("    <m_bSongPkg>0x0 </m_bSongPkg>\r\n");
            sb.append("    <m_szFreeBeginTime></m_szFreeBeginTime>\r\n");
            sb.append("    <m_szFreeEndTime></m_szFreeEndTime>\r\n");
            sb.append("    <m_ush4KeyEasy>0 </m_ush4KeyEasy>\r\n");//todo
            sb.append("    <m_ush4KeyNormal>0 </m_ush4KeyNormal>\r\n");//todo
            sb.append("    <m_ush4KeyHard>10 </m_ush4KeyHard>\r\n");//todo
            sb.append("    <m_ush5KeyEasy>0 </m_ush5KeyEasy>\r\n");//todo
            sb.append("    <m_ush5KeyNormal>0 </m_ush5KeyNormal>\r\n");//todo
            sb.append("    <m_ush5KeyHard>0 </m_ush5KeyHard>\r\n");//todo
            sb.append("    <m_ush6KeyEasy>0 </m_ush6KeyEasy>\r\n");//todo
            sb.append("    <m_ush6KeyNormal>0 </m_ush6KeyNormal>\r\n");//todo
            sb.append("    <m_ush6KeyHard>0 </m_ush6KeyHard>\r\n");//todo
            sb.append("    <m_iPrice>0 </m_iPrice>\r\n");
            sb.append("    <m_szNoteNumber></m_szNoteNumber>\r\n");
            sb.append("    <m_szProductID></m_szProductID>\r\n");
            sb.append("    <m_iVipFlag>0 </m_iVipFlag>\r\n");
            sb.append("    <m_bIsHide>0x0 </m_bIsHide>\r\n");
            sb.append("    <m_bIsReward>0x0 </m_bIsReward>\r\n");
            sb.append("    <m_bIsLevelReward>0x0 </m_bIsLevelReward>\r\n");
            sb.append("</SongConfig_Client>\r\n");

        }
//        <m_ushSongID>138</m_ushSongID>
//        <m_iVersion>0 </m_iVersion>
//        <m_szSongName>4 Mi.Mi.85 SS</m_szSongName>
//        <m_szPath>nobody</m_szPath>
//        <m_szArtist>M8 Floor Node 85</m_szArtist>
//        <m_szComposer></m_szComposer>
//        <m_szSongTime>0.104861</m_szSongTime>
//        <m_iGameTime>120 </m_iGameTime>
//        <m_iRegion>1 </m_iRegion>
//        <m_iStyle>4 </m_iStyle>
//        <m_ucIsNew>0 </m_ucIsNew>
//        <m_ucIsHot>1 </m_ucIsHot>
//        <m_ucIsRecommend>0 </m_ucIsRecommend>
//        <m_szBPM>150</m_szBPM>
//        <m_ucIsOpen>1 </m_ucIsOpen>
//        <m_ucCanBuy>0x0 </m_ucCanBuy>
//        <m_iOrderIndex>513 </m_iOrderIndex>
//        <m_bIsFree>0x0 </m_bIsFree>
//        <m_bSongPkg>0x0 </m_bSongPkg>
//        <m_szFreeBeginTime></m_szFreeBeginTime>
//        <m_szFreeEndTime></m_szFreeEndTime>
//        <m_ush4KeyEasy>1 </m_ush4KeyEasy>
//        <m_ush4KeyNormal>1 </m_ush4KeyNormal>
//        <m_ush4KeyHard>9 </m_ush4KeyHard>
//        <m_ush5KeyEasy>1 </m_ush5KeyEasy>
//        <m_ush5KeyNormal>1 </m_ush5KeyNormal>
//        <m_ush5KeyHard>9 </m_ush5KeyHard>
//        <m_ush6KeyEasy>1 </m_ush6KeyEasy>
//        <m_ush6KeyNormal>1 </m_ush6KeyNormal>
//        <m_ush6KeyHard>9 </m_ush6KeyHard>
//        <m_iPrice>0 </m_iPrice>
//        <m_szNoteNumber>0,0,2333,0,0,2333,0,0,2333</m_szNoteNumber>
//        <m_szProductID></m_szProductID>
//        <m_iVipFlag>0 </m_iVipFlag>
//        <m_bIsHide>0x0 </m_bIsHide>
//        <m_bIsReward>0x0 </m_bIsReward>
//        <m_bIsLevelReward>0x0 </m_bIsLevelReward>
        return mapping.findForward("confirm");
    }

    private void quickSortToAddSongsByLength(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(Integer.parseInt(list.get(i).get("length")) < Integer.parseInt(list.get(pos).get("length"))) {
                needSwap = true;
            }

            if(needSwap) {
                Map<String, String> tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        quickSortToAddSongsByLength(list, start, pos);
        quickSortToAddSongsByLength(list, pos + 1, end);
        return;
    }
}
