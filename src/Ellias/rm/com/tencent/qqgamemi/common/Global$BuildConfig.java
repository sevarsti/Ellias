package com.tencent.qqgamemi.common;

import android.content.Context;
import android.content.pm.ApplicationInfo;

public final class Global$BuildConfig
{
  private static boolean a;

  public static void a(Context paramContext)
  {
    ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
    if ((localApplicationInfo != null) && ((0x2 & localApplicationInfo.flags) != 0));
    for (boolean bool = true; ; bool = false)
    {
      a = bool;
      return;
    }
  }

  public static boolean a()
  {
    return a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.common.Global.BuildConfig
 * JD-Core Version:    0.6.0
 */