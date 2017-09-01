package com.saille.aliyun;

import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.saille.sys.BaseThread;
import com.saille.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Ellias on 2017-09-01.
 */
public class SynchronizeExcel extends BaseThread {
    private final static Logger LOGGER = LoggerFactory.getLogger(SynchronizeExcel.class);
    private final static String BUCKETNAME = "ellias-std";
    private final static String REMOTE_DIR = "excel";
    private static String[] CHECK_SUFFIX = new String[]{".xls", ".xlsm"};
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
    public static void main(String[] args) {
        OssUtils.init("LTAID3hnLwsUASY4", "znxnT9S3n1vdK4SEuZT7LoW67WmAr4");
        new SynchronizeExcel().execute();
    }

    private final static String EXCELPATH = "F:\\" + "EXCEL";
//    private final static String EXCELPATH = GlobalConstant.DISKPATH + "EXCEL";
    @Override
    protected int execute() {
        try {
            if(CommonUtils.hasSystemProcess("EXCEL")) {
                return 5;
            }
            File dir = new File(EXCELPATH);
            if(dir.exists() && dir.isDirectory()) {
                File[] files = dir.listFiles();
                if(files.length > 0) {
                    if(!OssUtils.bucketExist(BUCKETNAME)) {
                        throw new Exception("Bucket: " + BUCKETNAME + "不存在！");
                    }
                }
                List<OSSObjectSummary> remotefiles = OssUtils.listFiles(BUCKETNAME, REMOTE_DIR);
                //检查本地文件是否需要上传
                LOGGER.info("本地文件数量：" + files.length);
                for(File f : files) {
                    if (f.isDirectory()) {
                        continue;
                    }
                    if(!needUpload(f.getName())) {
                        continue;
                    }
                    boolean needupload = true;
                    boolean needbackup = false;
                    for(int i = 0; i < remotefiles.size(); i++) {
                        String remotefilename = remotefiles.get(i).getKey();
                        if(remotefilename.endsWith("/")) { //文件夹
                            continue;
                        }
                        remotefilename = remotefilename.substring(remotefilename.lastIndexOf("/") + 1);
                        if(remotefilename.equals(f.getName())) {
                            needbackup = true;
                            long localTime = f.lastModified();
                            LOGGER.debug("获取服务器文件属性:" + remotefiles.get(i).getKey());
                            OSSObject obj = OssUtils.getObject(BUCKETNAME, remotefiles.get(i));
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
                            } else {
                                LOGGER.info("[" + f.getName() + "]" + "本地文件时间：" + new Date(localTime) + "，服务器文件时间：" + new Date(remoteTime) + "，需要上传");
                            }
                            break;
                        }
                    }
                    if(needupload) {
                        LOGGER.info("需要上传文件：" + f.getName());
                        if(needbackup) {
                            LOGGER.info("需要备份原文件");
                            String backupkey = REMOTE_DIR + "/backup/" + f.getName();
                            String suffix = backupkey.substring(backupkey.lastIndexOf("."));
                            backupkey = backupkey.substring(0, backupkey.lastIndexOf("."));
                            backupkey += "." + SDF.format(new Date());
                            backupkey += suffix;
                            OssUtils.copyObject(BUCKETNAME, REMOTE_DIR + "/" + f.getName(), BUCKETNAME, backupkey);
                            LOGGER.info("原文件备份完毕");
//                            clearDeprecatedBacks(BUCKETNAME, f.getName(), BUCKETNAME, "backup/");
                        }
                        OssUtils.uploadFile(BUCKETNAME, REMOTE_DIR + "/" + f.getName(), f);
                        LOGGER.info("上传完毕");
                    }
                }
                //检查服务器文件是否需要下载
                LOGGER.info("服务器文件数量：" + remotefiles.size());
                for(OSSObjectSummary remotefile : remotefiles) {
                    if(remotefile.getKey().startsWith(REMOTE_DIR + "/backup/")) {
                        continue;
                    }
                    String filename = remotefile.getKey().substring(remotefile.getKey().lastIndexOf("/") + 1);
                    if(!needUpload(filename)) {
                        continue;
                    }
                    boolean needdownload = true;
                    boolean needbackup = false;
                    LOGGER.debug("获取服务器文件属性:" + remotefile.getKey());
                    OSSObject obj = OssUtils.getObject(BUCKETNAME, remotefile);
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
                        if(filename.trim().equals(f.getName())) {
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
                        String localfilename = EXCELPATH + File.separator + filename;
                        File localfile = new File(localfilename);
                        if(needbackup) {
                            try {
                                LOGGER.info("需要备份原文件");
                                File backdir = new File(EXCELPATH + File.separator + "backup");
                                if(!backdir.exists()) {
                                    backdir.mkdirs();
                                }
                                File targetfile = new File(EXCELPATH + File.separator + "backup" + File.separator + filename.substring(0,filename.lastIndexOf(".")) + "." + SDF.format(new Date()) + filename.substring(filename.lastIndexOf(".")));
                                if(!targetfile.exists()) {
                                    targetfile.createNewFile();
                                }
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
                            } catch (IOException ex){
                                ex.printStackTrace();
                            }
                        }
                        OssUtils.downloadFile(BUCKETNAME, remotefile.getKey(), localfile);
                        localfile.setLastModified(remoteTime);
                        LOGGER.info("下载完毕");
                    }
                }
            }
        } catch (Exception ex) {
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
//        for(String s : EXCLUDE_FILES) {
//            if(s.equals(filename)) {
//                return false;
//            }
//        }
        return true;
    }
}
