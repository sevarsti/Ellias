package com.tencent.component.utils.thread;

import com.tencent.component.annotation.PluginApi;

public abstract interface FutureListener
{
  @PluginApi(a=4)
  public abstract void onFutureBegin(Future paramFuture);

  @PluginApi(a=4)
  public abstract void onFutureDone(Future paramFuture);
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.FutureListener
 * JD-Core Version:    0.6.0
 */