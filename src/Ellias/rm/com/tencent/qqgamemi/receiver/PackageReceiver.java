package com.tencent.qqgamemi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.TLog;

public class PackageReceiver extends BroadcastReceiver
{
  private static final String a = "PackageReceiver";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    String str2 = paramIntent.getDataString();
    TLog.c("PackageReceiver", "onReceive " + str1 + "," + str2);
    if ("android.intent.action.PACKAGE_ADDED".equals(str1))
      if ((str2 != null) && (str2.contains("com.tencent.gamejoy")))
      {
        TLog.c("PackageReceiver", "com.tencent.gamejoy is install");
        QMiService.a(paramContext, 210);
      }
    do
      return;
    while ((!"android.intent.action.PACKAGE_REMOVED".equals(str1)) || (str2 == null) || (!str2.contains("com.tencent.gamejoy")));
    TLog.c("PackageReceiver", "com.tencent.gamejoy is uninstall");
    QMiService.a(paramContext, 211);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.PackageReceiver
 * JD-Core Version:    0.6.0
 */