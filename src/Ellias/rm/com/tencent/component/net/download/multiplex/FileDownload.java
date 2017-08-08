package com.tencent.component.net.download.multiplex;

import com.tencent.component.net.download.multiplex.download.DownloadManager;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;

public class FileDownload
{
  static DownloadManager a;
  private static final String b = FileDownload.class.getName();

  static
  {
    a = null;
  }

  public static DownloadTask a(String paramString)
  {
    return c().j(paramString);
  }

  public static void a()
  {
    LogUtil.i("Benson", "======== [FileDonwloader] startAllDownloadTask =========");
    c().c();
  }

  public static void a(String paramString, TaskObserver paramTaskObserver)
  {
    LogUtil.i("Benson", "======== [FileDonwloader] addTaskObsever =========");
    DownloadTask localDownloadTask = a(paramString);
    if (localDownloadTask != null)
      localDownloadTask.a(paramTaskObserver);
  }

  public static boolean a(DownloadTask paramDownloadTask)
  {
    LogUtil.i("Benson", "======== [FileDonwloader] downloadTaskToNext =========");
    if (paramDownloadTask != null);
    switch (paramDownloadTask.an())
    {
    case 3:
    case 4:
    default:
      return false;
    case 5:
    case 6:
      c().b(paramDownloadTask);
      return true;
    case 0:
    case 1:
    case 2:
    }
    c().a(paramDownloadTask.E());
    return true;
  }

  public static boolean a(String paramString1, String paramString2, String paramString3, TaskObserver paramTaskObserver)
  {
    DownloadTask localDownloadTask = c().a(paramString1, paramString3, paramString2, true);
    LogUtil.i("Benson", "======== [FileDonwloader] startDownloadApp DownloadTask:" + localDownloadTask + "=========");
    if (localDownloadTask != null)
    {
      localDownloadTask.a(paramTaskObserver);
      return true;
    }
    return false;
  }

  public static void b()
  {
    LogUtil.i("Benson", "======== [FileDonwloader] pauseAllDownloadTask =========");
    c().d();
  }

  public static boolean b(String paramString)
  {
    LogUtil.i("Benson", "======== [FileDonwloader] deleteDownloadTask =========");
    DownloadTask localDownloadTask = a(paramString);
    if (localDownloadTask != null)
      return c().a(localDownloadTask.E(), true);
    return false;
  }

  public static boolean b(String paramString1, String paramString2, String paramString3, TaskObserver paramTaskObserver)
  {
    DownloadTask localDownloadTask1 = c().j(paramString1);
    LogUtil.i("Benson", "======== [FileDonwloader] addDownloadTask DownloadTask:" + localDownloadTask1 + "=========");
    int i = 0;
    if (localDownloadTask1 == null)
    {
      DownloadTask localDownloadTask2 = new DownloadTask(paramString1, paramString3, paramString2);
      localDownloadTask2.a(paramTaskObserver);
      c().a(localDownloadTask2, false);
      i = 1;
    }
    return i;
  }

  private static DownloadManager c()
  {
    if (a == null)
    {
      a = new DownloadManager();
      a.a();
    }
    return a;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.component.net.download.multiplex.FileDownload
 * JD-Core Version:    0.6.0
 */