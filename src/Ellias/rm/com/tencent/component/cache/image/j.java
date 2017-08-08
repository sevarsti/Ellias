package com.tencent.component.cache.image;

import android.graphics.Bitmap.Config;
import android.text.TextUtils;
import com.tencent.component.utils.AssertUtil;

final class j
{
  final String a;
  final int b;
  final int c;
  final boolean d;
  final boolean e;
  final boolean f;
  final Bitmap.Config g;
  final String h;

  public j(String paramString, ImageCacheService.Options paramOptions)
  {
    boolean bool2;
    int j;
    label39: boolean bool3;
    label71: Bitmap.Config localConfig;
    label120: ImageProcessor localImageProcessor;
    if (!TextUtils.isEmpty(paramString))
    {
      bool2 = bool1;
      AssertUtil.a(bool2);
      this.a = paramString;
      if (paramOptions == null)
        break label190;
      j = paramOptions.i;
      this.b = j;
      if (paramOptions != null)
        i = paramOptions.j;
      this.c = i;
      if (paramOptions == null)
        break label197;
      bool3 = paramOptions.k;
      this.d = bool3;
      if (paramOptions != null)
        bool1 = paramOptions.m;
      this.e = bool1;
      boolean bool4 = false;
      if (paramOptions != null)
        bool4 = paramOptions.n;
      this.f = bool4;
      if (paramOptions == null)
        break label203;
      localConfig = paramOptions.o;
      this.g = localConfig;
      if (paramOptions == null)
        break label211;
      localImageProcessor = paramOptions.p;
      label136: if (localImageProcessor == null)
        break label219;
    }
    label190: label197: label203: label211: label219: for (String str = localImageProcessor.getClass().getName() + "#" + localImageProcessor.a(); ; str = null)
    {
      this.h = str;
      return;
      bool2 = false;
      break;
      j = i;
      break label39;
      bool3 = false;
      break label71;
      localConfig = ImageCacheService.Options.g;
      break label120;
      localImageProcessor = ImageCacheService.Options.h;
      break label136;
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

  public boolean equals(Object paramObject)
  {
    if ((paramObject == null) || (!(paramObject instanceof j)));
    j localj;
    do
    {
      return false;
      localj = (j)paramObject;
    }
    while ((!a(this.a, localj.a)) || (this.b != localj.b) || (this.c != localj.c) || (this.d != localj.d) || (this.e != localj.e) || (this.f != localj.f) || (!a(this.g, localj.g)) || (!a(this.h, localj.h)));
    return true;
  }

  public int hashCode()
  {
    int i = 1;
    int j = 31 * (31 * (31 * (527 + a(this.a)) + this.b) + this.c);
    int k;
    int n;
    label60: int i1;
    if (this.d)
    {
      k = i;
      int m = 31 * (k + j);
      if (!this.e)
        break label109;
      n = i;
      i1 = 31 * (n + m);
      if (!this.f)
        break label115;
    }
    while (true)
    {
      return 31 * (31 * (i1 + i) + a(this.g)) + a(this.h);
      k = 0;
      break;
      label109: n = 0;
      break label60;
      label115: i = 0;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.j
 * JD-Core Version:    0.6.0
 */