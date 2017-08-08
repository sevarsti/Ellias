package com.tencent.component.cache.common;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;

public class WeakCache
{
  private final HashMap a = new HashMap();
  private ReferenceQueue b = new ReferenceQueue();

  private void b()
  {
    for (e locale = (e)this.b.poll(); locale != null; locale = (e)this.b.poll())
      this.a.remove(locale.a);
  }

  public final Object a(Object paramObject1, Object paramObject2)
  {
    monitorenter;
    try
    {
      b();
      e locale = (e)this.a.put(paramObject1, new e(paramObject1, paramObject2, this.b));
      if (locale == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = locale.get();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final void a()
  {
    monitorenter;
    try
    {
      this.a.clear();
      this.b = new ReferenceQueue();
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

  public final boolean a(Object paramObject)
  {
    monitorenter;
    try
    {
      b();
      boolean bool = this.a.containsKey(paramObject);
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

  public final Object b(Object paramObject)
  {
    monitorenter;
    try
    {
      b();
      e locale = (e)this.a.get(paramObject);
      if (locale == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = locale.get();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final Object c(Object paramObject)
  {
    monitorenter;
    try
    {
      b();
      e locale = (e)this.a.remove(paramObject);
      if (locale == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = locale.get();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.WeakCache
 * JD-Core Version:    0.6.0
 */