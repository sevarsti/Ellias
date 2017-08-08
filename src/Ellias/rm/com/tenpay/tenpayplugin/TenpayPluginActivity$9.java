package com.tenpay.tenpayplugin;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.tenpay.tenpayplugin.view.ValidDateEdit;

class TenpayPluginActivity$9
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() == 5)
    {
      if (TenpayPluginActivity.t(this.a).isValid(System.currentTimeMillis() + 1000L * TenpayPluginActivity.u(this.a)) == 0)
        break label49;
      TenpayPluginActivity.t(this.a).onError();
    }
    label49: 
    do
      return;
    while ((TenpayPluginActivity.v(this.a).getVisibility() != 0) || (TenpayPluginActivity.v(this.a).getText().toString().length() != 0));
    TenpayPluginActivity.v(this.a).requestFocus();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.9
 * JD-Core Version:    0.6.0
 */