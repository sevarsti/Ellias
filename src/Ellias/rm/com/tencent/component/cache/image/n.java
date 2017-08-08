package com.tencent.component.cache.image;

import com.tencent.component.image.image.Image;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

abstract class n extends PhantomReference
{
  public n(Image paramImage, ReferenceQueue paramReferenceQueue)
  {
    super(paramImage, paramReferenceQueue);
  }

  public abstract void a();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.n
 * JD-Core Version:    0.6.0
 */