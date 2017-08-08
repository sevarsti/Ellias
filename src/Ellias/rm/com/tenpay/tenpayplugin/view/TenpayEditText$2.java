package com.tenpay.tenpayplugin.view;

import android.text.Editable;
import android.text.TextWatcher;

class TenpayEditText$2
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramCharSequence.toString().equals(""))
    {
      this.a.setTextSize(15.0F);
      this.a.setTextColor(-6710887);
      return;
    }
    this.a.setTextSize(18.0F);
    this.a.setTextColor(-13421773);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.TenpayEditText.2
 * JD-Core Version:    0.6.0
 */