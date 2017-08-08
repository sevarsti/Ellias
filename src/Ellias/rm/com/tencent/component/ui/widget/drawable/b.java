package com.tencent.component.ui.widget.drawable;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.tencent.component.image.image.GifStreamImage.Callback;
import java.lang.ref.WeakReference;

class b
  implements Handler.Callback, GifStreamImage.Callback
{
  private static final int a;
  private final Handler b = new Handler(Looper.getMainLooper(), this);
  private final WeakReference c;

  public b(GifStreamImageDrawable paramGifStreamImageDrawable)
  {
    if (paramGifStreamImageDrawable != null);
    for (WeakReference localWeakReference = new WeakReference(paramGifStreamImageDrawable); ; localWeakReference = null)
    {
      this.c = localWeakReference;
      return;
    }
  }

  private GifStreamImageDrawable b()
  {
    if (this.c != null)
      return (GifStreamImageDrawable)this.c.get();
    return null;
  }

  public void a()
  {
    this.b.removeMessages(0);
  }

  public void a(Bitmap paramBitmap)
  {
    Message localMessage = this.b.obtainMessage(0, paramBitmap);
    this.b.sendMessage(localMessage);
  }

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    }
    while (true)
    {
      return true;
      GifStreamImageDrawable localGifStreamImageDrawable = b();
      Bitmap localBitmap = (Bitmap)paramMessage.obj;
      if ((localGifStreamImageDrawable == null) || (localBitmap == null) || (localBitmap.isRecycled()))
        continue;
      localGifStreamImageDrawable.a(localBitmap);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.drawable.b
 * JD-Core Version:    0.6.0
 */