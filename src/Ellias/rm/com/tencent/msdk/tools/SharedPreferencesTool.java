package com.tencent.msdk.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPreferencesTool
{
  private static String msdkShareKey = "msdk";

  public static void cleanKey(Context paramContext, String paramString)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(msdkShareKey, 0).edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }

  public static boolean getBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    return paramContext.getSharedPreferences(msdkShareKey, 0).getBoolean(paramString, paramBoolean);
  }

  public static int getInt(Context paramContext, String paramString, int paramInt)
  {
    return paramContext.getSharedPreferences(msdkShareKey, 0).getInt(paramString, paramInt);
  }

  public static long getLong(Context paramContext, String paramString, long paramLong)
  {
    return paramContext.getSharedPreferences(msdkShareKey, 0).getLong(paramString, paramLong);
  }

  public static String getString(Context paramContext, String paramString1, String paramString2)
  {
    return paramContext.getSharedPreferences(msdkShareKey, 0).getString(paramString1, paramString2);
  }

  public static void putBoolean(Context paramContext, String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(msdkShareKey, 0).edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public static void putInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(msdkShareKey, 0).edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void putLong(Context paramContext, String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(msdkShareKey, 0).edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }

  public static void putString(Context paramContext, String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences(msdkShareKey, 0).edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.tools.SharedPreferencesTool
 * JD-Core Version:    0.6.0
 */