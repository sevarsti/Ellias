package com.tencent.qqgamemi.business;

import CobraHallQmiProto.TBodyGetPluginNoticeRsp;
import CobraHallQmiProto.TPloginNotice;
import android.annotation.SuppressLint;
import android.text.TextUtils;
import com.tencent.component.event.Event;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.component.event.Observable;
import com.tencent.component.event.Observer;
import com.tencent.component.protocol.ProtocolManager;
import com.tencent.component.protocol.ProtocolRequest;
import com.tencent.component.protocol.ProtocolRequestListener;
import com.tencent.component.protocol.ProtocolResponse;
import com.tencent.component.utils.clock.SimpleClock;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.plugin.PluginItem;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;
import com.tencent.qqgamemi.protocol.business.PluginUndealCountRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

@SuppressLint({"UseSparseArrays"})
public class PluginUndealCountManager extends Observable
  implements Observer, ProtocolRequestListener
{
  private static final String b = "PluginUndealCountManager";
  private static volatile PluginUndealCountManager c;
  private static final long e = 10000L;
  private static final long f = 10000L;
  private static final long g = 30000L;
  private static final long h = 60000L;
  private static final int i = 1;
  private static final int j = 2;
  private static final int k = 3;
  private HashMap a = new HashMap(10);
  private volatile SimpleClock d;
  private int l = 1;
  private long m = 30000L;
  private long n = 30000L;
  private long o = 60000L;
  private long p;
  private long q;

  private PluginUndealCountManager()
  {
    super("QmiUndealCount");
  }

  public static PluginUndealCountManager a()
  {
    if (c == null)
      monitorenter;
    try
    {
      if (c == null)
        c = new PluginUndealCountManager();
      return c;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void b(String paramString, int paramInt)
  {
    b(paramString, 1, paramInt);
  }

  private void b(String paramString, short paramShort, int paramInt)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      PluginUndealCountManager.UndealCount localUndealCount = (PluginUndealCountManager.UndealCount)this.a.get(paramString);
      if (localUndealCount == null)
        localUndealCount = new PluginUndealCountManager.UndealCount();
      localUndealCount.c = paramShort;
      localUndealCount.d = paramInt;
      this.a.put(paramString, localUndealCount);
    }
  }

  private void e()
  {
    notifyNormal(1, new Object[0]);
  }

  private void f()
  {
    monitorenter;
    try
    {
      if (this.d == null)
        this.d = SimpleClock.set(this.m, 0L, new d(this));
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  private ArrayList g()
  {
    ArrayList localArrayList1 = QMiPluginManager.a().h();
    ArrayList localArrayList2 = new ArrayList();
    if ((localArrayList1 != null) && (localArrayList1.size() > 0))
    {
      Iterator localIterator = localArrayList1.iterator();
      while (localIterator.hasNext())
      {
        PluginItem localPluginItem = (PluginItem)localIterator.next();
        if ((localPluginItem == null) || (localPluginItem.getStatus() != 7))
          continue;
        localArrayList2.add(localPluginItem.id);
      }
    }
    return localArrayList2;
  }

  private void h()
  {
    PluginUndealCountRequest localPluginUndealCountRequest = new PluginUndealCountRequest(g());
    localPluginUndealCountRequest.a(this);
    ProtocolManager.b().a(localPluginUndealCountRequest, QMiJceCommonData.b());
  }

  public PluginUndealCountManager.UndealCount a(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
      return (PluginUndealCountManager.UndealCount)this.a.get(paramString);
    return null;
  }

  public void a(int paramInt, ProtocolRequest paramProtocolRequest, ProtocolResponse paramProtocolResponse)
  {
    switch (paramInt)
    {
    default:
    case 115:
    }
    TBodyGetPluginNoticeRsp localTBodyGetPluginNoticeRsp;
    PluginUndealCountRequest localPluginUndealCountRequest;
    do
    {
      return;
      localTBodyGetPluginNoticeRsp = (TBodyGetPluginNoticeRsp)paramProtocolResponse.getBusiResponse();
      localPluginUndealCountRequest = (PluginUndealCountRequest)paramProtocolRequest;
    }
    while (localTBodyGetPluginNoticeRsp == null);
    ArrayList localArrayList1 = localTBodyGetPluginNoticeRsp.pluginNoticeList;
    if (localArrayList1 != null)
    {
      ArrayList localArrayList2 = localPluginUndealCountRequest.m();
      Iterator localIterator1 = localArrayList1.iterator();
      while (localIterator1.hasNext())
      {
        TPloginNotice localTPloginNotice = (TPloginNotice)localIterator1.next();
        if (localTPloginNotice == null)
          continue;
        localArrayList2.remove(localTPloginNotice.pluginPkgName);
        b(localTPloginNotice.pluginPkgName, localTPloginNotice.noticeType, localTPloginNotice.eventCount);
      }
      Iterator localIterator2 = localArrayList2.iterator();
      while (localIterator2.hasNext())
        b((String)localIterator2.next(), 0);
      e();
    }
    long l1 = Math.max(1000 * localTBodyGetPluginNoticeRsp.getInterval, 10000L);
    long l2 = Math.max(1000 * localTBodyGetPluginNoticeRsp.getIntervalLonger, 10000L);
    switch (this.l)
    {
    default:
      l1 = 0L;
    case 1:
    case 2:
    }
    while (true)
    {
      LogUtil.d("PluginUndealCountManager", "rsp.getInterval:" + localTBodyGetPluginNoticeRsp.getInterval + " |rsp.getIntervalLonger:" + localTBodyGetPluginNoticeRsp.getIntervalLonger + " |nextInterval:" + l1 + " |currentInterval:" + this.m);
      if ((l1 == 0L) || (this.m == l1))
        break;
      this.m = Math.max(l1, 10000L);
      d();
      f();
      return;
      l1 = l2;
    }
  }

  public void a(long paramLong)
  {
    monitorenter;
    if (paramLong >= 10000L);
    try
    {
      this.m = paramLong;
      d();
      f();
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(String paramString, int paramInt)
  {
    b(paramString, paramInt);
    e();
  }

  public void a(String paramString, short paramShort, int paramInt)
  {
    b(paramString, paramShort, paramInt);
    e();
  }

  public void b()
  {
    EventCenter.getInstance().addObserver(this, "qmiLogin", new int[] { 2 });
    EventCenter.getInstance().addObserver(this, "QmiUI", new int[] { 1, 2, 4, 3 });
  }

  public void b(int paramInt, ProtocolRequest paramProtocolRequest, ProtocolResponse paramProtocolResponse)
  {
  }

  public void b(String paramString)
  {
    b(paramString, 0);
    e();
  }

  public void c()
  {
    EventCenter.getInstance().removeObserver(this);
  }

  public void d()
  {
    monitorenter;
    try
    {
      SimpleClock localSimpleClock = this.d;
      if (localSimpleClock != null)
      {
        SimpleClock.cancel(localSimpleClock);
        this.d = null;
      }
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void onNotify(Event paramEvent)
  {
    if ("qmiLogin".equals(paramEvent.source.name))
      switch (paramEvent.what)
      {
      default:
      case 2:
      }
    do
    {
      return;
      this.a.clear();
      d();
      e();
      return;
    }
    while (!"QmiUI".equals(paramEvent.source.name));
    switch (paramEvent.what)
    {
    default:
      return;
    case 1:
      LogUtil.d("PluginUndealCountManager", "qmi enter background --> stop clock.");
      this.l = 3;
      d();
      return;
    case 2:
      LogUtil.d("PluginUndealCountManager", "on game resume --> reset clock.");
      this.l = 1;
      a(this.n);
      return;
    case 3:
      LogUtil.d("PluginUndealCountManager", "qmi scroll to side --> reset clock.");
      this.l = 2;
      a(this.o);
      return;
    case 4:
    }
    LogUtil.d("PluginUndealCountManager", "qmi scroll to scene --> reset clock.");
    this.l = 1;
    a(this.n);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.PluginUndealCountManager
 * JD-Core Version:    0.6.0
 */