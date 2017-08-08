package com.tencent.component.ui.widget.pulltorefresh;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public enum PullToRefreshBase$Mode
{
  protected int mIntValue;

  static
  {
    BOTH = new Mode("BOTH", 3, 3);
    PULL_TO_SCROLL = new Mode("PULL_TO_SCROLL", 4, 4);
    Mode[] arrayOfMode = new Mode[5];
    arrayOfMode[0] = DISABLED;
    arrayOfMode[1] = PULL_DOWN_TO_REFRESH;
    arrayOfMode[2] = PULL_UP_TO_REFRESH;
    arrayOfMode[3] = BOTH;
    arrayOfMode[4] = PULL_TO_SCROLL;
    $VALUES = arrayOfMode;
  }

  private PullToRefreshBase$Mode(int arg3)
  {
    int j;
    this.mIntValue = j;
  }

  @PluginApi(a=6)
  public static Mode mapIntToMode(int paramInt)
  {
    switch (paramInt)
    {
    case 1:
    default:
      return PULL_DOWN_TO_REFRESH;
    case 0:
      return DISABLED;
    case 2:
      return PULL_UP_TO_REFRESH;
    case 3:
      return BOTH;
    case 4:
    }
    return PULL_DOWN_TO_REFRESH;
  }

  @PluginApi(a=6)
  public boolean canPullDown()
  {
    return (this == PULL_DOWN_TO_REFRESH) || (this == BOTH) || (this == PULL_TO_SCROLL);
  }

  @PluginApi(a=6)
  public boolean canPullUp()
  {
    return (this == PULL_UP_TO_REFRESH) || (this == BOTH) || (this == PULL_TO_SCROLL);
  }

  @PluginApi(a=6)
  public int getIntValue()
  {
    return this.mIntValue;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase.Mode
 * JD-Core Version:    0.6.0
 */