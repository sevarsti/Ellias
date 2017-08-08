package com.tencent.component.utils.thread;

import com.tencent.component.annotation.PluginApi;

public enum ThreadPool$Priority
{
  int priorityInt;

  static
  {
    HIGH = new Priority("HIGH", 2, 3);
    Priority[] arrayOfPriority = new Priority[3];
    arrayOfPriority[0] = LOW;
    arrayOfPriority[1] = NORMAL;
    arrayOfPriority[2] = HIGH;
    $VALUES = arrayOfPriority;
  }

  private ThreadPool$Priority(int arg3)
  {
    int j;
    this.priorityInt = j;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.ThreadPool.Priority
 * JD-Core Version:    0.6.0
 */