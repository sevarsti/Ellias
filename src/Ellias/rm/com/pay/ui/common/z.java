package com.pay.ui.common;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import com.pay.tool.APCommMethod;

final class z
  implements TextWatcher
{
  z(APPayVerifyCodeActivity paramAPPayVerifyCodeActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_apCodeDel"))).setVisibility(0);
    do
    {
      return;
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_apCodeDel"))).setVisibility(8);
    }
    while ((paramEditable.length() != 4) || (APPayVerifyCodeActivity.b(this.a) == null));
    APPayVerifyCodeActivity.b(this.a).dismiss();
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.z
 * JD-Core Version:    0.6.0
 */