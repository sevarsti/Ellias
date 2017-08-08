package com.tencent.component.plugin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

class z extends BroadcastReceiver
{
  z(PluginManager paramPluginManager)
  {
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("platform_id");
    if ((!TextUtils.isEmpty(str1)) && (str1.equals(PluginManager.g(this.a))))
    {
      if (!"plugin_manager_plugin_created".equals(paramIntent.getAction()))
        break label66;
      String str3 = paramIntent.getStringExtra("plugin_id");
      if (PluginHelper.a(str3))
        PluginManager.b(this.a, str3);
    }
    label66: String str2;
    int i;
    int j;
    do
    {
      do
        return;
      while (!"plugin_manager_plugin_changed".equals(paramIntent.getAction()));
      str2 = paramIntent.getStringExtra("plugin_id");
      i = paramIntent.getIntExtra("plugin_change", 0);
      j = paramIntent.getIntExtra("plugin_status", 0);
    }
    while (!PluginHelper.a(str2));
    if (((i & 0x1) != 0) && ((j & 0x1) == 0))
      PluginManager.a(this.a, str2);
    PluginManager.a(this.a, str2, i, j);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.plugin.z
 * JD-Core Version:    0.6.0
 */