package com.tencent.component.cache.image;

import android.graphics.Bitmap.Config;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.SecurityUtil;
import java.io.File;

public final class ImageEntry
{
  public final String a;
  public final int b;
  public final boolean c;
  public final Bitmap.Config d;
  public final ImageProcessor e;
  public int f = -1;
  public int g = -1;
  private final String h;

  ImageEntry(String paramString, int paramInt, boolean paramBoolean, Bitmap.Config paramConfig, ImageProcessor paramImageProcessor)
  {
    StringBuilder localStringBuilder1;
    StringBuilder localStringBuilder2;
    if (paramString != null)
    {
      int j = i;
      AssertUtil.a(j);
      if (paramInt < i)
        paramInt = i;
      this.a = paramString;
      this.b = paramInt;
      this.c = paramBoolean;
      this.d = paramConfig;
      this.e = paramImageProcessor;
      localStringBuilder1 = new StringBuilder(paramString);
      File localFile = new File(paramString);
      if (localFile.exists())
      {
        localStringBuilder1.append('-').append(localFile.length());
        localStringBuilder1.append('-').append(localFile.lastModified());
      }
      localStringBuilder1.append('-').append(paramInt);
      localStringBuilder2 = localStringBuilder1.append('-');
      if (!paramBoolean)
        break label225;
    }
    while (true)
    {
      localStringBuilder2.append(i);
      localStringBuilder1.append('-').append(paramConfig);
      if (paramImageProcessor != null)
        localStringBuilder1.append('-').append(paramImageProcessor.getClass().getName()).append('#').append(paramImageProcessor.a());
      this.h = localStringBuilder1.toString();
      return;
      int k = 0;
      break;
      label225: i = 0;
    }
  }

  private static int a(Object paramObject)
  {
    if (paramObject == null)
      return 0;
    return paramObject.hashCode();
  }

  private static boolean a(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject2 == null;
    return paramObject1.equals(paramObject2);
  }

  final void a(ImageCacheService.Options paramOptions)
  {
    int i = -1;
    int j;
    if (paramOptions == null)
    {
      j = i;
      this.f = j;
      if (paramOptions != null)
        break label31;
    }
    while (true)
    {
      this.g = i;
      return;
      j = paramOptions.i;
      break;
      label31: i = paramOptions.j;
    }
  }

  final byte[] a()
  {
    return SecurityUtil.c(this.h);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof ImageEntry)))
      return false;
    ImageEntry localImageEntry = (ImageEntry)paramObject;
    return a(this.h, localImageEntry.h);
  }

  public int hashCode()
  {
    return a(this.h);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.ImageEntry
 * JD-Core Version:    0.6.0
 */