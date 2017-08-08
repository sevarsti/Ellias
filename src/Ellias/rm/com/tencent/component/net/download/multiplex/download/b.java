package com.tencent.component.net.download.multiplex.download;

import java.util.Iterator;
import java.util.List;
import java.util.TimerTask;

class b extends TimerTask
{
  b(DownloadManager paramDownloadManager)
  {
  }

  public void run()
  {
    DownloadManager.a(this.a).a();
    while (true)
    {
      synchronized (DownloadManager.b(this.a))
      {
        Iterator localIterator = DownloadManager.b(this.a).iterator();
        if (!localIterator.hasNext())
          continue;
        DownloadTask localDownloadTask = (DownloadTask)localIterator.next();
        localDownloadTask.B();
        localDownloadTask.ab();
        DownloadManager.c(this.a).b(localDownloadTask);
        if (DownloadManager.a(this.a).c(localDownloadTask.E()) == null)
        {
          i = 0;
          if ((localDownloadTask.c()) || (i == 0) || (localDownloadTask.aD == 1) || (localDownloadTask.aD != 2))
            continue;
          continue;
          DownloadManager.d(this.a);
          return;
        }
      }
      int i = 1;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.b
 * JD-Core Version:    0.6.0
 */