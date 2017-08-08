package com.tenpay.tenpayplugin;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import com.tenpay.tenpayplugin.view.ClearableEditText;

class TenpayNewCardActivity$13
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() == 18)
    {
      if (!TenpayNewCardActivity.invalidateID(paramEditable.toString().toUpperCase()))
        break label70;
      if (TenpayNewCardActivity.f(this.a).getText().toString().length() == 0)
        this.a.mHandler.postDelayed(new TenpayNewCardActivity.13.1(this), 200L);
    }
    return;
    label70: TenpayNewCardActivity.F(this.a).onError();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.13
 * JD-Core Version:    0.6.0
 */