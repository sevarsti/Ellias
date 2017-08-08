package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.j;
import com.tencent.feedback.upload.UploadHandleListener;
import com.tencent.feedback.upload.g;

public final class e extends j
{
  private static e b;
  private CrashStrategyBean c = null;
  private CrashStrategyBean d = null;
  private b e = null;
  private CrashHandleListener f = null;
  private final boolean g;

  private e(Context paramContext, String paramString, boolean paramBoolean, com.tencent.feedback.upload.f paramf, UploadHandleListener paramUploadHandleListener, CrashHandleListener paramCrashHandleListener, CrashStrategyBean paramCrashStrategyBean)
  {
    super(paramContext, paramString, 3, 202, 302, paramf, new c(paramContext.getApplicationContext()), paramUploadHandleListener);
    if (paramCrashStrategyBean != null)
      com.tencent.feedback.common.e.e("rqdp{  cus eupstrategy} %s", new Object[] { paramCrashStrategyBean });
    for (this.c = paramCrashStrategyBean; ; this.c = new CrashStrategyBean())
    {
      this.e = b.a(this.a);
      this.f = paramCrashHandleListener;
      this.g = paramBoolean;
      return;
      com.tencent.feedback.common.e.e("rqdp{  default eupstrategy}", new Object[0]);
    }
  }

