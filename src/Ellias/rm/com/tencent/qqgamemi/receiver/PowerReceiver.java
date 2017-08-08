package com.tencent.qqgamemi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.TLog;

public class PowerReceiver extends BroadcastReceiver
{
  private static final String a = "PowerReceiver";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    new Intent().setClass(paramContext, QMiService.class);
    if (str.equals("android.intent.action.SCREEN_ON"))
    {
      TLog.c("PowerReceiver", "SCREEN ON");
      QMiService.a(paramContext, 160);
    }
    do
      return;
    while (!str.equals("android.intent.action.SCREEN_OFF"));
    TLog.c("PowerReceiver", "SCREEN OFF");
    QMiService.a(paramContext, 161);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.PowerReceiver
 * JD-Core Version:    0.6.0
 */