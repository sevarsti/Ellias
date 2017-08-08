package com.tencent.component.cache.common;

import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class ExtendLruCache
{
  private final LruCache a;
  private final HashMap b = new HashMap();
  private ReferenceQueue c = new ReferenceQueue();

  public ExtendLruCache(int paramInt)
  {
    this.a = new b(this, paramInt);
  }

  private void d()
  {
    for (c localc = (c)this.c.poll(); localc != null; localc = (c)this.c.poll())
      this.b.remove(localc.a);
  }

  public final int a(Object paramObject, ExtendLruCache.Matcher paramMatcher)
  {
    monitorenter;
    if (paramObject == null)
      try
      {
        throw new NullPointerException("key == null");
      }
      finally
      {
        monitorexit;
      }
    if (paramMatcher == null)
      throw new NullPointerException("keyMatcher == null");
    d();
    Set localSet = this.b.keySet();
    int i = 0;
    Iterator localIterator2;
    if (localSet != null)
    {
      int j = localSet.size();
      i = 0;
      if (j > 0)
      {
        ArrayList localArrayList = new ArrayList();
        Iterator localIterator1 = localSet.iterator();
        while (localIterator1.hasNext())
        {
          Object localObject6 = localIterator1.next();
          if (!paramMatcher.a(paramObject, localObject6))
            continue;
          localArrayList.add(localObject6);
        }
        localIterator2 = localArrayList.iterator();
      }
    }
    while (true)
    {
      Object localObject2;
      Object localObject3;
      if (localIterator2.hasNext())
      {
        Object localObject1 = localIterator2.next();
        localObject2 = this.a.c(localObject1);
        c localc = (c)this.b.remove(localObject1);
        if (localObject2 == null)
        {
          if (localc == null)
          {
            localObject3 = null;
            break label232;
          }
          Object localObject4 = localc.get();
          localObject3 = localObject4;
          break label232;
        }
      }
      else
      {
        monitorexit;
        return i;
      }
      int k;
      while (true)
      {
        k = i;
        break;
        localObject3 = localObject2;
        label232: if (localObject3 == null)
          continue;
        k = i + 1;
      }
      i = k;
    }
  }

  public final Object a(Object paramObject1, Object paramObject2)
  {
    monitorenter;
    try
    {
      d();
      this.a.b(paramObject1, paramObject2);
      c localc = (c)this.b.put(paramObject1, new c(paramObject1, paramObject2, this.c));
      if (localc == null);
      Object localObject2;
      for (Object localObject3 = null; ; localObject3 = localObject2)
      {
        return localObject3;
        localObject2 = localc.get();
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
      this.a.a();
      this.b.clear();
      this.c = new ReferenceQueue();
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
      boolean bool = this.b.containsKey(paramObject);
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
      int i = this.a.b();
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

  protected int b(Object paramObject1, Object paramObject2)
  {
    return 1;
  }

  public final Object b(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      Object localObject2 = this.a.b(paramObject);
      Object localObject3 = localObject2;
      if (localObject3 != null);
      while (true)
      {
        return localObject3;
        c localc = (c)this.b.get(paramObject);
        if (localc == null)
        {
          localObject3 = null;
          continue;
        }
        Object localObject4 = localc.get();
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
    monitorenter;
    try
    {
      int i = this.a.c();
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

  public final Object c(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      Object localObject2 = this.a.c(paramObject);
      c localc = (c)this.b.remove(paramObject);
      Object localObject3;
      if (localObject2 != null)
        localObject3 = localObject2;
      while (true)
      {
        return localObject3;
        if (localc == null)
        {
          localObject3 = null;
          continue;
        }
        Object localObject4 = localc.get();
        localObject3 = localObject4;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }

  protected Object d(Object paramObject)
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.ExtendLruCache
 * JD-Core Version:    0.6.0
 */