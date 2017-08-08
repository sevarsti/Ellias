package com.tencent.component.cache.image;

final class h
  implements ImageCache.Matcher
{
  private boolean a(String paramString1, String paramString2)
  {
    return (paramString1 == paramString2) || ((paramString1 != null) && (paramString1.equals(paramString2)));
  }

  public boolean a(ImageEntry paramImageEntry1, ImageEntry paramImageEntry2)
  {
    if (paramImageEntry1 == paramImageEntry2)
      return true;
    if ((paramImageEntry1 == null) || (paramImageEntry2 == null))
      return false;
    return a(paramImageEntry1.a, paramImageEntry2.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.h
 * JD-Core Version:    0.6.0
 */