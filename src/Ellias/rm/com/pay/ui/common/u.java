package com.pay.ui.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class u
  implements TextWatcher
{
  u(APPayPasswordActivity paramAPPayPasswordActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      this.a.unipay_id_apPayPWDEdit.setTextSize(19.0F);
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_apDelBtn"))).setVisibility(0);
      return;
    }
    this.a.unipay_id_apPayPWDEdit.setTextSize(15.0F);
    ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_apDelBtn"))).setVisibility(8);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.u
 * JD-Core Version:    0.6.0
 */