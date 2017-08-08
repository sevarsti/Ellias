package com.tencent.component.net.http.request;

import android.content.Context;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.AsyncRequestListener;
import com.tencent.component.net.http.AsyncResponseHandler;
import com.tencent.component.net.http.FluentCaseInsensitiveStringsMap;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.utils.HttpUtil.RequestOptions;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import org.apache.http.client.methods.HttpUriRequest;

@PluginApi(a=8)
public abstract interface AsyncHttpRequest
{
  @PluginApi(a=8)
  public abstract void cancel();

  @PluginApi(a=8)
  public abstract HttpUriRequest generateHttpRequest(Context paramContext, HttpUtil.RequestOptions paramRequestOptions);

  @PluginApi(a=8)
  public abstract Object getExtra(String paramString);

  @PluginApi(a=8)
  public abstract FluentCaseInsensitiveStringsMap getHeaders();

  @PluginApi(a=8)
  public abstract String getIdentifier();

  @PluginApi(a=8)
  public abstract ThreadPool.Priority getPriority();

  @PluginApi(a=8)
  public abstract AsyncRequestListener getRequestListener();

  @PluginApi(a=8)
  public abstract AsyncResponseHandler getResponseHandler();

  @PluginApi(a=8)
  public abstract AsyncRetryHandler getRetryHandler();

  @PluginApi(a=8)
  public abstract int getSeqNo();

  @PluginApi(a=8)
  public abstract long getTimeout();

  @PluginApi(a=8)
  public abstract String getUrl();

  @PluginApi(a=8)
  public abstract boolean isCanceled();

  @PluginApi(a=8)
  public abstract void putExtra(String paramString, Object paramObject);

  @PluginApi(a=8)
  public abstract void setSeqNo(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.request.AsyncHttpRequest
 * JD-Core Version:    0.6.0
 */