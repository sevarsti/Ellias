package com.saille.rm.action;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;
import com.saille.aliyun.OssUtils;
import com.saille.rm.form.RMForm;
import com.saille.rm.util.RMUtils;
import com.saille.sys.Setting;
import com.saille.sys.util.SysUtils;
import com.saille.util.FFMpegUtils;
import org.apache.log4j.Logger;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Ellias on 2017-09-11.
 */
public class RMAction extends AbstractDispatchAction{
    private final static Logger LOGGER = Logger.getLogger(RMAction.class);
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
        HSSFCellStyle numberStyle = workbook.createCellStyle();
        numberStyle.setDataFormat(workbook.createDataFormat().getFormat("0.##"));
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
            cell.setCellStyle(numberStyle);
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
            DecimalFormat doubleFormat = new DecimalFormat("0.####");
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
                int songId = (int)sheet.getRow(i).getCell(6).getNumericCellValue();
                String type = sheet.getRow(i).getCell(0).getRichStringCellValue().getString();
                int order = (int)sheet.getRow(i).getCell(3).getNumericCellValue();
                double ratio = sheet.getRow(i).getCell(4).getNumericCellValue();
                double repeat = sheet.getRow(i).getCell(5).getNumericCellValue();
                songxls.add(new String[]{type, songId + "", order + "", doubleFormat.format(ratio), repeat + ""});
                LOGGER.info("songid=" + songId + ", type=" + type + ", order=" + order + ", ratio=" + ratio);
            }
            is.close();
            /* 将excel的数据读到songs中 */
            for(int i = 0; i < songxls.size(); i++) {
                String[] infos = songxls.get(i);
                Map<String, String> map = new HashMap<String, String>();
                String songtype = infos[0];
                String order = infos[2];
                String ratio = infos[3];
                if("官谱".equals(songtype)) { //官谱
//                    String path = infos[1];
                    int songid = Integer.parseInt(infos[1]);
                    Map<String, Object> info = jt.queryForMap("select path, songname, `length`, author from rm_song where songid = ?", new Object[]{songid});
                    String path = info.get("path").toString();
                    String length = info.get("length").toString();
                    map.put("name", info.get("songname").toString());
                    map.put("type", "1");
                    map.put("songid", songid + "");
                    map.put("order", order);
                    map.put("ratio", ratio);
                    map.put("path", path);
                    map.put("author", info.get("author").toString());
                    map.put("length", length);
                    songs.add(map);
                } else { //自制
                    int songid = Integer.parseInt(infos[1]);
                    Map<String, Object> info = jt.queryForMap("select * from rm_customsong where id = ?", new Object[]{songid});
                    String length = info.get("length").toString();
                    String path = info.get("path").toString();
                    map.put("type", "2");
                    map.put("order", order);
                    map.put("ratio", ratio);
                    map.put("path", path);
                    map.put("length", length);
                    map.put("name", info.get("name").toString());
                    map.put("author", info.get("author").toString());
                    map.put("objid", songid + "");
                    songs.add(map);
                }
            }
            /* 列出可以打的歌曲 */
            List<Map<String, Object>> list = jt.queryForList("select songid, path, length from rm_song where has = 1 order by `length`");
            List<String> songIdList = new ArrayList<String>();
            List<String> usedSongids = new ArrayList<String>();
            for(Map<String, Object> m : list) {
                String songId = m.get("songid").toString();
                if(Integer.parseInt(songId) >= 251 && Integer.parseInt(songId) <= 262) { //过滤等级歌
                    continue;
                }
                songIdList.add(songId);
//                songIdMap.put(songId, m);
            }
            /* 将不需要调整名字直接可以打的歌放在最前面 */
            int hasCount = 0;
            for(int i = 0; i < songs.size(); i++) {
                if(songs.get(i).get("type").equals("1") && songIdList.contains(songs.get(i).get("songid"))) {
                    if("1".equals(songs.get(i).get("ratio")) || "0".equals(songs.get(i).get("ratio"))) {
                        Map<String, String> m = songs.remove(i);
                        usedSongids.add(m.get("songid"));
                        songs.add(0, m);
                        hasCount++;
                    }
                }
            }
            for(int i = list.size() - 1; i >= 0; i--) {
                if(usedSongids.contains(list.get(i).get("songid").toString())) {
                    list.remove(i);
                }
            }
            quickSortToAddSongsByLength(songs, hasCount, songs.size()); //将需要改名的歌根据长度排序
            for(int i = 0; i < hasCount; i++) { //官谱
                songs.get(i).put("targetid", songs.get(i).get("songid"));
                songs.get(i).put("targetpath", songs.get(i).get("path"));
            }
            for(int i = hasCount; i < songs.size(); i++) {
                songs.get(i).put("targetid", list.get(i - hasCount).get("songid").toString());
                songs.get(i).put("targetpath", list.get(i - hasCount).get("path").toString());
            }
            /* 生成xml */
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
            String bpm = "";
            DecimalFormat df = new DecimalFormat("0.#");
            for(int i = 0; i < songs.size(); i++) {
                int[][] levels = new int[3][3];
                int[][] totalkeys = new int[3][3];
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
                        levels[key - 4][level - 1] = difficulty > 10 ? 10 : difficulty;
                        totalkeys[key - 4][level - 1] = totalkey;
                    }
                    Map<String, Object> map = jt.queryForMap("select bpm from rm_customsong where id = ?", new Object[]{Integer.parseInt(m.get("objid"))});
                    bpm = df.format(((Number)map.get("bpm")).doubleValue());
                }
                sb.append("<SongConfig_Client version=\"1\">\r\n");
                sb.append("    <m_ushSongID>").append(m.get("targetid")).append("</m_ushSongID>\r\n");
                sb.append("    <m_iVersion>0 </m_iVersion>\r\n");
                sb.append("    <m_szSongName>").append(m.get("type").equals("1") ? "" : (getKeyDesc(totalkeys) + " ")).append(m.get("ratio").equals("1") ? "" : (m.get("ratio") + "x")).append(m.get("name")).append("</m_szSongName>\r\n");
                sb.append("    <m_szPath>").append(m.get("targetpath")).append("</m_szPath>\r\n");
                sb.append("    <m_szArtist>").append(m.get("author")).append("</m_szArtist>\r\n");
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
            sb.append("</SongConfig_Client_Tab>\r\n");

            /* 生成最后打包的zip */
            String savepath = this.getClass().getResource("/").getPath();
            if(savepath.startsWith("/")) {
                savepath = savepath.substring(1, savepath.length() - 1);
            }
            savepath = savepath.substring(0, savepath.lastIndexOf("/"));
            savepath = savepath.substring(0, savepath.lastIndexOf("/"));
            savepath = savepath + File.separator + "temp" + File.separator + new SimpleDateFormat("yyyyMMdd HHmmss").format(new Date()) + ".zip";
            LOGGER.info("savepath=" + savepath);
            File f = new File(savepath);
            if(!f.exists()) {
                f.createNewFile();
            }
            SysUtils.addTempFile(savepath, null, 60 * 60 * 24 * 7);
            FileOutputStream fos = new FileOutputStream(f);
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry("mrock_song_client.xml"));
            zos.write(sb.toString().getBytes("UTF-8"));
            sb = null;
            FileInputStream fis;
            byte[] bytes = new byte[4096];
            int bytecount = 0;
            /* 写歌曲目录 */
            for(int i = 0; i < songs.size(); i++) {
                Map<String, String> m = songs.get(i);
                LOGGER.info(i + "/" + songs.size() + ":" + (m.get("type").equals("1") ? "官谱" : "自制") + "_" + m.get("path") + "->" + m.get("targetpath"));
                String targetpath = m.get("targetpath");
                if(m.get("type").equals("1")) { //官谱
                    File dir = new File(Setting.getSettingString("RM_PATH") + "song\\" + m.get("path"));
                    File[] files = dir.listFiles();
                    for(File file : files) {
                        String oldname = file.getName();
                        String newname;
                        if(oldname.contains("_")) {
                            newname = m.get("targetpath") + oldname.substring(oldname.indexOf("_"));
                        } else {
                            newname = m.get("targetpath") + oldname.substring(oldname.indexOf("."));
                        }
                        zos.putNextEntry(new ZipEntry("song\\" + m.get("targetpath") + "\\" + newname));
                        boolean changebpm = !m.get("ratio").equals("1");
                        if(changebpm && (oldname.toLowerCase().endsWith(".mp3") || oldname.toLowerCase().endsWith(".imd"))) { //mp3和imd需要变速
                            if(oldname.toLowerCase().endsWith(".mp3")) { //用ffmpeg变速
                                String tempmp3file = System.getProperty("java.io.tmpdir") + File.separator + oldname + m.get("ratio");
                                LOGGER.info("mp3需要变速:" + m.get("ratio") + "x->" + tempmp3file);
                                FFMpegUtils.changeSpeed(file.getCanonicalPath(), tempmp3file, Double.parseDouble(m.get("ratio")));
                                SysUtils.addTempFile(tempmp3file, null, 60 * 5);
                                File newfile = new File(tempmp3file);
                                byte[] newbytes = new byte[(int)newfile.length()];
                                FileInputStream newfis = new FileInputStream(newfile);
                                newfis.read(newbytes);
                                newfis.close();
                                LOGGER.info("mp3变速完成");
                                zos.write(newbytes);
                            } else { //imd变速
                                FileInputStream oldfis = new FileInputStream(file);
                                byte[] oldbytes = new byte[(int)file.length()];
                                oldfis.read(oldbytes);
                                oldfis.close();
                                byte[] newbytes = RMUtils.changeBpm(oldbytes, Double.parseDouble(m.get("ratio")));
                                zos.write(newbytes);
                            }
                        } else {
                            fis = new FileInputStream(file);
                            while ((bytecount = fis.read(bytes)) > 0) {
                                zos.write(bytes, 0, bytecount);
                            }
                            fis.close();
                        }
                    }
                } else { //自制
                    File dir = new File(Setting.getSettingString("RM_PATH") + "zizhi\\" + m.get("path"));
                    if(dir.exists()) { //本地有文件，不需要读取OSS
                        File[] files = dir.listFiles();
                        for(File file : files) {
                            String oldname = file.getName();
                            String newname;
                            if(oldname.contains("_")) {
                                newname = m.get("targetpath") + oldname.substring(oldname.indexOf("_"));
                            } else {
                                newname = m.get("targetpath") + oldname.substring(oldname.indexOf("."));
                            }
                            zos.putNextEntry(new ZipEntry("song\\" + m.get("targetpath") + "\\" + newname));
                            boolean changebpm = !m.get("ratio").equals("1");
                            if(changebpm && (oldname.toLowerCase().endsWith(".mp3") || oldname.toLowerCase().endsWith(".imd"))) { //mp3和imd需要变速
                                if(oldname.toLowerCase().endsWith(".mp3")) { //用ffmpeg变速
                                    String tempmp3file = System.getProperty("java.io.tmpdir") + File.separator + oldname;
                                    File newfile = new File(tempmp3file);
                                    tempmp3file = newfile.getParent() + File.separator + m.get("ratio") + newfile.getName();
                                    LOGGER.info("mp3需要变速:" + m.get("ratio") + "x->" + tempmp3file);
                                    FFMpegUtils.changeSpeed(file.getCanonicalPath(), tempmp3file, Double.parseDouble(m.get("ratio")));
                                    SysUtils.addTempFile(tempmp3file, null, 60 * 5);
                                    newfile = new File(tempmp3file);
                                    byte[] newbytes = new byte[(int)newfile.length()];
                                    FileInputStream newfis = new FileInputStream(newfile);
                                    newfis.read(newbytes);
                                    newfis.close();
                                    LOGGER.info("mp3变速完成");
                                    zos.write(newbytes);
                                } else { //imd变速
                                    FileInputStream oldfis = new FileInputStream(file);
                                    byte[] oldbytes = new byte[(int)file.length()];
                                    oldfis.read(oldbytes);
                                    oldfis.close();
                                    byte[] newbytes = RMUtils.changeBpm(oldbytes, Double.parseDouble(m.get("ratio")));
                                    zos.write(newbytes);
                                }
                            } else {
                                fis = new FileInputStream(file);
                                while ((bytecount = fis.read(bytes)) > 0) {
                                    zos.write(bytes, 0, bytecount);
                                }
                                fis.close();
                            }
                        }
                    } else { //本地没有，需要从OSS下载
                        final String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
                        final String accessKeyId = OssUtils.getAccessKeyId();
                        final String accessKeySecret = OssUtils.getAccessKeySecret();
                        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

                        ListObjectsRequest req = new ListObjectsRequest();
                        req.setBucketName("ellias-ia");
                        req.setPrefix("rm/zizhi/" + m.get("path") + "/");
                        req.setDelimiter("/");
                        ObjectListing objectListing = ossClient.listObjects(req);
                        for(int j = 0; j < objectListing.getObjectSummaries().size(); j++) {
                            InputStream ossIs = ossClient.getObject("ellias-ia", objectListing.getObjectSummaries().get(j).getKey()).getObjectContent();
                            boolean isMp3 = false, isImd = false;
                            String oldname = objectListing.getObjectSummaries().get(j).getKey();
                            if(oldname.toLowerCase().endsWith(".imd")) {
                                isImd = true;
                            } else if(oldname.toLowerCase().endsWith(".mp3")) {
                                isMp3 = true;
                            }
                            oldname = oldname.substring(oldname.lastIndexOf("/") + 1);
                            String newname;
                            if(oldname.contains("_")) {
                                newname = m.get("targetpath") + oldname.substring(oldname.indexOf("_"));
                            } else {
                                newname = m.get("targetpath") + oldname.substring(oldname.indexOf("."));
                            }
                            zos.putNextEntry(new ZipEntry("song\\" + m.get("targetpath") + "\\" + newname));
                            boolean changebpm = !m.get("ratio").equals("1");
                            if(changebpm && isImd) {
                                byte[] imdbytes = new byte[(int)objectListing.getObjectSummaries().get(j).getSize()];
                                ossIs.read(imdbytes);
                                byte[] newbytes = RMUtils.changeBpm(imdbytes, Double.parseDouble(m.get("ratio")));
                                zos.write(newbytes);
                            } else if(changebpm && isMp3) {
                                String originalname = System.getProperty("java.io.tmpdir") + File.separator + oldname;
                                LOGGER.info("mp3需要变速:" + m.get("ratio") + "x->" + originalname);
                                File tempfile = new File(originalname);
                                if(!tempfile.exists()) {
                                    tempfile.createNewFile();
                                }
                                SysUtils.addTempFile(originalname, null, 60 * 5);
                                FileOutputStream mp3fos = new FileOutputStream(tempfile);
                                while ((bytecount = ossIs.read(bytes)) > 0) {
                                    mp3fos.write(bytes, 0, bytecount);
                                }
                                mp3fos.close();
                                LOGGER.info("mp3下载完成，调用ffmpeg开始变速");
                                tempfile = new File(originalname);
                                String tempmp3filepath = tempfile.getParent() + File.separator + m.get("ratio") + tempfile.getName();
                                FFMpegUtils.changeSpeed(originalname, tempmp3filepath, Double.parseDouble(m.get("ratio")));
                                LOGGER.info("mp3变速完成");
                                SysUtils.addTempFile(tempmp3filepath, null, 60 * 5);
                                fis = new FileInputStream(tempmp3filepath);
                                while ((bytecount = fis.read(bytes)) > 0) {
                                    zos.write(bytes, 0, bytecount);
                                }
                                fis.close();
                            } else {
                                while ((bytecount = ossIs.read(bytes)) > 0) {
                                    zos.write(bytes, 0, bytecount);
                                }
                            }
                            ossIs.close();
                        }
                        ossClient.shutdown();
                    }
                }
            }
            zos.close();
            fos.close();
            return null;
