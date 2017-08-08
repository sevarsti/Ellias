package com.tencent.qqgamemi.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.TextView;

class r
  implements TextWatcher
{
  r(LoginDialog paramLoginDialog)
  {
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    LoginDialog.b(this.a, true);
    if (paramInt3 > 0)
      if (LoginDialog.d(this.a).hasFocus())
        LoginDialog.g(this.a).setVisibility(0);
    while (true)
    {
      if (LoginDialog.h(this.a))
        LoginDialog.i(this.a);
      return;
      LoginDialog.g(this.a).setVisibility(8);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.r
 * JD-Core Version:    0.6.0
 */