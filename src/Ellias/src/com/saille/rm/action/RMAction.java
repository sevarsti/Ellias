package com.saille.rm.action;

import com.saille.rm.RMConstant;
import com.saille.rm.form.RMForm;
import com.saille.rm.util.RMUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.springframework.jdbc.core.JdbcTemplate;
import servlet.AbstractDispatchAction;
import servlet.GlobalContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Ellias on 2017-09-11.
 */
public class RMAction extends AbstractDispatchAction{
    private JdbcTemplate jt;
    public RMAction() {
        DataSource ds = (DataSource) GlobalContext.getSpringContext().getBean("mysql_ds");
        jt = new JdbcTemplate(ds);
    }

    public ActionForward example(ActionMapping mapping,
                                 ActionForm _form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row;
        HSSFCell cell;
        HSSFCellStyle intStyle = workbook.createCellStyle();
        intStyle.setDataFormat(workbook.createDataFormat().getFormat("0"));
        int rownum = 0;
        row = sheet.createRow(rownum++);
        cell = row.createCell(0);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("类型"));
        cell = row.createCell(1);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("歌名"));
        cell = row.createCell(2);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("路径"));
        cell = row.createCell(3);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("顺序"));
        cell = row.createCell(4);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("变速"));
        cell = row.createCell(5);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("重复"));
        cell = row.createCell(6);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("ID"));
        cell = row.createCell(7);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("长度"));
        cell = row.createCell(8);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("最高难度"));
        cell = row.createCell(9);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cell.setCellValue(new HSSFRichTextString("作者"));

        List<Map<String, Object>> list = jt.queryForList("select a.songid, songname, a.author, path, `length`, b.lv from rm_song a join (select songid, max(songlevel) as lv from rm_songkey group by songid) b on a.songid =b.songid order by b.lv desc");
        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            row = sheet.createRow(rownum++);
            cell = row.createCell(0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString("官谱"));
            cell = row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("songname").toString()));
            cell = row.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("path").toString()));
            cell = row.createCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(0);
            cell = row.createCell(4);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(1);
            cell = row.createCell(5);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(1);
            cell = row.createCell(6);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(((Number) map.get("songid")).intValue());
            cell = row.createCell(7);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(RMUtils.convertLength(((Number)map.get("length")).intValue())));
            cell = row.createCell(8);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((Number)map.get("lv")).doubleValue());
            cell = row.createCell(9);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("author").toString()));
        }

        list = jt.queryForList("select a.id, a.name, a.author, a.path, `length`, b.lv from rm_customsong a join (select songid, max(`difficulty`) as lv from rm_customsongimd group by songid) b on a.id = b.songid order by b.lv desc");
        for(int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            row = sheet.createRow(rownum++);
            cell = row.createCell(0);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString("自制"));
            cell = row.createCell(1);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("name").toString()));
            cell = row.createCell(2);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("path").toString()));
            cell = row.createCell(3);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(0);
            cell = row.createCell(4);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(1);
            cell = row.createCell(5);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(1);
            cell = row.createCell(6);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellStyle(intStyle);
            cell.setCellValue(((Number) map.get("id")).intValue());
            cell = row.createCell(7);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(RMUtils.convertLength(((Number)map.get("length")).intValue())));
            cell = row.createCell(8);
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((Number)map.get("lv")).doubleValue());
            cell = row.createCell(9);
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(map.get("author").toString()));
        }
        try {
            response.addHeader("Content-Disposition", "attachment; filename=example.xls");
            response.setHeader("Content-Type", "application/vnd.ms-excel");
            OutputStream os = response.getOutputStream();
            workbook.write(os);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ActionForward generateXml(ActionMapping mapping,
                                ActionForm _form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        try {
            RMForm form = (RMForm) _form;
            List<Map<String, String>> songs = new ArrayList<Map<String, String>>();
            FormFile uploadxls = form.getUploadXls();
            InputStream is = uploadxls.getInputStream();
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            List<String[]> songxls = new ArrayList<String[]>();
            for(int i = 1; i <= sheet.getLastRowNum(); i++) {
                if(sheet.getRow(i) == null || sheet.getRow(i).getCell(0) == null) {
                    continue;
                }
                String type = sheet.getRow(i).getCell(0).getRichStringCellValue().getString();
                String path = sheet.getRow(i).getCell(2).getRichStringCellValue().getString();
                int order = (int)sheet.getRow(i).getCell(3).getNumericCellValue();
                double ratio = sheet.getRow(i).getCell(4).getNumericCellValue();
                double repeat = sheet.getRow(i).getCell(5).getNumericCellValue();
                songxls.add(new String[]{type, path, order + "", ratio + "", repeat + ""});
            }
            is.close();
            for(int i = 0; i < songxls.size(); i++) {
                String[] infos = songxls.get(i);
                Map<String, String> map = new HashMap<String, String>();
                String songtype = infos[0];
                String order = infos[2];
                String ratio = infos[3];
                if("官谱".equals(songtype)) { //官谱
                    String path = infos[1];
                    Map<String, Object> info = jt.queryForMap("select songid, songname, `length` from rm_song where path = ?", new Object[]{path});
                    int songid = ((Number)info.get("songid")).intValue();
                    String length = info.get("length").toString();
                    map.put("name", info.get("songname").toString());
                    map.put("type", "1");
                    map.put("songid", songid + "");
                    map.put("order", order);
                    map.put("ratio", ratio);
                    map.put("path", path);
                    map.put("length", length);
                    songs.add(map);
                } else { //自制
                    String path = infos[1];
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
            List<String> songIdList = new ArrayList<String>();
            List<String> usedSongids = new ArrayList<String>();
            for(Map<String, Object> m : list) {
                String songId = m.get("songid").toString();
                songIdList.add(songId);
//                songIdMap.put(songId, m);
            }
            int hasCount = 0;
            for(int i = 0; i < songs.size(); i++) {
                if(songs.get(i).get("type").equals("1") && songIdList.contains(songs.get(i).get("songid"))) {
                    Map<String, String> m = songs.remove(i);
                    usedSongids.add(songs.get(i).get("songid"));
                    songs.add(0, m);
                    hasCount++;
                }
            }
            for(int i = list.size() - 1; i >= 0; i--) {
                if(usedSongids.contains(list.get(i).get("songid").toString())) {
                    list.remove(i);
                }
            }
            quickSortToAddSongsByLength(songs, hasCount, songs.size());
            for(int i = 0; i < hasCount; i++) { //官谱
                songs.get(i).put("targetid", songs.get(i).get("songid"));
                songs.get(i).put("targetpath", songs.get(i).get("path"));
            }
            for(int i = hasCount; i < songs.size(); i++) {
                songs.get(i).put("targetid", list.get(i - hasCount).get("songid").toString());
                songs.get(i).put("targetpath", list.get(i - hasCount).get("path").toString());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\" ?>\r\n");
            sb.append("<SongConfig_Client_Tab>\r\n");
            sb.append("<TResHeadAll version=\"5\">\r\n");
            sb.append("    <resHead type=\"TResHead\">\r\n");
            sb.append("        <Magic>1397052237 </Magic>\r\n");
            sb.append("        <Version>6 </Version>\r\n");
            sb.append("        <Unit>830 </Unit>\r\n");
            sb.append("        <Count>").append(songs.size()).append(" </Count>\r\n");
            sb.append("        <MetalibHash>aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</MetalibHash>\r\n");
            sb.append("        <ResVersion>0 </ResVersion>\r\n");
            sb.append("        <CreateTime>   0-00-00 00:00:00</CreateTime>\r\n");
            sb.append("        <ResEncording>UTF-8</ResEncording>\r\n");
            sb.append("        <ContentHash>0e01770c4a8fb3efc8e26038fd2ac620</ContentHash>\r\n");
            sb.append("    </resHead>\r\n");
            sb.append("    <resHeadExt type=\"TResHeadExt\">\r\n");
            sb.append("        <DataOffset>136 </DataOffset>\r\n");
            sb.append("    </resHeadExt>\r\n");
            sb.append("</TResHeadAll>\r\n");
            int[][] levels = new int[3][3];
            int[][] totalkeys = new int[3][3];
            String bpm = "";
            DecimalFormat df = new DecimalFormat("0.#");
            for(int i = 0; i < songs.size(); i++) {
                Map<String, String> m = songs.get(i);
                if(m.get("type").equals("1")) {
                    list = jt.queryForList("select songlevel, `key`, `level`, totalkey from rm_songkey where songid = ?", new Object[]{Integer.parseInt(m.get("songid"))});
                    for(Map<String, Object> map : list) {
                        int key = ((Number)map.get("key")).intValue();
                        int level = ((Number)map.get("level")).intValue();
                        int songlevel = ((Number)map.get("songlevel")).intValue();
                        int totalkey = ((Number)map.get("totalkey")).intValue();
                        levels[key - 4][level - 1] = songlevel;
                        totalkeys[key - 4][level - 1] = totalkey;
                    }
                    Map<String, Object> map = jt.queryForMap("select bpm from rm_song where songid = ?", new Object[]{Integer.parseInt(m.get("songid"))});
                    bpm = df.format(((Number)map.get("bpm")).doubleValue());
                } else {
                    list = jt.queryForList("select difficulty, `key`, `level`, totalkey from rm_customsongimd where songid = ?", new Object[]{Integer.parseInt(m.get("objid"))});
                    for(Map<String, Object> map : list) {
                        int key = ((Number)map.get("key")).intValue();
                        int level = ((Number)map.get("level")).intValue();
                        int difficulty = ((Number)map.get("difficulty")).intValue();
                        int totalkey = ((Number)map.get("totalkey")).intValue();
                        levels[key - 4][level - 1] = difficulty;
                        totalkeys[key - 4][level - 1] = totalkey;
                    }
                    Map<String, Object> map = jt.queryForMap("select bpm from rm_customsong where id = ?", new Object[]{Integer.parseInt(m.get("objid"))});
                    bpm = df.format(((Number)map.get("bpm")).doubleValue());
                }
                sb.append("<SongConfig_Client version=\"1\">\r\n");
                sb.append("    <m_ushSongID>").append(m.get("targetid")).append("</m_ushSongID>\r\n");
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
                sb.append("    <m_szBPM>").append(bpm).append("</m_szBPM>\r\n");
                sb.append("    <m_ucIsOpen>1 </m_ucIsOpen>\r\n");
                sb.append("    <m_ucCanBuy>0x0 </m_ucCanBuy>\r\n");
                sb.append("    <m_iOrderIndex>").append(m.get("order")).append(" </m_iOrderIndex>\r\n");
                sb.append("    <m_bIsFree>0x1 </m_bIsFree>\r\n");
                sb.append("    <m_bSongPkg>0x0 </m_bSongPkg>\r\n");
                sb.append("    <m_szFreeBeginTime></m_szFreeBeginTime>\r\n");
                sb.append("    <m_szFreeEndTime></m_szFreeEndTime>\r\n");
                sb.append("    <m_ush4KeyEasy>").append(levels[0][0]).append(" </m_ush4KeyEasy>\r\n");
                sb.append("    <m_ush4KeyNormal>").append(levels[0][1]).append(" </m_ush4KeyNormal>\r\n");
                sb.append("    <m_ush4KeyHard>").append(levels[0][2]).append(" </m_ush4KeyHard>\r\n");
                sb.append("    <m_ush5KeyEasy>").append(levels[1][0]).append(" </m_ush5KeyEasy>\r\n");
                sb.append("    <m_ush5KeyNormal>").append(levels[1][1]).append(" </m_ush5KeyNormal>\r\n");
                sb.append("    <m_ush5KeyHard>").append(levels[1][2]).append(" </m_ush5KeyHard>\r\n");
                sb.append("    <m_ush6KeyEasy>").append(levels[2][0]).append(" </m_ush6KeyEasy>\r\n");
                sb.append("    <m_ush6KeyNormal>").append(levels[2][1]).append(" </m_ush6KeyNormal>\r\n");
                sb.append("    <m_ush6KeyHard>").append(levels[2][2]).append(" </m_ush6KeyHard>\r\n");
                sb.append("    <m_iPrice>0 </m_iPrice>\r\n");
                sb.append("    <m_szNoteNumber>");
                for(int j = 0; j < 3; j++) {
                    for(int k = 0; k < 3; k++) {
                        if(j != 0 || k != 0) {
                            sb.append(",");
                        }
                        sb.append(totalkeys[j][k]);
                    }
                }
                sb.append("</m_szNoteNumber>\r\n");
                sb.append("    <m_szProductID></m_szProductID>\r\n");
                sb.append("    <m_iVipFlag>0 </m_iVipFlag>\r\n");
                sb.append("    <m_bIsHide>0x0 </m_bIsHide>\r\n");
                sb.append("    <m_bIsReward>0x0 </m_bIsReward>\r\n");
                sb.append("    <m_bIsLevelReward>0x0 </m_bIsLevelReward>\r\n");
                sb.append("</SongConfig_Client>\r\n");
            }
            File f = new File("D:\\test.xml");
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(sb.toString().getBytes("UTF-8"));
            fos.close();

            f = new File("D:\\temp\\a\\song.zip");
            if(!f.exists()) {
                f.createNewFile();
            }
            fos = new FileOutputStream(f);
            ZipOutputStream zos = new ZipOutputStream(fos);
            byte[] bytes = new byte[8192];
            int bytecount = 0;
            FileInputStream fis;
            for(int i = 0; i < songs.size(); i++) {
                Map<String, String> m = songs.get(i);
                if(m.get("type").equals("1")) { //官谱
                    File dir = new File(RMConstant.RM_ROOT + "song\\" + m.get("path"));
                    File[] files = dir.listFiles();
                    for(File file : files) {
                        zos.putNextEntry(new ZipEntry("songs\\" + m.get("path") + "\\" + file.getName()));
                        fis = new FileInputStream(file);
                        while((bytecount = fis.read(bytes)) > 0) {
                            zos.write(bytes, 0, bytecount);
                        }
                        fis.close();
                    }
                } else { //自制
                    File dir = new File(RMConstant.RM_ROOT + "song\\" + m.get("targetpath"));
                    File[] files = dir.listFiles();
                    for(File file : files) {
                        String oldname = file.getName();
                        String newname = m.get("targetpath")
                        zos.putNextEntry(new ZipEntry("songs\\" + m.get("targetpath") + "\\" + file.getName()));
                        fis = new FileInputStream(file);
                        while((bytecount = fis.read(bytes)) > 0) {
                            zos.write(bytes, 0, bytecount);
                        }
                        fis.close();
                    }

                }
            }
            return null;
//            return mapping.findForward("confirm");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
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
