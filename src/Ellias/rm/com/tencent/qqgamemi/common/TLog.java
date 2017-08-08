package com.tencent.qqgamemi.common;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.Log;
import com.tencent.component.utils.log.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TLog
{
  private static final int A = 77;
  private static final int B = 78;
  private static long C = 0L;
  private static long D = 0L;
  private static HashMap E;
  private static HashMap F;
  public static final String a = "Benson";
  public static final String b = "Billy";
  public static final String c = "Tom";
  public static final String d = "Afeng";
  public static final String e = "ChaoQun";
  public static final String f = "City";
  public static final String g = "Bobby";
  public static final String h = "rexzou";
  public static final String i = "SYBACCOUNT";
  public static final String j = "gameAction";
  public static final String k = "halfHide";
  public static final String l = "OnTouch";
  public static final String m = "pluginOrder";
  public static final boolean n = false;
  public static boolean o = false;
  public static boolean p = false;
  public static final boolean q = true;
  public static boolean r = false;
  public static final boolean s = true;
  public static final String t = "net_log_";
  public static final String u = "serv_error_log_";
  public static String v;
  public static String w;
  private static boolean x = true;
  private static Handler.Callback y;
  private static Handler z;

  static
  {
    n = x;
    o = false;
    p = false;
    r = true;
    y = null;
    z = null;
    E = new HashMap();
    F = new HashMap();
    v = null;
    w = null;
  }

  public static void a(Context paramContext)
  {
    LogUtil.a(62);
    x = true;
  }

  public static void a(String paramString)
  {
    e("", paramString);
  }

  public static void a(String paramString1, String paramString2)
  {
    if (a())
      Log.v(paramString1, paramString2);
  }

  public static void a(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      Log.v(paramString1, paramString2, paramThrowable);
  }

  public static void a(String paramString1, String paramString2, boolean paramBoolean)
  {
    int i1 = 0;
    if (!a())
      return;
    ArrayList localArrayList1 = (ArrayList)E.get(paramString1);
    ArrayList localArrayList2;
    if (localArrayList1 == null)
    {
      localArrayList2 = new ArrayList();
      E.put(paramString1, localArrayList2);
    }
    for (ArrayList localArrayList3 = localArrayList2; ; localArrayList3 = localArrayList1)
    {
      localArrayList3.add(paramString2);
      ArrayList localArrayList4 = (ArrayList)F.get(paramString1);
      ArrayList localArrayList5;
      if (localArrayList4 == null)
      {
        localArrayList5 = new ArrayList();
        F.put(paramString1, localArrayList5);
      }
      for (ArrayList localArrayList6 = localArrayList5; ; localArrayList6 = localArrayList4)
      {
        localArrayList6.add(Long.valueOf(System.currentTimeMillis()));
        if (!paramBoolean)
          break;
        StringBuffer localStringBuffer = new StringBuffer();
        long l1 = ((Long)localArrayList6.get(0)).longValue();
        localStringBuffer.append("total time:");
        localStringBuffer.append(((Long)localArrayList6.get(-1 + localArrayList6.size())).longValue() - l1);
        localStringBuffer.append(" ");
        while (i1 < localArrayList3.size())
        {
          localStringBuffer.append(((Long)localArrayList6.get(i1)).longValue() - l1);
          l1 = ((Long)localArrayList6.get(i1)).longValue();
          localStringBuffer.append(" ");
          localStringBuffer.append((String)localArrayList3.get(i1));
          localStringBuffer.append(" ");
          i1++;
        }
        a(paramString1, localStringBuffer.toString());
        localArrayList3.clear();
        localArrayList6.clear();
        return;
      }
    }
  }

  public static void a(String paramString, boolean paramBoolean)
  {
    a("UseTime", paramString, paramBoolean);
  }

  public static void a(boolean paramBoolean)
  {
    x = paramBoolean;
  }

  public static boolean a()
  {
    return x;
  }

  public static void b()
  {
    if (a())
      LogUtil.a();
  }

  public static void b(String paramString)
  {
    if ((a()) && (z != null))
    {
      Message localMessage = Message.obtain();
      localMessage.what = 78;
      localMessage.obj = paramString;
      z.sendMessage(localMessage);
    }
  }

  public static void b(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.i(paramString1, paramString2);
    }
  }

  public static void b(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      Log.i(paramString1, paramString2, paramThrowable);
  }

  public static void c()
  {
    if ((a()) && (y == null))
    {
      y = new b();
      z = new Handler(y);
    }
  }

  public static void c(String paramString)
  {
    D = System.currentTimeMillis();
    a("time", paramString + " " + (D - C));
  }

  public static void c(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.d(paramString1, paramString2);
    }
  }

  public static void c(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      Log.e(paramString1, paramString2, paramThrowable);
  }

  public static void d()
  {
    y = null;
    z = null;
  }

  public static void d(String paramString)
  {
    a("UseTime", paramString, false);
  }

  public static void d(String paramString1, String paramString2)
  {
    if (a())
      Log.e(paramString1, paramString2);
  }

  public static void d(String paramString1, String paramString2, Throwable paramThrowable)
  {
    if (a())
      Log.w(paramString1, paramString2, paramThrowable);
  }

  public static void e()
  {
    C = System.currentTimeMillis();
  }

  public static void e(String paramString)
  {
    if (a())
    {
      StackTraceElement[] arrayOfStackTraceElement = (StackTraceElement[])Thread.getAllStackTraces().get(Thread.currentThread());
      a(paramString, "======================start============================");
      int i1 = arrayOfStackTraceElement.length;
      for (int i2 = 0; i2 < i1; i2++)
        a(paramString, arrayOfStackTraceElement[i2].toString());
      a(paramString, "=======================end============================");
    }
  }

  public static void e(String paramString1, String paramString2)
  {
    if (a())
      Log.w(paramString1, paramString2);
  }

  public static void f(String paramString1, String paramString2)
  {
    if (a())
      Log.i("Start:" + paramString1, paramString2);
  }

  public static boolean f()
  {
    return x;
  }

  public static void g(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, false);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.TLog
 * JD-Core Version:    0.6.0
 */