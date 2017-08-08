package com.tencent.component.cache.image;

import android.graphics.Bitmap;

public abstract class ImageProcessor
{
  public abstract Bitmap a(Bitmap paramBitmap);

  final Bitmap a(Bitmap paramBitmap, boolean paramBoolean)
  {
    if (paramBitmap == null)
      paramBitmap = null;
    Bitmap localBitmap;
    do
    {
      return paramBitmap;
      localBitmap = a(paramBitmap);
    }
    while ((localBitmap == null) || (localBitmap == paramBitmap));
    if ((paramBoolean) && (!paramBitmap.isRecycled()))
      paramBitmap.recycle();
    return localBitmap;
  }

  public abstract String a();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.ImageProcessor
 * JD-Core Version:    0.6.0
 */