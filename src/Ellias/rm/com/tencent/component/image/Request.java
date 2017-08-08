package com.tencent.component.image;

import android.text.TextUtils;
import com.tencent.component.cache.image.ImageCacheService.ImageCacheListener;
import com.tencent.component.net.http.download.DownloadListener;
import com.tencent.component.utils.AssertUtil;

public final class Request
{
  public final String a;
  public final String b;
  public final ImageLoader.ImageLoadListener c;
  public final ImageLoader.Options d;
  public String e;
  public ImageCacheService.ImageCacheListener f;
  public String g;
  public DownloadListener h;
  public String i;

  public Request(String paramString1, String paramString2, ImageLoader.ImageLoadListener paramImageLoadListener, ImageLoader.Options paramOptions)
  {
    if (!TextUtils.isEmpty(paramString1));
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = paramString1;
      this.b = paramString2;
      this.c = paramImageLoadListener;
      this.d = paramOptions;
      return;
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
    if (this == paramObject);
    Request localRequest;
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof Request)))
        return false;
      localRequest = (Request)paramObject;
    }
    while ((a(this.a, localRequest.a)) && (a(this.b, localRequest.b)) && (a(this.c, localRequest.c)) && (a(this.d, localRequest.d)));
    return false;
  }

  public int hashCode()
  {
    return 31 * (31 * (31 * (527 + a(this.a)) + a(this.b)) + a(this.c)) + a(this.d);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.Request
 * JD-Core Version:    0.6.0
 */