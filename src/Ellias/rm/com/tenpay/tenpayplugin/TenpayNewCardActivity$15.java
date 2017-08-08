package com.tenpay.tenpayplugin;

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

class TenpayNewCardActivity$15
  implements View.OnFocusChangeListener
{
  public void onFocusChange(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      TenpayNewCardActivity.a(this.a, TenpayNewCardActivity.F(this.a));
      if (TenpayNewCardActivity.H(this.a) != null)
      {
        TenpayNewCardActivity.H(this.a).setText("");
        EditText localEditText = TenpayNewCardActivity.H(this.a);
        InputFilter[] arrayOfInputFilter = new InputFilter[1];
        arrayOfInputFilter[0] = new InputFilter.LengthFilter(18);
        localEditText.setFilters(arrayOfInputFilter);
        TenpayNewCardActivity.H(this.a).append(TenpayNewCardActivity.F(this.a).getText());
        TenpayNewCardActivity.H(this.a).setHint(TenpayNewCardActivity.F(this.a).getHint());
      }
      TenpayNewCardActivity.u(this.a).clearFocus();
      TenpayNewCardActivity.v(this.a).clearFocus();
      TenpayNewCardActivity.r(this.a).clearFocus();
      TenpayNewCardActivity.E(this.a).clearFocus();
      TenpayNewCardActivity.F(this.a).manageClearButton();
      ((InputMethodManager)this.a.getSystemService("input_method")).hideSoftInputFromWindow(TenpayNewCardActivity.F(this.a).getWindowToken(), 0);
      TenpayNewCardActivity.F(this.a).postDelayed(new TenpayNewCardActivity.15.1(this), 200L);
    }
    do
    {
      return;
      TenpayNewCardActivity.F(this.a).removeClearButton();
    }
    while ((TenpayNewCardActivity.f(this.a).isFocused()) || ((TenpayNewCardActivity.H(this.a) != null) && (TenpayNewCardActivity.H(this.a).isFocused())));
    if (TenpayNewCardActivity.J(this.a) != null)
      TenpayNewCardActivity.J(this.a).setVisibility(8);
    TenpayNewCardActivity.K(this.a).setVisibility(8);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.15
 * JD-Core Version:    0.6.0
 */