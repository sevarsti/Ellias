package com.tenpay.tenpayplugin;

import android.os.Handler;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;
import com.tenpay.tenpayplugin.view.TenpayNumberEditText;
import com.tenpay.tenpayplugin.view.ValidDateEdit;

class TenpayNewCardActivity$18
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      TenpayNewCardActivity.a(this.a, TenpayNewCardActivity.f(this.a));
      TenpayNewCardActivity.u(this.a).clearFocus();
      TenpayNewCardActivity.v(this.a).clearFocus();
      TenpayNewCardActivity.r(this.a).clearFocus();
      TenpayNewCardActivity.E(this.a).clearFocus();
      if (TenpayNewCardActivity.H(this.a) != null)
      {
        TenpayNewCardActivity.H(this.a).setText("");
        EditText localEditText = TenpayNewCardActivity.H(this.a);
        InputFilter[] arrayOfInputFilter = new InputFilter[1];
        arrayOfInputFilter[0] = new InputFilter.LengthFilter(11);
        localEditText.setFilters(arrayOfInputFilter);
        TenpayNewCardActivity.H(this.a).append(TenpayNewCardActivity.f(this.a).getText());
        TenpayNewCardActivity.H(this.a).setHint(TenpayNewCardActivity.f(this.a).getHint());
      }
      TenpayNewCardActivity.f(this.a).manageClearButton();
      ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(TenpayNewCardActivity.f(this.a).getWindowToken(), 0);
      TenpayNewCardActivity.f(this.a).postDelayed(new TenpayNewCardActivity.18.1(this), 200L);
      this.a.mHandler.postDelayed(new TenpayNewCardActivity.18.2(this), 300L);
    }
    do
    {
      do
      {
        return;
        TenpayNewCardActivity.f(this.a).removeClearButton();
      }
      while ((TenpayNewCardActivity.F(this.a).isFocused()) || ((TenpayNewCardActivity.H(this.a) != null) && (TenpayNewCardActivity.H(this.a).isFocused())));
      TenpayNewCardActivity.K(this.a).setVisibility(8);
    }
    while (TenpayNewCardActivity.J(this.a) == null);
    TenpayNewCardActivity.J(this.a).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.18
 * JD-Core Version:    0.6.0
 */