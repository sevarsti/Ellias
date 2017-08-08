package com.tencent.msdk.timer.task;

import java.util.TimerTask;

public abstract class BaseTask extends TimerTask
{
  private int notifiedTimes = 0;

  public abstract int getMyInterval();

  public final int getNotifiedTimes()
  {
    return this.notifiedTimes;
  }

  public final void increaseNotifiedTimes()
  {
    this.notifiedTimes = (1 + this.notifiedTimes);
  }

  public final void resetNotifiedTimes()
  {
    this.notifiedTimes = 0;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.msdk.timer.task.BaseTask
 * JD-Core Version:    0.6.0
 */