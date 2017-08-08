package com.tencent.qqgamemi.plugin;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=7)
public abstract interface QmiPlugin$FloatViewTaskLifecycleCallbacks
{
  @PluginApi(a=7)
  public abstract void onBroughtTaskToFront();

  @PluginApi(a=7)
  public abstract void onMoveTaskToBack();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.QmiPlugin.FloatViewTaskLifecycleCallbacks
 * JD-Core Version:    0.6.0
 */