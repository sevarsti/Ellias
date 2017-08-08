package com.tencent.android.tpush.service;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.LocalServerSocket;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.a.b;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class i
{
  private static Context a = null;
  private static LocalServerSocket b = null;
  private static volatile boolean d = false;
  private static volatile boolean e = false;
  private Handler c = null;

  public static i a()
  {
    return k.a;
  }

  public static void a(Context paramContext)
  {
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ setContext(" + paramContext.getPackageName() + ")");
      a = paramContext;
    }
  }

  public static void a(Context paramContext, Intent paramIntent)
  {
    Intent localIntent;
    if (paramContext != null)
    {
      TLog.v("XGService", "@@ startService(" + paramContext.getPackageName() + ")");
      String str = "";
      boolean bool = false;
      if (paramIntent != null)
      {
        bool = paramIntent.getBooleanExtra("com.tencent.android.tpush.action.START_SELF_FIRST", false);
        str = paramIntent.getStringExtra("com.tencent.android.tpush.action.START_SELF_FIRST_SOURCE");
      }
      localIntent = new Intent();
      localIntent.putExtra("com.tencent.android.tpush.action.START_SELF_FIRST", bool);
      localIntent.putExtra("com.tencent.android.tpush.action.START_SELF_FIRST_SOURCE", str);
      localIntent.setClass(paramContext, XGPushService.class);
      if (TpnsSecurity.checkTpnsSecurityLibSo(paramContext))
        paramContext.startService(localIntent);
    }
    else
    {
      return;
    }
    Log.e("XGService", "startService failed, libtpnsSecurity.so not found.");
    paramContext.stopService(localIntent);
  }

  public static Context e()
  {
    return a;
  }

  private boolean h()
  {
    while (true)
    {
      Object localObject2;
      try
      {
        TLog.v("XGService", "@@ isSurvive()");
        List localList1 = com.tencent.android.tpush.service.c.c.a(a);
        if ((localList1 != null) && (localList1.size() >= 2))
          continue;
        TLog.i("XGService", ">> Just one app with push sdk found in this device:" + a.getPackageName());
        break label250;
        com.tencent.android.tpush.service.a.c localc = b.a(a);
        List localList2 = ((ActivityManager)a.getSystemService("activity")).getRunningServices(2147483647);
        localObject1 = null;
        if (localList2 == null)
          continue;
        int i = localList2.size();
        localObject1 = null;
        if (i <= 0)
          continue;
        String str = XGPushService.class.getName();
        Iterator localIterator = localList2.iterator();
        if (!localIterator.hasNext())
          continue;
        ActivityManager.RunningServiceInfo localRunningServiceInfo = (ActivityManager.RunningServiceInfo)localIterator.next();
        if (!str.equals(localRunningServiceInfo.service.getClassName()))
          continue;
        localObject2 = b.a(a, localRunningServiceInfo.service.getPackageName());
        if (localObject1 == null)
          break label252;
        if (((com.tencent.android.tpush.service.a.c)localObject2).a <= localObject1.a)
          continue;
        break label252;
        if (localObject1 != null)
        {
          float f1 = localObject1.a;
          float f2 = localc.a;
          if (f1 > f2)
            return false;
        }
      }
      catch (Exception localException)
      {
        TLog.e("XGService", localException.toString());
        break label250;
        localObject2 = localObject1;
      }
      label250: return true;
      label252: Object localObject1 = localObject2;
    }
  }

  private boolean i()
  {
    TLog.v("XGService", "@@ tryToKeepServiceAlive(" + a.getPackageName() + ")");
    boolean bool = h();
    TLog.i("XGService", ">> isSurvive():" + bool + ", isGetRunningToken=" + d);
    monitorenter;
    if (bool);
    try
    {
      String str = com.tencent.android.tpush.service.c.c.c(a, "com.tencent.android.tpush.socket.name");
      if (com.tencent.android.tpush.service.c.c.a(str))
      {
        str = com.tencent.android.tpush.service.c.c.a();
        com.tencent.android.tpush.service.c.c.a(a, "com.tencent.android.tpush.socket.name", str);
      }
      TLog.i("XGService", ">> socket=" + str + " @" + a.getPackageName());
      b = new LocalServerSocket(str);
      d = Boolean.valueOf(true).booleanValue();
      TLog.i("XGService", ">> Socket created, get token success to survive. @" + a.getPackageName());
      return bool;
    }
    catch (Throwable localThrowable)
    {
      while (true)
      {
        TLog.e("XGService", ">> Address in use already @" + a.getPackageName());
        bool = d;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void j()
  {
    TLog.v("XGService", "@@ initHandler()");
    this.c = new j(this, Looper.getMainLooper());
  }

  public void a(Intent paramIntent)
  {
    long l1 = 1100L;
    TLog.v("XGService", "@@ serviceStartHandler(" + paramIntent + ")");
    if (this.c == null)
      j();
    monitorenter;
    while (true)
    {
      long l2;
      try
      {
        if ((!d) || (b == null))
          continue;
        TLog.i("XGService", ">> [service is running?]:true");
        return;
        monitorexit;
        l2 = 0L;
        List localList = com.tencent.android.tpush.service.c.c.a(a);
        if ((localList != null) && (localList.size() > 1))
        {
          l2 = 1000 + (int)(1000.0D * Math.random());
          if (l2 < l1)
          {
            TLog.i("XGService", ">> delay millis:" + l1 + " @" + a.getPackageName());
            Message localMessage = this.c.obtainMessage(1, paramIntent);
            this.c.sendMessageDelayed(localMessage, l1);
            return;
          }
        }
      }
      finally
      {
        monitorexit;
      }
      l1 = l2;
    }
  }

  public void b()
  {
    TLog.v("XGService", "@@ initApplication()");
    b.a(a, new com.tencent.android.tpush.service.a.c(2.3F, 0));
  }

  public void c()
  {
    TLog.v("XGService", "@@ serviceExit()");
    if (this.c != null)
    {
      this.c.removeCallbacksAndMessages(null);
      this.c = null;
    }
    if (com.tencent.android.tpush.common.c.a().b() != null)
      com.tencent.android.tpush.common.c.a().b().removeCallbacksAndMessages(null);
    a.a().b(a);
    d();
  }

  public void d()
  {
    TLog.v("XGService", "@@ closeLocalSocket()");
    monitorenter;
    try
    {
      LocalServerSocket localLocalServerSocket = b;
      if (localLocalServerSocket != null);
      try
      {
        b.close();
        b = null;
        d = Boolean.valueOf(false).booleanValue();
        monitorexit;
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          TLog.e("XGService", ">> Destroy local socket exception", localIOException);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.i
 * JD-Core Version:    0.6.0
 */