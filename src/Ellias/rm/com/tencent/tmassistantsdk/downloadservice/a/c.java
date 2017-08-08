package com.tencent.tmassistantsdk.downloadservice.a;

import com.tencent.tmassistantsdk.c.i;
import com.tencent.tmassistantsdk.downloadservice.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class c
  implements com.tencent.tmassistantsdk.downloadservice.l
{
  protected ArrayList a = new ArrayList();
  protected a b = null;

  public c(ArrayList paramArrayList)
  {
    if ((paramArrayList != null) && (paramArrayList.size() > 0))
      this.a.addAll(paramArrayList);
  }

  public int a(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, Map paramMap)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return 3;
    com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "startDownload,clientKey:" + paramString1);
    if (d(paramString1, paramString2) != null)
    {
      com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "startDownload,clientKey:" + paramString1 + ",taskItem is null");
      return com.tencent.tmassistantsdk.downloadservice.a.a().a(paramString2, paramInt, paramString3, paramString4, paramMap);
    }
    ArrayList localArrayList = a(paramString2);
    b localb1 = new b(paramString1, paramString2);
    localb1.c = 1;
    this.a.add(localb1);
    com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "startDownload,clientKey:" + paramString1 + ",add newTask");
    Iterator localIterator = localArrayList.iterator();
    int i = 0;
    int j = 0;
    b localb2;
    int k;
    int m;
    if (localIterator.hasNext())
    {
      localb2 = (b)localIterator.next();
      if (localb2.c == 2)
      {
        k = 1;
        m = j;
      }
    }
    while (true)
    {
      j = m;
      i = k;
      break;
      if (localb2.c == 1)
      {
        k = i;
        m = 1;
        continue;
        if ((i == 1) || (j == 1))
        {
          if (i == 1)
            localb1.c = 2;
          while (true)
          {
            if (this.b != null)
              this.b.a(paramString1, paramString2, localb1.c, 0, null);
            com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "startDownload,clientKey:" + paramString1 + ",newTask is downloading");
            return 0;
            if (j != 1)
              continue;
            localb1.c = 1;
          }
        }
        com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "startDownload,clientKey:" + paramString1 + ",start newTask download");
        return com.tencent.tmassistantsdk.downloadservice.a.a().a(paramString2, paramInt, paramString3, paramString4, paramMap);
      }
      k = i;
      m = j;
    }
  }

  public i a(String paramString1, String paramString2)
  {
    d locald = com.tencent.tmassistantsdk.downloadservice.a.a().c(paramString2);
    if (locald != null)
      return new i(locald.b, com.tencent.tmassistantsdk.f.b.b(locald.l), locald.i, locald.j, locald.b(), locald.a);
    if (com.tencent.tmassistantsdk.downloadservice.c.b(paramString2, "application/vnd.android.package-archive"))
    {
      String str3 = com.tencent.tmassistantsdk.downloadservice.c.a(paramString2, "application/vnd.android.package-archive");
      String str4 = com.tencent.tmassistantsdk.f.b.b(str3);
      com.tencent.tmassistantsdk.f.b localb2 = new com.tencent.tmassistantsdk.f.b(str3, str3);
      return new i(paramString2, str4, 4, localb2.b(), localb2.b(), "application/vnd.android.package-archive");
    }
    if (com.tencent.tmassistantsdk.downloadservice.c.b(paramString2, "application/tm.android.apkdiff"))
    {
      String str1 = com.tencent.tmassistantsdk.downloadservice.c.a(paramString2, "application/tm.android.apkdiff");
      String str2 = com.tencent.tmassistantsdk.f.b.b(str1);
      com.tencent.tmassistantsdk.f.b localb1 = new com.tencent.tmassistantsdk.f.b(str1, str1);
      return new i(paramString2, str2, 4, localb1.b(), localb1.b(), "application/tm.android.apkdiff");
    }
    b(paramString2);
    return null;
  }

  protected ArrayList a(String paramString)
  {
    monitorenter;
    if (paramString == null);
    ArrayList localArrayList;
    for (Object localObject2 = null; ; localObject2 = localArrayList)
    {
      monitorexit;
      return localObject2;
      try
      {
        localArrayList = new ArrayList();
        Iterator localIterator = this.a.iterator();
        while (localIterator.hasNext())
        {
          b localb = (b)localIterator.next();
          if (!localb.b.equals(paramString))
            continue;
          localArrayList.add(localb);
        }
      }
      finally
      {
        monitorexit;
      }
    }
  }

  public void a()
  {
    if (this != null)
      com.tencent.tmassistantsdk.downloadservice.a.a().a(this);
  }

  public void a(a parama)
  {
    this.b = parama;
  }

  public void a(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    if (this.b == null);
    while (true)
    {
      return;
      ArrayList localArrayList = a(paramString1);
      if ((localArrayList == null) || (localArrayList.size() <= 0))
        continue;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "OnDownloadStateChanged,clientKey:" + localb.a + ",state:" + paramInt1 + ",errorcode:" + paramInt2 + ",url:" + paramString1.hashCode());
        localb.c = paramInt1;
        this.b.a(localb.a, paramString1, paramInt1, paramInt2, paramString2);
      }
    }
  }

  public void a(String paramString, long paramLong1, long paramLong2)
  {
    if (this.b == null);
    while (true)
    {
      return;
      ArrayList localArrayList = a(paramString);
      if ((localArrayList == null) || (localArrayList.size() <= 0))
        continue;
      long l = System.currentTimeMillis();
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        b localb = (b)localIterator.next();
        if (localb.a(paramLong1, paramLong2, l) != true)
          continue;
        com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "OnDownloadProgressChanged,clientKey:" + localb.a + ",receivedLen:" + paramLong1 + ",url:" + paramString.hashCode());
        this.b.a(localb.a, paramString, paramLong1, paramLong2);
      }
    }
  }

  public void b()
  {
    if (this != null)
      com.tencent.tmassistantsdk.downloadservice.a.a().b(this);
  }

  protected void b(String paramString)
  {
    monitorenter;
    if (paramString == null);
    while (true)
    {
      monitorexit;
      return;
      ArrayList localArrayList;
      try
      {
        localArrayList = new ArrayList();
        Iterator localIterator = this.a.iterator();
        while (localIterator.hasNext())
        {
          b localb = (b)localIterator.next();
          if (!localb.b.equals(paramString))
            continue;
          localArrayList.add(localb);
        }
      }
      finally
      {
        monitorexit;
      }
      if (localArrayList.size() <= 0)
        continue;
      this.a.removeAll(localArrayList);
    }
  }

  public void b(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "pauseDownload,clientKey:" + paramString1);
    b localb = d(paramString1, paramString2);
    if (localb != null)
    {
      localb.c = 3;
      this.a.remove(localb);
      com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "pauseDownload,clientKey:" + paramString1 + ",remove taskItem");
      ArrayList localArrayList = a(paramString2);
      if ((localArrayList == null) || (localArrayList.size() == 0))
      {
        com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "pauseDownload,clientKey:" + paramString1 + ",taskItem is the only on pauseAll");
        com.tencent.tmassistantsdk.downloadservice.a.a().a(paramString2);
        com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "pauseDownload end,clientKey:" + paramString1 + ",taskItem is the only on pauseAll");
      }
      if (this.b != null)
        this.b.a(paramString1, paramString2, localb.c, 0, null);
    }
    while (true)
    {
      com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "pauseDownload end,clientKey:" + paramString1);
      return;
      com.tencent.tmassistantsdk.g.l.d("ServiceDownloadTaskManager", "pauseDownload,clientKey:" + paramString1 + ",taskItem is null");
    }
  }

  public void c(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null))
      return;
    com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "cancelDownload,clientKey:" + paramString1);
    b localb = d(paramString1, paramString2);
    if (localb != null)
    {
      this.a.remove(localb);
      com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "cancelDownload,clientKey:" + paramString1 + ",remove taskItem");
    }
    while (true)
    {
      ArrayList localArrayList = a(paramString2);
      if ((localArrayList != null) && (localArrayList.size() != 0))
        break;
      com.tencent.tmassistantsdk.g.l.b("ServiceDownloadTaskManager", "cancelDownload,clientKey:" + paramString1 + ",taskItem is the only on cancelAll");
      com.tencent.tmassistantsdk.downloadservice.a.a().b(paramString2);
      return;
      com.tencent.tmassistantsdk.g.l.d("ServiceDownloadTaskManager", "cancelDownload,clientKey:" + paramString1 + ",taskItem is null");
    }
  }

  protected b d(String paramString1, String paramString2)
  {
    monitorenter;
    b localb;
    if ((paramString1 == null) || (paramString2 == null))
      localb = null;
    while (true)
    {
      monitorexit;
      return localb;
      try
      {
        Iterator localIterator = this.a.iterator();
        while (true)
          if (localIterator.hasNext())
          {
            localb = (b)localIterator.next();
            if ((localb.a == null) || (!localb.a.equals(paramString1)))
              continue;
            boolean bool = localb.b.equals(paramString2);
            if (!bool)
              continue;
            break;
          }
        localb = null;
        continue;
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.a.c
 * JD-Core Version:    0.6.0
 */