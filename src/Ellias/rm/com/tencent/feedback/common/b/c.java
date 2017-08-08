package com.tencent.feedback.common.b;

import android.content.Context;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.List;

public final class c
{
  private static c a = null;
  private f b = null;
  private boolean c = false;
  private int d = 0;
  private com.tencent.feedback.upload.e e = null;
  private Runnable f = null;
  private List<b> g = new ArrayList(5);
  private SparseArray<com.tencent.feedback.upload.f> h = new SparseArray(5);
  private List<g> i = new ArrayList(5);

  private c(Context paramContext)
  {
    this.e = new a(paramContext);
    this.f = new d(paramContext);
    com.tencent.feedback.common.b.b().a(this.f);
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

  public static com.tencent.feedback.upload.f a()
  {
    monitorenter;
    try
    {
      if (a != null)
      {
        com.tencent.feedback.upload.f localf2 = a.f();
        localf1 = localf2;
        return localf1;
      }
      com.tencent.feedback.upload.f localf1 = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private com.tencent.feedback.upload.f f()
  {
    monitorenter;
    try
    {
      if ((this.h != null) && (this.h.size() > 0))
      {
        localf = (com.tencent.feedback.upload.f)this.h.valueAt(0);
        return localf;
      }
      com.tencent.feedback.upload.f localf = null;
    }
    finally
    {
      monitorexit;
    }
  }

  private boolean g()
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

  private g[] h()
  {
    monitorenter;
    try
    {
      if ((this.i != null) && (this.i.size() > 0))
      {
        arrayOfg = (g[])this.i.toArray(new g[0]);
        return arrayOfg;
      }
      g[] arrayOfg = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public final void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.d = paramInt;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      com.tencent.feedback.common.e.f("rqdp{  step }%d", arrayOfObject);
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

  public final void a(int paramInt, com.tencent.feedback.upload.f paramf)
  {
    monitorenter;
    try
    {
      if (this.h != null)
      {
        if (paramf != null)
          break label24;
        this.h.remove(paramInt);
      }
      while (true)
      {
        return;
        label24: this.h.put(paramInt, paramf);
        paramf.a(300, c());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
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
        int j = e();
        if (g())
        {
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = paramb.toString();
          com.tencent.feedback.common.e.e("rqdp{  add listener should notify app first run! }%s", arrayOfObject2);
          com.tencent.feedback.common.b.b().a(new Runnable(paramb)
          {
            public final void run()
            {
              this.a.f();
            }
          });
        }
        if (j < 2)
          continue;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = paramb.toString();
        com.tencent.feedback.common.e.e("rqdp{  add listener should notify app start query!} %s", arrayOfObject1);
        com.tencent.feedback.common.b.b().a(new Runnable(paramb, j)
        {
          public final void run()
          {
            this.a.d();
            if (this.b >= 3)
            {
              com.tencent.feedback.common.e.e("rqdp{  query finished should notify}", new Object[0]);
              this.a.e();
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

  public final void a(f paramf)
  {
    g[] arrayOfg = h();
    if (arrayOfg != null)
    {
      int j = arrayOfg.length;
      int k = 0;
      while (true)
        if (k < j)
        {
          g localg = arrayOfg[k];
          try
          {
            localg.a(paramf);
            k++;
          }
          catch (Throwable localThrowable)
          {
            while (true)
            {
              localThrowable.printStackTrace();
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = localThrowable.toString();
              com.tencent.feedback.common.e.d("rqdp{  com strategy changed error }%s", arrayOfObject);
            }
          }
        }
    }
  }

  public final void a(g paramg)
  {
    monitorenter;
    if (paramg != null);
    try
    {
      if ((this.i != null) && (!this.i.contains(paramg)))
        this.i.add(paramg);
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
      com.tencent.feedback.common.e.f("rqdp{  isFirst }%b", arrayOfObject);
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

  public final f b()
  {
    monitorenter;
    try
    {
      f localf = this.b;
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

  public final com.tencent.feedback.upload.e c()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.upload.e locale = this.e;
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

  public final b[] d()
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

  public final int e()
  {
    monitorenter;
    try
    {
      int j = this.d;
      monitorexit;
      return j;
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
 * Qualified Name:     com.tencent.feedback.common.b.c
 * JD-Core Version:    0.6.0
 */