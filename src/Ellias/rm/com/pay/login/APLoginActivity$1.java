package com.pay.login;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;

class APLoginActivity$1
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      APLoginActivity.a(this.a).setVisibility(0);
      return;
    }
    APLoginActivity.a(this.a).setVisibility(4);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.1
 * JD-Core Version:    0.6.0
 */