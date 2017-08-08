package com.tencent.component.cache.image;

import android.graphics.Bitmap;
import com.tencent.component.image.image.BitmapImage;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

final class l extends n
{
  private final WeakReference a;

  public l(BitmapImage paramBitmapImage, ReferenceQueue paramReferenceQueue)
  {
    super(paramBitmapImage, paramReferenceQueue);
    if (paramBitmapImage != null);
    for (Bitmap localBitmap = paramBitmapImage.e(); ; localBitmap = null)
    {
      WeakReference localWeakReference = null;
      if (localBitmap != null)
        localWeakReference = new WeakReference(localBitmap);
      this.a = localWeakReference;
      return;
    }
  }

  public void a()
  {
    if (this.a == null)
      return;
    Bitmap localBitmap = (Bitmap)this.a.get();
    if (localBitmap != null);
    try
    {
      if (!localBitmap.isRecycled())
        localBitmap.recycle();
      this.a.clear();
      return;
    }
    catch (Throwable localThrowable)
    {
      while (true)
        LogUtil.d("ImageTracker", "fail to recycle " + localBitmap);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.l
 * JD-Core Version:    0.6.0
 */