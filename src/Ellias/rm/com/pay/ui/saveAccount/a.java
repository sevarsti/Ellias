package com.pay.ui.saveAccount;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

final class a
  implements TextWatcher
{
  a(APSaveAccountInputNumActivity paramAPSaveAccountInputNumActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.length() != 0)
    {
      APSaveAccountInputNumActivity.a(this.a).setTextSize(19.0F);
      APSaveAccountInputNumActivity.b(this.a).setVisibility(0);
    }
    while (true)
    {
      this.a.refreshAccountCost(APSaveAccountInputNumActivity.a(this.a).getText().toString().trim(), APSaveAccountInputNumActivity.c(this.a));
      return;
      APSaveAccountInputNumActivity.a(this.a).setTextSize(15.0F);
      APSaveAccountInputNumActivity.a(this.a).setHint("充值数额");
      APSaveAccountInputNumActivity.b(this.a).setVisibility(4);
    }
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 == 0) && (paramInt2 == 1) && (paramInt3 == 0))
      this.a.refreshAccountCost(APSaveAccountInputNumActivity.a(this.a).getText().toString().trim(), APSaveAccountInputNumActivity.c(this.a));
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.saveAccount.a
 * JD-Core Version:    0.6.0
 */