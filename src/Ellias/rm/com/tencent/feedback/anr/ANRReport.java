package com.tencent.feedback.anr;

import android.content.Context;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.eup.CrashReport;
import com.tencent.feedback.eup.CrashStrategyBean;
import com.tencent.feedback.eup.d;

public class ANRReport
{
  public static void startANRMonitor(Context paramContext)
  {
    b.a(paramContext).startWatching();
  }

  public static void stopANRMonitor()
  {
    if (b.a(null) != null)
      b.a(null).stopWatching();
  }

  public static boolean uploadANRInfo(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, long paramLong)
  {
    c localc = c.p();
    if (localc == null)
    {
      e.d("commonInfo is null not init ?", new Object[0]);
      return false;
    }
    CrashStrategyBean localCrashStrategyBean = CrashReport.getCrashRuntimeStrategy();
    if (localCrashStrategyBean == null)
    {
      e.d("crash strategy null,not init?", new Object[0]);
      return false;
    }
    d locald = com.tencent.feedback.eup.b.a(paramContext, localc.g(), localc.i(), localc.n(), paramString1, "main", "", "ANR_RQD_EXCEPTION", "", paramString2, paramLong, paramString3, null);
    locald.e(true);
    boolean bool = com.tencent.feedback.eup.b.a(paramContext).a(locald, localCrashStrategyBean);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = locald.t();
    arrayOfObject1[1] = Integer.valueOf(locald.r());
    e.b("sha1:%s %d", arrayOfObject1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Boolean.valueOf(bool);
    e.b("handle anr %b", arrayOfObject2);
    return bool;
  }

  public static void uploadANRInfoAsync(Context paramContext, int paramInt, String paramString1, String paramString2, String paramString3, long paramLong)
  {
    if ((paramInt <= 0) || (paramString1 == null) || (paramLong <= 0L))
    {
      e.d("anr args unright pid, procName ,anrTime should not be null", new Object[0]);
      return;
    }
    com.tencent.feedback.common.b.b().a(new Runnable(paramContext, paramInt, paramString1, paramString2, paramString3, paramLong)
    {
      public final void run()
      {
        ANRReport.uploadANRInfo(this.a, this.b, this.c, this.d, this.e, this.f);
      }
    });
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.feedback.anr.ANRReport
 * JD-Core Version:    0.6.0
 */