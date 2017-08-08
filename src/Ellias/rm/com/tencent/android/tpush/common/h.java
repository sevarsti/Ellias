package com.tencent.android.tpush.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class h
{
  private static SharedPreferences a = null;

  public static int a(Context paramContext, String paramString, int paramInt)
  {
    return a(paramContext).getInt(paramString, paramInt);
  }

  static SharedPreferences a(Context paramContext)
  {
    monitorenter;
    try
    {
      if (a == null)
        a = paramContext.getSharedPreferences(".tpns.xml", 1);
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
    if (!a(paramContext).contains(paramString1))
      return paramString2;
    return a(paramContext).getString(paramString1, paramString2);
  }

  public static void b(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = a(paramContext).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void b(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = a(paramContext).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.common.h
 * JD-Core Version:    0.6.0
 */