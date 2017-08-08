package com.pay.ui.payCenter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;
import com.pay.common.tool.APTypeChange;
import com.pay.ui.common.APUICommonMethod;

final class a
  implements TextWatcher
{
  a(APPayGameInputNumActivity paramAPPayGameInputNumActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
    APPayGameInputNumActivity.a(this.a);
    String str = paramEditable.toString().trim();
    if (paramEditable.length() == 0)
    {
      APPayGameInputNumActivity.b(this.a).setVisibility(4);
      this.a.setUptoNumMpMpSendInfo(0);
      return;
    }
    if (paramEditable.toString().equals("0"))
    {
      APPayGameInputNumActivity.b(this.a).setVisibility(4);
      APPayGameInputNumActivity.c(this.a).setText("");
      this.a.setUptoNumMpMpSendInfo(0);
      return;
    }
    APPayGameInputNumActivity.b(this.a).setVisibility(0);
    int i = APTypeChange.StringToInt(str);
    if (i > APPayGameInputNumActivity.d(this.a))
    {
      APPayGameInputNumActivity.c(this.a).setText(String.valueOf(APPayGameInputNumActivity.d(this.a)));
      APPayGameInputNumActivity.c(this.a).setSelection(String.valueOf(APPayGameInputNumActivity.d(this.a)).toString().length());
      APUICommonMethod.showToast(this.a, "购买数量不能超过最大限制");
    }
    while (true)
    {
      this.a.setUptoNumMpMpSendInfo(i);
      int j = APPayGameInputNumActivity.c(this.a).getText().length();
      if (j <= 0)
        break;
      APPayGameInputNumActivity.c(this.a).setSelection(j);
      return;
      if (i != APPayGameInputNumActivity.d(this.a))
        continue;
      APPayGameInputNumActivity.c(this.a).setSelection(APPayGameInputNumActivity.c(this.a).getText().toString().length());
    }
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 == 0) && (paramInt2 == 1) && (paramInt3 == 0))
      APPayGameInputNumActivity.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.payCenter.a
 * JD-Core Version:    0.6.0
 */