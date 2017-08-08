package com.tenpay.tenpayplugin.view;

import android.text.Editable;
import android.text.TextWatcher;

class ClearableEditText$4
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (this.a.b != null)
      this.a.b.onTextChanged();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.a.isFocused())
      this.a.manageClearButton();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.ClearableEditText.4
 * JD-Core Version:    0.6.0
 */