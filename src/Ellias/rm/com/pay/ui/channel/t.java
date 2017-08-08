package com.pay.ui.channel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class t
  implements TextWatcher
{
  t(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardPWDDel"))).setVisibility(0);
      this.a.b.setTextSize(19.0F);
      this.a.b.setHintTextColor(-13421773);
      return;
    }
    this.a.b.setTextSize(16.0F);
    this.a.b.setHintTextColor(-6710887);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.t
 * JD-Core Version:    0.6.0
 */