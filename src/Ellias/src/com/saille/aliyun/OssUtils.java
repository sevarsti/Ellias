package com.saille.aliyun;

import com.GlobalConstant;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2017/08/21 0021
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class OssUtils {
    private final static Logger LOGGER = Logger.getLogger(OssUtils.class);
    private DataSource ds;
    private final static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    public static OSSClient OssClient;
    private static Map<String, OSSObject> OBJECT_CACHE = new HashMap<String, OSSObject>();
    private static Map<String, Date> OBJECT_MODIFY_DATE = new HashMap<String, Date>();

    public static void setAccessKeyId(String accessKeyId) {
        OssUtils.accessKeyId = accessKeyId;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssUtils.accessKeySecret = accessKeySecret;
    }

    private static String accessKeyId = "";
    private static String accessKeySecret = "";

    public static void init(String accessKeyId, String accessKeySecret) {
        OssUtils.accessKeyId = accessKeyId;
        OssUtils.accessKeySecret = accessKeySecret;
        OssUtils.OssClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    }

    public static void main(String[] args) {
        ListObjectsRequest req = new ListObjectsRequest();
        req.setBucketName("ellias-persistent");
        req.setPrefix("bbs/yssy/站庆/200");
        req.setDelimiter("/");
        req.setMaxKeys(1000);
        List<OSSObjectSummary> list = OssClient.listObjects(req).getObjectSummaries();
        for(OSSObjectSummary obj : list) {
            if(obj.getKey().endsWith(".jpg")) {
                String filename = obj.getKey().substring(obj.getKey().lastIndexOf("/") + 1);
                File f = new File(GlobalConstant.DISKPATH + "bbs\\bbs10\\" + filename);
                obj.setLastModified(new Date(f.lastModified()));
                System.out.println(obj);
//                CopyObjectRequest copyReq = new CopyObjectRequest();
//                copyReq.
//                ossClient.copyObject(CopyObjectRequest)
                OSSObject obj2 = OssClient.getObject("ellias-persistent", obj.getKey());
//                System.out.println(obj2);
            }
        }
//        List<Bucket> bucketList = ossClient.listBuckets();
//        for(Bucket b : bucketList) {
//            System.out.println(b);
//        }
        List<Bucket> bucketList = OssClient.listBuckets();
        for(Bucket b : bucketList) {
            System.out.println(b);
        }
    }

    public static boolean bucketExist(String bucketName) {
        return OssClient.doesBucketExist(bucketName);
    }

    public static List<OSSObjectSummary> listFiles(String bucketName, String dir) {
        ListObjectsRequest req = new ListObjectsRequest();
        req.setBucketName(bucketName);
        req.setPrefix(dir);
        ObjectListing list = OssClient.listObjects(req);
        return list.getObjectSummaries();
    }

    public static void downloadFile(String bucketName, String key, File localfile) {
        if(localfile.exists() && localfile.isFile()) {
            localfile.delete();
        }
//        String key = localfile.getName();
        OssClient.getObject(new GetObjectRequest(bucketName, key), localfile);
    }

    public static OSSObject getObject(String bucketName, OSSObjectSummary summary) {
        String key = summary.getKey();
        if(OBJECT_MODIFY_DATE.containsKey("[" + bucketName + "]" + key)) {
            if(summary.getLastModified().getTime() > OBJECT_MODIFY_DATE.get("[" + bucketName + "]" + key).getTime()) {
                LOGGER.info("读取文件详情：[" + bucketName + "]" + key);
                OSSObject obj = OssClient.getObject(bucketName, key);
                OBJECT_CACHE.put("[" + bucketName + "]" + key, obj);
                OBJECT_MODIFY_DATE.put("[" + bucketName + "]" + key, summary.getLastModified());
            }
            return OBJECT_CACHE.get("[" + bucketName + "]" + key);
        } else {
            LOGGER.info("读取文件详情：[" + bucketName + "]" + key);
            OSSObject obj = OssClient.getObject(bucketName, key);
            OBJECT_MODIFY_DATE.put("[" + bucketName + "]" + key, summary.getLastModified());
            OBJECT_CACHE.put("[" + bucketName + "]" + key, obj);
            return obj;
        }
    }

    public static void copyObject(String srcBucketname, String srcKey, String desBucketname, String desKey) {
        if(!OssClient.doesBucketExist(desBucketname)) {
            OssClient.createBucket(desBucketname);
        }
        OssClient.copyObject(srcBucketname, srcKey, desBucketname, desKey);
    }

    public static void uploadFile(String bucketName, String key, File localfile) {
        if(!localfile.exists() || localfile.isDirectory()) {
            return;
        }
        ObjectMetadata meta = new ObjectMetadata();
        meta.setLastModified(new Date(localfile.lastModified()));
        Map<String, String> usermetadata = new HashMap<String, String>();
        usermetadata.put("lastmodified", localfile.lastModified() + "");
        meta.setUserMetadata(usermetadata);
        LOGGER.info("上传文件：[" + bucketName + "]" + key);
        PutObjectResult result = OssClient.putObject(bucketName, key, localfile, meta);
        LOGGER.info("上传结果：" + result);
    }

    public static List<Bucket> sortBucket(List<Bucket> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(list.get(i).getName().compareTo(list.get(pos).getName()) > 0) {
                needSwap = true;
            }
            if(needSwap) {
                Bucket tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortBucket(list, start, pos);
        sortBucket(list, pos + 1, end);
        return list;
    }

    public static List<OSSObjectSummary> sortObject(List<OSSObjectSummary> list, int start, int end) {
        if(start >= end) {
            return list;
        }
        int pos = start;
        for(int i = pos + 1; i < end; i++) {
            boolean needSwap = false;
            if(list.get(i).getKey().compareTo(list.get(pos).getKey()) < 0) {
                needSwap = true;
            }
            if(needSwap) {
                OSSObjectSummary tmp = list.get(i);
                for(int m = i; m > pos; m--) {
                    list.set(m, list.get(m - 1));
                }
                list.set(pos, tmp);
            }
            pos = i;
        }
        sortObject(list, start, pos);
        sortObject(list, pos + 1, end);
        return list;
    }
}
