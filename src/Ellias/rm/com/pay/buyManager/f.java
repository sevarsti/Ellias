package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APDataReportManager;

final class f
  implements DialogInterface.OnClickListener
{
  f(APPayManager paramAPPayManager, int paramInt)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.b == 4)
      APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, "16", null);
    while (true)
    {
      paramDialogInterface.cancel();
      return;
      APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, String.valueOf(this.b), null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.f
 * JD-Core Version:    0.6.0
 */