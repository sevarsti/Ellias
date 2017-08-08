package com.pay.ui.channel;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.tool.APCommMethod;

final class DUpper
  implements TextWatcher
{
  DUpper(APQCardPayActivity paramAPQCardPayActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardNumDel"))).setVisibility(0);
      APQCardPayActivity.b(this.a).setTextSize(19.0F);
      APQCardPayActivity.b(this.a).setHintTextColor(-13421773);
      if (this.a.getResources().getConfiguration().orientation == 1)
        APQCardPayActivity.a(this.a, paramEditable, this.a.a);
      return;
    }
    ((ImageButton)this.a.findViewById(APCommMethod.getId(this.a, "unipay_id_CardNumDel"))).setVisibility(8);
    APQCardPayActivity.b(this.a).setTextSize(16.0F);
    APQCardPayActivity.b(this.a).setHintTextColor(-6710887);
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}