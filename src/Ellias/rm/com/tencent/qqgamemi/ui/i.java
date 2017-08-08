package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.tencent.qqgamemi.common.QMiCommon;
import com.tencent.qqgamemi.login.LoginCallBack;

class i
  implements View.OnClickListener
{
  i(LoginDialog paramLoginDialog)
  {
  }

  public void onClick(View paramView)
  {
    if (LoginDialog.a(this.a))
    {
      QMiCommon.c(LoginDialog.b(this.a), "正在登录,请稍候");
      return;
    }
    if (LoginDialog.c(this.a) != null)
    {
      LoginDialog.a(this.a, true);
      LoginDialog.c(this.a).onLoginClose();
    }
    this.a.dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.i
 * JD-Core Version:    0.6.0
 */