package com.pay.http;

import com.pay.common.tool.APLog;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APAppDataInterface;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.security.cert.CertificateNotYetValidException;
import java.util.HashMap;
import javax.security.cert.CertificateExpiredException;
import org.apache.http.conn.util.InetAddressUtils;

public abstract class APBaseHttpReq extends Thread
{
  private byte[] a;
  private boolean b = false;
  protected IAPHttpAns httpAns;
  public APBaseHttpParam httpParam = new APBaseHttpParam();
  protected HttpURLConnection httpURLConnection;

  public APBaseHttpReq()
  {
    this.httpParam.reqParam = new HashMap();
    this.httpParam.domain = APAppDataInterface.singleton().getSysServerIP();
  }

  // ERROR //
  private void a()
  {
    // Byte code:
    //   0: new 58	java/io/ByteArrayOutputStream
    //   3: dup
    //   4: invokespecial 59	java/io/ByteArrayOutputStream:<init>	()V
    //   7: astore_1
    //   8: aload_0
    //   9: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   12: invokestatic 65	java/lang/System:currentTimeMillis	()J
    //   15: putfield 69	com/pay/http/APBaseHttpParam:begTime	J
    //   18: aload_0
    //   19: invokevirtual 72	com/pay/http/APBaseHttpReq:constructParam	()V
    //   22: aload_0
    //   23: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   26: invokevirtual 75	com/pay/http/APBaseHttpParam:constructUrl	()V
    //   29: aload_0
    //   30: invokevirtual 78	com/pay/http/APBaseHttpReq:preCreateConnection	()V
    //   33: new 80	java/net/URL
    //   36: dup
    //   37: aload_0
    //   38: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   41: getfield 83	com/pay/http/APBaseHttpParam:url	Ljava/lang/String;
    //   44: invokespecial 86	java/net/URL:<init>	(Ljava/lang/String;)V
    //   47: astore_2
    //   48: ldc 88
    //   50: new 90	java/lang/StringBuilder
    //   53: dup
    //   54: ldc 92
    //   56: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   59: aload_0
    //   60: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   63: getfield 83	com/pay/http/APBaseHttpParam:url	Ljava/lang/String;
    //   66: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: ldc 99
    //   71: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: aload_0
    //   75: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   78: getfield 102	com/pay/http/APBaseHttpParam:defaultDomain	Ljava/lang/String;
    //   81: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   87: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   90: aload_0
    //   91: getfield 113	com/pay/http/APBaseHttpReq:httpAns	Lcom/pay/http/IAPHttpAns;
    //   94: aload_0
    //   95: invokeinterface 119 2 0
    //   100: aload_0
    //   101: aload_2
    //   102: invokevirtual 123	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   105: checkcast 125	java/net/HttpURLConnection
    //   108: putfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   111: aload_0
    //   112: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   115: aload_0
    //   116: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   119: getfield 131	com/pay/http/APBaseHttpParam:connectTimeout	I
    //   122: invokevirtual 135	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   125: aload_0
    //   126: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   129: aload_0
    //   130: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   133: getfield 138	com/pay/http/APBaseHttpParam:readTimeout	I
    //   136: invokevirtual 141	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   139: aload_0
    //   140: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   143: ldc 143
    //   145: aload_0
    //   146: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   149: getfield 102	com/pay/http/APBaseHttpParam:defaultDomain	Ljava/lang/String;
    //   152: invokevirtual 146	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   155: aload_0
    //   156: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   159: iconst_0
    //   160: invokevirtual 150	java/net/HttpURLConnection:setUseCaches	(Z)V
    //   163: aload_0
    //   164: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   167: invokevirtual 154	java/net/HttpURLConnection:getDoOutput	()Z
    //   170: ifeq +23 -> 193
    //   173: aload_0
    //   174: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   177: invokevirtual 158	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   180: invokevirtual 163	java/io/OutputStream:flush	()V
    //   183: aload_0
    //   184: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   187: invokevirtual 158	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   190: invokevirtual 166	java/io/OutputStream:close	()V
    //   193: aload_0
    //   194: invokevirtual 169	com/pay/http/APBaseHttpReq:setHeader	()V
    //   197: aload_0
    //   198: invokevirtual 172	com/pay/http/APBaseHttpReq:setBody	()V
    //   201: aload_0
    //   202: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   205: getfield 175	com/pay/http/APBaseHttpParam:sendType	Ljava/lang/String;
    //   208: ldc 177
    //   210: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   213: ifeq +39 -> 252
    //   216: new 185	java/io/DataOutputStream
    //   219: dup
    //   220: aload_0
    //   221: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   224: invokevirtual 158	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   227: invokespecial 188	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   230: astore 21
    //   232: aload 21
    //   234: aload_0
    //   235: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   238: getfield 191	com/pay/http/APBaseHttpParam:urlParams	Ljava/lang/String;
    //   241: invokevirtual 195	java/lang/String:getBytes	()[B
    //   244: invokevirtual 199	java/io/DataOutputStream:write	([B)V
    //   247: aload 21
    //   249: invokevirtual 200	java/io/DataOutputStream:flush	()V
    //   252: aload_0
    //   253: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   256: invokevirtual 204	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   259: astore 22
    //   261: aload 22
    //   263: astore 7
    //   265: aload_0
    //   266: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   269: invokevirtual 208	java/net/HttpURLConnection:getResponseCode	()I
    //   272: sipush 200
    //   275: if_icmpne +297 -> 572
    //   278: sipush 1024
    //   281: newarray byte
    //   283: astore 26
    //   285: iconst_0
    //   286: istore 27
    //   288: aload 7
    //   290: aload 26
    //   292: invokevirtual 214	java/io/InputStream:read	([B)I
    //   295: istore 28
    //   297: iload 28
    //   299: ifgt +145 -> 444
    //   302: aload_0
    //   303: aload_1
    //   304: invokevirtual 217	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   307: putfield 219	com/pay/http/APBaseHttpReq:a	[B
    //   310: invokestatic 225	com/pay/http/APIPList:getInstance	()Lcom/pay/http/APIPList;
    //   313: aload_0
    //   314: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   317: getfield 46	com/pay/http/APBaseHttpParam:domain	Ljava/lang/String;
    //   320: iconst_1
    //   321: invokevirtual 229	com/pay/http/APIPList:setIPState	(Ljava/lang/String;Z)V
    //   324: invokestatic 38	com/pay/tool/APAppDataInterface:singleton	()Lcom/pay/tool/APAppDataInterface;
    //   327: aload_0
    //   328: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   331: getfield 46	com/pay/http/APBaseHttpParam:domain	Ljava/lang/String;
    //   334: invokevirtual 232	com/pay/tool/APAppDataInterface:setSysServerIP	(Ljava/lang/String;)V
    //   337: aload_0
    //   338: getfield 113	com/pay/http/APBaseHttpReq:httpAns	Lcom/pay/http/IAPHttpAns;
    //   341: aload_0
    //   342: invokeinterface 235 2 0
    //   347: aload_0
    //   348: iconst_0
    //   349: sipush 200
    //   352: invokespecial 238	com/pay/http/APBaseHttpReq:a	(II)V
    //   355: aload_0
    //   356: aload 7
    //   358: aload_1
    //   359: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   362: aload_0
    //   363: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   366: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   369: ldc 246
    //   371: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   374: ifeq +39 -> 413
    //   377: ldc 248
    //   379: ldc 250
    //   381: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   384: ldc 252
    //   386: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   389: astore 25
    //   391: aload 25
    //   393: aconst_null
    //   394: aconst_null
    //   395: new 259	java/security/SecureRandom
    //   398: dup
    //   399: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   402: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   405: aload 25
    //   407: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   410: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   413: return
    //   414: astore 34
    //   416: aload 34
    //   418: invokevirtual 277	java/net/MalformedURLException:printStackTrace	()V
    //   421: aconst_null
    //   422: astore_2
    //   423: goto -375 -> 48
    //   426: astore_3
    //   427: aload_3
    //   428: invokevirtual 278	java/io/IOException:printStackTrace	()V
    //   431: goto -268 -> 163
    //   434: astore 33
    //   436: aload 33
    //   438: invokevirtual 278	java/io/IOException:printStackTrace	()V
    //   441: goto -248 -> 193
    //   444: aload_0
    //   445: getfield 20	com/pay/http/APBaseHttpReq:b	Z
    //   448: ifeq +84 -> 532
    //   451: invokestatic 282	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   454: invokevirtual 285	java/lang/Thread:interrupt	()V
    //   457: aload_0
    //   458: aload 7
    //   460: aload_1
    //   461: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   464: aload_0
    //   465: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   468: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   471: ldc 246
    //   473: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   476: ifeq -63 -> 413
    //   479: ldc 248
    //   481: ldc 250
    //   483: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   486: ldc 252
    //   488: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   491: astore 32
    //   493: aload 32
    //   495: aconst_null
    //   496: aconst_null
    //   497: new 259	java/security/SecureRandom
    //   500: dup
    //   501: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   504: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   507: aload 32
    //   509: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   512: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   515: return
    //   516: astore 31
    //   518: ldc 248
    //   520: ldc_w 287
    //   523: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   526: aload 31
    //   528: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   531: return
    //   532: aload_1
    //   533: aload 26
    //   535: iconst_0
    //   536: iload 28
    //   538: invokevirtual 294	java/io/ByteArrayOutputStream:write	([BII)V
    //   541: iload 27
    //   543: iload 28
    //   545: iadd
    //   546: istore 30
    //   548: aload_0
    //   549: getfield 113	com/pay/http/APBaseHttpReq:httpAns	Lcom/pay/http/IAPHttpAns;
    //   552: aload 26
    //   554: iload 28
    //   556: iload 30
    //   558: i2l
    //   559: aload_0
    //   560: invokeinterface 298 6 0
    //   565: iload 30
    //   567: istore 27
    //   569: goto -281 -> 288
    //   572: new 90	java/lang/StringBuilder
    //   575: dup
    //   576: ldc_w 300
    //   579: invokespecial 93	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   582: aload_0
    //   583: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   586: invokevirtual 208	java/net/HttpURLConnection:getResponseCode	()I
    //   589: invokevirtual 303	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   592: ldc_w 305
    //   595: invokevirtual 97	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   598: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   601: astore 23
    //   603: aload_0
    //   604: bipush 246
    //   606: aload_0
    //   607: getfield 127	com/pay/http/APBaseHttpReq:httpURLConnection	Ljava/net/HttpURLConnection;
    //   610: invokevirtual 208	java/net/HttpURLConnection:getResponseCode	()I
    //   613: aconst_null
    //   614: aload 23
    //   616: invokespecial 308	com/pay/http/APBaseHttpReq:a	(IILjava/lang/Exception;Ljava/lang/String;)V
    //   619: goto -264 -> 355
    //   622: astore 4
    //   624: aload 7
    //   626: astore 5
    //   628: aload_0
    //   629: aload 5
    //   631: aload_1
    //   632: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   635: aload_0
    //   636: bipush 249
    //   638: iconst_m1
    //   639: aload 4
    //   641: ldc_w 310
    //   644: invokespecial 308	com/pay/http/APBaseHttpReq:a	(IILjava/lang/Exception;Ljava/lang/String;)V
    //   647: aload_0
    //   648: aload 5
    //   650: aload_1
    //   651: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   654: aload_0
    //   655: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   658: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   661: ldc 246
    //   663: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   666: ifeq -253 -> 413
    //   669: ldc 248
    //   671: ldc 250
    //   673: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   676: ldc 252
    //   678: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   681: astore 11
    //   683: aload 11
    //   685: aconst_null
    //   686: aconst_null
    //   687: new 259	java/security/SecureRandom
    //   690: dup
    //   691: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   694: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   697: aload 11
    //   699: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   702: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   705: return
    //   706: astore 10
    //   708: ldc 248
    //   710: ldc_w 287
    //   713: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   716: aload 10
    //   718: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   721: return
    //   722: astore 18
    //   724: aconst_null
    //   725: astore 7
    //   727: aload_0
    //   728: aload 7
    //   730: aload_1
    //   731: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   734: aload_0
    //   735: bipush 248
    //   737: iconst_m1
    //   738: aload 18
    //   740: ldc_w 312
    //   743: invokespecial 308	com/pay/http/APBaseHttpReq:a	(IILjava/lang/Exception;Ljava/lang/String;)V
    //   746: aload_0
    //   747: aload 7
    //   749: aload_1
    //   750: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   753: aload_0
    //   754: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   757: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   760: ldc 246
    //   762: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   765: ifeq -352 -> 413
    //   768: ldc 248
    //   770: ldc 250
    //   772: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   775: ldc 252
    //   777: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   780: astore 20
    //   782: aload 20
    //   784: aconst_null
    //   785: aconst_null
    //   786: new 259	java/security/SecureRandom
    //   789: dup
    //   790: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   793: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   796: aload 20
    //   798: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   801: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   804: return
    //   805: astore 19
    //   807: ldc 248
    //   809: ldc_w 287
    //   812: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   815: aload 19
    //   817: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   820: return
    //   821: astore 15
    //   823: aconst_null
    //   824: astore 7
    //   826: aload_0
    //   827: aload 15
    //   829: invokestatic 318	com/pay/tool/APTools:getErrorCodeFromException	(Ljava/io/IOException;)I
    //   832: iconst_m1
    //   833: aload 15
    //   835: ldc_w 320
    //   838: invokespecial 308	com/pay/http/APBaseHttpReq:a	(IILjava/lang/Exception;Ljava/lang/String;)V
    //   841: aload_0
    //   842: aload 7
    //   844: aload_1
    //   845: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   848: aload_0
    //   849: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   852: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   855: ldc 246
    //   857: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   860: ifeq -447 -> 413
    //   863: ldc 248
    //   865: ldc 250
    //   867: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   870: ldc 252
    //   872: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   875: astore 17
    //   877: aload 17
    //   879: aconst_null
    //   880: aconst_null
    //   881: new 259	java/security/SecureRandom
    //   884: dup
    //   885: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   888: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   891: aload 17
    //   893: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   896: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   899: return
    //   900: astore 16
    //   902: ldc 248
    //   904: ldc_w 287
    //   907: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   910: aload 16
    //   912: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   915: return
    //   916: astore 12
    //   918: aconst_null
    //   919: astore 7
    //   921: aload_0
    //   922: aload 7
    //   924: aload_1
    //   925: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   928: aload_0
    //   929: bipush 250
    //   931: iconst_m1
    //   932: aload 12
    //   934: ldc_w 322
    //   937: invokespecial 308	com/pay/http/APBaseHttpReq:a	(IILjava/lang/Exception;Ljava/lang/String;)V
    //   940: aload_0
    //   941: aload 7
    //   943: aload_1
    //   944: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   947: aload_0
    //   948: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   951: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   954: ldc 246
    //   956: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   959: ifeq -546 -> 413
    //   962: ldc 248
    //   964: ldc 250
    //   966: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   969: ldc 252
    //   971: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   974: astore 14
    //   976: aload 14
    //   978: aconst_null
    //   979: aconst_null
    //   980: new 259	java/security/SecureRandom
    //   983: dup
    //   984: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   987: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   990: aload 14
    //   992: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   995: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   998: return
    //   999: astore 13
    //   1001: ldc 248
    //   1003: ldc_w 287
    //   1006: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1009: aload 13
    //   1011: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   1014: return
    //   1015: astore 6
    //   1017: aconst_null
    //   1018: astore 7
    //   1020: aload_0
    //   1021: aload 7
    //   1023: aload_1
    //   1024: invokespecial 241	com/pay/http/APBaseHttpReq:a	(Ljava/io/InputStream;Ljava/io/OutputStream;)V
    //   1027: aload_0
    //   1028: getfield 25	com/pay/http/APBaseHttpReq:httpParam	Lcom/pay/http/APBaseHttpParam;
    //   1031: getfield 244	com/pay/http/APBaseHttpParam:reqType	Ljava/lang/String;
    //   1034: ldc 246
    //   1036: invokevirtual 183	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   1039: ifeq +39 -> 1078
    //   1042: ldc 248
    //   1044: ldc 250
    //   1046: invokestatic 111	com/pay/common/tool/APLog:i	(Ljava/lang/String;Ljava/lang/String;)V
    //   1049: ldc 252
    //   1051: invokestatic 257	javax/net/ssl/SSLContext:getInstance	(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;
    //   1054: astore 9
    //   1056: aload 9
    //   1058: aconst_null
    //   1059: aconst_null
    //   1060: new 259	java/security/SecureRandom
    //   1063: dup
    //   1064: invokespecial 260	java/security/SecureRandom:<init>	()V
    //   1067: invokevirtual 264	javax/net/ssl/SSLContext:init	([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V
    //   1070: aload 9
    //   1072: invokevirtual 268	javax/net/ssl/SSLContext:getSocketFactory	()Ljavax/net/ssl/SSLSocketFactory;
    //   1075: invokestatic 274	javax/net/ssl/HttpsURLConnection:setDefaultSSLSocketFactory	(Ljavax/net/ssl/SSLSocketFactory;)V
    //   1078: aload 6
    //   1080: athrow
    //   1081: astore 8
    //   1083: ldc 248
    //   1085: ldc_w 287
    //   1088: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1091: aload 8
    //   1093: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   1096: goto -18 -> 1078
    //   1099: astore 24
    //   1101: ldc 248
    //   1103: ldc_w 287
    //   1106: invokestatic 290	com/pay/common/tool/APLog:w	(Ljava/lang/String;Ljava/lang/String;)V
    //   1109: aload 24
    //   1111: invokevirtual 291	java/lang/Exception:printStackTrace	()V
    //   1114: return
    //   1115: astore 6
    //   1117: goto -97 -> 1020
    //   1120: astore 6
    //   1122: aload 5
    //   1124: astore 7
    //   1126: goto -106 -> 1020
    //   1129: astore 12
    //   1131: goto -210 -> 921
    //   1134: astore 15
    //   1136: goto -310 -> 826
    //   1139: astore 18
    //   1141: goto -414 -> 727
    //   1144: astore 4
    //   1146: aconst_null
    //   1147: astore 5
    //   1149: goto -521 -> 628
    //   1152: astore 29
    //   1154: goto -830 -> 324
    //
    // Exception table:
    //   from	to	target	type
    //   33	48	414	java/net/MalformedURLException
    //   100	163	426	java/io/IOException
    //   173	193	434	java/io/IOException
    //   486	515	516	java/lang/Exception
    //   265	285	622	org/apache/http/conn/ConnectTimeoutException
    //   288	297	622	org/apache/http/conn/ConnectTimeoutException
    //   302	310	622	org/apache/http/conn/ConnectTimeoutException
    //   310	324	622	org/apache/http/conn/ConnectTimeoutException
    //   324	355	622	org/apache/http/conn/ConnectTimeoutException
    //   444	457	622	org/apache/http/conn/ConnectTimeoutException
    //   532	541	622	org/apache/http/conn/ConnectTimeoutException
    //   548	565	622	org/apache/http/conn/ConnectTimeoutException
    //   572	619	622	org/apache/http/conn/ConnectTimeoutException
    //   676	705	706	java/lang/Exception
    //   201	252	722	java/net/SocketTimeoutException
    //   252	261	722	java/net/SocketTimeoutException
    //   775	804	805	java/lang/Exception
    //   201	252	821	java/io/IOException
    //   252	261	821	java/io/IOException
    //   870	899	900	java/lang/Exception
    //   201	252	916	java/lang/Exception
    //   252	261	916	java/lang/Exception
    //   969	998	999	java/lang/Exception
    //   201	252	1015	finally
    //   252	261	1015	finally
    //   1049	1078	1081	java/lang/Exception
    //   384	413	1099	java/lang/Exception
    //   265	285	1115	finally
    //   288	297	1115	finally
    //   302	310	1115	finally
    //   310	324	1115	finally
    //   324	355	1115	finally
    //   444	457	1115	finally
    //   532	541	1115	finally
    //   548	565	1115	finally
    //   572	619	1115	finally
    //   727	746	1115	finally
    //   826	841	1115	finally
    //   921	940	1115	finally
    //   628	647	1120	finally
    //   265	285	1129	java/lang/Exception
    //   288	297	1129	java/lang/Exception
    //   302	310	1129	java/lang/Exception
    //   324	355	1129	java/lang/Exception
    //   444	457	1129	java/lang/Exception
    //   532	541	1129	java/lang/Exception
    //   548	565	1129	java/lang/Exception
    //   572	619	1129	java/lang/Exception
    //   265	285	1134	java/io/IOException
    //   288	297	1134	java/io/IOException
    //   302	310	1134	java/io/IOException
    //   310	324	1134	java/io/IOException
    //   324	355	1134	java/io/IOException
    //   444	457	1134	java/io/IOException
    //   532	541	1134	java/io/IOException
    //   548	565	1134	java/io/IOException
    //   572	619	1134	java/io/IOException
    //   265	285	1139	java/net/SocketTimeoutException
    //   288	297	1139	java/net/SocketTimeoutException
    //   302	310	1139	java/net/SocketTimeoutException
    //   310	324	1139	java/net/SocketTimeoutException
    //   324	355	1139	java/net/SocketTimeoutException
    //   444	457	1139	java/net/SocketTimeoutException
    //   532	541	1139	java/net/SocketTimeoutException
    //   548	565	1139	java/net/SocketTimeoutException
    //   572	619	1139	java/net/SocketTimeoutException
    //   201	252	1144	org/apache/http/conn/ConnectTimeoutException
    //   252	261	1144	org/apache/http/conn/ConnectTimeoutException
    //   310	324	1152	java/lang/Exception
  }

