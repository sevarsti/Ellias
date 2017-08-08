package com.tencent.component.utils.log;

import android.util.Log;
import com.tencent.component.annotation.PluginApi;
import java.io.File;

@PluginApi(a=6)
public class LogUtil
{
  public static final String a = "Benson";
  private static final LogUtil.LogProxy b = new b(null);
  private static volatile LogUtil.LogProxy c = b;

  private static String a(Throwable paramThrowable)
  {
    return Log.getStackTraceString(paramThrowable);
  }

  public static void a()
  {
    b().a();
  }

  public static void a(int paramInt)
  {
    b().a(paramInt);
  }

  public static void a(LogUtil.LogProxy paramLogProxy)
  {
    monitorenter;
    try
    {
      c = paramLogProxy;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void a(boolean paramBoolean)
  {
    b().a(paramBoolean);
  }

  private static LogUtil.LogProxy b()
  {
    LogUtil.LogProxy localLogProxy = c;
    if (localLogProxy != null)
      return localLogProxy;
    return b;
  }

  public static void b(boolean paramBoolean)
  {
    b().b(paramBoolean);
  }

  @PluginApi(a=6)
  public static void d(String paramString1, String paramString2)
  {
    b().b(paramString1, paramString2);
  }

  @PluginApi(a=6)
  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b().b(paramString1, paramString2 + '\n' + a(paramThrowable));
  }

  @PluginApi(a=6)
  public static void e(String paramString1, String paramString2)
  {
    b().e(paramString1, paramString2);
  }

  @PluginApi(a=6)
  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b().e(paramString1, paramString2 + '\n' + a(paramThrowable));
  }

  @PluginApi(a=6)
  public static File getWorkerFolder()
  {
    return b().b();
  }

  @PluginApi(a=6)
  public static void i(String paramString1, String paramString2)
  {
    b().c(paramString1, paramString2);
  }

  @PluginApi(a=6)
  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b().c(paramString1, paramString2 + '\n' + a(paramThrowable));
  }

  @PluginApi(a=6)
  public static void v(String paramString1, String paramString2)
  {
    b().a(paramString1, paramString2);
  }

  @PluginApi(a=6)
  public static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b().a(paramString1, paramString2 + '\n' + a(paramThrowable));
  }

  @PluginApi(a=6)
  public static void w(String paramString1, String paramString2)
  {
    b().d(paramString1, paramString2);
  }

  @PluginApi(a=6)
  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    b().d(paramString1, paramString2 + '\n' + a(paramThrowable));
  }

  @PluginApi(a=6)
  public static void w(String paramString, Throwable paramThrowable)
  {
    b().d(paramString, a(paramThrowable));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.LogUtil
 * JD-Core Version:    0.6.0
 */