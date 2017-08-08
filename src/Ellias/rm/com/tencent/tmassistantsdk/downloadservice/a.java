package com.tencent.tmassistantsdk.downloadservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class a
  implements n
{
  protected static a a = null;
  final ConcurrentHashMap b = new ConcurrentHashMap();

  public static a a()
  {
    if (a == null)
      a = new a();
    return a;
  }

  public int a(String paramString1, int paramInt, String paramString2, String paramString3, Map paramMap)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, url: " + paramString1 + "priority: " + paramInt);
    if (!c.c())
    {
      com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: 1");
      return 1;
    }
    if ((!c.b().equalsIgnoreCase("wifi")) && (f.a().b()))
    {
      com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: 2");
      return 2;
    }
    if (!c.f(paramString1))
    {
      com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: 3");
      return 3;
    }
    if (c.b(paramString1, paramString2))
    {
      com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: 4");
      return 4;
    }
    d locald1 = (d)this.b.get(paramString1);
    d locald3;
    if (locald1 == null)
    {
      locald3 = com.tencent.tmassistantsdk.f.a.a().b(paramString1);
      if (locald3 == null)
      {
        locald3 = new d(paramString1, paramInt, paramString2);
        if (locald3.a.equals("resource/tm.android.unknown"))
          locald3.l = paramString3;
        this.b.put(paramString1, locald3);
      }
    }
    for (d locald2 = locald3; ; locald2 = locald1)
    {
      locald2.a((HashMap)paramMap);
      if (locald2.a.equals("application/tm.android.apkdiff"));
      for (boolean bool1 = c.b(paramString1, locald2.a); ; bool1 = c.g(locald2.l))
      {
        if ((locald2.c()) && (!bool1))
        {
          this.b.remove(paramString1);
          com.tencent.tmassistantsdk.f.a.a().a(paramString1);
          locald2 = new d(paramString1, paramInt, paramString2);
          if (locald2.a.equals("resource/tm.android.unknown"))
            locald2.l = paramString3;
          locald2.a((HashMap)paramMap);
          this.b.put(paramString1, locald2);
        }
        int i = locald2.f();
        com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: " + i);
        return i;
        if (locald3.a.equals("application/tm.android.apkdiff"));
        for (boolean bool2 = c.b(paramString1, locald3.a); bool2; bool2 = c.g(locald3.l))
        {
          com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call startDownload, return errCode: 4");
          return 4;
        }
        break;
      }
    }
  }

  public void a(l paraml)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call AddDownloadListener, dl: " + paraml);
    e.a().a(paraml);
  }

  public void a(String paramString)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call pauseDownload, url: " + paramString);
    d locald = (d)this.b.get(paramString);
    if (locald != null)
      locald.g();
  }

  public void b()
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "Start to load DownloadInfo list.");
    this.b.clear();
    ArrayList localArrayList = com.tencent.tmassistantsdk.f.a.a().b();
    if (localArrayList != null)
    {
      com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "The size of downloadinfo_list: " + localArrayList.size());
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)localIterator.next();
        com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "---------------load download info---------------");
        locald.a("ApkDownloadManager");
        this.b.put(locald.b, locald);
        if (locald.e())
          continue;
        locald.a(3);
      }
    }
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "Add NetworkChangedObserver to NetworkMonitorReceiver");
    NetworkMonitorReceiver.a().a(this);
  }

  public void b(l paraml)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call RemoveDownloadListener, dl: " + paraml);
    e.a().b(paraml);
  }

  public void b(String paramString)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call cancelDownload, url: " + paramString);
    d locald = (d)this.b.remove(paramString);
    if (locald != null)
      locald.h();
  }

  public d c(String paramString)
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "call queryDownloadInfo, url: " + paramString);
    d locald = (d)this.b.get(paramString);
    if (locald == null)
      locald = com.tencent.tmassistantsdk.f.a.a().b(paramString);
    boolean bool = false;
    if (locald != null)
      if (!locald.a.equals("application/tm.android.apkdiff"))
        break label110;
    label110: for (bool = c.b(paramString, locald.a); ; bool = c.g(locald.l))
    {
      if ((locald != null) && (locald.c()) && (!bool))
      {
        this.b.remove(paramString);
        com.tencent.tmassistantsdk.f.a.a().a(paramString);
        locald = null;
      }
      return locald;
    }
  }

  public void c()
  {
    NetworkMonitorReceiver.a().b(this);
    ArrayList localArrayList = new ArrayList();
    if (!this.b.isEmpty())
    {
      Iterator localIterator = this.b.keySet().iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)this.b.get(localIterator.next());
        if (locald.i == 2)
          a(locald.b);
        localArrayList.add(locald);
      }
    }
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "Start to save DownloadInfo list.");
    com.tencent.tmassistantsdk.f.a.a().a(localArrayList);
  }

  public Boolean d()
  {
    Iterator localIterator = this.b.keySet().iterator();
    while (localIterator.hasNext())
      if (!((d)this.b.get(localIterator.next())).e())
        return Boolean.valueOf(false);
    return Boolean.valueOf(true);
  }

  public void e()
  {
    com.tencent.tmassistantsdk.g.l.b("ApkDownloadManager", "onNetworkChanged");
    if ((c.c()) && (f.a().d()))
    {
      String str = c.b();
      Iterator localIterator = this.b.keySet().iterator();
      while (localIterator.hasNext())
      {
        d locald = (d)this.b.get(localIterator.next());
        if ((!f.a().a(locald.p, str)) || ((locald.o != 601) && (locald.o != 602) && (locald.o != 605)) || (System.currentTimeMillis() - locald.q >= 120000L))
          continue;
        locald.f();
      }
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.a
 * JD-Core Version:    0.6.0
 */