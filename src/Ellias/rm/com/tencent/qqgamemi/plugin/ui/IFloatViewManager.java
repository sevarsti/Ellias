package com.tencent.qqgamemi.plugin.ui;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=7)
public abstract interface IFloatViewManager
{
  @PluginApi(a=7)
  public abstract void broughtToFront();

  @PluginApi(a=7)
  public abstract void finishAllFloatViews();

  @PluginApi(a=7)
  public abstract int getFloatViewSize();

  @PluginApi(a=7)
  public abstract int getVisibleFloatViewSize();

  @PluginApi(a=7)
  public abstract boolean isFront();

  @PluginApi(a=7)
  public abstract void moveTaskToBack();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.IFloatViewManager
 * JD-Core Version:    0.6.0
 */