package com.tencent.beacon.upload;

import android.content.Context;
import android.util.SparseArray;
import com.tencent.beacon.e.d;
import java.util.ArrayList;
import java.util.List;

public final class h
  implements g
{
  private static h a = null;
  private SparseArray<f> b = new SparseArray(5);
  private List<UploadHandleListener> c = new ArrayList(5);
  private e d;
  private Context e = null;
  private boolean f = true;
  private int g = 0;
  private boolean h = true;

  private h(Context paramContext, boolean paramBoolean)
  {
    this.e = paramContext.getApplicationContext();
    this.f = paramBoolean;
    this.d = e.a(this.e);
  }

  private static com.tencent.beacon.c.a.c a(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null)
      try
      {
        byte[] arrayOfByte = com.tencent.beacon.applog.a.b(1, paramArrayOfByte);
        d locald = new d();
        locald.a(arrayOfByte);
        com.tencent.beacon.c.a.c localc1 = new com.tencent.beacon.c.a.c();
        com.tencent.beacon.d.a.b(" covert to ResponsePackage ", new Object[0]);
        com.tencent.beacon.c.a.c localc2 = (com.tencent.beacon.c.a.c)locald.b("detail", localc1);
        return localc2;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    return null;
  }

  public static h a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        a = new h(paramContext, true);
        com.tencent.beacon.d.a.h(" create uphandler up:true", new Object[0]);
      }
      h localh = a;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static h a(Context paramContext, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (a == null)
      {
        a = new h(paramContext, paramBoolean);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Boolean.valueOf(paramBoolean);
        com.tencent.beacon.d.a.h(" create uphandler up: %b", arrayOfObject2);
      }
      if (a.b() != paramBoolean)
      {
        a.b(paramBoolean);
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Boolean.valueOf(paramBoolean);
        com.tencent.beacon.d.a.h(" change uphandler up: %b", arrayOfObject1);
      }
      h localh = a;
      return localh;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(int paramInt1, int paramInt2, long paramLong1, long paramLong2, boolean paramBoolean, String paramString)
  {
    UploadHandleListener[] arrayOfUploadHandleListener = d();
    if (arrayOfUploadHandleListener != null)
    {
      int i = arrayOfUploadHandleListener.length;
      for (int j = 0; j < i; j++)
        arrayOfUploadHandleListener[j].onUploadEnd(paramInt1, paramInt2, paramLong1, paramLong2, paramBoolean, paramString);
    }
  }

  private boolean a(SparseArray<f> paramSparseArray, int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramSparseArray == null) || (paramArrayOfByte == null))
      return true;
    f localf;
    switch (paramInt)
    {
    default:
      localf = (f)paramSparseArray.get(paramInt);
      if (localf != null)
        break;
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(paramInt);
      com.tencent.beacon.d.a.c(" no handler key:%d", arrayOfObject4);
      return false;
    case 103:
      try
      {
        com.tencent.beacon.d.a.a(" process CMD_RESPONSE_GEN_QIMEI", new Object[0]);
        com.tencent.beacon.e.a locala = new com.tencent.beacon.e.a(paramArrayOfByte);
        com.tencent.beacon.c.c.a locala1 = new com.tencent.beacon.c.c.a();
        locala1.a(locala);
        if (locala1.a != null)
        {
          com.tencent.beacon.b.a.a(this.e).a(locala1.a);
          com.tencent.beacon.a.a.a(this.e, "QIMEI_DENGTA", locala1.a);
        }
        Object[] arrayOfObject1 = new Object[5];
        arrayOfObject1[0] = locala1.a;
        arrayOfObject1[1] = locala1.b;
        arrayOfObject1[2] = locala1.d;
        arrayOfObject1[3] = locala1.e;
        arrayOfObject1[4] = locala1.c;
        com.tencent.beacon.d.a.h(" Qimei:%s  imei:%s  imsi:%s  aid:%s  mac:%s ", arrayOfObject1);
        return true;
      }
      catch (Throwable localThrowable1)
      {
        while (true)
          localThrowable1.printStackTrace();
      }
    }
    try
    {
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Integer.valueOf(paramInt);
      arrayOfObject3[1] = localf.getClass().toString();
      com.tencent.beacon.d.a.b(" key:%d  handler: %s", arrayOfObject3);
      localf.a(paramInt, paramArrayOfByte, true);
      return true;
    }
    catch (Throwable localThrowable2)
    {
      localThrowable2.printStackTrace();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      com.tencent.beacon.d.a.d(" handle error key:%d", arrayOfObject2);
    }
    return false;
  }

  private void b(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.f = paramBoolean;
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

  private static byte[] b(a parama)
  {
    if (parama != null)
      try
      {
        com.tencent.beacon.c.a.b localb = parama.a();
        if (localb != null)
        {
          Object[] arrayOfObject = new Object[4];
          arrayOfObject[0] = localb.b;
          arrayOfObject[1] = localb.d;
          arrayOfObject[2] = localb.c;
          arrayOfObject[3] = Integer.valueOf(localb.f);
          com.tencent.beacon.d.a.b(" RequestPackage info appkey:%s sdkid:%s appVersion:%s cmd: %d", arrayOfObject);
          d locald = new d();
          locald.a(1);
          locald.b("test");
          locald.a("test");
          locald.a("detail", localb);
          byte[] arrayOfByte = com.tencent.beacon.applog.a.a(1, locald.a());
          return arrayOfByte;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        parama.b();
      }
    return null;
  }

  private UploadHandleListener[] d()
  {
    monitorenter;
    try
    {
      if ((this.c != null) && (this.c.size() > 0))
      {
        arrayOfUploadHandleListener = (UploadHandleListener[])this.c.toArray(new UploadHandleListener[0]);
        return arrayOfUploadHandleListener;
      }
      UploadHandleListener[] arrayOfUploadHandleListener = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private e e()
  {
    monitorenter;
    try
    {
      e locale = this.d;
      monitorexit;
      return locale;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private SparseArray<f> f()
  {
    monitorenter;
    try
    {
      if ((this.b != null) && (this.b.size() > 0))
      {
        new com.tencent.beacon.d.c();
        SparseArray localSparseArray2 = com.tencent.beacon.d.c.a(this.b);
        localSparseArray1 = localSparseArray2;
        return localSparseArray1;
      }
      SparseArray localSparseArray1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public final int a()
  {
    monitorenter;
    try
    {
      int i = this.g;
      monitorexit;
      return i;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.g = paramInt;
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
  public final void a(a parama)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 115	com/tencent/beacon/upload/h:b	()Z
    //   4: ifeq +10 -> 14
    //   7: aload_0
    //   8: invokevirtual 262	com/tencent/beacon/upload/h:c	()Z
    //   11: ifne +74 -> 85
    //   14: aload_1
    //   15: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   18: iconst_2
    //   19: if_icmpne +23 -> 42
    //   22: ldc_w 266
    //   25: iconst_0
    //   26: anewarray 4	java/lang/Object
    //   29: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   32: aload_1
    //   33: invokevirtual 212	com/tencent/beacon/upload/a:a	()Lcom/tencent/beacon/c/a/b;
    //   36: pop
    //   37: aload_1
    //   38: iconst_0
    //   39: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   42: aload_1
    //   43: getfield 270	com/tencent/beacon/upload/a:b	I
    //   46: ifeq +29 -> 75
    //   49: iconst_1
    //   50: anewarray 4	java/lang/Object
    //   53: astore 36
    //   55: aload 36
    //   57: iconst_0
    //   58: aload_1
    //   59: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   62: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   65: aastore
    //   66: ldc_w 272
    //   69: aload 36
    //   71: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   74: return
    //   75: ldc_w 274
    //   78: iconst_0
    //   79: anewarray 4	java/lang/Object
    //   82: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   85: aload_0
    //   86: getfield 45	com/tencent/beacon/upload/h:e	Landroid/content/Context;
    //   89: invokestatic 279	com/tencent/beacon/d/b:b	(Landroid/content/Context;)Z
    //   92: ifne +27 -> 119
    //   95: ldc_w 281
    //   98: iconst_0
    //   99: anewarray 4	java/lang/Object
    //   102: invokestatic 145	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   105: aload_1
    //   106: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   109: iconst_2
    //   110: if_icmpne -36 -> 74
    //   113: aload_1
    //   114: iconst_0
    //   115: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   118: return
    //   119: aload_0
    //   120: aload_0
    //   121: getfield 45	com/tencent/beacon/upload/h:e	Landroid/content/Context;
    //   124: invokestatic 286	com/tencent/beacon/a/f:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/f;
    //   127: invokevirtual 289	com/tencent/beacon/upload/h:a	(Lcom/tencent/beacon/upload/UploadHandleListener;)Z
    //   130: pop
    //   131: aload_1
    //   132: ifnonnull +14 -> 146
    //   135: ldc_w 291
    //   138: iconst_0
    //   139: anewarray 4	java/lang/Object
    //   142: invokestatic 206	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   145: return
    //   146: aload_1
    //   147: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   150: istore_3
    //   151: lconst_0
    //   152: lstore 4
    //   154: lconst_0
    //   155: lstore 6
    //   157: iconst_0
    //   158: istore 8
    //   160: iconst_m1
    //   161: istore 9
    //   163: aload_1
    //   164: invokevirtual 293	com/tencent/beacon/upload/a:d	()Ljava/lang/String;
    //   167: astore 10
    //   169: aload 10
    //   171: ifnull +17 -> 188
    //   174: ldc_w 295
    //   177: aload 10
    //   179: invokevirtual 300	java/lang/String:trim	()Ljava/lang/String;
    //   182: invokevirtual 304	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   185: ifeq +39 -> 224
    //   188: ldc_w 306
    //   191: iconst_0
    //   192: anewarray 4	java/lang/Object
    //   195: invokestatic 206	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   198: aload_1
    //   199: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   202: iconst_2
    //   203: if_icmpne +8 -> 211
    //   206: aload_1
    //   207: iconst_0
    //   208: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   211: aload_0
    //   212: iload_3
    //   213: iconst_m1
    //   214: lconst_0
    //   215: lconst_0
    //   216: iconst_0
    //   217: ldc_w 308
    //   220: invokespecial 310	com/tencent/beacon/upload/h:a	(IIJJZLjava/lang/String;)V
    //   223: return
    //   224: iconst_3
    //   225: anewarray 4	java/lang/Object
    //   228: astore 11
    //   230: aload 11
    //   232: iconst_0
    //   233: iload_3
    //   234: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   237: aastore
    //   238: aload 11
    //   240: iconst_1
    //   241: aload 10
    //   243: aastore
    //   244: aload 11
    //   246: iconst_2
    //   247: aload_1
    //   248: invokevirtual 191	java/lang/Object:getClass	()Ljava/lang/Class;
    //   251: invokevirtual 197	java/lang/Class:toString	()Ljava/lang/String;
    //   254: aastore
    //   255: ldc_w 312
    //   258: aload 11
    //   260: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   263: aload_1
    //   264: invokestatic 314	com/tencent/beacon/upload/h:b	(Lcom/tencent/beacon/upload/a;)[B
    //   267: astore 12
    //   269: aload 12
    //   271: ifnonnull +26 -> 297
    //   274: ldc_w 316
    //   277: iconst_0
    //   278: anewarray 4	java/lang/Object
    //   281: invokestatic 206	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   284: aload_0
    //   285: iload_3
    //   286: iconst_m1
    //   287: lconst_0
    //   288: lconst_0
    //   289: iconst_0
    //   290: ldc_w 318
    //   293: invokespecial 310	com/tencent/beacon/upload/h:a	(IIJJZLjava/lang/String;)V
    //   296: return
    //   297: aload_0
    //   298: invokespecial 320	com/tencent/beacon/upload/h:e	()Lcom/tencent/beacon/upload/e;
    //   301: astore 13
    //   303: aload 13
    //   305: ifnonnull +26 -> 331
    //   308: ldc_w 322
    //   311: iconst_0
    //   312: anewarray 4	java/lang/Object
    //   315: invokestatic 206	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   318: aload_0
    //   319: iload_3
    //   320: iconst_m1
    //   321: lconst_0
    //   322: lconst_0
    //   323: iconst_0
    //   324: ldc_w 324
    //   327: invokespecial 310	com/tencent/beacon/upload/h:a	(IIJJZLjava/lang/String;)V
    //   330: return
    //   331: new 326	com/tencent/beacon/upload/d
    //   334: dup
    //   335: invokespecial 327	com/tencent/beacon/upload/d:<init>	()V
    //   338: astore 14
    //   340: aload 13
    //   342: aload 10
    //   344: aload 12
    //   346: aload 14
    //   348: aload_1
    //   349: invokevirtual 330	com/tencent/beacon/upload/e:a	(Ljava/lang/String;[BLcom/tencent/beacon/upload/d;Lcom/tencent/beacon/upload/a;)[B
    //   352: astore 19
    //   354: aload 19
    //   356: ifnonnull +154 -> 510
    //   359: iload_3
    //   360: bipush 100
    //   362: if_icmpne +29 -> 391
    //   365: ldc_w 332
    //   368: aload 10
    //   370: invokevirtual 304	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   373: ifne +18 -> 391
    //   376: aload 13
    //   378: ldc_w 332
    //   381: aload 12
    //   383: aload 14
    //   385: aload_1
    //   386: invokevirtual 330	com/tencent/beacon/upload/e:a	(Ljava/lang/String;[BLcom/tencent/beacon/upload/d;Lcom/tencent/beacon/upload/a;)[B
    //   389: astore 19
    //   391: aload_0
    //   392: iconst_1
    //   393: aload_0
    //   394: invokevirtual 334	com/tencent/beacon/upload/h:a	()I
    //   397: iadd
    //   398: invokevirtual 335	com/tencent/beacon/upload/h:a	(I)V
    //   401: aload 14
    //   403: invokevirtual 338	com/tencent/beacon/upload/d:a	()J
    //   406: lstore 4
    //   408: aload 14
    //   410: invokevirtual 340	com/tencent/beacon/upload/d:b	()J
    //   413: lstore 6
    //   415: aload 19
    //   417: invokestatic 342	com/tencent/beacon/upload/h:a	([B)Lcom/tencent/beacon/c/a/c;
    //   420: astore 20
    //   422: iconst_0
    //   423: istore 8
    //   425: aload 20
    //   427: ifnull +677 -> 1104
    //   430: aload 20
    //   432: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   435: istore 9
    //   437: aload 20
    //   439: getfield 346	com/tencent/beacon/c/a/c:a	B
    //   442: ifne +134 -> 576
    //   445: iconst_1
    //   446: istore 8
    //   448: iconst_2
    //   449: anewarray 4	java/lang/Object
    //   452: astore 21
    //   454: aload 21
    //   456: iconst_0
    //   457: aload 20
    //   459: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   462: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   465: aastore
    //   466: aload 21
    //   468: iconst_1
    //   469: aload 20
    //   471: getfield 346	com/tencent/beacon/c/a/c:a	B
    //   474: invokestatic 351	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   477: aastore
    //   478: ldc_w 353
    //   481: aload 21
    //   483: invokestatic 88	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   486: goto +618 -> 1104
    //   489: aload_0
    //   490: iload_3
    //   491: iload 9
    //   493: lload 4
    //   495: lload 6
    //   497: iload 8
    //   499: aconst_null
    //   500: invokespecial 310	com/tencent/beacon/upload/h:a	(IIJJZLjava/lang/String;)V
    //   503: aload_1
    //   504: iload 8
    //   506: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   509: return
    //   510: aload_0
    //   511: iconst_0
    //   512: invokevirtual 335	com/tencent/beacon/upload/h:a	(I)V
    //   515: goto -114 -> 401
    //   518: astore 16
    //   520: iload 8
    //   522: istore 17
    //   524: aload 16
    //   526: invokevirtual 96	java/lang/Throwable:printStackTrace	()V
    //   529: aload_0
    //   530: iload_3
    //   531: iload 9
    //   533: lload 4
    //   535: lload 6
    //   537: iconst_0
    //   538: aload 16
    //   540: invokevirtual 354	java/lang/Throwable:toString	()Ljava/lang/String;
    //   543: invokespecial 310	com/tencent/beacon/upload/h:a	(IIJJZLjava/lang/String;)V
    //   546: iconst_1
    //   547: anewarray 4	java/lang/Object
    //   550: astore 18
    //   552: aload 18
    //   554: iconst_0
    //   555: aload 16
    //   557: invokevirtual 354	java/lang/Throwable:toString	()Ljava/lang/String;
    //   560: aastore
    //   561: ldc_w 356
    //   564: aload 18
    //   566: invokestatic 206	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   569: aload_1
    //   570: iload 17
    //   572: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   575: return
    //   576: iconst_0
    //   577: istore 8
    //   579: goto -131 -> 448
    //   582: invokestatic 362	com/tencent/beacon/a/d:m	()Lcom/tencent/beacon/a/d;
    //   585: astore 22
    //   587: aload 22
    //   589: ifnull +84 -> 673
    //   592: aload 20
    //   594: getfield 363	com/tencent/beacon/c/a/c:d	Ljava/lang/String;
    //   597: ifnull +16 -> 613
    //   600: aload 22
    //   602: aload 20
    //   604: getfield 363	com/tencent/beacon/c/a/c:d	Ljava/lang/String;
    //   607: invokevirtual 300	java/lang/String:trim	()Ljava/lang/String;
    //   610: invokevirtual 364	com/tencent/beacon/a/d:a	(Ljava/lang/String;)V
    //   613: new 366	java/util/Date
    //   616: dup
    //   617: invokespecial 367	java/util/Date:<init>	()V
    //   620: astore 23
    //   622: aload 22
    //   624: aload 20
    //   626: getfield 370	com/tencent/beacon/c/a/c:g	J
    //   629: aload 23
    //   631: invokevirtual 373	java/util/Date:getTime	()J
    //   634: lsub
    //   635: invokevirtual 376	com/tencent/beacon/a/d:a	(J)V
    //   638: iconst_2
    //   639: anewarray 4	java/lang/Object
    //   642: astore 24
    //   644: aload 24
    //   646: iconst_0
    //   647: aload 22
    //   649: invokevirtual 378	com/tencent/beacon/a/d:g	()Ljava/lang/String;
    //   652: aastore
    //   653: aload 24
    //   655: iconst_1
    //   656: aload 22
    //   658: invokevirtual 380	com/tencent/beacon/a/d:h	()J
    //   661: invokestatic 385	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   664: aastore
    //   665: ldc_w 387
    //   668: aload 24
    //   670: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   673: aload 20
    //   675: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   678: bipush 101
    //   680: if_icmpeq +23 -> 703
    //   683: aload 20
    //   685: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   688: bipush 103
    //   690: if_icmpeq +13 -> 703
    //   693: aload 20
    //   695: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   698: bipush 105
    //   700: if_icmpne +108 -> 808
    //   703: aload_0
    //   704: getfield 45	com/tencent/beacon/upload/h:e	Landroid/content/Context;
    //   707: invokestatic 392	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   710: invokevirtual 395	com/tencent/beacon/a/b/c:b	()Lcom/tencent/beacon/a/b/e;
    //   713: astore 25
    //   715: aload 25
    //   717: ifnull +91 -> 808
    //   720: aload 25
    //   722: invokevirtual 400	com/tencent/beacon/a/b/e:g	()B
    //   725: aload 20
    //   727: getfield 402	com/tencent/beacon/c/a/c:f	B
    //   730: if_icmpeq +13 -> 743
    //   733: aload 25
    //   735: aload 20
    //   737: getfield 402	com/tencent/beacon/c/a/c:f	B
    //   740: invokevirtual 405	com/tencent/beacon/a/b/e:b	(B)V
    //   743: aload 25
    //   745: invokevirtual 407	com/tencent/beacon/a/b/e:f	()B
    //   748: aload 20
    //   750: getfield 409	com/tencent/beacon/c/a/c:e	B
    //   753: if_icmpeq +13 -> 766
    //   756: aload 25
    //   758: aload 20
    //   760: getfield 409	com/tencent/beacon/c/a/c:e	B
    //   763: invokevirtual 411	com/tencent/beacon/a/b/e:a	(B)V
    //   766: aload 25
    //   768: invokevirtual 413	com/tencent/beacon/a/b/e:h	()Ljava/lang/String;
    //   771: ifnull +27 -> 798
    //   774: aload 20
    //   776: getfield 415	com/tencent/beacon/c/a/c:h	Ljava/lang/String;
    //   779: ifnull +29 -> 808
    //   782: aload 25
    //   784: invokevirtual 413	com/tencent/beacon/a/b/e:h	()Ljava/lang/String;
    //   787: aload 20
    //   789: getfield 415	com/tencent/beacon/c/a/c:h	Ljava/lang/String;
    //   792: invokevirtual 304	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   795: ifne +13 -> 808
    //   798: aload 25
    //   800: aload 20
    //   802: getfield 415	com/tencent/beacon/c/a/c:h	Ljava/lang/String;
    //   805: invokevirtual 416	com/tencent/beacon/a/b/e:b	(Ljava/lang/String;)V
    //   808: aload 20
    //   810: getfield 419	com/tencent/beacon/c/a/c:c	[B
    //   813: astore 26
    //   815: aload 26
    //   817: ifnonnull +27 -> 844
    //   820: ldc_w 421
    //   823: iconst_0
    //   824: anewarray 4	java/lang/Object
    //   827: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   830: goto -341 -> 489
    //   833: astore 15
    //   835: aload_1
    //   836: iload 8
    //   838: invokevirtual 268	com/tencent/beacon/upload/a:a	(Z)V
    //   841: aload 15
    //   843: athrow
    //   844: aload 26
    //   846: aload 20
    //   848: getfield 402	com/tencent/beacon/c/a/c:f	B
    //   851: aload 20
    //   853: getfield 409	com/tencent/beacon/c/a/c:e	B
    //   856: aload 20
    //   858: getfield 424	com/tencent/beacon/c/a/c:i	Ljava/lang/String;
    //   861: invokestatic 429	com/tencent/beacon/a/e:b	([BIILjava/lang/String;)[B
    //   864: astore 27
    //   866: aload_0
    //   867: invokespecial 431	com/tencent/beacon/upload/h:f	()Landroid/util/SparseArray;
    //   870: astore 28
    //   872: aload 28
    //   874: ifnull +11 -> 885
    //   877: aload 28
    //   879: invokevirtual 253	android/util/SparseArray:size	()I
    //   882: ifgt +16 -> 898
    //   885: ldc_w 433
    //   888: iconst_0
    //   889: anewarray 4	java/lang/Object
    //   892: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   895: goto -406 -> 489
    //   898: aload_1
    //   899: invokevirtual 264	com/tencent/beacon/upload/a:c	()I
    //   902: istore 29
    //   904: aload 20
    //   906: getfield 343	com/tencent/beacon/c/a/c:b	I
    //   909: istore 30
    //   911: iload 30
    //   913: ifne +203 -> 1116
    //   916: ldc_w 435
    //   919: iconst_0
    //   920: anewarray 4	java/lang/Object
    //   923: invokestatic 103	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   926: goto -437 -> 489
    //   929: iconst_1
    //   930: anewarray 4	java/lang/Object
    //   933: astore 35
    //   935: aload 35
    //   937: iconst_0
    //   938: iload 29
    //   940: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   943: aastore
    //   944: ldc_w 437
    //   947: aload 35
    //   949: invokestatic 145	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   952: goto -463 -> 489
    //   955: iload 30
    //   957: bipush 101
    //   959: if_icmpeq +122 -> 1081
    //   962: iconst_2
    //   963: anewarray 4	java/lang/Object
    //   966: astore 34
    //   968: aload 34
    //   970: iconst_0
    //   971: iload 29
    //   973: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   976: aastore
    //   977: aload 34
    //   979: iconst_1
    //   980: iload 30
    //   982: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   985: aastore
    //   986: ldc_w 439
    //   989: aload 34
    //   991: invokestatic 145	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   994: goto -505 -> 489
    //   997: iload 30
    //   999: bipush 105
    //   1001: if_icmpeq +80 -> 1081
    //   1004: iconst_2
    //   1005: anewarray 4	java/lang/Object
    //   1008: astore 33
    //   1010: aload 33
    //   1012: iconst_0
    //   1013: iload 29
    //   1015: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1018: aastore
    //   1019: aload 33
    //   1021: iconst_1
    //   1022: iload 30
    //   1024: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1027: aastore
    //   1028: ldc_w 439
    //   1031: aload 33
    //   1033: invokestatic 145	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1036: goto -547 -> 489
    //   1039: iload 30
    //   1041: bipush 103
    //   1043: if_icmpeq +38 -> 1081
    //   1046: iconst_2
    //   1047: anewarray 4	java/lang/Object
    //   1050: astore 31
    //   1052: aload 31
    //   1054: iconst_0
    //   1055: iload 29
    //   1057: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1060: aastore
    //   1061: aload 31
    //   1063: iconst_1
    //   1064: iload 30
    //   1066: invokestatic 141	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   1069: aastore
    //   1070: ldc_w 441
    //   1073: aload 31
    //   1075: invokestatic 145	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   1078: goto -589 -> 489
    //   1081: aload_0
    //   1082: aload 28
    //   1084: iload 30
    //   1086: aload 27
    //   1088: invokespecial 443	com/tencent/beacon/upload/h:a	(Landroid/util/SparseArray;I[B)Z
    //   1091: pop
    //   1092: goto -603 -> 489
    //   1095: astore 15
    //   1097: iload 17
    //   1099: istore 8
    //   1101: goto -266 -> 835
    //   1104: aload_1
    //   1105: ifnull -616 -> 489
    //   1108: aload 20
    //   1110: ifnonnull -528 -> 582
    //   1113: goto -624 -> 489
    //   1116: iload 29
    //   1118: lookupswitch	default:+-189->929, 4:+-121->997, 100:+-163->955, 102:+-79->1039
    //
    // Exception table:
    //   from	to	target	type
    //   331	354	518	java/lang/Throwable
    //   365	391	518	java/lang/Throwable
    //   391	401	518	java/lang/Throwable
    //   401	422	518	java/lang/Throwable
    //   430	445	518	java/lang/Throwable
    //   448	486	518	java/lang/Throwable
    //   489	503	518	java/lang/Throwable
    //   510	515	518	java/lang/Throwable
    //   582	587	518	java/lang/Throwable
    //   592	613	518	java/lang/Throwable
    //   613	673	518	java/lang/Throwable
    //   673	703	518	java/lang/Throwable
    //   703	715	518	java/lang/Throwable
    //   720	743	518	java/lang/Throwable
    //   743	766	518	java/lang/Throwable
    //   766	798	518	java/lang/Throwable
    //   798	808	518	java/lang/Throwable
    //   808	815	518	java/lang/Throwable
    //   820	830	518	java/lang/Throwable
    //   844	872	518	java/lang/Throwable
    //   877	885	518	java/lang/Throwable
    //   885	895	518	java/lang/Throwable
    //   898	911	518	java/lang/Throwable
    //   916	926	518	java/lang/Throwable
    //   929	952	518	java/lang/Throwable
    //   962	994	518	java/lang/Throwable
    //   1004	1036	518	java/lang/Throwable
    //   1046	1078	518	java/lang/Throwable
    //   1081	1092	518	java/lang/Throwable
    //   331	354	833	finally
    //   365	391	833	finally
    //   391	401	833	finally
    //   401	422	833	finally
    //   430	445	833	finally
    //   448	486	833	finally
    //   489	503	833	finally
    //   510	515	833	finally
    //   582	587	833	finally
    //   592	613	833	finally
    //   613	673	833	finally
    //   673	703	833	finally
    //   703	715	833	finally
    //   720	743	833	finally
    //   743	766	833	finally
    //   766	798	833	finally
    //   798	808	833	finally
    //   808	815	833	finally
    //   820	830	833	finally
    //   844	872	833	finally
    //   877	885	833	finally
    //   885	895	833	finally
    //   898	911	833	finally
    //   916	926	833	finally
    //   929	952	833	finally
    //   962	994	833	finally
    //   1004	1036	833	finally
    //   1046	1078	833	finally
    //   1081	1092	833	finally
    //   524	569	1095	finally
  }

  public final void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.h = paramBoolean;
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

  public final boolean a(int paramInt, f paramf)
  {
    monitorenter;
    int i;
    if (paramf == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        this.b.append(paramInt, paramf);
        i = 1;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public final boolean a(UploadHandleListener paramUploadHandleListener)
  {
    monitorenter;
    int i;
    if (paramUploadHandleListener == null)
      i = 0;
    while (true)
    {
      monitorexit;
      return i;
      try
      {
        if (!this.c.contains(paramUploadHandleListener))
          this.c.add(paramUploadHandleListener);
        i = 1;
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public final boolean b()
  {
    monitorenter;
    try
    {
      boolean bool = this.f;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final boolean c()
  {
    monitorenter;
    try
    {
      boolean bool1 = com.tencent.beacon.d.b.a(this.e);
      if (bool1);
      for (boolean bool2 = true; ; bool2 = this.h)
        return bool2;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.upload.h
 * JD-Core Version:    0.6.0
 */