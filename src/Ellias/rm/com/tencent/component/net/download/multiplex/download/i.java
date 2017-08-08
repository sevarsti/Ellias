package com.tencent.component.net.download.multiplex.download;

import android.os.Process;
import com.tencent.component.utils.log.LogUtil;
import java.util.List;

class i extends Thread
{
  private DownloadTask b;

  public i(DownloadTaskManager paramDownloadTaskManager, DownloadTask paramDownloadTask)
  {
    this.b = paramDownloadTask;
  }

  public DownloadTask a()
  {
    return this.b;
  }

  public boolean a(DownloadTask paramDownloadTask)
  {
    return (this.b != null) && (this.b.equals(paramDownloadTask));
  }

  public boolean b(DownloadTask paramDownloadTask)
  {
    LogUtil.d("DownloadTaskManager", "Worker - Try to cancel task at worker - " + this.b);
    if ((this.b != null) && (this.b.equals(paramDownloadTask)))
    {
      this.b.R();
      LogUtil.d("DownloadTaskManager", "Worker - Task cancelled.");
      synchronized (DownloadTaskManager.a(this.a))
      {
        DownloadTaskManager.a(this.a).remove(this);
        return true;
      }
    }
    return false;
  }

  public void run()
  {
    Process.setThreadPriority(10);
    this.b.run();
    LogUtil.d("DownloadTaskManager", "Worker - Task done - " + this.b);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.i
 * JD-Core Version:    0.6.0
 */