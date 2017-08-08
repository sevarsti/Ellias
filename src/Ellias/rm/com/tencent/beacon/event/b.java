package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.c.d.c;
import com.tencent.beacon.c.d.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class b extends com.tencent.beacon.upload.a
{
  private static b h = null;
  private Context d = null;
  private com.tencent.beacon.c.a.b e = null;
  private Long[] f = null;
  private List<Long> g = null;

  private b(Context paramContext)
  {
    super(paramContext, 1, 4);
    this.d = paramContext;
  }

  private com.tencent.beacon.c.a.a a(List<h> paramList)
  {
    int i = 1;
    if ((paramList == null) || (paramList.size() <= 0))
      return null;
    ArrayList localArrayList1 = new ArrayList(5);
    ArrayList localArrayList2 = new ArrayList(5);
    ArrayList localArrayList3 = new ArrayList(5);
    ArrayList localArrayList4 = new ArrayList();
    int j = paramList.size();
    this.g = new ArrayList();
    int k = 0;
    h localh;
    if (k < j)
      try
      {
        localh = (h)paramList.get(k);
        Map localMap = localh.e();
        com.tencent.beacon.d.a.a(" bean.getTP: " + localh.b(), new Object[0]);
        if (localMap != null)
          if ("IP".equals(localh.b()))
          {
            com.tencent.beacon.c.d.b localb1 = a.b(localh);
            if (localb1 != null)
            {
              localArrayList1.add(localb1);
              break label759;
            }
            this.g.add(Long.valueOf(localh.a()));
          }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        com.tencent.beacon.d.a.d(" CommonRecordUploadDatas.encode2MixPackage() error1", new Object[0]);
      }
    while (true)
    {
      c localc1;
      try
      {
        localc1 = new c();
        if (localArrayList3.size() <= 0)
          continue;
        localc1.c = localArrayList3;
        i = 0;
        if (localArrayList2.size() <= 0)
          continue;
        localc1.b = localArrayList2;
        i = 0;
        if (localArrayList1.size() <= 0)
          break label765;
        localc1.a = localArrayList1;
        i = 0;
        break label765;
        label269: if (localArrayList4.size() > 0)
        {
          localb = new com.tencent.beacon.c.b.b();
          localb.a = localArrayList4;
          if ((localc2 == null) && (localb == null))
          {
            return null;
            if (!"DN".equals(localh.b()))
              continue;
            com.tencent.beacon.c.d.a locala2 = a.c(localh);
            if (locala2 == null)
              continue;
            localArrayList2.add(locala2);
            break label759;
            this.g.add(Long.valueOf(localh.a()));
            break label759;
            if (!"HO".equals(localh.b()))
              continue;
            d locald = a.d(localh);
            if (locald == null)
              continue;
            localArrayList3.add(locald);
            break label759;
            this.g.add(Long.valueOf(localh.a()));
            break label759;
            if (!"UA".equals(localh.b()))
              break label759;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = localh.d();
            com.tencent.beacon.d.a.f(" Pack2Upload eventName:}%s ", arrayOfObject);
            com.tencent.beacon.c.b.a locala1 = a.e(localh);
            if (locala1 == null)
              continue;
            localArrayList4.add(locala1);
            break label759;
            this.g.add(Long.valueOf(localh.a()));
            break label759;
            if (this.g.size() <= 0)
              continue;
            com.tencent.beacon.applog.a.a(this.d, (Long[])this.g.toArray(new Long[this.g.size()]));
            com.tencent.beacon.d.a.b(" up hmList:" + localArrayList3.size(), new Object[0]);
            com.tencent.beacon.d.a.b(" up dmList:" + localArrayList2.size(), new Object[0]);
            com.tencent.beacon.d.a.b(" up ipList:" + localArrayList1.size(), new Object[0]);
            com.tencent.beacon.d.a.b(" up erList:" + localArrayList4.size(), new Object[0]);
            continue;
          }
          HashMap localHashMap = new HashMap();
          if (localc2 == null)
            continue;
          localHashMap.put(Integer.valueOf(3), localc2.a());
          if (localb == null)
            continue;
          localHashMap.put(Integer.valueOf(1), localb.a());
          com.tencent.beacon.c.a.a locala = new com.tencent.beacon.c.a.a();
          locala.a = localHashMap;
          return locala;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        com.tencent.beacon.d.a.d(" CommonRecordUploadDatas.encode2MixPackage() error2", new Object[0]);
        b();
        return null;
      }
      com.tencent.beacon.c.b.b localb = null;
      continue;
      label759: label765: 
      do
      {
        localc2 = localc1;
        break label269;
        k++;
        break;
      }
      while (i == 0);
      c localc2 = null;
    }
  }

  public static b a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (h == null)
        h = new b(paramContext);
      b localb = h;
      return localb;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  // ERROR //
  public final com.tencent.beacon.c.a.b a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 216	com/tencent/beacon/event/m:d	()Lcom/tencent/beacon/event/m;
    //   5: astore_2
    //   6: aload_2
    //   7: ifnull +10 -> 17
    //   10: aload_2
    //   11: invokevirtual 219	com/tencent/beacon/event/m:h	()Z
    //   14: ifne +18 -> 32
    //   17: ldc 221
    //   19: iconst_0
    //   20: anewarray 80	java/lang/Object
    //   23: invokestatic 223	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   26: aconst_null
    //   27: astore_3
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_3
    //   31: areturn
    //   32: aload_2
    //   33: invokevirtual 227	com/tencent/beacon/event/m:k	()Lcom/tencent/beacon/event/d;
    //   36: astore 4
    //   38: aload 4
    //   40: ifnonnull +17 -> 57
    //   43: ldc 229
    //   45: iconst_0
    //   46: anewarray 80	java/lang/Object
    //   49: invokestatic 223	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   52: aconst_null
    //   53: astore_3
    //   54: goto -26 -> 28
    //   57: aload_0
    //   58: getfield 28	com/tencent/beacon/event/b:e	Lcom/tencent/beacon/c/a/b;
    //   61: ifnull +11 -> 72
    //   64: aload_0
    //   65: getfield 28	com/tencent/beacon/event/b:e	Lcom/tencent/beacon/c/a/b;
    //   68: astore_3
    //   69: goto -41 -> 28
    //   72: aload 4
    //   74: invokevirtual 233	com/tencent/beacon/event/d:h	()I
    //   77: i2l
    //   78: lstore 11
    //   80: aload_0
    //   81: getfield 26	com/tencent/beacon/event/b:d	Landroid/content/Context;
    //   84: invokestatic 238	com/tencent/beacon/d/b:a	(Landroid/content/Context;)Z
    //   87: ifeq +11 -> 98
    //   90: aload 4
    //   92: invokevirtual 240	com/tencent/beacon/event/d:g	()I
    //   95: i2l
    //   96: lstore 11
    //   98: lload 11
    //   100: lconst_0
    //   101: lcmp
    //   102: iflt +270 -> 372
    //   105: aload_0
    //   106: getfield 26	com/tencent/beacon/event/b:d	Landroid/content/Context;
    //   109: aconst_null
    //   110: lload 11
    //   112: invokestatic 243	com/tencent/beacon/applog/a:a	(Landroid/content/Context;[Ljava/lang/String;J)Ljava/util/List;
    //   115: astore 13
    //   117: aload 13
    //   119: ifnull +13 -> 132
    //   122: aload 13
    //   124: invokeinterface 44 1 0
    //   129: ifgt +17 -> 146
    //   132: ldc 245
    //   134: iconst_0
    //   135: anewarray 80	java/lang/Object
    //   138: invokestatic 247	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   141: aconst_null
    //   142: astore_3
    //   143: goto -115 -> 28
    //   146: aload 13
    //   148: invokeinterface 44 1 0
    //   153: istore 14
    //   155: iconst_1
    //   156: anewarray 80	java/lang/Object
    //   159: astore 15
    //   161: aload 15
    //   163: iconst_0
    //   164: iload 14
    //   166: invokestatic 186	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   169: aastore
    //   170: ldc 249
    //   172: aload 15
    //   174: invokestatic 247	com/tencent/beacon/d/a:h	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   177: aload_0
    //   178: aload 13
    //   180: invokespecial 251	com/tencent/beacon/event/b:a	(Ljava/util/List;)Lcom/tencent/beacon/c/a/a;
    //   183: astore 16
    //   185: aload 16
    //   187: astore 6
    //   189: aload_0
    //   190: iload 14
    //   192: anewarray 106	java/lang/Long
    //   195: putfield 30	com/tencent/beacon/event/b:f	[Ljava/lang/Long;
    //   198: iconst_0
    //   199: istore 17
    //   201: iload 17
    //   203: aload_0
    //   204: getfield 30	com/tencent/beacon/event/b:f	[Ljava/lang/Long;
    //   207: arraylength
    //   208: if_icmpge +34 -> 242
    //   211: aload_0
    //   212: getfield 30	com/tencent/beacon/event/b:f	[Ljava/lang/Long;
    //   215: iload 17
    //   217: aload 13
    //   219: iload 17
    //   221: invokeinterface 55 2 0
    //   226: checkcast 57	com/tencent/beacon/event/h
    //   229: invokevirtual 104	com/tencent/beacon/event/h:a	()J
    //   232: invokestatic 110	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   235: aastore
    //   236: iinc 17 1
    //   239: goto -38 -> 201
    //   242: aload 13
    //   244: invokeinterface 254 1 0
    //   249: aload 6
    //   251: ifnull +115 -> 366
    //   254: aload 6
    //   256: invokevirtual 255	com/tencent/beacon/c/a/a:a	()[B
    //   259: astore 8
    //   261: aload_0
    //   262: aload_0
    //   263: getfield 257	com/tencent/beacon/event/b:c	Landroid/content/Context;
    //   266: aload_0
    //   267: getfield 260	com/tencent/beacon/event/b:a	I
    //   270: aload 8
    //   272: invokestatic 263	com/tencent/beacon/event/b:a	(Landroid/content/Context;I[B)Lcom/tencent/beacon/c/a/b;
    //   275: putfield 28	com/tencent/beacon/event/b:e	Lcom/tencent/beacon/c/a/b;
    //   278: aload_0
    //   279: getfield 28	com/tencent/beacon/event/b:e	Lcom/tencent/beacon/c/a/b;
    //   282: astore_3
    //   283: goto -255 -> 28
    //   286: astore 5
    //   288: aconst_null
    //   289: astore 6
    //   291: aload 5
    //   293: invokevirtual 114	java/lang/Throwable:printStackTrace	()V
    //   296: iconst_1
    //   297: anewarray 80	java/lang/Object
    //   300: astore 7
    //   302: aload 7
    //   304: iconst_0
    //   305: aload 5
    //   307: invokevirtual 264	java/lang/Throwable:toString	()Ljava/lang/String;
    //   310: aastore
    //   311: ldc_w 266
    //   314: aload 7
    //   316: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   319: goto -70 -> 249
    //   322: astore_1
    //   323: aload_0
    //   324: monitorexit
    //   325: aload_1
    //   326: athrow
    //   327: astore 9
    //   329: iconst_1
    //   330: anewarray 80	java/lang/Object
    //   333: astore 10
    //   335: aload 10
    //   337: iconst_0
    //   338: aload 9
    //   340: invokevirtual 267	java/lang/Exception:toString	()Ljava/lang/String;
    //   343: aastore
    //   344: ldc_w 266
    //   347: aload 10
    //   349: invokestatic 118	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   352: aload_0
    //   353: invokevirtual 207	com/tencent/beacon/event/b:b	()V
    //   356: aconst_null
    //   357: astore_3
    //   358: goto -330 -> 28
    //   361: astore 5
    //   363: goto -72 -> 291
    //   366: aconst_null
    //   367: astore 8
    //   369: goto -108 -> 261
    //   372: aconst_null
    //   373: astore 13
    //   375: goto -258 -> 117
    //
    // Exception table:
    //   from	to	target	type
    //   72	98	286	java/lang/Throwable
    //   105	117	286	java/lang/Throwable
    //   122	132	286	java/lang/Throwable
    //   132	141	286	java/lang/Throwable
    //   146	185	286	java/lang/Throwable
    //   2	6	322	finally
    //   10	17	322	finally
    //   17	26	322	finally
    //   32	38	322	finally
    //   43	52	322	finally
    //   57	69	322	finally
    //   72	98	322	finally
    //   105	117	322	finally
    //   122	132	322	finally
    //   132	141	322	finally
    //   146	185	322	finally
    //   189	198	322	finally
    //   201	236	322	finally
    //   242	249	322	finally
    //   254	261	322	finally
    //   261	283	322	finally
    //   291	319	322	finally
    //   329	356	322	finally
    //   254	261	327	java/lang/Exception
    //   261	283	327	java/lang/Exception
    //   189	198	361	java/lang/Throwable
    //   201	236	361	java/lang/Throwable
    //   242	249	361	java/lang/Throwable
  }

  public final void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      com.tencent.beacon.d.a.b("done()", new Object[0]);
      if ((paramBoolean) && (this.f != null))
      {
        int i = this.f.length;
        if (i > 0)
          break label36;
      }
      while (true)
      {
        return;
        label36: int j = com.tencent.beacon.applog.a.a(this.d, this.f);
        com.tencent.beacon.d.a.b(" remove num :" + j, new Object[0]);
        this.e = null;
        this.f = null;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void b()
  {
    monitorenter;
    try
    {
      com.tencent.beacon.d.a.c(" encode failed, clear db data", new Object[0]);
      if ((this.f != null) && (this.f.length > 0))
      {
        int i = com.tencent.beacon.applog.a.a(this.d, this.f);
        com.tencent.beacon.d.a.b(" remove num :" + i, new Object[0]);
        this.e = null;
        this.f = null;
      }
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.b
 * JD-Core Version:    0.6.0
 */