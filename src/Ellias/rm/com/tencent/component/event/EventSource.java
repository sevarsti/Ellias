package com.tencent.component.event;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public class EventSource
{

  @PluginApi(a=6)
  public String name;

  @PluginApi(a=6)
  public Object sender;

  @PluginApi(a=6)
  public EventSource(Class paramClass, Object paramObject)
  {
    this(paramClass.getName(), paramObject);
  }

  @PluginApi(a=6)
  public EventSource(String paramString)
  {
    this(paramString, null);
  }

  @PluginApi(a=6)
  public EventSource(String paramString, Object paramObject)
  {
    if ((paramString == null) || (paramString.length() == 0))
      throw new NullPointerException("The mEventName of EventSource cannot be empty");
    this.sender = paramObject;
    this.name = paramString;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    EventSource localEventSource;
    do
      while (true)
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localEventSource = (EventSource)paramObject;
        if (this.name != null)
          break;
        if (localEventSource.name != null)
          return false;
      }
    while (this.name.equals(localEventSource.name));
    return false;
  }

  public int hashCode()
  {
    if (this.name == null);
    for (int i = 0; ; i = this.name.hashCode())
      return i + 31;
  }

  public String toString()
  {
    return "EventSource [mName=" + this.name + ", mSender=" + this.sender + "]";
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.event.EventSource
 * JD-Core Version:    0.6.0
 */