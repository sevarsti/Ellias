package com.tencent.component.plugin.server;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public final class PluginService extends Service
{
  private static final String a = "com.tencent.component.plugin.server.PluginService.";
  private static final String b = "platformId";
  private PluginServiceLogic c;

  public static void a(Context paramContext, ServiceConnection paramServiceConnection, String paramString)
  {
    Intent localIntent = new Intent(paramContext, PluginService.class);
    localIntent.putExtra("platformId", paramString);
    paramContext.bindService(localIntent, paramServiceConnection, 1);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.c.a(paramIntent);
  }

  public void onCreate()
  {
    super.onCreate();
    this.c = new PluginServiceLogic(getApplicationContext());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.PluginService
 * JD-Core Version:    0.6.0
 */