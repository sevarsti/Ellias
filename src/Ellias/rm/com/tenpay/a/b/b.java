package com.tenpay.a.b;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class b extends a
{
  private static final String c = b.class.getSimpleName();
  private HttpPost d = new HttpPost();

  public b()
  {
  }

  public b(boolean paramBoolean)
  {
    super(paramBoolean);
  }

  private List c(String paramString)
  {
    if ((paramString != null) && (paramString.length() > 0))
    {
      String[] arrayOfString = paramString.split("&");
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; ; i++)
      {
        if (i >= arrayOfString.length)
          return localArrayList;
        int j = arrayOfString[i].indexOf("=");
        if ((j <= 0) || (j >= -1 + arrayOfString[i].length()))
          continue;
        localArrayList.add(new BasicNameValuePair(arrayOfString[i].substring(0, j), arrayOfString[i].substring(j + 1)));
      }
    }
    return null;
  }

  // ERROR //
  public android.os.Bundle a(String paramString)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 83	android/os/Bundle
    //   5: dup
    //   6: invokespecial 84	android/os/Bundle:<init>	()V
    //   9: astore_3
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 88	com/tenpay/a/b/b:b	(Ljava/lang/String;)Z
    //   15: ifne +10 -> 25
    //   18: aload_0
    //   19: aload_3
    //   20: invokevirtual 92	com/tenpay/a/b/b:e	(Landroid/os/Bundle;)V
    //   23: aload_3
    //   24: areturn
    //   25: aload_0
    //   26: aload_3
    //   27: invokevirtual 94	com/tenpay/a/b/b:b	(Landroid/os/Bundle;)V
    //   30: aload_0
    //   31: invokevirtual 97	com/tenpay/a/b/b:b	()Lorg/apache/http/client/HttpClient;
    //   34: astore 11
    //   36: aload 11
    //   38: astore 5
    //   40: aload_0
    //   41: getfield 26	com/tenpay/a/b/b:d	Lorg/apache/http/client/methods/HttpPost;
    //   44: ldc 99
    //   46: ldc 101
    //   48: invokevirtual 104	org/apache/http/client/methods/HttpPost:setHeader	(Ljava/lang/String;Ljava/lang/String;)V
    //   51: aload_0
    //   52: getfield 26	com/tenpay/a/b/b:d	Lorg/apache/http/client/methods/HttpPost;
    //   55: invokevirtual 108	org/apache/http/client/methods/HttpPost:getURI	()Ljava/net/URI;
    //   58: astore 12
    //   60: aconst_null
    //   61: astore_2
    //   62: aload 12
    //   64: ifnull +123 -> 187
    //   67: aload_0
    //   68: getfield 111	com/tenpay/a/b/b:b	Lorg/apache/http/HttpHost;
    //   71: astore 13
    //   73: aconst_null
    //   74: astore_2
    //   75: aload 13
    //   77: ifnull +22 -> 99
    //   80: aload 5
    //   82: invokeinterface 117 1 0
    //   87: ldc 119
    //   89: aload_0
    //   90: getfield 111	com/tenpay/a/b/b:b	Lorg/apache/http/HttpHost;
    //   93: invokeinterface 125 3 0
    //   98: pop
    //   99: aload_0
    //   100: getfield 128	com/tenpay/a/b/b:a	Z
    //   103: istore 15
    //   105: aconst_null
    //   106: astore_2
    //   107: iload 15
    //   109: ifeq +31 -> 140
    //   112: aload_0
    //   113: aload_3
    //   114: invokevirtual 130	com/tenpay/a/b/b:d	(Landroid/os/Bundle;)V
    //   117: aload 5
    //   119: ifnull +15 -> 134
    //   122: aload 5
    //   124: invokeinterface 134 1 0
    //   129: invokeinterface 139 1 0
    //   134: iconst_0
    //   135: ifeq -112 -> 23
    //   138: aload_3
    //   139: areturn
    //   140: aload 5
    //   142: aload_0
    //   143: getfield 26	com/tenpay/a/b/b:d	Lorg/apache/http/client/methods/HttpPost;
    //   146: invokeinterface 143 2 0
    //   151: astore_2
    //   152: aload_0
    //   153: getfield 128	com/tenpay/a/b/b:a	Z
    //   156: ifeq +59 -> 215
    //   159: aload_0
    //   160: aload_3
    //   161: invokevirtual 130	com/tenpay/a/b/b:d	(Landroid/os/Bundle;)V
    //   164: aload 5
    //   166: ifnull +15 -> 181
    //   169: aload 5
    //   171: invokeinterface 134 1 0
    //   176: invokeinterface 139 1 0
    //   181: aload_2
    //   182: ifnull -159 -> 23
    //   185: aload_3
    //   186: areturn
    //   187: aload_0
    //   188: aload_3
    //   189: invokevirtual 92	com/tenpay/a/b/b:e	(Landroid/os/Bundle;)V
    //   192: aload 5
    //   194: ifnull +15 -> 209
    //   197: aload 5
    //   199: invokeinterface 134 1 0
    //   204: invokeinterface 139 1 0
    //   209: iconst_0
    //   210: ifeq -187 -> 23
    //   213: aload_3
    //   214: areturn
    //   215: aload_2
    //   216: invokeinterface 149 1 0
    //   221: invokeinterface 154 1 0
    //   226: istore 16
    //   228: aload_3
    //   229: ldc 156
    //   231: iload 16
    //   233: invokevirtual 160	android/os/Bundle:putInt	(Ljava/lang/String;I)V
    //   236: iload 16
    //   238: sipush 200
    //   241: if_icmpeq +41 -> 282
    //   244: iload 16
    //   246: sipush 206
    //   249: if_icmpeq +33 -> 282
    //   252: aload_0
    //   253: getfield 26	com/tenpay/a/b/b:d	Lorg/apache/http/client/methods/HttpPost;
    //   256: invokevirtual 163	org/apache/http/client/methods/HttpPost:abort	()V
    //   259: aload 5
    //   261: ifnull +15 -> 276
    //   264: aload 5
    //   266: invokeinterface 134 1 0
    //   271: invokeinterface 139 1 0
    //   276: aload_2
    //   277: ifnull -254 -> 23
    //   280: aload_3
    //   281: areturn
    //   282: aload_0
    //   283: aload_3
    //   284: aload_2
    //   285: invokevirtual 166	com/tenpay/a/b/b:a	(Landroid/os/Bundle;Lorg/apache/http/HttpResponse;)V
    //   288: aload_0
    //   289: aload_3
    //   290: aload_2
    //   291: invokevirtual 168	com/tenpay/a/b/b:b	(Landroid/os/Bundle;Lorg/apache/http/HttpResponse;)V
    //   294: aload 5
    //   296: ifnull +15 -> 311
    //   299: aload 5
    //   301: invokeinterface 134 1 0
    //   306: invokeinterface 139 1 0
    //   311: aload_2
    //   312: ifnull -289 -> 23
    //   315: aload_3
    //   316: areturn
    //   317: astore 10
    //   319: aconst_null
    //   320: astore 5
    //   322: aload 10
    //   324: invokevirtual 171	org/apache/http/client/ClientProtocolException:printStackTrace	()V
    //   327: aload 5
    //   329: ifnull +15 -> 344
    //   332: aload 5
    //   334: invokeinterface 134 1 0
    //   339: invokeinterface 139 1 0
    //   344: aload_2
    //   345: ifnull -322 -> 23
    //   348: aload_3
    //   349: areturn
    //   350: astore 9
    //   352: aconst_null
    //   353: astore 5
    //   355: aload 9
    //   357: invokevirtual 172	java/net/SocketException:printStackTrace	()V
    //   360: aload 5
    //   362: ifnull +15 -> 377
    //   365: aload 5
    //   367: invokeinterface 134 1 0
    //   372: invokeinterface 139 1 0
    //   377: aload_2
    //   378: ifnull -355 -> 23
    //   381: aload_3
    //   382: areturn
    //   383: astore 8
    //   385: aconst_null
    //   386: astore 5
    //   388: aload 8
    //   390: invokevirtual 173	java/net/SocketTimeoutException:printStackTrace	()V
    //   393: aload_0
    //   394: aload_3
    //   395: invokevirtual 175	com/tenpay/a/b/b:c	(Landroid/os/Bundle;)V
    //   398: aload 5
    //   400: ifnull +15 -> 415
    //   403: aload 5
    //   405: invokeinterface 134 1 0
    //   410: invokeinterface 139 1 0
    //   415: aload_2
    //   416: ifnull -393 -> 23
    //   419: aload_3
    //   420: areturn
    //   421: astore 7
    //   423: aconst_null
    //   424: astore 5
    //   426: aload 7
    //   428: invokevirtual 176	java/io/IOException:printStackTrace	()V
    //   431: aload 5
    //   433: ifnull +15 -> 448
    //   436: aload 5
    //   438: invokeinterface 134 1 0
    //   443: invokeinterface 139 1 0
    //   448: aload_2
    //   449: ifnull -426 -> 23
    //   452: aload_3
    //   453: areturn
    //   454: astore 6
    //   456: aconst_null
    //   457: astore 5
    //   459: aload 6
    //   461: invokevirtual 177	java/lang/Throwable:printStackTrace	()V
    //   464: aload 5
    //   466: ifnull +15 -> 481
    //   469: aload 5
    //   471: invokeinterface 134 1 0
    //   476: invokeinterface 139 1 0
    //   481: aload_2
    //   482: ifnull -459 -> 23
    //   485: aload_3
    //   486: areturn
    //   487: astore 4
    //   489: aconst_null
    //   490: astore 5
    //   492: aload 5
    //   494: ifnull +15 -> 509
    //   497: aload 5
    //   499: invokeinterface 134 1 0
    //   504: invokeinterface 139 1 0
    //   509: aload_2
    //   510: ifnull +3 -> 513
    //   513: aload 4
    //   515: athrow
    //   516: astore 4
    //   518: goto -26 -> 492
    //   521: astore 6
    //   523: goto -64 -> 459
    //   526: astore 7
    //   528: goto -102 -> 426
    //   531: astore 8
    //   533: goto -145 -> 388
    //   536: astore 9
    //   538: goto -183 -> 355
    //   541: astore 10
    //   543: goto -221 -> 322
    //
    // Exception table:
    //   from	to	target	type
    //   30	36	317	org/apache/http/client/ClientProtocolException
    //   30	36	350	java/net/SocketException
    //   30	36	383	java/net/SocketTimeoutException
    //   30	36	421	java/io/IOException
    //   30	36	454	java/lang/Throwable
    //   30	36	487	finally
    //   40	60	516	finally
    //   67	73	516	finally
    //   80	99	516	finally
    //   99	105	516	finally
    //   112	117	516	finally
    //   140	164	516	finally
    //   187	192	516	finally
    //   215	236	516	finally
    //   252	259	516	finally
    //   282	294	516	finally
    //   322	327	516	finally
    //   355	360	516	finally
    //   388	398	516	finally
    //   426	431	516	finally
    //   459	464	516	finally
    //   40	60	521	java/lang/Throwable
    //   67	73	521	java/lang/Throwable
    //   80	99	521	java/lang/Throwable
    //   99	105	521	java/lang/Throwable
    //   112	117	521	java/lang/Throwable
    //   140	164	521	java/lang/Throwable
    //   187	192	521	java/lang/Throwable
    //   215	236	521	java/lang/Throwable
    //   252	259	521	java/lang/Throwable
    //   282	294	521	java/lang/Throwable
    //   40	60	526	java/io/IOException
    //   67	73	526	java/io/IOException
    //   80	99	526	java/io/IOException
    //   99	105	526	java/io/IOException
    //   112	117	526	java/io/IOException
    //   140	164	526	java/io/IOException
    //   187	192	526	java/io/IOException
    //   215	236	526	java/io/IOException
    //   252	259	526	java/io/IOException
    //   282	294	526	java/io/IOException
    //   40	60	531	java/net/SocketTimeoutException
    //   67	73	531	java/net/SocketTimeoutException
    //   80	99	531	java/net/SocketTimeoutException
    //   99	105	531	java/net/SocketTimeoutException
    //   112	117	531	java/net/SocketTimeoutException
    //   140	164	531	java/net/SocketTimeoutException
    //   187	192	531	java/net/SocketTimeoutException
    //   215	236	531	java/net/SocketTimeoutException
    //   252	259	531	java/net/SocketTimeoutException
    //   282	294	531	java/net/SocketTimeoutException
    //   40	60	536	java/net/SocketException
    //   67	73	536	java/net/SocketException
    //   80	99	536	java/net/SocketException
    //   99	105	536	java/net/SocketException
    //   112	117	536	java/net/SocketException
    //   140	164	536	java/net/SocketException
    //   187	192	536	java/net/SocketException
    //   215	236	536	java/net/SocketException
    //   252	259	536	java/net/SocketException
    //   282	294	536	java/net/SocketException
    //   40	60	541	org/apache/http/client/ClientProtocolException
    //   67	73	541	org/apache/http/client/ClientProtocolException
    //   80	99	541	org/apache/http/client/ClientProtocolException
    //   99	105	541	org/apache/http/client/ClientProtocolException
    //   112	117	541	org/apache/http/client/ClientProtocolException
    //   140	164	541	org/apache/http/client/ClientProtocolException
    //   187	192	541	org/apache/http/client/ClientProtocolException
    //   215	236	541	org/apache/http/client/ClientProtocolException
    //   252	259	541	org/apache/http/client/ClientProtocolException
    //   282	294	541	org/apache/http/client/ClientProtocolException
  }

  public boolean a(List paramList)
  {
    if ((paramList == null) || (paramList.isEmpty()));
    while (true)
    {
      return false;
      try
      {
        UrlEncodedFormEntity localUrlEncodedFormEntity = new UrlEncodedFormEntity(paramList);
        if (this.d == null)
          continue;
        this.d.setEntity(localUrlEncodedFormEntity);
        return true;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
      }
    }
    return false;
  }

  public boolean b(String paramString)
  {
    try
    {
      int i = paramString.indexOf('?');
      String str;
      if (i > 0)
      {
        str = paramString.substring(0, i);
        a(c(paramString.substring(i + 1)));
      }
      for (URI localURI = new URI(str); this.d != null; localURI = new URI(paramString))
      {
        this.d.setURI(localURI);
        return true;
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      localURISyntaxException.printStackTrace();
      return false;
    }
    return false;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.a.b.b
 * JD-Core Version:    0.6.0
 */