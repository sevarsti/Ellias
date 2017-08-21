package com.saille.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;

import java.util.List;

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
        List<Bucket> bucketList = ossClient.listBuckets();
        for(Bucket b : bucketList) {
            System.out.println(b);
        }
    }
}