//            return mapping.findForward("confirm");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String getKeyDesc(int[][] keys) {
        StringBuilder sb[] = new StringBuilder[3];
        char[] desc = new char[]{'e', 'n', 'h'};
        for(int i = 0; i < 3; i++) {
            if(keys[i][0] > 0 && keys[i][1] > 0 && keys[i][2] > 0) {
                sb[i] = new StringBuilder();
                sb[i].append(i + 4);
                sb[i].append("k");
            } else if(keys[i][0] == 0 && keys[i][1] == 0 && keys[i][2] == 0) {
                continue;
            } else {
                sb[i] = new StringBuilder();
                sb[i].append(i + 4);
                for(int j = 0; j < 3; j++) {
                    if(keys[i][j] > 0) {
                        sb[i].append(desc[j]);
                    }
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        for(StringBuilder s : sb) {
            if(s != null && s.length() > 0) {
                ret.append(s);
            }
        }
        return ret.toString();
    }

    private void quickSortToAddSongsByLength(List<Map<String, String>> list, int start, int end) {
        if(start >= end) {
            return;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if((Double.parseDouble(list.get(i).get("length")) / Double.parseDouble(list.get(i).get("ratio"))) < (Double.parseDouble(list.get(pos).get("length")) / Double.parseDouble(list.get(pos).get("ratio")))) {
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
