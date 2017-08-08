package com.tencent.qqgamemi.plugin;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=7)
public abstract interface PluginUndealCountManagerProxy
{
  @PluginApi(a=7)
  public abstract void clearUndealCount();

  @PluginApi(a=7)
  public abstract void setUndealCount(int paramInt, boolean paramBoolean);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginUndealCountManagerProxy
 * JD-Core Version:    0.6.0
 */