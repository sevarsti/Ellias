package com.tencent.tmassistantsdk.openSDK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.HandlerThread;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;

public class DownloadStateChangedReceiver extends BroadcastReceiver
{
  protected static DownloadStateChangedReceiver a = null;
  protected HandlerThread b = new HandlerThread("downloadStateChangedThread");
  protected Handler c = null;
  protected boolean d = false;
  ArrayList e = new ArrayList();

  public DownloadStateChangedReceiver()
  {
    this.b.start();
    this.c = new Handler(this.b.getLooper());
  }

  public static DownloadStateChangedReceiver a()
  {
    monitorenter;
    try
    {
      if (a == null)
        a = new DownloadStateChangedReceiver();
      DownloadStateChangedReceiver localDownloadStateChangedReceiver = a;
      return localDownloadStateChangedReceiver;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void a(Context paramContext)
  {
    IntentFilter localIntentFilter;
    if (!this.d)
    {
      l.b("DownloadStateChangedReceiver", "registeReceiver   context" + paramContext + "  receiver:" + this);
      localIntentFilter = new IntentFilter("com.tencent.assistantOpenSDK.downloadStateChange.action");
    }
    try
    {
      Intent localIntent = paramContext.registerReceiver(this, localIntentFilter);
      l.b("DownloadStateChangedReceiver", "" + localIntent);
      this.d = true;
      return;
    }
    catch (Throwable localThrowable)
    {
      l.b("DownloadStateChangedReceiver", "registeReceiver exception!!!");
      this.d = false;
      localThrowable.printStackTrace();
    }
  }

  public void a(c paramc)
  {
    if ((paramc != null) && (!this.e.contains(paramc)))
      this.e.add(paramc);
  }

  public void b(Context paramContext)
  {
    if ((paramContext == null) || (a == null));
    do
      return;
    while (!this.d);
    l.b("DownloadStateChangedReceiver", "realy unRegisteReceiver  context:" + paramContext + "  receiver:" + this);
    try
    {
      paramContext.unregisterReceiver(this);
      this.d = false;
      return;
    }
    catch (Throwable localThrowable)
    {
      l.b("DownloadStateChangedReceiver", "unRegisteReceiver exception!!!");
      this.d = false;
      localThrowable.printStackTrace();
    }
  }

  public void b(c paramc)
  {
    if (paramc != null)
      this.e.remove(paramc);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (this.c != null)
      this.c.post(new b(this, paramIntent));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.openSDK.DownloadStateChangedReceiver
 * JD-Core Version:    0.6.0
 */