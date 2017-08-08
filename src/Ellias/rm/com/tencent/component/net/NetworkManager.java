package com.tencent.component.net;

import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.tencent.component.net.http.download.Downloader;
import com.tencent.component.net.http.download.ImageDownloader;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NetworkManager
{
  public static final int a = 20000;
  public static final int b = 0;
  public static final int c = 1;
  public static final int d = 2;
  public static final int e = 3;
  public static final int f = 4;
  private static Downloader g;
  private static Downloader h;
  private static NetworkManager.NetworkChangeReceiver i;
  private static SharedPreferences j;
  private static Object k = new Object();
  private static List l = Collections.synchronizedList(new ArrayList());

  public static Downloader a(Context paramContext)
  {
    return new Downloader(paramContext);
  }

  public static String a()
  {
    String str = "none";
    if (i != null)
    {
      str = i.a();
      if (str == "none")
        str = i.d();
    }
    return str;
  }

  public static void a(NetworkManager.NetStatusListener paramNetStatusListener)
  {
    WeakReference localWeakReference = new WeakReference(paramNetStatusListener);
    if (localWeakReference != null)
      synchronized (k)
      {
        l.add(localWeakReference);
        return;
      }
  }

  public static Downloader b(Context paramContext)
  {
    if (g != null)
      return g;
    monitorenter;
    try
    {
      if (g != null)
      {
        Downloader localDownloader2 = g;
        return localDownloader2;
      }
    }
    finally
    {
      monitorexit;
    }
    g = new Downloader(paramContext);
    Downloader localDownloader1 = g;
    monitorexit;
    return localDownloader1;
  }

  public static void b(NetworkManager.NetStatusListener paramNetStatusListener)
  {
    synchronized (k)
    {
      Iterator localIterator = l.iterator();
      while (localIterator.hasNext())
      {
        WeakReference localWeakReference = (WeakReference)localIterator.next();
        if ((NetworkManager.NetStatusListener)localWeakReference.get() != paramNetStatusListener)
          continue;
        l.remove(localWeakReference);
      }
      return;
    }
  }

  public static boolean b()
  {
    String str = a();
    if (TextUtils.isEmpty(str));
    do
      return false;
    while ((!str.contains("cmwap")) && (!str.contains("uniwap")) && (!str.contains("3gwap")) && (!str.contains("ctwap")));
    return true;
  }

  public static void c(Context paramContext)
  {
    e(paramContext);
  }

  public static Downloader d(Context paramContext)
  {
    if (h != null)
      return h;
    monitorenter;
    try
    {
      if (h != null)
      {
        Downloader localDownloader2 = h;
        return localDownloader2;
      }
    }
    finally
    {
      monitorexit;
    }
    h = new ImageDownloader(paramContext);
    e(paramContext);
    Downloader localDownloader1 = h;
    monitorexit;
    return localDownloader1;
  }

  private static void e(Context paramContext)
  {
    if (i == null)
    {
      i = new NetworkManager.NetworkChangeReceiver(paramContext);
      IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
      paramContext.registerReceiver(i, localIntentFilter);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.NetworkManager
 * JD-Core Version:    0.6.0
 */