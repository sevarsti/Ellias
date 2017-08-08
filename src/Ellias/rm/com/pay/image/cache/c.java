package com.pay.image.cache;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;

final class c extends Handler
{
  c(APImageCache paramAPImageCache)
  {
  }

  public final void handleMessage(Message paramMessage)
  {
    if (paramMessage != null)
      switch (paramMessage.what)
      {
      default:
      case 0:
      }
    do
      return;
    while ((!(paramMessage.obj instanceof APImageCache.ImageRef)) || (paramMessage.obj == null));
    APImageCache.ImageRef localImageRef = (APImageCache.ImageRef)paramMessage.obj;
    APImageCache.a(this.a, localImageRef.a, (Bitmap)this.a.mMemoryCache.get(localImageRef.b), true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.image.cache.c
 * JD-Core Version:    0.6.0
 */