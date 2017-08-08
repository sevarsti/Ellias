package com.tenpay.tenpayplugin;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

class TenpayNewCardActivity$20
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() < 11)
    {
      TenpayNewCardActivity.M(this.a).setClickable(false);
      TenpayNewCardActivity.M(this.a).setEnabled(false);
      return;
    }
    TenpayNewCardActivity.M(this.a).setClickable(true);
    TenpayNewCardActivity.M(this.a).setEnabled(true);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.20
 * JD-Core Version:    0.6.0
 */