package com.tencent.component.utils.thread;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract interface ThreadPool$Job
{
  @PluginApi(a=4)
  public abstract Object run(ThreadPool.JobContext paramJobContext);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.ThreadPool.Job
 * JD-Core Version:    0.6.0
 */