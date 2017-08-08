package com.tencent.feedback.upload;

import android.content.Context;
import android.net.Proxy;
import com.tencent.feedback.common.e;
import com.tencent.feedback.common.g;
import java.io.IOException;
import java.util.Locale;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

public abstract class d
{
  private static d a = null;

  public static d a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new a(paramContext.getApplicationContext());
      d locald = a;
      return locald;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public abstract byte[] a(String paramString, byte[] paramArrayOfByte, c paramc);

  public static final class a extends d
  {
    private Context a;

    public a(Context paramContext)
    {
      this.a = paramContext.getApplicationContext();
    }

    // ERROR //
    private HttpResponse a(String paramString1, byte[] paramArrayOfByte, String paramString2)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 4
      //   3: aload_1
      //   4: ifnonnull +15 -> 19
      //   7: ldc 24
      //   9: iconst_0
      //   10: anewarray 26	java/lang/Object
      //   13: invokestatic 32	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   16: aload 4
      //   18: areturn
      //   19: aload_2
      //   20: ifnull +81 -> 101
      //   23: new 34	org/apache/http/entity/ByteArrayEntity
      //   26: dup
      //   27: aload_2
      //   28: invokespecial 37	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
      //   31: astore 5
      //   33: aload_0
      //   34: aload_3
      //   35: invokespecial 40	com/tencent/feedback/upload/d$a:a	(Ljava/lang/String;)Lorg/apache/http/client/HttpClient;
      //   38: astore 11
      //   40: aload 11
      //   42: ifnonnull +98 -> 140
      //   45: ldc 42
      //   47: iconst_0
      //   48: anewarray 26	java/lang/Object
      //   51: invokestatic 32	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   54: aconst_null
      //   55: areturn
      //   56: astore 9
      //   58: aconst_null
      //   59: astore 7
      //   61: aload 9
      //   63: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
      //   66: iconst_1
      //   67: anewarray 26	java/lang/Object
      //   70: astore 10
      //   72: aload 10
      //   74: iconst_0
      //   75: aload 9
      //   77: invokevirtual 49	java/lang/Throwable:toString	()Ljava/lang/String;
      //   80: aastore
      //   81: ldc 51
      //   83: aload 10
      //   85: invokestatic 32	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   88: aload 7
      //   90: ifnull -74 -> 16
      //   93: aload 7
      //   95: invokevirtual 56	org/apache/http/client/methods/HttpPost:abort	()V
      //   98: aload 4
      //   100: areturn
      //   101: new 34	org/apache/http/entity/ByteArrayEntity
      //   104: dup
      //   105: ldc 58
      //   107: invokevirtual 64	java/lang/String:getBytes	()[B
      //   110: invokespecial 37	org/apache/http/entity/ByteArrayEntity:<init>	([B)V
      //   113: astore 5
      //   115: goto -82 -> 33
      //   118: astore 6
      //   120: aconst_null
      //   121: astore 7
      //   123: aload 6
      //   125: astore 8
      //   127: aload 7
      //   129: ifnull +8 -> 137
      //   132: aload 7
      //   134: invokevirtual 56	org/apache/http/client/methods/HttpPost:abort	()V
      //   137: aload 8
      //   139: athrow
      //   140: new 53	org/apache/http/client/methods/HttpPost
      //   143: dup
      //   144: aload_1
      //   145: invokespecial 67	org/apache/http/client/methods/HttpPost:<init>	(Ljava/lang/String;)V
      //   148: astore 7
      //   150: aload 7
      //   152: aload 5
      //   154: invokevirtual 71	org/apache/http/client/methods/HttpPost:setEntity	(Lorg/apache/http/HttpEntity;)V
      //   157: new 73	org/apache/http/protocol/BasicHttpContext
      //   160: dup
      //   161: invokespecial 74	org/apache/http/protocol/BasicHttpContext:<init>	()V
      //   164: astore 12
      //   166: aload 11
      //   168: aload 7
      //   170: aload 12
      //   172: invokeinterface 80 3 0
      //   177: astore 13
      //   179: aload 12
      //   181: ldc 82
      //   183: invokeinterface 88 2 0
      //   188: checkcast 90	org/apache/http/HttpRequest
      //   191: astore 15
      //   193: iconst_1
      //   194: anewarray 26	java/lang/Object
      //   197: astore 16
      //   199: aload 16
      //   201: iconst_0
      //   202: aload 15
      //   204: invokeinterface 94 1 0
      //   209: invokevirtual 95	java/lang/Object:toString	()Ljava/lang/String;
      //   212: aastore
      //   213: ldc 97
      //   215: aload 16
      //   217: invokestatic 100	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   220: aload 7
      //   222: invokevirtual 56	org/apache/http/client/methods/HttpPost:abort	()V
      //   225: aload 13
      //   227: areturn
      //   228: astore 8
      //   230: goto -103 -> 127
      //   233: astore 9
      //   235: aconst_null
      //   236: astore 4
      //   238: goto -177 -> 61
      //   241: astore 14
      //   243: aload 13
      //   245: astore 4
      //   247: aload 14
      //   249: astore 9
      //   251: goto -190 -> 61
      //
      // Exception table:
      //   from	to	target	type
      //   23	33	56	java/lang/Throwable
      //   33	40	56	java/lang/Throwable
      //   45	54	56	java/lang/Throwable
      //   101	115	56	java/lang/Throwable
      //   140	150	56	java/lang/Throwable
      //   23	33	118	finally
      //   33	40	118	finally
      //   45	54	118	finally
      //   101	115	118	finally
      //   140	150	118	finally
      //   61	88	228	finally
      //   150	179	228	finally
      //   179	220	228	finally
      //   150	179	233	java/lang/Throwable
      //   179	220	241	java/lang/Throwable
    }

