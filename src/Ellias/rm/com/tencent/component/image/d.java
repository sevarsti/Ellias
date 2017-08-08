package com.tencent.component.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.cache.file.FileCacheService;
import com.tencent.component.cache.image.ImageCacheService.ImageCacheListener;

class d
  implements ImageCacheService.ImageCacheListener
{
  d(c paramc, boolean paramBoolean, FileCacheService paramFileCacheService)
  {
  }

  public void a(String paramString)
  {
    if (this.a)
      this.b.d(this.c.a.e);
  }

  public void a(String paramString, Drawable paramDrawable)
  {
    if (ImageLoader.a(paramDrawable))
      ImageLoader.a(this.c.b, ImageLoader.b(this.c.b, this.c.a), ImageLoader.a(paramDrawable, this.c.a.d));
    while (true)
    {
      if (this.a)
        this.b.d(this.c.a.e);
      return;
      ImageLoader.c(this.c.b, ImageLoader.b(this.c.b, this.c.a));
    }
  }

  public void a(String paramString, Throwable paramThrowable)
  {
    ImageLoader.c(this.c.b, ImageLoader.b(this.c.b, this.c.a));
    if (this.a)
      this.b.d(this.c.a.e);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.d
 * JD-Core Version:    0.6.0
 */