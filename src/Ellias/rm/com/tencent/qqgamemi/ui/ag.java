package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.business.UrlDownLoadGameJoy;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.login.QMiLoginManager;
import com.tencent.qqgamemi.report.UserAccessStatics;

class ag
  implements View.OnClickListener
{
  ag(MeDialog.Builder paramBuilder)
  {
  }

  private void a()
  {
    QMiLoginManager.a(MeDialog.Builder.e(this.a), new ah(this));
    UserAccessStatics.getInstance(MeDialog.Builder.e(this.a)).addQMiAction(212, System.currentTimeMillis(), QMiCommon.a(MeDialog.Builder.e(this.a)), null);
  }

  public void onClick(View paramView)
  {
    if (paramView == MeDialog.Builder.a(this.a))
    {
      TLog.c("MeDialog", "show LoginDialog");
      MeDialog.Builder.b(this.a).dismiss();
      a();
    }
    do
    {
      do
        return;
      while (paramView == MeDialog.Builder.c(this.a));
      if (paramView == MeDialog.Builder.d(this.a))
      {
        TLog.c("MeDialog", "request startInfo");
        MeDialog.Builder.b(this.a).dismiss();
        UrlDownLoadGameJoy.a(MeDialog.Builder.e(this.a), "me");
        return;
      }
      if (paramView == MeDialog.Builder.f(this.a))
      {
        TLog.c("MeDialog", "show PluginManagerdialog");
        MeDialog.Builder.b(this.a).dismiss();
        new PluginManagerDialog(MeDialog.Builder.e(this.a)).show();
        UserAccessStatics.getInstance(MeDialog.Builder.e(this.a)).addQMiAction(214, System.currentTimeMillis(), QMiCommon.a(MeDialog.Builder.e(this.a)), null);
        return;
      }
      if (paramView != MeDialog.Builder.g(this.a))
        continue;
      TLog.c("MeDialog", "show Feedbackdialog");
      MeDialog.Builder.b(this.a).dismiss();
      if (MeDialog.Builder.h(this.a).m())
      {
        new FeedbackDialog(MeDialog.Builder.e(this.a)).show();
        UserAccessStatics.getInstance(MeDialog.Builder.e(this.a)).addQMiAction(216, System.currentTimeMillis(), QMiCommon.a(MeDialog.Builder.e(this.a)), null);
        return;
      }
      a();
      return;
    }
    while (paramView != MeDialog.Builder.i(this.a));
    TLog.c("MeDialog", "show IntroduceDialog");
    MeDialog.Builder.b(this.a).dismiss();
    new IntroduceDialog(MeDialog.Builder.e(this.a)).show();
    UserAccessStatics.getInstance(MeDialog.Builder.e(this.a)).addQMiAction(217, System.currentTimeMillis(), QMiCommon.a(MeDialog.Builder.e(this.a)), null);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ag
 * JD-Core Version:    0.6.0
 */