package com.tencent.component.utils.collections;

import java.util.Map.Entry;

class c
  implements Map.Entry
{
  private Map.Entry b;
  private Object c;

  c(WeakValueHashMap paramWeakValueHashMap, Map.Entry paramEntry, Object paramObject)
  {
    this.b = paramEntry;
    this.c = paramObject;
  }

  private boolean a(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject2 == null;
    return paramObject1.equals(paramObject2);
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Map.Entry));
    Map.Entry localEntry;
    do
    {
      return false;
      localEntry = (Map.Entry)paramObject;
    }
    while ((!a(this.b.getKey(), localEntry.getKey())) || (!a(this.c, localEntry.getValue())));
    return true;
  }

  public Object getKey()
  {
    return this.b.getKey();
  }

  public Object getValue()
  {
    return this.c;
  }

  public int hashCode()
  {
    Object localObject1 = this.b.getKey();
    int i;
    int j;
    if (localObject1 == null)
    {
      i = 0;
      Object localObject2 = this.c;
      j = 0;
      if (localObject2 != null)
        break label41;
    }
    while (true)
    {
      return i ^ j;
      i = localObject1.hashCode();
      break;
      label41: j = this.c.hashCode();
    }
  }

  public Object setValue(Object paramObject)
  {
    Object localObject = this.c;
    this.c = paramObject;
    this.b.setValue(f.a(getKey(), paramObject, WeakValueHashMap.a(this.a)));
    return localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.c
 * JD-Core Version:    0.6.0
 */