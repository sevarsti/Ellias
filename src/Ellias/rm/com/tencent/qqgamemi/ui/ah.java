package com.tencent.qqgamemi.ui;

import android.app.Dialog;
import com.tencent.qqgamemi.login.LoginCallBack;

class ah
  implements LoginCallBack
{
  ah(ag paramag)
  {
  }

  public void onLoginCancel()
  {
    new MeDialog.Builder(MeDialog.Builder.e(this.a.a)).a().show();
  }

  public void onLoginClose()
  {
  }

  public void onLoginSuccess()
  {
    new MeDialog.Builder(MeDialog.Builder.e(this.a.a)).a().show();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.ah
 * JD-Core Version:    0.6.0
 */