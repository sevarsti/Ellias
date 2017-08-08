package com.tenpay.tenpayplugin;

import com.tenpay.tenpayplugin.view.BankSelectDialog;
import com.tenpay.tenpayplugin.view.TenpayResizeLinearLayout.OnSizeChangedListener;

class TenpayPluginActivity$3
  implements TenpayResizeLinearLayout.OnSizeChangedListener
{
  public void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((TenpayPluginActivity.n(this.a) != null) && (TenpayPluginActivity.n(this.a).isShowing()))
      TenpayPluginActivity.n(this.a).dismiss();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayPluginActivity.3
 * JD-Core Version:    0.6.0
 */