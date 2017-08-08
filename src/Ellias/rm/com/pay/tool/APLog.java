package com.pay.tool;

import android.util.Log;

public class APLog
{
  private static boolean a = true;

  public static void d(String paramString1, String paramString2)
  {
    if (a)
      Log.d("TencentPay", paramString1 + " | " + paramString2);
  }

  public static void e(String paramString1, String paramString2)
  {
    Log.e("TencentPay", paramString1 + " | " + paramString2);
  }

  public static String getLine()
  {
    StackTraceElement localStackTraceElement = new java.lang.Throwable().getStackTrace()[1];
    int i = localStackTraceElement.getLineNumber();
    return localStackTraceElement.getFileName() + " Line:" + String.valueOf(i) + " ";
  }

  public static boolean getLogEnable()
  {
    return a;
  }

  public static void i(String paramString1, String paramString2)
  {
    if (a)
      Log.i("TencentPay", paramString1 + " | " + paramString2);
  }

  public static void setLogEnable(boolean paramBoolean)
  {
    a = paramBoolean;
  }

  public static void v(String paramString1, String paramString2)
  {
    if (a)
      Log.v("TencentPay", paramString1 + " | " + paramString2);
  }

  public static void w(String paramString1, String paramString2)
  {
    Log.w("TencentPay", paramString1 + " | " + paramString2);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.tool.APLog
 * JD-Core Version:    0.6.0
 */