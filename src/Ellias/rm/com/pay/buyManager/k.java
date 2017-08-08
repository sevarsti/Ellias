package com.pay.buyManager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.pay.data.buyInfo.APBuyQBInfo;
import com.pay.data.buyInfo.APBuyQDInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.common.APUICommonMethod;
import com.pay.ui.saveAccount.APSaveAccountListNumActivity;

final class k
  implements DialogInterface.OnClickListener
{
  k(APPayManager paramAPPayManager, int paramInt)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, String.valueOf(this.b), null);
    if (this.b == 0)
    {
      APDataInterface.singleton().getOrderInfo().saveType = 2;
      APDataInterface.singleton().getOrderInfo().buyInfo = new APBuyQDInfo();
      APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, "0", null);
    }
    while (true)
    {
      APUICommonMethod.popActivity();
      Intent localIntent = new Intent();
      localIntent.setClass(this.a.context, APSaveAccountListNumActivity.class);
      this.a.context.startActivity(localIntent);
      return;
      APDataInterface.singleton().getOrderInfo().saveType = 3;
      APDataInterface.singleton().getOrderInfo().buyInfo = new APBuyQBInfo();
      APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, "11", null);
    }
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.k
 * JD-Core Version:    0.6.0
 */