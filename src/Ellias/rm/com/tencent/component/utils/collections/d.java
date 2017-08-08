package com.tencent.component.utils.collections;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class d extends AbstractSet
{
  private d(WeakValueHashMap paramWeakValueHashMap)
  {
  }

  public int hashCode()
  {
    Iterator localIterator = WeakValueHashMap.c(this.a).iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      f localf = (f)localEntry.getValue();
      if (localf == null)
        continue;
      Object localObject = localEntry.getKey();
      if (localObject == null);
      for (int j = 0; ; j = localObject.hashCode())
      {
        i += (j ^ localf.hashCode());
        break;
      }
    }
    return i;
  }

  public boolean isEmpty()
  {
    return !iterator().hasNext();
  }

  public Iterator iterator()
  {
    WeakValueHashMap.b(this.a);
    return new e(this);
  }

  public boolean remove(Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry));
    Object localObject1;
    Object localObject2;
    Object localObject3;
    do
      while (true)
      {
        return false;
        Map.Entry localEntry = (Map.Entry)paramObject;
        localObject1 = localEntry.getKey();
        localObject2 = localEntry.getValue();
        localObject3 = this.a.get(localObject1);
        if (localObject3 != null)
          break;
        if ((localObject2 != null) || (!this.a.containsKey(localObject1)))
          continue;
        this.a.remove(localObject1);
        return true;
      }
    while (!localObject3.equals(localObject2));
    this.a.remove(localObject1);
    return true;
  }

  public int size()
  {
    int i = 0;
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      i++;
      localIterator.next();
    }
    return i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.d
 * JD-Core Version:    0.6.0
 */