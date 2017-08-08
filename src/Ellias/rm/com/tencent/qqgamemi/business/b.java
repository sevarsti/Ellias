package com.tencent.qqgamemi.business;

import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;

class b
  implements TaskObserver
{
  b(AutoDownLoadGameJoy paramAutoDownLoadGameJoy)
  {
  }

  public void a(Task paramTask)
  {
  }

  public void b(Task paramTask)
  {
  }

  public void c(Task paramTask)
  {
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    LogUtil.d(AutoDownLoadGameJoy.b(), "download progress:" + localDownloadTask.u());
  }

  public void d(Task paramTask)
  {
    LogUtil.d(AutoDownLoadGameJoy.b(), "download completed");
    AutoDownLoadGameJoy.a(this.a, paramTask);
  }

  public void e(Task paramTask)
  {
    LogUtil.d(AutoDownLoadGameJoy.b(), "download failed");
  }

  public void g(Task paramTask)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.b
 * JD-Core Version:    0.6.0
 */