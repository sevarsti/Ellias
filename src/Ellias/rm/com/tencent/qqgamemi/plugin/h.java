package com.tencent.qqgamemi.plugin;

import android.content.Context;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;

class h
  implements TaskObserver
{
  private PluginItem a;
  private Context b;

  public h(PluginItem paramPluginItem, Context paramContext)
  {
    this.a = paramPluginItem;
    this.b = paramContext;
  }

  public void a(Task paramTask)
  {
  }

  public void b(Task paramTask)
  {
  }

  public void c(Task paramTask)
  {
  }

  public void d(Task paramTask)
  {
    DownloadTask localDownloadTask = (DownloadTask)paramTask;
    String str;
    if (this.a != null)
    {
      str = localDownloadTask.t() + "/" + localDownloadTask.q();
      if (this.a.getPluginInfo() != null)
        break label125;
      LogUtil.i(QMiPluginManager.m(), "auto install plugin(" + this.a.id + ") immediately.");
      QMiPluginManager.a().a(str, null);
    }
    while (true)
    {
      QMiPluginManager.a(this.a.id, this.b);
      QMiPluginManager.a(this.a.id, this.b);
      return;
      label125: LogUtil.i(QMiPluginManager.m(), "pending update plugin(" + this.a.id + ")");
      QMiPluginManager.a().c(str);
    }
  }

  public void e(Task paramTask)
  {
    if (this.a != null)
      LogUtil.e(QMiPluginManager.m(), "plugin update failed(id:" + this.a.id + " | url:" + paramTask.v() + ")");
  }

  public void g(Task paramTask)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.h
 * JD-Core Version:    0.6.0
 */