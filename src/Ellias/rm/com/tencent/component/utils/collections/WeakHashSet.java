package com.tencent.component.utils.collections;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

public class WeakHashSet extends AbstractSet
  implements Set
{
  private static final Object b = new Object();
  private transient WeakHashMap a;

  public WeakHashSet()
  {
    this.a = new WeakHashMap();
  }

  public WeakHashSet(int paramInt)
  {
    this.a = new WeakHashMap(paramInt);
  }

  public WeakHashSet(int paramInt, float paramFloat)
  {
    this.a = new WeakHashMap(paramInt, paramFloat);
  }

  public WeakHashSet(Collection paramCollection)
  {
    this.a = new WeakHashMap(Math.max(1 + (int)(paramCollection.size() / 0.75F), 16));
    addAll(paramCollection);
  }

  public boolean add(Object paramObject)
  {
    return this.a.put(paramObject, b) == null;
  }

  public void clear()
  {
    this.a.clear();
  }

  public boolean contains(Object paramObject)
  {
    return this.a.containsKey(paramObject);
  }

  public boolean isEmpty()
  {
    return this.a.isEmpty();
  }

  public Iterator iterator()
  {
    return this.a.keySet().iterator();
  }

  public boolean remove(Object paramObject)
  {
    return this.a.remove(paramObject) == b;
  }

  public int size()
  {
    return this.a.size();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.WeakHashSet
 * JD-Core Version:    0.6.0
 */