package com.tencent.android.tpush.service.channel;

import android.os.Handler;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class i
  implements Runnable
{
  private com.tencent.android.tpush.service.channel.a.a b = null;
  private ChannelException c = null;

  public i(b paramb, com.tencent.android.tpush.service.channel.a.a parama, ChannelException paramChannelException)
  {
    this.b = parama;
    this.c = paramChannelException;
  }

  public void run()
  {
    TLog.v("XGService", "@@ run()");
    long l = System.currentTimeMillis();
    a locala = this.b.e();
    ConcurrentHashMap localConcurrentHashMap = (ConcurrentHashMap)b.f(this.a).get(this.b);
    if (localConcurrentHashMap != null)
    {
      Iterator localIterator2 = localConcurrentHashMap.values().iterator();
      while (localIterator2.hasNext())
      {
        m localm2 = (m)localIterator2.next();
        n localn2 = localm2.d;
        if (localn2 == null)
          continue;
        locala.a(3, Long.valueOf(l - localm2.b));
        k localk2 = (k)b.h(this.a).remove(localm2);
        b.i(this.a).removeCallbacks(localk2);
        localn2.a(localm2.c, this.c, locala);
      }
      localConcurrentHashMap.clear();
    }
    if ((b.e(this.a) != null) && (!b.e(this.a).d()))
    {
      TLog.i("XGService", ">> tcp");
      synchronized (this.a)
      {
        Iterator localIterator1 = b.c(this.a).iterator();
        while (localIterator1.hasNext())
        {
          m localm1 = (m)localIterator1.next();
          n localn1 = localm1.d;
          if (localn1 == null)
            continue;
          locala.a(3, Long.valueOf(l - localm1.b));
          k localk1 = (k)b.h(this.a).get(localm1);
          b.i(this.a).removeCallbacks(localk1);
          localn1.a(localm1.c, this.c, locala);
        }
      }
      b.c(this.a).clear();
      monitorexit;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.i
 * JD-Core Version:    0.6.0
 */