package com.tencent.component.ui.widget.pulltorefresh;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public enum PullToRefreshBase$Direction
{
  static
  {
    FROM_END = new Direction("FROM_END", 1);
    Direction[] arrayOfDirection = new Direction[2];
    arrayOfDirection[0] = FROM_START;
    arrayOfDirection[1] = FROM_END;
    $VALUES = arrayOfDirection;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.ui.widget.pulltorefresh.PullToRefreshBase.Direction
 * JD-Core Version:    0.6.0
 */