package com.tencent.component.net.http;

import com.tencent.component.net.http.request.AsyncHttpRequest;
import com.tencent.component.utils.clock.Clock;
import com.tencent.component.utils.clock.OnClockListener;
import com.tencent.component.utils.collections.MultiHashMap;
import com.tencent.component.utils.thread.Future;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class e
  implements OnClockListener
{
  e(AsyncHttpClient paramAsyncHttpClient)
  {
  }

  public boolean onClockArrived(Clock paramClock)
  {
    MultiHashMap localMultiHashMap1 = new MultiHashMap();
    while (true)
    {
      long l2;
      synchronized (AsyncHttpClient.b(this.a))
      {
        Iterator localIterator1 = AsyncHttpClient.b(this.a).entrySet().iterator();
        if (localIterator1.hasNext())
        {
          HashSet localHashSet2 = (HashSet)((Map.Entry)localIterator1.next()).getValue();
          if ((localHashSet2 == null) || (localHashSet2.size() <= 0))
            continue;
          AsyncHttpRequest localAsyncHttpRequest2 = null;
          Iterator localIterator4 = localHashSet2.iterator();
          l1 = -1L;
          if (!localIterator4.hasNext())
            continue;
          localAsyncHttpRequest2 = (AsyncHttpRequest)localIterator4.next();
          if (localAsyncHttpRequest2 == null)
            continue;
          Long localLong = (Long)localAsyncHttpRequest2.getExtra("statistics.enqueque.time");
          if (localLong == null)
            continue;
          l2 = localLong.longValue();
          if (l2 <= 0L)
            continue;
          if (l1 == -1L)
            break label411;
          l2 = Math.min(l1, l2);
          break label411;
          if (System.currentTimeMillis() - l1 < localAsyncHttpRequest2.getTimeout())
            continue;
          localMultiHashMap1.put(localAsyncHttpRequest2.getIdentifier(), localHashSet2);
        }
      }
      monitorexit;
      if (localMultiHashMap1.size() > 0)
      {
        Iterator localIterator2 = localMultiHashMap1.entrySet().iterator();
        while (localIterator2.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator2.next();
          String str = (String)localEntry.getKey();
          Future localFuture = (Future)AsyncHttpClient.a(this.a).remove(str);
          if ((localFuture == null) || (localFuture.isCancelled()) || (localFuture.isDone()))
            continue;
          HashSet localHashSet1 = (HashSet)localEntry.getValue();
          if ((localHashSet1 == null) || (localHashSet1.size() <= 0))
            continue;
          Iterator localIterator3 = localHashSet1.iterator();
          while (localIterator3.hasNext())
          {
            AsyncHttpRequest localAsyncHttpRequest1 = (AsyncHttpRequest)localIterator3.next();
            AsyncRequestListener localAsyncRequestListener = localAsyncHttpRequest1.getRequestListener();
            if (localAsyncRequestListener == null)
              continue;
            localAsyncRequestListener.onRequestTimeout(localAsyncHttpRequest1);
          }
        }
      }
      return AsyncHttpClient.b(this.a).size() > 0;
      label411: long l1 = l2;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.http.e
 * JD-Core Version:    0.6.0
 */