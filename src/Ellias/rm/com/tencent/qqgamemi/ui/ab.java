package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.component.event.Event.EventRank;
import com.tencent.component.event.EventCenter;
import com.tencent.component.event.EventSource;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.report.UserAccessStatics;

class ab
  implements View.OnClickListener
{
  ab(LoginInfoDialog paramLoginInfoDialog)
  {
  }

  public void onClick(View paramView)
  {
    if (paramView == LoginInfoDialog.b(this.a))
    {
      TLog.c(LoginInfoDialog.a(), "request startInfo");
      this.a.dismiss();
      UrlDownLoadGameJoy.a(LoginInfoDialog.a(this.a), "me");
    }
    do
      return;
    while (paramView != LoginInfoDialog.c(this.a));
    TLog.c(LoginInfoDialog.a(), "logout");
    LoginInfoDialog.d(this.a).a(LoginInfoDialog.a(this.a), true);
    EventCenter.getInstance().notify(new EventSource("qmiLogin"), 2, Event.EventRank.NORMAL, new Object[0]);
    this.a.dismiss();
    new MeDialog.Builder(LoginInfoDialog.a(this.a)).a().show();
    UserAccessStatics.getInstance(LoginInfoDialog.a(this.a)).addQMiAction(206, System.currentTimeMillis(), QMiCommon.a(LoginInfoDialog.a(this.a)), null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ab
 * JD-Core Version:    0.6.0
 */