package com.tencent.component.net.download.multiplex.http;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

public class HttpRequester extends Requester
{
  public static boolean a = false;
  private static final String m = "HttpRequester";
  private URL n;
  private HttpURLConnection o;
  private MttInputStream p;
  private OutputStream q;
  private MttRequest r;
  private MttResponse s;

  HttpRequester()
  {
    this.i = true;
  }

  private void a(HttpURLConnection paramHttpURLConnection, MttResponse paramMttResponse)
  {
    if (paramHttpURLConnection == null)
      return;
    paramMttResponse.a(paramHttpURLConnection.getHeaderFields());
    paramMttResponse.a(Integer.valueOf(paramHttpURLConnection.getResponseCode()));
    paramMttResponse.b(paramHttpURLConnection.getHeaderField("Location"));
    paramMttResponse.h(paramHttpURLConnection.getHeaderField("Server"));
    paramMttResponse.a(c(paramHttpURLConnection.getHeaderField("Content-Length")));
    paramMttResponse.i(paramHttpURLConnection.getHeaderField("Content-Encoding"));
    paramMttResponse.d(paramHttpURLConnection.getHeaderField("Charset"));
    paramMttResponse.e(paramHttpURLConnection.getHeaderField("Transfer-Encoding"));
    paramMttResponse.f(paramHttpURLConnection.getHeaderField("Last-Modified"));
    paramMttResponse.j(paramHttpURLConnection.getHeaderField("Byte-Ranges"));
    paramMttResponse.g(paramHttpURLConnection.getHeaderField("Cache-Control"));
    paramMttResponse.k(paramHttpURLConnection.getHeaderField("Connection"));
    paramMttResponse.l(paramHttpURLConnection.getHeaderField("Content-Range"));
    paramMttResponse.m(paramHttpURLConnection.getHeaderField("Content-Disposition"));
    paramMttResponse.c(paramHttpURLConnection.getHeaderField("etag"));
    paramMttResponse.n(paramHttpURLConnection.getHeaderField("q-need"));
    paramMttResponse.o(paramHttpURLConnection.getHeaderField("q-nkey"));
    paramMttResponse.p(paramHttpURLConnection.getHeaderField("q-tip"));
    paramMttResponse.q(paramHttpURLConnection.getHeaderField("QQ-S-ZIP"));
    paramMttResponse.r(paramHttpURLConnection.getHeaderField("QQ-S-Encrypt"));
    ContentType localContentType = new ContentType("text", "html", null);
    String str1 = paramHttpURLConnection.getHeaderField("Content-Type");
    String str2;
    String str3;
    String str4;
    if ((str1 != null) || (str1 != null))
    {
      str2 = str1.trim();
      if (!"".equals(str2))
      {
        int i = str2.indexOf(';');
        if (i == -1)
          break label376;
        str3 = str2.substring(0, i);
        str4 = str2.substring(i + 1);
        if (str3 != null)
        {
          int k = str3.indexOf('/');
          if (k == -1)
            break label386;
          localContentType.a(str3.substring(0, k));
          localContentType.b(str3.substring(k + 1));
        }
      }
    }
    while (true)
    {
      if (str4 != null)
      {
        int j = str4.indexOf('=');
        if (j != -1)
          localContentType.c(str4.substring(j + 1));
      }
      paramMttResponse.a(localContentType);
      return;
      label376: str3 = str2;
      str4 = null;
      break;
      label386: localContentType.a(str3);
    }
  }

  private Integer b(String paramString)
  {
    if (paramString != null)
      try
      {
        Integer localInteger = Integer.valueOf(paramString);
        return localInteger;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    return null;
  }

  private void b(MttRequest paramMttRequest)
  {
  }

  private long c(String paramString)
  {
    if (paramString != null)
      return Long.parseLong(paramString);
    return 0L;
  }

  private void c(MttRequest paramMttRequest)
  {
    this.r.a(this.l, this.i, a, g());
    Iterator localIterator = paramMttRequest.h().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      this.o.setRequestProperty((String)localEntry.getKey(), (String)localEntry.getValue());
    }
  }

  private static void i()
  {
    TrustManager[] arrayOfTrustManager = new TrustManager[1];
    arrayOfTrustManager[0] = new HttpRequester.miTM();
    SSLContext localSSLContext = SSLContext.getInstance("TLS");
    localSSLContext.init(null, arrayOfTrustManager, null);
    HttpsURLConnection.setDefaultSSLSocketFactory(localSSLContext.getSocketFactory());
  }

