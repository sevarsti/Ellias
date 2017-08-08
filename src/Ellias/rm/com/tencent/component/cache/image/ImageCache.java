package com.tencent.component.cache.image;

import com.tencent.component.cache.common.ExtendLruCache;
import com.tencent.component.image.image.Image;
import java.lang.ref.ReferenceQueue;

class ImageCache
{
  private static final int a = 1;
  private final ExtendLruCache b;
  private final ExtendLruCache c;
  private ReferenceQueue d = new ReferenceQueue();

  public ImageCache(int paramInt)
  {
    this(paramInt, 0);
  }

  public ImageCache(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 1)
      paramInt1 = 1;
    if (paramInt2 < 1)
      paramInt2 = 1;
    this.b = new a(this, paramInt1);
    this.c = new b(this, paramInt2);
  }

  private void d()
  {
    for (d locald = (d)this.d.poll(); locald != null; locald = (d)this.d.poll())
      this.c.c(locald.a);
  }

  public Image a(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      c localc = (c)this.b.b(paramObject);
      d locald = (d)this.c.b(paramObject);
      if (localc == null)
      {
        localImage = null;
        if (localImage == null)
          if (locald != null)
            break label65;
      }
      label65: for (Image localImage = null; ; localImage = (Image)locald.get())
      {
        return localImage;
        localImage = localc.a();
        break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a()
  {
    monitorenter;
    try
    {
      this.b.a();
      this.c.a();
      this.d = new ReferenceQueue();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void a(Object paramObject, ImageCache.Matcher paramMatcher)
  {
    monitorenter;
    try
    {
      d();
      this.b.a(paramObject, paramMatcher);
      this.c.a(paramObject, paramMatcher);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void a(Object paramObject, Image paramImage)
  {
    monitorenter;
    try
    {
      d();
      if (paramImage != null)
      {
        boolean bool = paramImage.c();
        if (!bool)
          break label24;
      }
      while (true)
      {
        return;
        label24: if (this.b.c() > 1)
          this.b.a(paramObject, new c(paramObject, paramImage));
        if (this.c.c() <= 1)
          continue;
        this.c.a(paramObject, new d(paramObject, paramImage, this.d));
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int b()
  {
    return this.b.c();
  }

  public Image b(Object paramObject)
  {
    monitorenter;
    try
    {
      d();
      c localc = (c)this.b.c(paramObject);
      d locald = (d)this.c.c(paramObject);
      if (localc == null)
      {
        localImage = null;
        if (localImage == null)
          if (locald != null)
            break label65;
      }
      label65: for (Image localImage = null; ; localImage = (Image)locald.get())
      {
        return localImage;
        localImage = localc.a();
        break;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int c()
  {
    return Math.max(this.b.c(), this.c.c());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.ImageCache
 * JD-Core Version:    0.6.0
 */