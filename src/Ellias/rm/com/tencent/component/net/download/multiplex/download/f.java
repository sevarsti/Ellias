package com.tencent.component.net.download.multiplex.download;

import com.tencent.component.net.download.multiplex.download.extension.FileUtils;
import java.io.File;

class f extends Thread
{
  File a = null;
  DownloadManager.FileDeletedListener b = null;

  public f(DownloadManager paramDownloadManager, File paramFile, DownloadManager.FileDeletedListener paramFileDeletedListener)
  {
    setName("DeleteFileThread");
    this.a = paramFile;
    this.b = paramFileDeletedListener;
  }

  public void run()
  {
    if (this.a == null);
    boolean bool;
    do
    {
      return;
      String str1 = this.a.getParent();
      String str2 = this.a.getName();
      bool = this.c.a(str1, str2);
      FileUtils.b(str2, str1);
    }
    while (this.b == null);
    if (bool)
    {
      this.b.b(this.a);
      return;
    }
    this.b.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.f
 * JD-Core Version:    0.6.0
 */