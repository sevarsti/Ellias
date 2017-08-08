package com.tencent.qqgamemi.business;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.component.net.download.multiplex.task.Task;

class f extends Handler
{
  f(UrlDownLoadGameJoy paramUrlDownLoadGameJoy, Looper paramLooper)
  {
    super(paramLooper);
  }

  public void handleMessage(Message paramMessage)
  {
    Task localTask = (Task)paramMessage.obj;
    switch (paramMessage.what)
    {
    case 1:
    default:
      return;
    case 2:
      UrlDownLoadGameJoy.a(this.a, localTask);
      return;
    case 3:
      UrlDownLoadGameJoy.b(this.a, localTask);
      return;
    case 4:
    }
    UrlDownLoadGameJoy.c(this.a, localTask);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.business.f
 * JD-Core Version:    0.6.0
 */