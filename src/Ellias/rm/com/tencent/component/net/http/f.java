package com.tencent.component.net.http;

import android.content.Context;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.net.http.strategy.StrategyInfo;
import com.tencent.component.utils.HttpUtil.RequestOptions;
import com.tencent.component.utils.NetworkUtil;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool.Job;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

class f
  implements ThreadPool.Job
{
  private final DefaultHttpClient b;
  private final AsyncHttpRequest c;
  private HttpContext d;
  private int e;
  private final String f;
  private final Context g;
  private final HttpUtil.RequestOptions h;
  private final ThreadPool.Priority i;

  public f(AsyncHttpClient paramAsyncHttpClient, Context paramContext, AsyncHttpRequest paramAsyncHttpRequest, String paramString, ThreadPool.Priority paramPriority)
  {
    this.b = paramAsyncHttpClient.obtainHttpClient();
    this.c = paramAsyncHttpRequest;
    this.d = new BasicHttpContext();
    this.f = paramString;
    this.g = paramContext;
    this.h = new HttpUtil.RequestOptions();
    if (paramPriority == null)
      paramPriority = ThreadPool.Priority.NORMAL;
    this.i = paramPriority;
    paramAsyncHttpClient.initHttpClient(this.b);
  }

  public AsyncHttpResult a(ThreadPool.JobContext paramJobContext)
  {
    AsyncResponseHandler localAsyncResponseHandler = this.c.getResponseHandler();
    AsyncHttpResult localAsyncHttpResult = localAsyncResponseHandler.generateAsyncHttpResult(this.f);
    if (paramJobContext.isCancelled());
    HttpUriRequest localHttpUriRequest;
    StrategyInfo localStrategyInfo1;
    while (true)
    {
      return localAsyncHttpResult;
      paramJobContext.setMode(2);
      localHttpUriRequest = null;
      localStrategyInfo1 = null;
      bool = false;
      try
      {
        if (paramJobContext.isCancelled())
          continue;
        if (NetworkUtil.a(this.g))
          break label223;
        localAsyncHttpResult.getStatus().a(AsyncHttpClient.a());
        return localAsyncHttpResult;
      }
      catch (Throwable localThrowable)
      {
        LogUtil.e("AsyncHttpClient", localThrowable.getMessage(), localThrowable);
        localAsyncHttpResult.getStatus().a(new AsyncHttpResult.NetworkExceptionDescription(localThrowable));
      }
    }
    label110: AsyncRetryHandler localAsyncRetryHandler1;
    if (!localAsyncHttpResult.getStatus().isSucceed())
    {
      localAsyncRetryHandler1 = this.c.getRetryHandler();
      if (localAsyncRetryHandler1 == null)
        break label445;
    }
    label425: label445: for (boolean bool = localAsyncRetryHandler1.isNeedRetry(localAsyncHttpResult.getStatus(), this.e, this.d); ; bool = false)
    {
      this.e = (1 + this.e);
      if (localHttpUriRequest != null)
      {
        localHttpUriRequest.abort();
        localHttpUriRequest = null;
      }
      if (bool)
        break;
      StrategyInfo localStrategyInfo2 = localStrategyInfo1;
      while (true)
      {
        this.a.shutDownHttpClient(this.b);
        this.a.handleReport(this.g, paramJobContext, localStrategyInfo2, this.c, localAsyncHttpResult);
        return localAsyncHttpResult;
        label223: AsyncRetryHandler localAsyncRetryHandler2 = this.c.getRetryHandler();
        if (localAsyncRetryHandler2 != null)
          localStrategyInfo1 = localAsyncRetryHandler2.getStrategyInfo(this.e, this.d);
        if (localStrategyInfo1 != null)
        {
          this.h.allowProxy = localStrategyInfo1.allowProxy;
          this.h.apnProxy = localStrategyInfo1.useConfigApn;
        }
        localHttpUriRequest = this.c.generateHttpRequest(this.g, this.h);
        HttpResponse localHttpResponse = this.b.execute(localHttpUriRequest, this.d);
        if (localHttpResponse == null)
          break label425;
        int j = localHttpResponse.getStatusLine().getStatusCode();
        localAsyncHttpResult.getStatus().a = j;
        if ((200 == j) || (206 == j))
        {
          if (!localAsyncResponseHandler.onResponseReceived(localHttpResponse, localAsyncHttpResult, this.c, paramJobContext))
            break;
          localAsyncHttpResult.getStatus().a();
          localStrategyInfo2 = localStrategyInfo1;
          continue;
        }
        localAsyncHttpResult.getStatus().a(new AsyncHttpResult.NetworkFailDescription(j));
        if (404 != j)
          break;
        localStrategyInfo2 = localStrategyInfo1;
      }
      localAsyncHttpResult.getStatus().a(new AsyncHttpResult.NetworkFailDescription(-10900));
      break label110;
    }
  }

  public ThreadPool.Priority a()
  {
    return this.i;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.f
 * JD-Core Version:    0.6.0
 */