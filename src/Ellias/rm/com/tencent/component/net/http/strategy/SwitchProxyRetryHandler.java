package com.tencent.component.net.http.strategy;

import com.tencent.component.net.http.AsyncHttpResult.Status;
import com.tencent.component.net.http.download.ProxyStatistics;
import org.apache.http.protocol.HttpContext;

public class SwitchProxyRetryHandler
  implements AsyncRetryHandler
{
  private static final int a = 4;
  private StrategyInfo b;
  private StrategyInfo c;

  private void a()
  {
    this.b = new StrategyInfo(ProxyStatistics.d().b(), ProxyStatistics.d().c());
  }

  private void b()
  {
    if (this.b == null)
      a();
    boolean bool1 = this.b.allowProxy;
    boolean bool2 = this.b.useConfigApn;
    boolean bool3;
    if (this.b.allowProxy)
    {
      boolean bool4 = this.b.useConfigApn;
      bool3 = false;
      if (bool4);
    }
    while (true)
    {
      this.b = this.c;
      this.c = new StrategyInfo(bool3, bool2);
      return;
      bool3 = bool1;
      bool2 = false;
      continue;
      bool2 = true;
      bool3 = true;
    }
  }

  public StrategyInfo getStrategyInfo(int paramInt, HttpContext paramHttpContext)
  {
    if (paramInt == 0)
    {
      a();
      this.c = this.b;
    }
    while (true)
    {
      return this.c;
      b();
    }
  }

  public boolean isNeedRetry(AsyncHttpResult.Status paramStatus, int paramInt, HttpContext paramHttpContext)
  {
    return paramInt < 4;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.strategy.SwitchProxyRetryHandler
 * JD-Core Version:    0.6.0
 */