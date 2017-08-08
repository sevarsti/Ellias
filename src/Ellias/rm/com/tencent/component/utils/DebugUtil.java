package com.tencent.component.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import com.tencent.component.ComponentContext;

public class DebugUtil
{
  private static Boolean a;

  public static boolean a()
  {
    return a(ComponentContext.a());
  }

  public static boolean a(Context paramContext)
  {
    if (a == null)
    {
      ApplicationInfo localApplicationInfo = paramContext.getApplicationInfo();
      if ((localApplicationInfo == null) || ((0x2 & localApplicationInfo.flags) == 0))
        break label40;
    }
    label40: for (boolean bool = true; ; bool = false)
    {
      a = Boolean.valueOf(bool);
      return a.booleanValue();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.DebugUtil
 * JD-Core Version:    0.6.0
 */