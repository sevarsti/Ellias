package com.tencent.qqgamemi.plugin;

import com.tencent.component.db.annotation.Column;
import com.tencent.component.db.annotation.Id;
import com.tencent.component.db.annotation.Table;

@Table(name="PluginOnlineTable", version=1)
public class PluginOnline
{

  @Id(strategy=1)
  String gamePkg;

  @Column
  boolean isGetOnLineReady;

  public PluginOnline()
  {
  }

  public PluginOnline(String paramString, boolean paramBoolean)
  {
    this.gamePkg = paramString;
    this.isGetOnLineReady = paramBoolean;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.PluginOnline
 * JD-Core Version:    0.6.0
 */