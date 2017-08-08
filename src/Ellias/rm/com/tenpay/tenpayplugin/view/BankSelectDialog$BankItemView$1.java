package com.tenpay.tenpayplugin.view;

import android.view.View;
import android.view.View.OnClickListener;

class BankSelectDialog$BankItemView$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    BankSelectDialog.BankItemView.c(this.a).dismiss();
    if (BankSelectDialog.BankItemView.c(this.a).a != null)
      BankSelectDialog.BankItemView.c(this.a).a.onItemSelect(paramView.getId());
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.view.BankSelectDialog.BankItemView.1
 * JD-Core Version:    0.6.0
 */