package com.tencent.android.tpush.logging.a;

import android.os.Environment;
import com.tencent.android.tpush.logging.b.c;
import com.tencent.android.tpush.logging.b.d;
import com.tencent.android.tpush.logging.b.e;
import java.io.File;

public class i
{
  protected b a;
  protected a b;
  private volatile boolean c = true;
  private volatile boolean d = true;
  private volatile boolean e = false;

  public static File c()
  {
    String str = com.tencent.android.tpush.logging.b.b.a;
    try
    {
      e locale = d.b();
      int i = 0;
      if (locale != null)
      {
        boolean bool = locale.c() < 8388608L;
        i = 0;
        if (bool)
          i = 1;
      }
      if (i != 0)
        return new File(Environment.getExternalStorageDirectory(), str);
      File localFile = new File(c.b(), str);
      return localFile;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public void a(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (e())
    {
      if (!f())
        break label40;
      if (this.b != null)
        break label22;
    }
    label22: label40: 
    do
    {
      return;
      this.b.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
    }
    while (!g());
    f.a.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public final void a(boolean paramBoolean)
  {
    this.b.a();
    this.d = paramBoolean;
  }

  public void b()
  {
    if (this.b != null)
    {
      this.b.a();
      this.b.b();
    }
  }

  public final void b(boolean paramBoolean)
  {
    this.e = paramBoolean;
  }

  public void d()
  {
    File localFile = c();
    if (com.tencent.android.tpush.service.a.a.s == 0);
    for (int i = com.tencent.android.tpush.service.a.a.a("logFileSizeLimit", 262144); ; i = com.tencent.android.tpush.service.a.a.s)
    {
      this.a = new b(localFile, 24, i, 4096, "TPush.Client.File.Tracer", 20000L, 10, ".app.log", 604800000L);
      return;
    }
  }

  public final boolean e()
  {
    return this.c;
  }

  public final boolean f()
  {
    return this.d;
  }

  public final boolean g()
  {
    return this.e;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.a.i
 * JD-Core Version:    0.6.0
 */