  private void a(int paramInt1, int paramInt2)
  {
    if (this.httpParam.urlName.endsWith("log_data"))
      return;
    this.httpParam.endTime = System.currentTimeMillis();
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("name=");
    localStringBuffer.append(this.httpParam.urlName);
    localStringBuffer.append("&");
    localStringBuffer.append("code=");
    localStringBuffer.append(paramInt2);
    localStringBuffer.append("&");
    localStringBuffer.append("times=");
    localStringBuffer.append(this.httpParam.endTime - this.httpParam.begTime);
    localStringBuffer.append("&");
    localStringBuffer.append("network=");
    localStringBuffer.append(APAppDataInterface.singleton().getNetworkState());
    localStringBuffer.append("&");
    localStringBuffer.append("errorCode=");
    localStringBuffer.append(paramInt1);
    try
    {
      APDataReportManager.getInstance().insertData("sdk.midas.networkrequest", APDataInterface.singleton().getOrderInfo().saveType, null, null, localStringBuffer.toString());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  private void a(int paramInt1, int paramInt2, Exception paramException, String paramString)
  {
    a(paramInt1, paramInt2);
    APLog.i("APBaseHttpReq", getClass().getName() + " tryAgain reqTimes = " + this.httpParam.requestTimes + " tryTimes = " + this.httpParam.reTryTimes);
    while (true)
    {
      Object localObject;
      try
      {
        if (this.httpParam.requestTimes >= this.httpParam.reTryTimes)
          continue;
        this.httpParam.constructReTryUrl();
        a();
        return;
        try
        {
          if (!this.httpParam.reqType.equals("https://"))
            continue;
          localObject = paramException;
          break label202;
          this.httpAns.onError(this, 1000, paramString);
          APLog.i("APBaseHttpReq", paramString);
          paramException.printStackTrace();
          return;
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          return;
        }
      }
      catch (Exception localException1)
      {
        return;
      }
      label202: 
      do
      {
        if (((localObject instanceof CertificateExpiredException)) || ((localObject instanceof CertificateNotYetValidException)))
        {
          APLog.w("APBaseHttpReq", "您的设备系统时间不正确，请更改");
          this.httpAns.onError(this, 1100, paramString);
          return;
        }
        Throwable localThrowable = ((Throwable)localObject).getCause();
        localObject = localThrowable;
      }
      while (localObject != null);
    }
  }

  private void a(InputStream paramInputStream, OutputStream paramOutputStream)
  {
    if (paramInputStream != null);
    try
    {
      paramInputStream.close();
      if (paramOutputStream != null)
      {
        paramOutputStream.flush();
        paramOutputStream.close();
      }
      this.httpURLConnection.disconnect();
      return;
    }
    catch (Exception localException)
    {
    }
  }

  public void constructParam()
  {
  }

  public byte[] getContent()
  {
    return this.a;
  }

  public IAPHttpAns getHttpAns()
  {
    return this.httpAns;
  }

  public boolean isIPAddress(String paramString)
  {
    return (paramString != null) && (InetAddressUtils.isIPv4Address(paramString));
  }

  protected void preCreateConnection()
  {
  }

  public void requestAgain()
  {
    a();
  }

  public void run()
  {
    a();
    super.run();
  }

  protected void setBody()
  {
  }

  public void setContent(byte[] paramArrayOfByte)
  {
    this.a = paramArrayOfByte;
  }

  protected void setHeader()
  {
  }

  public void setHttpAns(IAPHttpAns paramIAPHttpAns)
  {
    this.httpAns = paramIAPHttpAns;
  }

  protected void setUrl(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    this.httpParam.setUrl(paramString1, paramString2, paramString3, paramString4);
  }

  public void startRequest()
  {
    start();
  }

  public void stopRequest()
  {
    APLog.i("APBaseHttpReq", "stopRequest");
    this.b = true;
    this.httpAns.onStop(this);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.http.APBaseHttpReq
 * JD-Core Version:    0.6.0
 */