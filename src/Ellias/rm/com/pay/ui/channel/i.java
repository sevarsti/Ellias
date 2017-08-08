package com.pay.ui.channel;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APNetworkManager;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class i
  implements DialogInterface.OnClickListener
{
  i(APHFPayActivity paramAPHFPayActivity)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APHFPayActivity.a(this.a, false);
    APDataReportManager.getInstance().insertData("sdk.ask.sent", APHFPayActivity.f(this.a));
    APNetworkManager.getInstance().queryHFStatus(APDataInterface.singleton().getOrderInfo().orderId, this.a);
    paramDialogInterface.cancel();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.channel.i
 * JD-Core Version:    0.6.0
 */