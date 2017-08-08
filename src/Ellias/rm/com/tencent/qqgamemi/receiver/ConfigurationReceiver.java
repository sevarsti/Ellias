package com.tencent.qqgamemi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.QMiService;
import com.tencent.qqgamemi.common.TLog;

public class ConfigurationReceiver extends BroadcastReceiver
{
  private static final String a = "ConfigurationReceiver";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED"))
    {
      TLog.c("ConfigurationReceiver", "Configuration changed");
      QMiService.a(paramContext, 180);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.ConfigurationReceiver
 * JD-Core Version:    0.6.0
 */