package com.tencent.msdk.push;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import com.tencent.msdk.config.ConfigManager;
import com.tencent.msdk.tools.Logger;
import com.tencent.msdk.tools.T;

public class HttpPushService extends Service
{
  public static final String PUSH_SERVICE_PROC_NAME = ".msdk_push_v_1";
  public static Context sHttpPushServiceContext;
  public static int sLastPollingInterval = 600000;
  public static PendingIntent sPendingIntent;
  public final IBinder mBinder = new LocalBinder();

  static
  {
    sHttpPushServiceContext = null;
  }

  public HttpPushService()
  {
    Logger.d("called");
  }

  private void startPoll(Context paramContext)
  {
    Logger.d("startPoll");
    AlarmManager localAlarmManager = (AlarmManager)paramContext.getSystemService("alarm");
    Intent localIntent = new Intent(paramContext, AlarmReveiver.class);
    if (sPendingIntent == null)
      sPendingIntent = PendingIntent.getBroadcast(paramContext, 8888, localIntent, 134217728);
    long l = 15000L + SystemClock.elapsedRealtime();
    int i = 600000;
    String str = ConfigManager.getConfigedPollingInterval();
    if (!T.ckIsEmpty(str));
    while (true)
    {
      try
      {
        i = Integer.parseInt(str);
        Logger.d("use test interval");
        Logger.d("PUSH_DEAULT_POLLING_INTERVAL: " + i);
        localAlarmManager.setRepeating(3, l, i, sPendingIntent);
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        Logger.d("no test polling interval");
        localNumberFormatException.printStackTrace();
        continue;
      }
      Logger.d("no test polling interval");
    }
  }

  public IBinder onBind(Intent paramIntent)
  {
    Logger.d("onBind");
    return this.mBinder;
  }

  public void onCreate()
  {
    Logger.d("called");
    sHttpPushServiceContext = this;
    super.onCreate();
  }

  public void onDestroy()
  {
    Logger.d("called");
    super.onDestroy();
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    Logger.d(getApplication().getPackageName() + "called " + (int)System.currentTimeMillis() / 1000);
    startPoll(this);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  public boolean onUnbind(Intent paramIntent)
  {
    Logger.d("called");
    return super.onUnbind(paramIntent);
  }

  public class LocalBinder extends Binder
  {
    public LocalBinder()
    {
    }

    public HttpPushService getService()
    {
      return HttpPushService.this;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.push.HttpPushService
 * JD-Core Version:    0.6.0
 */