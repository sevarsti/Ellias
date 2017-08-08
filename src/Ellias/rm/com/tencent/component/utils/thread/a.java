package com.tencent.component.utils.thread;

import android.os.Process;

class a extends Thread
{
  a(PriorityThreadFactory paramPriorityThreadFactory, Runnable paramRunnable, String paramString)
  {
    super(paramRunnable, paramString);
  }

  public void run()
  {
    Process.setThreadPriority(PriorityThreadFactory.a(this.a));
    super.run();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.utils.thread.a
 * JD-Core Version:    0.6.0
 */