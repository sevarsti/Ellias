package com.tencent.qqgamemi.plugin;

import java.io.Serializable;

public class PluginOrder
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  public boolean isEnable;
  public String pluginName;

  public PluginOrder(String paramString, boolean paramBoolean)
  {
    this.pluginName = paramString;
    this.isEnable = paramBoolean;
  }

  public String toString()
  {
    return "[" + this.pluginName + "," + this.isEnable + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginOrder
 * JD-Core Version:    0.6.0
 */