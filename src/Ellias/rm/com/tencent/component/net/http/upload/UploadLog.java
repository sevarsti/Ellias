package com.tencent.component.net.http.upload;

import com.tencent.component.ComponentContext;
import com.tencent.component.utils.DebugUtil;
import com.tencent.component.utils.log.LogUtil;

public class UploadLog
{
  private static Boolean a = null;

  public static void a(String paramString1, String paramString2)
  {
    if (a())
      LogUtil.v(paramString1, paramString2);
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      LogUtil.v(paramString1, paramString2, paramThrowable);
  }

  private static boolean a()
  {
    if (a == null)
      a = Boolean.valueOf(DebugUtil.a(ComponentContext.a()));
    return a.booleanValue();
  }

  public static void b(String paramString1, String paramString2)
  {
    if (a())
      LogUtil.d(paramString1, paramString2);
  }

  public static void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      LogUtil.d(paramString1, paramString2, paramThrowable);
  }

  public static void c(String paramString1, String paramString2)
  {
    if (a())
      LogUtil.i(paramString1, paramString2);
  }

  public static void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      LogUtil.i(paramString1, paramString2, paramThrowable);
  }

  public static void d(String paramString1, String paramString2)
  {
    if (a())
      LogUtil.w(paramString1, paramString2);
  }

  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      LogUtil.w(paramString1, paramString2, paramThrowable);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (a())
      LogUtil.e(paramString1, paramString2);
  }

  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      LogUtil.e(paramString1, paramString2, paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.UploadLog
 * JD-Core Version:    0.6.0
 */