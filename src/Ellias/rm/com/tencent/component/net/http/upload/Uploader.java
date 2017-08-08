package com.tencent.component.net.http.upload;

import android.content.Context;
import com.tencent.component.ComponentContext;
import com.tencent.component.net.http.AsyncHttpClient;
import com.tencent.component.net.http.AsyncHttpResult;
import com.tencent.component.net.http.AsyncHttpResult.Status;
import com.tencent.component.net.http.AsyncRequestListener;
import com.tencent.component.net.http.AsyncResponseHandler;
import com.tencent.component.net.http.DefaultResponseHandler;
import com.tencent.component.net.http.download.ProxyStatistics;
import com.tencent.component.net.http.request.AsyncHttpPostRequest;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.net.http.strategy.StrategyInfo;
import com.tencent.component.net.http.strategy.SwitchProxyRetryHandler;
import com.tencent.component.utils.HttpUtil;
import com.tencent.component.utils.HttpUtil.ClientOptions;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class Uploader extends AsyncHttpClient
{
  private static final int a = 1;
  private static final int b = 3;
  private static final long c = 50000L;
  private static volatile Uploader d;
  private AsyncRetryHandler e = new SwitchProxyRetryHandler();
  private AsyncResponseHandler f = new DefaultResponseHandler();

  private Uploader()
  {
    super(ComponentContext.a());
  }

  public static Uploader b()
  {
    if (d == null)
      monitorenter;
    try
    {
      if (d == null)
        d = new Uploader();
      return d;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(String paramString, long paramLong, byte[] paramArrayOfByte, AsyncRequestListener paramAsyncRequestListener)
  {
    AsyncHttpPostRequest localAsyncHttpPostRequest = new AsyncHttpPostRequest();
    localAsyncHttpPostRequest.b(paramString);
    localAsyncHttpPostRequest.a(paramAsyncRequestListener);
    localAsyncHttpPostRequest.a(this.f);
    localAsyncHttpPostRequest.a(this.e);
    localAsyncHttpPostRequest.a(new ByteArrayEntity(paramArrayOfByte));
    localAsyncHttpPostRequest.a(paramLong);
    sendRequest(localAsyncHttpPostRequest);
  }

  public void a(String paramString, byte[] paramArrayOfByte, AsyncRequestListener paramAsyncRequestListener)
  {
    a(paramString, 50000L, paramArrayOfByte, paramAsyncRequestListener);
  }

  protected void handleReport(Context paramContext, ThreadPool.JobContext paramJobContext, StrategyInfo paramStrategyInfo, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
    if ((paramStrategyInfo != null) && (paramAsyncHttpResult.getStatus().isSucceed()))
      ProxyStatistics.d().a(paramContext, paramStrategyInfo.allowProxy, paramStrategyInfo.useConfigApn);
  }

  protected DefaultHttpClient obtainHttpClient()
  {
    HttpUtil.ClientOptions localClientOptions = new HttpUtil.ClientOptions();
    localClientOptions.a = false;
    return HttpUtil.a(localClientOptions);
  }

  protected void shutDownHttpClient(DefaultHttpClient paramDefaultHttpClient)
  {
    if (paramDefaultHttpClient != null)
      paramDefaultHttpClient.getConnectionManager().shutdown();
  }

  protected ThreadPool supplyThreadPool()
  {
    return new ThreadPool("upload", 1, 3);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.upload.Uploader
 * JD-Core Version:    0.6.0
 */