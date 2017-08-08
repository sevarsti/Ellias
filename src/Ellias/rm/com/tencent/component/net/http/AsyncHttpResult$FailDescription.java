package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=8)
public abstract class AsyncHttpResult$FailDescription
{

  @PluginApi(a=8)
  public static final int REASON_FAIL_CONTENT = 5;

  @PluginApi(a=8)
  public static final int REASON_FAIL_EXCEPTION = 4;

  @PluginApi(a=8)
  public static final int REASON_FAIL_NETWORK = 3;

  @PluginApi(a=8)
  public static final int REASON_FAIL_NETWORK_UNAVAILABLE = 6;

  @PluginApi(a=8)
  public static final int REASON_FAIL_NONE = 0;

  @PluginApi(a=8)
  public static final int REASON_FAIL_STORAGE = 2;

  @PluginApi(a=8)
  public Object extraInfo;

  @PluginApi(a=8)
  public int failType;

  public AsyncHttpResult$FailDescription(int paramInt, Object paramObject)
  {
    this.failType = paramInt;
    this.extraInfo = paramObject;
  }

  @PluginApi(a=8)
  public abstract String description();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpResult.FailDescription
 * JD-Core Version:    0.6.0
 */