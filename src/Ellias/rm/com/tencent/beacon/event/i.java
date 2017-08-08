package com.tencent.beacon.event;

import android.content.Context;
import com.tencent.beacon.a.c;
import com.tencent.beacon.a.e;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class i
  implements g
{
  private boolean a = false;
  private List<h> b;
  private Context c;
  private Runnable d = new Runnable()
  {
    public final void run()
    {
      try
      {
        i.this.b();
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  };

  public i(Context paramContext)
  {
    this.c = paramContext;
    this.b = Collections.synchronizedList(new ArrayList(25));
  }

  private boolean c()
  {
    monitorenter;
    try
    {
      boolean bool = this.a;
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

  public final List<h> a()
  {
    monitorenter;
    try
    {
      ArrayList localArrayList;
      if ((this.b != null) && (this.b.size() > 0))
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
        localArrayList.addAll(this.b);
        this.b.clear();
        com.tencent.beacon.d.a.b(" get realEventCnt in Mem:" + localArrayList.size(), new Object[0]);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      if (this.a != paramBoolean)
      {
        if (!paramBoolean)
          break label53;
        this.a = paramBoolean;
        long l = 1000 * m.d().k().b();
        c.a().a(103, this.d, 5000L, l);
      }
      while (true)
      {
        return;
        label53: c.a().a(103, true);
        if ((this.b != null) && (this.b.size() > 0))
          com.tencent.beacon.applog.a.c(this.c, this.b);
        this.a = paramBoolean;
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
        arrayOfObject[1] = Boolean.valueOf(true);
        if (paramh != null)
          continue;
        Object localObject2 = "null";
        arrayOfObject[2] = localObject2;
        com.tencent.beacon.d.a.f(" BF eN:%s   isRT:%b  isCR:%b", arrayOfObject);
        if ((this.c != null) && (paramh != null))
          continue;
        com.tencent.beacon.d.a.c("processUA return false, context is null or bean is null !", new Object[0]);
        return i;
        str = paramh.d();
        continue;
        localObject2 = Boolean.valueOf(paramh.f());
        continue;
        if (!c())
        {
          com.tencent.beacon.d.a.c("processUA return false, isEnable is false !", new Object[0]);
          i = 0;
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      d locald = m.d().k();
      int j = locald.a();
      long l = 1000 * locald.b();
      if (this.b.size() >= j)
      {
        com.tencent.beacon.d.a.f(" BF mN!", new Object[0]);
        c.a().a(this.d);
        c.a().a(103, this.d, l, l);
      }
      this.b.add(paramh);
      if (this.b.size() >= j)
        com.tencent.beacon.d.a.c(" err BF 3R! num:" + this.b.size(), new Object[0]);
      if ("rqd_applaunched".equals(paramh.d()))
        c.a().a(this.d);
      com.tencent.beacon.d.a.a("processUA:true!", new Object[0]);
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
      com.tencent.beacon.d.a.e(" onUAC  %s RT", new Object[] { paramString });
      String str;
      if ((paramString == null) || (this.c == null))
        if (" err 1R " + this.c == null)
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
        Iterator localIterator = this.b.iterator();
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
          break label462;
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
        localh2 = a.a(this.c, paramString, paramBoolean, paramLong1, paramLong2, localHashMap1);
        if (localh2 != null)
          break label250;
      }
      label250: Map localMap1 = localh2.e();
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
    label462: com.tencent.beacon.d.a.e(" onUAC up O", new Object[0]);
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
      com.tencent.beacon.d.a.c(" err ? ep==null} %s", arrayOfObject);
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

  protected final void b()
  {
    if (!c())
      com.tencent.beacon.d.a.c(" err su 1R", new Object[0]);
    List localList;
    do
    {
      return;
      localList = a();
    }
    while ((localList == null) || (localList.size() <= 0));
    com.tencent.beacon.upload.g localg = m.d().j();
    if ((!com.tencent.beacon.d.b.b(this.c)) || (localg == null))
    {
      com.tencent.beacon.d.a.f(" dsu real events 2 db" + localList.size(), new Object[0]);
      com.tencent.beacon.applog.a.c(this.c, localList);
      return;
    }
    com.tencent.beacon.d.a.f(" dsu real events 2 up " + localList.size(), new Object[0]);
    localg.a(new a(this.c, localList));
  }

  static final class a extends com.tencent.beacon.upload.a
  {
    private List<h> d = null;
    private Context e;
    private Long[] f = null;
    private boolean g = false;

    public a(Context paramContext, List<h> paramList)
    {
      super(1, 2);
      this.d = paramList;
      this.e = paramContext;
      if ((this.d.size() == 1) && ("rqd_heartbeat".equals(((h)this.d.get(0)).d())))
        this.g = true;
    }

    public final com.tencent.beacon.c.a.b a()
    {
      monitorenter;
      try
      {
        com.tencent.beacon.d.a.b(" TUUD.GetUD start", new Object[0]);
        Object localObject2;
        if (this.d != null)
        {
          int i = this.d.size();
          if (i > 0);
        }
        else
        {
          localObject2 = null;
        }
        while (true)
        {
          return localObject2;
          try
          {
            com.tencent.beacon.c.a.b localb = j.a(this.e, this.a, this.d);
            localObject2 = localb;
            if (localObject2 != null)
              continue;
            localObject2 = null;
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              localThrowable.printStackTrace();
              com.tencent.beacon.d.a.d(" TUUD.GetUD start error", new Object[0]);
            }
          }
        }
      }
      finally
      {
        monitorexit;
      }
      throw localObject1;
    }

    public final void a(boolean paramBoolean)
    {
      monitorenter;
      try
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Boolean.valueOf(paramBoolean);
        com.tencent.beacon.d.a.b(" TimeUpUploadDatas.done(), result:%b", arrayOfObject);
        if ((this.d != null) && (!paramBoolean))
        {
          com.tencent.beacon.d.a.f(" upload failed, save to db", new Object[0]);
          if (!this.g)
          {
            this.f = com.tencent.beacon.applog.a.c(this.e, this.d);
            this.d = null;
          }
        }
        if ((paramBoolean) && (this.g))
        {
          Context localContext = this.e;
          c.a().a(108, true);
          com.tencent.beacon.a.a.a(localContext, "HEART_DENGTA", e.k());
          com.tencent.beacon.d.a.a("heartbeat uploaded sucess!", new Object[0]);
        }
        if ((paramBoolean) && (this.f != null))
          com.tencent.beacon.applog.a.a(this.e, this.f);
        if ((paramBoolean) && (this.f == null) && (this.d != null))
          this.d = null;
        return;
      }
      finally
      {
        monitorexit;
      }
      throw localObject;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.beacon.event.i
 * JD-Core Version:    0.6.0
 */