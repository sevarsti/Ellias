package com.tencent.component.utils.log;

import com.tencent.component.utils.thread.ThreadPool;
import java.io.File;

class b
  implements LogUtil.LogProxy
{
  private volatile AppTracer o;

  private b()
  {
    try
    {
      ThreadPool.getInstance().submit(new c(this));
      return;
    }
    catch (Throwable localThrowable)
    {
    }
  }

  public void a()
  {
    if (this.o != null)
      this.o.c();
  }

  public void a(int paramInt)
  {
    if (this.o != null)
      this.o.a(paramInt);
  }

  public void a(String paramString1, String paramString2)
  {
    if (this.o != null)
      this.o.a(1, paramString1, paramString2, null);
  }

  public void a(boolean paramBoolean)
  {
    if (this.o != null)
      this.o.c(paramBoolean);
  }

  public File b()
  {
    if (this.o != null)
      return this.o.g();
    return null;
  }

  public void b(String paramString1, String paramString2)
  {
    if (this.o != null)
      this.o.a(2, paramString1, paramString2, null);
  }

  public void b(boolean paramBoolean)
  {
    if (this.o != null)
      this.o.b(paramBoolean);
  }

  public void c(String paramString1, String paramString2)
  {
    if (this.o != null)
      this.o.a(4, paramString1, paramString2, null);
  }

  public void d(String paramString1, String paramString2)
  {
    if (this.o != null)
      this.o.a(8, paramString1, paramString2, null);
  }

  public void e(String paramString1, String paramString2)
  {
    if (this.o != null)
      this.o.a(16, paramString1, paramString2, null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.b
 * JD-Core Version:    0.6.0
 */