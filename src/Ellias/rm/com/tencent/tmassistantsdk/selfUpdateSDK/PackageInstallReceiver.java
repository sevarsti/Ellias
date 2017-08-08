package com.tencent.tmassistantsdk.selfUpdateSDK;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.Iterator;

public class PackageInstallReceiver extends BroadcastReceiver
{
  private static PackageInstallReceiver d = null;
  protected final String a = PackageInstallReceiver.class.getName();
  protected boolean b = false;
  ArrayList c = new ArrayList();
  private final Handler e = new a(this);

  public static PackageInstallReceiver a()
  {
    monitorenter;
    try
    {
      if (d == null)
        d = new PackageInstallReceiver();
      PackageInstallReceiver localPackageInstallReceiver = d;
      return localPackageInstallReceiver;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void a(String paramString, int paramInt)
  {
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
      ((b)localIterator.next()).a(paramString, paramInt);
  }

  public void a(Context paramContext)
  {
    if (paramContext != null)
    {
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addDataScheme("package");
      localIntentFilter.addAction("android.intent.action.PACKAGE_ADDED");
      localIntentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
      localIntentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
      paramContext.registerReceiver(this, localIntentFilter);
      this.b = true;
    }
  }

  public void a(b paramb)
  {
    if ((!this.c.contains(paramb)) && (paramb != null))
      this.c.add(paramb);
  }

  public void b(Context paramContext)
  {
    if (paramContext == null);
    do
      return;
    while (!this.b);
    paramContext.unregisterReceiver(this);
    this.b = false;
  }

  public void b(b paramb)
  {
    if (paramb != null)
      this.c.remove(paramb);
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    l.c(this.a, "onReceive >> " + paramIntent.getAction());
    String str1 = paramIntent.getDataString();
    if (TextUtils.isEmpty(str1))
    {
      l.e(this.a, "onReceive intentPkgNameString == null ");
      return;
    }
    String[] arrayOfString = str1.split(":");
    Message localMessage;
    if (arrayOfString.length == 2)
    {
      String str2 = arrayOfString[1];
      localMessage = Message.obtain();
      localMessage.obj = str2;
      if (!paramIntent.getAction().equals("android.intent.action.PACKAGE_REMOVED"))
        break label169;
      l.c(this.a, "ACTION_PACKAGE_REMOVED >> " + str1);
      localMessage.what = 2;
    }
    while (true)
    {
      this.e.sendMessage(localMessage);
      return;
      l.e(this.a, "onReceive packageName == null " + paramIntent.getDataString());
      return;
      label169: if (paramIntent.getAction().equals("android.intent.action.PACKAGE_REPLACED"))
      {
        l.c(this.a, "ACTION_PACKAGE_REPLACED >> " + str1);
        localMessage.what = 3;
        continue;
      }
      if (!paramIntent.getAction().equals("android.intent.action.PACKAGE_ADDED"))
        continue;
      l.c(this.a, "ACTION_PACKAGE_ADDED >> " + str1);
      localMessage.what = 1;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.selfUpdateSDK.PackageInstallReceiver
 * JD-Core Version:    0.6.0
 */