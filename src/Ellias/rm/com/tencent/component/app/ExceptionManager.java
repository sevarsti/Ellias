package com.tencent.component.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.cache.image.ImageCacheService;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.OOMHelper;

public class ExceptionManager
{
  private static final String a = "ExceptionManager";
  private volatile Context b;
  private volatile ExceptionManager.ExceptionInterceptor c;

  public static ExceptionManager a()
  {
    return c.a;
  }

  private static void a(Context paramContext, CharSequence paramCharSequence)
  {
    if (b())
    {
      Toast.makeText(paramContext, paramCharSequence, 1).show();
      return;
    }
    d.a.post(new b(paramContext, paramCharSequence));
  }

  private void a(OutOfMemoryError paramOutOfMemoryError)
  {
    Context localContext = this.b;
    if (localContext != null)
      CacheManager.a(localContext).b();
    System.gc();
    System.gc();
  }

  private void b(Throwable paramThrowable)
  {
    Context localContext = this.b;
    if (localContext == null);
    do
      return;
    while ((!OOMHelper.a(localContext, paramThrowable)) || (!DebugUtil.a(localContext)));
    a(localContext, "OOM occurs!!!");
  }

  private static boolean b()
  {
    Looper localLooper = Looper.getMainLooper();
    return (localLooper != null) && (localLooper.getThread() == Thread.currentThread());
  }

  public void a(Context paramContext)
  {
    if (paramContext == null);
    do
      return;
    while (this.b != null);
    monitorenter;
    try
    {
      if (this.b != null)
        return;
    }
    finally
    {
      monitorexit;
    }
    this.b = paramContext.getApplicationContext();
    monitorexit;
  }

  public void a(ExceptionManager.ExceptionInterceptor paramExceptionInterceptor)
  {
    this.c = paramExceptionInterceptor;
  }

  public void a(Throwable paramThrowable)
  {
    if (paramThrowable == null);
    ExceptionManager.ExceptionInterceptor localExceptionInterceptor;
    do
    {
      return;
      b(paramThrowable);
      localExceptionInterceptor = this.c;
    }
    while (((localExceptionInterceptor != null) && (localExceptionInterceptor.a(paramThrowable))) || (!(paramThrowable instanceof OutOfMemoryError)));
    a((OutOfMemoryError)paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.app.ExceptionManager
 * JD-Core Version:    0.6.0
 */