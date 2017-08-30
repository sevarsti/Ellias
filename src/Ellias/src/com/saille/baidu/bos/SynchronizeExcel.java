package com.saille.baidu.bos;

import com.GlobalConstant;
import com.baidubce.services.bos.model.BosObjectSummary;
import com.baidubce.services.bos.model.BosObject;
import com.saille.sys.BaseThread;

import java.io.*;
import java.util.List;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.nio.charset.Charset;

import com.saille.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-12-5
 * Time: 23:54:15
 * To change this template use File | Settings | File Templates.
 */
public class SynchronizeExcel extends BaseThread {
    private final static Logger LOGGER = LoggerFactory.getLogger(SynchronizeExcel.class);
    private final static String BUCKETNAME = "ellias-excel";
    private final static String BACKUPBUCKETNAME = "ellias-backup";
    private static String[] CHECK_SUFFIX = new String[]{".xls", ".xlsm"};
    private static String[] EXCLUDE_FILES = new String[]{"节奏大师歌曲 - 副本.xls"};
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");

    public static void main(String[] args) {
    }

    private final static String EXCELPATH = GlobalConstant.DISKPATH + "EXCEL";
    protected int execute() {
        try {
            if(CommonUtils.hasSystemProcess("EXCEL")) {
                return 5;
            }
            BOSUtils.init();
            File dir = new File(EXCELPATH);
            if(dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if(files.length > 0) {
                    if(!BOSUtils.bucketExist(BUCKETNAME)) {
                        BOSUtils.createBucket(BUCKETNAME);
                    }
                }
                List<BosObjectSummary> remotefiles = BOSUtils.listObjects(BUCKETNAME);
                //检查本地文件是否需要上传
                LOGGER.info("本地文件数量：" + files.length);
                for(File f : files) {
                    if(f.isDirectory()) {
                        continue;
                    }
                    if(!needUpload(f.getName())) {
                        continue;
                    }
                    boolean needupload = true;
                    boolean needbackup = false;
                    for(int i = 0; i < remotefiles.size(); i++) {
                        BosObjectSummary remotefile = remotefiles.get(i);
                        if(remotefile.getKey().trim().equals(f.getName())) {
                            needbackup = true;
                            long localTime = f.lastModified();
                            LOGGER.debug("获取服务器文件属性:" + remotefile.getKey());
                            BosObject obj = BOSUtils.getObject(BUCKETNAME, remotefile);
                            LOGGER.debug("获取服务器文件属性完毕");
                            long remoteTime = 0l;
                            String remoteTimeTemp =obj.getObjectMetadata().getUserMetadata().get("lastmodified");
                            if(remoteTimeTemp != null) {
                                remoteTime = Long.parseLong(remoteTimeTemp);
                            } else {
                                remoteTime = obj.getObjectMetadata().getLastModified().getTime();
                            }

                            if((localTime - remoteTime) <= 1000 * 60 * 5) {
                                needupload = false;
                            }
                            break;
                        }
                    }
                    if(needupload) {
                        LOGGER.info("需要上传文件：" + f.getName());
                        if(needbackup) {
                            LOGGER.info("需要备份原文件");
                            BOSUtils.copyObject(BUCKETNAME, f.getName(), BACKUPBUCKETNAME, "backup/" + f.getName() + "_" + SDF.format(new Date()));
                            LOGGER.info("原文件备份完毕");
                            clearDeprecatedBacks(BUCKETNAME, f.getName(), BACKUPBUCKETNAME, "backup/");
                        }
                        BOSUtils.uploadFile(BUCKETNAME, f);
                        LOGGER.info("上传完毕");
                    }
                }
                //检查服务器文件是否需要下载
                LOGGER.info("服务器文件数量：" + remotefiles.size());
                for(BosObjectSummary remotefile : remotefiles) {
                    if(remotefile.getKey().indexOf("/") >= 0) {
                        continue;
                    }
                    if(!needUpload(remotefile.getKey())) {
                        continue;
                    }
                    boolean needdownload = true;
                    boolean needbackup = false;
                    LOGGER.debug("获取服务器文件属性:" + remotefile.getKey());
                    BosObject obj = BOSUtils.getObject(BUCKETNAME, remotefile);
                    LOGGER.debug("获取服务器文件属性完毕");
                    long remoteTime = 0l;
                    String remoteTimeTemp =obj.getObjectMetadata().getUserMetadata().get("lastmodified");
                    if(remoteTimeTemp != null) {
                        remoteTime = Long.parseLong(remoteTimeTemp);
                    } else {
                        remoteTime = obj.getObjectMetadata().getLastModified().getTime();
                    }

                    for(int i = 0; i < files.length; i++) {
                        File f = files[i];
                        if(f.isDirectory()) {
                            continue;
                        }
                        if(remotefile.getKey().trim().equals(f.getName())) {
                            needbackup = true;
                            long localTime = f.lastModified();

                            if((remoteTime - localTime) <= 1000 * 60 * 5) {
                                needdownload = false;
                            }
                            break;
                        }
                    }
                    if(needdownload) {
                        LOGGER.info("需要下载文件：" + remotefile.getKey());
                        String localfilename = EXCELPATH + File.separator + remotefile.getKey();
                        File localfile = new File(localfilename);
                        if(needbackup) {
                            try {
                                LOGGER.info("需要备份原文件");
                                File backdir = new File(EXCELPATH + File.separator + "backup");
                                if(!backdir.exists()) {
                                    backdir.mkdirs();
                                }
                                File targetfile = new File(EXCELPATH + File.separator + "backup" + File.separator + remotefile.getKey().trim() + "_" + SDF.format(new Date()));
                                if(!targetfile.exists()) {
                                    targetfile.createNewFile();
                                }
//                                File originalfile = new File(localfilename);
                                FileInputStream fis = new FileInputStream(localfile);
                                FileOutputStream fos = new FileOutputStream(targetfile);
                                byte[] bytes = new byte[1024];
                                int i = 0;
                                while((i = fis.read(bytes)) > 0) {
                                    fos.write(bytes, 0, i);
                                }
                                fis.close();
                                fos.close();
                                targetfile.setLastModified(localfile.lastModified());
                                LOGGER.info("原文件备份完毕");
                            } catch(IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                        BOSUtils.downloadFile(BUCKETNAME, localfile);
                        localfile.setLastModified(remoteTime);
                        LOGGER.info("下载完毕");
                    }
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    private boolean needUpload(String filename) {
        boolean suffixMatch = false;
        for(String s : CHECK_SUFFIX) {
            if(filename.endsWith(s)) {
                suffixMatch = true;
                break;
            }
        }
        if(!suffixMatch) {
            return false;
        }
        for(String s : EXCLUDE_FILES) {
            if(s.equals(filename)) {
                return false;
            }
        }
        return true;
    }

    private void clearDeprecatedBacks(String bucketname, String filename, String backupBucketname, String backuppath) {
        if(!BOSUtils.bucketExist(backupBucketname)) {
            return;
        }
        List<BosObjectSummary> list = BOSUtils.listObjects(backupBucketname, backuppath);
        for(int i = list.size() - 1; i >= 0; i--) {
            BosObjectSummary obj = list.get(i);
            if(!obj.getKey().startsWith(backuppath + filename)) {
                list.remove(i);
            }
        }
        Map<String, BosObjectSummary> dailyMap = new HashMap<String, BosObjectSummary>();
        for(BosObjectSummary obj : list) {
            if(obj.getKey().length() <= 14) {
                continue;
            }
            String date = obj.getKey().substring(obj.getKey().length() - 14);
            String time = date.substring(8);
            date = date.substring(0, 8);
            if(dailyMap.containsKey(date)) {
                if(dailyMap.get(date).getKey().compareTo(obj.getKey()) >= 0) {
                    BOSUtils.deleteObject(dailyMap.get(date));
                    dailyMap.put(date, obj);
                } else {
                    BOSUtils.deleteObject(obj);
                }
            } else {
                dailyMap.put(date, obj);
            }
        }
    }
}
