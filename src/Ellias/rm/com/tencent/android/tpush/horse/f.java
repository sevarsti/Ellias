package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.a.a;

public class f
{
  public static long a()
  {
    return a.q;
  }

  public static boolean a(long paramLong)
  {
    if (paramLong == 0L);
    long l;
    do
    {
      return true;
      l = a();
      TLog.v("XGService", "@@ isStrategyExpired,timeStamp:" + paramLong + ",expiredPeriod:" + l);
    }
    while (paramLong + 60L * (l * 1000L) <= System.currentTimeMillis());
    TLog.e("XGService", ">>not expiredPeriod ");
    return false;
  }

  public static int b()
  {
    return a.o;
  }

  public static int c()
  {
    return a.p;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.f
 * JD-Core Version:    0.6.0
 */