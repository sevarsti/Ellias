package com.tencent.component.utils.thread;

import java.util.concurrent.atomic.AtomicLong;

class f extends h
  implements Comparable
{
  private final int c;
  private final boolean d;
  private final long e;

  public f(ThreadPool paramThreadPool, ThreadPool.Job paramJob, FutureListener paramFutureListener, int paramInt, boolean paramBoolean)
  {
    super(paramThreadPool, paramJob, paramFutureListener);
    this.c = paramInt;
    this.d = paramBoolean;
    this.e = ThreadPool.d.getAndIncrement();
  }

  private int b(f paramf)
  {
    int i;
    if (this.e < paramf.e)
      i = -1;
    while (true)
    {
      if (this.d)
        i = -i;
      return i;
      if (this.e > paramf.e)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  public int a(f paramf)
  {
    if (this.c > paramf.c)
      return -1;
    if (this.c < paramf.c)
      return 1;
    return b(paramf);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.f
 * JD-Core Version:    0.6.0
 */