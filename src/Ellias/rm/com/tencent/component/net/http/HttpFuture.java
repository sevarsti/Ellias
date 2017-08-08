package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.utils.thread.Future;

@PluginApi(a=8)
public class HttpFuture
{

  @PluginApi(a=8)
  private Future a;

  @PluginApi(a=8)
  public HttpFuture(Future paramFuture)
  {
    this.a = paramFuture;
  }

  @PluginApi(a=8)
  public void cancel()
  {
    this.a.cancel();
  }

  @PluginApi(a=8)
  public AsyncHttpResult get()
  {
    return (AsyncHttpResult)this.a.get();
  }

  @PluginApi(a=8)
  public boolean isCancelled()
  {
    return this.a.isCancelled();
  }

  @PluginApi(a=8)
  public boolean isDone()
  {
    return this.a.isDone();
  }

  @PluginApi(a=8)
  public void waitDone()
  {
    this.a.waitDone();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.HttpFuture
 * JD-Core Version:    0.6.0
 */