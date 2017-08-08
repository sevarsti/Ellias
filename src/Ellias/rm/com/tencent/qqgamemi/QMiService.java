package com.tencent.qqgamemi;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.IBinder;
import android.util.Log;
import com.tencent.component.utils.ResourceUtil;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.ui.EnvironmentSelectDialog;
import com.tencent.qqgamemi.ui.EnvironmentSelectDialog.Builder;

public class QMiService extends Service
{
  public static final String a = "QMiService";
  public static boolean b = false;
  private static Service d;
  private QMiServiceLogic c;

  public static Service a()
  {
    return d;
  }

  public static String a(Context paramContext)
  {
    return "com.tencent.qqgamemi.QMiService." + paramContext.getApplicationContext().getPackageName();
  }

  public static void a(Service paramService)
  {
    d = paramService;
  }

  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent();
    localIntent.setClass(paramContext, QMiService.class);
    localIntent.putExtra("operation", paramInt);
    try
    {
      paramContext.startService(localIntent);
      return;
    }
    catch (Throwable localThrowable)
    {
      Log.e("QMiService", "startQMiService", localThrowable);
    }
  }

  private boolean b()
  {
    boolean bool = getSharedPreferences("FirstRun", 0).getBoolean("isFirstRun", true);
    TLog.c("QMiService", "isFirstRun:" + bool);
    return bool;
  }

  private void c()
  {
    TLog.c("QMiService", "setFirstRun");
    SharedPreferences.Editor localEditor = getSharedPreferences("FirstRun", 2).edit();
    localEditor.putBoolean("isFirstRun", false);
    localEditor.commit();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    Log.i("QMiService", "QmiService onCreate.");
    d = this;
    ResourceUtil.a(getApplicationContext());
    this.c = new QMiServiceLogic(this);
    this.c.b();
    if ((QMiConfig.a()) && (b()))
    {
      Log.i("QMiService", "EnvironmentSelectDialog show");
      b = true;
      new EnvironmentSelectDialog.Builder(this).a().show();
      c();
      return;
    }
    this.c.a();
    Log.i("QMiService", "QmiService onCreate finish.");
  }

  public void onDestroy()
  {
    super.onDestroy();
    if (!b)
      this.c.c();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
    if (!b)
    {
      Log.i("QMiService", "QmiService onStart .");
      this.c.a(paramIntent, paramInt);
      return;
    }
    Log.i("QMiService", "QmiService onStart is waitting");
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (!b)
      return this.c.a(paramIntent, paramInt1, paramInt2);
    Log.i("QMiService", "QmiService onStartCommand is waitting");
    return 1;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.QMiService
 * JD-Core Version:    0.6.0
 */