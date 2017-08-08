package com.tencent.qqgamemi;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.component.utils.DebugUtil;
import com.tencent.qqgamemi.business.GameActionReportHelper;
import com.tencent.qqgamemi.business.MIUINotifyHelper;
import com.tencent.qqgamemi.business.QMiEnvironmentHelper;
import com.tencent.qqgamemi.business.ReceiverRegisterHelper;
import com.tencent.qqgamemi.business.ShowPriorityHelper;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.data.DataModel;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.protocol.QMiJceCommonData;

class c extends Handler
{
  c(QMiServiceLogic paramQMiServiceLogic)
  {
  }

  public void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 200:
    case 230:
    case 240:
    case 210:
    case 270:
    case 271:
    case 280:
    case 290:
    case 340:
    case 300:
    case 301:
    case 310:
    case 311:
    case 330:
    }
    Intent localIntent1;
    do
    {
      return;
      if (QMiServiceLogic.b(this.a))
      {
        if (DebugUtil.a())
          TLog.c("QMiService", "is OurGame");
        if (QMiServiceLogic.c(this.a).e())
          if (QMiServiceLogic.b != null)
            QMiServiceLogic.a(this.a, QMiServiceLogic.b);
      }
      while (true)
      {
        QMiServiceLogic.e(this.a).b();
        QMiServiceLogic.f(this.a).sendEmptyMessageDelayed(200, 1000L);
        return;
        QMiServiceLogic.d(this.a);
        continue;
        if (DebugUtil.a())
          TLog.c("QMiService", "not OurGame");
        QMiServiceLogic.d(this.a);
      }
      TLog.c("QMiService", "HANDLE_DISABLE_CURRENT_GAME");
      QMiServiceLogic.g(this.a);
      return;
      TLog.c("QMiService", "HANDLE_UPDATE_GAMEINFO");
      QMiServiceLogic.h(this.a).e();
      return;
      QMiWindowManager.k();
      return;
      QMiServiceLogic.i(this.a).e();
      return;
      QMiServiceLogic.i(this.a).f();
      return;
      QMiServiceLogic.i(this.a).d();
      return;
      QMiServiceLogic.j(this.a).a();
      return;
      QMiLoginManager.a().p();
      QMiLoginManager.a().q();
      TLog.c("SYBACCOUNT", "HANDLE_MSDK_LOGIN_SUC!");
      return;
      Intent localIntent2 = (Intent)paramMessage.obj;
      QMiLoginManager.a().a(localIntent2);
      Long localLong = Long.valueOf(QMiLoginManager.a().i());
      QMiEnvironmentHelper.a(QMiServiceLogic.a(this.a), localLong.longValue());
      return;
      TLog.c("SYBACCOUNT", "logout2");
      QMiLoginManager.a().a(QMiServiceLogic.a(this.a), false);
      return;
      QMiServiceLogic.c(this.a).b();
      return;
      QMiServiceLogic.c(this.a).c();
      return;
      localIntent1 = (Intent)paramMessage.obj;
    }
    while ((localIntent1 == null) || (!TextUtils.isEmpty(QMiJceCommonData.p)));
    QMiJceCommonData.p = localIntent1.getExtras().getString("QIMEI");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.c
 * JD-Core Version:    0.6.0
 */