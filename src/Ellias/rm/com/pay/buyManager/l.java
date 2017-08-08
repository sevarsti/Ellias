package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APDataReportManager;

final class l
  implements DialogInterface.OnClickListener
{
  l(APPayManager paramAPPayManager, int paramInt)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APDataReportManager.getInstance().insertData("sdk.notenough.cancel", this.a.saveType, null, String.valueOf(this.b), null);
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.l
 * JD-Core Version:    0.6.0
 */