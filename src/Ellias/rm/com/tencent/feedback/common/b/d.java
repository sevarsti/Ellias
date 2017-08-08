package com.tencent.feedback.common.b;

import android.content.Context;
import com.tencent.feedback.upload.f;
import common.MixPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import userinfo.UserInfoPackage;

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

  private void b()
  {
    byte b1 = 1;
    f localf1 = c.a();
    h localh = h.a(this.b, com.tencent.feedback.common.b.b(), localf1);
    boolean bool = localh.b();
    if (!bool)
      localh.a(60000L);
    f localf2 = localf1;
    int i = 0;
    while (true)
      if (localf2 == null)
      {
        i++;
        if (i < 5)
          try
          {
            Thread.sleep(200L);
            localf2 = c.a();
          }
          catch (InterruptedException localInterruptedException)
          {
            while (true)
              localInterruptedException.printStackTrace();
          }
      }
    if (localf2 != null);
    while (true)
    {
      try
      {
        Context localContext = this.b;
        if (!bool)
          continue;
        b1 = 2;
        localUserInfoPackage = j.a(localContext, localh, b1);
        if (localUserInfoPackage == null)
          break label323;
        HashMap localHashMap = new HashMap();
        localHashMap.put(Integer.valueOf(110), localUserInfoPackage.toByteArray());
        localMixPackage = new MixPackage();
        localMixPackage.mixMap = localHashMap;
        Object[] arrayOfObject = new Object[1];
        if (localUserInfoPackage.list != null)
          continue;
        j = 0;
        arrayOfObject[0] = Integer.valueOf(j);
        com.tencent.feedback.common.e.b("rqdp{ common query add uin} %d", arrayOfObject);
        localf2.a(new e(this.b, 1111, 200, localMixPackage));
      }
      catch (Exception localException1)
      {
        try
        {
          UserInfoPackage localUserInfoPackage;
          String str = com.tencent.feedback.common.a.b(this.b, "GEN_QIMEI", "");
          if (com.tencent.feedback.anr.a.g().equals(str))
            continue;
          localf2.a(new com.tencent.feedback.upload.b(this.b));
          com.tencent.feedback.common.a.a(this.b, "GEN_QIMEI", com.tencent.feedback.anr.a.g());
          return;
          int k = localUserInfoPackage.list.size();
          int j = k;
          continue;
          localException1 = localException1;
          localException1.printStackTrace();
          localMixPackage = null;
        }
        catch (Exception localException2)
        {
          com.tencent.feedback.common.e.c("rqdp{  save GEN_QIMEI flag failed! }", new Object[0]);
          return;
        }
      }
      com.tencent.feedback.common.e.h("rqdp{  no uphandler ,no upload!}", new Object[0]);
      return;
      label323: MixPackage localMixPackage = null;
    }
  }

  // ERROR //
  public final void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   4: sipush 300
    //   7: invokestatic 164	com/tencent/feedback/anr/a:a	(Landroid/content/Context;I)Lcom/tencent/feedback/common/b/i;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnull +61 -> 73
    //   15: aload_1
    //   16: invokevirtual 168	com/tencent/feedback/common/b/i:b	()I
    //   19: sipush 300
    //   22: if_icmpne +51 -> 73
    //   25: aload_1
    //   26: invokevirtual 170	com/tencent/feedback/common/b/i:c	()[B
    //   29: ifnull +44 -> 73
    //   32: aload_0
    //   33: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   36: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   39: invokevirtual 176	com/tencent/feedback/common/b/c:c	()Lcom/tencent/feedback/upload/e;
    //   42: astore 49
    //   44: aload 49
    //   46: ifnull +27 -> 73
    //   49: aload 49
    //   51: sipush 300
    //   54: aload_1
    //   55: invokevirtual 170	com/tencent/feedback/common/b/i:c	()[B
    //   58: iconst_0
    //   59: invokeinterface 181 4 0
    //   64: ldc 183
    //   66: iconst_0
    //   67: anewarray 4	java/lang/Object
    //   70: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   73: aload_0
    //   74: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   77: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   80: astore_2
    //   81: aload_2
    //   82: invokevirtual 188	com/tencent/feedback/common/b/c:e	()I
    //   85: ifne +689 -> 774
    //   88: ldc 190
    //   90: iconst_0
    //   91: anewarray 4	java/lang/Object
    //   94: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   97: aload_2
    //   98: iconst_1
    //   99: invokevirtual 193	com/tencent/feedback/common/b/c:a	(I)V
    //   102: aload_0
    //   103: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   106: aload_0
    //   107: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   110: invokevirtual 198	android/content/Context:getPackageName	()Ljava/lang/String;
    //   113: invokestatic 201	com/tencent/feedback/common/a:a	(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;
    //   116: astore 16
    //   118: new 203	java/io/File
    //   121: dup
    //   122: aload 16
    //   124: invokespecial 206	java/io/File:<init>	(Ljava/lang/String;)V
    //   127: invokevirtual 209	java/io/File:lastModified	()J
    //   130: lstore 17
    //   132: new 203	java/io/File
    //   135: dup
    //   136: aload 16
    //   138: invokespecial 206	java/io/File:<init>	(Ljava/lang/String;)V
    //   141: invokevirtual 212	java/io/File:length	()J
    //   144: lstore 19
    //   146: aload_0
    //   147: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   150: invokestatic 215	com/tencent/feedback/common/a:c	(Landroid/content/Context;)Ljava/lang/String;
    //   153: astore 21
    //   155: aload_0
    //   156: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   159: aload 16
    //   161: iconst_0
    //   162: bipush 10
    //   164: invokestatic 220	com/tencent/feedback/common/a/a:a	(Landroid/content/Context;Ljava/lang/String;II)Ljava/util/List;
    //   167: astore 22
    //   169: aload 22
    //   171: ifnull +874 -> 1045
    //   174: aload 22
    //   176: invokeinterface 223 1 0
    //   181: ifle +864 -> 1045
    //   184: aload 22
    //   186: iconst_0
    //   187: invokeinterface 227 2 0
    //   192: checkcast 229	com/tencent/feedback/common/a/d
    //   195: astore 23
    //   197: aload 23
    //   199: ifnull +283 -> 482
    //   202: aload 23
    //   204: invokevirtual 232	com/tencent/feedback/common/a/d:d	()Ljava/lang/String;
    //   207: ifnull +275 -> 482
    //   210: aload 21
    //   212: aload 23
    //   214: invokevirtual 235	com/tencent/feedback/common/a/d:f	()Ljava/lang/String;
    //   217: invokevirtual 134	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   220: ifeq +262 -> 482
    //   223: lload 17
    //   225: aload 23
    //   227: invokevirtual 237	com/tencent/feedback/common/a/d:b	()J
    //   230: lcmp
    //   231: ifne +251 -> 482
    //   234: lload 19
    //   236: aload 23
    //   238: invokevirtual 239	com/tencent/feedback/common/a/d:c	()J
    //   241: lcmp
    //   242: ifne +240 -> 482
    //   245: aload_0
    //   246: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   249: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   252: invokevirtual 242	com/tencent/feedback/common/b/c:b	()Lcom/tencent/feedback/common/b/f;
    //   255: aload 23
    //   257: invokevirtual 232	com/tencent/feedback/common/a/d:d	()Ljava/lang/String;
    //   260: invokevirtual 246	com/tencent/feedback/common/b/f:d	(Ljava/lang/String;)V
    //   263: iconst_0
    //   264: istore 32
    //   266: iconst_1
    //   267: anewarray 4	java/lang/Object
    //   270: astore 33
    //   272: aload 33
    //   274: iconst_0
    //   275: iload 32
    //   277: invokestatic 251	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   280: aastore
    //   281: ldc 253
    //   283: aload 33
    //   285: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   288: invokestatic 259	com/tencent/feedback/common/c:p	()Lcom/tencent/feedback/common/c;
    //   291: astore 34
    //   293: aload 34
    //   295: ifnull +419 -> 714
    //   298: aload 34
    //   300: invokevirtual 262	com/tencent/feedback/common/c:j	()Ljava/lang/String;
    //   303: ifnull +17 -> 320
    //   306: aload 34
    //   308: invokevirtual 262	com/tencent/feedback/common/c:j	()Ljava/lang/String;
    //   311: invokevirtual 265	java/lang/String:trim	()Ljava/lang/String;
    //   314: invokevirtual 267	java/lang/String:length	()I
    //   317: ifgt +397 -> 714
    //   320: aload 34
    //   322: aload_2
    //   323: invokevirtual 242	com/tencent/feedback/common/b/c:b	()Lcom/tencent/feedback/common/b/f;
    //   326: invokevirtual 269	com/tencent/feedback/common/b/f:h	()Ljava/lang/String;
    //   329: invokevirtual 271	com/tencent/feedback/common/c:c	(Ljava/lang/String;)V
    //   332: iconst_1
    //   333: anewarray 4	java/lang/Object
    //   336: astore 45
    //   338: aload 45
    //   340: iconst_0
    //   341: aload 34
    //   343: invokevirtual 262	com/tencent/feedback/common/c:j	()Ljava/lang/String;
    //   346: aastore
    //   347: ldc_w 273
    //   350: aload 45
    //   352: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   355: iload 32
    //   357: ifeq +377 -> 734
    //   360: aload_0
    //   361: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   364: aconst_null
    //   365: ldc2_w 274
    //   368: ldc2_w 276
    //   371: iconst_m1
    //   372: iconst_m1
    //   373: invokestatic 280	com/tencent/feedback/common/a/a:a	(Landroid/content/Context;[IJJII)I
    //   376: istore 39
    //   378: iconst_1
    //   379: anewarray 4	java/lang/Object
    //   382: astore 40
    //   384: aload 40
    //   386: iconst_0
    //   387: iload 39
    //   389: invokestatic 73	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   392: aastore
    //   393: ldc_w 282
    //   396: aload 40
    //   398: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   401: aload_2
    //   402: monitorenter
    //   403: ldc_w 284
    //   406: iconst_0
    //   407: anewarray 4	java/lang/Object
    //   410: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   413: aload_2
    //   414: invokevirtual 287	com/tencent/feedback/common/b/c:d	()[Lcom/tencent/feedback/common/b/b;
    //   417: astore 42
    //   419: aload_2
    //   420: iconst_1
    //   421: invokevirtual 290	com/tencent/feedback/common/b/c:a	(Z)V
    //   424: aload_2
    //   425: monitorexit
    //   426: aload 42
    //   428: ifnull +346 -> 774
    //   431: ldc_w 292
    //   434: iconst_0
    //   435: anewarray 4	java/lang/Object
    //   438: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   441: aload 42
    //   443: arraylength
    //   444: istore 43
    //   446: iconst_0
    //   447: istore 44
    //   449: iload 44
    //   451: iload 43
    //   453: if_icmpge +321 -> 774
    //   456: aload 42
    //   458: iload 44
    //   460: aaload
    //   461: invokeinterface 296 1 0
    //   466: iinc 44 1
    //   469: goto -20 -> 449
    //   472: astore 48
    //   474: aload 48
    //   476: invokevirtual 297	java/lang/Throwable:printStackTrace	()V
    //   479: goto -406 -> 73
    //   482: aload_0
    //   483: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   486: invokestatic 299	com/tencent/feedback/common/a:g	(Landroid/content/Context;)Ljava/lang/String;
    //   489: astore 24
    //   491: aload 24
    //   493: ifnonnull +19 -> 512
    //   496: ldc_w 301
    //   499: iconst_0
    //   500: anewarray 4	java/lang/Object
    //   503: invokestatic 303	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   506: iconst_0
    //   507: istore 32
    //   509: goto -243 -> 266
    //   512: iconst_1
    //   513: anewarray 4	java/lang/Object
    //   516: astore 25
    //   518: aload 23
    //   520: ifnonnull +184 -> 704
    //   523: ldc_w 305
    //   526: astore 26
    //   528: aload 25
    //   530: iconst_0
    //   531: aload 26
    //   533: aastore
    //   534: ldc_w 307
    //   537: aload 25
    //   539: invokestatic 309	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   542: aload 23
    //   544: ifnull +37 -> 581
    //   547: aload_0
    //   548: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   551: aload 22
    //   553: invokestatic 312	com/tencent/feedback/common/a/a:d	(Landroid/content/Context;Ljava/util/List;)I
    //   556: istore 46
    //   558: iconst_1
    //   559: anewarray 4	java/lang/Object
    //   562: astore 47
    //   564: aload 47
    //   566: iconst_0
    //   567: iload 46
    //   569: invokestatic 73	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   572: aastore
    //   573: ldc_w 314
    //   576: aload 47
    //   578: invokestatic 309	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   581: new 143	java/util/ArrayList
    //   584: dup
    //   585: invokespecial 315	java/util/ArrayList:<init>	()V
    //   588: astore 27
    //   590: new 229	com/tencent/feedback/common/a/d
    //   593: dup
    //   594: invokespecial 316	com/tencent/feedback/common/a/d:<init>	()V
    //   597: astore 28
    //   599: aload 28
    //   601: iconst_0
    //   602: invokevirtual 317	com/tencent/feedback/common/a/d:a	(I)V
    //   605: aload 28
    //   607: aload 16
    //   609: invokevirtual 319	com/tencent/feedback/common/a/d:a	(Ljava/lang/String;)V
    //   612: aload 28
    //   614: aload 24
    //   616: invokevirtual 321	com/tencent/feedback/common/a/d:b	(Ljava/lang/String;)V
    //   619: aload 28
    //   621: aload 21
    //   623: invokevirtual 322	com/tencent/feedback/common/a/d:c	(Ljava/lang/String;)V
    //   626: aload 28
    //   628: lload 17
    //   630: invokevirtual 324	com/tencent/feedback/common/a/d:b	(J)V
    //   633: aload 28
    //   635: lload 19
    //   637: invokevirtual 326	com/tencent/feedback/common/a/d:c	(J)V
    //   640: aload 27
    //   642: aload 28
    //   644: invokeinterface 329 2 0
    //   649: pop
    //   650: aload_0
    //   651: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   654: aload 27
    //   656: invokestatic 331	com/tencent/feedback/common/a/a:c	(Landroid/content/Context;Ljava/util/List;)I
    //   659: pop
    //   660: iconst_1
    //   661: anewarray 4	java/lang/Object
    //   664: astore 31
    //   666: aload 31
    //   668: iconst_0
    //   669: aload 28
    //   671: invokevirtual 334	com/tencent/feedback/common/a/d:toString	()Ljava/lang/String;
    //   674: aastore
    //   675: ldc_w 336
    //   678: aload 31
    //   680: invokestatic 309	com/tencent/feedback/common/e:g	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   683: aload_0
    //   684: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   687: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   690: invokevirtual 242	com/tencent/feedback/common/b/c:b	()Lcom/tencent/feedback/common/b/f;
    //   693: aload 24
    //   695: invokevirtual 246	com/tencent/feedback/common/b/f:d	(Ljava/lang/String;)V
    //   698: iconst_1
    //   699: istore 32
    //   701: goto -435 -> 266
    //   704: aload 23
    //   706: invokevirtual 334	com/tencent/feedback/common/a/d:toString	()Ljava/lang/String;
    //   709: astore 26
    //   711: goto -183 -> 528
    //   714: ldc_w 338
    //   717: iconst_0
    //   718: anewarray 4	java/lang/Object
    //   721: invokestatic 153	com/tencent/feedback/common/e:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   724: goto -369 -> 355
    //   727: astore 41
    //   729: aload_2
    //   730: monitorexit
    //   731: aload 41
    //   733: athrow
    //   734: invokestatic 340	com/tencent/feedback/common/b/d:a	()J
    //   737: lstore 35
    //   739: lload 35
    //   741: lconst_0
    //   742: lcmp
    //   743: ifle +31 -> 774
    //   746: iconst_1
    //   747: anewarray 4	java/lang/Object
    //   750: astore 37
    //   752: aload 37
    //   754: iconst_0
    //   755: lload 35
    //   757: invokestatic 345	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   760: aastore
    //   761: ldc_w 347
    //   764: aload 37
    //   766: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   769: lload 35
    //   771: invokestatic 56	java/lang/Thread:sleep	(J)V
    //   774: aload_2
    //   775: monitorenter
    //   776: ldc_w 349
    //   779: iconst_0
    //   780: anewarray 4	java/lang/Object
    //   783: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   786: aload_2
    //   787: invokevirtual 287	com/tencent/feedback/common/b/c:d	()[Lcom/tencent/feedback/common/b/b;
    //   790: astore 4
    //   792: aload_2
    //   793: iconst_2
    //   794: invokevirtual 193	com/tencent/feedback/common/b/c:a	(I)V
    //   797: aload_2
    //   798: monitorexit
    //   799: aload 4
    //   801: ifnull +59 -> 860
    //   804: ldc_w 351
    //   807: iconst_0
    //   808: anewarray 4	java/lang/Object
    //   811: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   814: aload 4
    //   816: arraylength
    //   817: istore 14
    //   819: iconst_0
    //   820: istore 15
    //   822: iload 15
    //   824: iload 14
    //   826: if_icmpge +34 -> 860
    //   829: aload 4
    //   831: iload 15
    //   833: aaload
    //   834: invokeinterface 353 1 0
    //   839: iinc 15 1
    //   842: goto -20 -> 822
    //   845: astore 38
    //   847: aload 38
    //   849: invokevirtual 59	java/lang/InterruptedException:printStackTrace	()V
    //   852: goto -78 -> 774
    //   855: astore_3
    //   856: aload_2
    //   857: monitorexit
    //   858: aload_3
    //   859: athrow
    //   860: aload_0
    //   861: invokespecial 355	com/tencent/feedback/common/b/d:b	()V
    //   864: ldc_w 357
    //   867: iconst_0
    //   868: anewarray 4	java/lang/Object
    //   871: invokestatic 158	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   874: aload_2
    //   875: monitorenter
    //   876: aload_2
    //   877: invokevirtual 287	com/tencent/feedback/common/b/c:d	()[Lcom/tencent/feedback/common/b/b;
    //   880: astore 6
    //   882: aload_2
    //   883: iconst_3
    //   884: invokevirtual 193	com/tencent/feedback/common/b/c:a	(I)V
    //   887: aload_2
    //   888: monitorexit
    //   889: aload 6
    //   891: ifnull +51 -> 942
    //   894: ldc_w 359
    //   897: iconst_0
    //   898: anewarray 4	java/lang/Object
    //   901: invokestatic 186	com/tencent/feedback/common/e:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   904: aload 6
    //   906: arraylength
    //   907: istore 12
    //   909: iconst_0
    //   910: istore 13
    //   912: iload 13
    //   914: iload 12
    //   916: if_icmpge +26 -> 942
    //   919: aload 6
    //   921: iload 13
    //   923: aaload
    //   924: invokeinterface 361 1 0
    //   929: iinc 13 1
    //   932: goto -20 -> 912
    //   935: astore 5
    //   937: aload_2
    //   938: monitorexit
    //   939: aload 5
    //   941: athrow
    //   942: aload_0
    //   943: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   946: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   949: invokevirtual 242	com/tencent/feedback/common/b/c:b	()Lcom/tencent/feedback/common/b/f;
    //   952: astore 7
    //   954: aload 7
    //   956: ifnonnull +14 -> 970
    //   959: ldc_w 363
    //   962: iconst_0
    //   963: anewarray 4	java/lang/Object
    //   966: invokestatic 303	com/tencent/feedback/common/e:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   969: return
    //   970: ldc_w 364
    //   973: aload 7
    //   975: invokevirtual 366	com/tencent/feedback/common/b/f:c	()I
    //   978: imul
    //   979: i2l
    //   980: lstore 8
    //   982: lload 8
    //   984: lconst_0
    //   985: lcmp
    //   986: ifle +48 -> 1034
    //   989: invokestatic 36	com/tencent/feedback/common/b:b	()Lcom/tencent/feedback/common/b;
    //   992: aload_0
    //   993: lload 8
    //   995: invokevirtual 369	com/tencent/feedback/common/b:a	(Ljava/lang/Runnable;J)Z
    //   998: pop
    //   999: iconst_1
    //   1000: anewarray 4	java/lang/Object
    //   1003: astore 11
    //   1005: aload 11
    //   1007: iconst_0
    //   1008: lload 8
    //   1010: invokestatic 345	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   1013: aastore
    //   1014: ldc_w 371
    //   1017: aload 11
    //   1019: invokestatic 158	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1022: aload_0
    //   1023: getfield 20	com/tencent/feedback/common/b/d:b	Landroid/content/Context;
    //   1026: invokestatic 173	com/tencent/feedback/common/b/c:a	(Landroid/content/Context;)Lcom/tencent/feedback/common/b/c;
    //   1029: iconst_4
    //   1030: invokevirtual 193	com/tencent/feedback/common/b/c:a	(I)V
    //   1033: return
    //   1034: ldc_w 373
    //   1037: iconst_0
    //   1038: anewarray 4	java/lang/Object
    //   1041: invokestatic 158	com/tencent/feedback/common/e:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1044: return
    //   1045: aconst_null
    //   1046: astore 23
    //   1048: goto -851 -> 197
    //
    // Exception table:
    //   from	to	target	type
    //   32	44	472	java/lang/Throwable
    //   49	73	472	java/lang/Throwable
    //   403	426	727	finally
    //   769	774	845	java/lang/InterruptedException
    //   776	799	855	finally
    //   876	889	935	finally
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.b.d
 * JD-Core Version:    0.6.0
 */