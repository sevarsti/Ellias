package com.tencent.tmassistantsdk.downloadservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import com.tencent.tmassistantsdk.g.f;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.Iterator;

public class NetworkMonitorReceiver extends BroadcastReceiver
{
  protected static NetworkMonitorReceiver a = null;
  protected boolean b = false;
  protected final Handler c = new m(this);
  ArrayList d = new ArrayList();

  public static NetworkMonitorReceiver a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new NetworkMonitorReceiver();
      NetworkMonitorReceiver localNetworkMonitorReceiver = a;
      return localNetworkMonitorReceiver;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(n paramn)
  {
    if ((!this.d.contains(paramn)) && (paramn != null))
      this.d.add(paramn);
  }

  public void b()
  {
    Context localContext = f.a().b();
    if (localContext == null)
      return;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    try
    {
      localContext.registerReceiver(this, localIntentFilter);
      this.b = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      this.b = false;
      localThrowable.printStackTrace();
    }
  }

  public void b(n paramn)
  {
    if (paramn != null)
      this.d.remove(paramn);
  }

  public void c()
  {
    if (a == null);
    while (true)
    {
      return;
      Context localContext = f.a().b();
      if (localContext == null)
        continue;
      try
      {
        if (!this.b)
          continue;
        localContext.unregisterReceiver(this);
        this.b = false;
        return;
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
      }
    }
  }

  public void d()
  {
    Iterator localIterator = this.d.iterator();
    while (localIterator.hasNext())
      ((n)localIterator.next()).e();
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    l.b("NetworkMonitorReceiver", "network changed!");
    this.c.removeMessages(67);
    Message localMessage = Message.obtain();
    localMessage.what = 67;
    this.c.sendMessageDelayed(localMessage, 3500L);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.NetworkMonitorReceiver
 * JD-Core Version:    0.6.0
 */