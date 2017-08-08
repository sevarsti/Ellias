package com.tencent.tmassistantsdk.openSDK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.downloadservice.NetworkMonitorReceiver;
import com.tencent.tmassistantsdk.g.l;
import com.tencent.tmassistantsdk.protocol.jce.DownloadChunkLogInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class f extends a
  implements c
{
  protected static f h = null;
  public static final Uri k = com.tencent.tmassistantsdk.openSDK.a.b.a;
  protected int i = 1;
  Map j = null;

  public static f b()
  {
    monitorenter;
    try
    {
      if (h == null)
        h = new f();
      f localf = h;
      return localf;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int a(int paramInt)
  {
    if (this.b == null)
      throw new Exception("you must initial openSDK,by calling initQQDownloaderOpenSDK method!");
    PackageManager localPackageManager = this.b.getPackageManager();
    if (localPackageManager != null)
      try
      {
        if (localPackageManager.getPackageInfo("com.tencent.android.qqdownloader", 0) != null)
        {
          int m = com.tencent.tmassistantsdk.g.f.a().n();
          int n = com.tencent.tmassistantsdk.g.f.a().m();
          int i1 = this.i;
          if (i1 > n);
          do
            return 2;
          while (paramInt > m);
          return 0;
        }
        return 1;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        localNameNotFoundException.printStackTrace();
      }
    return 1;
  }

  public void a(Context paramContext)
  {
    if (paramContext != null)
    {
      Intent localIntent = paramContext.getPackageManager().getLaunchIntentForPackage("com.tencent.android.qqdownloader");
      if ((paramContext instanceof Application))
        localIntent.addFlags(268435456);
      paramContext.startActivity(localIntent);
    }
  }

  public void a(Context paramContext, g paramg, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramContext == null)
      throw new Exception("you must input an application or activity context!");
    String str = UUID.randomUUID().toString();
    if (this.j != null)
      this.j.put(str, paramg);
    if (paramBoolean1)
    {
      DownloadChunkLogInfo localDownloadChunkLogInfo = com.tencent.tmassistantsdk.d.b.g().a(1);
      localDownloadChunkLogInfo.via = paramg.e;
      localDownloadChunkLogInfo.UUID = str;
      localDownloadChunkLogInfo.appId = paramg.b;
      localDownloadChunkLogInfo.resultState = 1;
      com.tencent.tmassistantsdk.d.b.g().a(localDownloadChunkLogInfo);
    }
    Map localMap = super.a(paramg, paramBoolean1, paramBoolean2);
    localMap.put("taskid", str);
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(super.a(2, localMap)));
    if ((paramContext instanceof Application))
      localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(a(paramString)));
    if ((paramContext instanceof Application))
      localIntent.addFlags(268435456);
    paramContext.startActivity(localIntent);
  }

  public void a(h paramh)
  {
    g localg1 = paramh.a;
    int m = com.tencent.tmassistantsdk.g.f.a(paramh.e);
    int n = com.tencent.tmassistantsdk.g.f.b(paramh.f);
    String str1 = paramh.g;
    String str2 = paramh.d;
    l.b("QQDownloaderOpenSDK", "onDownloadStateChanged state = " + m);
    g localg2 = null;
    if (str2 != null)
    {
      int i1 = str2.trim().length();
      localg2 = null;
      if (i1 > 0)
        localg2 = (g)this.j.get(str2);
    }
    if (localg2 != null)
      a(localg2, m, n, str1);
    while (6 == m)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator1 = this.j.entrySet().iterator();
      while (true)
        if (localIterator1.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator1.next();
          g localg3 = (g)localEntry.getValue();
          String str4 = (String)localEntry.getKey();
          if ((localg3 == null) || (TextUtils.isEmpty(localg1.f)) || (!localg3.f.equals(localg1.f)) || (localg3.d != localg1.d))
            continue;
          localArrayList.add(str4);
          continue;
          l.b("QQDownloaderOpenSDK", "onDownloadStateChanged storeParam = null");
          break;
        }
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
      {
        String str3 = (String)localIterator2.next();
        this.j.remove(str3);
      }
    }
  }

  public long b(g paramg, boolean paramBoolean1, boolean paramBoolean2)
  {
    if ((this.j != null) && (paramg != null))
    {
      String str = UUID.randomUUID().toString();
      this.j.put(str, paramg);
    }
    return super.a(paramg, paramBoolean1, paramBoolean2, paramg.j, null, 2);
  }

  public long b(String paramString)
  {
    String str = super.a(paramString);
    long l1 = System.currentTimeMillis();
    long l2 = l1 + 300000L;
    return this.g.a(this.c, this.d, "", 0, str, l1, l2, 0, null);
  }

  public void b(Context paramContext)
  {
    this.b = paramContext;
    this.c = paramContext.getPackageName();
    this.d = com.tencent.tmassistantsdk.g.f.c(this.b);
    com.tencent.tmassistantsdk.g.f.a().a(this.b);
    this.i = 1;
    DownloadStateChangedReceiver.a().a(this.b);
    DownloadStateChangedReceiver.a().a(this);
    NetworkMonitorReceiver.a().b();
    com.tencent.tmassistantsdk.d.c.a().c();
    com.tencent.tmassistantsdk.d.f.a().c();
  }

  public void c()
  {
    NetworkMonitorReceiver.a().c();
    com.tencent.tmassistantsdk.d.c.a().b();
    com.tencent.tmassistantsdk.d.f.a().d();
    com.tencent.tmassistantsdk.d.f.a().b();
    if (this.b != null)
    {
      DownloadStateChangedReceiver.a().b(this.b);
      DownloadStateChangedReceiver.a().b(this);
    }
    com.tencent.tmassistantsdk.g.f.a().c();
    this.b = null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.f
 * JD-Core Version:    0.6.0
 */