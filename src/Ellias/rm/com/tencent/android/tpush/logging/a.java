package com.tencent.android.tpush.logging;

import android.text.format.Time;
import com.tencent.android.tpush.logging.b.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.i;
import java.io.File;
import java.nio.charset.Charset;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

final class a extends Thread
{
  a(long paramLong1, long paramLong2)
  {
  }

  public void run()
  {
    long l1 = 1000L * this.a;
    long l2 = 1000L * this.b;
    Time localTime = new Time();
    localTime.set(l1);
    String str1 = localTime.format("%Y%m%d%H");
    TLog.i(LogUtil.a(), ">>startTime=" + localTime.hour + " time=" + this.a + " start" + str1);
    localTime.set(l2);
    String str2 = localTime.format("%Y%m%d%H");
    TLog.i(LogUtil.a(), ">>endTime=" + localTime.hour + " time=" + l2 + " end=" + str2);
    String str3 = LogUtil.a(l1, l2);
    HttpClient localHttpClient = com.tencent.android.tpush.service.report.a.a(null, null);
    HttpPost localHttpPost = new HttpPost(b.b);
    try
    {
      File localFile = new File(str3);
      MultipartEntity localMultipartEntity = new MultipartEntity();
      localMultipartEntity.addPart("file", new FileBody(localFile));
      localMultipartEntity.addPart("uin", new StringBody(LogUtil.uin));
      localMultipartEntity.addPart("token", new StringBody("" + CacheManager.getToken(i.e())));
      localMultipartEntity.addPart("timeStart", new StringBody(str1, Charset.forName("UTF-8")));
      StringBody localStringBody = new StringBody(str2, Charset.forName("UTF-8"));
      TLog.i(LogUtil.a(), "endbody=" + localStringBody.getCharset() + " " + localStringBody.toString());
      localMultipartEntity.addPart("timeEnd", localStringBody);
      localHttpPost.setEntity(localMultipartEntity);
      localHttpClient.execute(localHttpPost);
      localFile.delete();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    finally
    {
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a
 * JD-Core Version:    0.6.0
 */