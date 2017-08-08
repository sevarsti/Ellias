package com.tencent.android.tpush.logging;

import android.content.Context;
import com.tencent.android.tpush.logging.a.h;
import com.tencent.android.tpush.logging.b.c;
import com.tencent.android.tpush.logging.b.d;
import com.tencent.android.tpush.service.i;
import java.util.ArrayList;
import java.util.List;

public class TLog
{
  private static List a = new ArrayList();

  private static String a()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    if (arrayOfStackTraceElement == null)
      return null;
    int i = arrayOfStackTraceElement.length;
    int j = 0;
    label18: StackTraceElement localStackTraceElement;
    if (j < i)
    {
      localStackTraceElement = arrayOfStackTraceElement[j];
      if (!localStackTraceElement.isNativeMethod())
        break label40;
    }
    label40: 
    do
    {
      j++;
      break label18;
      break;
    }
    while ((localStackTraceElement.getClassName().equals(Thread.class.getName())) || (localStackTraceElement.getClassName().equals(TLog.class.getName())));
    return "[" + Thread.currentThread().getName() + "(" + Thread.currentThread().getId() + "): " + localStackTraceElement.getFileName() + ":" + localStackTraceElement.getLineNumber() + "]";
  }

  private static boolean a(String paramString)
  {
    if (c.a() == null)
      c.a(i.e());
    if (d.b() == null);
    do
      return true;
    while ((c.a() == null) || (a.contains(paramString)));
    return false;
  }

  public static void d(String paramString1, String paramString2)
  {
    if (a(paramString1))
      return;
    h.b(paramString1, a() + ":" + paramString2);
  }

  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a(paramString1))
      return;
    h.b(paramString1, a() + ":" + paramString2, paramThrowable);
  }

  public static void e(String paramString1, String paramString2)
  {
    if (a(paramString1))
      return;
    h.e(paramString1, a() + ":" + paramString2);
  }

  public static void e(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a(paramString1))
      return;
    h.e(paramString1, a() + ":" + paramString2, paramThrowable);
  }

  public static void enable(boolean paramBoolean)
  {
    h.a().a(paramBoolean);
    h.a().b(paramBoolean);
  }

  public static void i(String paramString1, String paramString2)
  {
    if (a(paramString1))
      return;
    h.c(paramString1, a() + ":" + paramString2);
  }

  public static void i(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a(paramString1))
      return;
    h.c(paramString1, a() + ":" + paramString2, paramThrowable);
  }

  public static void init(Context paramContext)
  {
    if (paramContext != null)
      c.a(paramContext.getApplicationContext());
  }

  public static void stop()
  {
    h.a().b();
  }

  public static void v(String paramString1, String paramString2)
  {
    if (a(paramString1))
      return;
    h.a(paramString1, a() + ":" + paramString2);
  }

  public static void v(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a(paramString1))
      return;
    h.a(paramString1, a() + ":" + paramString2, paramThrowable);
  }

  public static void w(String paramString1, String paramString2)
  {
    if (a(paramString1))
      return;
    h.d(paramString1, a() + ":" + paramString2);
  }

  public static void w(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a(paramString1))
      return;
    h.d(paramString1, a() + ":" + paramString2, paramThrowable);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.TLog
 * JD-Core Version:    0.6.0
 */