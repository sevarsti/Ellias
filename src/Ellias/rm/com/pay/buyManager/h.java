package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import com.pay.AndroidPay;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataInterface;
import com.pay.ui.common.APUICommonMethod;

final class h
  implements DialogInterface.OnKeyListener
{
  h(APPayManager paramAPPayManager, String paramString)
  {
  }

  public final boolean onKey(DialogInterface paramDialogInterface, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramInt == 4) && (paramKeyEvent.getAction() == 1))
    {
      if (!this.b.equals("protalgetway"))
        break label47;
      if (APDataInterface.singleton().getOrderInfo().saveType == 1)
        APPayManager.a(this.a);
    }
    while (true)
    {
      return false;
      label47: if ((!this.b.equals("error")) || (!AndroidPay.singleton().isValidPayChannelAndMarket()))
        continue;
      APUICommonMethod.popActivity();
      APCommMethod.payErrorCallBack(-1, "");
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.h
 * JD-Core Version:    0.6.0
 */