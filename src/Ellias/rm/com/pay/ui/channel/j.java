package com.pay.ui.channel;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.tool.APDataReportManager;

final class j
  implements DialogInterface.OnClickListener
{
  j(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APHFPayActivity.a(this.a, false);
    APDataReportManager.getInstance().insertData("sdk.ask.nonsent", APHFPayActivity.f(this.a));
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.j
 * JD-Core Version:    0.6.0
 */