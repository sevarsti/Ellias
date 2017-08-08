package com.tencent.component.utils.thread;

final class b
  implements ThreadPool.Job
{
  b(Runnable paramRunnable)
  {
  }

  public Object run(ThreadPool.JobContext paramJobContext)
  {
    this.a.run();
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.b
 * JD-Core Version:    0.6.0
 */