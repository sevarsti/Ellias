package com.tencent.tmassistantsdk.downloadservice;

import android.os.PowerManager.WakeLock;
import com.tencent.tmassistantsdk.g.l;
import java.util.ArrayList;
import java.util.PriorityQueue;

class j extends Thread
{
  private int b = 0;

  public j(h paramh, int paramInt)
  {
    this.b = paramInt;
    setName("download_thread_" + this.b);
    start();
  }

  public void run()
  {
    l.b("DownloadThreadPool", "Thread " + this.b + " starts running...");
    while (true)
    {
      synchronized (this.a.e)
      {
        try
        {
          l.b("DownloadThreadPool", "Thread " + this.b + " is waitting...");
          this.a.e.wait();
          if (!Thread.currentThread().isInterrupted())
            break label275;
          l.b("DownloadThreadPool", "Thread " + this.b + " is interrupted...");
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          l.b("DownloadThreadPool", "Thread " + this.b + " is interrupted...");
          localInterruptedException.printStackTrace();
          return;
        }
      }
      label173: Object localObject5;
      if (localObject5 != null)
      {
        l.b("DownloadThreadPool", "TaskThread::Run ThreadName: " + getName() + " url: " + ((g)localObject5).c());
        PowerManager.WakeLock localWakeLock = c.a();
        ((g)localObject5).a(getName());
        if (localWakeLock != null)
          localWakeLock.release();
      }
      Object localObject6 = this.a.f;
      monitorenter;
      if (localObject5 != null);
      try
      {
        while (true)
        {
          this.a.c.remove(localObject5);
          monitorexit;
          label275: if (!this.a.b())
            break;
          synchronized (this.a.f)
          {
            int i = this.a.b.size();
            localObject5 = null;
            if (i > 0)
            {
              localObject5 = (g)this.a.b.remove();
              this.a.c.add(localObject5);
            }
            if (!Thread.currentThread().isInterrupted())
              break label173;
            l.b("DownloadThreadPool", "Thread " + this.b + " is interrupted...");
            return;
          }
        }
      }
      finally
      {
        monitorexit;
      }
    }
    throw localObject7;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.tmassistantsdk.downloadservice.j
 * JD-Core Version:    0.6.0
 */