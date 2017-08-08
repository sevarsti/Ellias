package com.pay.ui.channel;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class r
  implements DialogInterface.OnClickListener
{
  r(APMCardPayActivity paramAPMCardPayActivity)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APMCardPayActivity.b(this.a, false);
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.r
 * JD-Core Version:    0.6.0
 */