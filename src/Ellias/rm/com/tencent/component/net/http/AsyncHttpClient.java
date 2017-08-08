package com.tencent.component.net.http;

import android.content.Context;
import android.text.TextUtils;
import com.tencent.component.annotation.PluginApi;
import com.tencent.component.net.http.cookie.PersistentCookieStore;
import com.tencent.component.net.http.request.AsyncHttpGetRequest;
import com.tencent.component.net.http.request.AsyncHttpPostRequest;
import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.net.http.strategy.AsyncRetryHandler;
import com.tencent.component.net.http.strategy.StrategyInfo;
import com.tencent.component.utils.StringUtils;
import com.tencent.component.utils.clock.OnClockListener;
import com.tencent.component.utils.clock.SimpleClock;
import com.tencent.component.utils.collections.MultiHashMap;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.ThreadPool;
import com.tencent.component.utils.thread.ThreadPool.JobContext;
import com.tencent.component.utils.thread.ThreadPool.Priority;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.DefaultHttpClient;

@PluginApi(a=8)
public abstract class AsyncHttpClient
{
  private static final String a = "AsyncHttpClient";
  private static final String b = "Accept-Encoding";
  private static final String c = "gzip";
  private static AtomicInteger d = new AtomicInteger(1);
  private static final long j = 1500L;
  private static final String k = "statistics.enqueque.time";
  private static final AsyncHttpResult.NetworkUnavailableDescription n = new AsyncHttpResult.NetworkUnavailableDescription();
  private Context e;
  private ThreadPool f;
  private boolean g = true;
  private final MultiHashMap h = new MultiHashMap();
  private final ConcurrentHashMap i = new ConcurrentHashMap();
  private SimpleClock l;
  private OnClockListener m = new e(this);

  @PluginApi(a=8)
  public AsyncHttpClient(Context paramContext)
  {
    this.e = paramContext.getApplicationContext();
    this.f = supplyThreadPool();
  }

  private HttpFuture a(f paramf)
  {
    if (paramf == null)
      return null;
    Future localFuture = this.f.submit(paramf, new c(this, paramf), paramf.a());
    a(f.a(paramf).getIdentifier());
    synchronized (this.i)
    {
      this.i.put(f.a(paramf).getIdentifier(), localFuture);
      HttpFuture localHttpFuture = null;
      if (localFuture != null)
      {
        localFuture.a(new d(this, paramf));
        localHttpFuture = new HttpFuture(localFuture);
      }
      b();
      return localHttpFuture;
    }
  }

  private static AsyncHttpGetRequest a(String paramString, Map paramMap, AsyncRequestListener paramAsyncRequestListener, AsyncResponseHandler paramAsyncResponseHandler, AsyncRetryHandler paramAsyncRetryHandler)
  {
    AsyncHttpGetRequest localAsyncHttpGetRequest = new AsyncHttpGetRequest();
    localAsyncHttpGetRequest.b(paramString);
    localAsyncHttpGetRequest.a(paramMap);
    localAsyncHttpGetRequest.a(paramAsyncRequestListener);
    localAsyncHttpGetRequest.a(paramAsyncResponseHandler);
    localAsyncHttpGetRequest.a(paramAsyncRetryHandler);
    return localAsyncHttpGetRequest;
  }

