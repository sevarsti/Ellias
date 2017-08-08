package com.tencent.component.cache.sp;

import android.content.Context;
import android.content.SharedPreferences;
import com.tencent.component.utils.SecurityUtil;
import java.io.File;

public class PreferenceUtil
{
  private static final String a = "global";
  private static final String b = "preference";
  private static final String c = "cache";

  public static SharedPreferences a(Context paramContext)
  {
    return a(paramContext, null);
  }

  public static SharedPreferences a(Context paramContext, float paramFloat)
  {
    return a(paramContext, null, paramFloat);
  }

  public static SharedPreferences a(Context paramContext, long paramLong)
  {
    return a(paramContext, paramLong, null);
  }

  public static SharedPreferences a(Context paramContext, long paramLong, float paramFloat)
  {
    return a(paramContext, paramLong, null);
  }

  public static SharedPreferences a(Context paramContext, long paramLong, String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      paramString = "preference";
    String str1 = paramString.replaceAll(File.separator, "%2F");
    if (paramLong == 0L);
    for (String str2 = "global"; ; str2 = SecurityUtil.a(String.valueOf(paramLong)))
      return paramContext.getSharedPreferences(paramContext.getPackageName() + "_" + str2 + "_" + str1, 0);
  }

  public static SharedPreferences a(Context paramContext, long paramLong, String paramString, float paramFloat)
  {
    if ((paramString == null) || (paramString.length() == 0))
      paramString = "preference";
    if (paramLong == 0L);
    for (String str = "global"; ; str = SecurityUtil.a(String.valueOf(paramLong)))
      return paramContext.getSharedPreferences(paramContext.getPackageName() + "_" + str + "_" + paramString + "_" + paramFloat, 0);
  }

  public static SharedPreferences a(Context paramContext, String paramString)
  {
    return a(paramContext, 0L, paramString);
  }

  public static SharedPreferences a(Context paramContext, String paramString, float paramFloat)
  {
    return a(paramContext, 0L, paramString, paramFloat);
  }

  public static SharedPreferences b(Context paramContext)
  {
    return a(paramContext, "cache");
  }

  public static SharedPreferences b(Context paramContext, float paramFloat)
  {
    return a(paramContext, "cache", paramFloat);
  }

  public static SharedPreferences b(Context paramContext, long paramLong)
  {
    return a(paramContext, paramLong, "cache");
  }

  public static SharedPreferences b(Context paramContext, long paramLong, float paramFloat)
  {
    return a(paramContext, paramLong, "cache", paramFloat);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.cache.sp.PreferenceUtil
 * JD-Core Version:    0.6.0
 */