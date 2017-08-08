package com.tencent.component.cache.image;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import com.tencent.component.image.image.BitmapImage;
import com.tencent.component.image.image.Image;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class k
{
  private static final String a = "ImageTracker";
  private static final String b = "ImageTracker";
  private static final int c = 19;
  private static final int d = 1048576;
  private static final int e = 16;
  private static final float f = 0.125F;
  private static final int g = 6291456;
  private static volatile k n;
  private final List h = Collections.synchronizedList(new ArrayList());
  private final ReferenceQueue i = new ReferenceQueue();
  private Thread j;
  private volatile boolean k = false;
  private final int l;
  private int m = 0;

  private k(Context paramContext)
  {
    ActivityManager localActivityManager = (ActivityManager)paramContext.getSystemService("activity");
    if (Build.VERSION.SDK_INT >= 5);
    for (int i1 = localActivityManager.getMemoryClass(); ; i1 = 16)
    {
      if (i1 <= 0)
        i1 = 16;
      this.l = a((int)(0.125F * (i1 * 1048576)), 6291456);
      return;
    }
  }

  private static int a(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2)
      return paramInt1;
    return paramInt2;
  }

  public static k a(Context paramContext)
  {
    if (n == null)
      monitorenter;
    try
    {
      if (n == null)
        n = new k(paramContext);
      return n;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(Image paramImage)
  {
    monitorenter;
    if (paramImage != null);
    while (true)
    {
      try
      {
        boolean bool1 = paramImage.d();
        if (!bool1)
          return;
        if (this.k)
          throw new IllegalStateException("No new trackers can be added once exitWhenFinished() is called");
      }
      finally
      {
        monitorexit;
      }
      if (this.j == null)
      {
        this.j = new m(this);
        this.j.start();
      }
      c(paramImage);
      boolean bool2 = paramImage instanceof BitmapImage;
      l locall = null;
      if (bool2)
        locall = new l((BitmapImage)paramImage, this.i);
      if (locall == null)
        continue;
      this.h.add(locall);
    }
  }

  private void c(Image paramImage)
  {
    monitorenter;
    try
    {
      this.m += paramImage.a();
      if (this.m >= this.l)
      {
        this.m = 0;
        System.gc();
        System.gc();
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

  public int a()
  {
    return this.h.size();
  }

  public void a(Image paramImage)
  {
    if (Build.VERSION.SDK_INT > 10)
      return;
    b(paramImage);
  }

  public void b()
  {
    monitorenter;
    try
    {
      boolean bool = this.k;
      if (bool);
      while (true)
      {
        return;
        this.k = true;
        if (this.j == null)
          continue;
        synchronized (this.j)
        {
          this.j.interrupt();
        }
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.image.k
 * JD-Core Version:    0.6.0
 */