package com.tencent.component.utils.clock;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=6)
public abstract class Clock
{
  private long a = 10000L;
  private int b = -1;
  private OnClockListener c;

  protected Clock(int paramInt, long paramLong, OnClockListener paramOnClockListener)
  {
    a(paramLong);
    a(paramInt);
    a(paramOnClockListener);
  }

  private void a(int paramInt)
  {
    this.b = paramInt;
  }

  private void a(OnClockListener paramOnClockListener)
  {
    this.c = paramOnClockListener;
  }

  public long a()
  {
    return this.a;
  }

  public void a(long paramLong)
  {
    this.a = paramLong;
  }

  public int b()
  {
    return this.b;
  }

  public OnClockListener c()
  {
    return this.c;
  }

  public abstract void cancel();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.clock.Clock
 * JD-Core Version:    0.6.0
 */