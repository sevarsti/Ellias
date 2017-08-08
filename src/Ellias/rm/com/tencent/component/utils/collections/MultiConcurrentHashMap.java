package com.tencent.component.utils.collections;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MultiConcurrentHashMap extends ConcurrentHashMap
{
  public int a(Object paramObject)
  {
    Collection localCollection = (Collection)get(paramObject);
    if (localCollection == null)
      return 0;
    return localCollection.size();
  }

  public boolean a(Object paramObject1, Object paramObject2)
  {
    if (paramObject2 == null)
      return false;
    ConcurrentLinkedQueue localConcurrentLinkedQueue = (ConcurrentLinkedQueue)get(paramObject1);
    if (localConcurrentLinkedQueue == null)
    {
      localConcurrentLinkedQueue = new ConcurrentLinkedQueue();
      put(paramObject1, localConcurrentLinkedQueue);
    }
    return localConcurrentLinkedQueue.add(paramObject2);
  }

  public boolean b(Object paramObject1, Object paramObject2)
  {
    int i = 1;
    if (paramObject2 == null)
    {
      if (remove(paramObject1) != null)
        return i;
      return false;
    }
    Collection localCollection = (Collection)get(paramObject1);
    if ((localCollection != null) && (localCollection.remove(paramObject2)));
    while (true)
    {
      if ((localCollection != null) && (localCollection.isEmpty()))
        remove(paramObject1);
      return i;
      i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.MultiConcurrentHashMap
 * JD-Core Version:    0.6.0
 */