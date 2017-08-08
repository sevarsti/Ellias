package com.pay.login;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

class APLoginVerifyCodeActivity$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.loginvc.textchange", APDataInterface.singleton().getOrderInfo().saveType);
    APLoginVerifyCodeActivity.a(this.a);
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginVerifyCodeActivity.2
 * JD-Core Version:    0.6.0
 */