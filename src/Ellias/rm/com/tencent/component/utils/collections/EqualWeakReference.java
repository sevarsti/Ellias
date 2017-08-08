package com.tencent.component.utils.collections;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class EqualWeakReference extends WeakReference
{
  public EqualWeakReference(Object paramObject)
  {
    super(paramObject);
  }

  public EqualWeakReference(Object paramObject, ReferenceQueue paramReferenceQueue)
  {
    super(paramObject, paramReferenceQueue);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      Object localObject1 = ((EqualWeakReference)paramObject).get();
      Object localObject2 = get();
      if (localObject2 != null)
        return localObject2.equals(localObject1);
      if (localObject1 == null)
        return true;
    }
    return false;
  }

  public int hashCode()
  {
    Object localObject = get();
    if (localObject != null)
      return localObject.hashCode();
    return 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.EqualWeakReference
 * JD-Core Version:    0.6.0
 */