package com.tencent.component.utils.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class MultiHashMap extends HashMap
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
    HashSet localHashSet = (HashSet)get(paramObject1);
    if (localHashSet == null)
    {
      localHashSet = new HashSet();
      put(paramObject1, localHashSet);
    }
    return localHashSet.add(paramObject2);
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
 * Qualified Name:     com.tencent.component.utils.collections.MultiHashMap
 * JD-Core Version:    0.6.0
 */