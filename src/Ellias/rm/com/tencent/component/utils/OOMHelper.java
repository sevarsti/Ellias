package com.tencent.component.utils;

import android.content.Context;
import android.os.Debug;
import android.view.InflateException;
import com.tencent.component.cache.CacheManager;
import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OOMHelper
{
  private static final String a = "OOMHelper";
  private static final String b = "oom";
  private static final String c = ".hprof";
  private static ThreadLocal d = new e();

  private static String a()
  {
    return ((SimpleDateFormat)d.get()).format(new Date(System.currentTimeMillis()));
  }

  public static String a(Context paramContext)
  {
    if (paramContext == null)
      return null;
    return b(paramContext);
  }

  public static boolean a(Context paramContext, Throwable paramThrowable)
  {
    if (!DebugUtil.a(paramContext));
    do
      return false;
    while ((paramContext == null) || (paramThrowable == null) || (!a(paramThrowable)));
    try
    {
      String str1 = b(paramContext);
      String str2 = a() + "#" + paramThrowable.getClass().getSimpleName() + ".hprof";
      String str3;
      if (str1 != null)
      {
        str3 = str1 + File.separator + str2;
        if (str3 == null)
          break label164;
      }
      label164: for (File localFile = new File(str3); ; localFile = null)
      {
        if ((localFile != null) && (!DebugUtil.a(paramContext)))
          FileUtil.a(localFile.getParentFile(), true);
        if ((localFile != null) && (b(localFile.getParentFile())))
          Debug.dumpHprofData(str3);
        return true;
        str3 = null;
        break;
      }
    }
    catch (Throwable localThrowable)
    {
      while (true)
        LogUtil.w("OOMHelper", "fail to dump hprof", localThrowable);
    }
  }

  private static boolean a(File paramFile)
  {
    return (paramFile != null) && (paramFile.isDirectory()) && (paramFile.exists());
  }

  public static boolean a(Throwable paramThrowable)
  {
    int k;
    for (int i = 0; ; i = k)
    {
      int j = 0;
      if (paramThrowable != null)
      {
        k = i + 1;
        j = 0;
        if (i < 5)
        {
          if (!b(paramThrowable))
            break label30;
          j = 1;
        }
      }
      return j;
      label30: paramThrowable = paramThrowable.getCause();
    }
  }

  private static String b(Context paramContext)
  {
    return CacheManager.b(paramContext, "oom", true);
  }

  private static boolean b(File paramFile)
  {
    if (paramFile == null)
      return false;
    if (!a(paramFile))
    {
      FileUtil.a(paramFile);
      return paramFile.mkdirs();
    }
    return true;
  }

  private static boolean b(Throwable paramThrowable)
  {
    if (paramThrowable == null);
    do
      return false;
    while ((!(paramThrowable instanceof OutOfMemoryError)) && (!(paramThrowable instanceof InflateException)));
    return true;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.OOMHelper
 * JD-Core Version:    0.6.0
 */