package com.tencent.component.net.http;

import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.thread.Future;
import com.tencent.component.utils.thread.FutureListener;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

class c
  implements FutureListener
{
  c(AsyncHttpClient paramAsyncHttpClient, f paramf)
  {
  }

  public void onFutureBegin(Future paramFuture)
  {
    String str = f.a(this.a).getIdentifier();
    AsyncHttpClient.a(this.b, str);
  }

  public void onFutureDone(Future paramFuture)
  {
    String str = f.a(this.a).getIdentifier();
    AsyncHttpResult localAsyncHttpResult;
    do
      synchronized (AsyncHttpClient.a(this.b))
      {
        AsyncHttpClient.a(this.b).remove(str);
        localAsyncHttpResult = (AsyncHttpResult)paramFuture.get();
        if ((localAsyncHttpResult != null) && (localAsyncHttpResult.getStatus().c()))
          return;
      }
    while (paramFuture.isCancelled());
    ArrayList localArrayList = new ArrayList();
    this.b.collectPendingRequest(str, true, localArrayList);
    if ((localAsyncHttpResult == null) || (!localAsyncHttpResult.getStatus().isSucceed()))
      AsyncHttpClient.a(this.b, localArrayList, localAsyncHttpResult);
    while (true)
    {
      this.b.onHttpTaskFinish(localArrayList, f.a(this.a), localAsyncHttpResult);
      return;
      AsyncHttpClient.b(this.b, localArrayList, localAsyncHttpResult);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.c
 * JD-Core Version:    0.6.0
 */