package com.pay.ui.common;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;

public class APHFPaySuccessActivity$BackGameClick
  implements View.OnClickListener
{
  public APHFPaySuccessActivity$BackGameClick(APHFPaySuccessActivity paramAPHFPaySuccessActivity)
  {
  }

  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.hfsucc.back", APHFPaySuccessActivity.c(this.a), null, APHFPaySuccessActivity.b(this.a), null);
    APUICommonMethod.popActivity();
    if (APHFPaySuccessActivity.b(this.a).equals("failed"))
    {
      APCommMethod.payErrorCallBack(-1, "fail");
      return;
    }
    if (APHFPaySuccessActivity.b(this.a).equals("succeed"))
    {
      APCommMethod.paySuccCallBack(9, 0, -1);
      return;
    }
    APCommMethod.paySuccCallBack(9, -1, -1);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.APHFPaySuccessActivity.BackGameClick
 * JD-Core Version:    0.6.0
 */