package com.tencent.tmassistantsdk.g;

import android.util.Log;
import java.util.HashMap;

public class l
{
  protected static boolean a = true;
  protected static HashMap b = new HashMap();
  protected static HashMap c = new HashMap();
  protected static boolean d = false;
  protected static String e = "";

  public static void a(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.v(paramString1, paramString2);
    }
  }

  public static boolean a()
  {
    return a;
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

  public static void c(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.d(paramString1, paramString2);
    }
  }

  public static void d(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.w(paramString1, paramString2);
    }
  }

  public static void e(String paramString1, String paramString2)
  {
    if (a())
    {
      if (paramString2 == null)
        paramString2 = "............";
      Log.e(paramString1, paramString2);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.g.l
 * JD-Core Version:    0.6.0
 */