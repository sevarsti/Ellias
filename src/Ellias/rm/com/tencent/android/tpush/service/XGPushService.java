package com.tencent.android.tpush.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;
import com.tencent.android.tpush.horse.Tools;
import com.tencent.android.tpush.logging.TLog;
import com.tencent.android.tpush.service.c.c;
import com.tencent.android.tpush.service.channel.security.TpnsSecurity;

public class XGPushService extends Service
{
  private void a()
  {
    int i = 1;
    TLog.v("XGService", "@@ initDebug()");
    TLog.init(getApplicationContext());
    if (c.b(getApplicationContext(), "com.tencent.android.tpush.debug," + getApplicationContext().getPackageName(), 0) == i);
    while (true)
    {
      TLog.enable(i);
      return;
      int j = 0;
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    i.a(getApplicationContext());
    a();
    TLog.v("XGService", "@@ onCreate():" + getPackageName());
    i.a().b();
  }

  public void onDestroy()
  {
    TLog.w("XGService", "@@ onDestroy()");
    i.a().c();
    TLog.stop();
    Tools.saveCurStrategyItem(i.e(), null);
    Process.killProcess(Process.myPid());
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
    TLog.v("XGService", "@@ onStart(" + paramIntent + "," + paramInt + ")");
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (!TpnsSecurity.checkTpnsSecurityLibSo(getApplicationContext()))
    {
      Log.e("XGService", "tpnsSecurity load failed, kill xg service.");
      Process.killProcess(Process.myPid());
      return 2;
    }
    a();
    TLog.v("XGService", "@@ onStartCommand(" + paramIntent + "," + paramInt1 + "," + paramInt2 + ")");
    if (paramIntent == null)
    {
      TLog.i("TPush", ">> intent is null, create one.");
      paramIntent = new Intent(getApplicationContext(), XGPushService.class);
    }
    i.a().a(paramIntent);
    return 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.android.tpush.service.XGPushService
 * JD-Core Version:    0.6.0
 */