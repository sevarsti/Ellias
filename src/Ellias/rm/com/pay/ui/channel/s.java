package com.pay.ui.channel;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class s
  implements TextWatcher
{
  s(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardNumDel"))).setVisibility(0);
      this.a.a.setTextSize(19.0F);
      this.a.a.setHintTextColor(-13421773);
      return;
    }
    this.a.a.setTextSize(16.0F);
    this.a.a.setHintTextColor(-6710887);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.s
 * JD-Core Version:    0.6.0
 */