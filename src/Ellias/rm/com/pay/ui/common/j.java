package com.pay.ui.common;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;
import com.pay.ui.channel.APMCardPayActivity;

final class j
  implements View.OnClickListener
{
  j(APMcardSuccessActivity paramAPMcardSuccessActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.telsucc.continue", APDataInterface.singleton().getOrderInfo().saveType);
    if (this.a.orderInfo.isNumCanChange)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.a, APMCardPayActivity.class);
      this.a.startActivity(localIntent);
      this.a.finish();
      return;
    }
    APUICommonMethod.popActivity();
    APMcardSuccessActivity.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.j
 * JD-Core Version:    0.6.0
 */