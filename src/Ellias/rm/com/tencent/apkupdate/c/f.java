package com.tencent.apkupdate.c;

import android.util.Log;
import java.util.HashMap;

public final class f
{
  private static boolean a = true;

  static
  {
    new HashMap();
    new HashMap();
  }

  public static void a(String paramString1, String paramString2)
  {
    if (a)
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.i(paramString1, paramString2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.apkupdate.c.f
 * JD-Core Version:    0.6.0
 */