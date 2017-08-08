package com.tenpay.tenpayplugin;

import com.tenpay.tenpayplugin.view.BankSelectDialog;
import com.tenpay.tenpayplugin.view.TenpayResizeLinearLayout.OnSizeChangedListener;

class TenpayNewCardActivity$2
  implements TenpayResizeLinearLayout.OnSizeChangedListener
{
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((TenpayNewCardActivity.n(this.a) != null) && (TenpayNewCardActivity.n(this.a).isShowing()))
      TenpayNewCardActivity.n(this.a).dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.2
 * JD-Core Version:    0.6.0
 */