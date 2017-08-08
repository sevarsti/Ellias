package com.pay.ui.common;

import android.text.ClipboardManager;
import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

final class p
  implements View.OnClickListener
{
  p(APMcardSuccessActivity paramAPMcardSuccessActivity)
  {
  }

  public final void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.telsucc.copy", APDataInterface.singleton().getOrderInfo().saveType);
    ((ClipboardManager)this.a.getSystemService("clipboard")).setText(APDataInterface.singleton().getOrderInfo().orderId);
    APUICommonMethod.showToast(this.a, "复制成功");
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.ui.common.p
 * JD-Core Version:    0.6.0
 */