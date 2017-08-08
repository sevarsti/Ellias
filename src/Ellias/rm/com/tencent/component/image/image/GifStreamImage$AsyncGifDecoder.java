package com.tencent.component.image.image;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.component.app.ExceptionManager;
import com.tencent.component.utils.AssertUtil;
import com.tencent.component.utils.gif.GifStreamDecoder;
import com.tencent.component.utils.gif.GifStreamDecoder.GifFrame;
import java.util.concurrent.atomic.AtomicInteger;

final class GifStreamImage$AsyncGifDecoder
{
  private static final long a = 5L;
  private static final int b = 0;
  private static final int c = 1;
  private static final AtomicInteger d = new AtomicInteger(0);
  private Handler e;
  private final String f;
  private final float g;
  private final GifStreamImage.AsyncGifDecoder.DecodeCallback h;
  private final GifStreamDecoder i;
  private boolean j = false;

  public GifStreamImage$AsyncGifDecoder(String paramString, float paramFloat, GifStreamImage.AsyncGifDecoder.DecodeCallback paramDecodeCallback)
  {
    this(paramString, paramFloat, paramDecodeCallback, false);
  }

  public GifStreamImage$AsyncGifDecoder(String paramString, float paramFloat, GifStreamImage.AsyncGifDecoder.DecodeCallback paramDecodeCallback, boolean paramBoolean)
  {
    boolean bool1 = b(paramString);
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    AssertUtil.a(bool2);
    this.f = paramString;
    this.g = paramFloat;
    this.h = paramDecodeCallback;
    this.i = new GifStreamDecoder(this.f);
    if (paramBoolean)
    {
      long l1 = SystemClock.uptimeMillis();
      GifStreamDecoder.GifFrame localGifFrame = d();
      long l2 = SystemClock.uptimeMillis() - l1;
      if (localGifFrame != null)
      {
        a(localGifFrame.a);
        a(localGifFrame.b - l2 - 5L);
      }
    }
  }

  private Handler a(String paramString)
  {
    HandlerThread localHandlerThread = new HandlerThread(paramString, 10);
    localHandlerThread.start();
    return new b(this, localHandlerThread.getLooper());
  }

  private void a(long paramLong)
  {
    monitorenter;
    try
    {
      if (this.e == null)
        this.e = a("decode-" + d.getAndIncrement());
      this.e.sendEmptyMessageDelayed(0, paramLong);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(Bitmap paramBitmap)
  {
    if (paramBitmap == null);
    GifStreamImage.AsyncGifDecoder.DecodeCallback localDecodeCallback;
    do
    {
      return;
      localDecodeCallback = this.h;
    }
    while (localDecodeCallback == null);
    localDecodeCallback.a(paramBitmap);
  }

  private boolean a(Handler paramHandler, Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 0:
    case 1:
    }
    while (true)
    {
      return true;
      long l1 = SystemClock.uptimeMillis();
      GifStreamDecoder.GifFrame localGifFrame = d();
      long l2 = SystemClock.uptimeMillis() - l1;
      if (localGifFrame == null)
        continue;
      a(localGifFrame.a);
      paramHandler.sendEmptyMessageDelayed(0, localGifFrame.b - l2 - 5L);
      continue;
      e();
      Looper localLooper = paramHandler.getLooper();
      if (localLooper == null)
        continue;
      localLooper.quit();
    }
  }

  private static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  private GifStreamDecoder.GifFrame d()
  {
    GifStreamDecoder.GifFrame localGifFrame = null;
    try
    {
      synchronized (this.i)
      {
        localGifFrame = this.i.c();
        float f1 = this.g;
        if ((localGifFrame != null) && (localGifFrame.a != null) && (f1 > 1.0F))
        {
          int k = (int)(0.5F + localGifFrame.a.getWidth() / f1);
          int m = (int)(0.5F + localGifFrame.a.getHeight() / f1);
          Bitmap localBitmap = Bitmap.createScaledBitmap(localGifFrame.a, k, m, false);
          if ((localBitmap != null) && (localBitmap != localGifFrame.a))
            localGifFrame.a = localBitmap;
        }
        if ((localGifFrame == null) || (localGifFrame.a == null))
          this.j = true;
        return localGifFrame;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        ExceptionManager.a().a(localThrowable);
    }
  }

  private void e()
  {
    synchronized (this.i)
    {
      this.i.d();
      return;
    }
  }

  public void a()
  {
    monitorenter;
    try
    {
      if (this.e == null)
        a(0L);
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void b()
  {
    monitorenter;
    try
    {
      if (this.e != null)
      {
        this.e.removeMessages(0);
        this.e.removeMessages(1);
        this.e.sendEmptyMessage(1);
        this.e = null;
      }
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public boolean c()
  {
    return !this.j;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.image.image.GifStreamImage.AsyncGifDecoder
 * JD-Core Version:    0.6.0
 */