  public static e a(Context paramContext, String paramString, boolean paramBoolean, com.tencent.feedback.upload.f paramf, UploadHandleListener paramUploadHandleListener, CrashHandleListener paramCrashHandleListener, CrashStrategyBean paramCrashStrategyBean)
  {
    monitorenter;
    try
    {
      if (b == null)
      {
        com.tencent.feedback.common.e.e("rqdp{  eup create instance}", new Object[0]);
        e locale2 = new e(paramContext, paramString, false, paramf, paramUploadHandleListener, paramCrashHandleListener, paramCrashStrategyBean);
        b = locale2;
        locale2.a(true);
      }
      e locale1 = b;
      return locale1;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static com.tencent.feedback.upload.f a(Context paramContext, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      g localg = g.a(paramContext, paramBoolean);
      monitorexit;
      return localg;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean a(Thread paramThread, Throwable paramThrowable, String paramString, byte[] paramArrayOfByte)
  {
    com.tencent.feedback.common.e.e("rqdp{  handleCatchException}", new Object[0]);
    if (!m())
      return false;
    e locale = k();
    if (locale == null)
    {
      com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
      return false;
    }
    if (locale.a());
    while (true)
    {
      try
      {
        b localb = locale.s();
        if (localb != null)
          break label125;
        com.tencent.feedback.common.e.c("rqdp{  imposiable chandler null!}", new Object[0]);
        return false;
        return localb.a((String)localObject, paramThrowable, paramString, paramArrayOfByte, false);
        String str = paramThread.getName();
        localObject = str;
        continue;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.toString();
        com.tencent.feedback.common.e.d("rqdp{  handleCatchException error} %s", arrayOfObject);
      }
      return false;
      label125: if (paramThread != null)
        continue;
      Object localObject = null;
    }
  }

  public static e k()
  {
    monitorenter;
    try
    {
      e locale = b;
      monitorexit;
      return locale;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public static boolean l()
  {
    if (!m())
      return false;
    com.tencent.feedback.common.e.e("rqdp{  doUploadExceptionDatas}", new Object[0]);
    e locale = k();
    if (locale == null)
    {
      com.tencent.feedback.common.e.c("rqdp{  instance == null}", new Object[0]);
      return false;
    }
    return locale.h();
  }

  public static boolean m()
  {
    boolean bool = false;
    e locale = k();
    if (locale == null)
      com.tencent.feedback.common.e.d("rqdp{  not init eup}", new Object[0]);
    do
    {
      return bool;
      bool = locale.a();
    }
    while ((!bool) || (!locale.r()));
    return locale.b();
  }

  private boolean r()
  {
    monitorenter;
    try
    {
      boolean bool = this.g;
      monitorexit;
      return bool;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private b s()
  {
    monitorenter;
    try
    {
      b localb = this.e;
      monitorexit;
      return localb;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void a(CrashStrategyBean paramCrashStrategyBean)
  {
    monitorenter;
    try
    {
      this.d = paramCrashStrategyBean;
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

  public final void b(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      super.b(paramBoolean);
      if (a())
        this.e.a();
      while (true)
      {
        return;
        this.e.b();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void e()
  {
    int i = -1;
    super.e();
    Context localContext = this.a;
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.deleteEup() start}", new Object[0]);
    if (localContext == null)
      com.tencent.feedback.common.e.c("rqdp{  deleteEup() context is null arg}", new Object[0]);
    while (true)
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      com.tencent.feedback.common.e.b("remove fail updata num :%d", arrayOfObject);
      return;
      i = com.tencent.feedback.common.a.a.a(localContext, new int[] { 1, 2 }, -1L, 9223372036854775807L, 3, i);
    }
  }

  public final void f()
  {
    int i = -1;
    super.f();
    Context localContext = this.a;
    com.tencent.feedback.common.e.b("rqdp{  EUPDAO.deleteEup() start}", new Object[0]);
    if (localContext == null)
      com.tencent.feedback.common.e.c("rqdp{  deleteEup() context is null arg}", new Object[0]);
    while (true)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(i);
      com.tencent.feedback.common.e.e("rqdp{  eup clear} %d ", arrayOfObject1);
      int j = com.tencent.feedback.anr.a.b(this.a, 302);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(j);
      com.tencent.feedback.common.e.e("rqdp{  eup strategy clear} %d ", arrayOfObject2);
      return;
      i = com.tencent.feedback.common.a.a.a(localContext, new int[] { 1, 2 }, -1L, 9223372036854775807L, i, i);
    }
  }

  public final int g()
  {
    CrashStrategyBean localCrashStrategyBean = q();
    if ((localCrashStrategyBean != null) && (super.g() >= 0))
    {
      int i;
      if (!localCrashStrategyBean.isMerged())
      {
        com.tencent.feedback.common.e.e("rqdp{  in no merge}", new Object[0]);
        i = c.a(this.a);
      }
      boolean bool;
      do
      {
        return i;
        com.tencent.feedback.common.e.e("rqdp{  in merge}", new Object[0]);
        bool = s().c();
        i = 0;
      }
      while (!bool);
      return 1;
    }
    return -1;
  }

  public final boolean h()
  {
    if (super.h())
    {
      f localf = f.a(this.a);
      com.tencent.feedback.upload.f localf1 = c();
      if ((localf == null) || (localf1 == null))
      {
        com.tencent.feedback.common.e.c("rqdp{  upDatas or uphandler null!}", new Object[0]);
        return false;
      }
      try
      {
        localf1.a(localf);
        return true;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localThrowable.toString();
        com.tencent.feedback.common.e.d("rqdp{  upload eupdata error} %s", arrayOfObject);
      }
    }
    return false;
  }

  public final boolean i()
  {
    return o() != null;
  }

  public final CrashStrategyBean n()
  {
    monitorenter;
    try
    {
      CrashStrategyBean localCrashStrategyBean = this.c;
      monitorexit;
      return localCrashStrategyBean;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final CrashStrategyBean o()
  {
    monitorenter;
    try
    {
      CrashStrategyBean localCrashStrategyBean = this.d;
      monitorexit;
      return localCrashStrategyBean;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final CrashHandleListener p()
  {
    monitorenter;
    try
    {
      CrashHandleListener localCrashHandleListener = this.f;
      monitorexit;
      return localCrashHandleListener;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final CrashStrategyBean q()
  {
    while (true)
    {
      try
      {
        if (com.tencent.feedback.common.b.c.a(this.a).b().i())
        {
          localObject = o();
          if (localObject != null)
            continue;
          CrashStrategyBean localCrashStrategyBean = n();
          localObject = localCrashStrategyBean;
          return localObject;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        return null;
      }
      Object localObject = null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.eup.e
 * JD-Core Version:    0.6.0
 */