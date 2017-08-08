package com.tencent.qqgamemi.business;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.qqgamemi.common.TLog;

public class QMiEnvironmentHelper
{
  public static Long a;
  private static QMiEnvironmentHelper b = null;

  static
  {
    a = Long.valueOf(0L);
  }

  public static QMiEnvironmentHelper a()
  {
    if (b == null)
      b = new QMiEnvironmentHelper();
    return b;
  }

  public static void a(Context paramContext)
  {
    a = Long.valueOf(b(paramContext));
  }

  public static void a(Context paramContext, long paramLong)
  {
    TLog.c("gameAction", "setUin:" + paramLong);
    a = Long.valueOf(paramLong);
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("LastUin", 2).edit();
    localEditor.putLong("getLastUin", paramLong);
    localEditor.commit();
  }

  private static long b(Context paramContext)
  {
    long l = paramContext.getSharedPreferences("LastUin", 0).getLong("getLastUin", 0L);
    TLog.c("gameAction", "getLastUin:" + l);
    return l;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.QMiEnvironmentHelper
 * JD-Core Version:    0.6.0
 */