package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class q
  implements DialogInterface.OnClickListener
{
  q(APPayManager paramAPPayManager)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.q
 * JD-Core Version:    0.6.0
 */