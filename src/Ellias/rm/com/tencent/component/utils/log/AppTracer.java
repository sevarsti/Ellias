package com.tencent.component.utils.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;
import com.tencent.component.ComponentContext;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.debug.FileTracer;
import com.tencent.component.debug.FileTracerConfig;
import com.tencent.component.debug.LogcatTracer;
import com.tencent.component.debug.TraceLevel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class AppTracer
  implements SharedPreferences.OnSharedPreferenceChangeListener, TraceLevel
{
  private FileTracer o;
  private volatile boolean p = true;
  private volatile boolean q = true;
  private volatile boolean r = true;

  public AppTracer(FileTracerConfig paramFileTracerConfig)
  {
    this.o = new FileTracer(paramFileTracerConfig);
    LogConfig.a().a(this);
  }

  public static File a()
  {
    String str;
    if (CacheManager.a())
    {
      str = CacheManager.c(ComponentContext.a(), "logs", true);
      if (TextUtils.isEmpty(str));
    }
    while (true)
    {
      try
      {
        localFile1 = new File(str);
        if (localFile1 != null);
      }
      catch (Exception localException2)
      {
        try
        {
          File localFile2 = new File(ComponentContext.a().getFilesDir(), "logs");
          return localFile2;
          localException2 = localException2;
          localFile1 = null;
        }
        catch (Exception localException1)
        {
          return localFile1;
        }
      }
      return localFile1;
      File localFile1 = null;
    }
  }

  public static void a(File paramFile)
  {
    if ((paramFile == null) || (!paramFile.exists()));
    while (true)
    {
      return;
      if (paramFile.isFile())
      {
        paramFile.delete();
        return;
      }
      File[] arrayOfFile = paramFile.listFiles();
      int i = arrayOfFile.length;
      for (int j = 0; j < i; j++)
        a(arrayOfFile[j]);
    }
  }

  public final void a(int paramInt)
  {
    this.o.a(paramInt);
  }

  public void a(int paramInt, String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (d())
    {
      if ((e()) && (this.o != null))
        this.o.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
      if (f())
        LogcatTracer.a.b(paramInt, Thread.currentThread(), System.currentTimeMillis(), paramString1, paramString2, paramThrowable);
    }
  }

  public final void a(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }

  public BufferedReader b(int paramInt)
  {
    File localFile1 = this.o.c().a(System.currentTimeMillis());
    if ((localFile1 == null) || (!localFile1.isDirectory()))
      return null;
    File[] arrayOfFile1 = this.o.c().b(localFile1);
    File[] arrayOfFile2 = this.o.c().b(arrayOfFile1);
    if ((paramInt >= 0) && (paramInt < arrayOfFile2.length))
    {
      File localFile2 = arrayOfFile2[(-1 + (arrayOfFile2.length - paramInt))];
      try
      {
        BufferedReader localBufferedReader = new BufferedReader(new FileReader(localFile2));
        return localBufferedReader;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        return null;
      }
    }
    return null;
  }

  public void b()
  {
    if (this.o != null)
    {
      this.o.a();
      this.o.b();
    }
  }

  public final void b(boolean paramBoolean)
  {
    this.o.a();
    this.q = paramBoolean;
  }

  public void c()
  {
    if (this.o != null)
      this.o.a();
  }

  public final void c(boolean paramBoolean)
  {
    this.r = paramBoolean;
  }

  public final boolean d()
  {
    return this.p;
  }

  public final boolean e()
  {
    return this.q;
  }

  public final boolean f()
  {
    return this.r;
  }

  public File g()
  {
    return this.o.c().a(System.currentTimeMillis());
  }

  public void h()
  {
    File localFile = this.o.c().a(System.currentTimeMillis());
    File[] arrayOfFile = this.o.c().b(localFile);
    if (arrayOfFile != null)
      for (int i = 0; i < arrayOfFile.length; i++)
        a(arrayOfFile[i]);
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (("debug.file.tracelevel".equals(paramString)) || (paramString == null))
    {
      int i = LogConfig.a().d();
      a(16, "WnsTracer", "File Trace Level Changed = " + i, null);
      this.o.a(i);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.AppTracer
 * JD-Core Version:    0.6.0
 */