  // ERROR //
  public MttResponse a(MttRequest paramMttRequest)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnonnull +5 -> 6
    //   4: aconst_null
    //   5: areturn
    //   6: aload_1
    //   7: iconst_1
    //   8: putfield 288	com/tencent/component/net/download/multiplex/http/MttRequest:h	B
    //   11: aload_0
    //   12: aload_1
    //   13: putfield 206	com/tencent/component/net/download/multiplex/http/HttpRequester:r	Lcom/tencent/component/net/download/multiplex/http/MttRequest;
    //   16: aload_0
    //   17: invokestatic 292	com/tencent/component/net/download/multiplex/http/Apn:c	()I
    //   20: invokevirtual 295	com/tencent/component/net/download/multiplex/http/HttpRequester:c	(I)V
    //   23: aload_0
    //   24: aload_0
    //   25: getfield 206	com/tencent/component/net/download/multiplex/http/HttpRequester:r	Lcom/tencent/component/net/download/multiplex/http/MttRequest;
    //   28: invokevirtual 297	com/tencent/component/net/download/multiplex/http/MttRequest:a	()Ljava/lang/String;
    //   31: invokestatic 302	com/tencent/component/net/download/multiplex/download/extension/UrlUtility:c	(Ljava/lang/String;)Ljava/net/URL;
    //   34: putfield 304	com/tencent/component/net/download/multiplex/http/HttpRequester:n	Ljava/net/URL;
    //   37: ldc 11
    //   39: new 306	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 307	java/lang/StringBuilder:<init>	()V
    //   46: ldc_w 309
    //   49: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   52: invokestatic 315	com/tencent/component/net/download/multiplex/http/Apn:a	()I
    //   55: invokevirtual 318	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   58: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   61: invokestatic 325	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   64: invokestatic 328	com/tencent/component/net/download/multiplex/http/Apn:e	()Lcom/tencent/component/net/download/multiplex/http/Apn$ApnProxyInfo;
    //   67: astore_2
    //   68: aload_2
    //   69: getfield 332	com/tencent/component/net/download/multiplex/http/Apn$ApnProxyInfo:d	Z
    //   72: ifeq +504 -> 576
    //   75: aload_0
    //   76: getfield 304	com/tencent/component/net/download/multiplex/http/HttpRequester:n	Ljava/net/URL;
    //   79: invokevirtual 335	java/net/URL:toString	()Ljava/lang/String;
    //   82: astore 12
    //   84: iconst_3
    //   85: aload 12
    //   87: ldc_w 337
    //   90: invokevirtual 340	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   93: iadd
    //   94: istore 13
    //   96: aload 12
    //   98: bipush 47
    //   100: iload 13
    //   102: invokevirtual 343	java/lang/String:indexOf	(II)I
    //   105: istore 14
    //   107: iload 14
    //   109: ifge +384 -> 493
    //   112: aload 12
    //   114: iload 13
    //   116: invokevirtual 183	java/lang/String:substring	(I)Ljava/lang/String;
    //   119: astore 15
    //   121: ldc 168
    //   123: astore 16
    //   125: aload_2
    //   126: getfield 345	com/tencent/component/net/download/multiplex/http/Apn$ApnProxyInfo:c	B
    //   129: iconst_1
    //   130: if_icmpne +386 -> 516
    //   133: ldc 11
    //   135: ldc_w 347
    //   138: invokestatic 325	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   141: new 349	java/net/Proxy
    //   144: dup
    //   145: getstatic 355	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   148: new 357	java/net/InetSocketAddress
    //   151: dup
    //   152: aload_2
    //   153: getfield 359	com/tencent/component/net/download/multiplex/http/Apn$ApnProxyInfo:a	Ljava/lang/String;
    //   156: bipush 80
    //   158: invokespecial 362	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   161: invokespecial 365	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   164: astore 17
    //   166: aload_0
    //   167: aload_0
    //   168: getfield 304	com/tencent/component/net/download/multiplex/http/HttpRequester:n	Ljava/net/URL;
    //   171: aload 17
    //   173: invokevirtual 369	java/net/URL:openConnection	(Ljava/net/Proxy;)Ljava/net/URLConnection;
    //   176: checkcast 36	java/net/HttpURLConnection
    //   179: putfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   182: aload_0
    //   183: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   186: aload_0
    //   187: getfield 206	com/tencent/component/net/download/multiplex/http/HttpRequester:r	Lcom/tencent/component/net/download/multiplex/http/MttRequest;
    //   190: invokevirtual 371	com/tencent/component/net/download/multiplex/http/MttRequest:g	()Ljava/lang/String;
    //   193: invokevirtual 374	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   196: aload_0
    //   197: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   200: iconst_0
    //   201: invokevirtual 378	java/net/HttpURLConnection:setInstanceFollowRedirects	(Z)V
    //   204: aload_0
    //   205: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   208: aload_0
    //   209: getfield 381	com/tencent/component/net/download/multiplex/http/HttpRequester:f	I
    //   212: invokevirtual 384	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   215: aload_0
    //   216: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   219: aload_0
    //   220: getfield 386	com/tencent/component/net/download/multiplex/http/HttpRequester:e	I
    //   223: invokevirtual 389	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   226: aload_0
    //   227: getfield 206	com/tencent/component/net/download/multiplex/http/HttpRequester:r	Lcom/tencent/component/net/download/multiplex/http/MttRequest;
    //   230: invokevirtual 371	com/tencent/component/net/download/multiplex/http/MttRequest:g	()Ljava/lang/String;
    //   233: ldc_w 391
    //   236: invokevirtual 395	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   239: ifeq +11 -> 250
    //   242: aload_0
    //   243: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   246: iconst_1
    //   247: invokevirtual 398	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   250: aload_0
    //   251: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   254: iconst_1
    //   255: invokevirtual 401	java/net/HttpURLConnection:setDoInput	(Z)V
    //   258: aload_0
    //   259: getfield 304	com/tencent/component/net/download/multiplex/http/HttpRequester:n	Ljava/net/URL;
    //   262: invokevirtual 404	java/net/URL:getHost	()Ljava/lang/String;
    //   265: invokestatic 406	com/tencent/component/net/download/multiplex/download/extension/UrlUtility:a	(Ljava/lang/String;)Z
    //   268: ifeq +325 -> 593
    //   271: ldc_w 408
    //   274: new 306	java/lang/StringBuilder
    //   277: dup
    //   278: invokespecial 307	java/lang/StringBuilder:<init>	()V
    //   281: ldc_w 410
    //   284: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: invokestatic 412	com/tencent/component/net/download/multiplex/http/MttRequest:n	()Ljava/lang/String;
    //   290: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: ldc_w 414
    //   296: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   299: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   302: invokestatic 325	com/tencent/component/utils/log/LogUtil:d	(Ljava/lang/String;Ljava/lang/String;)V
    //   305: invokestatic 412	com/tencent/component/net/download/multiplex/http/MttRequest:n	()Ljava/lang/String;
    //   308: ifnull +32 -> 340
    //   311: aload_1
    //   312: ldc_w 416
    //   315: new 306	java/lang/StringBuilder
    //   318: dup
    //   319: invokespecial 307	java/lang/StringBuilder:<init>	()V
    //   322: invokestatic 412	com/tencent/component/net/download/multiplex/http/MttRequest:n	()Ljava/lang/String;
    //   325: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   328: ldc_w 418
    //   331: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   334: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   337: invokevirtual 420	com/tencent/component/net/download/multiplex/http/MttRequest:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   340: aload_1
    //   341: invokevirtual 423	com/tencent/component/net/download/multiplex/http/MttRequest:b	()B
    //   344: bipush 104
    //   346: if_icmpne +3 -> 349
    //   349: aload_1
    //   350: iconst_2
    //   351: putfield 288	com/tencent/component/net/download/multiplex/http/MttRequest:h	B
    //   354: aload_0
    //   355: aload_1
    //   356: invokespecial 425	com/tencent/component/net/download/multiplex/http/HttpRequester:c	(Lcom/tencent/component/net/download/multiplex/http/MttRequest;)V
    //   359: aload_0
    //   360: aload_1
    //   361: invokespecial 427	com/tencent/component/net/download/multiplex/http/HttpRequester:b	(Lcom/tencent/component/net/download/multiplex/http/MttRequest;)V
    //   364: aload_1
    //   365: iconst_3
    //   366: putfield 288	com/tencent/component/net/download/multiplex/http/MttRequest:h	B
    //   369: aload_0
    //   370: new 42	com/tencent/component/net/download/multiplex/http/MttResponse
    //   373: dup
    //   374: invokespecial 428	com/tencent/component/net/download/multiplex/http/MttResponse:<init>	()V
    //   377: putfield 430	com/tencent/component/net/download/multiplex/http/HttpRequester:s	Lcom/tencent/component/net/download/multiplex/http/MttResponse;
    //   380: aload_0
    //   381: aload_0
    //   382: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   385: aload_0
    //   386: getfield 430	com/tencent/component/net/download/multiplex/http/HttpRequester:s	Lcom/tencent/component/net/download/multiplex/http/MttResponse;
    //   389: invokespecial 432	com/tencent/component/net/download/multiplex/http/HttpRequester:a	(Ljava/net/HttpURLConnection;Lcom/tencent/component/net/download/multiplex/http/MttResponse;)V
    //   392: aload_0
    //   393: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   396: invokevirtual 436	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   399: astore 11
    //   401: aload 11
    //   403: astore 6
    //   405: aload 6
    //   407: ifnull +76 -> 483
    //   410: aload_1
    //   411: invokevirtual 423	com/tencent/component/net/download/multiplex/http/MttRequest:b	()B
    //   414: bipush 104
    //   416: if_icmpeq +298 -> 714
    //   419: aload_0
    //   420: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   423: invokevirtual 439	java/net/HttpURLConnection:getContentEncoding	()Ljava/lang/String;
    //   426: astore 8
    //   428: aload 8
    //   430: ifnull +249 -> 679
    //   433: aload 8
    //   435: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   438: ldc_w 444
    //   441: invokevirtual 340	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   444: iconst_m1
    //   445: if_icmpeq +234 -> 679
    //   448: new 446	java/util/zip/GZIPInputStream
    //   451: dup
    //   452: aload 6
    //   454: invokespecial 449	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   457: astore 7
    //   459: aload_0
    //   460: new 451	com/tencent/component/net/download/multiplex/http/MttInputStream
    //   463: dup
    //   464: aload 7
    //   466: invokespecial 452	com/tencent/component/net/download/multiplex/http/MttInputStream:<init>	(Ljava/io/InputStream;)V
    //   469: putfield 454	com/tencent/component/net/download/multiplex/http/HttpRequester:p	Lcom/tencent/component/net/download/multiplex/http/MttInputStream;
    //   472: aload_0
    //   473: getfield 430	com/tencent/component/net/download/multiplex/http/HttpRequester:s	Lcom/tencent/component/net/download/multiplex/http/MttResponse;
    //   476: aload_0
    //   477: getfield 454	com/tencent/component/net/download/multiplex/http/HttpRequester:p	Lcom/tencent/component/net/download/multiplex/http/MttInputStream;
    //   480: invokevirtual 457	com/tencent/component/net/download/multiplex/http/MttResponse:a	(Lcom/tencent/component/net/download/multiplex/http/MttInputStream;)V
    //   483: aload_1
    //   484: iconst_4
    //   485: putfield 288	com/tencent/component/net/download/multiplex/http/MttRequest:h	B
    //   488: aload_0
    //   489: getfield 430	com/tencent/component/net/download/multiplex/http/HttpRequester:s	Lcom/tencent/component/net/download/multiplex/http/MttResponse;
    //   492: areturn
    //   493: aload 12
    //   495: iload 13
    //   497: iload 14
    //   499: invokevirtual 180	java/lang/String:substring	(II)Ljava/lang/String;
    //   502: astore 15
    //   504: aload 12
    //   506: iload 14
    //   508: invokevirtual 183	java/lang/String:substring	(I)Ljava/lang/String;
    //   511: astore 16
    //   513: goto -388 -> 125
    //   516: aload_0
    //   517: new 334	java/net/URL
    //   520: dup
    //   521: new 306	java/lang/StringBuilder
    //   524: dup
    //   525: invokespecial 307	java/lang/StringBuilder:<init>	()V
    //   528: ldc_w 459
    //   531: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   534: aload_2
    //   535: getfield 359	com/tencent/component/net/download/multiplex/http/Apn$ApnProxyInfo:a	Ljava/lang/String;
    //   538: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   541: aload 16
    //   543: invokevirtual 313	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   546: invokevirtual 321	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   549: invokespecial 461	java/net/URL:<init>	(Ljava/lang/String;)V
    //   552: invokevirtual 464	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   555: checkcast 36	java/net/HttpURLConnection
    //   558: putfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   561: aload_0
    //   562: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   565: ldc_w 466
    //   568: aload 15
    //   570: invokevirtual 253	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   573: goto -391 -> 182
    //   576: aload_0
    //   577: aload_0
    //   578: getfield 304	com/tencent/component/net/download/multiplex/http/HttpRequester:n	Ljava/net/URL;
    //   581: invokevirtual 464	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   584: checkcast 36	java/net/HttpURLConnection
    //   587: putfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   590: goto -408 -> 182
    //   593: aload_1
    //   594: invokevirtual 468	com/tencent/component/net/download/multiplex/http/MttRequest:m	()Ljava/lang/String;
    //   597: ifnull -257 -> 340
    //   600: aload_0
    //   601: getfield 208	com/tencent/component/net/download/multiplex/http/HttpRequester:l	Z
    //   604: ifne -264 -> 340
    //   607: aload_1
    //   608: ldc_w 416
    //   611: aload_1
    //   612: invokevirtual 468	com/tencent/component/net/download/multiplex/http/MttRequest:m	()Ljava/lang/String;
    //   615: invokevirtual 420	com/tencent/component/net/download/multiplex/http/MttRequest:a	(Ljava/lang/String;Ljava/lang/String;)V
    //   618: goto -278 -> 340
    //   621: astore_3
    //   622: aload_3
    //   623: athrow
    //   624: astore 4
    //   626: ldc 11
    //   628: ldc_w 470
    //   631: aload 4
    //   633: invokestatic 473	com/tencent/component/utils/log/LogUtil:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)V
    //   636: aload_0
    //   637: getfield 243	com/tencent/component/net/download/multiplex/http/HttpRequester:o	Ljava/net/HttpURLConnection;
    //   640: invokevirtual 476	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
    //   643: astore 10
    //   645: aload 10
    //   647: astore 6
    //   649: goto -244 -> 405
    //   652: astore 5
    //   654: aload 5
    //   656: invokevirtual 198	java/lang/Exception:printStackTrace	()V
    //   659: aconst_null
    //   660: astore 6
    //   662: goto -257 -> 405
    //   665: astore 9
    //   667: aload 9
    //   669: invokevirtual 198	java/lang/Exception:printStackTrace	()V
    //   672: aload 6
    //   674: astore 7
    //   676: goto -217 -> 459
    //   679: aload 8
    //   681: ifnull +33 -> 714
    //   684: aload 8
    //   686: invokevirtual 442	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   689: ldc_w 478
    //   692: invokevirtual 340	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   695: iconst_m1
    //   696: if_icmpeq +18 -> 714
    //   699: new 446	java/util/zip/GZIPInputStream
    //   702: dup
    //   703: aload 6
    //   705: iconst_0
    //   706: invokespecial 481	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;I)V
    //   709: astore 7
    //   711: goto -252 -> 459
    //   714: aload 6
    //   716: astore 7
    //   718: goto -259 -> 459
    //
    // Exception table:
    //   from	to	target	type
    //   359	364	621	java/lang/OutOfMemoryError
    //   392	401	624	java/io/IOException
    //   636	645	652	java/lang/Exception
    //   448	459	665	java/lang/Exception
  }

  public void a()
  {
    this.i = false;
    a = false;
  }

  public void a(HttpURLConnection paramHttpURLConnection)
  {
  }

  public void a(boolean paramBoolean)
  {
    this.i = paramBoolean;
  }

  public MttResponse b()
  {
    return this.s;
  }

  public void c()
  {
    if (this.p != null);
    try
    {
      this.p.c();
      if (this.q == null);
    }
    catch (Exception localException2)
    {
      try
      {
        this.q.close();
        if (this.o != null)
          this.o.disconnect();
        return;
        localException2 = localException2;
        localException2.printStackTrace();
      }
      catch (Exception localException1)
      {
        while (true)
          localException1.printStackTrace();
      }
    }
  }

  public void d()
  {
    c();
  }

  public boolean e()
  {
    return this.i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.http.HttpRequester
 * JD-Core Version:    0.6.0
 */