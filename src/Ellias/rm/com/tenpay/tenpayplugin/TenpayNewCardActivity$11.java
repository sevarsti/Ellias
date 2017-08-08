package com.tenpay.tenpayplugin;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;

class TenpayNewCardActivity$11
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() == 3)
    {
      if (TenpayNewCardActivity.t(this.a).getVisibility() != 0)
        TenpayNewCardActivity.b(this.a, "name");
      TenpayNewCardActivity.t(this.a).setVisibility(0);
      if ((TenpayNewCardActivity.E(this.a).getVisibility() != 0) || (!TenpayNewCardActivity.E(this.a).isEnabled()) || (TenpayNewCardActivity.E(this.a).getText().toString().length() != 0))
        break label102;
      TenpayNewCardActivity.E(this.a).requestFocus();
    }
    label102: 
    do
    {
      return;
      if ((TenpayNewCardActivity.F(this.a).getVisibility() != 0) || (!TenpayNewCardActivity.F(this.a).isEnabled()) || (TenpayNewCardActivity.F(this.a).getText().toString().length() != 0))
        continue;
      TenpayNewCardActivity.F(this.a).requestFocus();
      return;
    }
    while (TenpayNewCardActivity.f(this.a).getText().toString().length() != 0);
    TenpayNewCardActivity.f(this.a).requestFocus();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.11
 * JD-Core Version:    0.6.0
 */