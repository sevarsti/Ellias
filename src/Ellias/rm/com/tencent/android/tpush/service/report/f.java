package com.tencent.android.tpush.service.report;

import android.os.Bundle;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.i;
import java.net.SocketTimeoutException;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.ByteArrayEntity;

class f extends Thread
{
  f(e parame, String paramString, Bundle paramBundle)
  {
  }

  public void run()
  {
    TLog.i("ReportLogTag", "ReportManager doUploadItems Thread start, url = " + this.a);
    e.a(this.c, com.tencent.android.tpush.service.a.a.k);
    int i = 0;
    int j = 0;
    while (true)
    {
      j++;
      TLog.i("ReportLogTag", "ReportManager doUploadItems Thread request count = " + j);
      try
      {
        HttpClient localHttpClient = a.a(null, this.a);
        HttpPost localHttpPost = new HttpPost(this.a);
        localHttpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        localHttpPost.setEntity(new ByteArrayEntity(a.a(this.b).getBytes()));
        if (localHttpClient.execute(localHttpPost).getStatusLine().getStatusCode() != 200)
          TLog.e("ReportLogTag", "ReportManager doUploadItems : HttpStatuscode != 200");
        while (true)
        {
          e.a(this.c, false);
          if (i == 0)
          {
            TLog.i("TPush", "ReportManager doUploadItems Thread request failed");
            e.c(this.c).a(i.e(), e.b(this.c));
          }
          return;
          i = 1;
          TLog.i("ReportLogTag", "ReportManager doUploadItems Thread success");
        }
      }
      catch (ConnectTimeoutException localConnectTimeoutException)
      {
        while (true)
        {
          TLog.e("TPush", "ReportManager doUploadItems : ConnectTimeoutException");
          if (j < e.a(this.c))
            break;
        }
      }
      catch (Exception localException)
      {
        while (true)
          TLog.e("TPush", "ReportManager doUploadItems : Exception");
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        label218: break label218;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.report.f
 * JD-Core Version:    0.6.0
 */