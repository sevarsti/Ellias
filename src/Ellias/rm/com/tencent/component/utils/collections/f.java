package com.tencent.component.utils.collections;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class f extends WeakReference
{
  private Object a;

  private f(Object paramObject)
  {
    super(paramObject);
  }

  private f(Object paramObject1, Object paramObject2, ReferenceQueue paramReferenceQueue)
  {
    super(paramObject2, paramReferenceQueue);
    this.a = paramObject1;
  }

  private static f b(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return new f(paramObject);
  }

  private static f b(Object paramObject1, Object paramObject2, ReferenceQueue paramReferenceQueue)
  {
    if (paramObject2 == null)
      return null;
    return new f(paramObject1, paramObject2, paramReferenceQueue);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    Object localObject1;
    Object localObject2;
    do
    {
      return true;
      if (!(paramObject instanceof f))
        return false;
      localObject1 = get();
      localObject2 = ((f)paramObject).get();
    }
    while (localObject1 == localObject2);
    if ((localObject1 == null) || (localObject2 == null))
      return false;
    return localObject1.equals(localObject2);
  }

  public int hashCode()
  {
    Object localObject = get();
    if (localObject == null)
      return 0;
    return localObject.hashCode();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.collections.f
 * JD-Core Version:    0.6.0
 */