package com.pay.ui.common;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APCommMethod;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APHFPayActivity;

public class APHFPaySuccessActivity$BuyContinue
  implements View.OnClickListener
{
  public APHFPaySuccessActivity$BuyContinue(APHFPaySuccessActivity paramAPHFPaySuccessActivity)
  {
  }

  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.hfsucc.continue", APHFPaySuccessActivity.c(this.a), null, APHFPaySuccessActivity.b(this.a), null);
    if (this.a.orderInfo.isNumCanChange)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.a, APHFPayActivity.class);
      this.a.startActivity(localIntent);
      this.a.finish();
      return;
    }
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
 * Qualified Name:     com.pay.ui.common.APHFPaySuccessActivity.BuyContinue
 * JD-Core Version:    0.6.0
 */