  private void a(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    collectPendingRequest(paramString, false, localArrayList);
    if (localArrayList.size() == 0);
    while (true)
    {
      return;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest.getRequestListener();
        if (localAsyncRequestListener == null)
          continue;
        localAsyncRequestListener.onRequestEnqueque(localAsyncHttpRequest);
      }
    }
  }

  private void a(Collection paramCollection)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest.getRequestListener();
        if (localAsyncRequestListener == null)
          continue;
        localAsyncRequestListener.onRequestCanceled(localAsyncHttpRequest);
      }
    }
  }

  private void a(Collection paramCollection, AsyncHttpResult paramAsyncHttpResult)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest.getRequestListener();
        if (localAsyncRequestListener == null)
          continue;
        localAsyncRequestListener.onRequestFailed(localAsyncHttpRequest, paramAsyncHttpResult);
      }
    }
  }

  private boolean a(String paramString, AsyncHttpRequest paramAsyncHttpRequest)
  {
    if (paramAsyncHttpRequest == null)
      return false;
    while (true)
    {
      synchronized (this.h)
      {
        this.h.a(paramString);
        Collection localCollection = (Collection)this.h.get(paramString);
        if (localCollection == null)
          break label145;
        Iterator localIterator = localCollection.iterator();
        i1 = 0;
        if (!localIterator.hasNext())
          continue;
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          break label134;
        i3 = i1 + 1;
        break label138;
        this.h.a(paramString, paramAsyncHttpRequest);
        if (i1 == 0)
        {
          i2 = 1;
          return i2;
        }
      }
      int i2 = 0;
      continue;
      label134: int i3 = i1;
      label138: int i1 = i3;
      continue;
      label145: i1 = 0;
    }
  }

  private boolean a(String paramString, Collection paramCollection)
  {
    while (true)
    {
      int i1;
      synchronized (this.h)
      {
        i1 = this.h.a(paramString);
        if (paramCollection == null)
          continue;
        paramCollection.clear();
        Collection localCollection = (Collection)this.h.get(paramString);
        if (localCollection == null)
          break label146;
        Iterator localIterator = localCollection.iterator();
        i2 = 0;
        if (!localIterator.hasNext())
          break label149;
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        localAsyncHttpRequest.cancel();
        if (paramCollection == null)
          continue;
        paramCollection.add(localAsyncHttpRequest);
        if ((localAsyncHttpRequest != null) && (!localAsyncHttpRequest.isCanceled()))
        {
          i4 = i2 + 1;
          break label139;
          return i3;
        }
      }
      int i4 = i2;
      label139: int i2 = i4;
      continue;
      label146: i2 = 0;
      label149: if ((i1 > 0) && (i2 == 0))
      {
        i3 = 1;
        continue;
      }
      int i3 = 0;
    }
  }

  private void b()
  {
    monitorenter;
    try
    {
      if ((this.l == null) || (this.l.isCanceled()))
        this.l = SimpleClock.set(1500L, 1500L, this.m);
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(f paramf)
  {
    ArrayList localArrayList;
    if ((paramf != null) && (f.a(paramf) != null))
    {
      localArrayList = new ArrayList();
      if (!a(f.a(paramf).getIdentifier(), localArrayList));
    }
    synchronized (this.i)
    {
      this.i.remove(f.a(paramf).getIdentifier());
      a(localArrayList);
      return;
    }
  }

  private void b(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    collectPendingRequest(paramString, false, localArrayList);
    if (localArrayList.size() == 0);
    while (true)
    {
      return;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest.getRequestListener();
        if (localAsyncRequestListener == null)
          continue;
        localAsyncRequestListener.onRequestStart(localAsyncHttpRequest);
      }
    }
  }

  private void b(Collection paramCollection, AsyncHttpResult paramAsyncHttpResult)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
      {
        AsyncHttpRequest localAsyncHttpRequest = (AsyncHttpRequest)localIterator.next();
        if ((localAsyncHttpRequest == null) || (localAsyncHttpRequest.isCanceled()))
          continue;
        AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest.getRequestListener();
        if (localAsyncRequestListener == null)
          continue;
        localAsyncRequestListener.onRequestSuccess(localAsyncHttpRequest, paramAsyncHttpResult);
      }
    }
  }

  private static boolean c(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (StringUtils.F(paramString));
  }

  public void a(ThreadPool paramThreadPool)
  {
    this.f = paramThreadPool;
  }

  @PluginApi(a=8)
  protected void cancel(AsyncHttpRequest paramAsyncHttpRequest)
  {
    String str;
    ArrayList localArrayList;
    if (paramAsyncHttpRequest != null)
    {
      str = paramAsyncHttpRequest.getIdentifier();
      localArrayList = new ArrayList();
      if (!a(str, localArrayList));
    }
    synchronized (this.i)
    {
      Future localFuture = (Future)this.i.remove(str);
      if (localFuture != null)
        localFuture.cancel();
      a(localArrayList);
      return;
    }
  }

  @PluginApi(a=8)
  protected Collection collectPendingRequest(String paramString, boolean paramBoolean, Collection paramCollection)
  {
    MultiHashMap localMultiHashMap = this.h;
    monitorenter;
    if (paramBoolean);
    try
    {
      for (HashSet localHashSet = (HashSet)this.h.remove(paramString); paramCollection != null; localHashSet = (HashSet)this.h.get(paramString))
      {
        paramCollection.clear();
        if (localHashSet != null)
          paramCollection.addAll(localHashSet);
        return paramCollection;
      }
      return localHashSet;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @PluginApi(a=100)
  protected HttpFuture get(AsyncHttpGetRequest paramAsyncHttpGetRequest)
  {
    return sendRequest(paramAsyncHttpGetRequest);
  }

  @PluginApi(a=8)
  protected HttpFuture get(String paramString, AsyncRequestListener paramAsyncRequestListener, AsyncResponseHandler paramAsyncResponseHandler)
  {
    return get(paramString, null, paramAsyncRequestListener, paramAsyncResponseHandler, null);
  }

  @PluginApi(a=8)
  protected HttpFuture get(String paramString, Map paramMap, AsyncRequestListener paramAsyncRequestListener, AsyncResponseHandler paramAsyncResponseHandler, AsyncRetryHandler paramAsyncRetryHandler)
  {
    return sendRequest(a(paramString, paramMap, paramAsyncRequestListener, paramAsyncResponseHandler, paramAsyncRetryHandler));
  }

  @PluginApi(a=8)
  protected void handleReport(Context paramContext, ThreadPool.JobContext paramJobContext, StrategyInfo paramStrategyInfo, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
  }

  @PluginApi(a=8)
  protected void initHttpClient(DefaultHttpClient paramDefaultHttpClient)
  {
    paramDefaultHttpClient.setCookieStore(new PersistentCookieStore(this.e));
    paramDefaultHttpClient.addRequestInterceptor(new a(this));
    paramDefaultHttpClient.addResponseInterceptor(new b(this));
    paramDefaultHttpClient.setReuseStrategy(new CustomConnectionReuseStrategy());
  }

  @PluginApi(a=8)
  public boolean isUrlEncodingEnabled()
  {
    return this.g;
  }

  @PluginApi(a=8)
  protected abstract DefaultHttpClient obtainHttpClient();

  @PluginApi(a=8)
  protected void onHttpTaskFinish(Collection paramCollection, AsyncHttpRequest paramAsyncHttpRequest, AsyncHttpResult paramAsyncHttpResult)
  {
  }

  @PluginApi(a=8)
  protected HttpFuture post(String paramString, HttpEntity paramHttpEntity, AsyncRequestListener paramAsyncRequestListener, AsyncResponseHandler paramAsyncResponseHandler, AsyncRetryHandler paramAsyncRetryHandler)
  {
    AsyncHttpPostRequest localAsyncHttpPostRequest = new AsyncHttpPostRequest();
    localAsyncHttpPostRequest.b(paramString);
    localAsyncHttpPostRequest.a(paramAsyncRequestListener);
    localAsyncHttpPostRequest.a(paramAsyncResponseHandler);
    localAsyncHttpPostRequest.a(paramAsyncRetryHandler);
    localAsyncHttpPostRequest.a(paramHttpEntity);
    return sendRequest(localAsyncHttpPostRequest);
  }

  @PluginApi(a=8)
  protected HttpFuture sendRequest(AsyncHttpRequest paramAsyncHttpRequest)
  {
    if ((paramAsyncHttpRequest == null) || (!c(paramAsyncHttpRequest.getUrl())));
    do
    {
      return null;
      paramAsyncHttpRequest.setSeqNo(d.getAndIncrement());
    }
    while (!a(paramAsyncHttpRequest.getIdentifier(), paramAsyncHttpRequest));
    ThreadPool.Priority localPriority = paramAsyncHttpRequest.getPriority();
    if (localPriority == null)
      localPriority = ThreadPool.Priority.NORMAL;
    f localf = new f(this, this.e, paramAsyncHttpRequest, paramAsyncHttpRequest.getUrl(), localPriority);
    paramAsyncHttpRequest.putExtra("statistics.enqueque.time", Long.valueOf(System.currentTimeMillis()));
    return a(localf);
  }

  @PluginApi(a=8)
  public void setURLEncodingEnabled(boolean paramBoolean)
  {
    this.g = paramBoolean;
  }

  @PluginApi(a=8)
  protected abstract void shutDownHttpClient(DefaultHttpClient paramDefaultHttpClient);

  @PluginApi(a=8)
  protected abstract ThreadPool supplyThreadPool();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.AsyncHttpClient
 * JD-Core Version:    0.6.0
 */