package com.tencent.stat;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.k;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

class d
{
  private static StatLogger c = k.b();
  private static long d = -1L;
  private static d e = null;
  private static Context f = null;
  DefaultHttpClient a = null;
  Handler b = null;

  private d()
  {
    try
    {
      HandlerThread localHandlerThread = new HandlerThread("StatDispatcher");
      localHandlerThread.start();
      d = localHandlerThread.getId();
      this.b = new Handler(localHandlerThread.getLooper());
      BasicHttpParams localBasicHttpParams = new BasicHttpParams();
      HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 10000);
      HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
      this.a = new DefaultHttpClient(localBasicHttpParams);
      this.a.setKeepAliveStrategy(new e(this));
      if (StatConfig.b() != null)
        this.a.getParams().setParameter("http.route.default-proxy", StatConfig.b());
      return;
    }
    catch (Throwable localThrowable)
    {
      c.e(localThrowable);
    }
  }

  static Context a()
  {
    return f;
  }

  static void a(Context paramContext)
  {
    f = paramContext.getApplicationContext();
  }

  static d b()
  {
    monitorenter;
    try
    {
      if (e == null)
        e = new d();
      d locald = e;
      return locald;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  void a(com.tencent.stat.a.e parame, c paramc)
  {
    String[] arrayOfString = new String[1];
    arrayOfString[0] = parame.d();
    b(Arrays.asList(arrayOfString), paramc);
  }

  void a(List<String> paramList, c paramc)
  {
    while (true)
    {
      int i;
      HttpPost localHttpPost;
      HttpHost localHttpHost;
      ByteArrayOutputStream localByteArrayOutputStream;
      byte[] arrayOfByte1;
      int k;
      HttpResponse localHttpResponse;
      HttpEntity localHttpEntity;
      int m;
      long l;
      try
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("[");
        i = 0;
        if (i >= paramList.size())
          continue;
        localStringBuilder.append((String)paramList.get(i));
        if (i == -1 + paramList.size())
          break label1143;
        localStringBuilder.append(",");
        break label1143;
        localStringBuilder.append("]");
        String str1 = StatConfig.getStatReportUrl();
        c.i("[" + str1 + "]Send request(" + localStringBuilder.toString().length() + "bytes):" + localStringBuilder.toString());
        localHttpPost = new HttpPost(str1);
        localHttpPost.addHeader("Accept-Encoding", "gzip");
        localHttpPost.setHeader("Connection", "Keep-Alive");
        localHttpPost.removeHeaders("Cache-Control");
        localHttpHost = k.a(f);
        int j = 0;
        if (localHttpHost == null)
          continue;
        this.a.getParams().setParameter("http.route.default-proxy", k.a(f));
        localHttpPost.addHeader("X-Online-Host", "pingma.qq.com:80");
        localHttpPost.addHeader("Accept", "*/*");
        localHttpPost.addHeader("Content-Type", "json");
        j = 1;
        localByteArrayOutputStream = new ByteArrayOutputStream();
        arrayOfByte1 = localStringBuilder.toString().getBytes("UTF-8");
        k = arrayOfByte1.length;
        if (localStringBuilder.length() < 256)
        {
          if (localHttpHost != null)
            continue;
          localHttpPost.addHeader("Content-Encoding", "rc4");
          localHttpPost.setEntity(new ByteArrayEntity(com.tencent.stat.common.e.a(arrayOfByte1)));
          localHttpResponse = this.a.execute(localHttpPost);
          if (j == 0)
            continue;
          this.a.getParams().removeParameter("http.route.default-proxy");
          localHttpEntity = localHttpResponse.getEntity();
          m = localHttpResponse.getStatusLine().getStatusCode();
          l = localHttpEntity.getContentLength();
          c.i("recv response status code:" + m + ", content length:" + l);
          if (l != 0L)
            break label648;
          EntityUtils.toString(localHttpEntity);
          if (m != 200)
            break label618;
          if (paramc == null)
            continue;
          paramc.a();
          localByteArrayOutputStream.close();
          return;
          localHttpPost.addHeader("X-Content-Encoding", "rc4");
          continue;
        }
      }
      catch (Throwable localThrowable1)
      {
        c.e(localThrowable1);
        if (paramc == null)
          break;
        try
        {
          paramc.b();
          return;
        }
        catch (Throwable localThrowable2)
        {
          c.e(localThrowable2);
          return;
        }
      }
      finally
      {
      }
      if (localHttpHost == null)
        localHttpPost.addHeader("Content-Encoding", "rc4,gzip");
      while (true)
      {
        localByteArrayOutputStream.write(new byte[4]);
        GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
        localGZIPOutputStream.write(arrayOfByte1);
        localGZIPOutputStream.close();
        arrayOfByte1 = localByteArrayOutputStream.toByteArray();
        ByteBuffer.wrap(arrayOfByte1, 0, 4).putInt(k);
        c.d("before Gzip:" + k + " bytes, after Gzip:" + arrayOfByte1.length + " bytes");
        break;
        localHttpPost.addHeader("X-Content-Encoding", "rc4,gzip");
      }
      label618: c.error("Server response error code:" + m);
      continue;
      label648: if (l > 0L)
      {
        InputStream localInputStream = localHttpEntity.getContent();
        DataInputStream localDataInputStream = new DataInputStream(localInputStream);
        Object localObject2 = new byte[(int)localHttpEntity.getContentLength()];
        localDataInputStream.readFully(localObject2);
        localInputStream.close();
        localDataInputStream.close();
        Header localHeader = localHttpResponse.getFirstHeader("Content-Encoding");
        if (localHeader != null)
        {
          if (localHeader.getValue().equalsIgnoreCase("gzip,rc4"))
          {
            byte[] arrayOfByte3 = com.tencent.stat.common.e.b(k.a(localObject2));
            localObject2 = arrayOfByte3;
          }
        }
        else
          label750: if (m != 200)
            break label1084;
        while (true)
        {
          try
          {
            String str2 = new String(localObject2, "UTF-8");
            c.d(str2);
            JSONObject localJSONObject = new JSONObject(str2);
            if (localJSONObject.isNull("cfg"))
              continue;
            StatConfig.a(localJSONObject.getJSONObject("cfg"));
            if ((localJSONObject.isNull("et")) || (localJSONObject.isNull("st")))
              continue;
            c.d("get mid respone:" + str2);
            if (localJSONObject.getInt("et") != com.tencent.stat.a.f.b.a())
              continue;
            int n = localJSONObject.getInt("st");
            switch (n)
            {
            default:
              c.e("error type for st:" + n);
              if (paramc == null)
                continue;
              paramc.a();
              localInputStream.close();
              break;
              if (!localHeader.getValue().equalsIgnoreCase("rc4,gzip"))
                continue;
              localObject2 = k.a(com.tencent.stat.common.e.b(localObject2));
              break label750;
              if (!localHeader.getValue().equalsIgnoreCase("gzip"))
                continue;
              localObject2 = k.a(localObject2);
              break label750;
              if (!localHeader.getValue().equalsIgnoreCase("rc4"))
                break label750;
              byte[] arrayOfByte2 = com.tencent.stat.common.e.b(localObject2);
              localObject2 = arrayOfByte2;
              break;
            case -1:
            case 0:
              if (localJSONObject.isNull("mid"))
                continue;
              StatMid.updateDeviceInfo(f, localJSONObject.getString("mid"));
              continue;
            }
          }
          catch (Throwable localThrowable3)
          {
            c.i(localThrowable3.toString());
            continue;
          }
          label1084: c.error("Server response error code:" + m + ", error:" + new String(localObject2, "UTF-8"));
        }
      }
      EntityUtils.toString(localHttpEntity);
      continue;
      label1143: i++;
    }
  }

  void b(List<String> paramList, c paramc)
  {
    if ((paramList.isEmpty()) || (this.b == null))
      return;
    this.b.post(new f(this, paramList, paramc));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.d
 * JD-Core Version:    0.6.0
 */