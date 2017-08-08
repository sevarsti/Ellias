package com.tencent.qqgamemi.ui;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

class o
  implements TextWatcher
{
  o(LoginDialog paramLoginDialog)
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
    LoginDialog.d(this.a).setText("");
    LoginDialog.b(this.a, true);
    if (paramInt3 > 0)
    {
      if (LoginDialog.e(this.a).hasFocus())
        LoginDialog.f(this.a).setVisibility(0);
      return;
    }
    LoginDialog.f(this.a).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tencent.qqgamemi.ui.o
 * JD-Core Version:    0.6.0
 */