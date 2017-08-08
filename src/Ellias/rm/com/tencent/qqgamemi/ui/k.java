package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.tencent.qqgamemi.common.TLog;
import com.tencent.qqgamemi.view.QMiToast;

class k
  implements View.OnClickListener
{
  k(LoginDialog paramLoginDialog)
  {
  }

  public void onClick(View paramView)
  {
    TLog.c("LoginDialog", "login click");
    if ((LoginDialog.e(this.a) != null) && (LoginDialog.e(this.a).getText().toString().trim().equals("")))
    {
      QMiToast.a(LoginDialog.b(this.a), "帐号输入有误", 1000).show();
      return;
    }
    if ((LoginDialog.d(this.a) != null) && (LoginDialog.d(this.a).getText().toString().trim().equals("")))
    {
      QMiToast.a(LoginDialog.b(this.a), "请输入密码", 1000).show();
      return;
    }
    if (LoginDialog.h(this.a))
    {
      if (LoginDialog.o(this.a).getText().toString().trim().equals(""))
      {
        QMiToast.a(LoginDialog.b(this.a), "请输入验证码", 1000).show();
        return;
      }
      LoginDialog.p(this.a).setClickable(false);
      LoginDialog.c(this.a, true);
      return;
    }
    QMiToast.a(LoginDialog.b(this.a), "正在登录,请稍候", 1000).show();
    LoginDialog.p(this.a).setClickable(false);
    LoginDialog.c(this.a, true);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.k
 * JD-Core Version:    0.6.0
 */