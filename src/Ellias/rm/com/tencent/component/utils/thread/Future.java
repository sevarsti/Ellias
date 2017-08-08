package com.tencent.component.utils.thread;

import com.tencent.component.annotation.PluginApi;

@PluginApi(a=4)
public abstract interface Future
{
  public abstract void a(Future.CancelListener paramCancelListener);

  @PluginApi(a=4)
  public abstract void cancel();

  @PluginApi(a=4)
  public abstract Object get();

  @PluginApi(a=4)
  public abstract boolean isCancelled();

  @PluginApi(a=4)
  public abstract boolean isDone();

  @PluginApi(a=4)
  public abstract void waitDone();
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.Future
 * JD-Core Version:    0.6.0
 */