package com.tencent.component.utils.log;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import com.tencent.component.ComponentContext;
import com.tencent.component.cache.sp.PreferenceUtil;

public class LogConfig
{
  public static final boolean a = true;
  public static final boolean b = true;
  public static final boolean c = false;
  public static final boolean d = true;
  public static final boolean e = false;
  public static final boolean f = false;
  public static final long g = 8388608L;
  public static final int h = 262144;
  public static final int i = 8192;
  public static final int j = 10000;
  public static final String k = "debug.file.blockcount";
  public static final String l = "debug.file.keepperiod";
  public static final String m = "debug.file.tracelevel";
  private static int n = 24;
  private static int o = 63;
  private static long p = 604800000L;
  private static volatile LogConfig r;
  private SharedPreferences q = PreferenceUtil.a(ComponentContext.a(), "app_log_config");

  public static LogConfig a()
  {
    if (r == null)
      monitorenter;
    try
    {
      if (r == null)
        r = new LogConfig();
      return r;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(int paramInt)
  {
    if ((paramInt > 63) || (paramInt < 0))
      paramInt = o;
    this.q.edit().putInt("debug.file.tracelevel", paramInt).commit();
  }

  public void a(long paramLong)
  {
    int i1 = (int)(paramLong / 262144L);
    if (i1 < 1)
      i1 = n;
    this.q.edit().putInt("debug.file.blockcount", i1).commit();
  }

  public void a(SharedPreferences.OnSharedPreferenceChangeListener paramOnSharedPreferenceChangeListener)
  {
    this.q.registerOnSharedPreferenceChangeListener(paramOnSharedPreferenceChangeListener);
  }

  public int b()
  {
    return this.q.getInt("debug.file.blockcount", n);
  }

  public void b(long paramLong)
  {
    if (paramLong < 86400000L)
      paramLong = p;
    this.q.edit().putLong("debug.file.keepperiod", paramLong).commit();
  }

  public long c()
  {
    return this.q.getLong("debug.file.keepperiod", p);
  }

  public int d()
  {
    return this.q.getInt("debug.file.tracelevel", o);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.log.LogConfig
 * JD-Core Version:    0.6.0
 */