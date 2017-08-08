package com.tencent.component.net.http;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import org.apache.http.HttpResponse;

@PluginApi(a=8)
public abstract interface AsyncResponseHandler
{
  @PluginApi(a=8)
  public abstract AsyncHttpResult generateAsyncHttpResult(String paramString);

  @PluginApi(a=8)
  public abstract boolean onResponseReceived(HttpResponse paramHttpResponse, AsyncHttpResult paramAsyncHttpResult, AsyncHttpRequest paramAsyncHttpRequest, ThreadPool.JobContext paramJobContext);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncResponseHandler
 * JD-Core Version:    0.6.0
 */