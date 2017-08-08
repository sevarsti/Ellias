package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.ui.common.APUICommonMethod;

final class g
  implements DialogInterface.OnClickListener
{
  g(APPayManager paramAPPayManager, String paramString)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.b.equals("protalgetway"))
      if (APDataInterface.singleton().getOrderInfo().saveType == 1)
        APPayManager.a(this.a);
    while (true)
    {
      paramDialogInterface.cancel();
      return;
      if ((!this.b.equals("error")) || (!AndroidPay.singleton().isValidPayChannelAndMarket()))
        continue;
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(-1, "");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.g
 * JD-Core Version:    0.6.0
 */