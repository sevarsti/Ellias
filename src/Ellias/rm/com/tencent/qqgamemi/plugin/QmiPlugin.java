package com.tencent.qqgamemi.plugin;

import com.tencent.component.annotation.PluginApi;
import com.tencent.component.plugin.Plugin;
import com.tencent.component.utils.log.LogUtil;
import com.tencent.qqgamemi.plugin.ui.IFloatViewManager;

@PluginApi(a=7)
public abstract class QmiPlugin extends Plugin
{
  private PluginUndealCountManagerProxy a = new i(this);

  @PluginApi(a=7)
  public abstract IFloatViewManager getPluginFloatViewManager();

  @PluginApi(a=7)
  public final PluginUndealCountManagerProxy getPluginUndealCountManager()
  {
    return this.a;
  }

  public void onBusinessLifeCycle(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default:
      return;
    case 1:
    }
    onGameResume((String)paramObject);
  }

  @PluginApi(a=7)
  protected void onGameResume(String paramString)
  {
    LogUtil.d("QmiPlugin", "onGameResume:" + paramString);
  }

  @PluginApi(a=7)
  public abstract void registerFloatViewLifecycleCallbacks(QmiPlugin.FloatViewLifecycleCallbacks paramFloatViewLifecycleCallbacks);

  @PluginApi(a=7)
  public abstract void registerFloatViewTaskLifecycleCallbacks(QmiPlugin.FloatViewTaskLifecycleCallbacks paramFloatViewTaskLifecycleCallbacks);

  @PluginApi(a=7)
  public abstract void unregisterFloatViewLifecycleCallbacks(QmiPlugin.FloatViewLifecycleCallbacks paramFloatViewLifecycleCallbacks);

  @PluginApi(a=7)
  public abstract void unregisterFloatViewTaskLifecycleCallbacks(QmiPlugin.FloatViewTaskLifecycleCallbacks paramFloatViewTaskLifecycleCallbacks);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.QmiPlugin
 * JD-Core Version:    0.6.0
 */