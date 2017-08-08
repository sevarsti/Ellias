package com.tencent.component.cache.image;

import com.tencent.component.image.image.Image;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

final class d extends SoftReference
{
  final Object a;
  final int b;

  public d(Object paramObject, Image paramImage)
  {
    this(paramObject, paramImage, null);
  }

  public d(Object paramObject, Image paramImage, ReferenceQueue paramReferenceQueue)
  {
    super(paramImage, paramReferenceQueue);
    this.a = paramObject;
    this.b = paramImage.a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.d
 * JD-Core Version:    0.6.0
 */