package com.tencent.component.image.request;

import android.graphics.Bitmap.Config;
import com.tencent.component.cache.image.ImageEntry;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;

public abstract class ImageRequest
  implements ThreadPool.Job
{
  private final ImageEntry a;
  private final ImageRequest.Callback b;
  private final Bitmap.Config c;

  public ImageRequest(ImageEntry paramImageEntry, ImageRequest.Callback paramCallback, Bitmap.Config paramConfig)
  {
    if ((paramImageEntry != null) && (paramCallback != null));
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = paramImageEntry;
      this.b = paramCallback;
      this.c = paramConfig;
      return;
    }
  }

  protected final ImageEntry a()
  {
    return this.a;
  }

  protected final ImageRequest.Callback b()
  {
    return this.b;
  }

  protected final Bitmap.Config c()
  {
    return this.c;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.request.ImageRequest
 * JD-Core Version:    0.6.0
 */