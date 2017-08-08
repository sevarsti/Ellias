package com.pay.ui.common;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

final class v
  implements View.OnClickListener
{
  v(APPayPasswordActivity paramAPPayPasswordActivity)
  {
  }

  public final void onClick(View paramView)
  {
    ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.unipay_id_apPayPWDEdit.getWindowToken(), 0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.v
 * JD-Core Version:    0.6.0
 */