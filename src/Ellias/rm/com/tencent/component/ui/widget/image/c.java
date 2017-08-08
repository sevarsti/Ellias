package com.tencent.component.ui.widget.image;

import android.graphics.drawable.Drawable;
import com.tencent.component.image.ImageLoader.ImageLoadListener;
import com.tencent.component.image.ImageLoader.Options;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;
import java.lang.ref.WeakReference;

class c
  implements ImageLoader.ImageLoadListener
{
  private WeakReference a;

  public c(AsyncImageable.AsyncImageableImpl paramAsyncImageableImpl)
  {
    if (paramAsyncImageableImpl != null);
    for (boolean bool = true; ; bool = false)
    {
      AssertUtil.a(bool);
      this.a = new WeakReference(paramAsyncImageableImpl);
      return;
    }
  }

  private AsyncImageable.AsyncImageableImpl a()
  {
    return (AsyncImageable.AsyncImageableImpl)this.a.get();
  }

  public void a(String paramString, float paramFloat, ImageLoader.Options paramOptions)
  {
    AsyncImageable.AsyncImageableImpl localAsyncImageableImpl = a();
    if (localAsyncImageableImpl == null);
    while (true)
    {
      return;
      if (paramOptions == null);
      for (String[] arrayOfString = null; !AsyncImageable.AsyncImageableImpl.a(localAsyncImageableImpl, paramString, arrayOfString); arrayOfString = paramOptions.s)
      {
        AsyncImageable.AsyncImageableImpl.a(localAsyncImageableImpl, paramFloat);
        return;
      }
    }
  }

  public void a(String paramString, Drawable paramDrawable, ImageLoader.Options paramOptions)
  {
    AsyncImageable.AsyncImageableImpl localAsyncImageableImpl = a();
    if (localAsyncImageableImpl == null)
      LogUtil.i("AsyncImageable", "cannot find asyncImageable after " + paramString + " loaded.");
    do
    {
      return;
      if (paramOptions == null);
      for (String[] arrayOfString = null; !AsyncImageable.AsyncImageableImpl.a(localAsyncImageableImpl, paramString, arrayOfString); arrayOfString = paramOptions.s)
      {
        AsyncImageable.AsyncImageableImpl.a(localAsyncImageableImpl, paramDrawable, true);
        AsyncImageable.AsyncImageableImpl.e(localAsyncImageableImpl);
        return;
      }
    }
    while (!DebugUtil.a());
    LogUtil.i("AsyncImageable", "image url changed after " + paramString + " loaded.");
  }

  public void a(String paramString, ImageLoader.Options paramOptions)
  {
  }

  public void b(String paramString, ImageLoader.Options paramOptions)
  {
    AsyncImageable.AsyncImageableImpl localAsyncImageableImpl = a();
    if (localAsyncImageableImpl == null);
    while (true)
    {
      return;
      if (paramOptions == null);
      for (String[] arrayOfString = null; !AsyncImageable.AsyncImageableImpl.a(localAsyncImageableImpl, paramString, arrayOfString); arrayOfString = paramOptions.s)
      {
        AsyncImageable.AsyncImageableImpl.b(localAsyncImageableImpl);
        AsyncImageable.AsyncImageableImpl.c(localAsyncImageableImpl);
        AsyncImageable.AsyncImageableImpl.d(localAsyncImageableImpl);
        return;
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.image.c
 * JD-Core Version:    0.6.0
 */