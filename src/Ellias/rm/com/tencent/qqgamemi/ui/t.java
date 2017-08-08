package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

class t
  implements View.OnClickListener
{
  t(LoginDialog paramLoginDialog)
  {
  }

  public void onClick(View paramView)
  {
    LoginDialog.b(this.a, true);
    LoginDialog.d(this.a).setText("");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.t
 * JD-Core Version:    0.6.0
 */