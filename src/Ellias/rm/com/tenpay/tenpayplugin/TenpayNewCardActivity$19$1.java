package com.tenpay.tenpayplugin;

import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import com.tenpay.tenpayplugin.view.ClearableEditText;

class TenpayNewCardActivity$19$1
  implements Runnable
{
  public void run()
  {
    if (TenpayNewCardActivity.J(TenpayNewCardActivity.19.a(this.a)) != null)
      TenpayNewCardActivity.J(TenpayNewCardActivity.19.a(this.a)).setVisibility(0);
    if (TenpayNewCardActivity.H(TenpayNewCardActivity.19.a(this.a)) != null)
    {
      TenpayNewCardActivity.H(TenpayNewCardActivity.19.a(this.a)).setText("");
      EditText localEditText = TenpayNewCardActivity.H(TenpayNewCardActivity.19.a(this.a));
      InputFilter[] arrayOfInputFilter = new InputFilter[1];
      arrayOfInputFilter[0] = new InputFilter.LengthFilter(11);
      localEditText.setFilters(arrayOfInputFilter);
      TenpayNewCardActivity.H(TenpayNewCardActivity.19.a(this.a)).append(TenpayNewCardActivity.f(TenpayNewCardActivity.19.a(this.a)).getText());
      TenpayNewCardActivity.H(TenpayNewCardActivity.19.a(this.a)).setHint(TenpayNewCardActivity.f(TenpayNewCardActivity.19.a(this.a)).getHint());
    }
    TenpayNewCardActivity.K(TenpayNewCardActivity.19.a(this.a)).setVisibility(0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.19.1
 * JD-Core Version:    0.6.0
 */