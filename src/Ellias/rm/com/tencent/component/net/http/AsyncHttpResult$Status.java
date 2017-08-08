package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public final class AsyncHttpResult$Status
{
  private static final int b = 1;
  private static final int c = 2;
  private static final int d = 3;
  public int a;
  private int e = 2;
  private AsyncHttpResult.FailDescription f;

  final void a()
  {
    this.e = 1;
  }

  public final void a(AsyncHttpResult.FailDescription paramFailDescription)
  {
    this.e = 2;
    this.f = paramFailDescription;
  }

  final void b()
  {
    this.e = 3;
  }

  final boolean c()
  {
    return this.e == 3;
  }

  @PluginApi(a=8)
  public Throwable getFailException()
  {
    AsyncHttpResult.FailDescription localFailDescription = getFailReason();
    if ((localFailDescription != null) && (localFailDescription.failType == 4))
      return (Throwable)localFailDescription.extraInfo;
    return null;
  }

  @PluginApi(a=8)
  public AsyncHttpResult.FailDescription getFailReason()
  {
    if (isFailed())
      return this.f;
    return null;
  }

  @PluginApi(a=8)
  public boolean isFailed()
  {
    return this.e == 2;
  }

  @PluginApi(a=8)
  public boolean isSucceed()
  {
    return this.e == 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.Status
 * JD-Core Version:    0.6.0
 */