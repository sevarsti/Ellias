package com.pay.ui.common;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.http.APNetworkManager;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

public class APHFPaySuccessActivity$SearchOrderClick
  implements View.OnClickListener
{
  public APHFPaySuccessActivity$SearchOrderClick(APHFPaySuccessActivity paramAPHFPaySuccessActivity)
  {
  }

  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.hfsucc.search", APHFPaySuccessActivity.c(this.a), null, APHFPaySuccessActivity.b(this.a), null);
    APNetworkManager.getInstance().queryHFStatus(APDataInterface.singleton().getOrderInfo().orderId, this.a.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APHFPaySuccessActivity.SearchOrderClick
 * JD-Core Version:    0.6.0
 */