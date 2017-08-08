package com.tencent.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class p
{
  private static SharedPreferences a = null;

  public static int a(Context paramContext, String paramString, int paramInt)
  {
    String str = k.b(paramContext, "" + paramString);
    return a(paramContext).getInt(str, paramInt);
  }

  public static long a(Context paramContext, String paramString, long paramLong)
  {
    String str = k.b(paramContext, "" + paramString);
    return a(paramContext).getLong(str, paramLong);
  }

  static SharedPreferences a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = PreferenceManager.getDefaultSharedPreferences(paramContext);
      SharedPreferences localSharedPreferences = a;
      return localSharedPreferences;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public static String a(Context paramContext, String paramString1, String paramString2)
  {
    String str = k.b(paramContext, "" + paramString1);
    return a(paramContext).getString(str, paramString2);
  }

  public static void b(Context paramContext, String paramString, int paramInt)
  {
    String str = k.b(paramContext, "" + paramString);
    SharedPreferences.Editor localEditor = a(paramContext).edit();
    localEditor.putInt(str, paramInt);
    localEditor.commit();
  }

  public static void b(Context paramContext, String paramString, long paramLong)
  {
    String str = k.b(paramContext, "" + paramString);
    SharedPreferences.Editor localEditor = a(paramContext).edit();
    localEditor.putLong(str, paramLong);
    localEditor.commit();
  }

  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    String str = k.b(paramContext, "" + paramString1);
    SharedPreferences.Editor localEditor = a(paramContext).edit();
    localEditor.putString(str, paramString2);
    localEditor.commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.common.p
 * JD-Core Version:    0.6.0
 */