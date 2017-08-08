package com.tencent.mid.a;

import com.tencent.mid.util.Util;
import com.tencent.mid.util.h;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public class b
{
  private static DefaultHttpClient a = null;
  private static int b = 50000;

  public static f a(String paramString)
  {
    a = a();
    Util.logInfo("http get:" + paramString);
    HttpGet localHttpGet = new HttpGet(paramString);
    HttpHost localHttpHost = Util.getHttpProxy(d.a());
    StringBuilder localStringBuilder = new StringBuilder().append("proxy==");
    String str1;
    if (localHttpHost == null)
    {
      str1 = "null";
      Util.logInfo(str1);
      if (localHttpHost == null)
        break label195;
      localHttpGet.addHeader("X-Online-Host", "pingmid.qq.com");
      localHttpGet.addHeader("Accept", "*/*");
      localHttpGet.removeHeaders("X-Content-Encoding");
    }
    while (true)
    {
      localHttpGet.addHeader("Accept-Encoding", "gzip");
      HttpResponse localHttpResponse = a.execute(localHttpGet);
      int i = localHttpResponse.getStatusLine().getStatusCode();
      String str2 = a(localHttpResponse);
      Util.logInfo("http get response code:" + i + " ,data:" + str2);
      return new f(i, str2);
      str1 = localHttpHost.getHostName();
      break;
      label195: a.getParams().removeParameter("http.route.default-proxy");
    }
  }

  public static f a(String paramString1, String paramString2)
  {
    a = a();
    Util.logInfo("[" + paramString1 + "]Send request(" + paramString2.length() + "bytes):" + paramString2);
    HttpPost localHttpPost = new HttpPost(paramString1);
    localHttpPost.addHeader("Accept-Encoding", "gzip");
    localHttpPost.setHeader("Connection", "Keep-Alive");
    localHttpPost.removeHeaders("Cache-Control");
    HttpHost localHttpHost = Util.getHttpProxy(d.a());
    StringBuilder localStringBuilder = new StringBuilder().append("proxy==");
    String str1;
    label150: ByteArrayOutputStream localByteArrayOutputStream;
    byte[] arrayOfByte;
    int i;
    label194: HttpResponse localHttpResponse;
    int j;
    String str2;
    if (localHttpHost == null)
    {
      str1 = "null";
      Util.logInfo(str1);
      if (localHttpHost == null)
        break label359;
      localHttpPost.addHeader("X-Online-Host", "pingmid.qq.com");
      localHttpPost.addHeader("Accept", "*/*");
      localHttpPost.addHeader("Content-Type", "json");
      localByteArrayOutputStream = new ByteArrayOutputStream();
      arrayOfByte = paramString2.getBytes("UTF-8");
      i = arrayOfByte.length;
      if (paramString2.length() >= 256)
        break label387;
      if (localHttpHost != null)
        break label376;
      localHttpPost.addHeader("Content-Encoding", "rc4");
      localByteArrayOutputStream.close();
      localHttpPost.setEntity(new ByteArrayEntity(h.a(arrayOfByte)));
      localHttpResponse = a.execute(localHttpPost);
      HttpEntity localHttpEntity = localHttpResponse.getEntity();
      j = localHttpResponse.getStatusLine().getStatusCode();
      long l = localHttpEntity.getContentLength();
      Util.logInfo("recv response status code:" + j + ", content length:" + l);
      str2 = null;
      if (l > 0L)
        break label503;
      EntityUtils.toString(localHttpEntity);
    }
    while (true)
    {
      Util.logInfo("recv response status code:" + j + ", content :" + str2);
      return new f(j, str2);
      str1 = localHttpHost.getHostName();
      break;
      label359: a.getParams().removeParameter("http.route.default-proxy");
      break label150;
      label376: localHttpPost.addHeader("X-Content-Encoding", "rc4");
      break label194;
      label387: if (localHttpHost == null)
        localHttpPost.addHeader("Content-Encoding", "rc4,gzip");
      while (true)
      {
        localByteArrayOutputStream.write(new byte[4]);
        GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
        localGZIPOutputStream.write(arrayOfByte);
        localGZIPOutputStream.close();
        arrayOfByte = localByteArrayOutputStream.toByteArray();
        ByteBuffer.wrap(arrayOfByte, 0, 4).putInt(i);
        Util.logInfo("before Gzip:" + i + " bytes, after Gzip:" + arrayOfByte.length + " bytes");
        break;
        localHttpPost.addHeader("X-Content-Encoding", "rc4,gzip");
      }
      label503: str2 = a(localHttpResponse);
    }
  }

  public static String a(Map<String, String> paramMap)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if ((paramMap != null) && (paramMap.size() != 0))
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      int i = 0;
      if (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        if (i == 0);
        for (String str = "?"; ; str = "&")
        {
          int j = i + 1;
          localStringBuilder.append(str);
          localStringBuilder.append((String)localEntry.getKey());
          localStringBuilder.append("=");
          localStringBuilder.append((String)localEntry.getValue());
          i = j;
          break;
        }
      }
    }
    return localStringBuilder.toString();
  }

  private static String a(HttpResponse paramHttpResponse)
  {
    InputStream localInputStream = paramHttpResponse.getEntity().getContent();
    DataInputStream localDataInputStream = new DataInputStream(localInputStream);
    byte[] arrayOfByte = new byte[(int)paramHttpResponse.getEntity().getContentLength()];
    localDataInputStream.readFully(arrayOfByte);
    localInputStream.close();
    localDataInputStream.close();
    Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
    if (localHeader != null)
    {
      if (!localHeader.getValue().equalsIgnoreCase("gzip,rc4"))
        break label99;
      arrayOfByte = h.b(Util.deocdeGZipContent(arrayOfByte));
    }
    while (true)
    {
      return new String(arrayOfByte, "UTF-8");
      label99: if (localHeader.getValue().equalsIgnoreCase("rc4,gzip"))
      {
        arrayOfByte = Util.deocdeGZipContent(h.b(arrayOfByte));
        continue;
      }
      if (localHeader.getValue().equalsIgnoreCase("gzip"))
      {
        arrayOfByte = Util.deocdeGZipContent(arrayOfByte);
        continue;
      }
      if (!localHeader.getValue().equalsIgnoreCase("rc4"))
        continue;
      arrayOfByte = h.b(arrayOfByte);
    }
  }

  // ERROR //
  public static DefaultHttpClient a()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   6: ifnonnull +265 -> 271
    //   9: invokestatic 328	com/tencent/mid/api/MidService:isEnableDebug	()Z
    //   12: istore 4
    //   14: iload 4
    //   16: ifeq +77 -> 93
    //   19: ldc_w 330
    //   22: invokestatic 336	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   25: getstatic 342	java/util/logging/Level:FINER	Ljava/util/logging/Level;
    //   28: invokevirtual 346	java/util/logging/Logger:setLevel	(Ljava/util/logging/Level;)V
    //   31: ldc_w 348
    //   34: invokestatic 336	java/util/logging/Logger:getLogger	(Ljava/lang/String;)Ljava/util/logging/Logger;
    //   37: getstatic 342	java/util/logging/Level:FINER	Ljava/util/logging/Level;
    //   40: invokevirtual 346	java/util/logging/Logger:setLevel	(Ljava/util/logging/Level;)V
    //   43: ldc_w 350
    //   46: ldc_w 352
    //   49: invokestatic 358	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   52: pop
    //   53: ldc_w 360
    //   56: ldc_w 362
    //   59: invokestatic 358	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   62: pop
    //   63: ldc_w 364
    //   66: ldc_w 366
    //   69: invokestatic 358	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   72: pop
    //   73: ldc_w 368
    //   76: ldc_w 366
    //   79: invokestatic 358	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   82: pop
    //   83: ldc_w 370
    //   86: ldc_w 366
    //   89: invokestatic 358	java/lang/System:setProperty	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   92: pop
    //   93: new 372	org/apache/http/params/BasicHttpParams
    //   96: dup
    //   97: invokespecial 373	org/apache/http/params/BasicHttpParams:<init>	()V
    //   100: astore 5
    //   102: aload 5
    //   104: getstatic 15	com/tencent/mid/a/b:b	I
    //   107: i2l
    //   108: invokestatic 379	org/apache/http/conn/params/ConnManagerParams:setTimeout	(Lorg/apache/http/params/HttpParams;J)V
    //   111: aload 5
    //   113: new 381	org/apache/http/conn/params/ConnPerRouteBean
    //   116: dup
    //   117: bipush 20
    //   119: invokespecial 384	org/apache/http/conn/params/ConnPerRouteBean:<init>	(I)V
    //   122: invokestatic 388	org/apache/http/conn/params/ConnManagerParams:setMaxConnectionsPerRoute	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/params/ConnPerRoute;)V
    //   125: aload 5
    //   127: bipush 100
    //   129: invokestatic 392	org/apache/http/conn/params/ConnManagerParams:setMaxTotalConnections	(Lorg/apache/http/params/HttpParams;I)V
    //   132: aload 5
    //   134: getstatic 398	org/apache/http/HttpVersion:HTTP_1_1	Lorg/apache/http/HttpVersion;
    //   137: invokestatic 404	org/apache/http/params/HttpProtocolParams:setVersion	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/ProtocolVersion;)V
    //   140: aload 5
    //   142: ldc 163
    //   144: invokestatic 408	org/apache/http/params/HttpProtocolParams:setContentCharset	(Lorg/apache/http/params/HttpParams;Ljava/lang/String;)V
    //   147: aload 5
    //   149: iconst_0
    //   150: invokestatic 414	org/apache/http/params/HttpConnectionParams:setStaleCheckingEnabled	(Lorg/apache/http/params/HttpParams;Z)V
    //   153: aload 5
    //   155: iconst_1
    //   156: invokestatic 419	org/apache/http/client/params/HttpClientParams:setRedirecting	(Lorg/apache/http/params/HttpParams;Z)V
    //   159: aload 5
    //   161: getstatic 15	com/tencent/mid/a/b:b	I
    //   164: invokestatic 422	org/apache/http/params/HttpConnectionParams:setSoTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   167: aload 5
    //   169: getstatic 15	com/tencent/mid/a/b:b	I
    //   172: invokestatic 425	org/apache/http/params/HttpConnectionParams:setConnectionTimeout	(Lorg/apache/http/params/HttpParams;I)V
    //   175: aload 5
    //   177: iconst_1
    //   178: invokestatic 428	org/apache/http/params/HttpConnectionParams:setTcpNoDelay	(Lorg/apache/http/params/HttpParams;Z)V
    //   181: new 430	org/apache/http/conn/scheme/SchemeRegistry
    //   184: dup
    //   185: invokespecial 431	org/apache/http/conn/scheme/SchemeRegistry:<init>	()V
    //   188: astore 6
    //   190: aload 6
    //   192: new 433	org/apache/http/conn/scheme/Scheme
    //   195: dup
    //   196: ldc_w 435
    //   199: invokestatic 441	org/apache/http/conn/scheme/PlainSocketFactory:getSocketFactory	()Lorg/apache/http/conn/scheme/PlainSocketFactory;
    //   202: bipush 80
    //   204: invokespecial 444	org/apache/http/conn/scheme/Scheme:<init>	(Ljava/lang/String;Lorg/apache/http/conn/scheme/SocketFactory;I)V
    //   207: invokevirtual 448	org/apache/http/conn/scheme/SchemeRegistry:register	(Lorg/apache/http/conn/scheme/Scheme;)Lorg/apache/http/conn/scheme/Scheme;
    //   210: pop
    //   211: new 80	org/apache/http/impl/client/DefaultHttpClient
    //   214: dup
    //   215: new 450	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager
    //   218: dup
    //   219: aload 5
    //   221: aload 6
    //   223: invokespecial 453	org/apache/http/impl/conn/tsccm/ThreadSafeClientConnManager:<init>	(Lorg/apache/http/params/HttpParams;Lorg/apache/http/conn/scheme/SchemeRegistry;)V
    //   226: aload 5
    //   228: invokespecial 456	org/apache/http/impl/client/DefaultHttpClient:<init>	(Lorg/apache/http/conn/ClientConnectionManager;Lorg/apache/http/params/HttpParams;)V
    //   231: putstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   234: invokestatic 49	com/tencent/mid/a/d:a	()Landroid/content/Context;
    //   237: invokestatic 53	com/tencent/mid/util/Util:getHttpProxy	(Landroid/content/Context;)Lorg/apache/http/HttpHost;
    //   240: astore 8
    //   242: getstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   245: invokevirtual 120	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   248: ldc 122
    //   250: aload 8
    //   252: invokeinterface 460 3 0
    //   257: pop
    //   258: getstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   261: new 462	com/tencent/mid/a/c
    //   264: dup
    //   265: invokespecial 463	com/tencent/mid/a/c:<init>	()V
    //   268: invokevirtual 469	org/apache/http/impl/client/AbstractHttpClient:setKeepAliveStrategy	(Lorg/apache/http/conn/ConnectionKeepAliveStrategy;)V
    //   271: invokestatic 49	com/tencent/mid/a/d:a	()Landroid/content/Context;
    //   274: invokestatic 53	com/tencent/mid/util/Util:getHttpProxy	(Landroid/content/Context;)Lorg/apache/http/HttpHost;
    //   277: astore_1
    //   278: getstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   281: invokevirtual 120	org/apache/http/impl/client/DefaultHttpClient:getParams	()Lorg/apache/http/params/HttpParams;
    //   284: ldc 122
    //   286: aload_1
    //   287: invokeinterface 460 3 0
    //   292: pop
    //   293: getstatic 12	com/tencent/mid/a/b:a	Lorg/apache/http/impl/client/DefaultHttpClient;
    //   296: astore_3
    //   297: ldc 2
    //   299: monitorexit
    //   300: aload_3
    //   301: areturn
    //   302: astore 10
    //   304: aload 10
    //   306: invokevirtual 472	java/lang/Throwable:printStackTrace	()V
    //   309: goto -38 -> 271
    //   312: astore_0
    //   313: ldc 2
    //   315: monitorexit
    //   316: aload_0
    //   317: athrow
    //   318: astore 11
    //   320: goto -227 -> 93
    //
    // Exception table:
    //   from	to	target	type
    //   258	271	302	java/lang/Throwable
    //   3	14	312	finally
    //   19	93	312	finally
    //   93	258	312	finally
    //   258	271	312	finally
    //   271	297	312	finally
    //   304	309	312	finally
    //   19	93	318	java/lang/Throwable
  }

  public static void b()
  {
    try
    {
      if (a != null)
      {
        a.getConnectionManager().shutdown();
        a = null;
        Util.logInfo("close http client.");
      }
      return;
    }
    catch (Throwable localThrowable)
    {
      Util.logWarn(localThrowable);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.mid.a.b
 * JD-Core Version:    0.6.0
 */