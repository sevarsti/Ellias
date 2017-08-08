package com.tencent.feedback.common;

import android.content.Context;
import com.tencent.feedback.common.b.f.a;
import com.tencent.feedback.common.b.g;
import com.tencent.feedback.common.b.i;
import com.tencent.feedback.upload.UploadHandleListener;

public class j
  implements com.tencent.feedback.common.b.b, g
{
  protected final Context a;
  private int b;
  private int c;
  private int d;
  private boolean e;
  private boolean f;
  private com.tencent.feedback.upload.f g;
  private com.tencent.feedback.upload.e h;
  private int i = 0;

  public j(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, com.tencent.feedback.upload.f paramf, com.tencent.feedback.upload.e parame, UploadHandleListener paramUploadHandleListener)
  {
    this.a = paramContext.getApplicationContext();
    if (c.p() == null)
      c.a(this.a, paramString, "unknown");
    while (true)
    {
      this.b = 3;
      this.c = 202;
      this.d = 302;
      this.g = paramf;
      this.h = parame;
      if (paramf != null)
      {
        paramf.a(302, parame);
        paramf.a(paramUploadHandleListener);
      }
      com.tencent.feedback.common.b.c localc = com.tencent.feedback.common.b.c.a(this.a);
      localc.a(this);
      localc.a(this);
      localc.a(3, paramf);
      return;
      if ((paramString == null) || ("10000".equals(paramString)) || (paramString.trim().length() <= 0))
        continue;
      c.p().a(paramString);
    }
  }

  private void a(int paramInt)
  {
    monitorenter;
    try
    {
      this.i = paramInt;
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

  private void c(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.f = true;
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

  private com.tencent.feedback.upload.e k()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.upload.e locale = this.h;
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

  private int l()
  {
    monitorenter;
    try
    {
      int j = this.i;
      monitorexit;
      return j;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void a(com.tencent.feedback.common.b.f paramf)
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = getClass().toString();
    e.h("rqdp{  com strateyg changed }%s", arrayOfObject1);
    if (paramf != null)
    {
      f.a locala = paramf.e(this.b);
      if (locala != null)
        if ((!locala.c()) || (!locala.b()))
          break label104;
    }
    label104: for (boolean bool = true; ; bool = false)
    {
      if (a() != bool)
      {
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(this.b);
        arrayOfObject2[1] = Boolean.valueOf(bool);
        e.f("rqdp{  module} %d rqdp{  able changed }%b", arrayOfObject2);
        b(bool);
      }
      return;
    }
  }

  public final void a(boolean paramBoolean)
  {
    boolean bool = true;
    com.tencent.feedback.common.b.c localc = com.tencent.feedback.common.b.c.a(this.a);
    if (localc != null)
    {
      f.a locala = localc.b().e(this.b);
      if ((locala != null) && (locala.b() != paramBoolean))
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(this.b);
        arrayOfObject[bool] = Boolean.valueOf(paramBoolean);
        e.e("rqdp{  mid:}%d rqdp{  change user switch} %b", arrayOfObject);
        locala.a(paramBoolean);
        if ((!locala.b()) || (!locala.c()))
          break label109;
      }
    }
    while (true)
    {
      if (bool != a())
        b(bool);
      return;
      label109: bool = false;
    }
  }

  public final boolean a()
  {
    monitorenter;
    try
    {
      boolean bool = this.e;
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

  public void b(boolean paramBoolean)
  {
    monitorenter;
    try
    {
      this.e = paramBoolean;
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

  public final boolean b()
  {
    monitorenter;
    try
    {
      boolean bool = this.f;
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

  public final com.tencent.feedback.upload.f c()
  {
    monitorenter;
    try
    {
      com.tencent.feedback.upload.f localf = this.g;
      monitorexit;
      return localf;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public final void d()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getClass().toString();
    e.h("rqdp{  com query start }%s", arrayOfObject);
    a(1 + l());
  }

  public void e()
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = getClass().toString();
    e.h("rqdp{  com query end }%s", arrayOfObject1);
    if (!b())
    {
      e.b("rqdp{  step after query}", new Object[0]);
      c(true);
    }
    while (true)
    {
      try
      {
        com.tencent.feedback.common.b.f localf = com.tencent.feedback.common.b.c.a(this.a).b();
        if (localf == null)
          return;
        f.a locala = localf.e(this.b);
        if ((!a()) || (locala == null))
          break label367;
        e.b("rqdp{  isable}", new Object[0]);
        boolean bool1 = locala.d();
        boolean bool2 = localf.k();
        if (!bool1)
          break label377;
        if (bool2)
        {
          bool3 = true;
          Object[] arrayOfObject3 = new Object[3];
          arrayOfObject3[0] = Boolean.valueOf(locala.d());
          arrayOfObject3[1] = Boolean.valueOf(localf.k());
          arrayOfObject3[2] = Boolean.valueOf(bool3);
          e.b("rqdp{  needDetail} %b rqdp{  allQ:}%b rqdp{  result:}%b", arrayOfObject3);
          if (g() <= 0)
            break label383;
          j = 1;
          if (j == 0)
            break label322;
          Object[] arrayOfObject4 = new Object[1];
          arrayOfObject4[0] = Integer.valueOf(this.b);
          e.e("rqdp{  asyn up module} %d", arrayOfObject4);
          b.b().a(new Runnable()
          {
            public final void run()
            {
              j.this.h();
            }
          });
          return;
        }
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = localThrowable.toString();
        e.d("rqdp{  common query end error} %s", arrayOfObject2);
        return;
      }
      if (!i())
      {
        i locali = com.tencent.feedback.anr.a.a(this.a, this.d);
        if (locali != null)
        {
          com.tencent.feedback.upload.e locale = k();
          if (locale == null)
            e.c("rqdp{  imposiable eup reshandler not ready}", new Object[0]);
          else
            locale.a(this.d, locali.c(), false);
        }
        else if (!i())
        {
          bool3 = true;
          continue;
          label322: if (bool3)
          {
            Object[] arrayOfObject5 = new Object[1];
            arrayOfObject5[0] = Integer.valueOf(this.b);
            e.e("rqdp{  asyn query module }%d", arrayOfObject5);
            b.b().a(new Runnable()
            {
              public final void run()
              {
                j.this.j();
              }
            });
            return;
            label367: e.b("rqdp{  disable}", new Object[0]);
          }
          return;
        }
      }
      label377: boolean bool3 = false;
      continue;
      label383: int j = 0;
    }
  }

  public void f()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getClass().toString();
    e.h("rqdp{  app first start} %s", arrayOfObject);
  }

  public int g()
  {
    if (!a())
      return -1;
    return 0;
  }

  public boolean h()
  {
    return a();
  }

  public boolean i()
  {
    return true;
  }

  public final boolean j()
  {
    if (!a());
    com.tencent.feedback.upload.f localf;
    do
    {
      return false;
      localf = c();
    }
    while (localf == null);
    localf.a(new com.tencent.feedback.upload.a(this.a, this.b, this.c));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.common.j
 * JD-Core Version:    0.6.0
 */