package com.tencent.qqgamemi.business;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.QMiConfig;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.receiver.AlarmReceiver;
import com.tencent.qqgamemi.receiver.ConfigurationReceiver;
import com.tencent.qqgamemi.receiver.PackageReceiver;
import com.tencent.qqgamemi.receiver.PowerReceiver;

public class ReceiverRegisterHelper
{
  private static final String a = ReceiverRegisterHelper.class.getSimpleName();
  private static final long f = 10000L;
  private Context b;
  private ConfigurationReceiver c = null;
  private boolean d = false;
  private PowerReceiver e = null;
  private AlarmReceiver g = null;
  private PackageReceiver h = null;

  public ReceiverRegisterHelper(Context paramContext)
  {
    this.b = paramContext;
  }

  public void a()
  {
    this.c = new ConfigurationReceiver();
    IntentFilter localIntentFilter1 = new IntentFilter();
    localIntentFilter1.addAction("android.intent.action.CONFIGURATION_CHANGED");
    this.b.registerReceiver(this.c, localIntentFilter1);
    TLog.c(a, "register configReceiver");
    this.e = new PowerReceiver();
    IntentFilter localIntentFilter2 = new IntentFilter();
    localIntentFilter2.addAction("android.intent.action.SCREEN_ON");
    localIntentFilter2.addAction("android.intent.action.SCREEN_OFF");
    this.b.registerReceiver(this.e, localIntentFilter2);
    TLog.c(a, "register powerReceiver");
    if (QMiConfig.b())
    {
      this.g = new AlarmReceiver();
      IntentFilter localIntentFilter3 = new IntentFilter();
      localIntentFilter3.addAction("com.tencent.qqgamemi.alarm.action");
      this.b.registerReceiver(this.g, localIntentFilter3);
      TLog.c(a, "register alarmReceiver");
      this.h = new PackageReceiver();
      IntentFilter localIntentFilter4 = new IntentFilter();
      localIntentFilter4.addAction("android.intent.action.PACKAGE_ADDED");
      localIntentFilter4.addAction("android.intent.action.PACKAGE_REMOVED");
      localIntentFilter4.addDataScheme("package");
      this.b.registerReceiver(this.h, localIntentFilter4);
      TLog.c(a, "register packageReceiver");
    }
  }

  public void b()
  {
    this.b.unregisterReceiver(this.c);
    this.b.unregisterReceiver(this.e);
    if (QMiConfig.b())
    {
      this.b.unregisterReceiver(this.g);
      this.b.unregisterReceiver(this.h);
    }
  }

  public void c()
  {
    if (!QMiConfig.b())
    {
      TLog.c(a, "startAlarmRepeat");
      Intent localIntent = new Intent(this.b, AlarmReceiver.class);
      localIntent.setAction("com.tencent.qqgamemi.alarm.action");
      PendingIntent localPendingIntent = PendingIntent.getBroadcast(this.b, 0, localIntent, 0);
      ((AlarmManager)this.b.getSystemService("alarm")).set(1, 10000L + System.currentTimeMillis(), localPendingIntent);
    }
  }

  public void d()
  {
    if (!QMiConfig.b())
    {
      if (!this.d)
        QMiCommon.showQMi(this.b);
      c();
    }
  }

  public void e()
  {
    this.d = false;
    QMiCommon.showQMi(this.b);
  }

  public void f()
  {
    this.d = true;
    QMiCommon.hideQMi(this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.ReceiverRegisterHelper
 * JD-Core Version:    0.6.0
 */