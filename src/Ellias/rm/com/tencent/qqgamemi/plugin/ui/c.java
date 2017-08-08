package com.tencent.qqgamemi.plugin.ui;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.component.net.download.multiplex.download.DownloadTask;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import java.io.File;
import java.util.List;

class c extends Handler
{
  c(PluginListAdapter paramPluginListAdapter, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1:
      Task localTask4 = (Task)paramMessage.obj;
      PluginListAdapter.a(this.a, localTask4);
      return;
    case 2:
      Task localTask3 = (Task)paramMessage.obj;
      DownloadTask localDownloadTask = (DownloadTask)localTask3;
      PluginListAdapter.b(this.a, localTask3);
      String str = localDownloadTask.t() + File.separator + localDownloadTask.q();
      TLog.c(PluginListAdapter.c(), "download done, start install plugin:" + str);
      QMiPluginManager.a().a(str, new d(this, localTask3));
      return;
    case 3:
      Task localTask2 = (Task)paramMessage.obj;
      PluginListAdapter.a(this.a, "下载插件失败");
      PluginListAdapter.c(this.a, localTask2);
      return;
    case 4:
      List localList = (List)paramMessage.obj;
      PluginListAdapter.a(this.a, localList);
      return;
    case 5:
      Task localTask1 = (Task)paramMessage.obj;
      PluginListAdapter.d(this.a, localTask1);
      PluginListAdapter.a(this.a, "安装成功");
      return;
    case 6:
    }
    PluginListAdapter.a(this.a, "安装插件失败");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.c
 * JD-Core Version:    0.6.0
 */