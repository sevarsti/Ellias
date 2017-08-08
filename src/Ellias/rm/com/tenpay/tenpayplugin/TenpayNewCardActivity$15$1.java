package com.tenpay.tenpayplugin;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;

class TenpayNewCardActivity$15$1
  implements Runnable
{
  public void run()
  {
    TenpayNewCardActivity.I(TenpayNewCardActivity.15.a(this.a)).setText(TenpayResourceUtil.getStringId(TenpayNewCardActivity.15.a(this.a), "unipay_tenpay_x"));
    if (TenpayNewCardActivity.J(TenpayNewCardActivity.15.a(this.a)) != null)
    {
      TenpayNewCardActivity.J(TenpayNewCardActivity.15.a(this.a)).setVisibility(0);
      TenpayNewCardActivity.H(TenpayNewCardActivity.15.a(this.a)).requestFocus();
    }
    TenpayNewCardActivity.K(TenpayNewCardActivity.15.a(this.a)).setVisibility(0);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.15.1
 * JD-Core Version:    0.6.0
 */