package com.tencent.component.event;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public final class Event
{
  private static final Object b = new Object();
  private static Event c;
  private static int d = 0;
  private static final int e = 50;
  Event a;

  @PluginApi(a=6)
  public Event.EventRank eventRank;
  private int f = 0;

  @PluginApi(a=6)
  public Object params;

  @PluginApi(a=6)
  public EventSource source;

  @PluginApi(a=6)
  public int what;

  static Event a()
  {
    return new Event();
  }

  public static Event a(int paramInt, EventSource paramEventSource)
  {
    return a(paramInt, paramEventSource, null, Event.EventRank.NORMAL);
  }

  static Event a(int paramInt, EventSource paramEventSource, Event.EventRank paramEventRank)
  {
    return a(paramInt, paramEventSource, null, paramEventRank);
  }

  static Event a(int paramInt, EventSource paramEventSource, Object paramObject, Event.EventRank paramEventRank)
  {
    Event localEvent = a();
    localEvent.what = paramInt;
    localEvent.source = paramEventSource;
    localEvent.params = paramObject;
    localEvent.eventRank = paramEventRank;
    localEvent.f = (1 + localEvent.f);
    return localEvent;
  }

  public void b()
  {
    int i = -1 + this.f;
    this.f = i;
    if (i <= 0)
    {
      c();
      synchronized (b)
      {
        if (d < 50)
        {
          this.a = c;
          c = this;
          d = 1 + d;
        }
        return;
      }
    }
  }

  void c()
  {
    this.what = 0;
    this.source = null;
    this.params = null;
    this.eventRank = null;
    this.f = 0;
  }

  public void d()
  {
    this.f = (1 + this.f);
  }

  public String toString()
  {
    return "Event [what=" + this.what + ", source=" + this.source + ", params=" + this.params + ", eventRank=" + this.eventRank + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.Event
 * JD-Core Version:    0.6.0
 */