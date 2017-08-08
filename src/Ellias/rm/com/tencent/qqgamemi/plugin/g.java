package com.tencent.qqgamemi.plugin;

import CobraHallQmiProto.TBodyQmiGamePageListRsp;
import android.os.Handler;
import android.os.Message;
import com.tencent.component.utils.DebugUtil;
import com.tencent.qqgamemi.common.TLog;

class g extends Handler
{
  g(QMiPluginManager paramQMiPluginManager)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 11:
    case 1002:
    }
    while (true)
    {
      return;
      String str = (String)paramMessage.obj;
      if (QMiPluginManager.f(this.a))
      {
        QMiPluginManager.c(this.a, str);
        return;
      }
      if (DebugUtil.a())
        TLog.c(QMiPluginManager.m(), "sdk holder:" + str);
      Message localMessage = new Message();
      localMessage.what = 11;
      localMessage.obj = str;
      QMiPluginManager.g(this.a).sendMessageDelayed(localMessage, QMiPluginManager.n().longValue());
      return;
      if (paramMessage.arg1 != 0)
        break;
      TBodyQmiGamePageListRsp localTBodyQmiGamePageListRsp = (TBodyQmiGamePageListRsp)paramMessage.obj;
      if (localTBodyQmiGamePageListRsp == null)
        continue;
      this.a.a(localTBodyQmiGamePageListRsp.gameList, localTBodyQmiGamePageListRsp.gamePkgName);
      return;
    }
    TLog.e(QMiPluginManager.m(), "request plugins failed:" + paramMessage.obj);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.plugin.g
 * JD-Core Version:    0.6.0
 */