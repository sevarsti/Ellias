package com.saille.aliyun;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import org.apache.log4j.Logger;

import javax.sql.DataSource;
import java.io.File;
import java.util.Date;
import java.util.List;

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

    public static void setAccessKeyId(String accessKeyId) {
        OssUtils.accessKeyId = accessKeyId;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        OssUtils.accessKeySecret = accessKeySecret;
    }

    private static String accessKeyId = "";
    private static String accessKeySecret = "";


    public static void main(String[] args) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ListObjectsRequest req = new ListObjectsRequest();
        req.setBucketName("ellias-persistent");
        req.setPrefix("bbs/yssy/Õ¾Çì/200");
        req.setDelimiter("/");
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
                OSSObject obj2 = ossClient.getObject("ellias-persistent", obj.getKey());
//                System.out.println(obj2);
            }
        }
//        List<Bucket> bucketList = ossClient.listBuckets();
//        for(Bucket b : bucketList) {
//            System.out.println(b);
//        }
        List<Bucket> bucketList = ossClient.listBuckets();
        for(Bucket b : bucketList) {
            System.out.println(b);
        }
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

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public DataSource getDs() {
        return ds;
    }
}
