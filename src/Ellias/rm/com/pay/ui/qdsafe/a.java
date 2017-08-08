package com.pay.ui.qdsafe;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class a
  implements View.OnClickListener
{
  a(APSafeCenterWebActivity paramAPSafeCenterWebActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.mb.back", APDataInterface.singleton().getOrderInfo().saveType);
    this.a.setResult(-1);
    this.a.finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.qdsafe.a
 * JD-Core Version:    0.6.0
 */