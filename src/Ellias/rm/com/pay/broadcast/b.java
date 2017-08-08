package com.pay.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

final class b extends BroadcastReceiver
{
  b(HomeWatcher paramHomeWatcher)
  {
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    String str;
    if (paramIntent.getAction().equals("android.intent.action.CLOSE_SYSTEM_DIALOGS"))
    {
      str = paramIntent.getStringExtra("reason");
      if ((str != null) && (HomeWatcher.a(this.a) != null))
      {
        if (!str.equals("homekey"))
          break label55;
        HomeWatcher.a(this.a).onHomePressed();
      }
    }
    label55: 
    do
      return;
    while (!str.equals("recentapps"));
    HomeWatcher.a(this.a).onHomeLongPressed();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.broadcast.b
 * JD-Core Version:    0.6.0
 */