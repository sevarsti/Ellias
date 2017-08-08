package com.tencent.android.tpush.logging.c;

import android.text.format.Time;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class d
{
  public static String a(long paramLong)
  {
    Time localTime = new Time();
    localTime.set(paramLong);
    return localTime.format("%Y%m%d");
  }

  public static SimpleDateFormat a(String paramString)
  {
    return new SimpleDateFormat(paramString, Locale.US);
  }

  public static boolean b(String paramString)
  {
    return (paramString == null) || (paramString.length() < 1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.logging.c.d
 * JD-Core Version:    0.6.0
 */