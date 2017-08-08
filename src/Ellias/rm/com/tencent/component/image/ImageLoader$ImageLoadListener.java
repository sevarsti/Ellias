package com.tencent.component.image;

import android.graphics.drawable.Drawable;

public abstract interface ImageLoader$ImageLoadListener
{
  public abstract void a(String paramString, float paramFloat, ImageLoader.Options paramOptions);

  public abstract void a(String paramString, Drawable paramDrawable, ImageLoader.Options paramOptions);

  public abstract void a(String paramString, ImageLoader.Options paramOptions);

  public abstract void b(String paramString, ImageLoader.Options paramOptions);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.ImageLoader.ImageLoadListener
 * JD-Core Version:    0.6.0
 */