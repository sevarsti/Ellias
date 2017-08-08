package com.tencent.component.event;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public enum Event$EventRank
{
  static
  {
    CORE = new EventRank("CORE", 2);
    EventRank[] arrayOfEventRank = new EventRank[3];
    arrayOfEventRank[0] = NORMAL;
    arrayOfEventRank[1] = SYSTEM;
    arrayOfEventRank[2] = CORE;
    $VALUES = arrayOfEventRank;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.Event.EventRank
 * JD-Core Version:    0.6.0
 */