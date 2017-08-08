package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class k
  implements Runnable
{
  private k(b paramb)
  {
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    long l1 = System.currentTimeMillis();
    long l2 = 9223372036854775807L;
    long l3 = com.tencent.android.tpush.service.a.a.f;
    int i = 0;
    long l4;
    ChannelException localChannelException1;
    Iterator localIterator1;
    if (l3 < 15000L)
    {
      l4 = 15000L;
      localChannelException1 = new ChannelException(10107, "TpnsMessage wait for response timeout!");
      localIterator1 = b.f(this.a).keySet().iterator();
    }
    while (true)
    {
      if (!localIterator1.hasNext())
        break label299;
      com.tencent.android.tpush.service.channel.a.a locala = (com.tencent.android.tpush.service.channel.a.a)localIterator1.next();
      Iterator localIterator3 = ((ConcurrentHashMap)b.f(this.a).get(locala)).entrySet().iterator();
      a locala1 = locala.e();
      int m = i;
      long l8 = l2;
      while (true)
      {
        if (!localIterator3.hasNext())
          break label289;
        m localm2 = (m)((Map.Entry)localIterator3.next()).getValue();
        if (localm2 != null)
        {
          long l9 = l1 - localm2.b;
          locala1.a(3, Long.valueOf(l9));
          if (l9 < 0L)
            continue;
          if (l9 > l4)
          {
            n localn2 = localm2.d;
            if (localn2 != null)
            {
              m = 1;
              localn2.a(localm2.c, localChannelException1, locala1);
              localm2.d = null;
            }
            localIterator3.remove();
            continue;
            l4 = l3;
            break;
          }
          if (l4 - l9 >= l8)
            continue;
          l8 = l4 - l9;
          continue;
        }
        localIterator3.remove();
      }
      label289: l2 = l8;
      i = m;
    }
    label299: ChannelException localChannelException2 = new ChannelException(10106, "TpnsMessage wait for response timeout!");
    Object localObject1 = null;
    Iterator localIterator2;
    long l5;
    Object localObject3;
    int j;
    long l6;
    synchronized (this.a)
    {
      localIterator2 = b.c(this.a).iterator();
      m localm1;
      do
      {
        if (!localIterator2.hasNext())
          break label546;
        localm1 = (m)localIterator2.next();
        if (localm1 == null)
          break;
        l5 = l1 - localm1.a;
      }
      while (l5 < 0L);
      if (l5 > l4)
      {
        n localn1 = localm1.d;
        if (localn1 != null)
        {
          if (localObject1 == null)
          {
            if (b.e(this.a) != null)
            {
              localObject1 = b.e(this.a).e();
              ((a)localObject1).a(3, Long.valueOf(l5));
            }
          }
          else
          {
            i = 1;
            localn1.a(localm1.c, localChannelException2, (a)localObject1);
            localm1.d = null;
          }
        }
        else
        {
          localObject3 = localObject1;
          j = i;
          localIterator2.remove();
          l6 = l2;
          break label562;
        }
        localObject1 = new a();
      }
    }
    if (l4 - l5 < l2)
    {
      long l7 = l4 - l5;
      localObject3 = localObject1;
      j = i;
      l6 = l7;
      break label562;
      localIterator2.remove();
      break label580;
      label546: monitorexit;
      if (i != 0)
        this.a.e();
      return;
    }
    while (true)
    {
      label562: int k = j;
      localObject1 = localObject3;
      l2 = l6;
      i = k;
      break;
      label580: localObject3 = localObject1;
      j = i;
      l6 = l2;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.k
 * JD-Core Version:    0.6.0
 */