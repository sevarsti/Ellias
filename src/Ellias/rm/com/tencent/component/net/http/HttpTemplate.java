package com.tencent.component.net.http;

import android.content.Context;
import com.tencent.component.net.http.request.AsyncHttpGetRequest;
import com.tencent.component.net.http.request.AsyncHttpPostRequest;
import com.tencent.component.utils.HttpUtil;
import com.tencent.component.utils.HttpUtil.ClientOptions;
import com.tencent.component.utils.thread.ThreadPool;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpTemplate extends AsyncHttpClient
{
  private static final int a = 2;
  private static final int b = 5;
  private DefaultResponseHandler c = new DefaultResponseHandler();
  private i d = new i(null);
  private ContentHandler e = new h(this);

  public HttpTemplate(Context paramContext)
  {
    super(paramContext);
    this.c.a(this.e);
  }

  public HttpFuture a(AsyncHttpGetRequest paramAsyncHttpGetRequest, AsyncRequestListener paramAsyncRequestListener)
  {
    paramAsyncHttpGetRequest.a(paramAsyncRequestListener);
    paramAsyncHttpGetRequest.a(this.c);
    paramAsyncHttpGetRequest.a(this.d);
    return get(paramAsyncHttpGetRequest);
  }

  public HttpFuture a(AsyncHttpPostRequest paramAsyncHttpPostRequest, AsyncRequestListener paramAsyncRequestListener)
  {
    paramAsyncHttpPostRequest.a(paramAsyncRequestListener);
    paramAsyncHttpPostRequest.a(this.c);
    paramAsyncHttpPostRequest.a(this.d);
    return sendRequest(paramAsyncHttpPostRequest);
  }

  public HttpFuture a(String paramString, AsyncRequestListener paramAsyncRequestListener)
  {
    return a(paramString, null, paramAsyncRequestListener);
  }

  public HttpFuture a(String paramString, Map paramMap, AsyncRequestListener paramAsyncRequestListener)
  {
    return get(paramString, paramMap, paramAsyncRequestListener, this.c, this.d);
  }

  public HttpFuture a(String paramString, HttpEntity paramHttpEntity, AsyncRequestListener paramAsyncRequestListener)
  {
    return post(paramString, paramHttpEntity, paramAsyncRequestListener, this.c, this.d);
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
    return new ThreadPool("http-protocol", 2, 5);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.HttpTemplate
 * JD-Core Version:    0.6.0
 */