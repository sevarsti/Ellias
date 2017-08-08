package com.tencent.component.net.download.multiplex.download;

import java.util.ArrayList;

class c
  implements Runnable
{
  c(DownloadManager paramDownloadManager, Integer paramInteger, DownloadTask paramDownloadTask, DownloadManager.FileDeletedListener paramFileDeletedListener)
  {
  }

  public void run()
  {
    ArrayList localArrayList = DownloadManager.a(this.d, this.a.intValue(), this.b.t(), this.b.q());
    new g(this.d, localArrayList, this.c, true).a();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.c
 * JD-Core Version:    0.6.0
 */