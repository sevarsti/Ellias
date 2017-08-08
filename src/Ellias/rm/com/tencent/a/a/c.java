package com.tencent.a.a;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Environment;
import com.tencent.a.b.b.a;
import com.tencent.a.b.d;
import java.io.File;

public class c
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  protected static final h a;
  protected static final h b;
  protected a c;
  private volatile boolean d = false;
  private volatile boolean e = true;
  private volatile boolean f = true;

  static
  {
    File localFile = a();
    a = new h(localFile, 24, 262144, 8192, "OpenSDK.Client.File.Tracer", 10000L, 10, ".app.log", 604800000L);
    b = new h(localFile, 24, 262144, 8192, "OpenSDK.File.Tracer", 10000L, 10, ".OpenSDK.log", 604800000L);
  }

  public static File a()
  {
    String str = b.a.a + File.separator + com.tencent.a.b.a.b();
    d locald = com.tencent.a.b.c.b();
    int i = 0;
    if (locald != null)
    {
      boolean bool = locald.c() < 8388608L;
      i = 0;
      if (bool)
        i = 1;
    }
    if (i != 0)
      return new File(Environment.getExternalStorageDirectory(), str);
    return new File(com.tencent.a.b.a.c(), str);
  }

  public void a(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (c())
    {
      if (!d())
        break label40;
      if (this.c != null)
        break label22;
    }
    label22: label40: 
    do
    {
      return;
      this.c.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
    }
    while (!e());
    f.a.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
  }

  public void b()
  {
    if (this.c != null)
    {
      this.c.a();
      this.c.b();
    }
  }

  public final boolean c()
  {
    return this.d;
  }

  public final boolean d()
  {
    return this.e;
  }

  public final boolean e()
  {
    return this.f;
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if ("debug.file.tracelevel".equals(paramString))
    {
      int i = paramSharedPreferences.getInt("debug.file.tracelevel", 63);
      a(8, "WnsTracer", "File Trace Level Changed = " + i, null);
      this.c.a(i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.a.a.c
 * JD-Core Version:    0.6.0
 */