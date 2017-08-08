package com.tencent.qqgamemi.receiver;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tencent.qqgamemi.common.TLog;

public class HideReceiver extends BroadcastReceiver
{
  private static final String a = "HideReceiver";
  private Dialog b;

  public HideReceiver(Dialog paramDialog)
  {
    this.b = paramDialog;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("com.tencent.qqgamemi.hide.action"))
    {
      TLog.c("HideReceiver", "qmi hide");
      this.b.dismiss();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.receiver.HideReceiver
 * JD-Core Version:    0.6.0
 */