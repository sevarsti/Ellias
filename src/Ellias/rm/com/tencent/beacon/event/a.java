package com.tencent.beacon.event;

import android.content.Context;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class a
  implements g
{
  private Context a;
  private boolean b = false;
  private List<h> c;
  private Runnable d = new Runnable()
  {
    public final void run()
    {
      a.this.a();
    }
  };
  private Runnable e = new Runnable()
  {
    public final void run()
    {
      com.tencent.beacon.d.a.f(" maxN to up", new Object[0]);
      try
      {
        m.g();
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  };

  public a(Context paramContext)
  {
    this.a = paramContext;
    this.c = new ArrayList(25);
  }

  public static h a(Context paramContext, String paramString, boolean paramBoolean, long paramLong1, long paramLong2, Map<String, String> paramMap)
  {
    if (paramString == null)
      return null;
    com.tencent.beacon.a.d locald = com.tencent.beacon.a.d.m();
    if (locald == null)
    {
      com.tencent.beacon.d.a.d("  CommonInfo have not been Created return null!", new Object[0]);
      return null;
    }
    String str1 = UserAction.getUserID();
    long l = locald.h() + new Date().getTime();
    String str2 = locald.g();
    String str3 = com.tencent.beacon.d.b.c(paramContext);
    if (str3 == null)
      str3 = "null";
    HashMap localHashMap = new HashMap();
    if (UserAction.a != null)
      localHashMap.putAll(UserAction.a);
    if (paramMap != null)
      localHashMap.putAll(paramMap);
    localHashMap.put("A1", str1);
    localHashMap.put("QQ", UserAction.getQQ());
    localHashMap.put("A19", str3);
    localHashMap.put("A28", str2);
    localHashMap.put("A25", paramBoolean);
    localHashMap.put("A26", paramLong1);
    localHashMap.put("A27", paramLong2);
    localHashMap.put("A2", locald.i());
    c localc = c.a(paramContext);
    localHashMap.put("A4", localc.b());
    localHashMap.put("A6", localc.a());
    localHashMap.put("A7", localc.c());
    localHashMap.put("A3", com.tencent.beacon.b.a.a(paramContext).a());
    localHashMap.put("A23", localc.d());
    localHashMap.put("A67", com.tencent.beacon.a.a.i(paramContext));
    localHashMap.put("A76", com.tencent.beacon.a.a.a());
    h localh = new h();
    localh.b(paramString);
    localh.b(l);
    localh.a("UA");
    localh.a(localHashMap);
    localh.c(1L);
    boolean bool = paramLong1 < 1200000L;
    int i = 0;
    if (!bool)
      i = 1;
    if (paramLong2 >= 50000000L)
      i++;
    if (i > 0)
    {
      localh.d(i);
      localh.b(true);
      return localh;
    }
    localh.d(0L);
    localh.b(false);
    return localh;
  }

  public static com.tencent.beacon.c.d.b b(h paramh)
  {
    if ((paramh == null) || (!"IP".equals(paramh.b())))
      return null;
    Map localMap = paramh.e();
    if (localMap == null)
      return null;
    try
    {
      com.tencent.beacon.c.d.b localb = new com.tencent.beacon.c.d.b();
      localb.a = ((String)localMap.get("A19"));
      String str = (String)localMap.get("A26");
      if (str == null)
        str = "-1";
      localb.e = Long.parseLong(str);
      String[] arrayOfString = paramh.d().split(":");
      localb.c = arrayOfString[0];
      localb.d = Integer.parseInt(arrayOfString[1]);
      localb.b = ((String)localMap.get("A28"));
      localb.f = paramh.c();
      HashMap localHashMap = new HashMap();
      localHashMap.put("A33", localMap.get("A33"));
      localHashMap.put("A3", localMap.get("A3"));
      localHashMap.put("A20", localMap.get("A20"));
      localHashMap.put("A74", localMap.get("A74"));
      if (localMap.get("test") != null)
        localHashMap.put("test", "Y");
      localb.g = com.tencent.beacon.applog.a.a(localHashMap);
      return localb;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d(localThrowable.getMessage(), new Object[0]);
    }
    return null;
  }

  private List<h> b()
  {
    monitorenter;
    try
    {
      ArrayList localArrayList;
      if ((this.c != null) && (this.c.size() > 0))
      {
        boolean bool = c();
        if (bool);
      }
      else
      {
        localArrayList = null;
      }
      while (true)
      {
        return localArrayList;
        localArrayList = new ArrayList();
        localArrayList.addAll(this.c);
        this.c.clear();
        com.tencent.beacon.d.a.b(" get MN:" + localArrayList.size(), new Object[0]);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static com.tencent.beacon.c.d.a c(h paramh)
  {
    if ((paramh == null) || (!"DN".equals(paramh.b())))
      return null;
    Map localMap = paramh.e();
    if (localMap == null)
      return null;
    try
    {
      com.tencent.beacon.c.d.a locala = new com.tencent.beacon.c.d.a();
      locala.a = ((String)localMap.get("A19"));
      locala.c = paramh.d();
      locala.j = ((String)localMap.get("A34"));
      locala.d = Long.parseLong((String)localMap.get("A35"));
      locala.f = Long.parseLong((String)localMap.get("A36"));
      locala.g = Long.parseLong((String)localMap.get("A37"));
      locala.h = Long.parseLong((String)localMap.get("A38"));
      locala.b = ((String)localMap.get("A28"));
      locala.i = ((String)localMap.get("A39"));
      locala.e = Long.parseLong((String)localMap.get("A40"));
      locala.k = paramh.c();
      HashMap localHashMap = new HashMap();
      localHashMap.put("A33", localMap.get("A33"));
      localHashMap.put("A3", localMap.get("A3"));
      localHashMap.put("A20", localMap.get("A20"));
      localHashMap.put("A74", localMap.get("A74"));
      if (localMap.get("test") != null)
        localHashMap.put("test", "Y");
      locala.l = com.tencent.beacon.applog.a.a(localHashMap);
      return locala;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d(localThrowable.getMessage(), new Object[0]);
    }
    return null;
  }

  private boolean c()
  {
    monitorenter;
    try
    {
      boolean bool = this.b;
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

  public static com.tencent.beacon.c.d.d d(h paramh)
  {
    if ((paramh == null) || (!"HO".equals(paramh.b())))
      return null;
    Map localMap = paramh.e();
    if (localMap == null)
      return null;
    try
    {
      com.tencent.beacon.c.d.d locald = new com.tencent.beacon.c.d.d();
      locald.a = ((String)localMap.get("A19"));
      String[] arrayOfString = ((String)localMap.get("hostip")).split(":");
      locald.m = Integer.parseInt(arrayOfString[1]);
      locald.b = ((String)localMap.get("A28"));
      locald.c = ((String)localMap.get("A34"));
      locald.k = arrayOfString[0];
      locald.l = paramh.d();
      locald.d = Long.parseLong((String)localMap.get("A35"));
      locald.e = Long.parseLong((String)localMap.get("A40"));
      locald.f = Long.parseLong((String)localMap.get("A36"));
      locald.g = Long.parseLong((String)localMap.get("A37"));
      locald.h = Long.parseLong((String)localMap.get("A38"));
      locald.j = paramh.c();
      locald.i = ((String)localMap.get("A39"));
      HashMap localHashMap = new HashMap();
      localHashMap.put("A33", localMap.get("A33"));
      localHashMap.put("A3", localMap.get("A3"));
      localHashMap.put("A20", localMap.get("A20"));
      localHashMap.put("A74", localMap.get("A74"));
      locald.n = com.tencent.beacon.applog.a.a(localHashMap);
      return locald;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d(localThrowable.getMessage(), new Object[0]);
    }
    return null;
  }

  public static com.tencent.beacon.c.b.a e(h paramh)
  {
    if ((paramh == null) || (!"UA".equals(paramh.b())))
      return null;
    Map localMap = paramh.e();
    if (localMap == null)
      return null;
    try
    {
      com.tencent.beacon.c.b.a locala = new com.tencent.beacon.c.b.a();
      locala.a = ((String)localMap.get("A19"));
      locala.c = paramh.d();
      locala.h = paramh.c();
      locala.b = ((String)localMap.get("A28"));
      locala.f = Long.parseLong((String)localMap.get("A26"));
      locala.d = Boolean.parseBoolean((String)localMap.get("A25"));
      locala.e = Long.parseLong((String)localMap.get("A27"));
      if (paramh.h())
        localMap.put("C9", paramh.i());
      if (paramh.f())
        localMap.put("C3", paramh.g());
      locala.g = com.tencent.beacon.applog.a.a(localMap);
      if (paramh.f());
      for (int i = 1; ; i = 0)
      {
        locala.i = i;
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = locala.c;
        arrayOfObject[1] = Boolean.valueOf(locala.d);
        arrayOfObject[2] = locala.g;
        com.tencent.beacon.d.a.b("new event record:\neventName:%s\neventResult:%b\neventValue:%s", arrayOfObject);
        return locala;
      }
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      com.tencent.beacon.d.a.d(localThrowable.getMessage(), new Object[0]);
    }
    return null;
  }

  protected final void a()
  {
    if (!c())
      com.tencent.beacon.d.a.c(" err su 1R", new Object[0]);
    Long[] arrayOfLong;
    com.tencent.beacon.upload.h localh;
    do
    {
      List localList;
      do
      {
        return;
        localList = b();
      }
      while ((localList == null) || (localList.size() <= 0));
      arrayOfLong = com.tencent.beacon.applog.a.c(this.a, localList);
      localh = com.tencent.beacon.upload.h.a(this.a);
    }
    while (arrayOfLong == null);
    long l = m.d().k().e();
    if (com.tencent.beacon.d.b.a(this.a))
    {
      com.tencent.beacon.d.a.e(" onwifi, so half mSZ " + l, new Object[0]);
      l /= 2L;
    }
    int i;
    if (com.tencent.beacon.applog.a.d(this.a) >= l)
    {
      i = 1;
      label129: if ((i == 0) || (!localh.b()) || (!localh.c()))
        break label206;
      if (localh.a() < 10)
        break label208;
      com.tencent.beacon.d.a.c(" doUpload request failed 10 times sleep...", new Object[0]);
      com.tencent.beacon.a.c.a().a(this.e, 600000L);
      com.tencent.beacon.upload.h.a(this.a).a(0);
    }
    while (true)
    {
      com.tencent.beacon.d.a.e(" max Up", new Object[0]);
      return;
      i = 0;
      break label129;
      label206: break;
      label208: this.e.run();
    }
  }

  public final void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (this.b != paramBoolean)
      {
        if (!paramBoolean)
          break label51;
        this.b = paramBoolean;
        long l = 1000 * m.d().k().d();
        com.tencent.beacon.a.c.a().a(102, this.d, l, l);
      }
      while (true)
      {
        return;
        label51: com.tencent.beacon.a.c.a().a(102, true);
        a();
        this.b = paramBoolean;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final boolean a(h paramh)
  {
    int i = 0;
    monitorenter;
    while (true)
    {
      try
      {
        Object[] arrayOfObject = new Object[3];
        if (paramh != null)
          continue;
        String str = "null";
        arrayOfObject[0] = str;
        arrayOfObject[1] = Boolean.valueOf(false);
        if (paramh != null)
          continue;
        Object localObject2 = "null";
        arrayOfObject[2] = localObject2;
        com.tencent.beacon.d.a.f(" BF eN:%s   isRT:%b  isCR:%b", arrayOfObject);
        if ((this.a != null) && (paramh != null) && (this.b))
          continue;
        com.tencent.beacon.d.a.d(" err BF 1R", new Object[0]);
        return i;
        str = paramh.d();
        continue;
        localObject2 = Boolean.valueOf(paramh.f());
        continue;
        if (!c())
        {
          com.tencent.beacon.d.a.d(" err BF 2R", new Object[0]);
          i = 0;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      d locald = m.d().k();
      int j = locald.c();
      long l = 1000 * locald.d();
      int k = this.c.size();
      if (k >= j)
      {
        com.tencent.beacon.d.a.d(" err BF 3R! list size:" + k, new Object[0]);
        i = 0;
        continue;
      }
      this.c.add(paramh);
      if (k + 1 >= j)
      {
        com.tencent.beacon.d.a.f(" BF mN!}", new Object[0]);
        com.tencent.beacon.a.c.a().a(this.d);
        com.tencent.beacon.a.c.a().a(102, this.d, l, l);
      }
      i = 1;
    }
  }

  public final boolean a(String paramString, boolean paramBoolean, long paramLong1, long paramLong2, CountItem[] paramArrayOfCountItem)
  {
    monitorenter;
    boolean bool1;
    Object localObject2;
    try
    {
      com.tencent.beacon.d.a.e(" onUAC %s", new Object[] { paramString });
      String str;
      if ((paramString == null) || (this.a == null))
        if (" err 1R " + this.a == null)
        {
          str = "context";
          com.tencent.beacon.d.a.e(str, new Object[0]);
        }
      h localh2;
      for (bool1 = false; ; bool1 = false)
      {
        return bool1;
        str = "en";
        break;
        Iterator localIterator = this.c.iterator();
        while (true)
        {
          boolean bool2 = localIterator.hasNext();
          localObject2 = null;
          if (!bool2)
            break;
          h localh1 = (h)localIterator.next();
          if ((!localh1.f()) || (!localh1.d().equals(paramString)))
            continue;
          localObject2 = localh1;
        }
        if (localObject2 != null)
          break label489;
        com.tencent.beacon.d.a.e(" onUAC add new", new Object[0]);
        HashMap localHashMap1 = new HashMap();
        if ((paramArrayOfCountItem != null) && (paramArrayOfCountItem.length > 0))
        {
          int i = paramArrayOfCountItem.length;
          for (int j = 0; j < i; j++)
          {
            CountItem localCountItem1 = paramArrayOfCountItem[j];
            localHashMap1.put(localCountItem1.name, Long.toString(localCountItem1.addValue));
          }
        }
        localh2 = a(this.a, paramString, paramBoolean, paramLong1, paramLong2, localHashMap1);
        if (localh2 != null)
          break label255;
      }
      label255: Map localMap1 = localh2.e();
      localMap1.put("C1", Long.toString(localh2.c()));
      localMap1.put("C2", Long.toString(1L));
      if (paramBoolean)
      {
        localMap1.put("C4", Long.toString(paramLong1));
        localMap1.put("C5", Long.toString(paramLong2));
        localMap1.put("C6", Long.toString(0L));
        localMap1.put("C7", Long.toString(0L));
        localMap1.put("C8", Long.toString(0L));
      }
      while (true)
      {
        localh2.a(true);
        com.tencent.beacon.d.a.b(" add record, return!", new Object[0]);
        bool1 = a(localh2);
        break;
        localMap1.put("C4", Long.toString(0L));
        localMap1.put("C5", Long.toString(0L));
        localMap1.put("C6", Long.toString(1L));
        localMap1.put("C7", Long.toString(paramLong1));
        localMap1.put("C8", Long.toString(paramLong2));
      }
    }
    finally
    {
      monitorexit;
    }
    label489: com.tencent.beacon.d.a.e(" onUAC up O", new Object[0]);
    localObject2.c(1L + localObject2.g());
    if (paramLong1 >= 1200000L)
    {
      localObject2.b(true);
      localObject2.d(1L + localObject2.i());
    }
    if (paramLong2 >= 50000000L)
    {
      localObject2.b(true);
      localObject2.d(1L + localObject2.i());
    }
    Map localMap2 = localObject2.e();
    HashMap localHashMap2;
    if (localMap2 == null)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localObject2.d();
      com.tencent.beacon.d.a.c(" err ? ep==null: %s", arrayOfObject);
      localHashMap2 = new HashMap();
      localObject2.a(localHashMap2);
    }
    for (Object localObject3 = localHashMap2; ; localObject3 = localMap2)
    {
      ((Map)localObject3).put("C1", Long.toString(new Date().getTime()));
      com.tencent.beacon.applog.a.a((Map)localObject3, "A26", paramLong1);
      com.tencent.beacon.applog.a.a((Map)localObject3, "A27", paramLong2);
      com.tencent.beacon.applog.a.a((Map)localObject3, "C2", 1L);
      if (paramBoolean)
      {
        com.tencent.beacon.applog.a.a((Map)localObject3, "C4", paramLong1);
        com.tencent.beacon.applog.a.a((Map)localObject3, "C5", paramLong2);
      }
      while ((paramArrayOfCountItem != null) && (paramArrayOfCountItem.length > 0))
      {
        int k = paramArrayOfCountItem.length;
        for (int m = 0; m < k; m++)
        {
          CountItem localCountItem2 = paramArrayOfCountItem[m];
          com.tencent.beacon.applog.a.a((Map)localObject3, localCountItem2.name, localCountItem2.addValue);
        }
        com.tencent.beacon.applog.a.a((Map)localObject3, "C6", 1L);
        com.tencent.beacon.applog.a.a((Map)localObject3, "C7", paramLong1);
        com.tencent.beacon.applog.a.a((Map)localObject3, "C8", paramLong2);
      }
      bool1 = true;
      break;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.a
 * JD-Core Version:    0.6.0
 */