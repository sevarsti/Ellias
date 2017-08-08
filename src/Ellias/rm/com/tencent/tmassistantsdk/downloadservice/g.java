package com.tencent.tmassistantsdk.downloadservice;

import android.text.TextUtils;
import com.tencent.tmassistantsdk.f.b;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.DownloadChunkLogInfo;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class g
{
  protected boolean a = false;
  HttpClient b = null;
  HttpGet c = null;
  protected d d = null;
  protected final byte[] e = new byte[4096];
  b f;
  protected final int g = com.tencent.tmassistantsdk.g.f.k();

  public g(d paramd)
  {
    this.d = paramd;
  }

  private void a(Throwable paramThrowable)
  {
    if (paramThrowable != null)
      paramThrowable.printStackTrace();
  }

  private void a(HttpResponse paramHttpResponse)
  {
    if (paramHttpResponse == null);
    while (true)
    {
      return;
      if (!this.d.a.equals("application/vnd.android.package-archive"))
        continue;
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Disposition");
      String str1;
      if ((arrayOfHeader != null) && (arrayOfHeader.length > 0))
      {
        String str3 = arrayOfHeader[0].getValue();
        if ((!TextUtils.isEmpty(str3)) && (str3.contains("filename=\"")))
        {
          String str4 = str3.substring(str3.indexOf("filename=\"") + "filename=\"".length());
          boolean bool = TextUtils.isEmpty(str4);
          str1 = null;
          if (!bool)
          {
            str1 = str4.substring(0, str4.indexOf("\""));
            l.b("_DownloadTask", "header file Name =" + str1);
          }
        }
      }
      while (!TextUtils.isEmpty(str1))
      {
        String str2 = c.d(c.c(str1));
        this.d.l = str2;
        return;
        str1 = c.b(this.d.c);
        continue;
        str1 = c.b(this.d.c);
      }
    }
  }

  private void a(HttpResponse paramHttpResponse, DownloadChunkLogInfo paramDownloadChunkLogInfo)
  {
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    l.b("_DownloadTask", "httpResponseCode = " + i + " " + Thread.currentThread().getName());
    paramDownloadChunkLogInfo.readHeaderTime = System.currentTimeMillis();
    paramDownloadChunkLogInfo.responseHttpCode = i;
    switch (i)
    {
    default:
      throw new o(i, "HTTP response code error, code = " + i);
    case 200:
      Header[] arrayOfHeader = paramHttpResponse.getHeaders("Content-Type");
      if ((arrayOfHeader != null) && (arrayOfHeader.length > 0))
      {
        if (!this.d.a.equals("resource/tm.android.unknown"))
          break label236;
        String str4 = c.e(this.d.l);
        this.d.l = str4;
      }
      while (true)
      {
        b(paramHttpResponse, paramDownloadChunkLogInfo);
        return;
        String str3 = arrayOfHeader[0].getValue();
        if ((!TextUtils.isEmpty(str3)) && (str3.startsWith("text")))
          throw new o(708, "Return contenttype = text " + Thread.currentThread().getName());
        a(paramHttpResponse);
      }
    case 206:
      if (this.d.a.equals("resource/tm.android.unknown"))
      {
        String str2 = c.e(this.d.l);
        this.d.l = str2;
      }
      while (true)
      {
        b(paramHttpResponse, paramDownloadChunkLogInfo);
        return;
        a(paramHttpResponse);
      }
    case 301:
    case 302:
    case 303:
    case 307:
      if (this.d.e > 5)
        throw new o(709, "Redirect cnt many times.");
      Header localHeader = paramHttpResponse.getFirstHeader("location");
      if (localHeader != null)
      {
        String str1 = localHeader.getValue();
        if (c.f(str1))
        {
          this.d.c = c.a(str1);
          d locald = this.d;
          locald.e = (1 + locald.e);
          return;
        }
        throw new o(700, "Jump url is not valid. httpResponseCode = " + i + " url: " + str1);
      }
      throw new o(702, "location header is null. httpResponseCode = " + i);
    case 416:
      throw new o(i, "HTTP response code error, code = " + i);
    case 503:
      label236: throw new o(i, "HTTP response code error, code = " + i);
    case 500:
    }
    throw new o(i, "HTTP response code error, code = " + i);
  }

  private void a(HttpGet paramHttpGet, DownloadChunkLogInfo paramDownloadChunkLogInfo)
  {
    String str1 = c.b();
    this.d.p = str1;
    if ((!TextUtils.isEmpty(str1)) && ((str1.contains("wap")) || ((str1.contains("net")) && (this.d.d > 0))))
      try
      {
        int i = f.a(str1);
        long l1 = this.d.j;
        long l2;
        if (this.d.b() > 0L)
        {
          l2 = this.d.j + i - 1L;
          if (l2 >= this.d.b())
            l2 = this.d.b() - 1L;
        }
        while (true)
        {
          String str3 = "bytes=" + l1 + "-" + l2;
          paramHttpGet.addHeader("range", str3);
          l.c("_DownloadTask", "set range header: " + str3);
          paramDownloadChunkLogInfo.responseContentLength = this.d.b();
          paramDownloadChunkLogInfo.requestRanagePosition = l1;
          paramDownloadChunkLogInfo.requestRanageSize = i;
          return;
          l2 = i - 1;
        }
      }
      catch (UnsupportedOperationException localUnsupportedOperationException)
      {
        localUnsupportedOperationException.printStackTrace();
        return;
      }
    String str2 = "bytes=" + this.d.j + "-";
    paramHttpGet.addHeader("range", str2);
    l.c("_DownloadTask", "set range header: " + str2);
    paramDownloadChunkLogInfo.responseContentLength = this.d.b();
    paramDownloadChunkLogInfo.requestRanagePosition = this.d.j;
    paramDownloadChunkLogInfo.requestRanageSize = 0L;
  }

  private void a(HttpGet paramHttpGet, Map paramMap)
  {
    if ((paramHttpGet != null) && (paramMap != null) && (paramMap.size() > 0))
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        paramHttpGet.addHeader((String)localEntry.getKey(), (String)localEntry.getValue());
      }
    }
  }

  // ERROR //
  private void b(HttpResponse paramHttpResponse, DownloadChunkLogInfo paramDownloadChunkLogInfo)
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: aload_1
    //   3: invokeinterface 311 1 0
    //   8: astore 5
    //   10: aload_0
    //   11: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   14: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   17: lload_3
    //   18: lcmp
    //   19: ifne +413 -> 432
    //   22: aload_1
    //   23: invokeinterface 140 1 0
    //   28: invokeinterface 145 1 0
    //   33: sipush 200
    //   36: if_icmpne +273 -> 309
    //   39: aload_0
    //   40: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   43: aload 5
    //   45: invokeinterface 316 1 0
    //   50: invokevirtual 319	com/tencent/tmassistantsdk/downloadservice/d:a	(J)V
    //   53: ldc 104
    //   55: new 106	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   62: ldc_w 321
    //   65: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload_0
    //   69: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   72: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   75: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   78: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   81: invokestatic 121	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   84: ldc 104
    //   86: new 106	java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   93: ldc_w 323
    //   96: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: aload_0
    //   100: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   103: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   106: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   109: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   112: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   115: aload_1
    //   116: ldc_w 327
    //   119: invokeinterface 211 2 0
    //   124: astore 24
    //   126: aload 24
    //   128: ifnull +41 -> 169
    //   131: aload 24
    //   133: invokeinterface 75 1 0
    //   138: invokestatic 332	com/tencent/tmassistantsdk/downloadservice/b:a	(Ljava/lang/String;)Lcom/tencent/tmassistantsdk/downloadservice/b;
    //   141: astore 25
    //   143: aload_2
    //   144: aload 25
    //   146: invokevirtual 333	com/tencent/tmassistantsdk/downloadservice/b:b	()J
    //   149: putfield 336	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseRangePosition	J
    //   152: aload_2
    //   153: lconst_1
    //   154: aload 25
    //   156: invokevirtual 338	com/tencent/tmassistantsdk/downloadservice/b:c	()J
    //   159: aload 25
    //   161: invokevirtual 333	com/tencent/tmassistantsdk/downloadservice/b:b	()J
    //   164: lsub
    //   165: ladd
    //   166: putfield 341	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseRangeLength	J
    //   169: aload_2
    //   170: aload_0
    //   171: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   174: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   177: putfield 265	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseContentLength	J
    //   180: aload_0
    //   181: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   184: ifnonnull +28 -> 212
    //   187: aload_0
    //   188: new 345	com/tencent/tmassistantsdk/f/b
    //   191: dup
    //   192: aload_0
    //   193: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   196: getfield 348	com/tencent/tmassistantsdk/downloadservice/d:m	Ljava/lang/String;
    //   199: aload_0
    //   200: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   203: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   206: invokespecial 350	com/tencent/tmassistantsdk/f/b:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   209: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   212: aload 5
    //   214: invokeinterface 354 1 0
    //   219: astore 16
    //   221: ldc 104
    //   223: new 106	java/lang/StringBuilder
    //   226: dup
    //   227: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   230: ldc_w 356
    //   233: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   236: aload_0
    //   237: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   240: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   243: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   249: invokestatic 121	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   252: lload_3
    //   253: lstore 14
    //   255: aload 16
    //   257: aload_0
    //   258: getfield 33	com/tencent/tmassistantsdk/downloadservice/g:e	[B
    //   261: invokevirtual 362	java/io/InputStream:read	([B)I
    //   264: istore 17
    //   266: iload 17
    //   268: ifle +15 -> 283
    //   271: aload_0
    //   272: getfield 25	com/tencent/tmassistantsdk/downloadservice/g:a	Z
    //   275: ifeq +427 -> 702
    //   278: aload 16
    //   280: invokevirtual 365	java/io/InputStream:close	()V
    //   283: aload_0
    //   284: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   287: ifnull +15 -> 302
    //   290: aload_0
    //   291: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   294: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   297: aload_0
    //   298: aconst_null
    //   299: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   302: aload_2
    //   303: lload 14
    //   305: putfield 370	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:receiveDataSize	J
    //   308: return
    //   309: aload_1
    //   310: invokeinterface 140 1 0
    //   315: invokeinterface 145 1 0
    //   320: sipush 206
    //   323: if_icmpne +65 -> 388
    //   326: aload_1
    //   327: ldc_w 327
    //   330: invokeinterface 211 2 0
    //   335: astore 26
    //   337: aload_0
    //   338: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   341: aload 26
    //   343: invokeinterface 75 1 0
    //   348: invokestatic 373	com/tencent/tmassistantsdk/downloadservice/b:b	(Ljava/lang/String;)J
    //   351: invokevirtual 319	com/tencent/tmassistantsdk/downloadservice/d:a	(J)V
    //   354: ldc 104
    //   356: new 106	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   363: ldc_w 375
    //   366: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   369: aload_0
    //   370: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   373: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   376: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   379: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   382: invokestatic 121	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   385: goto -301 -> 84
    //   388: ldc 104
    //   390: new 106	java/lang/StringBuilder
    //   393: dup
    //   394: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   397: ldc_w 377
    //   400: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   403: aload_1
    //   404: invokeinterface 140 1 0
    //   409: invokeinterface 145 1 0
    //   414: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   417: ldc_w 379
    //   420: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   423: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   426: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   429: goto -345 -> 84
    //   432: aload_1
    //   433: invokeinterface 140 1 0
    //   438: invokeinterface 145 1 0
    //   443: sipush 206
    //   446: if_icmpne -266 -> 180
    //   449: aload_1
    //   450: ldc_w 327
    //   453: invokeinterface 211 2 0
    //   458: astore 8
    //   460: aload 8
    //   462: invokeinterface 75 1 0
    //   467: invokestatic 332	com/tencent/tmassistantsdk/downloadservice/b:a	(Ljava/lang/String;)Lcom/tencent/tmassistantsdk/downloadservice/b;
    //   470: astore 9
    //   472: aload 8
    //   474: invokeinterface 75 1 0
    //   479: invokestatic 373	com/tencent/tmassistantsdk/downloadservice/b:b	(Ljava/lang/String;)J
    //   482: lstore 10
    //   484: aload_2
    //   485: aload 9
    //   487: invokevirtual 333	com/tencent/tmassistantsdk/downloadservice/b:b	()J
    //   490: putfield 336	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseRangePosition	J
    //   493: aload_2
    //   494: lconst_1
    //   495: aload 9
    //   497: invokevirtual 338	com/tencent/tmassistantsdk/downloadservice/b:c	()J
    //   500: aload 9
    //   502: invokevirtual 333	com/tencent/tmassistantsdk/downloadservice/b:b	()J
    //   505: lsub
    //   506: ladd
    //   507: putfield 341	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseRangeLength	J
    //   510: aload_2
    //   511: lload 10
    //   513: putfield 265	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:responseContentLength	J
    //   516: ldc 104
    //   518: new 106	java/lang/StringBuilder
    //   521: dup
    //   522: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   525: ldc_w 381
    //   528: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: lload 10
    //   533: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   536: ldc_w 383
    //   539: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: aload_0
    //   543: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   546: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   549: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   552: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   555: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   558: aload 9
    //   560: invokevirtual 333	com/tencent/tmassistantsdk/downloadservice/b:b	()J
    //   563: aload_0
    //   564: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   567: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   570: lcmp
    //   571: ifeq +56 -> 627
    //   574: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   577: dup
    //   578: sipush 706
    //   581: ldc_w 385
    //   584: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   587: athrow
    //   588: astore 7
    //   590: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   593: dup
    //   594: sipush 704
    //   597: aload 7
    //   599: invokespecial 388	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/Throwable;)V
    //   602: athrow
    //   603: astore 6
    //   605: aload_0
    //   606: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   609: ifnull +15 -> 624
    //   612: aload_0
    //   613: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   616: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   619: aload_0
    //   620: aconst_null
    //   621: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   624: aload 6
    //   626: athrow
    //   627: lload 10
    //   629: aload_0
    //   630: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   633: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   636: lcmp
    //   637: ifeq +17 -> 654
    //   640: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   643: dup
    //   644: sipush 705
    //   647: ldc_w 390
    //   650: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   653: athrow
    //   654: ldc 104
    //   656: new 106	java/lang/StringBuilder
    //   659: dup
    //   660: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   663: ldc_w 392
    //   666: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   669: aload 8
    //   671: invokevirtual 395	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   674: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   677: invokestatic 262	com/tencent/tmassistantsdk/g/l:c	(Ljava/lang/String;Ljava/lang/String;)V
    //   680: aload_0
    //   681: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   684: ifnull -504 -> 180
    //   687: aload_0
    //   688: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   691: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   694: aload_0
    //   695: aconst_null
    //   696: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   699: goto -519 -> 180
    //   702: aload_0
    //   703: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   706: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   709: iload 17
    //   711: i2l
    //   712: ladd
    //   713: lstore 18
    //   715: lload 18
    //   717: aload_0
    //   718: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   721: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   724: lcmp
    //   725: ifgt +423 -> 1148
    //   728: lload 18
    //   730: aload_0
    //   731: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   734: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   737: lcmp
    //   738: ifne +201 -> 939
    //   741: iconst_1
    //   742: istore 20
    //   744: aload_0
    //   745: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   748: aload_0
    //   749: getfield 33	com/tencent/tmassistantsdk/downloadservice/g:e	[B
    //   752: iconst_0
    //   753: iload 17
    //   755: aload_0
    //   756: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   759: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   762: iload 20
    //   764: invokevirtual 398	com/tencent/tmassistantsdk/f/b:a	([BIIJZ)Z
    //   767: ifne +360 -> 1127
    //   770: invokestatic 400	com/tencent/tmassistantsdk/f/b:e	()Ljava/lang/String;
    //   773: aload_0
    //   774: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   777: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   780: invokestatic 403	com/tencent/tmassistantsdk/downloadservice/c:a	(Ljava/lang/String;J)Z
    //   783: ifeq +253 -> 1036
    //   786: invokestatic 405	com/tencent/tmassistantsdk/f/b:g	()Z
    //   789: ifeq +156 -> 945
    //   792: new 106	java/lang/StringBuilder
    //   795: dup
    //   796: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   799: ldc_w 407
    //   802: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: aload_0
    //   806: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   809: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   812: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   815: ldc_w 409
    //   818: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   821: aload_0
    //   822: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   825: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   828: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   831: ldc_w 411
    //   834: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   837: iload 17
    //   839: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   842: ldc_w 413
    //   845: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   848: aload_0
    //   849: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   852: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   855: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   858: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   861: astore 23
    //   863: ldc 104
    //   865: aload 23
    //   867: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   870: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   873: dup
    //   874: sipush 703
    //   877: aload 23
    //   879: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   882: athrow
    //   883: astore 12
    //   885: lload 14
    //   887: lstore_3
    //   888: aload 12
    //   890: invokevirtual 414	java/net/SocketException:printStackTrace	()V
    //   893: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   896: dup
    //   897: sipush 605
    //   900: aload 12
    //   902: invokespecial 388	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/Throwable;)V
    //   905: athrow
    //   906: astore 13
    //   908: lload_3
    //   909: lstore 14
    //   911: aload_0
    //   912: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   915: ifnull +15 -> 930
    //   918: aload_0
    //   919: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   922: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   925: aload_0
    //   926: aconst_null
    //   927: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   930: aload_2
    //   931: lload 14
    //   933: putfield 370	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:receiveDataSize	J
    //   936: aload 13
    //   938: athrow
    //   939: iconst_0
    //   940: istore 20
    //   942: goto -198 -> 744
    //   945: new 106	java/lang/StringBuilder
    //   948: dup
    //   949: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   952: ldc_w 416
    //   955: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   958: aload_0
    //   959: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   962: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   965: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   968: ldc_w 409
    //   971: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   974: aload_0
    //   975: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   978: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   981: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   984: ldc_w 411
    //   987: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   990: iload 17
    //   992: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   995: ldc_w 413
    //   998: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1001: aload_0
    //   1002: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1005: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   1008: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1011: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1014: astore 22
    //   1016: ldc 104
    //   1018: aload 22
    //   1020: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1023: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   1026: dup
    //   1027: sipush 711
    //   1030: aload 22
    //   1032: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   1035: athrow
    //   1036: new 106	java/lang/StringBuilder
    //   1039: dup
    //   1040: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   1043: ldc_w 418
    //   1046: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1049: aload_0
    //   1050: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1053: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   1056: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1059: ldc_w 409
    //   1062: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1065: aload_0
    //   1066: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1069: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   1072: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1075: ldc_w 411
    //   1078: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1081: iload 17
    //   1083: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1086: ldc_w 413
    //   1089: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1092: aload_0
    //   1093: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1096: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   1099: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1102: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1105: astore 21
    //   1107: ldc 104
    //   1109: aload 21
    //   1111: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1114: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   1117: dup
    //   1118: sipush 710
    //   1121: aload 21
    //   1123: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   1126: athrow
    //   1127: aload_0
    //   1128: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1131: iload 17
    //   1133: i2l
    //   1134: invokevirtual 420	com/tencent/tmassistantsdk/downloadservice/d:b	(J)V
    //   1137: lload 14
    //   1139: iload 17
    //   1141: i2l
    //   1142: ladd
    //   1143: lstore 14
    //   1145: goto -890 -> 255
    //   1148: ldc 104
    //   1150: new 106	java/lang/StringBuilder
    //   1153: dup
    //   1154: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   1157: ldc_w 422
    //   1160: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1163: iload 17
    //   1165: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   1168: ldc_w 424
    //   1171: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1174: ldc_w 426
    //   1177: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1180: aload_0
    //   1181: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1184: getfield 242	com/tencent/tmassistantsdk/downloadservice/d:j	J
    //   1187: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1190: ldc_w 424
    //   1193: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1196: ldc_w 428
    //   1199: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1202: aload_0
    //   1203: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1206: invokevirtual 244	com/tencent/tmassistantsdk/downloadservice/d:b	()J
    //   1209: invokevirtual 249	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   1212: ldc_w 424
    //   1215: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1218: ldc_w 430
    //   1221: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1224: iconst_0
    //   1225: invokevirtual 433	java/lang/StringBuilder:append	(Z)Ljava/lang/StringBuilder;
    //   1228: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   1231: invokestatic 325	com/tencent/tmassistantsdk/g/l:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   1234: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   1237: dup
    //   1238: sipush 703
    //   1241: ldc_w 435
    //   1244: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   1247: athrow
    //   1248: astore 12
    //   1250: goto -362 -> 888
    //   1253: astore 13
    //   1255: goto -344 -> 911
    //
    // Exception table:
    //   from	to	target	type
    //   449	588	588	java/lang/Throwable
    //   627	654	588	java/lang/Throwable
    //   654	680	588	java/lang/Throwable
    //   449	588	603	finally
    //   590	603	603	finally
    //   627	654	603	finally
    //   654	680	603	finally
    //   255	266	883	java/net/SocketException
    //   271	283	883	java/net/SocketException
    //   702	741	883	java/net/SocketException
    //   744	883	883	java/net/SocketException
    //   945	1036	883	java/net/SocketException
    //   1036	1127	883	java/net/SocketException
    //   1127	1137	883	java/net/SocketException
    //   1148	1248	883	java/net/SocketException
    //   212	252	906	finally
    //   888	906	906	finally
    //   212	252	1248	java/net/SocketException
    //   255	266	1253	finally
    //   271	283	1253	finally
    //   702	741	1253	finally
    //   744	883	1253	finally
    //   945	1036	1253	finally
    //   1036	1127	1253	finally
    //   1127	1137	1253	finally
    //   1148	1248	1253	finally
  }

  public int a()
  {
    return this.g;
  }

  // ERROR //
  public void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   4: iconst_1
    //   5: putfield 448	com/tencent/tmassistantsdk/downloadservice/d:g	Z
    //   8: aload_0
    //   9: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   12: iconst_2
    //   13: invokevirtual 451	com/tencent/tmassistantsdk/downloadservice/d:a	(I)V
    //   16: aconst_null
    //   17: astore_2
    //   18: aconst_null
    //   19: astore_3
    //   20: iconst_1
    //   21: istore 4
    //   23: iload 4
    //   25: ifeq +56 -> 81
    //   28: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   31: iconst_0
    //   32: invokevirtual 459	com/tencent/tmassistantsdk/d/b:a	(B)Lcom/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo;
    //   35: astore 5
    //   37: aload 5
    //   39: aload_0
    //   40: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   43: getfield 461	com/tencent/tmassistantsdk/downloadservice/d:f	Ljava/lang/String;
    //   46: putfield 464	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:UUID	Ljava/lang/String;
    //   49: aload 5
    //   51: aload_0
    //   52: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   55: getfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   58: i2b
    //   59: putfield 468	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:currentRetryCnt	B
    //   62: aload 5
    //   64: aload_0
    //   65: invokevirtual 470	com/tencent/tmassistantsdk/downloadservice/g:c	()Ljava/lang/String;
    //   68: putfield 473	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:requestUrl	Ljava/lang/String;
    //   71: aload_0
    //   72: getfield 25	com/tencent/tmassistantsdk/downloadservice/g:a	Z
    //   75: ifeq +127 -> 202
    //   78: aload 5
    //   80: astore_2
    //   81: aload_0
    //   82: getfield 25	com/tencent/tmassistantsdk/downloadservice/g:a	Z
    //   85: ifne +21 -> 106
    //   88: aload_0
    //   89: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   92: invokevirtual 475	com/tencent/tmassistantsdk/downloadservice/d:c	()Z
    //   95: ifeq +1616 -> 1711
    //   98: aload_0
    //   99: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   102: iconst_4
    //   103: invokevirtual 451	com/tencent/tmassistantsdk/downloadservice/d:a	(I)V
    //   106: aload_2
    //   107: ifnull +39 -> 146
    //   110: aload_2
    //   111: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   114: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   117: aload_2
    //   118: aload_0
    //   119: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   122: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   125: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   128: aload_2
    //   129: aload_0
    //   130: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   133: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   136: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   139: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   142: aload_2
    //   143: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   146: ldc 104
    //   148: new 106	java/lang/StringBuilder
    //   151: dup
    //   152: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   155: ldc_w 495
    //   158: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   161: aload_0
    //   162: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   165: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   168: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   171: ldc_w 497
    //   174: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   177: aload_0
    //   178: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   181: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   184: invokevirtual 150	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   187: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   190: invokestatic 121	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   193: aload_0
    //   194: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   197: iconst_0
    //   198: putfield 448	com/tencent/tmassistantsdk/downloadservice/d:g	Z
    //   201: return
    //   202: aload_0
    //   203: invokestatic 502	com/tencent/tmassistantsdk/downloadservice/k:a	()Lorg/apache/http/client/HttpClient;
    //   206: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   209: aload_0
    //   210: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   213: invokestatic 505	com/tencent/tmassistantsdk/downloadservice/k:a	(Lorg/apache/http/client/HttpClient;)V
    //   216: aload_0
    //   217: new 255	org/apache/http/client/methods/HttpGet
    //   220: dup
    //   221: invokespecial 506	org/apache/http/client/methods/HttpGet:<init>	()V
    //   224: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   227: aload_0
    //   228: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   231: new 508	java/net/URI
    //   234: dup
    //   235: aload_0
    //   236: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   239: getfield 133	com/tencent/tmassistantsdk/downloadservice/d:c	Ljava/lang/String;
    //   242: invokespecial 510	java/net/URI:<init>	(Ljava/lang/String;)V
    //   245: invokevirtual 514	org/apache/http/client/methods/HttpGet:setURI	(Ljava/net/URI;)V
    //   248: aload_0
    //   249: aload_0
    //   250: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   253: aload_0
    //   254: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   257: invokevirtual 517	com/tencent/tmassistantsdk/downloadservice/d:a	()Ljava/util/HashMap;
    //   260: invokespecial 519	com/tencent/tmassistantsdk/downloadservice/g:a	(Lorg/apache/http/client/methods/HttpGet;Ljava/util/Map;)V
    //   263: aload_0
    //   264: aload_0
    //   265: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   268: aload 5
    //   270: invokespecial 521	com/tencent/tmassistantsdk/downloadservice/g:a	(Lorg/apache/http/client/methods/HttpGet;Lcom/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo;)V
    //   273: ldc 104
    //   275: new 106	java/lang/StringBuilder
    //   278: dup
    //   279: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   282: ldc_w 523
    //   285: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   288: invokestatic 158	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   291: invokevirtual 161	java/lang/Thread:getName	()Ljava/lang/String;
    //   294: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   300: invokestatic 121	com/tencent/tmassistantsdk/g/l:b	(Ljava/lang/String;Ljava/lang/String;)V
    //   303: aload_0
    //   304: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   307: aload_0
    //   308: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   311: invokeinterface 529 2 0
    //   316: astore 18
    //   318: aload 18
    //   320: ifnonnull +217 -> 537
    //   323: new 178	com/tencent/tmassistantsdk/downloadservice/o
    //   326: dup
    //   327: sipush 701
    //   330: new 106	java/lang/StringBuilder
    //   333: dup
    //   334: invokespecial 107	java/lang/StringBuilder:<init>	()V
    //   337: ldc_w 531
    //   340: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   343: invokestatic 158	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   346: invokevirtual 161	java/lang/Thread:getName	()Ljava/lang/String;
    //   349: invokevirtual 113	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   352: invokevirtual 116	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   355: invokespecial 183	com/tencent/tmassistantsdk/downloadservice/o:<init>	(ILjava/lang/String;)V
    //   358: athrow
    //   359: astore 15
    //   361: aload 15
    //   363: invokevirtual 532	org/apache/http/conn/ConnectTimeoutException:printStackTrace	()V
    //   366: aload_0
    //   367: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   370: invokevirtual 534	com/tencent/tmassistantsdk/downloadservice/d:d	()Z
    //   373: istore 16
    //   375: iload 16
    //   377: ifeq +336 -> 713
    //   380: aload_0
    //   381: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   384: astore 17
    //   386: aload 17
    //   388: iconst_1
    //   389: aload 17
    //   391: getfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   394: iadd
    //   395: putfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   398: ldc2_w 535
    //   401: invokestatic 539	java/lang/Thread:sleep	(J)V
    //   404: iconst_1
    //   405: istore 4
    //   407: aload_0
    //   408: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   411: ifnull +25 -> 436
    //   414: aload_0
    //   415: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   418: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   421: ifne +10 -> 431
    //   424: aload_0
    //   425: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   428: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   431: aload_0
    //   432: aconst_null
    //   433: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   436: aload_0
    //   437: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   440: ifnull +22 -> 462
    //   443: aload_0
    //   444: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   447: invokeinterface 549 1 0
    //   452: invokeinterface 554 1 0
    //   457: aload_0
    //   458: aconst_null
    //   459: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   462: aload_0
    //   463: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   466: ifnull +15 -> 481
    //   469: aload_0
    //   470: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   473: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   476: aload_0
    //   477: aconst_null
    //   478: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   481: iload 4
    //   483: iconst_1
    //   484: if_icmpne +1251 -> 1735
    //   487: aload 5
    //   489: ifnull +1246 -> 1735
    //   492: aload 5
    //   494: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   497: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   500: aload 5
    //   502: aload_0
    //   503: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   506: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   509: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   512: aload 5
    //   514: aload_0
    //   515: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   518: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   521: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   524: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   527: aload 5
    //   529: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   532: aconst_null
    //   533: astore_2
    //   534: goto -511 -> 23
    //   537: aload_0
    //   538: aload 18
    //   540: aload 5
    //   542: invokespecial 556	com/tencent/tmassistantsdk/downloadservice/g:a	(Lorg/apache/http/HttpResponse;Lcom/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo;)V
    //   545: aload_0
    //   546: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   549: invokevirtual 475	com/tencent/tmassistantsdk/downloadservice/d:c	()Z
    //   552: istore 19
    //   554: iload 19
    //   556: ifne +134 -> 690
    //   559: iconst_1
    //   560: istore 4
    //   562: aload_0
    //   563: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   566: ifnull +25 -> 591
    //   569: aload_0
    //   570: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   573: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   576: ifne +10 -> 586
    //   579: aload_0
    //   580: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   583: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   586: aload_0
    //   587: aconst_null
    //   588: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   591: aload_0
    //   592: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   595: ifnull +22 -> 617
    //   598: aload_0
    //   599: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   602: invokeinterface 549 1 0
    //   607: invokeinterface 554 1 0
    //   612: aload_0
    //   613: aconst_null
    //   614: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   617: aload_0
    //   618: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   621: ifnull +15 -> 636
    //   624: aload_0
    //   625: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   628: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   631: aload_0
    //   632: aconst_null
    //   633: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   636: iload 4
    //   638: iconst_1
    //   639: if_icmpne +1096 -> 1735
    //   642: aload 5
    //   644: ifnull +1091 -> 1735
    //   647: aload 5
    //   649: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   652: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   655: aload 5
    //   657: aload_0
    //   658: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   661: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   664: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   667: aload 5
    //   669: aload_0
    //   670: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   673: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   676: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   679: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   682: aload 5
    //   684: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   687: goto -155 -> 532
    //   690: iconst_0
    //   691: istore 4
    //   693: goto -131 -> 562
    //   696: astore_3
    //   697: aload_0
    //   698: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   701: sipush 600
    //   704: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   707: iconst_0
    //   708: istore 4
    //   710: goto -303 -> 407
    //   713: aload_0
    //   714: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   717: sipush 601
    //   720: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   723: aload 15
    //   725: astore_3
    //   726: iconst_0
    //   727: istore 4
    //   729: goto -322 -> 407
    //   732: astore 11
    //   734: aload 11
    //   736: invokevirtual 557	java/net/SocketTimeoutException:printStackTrace	()V
    //   739: aload_0
    //   740: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   743: invokevirtual 534	com/tencent/tmassistantsdk/downloadservice/d:d	()Z
    //   746: istore 12
    //   748: iload 12
    //   750: ifeq +179 -> 929
    //   753: aload_0
    //   754: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   757: astore 13
    //   759: aload 13
    //   761: iconst_1
    //   762: aload 13
    //   764: getfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   767: iadd
    //   768: putfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   771: ldc2_w 535
    //   774: invokestatic 539	java/lang/Thread:sleep	(J)V
    //   777: iconst_1
    //   778: istore 4
    //   780: aload_0
    //   781: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   784: ifnull +25 -> 809
    //   787: aload_0
    //   788: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   791: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   794: ifne +10 -> 804
    //   797: aload_0
    //   798: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   801: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   804: aload_0
    //   805: aconst_null
    //   806: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   809: aload_0
    //   810: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   813: ifnull +22 -> 835
    //   816: aload_0
    //   817: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   820: invokeinterface 549 1 0
    //   825: invokeinterface 554 1 0
    //   830: aload_0
    //   831: aconst_null
    //   832: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   835: aload_0
    //   836: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   839: ifnull +15 -> 854
    //   842: aload_0
    //   843: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   846: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   849: aload_0
    //   850: aconst_null
    //   851: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   854: iload 4
    //   856: iconst_1
    //   857: if_icmpne +878 -> 1735
    //   860: aload 5
    //   862: ifnull +873 -> 1735
    //   865: aload 5
    //   867: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   870: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   873: aload 5
    //   875: aload_0
    //   876: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   879: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   882: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   885: aload 5
    //   887: aload_0
    //   888: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   891: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   894: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   897: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   900: aload 5
    //   902: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   905: goto -373 -> 532
    //   908: astore 14
    //   910: aload_0
    //   911: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   914: sipush 600
    //   917: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   920: aload 11
    //   922: astore_3
    //   923: iconst_0
    //   924: istore 4
    //   926: goto -146 -> 780
    //   929: aload_0
    //   930: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   933: sipush 602
    //   936: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   939: aload 11
    //   941: astore_3
    //   942: iconst_0
    //   943: istore 4
    //   945: goto -165 -> 780
    //   948: astore_3
    //   949: aload_3
    //   950: invokevirtual 558	java/net/UnknownHostException:printStackTrace	()V
    //   953: aload_0
    //   954: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   957: sipush 603
    //   960: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   963: aload_0
    //   964: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   967: ifnull +25 -> 992
    //   970: aload_0
    //   971: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   974: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   977: ifne +10 -> 987
    //   980: aload_0
    //   981: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   984: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   987: aload_0
    //   988: aconst_null
    //   989: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   992: aload_0
    //   993: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   996: ifnull +22 -> 1018
    //   999: aload_0
    //   1000: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1003: invokeinterface 549 1 0
    //   1008: invokeinterface 554 1 0
    //   1013: aload_0
    //   1014: aconst_null
    //   1015: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1018: aload_0
    //   1019: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1022: ifnull +15 -> 1037
    //   1025: aload_0
    //   1026: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1029: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1032: aload_0
    //   1033: aconst_null
    //   1034: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1037: aload 5
    //   1039: astore_2
    //   1040: iconst_0
    //   1041: istore 4
    //   1043: goto -1020 -> 23
    //   1046: astore_3
    //   1047: aload_3
    //   1048: invokevirtual 559	java/lang/InterruptedException:printStackTrace	()V
    //   1051: aload_0
    //   1052: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1055: sipush 600
    //   1058: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1061: aload_0
    //   1062: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1065: ifnull +25 -> 1090
    //   1068: aload_0
    //   1069: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1072: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   1075: ifne +10 -> 1085
    //   1078: aload_0
    //   1079: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1082: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   1085: aload_0
    //   1086: aconst_null
    //   1087: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1090: aload_0
    //   1091: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1094: ifnull +22 -> 1116
    //   1097: aload_0
    //   1098: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1101: invokeinterface 549 1 0
    //   1106: invokeinterface 554 1 0
    //   1111: aload_0
    //   1112: aconst_null
    //   1113: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1116: aload_0
    //   1117: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1120: ifnull +15 -> 1135
    //   1123: aload_0
    //   1124: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1127: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1130: aload_0
    //   1131: aconst_null
    //   1132: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1135: aload 5
    //   1137: astore_2
    //   1138: iconst_0
    //   1139: istore 4
    //   1141: goto -1118 -> 23
    //   1144: astore_3
    //   1145: aload_3
    //   1146: invokevirtual 560	java/io/IOException:printStackTrace	()V
    //   1149: aload_0
    //   1150: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1153: sipush 606
    //   1156: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1159: aload_0
    //   1160: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1163: ifnull +25 -> 1188
    //   1166: aload_0
    //   1167: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1170: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   1173: ifne +10 -> 1183
    //   1176: aload_0
    //   1177: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1180: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   1183: aload_0
    //   1184: aconst_null
    //   1185: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1188: aload_0
    //   1189: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1192: ifnull +22 -> 1214
    //   1195: aload_0
    //   1196: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1199: invokeinterface 549 1 0
    //   1204: invokeinterface 554 1 0
    //   1209: aload_0
    //   1210: aconst_null
    //   1211: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1214: aload_0
    //   1215: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1218: ifnull +15 -> 1233
    //   1221: aload_0
    //   1222: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1225: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1228: aload_0
    //   1229: aconst_null
    //   1230: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1233: aload 5
    //   1235: astore_2
    //   1236: iconst_0
    //   1237: istore 4
    //   1239: goto -1216 -> 23
    //   1242: astore_3
    //   1243: aload_3
    //   1244: invokevirtual 561	com/tencent/tmassistantsdk/downloadservice/o:printStackTrace	()V
    //   1247: aload_0
    //   1248: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1251: aload_3
    //   1252: getfield 563	com/tencent/tmassistantsdk/downloadservice/o:a	I
    //   1255: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1258: sipush 704
    //   1261: aload_3
    //   1262: getfield 563	com/tencent/tmassistantsdk/downloadservice/o:a	I
    //   1265: if_icmpne +208 -> 1473
    //   1268: aload_0
    //   1269: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1272: lconst_0
    //   1273: invokevirtual 319	com/tencent/tmassistantsdk/downloadservice/d:a	(J)V
    //   1276: new 345	com/tencent/tmassistantsdk/f/b
    //   1279: dup
    //   1280: aload_0
    //   1281: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1284: getfield 348	com/tencent/tmassistantsdk/downloadservice/d:m	Ljava/lang/String;
    //   1287: aload_0
    //   1288: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1291: getfield 131	com/tencent/tmassistantsdk/downloadservice/d:l	Ljava/lang/String;
    //   1294: invokespecial 350	com/tencent/tmassistantsdk/f/b:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   1297: invokevirtual 565	com/tencent/tmassistantsdk/f/b:a	()V
    //   1300: aload_0
    //   1301: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1304: invokevirtual 534	com/tencent/tmassistantsdk/downloadservice/d:d	()Z
    //   1307: istore 8
    //   1309: iload 8
    //   1311: ifeq +156 -> 1467
    //   1314: aload_0
    //   1315: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1318: astore 10
    //   1320: aload 10
    //   1322: iconst_1
    //   1323: aload 10
    //   1325: getfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   1328: iadd
    //   1329: putfield 235	com/tencent/tmassistantsdk/downloadservice/d:d	I
    //   1332: iconst_1
    //   1333: istore 9
    //   1335: iload 9
    //   1337: istore 4
    //   1339: aload_0
    //   1340: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1343: ifnull +25 -> 1368
    //   1346: aload_0
    //   1347: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1350: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   1353: ifne +10 -> 1363
    //   1356: aload_0
    //   1357: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1360: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   1363: aload_0
    //   1364: aconst_null
    //   1365: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1368: aload_0
    //   1369: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1372: ifnull +22 -> 1394
    //   1375: aload_0
    //   1376: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1379: invokeinterface 549 1 0
    //   1384: invokeinterface 554 1 0
    //   1389: aload_0
    //   1390: aconst_null
    //   1391: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1394: aload_0
    //   1395: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1398: ifnull +15 -> 1413
    //   1401: aload_0
    //   1402: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1405: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1408: aload_0
    //   1409: aconst_null
    //   1410: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1413: iload 4
    //   1415: iconst_1
    //   1416: if_icmpne +319 -> 1735
    //   1419: aload 5
    //   1421: ifnull +314 -> 1735
    //   1424: aload 5
    //   1426: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   1429: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   1432: aload 5
    //   1434: aload_0
    //   1435: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1438: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1441: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   1444: aload 5
    //   1446: aload_0
    //   1447: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1450: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   1453: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   1456: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   1459: aload 5
    //   1461: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   1464: goto -932 -> 532
    //   1467: iconst_0
    //   1468: istore 9
    //   1470: goto -135 -> 1335
    //   1473: iconst_0
    //   1474: istore 4
    //   1476: goto -137 -> 1339
    //   1479: astore_3
    //   1480: aload_3
    //   1481: invokevirtual 47	java/lang/Throwable:printStackTrace	()V
    //   1484: aload_0
    //   1485: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1488: sipush 604
    //   1491: putfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1494: aload_0
    //   1495: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1498: ifnull +25 -> 1523
    //   1501: aload_0
    //   1502: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1505: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   1508: ifne +10 -> 1518
    //   1511: aload_0
    //   1512: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1515: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   1518: aload_0
    //   1519: aconst_null
    //   1520: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1523: aload_0
    //   1524: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1527: ifnull +22 -> 1549
    //   1530: aload_0
    //   1531: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1534: invokeinterface 549 1 0
    //   1539: invokeinterface 554 1 0
    //   1544: aload_0
    //   1545: aconst_null
    //   1546: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1549: aload_0
    //   1550: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1553: ifnull +15 -> 1568
    //   1556: aload_0
    //   1557: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1560: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1563: aload_0
    //   1564: aconst_null
    //   1565: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1568: aload 5
    //   1570: astore_2
    //   1571: iconst_0
    //   1572: istore 4
    //   1574: goto -1551 -> 23
    //   1577: astore 6
    //   1579: iload 4
    //   1581: istore 7
    //   1583: aload_0
    //   1584: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1587: ifnull +25 -> 1612
    //   1590: aload_0
    //   1591: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1594: invokevirtual 542	org/apache/http/client/methods/HttpGet:isAborted	()Z
    //   1597: ifne +10 -> 1607
    //   1600: aload_0
    //   1601: getfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1604: invokevirtual 545	org/apache/http/client/methods/HttpGet:abort	()V
    //   1607: aload_0
    //   1608: aconst_null
    //   1609: putfield 29	com/tencent/tmassistantsdk/downloadservice/g:c	Lorg/apache/http/client/methods/HttpGet;
    //   1612: aload_0
    //   1613: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1616: ifnull +22 -> 1638
    //   1619: aload_0
    //   1620: getfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1623: invokeinterface 549 1 0
    //   1628: invokeinterface 554 1 0
    //   1633: aload_0
    //   1634: aconst_null
    //   1635: putfield 27	com/tencent/tmassistantsdk/downloadservice/g:b	Lorg/apache/http/client/HttpClient;
    //   1638: aload_0
    //   1639: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1642: ifnull +15 -> 1657
    //   1645: aload_0
    //   1646: getfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1649: invokevirtual 367	com/tencent/tmassistantsdk/f/b:d	()V
    //   1652: aload_0
    //   1653: aconst_null
    //   1654: putfield 343	com/tencent/tmassistantsdk/downloadservice/g:f	Lcom/tencent/tmassistantsdk/f/b;
    //   1657: iload 7
    //   1659: iconst_1
    //   1660: if_icmpne +48 -> 1708
    //   1663: aload 5
    //   1665: ifnull +43 -> 1708
    //   1668: aload 5
    //   1670: invokestatic 167	java/lang/System:currentTimeMillis	()J
    //   1673: putfield 478	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:endTime	J
    //   1676: aload 5
    //   1678: aload_0
    //   1679: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1682: getfield 481	com/tencent/tmassistantsdk/downloadservice/d:o	I
    //   1685: putfield 484	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:errorCode	I
    //   1688: aload 5
    //   1690: aload_0
    //   1691: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1694: getfield 487	com/tencent/tmassistantsdk/downloadservice/d:i	I
    //   1697: putfield 490	com/tencent/tmassistantsdk/protocol/jce/DownloadChunkLogInfo:resultState	I
    //   1700: invokestatic 456	com/tencent/tmassistantsdk/d/b:g	()Lcom/tencent/tmassistantsdk/d/b;
    //   1703: aload 5
    //   1705: invokevirtual 493	com/tencent/tmassistantsdk/d/b:a	(Lcom/qq/taf/jce/JceStruct;)V
    //   1708: aload 6
    //   1710: athrow
    //   1711: aload_0
    //   1712: getfield 31	com/tencent/tmassistantsdk/downloadservice/g:d	Lcom/tencent/tmassistantsdk/downloadservice/d;
    //   1715: iconst_5
    //   1716: invokevirtual 451	com/tencent/tmassistantsdk/downloadservice/d:a	(I)V
    //   1719: aload_0
    //   1720: aload_3
    //   1721: invokespecial 567	com/tencent/tmassistantsdk/downloadservice/g:a	(Ljava/lang/Throwable;)V
    //   1724: goto -1618 -> 106
    //   1727: astore 6
    //   1729: iconst_1
    //   1730: istore 7
    //   1732: goto -149 -> 1583
    //   1735: aload 5
    //   1737: astore_2
    //   1738: goto -1715 -> 23
    //
    // Exception table:
    //   from	to	target	type
    //   202	318	359	org/apache/http/conn/ConnectTimeoutException
    //   323	359	359	org/apache/http/conn/ConnectTimeoutException
    //   537	554	359	org/apache/http/conn/ConnectTimeoutException
    //   398	404	696	java/lang/InterruptedException
    //   202	318	732	java/net/SocketTimeoutException
    //   323	359	732	java/net/SocketTimeoutException
    //   537	554	732	java/net/SocketTimeoutException
    //   771	777	908	java/lang/InterruptedException
    //   202	318	948	java/net/UnknownHostException
    //   323	359	948	java/net/UnknownHostException
    //   537	554	948	java/net/UnknownHostException
    //   202	318	1046	java/lang/InterruptedException
    //   323	359	1046	java/lang/InterruptedException
    //   537	554	1046	java/lang/InterruptedException
    //   202	318	1144	java/io/IOException
    //   323	359	1144	java/io/IOException
    //   537	554	1144	java/io/IOException
    //   202	318	1242	com/tencent/tmassistantsdk/downloadservice/o
    //   323	359	1242	com/tencent/tmassistantsdk/downloadservice/o
    //   537	554	1242	com/tencent/tmassistantsdk/downloadservice/o
    //   202	318	1479	java/lang/Throwable
    //   323	359	1479	java/lang/Throwable
    //   537	554	1479	java/lang/Throwable
    //   202	318	1577	finally
    //   323	359	1577	finally
    //   361	375	1577	finally
    //   537	554	1577	finally
    //   713	723	1577	finally
    //   734	748	1577	finally
    //   929	939	1577	finally
    //   949	963	1577	finally
    //   1047	1061	1577	finally
    //   1145	1159	1577	finally
    //   1243	1309	1577	finally
    //   1480	1494	1577	finally
    //   380	398	1727	finally
    //   398	404	1727	finally
    //   697	707	1727	finally
    //   753	771	1727	finally
    //   771	777	1727	finally
    //   910	920	1727	finally
    //   1314	1332	1727	finally
  }

  public void b()
  {
    l.b("_DownloadTask", "DownloadTask::cancel url: " + this.d.b);
    this.a = true;
    if ((this.c != null) && (!this.c.isAborted()))
      this.c.abort();
  }

  public String c()
  {
    return this.d.b;
  }

  public int d()
  {
    return this.d.n;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.g
 * JD-Core Version:    0.6.0
 */