package com.tencent.qqgamemi.plugin.ui;

import com.tencent.qqgamemi.plugin.PluginItem;

public class PinnedItem
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 3;
  public int e;
  public PluginItem f;

  public PinnedItem(int paramInt, PluginItem paramPluginItem)
  {
    this.e = paramInt;
    this.f = paramPluginItem;
  }

  public void a()
  {
    this.e = 1;
  }

  public void b()
  {
    this.e = 3;
  }

  public boolean c()
  {
    return this.e == 1;
  }

  public boolean d()
  {
    return this.e == 3;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.PinnedItem
 * JD-Core Version:    0.6.0
 */