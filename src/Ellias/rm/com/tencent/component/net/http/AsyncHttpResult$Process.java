package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public final class AsyncHttpResult$Process
{

  @PluginApi(a=8)
  public long duration;

  @PluginApi(a=8)
  public long endTime;

  @PluginApi(a=8)
  public long startTime;

  @PluginApi(a=8)
  public void setDuration(long paramLong1, long paramLong2)
  {
    this.startTime = paramLong1;
    this.endTime = paramLong2;
    this.duration = (paramLong2 - paramLong1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.Process
 * JD-Core Version:    0.6.0
 */