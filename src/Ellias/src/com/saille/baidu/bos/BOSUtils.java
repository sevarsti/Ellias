package com.saille.baidu.bos;

import com.baidubce.services.bos.BosClientConfiguration;
import com.baidubce.services.bos.BosClient;
import com.baidubce.services.bos.model.*;
import com.baidubce.auth.DefaultBceCredentials;

import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;
import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: ELLIAS
 * Date: 2015-12-5
 * Time: 18:53:54
 * To change this template use File | Settings | File Templates.
 */
public class BOSUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(BOSUtils.class);
    private final static String ACCESS_KEY_ID = "a57dac57f60047ccab67151ffa5cbd54";
    private final static String SECRET_ACCESS_KEY = "31ffa06873584dfcbd11697944635479";
    private static BosClient CLIENT;
    private static Map<String, BosObject> OBJECT_CACHE = new HashMap<String, BosObject>();
    private static Map<String, Date> OBJECT_MODIFY_DATE = new HashMap<String, Date>();

    static {
        init();
    }

    public static void init() {
        BosClientConfiguration config = new BosClientConfiguration();
        config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY));
        config.setSocketTimeoutInMillis(1000 * 30);
        config.setConnectionTimeoutInMillis(1000 * 30);
        CLIENT = new BosClient(config);
    }
    
    public static boolean bucketExist(String bucketName) {
        return CLIENT.doesBucketExist(bucketName);
    }

    public static boolean createBucket(String bucketName) {
        try {
            CLIENT.createBucket(bucketName);
            return true;
        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static List<BosObjectSummary> listObjects(String bucketName) {
        return CLIENT.listObjects(bucketName).getContents();
    }

    public static List<BosObjectSummary> listObjects(String bucketName, String dir) {
        ListObjectsRequest request = new ListObjectsRequest(bucketName);
        request.setDelimiter("/");
        request.setPrefix(dir);
        return CLIENT.listObjects(request).getContents();
    }

    public static void deleteObject(BosObjectSummary obj) {
        CLIENT.deleteObject(obj.getBucketName(), obj.getKey());
    }

    public static BosObject getObject(String bucketName, BosObjectSummary summary) {
        String filename = summary.getKey();
        if(OBJECT_MODIFY_DATE.containsKey(bucketName + "_" + filename)) {
            if(summary.getLastModified().getTime() > OBJECT_MODIFY_DATE.get(bucketName + "_" + filename).getTime()) {
                LOGGER.info("读取文件详情：" + bucketName + "_" + filename);
                BosObject obj = CLIENT.getObject(bucketName, filename);
                OBJECT_CACHE.put(bucketName + "_" + filename, obj);
                OBJECT_MODIFY_DATE.put(bucketName + "_" + filename, summary.getLastModified());
            }
            return OBJECT_CACHE.get(bucketName + "_" + filename);
        } else {
            LOGGER.info("读取文件详情：" + bucketName + "_" + filename);
            BosObject obj = CLIENT.getObject(bucketName, filename);
            OBJECT_MODIFY_DATE.put(bucketName + "_" + filename, summary.getLastModified());
            OBJECT_CACHE.put(bucketName + "_" + filename, obj);
            return obj;
        }
    }

    public static void uploadFile(String bucketName, File localfile) {
        if(!localfile.exists() || localfile.isDirectory()) {
            return;
        }
        String filename = localfile.getName();
        PutObjectRequest request = new PutObjectRequest(bucketName, filename, localfile);
        request.getObjectMetadata().setLastModified(new Date(localfile.lastModified()));
        ObjectMetadata metadata = request.getObjectMetadata();
        Map<String, String> usermetadata = new HashMap<String, String>();
        usermetadata.put("lastmodified", localfile.lastModified() + "");
        metadata.setUserMetadata(usermetadata);
        LOGGER.info("上传文件：" + bucketName + "_" + filename);
        PutObjectResponse response = CLIENT.putObject(bucketName, filename, localfile, metadata);
        System.out.println(response);
    }

    public static void downloadFile(String bucketName, File localfile) {
        if(localfile.exists() && localfile.isFile()) {
            localfile.delete();
        }
        String key = localfile.getName();
        LOGGER.info("下载文件：" + bucketName + "_" + key);
        CLIENT.getObject(bucketName, key, localfile);
    }

    public static void copyObject(String srcBucketname, String srcKey, String desBucketname, String desKey) {
        if(!CLIENT.doesBucketExist(desBucketname)) {
            CLIENT.createBucket(desBucketname);
        }
        CLIENT.copyObject(srcBucketname, srcKey, desBucketname, desKey);
    }

    public static void main(String[] args) {

        List<BucketSummary> buckets = CLIENT.listBuckets().getBuckets();

        // 遍历Bucket
        String buketname = "";
        for (BucketSummary bucket : buckets) {
            buketname = bucket.getName();
            System.out.println(bucket.getName());
        }
        try {
            BosObject obj = CLIENT.getObject("testellias", "rm_song");
            System.out.println(obj);
//            putObject(client, buketname, "rm_song", GlobalConstant.DISKPATH + "excel\\节奏大师歌曲.xls");
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void putObject(BosClient client, String bucketName, String objectKey, String filename) throws Exception{
        File file = new File(filename);
        PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);
//        PutObjectResponse putObjectFromFileResponse = client.putObject(bucketName, objectKey, file);
        System.out.println(putObjectFromFileResponse);
    }

}
