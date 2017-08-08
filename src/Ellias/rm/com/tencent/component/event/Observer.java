package com.tencent.component.event;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract interface Observer
{
  @PluginApi(a=6)
  public abstract void onNotify(Event paramEvent);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.Observer
 * JD-Core Version:    0.6.0
 */