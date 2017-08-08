package com.pay.ui.common;

import android.app.ProgressDialog;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APNetworkManager;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class n
  implements View.OnClickListener
{
  n(APMcardSuccessActivity paramAPMcardSuccessActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.telsucc.search", APDataInterface.singleton().getOrderInfo().saveType);
    this.a.waitDialog.show();
    APNetworkManager.getInstance().queryMcardStatus(APDataInterface.singleton().getOrderInfo().orderId, this.a.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.n
 * JD-Core Version:    0.6.0
 */