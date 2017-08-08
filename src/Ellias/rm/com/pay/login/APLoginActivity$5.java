package com.pay.login;

import android.view.View;
import android.view.View.OnClickListener;
import com.pay.data.orderInfo.APOrderInfo;
import com.pay.tool.APDataInterface;
import com.pay.tool.APDataReportManager;

class APLoginActivity$5
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    APDataReportManager.getInstance().insertData("sdk.login.cancel", APDataInterface.singleton().getOrderInfo().saveType, null, String.valueOf(APLoginActivity.b(this.a)), null);
    this.a.finish();
  }
}

/* Location:           D:\rm_src\classes_dex2jar\
 * Qualified Name:     com.pay.login.APLoginActivity.5
 * JD-Core Version:    0.6.0
 */