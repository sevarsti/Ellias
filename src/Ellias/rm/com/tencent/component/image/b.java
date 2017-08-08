package com.tencent.component.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.cache.image.ImageCacheService.ImageCacheListener;

class b
  implements ImageCacheService.ImageCacheListener
{
  final Result a = new Result();

  b(ImageLoader paramImageLoader, Request paramRequest)
  {
  }

  public void a(String paramString)
  {
  }

  public void a(String paramString, Drawable paramDrawable)
  {
    if (ImageLoader.a(paramDrawable))
      ImageLoader.a(this.c, ImageLoader.b(this.c, this.b), ImageLoader.a(paramDrawable, this.b.d));
    while (true)
    {
      this.a.a();
      ImageLoader.a(this.c, this.b, this.a);
      return;
      ImageLoader.c(this.c, ImageLoader.b(this.c, this.b));
    }
  }

  public void a(String paramString, Throwable paramThrowable)
  {
    ImageLoader.c(this.c, ImageLoader.b(this.c, this.b));
    this.a.a(paramThrowable);
    ImageLoader.a(this.c, this.b, this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.b
 * JD-Core Version:    0.6.0
 */