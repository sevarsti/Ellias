package com.tencent.stat;

import android.content.Context;
import com.tencent.stat.a.e;
import com.tencent.stat.a.f;
import com.tencent.stat.common.StatLogger;
import com.tencent.stat.common.p;

class k
  implements Runnable
{
  private e a;
  private StatReportStrategy b = null;
  private c c = new l(this);

  public k(e parame)
  {
    this.a = parame;
    this.b = StatConfig.getStatSendStrategy();
  }

  private void a()
  {
    if (n.b().a() > 0)
    {
      n.b().a(this.a, null);
      n.b().a(-1);
      return;
    }
    a(true);
  }

  private void a(boolean paramBoolean)
  {
    d.b().a(this.a, this.c);
  }

  public void run()
  {
    try
    {
      if (!StatConfig.isEnableStatService())
        return;
      if ((this.a.a() != f.c) && (this.a.d().length() > StatConfig.getMaxReportEventLength()))
      {
        StatService.b().e("Event length exceed StatConfig.getMaxReportEventLength(): " + StatConfig.getMaxReportEventLength());
        return;
      }
    }
    catch (Throwable localThrowable)
    {
      StatService.b().e(localThrowable);
      return;
    }
    if (StatConfig.getMaxSessionStatReportCount() > 0)
    {
      if (StatConfig.getCurSessionStatReportCount() >= StatConfig.getMaxSessionStatReportCount())
      {
        StatService.b().e("Times for reporting events has reached the limit of StatConfig.getMaxSessionStatReportCount() in current session.");
        return;
      }
      StatConfig.c();
    }
    StatService.b().i("Lauch stat task in thread:" + Thread.currentThread().getName());
    Context localContext = this.a.c();
    if (!com.tencent.stat.common.k.h(localContext))
    {
      n.a(localContext).a(this.a, null);
      return;
    }
    if ((StatConfig.isEnableSmartReporting()) && (this.b != StatReportStrategy.ONLY_WIFI_NO_CACHE) && (com.tencent.stat.common.k.g(localContext)))
      this.b = StatReportStrategy.INSTANT;
    switch (h.a[this.b.ordinal()])
    {
    default:
      StatService.b().error("Invalid stat strategy:" + StatConfig.getStatSendStrategy());
      return;
    case 1:
      a();
      return;
    case 2:
      if (com.tencent.stat.common.k.e(localContext))
      {
        a();
        return;
      }
      n.a(localContext).a(this.a, null);
      return;
    case 3:
    case 4:
      n.a(localContext).a(this.a, null);
      return;
    case 5:
      if (n.a(this.a.c()) == null)
        break;
      n.a(localContext).a(this.a, new m(this));
      return;
    case 6:
      try
      {
        n.a(localContext).a(this.a, null);
        Long localLong1 = Long.valueOf(p.a(localContext, "last_period_ts", 0L));
        Long localLong2 = Long.valueOf(System.currentTimeMillis());
        if (Long.valueOf(Long.valueOf(localLong2.longValue() - localLong1.longValue()).longValue() / 60000L).longValue() <= StatConfig.getSendPeriodMinutes())
          break;
        n.a(localContext).a(-1);
        p.b(localContext, "last_period_ts", localLong2.longValue());
        return;
      }
      catch (Exception localException)
      {
        StatService.b().e(localException);
        return;
      }
    case 7:
      if (!com.tencent.stat.common.k.e(localContext))
        break;
      a(false);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.stat.k
 * JD-Core Version:    0.6.0
 */