package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;

class p
  implements View.OnFocusChangeListener
{
  p(LoginDialog paramLoginDialog)
  {
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (LoginDialog.f(this.a) != null)
    {
      if (!paramBoolean)
        break label39;
      if (LoginDialog.e(this.a).length() != 0)
        LoginDialog.f(this.a).setVisibility(0);
    }
    return;
    label39: LoginDialog.f(this.a).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.p
 * JD-Core Version:    0.6.0
 */