package com.tencent.component.net.download.multiplex.download;

import com.tencent.component.utils.log.LogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

class g
  implements DownloadManager.FileDeletedListener
{
  private static final int b = 5;
  private DownloadManager.FileDeletedListener c;
  private ArrayList d = null;
  private int e = 5;
  private boolean f = false;

  public g(DownloadManager paramDownloadManager, ArrayList paramArrayList, DownloadManager.FileDeletedListener paramFileDeletedListener)
  {
    this(paramDownloadManager, paramArrayList, paramFileDeletedListener, false);
  }

  public g(DownloadManager paramDownloadManager, ArrayList paramArrayList, DownloadManager.FileDeletedListener paramFileDeletedListener, boolean paramBoolean)
  {
    this.d = paramArrayList;
    this.c = paramFileDeletedListener;
    this.f = paramBoolean;
    if (paramBoolean)
    {
      this.e = 1;
      return;
    }
    this.e = 5;
  }

  private void b()
  {
    ArrayList localArrayList1 = this.d;
    File localFile = null;
    if (localArrayList1 != null);
    synchronized (this.d)
    {
      boolean bool = this.d.isEmpty();
      localFile = null;
      if (!bool)
        localFile = (File)this.d.remove(0);
      if (localFile != null)
        new f(this.a, localFile, this).start();
      return;
    }
  }

  public void a()
  {
    if (this.d == null)
      return;
    while (true)
    {
      int j;
      synchronized (this.d)
      {
        Iterator localIterator = this.d.iterator();
        i = 0;
        if (!localIterator.hasNext())
          break;
        File localFile = (File)localIterator.next();
        localIterator.remove();
        new f(this.a, localFile, this).start();
        j = i + 1;
        if (j >= this.e)
          return;
      }
      int i = j;
    }
    monitorexit;
  }

  public void a(File paramFile)
  {
    if (this.c != null)
      this.c.a(paramFile);
    b();
  }

  public void b(File paramFile)
  {
    if (this.c != null)
      this.c.b(paramFile);
    File[] arrayOfFile;
    if ((this.f) && (this.d != null) && (this.d.size() <= 0))
    {
      File localFile = paramFile.getParentFile();
      if (localFile.isDirectory())
      {
        arrayOfFile = localFile.listFiles();
        if ((arrayOfFile != null) && (arrayOfFile.length > 0))
          break label104;
        localFile.delete();
        LogUtil.d("DownloadManager", "[DownloadManager] 删除父目录:" + localFile.getAbsolutePath());
      }
    }
    while (true)
    {
      b();
      return;
      label104: LogUtil.d("DownloadManager", "[DownloadManager] 未删除父目录, 因为还有" + arrayOfFile.length + "个子文件,subFiles[0]=" + arrayOfFile[0].getAbsolutePath());
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.download.g
 * JD-Core Version:    0.6.0
 */