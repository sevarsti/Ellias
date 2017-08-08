package com.tencent.component.event;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class Observable
{
  private EventSource a;

  @PluginApi(a=6)
  public Observable()
  {
    this.a = new EventSource(getClass(), this);
  }

  @PluginApi(a=6)
  public Observable(String paramString)
  {
    this.a = new EventSource(paramString, this);
  }

  private void a(int paramInt, Event.EventRank paramEventRank, Object[] paramArrayOfObject)
  {
    EventCenter.getInstance().notify(this.a, paramInt, paramEventRank, paramArrayOfObject);
  }

  @PluginApi(a=6)
  protected EventSource getEventSource()
  {
    return this.a;
  }

  @PluginApi(a=6)
  protected void notify(Event paramEvent)
  {
    paramEvent.source = this.a;
    EventCenter.getInstance().notify(paramEvent);
  }

  @PluginApi(a=6)
  protected void notifyCore(int paramInt, Object[] paramArrayOfObject)
  {
    a(paramInt, Event.EventRank.CORE, paramArrayOfObject);
  }

  @PluginApi(a=6)
  protected void notifyNormal(int paramInt, Object[] paramArrayOfObject)
  {
    a(paramInt, Event.EventRank.NORMAL, paramArrayOfObject);
  }

  @PluginApi(a=6)
  protected void notifySystem(int paramInt, Object[] paramArrayOfObject)
  {
    a(paramInt, Event.EventRank.SYSTEM, paramArrayOfObject);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.Observable
 * JD-Core Version:    0.6.0
 */