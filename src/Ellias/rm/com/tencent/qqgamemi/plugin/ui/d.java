package com.tencent.qqgamemi.plugin.ui;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.plugin.InstallPluginListener;
import com.tencent.qqgamemi.common.TLog;

class d
  implements InstallPluginListener
{
  d(c paramc, Task paramTask)
  {
  }

  public void a()
  {
    TLog.c(PluginListAdapter.c(), "onInstallSuccess");
    Message localMessage = new Message();
    localMessage.what = 5;
    localMessage.obj = this.a;
    this.b.a.a.sendMessage(localMessage);
  }

  public void a(String paramString)
  {
    TLog.c(PluginListAdapter.c(), "onInstallFailed：" + paramString);
    Message localMessage = new Message();
    localMessage.what = 6;
    localMessage.obj = this.a;
    this.b.a.a.sendMessage(localMessage);
  }

  public IBinder asBinder()
  {
    return null;
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.ui.d
 * JD-Core Version:    0.6.0
 */