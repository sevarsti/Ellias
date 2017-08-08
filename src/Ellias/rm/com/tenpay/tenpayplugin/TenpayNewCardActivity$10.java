package com.tenpay.tenpayplugin;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;
import com.tenpay.tenpayplugin.view.ValidDateEdit;

class TenpayNewCardActivity$10
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    int i;
    if (paramEditable.length() == 5)
    {
      i = TenpayNewCardActivity.v(this.a).isValid(System.currentTimeMillis() + 1000L * TenpayNewCardActivity.G(this.a));
      if (i != 0)
        TenpayNewCardActivity.v(this.a).onError();
      if ((TenpayNewCardActivity.u(this.a).getVisibility() != 0) || (TenpayNewCardActivity.u(this.a).getText().toString().length() != 0))
        break label100;
      if (i == 0)
        TenpayNewCardActivity.u(this.a).requestFocus();
    }
    label100: 
    do
    {
      do
      {
        return;
        if (TenpayNewCardActivity.t(this.a).getVisibility() != 0)
          TenpayNewCardActivity.b(this.a, "name");
        TenpayNewCardActivity.t(this.a).setVisibility(0);
      }
      while (i != 0);
      if ((TenpayNewCardActivity.E(this.a).getVisibility() == 0) && (TenpayNewCardActivity.E(this.a).isEnabled()) && (TenpayNewCardActivity.E(this.a).getText().toString().length() == 0))
      {
        TenpayNewCardActivity.E(this.a).requestFocus();
        return;
      }
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
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.10
 * JD-Core Version:    0.6.0
 */