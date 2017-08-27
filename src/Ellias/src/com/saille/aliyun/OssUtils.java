package com.saille.aliyun;

import com.aliyun.oss.OSSClient;
<<<<<<< HEAD
import com.aliyun.oss.model.*;

import java.util.List;
import java.util.Date;
import java.io.File;
=======
import com.aliyun.oss.model.Bucket;

import java.util.List;
>>>>>>> 4bb486eb3b30a733b133c33a07b6459d571e4a4d

/**
 * Created with IntelliJ IDEA.
 * User: Ellias
 * Date: 2017/08/21 0021
 * Time: 17:26
 * To change this template use File | Settings | File Templates.
 */
public class OssUtils {
    private final static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private final static String accessKeyId = "LTAID3hnLwsUASY4";
    private final static String accessKeySecret = "znxnT9S3n1vdK4SEuZT7LoW67WmAr4";

    public static void main(String[] args) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
<<<<<<< HEAD
        ListObjectsRequest req = new ListObjectsRequest();
        req.setBucketName("ellias-persistent");
        req.setPrefix("bbs/yssy/Õ¾Çì/200");
        req.setMaxKeys(1000);
        List<OSSObjectSummary> list = ossClient.listObjects(req).getObjectSummaries();
        for(OSSObjectSummary obj : list) {
            if(obj.getKey().endsWith(".jpg")) {
                String filename = obj.getKey().substring(obj.getKey().lastIndexOf("/") + 1);
                File f = new File("D:\\bbs\\bbs10\\" + filename);
                obj.setLastModified(new Date(f.lastModified()));
                System.out.println(obj);
//                CopyObjectRequest copyReq = new CopyObjectRequest();
//                copyReq.
//                ossClient.copyObject(CopyObjectRequest)
                OSSObject obj2 = ossClient.getObject("ellias-persistent", obj.getKey()).;
//                System.out.println(obj2);
            }
        }
//        List<Bucket> bucketList = ossClient.listBuckets();
//        for(Bucket b : bucketList) {
//            System.out.println(b);
//        }
=======
        List<Bucket> bucketList = ossClient.listBuckets();
        for(Bucket b : bucketList) {
            System.out.println(b);
        }
>>>>>>> 4bb486eb3b30a733b133c33a07b6459d571e4a4d
    }
}
