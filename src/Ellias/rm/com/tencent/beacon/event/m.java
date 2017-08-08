package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.b.e.a;
import com.tencent.beacon.b.a.b;
import com.tencent.beacon.upload.UploadHandleListener;
import com.tencent.beacon.upload.h;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class m
  implements com.tencent.beacon.a.b.b, com.tencent.beacon.a.b.f, com.tencent.beacon.a.b.g
{
  public static boolean b;
  private static m d = null;
  private static int i = 100;
  public d a = null;
  public final Context c;
  private g e = null;
  private g f = null;
  private final boolean g;
  private boolean h = false;
  private boolean j = false;
  private com.tencent.beacon.upload.g k;
  private boolean l;
  private int m = 0;

  private m(Context paramContext, boolean paramBoolean, com.tencent.beacon.upload.g paramg, UploadHandleListener paramUploadHandleListener, d paramd)
  {
    this.c = paramContext.getApplicationContext();
    if (com.tencent.beacon.a.d.m() == null)
      com.tencent.beacon.a.d.a(this.c);
    this.k = paramg;
    if (paramg != null)
    {
      paramg.a(0, new n(paramContext));
      paramg.a(paramUploadHandleListener);
    }
    com.tencent.beacon.a.b.c localc = com.tencent.beacon.a.b.c.a(this.c);
    localc.a(this);
    localc.a(this);
    localc.a(1, this);
    localc.a(0, paramg);
    localc.a(1, paramg);
    if (paramd != null);
    for (this.a = paramd; ; this.a = new d())
    {
      this.e = new a(paramContext);
      this.f = new i(paramContext);
      this.g = paramBoolean;
      return;
    }
  }

  public static m a(Context paramContext, boolean paramBoolean, com.tencent.beacon.upload.g paramg, UploadHandleListener paramUploadHandleListener, d paramd)
  {
    monitorenter;
    try
    {
      if (d == null)
      {
        boolean bool = a(paramContext);
        b = bool;
        if (!bool)
        {
          com.tencent.beacon.d.a.e(" ua create instance ", new Object[0]);
          m localm2 = new m(paramContext, true, paramg, paramUploadHandleListener, null);
          d = localm2;
          localm2.a(true);
        }
      }
      m localm1 = d;
      return localm1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static com.tencent.beacon.upload.g a(Context paramContext, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      h localh = h.a(paramContext, paramBoolean);
      monitorexit;
      return localh;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.m = paramInt;
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
  private static boolean a(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 139
    //   3: ldc 141
    //   5: invokestatic 146	com/tencent/beacon/a/a:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   8: invokestatic 152	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   11: istore_3
    //   12: aload_0
    //   13: ldc 154
    //   15: ldc 156
    //   17: invokestatic 146	com/tencent/beacon/a/a:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   20: astore 4
    //   22: invokestatic 161	com/tencent/beacon/a/e:k	()Ljava/lang/String;
    //   25: aload 4
    //   27: invokevirtual 167	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   30: ifne +12 -> 42
    //   33: aload_0
    //   34: invokestatic 161	com/tencent/beacon/a/e:k	()Ljava/lang/String;
    //   37: invokestatic 170	com/tencent/beacon/a/a:c	(Landroid/content/Context;Ljava/lang/String;)V
    //   40: iconst_0
    //   41: istore_3
    //   42: iload_3
    //   43: getstatic 36	com/tencent/beacon/event/m:i	I
    //   46: if_icmpgt +29 -> 75
    //   49: iload_3
    //   50: iconst_1
    //   51: iadd
    //   52: istore 5
    //   54: aload_0
    //   55: new 172	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   62: iload 5
    //   64: invokevirtual 177	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   67: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   70: invokestatic 182	com/tencent/beacon/a/a:b	(Landroid/content/Context;Ljava/lang/String;)V
    //   73: iconst_0
    //   74: ireturn
    //   75: iconst_1
    //   76: istore_2
    //   77: ldc 184
    //   79: iconst_0
    //   80: anewarray 4	java/lang/Object
    //   83: invokestatic 123	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   86: iload_2
    //   87: ireturn
    //   88: astore_1
    //   89: iconst_0
    //   90: istore_2
    //   91: ldc 186
    //   93: iconst_0
    //   94: anewarray 4	java/lang/Object
    //   97: invokestatic 188	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   100: iload_2
    //   101: ireturn
    //   102: astore 6
    //   104: goto -13 -> 91
    //
    // Exception table:
    //   from	to	target	type
    //   0	40	88	java/lang/Exception
    //   42	49	88	java/lang/Exception
    //   54	73	88	java/lang/Exception
    //   77	86	102	java/lang/Exception
  }

  public static boolean a(String paramString, boolean paramBoolean1, long paramLong1, long paramLong2, Map<String, String> paramMap, boolean paramBoolean2)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Boolean.valueOf(paramBoolean1);
    arrayOfObject[2] = Long.valueOf(paramLong1);
    arrayOfObject[3] = Long.valueOf(paramLong2);
    arrayOfObject[4] = Boolean.valueOf(paramBoolean2);
    com.tencent.beacon.d.a.e(" onUA: %s,%b,%d,%d,%b", arrayOfObject);
    if (!m())
      return false;
    m localm = d();
    if (localm.a.a(paramString))
    {
      com.tencent.beacon.d.a.c("onUserAction return false, because eventName:[%s] is not allowed in server strategy!", new Object[] { paramString });
      return false;
    }
    if (paramBoolean2);
    for (g localg = localm.o(); localg != null; localg = localm.n())
      return localg.a(a.a(localm.c, paramString, paramBoolean1, paramLong1, paramLong2, paramMap));
    return false;
  }

  protected static boolean a(String paramString, boolean paramBoolean1, long paramLong1, long paramLong2, boolean paramBoolean2, CountItem[] paramArrayOfCountItem)
  {
    com.tencent.beacon.d.a.a(" onMergeUserActionCommon start", new Object[0]);
    Object[] arrayOfObject = new Object[6];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = Boolean.valueOf(paramBoolean1);
    arrayOfObject[2] = Long.valueOf(paramLong1);
    arrayOfObject[3] = Long.valueOf(paramLong2);
    arrayOfObject[4] = Boolean.valueOf(paramBoolean2);
    int n;
    if (paramArrayOfCountItem == null)
    {
      n = 0;
      arrayOfObject[5] = Integer.valueOf(n);
      com.tencent.beacon.d.a.e(" onMergeUserActionCommon:%s %b   elapase:%d  size:%d  isRealTime:%b  , items:%d", arrayOfObject);
      if (m())
        break label95;
    }
    while (true)
    {
      return false;
      n = paramArrayOfCountItem.length;
      break;
      label95: m localm = d();
      if (localm.a.a(paramString))
      {
        com.tencent.beacon.d.a.c("onMergeUserAction return false, because eventName:[%s] is not allowed in server strategy!", new Object[] { paramString });
        return false;
      }
      if (paramBoolean2);
      for (g localg = localm.o(); localg != null; localg = localm.n())
        return localg.a(paramString, paramBoolean1, paramLong1, paramLong2, paramArrayOfCountItem);
    }
  }

  private void c(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.h = true;
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

  public static m d()
  {
    monitorenter;
    try
    {
      m localm = d;
      monitorexit;
      return localm;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private void d(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (paramBoolean != h())
      {
        g localg1 = n();
        if (localg1 != null)
          localg1.a(paramBoolean);
        g localg2 = o();
        if (localg2 != null)
          localg2.a(paramBoolean);
        this.l = paramBoolean;
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void e()
  {
    h.a(d().c, false);
    a("rqd_appexited", true, 0L, 0L, null, false);
    m localm = d();
    if (localm != null)
      localm.d(false);
  }

  public static boolean g()
  {
    m localm = d();
    if (localm == null)
      com.tencent.beacon.d.a.c(" ua module not ready!", new Object[0]);
    do
      return false;
    while (!m());
    return localm.i();
  }

  private static boolean m()
  {
    m localm = d();
    if (localm == null)
    {
      com.tencent.beacon.d.a.d("isModuleAble:not init ua", new Object[0]);
      return false;
    }
    boolean bool = localm.h();
    if ((bool) && (localm.p()))
      bool = localm.q();
    if (bool)
    {
      d locald = localm.a;
      if (locald != null)
      {
        com.tencent.beacon.a.a.d locald1 = com.tencent.beacon.a.f.d(localm.c);
        if (locald1.g + locald1.f >= locald.f())
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(locald.f());
          com.tencent.beacon.d.a.c(" reach daily consume limited! %d ", arrayOfObject);
          return false;
        }
      }
    }
    return bool;
  }

  private g n()
  {
    monitorenter;
    try
    {
      g localg = this.e;
      monitorexit;
      return localg;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private g o()
  {
    monitorenter;
    try
    {
      g localg = this.f;
      monitorexit;
      return localg;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private boolean p()
  {
    monitorenter;
    try
    {
      boolean bool = this.g;
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

  private boolean q()
  {
    monitorenter;
    try
    {
      boolean bool = this.h;
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

  private void r()
  {
    try
    {
      if ((!h()) && (!this.a.n()))
        return;
      com.tencent.beacon.a.b.c localc = com.tencent.beacon.a.b.c.a(this.c);
      if (localc != null)
      {
        e.a locala = localc.b().b(1);
        if (locala != null)
        {
          String str = locala.b();
          if ((str != null) && (!"".equals(str.trim())))
          {
            new f(this.c).a(false);
            return;
          }
        }
      }
    }
    catch (Exception localException)
    {
      com.tencent.beacon.d.a.c(" startHeart failed! " + localException.getMessage(), new Object[0]);
    }
  }

  private void s()
  {
    if (com.tencent.beacon.a.e.a(this.c) == null)
    {
      com.tencent.beacon.d.a.c(" DeviceInfo == null?,return", new Object[0]);
      return;
    }
    HashMap localHashMap = new HashMap();
    localHashMap.put("A33", com.tencent.beacon.a.e.j(this.c));
    localHashMap.put("A63", "Y");
    if (com.tencent.beacon.a.a.c(this.c))
    {
      localHashMap.put("A21", "Y");
      label82: if (!com.tencent.beacon.a.b.c.a(this.c).d())
        break label285;
      localHashMap.put("A45", "Y");
      label108: if (!com.tencent.beacon.a.a.g(this.c))
        break label301;
      localHashMap.put("A66", "F");
    }
    while (true)
    {
      while (true)
      {
        localHashMap.put("A68", com.tencent.beacon.a.a.h(this.c));
        a("rqd_applaunched", true, 0L, 0L, localHashMap, true);
        try
        {
          if (!this.a.m())
            break;
          int n = this.a.l();
          com.tencent.beacon.a.c.a().a(106, new e(this.c, n), n * 1000, n * 1000);
          return;
        }
        catch (Exception localException)
        {
          com.tencent.beacon.d.a.c(" startAutoLaunchEvent failed! ", new Object[0]);
          com.tencent.beacon.d.a.b(" startAutoLaunchEvent failed! " + localException.getMessage(), new Object[0]);
          return;
        }
      }
      localHashMap.put("A21", "N");
      break label82;
      label285: localHashMap.put("A45", "N");
      break label108;
      label301: localHashMap.put("A66", "B");
    }
  }

  public final void a()
  {
    a(1 + l());
  }

  public final void a(com.tencent.beacon.a.b.e parame)
  {
    if (parame != null)
    {
      e.a locala = parame.b(1);
      if (locala != null)
      {
        boolean bool = locala.a();
        if (h() != bool)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Boolean.valueOf(bool);
          com.tencent.beacon.d.a.f("UAR onCommonStrategyChange setUsable:%b ", arrayOfObject);
          d(bool);
        }
      }
    }
  }

  public final void a(Map<String, String> paramMap)
  {
    if ((paramMap != null) && (paramMap.size() > 0) && (this.a != null))
      this.a.a(paramMap);
  }

  public final void a(boolean paramBoolean)
  {
    com.tencent.beacon.a.b.c localc = com.tencent.beacon.a.b.c.a(this.c);
    if (localc != null)
    {
      e.a locala = localc.b().b(1);
      if ((locala != null) && (locala.a() != paramBoolean))
      {
        locala.a(paramBoolean);
        if (paramBoolean != h())
          d(paramBoolean);
      }
    }
  }

  public final boolean a(List<String> paramList)
  {
    com.tencent.beacon.d.a.a(" testSpeedIp start", new Object[0]);
    if (!m())
      return false;
    if ((paramList == null) || (paramList.size() <= 0))
    {
      com.tencent.beacon.d.a.c(" ipList == null || ipList.size() <= 0", new Object[0]);
      return true;
    }
    1 local1 = new Runnable((String[])paramList.toArray(new String[0]))
    {
      public final void run()
      {
        if (this.a != null)
        {
          com.tencent.beacon.d.a.b(" start to ip test:", new Object[0]);
          String[] arrayOfString1 = this.a;
          int i = arrayOfString1.length;
          int j = 0;
          while (true)
            if (j < i)
            {
              String str = arrayOfString1[j];
              com.tencent.beacon.d.a.b(" ip:" + str, new Object[0]);
              long l1 = -1L;
              try
              {
                String[] arrayOfString2 = str.split(":");
                if ((arrayOfString2 == null) || (arrayOfString2.length != 2))
                  com.tencent.beacon.d.a.c(" ip wrong format ,not ip:port " + str, new Object[0]);
                while (true)
                {
                  com.tencent.beacon.d.a.b(" elapse:" + l1, new Object[0]);
                  HashMap localHashMap = new HashMap(1);
                  localHashMap.put("A29", str);
                  if (l1 <= 0L)
                    break label219;
                  bool = true;
                  m.a("rqd_ipSpeed", bool, l1, 0L, localHashMap, true);
                  j++;
                  break;
                  long l2 = com.tencent.beacon.b.a.a(arrayOfString2[0], Integer.parseInt(arrayOfString2[1]));
                  l1 = l2;
                }
              }
              catch (Throwable localThrowable)
              {
                while (true)
                {
                  localThrowable.printStackTrace();
                  continue;
                  label219: boolean bool = false;
                }
              }
            }
        }
      }
    };
    com.tencent.beacon.a.c.a().a(local1);
    return true;
  }

  // ERROR //
  public final void b()
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokespecial 430	com/tencent/beacon/event/m:c	(Z)V
    //   5: aload_0
    //   6: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   9: invokestatic 87	com/tencent/beacon/a/b/c:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/b/c;
    //   12: invokevirtual 291	com/tencent/beacon/a/b/c:b	()Lcom/tencent/beacon/a/b/e;
    //   15: astore 25
    //   17: aload 25
    //   19: ifnonnull +234 -> 253
    //   22: aload_0
    //   23: invokevirtual 388	com/tencent/beacon/event/m:l	()I
    //   26: iconst_2
    //   27: if_icmpge +225 -> 252
    //   30: aload_0
    //   31: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   34: invokestatic 433	com/tencent/beacon/upload/h:a	(Landroid/content/Context;)Lcom/tencent/beacon/upload/h;
    //   37: invokevirtual 435	com/tencent/beacon/upload/h:b	()Z
    //   40: ifeq +212 -> 252
    //   43: aload_0
    //   44: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   47: invokestatic 440	com/tencent/beacon/a/g:a	(Landroid/content/Context;)Lcom/tencent/beacon/a/g;
    //   50: ldc_w 442
    //   53: invokevirtual 443	com/tencent/beacon/a/g:a	(Ljava/lang/String;)Z
    //   56: ifeq +196 -> 252
    //   59: ldc_w 445
    //   62: iconst_1
    //   63: anewarray 4	java/lang/Object
    //   66: dup
    //   67: iconst_0
    //   68: ldc_w 442
    //   71: aastore
    //   72: invokestatic 123	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   75: aload_0
    //   76: getfield 42	com/tencent/beacon/event/m:a	Lcom/tencent/beacon/event/d;
    //   79: invokevirtual 447	com/tencent/beacon/event/d:k	()Z
    //   82: istore 22
    //   84: iload 22
    //   86: ifne +347 -> 433
    //   89: aload_0
    //   90: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   93: invokestatic 433	com/tencent/beacon/upload/h:a	(Landroid/content/Context;)Lcom/tencent/beacon/upload/h;
    //   96: invokevirtual 449	com/tencent/beacon/upload/h:c	()Z
    //   99: ifeq +153 -> 252
    //   102: aload_0
    //   103: invokespecial 451	com/tencent/beacon/event/m:r	()V
    //   106: aload_0
    //   107: invokespecial 453	com/tencent/beacon/event/m:s	()V
    //   110: aload_0
    //   111: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   114: invokestatic 455	com/tencent/beacon/a/f:b	(Landroid/content/Context;)Lcom/tencent/beacon/a/a/d;
    //   117: astore 8
    //   119: aload 8
    //   121: ifnull +102 -> 223
    //   124: aload 8
    //   126: getfield 457	com/tencent/beacon/a/a/d:d	J
    //   129: aload 8
    //   131: getfield 459	com/tencent/beacon/a/a/d:e	J
    //   134: ladd
    //   135: lstore 15
    //   137: aload 8
    //   139: getfield 459	com/tencent/beacon/a/a/d:e	J
    //   142: lstore 17
    //   144: new 326	java/util/HashMap
    //   147: dup
    //   148: invokespecial 327	java/util/HashMap:<init>	()V
    //   151: astore 19
    //   153: aload 19
    //   155: ldc_w 461
    //   158: new 172	java/lang/StringBuilder
    //   161: dup
    //   162: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   165: lload 15
    //   167: invokevirtual 464	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   170: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   173: invokevirtual 465	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   176: pop
    //   177: aload 19
    //   179: ldc_w 467
    //   182: new 172	java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial 173	java/lang/StringBuilder:<init>	()V
    //   189: lload 17
    //   191: invokevirtual 464	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   194: invokevirtual 180	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   197: invokevirtual 465	java/util/HashMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   200: pop
    //   201: ldc_w 469
    //   204: iconst_1
    //   205: lconst_0
    //   206: lconst_0
    //   207: aload 19
    //   209: iconst_0
    //   210: invokestatic 252	com/tencent/beacon/event/m:a	(Ljava/lang/String;ZJJLjava/util/Map;Z)Z
    //   213: ifeq +10 -> 223
    //   216: aload_0
    //   217: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   220: invokestatic 474	com/tencent/beacon/event/UserAction:clearSDKTotalConsume	(Landroid/content/Context;)V
    //   223: aload_0
    //   224: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   227: ldc_w 476
    //   230: ldc 156
    //   232: invokestatic 146	com/tencent/beacon/a/a:b	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   235: astore 10
    //   237: invokestatic 479	com/tencent/beacon/applog/a:b	()Ljava/lang/String;
    //   240: aload 10
    //   242: invokevirtual 167	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   245: istore 11
    //   247: iload 11
    //   249: ifeq +310 -> 559
    //   252: return
    //   253: aload 25
    //   255: iconst_1
    //   256: invokevirtual 296	com/tencent/beacon/a/b/e:b	(I)Lcom/tencent/beacon/a/b/e$a;
    //   259: astore 26
    //   261: aload 26
    //   263: ifnull +43 -> 306
    //   266: aload 26
    //   268: invokevirtual 482	com/tencent/beacon/a/b/e$a:d	()Ljava/util/Set;
    //   271: ifnull +35 -> 306
    //   274: aload 26
    //   276: invokevirtual 482	com/tencent/beacon/a/b/e$a:d	()Ljava/util/Set;
    //   279: invokeinterface 485 1 0
    //   284: ifle +22 -> 306
    //   287: aload_0
    //   288: getfield 42	com/tencent/beacon/event/m:a	Lcom/tencent/beacon/event/d;
    //   291: ifnull +15 -> 306
    //   294: aload_0
    //   295: getfield 42	com/tencent/beacon/event/m:a	Lcom/tencent/beacon/event/d;
    //   298: aload 26
    //   300: invokevirtual 482	com/tencent/beacon/a/b/e$a:d	()Ljava/util/Set;
    //   303: invokevirtual 488	com/tencent/beacon/event/d:a	(Ljava/util/Set;)V
    //   306: aload_0
    //   307: invokevirtual 245	com/tencent/beacon/event/m:h	()Z
    //   310: ifeq +110 -> 420
    //   313: aload 26
    //   315: ifnull +105 -> 420
    //   318: aload_0
    //   319: invokevirtual 245	com/tencent/beacon/event/m:h	()Z
    //   322: ifeq +86 -> 408
    //   325: aload_0
    //   326: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   329: invokestatic 490	com/tencent/beacon/applog/a:d	(Landroid/content/Context;)I
    //   332: istore 27
    //   334: goto +283 -> 617
    //   337: iload 28
    //   339: ifeq -317 -> 22
    //   342: iconst_1
    //   343: anewarray 4	java/lang/Object
    //   346: astore 29
    //   348: aload 29
    //   350: iconst_0
    //   351: iconst_1
    //   352: invokestatic 236	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   355: aastore
    //   356: ldc_w 492
    //   359: aload 29
    //   361: invokestatic 123	com/tencent/beacon/d/a:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   364: invokestatic 371	com/tencent/beacon/a/c:a	()Lcom/tencent/beacon/a/c;
    //   367: new 494	com/tencent/beacon/event/m$3
    //   370: dup
    //   371: aload_0
    //   372: invokespecial 497	com/tencent/beacon/event/m$3:<init>	(Lcom/tencent/beacon/event/m;)V
    //   375: invokevirtual 426	com/tencent/beacon/a/c:a	(Ljava/lang/Runnable;)V
    //   378: goto -356 -> 22
    //   381: astore_1
    //   382: aload_1
    //   383: invokevirtual 500	java/lang/Throwable:printStackTrace	()V
    //   386: iconst_1
    //   387: anewarray 4	java/lang/Object
    //   390: astore_2
    //   391: aload_2
    //   392: iconst_0
    //   393: aload_1
    //   394: invokevirtual 501	java/lang/Throwable:toString	()Ljava/lang/String;
    //   397: aastore
    //   398: ldc_w 503
    //   401: aload_2
    //   402: invokestatic 262	com/tencent/beacon/d/a:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   405: goto -383 -> 22
    //   408: iconst_m1
    //   409: istore 27
    //   411: goto +206 -> 617
    //   414: iconst_0
    //   415: istore 28
    //   417: goto -80 -> 337
    //   420: ldc_w 505
    //   423: iconst_0
    //   424: anewarray 4	java/lang/Object
    //   427: invokestatic 383	com/tencent/beacon/d/a:b	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   430: goto -408 -> 22
    //   433: aload_0
    //   434: getfield 42	com/tencent/beacon/event/m:a	Lcom/tencent/beacon/event/d;
    //   437: invokevirtual 507	com/tencent/beacon/event/d:j	()I
    //   440: istore 23
    //   442: iload 23
    //   444: istore 4
    //   446: aload_0
    //   447: getfield 42	com/tencent/beacon/event/m:a	Lcom/tencent/beacon/event/d;
    //   450: invokevirtual 509	com/tencent/beacon/event/d:i	()I
    //   453: istore 24
    //   455: iload 24
    //   457: istore 5
    //   459: iload 4
    //   461: istore 6
    //   463: iload 6
    //   465: ifle -376 -> 89
    //   468: iload 5
    //   470: ifle -381 -> 89
    //   473: new 511	com/tencent/beacon/a/b
    //   476: dup
    //   477: aload_0
    //   478: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   481: iload 6
    //   483: iload 5
    //   485: new 513	com/tencent/beacon/event/l
    //   488: dup
    //   489: aload_0
    //   490: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   493: invokespecial 514	com/tencent/beacon/event/l:<init>	(Landroid/content/Context;)V
    //   496: invokespecial 517	com/tencent/beacon/a/b:<init>	(Landroid/content/Context;IILjava/lang/Runnable;)V
    //   499: astore 7
    //   501: invokestatic 371	com/tencent/beacon/a/c:a	()Lcom/tencent/beacon/a/c;
    //   504: bipush 104
    //   506: aload 7
    //   508: iload 6
    //   510: sipush 1000
    //   513: imul
    //   514: i2l
    //   515: iload 6
    //   517: sipush 1000
    //   520: imul
    //   521: i2l
    //   522: invokevirtual 379	com/tencent/beacon/a/c:a	(ILjava/lang/Runnable;JJ)V
    //   525: goto -436 -> 89
    //   528: astore_3
    //   529: iconst_m1
    //   530: istore 4
    //   532: aload_3
    //   533: invokevirtual 500	java/lang/Throwable:printStackTrace	()V
    //   536: iconst_m1
    //   537: istore 5
    //   539: iload 4
    //   541: istore 6
    //   543: goto -80 -> 463
    //   546: astore 9
    //   548: ldc_w 519
    //   551: iconst_0
    //   552: anewarray 4	java/lang/Object
    //   555: invokestatic 188	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   558: return
    //   559: new 521	com/tencent/beacon/event/m$4
    //   562: dup
    //   563: aload_0
    //   564: invokespecial 522	com/tencent/beacon/event/m$4:<init>	(Lcom/tencent/beacon/event/m;)V
    //   567: astore 12
    //   569: invokestatic 371	com/tencent/beacon/a/c:a	()Lcom/tencent/beacon/a/c;
    //   572: aload 12
    //   574: ldc2_w 523
    //   577: invokevirtual 527	com/tencent/beacon/a/c:a	(Ljava/lang/Runnable;J)V
    //   580: aload_0
    //   581: getfield 60	com/tencent/beacon/event/m:c	Landroid/content/Context;
    //   584: ldc_w 476
    //   587: invokestatic 479	com/tencent/beacon/applog/a:b	()Ljava/lang/String;
    //   590: invokestatic 530	com/tencent/beacon/a/a:a	(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)Z
    //   593: pop
    //   594: return
    //   595: astore 13
    //   597: ldc_w 519
    //   600: iconst_0
    //   601: anewarray 4	java/lang/Object
    //   604: invokestatic 188	com/tencent/beacon/d/a:c	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   607: aload 13
    //   609: invokevirtual 531	java/lang/Exception:printStackTrace	()V
    //   612: return
    //   613: astore_3
    //   614: goto -82 -> 532
    //   617: iload 27
    //   619: ifle -205 -> 414
    //   622: iconst_1
    //   623: istore 28
    //   625: goto -288 -> 337
    //
    // Exception table:
    //   from	to	target	type
    //   5	17	381	java/lang/Throwable
    //   253	261	381	java/lang/Throwable
    //   266	306	381	java/lang/Throwable
    //   306	313	381	java/lang/Throwable
    //   318	334	381	java/lang/Throwable
    //   342	378	381	java/lang/Throwable
    //   420	430	381	java/lang/Throwable
    //   75	84	528	java/lang/Throwable
    //   433	442	528	java/lang/Throwable
    //   223	247	546	java/lang/Exception
    //   580	594	595	java/lang/Exception
    //   446	455	613	java/lang/Throwable
  }

  public final void b(boolean paramBoolean)
  {
    if ((h.a(this.c) != null) && (paramBoolean != h.a(this.c).c()))
    {
      if (paramBoolean)
      {
        h.a(this.c).a(true);
        r();
        s();
      }
    }
    else
      return;
    h.a(this.c).a(false);
    Context localContext = this.c;
    com.tencent.beacon.a.c.a().a(108, true);
    com.tencent.beacon.a.a.a(localContext, "HEART_DENGTA", com.tencent.beacon.a.e.k());
    com.tencent.beacon.d.a.a("heartbeat uploaded sucess!", new Object[0]);
  }

  public final boolean b(List<String> paramList)
  {
    if (!m())
      return false;
    if ((paramList == null) || (paramList.size() <= 0))
    {
      com.tencent.beacon.d.a.c(" dnsList == null || dnsList.size() <= 0", new Object[0]);
      return true;
    }
    2 local2 = new Runnable((String[])paramList.toArray(new String[0]))
    {
      public final void run()
      {
        int j;
        String str;
        a.b localb;
        if (this.a != null)
        {
          com.tencent.beacon.d.a.b(" start domain test:", new Object[0]);
          String[] arrayOfString = this.a;
          int i = arrayOfString.length;
          j = 0;
          if (j < i)
          {
            str = arrayOfString[j];
            com.tencent.beacon.d.a.b(" dns:" + str, new Object[0]);
            localb = com.tencent.beacon.b.a.a(str, false);
            if (localb != null)
              break label74;
          }
        }
        return;
        label74: long l = localb.a + localb.b + localb.c + localb.d + localb.e;
        com.tencent.beacon.d.a.b(" elapse:" + l, new Object[0]);
        HashMap localHashMap = new HashMap(1);
        localHashMap.put("A34", str);
        localHashMap.put("A35", String.valueOf(localb.a));
        localHashMap.put("A36", String.valueOf(localb.c));
        localHashMap.put("A37", String.valueOf(localb.d));
        localHashMap.put("A38", String.valueOf(localb.e));
        localHashMap.put("A40", String.valueOf(localb.b));
        if (l > 0L);
        for (boolean bool = true; ; bool = false)
        {
          m.a("rqd_domainSpeed", bool, l, 0L, localHashMap, true);
          j++;
          break;
        }
      }
    };
    com.tencent.beacon.d.a.a(" post the test task", new Object[0]);
    com.tencent.beacon.a.c.a().a(local2);
    return true;
  }

  public final void c()
  {
    Context localContext = this.c;
    com.tencent.beacon.d.a.a(" RecordDAO.deleteRecords() start", new Object[0]);
    int n = com.tencent.beacon.a.a.a.a(localContext, new int[] { 1, 2, 3, 4 }, -1L, 9223372036854775807L);
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(n);
    com.tencent.beacon.d.a.e(" ua first clean :%d", arrayOfObject1);
    int i1 = com.tencent.beacon.applog.a.b(this.c, 101);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(i1);
    com.tencent.beacon.d.a.e(" ua remove strategy :%d", arrayOfObject2);
  }

  public final void f()
  {
    com.tencent.beacon.d.a.a(" closeUseInfoEvent start", new Object[0]);
    try
    {
      this.a.a(false);
      com.tencent.beacon.a.c.a().a(104, true);
      return;
    }
    catch (Exception localException)
    {
      com.tencent.beacon.d.a.d(" closeUseInfoEvent function error:" + localException.getMessage(), new Object[0]);
    }
  }

  public final boolean h()
  {
    monitorenter;
    try
    {
      boolean bool = this.l;
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

  public final boolean i()
  {
    int n;
    if (h())
      n = com.tencent.beacon.applog.a.d(this.c);
    while (true)
      if (n > 0)
        try
        {
          if (this.k != null)
          {
            b localb = b.a(this.c);
            this.k.a(localb);
          }
          return true;
          n = -1;
        }
        catch (Throwable localThrowable)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localThrowable.toString();
          com.tencent.beacon.d.a.c(" up common error: %s", arrayOfObject);
          localThrowable.printStackTrace();
        }
    return false;
  }

  public final com.tencent.beacon.upload.g j()
  {
    return this.k;
  }

  public final d k()
  {
    return this.a;
  }

  public final int l()
  {
    monitorenter;
    try
    {
      int n = this.m;
      monitorexit;
      return n;
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
 * Qualified Name:     com.tencent.beacon.event.m
 * JD-Core Version:    0.6.0
 */