package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.request.AsyncHttpRequest;

@PluginApi(a=8)
public abstract interface AsyncRequestListener
{
  @PluginApi(a=8)
  public abstract void onRequestCanceled(AsyncHttpRequest paramAsyncHttpRequest);

  @PluginApi(a=8)
  public abstract void onRequestEnqueque(AsyncHttpRequest paramAsyncHttpRequest);

  @PluginApi(a=8)
  public abstract void onRequestFailed(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult);

  @PluginApi(a=8)
  public abstract void onRequestStart(AsyncHttpRequest paramAsyncHttpRequest);

  @PluginApi(a=8)
  public abstract void onRequestSuccess(AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult);

  @PluginApi(a=8)
  public abstract void onRequestTimeout(AsyncHttpRequest paramAsyncHttpRequest);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncRequestListener
 * JD-Core Version:    0.6.0
 */