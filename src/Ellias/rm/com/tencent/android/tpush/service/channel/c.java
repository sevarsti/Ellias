package com.tencent.android.tpush.service.channel;

import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.horse.data.StrategyItem;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.a.d;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.i;
import com.tencent.android.tpush.service.l;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class c
  implements com.tencent.android.tpush.horse.n
{
  c(b paramb)
  {
  }

  public void a(int paramInt, String paramString)
  {
    TLog.v("XGService", "@@ onFailure(" + paramInt + "," + paramString + ")");
    while (true)
    {
      m localm;
      synchronized (this.a)
      {
        b.a(this.a, false);
        if ((!com.tencent.android.tpush.service.c.a.g(i.e())) || (!b.a(this.a)))
          continue;
        TLog.i("XGService", ">> online but failed,retry");
        b.b(this.a, false);
        b.b(this.a);
        b.d(this.a);
        return;
        TLog.i("XGService", ">> retryed once,but failed again,then");
        ChannelException localChannelException = new ChannelException(paramInt, paramString);
        Iterator localIterator = b.c(this.a).iterator();
        if (!localIterator.hasNext())
          break label209;
        localm = (m)localIterator.next();
        if (localm.d != null)
          localm.d.a(localm.c, localChannelException, a.a());
      }
      TLog.e("XGService", localm.toString());
      continue;
      label209: b.c(this.a).clear();
    }
  }

  public void a(SocketChannel paramSocketChannel, StrategyItem paramStrategyItem)
  {
    TLog.v("XGService", "@@ onSuccess(" + paramSocketChannel + "," + paramSocketChannel + ")");
    while (true)
    {
      synchronized (this.a)
      {
        b.a(this.a, false);
        try
        {
          if (b.f().equals(paramStrategyItem.a()))
            continue;
          b.c = b.b;
          b.d = 2L;
          b.a(paramStrategyItem.a());
          b localb2 = this.a;
          if (paramStrategyItem.i())
          {
            if (!paramStrategyItem.h())
              continue;
            localObject2 = new d(paramSocketChannel, b.b(), paramStrategyItem.a(), paramStrategyItem.b());
            b.a(localb2, (com.tencent.android.tpush.service.channel.a.a)localObject2);
            m localm = l.a().b();
            if (localm == null)
              continue;
            TLog.e("XGTcpSendPacks", "@@ =============reconn()================" + CacheManager.getToken(i.e()));
            if ((i.e() == null) || ("0".equals(CacheManager.getToken(i.e()))))
              break label337;
            b.a(this.a, 0, localm);
            b.e(this.a).start();
            TLog.i("XGService", ">> sentMessagesOfClient Clear");
            b.f(this.a).clear();
            b.f(this.a).put(b.e(this.a), new ConcurrentHashMap());
            b.b(this.a, true);
            b.d(this.a);
            return;
            localObject2 = new com.tencent.android.tpush.service.channel.a.c(paramSocketChannel, b.b());
            continue;
          }
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
          continue;
        }
      }
      Object localObject2 = new com.tencent.android.tpush.service.channel.a.a(paramSocketChannel, b.b());
      continue;
      label337: if (!"0".equals(CacheManager.getToken(i.e())))
        continue;
      com.tencent.android.tpush.service.c.c.a(i.e(), "tpush_msgId_" + XGPushConfig.getAccessId(i.e()), "");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.c
 * JD-Core Version:    0.6.0
 */