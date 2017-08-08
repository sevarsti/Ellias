package com.tencent.stat.common;

import android.content.Context;

public class f
{
  static long a = -1L;

  static long a(Context paramContext, String paramString)
  {
    return p.a(paramContext, paramString, a);
  }

  static void a(Context paramContext, String paramString, long paramLong)
  {
    p.b(paramContext, paramString, paramLong);
  }

  public static boolean a(Context paramContext)
  {
    monitorenter;
    try
    {
      long l1 = a(paramContext, "1.6.2_begin_protection");
      long l2 = a(paramContext, "1.6.2_end__protection");
      if (l1 > 0L)
      {
        long l3 = a;
        if (l2 != l3);
      }
      for (int i = 0; ; i = 1)
      {
        return i;
        if (l1 != a)
          continue;
        a(paramContext, "1.6.2_begin_protection", System.currentTimeMillis());
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static void b(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a(paramContext, "1.6.2_end__protection") == a)
        a(paramContext, "1.6.2_end__protection", System.currentTimeMillis());
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
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.f
 * JD-Core Version:    0.6.0
 */