package com.tencent.qqgamemi.plugin;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=7)
public abstract interface QmiPlugin$FloatViewLifecycleCallbacks
{
  @PluginApi(a=7)
  public abstract void onFLoatViewCreated(Object paramObject);

  @PluginApi(a=7)
  public abstract void onFloatViewDestroyed(Object paramObject);

  @PluginApi(a=7)
  public abstract void onFloatViewStarted(Object paramObject);

  @PluginApi(a=7)
  public abstract void onFloatViewStopped(Object paramObject);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.QmiPlugin.FloatViewLifecycleCallbacks
 * JD-Core Version:    0.6.0
 */