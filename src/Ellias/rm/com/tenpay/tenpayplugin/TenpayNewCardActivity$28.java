package com.tenpay.tenpayplugin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class TenpayNewCardActivity$28
  implements DialogInterface.OnDismissListener
{
  public void onDismiss(DialogInterface paramDialogInterface)
  {
    if (this.a.f != null)
    {
      TenpayNewCardActivity.b(this.a, "keyback");
      this.a.g = this.a.f;
      this.a.f = null;
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.tenpay.tenpayplugin.TenpayNewCardActivity.28
 * JD-Core Version:    0.6.0
 */