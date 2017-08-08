package com.pay.ui.qdsafe;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageButton;

final class f
  implements TextWatcher
{
  f(APSmmActivity paramAPSmmActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      this.a.g.setVisibility(0);
      return;
    }
    this.a.g.setVisibility(8);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.f
 * JD-Core Version:    0.6.0
 */