package com.tencent.beacon.a.b;

import android.content.Context;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class c
{
  private static c a = null;
  private e b = null;
  private boolean c = false;
  private int d = 0;
  private com.tencent.beacon.upload.f e = null;
  private Runnable f = null;
  private List<b> g = new ArrayList(5);
  private SparseArray<com.tencent.beacon.upload.g> h = new SparseArray(5);
  private List<f> i = new ArrayList(5);
  private SparseArray<g> j = new SparseArray(2);

  private c(Context paramContext)
  {
    this.e = new a(paramContext);
    this.f = new d(paramContext);
    com.tencent.beacon.a.c.a().a(this.f);
  }

  public static c a(Context paramContext)
  {
    monitorenter;
    try
    {
      if ((a == null) && (paramContext != null))
        a = new c(paramContext);
      c localc = a;
      return localc;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static com.tencent.beacon.upload.g a()
  {
    monitorenter;
    try
    {
      if (a != null)
      {
        com.tencent.beacon.upload.g localg2 = a.g();
        localg1 = localg2;
        return localg1;
      }
      com.tencent.beacon.upload.g localg1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private com.tencent.beacon.upload.g g()
  {
    monitorenter;
    try
    {
      if ((this.h != null) && (this.h.size() > 0))
      {
        localg = (com.tencent.beacon.upload.g)this.h.get(0);
        return localg;
      }
      com.tencent.beacon.upload.g localg = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private f[] h()
  {
    monitorenter;
    try
    {
      if ((this.i != null) && (this.i.size() > 0))
      {
        arrayOff = (f[])this.i.toArray(new f[0]);
        return arrayOff;
      }
      f[] arrayOff = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private SparseArray<g> i()
  {
    monitorenter;
    try
    {
      SparseArray localSparseArray = this.j;
      monitorexit;
      return localSparseArray;
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
      this.d = paramInt;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      com.tencent.beacon.d.a.f("step:%d", arrayOfObject);
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

  public final void a(int paramInt, g paramg)
  {
    monitorenter;
    if (paramg != null);
    try
    {
      if (this.j != null)
        this.j.put(1, paramg);
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

  public final void a(int paramInt, com.tencent.beacon.upload.g paramg)
  {
    monitorenter;
    try
    {
      if (this.h != null)
      {
        if (paramg != null)
          break label24;
        this.h.remove(paramInt);
      }
      while (true)
      {
        return;
        label24: this.h.put(paramInt, paramg);
        paramg.a(101, c());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void a(int paramInt, Map<String, String> paramMap)
  {
    SparseArray localSparseArray = i();
    if (localSparseArray != null)
    {
      g localg = (g)localSparseArray.get(paramInt);
      if (localg != null)
        localg.a(paramMap);
    }
  }

  public final void a(b paramb)
  {
    monitorenter;
    if (paramb == null);
    while (true)
    {
      monitorexit;
      return;
      try
      {
        if (this.g == null)
          this.g = new ArrayList();
        if (this.g.contains(paramb))
          continue;
        this.g.add(paramb);
        int k = f();
        if (d())
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = paramb.toString();
          com.tencent.beacon.d.a.e("add listener should notify app first run! %s", arrayOfObject2);
          com.tencent.beacon.a.c.a().a(new Runnable(paramb)
          {
            public final void run()
            {
              this.a.c();
            }
          });
        }
        if (k < 2)
          continue;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramb.toString();
        com.tencent.beacon.d.a.e("add listener should notify app start query! %s", arrayOfObject1);
        com.tencent.beacon.a.c.a().a(new Runnable(paramb, k)
        {
          public final void run()
          {
            this.a.a();
            if (this.b >= 3)
            {
              com.tencent.beacon.d.a.e("query finished should notify", new Object[0]);
              this.a.b();
            }
          }
        });
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }

  public final void a(e parame)
  {
    f[] arrayOff = h();
    if (arrayOff != null)
    {
      int k = arrayOff.length;
      int m = 0;
      while (true)
        if (m < k)
        {
          f localf = arrayOff[m];
          try
          {
            localf.a(parame);
            m++;
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              localThrowable.printStackTrace();
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localThrowable.toString();
              com.tencent.beacon.d.a.d("com strategy changed error %s", arrayOfObject);
            }
          }
        }
    }
  }

  public final void a(f paramf)
  {
    monitorenter;
    if (paramf != null);
    try
    {
      if ((this.i != null) && (!this.i.contains(paramf)))
        this.i.add(paramf);
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

  protected final void a(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.c = true;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Boolean.valueOf(true);
      com.tencent.beacon.d.a.f("isFirst }%b", arrayOfObject);
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

  public final e b()
  {
    monitorenter;
    try
    {
      e locale = this.b;
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

  public final com.tencent.beacon.upload.f c()
  {
    monitorenter;
    try
    {
      com.tencent.beacon.upload.f localf = this.e;
      monitorexit;
      return localf;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final boolean d()
  {
    monitorenter;
    try
    {
      boolean bool = this.c;
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

  public final b[] e()
  {
    monitorenter;
    try
    {
      if ((this.g != null) && (this.g.size() > 0))
      {
        arrayOfb = (b[])this.g.toArray(new b[0]);
        return arrayOfb;
      }
      b[] arrayOfb = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public final int f()
  {
    monitorenter;
    try
    {
      int k = this.d;
      monitorexit;
      return k;
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
 * Qualified Name:     com.tencent.beacon.a.b.c
 * JD-Core Version:    0.6.0
 */