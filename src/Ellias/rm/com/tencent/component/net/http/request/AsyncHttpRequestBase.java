package com.tencent.component.net.http.request;

import com.tencent.component.net.http.AsyncRequestListener;
import com.tencent.component.net.http.AsyncResponseHandler;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AsyncHttpRequestBase
  implements AsyncHttpRequest
{
  private static final int a = 30000;
  private String b;
  private boolean c;
  private AsyncRequestListener d;
  private AsyncResponseHandler e;
  private AsyncRetryHandler f;
  private ThreadPool.Priority g = ThreadPool.Priority.NORMAL;
  private long h;
  private ConcurrentHashMap i = new ConcurrentHashMap();
  private int j;

  public void a(long paramLong)
  {
    this.h = paramLong;
  }

  public void a(AsyncRequestListener paramAsyncRequestListener)
  {
    this.d = paramAsyncRequestListener;
  }

  public void a(AsyncResponseHandler paramAsyncResponseHandler)
  {
    this.e = paramAsyncResponseHandler;
  }

  public void a(AsyncRetryHandler paramAsyncRetryHandler)
  {
    this.f = paramAsyncRetryHandler;
  }

  public void a(ThreadPool.Priority paramPriority)
  {
    this.g = paramPriority;
  }

  public void b(String paramString)
  {
    this.b = paramString;
  }

  public void cancel()
  {
    this.c = true;
  }

  public Object getExtra(String paramString)
  {
    return this.i.get(paramString);
  }

  public ThreadPool.Priority getPriority()
  {
    return this.g;
  }

  public AsyncRequestListener getRequestListener()
  {
    return this.d;
  }

  public AsyncResponseHandler getResponseHandler()
  {
    return this.e;
  }

  public AsyncRetryHandler getRetryHandler()
  {
    return this.f;
  }

  public int getSeqNo()
  {
    return this.j;
  }

  public long getTimeout()
  {
    if (this.h <= 0L)
      return 30000L;
    return this.h;
  }

  public String getUrl()
  {
    return this.b;
  }

  public boolean isCanceled()
  {
    return this.c;
  }

  public void putExtra(String paramString, Object paramObject)
  {
    this.i.put(paramString, paramObject);
  }

  public void setSeqNo(int paramInt)
  {
    this.j = paramInt;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.request.AsyncHttpRequestBase
 * JD-Core Version:    0.6.0
 */