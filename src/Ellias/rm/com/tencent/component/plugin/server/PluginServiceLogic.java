package com.tencent.component.plugin.server;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.text.TextUtils;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.component.utils.thread.ThreadPool;

public final class PluginServiceLogic
{
  private static final String a = "PlguinService";
  private static final String b = "com.tencent.component.plugin.server.PluginService";
  private static final String c = "platformId";
  private Context d;
  private ThreadPool e;

  public PluginServiceLogic(Context paramContext)
  {
    this.d = paramContext;
    this.e = new ThreadPool("plugin-server-pool", 1, 1);
  }

  public static void a(Context paramContext, ServiceConnection paramServiceConnection, String paramString)
  {
    Intent localIntent = new Intent("com.tencent.component.plugin.server.PluginService");
    localIntent.putExtra("platformId", paramString);
    paramContext.bindService(localIntent, paramServiceConnection, 1);
  }

  public IBinder a(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("platformId");
    if (TextUtils.isEmpty(str))
    {
      LogUtil.e("PlguinService", "Illeagal bind request as platformId is empty!");
      return null;
    }
    return new k(this.d, str, this.e);
  }

  public void a()
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.server.PluginServiceLogic
 * JD-Core Version:    0.6.0
 */