package com.tencent.component.utils.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.WeakHashMap;

public class MultiWeakHashMap
{
  private HashMap a;

  public MultiWeakHashMap()
  {
    this.a = new HashMap();
  }

  public MultiWeakHashMap(int paramInt)
  {
    this.a = new HashMap(paramInt);
  }

  public Collection a()
  {
    return this.a.keySet();
  }

  public Collection a(Object paramObject)
  {
    WeakHashMap localWeakHashMap = (WeakHashMap)this.a.get(paramObject);
    if (localWeakHashMap == null)
      return null;
    return localWeakHashMap.keySet();
  }

  public void a(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null)
      return;
    WeakHashMap localWeakHashMap = (WeakHashMap)this.a.get(paramObject1);
    if (localWeakHashMap == null)
    {
      localWeakHashMap = new WeakHashMap();
      this.a.put(paramObject1, localWeakHashMap);
    }
    localWeakHashMap.put(paramObject2, this);
  }

  public int b()
  {
    return this.a.size();
  }

  public void b(Object paramObject)
  {
    this.a.remove(paramObject);
  }

  public void b(Object paramObject1, Object paramObject2)
  {
    WeakHashMap localWeakHashMap = (WeakHashMap)this.a.get(paramObject1);
    if (localWeakHashMap != null)
      localWeakHashMap.remove(paramObject2);
  }

  public int c(Object paramObject)
  {
    WeakHashMap localWeakHashMap = (WeakHashMap)this.a.get(paramObject);
    if (localWeakHashMap != null)
      return localWeakHashMap.size();
    return 0;
  }

  public void c()
  {
    this.a.clear();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.MultiWeakHashMap
 * JD-Core Version:    0.6.0
 */