    private HttpClient a(String paramString)
    {
      try
      {
        BasicHttpParams localBasicHttpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 30000);
        HttpConnectionParams.setSoTimeout(localBasicHttpParams, 10000);
        HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 2000);
        localBasicHttpParams.setBooleanParameter("http.protocol.handle-redirects", false);
        DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient(localBasicHttpParams);
        localDefaultHttpClient.setHttpRequestRetryHandler(new HttpRequestRetryHandler()
        {
          public final boolean retryRequest(IOException paramIOException, int paramInt, HttpContext paramHttpContext)
          {
            return false;
          }
        });
        if ((paramString != null) && (paramString.toLowerCase(Locale.US).contains("wap")))
        {
          e.a("rqdp{  use proxy} %s", new Object[] { paramString });
          HttpHost localHttpHost = new HttpHost(Proxy.getDefaultHost(), Proxy.getDefaultPort());
          localDefaultHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
          return localDefaultHttpClient;
        }
        localDefaultHttpClient.getParams().removeParameter("http.route.default-proxy");
        return localDefaultHttpClient;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        e.d("rqdp{  httpclient error!}", new Object[0]);
      }
      return null;
    }

    // ERROR //
    private static byte[] a(HttpResponse paramHttpResponse)
    {
      // Byte code:
      //   0: aload_0
      //   1: ifnonnull +5 -> 6
      //   4: aconst_null
      //   5: areturn
      //   6: aload_0
      //   7: invokeinterface 194 1 0
      //   12: invokeinterface 199 1 0
      //   17: istore_1
      //   18: iload_1
      //   19: sipush 200
      //   22: if_icmpeq +40 -> 62
      //   25: aload_0
      //   26: invokeinterface 194 1 0
      //   31: astore 14
      //   33: iconst_2
      //   34: anewarray 26	java/lang/Object
      //   37: astore 15
      //   39: aload 15
      //   41: iconst_0
      //   42: iload_1
      //   43: invokestatic 205	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
      //   46: aastore
      //   47: aload 15
      //   49: iconst_1
      //   50: aload 14
      //   52: aastore
      //   53: ldc 207
      //   55: aload 15
      //   57: invokestatic 210	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   60: aconst_null
      //   61: areturn
      //   62: aload_0
      //   63: invokeinterface 214 1 0
      //   68: astore_2
      //   69: aload_2
      //   70: ifnonnull +14 -> 84
      //   73: ldc 216
      //   75: iconst_0
      //   76: anewarray 26	java/lang/Object
      //   79: invokestatic 32	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   82: aconst_null
      //   83: areturn
      //   84: new 218	java/io/BufferedInputStream
      //   87: dup
      //   88: new 220	java/io/DataInputStream
      //   91: dup
      //   92: aload_2
      //   93: invokeinterface 226 1 0
      //   98: invokespecial 229	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
      //   101: invokespecial 230	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
      //   104: astore_3
      //   105: new 232	java/io/ByteArrayOutputStream
      //   108: dup
      //   109: invokespecial 233	java/io/ByteArrayOutputStream:<init>	()V
      //   112: astore 4
      //   114: aload_3
      //   115: invokevirtual 236	java/io/BufferedInputStream:read	()I
      //   118: istore 10
      //   120: iload 10
      //   122: iconst_m1
      //   123: if_icmpeq +61 -> 184
      //   126: aload 4
      //   128: iload 10
      //   130: invokevirtual 240	java/io/ByteArrayOutputStream:write	(I)V
      //   133: goto -19 -> 114
      //   136: astore 7
      //   138: aload 7
      //   140: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
      //   143: iconst_1
      //   144: anewarray 26	java/lang/Object
      //   147: astore 8
      //   149: aload 8
      //   151: iconst_0
      //   152: aload 7
      //   154: invokevirtual 49	java/lang/Throwable:toString	()Ljava/lang/String;
      //   157: aastore
      //   158: ldc 242
      //   160: aload 8
      //   162: invokestatic 32	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   165: aload_3
      //   166: ifnull -162 -> 4
      //   169: aload_3
      //   170: invokevirtual 245	java/io/BufferedInputStream:close	()V
      //   173: aconst_null
      //   174: areturn
      //   175: astore 9
      //   177: aload 9
      //   179: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
      //   182: aconst_null
      //   183: areturn
      //   184: aload 4
      //   186: invokevirtual 248	java/io/ByteArrayOutputStream:flush	()V
      //   189: aload 4
      //   191: invokevirtual 251	java/io/ByteArrayOutputStream:toByteArray	()[B
      //   194: astore 11
      //   196: aload_3
      //   197: invokevirtual 245	java/io/BufferedInputStream:close	()V
      //   200: aload 11
      //   202: areturn
      //   203: astore 12
      //   205: aload 12
      //   207: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
      //   210: aload 11
      //   212: areturn
      //   213: astore 13
      //   215: aconst_null
      //   216: astore_3
      //   217: aload 13
      //   219: astore 5
      //   221: aload_3
      //   222: ifnull +7 -> 229
      //   225: aload_3
      //   226: invokevirtual 245	java/io/BufferedInputStream:close	()V
      //   229: aload 5
      //   231: athrow
      //   232: astore 6
      //   234: aload 6
      //   236: invokevirtual 45	java/lang/Throwable:printStackTrace	()V
      //   239: goto -10 -> 229
      //   242: astore 5
      //   244: goto -23 -> 221
      //   247: astore 7
      //   249: aconst_null
      //   250: astore_3
      //   251: goto -113 -> 138
      //
      // Exception table:
      //   from	to	target	type
      //   105	114	136	java/lang/Throwable
      //   114	120	136	java/lang/Throwable
      //   126	133	136	java/lang/Throwable
      //   184	196	136	java/lang/Throwable
      //   169	173	175	java/lang/Throwable
      //   196	200	203	java/lang/Throwable
      //   84	105	213	finally
      //   225	229	232	java/lang/Throwable
      //   105	114	242	finally
      //   114	120	242	finally
      //   126	133	242	finally
      //   138	165	242	finally
      //   184	196	242	finally
      //   84	105	247	java/lang/Throwable
    }

    public final byte[] a(String paramString, byte[] paramArrayOfByte, c paramc)
    {
      if (paramString == null)
      {
        e.d("rqdp{  no destUrl!}", new Object[0]);
        return null;
      }
      int i = 0;
      int j = 0;
      long l1;
      int k;
      int m;
      label85: HttpResponse localHttpResponse;
      HttpEntity localHttpEntity;
      int n;
      byte[] arrayOfByte;
      long l3;
      if (paramArrayOfByte == null)
      {
        l1 = 0L;
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = paramString;
        arrayOfObject1[1] = Long.valueOf(l1);
        e.h("rqdp{  start req} %s rqdp{  sz:}%d", arrayOfObject1);
        k = 0;
        m = i + 1;
        if ((i >= 3) || (j >= 2))
          break label460;
        if (k == 0)
          break label189;
        k = 0;
        String str = g.c(this.a);
        if (paramc != null)
          paramc.a(paramString, l1, str);
        localHttpResponse = a(paramString, paramArrayOfByte, str);
        if (localHttpResponse == null)
          break label444;
        localHttpEntity = localHttpResponse.getEntity();
        n = localHttpResponse.getStatusLine().getStatusCode();
        if (n != 200)
          break label248;
        arrayOfByte = a(localHttpResponse);
        if (paramc != null)
        {
          if (arrayOfByte != null)
            break label239;
          l3 = 0L;
        }
      }
      while (true)
      {
        while (true)
        {
          paramc.a(l3);
          return arrayOfByte;
          l1 = paramArrayOfByte.length;
          break;
          label189: if (m <= 1)
            break label85;
          e.h("rqdp{  try time} " + m, new Object[0]);
          try
          {
            Thread.sleep(10000L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
        break label85;
        label239: l3 = arrayOfByte.length;
      }
      label248: int i1;
      if ((n == 301) || (n == 302) || (n == 303) || (n == 307))
        i1 = 1;
      int i2;
      int i4;
      int i3;
      while (i1 != 0)
      {
        i2 = 1;
        Header localHeader = localHttpResponse.getFirstHeader("Location");
        if (localHeader == null)
        {
          e.d("rqdp{  redirect code:}" + n + "rqdp{   Location is null! return}", new Object[0]);
          return null;
          i1 = 0;
          continue;
        }
        i4 = j + 1;
        i3 = 0;
        paramString = localHeader.getValue();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(n);
        arrayOfObject2[1] = paramString;
        e.h("rqdp{  redirect code:}%d rqdp{  , to:}%s", arrayOfObject2);
      }
      while (true)
      {
        long l2 = 0L;
        if (localHttpEntity != null)
        {
          l2 = localHttpEntity.getContentLength();
          if (l2 < 0L)
            l2 = 0L;
        }
        if (paramc != null)
          paramc.a(l2);
        k = i2;
        j = i4;
        i = i3;
        break;
        label444: if (paramc != null)
          paramc.a(0L);
        i = m;
        break;
        label460: return null;
        i2 = k;
        i3 = m;
        i4 = j;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.upload.d
 * JD-Core Version:    0.6.0
 */