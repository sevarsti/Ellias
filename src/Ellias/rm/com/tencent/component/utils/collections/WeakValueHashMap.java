package com.tencent.component.utils.collections;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class WeakValueHashMap extends HashMap
{
  private Set entrySet = null;
  private Set hashEntrySet = null;
  private ReferenceQueue queue = new ReferenceQueue();
  private transient Collection values = null;

  private final Object a(WeakReference paramWeakReference)
  {
    if (paramWeakReference == null)
      return null;
    return paramWeakReference.get();
  }

  private void a()
  {
    while (true)
    {
      f localf = (f)this.queue.poll();
      if (localf == null)
        break;
      super.remove(f.a(localf));
    }
  }

  public boolean containsKey(Object paramObject)
  {
    a();
    return super.containsKey(paramObject);
  }

  public boolean containsValue(Object paramObject)
  {
    return super.containsValue(f.a(paramObject));
  }

  public Set entrySet()
  {
    if (this.entrySet == null)
    {
      this.hashEntrySet = super.entrySet();
      this.entrySet = new d(this, null);
    }
    return this.entrySet;
  }

  public Object get(Object paramObject)
  {
    return a((WeakReference)super.get(paramObject));
  }

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public Object put(Object paramObject1, Object paramObject2)
  {
    a();
    return a((f)super.put(paramObject1, f.a(paramObject1, paramObject2, this.queue)));
  }

  public Object remove(Object paramObject)
  {
    return a((WeakReference)super.remove(paramObject));
  }

  public int size()
  {
    return entrySet().size();
  }

  public Collection values()
  {
    if (this.values == null)
      this.values = new a(this);
    return this.values;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.WeakValueHashMap
 * JD-Core Version:    0.6.0
 */