package com.tencent.qqgamemi.business;

import android.os.Handler;
import android.os.Message;
import com.tencent.component.net.download.multiplex.task.Task;
import com.tencent.component.net.download.multiplex.task.TaskObserver;
import com.tencent.component.utils.log.LogUtil;

class e
  implements TaskObserver
{
  e(UrlDownLoadGameJoy paramUrlDownLoadGameJoy)
  {
  }

  public void a(Task paramTask)
  {
  }

  public void b(Task paramTask)
  {
    Message localMessage = new Message();
    localMessage.what = 1;
    localMessage.obj = paramTask;
    this.a.a.sendMessage(localMessage);
  }

  public void c(Task paramTask)
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    localMessage.obj = paramTask;
    this.a.a.sendMessage(localMessage);
  }

  public void d(Task paramTask)
  {
    LogUtil.d(UrlDownLoadGameJoy.b(), "download completed");
    Message localMessage = new Message();
    localMessage.what = 3;
    localMessage.obj = paramTask;
    this.a.a.sendMessage(localMessage);
  }

  public void e(Task paramTask)
  {
    LogUtil.d(UrlDownLoadGameJoy.b(), "download failed");
    Message localMessage = new Message();
    localMessage.what = 4;
    localMessage.obj = paramTask;
    this.a.a.sendMessage(localMessage);
  }

  public void g(Task paramTask)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.e
 * JD-Core Version:    0.6.0
 */