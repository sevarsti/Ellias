package com.tencent.component.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class ab extends BroadcastReceiver
{
  ab(PluginManager paramPluginManager)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("platform_id");
    if ((!TextUtils.isEmpty(str)) && (str.equals(PluginManager.g(this.a))))
    {
      if (!"plugin_platform_initialize_start".equals(paramIntent.getAction()))
        break label48;
      PluginManager.i(this.a);
    }
    label48: 
    do
      return;
    while (!"plugin_platform_initialize_finish".equals(paramIntent.getAction()));
    PluginManager.j(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.ab
 * JD-Core Version:    0.6.0
 */