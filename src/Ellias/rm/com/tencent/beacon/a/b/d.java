package com.tencent.beacon.a.b;

import android.content.Context;

public final class d
  implements Runnable
{
  private static long a = 0L;
  private Context b = null;

  public d(Context paramContext)
  {
    this.b = paramContext;
  }

  private static long a()
  {
    monitorenter;
    try
    {
      long l = a;
      monitorexit;
      return l;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static void a(long paramLong)
  {
    monitorenter;
    try
    {
      a = paramLong;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   4: bipush 101
    //   6: invokestatic 34	com/tencent/beacon/applog/a:a	(Landroid/content/Context;I)Lcom/tencent/beacon/a/b/h;
    //   9: astore_1
    //   10: aload_1
    //   11: ifnull +59 -> 70
    //   14: aload_1
    //   15: invokevirtual 39	com/tencent/beacon/a/b/h:b	()I
    //   18: bipush 101
    //   20: if_icmpne +50 -> 70
    //   23: aload_1
    //   24: invokevirtual 43	com/tencent/beacon/a/b/h:c	()[B
    //   27: ifnull +43 -> 70
    //   30: aload_0
    //   31: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   34: invokestatic 48	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   37: invokevirtual 51	com/tencent/beacon/a/b/c:c	()Lcom/tencent/beacon/upload/f;
    //   40: astore 35
    //   42: aload 35
    //   44: ifnull +26 -> 70
    //   47: aload 35
    //   49: bipush 101
    //   51: aload_1
    //   52: invokevirtual 43	com/tencent/beacon/a/b/h:c	()[B
    //   55: iconst_0
    //   56: invokeinterface 56 4 0
    //   61: ldc 58
    //   63: iconst_0
    //   64: anewarray 4	java/lang/Object
    //   67: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   70: aload_0
    //   71: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   74: invokestatic 48	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   77: astore_2
    //   78: aload_2
    //   79: invokevirtual 67	com/tencent/beacon/a/b/c:f	()I
    //   82: ifne +220 -> 302
    //   85: ldc 69
    //   87: iconst_0
    //   88: anewarray 4	java/lang/Object
    //   91: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   94: aload_2
    //   95: iconst_1
    //   96: invokevirtual 72	com/tencent/beacon/a/b/c:a	(I)V
    //   99: aload_0
    //   100: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   103: invokestatic 78	com/tencent/beacon/a/a:d	(Landroid/content/Context;)Z
    //   106: istore 21
    //   108: iconst_1
    //   109: anewarray 4	java/lang/Object
    //   112: astore 22
    //   114: aload 22
    //   116: iconst_0
    //   117: iload 21
    //   119: invokestatic 84	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   122: aastore
    //   123: ldc 86
    //   125: aload 22
    //   127: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   130: invokestatic 92	com/tencent/beacon/a/d:m	()Lcom/tencent/beacon/a/d;
    //   133: pop
    //   134: iload 21
    //   136: ifeq +127 -> 263
    //   139: aload_0
    //   140: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   143: aconst_null
    //   144: ldc2_w 93
    //   147: ldc2_w 95
    //   150: invokestatic 101	com/tencent/beacon/a/a/a:a	(Landroid/content/Context;[IJJ)I
    //   153: istore 28
    //   155: iconst_1
    //   156: anewarray 4	java/lang/Object
    //   159: astore 29
    //   161: aload 29
    //   163: iconst_0
    //   164: iload 28
    //   166: invokestatic 106	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   169: aastore
    //   170: ldc 108
    //   172: aload 29
    //   174: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   177: aload_2
    //   178: monitorenter
    //   179: ldc 110
    //   181: iconst_0
    //   182: anewarray 4	java/lang/Object
    //   185: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   188: aload_2
    //   189: invokevirtual 113	com/tencent/beacon/a/b/c:e	()[Lcom/tencent/beacon/a/b/b;
    //   192: astore 31
    //   194: aload_2
    //   195: iconst_1
    //   196: invokevirtual 116	com/tencent/beacon/a/b/c:a	(Z)V
    //   199: aload_2
    //   200: monitorexit
    //   201: aload 31
    //   203: ifnull +99 -> 302
    //   206: ldc 118
    //   208: iconst_0
    //   209: anewarray 4	java/lang/Object
    //   212: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   215: aload 31
    //   217: arraylength
    //   218: istore 32
    //   220: iconst_0
    //   221: istore 33
    //   223: iload 33
    //   225: iload 32
    //   227: if_icmpge +75 -> 302
    //   230: aload 31
    //   232: iload 33
    //   234: aaload
    //   235: invokeinterface 122 1 0
    //   240: iinc 33 1
    //   243: goto -20 -> 223
    //   246: astore 34
    //   248: aload 34
    //   250: invokevirtual 125	java/lang/Throwable:printStackTrace	()V
    //   253: goto -183 -> 70
    //   256: astore 30
    //   258: aload_2
    //   259: monitorexit
    //   260: aload 30
    //   262: athrow
    //   263: invokestatic 127	com/tencent/beacon/a/b/d:a	()J
    //   266: lstore 24
    //   268: lload 24
    //   270: lconst_0
    //   271: lcmp
    //   272: ifle +30 -> 302
    //   275: iconst_1
    //   276: anewarray 4	java/lang/Object
    //   279: astore 26
    //   281: aload 26
    //   283: iconst_0
    //   284: lload 24
    //   286: invokestatic 132	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   289: aastore
    //   290: ldc 134
    //   292: aload 26
    //   294: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   297: lload 24
    //   299: invokestatic 139	java/lang/Thread:sleep	(J)V
    //   302: aload_2
    //   303: monitorenter
    //   304: ldc 141
    //   306: iconst_0
    //   307: anewarray 4	java/lang/Object
    //   310: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   313: aload_2
    //   314: invokevirtual 113	com/tencent/beacon/a/b/c:e	()[Lcom/tencent/beacon/a/b/b;
    //   317: astore 4
    //   319: aload_2
    //   320: iconst_2
    //   321: invokevirtual 72	com/tencent/beacon/a/b/c:a	(I)V
    //   324: aload_2
    //   325: monitorexit
    //   326: aload 4
    //   328: ifnull +58 -> 386
    //   331: ldc 143
    //   333: iconst_0
    //   334: anewarray 4	java/lang/Object
    //   337: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   340: aload 4
    //   342: arraylength
    //   343: istore 19
    //   345: iconst_0
    //   346: istore 20
    //   348: iload 20
    //   350: iload 19
    //   352: if_icmpge +34 -> 386
    //   355: aload 4
    //   357: iload 20
    //   359: aaload
    //   360: invokeinterface 145 1 0
    //   365: iinc 20 1
    //   368: goto -20 -> 348
    //   371: astore 27
    //   373: aload 27
    //   375: invokevirtual 146	java/lang/InterruptedException:printStackTrace	()V
    //   378: goto -76 -> 302
    //   381: astore_3
    //   382: aload_2
    //   383: monitorexit
    //   384: aload_3
    //   385: athrow
    //   386: invokestatic 149	com/tencent/beacon/a/b/c:a	()Lcom/tencent/beacon/upload/g;
    //   389: astore 5
    //   391: iconst_0
    //   392: istore 6
    //   394: aload 5
    //   396: ifnonnull +36 -> 432
    //   399: iinc 6 1
    //   402: iload 6
    //   404: iconst_5
    //   405: if_icmpge +27 -> 432
    //   408: ldc2_w 150
    //   411: invokestatic 139	java/lang/Thread:sleep	(J)V
    //   414: invokestatic 149	com/tencent/beacon/a/b/c:a	()Lcom/tencent/beacon/upload/g;
    //   417: astore 5
    //   419: goto -25 -> 394
    //   422: astore 18
    //   424: aload 18
    //   426: invokevirtual 146	java/lang/InterruptedException:printStackTrace	()V
    //   429: goto -15 -> 414
    //   432: aload 5
    //   434: ifnull +162 -> 596
    //   437: aload 5
    //   439: new 153	com/tencent/beacon/upload/b
    //   442: dup
    //   443: aload_0
    //   444: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   447: iconst_0
    //   448: bipush 100
    //   450: invokespecial 156	com/tencent/beacon/upload/b:<init>	(Landroid/content/Context;II)V
    //   453: invokeinterface 161 2 0
    //   458: aload_0
    //   459: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   462: ldc 163
    //   464: ldc 165
    //   466: invokestatic 168	com/tencent/beacon/a/a:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   469: astore 16
    //   471: invokestatic 174	com/tencent/beacon/a/e:k	()Ljava/lang/String;
    //   474: aload 16
    //   476: invokevirtual 180	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   479: ifne +34 -> 513
    //   482: aload 5
    //   484: new 182	com/tencent/beacon/upload/c
    //   487: dup
    //   488: aload_0
    //   489: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   492: invokespecial 184	com/tencent/beacon/upload/c:<init>	(Landroid/content/Context;)V
    //   495: invokeinterface 161 2 0
    //   500: aload_0
    //   501: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   504: ldc 163
    //   506: invokestatic 174	com/tencent/beacon/a/e:k	()Ljava/lang/String;
    //   509: invokestatic 187	com/tencent/beacon/a/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   512: pop
    //   513: ldc 189
    //   515: iconst_0
    //   516: anewarray 4	java/lang/Object
    //   519: invokestatic 192	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   522: aload_2
    //   523: monitorenter
    //   524: aload_2
    //   525: invokevirtual 113	com/tencent/beacon/a/b/c:e	()[Lcom/tencent/beacon/a/b/b;
    //   528: astore 8
    //   530: aload_2
    //   531: iconst_3
    //   532: invokevirtual 72	com/tencent/beacon/a/b/c:a	(I)V
    //   535: aload_2
    //   536: monitorexit
    //   537: aload 8
    //   539: ifnull +76 -> 615
    //   542: ldc 194
    //   544: iconst_0
    //   545: anewarray 4	java/lang/Object
    //   548: invokestatic 64	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   551: aload 8
    //   553: arraylength
    //   554: istore 13
    //   556: iconst_0
    //   557: istore 14
    //   559: iload 14
    //   561: iload 13
    //   563: if_icmpge +52 -> 615
    //   566: aload 8
    //   568: iload 14
    //   570: aaload
    //   571: invokeinterface 196 1 0
    //   576: iinc 14 1
    //   579: goto -20 -> 559
    //   582: astore 15
    //   584: ldc 198
    //   586: iconst_0
    //   587: anewarray 4	java/lang/Object
    //   590: invokestatic 200	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   593: goto -80 -> 513
    //   596: ldc 202
    //   598: iconst_0
    //   599: anewarray 4	java/lang/Object
    //   602: invokestatic 192	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   605: goto -92 -> 513
    //   608: astore 7
    //   610: aload_2
    //   611: monitorexit
    //   612: aload 7
    //   614: athrow
    //   615: aload_0
    //   616: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   619: invokestatic 48	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   622: invokevirtual 205	com/tencent/beacon/a/b/c:b	()Lcom/tencent/beacon/a/b/e;
    //   625: astore 9
    //   627: aload 9
    //   629: ifnonnull +13 -> 642
    //   632: ldc 207
    //   634: iconst_0
    //   635: anewarray 4	java/lang/Object
    //   638: invokestatic 209	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   641: return
    //   642: ldc 210
    //   644: aload 9
    //   646: invokevirtual 214	com/tencent/beacon/a/b/e:c	()I
    //   649: imul
    //   650: i2l
    //   651: lstore 10
    //   653: lload 10
    //   655: lconst_0
    //   656: lcmp
    //   657: ifle +46 -> 703
    //   660: invokestatic 219	com/tencent/beacon/a/c:a	()Lcom/tencent/beacon/a/c;
    //   663: aload_0
    //   664: lload 10
    //   666: invokevirtual 222	com/tencent/beacon/a/c:a	(Ljava/lang/Runnable;J)V
    //   669: iconst_1
    //   670: anewarray 4	java/lang/Object
    //   673: astore 12
    //   675: aload 12
    //   677: iconst_0
    //   678: lload 10
    //   680: invokestatic 132	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   683: aastore
    //   684: ldc 224
    //   686: aload 12
    //   688: invokestatic 192	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   691: aload_0
    //   692: getfield 20	com/tencent/beacon/a/b/d:b	Landroid/content/Context;
    //   695: invokestatic 48	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   698: iconst_4
    //   699: invokevirtual 72	com/tencent/beacon/a/b/c:a	(I)V
    //   702: return
    //   703: ldc 226
    //   705: iconst_0
    //   706: anewarray 4	java/lang/Object
    //   709: invokestatic 192	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   712: return
    //
    // Exception table:
    //   from	to	target	type
    //   30	42	246	java/lang/Throwable
    //   47	70	246	java/lang/Throwable
    //   179	201	256	finally
    //   297	302	371	java/lang/InterruptedException
    //   304	326	381	finally
    //   408	414	422	java/lang/InterruptedException
    //   458	513	582	java/lang/Exception
    //   524	537	608	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.a.b.d
 * JD-Core Version:    0.6.0
 */