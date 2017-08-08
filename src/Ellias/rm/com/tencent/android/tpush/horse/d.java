package com.tencent.android.tpush.horse;

import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

class d
  implements r
{
  d(c paramc)
  {
  }

  public void a(StrategyItem paramStrategyItem)
  {
    TLog.v("XGHorse", "@@ onSuccess(" + c.a(this.a) + "," + a.b(this.a.a) + ")");
    synchronized (a.i())
    {
      a.a(this.a.a).clear();
      if ((a.b(this.a.a)) && (!paramStrategyItem.j()))
      {
        TLog.i("XGHorse", ">> hasSuccessCallback && !strategyItem.isRedirected()");
        return;
      }
      a.a(this.a.a, true);
      if ((paramStrategyItem.d() == 0) && (paramStrategyItem.f() == 1))
      {
        TLog.i("XGHorse", ">> tcp && redirect");
        if (paramStrategyItem.d() == 0)
        {
          TLog.i("XGHorse", ">> tcp ");
          this.a.a.f();
          this.a.a.g();
        }
        CacheManager.addOptStrategy(paramStrategyItem);
        this.a.a.b();
        if (a.c(this.a.a) != null)
        {
          a.c(this.a.a).a(this.a.a().a(), paramStrategyItem);
          return;
        }
      }
      else
      {
        TLog.i("XGHorse", ">> !(tcp && redirect)");
        this.a.a.a(c.a(this.a));
      }
    }
    TLog.i("XGHorse", ">> mchannelcallback:" + a.c(this.a.a));
  }

  public void a(StrategyItem paramStrategyItem1, StrategyItem paramStrategyItem2)
  {
    TLog.v("XGHorse", "@@ onRedirect(org:" + paramStrategyItem1 + ",redirect:" + paramStrategyItem2 + ")");
    synchronized (a.i())
    {
      a.a(this.a.a).clear();
      if ((a.b(this.a.a)) && (!paramStrategyItem1.j()))
      {
        TLog.i("XGHorse", ">> hasSuccessCallback && !strategyItem.isRedirected()");
        return;
      }
      a.a(this.a.a, true);
      this.a.a.a(c.a(this.a));
      if (paramStrategyItem1.d() == 0)
      {
        TLog.i("XGHorse", ">> tcp ");
        this.a.a.f();
        this.a.a.g();
      }
      CacheManager.addOptStrategy(paramStrategyItem1);
      if (paramStrategyItem1.equals(paramStrategyItem2))
      {
        this.a.a.b();
        if (a.c(this.a.a) == null)
          return;
        a.c(this.a.a).a(this.a.a().a(), paramStrategyItem1);
        return;
      }
    }
    if (paramStrategyItem1.f() == 0)
    {
      this.a.a.b();
      if (paramStrategyItem2.g())
      {
        TLog.i("XGHorse", ">> FormatOk:" + paramStrategyItem1.toString());
        a.a(this.a.a).add(paramStrategyItem2);
      }
      a.c(this.a.a).a(this.a.a().a(), paramStrategyItem1);
      return;
    }
    this.a.a.b();
    a.c(this.a.a).a(this.a.a().a(), paramStrategyItem1);
  }

  public void b(StrategyItem paramStrategyItem)
  {
    TLog.v("XGHorse", "@@ onFail(" + paramStrategyItem + ")");
    if (paramStrategyItem.f() == 1)
    {
      TLog.i("XGHorse", ">> redirect remained:" + a.d(this.a.a).get() + "," + paramStrategyItem);
      if (!a.b(this.a.a))
      {
        a.d(this.a.a).decrementAndGet();
        TLog.i("XGHorse", ">> fail remained:" + a.d(this.a.a).get() + "," + paramStrategyItem);
        if ((a.c(this.a.a) != null) && (!this.a.a.c()))
          a.c(this.a.a).a(paramStrategyItem);
      }
    }
    do
    {
      return;
      TLog.i("XGHorse", ">> remained:" + a.d(this.a.a).get() + "," + paramStrategyItem);
      a.d(this.a.a).decrementAndGet();
    }
    while ((a.c(this.a.a) == null) || (this.a.a.c()));
    a.c(this.a.a).a(paramStrategyItem);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.horse.d
 * JD-Core Version:    0.6.0
 */