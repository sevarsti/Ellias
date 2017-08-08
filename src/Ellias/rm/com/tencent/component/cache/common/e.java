package com.tencent.component.cache.common;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class e extends WeakReference
{
  Object a;

  public e(Object paramObject1, Object paramObject2, ReferenceQueue paramReferenceQueue)
  {
    super(paramObject2, paramReferenceQueue);
    this.a = paramObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.common.e
 * JD-Core Version:    0.6.0
 */