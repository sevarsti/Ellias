package com.tencent.component.net.http.strategy;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.AsyncHttpResult.Status;
import org.apache.http.protocol.HttpContext;

@PluginApi(a=8)
public abstract interface AsyncRetryHandler
{
  @PluginApi(a=8)
  public abstract StrategyInfo getStrategyInfo(int paramInt, HttpContext paramHttpContext);

  @PluginApi(a=8)
  public abstract boolean isNeedRetry(AsyncHttpResult.Status paramStatus, int paramInt, HttpContext paramHttpContext);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.strategy.AsyncRetryHandler
 * JD-Core Version:    0.6.0
 */