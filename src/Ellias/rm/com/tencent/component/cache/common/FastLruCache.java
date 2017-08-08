package com.tencent.component.cache.common;

import java.lang.ref.ReferenceQueue;
import java.util.HashMap;

public class FastLruCache
{
  private final int a;
  private final HashMap b;
  private final HashMap c = new HashMap();
  private ReferenceQueue d = new ReferenceQueue();

  public FastLruCache(int paramInt)
  {
    if (paramInt <= 0)
      throw new IllegalArgumentException("maxSize <= 0");
    this.a = paramInt;
    this.b = new FastLruCache.1(this, 16, 0.75F, true, paramInt);
  }

  private void d()
  {
    for (d locald = (d)this.d.poll(); locald != null; locald = (d)this.d.poll())
      this.c.remove(locald.a);
  }

  public final Object a(Object paramObject1, Object paramObject2)
  {
    monitorenter;
    try
    {
      d();
      this.b.put(paramObject1, paramObject2);
      d locald = (d)this.c.put(paramObject1, new d(paramObject1, paramObject2, this.d));
      if (locald == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = locald.get();
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
      this.b.clear();
      this.c.clear();
      this.d = new ReferenceQueue();
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
      d();
      boolean bool = this.c.containsKey(paramObject);
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

  public final int b()
  {
    monitorenter;
    try
    {
      int i = this.b.size();
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

  public final Object b(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      Object localObject2 = this.b.get(paramObject);
      Object localObject3 = localObject2;
      if (localObject3 != null);
      while (true)
      {
        return localObject3;
        d locald = (d)this.c.get(paramObject);
        if (locald == null)
        {
          localObject3 = null;
          continue;
        }
        Object localObject4 = locald.get();
        localObject3 = localObject4;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  public final int c()
  {
    return this.a;
  }

  public final Object c(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      Object localObject2 = this.b.remove(paramObject);
      d locald = (d)this.c.remove(paramObject);
      Object localObject3;
      if (localObject2 != null)
        localObject3 = localObject2;
      while (true)
      {
        return localObject3;
        if (locald == null)
        {
          localObject3 = null;
          continue;
        }
        Object localObject4 = locald.get();
        localObject3 = localObject4;
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
 * Qualified Name:     com.tencent.component.cache.common.FastLruCache
 * JD-Core Version:    0.6.0
 */