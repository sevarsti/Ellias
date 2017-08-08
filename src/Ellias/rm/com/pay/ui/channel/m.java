package com.pay.ui.channel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.pay.tool.APCommMethod;

final class m
  implements TextWatcher
{
  private m(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void afterTextChanged(Editable paramEditable)
  {
  }

  public final void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public final void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramCharSequence.length() == 11)
    {
      APHFPayActivity.g(this.a).setEnabled(true);
      APHFPayActivity.g(this.a).setBackgroundResource(APCommMethod.getDrawableId(this.a, "unipay_drawable_embtn"));
      APHFPayActivity.g(this.a).setText("下一步");
      APHFPayActivity.b(this.a, false);
      if (APHFPayActivity.h(this.a))
      {
        APHFPayActivity.i(this.a).hideSoftInputFromWindow(APHFPayActivity.a(this.a).getWindowToken(), 0);
        APHFPayActivity.c(this.a, false);
      }
      return;
    }
    APHFPayActivity.g(this.a).setEnabled(false);
    APHFPayActivity.g(this.a).setBackgroundResource(APCommMethod.getDrawableId(this.a, "unipay_pic_disbtnbg"));
    APHFPayActivity.g(this.a).setText("下一步");
    APHFPayActivity.b(this.a, false);
    APHFPayActivity.j(this.a).setText("");
    APHFPayActivity.k(this.a).setText("");
    APHFPayActivity.l(this.a).setText("");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.m
 * JD-Core Version:    0.6.0
 */