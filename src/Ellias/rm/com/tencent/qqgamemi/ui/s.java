package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.ImageView;
import android.widget.TextView;

class s
  implements View.OnFocusChangeListener
{
  s(LoginDialog paramLoginDialog)
  {
  }

  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (LoginDialog.g(this.a) != null)
    {
      if (!paramBoolean)
        break label39;
      if (LoginDialog.d(this.a).length() != 0)
        LoginDialog.g(this.a).setVisibility(0);
    }
    return;
    label39: LoginDialog.g(this.a).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.s
 * JD-Core Version:    0.6.0
 */