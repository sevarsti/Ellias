package com.tencent.component.utils.clock;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface OnClockListener
{
  @PluginApi(a=6)
  public abstract boolean onClockArrived(Clock paramClock);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.clock.OnClockListener
 * JD-Core Version:    0.6.0
 */