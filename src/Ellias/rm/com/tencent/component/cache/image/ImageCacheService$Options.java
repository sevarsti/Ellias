package com.tencent.component.cache.image;

import android.graphics.Bitmap.Config;

public class ImageCacheService$Options
  implements Cloneable
{
  public static final int a = -1;
  public static final int b = -1;
  public static final boolean c = false;
  public static final boolean d = true;
  public static final boolean e = true;
  public static final boolean f;
  public static final Bitmap.Config g = Bitmap.Config.RGB_565;
  public static final ImageProcessor h = null;
  public int i = -1;
  public int j = -1;
  public boolean k = false;
  public boolean l = true;
  public boolean m = true;
  public boolean n = false;
  public Bitmap.Config o = g;
  public ImageProcessor p = h;

  public final Options a()
  {
    try
    {
      Options localOptions = (Options)clone();
      return localOptions;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      localCloneNotSupportedException.printStackTrace();
    }
    return null;
  }

  public final boolean equals(Object paramObject)
  {
    return super.equals(paramObject);
  }

  public final int hashCode()
  {
    return super.hashCode();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.ImageCacheService.Options
 * JD-Core Version:    0.6.0
 */