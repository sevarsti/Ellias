package com.pay.ui.common;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class l
  implements View.OnClickListener
{
  l(APMcardSuccessActivity paramAPMcardSuccessActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.telsucc.back", APDataInterface.singleton().getOrderInfo().saveType);
    APUICommonMethod.popActivity();
    APMcardSuccessActivity.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.l
 * JD-Core Version:    0.6.0
 */