package com.tencent.component.utils.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class PriorityThreadFactory
  implements ThreadFactory
{
  private final int a;
  private final AtomicInteger b = new AtomicInteger();
  private final String c;

  public PriorityThreadFactory(String paramString, int paramInt)
  {
    this.c = paramString;
    this.a = paramInt;
  }

  public Thread newThread(Runnable paramRunnable)
  {
    return new a(this, paramRunnable, this.c + '-' + this.b.getAndIncrement());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.PriorityThreadFactory
 * JD-Core Version:    0.6.0
 */