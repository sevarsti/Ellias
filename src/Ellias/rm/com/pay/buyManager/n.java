package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APDataReportManager;

final class n
  implements DialogInterface.OnClickListener
{
  n(APPayManager paramAPPayManager)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APPayManager.a(this.a, "");
    APDataReportManager.getInstance().insertData("sdk.notenough.cancel", this.a.saveType, null, "15", null);
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.n
 * JD-Core Version:    0.6.0
 */