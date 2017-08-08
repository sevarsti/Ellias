package com.tencent.qqgamemi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;

public class BootReceiver extends BroadcastReceiver
{
  private static final String a = "BootReceiver";

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.intent.action.BOOT_COMPLETED".equals(paramIntent.getAction()))
    {
      TLog.c("BootReceiver", "receiver ACTION_BOOT_COMPLETED");
      QMiCommon.showQMi(paramContext);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.BootReceiver
 * JD-Core Version:    0.6.0
 */