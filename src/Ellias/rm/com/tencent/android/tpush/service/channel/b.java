package com.tencent.android.tpush.service.channel;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Looper;
import com.qq.taf.jce.JceStruct;
import com.tencent.android.tpush.data.CachedMessageIntent;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.horse.o;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.channel.exception.ChannelException;
import com.tencent.android.tpush.service.channel.protocol.TpnsPushVerifyReq;
import com.tencent.android.tpush.service.channel.protocol.TpnsReconnectReq;
import com.tencent.android.tpush.service.q;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class b
  implements o, com.tencent.android.tpush.service.channel.a.b
{
  public static long a = 140000L;
  public static long b = 280000L;
  public static long c = b;
  public static long d = 2L;
  private static volatile long n = 0L;
  private static volatile String o = "";
  private Handler e = new Handler(Looper.getMainLooper());
  private ArrayList f = new ArrayList();
  private Map g = new ConcurrentHashMap();
  private Map h = new ConcurrentHashMap();
  private com.tencent.android.tpush.service.channel.a.a i = null;
  private volatile boolean j = false;
  private PendingIntent k = null;
  private m l = null;
  private volatile boolean m = true;
  private com.tencent.android.tpush.horse.n p = new c(this);
  private n q = new d(this);

  private b()
  {
    TLog.v("XGService", "@@ TpnsChannel()");
    com.tencent.android.tpush.horse.i.a().a(this);
    this.e = com.tencent.android.tpush.common.c.a().b();
  }

  private void a(int paramInt, m paramm)
  {
    TLog.v("XGService", "@@ messageInQueue(" + paramInt + "," + paramm + ")");
    i();
    monitorenter;
    while (true)
    {
      try
      {
        if (this.f.size() < 128)
        {
          paramm.a = System.currentTimeMillis();
          if (paramInt != -1)
            continue;
          this.f.add(paramm);
          if (this.i == null)
            break label183;
          this.i.g();
          monitorexit;
          k localk = new k(this, null);
          this.e.postDelayed(localk, com.tencent.android.tpush.service.a.a.f);
          this.h.put(paramm, localk);
          return;
          this.f.add(paramInt, paramm);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      TLog.e("XGService", ">>FG messageInQueue is full,size:" + this.f.size());
      continue;
      label183: if (this.j)
        continue;
      this.j = true;
      com.tencent.android.tpush.horse.i.a().a(this.p);
    }
  }

  public static b b()
  {
    return l.a;
  }

  private void g()
  {
    TLog.v("XGService", "@@ checkAndSetupClient(" + this.i + "," + this.j + ")");
    monitorenter;
    try
    {
      if ((this.i == null) && (!this.j))
      {
        this.j = true;
        com.tencent.android.tpush.horse.i.a().a(this.p);
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void h()
  {
    monitorenter;
    try
    {
      TLog.v("XGService", "@@ heartbeat()");
      if (this.f.isEmpty())
      {
        if (this.l == null)
          this.l = new m(7, null, this.q);
        TLog.e("XGTcpSendPacks", "@@ =============heartbeat()================");
        a(-1, this.l);
      }
      j();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  @SuppressLint({"SimpleDateFormat"})
  private void i()
  {
    TLog.v("XGService", "@@ trySendCachedMsgIntent()");
    long l1 = System.currentTimeMillis();
    Context localContext;
    ArrayList localArrayList1;
    ArrayList localArrayList2;
    if (l1 - n > 60000L)
    {
      n = l1;
      localContext = com.tencent.android.tpush.service.i.e();
      if ((localContext != null) && (!com.tencent.android.tpush.service.c.c.a(localContext.getPackageName())))
      {
        localArrayList1 = com.tencent.android.tpush.service.b.a.a().e(localContext);
        if (localArrayList1 != null)
        {
          TLog.i("XGService", ">> CachedMsgList size:" + localArrayList1.size());
          localArrayList2 = new ArrayList();
        }
      }
    }
    for (int i1 = 0; ; i1++)
      if (i1 < localArrayList1.size())
      {
        CachedMessageIntent localCachedMessageIntent = (CachedMessageIntent)localArrayList1.get(i1);
        Intent localIntent;
        String str2;
        SimpleDateFormat localSimpleDateFormat;
        try
        {
          String str1 = Rijndael.decrypt(localCachedMessageIntent.intent);
          if (com.tencent.android.tpush.service.c.c.a(str1))
          {
            localArrayList2.add(localCachedMessageIntent);
            continue;
          }
          localIntent = Intent.parseUri(str1, 1);
          str2 = localIntent.getStringExtra("date");
          TLog.i("XGService", ">> date:" + str2);
          localSimpleDateFormat = new SimpleDateFormat("yyyyMMdd");
          if ((com.tencent.android.tpush.service.c.c.a(str2)) || ((!com.tencent.android.tpush.service.c.c.a(str2)) && (localSimpleDateFormat.parse(str2).compareTo(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date()))) == 0)))
          {
            if (!com.tencent.android.tpush.service.c.c.a(localIntent))
              continue;
            TLog.i("XGService", ">> sendBroadcast intent:" + localIntent);
            com.tencent.android.tpush.service.i.e().sendBroadcast(localIntent);
            localArrayList2.add(localCachedMessageIntent);
          }
        }
        catch (Exception localException)
        {
          TLog.e("XGService", localException.toString());
        }
        if ((com.tencent.android.tpush.service.c.c.a(str2)) || (localSimpleDateFormat.parse(str2).compareTo(localSimpleDateFormat.parse(localSimpleDateFormat.format(new Date()))) >= 0))
          continue;
        TLog.i("XGService", ">> sendBroadcast intent:" + localIntent);
        com.tencent.android.tpush.service.i.e().sendBroadcast(localIntent);
        localArrayList2.add(localCachedMessageIntent);
      }
      else
      {
        if (localArrayList2.size() > 0)
          com.tencent.android.tpush.service.b.a.a().a(localContext, localArrayList2, com.tencent.android.tpush.service.b.a.a().e(localContext));
        return;
      }
  }

  private void j()
  {
    TLog.v("XGService", "@@ scheduleHeartbeat()");
    if (this.k == null)
    {
      com.tencent.android.tpush.service.i.e().registerReceiver(new e(this), new IntentFilter("com.tencent.android.tpush.service.channel.heartbeatIntent"));
      Intent localIntent = new Intent("com.tencent.android.tpush.service.channel.heartbeatIntent");
      this.k = PendingIntent.getBroadcast(com.tencent.android.tpush.service.i.e(), 0, localIntent, 134217728);
    }
    long l1 = System.currentTimeMillis();
    TLog.i("XGService", ">> heartbeatinterval" + c);
    long l2;
    if (c > b)
      l2 = b;
    while (true)
    {
      long l3 = l2 + l1;
      q.a().a(0, l3, this.k);
      if (com.tencent.android.tpush.service.i.e() != null)
      {
        long l4 = System.currentTimeMillis();
        String str = com.tencent.android.tpush.service.a.a.b("lastRptTimeMillis");
        int i1 = com.tencent.android.tpush.service.a.a.a("rptLiveIntvl", 3600);
        TLog.i("XGService", ">> rptLiveIntvl" + i1);
        if (l4 - com.tencent.android.tpush.service.c.c.b(com.tencent.android.tpush.service.i.e(), str, 0L) > i1 * 1000)
        {
          com.tencent.android.tpush.service.c.c.a(com.tencent.android.tpush.service.i.e(), str, l4);
          com.tencent.android.tpush.service.c.c.d("?type=hb&token=" + CacheManager.getToken(com.tencent.android.tpush.service.i.e()));
        }
      }
      return;
      l2 = c;
    }
  }

  public ArrayList a(com.tencent.android.tpush.service.channel.a.a parama, int paramInt)
  {
    monitorenter;
    ArrayList localArrayList;
    while (true)
    {
      long l1;
      ConcurrentHashMap localConcurrentHashMap;
      Iterator localIterator;
      m localm2;
      try
      {
        TLog.v("XGTcpSendPacks", "@@ clientFetchSendPackets(" + paramInt + ")");
        if (paramInt >= 1)
          continue;
        paramInt = 1;
        l1 = System.currentTimeMillis();
        localConcurrentHashMap = (ConcurrentHashMap)this.g.get(parama);
        localArrayList = new ArrayList(paramInt);
        if (this.f.isEmpty())
          break;
        localIterator = this.f.iterator();
        m localm1 = (m)localIterator.next();
        com.tencent.android.tpush.service.channel.b.h localh1 = new com.tencent.android.tpush.service.channel.b.h(localm1.b());
        localm1.a(localh1);
        localArrayList.add(localh1);
        localm1.b = l1;
        if (localm1.a())
          continue;
        localConcurrentHashMap.put(Integer.valueOf(localm1.c()), localm1);
        localIterator.remove();
        i1 = paramInt - 1;
        boolean bool = localm1.c instanceof TpnsReconnectReq;
        if (!localIterator.hasNext())
          break;
        localm2 = (m)localIterator.next();
        if ((bool) && (((localm2.c instanceof TpnsReconnectReq)) || ((localm2.c instanceof TpnsPushVerifyReq))))
        {
          if (localm2.d == null)
            continue;
          this.e.post(new f(this, localm2));
          localIterator.remove();
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      int i2 = i1 - 1;
      if (i1 > 0)
      {
        com.tencent.android.tpush.service.channel.b.h localh2 = new com.tencent.android.tpush.service.channel.b.h(localm2.b());
        localm2.a(localh2);
        localArrayList.add(localh2);
        localm2.b = l1;
        if (!localm2.a())
          localConcurrentHashMap.put(Integer.valueOf(localm2.c()), localm2);
        localIterator.remove();
      }
      int i1 = i2;
    }
    monitorexit;
    return localArrayList;
  }

  public void a()
  {
    TLog.v("XGService", "@@ onNetworkChanged()");
    e();
  }

  public void a(JceStruct paramJceStruct, n paramn)
  {
    TLog.v("XGService", "@@ sendMessage(" + paramJceStruct + ")");
    if (paramJceStruct != null);
    try
    {
      a(-1, new m(paramJceStruct, paramn));
      return;
    }
    catch (Exception localException)
    {
      TLog.e("XGService", localException.toString());
    }
  }

  public void a(com.tencent.android.tpush.service.channel.a.a parama)
  {
    TLog.v("XGService", "@@ clientDidCancelled(isHttpClient : " + (parama instanceof com.tencent.android.tpush.service.channel.a.c) + ")");
    ChannelException localChannelException = new ChannelException(10102, "TpnsClient is cancelled!");
    this.e.post(new i(this, parama, localChannelException));
    d();
    if (!this.f.isEmpty())
    {
      TLog.i("XGService", ">> tpnsMessages is not empty!");
      g();
    }
  }

  public void a(com.tencent.android.tpush.service.channel.a.a parama, com.tencent.android.tpush.service.channel.b.i parami)
  {
    TLog.v("XGService", "@@ clientDidSendPacket(isHttpClient : " + (parama instanceof com.tencent.android.tpush.service.channel.a.c) + "," + parami + ")");
    m localm = (m)((ConcurrentHashMap)this.g.get(parama)).get(Integer.valueOf(parami.g()));
    if (localm != null)
    {
      localm.b = System.currentTimeMillis();
      return;
    }
    TLog.e("XGService", ">> message(" + parami.g() + ") not in the sentQueue!");
  }

  public void a(com.tencent.android.tpush.service.channel.a.a parama, ChannelException paramChannelException)
  {
    TLog.v("XGService", "@@ clientExceptionOccurs(isHttpClient : " + (parama instanceof com.tencent.android.tpush.service.channel.a.c) + "," + paramChannelException + ")");
    this.e.post(new i(this, parama, paramChannelException));
    this.i = null;
    d();
    monitorenter;
    try
    {
      if ((c >= a) || (d < 1L))
      {
        c = 4L * (c / 5L);
        d = 2L * d;
      }
      while (true)
      {
        d();
        if (!this.f.isEmpty())
        {
          TLog.i("XGService", ">> tpnsMessages is not empty!");
          g();
        }
        return;
        c = b;
        d = 2L;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void b(com.tencent.android.tpush.service.channel.a.a parama)
  {
    TLog.v("XGService", "@@ clientDidRetired(isHttpClient : " + (parama instanceof com.tencent.android.tpush.service.channel.a.c) + ")");
    ChannelException localChannelException = new ChannelException(10105, "TpnsMessage timeout!");
    this.e.post(new i(this, parama, localChannelException));
    d();
    if (!this.f.isEmpty())
    {
      TLog.i("XGService", ">> tpnsMessages is not empty!");
      g();
    }
  }

  public void b(com.tencent.android.tpush.service.channel.a.a parama, com.tencent.android.tpush.service.channel.b.i parami)
  {
    monitorenter;
    while (true)
    {
      try
      {
        i();
        TLog.v("XGService", "@@ clientDidReceivePacket(isHttpClient : " + (parama instanceof com.tencent.android.tpush.service.channel.a.c) + ",Protocol:" + parami.h() + ")");
        switch (parami.h())
        {
        default:
          TLog.e("XGTcpRecvPacks", "@@ =============Other()================");
          TLog.e("XGService", ">> clientDidReceivePacket >>> 不支持的协议：" + parami);
          j();
          return;
        case 1:
          if (parami.e())
            break;
          TLog.e("XGTcpRecvPacks", "@@ =============EResponse()================");
          this.e.post(new j(this, parama, parami));
        case 10:
          if (!parami.e())
            break label243;
          TLog.e("XGTcpRecvPacks", "@@ =============NResponse()================");
          this.e.post(new j(this, parama, parami));
          j();
          continue;
        case 20:
        }
      }
      finally
      {
        monitorexit;
      }
      TLog.e("XGTcpRecvPacks", "@@ =============EPushMessage()================");
      this.e.post(new h(this, parama, parami));
      continue;
      label243: TLog.e("XGTcpRecvPacks", "@@ =============NPushMessage()================");
      this.e.post(new h(this, parama, parami));
      continue;
      TLog.e("XGTcpRecvPacks", "@@ =============HeartBeat()================");
      this.e.post(new g(this, parama, parami));
    }
  }

  public void c()
  {
    TLog.v("XGService", "@@ init()");
    g();
  }

  public void d()
  {
    TLog.v("XGService", "@@ finish()");
    if (this.i != null)
    {
      this.i.c();
      this.i = null;
    }
    this.j = false;
  }

  public void e()
  {
    TLog.v("XGService", "@@ reCreateClient()");
    if (this.i != null)
      d();
    g();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.channel.b
 * JD-Core Version:    0.6.0
 */