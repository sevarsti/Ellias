package com.tencent.qqgamemi.report;

import android.os.Handler;
import android.os.Message;
import com.tencent.qqgamemi.common.TLog;

class a extends Handler
{
  a(UserAccessStatics paramUserAccessStatics)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    super.handleMessage(paramMessage);
    switch (paramMessage.what)
    {
    default:
    case 1:
    }
    while (true)
    {
      UserAccessStatics.a(false);
      return;
      TLog.c("QMiReport", "上报成功");
      UserAccessStatics.a(this.a).b();
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.report.a
 * JD-Core Version:    0.6.0
 */