package com.tencent.component.utils.thread;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract interface ThreadPool$JobContext
{
  @PluginApi(a=4)
  public abstract boolean isCancelled();

  @PluginApi(a=4)
  public abstract boolean setMode(int paramInt);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.ThreadPool.JobContext
 * JD-Core Version:    0.6.0
 */