package com.tencent.qqgamemi.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

class q
  implements View.OnClickListener
{
  q(LoginDialog paramLoginDialog)
  {
  }

  public void onClick(View paramView)
  {
    LoginDialog.e(this.a).setText("");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.q
 * JD-Core Version:    0.6.0
 */