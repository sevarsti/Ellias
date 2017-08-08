package com.pay.buyManager;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class o
  implements DialogInterface.OnClickListener
{
  o(APPayManager paramAPPayManager, String paramString1, String paramString2, String paramString3)
  {
  }

  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
    APPayManager.a(this.a, this.b);
    this.a.saveType = APDataInterface.singleton().getOrderInfo().saveType;
    APDataReportManager.getInstance().insertData("sdk.notenough.sure", this.a.saveType, null, "15", null);
    APPayManager.a(this.a, this.a.saveType, this.c, this.d, this.b, "", "");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.buyManager.o
 * JD-Core Version:    0.6.0
 */