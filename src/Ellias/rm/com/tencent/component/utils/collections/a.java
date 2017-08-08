package com.tencent.component.utils.collections;

import java.util.AbstractCollection;
import java.util.Iterator;

class a extends AbstractCollection
{
  a(WeakValueHashMap paramWeakValueHashMap)
  {
  }

  public boolean contains(Object paramObject)
  {
    return this.a.containsValue(paramObject);
  }

  public Iterator iterator()
  {
    return new b(this);
  }

  public int size()
  {
    return this.a.size();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.a
 * JD-Core Version:    0.6.0
 */