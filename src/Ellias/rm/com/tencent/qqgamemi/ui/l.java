package com.tencent.qqgamemi.ui;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import com.tencent.component.event.Event.EventRank;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.login.LoginCallBack;
import com.tencent.qqgamemi.plugin.QMiPluginManager;
import com.tencent.qqgamemi.view.QMiToast;

class l extends Handler
{
  l(LoginDialog paramLoginDialog)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    do
    {
      do
      {
        do
        {
          return;
          LoginDialog.c(this.a, false);
          LoginDialog.d(this.a, true);
          DataModel.a(LoginDialog.b(this.a)).a(Long.parseLong((String)paramMessage.obj), null);
          QMiPluginManager.a().g();
        }
        while (this.a.c());
        if (LoginDialog.c(this.a) != null)
        {
          LoginDialog.a(this.a, true);
          LoginDialog.c(this.a).onLoginSuccess();
          EventCenter.getInstance().notify(new EventSource("qmiLogin"), 1, Event.EventRank.NORMAL, new Object[0]);
        }
        QMiToast.a(LoginDialog.b(this.a), "登录成功", 1000).show();
        this.a.dismiss();
        return;
        LoginDialog.c(this.a, false);
      }
      while (this.a.c());
      String str = (String)paramMessage.obj;
      QMiToast.a(LoginDialog.b(this.a), "登录失败:" + str, 1000).show();
      LoginDialog.p(this.a).setClickable(true);
      return;
      LoginDialog.c(this.a, false);
    }
    while (this.a.c());
    byte[] arrayOfByte = (byte[])(byte[])paramMessage.obj;
    if (arrayOfByte != null);
    for (Bitmap localBitmap = QMiCommon.a(arrayOfByte); ; localBitmap = null)
    {
      if (localBitmap != null)
        LoginDialog.a(this.a, localBitmap);
      LoginDialog.p(this.a).setClickable(true);
      return;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.l
 * JD-Core Version:    0.6.0
 */