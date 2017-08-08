package com.tencent.qqgamemi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.TLog;

public class AlarmReceiver extends BroadcastReceiver
{
  private static final String a = "AlarmReceiver";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("com.tencent.qqgamemi.alarm.action"))
    {
      TLog.c("AlarmReceiver", "receiver QQGAMEMI_ALARM_INTENT");
      QMiService.a(paramContext, 170);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.AlarmReceiver
 * JD-Core Version:    0.6.0
 */