package com.tencent.beacon.b;

import android.content.Context;
import com.tencent.beacon.a.e;

public class a
{
  private static a f;
  private String a = "";
  private String b = "";
  private String c = "";
  private String d = "";
  private String e = "";

  private a(Context paramContext)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    e.a(paramContext);
    this.b = e.b(paramContext);
    e.a(paramContext);
    this.c = e.e(paramContext);
    e.a(paramContext);
    this.d = e.c(paramContext);
    e.a(paramContext);
    this.e = e.d(paramContext);
    try
    {
      String str = com.tencent.beacon.a.a.b(paramContext, "QIMEI_DENGTA", "");
      if ((str != null) && (!"".equals(str)))
        this.a = str;
      return;
    }
    catch (Exception localException)
    {
    }
  }

  // ERROR //
  public static long a(String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +7 -> 8
    //   4: iload_1
    //   5: ifgt +7 -> 12
    //   8: ldc2_w 75
    //   11: lreturn
    //   12: aconst_null
    //   13: astore_2
    //   14: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   17: lstore_3
    //   18: new 84	java/net/InetSocketAddress
    //   21: dup
    //   22: aload_0
    //   23: iload_1
    //   24: invokespecial 87	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   27: astore 5
    //   29: new 89	java/net/Socket
    //   32: dup
    //   33: invokespecial 90	java/net/Socket:<init>	()V
    //   36: astore 6
    //   38: aload 6
    //   40: aload 5
    //   42: sipush 30000
    //   45: invokevirtual 94	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   48: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   51: lload_3
    //   52: lsub
    //   53: lstore 11
    //   55: new 33	java/lang/StringBuilder
    //   58: dup
    //   59: ldc 96
    //   61: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   64: lload 11
    //   66: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   69: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: iconst_0
    //   73: anewarray 4	java/lang/Object
    //   76: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   79: aload 6
    //   81: invokevirtual 110	java/net/Socket:close	()V
    //   84: lload 11
    //   86: lreturn
    //   87: astore 13
    //   89: aload 13
    //   91: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   94: aload 13
    //   96: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   99: iconst_0
    //   100: anewarray 4	java/lang/Object
    //   103: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   106: lload 11
    //   108: lreturn
    //   109: astore 7
    //   111: aload 7
    //   113: invokevirtual 119	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   116: iconst_0
    //   117: anewarray 4	java/lang/Object
    //   120: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   123: new 33	java/lang/StringBuilder
    //   126: dup
    //   127: ldc 121
    //   129: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   132: aload_0
    //   133: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: ldc 123
    //   138: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: iload_1
    //   142: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   145: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: iconst_0
    //   149: anewarray 4	java/lang/Object
    //   152: invokestatic 128	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: aload_2
    //   156: ifnull +7 -> 163
    //   159: aload_2
    //   160: invokevirtual 110	java/net/Socket:close	()V
    //   163: ldc2_w 75
    //   166: lreturn
    //   167: astore 10
    //   169: aload 10
    //   171: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   174: aload 10
    //   176: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   179: iconst_0
    //   180: anewarray 4	java/lang/Object
    //   183: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   186: ldc2_w 75
    //   189: lreturn
    //   190: astore 8
    //   192: aload_2
    //   193: ifnull +7 -> 200
    //   196: aload_2
    //   197: invokevirtual 110	java/net/Socket:close	()V
    //   200: aload 8
    //   202: athrow
    //   203: astore 9
    //   205: aload 9
    //   207: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   210: aload 9
    //   212: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   215: iconst_0
    //   216: anewarray 4	java/lang/Object
    //   219: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   222: goto -22 -> 200
    //   225: astore 8
    //   227: aload 6
    //   229: astore_2
    //   230: goto -38 -> 192
    //   233: astore 7
    //   235: aload 6
    //   237: astore_2
    //   238: goto -127 -> 111
    //
    // Exception table:
    //   from	to	target	type
    //   79	84	87	java/io/IOException
    //   29	38	109	java/lang/Throwable
    //   159	163	167	java/io/IOException
    //   29	38	190	finally
    //   111	155	190	finally
    //   196	200	203	java/io/IOException
    //   38	79	225	finally
    //   38	79	233	java/lang/Throwable
  }

  // ERROR //
  public static b a(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnull +12 -> 13
    //   4: aload_0
    //   5: ldc 21
    //   7: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: ifeq +5 -> 15
    //   13: aconst_null
    //   14: areturn
    //   15: new 131	com/tencent/beacon/b/a$b
    //   18: dup
    //   19: invokespecial 132	com/tencent/beacon/b/a$b:<init>	()V
    //   22: astore_2
    //   23: aconst_null
    //   24: astore_3
    //   25: aconst_null
    //   26: astore 4
    //   28: aconst_null
    //   29: astore 5
    //   31: new 134	java/net/URL
    //   34: dup
    //   35: aload_0
    //   36: invokespecial 135	java/net/URL:<init>	(Ljava/lang/String;)V
    //   39: astore 6
    //   41: invokestatic 140	android/net/Proxy:getDefaultHost	()Ljava/lang/String;
    //   44: astore 13
    //   46: invokestatic 144	android/net/Proxy:getDefaultPort	()I
    //   49: istore 14
    //   51: aconst_null
    //   52: astore 5
    //   54: aconst_null
    //   55: astore 4
    //   57: aconst_null
    //   58: astore_3
    //   59: aload 13
    //   61: ifnull +19 -> 80
    //   64: aload 13
    //   66: invokevirtual 147	java/lang/String:trim	()Ljava/lang/String;
    //   69: invokevirtual 150	java/lang/String:length	()I
    //   72: ifeq +8 -> 80
    //   75: iload 14
    //   77: ifgt +562 -> 639
    //   80: ldc 152
    //   82: iconst_0
    //   83: anewarray 4	java/lang/Object
    //   86: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   89: aconst_null
    //   90: astore 5
    //   92: aconst_null
    //   93: astore 4
    //   95: aconst_null
    //   96: astore_3
    //   97: aload_1
    //   98: ifnull +735 -> 833
    //   101: aload_1
    //   102: invokevirtual 147	java/lang/String:trim	()Ljava/lang/String;
    //   105: invokevirtual 150	java/lang/String:length	()I
    //   108: ifgt +311 -> 419
    //   111: goto +722 -> 833
    //   114: aconst_null
    //   115: astore 5
    //   117: aconst_null
    //   118: astore 4
    //   120: aconst_null
    //   121: astore_3
    //   122: aload 15
    //   124: ifnonnull +621 -> 745
    //   127: aload 6
    //   129: invokevirtual 156	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   132: checkcast 158	java/net/HttpURLConnection
    //   135: astore_3
    //   136: aload_3
    //   137: iconst_1
    //   138: invokevirtual 162	java/net/HttpURLConnection:setDoInput	(Z)V
    //   141: aload_3
    //   142: iconst_1
    //   143: invokevirtual 165	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   146: aload_3
    //   147: sipush 30000
    //   150: invokevirtual 169	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   153: aload_3
    //   154: sipush 30000
    //   157: invokevirtual 172	java/net/HttpURLConnection:setReadTimeout	(I)V
    //   160: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   163: lstore 17
    //   165: aload_3
    //   166: invokevirtual 174	java/net/HttpURLConnection:connect	()V
    //   169: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   172: lstore 19
    //   174: aload_3
    //   175: invokevirtual 178	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   178: astore 5
    //   180: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   183: lstore 21
    //   185: aload_3
    //   186: invokevirtual 182	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   189: astore 4
    //   191: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   194: lstore 23
    //   196: aload 4
    //   198: invokevirtual 187	java/io/InputStream:read	()I
    //   201: iconst_m1
    //   202: if_icmpne -6 -> 196
    //   205: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   208: lstore 25
    //   210: aload_2
    //   211: ldc2_w 75
    //   214: putfield 190	com/tencent/beacon/b/a$b:a	J
    //   217: aload_2
    //   218: ldc 21
    //   220: putfield 192	com/tencent/beacon/b/a$b:f	Ljava/lang/String;
    //   223: aload_2
    //   224: lload 19
    //   226: lload 17
    //   228: lsub
    //   229: putfield 194	com/tencent/beacon/b/a$b:b	J
    //   232: aload_2
    //   233: lload 21
    //   235: lload 19
    //   237: lsub
    //   238: putfield 196	com/tencent/beacon/b/a$b:c	J
    //   241: aload_2
    //   242: lload 23
    //   244: lload 21
    //   246: lsub
    //   247: putfield 198	com/tencent/beacon/b/a$b:d	J
    //   250: aload_2
    //   251: lload 25
    //   253: lload 23
    //   255: lsub
    //   256: putfield 200	com/tencent/beacon/b/a$b:e	J
    //   259: new 33	java/lang/StringBuilder
    //   262: dup
    //   263: ldc 202
    //   265: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   268: aload_2
    //   269: getfield 190	com/tencent/beacon/b/a$b:a	J
    //   272: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   275: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   278: iconst_0
    //   279: anewarray 4	java/lang/Object
    //   282: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   285: new 33	java/lang/StringBuilder
    //   288: dup
    //   289: ldc 204
    //   291: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   294: aload_2
    //   295: getfield 194	com/tencent/beacon/b/a$b:b	J
    //   298: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   301: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   304: iconst_0
    //   305: anewarray 4	java/lang/Object
    //   308: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   311: new 33	java/lang/StringBuilder
    //   314: dup
    //   315: ldc 206
    //   317: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   320: aload_2
    //   321: getfield 196	com/tencent/beacon/b/a$b:c	J
    //   324: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   327: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   330: iconst_0
    //   331: anewarray 4	java/lang/Object
    //   334: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   337: new 33	java/lang/StringBuilder
    //   340: dup
    //   341: ldc 208
    //   343: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   346: aload_2
    //   347: getfield 198	com/tencent/beacon/b/a$b:d	J
    //   350: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   353: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   356: iconst_0
    //   357: anewarray 4	java/lang/Object
    //   360: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   363: new 33	java/lang/StringBuilder
    //   366: dup
    //   367: ldc 210
    //   369: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   372: aload_2
    //   373: getfield 200	com/tencent/beacon/b/a$b:e	J
    //   376: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   379: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   382: iconst_0
    //   383: anewarray 4	java/lang/Object
    //   386: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   389: aload 5
    //   391: ifnull +8 -> 399
    //   394: aload 5
    //   396: invokevirtual 213	java/io/OutputStream:close	()V
    //   399: aload 4
    //   401: ifnull +8 -> 409
    //   404: aload 4
    //   406: invokevirtual 214	java/io/InputStream:close	()V
    //   409: aload_3
    //   410: ifnull +7 -> 417
    //   413: aload_3
    //   414: invokevirtual 217	java/net/HttpURLConnection:disconnect	()V
    //   417: aload_2
    //   418: areturn
    //   419: new 219	com/tencent/beacon/b/a$a
    //   422: dup
    //   423: invokespecial 220	com/tencent/beacon/b/a$a:<init>	()V
    //   426: astore 30
    //   428: aload_1
    //   429: invokevirtual 223	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   432: astore 31
    //   434: aload 31
    //   436: ldc 225
    //   438: invokevirtual 229	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   441: ifeq +79 -> 520
    //   444: ldc 231
    //   446: iconst_0
    //   447: anewarray 4	java/lang/Object
    //   450: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   453: aload 30
    //   455: ldc 233
    //   457: putfield 234	com/tencent/beacon/b/a$a:a	Ljava/lang/String;
    //   460: aload 30
    //   462: bipush 80
    //   464: putfield 237	com/tencent/beacon/b/a$a:b	I
    //   467: goto +369 -> 836
    //   470: astore 10
    //   472: aload 10
    //   474: invokevirtual 238	java/lang/Throwable:printStackTrace	()V
    //   477: aload 10
    //   479: invokevirtual 119	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   482: iconst_0
    //   483: anewarray 4	java/lang/Object
    //   486: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   489: aload 5
    //   491: ifnull +8 -> 499
    //   494: aload 5
    //   496: invokevirtual 213	java/io/OutputStream:close	()V
    //   499: aload 4
    //   501: ifnull +8 -> 509
    //   504: aload 4
    //   506: invokevirtual 214	java/io/InputStream:close	()V
    //   509: aload_3
    //   510: ifnull -93 -> 417
    //   513: aload_3
    //   514: invokevirtual 217	java/net/HttpURLConnection:disconnect	()V
    //   517: goto -100 -> 417
    //   520: aload 31
    //   522: ldc 240
    //   524: invokevirtual 229	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   527: ifeq +62 -> 589
    //   530: ldc 242
    //   532: iconst_0
    //   533: anewarray 4	java/lang/Object
    //   536: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   539: aload 30
    //   541: ldc 244
    //   543: putfield 234	com/tencent/beacon/b/a$a:a	Ljava/lang/String;
    //   546: aload 30
    //   548: bipush 80
    //   550: putfield 237	com/tencent/beacon/b/a$a:b	I
    //   553: goto +283 -> 836
    //   556: astore 7
    //   558: aload 5
    //   560: ifnull +8 -> 568
    //   563: aload 5
    //   565: invokevirtual 213	java/io/OutputStream:close	()V
    //   568: aload 4
    //   570: ifnull +8 -> 578
    //   573: aload 4
    //   575: invokevirtual 214	java/io/InputStream:close	()V
    //   578: aload_3
    //   579: ifnull +7 -> 586
    //   582: aload_3
    //   583: invokevirtual 217	java/net/HttpURLConnection:disconnect	()V
    //   586: aload 7
    //   588: athrow
    //   589: aload 31
    //   591: ldc 246
    //   593: invokevirtual 229	java/lang/String:contains	(Ljava/lang/CharSequence;)Z
    //   596: ifeq +240 -> 836
    //   599: ldc 248
    //   601: iconst_0
    //   602: anewarray 4	java/lang/Object
    //   605: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   608: aload 30
    //   610: ldc 244
    //   612: putfield 234	com/tencent/beacon/b/a$a:a	Ljava/lang/String;
    //   615: aload 30
    //   617: bipush 80
    //   619: putfield 237	com/tencent/beacon/b/a$a:b	I
    //   622: goto +214 -> 836
    //   625: aload 30
    //   627: getfield 234	com/tencent/beacon/b/a$a:a	Ljava/lang/String;
    //   630: astore 13
    //   632: aload 30
    //   634: getfield 237	com/tencent/beacon/b/a$a:b	I
    //   637: istore 14
    //   639: aconst_null
    //   640: astore 15
    //   642: aconst_null
    //   643: astore 5
    //   645: aconst_null
    //   646: astore 4
    //   648: aconst_null
    //   649: astore_3
    //   650: aload 13
    //   652: ifnull -538 -> 114
    //   655: aload 13
    //   657: invokevirtual 147	java/lang/String:trim	()Ljava/lang/String;
    //   660: invokevirtual 150	java/lang/String:length	()I
    //   663: istore 16
    //   665: aconst_null
    //   666: astore 15
    //   668: aconst_null
    //   669: astore 5
    //   671: aconst_null
    //   672: astore 4
    //   674: aconst_null
    //   675: astore_3
    //   676: iload 16
    //   678: ifle -564 -> 114
    //   681: new 84	java/net/InetSocketAddress
    //   684: dup
    //   685: aload 13
    //   687: iload 14
    //   689: invokespecial 87	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   692: astore 15
    //   694: new 33	java/lang/StringBuilder
    //   697: dup
    //   698: ldc 250
    //   700: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   703: aload_1
    //   704: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   707: ldc 252
    //   709: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   712: aload 13
    //   714: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   717: ldc 123
    //   719: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   722: iload 14
    //   724: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   727: ldc 254
    //   729: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   732: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   735: iconst_0
    //   736: anewarray 4	java/lang/Object
    //   739: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   742: goto -628 -> 114
    //   745: aload 6
    //   747: new 256	java/net/Proxy
    //   750: dup
    //   751: getstatic 262	java/net/Proxy$Type:HTTP	Ljava/net/Proxy$Type;
    //   754: aload 15
    //   756: invokespecial 265	java/net/Proxy:<init>	(Ljava/net/Proxy$Type;Ljava/net/SocketAddress;)V
    //   759: invokevirtual 268	java/net/URL:openConnection	(Ljava/net/Proxy;)Ljava/net/URLConnection;
    //   762: checkcast 158	java/net/HttpURLConnection
    //   765: astore 29
    //   767: aload 29
    //   769: astore_3
    //   770: goto -634 -> 136
    //   773: astore 28
    //   775: aload 28
    //   777: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   780: goto -381 -> 399
    //   783: astore 27
    //   785: aload 27
    //   787: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   790: goto -381 -> 409
    //   793: astore 12
    //   795: aload 12
    //   797: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   800: goto -301 -> 499
    //   803: astore 11
    //   805: aload 11
    //   807: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   810: goto -301 -> 509
    //   813: astore 9
    //   815: aload 9
    //   817: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   820: goto -252 -> 568
    //   823: astore 8
    //   825: aload 8
    //   827: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   830: goto -252 -> 578
    //   833: aconst_null
    //   834: astore 30
    //   836: aload 30
    //   838: ifnonnull -213 -> 625
    //   841: aconst_null
    //   842: astore 15
    //   844: goto -730 -> 114
    //
    // Exception table:
    //   from	to	target	type
    //   31	51	470	java/lang/Throwable
    //   64	75	470	java/lang/Throwable
    //   80	89	470	java/lang/Throwable
    //   101	111	470	java/lang/Throwable
    //   127	136	470	java/lang/Throwable
    //   136	196	470	java/lang/Throwable
    //   196	389	470	java/lang/Throwable
    //   419	467	470	java/lang/Throwable
    //   520	553	470	java/lang/Throwable
    //   589	622	470	java/lang/Throwable
    //   625	639	470	java/lang/Throwable
    //   655	665	470	java/lang/Throwable
    //   681	742	470	java/lang/Throwable
    //   745	767	470	java/lang/Throwable
    //   31	51	556	finally
    //   64	75	556	finally
    //   80	89	556	finally
    //   101	111	556	finally
    //   127	136	556	finally
    //   136	196	556	finally
    //   196	389	556	finally
    //   419	467	556	finally
    //   472	489	556	finally
    //   520	553	556	finally
    //   589	622	556	finally
    //   625	639	556	finally
    //   655	665	556	finally
    //   681	742	556	finally
    //   745	767	556	finally
    //   394	399	773	java/io/IOException
    //   404	409	783	java/io/IOException
    //   494	499	793	java/io/IOException
    //   504	509	803	java/io/IOException
    //   563	568	813	java/io/IOException
    //   573	578	823	java/io/IOException
  }

  // ERROR //
  public static b a(String paramString1, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_0
    //   3: ifnull +38 -> 41
    //   6: aload_0
    //   7: ldc 21
    //   9: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: ifne +29 -> 41
    //   15: aload_1
    //   16: ifnull +25 -> 41
    //   19: aload_1
    //   20: ldc 21
    //   22: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   25: ifne +16 -> 41
    //   28: aload_2
    //   29: ifnull +12 -> 41
    //   32: aload_2
    //   33: ldc 21
    //   35: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   38: ifeq +9 -> 47
    //   41: aconst_null
    //   42: astore 4
    //   44: aload 4
    //   46: areturn
    //   47: new 131	com/tencent/beacon/b/a$b
    //   50: dup
    //   51: invokespecial 132	com/tencent/beacon/b/a$b:<init>	()V
    //   54: astore 4
    //   56: new 33	java/lang/StringBuilder
    //   59: dup
    //   60: ldc_w 271
    //   63: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   66: aload_1
    //   67: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   70: aload_2
    //   71: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: astore 5
    //   79: new 134	java/net/URL
    //   82: dup
    //   83: aload 5
    //   85: invokespecial 135	java/net/URL:<init>	(Ljava/lang/String;)V
    //   88: astore 6
    //   90: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   93: lstore 19
    //   95: aload_1
    //   96: invokestatic 277	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   99: astore 54
    //   101: aload 54
    //   103: astore 22
    //   105: aload 4
    //   107: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   110: lload 19
    //   112: lsub
    //   113: putfield 190	com/tencent/beacon/b/a$b:a	J
    //   116: new 33	java/lang/StringBuilder
    //   119: dup
    //   120: ldc_w 279
    //   123: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   126: aload 4
    //   128: getfield 190	com/tencent/beacon/b/a$b:a	J
    //   131: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   134: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   137: iconst_0
    //   138: anewarray 4	java/lang/Object
    //   141: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   144: new 281	java/lang/StringBuffer
    //   147: dup
    //   148: invokespecial 282	java/lang/StringBuffer:<init>	()V
    //   151: astore 23
    //   153: aload 22
    //   155: invokevirtual 286	java/net/InetAddress:getAddress	()[B
    //   158: astore 24
    //   160: iconst_0
    //   161: istore 25
    //   163: iload 25
    //   165: aload 24
    //   167: arraylength
    //   168: if_icmpge +164 -> 332
    //   171: new 33	java/lang/StringBuilder
    //   174: dup
    //   175: ldc_w 288
    //   178: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   181: astore 26
    //   183: aload 24
    //   185: iload 25
    //   187: baload
    //   188: ifge +134 -> 322
    //   191: sipush 256
    //   194: aload 24
    //   196: iload 25
    //   198: baload
    //   199: iadd
    //   200: istore 27
    //   202: aload 23
    //   204: aload 26
    //   206: iload 27
    //   208: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   211: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   214: invokevirtual 291	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   217: pop
    //   218: iinc 25 1
    //   221: goto -58 -> 163
    //   224: aload 4
    //   226: ldc2_w 75
    //   229: putfield 190	com/tencent/beacon/b/a$b:a	J
    //   232: goto -116 -> 116
    //   235: astore 13
    //   237: aconst_null
    //   238: astore 8
    //   240: aconst_null
    //   241: astore 9
    //   243: aconst_null
    //   244: astore 14
    //   246: aload 13
    //   248: astore 15
    //   250: aload 15
    //   252: invokevirtual 238	java/lang/Throwable:printStackTrace	()V
    //   255: aload 15
    //   257: invokevirtual 119	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   260: iconst_0
    //   261: anewarray 4	java/lang/Object
    //   264: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   267: aload 8
    //   269: ifnull +8 -> 277
    //   272: aload 8
    //   274: invokevirtual 213	java/io/OutputStream:close	()V
    //   277: aload 14
    //   279: ifnull +8 -> 287
    //   282: aload 14
    //   284: invokevirtual 214	java/io/InputStream:close	()V
    //   287: aload 9
    //   289: ifnull -245 -> 44
    //   292: aload 9
    //   294: invokevirtual 110	java/net/Socket:close	()V
    //   297: aload 4
    //   299: areturn
    //   300: astore 16
    //   302: aload 16
    //   304: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   307: aload 16
    //   309: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   312: iconst_0
    //   313: anewarray 4	java/lang/Object
    //   316: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   319: aload 4
    //   321: areturn
    //   322: aload 24
    //   324: iload 25
    //   326: baload
    //   327: istore 27
    //   329: goto -127 -> 202
    //   332: aload 4
    //   334: aload 23
    //   336: iconst_1
    //   337: invokevirtual 295	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   340: putfield 192	com/tencent/beacon/b/a$b:f	Ljava/lang/String;
    //   343: aload 23
    //   345: iconst_0
    //   346: invokevirtual 298	java/lang/StringBuffer:setLength	(I)V
    //   349: aload_0
    //   350: ldc 123
    //   352: invokevirtual 302	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   355: astore 29
    //   357: new 84	java/net/InetSocketAddress
    //   360: dup
    //   361: aload 29
    //   363: iconst_0
    //   364: aaload
    //   365: aload 29
    //   367: iconst_1
    //   368: aaload
    //   369: invokestatic 308	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   372: invokespecial 87	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   375: astore 30
    //   377: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   380: lstore 31
    //   382: new 89	java/net/Socket
    //   385: dup
    //   386: invokespecial 90	java/net/Socket:<init>	()V
    //   389: astore 9
    //   391: aload 9
    //   393: aload 30
    //   395: sipush 30000
    //   398: invokevirtual 94	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   401: aload 4
    //   403: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   406: lload 31
    //   408: lsub
    //   409: putfield 194	com/tencent/beacon/b/a$b:b	J
    //   412: new 33	java/lang/StringBuilder
    //   415: dup
    //   416: ldc_w 310
    //   419: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   422: aload 4
    //   424: getfield 194	com/tencent/beacon/b/a$b:b	J
    //   427: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   430: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   433: iconst_0
    //   434: anewarray 4	java/lang/Object
    //   437: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   440: new 33	java/lang/StringBuilder
    //   443: dup
    //   444: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   447: aload 6
    //   449: invokevirtual 313	java/net/URL:getPath	()Ljava/lang/String;
    //   452: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: astore 34
    //   457: aload 6
    //   459: invokevirtual 316	java/net/URL:getQuery	()Ljava/lang/String;
    //   462: ifnull +342 -> 804
    //   465: new 33	java/lang/StringBuilder
    //   468: dup
    //   469: ldc_w 318
    //   472: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   475: aload 6
    //   477: invokevirtual 316	java/net/URL:getQuery	()Ljava/lang/String;
    //   480: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   483: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   486: astore 35
    //   488: aload 34
    //   490: aload 35
    //   492: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   498: astore 36
    //   500: new 33	java/lang/StringBuilder
    //   503: dup
    //   504: ldc_w 320
    //   507: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   510: aload 36
    //   512: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: ldc_w 322
    //   518: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   521: aload_1
    //   522: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: ldc_w 324
    //   528: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   531: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   534: astore 37
    //   536: ldc_w 326
    //   539: new 33	java/lang/StringBuilder
    //   542: dup
    //   543: ldc_w 328
    //   546: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   549: aload 37
    //   551: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   554: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   557: invokestatic 334	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;)I
    //   560: pop
    //   561: aload 9
    //   563: invokevirtual 335	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   566: astore 39
    //   568: aload 39
    //   570: astore 8
    //   572: aload 9
    //   574: invokevirtual 336	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   577: astore 41
    //   579: aload 41
    //   581: astore 14
    //   583: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   586: lstore 42
    //   588: aload 8
    //   590: aload 37
    //   592: ldc_w 338
    //   595: invokevirtual 342	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   598: invokevirtual 346	java/io/OutputStream:write	([B)V
    //   601: aload 8
    //   603: invokevirtual 349	java/io/OutputStream:flush	()V
    //   606: aload 4
    //   608: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   611: lload 42
    //   613: lsub
    //   614: putfield 196	com/tencent/beacon/b/a$b:c	J
    //   617: new 33	java/lang/StringBuilder
    //   620: dup
    //   621: ldc_w 351
    //   624: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   627: aload 4
    //   629: getfield 196	com/tencent/beacon/b/a$b:c	J
    //   632: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   635: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   638: iconst_0
    //   639: anewarray 4	java/lang/Object
    //   642: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   645: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   648: lstore 44
    //   650: aload 14
    //   652: invokevirtual 187	java/io/InputStream:read	()I
    //   655: pop
    //   656: aload 4
    //   658: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   661: lload 44
    //   663: lsub
    //   664: putfield 198	com/tencent/beacon/b/a$b:d	J
    //   667: new 33	java/lang/StringBuilder
    //   670: dup
    //   671: ldc_w 353
    //   674: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   677: aload 4
    //   679: getfield 198	com/tencent/beacon/b/a$b:d	J
    //   682: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   685: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   688: iconst_0
    //   689: anewarray 4	java/lang/Object
    //   692: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   695: sipush 500
    //   698: newarray byte
    //   700: astore 47
    //   702: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   705: lstore 48
    //   707: aload 14
    //   709: aload 47
    //   711: invokevirtual 356	java/io/InputStream:read	([B)I
    //   714: pop
    //   715: aload 4
    //   717: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   720: lload 48
    //   722: lsub
    //   723: putfield 200	com/tencent/beacon/b/a$b:e	J
    //   726: new 33	java/lang/StringBuilder
    //   729: dup
    //   730: ldc_w 358
    //   733: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   736: aload 4
    //   738: getfield 200	com/tencent/beacon/b/a$b:e	J
    //   741: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   744: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   747: iconst_0
    //   748: anewarray 4	java/lang/Object
    //   751: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   754: aload 8
    //   756: ifnull +8 -> 764
    //   759: aload 8
    //   761: invokevirtual 213	java/io/OutputStream:close	()V
    //   764: aload 14
    //   766: ifnull +8 -> 774
    //   769: aload 14
    //   771: invokevirtual 214	java/io/InputStream:close	()V
    //   774: aload 9
    //   776: invokevirtual 110	java/net/Socket:close	()V
    //   779: aload 4
    //   781: areturn
    //   782: astore 51
    //   784: aload 51
    //   786: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   789: aload 51
    //   791: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   794: iconst_0
    //   795: anewarray 4	java/lang/Object
    //   798: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   801: aload 4
    //   803: areturn
    //   804: ldc 21
    //   806: astore 35
    //   808: goto -320 -> 488
    //   811: astore 53
    //   813: aload 53
    //   815: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   818: goto -54 -> 764
    //   821: astore 52
    //   823: aload 52
    //   825: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   828: goto -54 -> 774
    //   831: astore 18
    //   833: aload 18
    //   835: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   838: goto -561 -> 277
    //   841: astore 17
    //   843: aload 17
    //   845: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   848: goto -561 -> 287
    //   851: astore 7
    //   853: aconst_null
    //   854: astore 8
    //   856: aconst_null
    //   857: astore 9
    //   859: aload 8
    //   861: ifnull +8 -> 869
    //   864: aload 8
    //   866: invokevirtual 213	java/io/OutputStream:close	()V
    //   869: aload_3
    //   870: ifnull +7 -> 877
    //   873: aload_3
    //   874: invokevirtual 214	java/io/InputStream:close	()V
    //   877: aload 9
    //   879: ifnull +8 -> 887
    //   882: aload 9
    //   884: invokevirtual 110	java/net/Socket:close	()V
    //   887: aload 7
    //   889: athrow
    //   890: astore 12
    //   892: aload 12
    //   894: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   897: goto -28 -> 869
    //   900: astore 11
    //   902: aload 11
    //   904: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   907: goto -30 -> 877
    //   910: astore 10
    //   912: aload 10
    //   914: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   917: aload 10
    //   919: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   922: iconst_0
    //   923: anewarray 4	java/lang/Object
    //   926: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   929: goto -42 -> 887
    //   932: astore 7
    //   934: aconst_null
    //   935: astore_3
    //   936: aconst_null
    //   937: astore 8
    //   939: goto -80 -> 859
    //   942: astore 7
    //   944: aconst_null
    //   945: astore_3
    //   946: goto -87 -> 859
    //   949: astore 7
    //   951: aload 14
    //   953: astore_3
    //   954: goto -95 -> 859
    //   957: astore 33
    //   959: aload 33
    //   961: astore 15
    //   963: aconst_null
    //   964: astore 14
    //   966: aconst_null
    //   967: astore 8
    //   969: goto -719 -> 250
    //   972: astore 40
    //   974: aload 40
    //   976: astore 15
    //   978: aconst_null
    //   979: astore 14
    //   981: goto -731 -> 250
    //   984: astore 15
    //   986: goto -736 -> 250
    //   989: astore 55
    //   991: goto -767 -> 224
    //   994: astore 21
    //   996: aconst_null
    //   997: astore 22
    //   999: goto -775 -> 224
    //
    // Exception table:
    //   from	to	target	type
    //   79	95	235	java/lang/Throwable
    //   95	101	235	java/lang/Throwable
    //   105	116	235	java/lang/Throwable
    //   116	160	235	java/lang/Throwable
    //   163	202	235	java/lang/Throwable
    //   202	218	235	java/lang/Throwable
    //   224	232	235	java/lang/Throwable
    //   322	329	235	java/lang/Throwable
    //   332	391	235	java/lang/Throwable
    //   292	297	300	java/io/IOException
    //   774	779	782	java/io/IOException
    //   759	764	811	java/io/IOException
    //   769	774	821	java/io/IOException
    //   272	277	831	java/io/IOException
    //   282	287	841	java/io/IOException
    //   79	95	851	finally
    //   95	101	851	finally
    //   105	116	851	finally
    //   116	160	851	finally
    //   163	202	851	finally
    //   202	218	851	finally
    //   224	232	851	finally
    //   322	329	851	finally
    //   332	391	851	finally
    //   864	869	890	java/io/IOException
    //   873	877	900	java/io/IOException
    //   882	887	910	java/io/IOException
    //   391	488	932	finally
    //   488	568	932	finally
    //   572	579	942	finally
    //   250	267	949	finally
    //   583	754	949	finally
    //   391	488	957	java/lang/Throwable
    //   488	568	957	java/lang/Throwable
    //   572	579	972	java/lang/Throwable
    //   583	754	984	java/lang/Throwable
    //   105	116	989	java/lang/Exception
    //   95	101	994	java/lang/Exception
  }

  // ERROR //
  public static b a(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: ifnull +12 -> 15
    //   6: aload_0
    //   7: ldc 21
    //   9: invokevirtual 69	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   12: ifeq +7 -> 19
    //   15: aconst_null
    //   16: astore_3
    //   17: aload_3
    //   18: areturn
    //   19: new 131	com/tencent/beacon/b/a$b
    //   22: dup
    //   23: invokespecial 132	com/tencent/beacon/b/a$b:<init>	()V
    //   26: astore_3
    //   27: new 134	java/net/URL
    //   30: dup
    //   31: aload_0
    //   32: invokespecial 135	java/net/URL:<init>	(Ljava/lang/String;)V
    //   35: astore 4
    //   37: aload 4
    //   39: invokevirtual 362	java/net/URL:getHost	()Ljava/lang/String;
    //   42: astore 17
    //   44: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   47: lstore 18
    //   49: aload 17
    //   51: invokestatic 277	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   54: astore 20
    //   56: aload_3
    //   57: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   60: lload 18
    //   62: lsub
    //   63: putfield 190	com/tencent/beacon/b/a$b:a	J
    //   66: new 33	java/lang/StringBuilder
    //   69: dup
    //   70: ldc_w 364
    //   73: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   76: aload_3
    //   77: getfield 190	com/tencent/beacon/b/a$b:a	J
    //   80: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   83: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   86: iconst_0
    //   87: anewarray 4	java/lang/Object
    //   90: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   93: new 281	java/lang/StringBuffer
    //   96: dup
    //   97: invokespecial 282	java/lang/StringBuffer:<init>	()V
    //   100: astore 21
    //   102: aload 20
    //   104: invokevirtual 286	java/net/InetAddress:getAddress	()[B
    //   107: astore 22
    //   109: iconst_0
    //   110: istore 23
    //   112: iload 23
    //   114: aload 22
    //   116: arraylength
    //   117: if_icmpge +66 -> 183
    //   120: new 33	java/lang/StringBuilder
    //   123: dup
    //   124: ldc_w 288
    //   127: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   130: astore 24
    //   132: aload 22
    //   134: iload 23
    //   136: baload
    //   137: ifge +36 -> 173
    //   140: sipush 256
    //   143: aload 22
    //   145: iload 23
    //   147: baload
    //   148: iadd
    //   149: istore 25
    //   151: aload 21
    //   153: aload 24
    //   155: iload 25
    //   157: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   160: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   163: invokevirtual 291	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   166: pop
    //   167: iinc 23 1
    //   170: goto -58 -> 112
    //   173: aload 22
    //   175: iload 23
    //   177: baload
    //   178: istore 25
    //   180: goto -29 -> 151
    //   183: aload_3
    //   184: aload 21
    //   186: iconst_1
    //   187: invokevirtual 295	java/lang/StringBuffer:substring	(I)Ljava/lang/String;
    //   190: putfield 192	com/tencent/beacon/b/a$b:f	Ljava/lang/String;
    //   193: aload 21
    //   195: iconst_0
    //   196: invokevirtual 298	java/lang/StringBuffer:setLength	(I)V
    //   199: iload_1
    //   200: ifne -183 -> 17
    //   203: aload 4
    //   205: invokevirtual 367	java/net/URL:getPort	()I
    //   208: istore 27
    //   210: iload 27
    //   212: iflt +435 -> 647
    //   215: new 84	java/net/InetSocketAddress
    //   218: dup
    //   219: aload 20
    //   221: iload 27
    //   223: invokespecial 370	java/net/InetSocketAddress:<init>	(Ljava/net/InetAddress;I)V
    //   226: astore 28
    //   228: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   231: lstore 29
    //   233: new 89	java/net/Socket
    //   236: dup
    //   237: invokespecial 90	java/net/Socket:<init>	()V
    //   240: astore 7
    //   242: aload 7
    //   244: aload 28
    //   246: sipush 30000
    //   249: invokevirtual 94	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   252: aload_3
    //   253: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   256: lload 29
    //   258: lsub
    //   259: putfield 194	com/tencent/beacon/b/a$b:b	J
    //   262: new 33	java/lang/StringBuilder
    //   265: dup
    //   266: ldc_w 310
    //   269: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   272: aload_3
    //   273: getfield 194	com/tencent/beacon/b/a$b:b	J
    //   276: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   279: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   282: iconst_0
    //   283: anewarray 4	java/lang/Object
    //   286: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   289: new 33	java/lang/StringBuilder
    //   292: dup
    //   293: invokespecial 34	java/lang/StringBuilder:<init>	()V
    //   296: aload 4
    //   298: invokevirtual 313	java/net/URL:getPath	()Ljava/lang/String;
    //   301: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   304: astore 32
    //   306: aload 4
    //   308: invokevirtual 316	java/net/URL:getQuery	()Ljava/lang/String;
    //   311: ifnull +343 -> 654
    //   314: new 33	java/lang/StringBuilder
    //   317: dup
    //   318: ldc_w 318
    //   321: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   324: aload 4
    //   326: invokevirtual 316	java/net/URL:getQuery	()Ljava/lang/String;
    //   329: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   335: astore 33
    //   337: aload 32
    //   339: aload 33
    //   341: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   344: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   347: astore 34
    //   349: new 33	java/lang/StringBuilder
    //   352: dup
    //   353: ldc_w 320
    //   356: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   359: aload 34
    //   361: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: ldc_w 322
    //   367: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   370: aload 17
    //   372: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc_w 324
    //   378: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   384: astore 35
    //   386: aload 7
    //   388: invokevirtual 335	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   391: astore 36
    //   393: aload 36
    //   395: astore 6
    //   397: aload 7
    //   399: invokevirtual 336	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   402: astore 38
    //   404: aload 38
    //   406: astore 12
    //   408: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   411: lstore 39
    //   413: aload 6
    //   415: aload 35
    //   417: ldc_w 338
    //   420: invokevirtual 342	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   423: invokevirtual 346	java/io/OutputStream:write	([B)V
    //   426: aload 6
    //   428: invokevirtual 349	java/io/OutputStream:flush	()V
    //   431: aload_3
    //   432: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   435: lload 39
    //   437: lsub
    //   438: putfield 196	com/tencent/beacon/b/a$b:c	J
    //   441: new 33	java/lang/StringBuilder
    //   444: dup
    //   445: ldc_w 351
    //   448: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   451: aload_3
    //   452: getfield 196	com/tencent/beacon/b/a$b:c	J
    //   455: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   458: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   461: iconst_0
    //   462: anewarray 4	java/lang/Object
    //   465: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   468: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   471: lstore 41
    //   473: aload 12
    //   475: invokevirtual 187	java/io/InputStream:read	()I
    //   478: pop
    //   479: aload_3
    //   480: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   483: lload 41
    //   485: lsub
    //   486: putfield 198	com/tencent/beacon/b/a$b:d	J
    //   489: new 33	java/lang/StringBuilder
    //   492: dup
    //   493: ldc_w 353
    //   496: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   499: aload_3
    //   500: getfield 198	com/tencent/beacon/b/a$b:d	J
    //   503: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   506: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   509: iconst_0
    //   510: anewarray 4	java/lang/Object
    //   513: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   516: sipush 500
    //   519: newarray byte
    //   521: astore 44
    //   523: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   526: lstore 45
    //   528: aload 12
    //   530: aload 44
    //   532: invokevirtual 356	java/io/InputStream:read	([B)I
    //   535: istore 47
    //   537: aload_3
    //   538: invokestatic 82	java/lang/System:currentTimeMillis	()J
    //   541: lload 45
    //   543: lsub
    //   544: putfield 200	com/tencent/beacon/b/a$b:e	J
    //   547: new 33	java/lang/StringBuilder
    //   550: dup
    //   551: ldc_w 358
    //   554: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   557: aload_3
    //   558: getfield 200	com/tencent/beacon/b/a$b:e	J
    //   561: invokevirtual 102	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   564: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   567: iconst_0
    //   568: anewarray 4	java/lang/Object
    //   571: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   574: new 33	java/lang/StringBuilder
    //   577: dup
    //   578: ldc_w 372
    //   581: invokespecial 99	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   584: iload 47
    //   586: invokevirtual 126	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   589: invokevirtual 50	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   592: iconst_0
    //   593: anewarray 4	java/lang/Object
    //   596: invokestatic 107	com/tencent/beacon/d/a:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   599: aload 6
    //   601: ifnull +8 -> 609
    //   604: aload 6
    //   606: invokevirtual 213	java/io/OutputStream:close	()V
    //   609: aload 12
    //   611: ifnull +8 -> 619
    //   614: aload 12
    //   616: invokevirtual 214	java/io/InputStream:close	()V
    //   619: aload 7
    //   621: invokevirtual 110	java/net/Socket:close	()V
    //   624: aload_3
    //   625: areturn
    //   626: astore 48
    //   628: aload 48
    //   630: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   633: aload 48
    //   635: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   638: iconst_0
    //   639: anewarray 4	java/lang/Object
    //   642: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   645: aload_3
    //   646: areturn
    //   647: bipush 80
    //   649: istore 27
    //   651: goto -436 -> 215
    //   654: ldc 21
    //   656: astore 33
    //   658: goto -321 -> 337
    //   661: astore 50
    //   663: aload 50
    //   665: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   668: goto -59 -> 609
    //   671: astore 49
    //   673: aload 49
    //   675: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   678: goto -59 -> 619
    //   681: astore 11
    //   683: aconst_null
    //   684: astore 6
    //   686: aconst_null
    //   687: astore 7
    //   689: aconst_null
    //   690: astore 12
    //   692: aload 11
    //   694: astore 13
    //   696: aload 13
    //   698: invokevirtual 238	java/lang/Throwable:printStackTrace	()V
    //   701: aload 13
    //   703: invokevirtual 119	java/lang/Throwable:getMessage	()Ljava/lang/String;
    //   706: iconst_0
    //   707: anewarray 4	java/lang/Object
    //   710: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   713: aload 6
    //   715: ifnull +8 -> 723
    //   718: aload 6
    //   720: invokevirtual 213	java/io/OutputStream:close	()V
    //   723: aload 12
    //   725: ifnull +8 -> 733
    //   728: aload 12
    //   730: invokevirtual 214	java/io/InputStream:close	()V
    //   733: aload 7
    //   735: ifnull -718 -> 17
    //   738: aload 7
    //   740: invokevirtual 110	java/net/Socket:close	()V
    //   743: aload_3
    //   744: areturn
    //   745: astore 14
    //   747: aload 14
    //   749: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   752: aload 14
    //   754: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   757: iconst_0
    //   758: anewarray 4	java/lang/Object
    //   761: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   764: aload_3
    //   765: areturn
    //   766: astore 16
    //   768: aload 16
    //   770: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   773: goto -50 -> 723
    //   776: astore 15
    //   778: aload 15
    //   780: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   783: goto -50 -> 733
    //   786: astore 5
    //   788: aconst_null
    //   789: astore 6
    //   791: aconst_null
    //   792: astore 7
    //   794: aload 6
    //   796: ifnull +8 -> 804
    //   799: aload 6
    //   801: invokevirtual 213	java/io/OutputStream:close	()V
    //   804: aload_2
    //   805: ifnull +7 -> 812
    //   808: aload_2
    //   809: invokevirtual 214	java/io/InputStream:close	()V
    //   812: aload 7
    //   814: ifnull +8 -> 822
    //   817: aload 7
    //   819: invokevirtual 110	java/net/Socket:close	()V
    //   822: aload 5
    //   824: athrow
    //   825: astore 10
    //   827: aload 10
    //   829: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   832: goto -28 -> 804
    //   835: astore 9
    //   837: aload 9
    //   839: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   842: goto -30 -> 812
    //   845: astore 8
    //   847: aload 8
    //   849: invokevirtual 113	java/io/IOException:printStackTrace	()V
    //   852: aload 8
    //   854: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   857: iconst_0
    //   858: anewarray 4	java/lang/Object
    //   861: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   864: goto -42 -> 822
    //   867: astore 5
    //   869: aconst_null
    //   870: astore_2
    //   871: aconst_null
    //   872: astore 6
    //   874: goto -80 -> 794
    //   877: astore 5
    //   879: aconst_null
    //   880: astore_2
    //   881: goto -87 -> 794
    //   884: astore 5
    //   886: aload 12
    //   888: astore_2
    //   889: goto -95 -> 794
    //   892: astore 31
    //   894: aload 31
    //   896: astore 13
    //   898: aconst_null
    //   899: astore 12
    //   901: aconst_null
    //   902: astore 6
    //   904: goto -208 -> 696
    //   907: astore 37
    //   909: aload 37
    //   911: astore 13
    //   913: aconst_null
    //   914: astore 12
    //   916: goto -220 -> 696
    //   919: astore 13
    //   921: goto -225 -> 696
    //
    // Exception table:
    //   from	to	target	type
    //   619	624	626	java/io/IOException
    //   604	609	661	java/io/IOException
    //   614	619	671	java/io/IOException
    //   27	109	681	java/lang/Throwable
    //   112	151	681	java/lang/Throwable
    //   151	167	681	java/lang/Throwable
    //   173	180	681	java/lang/Throwable
    //   183	199	681	java/lang/Throwable
    //   203	210	681	java/lang/Throwable
    //   215	242	681	java/lang/Throwable
    //   738	743	745	java/io/IOException
    //   718	723	766	java/io/IOException
    //   728	733	776	java/io/IOException
    //   27	109	786	finally
    //   112	151	786	finally
    //   151	167	786	finally
    //   173	180	786	finally
    //   183	199	786	finally
    //   203	210	786	finally
    //   215	242	786	finally
    //   799	804	825	java/io/IOException
    //   808	812	835	java/io/IOException
    //   817	822	845	java/io/IOException
    //   242	337	867	finally
    //   337	393	867	finally
    //   397	404	877	finally
    //   408	599	884	finally
    //   696	713	884	finally
    //   242	337	892	java/lang/Throwable
    //   337	393	892	java/lang/Throwable
    //   397	404	907	java/lang/Throwable
    //   408	599	919	java/lang/Throwable
  }

  public static a a(Context paramContext)
  {
    if (f == null)
      f = new a(paramContext);
    return f;
  }

  public String a()
  {
    if ((this.a == null) || ("".equals(this.a)))
      return this.b;
    return this.a;
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public String b()
  {
    return this.b;
  }

  public String c()
  {
    return this.c;
  }

  public String d()
  {
    return this.d;
  }

  public String e()
  {
    return this.e;
  }

  static final class a
  {
    public String a = "";
    public int b = -1;
  }

  public static final class b
  {
    public long a = -1L;
    public long b = -1L;
    public long c = -1L;
    public long d = -1L;
    public long e = -1L;
    public String f = "";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.b.a
 * JD-Core Version:    0.6.0
 */